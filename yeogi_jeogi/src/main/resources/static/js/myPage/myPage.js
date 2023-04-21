function myPageChange() {
	if (document.getElementById('pagechanger').value == 'travel') {
		document.getElementById('wants_travel').style.display = 'block';
		document.getElementById('wants_package').style.display = 'none';
		document.getElementById('wants_festival').style.display = 'none';
	}
	else if (document.getElementById('pagechanger').value == 'package') {
		document.getElementById('wants_travel').style.display = 'none';
		document.getElementById('wants_package').style.display = 'block';
		document.getElementById('wants_festival').style.display = 'none';
	}
	else {
		document.getElementById('wants_travel').style.display = 'none';
		document.getElementById('wants_package').style.display = 'none';
		document.getElementById('wants_festival').style.display = 'block';
	}
}

function myPageWritesChange() {
	const freeLink = "/mypage/bfwrites";
	const travelLink = "/mypage/btwrites";
	
	if (document.getElementById('pagechanger').value == 'free') {
		if (window.location.pathname != "freeLink") {
			window.location.pathname = freeLink;
		}
	}
	else {
		if (window.location.pathname != "travelLink") {}
			window.location.pathname = travelLink;
		}
	}
function dataNumPost(thisA) {
	document.getElementByName('datacon').value = thisA.parentNode.lastChild.value;
	console.log("완료");
}