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
					<option value="free" selected>자유 게시판</option>
					<option value="travel">여행지 게시판</option>
				</select>
			</div>
			<div id="writes_bf">
				<c:set var="pageLength_bf" value="${MaxPage_bf}"/>
				<c:if test="${pageLength_bf != 0}">
					<table>
						<colgroup>
							<col width="8%">
							<col width="13%">
							<col width="45%">
							<col width="8%">
							<col width="8%">
							<col width="8%">
							<col width="5%">
							<col width="5%">
						</colgroup>
						<tr>
							<th>글 번호</th>
							<th>말 머리</th>
							<th>제목</th>
							<th>글쓴이</th>
							<th>작성일</th>
							<th>조회수</th>
							<th>추천</th>
							<th>삭제</th>
						</tr>
						<c:forEach var="bfList" items="${userMyPageBfWritesList}" varStatus="status">
							<tr>
								<td>${bfList.getBSubNum()}</td>
								<td>${bfList.getBSort()}</td>
								<td>
									<a href="/board/free/view/${bfList.getBNum()}">
										<c:set var="maxTitleLength_bf" value="30"/>
										<c:set var="title_bf" value="${bfList.getBTitle()}"/>
										<c:set var="titleLength_bf" value="${fn:length(title_bf)}"/>
										<c:choose>
											<c:when test="${titleLength_bf >= maxTitleLength_bf}">
												${fn:substring(title_bf, 0, maxTitleLength_bf)}${"..."}
											</c:when>
											<c:otherwise>
												${bfList.getBTitle()}
											</c:otherwise>
										</c:choose>
									</a>
								</td>
								<td>${bfList.getBUserId()}</td>
								<td>${bfList.getBCDate()}</td>
								<td>${bfList.getBCount()}</td>
								<td>${bfList.getBRcm()}</td>
								<td><a href="/mypage/bfwrites/delete/${bfList.getBNum()}"><img src="/images/myPage/remove.png"></a></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
				<c:if test="${pageLength_bf != 0}">
					<div class="wrap_board_num">
						<ul>
							<c:set var="pageNum" value="${myWritePageNum}"/>
							<c:choose>
	                		<c:when test="${pageLength_bf <= pageNum}">
	                			<c:set var="maxNum_bf" value="1"/>
	                		</c:when>
	                		<c:when test="${pageLength_bf % pageNum == 0}">
	                			<c:set var="maxNum_bf" value="${pageLength_bf / pageNum}"/>
	                		</c:when>
	                		<c:otherwise>
	                			<c:set var="maxNum_bf" value="${pageLength_bf / pageNum + 1}"/>
	                		</c:otherwise>
	                	</c:choose>
							<c:forEach var="i" begin="1" end="${maxNum_bf}" step="1">
								<c:choose>
			             		<c:when test="${myPageWriteNumColor == i}">
			             			<li><a id="pagenumcolor" href="/mypage/bfwrites/${i}">${i}</a></li>
			             		</c:when>
			             		<c:otherwise>
			             			<li><a href="/mypage/bfwrites/${i}">${i}</a></li>
			             		</c:otherwise>
	          				</c:choose>
							</c:forEach>
						</ul>
					</div>
				</c:if>
				<c:if test="${pageLength_bf == 0}">
					<span class="nullspan">게시글이 없습니다!</span>
				</c:if>
			</div>
		</div>
	</div>
	<%@include file="../footer.jsp" %>
	<script src="/js/myPage/myPage.js"></script>
</body>
</html>