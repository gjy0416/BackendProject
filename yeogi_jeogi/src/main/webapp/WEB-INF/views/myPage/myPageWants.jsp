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
	<link rel="stylesheet" href="/css/myPage/myPageWants.css">
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
						<a href="/mypage/infochange">회원정보수정</a>
						<a href="/mypage/bfwrites">내 게시글</a>
						<a href="/mypage/coms">내 댓글</a>
						<a id="selectedside" href="">찜 내역</a>
						<a href="/mypage/reservations">예약 내역</a>
					</td>
				</tr>
			</table>
		</div>
		<div id="wants">
			<div id="wants_header">
				<span>찜 내역</span>
				
				<select id="pagechanger" onchange="myPageChange();">
					<option value="travel" selected>여행지</option>
					<option value="festival">축제</option>
					<option value="package">여행 상품</option>
				</select>
			</div>
			<c:set var="travelLength" value="${fn:length(userMyPageTravelInfo)}"/>
			<c:set var="packageLength" value="${fn:length(userMyPagePackageInfo)}"/>
			<c:set var="festivalLength" value="${fn:length(userMyPageFestivalInfo)}"/>
			<script>
				function setWants() {
					document.getElementById('content').style.minHeight = '1200px';
					let sortLength = '<c:out value="${packageLength}"/>';
					let height = document.getElementById('content').style.minHeight;
					if (sortLength > 1) {
						document.getElementById('content').style.minHeight = parseInt(height.slice(0, -2)) + 500 + "px";
						console.log(document.getElementById('content').style.minHeight);
					}
				}
			</script>
			<div id="wants_content">
				<div id="wants_travel">
					<c:choose>
						<c:when test="${travelLength != 0}">
							<table>
								<c:forEach var="mptList" items="${userMyPageTravelInfo}" varStatus="status">
									<c:if test="${status.count % 4 == 1}">
										<tr>
									</c:if>
									<td>
										<a href="">
											<img class="table_backimg" src="${mptList.getMptImgAdd()}">
										</a>
										<div>
											<img class="icons" src="/images/festival/heart2.png"><span class="icontext">${mptList.getMptRcm()}</span><img class="icons" src="/images/festival/comments.png"><span class="icontext">--</span><img class="icons" src="/images/festival/hits.png"><span class="icontext">${mptList.getMptCount()}</span><br>
											<span class="blueregion">${mptList.getMptRName()}</span>
											<span class="travelname">
												<c:choose>
													  <c:when test="${fn:length(mptList.getMptName()) > 10}">
													  		${fn:substring(mptList.getMptName(), 0, 10)}${".."}
													  </c:when>
													  <c:otherwise>
													  		${mptList.getMptName()}
													  </c:otherwise>
												</c:choose>
											</span>
										</div>
									</td>
									<c:if test="${status.count % 4 == 0}">
										</tr>
									</c:if>
								</c:forEach>
							</table>
						</c:when>
						<c:otherwise>
							<span class="nolist">여행지 찜 내역이 없습니다!</span>
						</c:otherwise>
					</c:choose>
				</div>
				<div id="wants_package">
					<c:choose>
						<c:when test="${packageLength != 0}">
						<table>
							<c:set var="pi" value="1"/>
							<c:forEach var="mppList" items="${userMyPagePackageInfo}" varStatus="status">
								<c:if test="${status.count % 4 == 1}">
									<tr>
								</c:if>
								<td>
									<a href="">
										<img class="table_backimg" src="${mppList.getMppImgAdd()}">
									</a>
									<div>
										<img class="icons" src="/images/festival/heart2.png"><span class="icontext">${mppList.getMppRcm()}</span><img class="icons" src="/images/festival/comments.png"><span class="icontext">--</span><img class="icons" src="/images/festival/hits.png"><span class="icontext">${mppList.getMppCount()}</span><br>
										<span class="blueregion">${mppList.getMppRName()}</span>
										<span class="travelname">
											 <c:choose>
												  <c:when test="${fn:length(mppList.getMppTitle()) > 10}">
												  		${fn:substring(mppList.getMppTitle(), 0, 10)}${".."}
												  </c:when>
												  <c:otherwise>
												  		${mppList.getMppTitle()}
												  </c:otherwise>
											 </c:choose>
										</span>
									</div>
								</td>
								<c:if test="${status.count % 4 == 0}">
									</tr>
								</c:if>
							</c:forEach>
							</table>
						</c:when>
						<c:otherwise>
							<span class="nolist">여행 상품 찜 내역이 없습니다!</span>
						</c:otherwise>
					</c:choose>
				</div>
				<div id="wants_festival">
					<c:choose>
						<c:when test="${festivalLength != 0}">
							<c:set var="fi" value="1"/>
							<table>
							<c:forEach var="mpfList" items="${userMyPageFestivalInfo}" varStatus="status">
								<c:if test="${status.count % 4 == 1}">
									<tr>
								</c:if>
								<td>
									<a href="">
										<img class="table_backimg" src="${mpfList.getMpfImgAdd()}">
									</a>
									<div>
										<img class="icons" src="/images/festival/heart2.png"><span class="icontext">${mpfList.getMpfRcm()}</span><img class="icons" src="/images/festival/comments.png"><span class="icontext">--</span><img class="icons" src="/images/festival/hits.png"><span class="icontext">--</span><br>
										<span class="blueregion">${mpfList.getMpfRName()}</span>
										<span class="travelname">
											 <c:choose>
												  <c:when test="${fn:length(mpfList.getMpfTitle()) > 9}">
												  		${fn:substring(mpfList.getMpfTitle(), 0, 9)}${".."}
												  </c:when>
												  <c:otherwise>
												  		${mpfList.getMpfTitle()}
												  </c:otherwise>
											 </c:choose>
										</span>
									</div>
								</td>
								<c:if test="${status.count % 4 == 0}">
									</tr>
								</c:if>
							</c:forEach>
							</table>
						</c:when>
						<c:otherwise>
							<span class="nolist">축제 찜 내역이 없습니다!</span>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../footer.jsp" %>
	
	<script src="/js/myPage/myPage.js"></script>
</body>
</html>