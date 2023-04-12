<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gothic+A1&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/NewFile.css">
    
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<body>
	<input type="text" name="location_input" value="" placeholder="어디로 가실건가요?" readonly/>
	<div class="location_wrap" style="display: none;">
		<div class="loca_inner overseas">
			<div class="left_scrolling">
				<ul class="mainCate">
					<c:forEach var="province" items="${province}">
   						<li id="area_${province.lpId}" onclick="javascript:getAreaA('${province.lpId}');">${province.lpName}</li>
					</c:forEach>
				</ul>
			</div>
			<div class="scrolling_area left_scrolling">
				<div class="city_wrap">
					<ul>
						<c:forEach var="city" items="${city}">
	   						<li id="area_B_${city.lpId}"><a href="javascript:;"onclick="javascript:setAreaB('${city.lcId}','${city.lcName}');">${city.lcName}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>