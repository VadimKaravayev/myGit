<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Update vehicle</title>
	<link rel="stylesheet" href="">
</head>
<body>
	<c:import url="/app/admin-header.jsp"/>
	<h2>Update vehicle</h2>
	<form action="/SummaryTask4/Controller" method="post">
		<input type="hidden" name="command" value="UPDATE_VEHILCE"/>
		<input type="hidden" name="vehicleId" value="${vehicle.id}"/>
		<table>
			<tbody>
				<tr>
					<td><label>Model:</label></td>
					<td><input type="text" name="model" value="${vehicle.model}" required/></td>
				</tr>
				<tr>
					<td><label>Tonnage:</label></td>
					<td><input type="number" name="tonnage" value="${vehicle.tonnage}" min="5" required/></td>
				</tr>
				<tr>
					<td><label>Capacity:</label></td>
					<td><input type="number" name="capacity"  value="${vehicle.capacity}" step="0.01" min="5" required/></td>
				</tr>
				<tr>
					<td><label>Vehicle type:</label></td>
					<td>
						<select name="vehicleType">
							<option selected>${vehicle.type.name}</option>
							<c:forEach var="tempType" items="${vehicleTypes}">
								<option>${tempType.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td><label>Condition:</label></td>
					<td>
						<select name="condition">
							<c:choose>
								<c:when test="${vehicle.serviceable == true}">
									<option selected>In service</option>
								</c:when>
								<c:otherwise>
									<option selected>Out of service</option>
								</c:otherwise>
							</c:choose>
							<option>In service</option>
							<option>Out of service</option> 
						</select>
					</td>
				</tr>
				<tr>
					<td><label>Available:</label></td>
					<td>
						<select name="available">
							<c:choose>
								<c:when test="${vehicle.available == true}">
									<option value="yes" selected>Yes</option>		
								</c:when>
								<c:otherwise>
									<option value="no" selected>No</option>		
								</c:otherwise>
							</c:choose>
							<option value="yes">Yes</option>
							<option value="no">No</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Save"/></td>
				</tr>
			</tbody>
		</table>
		
	</form>
	
</body>
</html>