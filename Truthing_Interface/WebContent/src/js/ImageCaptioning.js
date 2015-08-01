width = $(window).width();
height = $(window).height();
$('html').css('width', width);
$('html').css('height', height*0.90);
var jsInstance = this;
function imageData(source, caption) {
    var that = this;
    that.source = source;
	that.caption = caption;
}

function viewModel() {
    var that = this;   
    jsInstance.images = ko.observableArray([]);
    that.save = function(){
    	data = [];
    	for(var i = 0; i < jsInstance.images().length; i++){
    		data[i] = {};
    		data[i] = {"name":jsInstance.files[i].name, "caption":jsInstance.images()[i].caption};
    	}
    	$.ajax({
    		data: {
    				loadProds: 1,
    				imgcaption:JSON.stringify(data)
    		},
    		dataType: 'JSON',
    		url: "../../ImageCaptionServlet",
    		success: function (response) {
    			if(response.status == 200){
    				alert("save successful");
    			}
    		},
    		error: function(response){
    			if(response.status == 200){
    				alert("save successful");
    			}
    		}
    	});
    }
	that.download = function() {
		var csvRows = [['source','caption']];
		var images = jsInstance.images();
		for(var i=1; i < images.length; i++){
			csvRows.push([images[i].source,images[i].caption]);
		}
		
		var csvString = csvRows.join("%0A");
		var a = document.createElement('a');
		a.href = 'data:attachment/csv,' + csvString;
		a.target = '_blank';
		a.download = 'myFile.csv';
		
		//document.body.appendChild(a);
		a.click();
	}
	
	that.load = function(){
		$(".overlay").css("visibility","visible");
	}
	that.logout = function(){
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
}

ko.applyBindings(new viewModel());

function handleFileSelect(evt) {
	var that = this;
    jsInstance.files = evt.target.files; 
    $(".overlay").css("visibility","hidden");
    var s = "";
    for (var i = 0, f; f = jsInstance.files[i]; i++) {
    	s += "'"+f.name+"',";
    }
    s = s.substring(0,s.length-1);
    console.log(s);
    $.ajax({
    	data: {
			loadProds: 1,
			imgcaptionstring:s
    	},
		method:"POST",
		url: "../../ImageCaptionUserServlet",
		success: function (response) {
			alert("save successful");
		},
		error: function(response){
			if(response.status == 200){
				alert("save successful");
			}
		}
	});
    for (var i = 0, f; f = jsInstance.files[i]; i++) {
      if (!f.type.match('image.*')) {
        continue;
      }

      var reader = new FileReader();
      reader.onload = (function(theFile) {
        return function(e) {
        	
        	jsInstance.images.push(new imageData(e.target.result,""));
        };
      })(f);
      reader.readAsDataURL(f);
    }
  }
$(document).ready(function(){
	document.getElementById('files').addEventListener('change', handleFileSelect, false);
});
