<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Update place</title>
	<link rel="stylesheet" href="">
</head>
<body>
	<c:import url="/app/admin-header.jsp"/>
	<h2>Update place</h2>
	<form action="/SummaryTask4/Controller" method="post">
		<input type="hidden" name="command" value="UPDATE_PLACE"/>
		<input type="hidden" name="placeId" value="${place.id}"/>
		<table>
			<tbody>
				<tr>
					<td><label>Country</label></td>
					<td><input type="text" name="country" value="${place.country}" required pattern="([A-Z]\w+\s?)+"/></td>
				</tr>
				<tr>
					<td><label>City</label></td>
					<td><input type="text" name="city" value="${place.city}" required pattern="([A-Z]\w+\s?)+"/></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Submit"/></td>
				</tr>
			</tfoot>		
		</table>
	</form>
	
	
</body>
</html>