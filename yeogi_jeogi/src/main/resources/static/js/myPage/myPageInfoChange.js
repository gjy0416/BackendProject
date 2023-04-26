refreshInfo();
function fileAdd(input) {
	if (input.files && input.files[0]) {
		if (input.files[0].size > 3*1024*1024) {
			alert("이미지는 3MB를 넘을 수 없습니다!");
			input.value='';
			return false;
		}
	}
	document.getElementById('imgCon').value = document.getElementById('file').value;
	return true;
}
function emailSelect() {
	const eSelect = document.getElementById('eSelect').value;
	const eInput2 = document.getElementById('email2');
	if (eSelect == '직접 쓰기') {
		eInput2.value = "";
		eInput2.disabled = false;
	}
	else {
		eInput2.disabled = true;
		eInput2.value = eSelect;
	}
}
function focusOut() {
	document.getElementById('pwd1').blur();
	document.getElementById('pwd2').blur();
	document.getElementById('email1').blur();
	document.getElementById('email2').blur();
}
function myPageChangeCheck() {
	focusOut();
	const pwd1 = document.getElementById('pwd1').value;
	const pwd2 = document.getElementById('pwd2').value;
	const email1 = document.getElementById('email1').value;
	const email2 = document.getElementById('email2').value;
	const sumEmail = document.getElementById('sumEmail').value;
	const file = document.getElementById('file').value;
	if (pwd1 != '' && pwd1 != pwd2) {
		alert('비밀번호가 같지 않습니다!');
		return false;
	}
	if (pwd1.length <= 7 && pwd1.length > 0) {
		alert('비밀번호는 8자리 이상이어야 합니다!');
		return false;
	}
	if (email1 && email2) {
		document.getElementById('sumEmail').value = email1 +'@'+email2;
	}
	if (!sumEmail && !pwd1 && !file) {
		alert('내용을 입력해야합니다!');
		return false;
	}
		
	return true;
}
function refreshInfo() {
	document.getElementById('pwd1').value = '';
	document.getElementById('pwd2').value = '';
	document.getElementById('file').value = '';
	document.getElementById('imgCon').value = '';
	document.getElementById('email1').value = '';
	document.getElementById('email2').value = '';
	document.getElementById('eSelect').value = '직접 쓰기';
	document.getElementById('sumEmail').value = '';
}
function sendInfo() {
	if (myPageChangeCheck()) {
		let param = JSON.stringify({
						PASSWORD: $('#pwd1').val(),
						EMAIL: $('#sumEmail').val()
					});
		$.ajax({
			type: 'put',
			contentType: "application/json",
			data: param,
			url: '/mypage/infochange/change',
			success: window.location.href = '/mypage/infochangesuccess'
		});
	}
}