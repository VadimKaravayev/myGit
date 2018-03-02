<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Add person form</title>
	<link rel="stylesheet" href="/SummaryTask4/css/styles.css"/>
	<link rel="stylesheet" href="/SummaryTask4/css/add-form-style.css"/>
	
</head>
<body>
	<c:import url="/app/admin-header.jsp"/>
	<div id="container">
		<h3>Add new person</h3>

		<form action="/SummaryTask4/Controller" method="post">
			<input type="hidden" name="command" value="ADD_NEW_PERSON" />
			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td><input type="text" name="firstName" required /></td>
					</tr>
					<tr>
						<td><label>Last name:</label></td>
						<td><input type="text" name="lastName" required /></td>
					</tr>
					<tr>
						<td><label>Email:</label></td>
						<td><input type="email" name="email" required /></td>
					</tr>
					<tr>
						<td><label>Password:</label></td>
						<td><input id="pass" type="password" name="password" required /></td>
					</tr>
					<tr>
						<td><label>Confirm password:</label></td>
						<td><input id="pass2" type="password" required
							onchange="checkPasswordMatch()" /> <span id="msg"></span></td>
					</tr>
					<tr>
						<td><label>Role:</label></td>
						<td><input type="text" name="role" required /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input id="sub" class="add-button" type="submit" value="Submit" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<script src="/SummaryTask4/javascript/myScript.js "></script>	
</body>
</html>