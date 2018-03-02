<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Update student</title>
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/add-student-style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Update student</h3>
		<form action="StudentControllerServlet" method="GET">
			<input type="hidden" name="command" value="UPDATE"/>
			<input type="hidden" name="studentId" value="${THE_STUDENT.id}"/>
			<table>
				<tbody>
					<tr>
						<td><label>First name: </label></td>
						<td><input type="text" name="firstName" value="${THE_STUDENT.firstName}"/></td>
					</tr>
					<tr>
						<td><label>Last name: </label></td>
						<td><input type="text" name="lastName" value="${THE_STUDENT.lastName}"/></td>
					</tr>
					<tr>
						<td><label>Email: </label></td>
						<td><input type="email" name="email" value="${THE_STUDENT.email}"/></td>
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