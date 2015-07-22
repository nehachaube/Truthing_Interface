width = $(window).width();
height = $(window).height();
$('html').css('width', width);
$('html').css('height', height);

$(document).ready(function(){
    $('#page1').click(function(){
    	//window.location.href = "../html/index.html";
    	$('.inputDir').css('visibility','visible');
    });
    $('.inputDir').change(function(){
    	window.location.href = "../jsp/ImageCaptioning.jsp?path="+this.value;
    });
});