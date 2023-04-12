<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!doctype html>
<html lang="ko">
<head>
    <meta charset="utf-8" />
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<!--     <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script> -->
<!--     <script src="https://unpkg.com/gijgo@1.9.14/js/gijgo.min.js" type="text/javascript"></script> -->
<!--     <link href="https://unpkg.com/gijgo@1.9.14/css/gijgo.min.css" rel="stylesheet" type="text/css" /> -->
    
<!-- 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"> -->
<!-- 	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous"> -->

	<script>
	$(document).ready(function() {
	    $("#datepicker").datepicker({
	        onSelect: function(dateText, inst) {
	            alert(dateText);
	        }
	        beforeShow: function (event, ui) {
	            var $link = $("#datep");
	            ui.dpDiv.offset({
	                top: $link.offset().top + 10,
	                left: $link.offset().left + 10
	            });
	        }
	    });
	
// 	$(function() {
// 		$('#datepicker').datepicker( {
// 	    	showOnFocus: true, 
// 	    	showRightIcon: false 
// 	    });
// 	});
	
	</script>
    
</head>
<body>
	<input type="text" id="datepicker">
<!-- 	<input type="text" id="gijgo"> -->
</body>
</html>