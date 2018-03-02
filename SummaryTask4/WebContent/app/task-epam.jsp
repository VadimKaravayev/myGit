<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Task Epam</title>
	<link rel="stylesheet" href="">
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
	<h3>Task page</h3>
	<p>The result: ${count} route(s) found</p>
	<c:forEach var="tempRoute" items="${routeList}" varStatus="theCount">
		<c:set var="i" value="0"/>
		<p>${theCount.count}. ${tempRoute.routeStatus}, ${tempRoute.request.vehicleCapacity}</p>
	</c:forEach>	

	
</body>
</html>