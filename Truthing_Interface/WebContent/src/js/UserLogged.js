width = $(window).width();
height = $(window).height();
$('html').css('width', width*0.95);
$('html').css('height', height*0.95);

$(document).ready(function(){
    $('#page1').click(function(){
    	window.location.href = "../jsp/ImageCaptioning.jsp";
    }); 
});

function logout(){
	$.ajax({
		method:"POST",
		url: "../../LoginServlet",
		success: function (response) {
			window.location.href = "../jsp/UserLogin.jsp";
		},
		error: function(response){
			alert("logout error");
		}
	});
}

$(document).ready(function(){
	document.getElementById("logout").onclick = logout;
});
