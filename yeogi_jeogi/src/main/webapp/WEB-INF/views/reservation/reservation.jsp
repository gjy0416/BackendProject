<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/reservation/reservation.css">
    <title>여행 상품</title>
</head>
<body>
  <div id="container">
    <!-- header -->
    <%@include file="../header.jsp" %>
    <!-- body -->
    <div id="wrap_content_header">
      <div id="content_header">
        <a href="">예약 내역</a>
      </div>
      <div class="reservation_box">
        <h3>장바구니</h3>
        <table class="size-table">
          <tbody>
              <tr>
                <th>날짜<img src="images/reservation/gray_icon.JPG"></th>
                <th>|</th>
                <th>상품정보</th>
                <th>|</th>
                <th>이용날짜</th>
                <th>|</th>
                <th>현재상태</th>
              </tr>
              <tr> <td></td></tr>
              <tr> <td></td></tr>
              <tr> <td></td></tr>
              <tr> <td></td></tr>
              <tr> <td></td></tr>
              <tr> <td></td></tr>
          </tbody>
        </table>
      </div>
      <div class="reservation_box">
        <h3>예약내역</h3>
        <table class="size-table">
          <tbody>
              <tr>
                <th>날짜<img src="images/reservation/gray_icon.JPG"></th>
                <th>|</th>
                <th>상품정보</th>
                <th>|</th>
                <th>이용날짜</th>
                <th>|</th>
                <th>현재상태</th>
              </tr>
              <tr> <td></td></tr>
              <tr> <td></td></tr>
              <tr> <td></td></tr>
              <tr> <td></td></tr>
              <tr> <td></td></tr>
              <tr> <td></td></tr>
          </tbody>
        </table>
      </div>
    </div>  
	 <%@include file="../footer.jsp" %>
  </div>
</body>
</html>