<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gothic+A1&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/board/event.css" type="text/css">
    <script src="/js/board/board.js"></script>
    <title>이벤트</title>
</head>
<body>
    <div id="container">
        <%@include file="../header.jsp" %>
        <div id="content">
            <div id="wrap_content_header">
                <div id="content_header">
						 <input id="radio_1" type="radio" name="board_check" value="event" checked>
					 	 <input id="radio_2" type="radio" name="board_check" value="notice">
						 <label for="radio_1"><a href="/board/event">이벤트</a></label>
						 <label for="radio_2"><a href="/board/notice">공지사항</a></label>
                </div>
            </div>
            <div id="wrap_board_search">
                <div id="board_search">           
                		<select id="sSort">
                			 <option value="all" selected>전체</option>
                			 <option value="title">제목</option>
                			 <option value="content">내용</option>
                			 <option value="id">아이디</option>
                		</select>         
                    <input type="text" id="sWord">
                    <button id="searchA" onclick="setHref()"><img src="/images/board/search_icon.png"></button>
            	 </div>
           	</div>
           	<c:set var="bdList" value="boardList"/>
		      <div id="event">
		      	<c:set var="maxPageNum" value="${allPageNum}"/>
					<c:choose>
						<c:when test="${maxPageNum > 0}">
							<table>
								<c:forEach var="bdList" items="${boardList}" varStatus="status">
									<c:if test="${status.count % 3 == 1}">
										<tr>
									</c:if>
										<td class="event_box">
											<img src="${bdList.getBImgListAdd()}">
											<div>
												<span>${bdList.getBTitle()}</span>
												<span>${bdList.getBCDate()}</span>
											</div>
										</td>
										<c:if test="${status.count % 3 == 0}">
											</tr>
										</c:if>
								</c:forEach>
							</table>
						</c:when>
						<c:otherwise>
							<span id="nonelist">이벤트 게시물이 없습니다!</span>
						</c:otherwise>
					</c:choose>
					<c:if test="${principal.getlRole() eq 'MANAGER'}">
	         		 <a id="board_write" href="/board/${boardType}/write">관리자 글쓰기</a>
	         	</c:if> 	
	         </div>
            <c:if test="${maxPageNum != 0}">
	            <div id="wrap_board_num">
	            	<ul>
	             	<c:set var="pageNum" value="${pageNum}"/>
	             	<c:choose>
	             		<c:when test="${maxPageNum <= pageNum}">
	             			<c:set var="maxNum" value="1"/>
	             		</c:when>
	             		<c:when test="${maxPageNum % pageNum == 0 }">
	             			<c:set var="maxNum" value="${maxPageNum / pageNum}"/>
	             		</c:when>
	             		<c:otherwise>
	             			<c:set var="maxNum" value="${maxPageNum / pageNum + 1}"/>
	             		</c:otherwise>
	             	</c:choose>
	             	<c:forEach var="i" begin="1" end="${maxNum}" step="1">
	             		<c:choose>
		             		<c:when test="${pageColor == i}">
		             			<li><a id="pagenumcolor" href="/board/post/${i}">${i}</a></li>
		             		</c:when>
		             		<c:otherwise>
		             			<li><a href="/board/${boardType}/${i}">${i}</a></li>
		             		</c:otherwise>
	          			</c:choose>
	             	</c:forEach>
	            	</ul>
	            </div>
            </c:if>  
            
        </div>
        <%@include file="../footer.jsp" %>
    </div>
</body>
</html>