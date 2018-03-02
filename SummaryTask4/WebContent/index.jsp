<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title></title>
	<link rel="stylesheet" href="/SummaryTask4/css/login-page-style.css"/>
</head>
<body>
	<div class="login-page">
		<div class="form">
			<h1>Welcome to Trucking depot!</h1>

			<form action="Controller" method="post">
				<input type="hidden" name="command" value="DO_LOGIN">
				<p style="color: red">${param.message}</p>
				<table>
					<tbody>
						<tr>
							<td>Email:</td>
							<td><input type="email" name="email"
								placeholder="user@depot.com"></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input type="password" name="password"
								placeholder="password"></td>
						</tr>
						<tr>
							<td></td>
							<td><input id="button" type="submit" value="Login"></td>
						</tr>
					</tbody>
				</table>
				<c:if test="${not empty param.error_message}">
					<p style="color: red">${param.error_message}</p>
				</c:if>
			</form>
		</div>
	</div>

</body>
</html>