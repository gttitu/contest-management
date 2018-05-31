<%@taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Contest-Management - Candidate</title>
	</head>
	<body>
		<div>
			<s:form action="saveCandidate" method="post">
				<select name="candidate.center">
					<s:iterator value="centers">
						<option value="<s:property value="id"/>"><s:property value="description"/> ( <s:property value="location"/> )</option>
					</s:iterator>
				</select>
				<input type="text" name="detail.firstname" placeholder="Firstname"/>
				<input type="text" name="detail.lastname" placeholder="Lastname"/>
				<input type="number" name="detail.age" placeholder="Age" min="0" step="1"/>
				<select name="detail.gender">
					<option value="1">Male</option>
					<option value="1">Female</option>
				</select>
				<input type="submit" value="Save">
			</s:form>
		</div><br/>
		<div>
			<table>
				<tr>
					<th>ID</th>
					<th>Firstname</th>
					<th>Lastname</th>
					<th>Gender</th>
					<th>Age</th>
				</tr>
				<s:iterator value="candidates">
					<tr>
						<td><s:property value="candidate"/></td>
						<td><s:property value="firstname"/></td>
						<td><s:property value="lastname"/></td>
						<td><s:property value="gender"/></td>
						<td><s:property value="age"/></td>
					</tr>
				</s:iterator>
			</table>
		</div>
	</body>
</html>