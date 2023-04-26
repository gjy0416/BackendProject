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
	<link rel="stylesheet" href="/css/myPage/myPage.css">
	<title>Insert title here</title>
</head>
<body>
	<%@include file="../header.jsp" %>
	<div id="mypage_bar">
		<div>
			<span>마이페이지</span>
		</div>
	</div>
	<div id="myinfo">
		<div id="info_left">
			<img src="${changeableInfo.getIMG_ADD()}">
			<div>
				<span>${principal.getUsername()}님 반갑습니다!</span>
				<span>내 MBTI: ${changeableInfo.getMBTI()}</span>
			</div>
		</div>
		<table>
			<tr>
				<td>내 게시글</td>
				<td>내 댓글</td>
				<td>찜 내역</td>
				<td>예약 내역</td>
			</tr>
			<tr>
				<td>${userMyPageInfo.getMpWrites()}</td>
				<td>${userMyPageInfo.getMpComments()}</td>
				<td>${userMyPageInfo.getMpTravels() + userMyPageInfo.getMpPackages() + userMyPageInfo.getMpFestivals()}</td>
				<td>${userMyPageInfo.getMpReservations()}</td>
			</tr>
		</table>
	</div>
</body>
</html>