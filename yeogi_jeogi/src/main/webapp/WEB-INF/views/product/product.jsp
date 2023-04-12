
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Gothic+A1&display=swap"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/product/product.js"></script>
<link rel="stylesheet" href="css/product/product.css">
<title>여행 상품</title>
</head>
<script>
  // 모달 열기
  function openModal(modalId) {
    document.getElementById(modalId).style.display = "block";
  }

  // 모달 닫기
  function closeModal(modalId) {
    document.getElementById(modalId).style.display = "none";
  }
</script>
<body>
	<%@ include file="../header.jsp" %>
	<div id="container">
		<!-- body -->
		<div id="wrap_content_header">
			<div id="content_header">
				<a href="">여행 상품</a>
			</div>
		</div>
		<div class="product_box">
			<div class="product_line">
				<h2>지역별 추천 상품 둘러보기★</h2>
				<h5>홈에서 가고 싶은 지역을 검색해보세요!</h5>
				<div id="product_area">
					<input type="hidden" name="lastPage" value="${lastPage}"> <input
						type="hidden" name="listA" value="1"> <input type="hidden"
						name="listB" value="1"> <input type="hidden" name="listC"
						value="1"> <input type="hidden" name="lcId" value="">
					<input type="hidden" name="sdate" value=""> <input
						type="hidden" name="edate" value="">
					<div class="picture-list">
						<div class="picture-list-bar">
							<button class="button" id="left_button"
								onclick="left_button('listA')"><</button>
							<ul>
								<li style="width: 0px;"></li>
								<div class="listA">
									<c:forEach var="product1" items="${product1}"
										varStatus="status">
										<li><img src="${product1.img}">
											<div class="caption">
												<h3>${product1.title}</h3>
												<p class="place">${product1.price}| ${product1.sort}</p>
											</div></li>
									</c:forEach>
								</div>
							</ul>
							<button class="button" id="right_button"
								onclick="right_button('listA')">></button>
						</div>
					</div>
				</div>
			</div>

			<div class="product_line">
				<h2>꽃나들이 하러 함께 떠나요☆</h2>
				<h5>다채로운 삶을 꿈꾼다면 이런 체험은 어떨까요?</h5>
				<div id="product_area">
					<div class="picture-list">
						<div class="picture-list-bar">
							<button class="button" id="left_button"
								onclick="left_button('listB')"><</button>
							<ul>
								<li style="width: 0px;"></li>
								<div class="listB">
									<c:forEach var="product2" items="${product2}" varStatus="status">
									
										<li>
											<a href="javascript:openModal('myModalB${status.index}');">
												<img id="product_image_${status.index}" src="${product2.img}">
											</a>
											<div class="caption">
												<h3>${product2.title}</h3>
												<p class="place">${product2.price}| ${product2.sort}</p>
											</div>
											
											<img id="product_image_0" src="product_image_0.jpg">
											<div id="myModalB${status.index}" class="modal">

												<div class="modal-content">
													<a href="javascript:closeModal('myModalB${status.index}');">
														<span class="close">&times;</span>
													</a>
													<div class=mo_header>
														<p>여행 상품</p>
														<div class="imgcontainer">
															<img class=con1 src="${product2.img1}"> <img
																class=con1 src="${product2.img2}">
														</div>
														<div class="textcontainer">
															<p class="text_title">${product2.title}</p>
															<p class="text_con">
																${product2.content}
															</p>
														</div>
														<div class="detail">
															<table>
															<thead>
																<tr>
																	<th colspan="2">상품 정보</th>
																</tr>
															</thead>
															<tbody>
																<tr>
																	<td>상품 가격</td>
																	<td>${product2.price}~</td>
																</tr>
																<tr>
																	<td>예약 가능 기간</td>
																	<td>${product2.time}</td>
																</tr>
																<tr>
																	<td>이 상품 속 주요 여행지</td>
																	<td>${product2.local}</td>
																</tr>
															</tbody>
															</table>
														</div>
														<div id="b_button">
															<form method="post" action="/resveration/insert" enctype="multipart/form-data">
																<input type="hidden" name="pkgNo" />
																<button class="long-button" onclick="">장바구니</button>
																<button class="long-button" onclick="">구매하기</button>
															</form>
														</div>
													</div>
												  </div>
												</div>
											
												
											
										</li>
									</c:forEach>
								</div>
							</ul>
							<button class="button" id="right_button"
								onclick="right_button('listB')">></button>
						</div>
					</div>
				</div>
			</div>


			<div class="product_line">
				<h2>무료한 일상 탈출! 이색 체험 여행★</h2>
				<h5>다채로운 삶을 꿈꾼다면 이런 체험은 어떨까요?</h5>
				<div id="product_area">
					<div class="picture-list">
						<div class="picture-list-bar">
							<button class="button" id="left_button"
								onclick="left_button('listC')"><</button>
							<ul>
								<li style="width: 0px;"></li>
								<div class="listC">
									<c:forEach var="product3" items="${product3}" varStatus="status">
										<li>
											<a href="javascript:openModal('myModalC${status.index}');">
												<img id="product_image_${status.index}" src="${product3.img}">
											</a>
											<div class="caption">
												<h3>${product3.title}</h3>
												<p class="place">${product3.price}| ${product3.sort}</p>
											</div>
											
											<img id="product_image_0" src="product_image_0.jpg">
											<div id="myModalC${status.index}" class="modal">

												<div class="modal-content">
													<a href="javascript:closeModal('myModalC${status.index}');">
														<span class="close">&times;</span>
													</a>
													<div class=mo_header>
														<p>여행 상품</p>
														<div class="imgcontainer">
															<img class=con1 src="${product3.img1}"> <img
																class=con1 src="${product3.img2}">
														</div>
														<div class="textcontainer">
															<p class="text_title">${product3.title}</p>
															<p class="text_con">
																${product3.content}
															</p>
														</div>
														<div class="detail">
															<table>
															<thead>
																<tr>
																	<th colspan="2">상품 정보</th>
																</tr>
															</thead>
															<tbody>
																<tr>
																	<td>상품 가격</td>
																	<td>${product3.price}~</td>
																</tr>
																<tr>
																	<td>예약 가능 기간</td>
																	<td>${product3.time}</td>
																</tr>
																<tr>
																	<td>이 상품 속 주요 여행지</td>
																	<td>${product3.local}</td>
																</tr>
															</tbody>
															</table>
														</div>
