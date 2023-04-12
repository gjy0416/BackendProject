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
 function idDupCheck() {
	  const id = document.getElementById('mUserId').value;
		 $.ajax({
			 type: 'post',
			 url: '/membership/check',
			 contentType: 'application/json; charset=utf-8',
			 data: id,
			 success: function(data) {
				 if (data) {
				 	alert('아이디 사용가능합니다');
				 	document.getElementById('checkbtn').disabled = true;
				 }
				 else {
					alert('아이디가 중복됩니다!');
				 }
			 }
		 });	 
	 }