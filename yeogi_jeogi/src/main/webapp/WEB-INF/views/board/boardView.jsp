<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <link rel="stylesheet" href="/css/board/boardView.css">
    <script src="/js/board/board.js"></script>
    <title>${board.getBTitle()}</title>
</head>
<body>
    <div id="container">
        <%@include file="../header.jsp" %>
        <div id="board_bar">
        		<div>
	        		<c:choose>
	        			<c:when test="${boardType eq 'free'}">
	        				<span>자유게시판</span>
	        			</c:when>
	        			<c:when test="${boardType eq 'travel'}">
	        				<span>여행지게시판</span>
	        			</c:when>
	        			<c:otherwise>
	        				<span>공지사항</span>
	        			</c:otherwise>
	        		</c:choose>
        		</div>
        	</div>
        <div id="content">
            <div id="wrap_hm">
                <div id="ch_title">
                		<span>제목</span>
                		<span>${board.getBTitle()}</span>
                </div>
                <div id="ch_id_date">
                	<div id="wrap_grade">
	                  <span>
	                  	${board.getBUserId()}
	                  	<img class="gradeimg" src="${board.getBUserGradeImgAdd()}">
	                  </span>
                  </div>
                  <div id="wrap_count">
	                  <span>조회 ${board.getBCount()}</span>
	                  <span>${board.getBCDate()}</span>
                  </div>
                </div>
                <hr>
                <div id="content_main">
                    ${board.getBContent()}
                </div>
                <div id="rcm">
                	 <button id="rcm_up_button" type="button" onclick="rcm()"><img src="/images/board/like.png"></button>
                	 <span id="rcm_num">추천 수 : ${board.getBRcm()}</span>
                </div>
                <script>
                	 const bNum = '${board.getBNum()}';
                	 const rUserNum = '${principal.getlUserNum()}';
                	 console.log(rUserNum);
                	 function rcm() {
 							 if (${boardUserNum != null}) {
	                		 const param = JSON.stringify({
	                				 rBNum : bNum,
	                		 		 rUserNum : rUserNum
                		 	 });
                		 	 $.ajax({
                		 		 type: 'POST',
                		 		 url: '/board/${boardType}/rcm',
                		 		 data: param,
                		 		 contentType: 'application/json',
                		 		 success: 
                		 		 function(data) {
	                		 		 if (!data) {
	                		 			 
	                		 		 	 alert('추천 하였습니다');	 
	                		 		 	 $('#rcm').load(location.href+' #rcm');
	                    		 	 }
	                    		 	 else {
	                    		 		alert('이미 추천한 게시글입니다');
	                    		 	 }
                		 		 }
                		 	 }) 
                	 	 }
 							 else
 							 	 alert('로그인 후 이용할 수 있습니다!');
                	 }
                </script>
                <hr>
                <div id="comment">
                	  <p>댓글</p>
                	  <hr style="border-color:  rgba(128, 128, 128, 0.2);">
                    <div id="comment_list">
                    		<c:set var="comListChk" value="${fn:length(comList)}"/>
                    		<c:choose>
	                    		<c:when test="${comListChk != 0}">
		                	      <c:forEach var="comList" items="${comList}">
		                	      	<div class="comments">
			                	      	<div>${comList.getCUserId()}<img class="gradeimg" src="${board.getBUserGradeImgAdd()}"></div>
			                	      	<div>${comList.getCContent()}</div>
		                	      	</div>
		                	      	<hr style="border-color:  rgba(128, 128, 128, 0.2);">
		                	      </c:forEach> 
	                	      </c:when>
	                	      <c:otherwise>
	                	      	<span>댓글이 없습니다!</span>
	                	      	<hr>
	                	      </c:otherwise>
                	      </c:choose>
                	  </div>
                	  <span>${principal.getUsername()}</span>
	                	  <c:choose>
	                	  <c:when test="${principal != null}">
		                	  <div id="comment_write">
			                    <form method="post" action="/board/${boardType}/addcom" enctype="multipart/form-data" onsubmit="return comContentBlankCheck()">
			                    		<input type="hidden" name="cBNum" value="${board.getBNum()}">
			                        <textarea id="cContent" name="cContent" onkeydown="resizeTextArea(this)"></textarea>
			                        <button type="submit">댓글등록</button>
			                    </form>
		                    </div>
	                    </c:when>
	                    <c:otherwise>
		                    <div id="comment_write">
		                        <textarea disabled>댓글을 등록하려면 로그인을 해주세요.</textarea>
		                        <button type="button">로그인 필요</button>
		                    </div>
	                    </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <%@include file="../footer.jsp" %>
    </div>
</body>
</html>