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
    		success: function (data) {
    			
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
}

ko.applyBindings(new viewModel());

function handleFileSelect(evt) {
	var that = this;
    jsInstance.files = evt.target.files; 
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
