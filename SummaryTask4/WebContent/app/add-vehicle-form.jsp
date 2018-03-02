<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Add vehicle</title>
	<link rel="stylesheet" href="/SummaryTask4/css/styles.css"/>
	<link rel="stylesheet" href="/SummaryTask4/css/add-form-style.css"/>
</head>
<body>
	<c:import url="/app/admin-header.jsp"/>
	<div id="container">
		<h3>Add vehicle</h3>
		<form action="/SummaryTask4/Controller" method="post">
			<input type="hidden" name="command" value="ADD_VEHILCE" />
			<table>
				<tbody>
					<tr>
						<td><label>Model:</label></td>
						<td><input type="text" name="model" required /></td>
					</tr>
					<tr>
						<td><label>Tonnage:</label></td>
						<td><input type="number" name="tonnage" min="5" required /></td>
					</tr>
					<tr>
						<td><label>Capacity:</label></td>
						<td><input type="number" name="capacity" step="0.01" min="5"
							required /></td>
					</tr>
					<tr>
						<td><label>Vehicle type:</label></td>
						<td><select name="vehicleType" class="sel">
								<c:forEach var="tempType" items="${vehicleTypes}">
									<option>${tempType.name}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label>Condition:</label></td>
						<td><select name="condition">
								<option>In service</option>
								<option>Out of service</option>
								<!-- <input type="text" name="vehicleType"/> -->
						</select></td>
					</tr>
					<tr>
						<td><label>Available:</label></td>
						<td><select name="available">
								<option value="yes">Yes</option>
								<option value="no">No</option>
						</select></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="add-button"/></td>
					</tr>
				</tbody>
			</table>

		</form>
	</div>
</body>
</html>