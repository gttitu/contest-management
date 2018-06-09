<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Contest Management</title>
	</head>
	<body>
		<div>
			<table>
				<tr>
					<th>ID</th>
					<th>Firstname</th>
					<th>Lastname</th>
					<th>Gender</th>
					<th>Age</th>
				</tr>
				<c:forEach items="${candidates}" var="c">
					<tr>
						<td>${ c.candidate }</td>
						<td>${ c.firstname }</td>
						<td>${ c.lastname }</td>
						<td>${ c.gender }</td>
						<td>${ c.age }</td>
					</tr>
				</c:forEach>
			</table>
		</div><br/></br/>
		<div>
			<a href="saveCandidate.action">Save candidate</a>
		</div>
	</body>
</html>