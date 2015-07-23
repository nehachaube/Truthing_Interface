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
	var fileExt = {};
	fileExt[0]=".png";
	fileExt[1]=".jpg";
	fileExt[2]=".gif";
	var url = window.location.search.substring(1).split("=")[1];
	$.ajax({
		//This will retrieve the contents of the folder if the folder is configured as 'browsable'
		data: url,
		url: "../../GetFolderContents",
		success: function (data) {
			data = data.split("\t");
			jsInstance.pathList = data;
		}     
	});
}

ko.applyBindings(new viewModel());

function handleFileSelect(evt) {
	var that = this;
    var files = evt.target.files; 
    for (var i = 0, f; f = files[i]; i++) {
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
