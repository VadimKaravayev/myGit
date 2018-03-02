<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Complete route page</title>
<link rel="stylesheet" href="/SummaryTask4/css/styles.css"/>
	

<style>
table, th, td {
	border: 1px solid black;
}
</style>

</head>
<body>
	<h3>Complete route page</h3>
	<p>You are going to complete the route</p>
	<a href="javascript:goBack()">Go Back</a>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="COMPLETE_ROUTE_CONFIRMED">
		<input type="hidden" name="requestId" value="${routeRequest.id}"/>
		<table>
			<thead>
				<tr>
					<th>id</th>
					<th>from</th>
					<th>to</th>
					<th>Driver name</th>
					<th>Route note</th>
					<th>Vehicle serviceability</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${routeRequest.id}</td>
					<td>${routeRequest.fromPlace.city}</td>
					<td>${routeRequest.toPlace.city}</td>
					<td>${routeRequest.driver.firstName} ${routeRequest.driver.lastName}</td>
					<td><input type="text" name="routeNote" value="Closed"/></td>
					<td>
						<select name="serviceability">
							<option>Positive</option>
							<option>Negative</option>
						</select>
					</td>
				</tr>
			</tbody>
		</table>
		<input type="submit" value="Confirm" class="add-button">
	</form>
<script src="/SummaryTask4/javascript/myScript.js "></script>	
</body>
</html>