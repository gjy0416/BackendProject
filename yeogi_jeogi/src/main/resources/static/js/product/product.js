
let csA = 0;
let csB = 0;
let csC = 0;

const sA = document.querySelectorAll('.listA');
const sB = document.querySelectorAll('.listB');
const sC = document.querySelectorAll('.listC');

$(document).ready(function() {
	$(".listA li").css("display", "none");
	$(".listB li").css("display", "none");
	$(".listC li").css("display", "none");
	for(let i = 1; i < 5; i++) {
		$(".listA li:nth-child(" + i + ")").css("display", "block");
		$(".listB li:nth-child(" + i + ")").css("display", "block");
		$(".listC li:nth-child(" + i + ")").css("display", "block");
	}
});

function left_button(cN) {
	if ($("input[name='" + cN + "']").val() != 1) {
		$("." + cN + " li").css("display", "none");
		$("input[name='" + cN + "']").attr('value', parseInt($("input[name='" + cN + "']").val()) - 1);
		for(let i = parseInt($("input[name='" + cN + "']").val()); i < parseInt($("input[name='" + cN + "']").val()) + 4; i++) {
			$("." + cN + " li:nth-child(" + i + ")").css("display", "block");
		}
	}
}

function right_button(cN) {
	if ($("input[name='" + cN + "']").val() != $("." + cN + " li").length - 3) {
		$("." + cN + " li").css("display", "none");
		$("input[name='" + cN + "']").attr('value', parseInt($("input[name='" + cN + "']").val()) + 1);
		for(let i = parseInt($("input[name='" + cN + "']").val()); i < parseInt($("input[name='" + cN + "']").val()) + 4; i++) {
			$("." + cN + " li:nth-child(" + i + ")").css("display", "block");
		}
	}
}