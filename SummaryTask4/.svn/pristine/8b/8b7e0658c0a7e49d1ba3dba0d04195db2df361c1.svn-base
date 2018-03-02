<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Places list</title>
	<link rel="stylesheet" href="/SummaryTask4/css/styles.css">
</head>
<body>
	<c:import url="/app/admin-header.jsp"/>
	<div id="place-list-style">
		<c:if test="${not empty message}">
			<span style="color: LimeGreen">${message}</span>
			<br>
		</c:if>
		<c:if test="${not empty errorMessage}">
			<span style="color: red">${errorMessage}</span>
			<br>
		</c:if>
		<input type="button" value="Add place" class="add-button"
			onclick="window.location.href='app/add-place-form.jsp'" />
		<table>
			<thead>
				<tr>
					<th>id</th>
					<th>Country</th>
					<th>City</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="tempPlace" items="${placeList}">
					<c:url var="updateLink" value="Controller">
						<c:param name="command" value="LOAD_THE_PLACE" />
						<c:param name="placeId" value="${tempPlace.id}" />
					</c:url>
					<c:url var="deleteLink" value="Controller">
						<c:param name="command" value="DELETE_THE_PLACE" />
						<c:param name="placeId" value="${tempPlace.id}" />
					</c:url>


					<tr>
						<td>${tempPlace.id}</td>
						<td>${tempPlace.country}</td>
						<td>${tempPlace.city}</td>
						<td><a href="${updateLink}">Update</a> | <a
							href="${deleteLink}"
							onclick="if(!(confirm('Are you sure?'))) return false;">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>





	<script src="/SummaryTask4/javascript/myScript.js"></script>
</body>
</html>