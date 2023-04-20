 const time_to_show_login = 400;
 const time_to_hidden_login = 200;

 function change_to_login() {
   document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_login";
   document.querySelector('.cont_form_login').style.display = "block";
   document.querySelector('.cont_form_sign_up').style.opacity = "0";

   setTimeout(function(){document.querySelector('.cont_form_login').style.opacity = "1";},time_to_show_login);
   setTimeout(function(){document.querySelector('.cont_form_sign_up').style.display = "none";},time_to_hidden_login);
 }

 const time_to_show_sign_up = 100;
 const time_to_hidden_sign_up = 400;

 function change_to_sign_up(at) {
   document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_sign_up";
   document.querySelector('.cont_form_sign_up').style.display = "block";
   document.querySelector('.cont_form_login').style.opacity = "0";

   setTimeout(function(){document.querySelector('.cont_form_sign_up').style.opacity = "1";},time_to_show_sign_up);
   setTimeout(function(){document.querySelector('.cont_form_login').style.display = "none";},time_to_hidden_sign_up);
 } 

 const time_to_hidden_all = 500;

 function hidden_login_and_sign_up() {

   document.querySelector('.cont_forms').className = "cont_forms";
   document.querySelector('.cont_form_sign_up').style.opacity = "0";
   document.querySelector('.cont_form_login').style.opacity = "0"; 

   setTimeout(function(){
     document.querySelector('.cont_form_sign_up').style.display = "none";
     document.querySelector('.cont_form_login').style.display = "none";
   },time_to_hidden_all);
 }
 function alertLoginBlank() {
	 if (document.getElementById('lUserId').value == '') {
		 alert('아이디를 입력해주세요');
		 return false;
	 }
	 if (document.getElementById('lPassword').value == '') {
		 alert('비밀번호를 입력해주세요');
		 return false;
	 }
	 return true;
 }
 function alertLoginBlank() {
	 if (document.getElementById('lUserId').value == '') {
		 alert('아이디를 입력해주세요');
		 return false;
	 }
	 if (document.getElementById('lPassword').value == '') {
		 alert('비밀번호를 입력해주세요');
		 return false;
	 }
	 
	 return true;
 }
 function onchangeCheck() {
	 document.getElementById('checkbtn').disabled = false;
 }
 function membershipCheck() {
	 if ($('#mUserId').val() == '') {
		 alert('아이디를 입력해주세요');
		 return false;
	 }
	 if ($('#mUserId').val().length < 5 || $('#mUserId').val().length > 12) {
		 alert('아이디는 5자 이상 12자 이하이어야 합니다');
		 return false;
	 }
	 if ($('#password1').val().length < 8 || $('#password1').val().length > 20) {
		 alert('비밀번호는 8자이상 20자 이하여야합니다');
		 return false;
	 }
	 if ($('#password1').val() != $('#password2').val()) {
		 alert('비밀번호확인이 다릅니다');
		 return false;
	 }
	 if ($('#checkbtn').disabled == false) {
		 alert('아이디 중복검사가 필요합니다!');
		 return false;
	 }
	 if ($('#input_name').val() == '') {
		 alert('이름을 입력해주세요');
		 return false;
	 }
	 if ($('#input_phone').val() == '') {
		 alert('휴대전화번호를 입력해주세요');
		 return false;
	 }
	 if ($('#MBTI2').val() == 'MBTI') {
		 alert('MBTI를 선택해주세요');
		 return false;
	 }
	 if ($('#email').val() == '') {
		 alert('이메일을 입력해주세요');
		 return false;
	 }
	 return true;
 }
 function idDupCheck() {
	  const id = $('#mUserId').val();
		 $.ajax({
			 type: 'post',
			 url: '/membership/check',
			 contentType: 'application/json; charset=utf-8',
			 data: id,
			 success: function(data) {
				 if (data) {
				 	alert('아이디 사용가능합니다');
				 	$('#checkbtn').disabled = true;
				 }
				 else {
					alert('아이디가 중복됩니다!');
				 }
			 }
		 });	 
	 }