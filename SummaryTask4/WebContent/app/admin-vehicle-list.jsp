<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Admin vehicle list</title>
	<link rel="stylesheet" href="/SummaryTask4/css/styles.css">
</head>
<body>
	<c:import url="/app/admin-header.jsp"/>
	<h3>Admin vehicle list</h3>
	
	<input type="button" value="Add vehicle" class="add-button" onclick="window.location.href='app/add-vehicle-form.jsp'"/>
	<c:if test="${not empty message}">
		<span style="color:LimeGreen">${message}</span>
	</c:if>
	<table>
		<thead>
			<tr>
				<th>id</th>
				<th>Model</th>
				<th>Tonnage</th>
				<th>Capacity</th>
				<th>Vehicle type</th>
				<th>Condition</th>
				<th>Available</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="tempVehicle" items="${vehicleList}">
				<c:url var="updateLink" value="Controller">
					<c:param name="command" value="LOAD_THE_VEHICLE"/>
					<c:param name="vehicleId" value="${tempVehicle.id}"/>
				</c:url>
				<c:url var="deleteLink" value="Controller">
					<c:param name="command" value="DELETE_VEHICLE"/>
					<c:param name="vehicleId" value="${tempVehicle.id}"/>
				</c:url>
				<tr>
					<td>${tempVehicle.id}</td>
					<td>${tempVehicle.model}</td>
					<td>${tempVehicle.tonnage}</td>
					<td>${tempVehicle.capacity}</td>
					<td>${tempVehicle.type.name}</td>
					<td>
						<c:choose>
							<c:when test="${tempVehicle.serviceable eq true}">
								In service
							</c:when>
							<c:otherwise>
								Out of service
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${tempVehicle.available eq true}">
								Yes
							</c:when>
							<c:otherwise>
								No
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<a href="${updateLink}">Update</a> 
					| 
						<a href="${deleteLink}" onclick="if(!confirm('Are you sure?')) return false;">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</body>
</html>