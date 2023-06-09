function labeling_do_loads(x, y) {
    $.ajax({
        type: "get",
        url: "doLabeling.needlogin.show",
        data: {
            "start": x,
            "end": y
        },
        success: function(project) {
            let lastvalue = -1;
            $.each(project, function(index, project2) {
                lastvalue = project2.project_no
                let flag = 0;
                if (project2.dolabel_state == "3") {
                    return true;
                } else if (project2.dolabel_state == "\u0000") {
                    project2.dolabel_state = "미신청";
                    flag = 1;
                } else if (project2.dolabel_state == "0") {
                    project2.dolabel_state = "수락대기중"
                } else if (project2.dolabel_state == "1") {
                    project2.dolabel_state = "수락됨";
                    flag = 2;
                } else if (project2.dolabel_state == "4") {
                    project2.dolabel_state = "초대됨"
                } else if (project2.dolabel_state == "2") {
                	project2.dolabel_state = "거절됨"
                } else if (project2.dolabel_state == "3") {
                	project2.dolabel_state = "밴당함"
                }
                var titleTd = ($("<h5></h5>").attr("class", "u-align-left u-custom-font u-font-ubuntu u-text u-text-default manage-text-palette-1-base u-text-3 labeling-text-3").text(project2.project_title))
                var requestorTd = ($("<p></p>").attr("class", "u-align-left u-text u-text-grey-40 u-text-4").attr("id", "labeling-progress").text("요청자 : " + project2.project_requestor).css("margin-bottom", 0))
                if (project2.project_how == 0) {
                    project_how = '# 직접 타이핑'
                } else if (project2.project_how == 1) {
                    project_how = '# 버튼 누르기'
                }
                var howTd = ($("<p></p>").attr("class", "u-align-left u-text u-text-grey-40 u-text-4").text(project_how))
                var stateTd = ($("<p></p>").attr("class", "u-align-left u-text u-text-grey-40 u-text-4").attr("id", "labeling-progress").text("현재 상태 : " + project2.dolabel_state).css("margin-top", 0))
                var titleDiv = ($("<div></div>").attr("class", "titleDiv").append(titleTd))
                var div = $("<div></div>").attr("class", "u-align-left u-container-align-center u-container-style manage-list-item u-radius-20 manage-repeater-item u-shape-round u-white manage-list-item-1")
                .append(
                    $("<div></div>").attr("id", "projectTbl").attr("class", "u-container-layout u-container-layout-1 manage-container-layout u-similar-container manage-container-layout-1")
                    .append(titleDiv, howTd, requestorTd, stateTd))
                $("#projectsontainer").append(div
                    .on("click", function() {
                        location.href = "doLabeling.needlogin.showIntro?project_no=" +
                            project2.project_no + "&flag=" + flag
                    }))
            })

            if (project.length < y) {
                $("button").remove(".loadBt")
            } else {
                $(".loadBt").attr("onclick", "labeling_do_loads(" + lastvalue + "," + y + ");");
            }
        },
        error: function() {
            $("button").remove(".loadBt")
        }
    })
}

function my_labeling_load(x, y) {
    $.ajax({
        type: "get",
        url: "doLabeling.needlogin.mypage.find",
        data: {
            "start": x,
            "end": y
        },
        success: function(myLabeling) {
        	$("#my_labeling").empty();
            $.each(myLabeling, function(index, myLabeling2) {
                if (myLabeling2.dolabel_state == 3) {
                    return true;
                }
                var pArea = $("<p></p>").attr("class", "u-align-left u-text u-text-grey-40 u-text-4").attr("id", "labeling-progress")
                var tbinf = $("<div></div>").attr("class", "u-align-left u-container-align-center u-container-style manage-list-item u-radius-20 manage-repeater-item u-shape-round u-white manage-list-item-1");
                var titleArea = $("<h5></h5>").attr("class", "u-align-left u-custom-font u-font-ubuntu u-text u-text-default manage-text-palette-1-base u-text-3 labeling-text-3").text(myLabeling2.project_title).css("margin-bottom", 0)
                var tableArea = $("<div></div>").attr("id", "projectTbl").attr("class", "u-container-layout u-container-layout-1 manage-container-layout u-similar-container manage-container-layout-1")
                tbinf.append(tableArea.append(titleArea));
                tbinf.append(pArea.text(myLabeling2.dolabel_user));
                if (myLabeling2.dolabel_state == 0) {
                    tableArea.append(pArea.text("상태 : 수락대기중"));
                } else if (myLabeling2.dolabel_state == 2 || myLabeling2.dolabel_state == 3) {
                	tableArea.append(pArea.text("상태 : 참여불가"))
                } else if (myLabeling2.dolabel_score != null) {
                    tableArea.append(pArea.text("스코어 : " + dolabel_score));
                } else if (myLabeling2.dolabel_state == 1) {
                    tableArea.append(pArea.text("상태 : 수락됨")).append("<button onclick=\"location.href='doLabeling.needlogin.start?project_no=" + myLabeling2.dolabel_project_no + "'\">라벨링 시작하기</button>" + "</td></tr>");
                } else if (myLabeling2.dolabel_state == 4) {
                    tableArea.append(pArea.text("상태 : ").append(
                        "<button>초대받기</button>" +
                        "</td></tr>"))
                }

                $("#my_labeling").append(tbinf);
            })
        },
        error: function() {
            alert("에러")
        }
    })
}