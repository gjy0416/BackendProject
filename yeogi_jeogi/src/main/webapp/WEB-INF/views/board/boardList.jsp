<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <link rel="stylesheet" href="/css/board/boardList.css" type="text/css">
    <script src="/js/board/board.js"></script>
    <c:choose>
    	<c:when test="${boardType eq 'free'}">
    		<title>자유게시판</title>
    	</c:when>
    	<c:when test="${boardType eq 'travel'}">
    		<title>여행지게시판</title>
    	</c:when>
    	<c:otherwise>
    		<title>공지사항</title>
    	</c:otherwise>
    </c:choose>
</head>
<body>
    <div id="container">
        <%@include file="../header.jsp" %>
        <div id="content">
            <div id="wrap_content_header">
                <div id="content_header">
					 <script>
						 setBoardLabel();
						 function setBoardLabel() {
						 	let boardType = '<c:out value="${boardType}"/>';
						 	
						 	if (boardType == 'free'|| boardType == 'travel') {
						 		document.getElementById('content_header').innerHTML = '<input id="radio_1" type="radio" name="board_check" value="free">'
						 																				+'<input id="radio_2" type="radio" name="board_check" value="travel">'
						 														                  +'<label for="radio_1"><a href="/board/free">자유게시판</a></label>'
						 														                  +'<label for="radio_2"><a href="/board/travel">나만의 여행지</a></label>';
						 	}
						 	else {
						 		document.getElementById('content_header').innerHTML = '<input id="radio_1" type="radio" name="board_check" value="event">'
						 																				+'<input id="radio_2" type="radio" name="board_check" value="notice">'
						 														                  +'<label for="radio_1"><a href="/board/event">이벤트</a></label>'
						 														                  +'<label for="radio_2"><a href="/board/notice">공지사항</a></label>';
						 	}
						 	if (boardType == 'free') {
						 		document.getElementById('content_header').firstChild.setAttribute('checked', true);
						 	}
						 	else if (boardType == 'travel') {
						 		document.getElementById('content_header').firstChild.nextSibling.setAttribute('checked', true);
						 	}
						 	else if (boardType == 'notice') {
						 		document.getElementById('content_header').firstChild.nextSibling.setAttribute('checked', true);
						 	}
						 }
					 </script>
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
            <div id="board">
            	 <c:set var="maxPageNum" value="${allPageNum}"/>
            	 <c:choose>
	            	 <c:when test="${maxPageNum != 0}">
		                <table>
		                 	<colgroup>
		                 			<c:choose>
			                 			<c:when test="${boardType eq 'free'}">
				                        <col width="8%">
				                        <col width="13%">
				                        <col width="45%">
				                        <col width="13%">
				                        <col width="8%">
				                        <col width="8%">
				                        <col width="5%">
			                        </c:when>
			                        <c:otherwise>
			                        	<col width="10%">
				                        <col width="50%">
				                        <col width="15%">
				                        <col width="10%">
				                        <col width="10%">
				                        <col width="5%">
			                        </c:otherwise>
		                        </c:choose>
		                    </colgroup>
		                    <tr>
		                        <th>글 번호</th>
		                        <c:if test="${boardType eq 'free'}">
		                        <th>
		                            말 머리
<!-- 		                            <select name="sort" id="board_sort" onchange="sortChangeList()"> -->
<!-- 		                            	  <option>전체</option> -->
<!-- 		                                <option>후기</option> -->
<!-- 		                                <option>여행 추천</option> -->
<!-- 		                                <option>여행 친구 찾기</option> -->
<!-- 		                            </select> -->
		                        </th>
		                        </c:if>
		                        <th>제목</th>
		                        <th>글쓴이</th>
		                        <th>작성일</th>
		                        <th>조회수</th>
		                        <th>추천</th>
		                    </tr>
		                  </table>
		                  <table id="foreach">
			                   <colgroup>
		                 			<c:choose>
			                 			<c:when test="${boardType eq 'free'}">
				                        <col width="8%">
				                        <col width="13%">
				                        <col width="45%">
				                        <col width="13%">
				                        <col width="8%">
				                        <col width="8%">
				                        <col width="5%">
			                        </c:when>
			                        <c:otherwise>
			                        	<col width="10%">
				                        <col width="50%">
				                        <col width="15%">
				                        <col width="10%">
				                        <col width="10%">
				                        <col width="5%">
			                        </c:otherwise>
		                        </c:choose>
		                    	 </colgroup>
			                   <c:forEach var="bfList" items="${boardList}" varStatus="status">
			                     <tr>
			                      <td>${bfList.getBSubNum()}</td>
			                      <c:if test="${boardType eq 'free'}">
			                      	<td>${bfList.getBSort()}</td>
			                      </c:if>
			                      <td>
			                      	<a href="/board/${boardType}/view/${bfList.getBNum()}">
			                      		<c:set var="maxTitleLength" value="40"/>
			                      		<c:set var="title" value="${bfList.getBTitle()}"/>
			                       	<c:set var="titleLength" value="${fn:length(title)}"/>
			                       	<c:choose>
			                       		<c:when test="${titleLength >= maxTitleLength}">
			                       			${fn:substring(title, 0, maxTitleLength)}${"..."}
			                       		</c:when>
			                       		<c:otherwise>
			                       			${bfList.getBTitle()}
			                       		</c:otherwise>
			                       	</c:choose>
			                    	  </a>
			                      </td>
			                      <td>${bfList.getBUserId()}<img class="iconimg" src="${bfList.getBUserGradeImgAdd()}"></td>
			                      <td>${bfList.getBCDate()}</td>
			                      <td>${bfList.getBCount()}</td>
			                      <td>${bfList.getBRcm()}</td>
			              			</tr>
			              		</c:forEach>
		                </table>
	                </c:when>
	                <c:otherwise>
						 	 <span id="nonelist">게시글이 없습니다!</span>
						 </c:otherwise>
                </c:choose>
                <sec:authorize access="isAuthenticated()">
                <c:choose>
	              	 <c:when test="${principal.getlRole() eq 'MANAGER'}">
	             	 	 <a id="board_write" href="/board/${boardType}/write">관리자 글쓰기</a>
	              	 </c:when>
	              	 <c:when test="${principal.getlRole() eq 'USER'}">
	                 	 <c:if test="${boardType eq 'free' || boardType eq 'travel'}">
	             	 	      <a id="board_write" href="/board/${boardType}/write">글쓰기</a>
	             	 	  </c:if>
	              	 </c:when>
              	 </c:choose>
              	 </sec:authorize>
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
	             	<c:choose>
	             		<c:when test="${pageColor -5 <= 1}">
	             			<c:set var="minpage" value="1"/>
	             		</c:when>
	             		<c:otherwise>
	             			<c:set var="minpage" value="${pageColor -5}"/>
	             		</c:otherwise>
	             	</c:choose>
	             	<c:choose>
	             		<c:when test="${pageColor +5 <= maxNum}">
	             			<c:set var="maxpage" value="${pageColor +5}"/>
	             		</c:when>
	             		<c:otherwise>
	             			<c:set var="maxpage" value="${maxNum}"/>
	             		</c:otherwise>
	             	</c:choose>
	             	<c:forEach var="i" begin="${minpage}" end="${maxpage}" step="1">
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