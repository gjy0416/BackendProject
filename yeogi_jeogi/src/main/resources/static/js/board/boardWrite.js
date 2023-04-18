var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
  oAppRef: oEditors,
  elPlaceHolder: document.getElementById('content_text'),
  sSkinURI: "/smarteditor/SmartEditor2Skin.html",
  fCreator: "createSEditor2"
});
function blankCheck() {
	oEditors.getById["content_text"].exec("UPDATE_CONTENTS_FIELD", []);
	if (document.getElementById('bTitle').value == "") {
		alert('제목이 없습니다!');
		return false;
	} 
	if (document.getElementById('content_text').value == "" || $('#content_text').val() == '<p><br></p>' || $('#content_text').val() == '<br>') {
		alert('내용이 없습니다!');
		return false;
	} 
	return true;
}