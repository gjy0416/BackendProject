function setBold() {
	console.log(document.activeElement);
	if (document.activeElement == document.getElementById('content_text')) {
		let selection = window.getSelection();
	
		if (document.activeElement.style.fontWeight != 'bold') {
			let text = selection.getRangeAt(0).toString();
			let bold = document.createElement('span');
			bold.innerHTML = text;
	
			bold.style.fontWeight = 'bold';
			selection.getRangeAt(0).deleteContents();
			selection.getRangeAt(0).insertNode(bold);
			document.Element
		}
		else {
			Range.startContainer
		}
	
		document.getElementById('content_text').focus({preventScroll: true});
	}
	document.element
}
function setItalic() {
	
}
function setFontSize() {
	document.getElementById('content_text').style = "font-size: 3px";
}
function setFont(name) {
	document.execCommand('fontName', false, name);
	document.getElementById('content_text').focus({preventScroll: true});
}
function blankCheck() {
	if (document.getElementById('bTitle').value == "") {
		alert('제목이 없습니다!');
		return false;
	} 
	if (document.getElementById('htmlContainer').value == "") {
		alert('내용이 없습니다!');
		return false;
	} 
	return true;
}
function getHtml() {
	document.getElementById('htmlContainer').value = document.getElementById('content_text').innerHTML;
}