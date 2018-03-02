<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Update route page</title>
	<link rel="stylesheet" href="css/styles.css">
	<script src="javascript/myScript.js"></script>
</head>
<body>
	<h2>Update route page</h2>
	<a href="javascript:goBack()">Go Back</a>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="UPDATE_ROUTE" /> 
		<input type="hidden" name="routeId" value="${route.id}" />
		<input type="hidden" name="newVehicle" value="${route.vehicle.id}"/>
		<input type="hidden" name="newRouteStatus" value="${route.routeStatus}"/>

		<ul>
			<li>Route id: ${route.id}</li>
			<li>Date of creation: ${route.creationDate}</li>
			<li>Start place: ${route.fromPlace.city},
				${route.fromPlace.country}</li>
			<li>End place: ${route.toPlace.city}, ${route.toPlace.country}</li>
			<li>Route status: ${route.routeStatus}</li>
			<li>Vehicle: 
				<c:url var="editVehicleFieldLink" value="Controller">
					<c:param name="command" value="LIST_VEHICLES"/>
					<c:param name="requestId" value="${route.request.id}"/>
					<c:param name="routeId" value="${route.id}"/>
				</c:url>
			
			<c:choose>
					<c:when test="${not empty route.vehicle}">
					${route.vehicle.model} / ${route.vehicle.tonnage}, ${route.vehicle.capacity}, ${route.vehicle.type}
				</c:when>
					<c:otherwise>
					Not assigned.
				</c:otherwise>
				</c:choose>
				<a href="${editVehicleFieldLink}">edit</a>
			</li>
			<li>Driver: 
				<c:choose>
					<c:when test="${not empty route.driver}">
						${route.driver.firstName} ${route.driver.lastName}
					</c:when>
					<c:otherwise>
						Not assigned.
					</c:otherwise>
				</c:choose>


			</li>
			
			<li>Request:
				<c:choose>
					<c:when test="${not empty route.request}">
					${route.request.id}
				</c:when>
					<c:otherwise>
					Not assigned
				</c:otherwise>
				</c:choose>
			</li>
		</ul>
		
		
		<input type="submit" value="Save"/>
	</form>
	
	

</body>
</html>