function imageData(source, caption) {
	    var self = this;
	    self.source = source;
		self.caption = caption;
	}

	function viewModel() {
	    var self = this;   
	    self.images = ko.observableArray([]);
	    self.save = function(){
	    	
	    }
	    self.download = function() {
	       	var csvRows = [['source','caption']];
			images = self.images();
	    	for(var i=1; i < images.length; i++){
	    	    csvRows.push([images[i].source,images[i].caption]);
	    	}

	    	var csvString = csvRows.join("%0A");
	    	var a         = document.createElement('a');
	    	a.href        = 'data:attachment/csv,' + csvString;
	    	a.target      = '_blank';
	    	a.download    = 'myFile.csv';

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
	    url: url,
	    success: function (data) {
	       $(data).find("a:contains(" + fileExt[0] + "),a:contains(" + fileExt[1] + "),a:contains(" + fileExt[2] + ")").each(function () {
	           var filename = this.href;
	           self.images.push(new imageData(filename,""));
	       });
	     }     
	  });
	}

	ko.applyBindings(new viewModel());