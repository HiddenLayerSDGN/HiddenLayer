<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="get.needlogin.my.bundle">내 데이터 관리</a>
	<table>
		<tr>
			<td><c:forEach var="p" items="${projects }">
					<table border="1">
						<tr>
							<td>
								<form action="get.needlogin.detail.myproject">
									<input type="hidden" value="${p.project_no }" name="project_no">
									<button>${p.project_title }</button>
								</form>
							</td>
						</tr>	
					</table>
				</c:forEach></td>
		</tr>
		<tr>
			<td><c:forEach var="p" begin="1" end="${projectPageCount }">
					<a href="project.needlogin.page.change?page=${p }">${p }</a>
				</c:forEach></td>
		</tr>
	</table>
</body>
</html>