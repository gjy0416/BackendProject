<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="/css/login/login.css">
  <script src="/js/login/login.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
  <title>대한민국 여기저기</title>
</head> 
<body>
  <div class="cotn_principal">
    <div class="cont_centrar">
      <div class="cont_login">
        <div class="cont_info_log_sign_up">
          <div class="col_md_login">
            <div class="cont_ba_opcitiy">
              <h2>로그인</h2>
              <p>안녕하세요 만나서 반갑습니다.<br>좋은 여행 되세요.</p> 
              <button class="btn_login" onclick="change_to_login()">로그인</button>
            </div>
          </div>
          <div class="col_md_sign_up">
            <div class="cont_ba_opcitiy">
              <h2>회원가입</h2>
              <p>안녕하세요 만나서 반갑습니다.<br>좋은 여행 되세요.</p> 
              <button class="btn_sign_up" onclick="change_to_sign_up()">회원가입</button>
            </div>
          </div>
        </div>
        <div class="cont_back_info">
          <div class="cont_img_back_grey">
            <img src="https://images.unsplash.com/42/U7Fc1sy5SCUDIu4tlJY3_NY_by_PhilippHenzler_philmotion.de.jpg?ixlib=rb-0.3.5&q=50&fm=jpg&crop=entropy&s=7686972873678f32efaf2cd79671673d" alt="" />
          </div>
        </div>
        <div class="cont_forms" >
          <div class="cont_img_back_">
            <img src="https://images.unsplash.com/42/U7Fc1sy5SCUDIu4tlJY3_NY_by_PhilippHenzler_philmotion.de.jpg?ixlib=rb-0.3.5&q=50&fm=jpg&crop=entropy&s=7686972873678f32efaf2cd79671673d" alt="" />
          </div>
          <div class="cont_form_login">
            <a href="#" onclick="hidden_login_and_sign_up()" ><i class="material-icons">&#xE5C4;</i></a>
            <h2>로그인</h2>
            <form method="post" action="/login/send" onsubmit="return alertLoginBlank()">
	            <input id="lUserId" type="text" name="lUserId" placeholder="아이디" />
	            <input id="lPassword" type="password" name="lPassword" placeholder="비밀번호" />
	            <button class="btn_login" type="submit" onclick="change_to_login()">로그인</button>
	         </form>
          </div>
          <div class="cont_form_sign_up">
            <a href="#" onclick="hidden_login_and_sign_up()"><i class="material-icons">&#xE5C4;</i></a>
            <h2>회원가입</h2>
            <form method="post" action="/membership/add" enctype="multipart/form-data">
            	<div id="sign_up_idwrap">
	            	<input id="mUserId" type="text" name ="mUserId" placeholder="아이디" onchange="onchangeCheck()"/>
		            <button id="checkbtn"type="button" onclick="idDupCheck()">중복확인</button>
            	</div>
	            <input type="password" name = "mPassword" placeholder="비밀번호"/>
	            <input type="password" name = "user-pwd" placeholder="비밀번호 확인"/>
	            <input type="text" name ="mName" placeholder="이름" />
	            <input type="phone" name = "mPhone" placeholder="휴대전화">
	            <!-- <input type="tel" id="phone" name="phone" style="cursor: pointer" placeholder="전화번호 입력"> -->
	            <!-- <a href="#" id="tel-btn">인증번호 받기</a>
	            <input type="phone" id="tel-btn2" name="tel-btn2" style="cursor: pointer" placeholder="인증번호 입력하세요"> -->
	            <div>
	              <label for="MBTI" id="MBTI1">MBTI</label>
	              <select id="MBTI2" name="mMbti">
	                <option value="MBTI">유형</option>
	                <option value="ISTJ">ISTJ</option>
	                <option value="ISTP">ISTP</option>
	                <option value="ISFJ">ISFJ</option>
	                <option value="ISFP">ISFP</option>
	                <option value="INTJ">INTJ</option>
	                <option value="INTP">INTP</option>
	                <option value="INFJ">INFJ</option>
	                <option value="INFP">INFP</option>
	                <option value="ESTJ">ESTJ</option>
	                <option value="ESTP">ESTP</option>
	                <option value="ESFJ">ESFJ</option>
	                <option value="ESFP">ESFP</option>
	                <option value="ENTJ">ENTJ</option>
	                <option value="ENTP">ENTP</option>
	                <option value="ENFJ">ENFJ</option>
	                <option value="ENFP">ENFP</option>
	              </select>
	            </div>
	            <input type="email" name="mEmail" id="email" style="cursor: pointer" placeholder="본인 확인 이메일(선택)">
	            <button class="btn_sign_up" type="submit" onclick="change_to_sign_up()">회원가입</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>