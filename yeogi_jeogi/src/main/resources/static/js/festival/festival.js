const urlParams = new URL(location.href).searchParams;
const name = urlParams.get('lcName');

function setPaging(page) {
   $(".list li").css("display", "none");
   $(".list li ul li").css("display", "block");
   $(".paging a").css("display", "none");
   $("input[name='page']").attr('value', page);
   for(let i = page - 1; i < page + (page == 1 ? 6 : page == 2 ? 5 : 4); i++) {    
      $(".paging a:nth-child(" + i + ")").css("display", "block");
      $(".paging a:nth-child(" + i + ")").css("color", "black");
   }
   for(let i = (page - 1) * 5 + 1; i < (page - 1) * 5 + 6; i++) {
      $(".list li:nth-child(" + i + ")").css("display", "block");
   }
   $(".paging a:nth-child(1)").css("display", "block");
   $(".paging a:nth-child(" + (page + 1) + ")").css("color", "#2E9BFF");
   $(".paging a:nth-child(" + $(".paging a").length + ")").css("display", "block");
}

$(document).ready(function() {
   $(".list li").css("display", "none");
   $(".paging a").css("display", "none");
   $(".paging a:nth-child(" + $(".paging a").length + ")").css("display", "block");
   for(let i = 1; i < 6; i++) {
      $(".list li:nth-child(" + i + ")").css("display", "block");
   }
   for(let i = 1; i < 7; i++) {
      $(".paging a:nth-child(" + i + ")").css("display", "block");
      $(".paging a:nth-child(2)").css("color", "#2E9BFF");
   }
	$("input[name='city']").change(function(){
	   	window.location.href = "festival?lcName=" + $("input[name='city']:checked").val();
	});
   for(let i = 0; i < 30; i++) {
	   //alert();
	   if($("input[name='city']").get(i).value == name) {
		   $("input[name='city']").eq(i).prop("checked", true);
		//alert('asdf');
	   }
   }
});
