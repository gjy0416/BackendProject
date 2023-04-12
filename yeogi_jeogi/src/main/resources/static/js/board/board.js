function resizeTextArea(obj) {
	obj.style.height = '100px';
	obj.style.height = (obj.scrollHeight)+'px';
}
function setHref() {
	let sSort = document.getElementById('sSort').value;
	let sWord = document.getElementById('sWord').value;
 	let href = window.location.protocol+"//"+window.location.host+'/search?sSort='+sSort+'&sWord='+sWord;

	window.location = href;
}
function rcmRefresh(){  
      $("rcm").load(window.location.href + "rcm");
}
function comContentBlankCheck() {
	if (document.getElementById('cContent').value == '') {
		alert('댓글 내용이 없습니다!');
		return false;
	}
	return true;
}
function sortChangeList() {
	const sort = document.getElementById('board_sort').value;
	console.log("함수 들어옴");
	$.ajax({
		type: 'post',
		url: '/board/freesort',
		contentType: 'application/json; charset=utf-8',
		data: sort,
		success: (data) => {
			 if (data) {
			 	console.log("새로고침");
			 	$('#foreach').load(location.href+' #foreach');
			 }
		}
	})
}