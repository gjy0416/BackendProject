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
	<link rel="stylesheet" href="/css/myPage/myPageReservations.css">
	<title>내 예약</title>
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
						<a href="/mypage/infochange">회원정보수정</a>
						<a href="/mypage/bfwrites">내 게시글</a>
						<a href="/mypage/reservations">내 댓글</a>
						<a href="/mypage/wants">찜 내역</a>
						<a id="selectedside" href="/mypage/reservations">예약 내역</a>
					</td>
				</tr>
			</table>
		</div>
		<div id="reservations">
			<div id="reservations_header">
				<span>내 예약내역</span>
			</div>
			<div id="reservations_content">
				<c:set var="rsvPageLength" value="${myRsvPageNum}"/>
				<c:if test="${rsvPageLength != 0}">
					<table>
						<colgroup>
							<col width="20%">
							<col width="12%">
							<col width="25%">
							<col width="15%">
							<col width="8%">
							<col width="13%">
							<col width="7%">
						</colgroup>
						<tr>
							<th>예약한 날짜</th>
							<th>지역</th>
							<th>상품 이름</th>
							<th>구분</th>
							<th>예약인원</th>
							<th>가격</th>
							<th>예약 취소</th>
						</tr>
						<c:set var="maxContentLength" value="30"/>
						<c:set var="contentLength" value="${fn:length(rsvList.getRContent())}"/>
						<c:forEach var="rsvList" items="${myReservations}" varStatus="status">
							<tr>
								<td>${rsvList.getRRDate()}</td>
								<td>${rsvList.getRRegionName()}</td>
								<td>${rsvList.getRTitle()}</td>
								<td>${rsvList.getRSort()}</td>
								<td>${rsvList.getRPeopleNum()}명</td>
								<td>${rsvList.getRPrice()}원</td>
								<td>
									<a href="/mypage/reservations/delete/${rsvList.getRRsvNum()}?rUserNum=${rsvList.getRUserNum()}" ><img src="/images/myPage/remove.png"></a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
				<c:if test="${rsvPageLength != 0}">
					<div class="wrap_board_num">
						<ul>
							<c:set var="rsvPageNum" value="${myRsvMaxPageNum}"/>
							<c:choose>
	                		<c:when test="${rsvPageLength <= rsvPageNum}">
	                			<c:set var="rsvMaxNum" value="1"/>
	                		</c:when>
	                		<c:when test="${rsvPageLength % rsvPageNum == 0}">
	                			<c:set var="rsvMaxNum" value="${rsvPageLength / rsvPageNum}"/>
	                		</c:when>
	                		<c:otherwise>
	                			<c:set var="rsvMaxNum" value="${(rsvPageLength / rsvPageNum) + 1}"/>
	                		</c:otherwise>
	                	</c:choose>
							<c:forEach var="i" begin="1" end="${rsvMaxNum}" step="1">
								<c:choose>
			             		<c:when test="${myPageRsvPageColor == i}">
			             			<li><a id="pagenumcolor" href="/mypage/reservations/${i}">${i}</a></li>
			             		</c:when>
			             		<c:otherwise>
			             			<li><a href="/mypage/reservations/${i}">${i}</a></li>
			             		</c:otherwise>
	          				</c:choose>
							</c:forEach>
						</ul>
					</div>
				</c:if>
				<c:if test="${rsvPageLength == 0}">
					<span class="nullspan">예약 내역이 없습니다!</span>
				</c:if>
			</div>
		</div>
	</div>
	<%@include file="../footer.jsp" %>
	<script src="/js/myPage/myPage.js"></script>
</body>
</html>