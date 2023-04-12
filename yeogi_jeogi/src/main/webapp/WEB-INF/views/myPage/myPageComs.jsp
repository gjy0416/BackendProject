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
	<link rel="stylesheet" href="/css/myPage/myPageComs.css">
	<title>내 댓글</title>
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
						<a id="selectedside" href="/mypage/coms">내 댓글</a>
						<a href="/mypage/wants">찜 내역</a>
						<a href="/mypage/reservations">예약 내역</a>
					</td>
				</tr>
			</table>
		</div>
		<div id="coms">
			<div id="coms_header">
				<span>내 댓글</span>
			</div>
			<div id="coms_content">
				<c:set var="comsPageLength" value="${myComPageNum}"/>
				<c:if test="${comsPageLength != 0}">
					<table>
						<colgroup>
							<col width="10%">
							<col width="15%">
							<col width="50%">
							<col width="15%">
							<col width="5%">
							<col width="5%">
						</colgroup>
						<tr>
							<th>글 번호</th>
							<th>게시판 구분</th>
							<th>댓글 내용</th>
							<th>작성(수정)일</th>
							<th>추천</th>
							<th>삭제</th>
						</tr>
						<c:set var="maxContentLength" value="30"/>
						<c:set var="contentLength" value="${fn:length(comList.getCcontent())}"/>
						<c:forEach var="comList" items="${myComList}" varStatus="status">
							<tr>
								<td>${comList.getCBSubNum()}</td>
								<c:choose>
									<c:when test="${comList.getCIsBF() == 1}">
										<td>자유</td>
										<td>
											<a href="/board/free/view/${comList.getCBNum()}">
									</c:when>
									<c:otherwise>
										<td>여행지</td>
										<td>
											<a href="/board/travel/view/${comList.getCBNum()}">
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${contentLength >= maxContentLength}">
										${fn:substring(comList.getCContent(), 0, maxContentLength -3)}${"..."}
									</c:when>
									<c:otherwise>
										${comList.getCContent()}
									</c:otherwise>
								</c:choose>
									</a>
								</td>
								<c:choose>
									<c:when test="${comList.getCMDate() == null}">
										<td>${comList.getCCDate()}</td>
									</c:when>
									<c:otherwise>
										<td>${comList.getCMDate()}(수정)</td>
									</c:otherwise>
								</c:choose>
								<td>${comList.getCGCount()}</td>
								<td>
									<a href="/mypage/coms/delete/${comList.getCNum()}?num=${comList.getCUserNum()}" ><img src="/images/myPage/remove.png"></a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
				<c:if test="${comsPageLength != 0}">
					<div class="wrap_board_num">
						<ul>
							<c:set var="comsPageNum" value="${myComMaxPageNum}"/>
							<c:choose>
	                		<c:when test="${comsPageLength <= comsPageNum}">
	                			<c:set var="comsMaxNum" value="1"/>
	                		</c:when>
	                		<c:when test="${comsPageLength % comsPageNum == 0}">
	                			<c:set var="comsMaxNum" value="${comsPageLength / comsPageNum}"/>
	                		</c:when>
	                		<c:otherwise>
	                			<c:set var="comsMaxNum" value="${comsPageLength / comsPageNum + 1}"/>
	                		</c:otherwise>
	                	</c:choose>
							<c:forEach var="i" begin="1" end="${comsMaxNum}" step="1">
								<c:choose>
			             		<c:when test="${myPageComsPageColor == i}">
			             			<li><a id="pagenumcolor" href="/mypage/coms/${i}">${i}</a></li>
			             		</c:when>
			             		<c:otherwise>
			             			<li><a href="/mypage/coms/${i}">${i}</a></li>
			             		</c:otherwise>
	          				</c:choose>
							</c:forEach>
						</ul>
					</div>
				</c:if>
				<c:if test="${comsPageLength == 0}">
					<span class="nullspan">댓글 내역이 없습니다!</span>
				</c:if>
			</div>
		</div>
	</div>
	<%@include file="../footer.jsp" %>
	<script src="/js/myPage/myPage.js"></script>
</body>
</html>