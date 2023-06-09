<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<body
	data-home-page="https://website4844962.nicepage.io/MainPage.html?version=26955cdb-ad9a-4139-8054-aecbcc5786d7"
	data-home-page-title="MainPage" class="u-body u-xl-mode" data-lang="en">

	<section
		class="u-clearfix u-container-align-center-sm u-container-align-center-xs u-grey-5 u-section-1"
		id="carousel_6806">
		<div class="u-clearfix u-layout-wrap u-layout-wrap-1">
			<div class="u-layout">
				<div class="u-layout-row">
					<div
						class="u-container-style u-layout-cell u-size-60 u-layout-cell-1">
						<div class="u-container-layout u-container-layout-1">
							<div class="u-expanded-width member-info-form member-info-form-1">
								<a href="doLabeling.needlogin.mypage" type="button" class="boardBtn">참여중인 라벨링</a>
								<form action="member.needlogin.update.do" method="post"
									style="padding: 10px;">
									<div
										class="u-align-center member-info-form-group member-info-form-name">
										<h3>내 정보</h3>
									</div>
									<div class="member-info-form-group member-info-form-name">
										<label for="name-bb79" class="u-label">아이디</label> <input
											type="text" id="name-bb79" value="${memberInfo.user_id }"
											readonly class="u-border-none u-input u-input-rectangle">
									</div>
									<div class="member-info-form-email member-info-form-group">
										<label for="email-bb79" class="u-label">이메일</label> <input
											type="email" id="email-bb79" name="user_email"
											value="${memberInfo.user_email }"
											class="u-border-none u-input u-input-rectangle">
									</div>
									<div class="member-info-form-group member-info-form-name">
										<label for="name-bb79" class="u-label">이름</label> <input
											type="text" id="name-bb79" name="user_name"
											value="${memberInfo.user_name }"
											class="u-border-none u-input u-input-rectangle">
									</div>
									<div class="member-info-form-group member-info-form-name">
										<label for="name-bb79" class="u-label">비밀번호</label> <input
											type="password" id="name-bb79" name="user_pw"
											value="${memberInfo.user_pw }"
											class="u-border-none u-input u-input-rectangle">
									</div>
									<div class="member-info-form-group member-info-form-name">
										<label for="name-bb79" class="u-label">비밀번호 확인</label> <input
											type="password" id="name-bb79" value="${memberInfo.user_pw }"
											class="u-border-none u-input u-input-rectangle">
									</div>
									<div class="member-info-form-group member-info-form-name">
										<label for="name-bb79" class="u-label">생일</label> <input
											type="text" id="name-bb79" name="birth"
											value="<fmt:formatDate value="${memberInfo.user_birth }" pattern="yyyyMMdd"/>"
											class="u-border-none u-input u-input-rectangle">
									</div>
									<div class="member-info-form-group member-info-form-name" align="center">
										<label for="name-bb79" class="u-label">프로필</label><br> <img id="searchImg"
											src="resources/image/profile/${sessionScope.loginUserIMG }"
											alt="${sessionScope.loginUserIMG }"><br>
										<button type="button" onclick="fileUpload('try.needlogin.upload')" class="boardBtn" >프사 변경</button>
									</div>
									<div
										class="u-align-center member-info-form-group member-info-form-submit">
										<button
											 class="boardBtn" >수정</button>
										<button type="button"  class="boardBtn" 
											onclick="deleteMember();">탈퇴</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

</body>
</html>