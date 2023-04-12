<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--     <script src="https://unpkg.com/gijgo@1.9.14/js/gijgo.min.js" type="text/javascript"></script> -->
<!--     <link href="/css/datepicker/gijgo.min.css" rel="stylesheet" type="text/css" /> -->
    
    <link rel="stylesheet" href="/css/main/main.css">
    <title>메인페이지</title>
</head>
<body>
    <%@ include file="../header.jsp" %>
    <div class="container">
        <!-- 배너 -->
        <div class="slider">
            <img src="/images/main/banner1.jpg" class="slide">
            <img src="/images/main/banner2.jpg" class="slide">
            <div class="toggle">
                <button class="active" data-slide="1"></button>
                <button data-slide="2"></button>
            </div>
        </div>
        <div class="main_search">
            <div class="main_search_bg">
                <div class="tabs_menu">
                    <p>어디로 여행을 떠날 예정인가요?</p>
                </div>
                <div class="search_box">
                    <div class="area">
                        <div class="tab_content">
                            <div class="search_form">
                            <ul id="main_travel_search">
                            <!-- 검색 창 -->
                                <form id="main_travelForm" method="get">
									<input type="hidden" name="lcId" value="">
									<input type="hidden" name="sdate" value="">	
									<input type="hidden" name="edate" value="">	
                                    <li class="area_form">
									<%@ include file="../NewFile.jsp" %>
<!--                                     <li class="chkin"> -->
<!-- 										<input name="checkInOut" id="main_checkinout_ok" placeholder="체크인 - 체크아웃 하실 날짜를 선택해주세요" class="search_cal" type="text" value=" ~ " style="cursor:pointer;" readonly="" required=""> -->
<!-- 									</li> -->
									<li class="cal">
                                        <input id="datepicker" name="calendar" placeholder="출발 날짜를 입력해주세요." class="search_cal" type="text" style="cursor:pointer;" >
									</li>
