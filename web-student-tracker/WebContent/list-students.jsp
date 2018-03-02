<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Student tracker app</title>
	<link rel="stylesheet" href="css/style.css">
</head>


<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<!-- put new button: add student -->
			<input type="button" value="Add student" 
				onclick="window.location.href='add-student-form.jsp'; return false;"
				class="add-student-button"/>
			<!-- search box -->
			<form action="StudentControllerServlet" method="get">
				<input type="hidden" name="command" value="SEARCH"/>
				Search student: <input type="text" name="theSearchName"/>
				<input type="submit" value="Search" class="add-student-button"/>
			</form>
			<table>
				<tr>
					<th>First name</th>
					<th>Last name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
					
				<c:forEach var="tempStudent" items="${student_list}">
					<!-- Set up a link for each student  -->
					<c:url var="tempLink" value="StudentControllerServlet">
						<c:param name="command" value="LOAD"/>
						<c:param name="studentId" value="${tempStudent.id}"/>
					</c:url>
					
					<!-- Set up a link to delete student  -->
					<c:url var="deleteLink" value="StudentControllerServlet">
						<c:param name="command" value="DELETE"/>
						<c:param name="studentId" value="${tempStudent.id}"/>
					</c:url>
					
					<tr>
						<td>${tempStudent.firstName}</td>
						<td>${tempStudent.lastName}</td>
						<td>${tempStudent.email}</td>
						<td>
							<a href="${tempLink}">Update</a>
							| 
							<a href="${deleteLink}" onclick="if (!(confirm('Are you sure?'))) 
								return false">Delete</a>
						</td>
					</tr>
				</c:forEach>	
					
			</table>
		</div>
	</div>
</body>
</html>