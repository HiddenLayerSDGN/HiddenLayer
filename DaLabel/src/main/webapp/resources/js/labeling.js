var obj = {}

function select() {
	let v = document.getElementById('project_how').options[document
			.getElementById('project_how').selectedIndex].value
	if (v === '1') {
		obj['type'] = 'button'
		obj['info'] = []
		let typing = $('<input>').attr('id', 'typing')
		let btn = $('<input>').attr({
			type : 'button',
			value : '추가',
			id : 'added',
			onclick : 'add_btn()'
		})
		let tbl = $('<table></table>').attr('id', 'tbl')
		$('#space').append(tbl, typing, btn)
	} else {
		obj['type'] = 'input'
		$('#tbl').remove()
		$('#space').empty()
	}
}

function add_btn() {
	let res = $('<input>').attr({
		type : 'button',
		'class' : 'for_eachs',
		value : document.getElementById('typing').value,
	})
	let del = $('<input>').attr({
		type : 'button',
		value : '삭제',
		onclick : 'del_btn(this)',
	})
	let tr = $('<tr></tr>').append($('<td></td>').html(res), $('<td></td>').html(del))
	$('#tbl').append(tr)
	$('#typing').val('')
}

function del_btn(obj) {
	$(obj).parent().parent().remove()
}

function json_() {
	let arr = []
	if ($('#project_how').val() === '-1' || $('#bundle_data_type').val() === '-1') {
		return false
	}
	if ($('#project_title').val() === '' || $('#project_guide').val() === '') {
		return false
	}
	if ($('#project_how').val() === '0') {
		obj['type'] = 'input'
		$('#project_category').attr('value', JSON.stringify(obj))
		console.log($('#project_category').val())
		return true
	}
	$('.for_eachs').each((i, e) => {
		arr[i] = e.value;
	})
	obj['info'] = arr
	$('#project_category').attr('value', JSON.stringify(obj))
	console.log($('#project_category').val())
	if ($('#project_how').val() === '1' && obj['info'].length === 0) {
		return false
	}
	return true
}

function cancle() {
	location.href = "get.needlogin.my.bundle"
}

function changeAccessLevel() {
	var d = 0
	$('.access').each((a,b)=>{
		if(b.checked){
		d+=Number(b.value)}
		})
	if (d==16){
		d=0
	}
	if(d%2==1){
		d=1
	}
	$('#project_access_level').val(d)
}

function selectAll() {
	$('.access').each((a,b)=>{
		if(a!=0){
		b.disabled= !b.disabled}
	})
}

function loadProjectAccessLevel(val){
	if($('.access').length==0){
		return 0;
	}
	$('.access')[4].checked=true
	$('.access').each((a,b)=>{
		if(val==1){
			if(a!=0){
				b.disabled=true
			}
		}
		if(b.value&val){
			b.checked=true
		}
	})
}

function getReportedData(no, user) {
	$.ajax({
		type : "get",
		url : "data.needlogin.get.reported",
		data : {
			"data_where" : no
		},
		success : function(reportedData) {
			$.each(reportedData, function(index, reportedData2) {
				$("#reportedDataTable").append($("<tr><td></td></tr>")
						.append($("<img>").attr("src", "resources/data/" + user + "/" + reportedData2)))
			})
		},
		error : function() {
			alert("없음")
		}
	})
}