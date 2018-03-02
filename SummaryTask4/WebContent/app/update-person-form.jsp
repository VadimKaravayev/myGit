<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Update person form</title>
	<link rel="stylesheet" href="">
	
</head>
<body>
	<c:import url="/app/admin-header.jsp"/>
	<h2>Update person</h2>
	
	<form action="/SummaryTask4/Controller" method="post">
		<input type="hidden" name="command" value="UPDATE_PERSON"/>
		<input type="hidden" name="personId" value="${person.id}"/>
		<table>
			<tbody>
				<tr>
					<td><label>First name:</label></td>
					<td><input type="text" name="firstName" value="${person.firstName}" required/></td>
				</tr>
				<tr>
					<td><label>Last name:</label></td>
					<td><input type="text" name="lastName" value="${person.lastName}" required/></td>
				</tr>
				<tr>
					<td><label>Email:</label></td>
					<td><input type="email" name="email" value="${person.email}" required/></td>
				</tr>
				<tr>
					<td><label>Password:</label></td>
					<td><input id="pass" type="password" name="password" value="${person.password}" required/></td>
				</tr>
				<tr>
					<td><label>Confirm password:</label></td>
					<td><input id="pass2" type="password" value="${person.password}" required onchange="checkPasswordMatch()"/>
						<span id="msg"></span>
					</td>
				</tr>
				<tr>
					<td><label>Role:</label></td>
					<td><input type="text" name="role" value="${person.role.roleName}" required/></td>
				</tr>
				<tr>
					<td><label></label></td>
					<td><input id="sub" type="submit" value="Submit"/></td>
				</tr>
			</tbody>
		</table>
		
	</form>

<script src="/SummaryTask4/app/javascript/myScript.js "></script>	
</body>
</html>