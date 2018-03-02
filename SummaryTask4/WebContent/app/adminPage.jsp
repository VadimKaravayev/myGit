<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Admin page</title>
	<link rel="stylesheet" href="/SummaryTask4/css/styles.css"/>
</head>
<body>
	<c:import url="/app/admin-header.jsp"/>
	<c:if test="${not empty message}">
		<p style="color:LimeGreen">${message}</p>
	</c:if>
	<h3>Admin page</h3>
	
	<input type="button" value="Add person" class="add-button"
		onclick="window.location.href='app/add-person-form.jsp'"/>
	<table>
		<thead>
			<tr>
				<th>id</th>
				<th>First name</th>
				<th>Last name</th>
				<th>Email</th>
				<th>Role</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="tempPerson" items="${peopleList}">
				<c:url var="updateLink" value="Controller">
					<c:param name="command" value="LOAD_PERSON"/>
					<c:param name="personId" value="${tempPerson.id}"/>
				</c:url>
				<c:url var="deleteLink" value="Controller">
					<c:param name="command" value="DELETE_PERSON"/>
					<c:param name="personId" value="${tempPerson.id}"/>
				</c:url>
				
				<tr>
					<td>${tempPerson.id}</td>
					<td>${tempPerson.firstName}</td>
					<td>${tempPerson.lastName}</td>
					<td>${tempPerson.email}</td>
					<td>${tempPerson.role.roleName}</td>
					<td>
						<a href="${updateLink}">Update</a> | 
						<a href="${deleteLink}" onclick="if(!(confirm('Are you sure?'))) 
							return false">Delete</a>
					</td>
				</tr>
			</c:forEach>
			
		</tbody>
	</table>
</body>
</html>