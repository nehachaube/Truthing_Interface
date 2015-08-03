<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html >
  <head>
    <meta charset="UTF-8">
    <title>Truthing Interface</title>
        <link rel="stylesheet" href="../css/style.css">  
  </head>
  <body>
  <div class="login-wrap">
  <h2>Truthing Interface</h2>
  <form action="../../LoginServlet">
  <div class="form">
    <input type="text" placeholder="Username" name="un" />
    <input type="password" placeholder="Password" name="pw" />
    <input type="submit" value="submit">
    </form>   
  </div>
</div>

<a href="RegisterBootStrap.jsp">Register</a>
<div class="col-lg-5 col-md-push-1">
            <div class="col-md-12">
                <div class="alert alert-success">
                    <strong name="msg"><span class="glyphicon glyphicon-ok"></span> 
<%
    if(null!=request.getAttribute("errorMessage"))
    {
        out.println(request.getAttribute("errorMessage"));
    }
%>
</strong>
                </div>
                </div>
                </div>
   <!--   <script src='https://code.jquery.com/jquery-1.10.0.min.js'></script>
    <script src="js/index.js"></script>  -->
  </body>
</html>
</html>