<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Route page</title>
	<link rel="stylesheet" href="/SummaryTask4/css/styles.css"/>
	<link rel="stylesheet" href="/SummaryTask4/css/add-form-style.css"/>
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
	<div id="container">
	<h3>Route page</h3>
	<a href="javascript:goBack()">Go Back</a>

	<form action="Controller" method="post">
		<input type="hidden" name="command" value="ROUTE_UPDATE_CLICKED" /> 
		<input type="hidden" name="routeId" value="${route.id}" />

		<ul>
			<li>Route id: ${route.id}</li>
			<li>Date of creation: ${route.creationDate}</li>
			<li>Start place: ${route.fromPlace.city},
				${route.fromPlace.country}</li>
			<li>End place: ${route.toPlace.city}, ${route.toPlace.country}</li>
			<li>Route status: ${route.routeStatus}</li>
			<li>Vehicle: <c:choose>
					<c:when test="${not empty route.vehicle}">
					${route.vehicle.model} / ${route.vehicle.tonnage}, ${route.vehicle.capacity}, ${route.vehicle.type}
				</c:when>
					<c:otherwise>
					Not assigned.
				</c:otherwise>
				</c:choose>
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
		<c:if test="${not empty error_msg}">
			<p id="error_msg">${error_msg}</p>
		</c:if>
		<c:if test="${route.routeStatus != 'OPEN'}">
			<c:set var="disabled" value="disabled"/>
		</c:if>
		<input type="submit" name="button" value="Update route" />
		<input type="submit" name="button" value="Cancel route" 
			onclick="if (!(confirm('Are you sure?'))) return false" ${disabled}/>
	</form>
	</div>
	<script src="/SummaryTask4/javascript/myScript.js"></script>
</body>
</html>