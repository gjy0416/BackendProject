<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="/css/myPage/myPageInfoChange.css">
	<title>내 찜 내역</title>
</head>
<body>
	<%@include file="myPage.jsp" %>
	<div id="content">
		<div id="wrap_sidemenu">
			<table id="sidemenu">
				<tr>
					<th>
						<span>MY PAGE</span>
					</th>
				</tr>
				<tr>
					<td>
						<a id="selectedside" href="/mypage/infochange">회원정보수정</a>
						<a href="/mypage/bfwrites">내 게시글</a>
						<a href="/mypage/coms">내 댓글</a>
						<a href="">찜 내역</a>
						<a href="/mypage/reservations">예약 내역</a>
					</td>
				</tr>
			</table>
		</div>
		<div id="info_change">
			<div id="info_change_header">
				<span>회원정보수정</span>
			</div>
			<div id="info_change_content">
				<form id="fileform" method="post" action="/mypage/infochange/change" enctype="multipart/form-data" onsubmit="return myPageChangeCheck()">
					<div id="wrap_content">
						<span>비밀번호 변경</span>
						<label class="input">비밀번호</label><input id="pwd1" name="PASSWORD" type="password"><br>
						<label class="input">비밀번호 확인</label><input id="pwd2" type="password">
						<span>프로필 사진 변경</span>
						<input id="imgCon" type="text" disabled><label for="file">이미지 선택</label>
						<input id="file" type="file" name="profileImg" style="display:none" onchange="return fileAdd(this)" accept=".png, .jpg, .jpeg">
						<span>이메일 주소 변경</span>
						<input id="email1" name="email1" type="text"> @ <input id="email2" class="input" type="text" name="email2">
						<select id="eSelect" onchange="emailSelect()">
							<option>직접 쓰기</option>
							<option>naver.com</option>
							<option>daum.net</option>
							<option>google.com</option>
						</select>
						<input type="hidden" id="sumEmail" name="EMAIL" value="">
					</div>
					<div id="wrap_button">
						<button type="button" onclick="refreshInfo()">초기화</button><button type="submit">적용</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@include file="../footer.jsp" %>	
	<script src="/js/myPage/myPage.js"></script>
</body>
</html>