<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>New route page</title>
<link rel="stylesheet" href="/SummaryTask4/css/styles.css"/>
<link rel="stylesheet" href="/SummaryTask4/css/add-form-style.css"/>


</head>
<body onload="currentDate()">
	
	<c:choose>
		<c:when test="${role == 'DISPATCHER'}">
			<c:import url="/app/dispatcherHeader.jsp"/>
		</c:when>
		<c:when test="${role == 'ADMIN'}">
			<c:import url="/app/admin-header.jsp"/>
		</c:when>
	</c:choose>
	<div id="container">
		<h3>New route page</h3>
		<a href="javascript:goBack()">Go Back</a>

		<form action="Controller" method="post">
			<input type="hidden" name="command" value="CREATE_ROUTE" />
			<c:url var="editVehicleFieldLink" value="Controller">
				<c:param name="command" value="LIST_VEHICLES" />
				<c:param name="requestId" value="${routeRequest.id}" />
			</c:url>

			<input type="hidden" name="requestId" value="${routeRequest.id}" /> <input
				type="hidden" name="vehicleId" value="${vehicle.id}" />
			<c:choose>
				<c:when test="${not empty vehicle}">
					<c:set var="display" value="${vehicle.model}, ${vehicle.type}" />
					<c:set var="status" value="UNDERWAY" />
				</c:when>
				<c:otherwise>
					<c:set var="display" value="Not assigned" />
					<c:set var="status" value="OPEN" />
				</c:otherwise>
			</c:choose>
			<table>
				<tbody>
					<tr>
						<td><label>Date of creation:</label></td>
						<td><input id="datePicker" type="date"readonly/></td>
					</tr>
					<tr>
						<td><label>Start place: </label></td>
						<td><input type="text" value="${routeRequest.fromPlace.city}, ${routeRequest.fromPlace.country}" readonly /></td>
					</tr>				
					<tr>
						<td><label>End place: </label></td>
						<td><input type="text" value="${routeRequest.toPlace.city}, ${routeRequest.toPlace.country}" readonly /></td>
					</tr>				
					<tr>
						<td><label>Route status: </label></td>
						<td><input type="text" name="routeStatus" value="${status}" readonly /></td>
					</tr>				
					<tr>
						<td><label>Vehicle: </label></td>
						<td><input type="text" value="${display}" readonly/> <a href="${editVehicleFieldLink}">edit</a></td>
					</tr>				
					<tr>
						<td><label>Driver: </label></td>
						<td><input type="text" value="${routeRequest.driver.firstName} ${routeRequest.driver.lastName}" readonly /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Create route" class="add-button"/></td>
					</tr>				
				</tbody>
			</table>
		</form>
	</div>





	<script src="/SummaryTask4/javascript/myScript.js"></script>
</body>
</html>