<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>New request form</title>
	<link rel="stylesheet" href="/SummaryTask4/css/styles.css"/>
	<link rel="stylesheet" href="/SummaryTask4/css/add-form-style.css"/>
	
</head>
<body>
	<div id="container">
		<h3>New request form</h3>
		<a href="javascript:goBack()">Go Back</a>
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="NEW_REQUEST_SAVE">
			<table>
				<tbody>
					<tr>
						<td><label>From: </label></td>
						<td><select name="fromPlace">
								<c:forEach var="tempPlace" items="${placeList}">
									<option value="${tempPlace.id}">${tempPlace.country},
										${tempPlace.city}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label>To: </label></td>
						<td><select name="toPlace">
								<c:forEach var="tempPlace" items="${placeList}">
									<option value="${tempPlace.id}">${tempPlace.country},
										${tempPlace.city}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label>Required tonnage(t) max 25: </label></td>
						<td><input type="number" name="tonnage" min="5" max="25"
							required /></td>
					</tr>
					<tr>
						<td><label>Required capacity(m3) max 120: </label></td>
						<td><input type="number" name="capacity" min="10" max="120"
							required /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="add-button"/></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<script src="/SummaryTask4/javascript/myScript.js"></script>
</body>
</html>


