<%@ page language="java"
        contentType="text/html; charset=windows-1256"
        pageEncoding="windows-1256" %>
 
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
 
  <html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
  	<link rel="stylesheet" type="text/css" href="../css/ImageCaptioning.css">
  	<script type='text/javascript' src='../js/jquery.js'></script>
  	<script type='text/javascript' src='../js/knockout-3.3.0.js'></script>
  	<title>   Image Captioning   </title>
  </head>
  <body class="imagecaption">
  	<div class="overlay">
  		<input class="inputDir" type="file" name="files[]" multiple id="files"/>
  	</div>
  	<%
  	String user = null;
  	if(session.getAttribute("currentSessionUser") == null){
  	    response.sendRedirect("UserLogged.jsp");
  	}else user = (String) session.getAttribute("currentSessionUser"); %>
  	<div class="heading">
  		<div class="headingtext">Welcome <%=user %></div>
  		<button class="button" data-bind="click: load">Load images</button>
  		<button class="button" data-bind="click: logout">Log out</button>
  	</div>
  	<div class="layout">
	  	<div data-bind="foreach: images">
	  		<div class="tiles">
	  			<img data-bind="attr:{src:source}" class="images">
	  			<input data-bind="value: caption" class="caption"/>
	  		</div>
	  	</div>
  	</div>
  	<div class="footer">
		<button data-bind="click: save" class="button">Save</button>
		<button data-bind="click: download" class="button">Download as CSV</button>
	</div>
	<script text="text/javascript" src="../js/ImageCaptioning.js"></script>
  </body>
</html>