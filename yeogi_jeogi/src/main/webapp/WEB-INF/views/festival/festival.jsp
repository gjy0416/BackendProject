<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/festival/festival.css">
    <title>축제 안내</title>
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
  <div id="container">
   	<%@include file="../header.jsp" %>
   	<script type="text/javascript" src="/js/festival/festival.js"></script>
      <!-- body -->
      <div id="wrap_content_header">
        <div id="content_header">
            <a href="">축제 안내</a>
        </div>
      </div>
      <div class="festival_box">
			<div class="festival_left_box">
				<div class="total_check">
					<strong> 총 &nbsp;<span id="totalCnt">${totalCnt}</span>건</strong>
					<input type="hidden" name="page" value="1">
					<input type="hidden" name="lastPage" value="${lastPage}">
					<hr id="festival_left_hr">
				</div>
				
				<div class="list">
					<c:forEach var="festival" items="${festival}" varStatus="status">
<%-- 					<c:if test="${festival.start_month == }"> --%>
						<li>
							<ul class="festival_list">
								<li class="bdr_nor">
									<div class="photo">
										<a href="#myModal${status.count}" onclick="openModal('myModal${status.count}')"> <img
											src="${festival.img}" alt="2023 축제">
											<p class="text">진행전</p>
										</a>
									</div>
									<div class="festival_txt">
										<div class="tit">
											<a href="#myModal${status.count}" onclick="openModal('myModal${status.count}')">${festival.title}</a>
										</div>
										<p>${festival.content}</p>
										<p>[ ${festival.sdate} ~ ${festival.edate} ]</p>
										<p>
											<img src="images/festival/heart2.png">&nbsp;266&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<img src="images/festival/comments.png">&nbsp;10&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<img src="images/festival/hits.png">&nbsp; 39.6K
										</p>
									</div>
									<div id="myModal${status.count}" class="modal">
										<div class="modal-content">
											<span class="close" onclick="closeModal('myModal${status.count}')">&times;</span>
											<div class=mo_header>
												<p>축제 안내</p>
												<div class="imgcontainer">
													<img class=con1 src="${festival.img1}">
													<img class=con1 src="${festival.img2}">
												</div>
												<div class="textcontainer">
													<p class="text_title">${festival.title}</p>
													<p class="text_con">${festival.content}</p>
												</div>
												<div class="detail">
													<table>
														<thead>
															<tr>
																<th colspan="2">행사 정보</th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td>기간</td>
																<td>${festival.sdate}~ ${festival.edate}</td>
															</tr>
															<tr>
																<td>전화번호</td>
																<td>${festival.phone}</td>
															</tr>
															<tr>
																<td>주소</td>
																<td>${festival.addr1}</td>
															</tr>
															<tr>
																<td>행사장소</td>
																<td>${festival.addr2}</td>
															</tr>
															<tr>
																<td>이용요금</td>
																<c:choose>
																	<c:when test="${festival.pay eq '0'}">
																		<td>무료</td>
																	</c:when>
																	<c:otherwise>
																		<td>${festival.pay}</td>
																	</c:otherwise>
																</c:choose>
															</tr>
															<tr>
																<td>행사시간</td>
																<td>${festival.time}</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</li>
							</ul>
							<hr class="mini_hr">
						</li>
<%-- 						</c:if> --%>
					</c:forEach>
				</div>
			</div>

			<div class="festival_right_box">
          <hr>

          
          <div class="mb-3">
			<label for="region" class="form-label"></label>
			<div class="form_toggle row-vh d-flex flex-row justify-content-between" >
			
				<div class="form_radio_btn radio_region">
					<input id="radio-14" type="radio" name="city" value="">
					<label for="radio-14">#전체</label>
				</div>
				<div class="form_radio_btn">
					<input id="radio-15" type="radio" name="city" value="서울">
					<label for="radio-15">#서울</label>
				</div>
				<div class="form_radio_btn">
					<input id="radio-16" type="radio" name="city" value="경기">
					<label for="radio-16">#경기</label>
				</div>
				<div class="form_radio_btn">
					<input id="radio-17" type="radio" name="city" value="인천">
					<label for="radio-17">#인천</label>
				</div>
				<div class="form_radio_btn">
					<input id="radio-18" type="radio" name="city" value="대전">
					<label for="radio-18">#대전</label>
				</div>
				<div class="form_radio_btn">
					<input id="radio-19" type="radio" name="city" value="대구">
					<label for="radio-19">#대구</label>
				</div>
				<div class="form_radio_btn">
					<input id="radio-20" type="radio" name="city" value="울산">
					<label for="radio-20">#울산</label>
				</div>
				<div class="form_radio_btn">
					<input id="radio-21" type="radio" name="city" value="광주">
					<label for="radio-21">#광주</label>
				</div>
				<div class="form_radio_btn">
					<input id="radio-22" type="radio" name="city" value="강원">
					<label for="radio-22">#강원</label>
				</div>
				<div class="form_radio_btn">
					<input id="radio-23" type="radio" name="city" value="부산">
					<label for="radio-23">#부산</label>
				</div>
				<div class="form_radio_btn">
					<input id="radio-24" type="radio" name="city" value="충북">
					<label for="radio-24">#충북</label>
				</div>
				<div class="form_radio_btn">
					<input id="radio-25" type="radio" name="city" value="충남">
					<label for="radio-25">#충남</label>
				</div>
				<div class="form_radio_btn">
					<input id="radio-26" type="radio" name="city" value="경북">
					<label for="radio-26">#경북</label>
				</div>
				<div class="form_radio_btn">
					<input id="radio-27" type="radio" name="city" value="경남">
					<label for="radio-27">#경남</label>
				</div>
				<div class="form_radio_btn">
					<input id="radio-28" type="radio" name="city" value="전북">
					<label for="radio-28">#전북</label>
				</div>
				<div class="form_radio_btn">
					<input id="radio-29" type="radio" name="city" value="전남">
					<label for="radio-29">#전남</label>
				</div>
				<div class="form_radio_btn">
					<input id="radio-30" type="radio" name="city" value="제주">
					<label for="radio-30">#제주</label>
				</div>
		    </div>
		  </div>
        </div>
         
        <div class="paging">
            <a href=""><img src="images/festival/left_Button.png"></a>
			 <c:forEach var="cnt" begin="1" end="${lastPage}">
			 	<c:if test="${cnt <= lastPage}">
	            	<a href="javascript:setPaging(${cnt});">${cnt}</a>
            	</c:if>
			 </c:forEach> 
            <a href=""><img src="images/festival/right_Button.png"></a>
        </div>
      </div>
      <%@include file="../footer.jsp" %>
  </div>
</body>
</html>