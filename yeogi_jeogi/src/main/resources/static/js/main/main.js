const date = new Date();
const year = date.getFullYear();
const month = ('0' + (date.getMonth() + 1)).slice(-2);
const day = ('0' + date.getDate()).slice(-2);
const dateStr = `${year}-${month}-${day}`;

const slides = document.querySelectorAll('.slide');
const toggleButtons = document.querySelectorAll('.toggle button');

let currentSlide = 0;

$(function() {
    $("#datepicker").datepicker({
        dateFormat: 'yy-mm-dd' //Input Display Format 변경
        ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
        ,showMonthAfterYear: true //년도 먼저 나오고, 뒤에 월 표시
        ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
        ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
        ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
        ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
    });                    
//    
    $('#datepicker').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)            
});


function showSlide(slideNumber) {
for (let i = 0; i < slides.length; i++) {
slides[i].classList.remove('active');
toggleButtons[i].classList.remove('active');
}
slides[slideNumber].classList.add('active');
toggleButtons[slideNumber].classList.add('active');
}

function nextSlide() {
currentSlide = (currentSlide + 1) % slides.length;
showSlide(currentSlide);
}

function prevSlide() {
currentSlide = (currentSlide - 1 + slides.length) % slides.length;
showSlide(currentSlide);
}

showSlide(0);
for (let i = 0; i < toggleButtons.length; i++) {
toggleButtons[i].addEventListener('click', function() {
    showSlide(Number(this.dataset.slide) - 1);
    currentSlide = Number(this.dataset.slide) - 1;
});
}

const interval = setInterval(nextSlide, 3000);

$(document).ready(function() {
	$(".mainCate li:first-child").addClass("on").siblings().removeClass("on");
	$('#datepicker').datepicker();
});

$(document).on("click", "input[name='location_input']", function() {
	if($(".location_wrap").css("display") == "none"){
		$(".location_wrap").css('display','block');
	}else{
		$(".location_wrap").css('display','none');
	}
});

$(document).on("click", "input[name='calendar']", function() {
	if($(".location_wrap").css("display") == "block"){
		$(".location_wrap").css('display','none');
	}
});

function getAreaA(lpId) {
	$("#area_" + lpId).addClass("on").siblings().removeClass("on");
	$(".city_wrap ul").children().removeClass("on");
	$(".city_wrap ul").children("#area_B_" + lpId).addClass("on");
}

function setAreaB(lcId, lcName) {
	$("input[name='location_input']").val(lcName);
	$('.location_wrap').css('display','none');
	$("input[name='lcId']").attr('value', lcId);
}

function searchBtn() {
	window.location.href = "products?lcId=" + $("input[name='lcId']").val() + "&sdate=" + $("input[name='sdate']").val() + "&edate=" + $("input[name='edate']").val();
}


document.querySelectorAll('.popularity_place_tabs li').forEach(function(btn, index) {
  btn.addEventListener('click', function() {
    // 모든 버튼에서 on 클래스를 제거하고 현재 클릭한 버튼에 on 클래스를 추가
    document.querySelectorAll('.popularity_place_tabs li').forEach(function(btn) {
      btn.classList.remove('on');
    });
    this.classList.add('on');

    // 해당 버튼의 순서에 따라 보여줄 내용의 id를 결정
    var contentId = '';
    switch(index) {
      case 0:
        contentId = 'seoulContent';
        break;
      case 1:
        contentId = 'gyeonggiContent';
        break;
      case 2:
        contentId = 'incheonContent';
        break;
      case 3:
        contentId = 'daejeonContent';
        break;
      case 4:
        contentId = 'daeguContent';
      break;
      default:
        contentId = 'seoulContent';
    }

    // 해당 내용을 표시
    document.querySelectorAll('.popularity_top_eight, .popularity_picture').forEach(function(content) {
      content.style.display = 'none';
    });
    document.querySelectorAll('#' + contentId + ', .' + contentId + ' .popularity_picture').forEach(function(content) {
      content.style.display = 'block';
    });
  });
});


document.querySelectorAll('.hot_place_tabs li').forEach(function(btn, index) {
  btn.addEventListener('click', function() {
    // 현재 선택된 버튼인 경우에는 컨텐츠 변경을 하지 않음
    if (this.classList.contains('on')) {
      return;
    }

    // 모든 버튼에서 on 클래스를 제거하고 현재 클릭한 버튼에 on 클래스를 추가
    document.querySelectorAll('.hot_place_tabs li').forEach(function(btn) {
      btn.classList.remove('on');
    });
    this.classList.add('on');

    // 해당 버튼의 순서에 따라 보여줄 내용의 id를 결정
    var contentId = '';
    switch(index) {
      case 0:
        contentId = 'cherryBlossoms';
        break;
      case 1:
        contentId = 'nightView';
        break;
      case 2:
        contentId = 'activity';
        break;
      case 3:
        contentId = 'cafe';
        break;
      case 4:
        contentId = 'performance';
      break;
      default:
        contentId = 'cherryBlossoms';
    }

    // 해당 내용을 표시
    document.querySelectorAll('.hot_picture-list').forEach(function(content) {
      content.style.display = 'none';
    });
    document.querySelectorAll('#' + contentId + ', .' + contentId + '.hot_picture-list').forEach(function(content) {
      content.style.display = 'block';
    });
  });
});

