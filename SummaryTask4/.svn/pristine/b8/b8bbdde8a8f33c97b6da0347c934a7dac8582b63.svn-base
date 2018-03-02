<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Driver page</title>
<link rel="stylesheet" href="/SummaryTask4/css/styles.css">

</head>
<body>
	<h1>Driver page</h1>
	<p>You are logged as ${role}, ${userName}</p>
	<c:import url="/app/header.jsp" />
	<h3>My requests</h3>
	<input type="button" value="New request" onclick="goToNewRequestPage()" class="add-button"/>
	<table>
		<thead>
			<tr>
				<th>RequestID</th>
				<th>From</th>
				<th>To</th>
				<th>RequestStatus</th>
				<th>RouteNote</th>
				<th>Driver name</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="tempRequest" items="${requestsList}">
				<c:url var="completeLink" value="Controller">
					<c:param name="command" value="COMPLETE_ROUTE" />
					<c:param name="requestId" value="${tempRequest.id}" />
				</c:url>
				<c:url var="updateLink" value="Controller">
					<c:param name="command" value="LOAD_REQUEST" />
					<c:param name="requestId" value="${tempRequest.id}" />
				</c:url>
				<tr>
					<td>${tempRequest.id}</td>
					<td>${tempRequest.fromPlace.city}</td>
					<td>${tempRequest.toPlace.city}</td>
					<td>${tempRequest.requestStatus}</td>
					<td>${tempRequest.routeNote}</td>
					<td>${tempRequest.driver.firstName}
						${tempRequest.driver.lastName}</td>
					<td>
						<a href="${updateLink}">Update</a> | 
						<a href="${completeLink}" id="completeLink">Complete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script src="/SummaryTask4/javascript/myScript.js"></script>
</body>
</html>