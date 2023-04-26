<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/>
</sec:authorize>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gothic+A1&display=swap" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="/css/header.css">
</head>
<body>
    <div id="mini_menu_top_background">
       <div id="wrap_mini_menu_top">
           <div id="mini_menu_top">
               <ul>
              	 	 <sec:authorize access="isAnonymous()">
                  	 <li><a href="/loginform">로그인/회원가입</a></li>
						 </sec:authorize>
						 <sec:authorize access="isAuthenticated()">
                  	 <li><a href="/logout">로그아웃</a></li>
                   </sec:authorize>
                   <!-- <li><a href="../수정/Login.html">회원가입</a></li> -->
                   <li><a href="/mypage">마이페이지</a></li>
                   <li><a href="">고객센터</a></li>
               </ul>
           </div>
       </div>
    </div>
    <div id="header">
       <a href="/main"><img id="logo_header" src="/images/main/logo.png"></a>
       <div id="menu_main">
           <ul>
               <li><a href="/travel/region/50201">여행도우미</a></li>
               <li><a href="/festival">축제안내</a></li>
               <li><a href="/products">예매/예약</a></li>
               <li><a href="/board/free">자유/여행지 게시판</a></li>
               <li><a href="/board/event">이벤트/공지사항</a></li>
           </ul>
       </div>
       <div id="menu_right">
           <ul>
          	    <c:if test="${!empty principal}">
                   <li><a href="/mypage/wants"><img src="/images/main/heart.jpg"></a></li>
                </c:if>
                <li><a href=""><img src="/images/main/menubutton.jpg"></a></li>
           </ul>
       </div>
    </div>
</body>
</html>