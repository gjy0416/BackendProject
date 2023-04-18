<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/board/boardWrite.css" type="text/css">
	 <script type="text/javascript" src="/smarteditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
    <title>글 쓰기</title>
</head>
<body>
    <div id="container">
        <%@include file="../header.jsp" %>
        <div id="content">
        		<form method="post" action="/board/${boardType}/write/add" enctype="multipart/form-data" onsubmit="return blankCheck()">
	            <div id="content_header">
	                <span>제목 : </span><input id="bTitle" type="text" placeholder="제목을 입력하세요." name="bTitle">
	                <select name="bSort">
	                	<option>후기</option>
	                	<option>asdad</option>
	                </select>
	            </div>
	            <textarea id="content_text" name="bContent" placeholder="내용을 입력해주세요" style="width: 100%; min-height: 500px;"></textarea>
	            <input id="test1" type="hidden" name="bUserId" value="${principal.getlUserId()}">
	            <div id="wrap_button">
	                <button type="submit">글 등록하기</button>
	            </div>
            </form>
        </div>
        <%@include file="../footer.jsp" %>
    </div>
    <script src="/js/board/boardWrite.js"></script>
</body>
</html>