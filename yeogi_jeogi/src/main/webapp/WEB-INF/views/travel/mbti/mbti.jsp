<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/travel/mbti/mbti.css">
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	 <script src="/js/travel/mbti.js"></script>
    <title>MBTI별 여행지</title>
</head>
<body>
    <%@ include file="../../header.jsp" %>
    <div id="container">
        <!-- 바디 -->
       <div class="menu">
            <ul>
                <li><a href="">지역별 여행</a></li>
                <li><a href="">계절별 여행</a></li>
                <li><a href="">MBTI별 여행</a></li> 
            </ul>
       </div>
       <div class="MBTI">
        <ul>
            <li><a href="">ISTJ</a></li>
            <li><a href="">ISFJ</a></li>
            <li><a href="">INFJ</a></li>
            <li><a href="">INTJ</a></li>
            <li><a href="">ISTP</a></li>
            <li><a href="">ISFP</a></li>
            <li><a href="">INFP</a></li>
            <li><a href="">INTP</a></li>
        </ul>
        <ul>
            <li><a href="">ESTP</a></li>
            <li><a href="">ESFP</a></li>
            <li><a href="">ENFP</a></li>
            <li><a href="">ENTP</a></li>
            <li><a href="">ESTJ</a></li>
            <li><a href="">ESFJ</a></li>
            <li><a href="">ENFJ</a></li>
            <li><a href="">ENTJ</a></li>
        </ul>
        </div>
        <br>
        <hr class="type1">
        <br>
        <div class="sub">
            <div>
                <img src="/images/mbti/istj.jpg">
                <div class="sub2">
                <h1>청렴결백한 논리주의자 ISTJ!</h1>
                <br>
                <p>사실에 근거하여 이들의 행동이나 결정 사항에 한 치의 의심을 사지 않는 현실주의자형이에요.<br>
                    ISTJ는 여행지에서 일어날 수 있는 모든 변수를 생각해서 계획을 세워요.<br>
                    혼자 계획을 짜고 일행에게 알려주는게 편하고, 내가 짠 계획에 태클을 걸면 짜증 나고 이해를 못할 수도 있어요.<br>
                    우유부단한 여행을 싫어하고 여행 관련해서 대신 다 선택해주는 편이에요.<br>
                    사람 많은 곳을 싫어하고 즉흥적으로 여행 가자고 하면 절대 안갈거에요.</p>
                    <br>
                <h3 style="color: green;">최고의 여행 궁합<br>
                    ESTJ, ESTP</h3>                    
                <h3 style="color: red;" >최악의 여행 궁합<br>
                    INFJ, ENFJ</h3>
                </div>   
            </div>
        </div>

       <div id="popup">
            <div class="popup-inner">
                <div class="slider-2">
    
                    <div class="side-btns">
                       <div><span><i class="fas fa-caret-left"></i></i></span></div>
                       <div><span><i class="fas fa-caret-right"></i></span></div>
                   </div>
                   
                   <div class="slides">
                       <div class="active" style="background-image:url(/images/region/1.jpg);"></div>
                       <div style="background-image:url(/images/region/2.jpg);"></div>
                       <div style="background-image:url(/images/region/3.jpg);"></div>
                       <div style="background-image:url(/images/region/4.jpg);"></div>
                   </div>
                   
                   <div class="page-nav">
                       <div class="active"></div>
                       <div></div>
                       <div></div>
                       <div></div>
                   </div>
               </div>
               <div class="wrap">
                <div class="tab_menu">
                  <ul class="list">
                    <li class="is_on">
                      <a href="#tab1" class="btn">여행지정보</a>
                      <div id="tab1" class="cont"><p>꿈꾸고(Dream), 만들고(Design), 누린다(Play)는 뜻을 가지고 있는 DDP(동대문 디자인 플라자)는 서울특별시 동대문에 위치한 복합문화공간이다. 
                        2014년 3월 21일 개관한 이래 굵직한 전시와 패션쇼, 신제품 발표회, 포럼과 콘퍼런스를 개최해오고 있다. 
                        알림터, 배움터, 살림터, DDP디자인뮤지엄, 디자인장터와 어울림광장, 동대문역사문화공원으로 나뉘었다. 
                        알림터에서는 서울패션위크, MAMA(Mnet Asian Music Awards), 반클리프 아펠전, BMW 신차 발표회 등을 개최했고, 
                        다목적 연회 공간인 국제회의장 시설을 갖추었다. BTS 주제전과 서울도시건축비엔날레 등이 열렸던 배움터, 
                        누구나 디자이너가 되는 곳을 주제로 2019년 개장한 시민 라운지 ‘D-숲’과 DDP 관련 기념품을 소개하는 DDP디자인스토어를 아우르는 살림터까지, 
                        규모에 걸맞은 내실로 관람객의 흥미를 북돋운다. 식당과 베이커리, 
                        카페가 들어선 디자인장터 역시 감성 충전을 기대하는 가족이나 연인을 만족시키고도 남을 산책 코스다.</p></div>
                    </li>
                    <li>
                      <a href="#tab2" class="btn">상세정보</a>
                      <div id="tab2" class="cont"><img src ="/images/region/5.jpg"></div>
                    </li>
                    <li>
                      <a href="#tab3" class="btn">오시는 길</a>
                      <div id="tab3" class="cont"><img src = "/images/region/6.jpg"></div>
                    </li>
                  </ul>
                </div>
              </div>
               <div id="heart">
                	 <button id="likeImage" type="button" onclick="heart()"><img src="/images/board/like.png"></button>
                	 <span id="like_count">0</span>
                	
                </div>
                    <button type ="button" class="pop1">닫기</button>
           
            </div>
        </div>

        <br>
            <div class="sub3">
                <h1 class="h1">여행지 추천</h1>
                <div class="sub3-1"><img src="/images/mbti/경주.JPG" width="375px" height="250px">
                    <p><b class="sub3-2">경주</b>&nbsp;<b>불국사와 석굴암</b></p>
                    <p>경북 경주시 불국로 385 불국사</p>
                    <p>054-746-9913</p>
                </div>
                <div class="sub3-1"><img src="/images/mbti/지하예술정원.jpg" width="375px" height="250px">
                    <p><b class="sub3-2">서울</b>&nbsp;<b>녹사평역 지하 예술 정원</b></p>
                    <p>서울특별시 용산구 녹사평대로 지하 195</p>
                    <p>02-794-5579</p>
                </div>
                <div class="sub3-1"><img src="/images/mbti/더스파.jpg" width="375px" height="250px">
                    <p><b class="sub3-2">인천</b>&nbsp;<b>더 스파 앳 파라다이스</b></p>
                    <p>인천광역시 중구 영종해안남로 321번길 186</p>
                    <p>032-729-2440</p>
                </div>
            </div>
        <br>

        <div class="paging">
            <a href="">1</a>
            <a href="">2</a>
            <a href="">3</a>
            <a href="">4</a>
            <a href="">5</a>
            <a href="">5</a>
            <a href="">6</a>        
            <a href="">7</a>
            <a href="">8</a>
            <a href="">9</a>
            <a href="">10</a>
            <a href=""><img src="../1.jpg"></a>
            <a href=""><img src="../2.jpg"></a>
        </div>
        <br>
	</div>
	<%@include file="../../footer.jsp" %>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="/js/travel/mbti.js"></script> -->
	 <script>
        // 좋아요 카운터 초기값
        let likeCount = 0;

        // 이미지 클릭 이벤트 핸들러
        document.getElementById('likeImage').addEventListener('click', function() {
            // 좋아요 수 증가
            likeCount++;
            
            // 좋아요 수 업데이트
            document.getElementById('like_count').innerText = likeCount;
        });
    </script>
</body>
</html>