<%-- 														'${product3.pNo}' --%>
														
														<div id="b_button">
															<form method="post" action="/resveration/insert" enctype="multipart/form-data">
																<input type="hidden" name="pkgNo" />
																<button class="long-button" onclick="">장바구니</button>
																<button class="long-button" onclick="">구매하기</button>
															</form>
														</div>
													</div>
												  </div>
												</div>
											
											
											
										</li>
									</c:forEach>
								</div>
							</ul>
							<button class="button" id="right_button"
								onclick="right_button('listC')">></button>
						</div>
					</div>
				</div>
			</div>        	
			<script>
			  // 각각의 모달을 위한 변수들 생성
// 			  var modals = document.getElementsByClassName("modal");
// 			  var modalImgs = document.getElementsByClassName("modal-content");
// 			  var modalCaptions = document.getElementsByClassName("modal-caption");
// 			  var closeButtons = document.getElementsByClassName("close");

// 			  // li마다 모달을 띄우는 함수
// 			  function showModal(index) {
// 			    modals[index].style.display = "block"; // 해당 모달을 띄움
// 			    modalImgs[index].src = document.getElementById("product_image_" + index).src;
// 			  }

// 			  // li마다 모달을 닫는 함수
// 			  function hideModal(index) {
// 			    modals[index].style.display = "none"; // 해당 모달을 닫음
// 			  }

// 			  // 각각의 li에 클릭 이벤트 추가
// 			  for (var i = 0; i < modals.length; i++) {
// 			    // 해당 li의 index 값을 활용하여 showModal 함수 실행
// 			    document.getElementById("product_image_" + i).addEventListener("click", function(i) {
// 			      showModal(i);
// 			    }.bind(null, i));
// 			    // 해당 li의 index 값을 활용하여 hideModal 함수 실행
// 			    closeButtons[i].addEventListener("click", function(i) {
// 			      hideModal(i);
// 			    }.bind(null, i));
// 			    // 해당 모달의 ID 값을 활용하여 closeModal 함수 실행
// 			    document.getElementById("myModal" + i).addEventListener("click", function(i) {
// 			      hideModal(i);
// 			    }.bind(null, i));
// 			  }
				</script>
		</div>
		<%@include file="../footer.jsp" %>
	</div>
</body>
</html>