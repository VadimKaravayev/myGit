<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Requests for dispatcher</title>
	<link rel="stylesheet" href="/SummaryTask4/css/styles.css">
	
</head>
<body>
	
	<c:choose>
		<c:when test="${role == 'DISPATCHER'}">
			<c:import url="/app/dispatcherHeader.jsp"/>
		</c:when>
		<c:when test="${role == 'ADMIN'}">
			<c:import url="/app/admin-header.jsp"/>
		</c:when>
	</c:choose>
	<h3>Requests (Dispatcher page)</h3>
	<a href="javascript:goBack()">Go Back</a>
	<c:if test="${not empty error_msg}">
		<p id="error_msg">${error_msg}</p>
	</c:if>
	<table>
		<thead>
			<tr>
				<th>RequestID</th>
				<th>To place</th>
				<th>From place</th>
				<th>Required tonnage</th>
				<th>Required capacity</th>
				<th>Driver</th>
				<th>Status</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="tempRequest" items="${requestsList}">
				<c:url var="createrouteLink" value="Controller">
					<c:param name="command" value="CREATE_ROUTE_CLICKED"/>
					<c:param name="requestId" value="${tempRequest.id}"/>
				</c:url>
				<tr>
					<td>${tempRequest.id}</td>
					<td>${tempRequest.fromPlace.city}</td>
					<td>${tempRequest.toPlace.city}</td>
					<td>${tempRequest.vehicleTonnage}</td>
					<td>${tempRequest.vehicleCapacity}</td>
					<td>${tempRequest.driver.firstName} ${tempRequest.driver.lastName}</td>
					<td>${tempRequest.requestStatus}</td>
					<td><a href="${createrouteLink}">Create route</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	

	<script src="/SummaryTask4/javascript/myScript.js"></script>
</body>
</html>