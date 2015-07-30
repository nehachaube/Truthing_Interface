<%@ page language="java"
        contentType="text/html; charset=windows-1256"
        pageEncoding="windows-1256" %>
 
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
 
  <html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
  	<script type='text/javascript' src='../js/jquery.js'></script>
  	<script type='text/javascript' src='../js/knockout-3.3.0.js'></script>
  	<title>   User Logged Successfully   </title>
  </head>
  <body class="main">
  <input class="inputDir" type="file" name="files[]" multiple id="files"/>
  	<table>
  		<tbody data-bind="foreach: images">
  			<tr>
  				<td><img data-bind="attr:{src:source}" height="42" width="42"></td>
  				<td><input data-bind="value: caption" /></td>
  			</tr>
  		</tbody>
  	</table>
	<button data-bind="click: save">Save</button>
	<button data-bind="click: download">Download as CSV</button>
	<script text="text/javascript" src="../js/ImageCaptioning.js"></script>
  </body>
</html>