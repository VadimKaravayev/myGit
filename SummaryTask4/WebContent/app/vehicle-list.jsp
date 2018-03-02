<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Vehicle list</title>
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
	<h3>Vehicle list</h3>
	<a href="javascript:goBack()">Go Back</a>
	
	<p>Required tonnage: ${routeRequest.vehicleTonnage} tons</p>
	<p>Required capacity: ${routeRequest.vehicleCapacity} m3</p>
	<input type="button" class="add-button" onclick="sortOutVehicles(${routeRequest.id}, ${routeId})" value="Sort out"/>
	
	<table>
		<thead>
			<tr>
				<th>VehicleID</th>
				<th>Model</th>
				<th>Tonnage</th>
				<th>Capacity</th>
				<th>Type</th>
				<th>Condition</th>
				<th>Available</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="tempVehicle" items="${vehicleList}">
				<c:url var="assignLink" value="Controller">
					<c:param name="command" value="ASSIGN_VEHICLE"/>
					<c:param name="vehicleId" value="${tempVehicle.id}"/>
					<c:param name="requestId" value="${routeRequest.id}"/>
					<c:if test="${not empty routeId}">
						<c:param name="routeId" value="${routeId}"/>
					</c:if>
					
				</c:url>
				<tr>
					<td>${tempVehicle.id}</td>
					<td>${tempVehicle.model}</td>
					<td>${tempVehicle.tonnage}</td>
					<td>${tempVehicle.capacity}</td>
					<td>${tempVehicle.type}</td>
					<td>
						<c:choose>
							<c:when test="${tempVehicle.serviceable == true}">
								in service
							</c:when>
							<c:otherwise>
								out of service
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${tempVehicle.available == true}">
								yes
							</c:when>
							<c:otherwise>
								no
							</c:otherwise>
						</c:choose>
					</td>
					<td><a href="${assignLink}">Assign</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script src="/SummaryTask4/javascript/myScript.js"></script>
</body>
</html>

<!-- private int id;
	private String model;
	private int tonnage;
	private double capacity;
	private VehicleType type;
	private boolean isServiceable;
	private boolean isAvailable; -->