<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Add place</title>
	<link rel="stylesheet" href="/SummaryTask4/css/styles.css"/>
	<link rel="stylesheet" href="/SummaryTask4/css/add-form-style.css"/>
</head>
<body>
	<c:import url="/app/admin-header.jsp"/>
	<div id="container">
		<h3>Add place</h3>
		<form action="/SummaryTask4/Controller" method="post">
			<input type="hidden" name="command" value="ADD_PLACE" />
			<table>
				<tbody>
					<tr>
						<td><label>Country</label></td>
						<td><input type="text" name="coutry" required
							pattern="([A-Z]\w+\s?)+" /></td>
					</tr>
					<tr>
						<td><label>City</label></td>
						<td><input type="text" name="city" required
							pattern="([A-Z]\w+\s?)+" /></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Submit" class="add-button"/></td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>
</html>