<!-- 									readonly="" required> -->
                                    <li class="cal">
                                        <input id="datepicker" name="calendar" placeholder="도착 날짜를 입력해주세요." class="search_cal" type="text" style="cursor:pointer;" >
                                    </li>
                                </form>		
                            </ul>
                                <li class="search_confirm" onclick="searchBtn()">
                                    <a href="javascript:;" id="mainSearchBtn" data-kind="h"><i class="xi-search"></i>&nbsp;검색</i></a>
                                </li>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- 이달의 인기여행지 -->
        <div id="popularity_place_area">
            <h2>이달의 인기 여행지</h2>
            <div class="popularity_place">
                <ul class="popularity_place_tabs">
                    <li class="on">#서울</li>
                    <li class>#경기</li>
                    <li class>#인천</li>
                    <li class>#대전</li>
                    <li class>#대구</li>
                    <li class>#광주</li>
                    <li class>#부산</li>
                    <li class>#울산</li>
                    <li class>#강원</li>
                    <li class>#제주</li>
                </ul>
                  <div class="popularity_top_eight" id="seoulContent">
                      <div class="size-text">
                          <strong>인기 TOP 8</strong>
                      </div>
                      <table class="size-table">
                          <tbody>
                              <tr>
                                  <th style="width:10%;" id="border_line_top">1. [서울 성동구] 서울숲</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">2. [서울 용산구] 남산타워</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">3. [서울 중구] 동대문 디자인 플라자</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">4. [서울 용산구] 하이브 인사이트</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">5. [서울 중구] 청계천</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">6. [서울 종로구] 경복궁</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">7. [서울 용산구] 남산 서울타워</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;" id="border_line_bottom">8. [서울 마포구] 하늘공원</th>
                              </tr>
                              <tr>
                                  <td>2023.04.29 15:00 기준</td>
                              </tr>
                          </tbody>
                      </table>
                    </div>
                    <div class="popularity_top_eight" id="gyeonggiContent" style="display: none;">
                      <div class="size-text">
                          <strong>인기 TOP 8</strong>
                      </div>
                      <table class="size-table">
                          <tbody>
                              <tr>
                                  <th style="width:10%;" id="border_line_top">1. [경기 광주시] 화담숲</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">2. [경기 포천시] 포천아트밸리</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">3. [경기 남양주시] 물의정원</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">4. [경기 파주시] 임진각(평화누리공원)</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">5. [경기 파주시] 헤이리 예술마을</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">6. [경기 가평군] 아침고요수목원</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">7. [경기 안성시] 안성팜랜드</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;" id="border_line_bottom">8. [경기 시흥시] 시흥 갯골생태공원</th>
                              </tr>
                              <tr>
                                  <td>2023.04.29 15:00 기준</td>
                              </tr>
                          </tbody>
                      </table>
                    </div>
                    <div class="popularity_top_eight" id="incheonContent" style="display: none;">
                      <div class="size-text">
                          <strong>인기 TOP 8</strong>
                      </div>
                      <table class="size-table">
                          <tbody>
                              <tr>
                                  <th style="width:10%;" id="border_line_top">1. [인천 중구] 월미도</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">2. [인천 연수구] 송도 센트럴파크</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">3. [인천 옹진군] 신시모도</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">4. [인천 강화군] 교동도(강화)</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">5. [인천 강화군] 석모도 미네랄 온천</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">6. [인천 중구] 차이나타운</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">7. [인천 중구] 을왕리해수욕장</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;" id="border_line_bottom">8. [인천 중구] 무의도</th>
                              </tr>
                              <tr>
                                  <td>2023.04.29 15:00 기준</td>
                              </tr>
                          </tbody>
                      </table>
                    </div>
                    <div class="popularity_top_eight" id="daejeonContent" style="display: none;"> 
                      <div class="size-text">
                          <strong>인기 TOP 8</strong>
                      </div>
                      <table class="size-table">
                          <tbody>
                              <tr>
                                  <th style="width:10%;" id="border_line_top">1. [대전 서구] 장태산자연휴양림</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">2. [대전 동구] 대청호오백리길</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">3. [대전 대덕구] 계족산 황톳길</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">4. [대전 동구] 상소동 산림욕장</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">5. [대전 유성구] 계룡산 수통골</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">6. [대전 서구] 한밭수목원</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">7. [대전 동구] 대청호 벚꽃길</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;" id="border_line_bottom">8. [대전 대덕구] 장동산림욕장</th>
                              </tr>
                              <tr>
                                  <td>2023.04.29 15:00 기준</td>
                              </tr>
                          </tbody>
                      </table>
                    </div>
                    <div class="popularity_top_eight" id="daeguContent" style="display: none;">
                      <div class="size-text">
                          <strong>인기 TOP 8</strong>
                      </div>
                      <table class="size-table">
                          <tbody>
                              <tr>
                                  <th style="width:10%;" id="border_line_top">1. [대구 중구] 달성공원</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">2. [대구 수성구] 수성못 유원지</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">3. [대구 중구] 서문시장 야시장</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">4. [대구 중구] 경상감영공원</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">5. [대구 중구] 국채보상운동기념공원</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">6. [대구 달서구] 대구수목원</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;">7. [대구 달성군] 도동서원</th>
                              </tr>
                              <tr>
                                  <th style="width:10%;" id="border_line_bottom">8. [대구 달성군] 옥연지 송해공원</th>
                              </tr>
                              <tr>
                                  <td>2023.04.29 15:00 기준</td>
                              </tr>
                          </tbody>
                      </table>
                    </div>
                    <div class="popularity_picture" id="seoulContent">
                      <div class="picture-list">
                        <div class="picture-list-bar">
                            <ul>
                            <li style="width: 0px;"></li>
                            <li>
                                <img src="/images/main/popular1.jpg">
                                <div class="caption">
                                <h3>[서울 성동구] 서울숲</h3>
                                <div class="icon">+</div>
                                <p class="place">
                                    반려동물 동반가능한, 시민의 참여로 <br>이루어진 최초의 공원
                                </p>
                                <p class="genre">문의 및 안내 : 02-460-2905<br>주소 : 서울특별시 성동구 뚝섬로 273<br>휴일 : 연중무휴 (일부 시설 월요일 휴관)</p>
                                </div>
                            </li>
                            <li>
                                <img src="/images/main/popular2.jpg">
                                <div class="caption">
                                <h3>[서울 용산구] 남산타워</h3>
                                <div class="icon">+</div>
                                <p class="place">
                                    대를 이어온 데이트의 추억
                                </p>
                                <p class="genre">문의 및 안내 : 02-3455-9277<br>주소 : 서울특별시 용산구 남산공원길 105<br>휴일 : 연중무휴</p>
                                </div>
                            </li>
                            <li>
                                <img src="/images/main/popular3.jpg">
                                <div class="caption">
                                <h3>[서울 중구] DDP</h3>
                                <div class="icon">+</div>
                                <p class="place">
                                    가장 매력적인 서울 야경을 볼 수 있는 동대문에 위치한 복합 문화공간
                                </p>
                                <p class="genre">문의 및 안내 : 02-2153-0000<br>주소 : 서울특별시 중구 을지로 281<br>휴일 : 신정, 추석, 설날 당일(살림터는 매월 세번째주 월요일 휴무)</p>
                                </div>
                            </li>
                            </ul>
                        </div>
                      </div>
                	</div>
                	<div class="popularity_picture" id="gyeonggiContent" style="display: none;">
                      <div class="picture-list">
                        <div class="picture-list-bar">
                            <ul>
                            <li style="width: 0px;"></li>
                            <li>
                                <img src="/images/main/popular4.jpg">
                                <div class="caption">
                                <h3>[경기 광주시] 화담숲</h3>
                                <div class="icon">+</div>
                                <p class="place">
                                    노고봉의 계곡과 능선을 따라 자연스럽게 자리 잡은 수목들의 다양한 모습을 볼 수 있다.
                                </p>
                                <p class="genre">문의 및 안내 : 	031-8026-6666<br>주소 : 경기도 광주시 도척면 도척윗로 278-1<br>휴일 : 매주 월요일</p>
                                </div>
                            </li>
                            <li>
                                <img src="/images/main/popular5.jpg">
                                <div class="caption">
                                <h3>[경기 포천시] 포천아트밸리</h3>
                                <div class="icon">+</div>
                                <p class="place">
                                    폐채석장의 변신, 반려동물 동반 가능한 복합문화공간
                                </p>
                                <p class="genre">문의 및 안내 : 1668-1035<br>주소 : 경기도 포천시 신북면 아트밸리로 234<br>휴일 : 연중무휴</p>
                                </div>
                            </li>
                            <li>
                                <img src="/images/main/popular6.jpg">
                                <div class="caption">
                                <h3>[경기 남양주시] 물의정원</h3>
                                <div class="icon">+</div>
                                <p class="place">
                                    액자에 담긴 북한강의 자태
                                </p>
                                <p class="genre">문의 및 안내 : 	031-590-8634<br>주소 : 	경기도 남양주시 조안면 북한강로 398<br>휴일 : 연중무휴</p>
                                </div>
                            </li>
                            </ul>
                        </div>
                      </div>
                	</div>  
                	<div class="popularity_picture" id="incheonContent" style="display: none;">
                      <div class="picture-list">
                        <div class="picture-list-bar">
                            <ul>
                            <li style="width: 0px;"></li>
                            <li>
                                <img src="/images/main/popular7.jpg">
                                <div class="caption">
                                <h3>[인천 중구] 월미도</h3>
                                <div class="icon">+</div>
                                <p class="place">
                                    아름다운 야경을 품고 있는 월미도
                                </p>
                                <p class="genre">문의 및 안내 : 	032-765-4169<br>주소 : 인천광역시 중구 월미문화로 36<br>휴일 : 연중무휴</p>
                                </div>
                            </li>
                            <li>
                                <img src="/images/main/popular8.jpg">
                                <div class="caption">
                                <h3>[인천 연수구] 송도 센트럴파크</h3>
                                <div class="icon">+</div>
                                <p class="place">
                                    부담없이 감상하는 백만 불짜리 야경
                                </p>
                                <p class="genre">문의 및 안내 : 032-832-3031<br>주소 : 인천 연수구 인천타워대로 234<br>휴일 : 연중무휴</p>
                                </div>
                            </li>
                            <li>
                                <img src="/images/main/popular9.jpg">
                                <div class="caption">
                                <h3>[인천 옹진군] 신시모도</h3>
                                <div class="icon">+</div>
                                <p class="place">
                                    색다른 매력의 사이좋은 삼형제섬
                                </p>
                                <p class="genre">문의 및 안내 : 032-899-3410<br>주소 : 	인천광역시 옹진군 북도면<br>휴일 : 연중무휴</p>
                                </div>
                            </li>
                            </ul>
                        </div>
                      </div>
                	</div>  
                	<div class="popularity_picture" id="daejeonContent" style="display: none;">
                      <div class="picture-list">
                        <div class="picture-list-bar">
                            <ul>
                            <li style="width: 0px;"></li>
                            <li>
                                <img src="/images/main/popular10.jpg">
                                <div class="caption">
                                <h3>[대전 서구] 장태산자연휴양림</h3>
                                <div class="icon">+</div>
                                <p class="place">
                                    피톤치드를 깊이 들이마시는 산책
                                </p>
                                <p class="genre">문의 및 안내 : 	042-270-7883<br>주소 : 	대전광역시 서구 장안로 461<br>휴일 : 연중무휴</p>
                                </div>
                            </li>
                            <li>
                                <img src="/images/main/popular11.jpg">
                                <div class="caption">
                                <h3>[대전 동구] 대청호오백리길</h3>
                                <div class="icon">+</div>
                                <p class="place">
                                    대전과 충북을 거치는 220km의 도보길
                                </p>
                                <p class="genre">문의 및 안내 : 042-250-1236<br>주소 : 대전광역시 동구 천개동로 34<br>휴일 : 연중무휴</p>
                                </div>
                            </li>
                            <li>
                                <img src="/images/main/popular12.jpg">
                                <div class="caption">
                                <h3>[대전 대덕구] 계족산 황톳길</h3>
                                <div class="icon">+</div>
                                <p class="place">
                                    맨발로 트레킹을 떠나자
                                </p>
                                <p class="genre">문의 및 안내 : 	042-623-9909<br>주소 : 	대전광역시 대덕구 장동 산85<br>휴일 : 연중무휴</p>
                                </div>
                            </li>
                            </ul>
                        </div>
                      </div>
                	</div>  
                	<div class="popularity_picture" id="daeguContent" style="display: none;">
                      <div class="picture-list">
                        <div class="picture-list-bar">
                            <ul>
                            <li style="width: 0px;"></li>
                            <li>
                                <img src="/images/main/popular13.jpg">
                                <div class="caption">
                                <h3>[대구 중구] 달성공원</h3>
                                <div class="icon">+</div>
                                <p class="place">
                                    대구시민에게 친근한 공원, 대구 달성공원
                                </p>
                                <p class="genre">문의 및 안내 : 	053-803-7350<br>주소 : 대구광역시 중구 달성공원로 35<br>휴일 : 연중무휴</p>
                                </div>
                            </li>
                            <li>
                                <img src="/images/main/popular14.jpg">
                                <div class="caption">
                                <h3>[대구 수성구] 수성못 유원지</h3>
                                <div class="icon">+</div>
                                <p class="place">
                                    사시사철 매력 팡팡
                                </p>
                                <p class="genre">문의 및 안내 : 	053-666-2863<br>주소 : 	대구광역시 수성구 용학로 35-5<br>휴일 : 연중무휴</p>
                                </div>
                            </li>
                            <li>
                                <img src="/images/main/popular15.jpg">
                                <div class="caption">
                                <h3>[대구 중구] 서문시장 야시장</h3>
                                <div class="icon">+</div>
                                <p class="place">
                                    대구장에서 야시장까지
                                </p>
                                <p class="genre">문의 및 안내 : 	053-256-6341<br>주소 : 대구광역시 중구 큰장로26길 45<br>휴일 : 연중무휴</p>
                                </div>
                            </li>
                            </ul>
                        </div>
                      </div>
                	</div>      
            </div>
            <a href="" class="more">더보기 ></a>
        </div>

        <!-- 요즘 뜨는 핫플 -->
        <div id="hot_place_background">
            <div class="hot_place">
                <h2>요즘 뜨는 핫플!</h2>
                <ul class="hot_place_tabs">
                    <li class="on">#벚꽃 축제</li>
                    <li class>#야경 명소</li>
                    <li class>#액티비티</li>
                    <li class>#감성 카페</li>
                    <li class>#전시/공연</li>
                    <li class>#힐링</li>
                    <li class>#배낭여행</li>
                </ul>
            </div>

            <div class="hot_picture-list" id="cherryBlossoms">
                <ul>
                    <li style="width: 0px;"></li>
                    <li>
                        <div class="image_group_bottom hot_picture">
                            <img src="/images/main/hot1.jpg">
                        </div>
                        <div class="caption">
                            <h3>대구 - 이월드 블라썸 피크닉</h3>
                            <p class="place">
                                축제기간 : 2023.03.18(토) ~ 2023.04.09(일)<br>운영시간 : 평일 10:30 ~ 21:00, 주말 : 10:00 ~ 22:00
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="image_group_top hot_picture">
                            <img src="/images/main/hot2.jpg">
                        </div>  
                        <div class="caption">
                            <h3>창원 - 진해 군항제</h3>
                            <p class="place">
                                축제기간 : 2023.03.25(토) ~ 2023.04.03(월)<br>운영시간 : 평일 10:30 ~ 21:00, 주말 : 10:00 ~ 22:00
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="image_group_bottom">
                            <img src="/images/main/hot3.jpg">
                        </div>  
                        <div class="caption">
                            <h3>서울 - 석촌호수 벚꽃 축제</h3>
                            <p class="place">
                                축제기간 : 2023.03.25(토) ~ 2023.04.03(월)<br>운영시간 : 평일 10:30 ~ 21:00, 주말 : 10:00 ~ 22:00
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="image_group_top">
                            <img src="/images/main/hot4.JPG">
                        </div> 
                        <div class="caption">
                            <h3>가평 - 에덴벚꽃길 벚꽃 축제</h3>
                            <p class="place">
                                축제기간 : 2023.04.08(토) ~ 2023.04.16(일)<br>운영시간 : 주말 09:00 ~ 20:00
                            </p>
                        </div>
                    </li>
                </ul>
                <a href="" class="more">더보기 ></a>
            </div>
            <div class="hot_picture-list" id="nightView" style="display: none;">
                <ul>
                    <li style="width: 0px;"></li>
                    <li>
                        <div class="image_group_bottom hot_picture">
                            <img src="/images/main/hot5.jpg">
                        </div>
                        <div class="caption">
                            <h3>여수 - 야경불꽃크루즈</h3>
                            <p class="place">
                                축제기간 : 2023. 1. 1. ~ 2023. 12. 31.<br>운영시간 : 매주 금/토/일요일 19:00
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="image_group_top hot_picture">
                            <img src="/images/main/hot6.jpg">
                        </div>  
                        <div class="caption">
                            <h3>포천 - 허브아일랜드 불빛동화축제</h3>
                            <p class="place">
                                축제기간 : 2023. 2. 1. ~ 2023. 12. 31.<br>운영시간 : 오전 10:00 ~ 오후 21:00(수요일 정기 휴장)
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="image_group_bottom">
                            <img src="/images/main/hot7.jpg">
                        </div>  
                        <div class="caption">
                            <h3>김포 - 김포 불꽃크루즈</h3>
                            <p class="place">
                                축제기간 : 2023. 1. 1. ~ 2023. 12. 31.<br>운영시간 : 주말 09:00 ~ 20:00
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="image_group_top">
                            <img src="/images/main/hot8.JPG">
                        </div> 
                        <div class="caption">
                            <h3>원주 - 오크밸리 3D 라이팅쇼</h3>
                            <p class="place">
                                축제기간 : 2023. 1. 1. ~ 2023. 12. 31.<br>운영시간 : 주말 09:00 ~ 20:00
                            </p>
                        </div>
                    </li>
                </ul>
                <a href="" class="more">더보기 ></a>
            </div>
            <div class="hot_picture-list" id="activity" style="display: none;">
                <ul>
                    <li style="width: 0px;"></li>
                    <li>
                        <div class="image_group_bottom hot_picture">
                            <img src="/images/main/hot9.jpg">
                        </div>
                        <div class="caption">
                            <h3>울산 - 자전거대축전</h3>
                            <p class="place">
                                축제기간 : 2023. 4. 22. ~ 2023. 4. 22.<br>운영시간 : 평일 09:00 - 12:30
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="image_group_top">
                            <img src="/images/main/hot10.jpg">
                        </div>  
                        <div class="caption">
                            <h3>예천 - 예천활축제</h3>
                            <p class="place">
                                축제기간 : 2023. 5. 4. ~ 2023. 5. 7.<br>운영시간 : 평일 10:00 ~ 18:00
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="image_group_bottom">
                            <img src="/images/main/hot11.jpg">
                        </div>  
                        <div class="caption">
                            <h3>부산 - 용골댄스페스타</h3>
                            <p class="place">
                                축제기간 : 2023. 4. 4. ~ 2023. 4. 4.<br>운영시간 : 평일 10:30 ~ 21:00
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="image_group_top">
                            <img src="/images/main/hot12.JPG">
                        </div> 
                        <div class="caption">
                            <h3>고양 - 행주가 예술이야</h3>
                            <p class="place">
                                축제기간 : 2023. 4. 28. ~ 2023. 5. 14.<br>운영시간 : 18:00-22:00 (21시 입장마감)
                            </p>
                        </div>
                    </li>
                </ul>
                <a href="" class="more">더보기 ></a>
            </div>
            <div class="hot_picture-list" id="cafe" style="display: none;">
                <ul>
                    <li style="width: 0px;"></li>
                    <li>
                        <div class="image_group_bottom hot_picture">
                            <img src="/images/main/hot13.PNG">
                        </div>
                        <div class="caption">
                            <h3>서울 - 익선잡방</h3>
                            <p class="place">
                                주소 : 서울 종로구 수표로28길 17-21 익선잡방 <br>운영시간 : 09:00~18:00
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="image_group_top hot_picture">
                            <img src="/images/main/hot14.jpg">
                        </div>  
                        <div class="caption">
                            <h3>인천 - 조양방직</h3>
                            <p class="place">
                                주소 : 인천광역시 강화군 강화읍 향나무길5번길 12 <br>운영시간 : 09:00~18:00
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="image_group_bottom">
                            <img src="/images/main/hot15.jpg">
                        </div>  
                        <div class="caption">
                            <h3>파주 - 더티트렁크</h3>
                            <p class="place">
                                주소 : 경기 파주시 지목로 114 1-2층 <br>운영시간 : 09:00~18:00
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="image_group_top">
                            <img src="/images/main/hot16.JPEG">
                        </div> 
                        <div class="caption">
                            <h3>대전 - 성심당 본점</h3>
                            <p class="place">
                                주소 : 대전 유성구 엑스포로 107 <br>운영시간 : 09:00~18:00
                            </p>
                        </div>
                    </li>
                </ul>
                <a href="" class="more">더보기 ></a>
            </div>
            <div class="hot_picture-list" id="performance" style="display: none;">
                <ul>
                    <li style="width: 0px;"></li>
                    <li>
                        <div class="image_group_bottom hot_picture">
                            <img src="/images/main/hot17.jpg">
                        </div>
                        <div class="caption">
                            <h3>서울 - 서울디저트페어</h3>
                            <p class="place">
                                기간 : 2023. 4. 8. ~ 2023. 4. 9.<br>운영시간 : 평일 12:00-17:00
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="image_group_top hot_picture">
                            <img src="/images/main/hot18.jpg">
                        </div>  
                        <div class="caption">
                            <h3>서울 - 베지노믹스페어 비건페스타</h3>
                            <p class="place">
                                기간 : 2023. 3. 17. ~ 2023. 3. 19.<br>운영시간 : 평일 10:00 ~ 18:00 (입장마감 17:00)
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="image_group_bottom">
                            <img src="/images/main/hot19.jpg">
                        </div>  
                        <div class="caption">
                            <h3>진도 - 진도토요민속여행 상설공연</h3>
                            <p class="place">
                                기간 : 2022. 3. 1. ~ 2022. 12. 31.<br>운영시간 : 	매주 토요일 오후 2시
                            </p>
                        </div>
                    </li>
                    <li>
                        <div class="image_group_top">
                            <img src="/images/main/hot20.JPG">
                        </div> 
                        <div class="caption">
                            <h3>대전 - 효문화뿌리축제</h3>
                            <p class="place">
                                기간 : 2023.04.08(토) ~ 2023.04.16(일)<br>운영시간 : 주말 09:00 ~ 20:00
                            </p>
                        </div>
                    </li>
                </ul>
                <a href="" class="more">더보기 ></a>
            </div>
        </div>

        <!-- 여행 상품 추천 -->
        <div class="travel_products">
            <h2>여행 상품 추천</h2>
            <div class="travel_products_list">
                <ul>
                    <li>
                        <div class="round_square_first">
                            <div class="image_group" id="img_first">
                                <img src="/images/main/product1.JPG">
                            </div>
                            <div class="caption">
                                <h3>[당일치기] 수원 화성 야경 투어 19,900원 ~</h3>
                                <p class="place">
                                    조선 최초의 신도시, 정조의 야심작 수원화성.조선의<br> 
                                    뇌섹남 정조가 꿈꾸었던 세상, 수원화성에서 만나봅니다.
                                </p>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="round_square_second">
                            <div class="image_group" id="img_second">
                                <img src="/images/main/product2.jpg">
                            </div>
                            <div class="caption">
                                <h3>[1박2일] KTX 부산 명소 구석구석 완전 일주 214,000원 ~</h3>
                                <p class="place">
                                    부산의 구석구석 BEST 명소를 찾아 떠나는 가성비 좋은 여행
                                </p>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="round_square_third">
                            <div class="image_group" id="img_third">
                                <img src="/images/main/product3.jpg">
                            </div>
                            <div class="caption">
                                <h3>[1박2일] 제천 워터파크 캠핑랜드  150,000원 ~</h3>
                                <p class="place">
                                    대형 실내 온수 워터파크, 놀이기구, 글램핑 등 아이들이 더 좋아하는<br> 캠핑장
                                </p>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        
        <!-- 이용가이드 / 공지사항&이벤트 -->
        <div id="guide_background">
            <div class="guide_wrap">
                <div class="cs_guide">
                    <h3>이용가이드</h3>
                    <table class="cs_guide_tbl">
                        <tr>
                            <td><a href=""><img src="/images/main/guide1.jpg" alt="예약조회"><p>예약조회</p></a></td>
                            <td><a href=""><img src="/images/main/guide2.jpg" alt="여행후기"><p>여행후기</p></a></td>
                            <td><a href=""><img src="/images/main/guide3.jpg" alt="문의답변"><p>문의답변</p></a></td>
                            <td><a href=""><img src="/images/main/guide4.jpg" alt="자주하는 질문"><p>자주하는 질문</p></a></td>
                        </tr>
                    </table>
                </div>
                <div class="notice">
                    <h3>공지사항 &amp; 이벤트<a href="" class="more_view">+ 더보기</a></h3>
                    <ul>
                        <li>
                            <a href="" title="공지사항">
                                <span class="c_y">[공지]</span>공지사항<span class="notice_date">2022-11-30</span>
                            </a>
                        </li>
                        <li>
                            <a href="" title="공지사항">
                                <span class="c_y">[공지]</span>공지사항<span class="notice_date">2022-11-30</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <%@include file="../footer.jsp" %>
    <script src="/js/main/main.js"></script>
</body>
</html>