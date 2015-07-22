<%@ page language="java"
        contentType="text/html; charset=windows-1256"
        pageEncoding="windows-1256" %>
 
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
 
  <html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
  	<link rel="stylesheet" type="text/css" href="../css/UserLogged.css">
  	<script type='text/javascript' src='../js/jquery.js'></script>
  	<script text="text/javascript" src="../js/UserLogged.js"></script>
  	<title>Home</title>
  </head>
  <body class="main">
  	<%
  	String user = null;
  	if(session.getAttribute("currentSessionUser") == null){
  	    response.sendRedirect("UserLogged.jsp");
  	}else user = (String) session.getAttribute("currentSessionUser"); %>
  	<h1>Welcome <%=user %></h1>
  	<div class="mainDiv">
	  	<div class="tile" id="page1"><p>Manual</p><input class="inputDir" placeholder="Enter images directory"/></div>
	  	<div class="tile">Commit</div>
	  	<div class="tile">Commit</div>
  	</div>
  </body>
</html>