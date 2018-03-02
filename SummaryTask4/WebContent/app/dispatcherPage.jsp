<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Dispatcher page</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
	<h3>Dispatcher page</h3>
	<p>You are logged as ${role}, ${userName}</p>

	<div class="sidenav">
		<button class="dropdown-btn">
			Sort <i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown-container">
			<a href="Controller?command=SORT_ROUTES&criterion=id"> By route id</a> 
			<a href="Controller?command=SORT_ROUTES&criterion=date">By date</a>
			<button class="dropdown-btn">
				By status <i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-container">
				<a href="Controller?command=SORT_ROUTES&criterion=open">Open</a> 
				<a href="Controller?command=SORT_ROUTES&criterion=underway">Underway</a> 
				<a href="Controller?command=SORT_ROUTES&criterion=closed">Closed</a>
				<a href="Controller?command=SORT_ROUTES&criterion=canceled">Canceled</a>
			</div>
		</div>
	</div>
	<c:if test="${not empty message}">
		<span style="color:LimeGreen">${message}</span>
	</c:if>
	<div id="container">
		<div id="content">
			<table>
				<thead>
					<tr>
						<th>RouteID</th>
						<th>Creation date</th>
						<th>From place</th>
						<th>To place</th>
						<th>Status</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="tempRoute" items="${routesList}">
						<c:url var="detailsLink" value="Controller">
							<c:param name="command" value="VIEW_ROUTE" />
							<c:param name="routeId" value="${tempRoute.id}" />
						</c:url>
						<tr>
							<td>${tempRoute.id}</td>
							<td>${tempRoute.creationDate}</td>
							<td>${tempRoute.fromPlace.city}</td>
							<td>${tempRoute.toPlace.city}</td>
							<td>${tempRoute.routeStatus}</td>
							<td><a href="${detailsLink}">Details</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script>
	doFilter();
	</script>
	<script src="/SummaryTask4/javascript/myScript.js"></script>
</body>
</html>