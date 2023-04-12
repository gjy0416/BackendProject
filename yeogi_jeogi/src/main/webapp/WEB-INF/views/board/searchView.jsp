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
    <link rel="stylesheet" href="/css/board/boardList.css" type="text/css">
    <title>자유게시판</title>
</head>
<body>
    <div id="container">
        <%@include file="../header.jsp" %>
        <div id="content">
            <div id="wrap_content_header">
                <span>검색 결과</span>
            </div>
            <div id="wrap_board_search">
                <div id="board_search">           
                		<select name="sSort">
                			 <option value="all" selected>전체</option>
                			 <option value="title">제목</option>
                			 <option value="content">내용</option>
                			 <option value="id">아이디</option>
                		</select>         
                    <input type="text" name="sWord" onchange="">
                    <button id="searchA" onclick="setHref()"><img src="/images/board/search_icon.png"></button>
            	 </div>
            </div>
            <div id="board">
            	 <c:set var="maxPageNum" value="${fn:length(searchBF)}"/>
            	 <c:choose>
	            	 <c:when test="${maxPageNum != 0}">
		                <table>
		                 	<colgroup>
		                        <col width="8%">
		                        <col width="13%">
		                        <col width="50%">
		                        <col width="8%">
		                        <col width="8%">
		                        <col width="8%">
		                        <col width="5%">
		                    </colgroup>
		                    <tr>
		                        <th>글 번호</th>
		                        <th>
		                            말 머리
		                            <select id="board_sort">
		                                <option>후기</option>
		                                <option>여행지 추천</option>
		                            </select>
		                        </th>
		                        <th>제목</th>
		                        <th>글쓴이</th>
		                        <th>작성일</th>
		                        <th>조회수</th>
		                        <th>추천</th>
		                    </tr>
		                    <c:forEach var="searchList" items="${searchBF}" varStatus="status">
		                     <tr>
		                      <td>${searchList.bSubNum}</td>
		                      <td>${searchList.bSort}</td>
		                      <td>
		                      	<a href="/bfpost/${searchList.bNum}">
		                      		<c:set var="maxTitleLength" value="30"/>
		                      		<c:set var="title" value="${searchList.bTitle}"/>
		                       	<c:set var="titleLength" value="${fn:length(title)}"/>
		                       	<c:choose>
		                       		<c:when test="${titleLength >= maxTitleLength}">
		                       			${fn:substring(title, 0, maxTitleLength -4)}${"..."}
		                       		</c:when>
		                       		<c:otherwise>
		                       			${searchList.bTitle}
		                       		</c:otherwise>
		                       	</c:choose>
		                    	  </a>
		                      </td>
		                      <td>${searchList.bUserId}</td>
		                      <td>${searchList.bCDate}</td>
		                      <td>${searchList.bCount}</td>
		                      <td>${searchList.bRcm}</td>
		              			</tr>
		              		</c:forEach>
		                </table>
	                </c:when>
	                <c:otherwise>
						 	 <span id="nonelist">게시글이 없습니다!</span>
						 </c:otherwise>
                </c:choose>
            </div>
        </div>
        <%@include file="../footer.jsp" %>
    </div>
</body>
</html>