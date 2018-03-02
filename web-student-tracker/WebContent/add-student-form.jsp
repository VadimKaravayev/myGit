<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Add student</title>
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/add-student-style.css">
</head>
<body>
	This is a form placeholder.
	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Add student</h3>
		<form action="StudentControllerServlet" method="GET">
			<input type="hidden" name="command" value="ADD"/>
			<table>
				<tbody>
					<tr>
						<td><label>First name: </label></td>
						<td><input type="text" name="firstName"/></td>
					</tr>
					<tr>
						<td><label>Last name: </label></td>
						<td><input type="text" name="lastName"/></td>
					</tr>
					<tr>
						<td><label>Email: </label></td>
						<td><input type="email" name="email"/></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save"/></td>
					</tr>
				</tbody>
			</table>
		</form>
		<div style="clear:both;"></div>
		<p>
			<a href="StudentControllerServlet">Back to list</a>
		</p>
		
	</div>
</body>
</html>