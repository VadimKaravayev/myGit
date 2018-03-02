<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Update request form</title>
	<link rel="stylesheet" href="/SummaryTask4/css/styles.css"/>
	<link rel="stylesheet" href="/SummaryTask4/css/add-form-style.css"/>
	
</head>

<body onload="disableButton()">
	<div id="container">
		<h3>Update request form</h3>
		<a href="javascript:goBack()">Go Back</a>
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="UPDATE_REQUEST"> <input
				type="hidden" name="requestId" value="${routeRequest.id}">
			<table>
				<tbody>
					<tr>
						<td><label>From</label></td>
						<td><select name="fromPlace">
								<option value="${routeRequest.fromPlace.id}" selected>
									${routeRequest.fromPlace.country},
									${routeRequest.fromPlace.city}</option>
								<c:forEach var="tempPlace" items="${placeList}">
									<c:if test="${tempPlace.id != routeRequest.fromPlace.id}">
										<option value="${tempPlace.id}">
											${tempPlace.country}, ${tempPlace.city}</option>
									</c:if>

								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label>To</label></td>
						<td><select name="toPlace">
								<option value="${routeRequest.toPlace.id}" selected>
									${routeRequest.toPlace.country}, ${routeRequest.toPlace.city}</option>
								<c:forEach var="tempPlace" items="${placeList}">
									<c:if test="${tempPlace.id != routeRequest.toPlace.id}">
										<option value="${tempPlace.id}">${tempPlace.country},
											${tempPlace.city}</option>
									</c:if>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label>Required tonnage(t): </label></td>
						<td><input type="number" name="tonnage" min="5" max="25"
							value="${routeRequest.vehicleTonnage}" /></td>
					</tr>
					<tr>
						<td><label>Required capacity(m3): </label></td>
						<td><input type="number" name="capacity" min="50" max="120"
							value="${routeRequest.vehicleCapacity}" /></td>
					</tr>
					<tr>

						<td><label></label></td>
						<td><c:if
								test="${routeRequest.requestStatus eq 'CONFIRMED' || routeRequest.requestStatus eq 'DECLINED'}">
								<c:set var="disabled" value="disabled"></c:set>
							</c:if> <input type="submit" value="Update" ${disabled} class="add-button"/></td>
					</tr>
				</tbody>
			</table>

		</form>
	</div>
	<script src="/SummaryTask4/javascript/myScript.js "></script>	
</body>
</html>