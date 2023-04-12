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
	<link rel="stylesheet" href="/css/myPage/myPageWrites.css">
	<title>내 게시글</title>
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
						<a id="selectedside" href="/mypage/bfwrites">내 게시글</a>
						<a href="/mypage/coms">내 댓글</a>
						<a href="/mypage/wants">찜 내역</a>
						<a href="/mypage/reservations">예약 내역</a>
					</td>
				</tr>
			</table>
		</div>
		<div id="writes">
			<div id="writes_header">
				<span>내 게시글</span>
				<select id="pagechanger" onchange="myPageWritesChange()">
					<option value="free">자유 게시판</option>
					<option value="travel" selected>여행지 게시판</option>
				</select>
			</div>
			<div id="writes_bt">
				<c:set var="pageLength_bt" value="${MaxPage_bt}"/>
				<c:if test="${pageLength_bt != 0}">
					<table>
						<colgroup>
							<col width="10%">
							<col width="50%">
							<col width="10%">
							<col width="10%">
							<col width="10%">
							<col width="5%">
							<col width="5%">
						</colgroup>
						<tr>
							<th>글 번호</th>
							<th>제목</th>
							<th>글쓴이</th>
							<th>작성일</th>
							<th>조회수</th>
							<th>추천</th>
							<th>삭제</th>
						</tr>
						<c:forEach var="btList" items="${userMyPageBtWritesList}" varStatus="status">
							<tr>
								<td>${btList.getBSubNum()}</td>
								<td>
									<a href="/board/travel/view/${btList.getBNum()}">
										<c:set var="maxTitleLength_bt" value="30"/>
										<c:set var="title_bt" value="${btList.getBTitle()}"/>
										<c:set var="titleLength_bt" value="${fn:length(title_bt)}"/>
										<c:choose>
											<c:when test="${titleLength_bt >= maxTitleLength_bt}">
												${fn:substring(title_bt, 0, maxTitleLength_bt)}${"..."}
											</c:when>
											<c:otherwise>
												${btList.getBTitle()}
											</c:otherwise>
										</c:choose>
									</a>
								</td>
								<td>${btList.getBUserId()}</td>
								<td>${btList.getBCDate()}</td>
								<td>${btList.getBCount()}</td>
								<td>${btList.getBRcm()}</td>
								<td><a href="/mypage/btwrites/delete/${btList.getBNum()}"><img src="/images/myPage/remove.png"></a></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
				<c:if test="${pageLength_bt != 0}">
					<div class="wrap_board_num">
						<ul>
							<c:set var="pageNum" value="${myWritePageNum}"/>
							<c:choose>
	                		<c:when test="${pageLength_bt <= pageNum}">
	                			<c:set var="maxNum_bt" value="1"/>
	                		</c:when>
	                		<c:when test="${pageLength_bt % pageNum == 0}">
	                			<c:set var="maxNum_bt" value="${pageLength_bt / pageNum}"/>
	                		</c:when>
	                		<c:otherwise>
	                			<c:set var="maxNum_bt" value="${pageLength_bt / pageNum + 1}"/>
	                		</c:otherwise>
	                	</c:choose>
							<c:forEach var="i" begin="1" end="${maxNum_bt}" step="1">
								<c:choose>
			             		<c:when test="${myPageWriteNumColor == i}">
			             			<li><a id="pagenumcolor" href="/mypage/btwrites/${i}">${i}</a></li>
			             		</c:when>
			             		<c:otherwise>
			             			<li><a href="/mypage/btwrites/${i}">${i}</a></li>
			             		</c:otherwise>
	          				</c:choose>
							</c:forEach>
						</ul>
					</div>
				</c:if>
				<c:if test="${pageLength_bt == 0}">
					<span class="nullspan">게시글이 없습니다!</span>
				</c:if>
			</div>
		</div>
	</div>
	<%@include file="../footer.jsp" %>
	<script src="/js/myPage/myPage.js"></script>
</body>
</html>