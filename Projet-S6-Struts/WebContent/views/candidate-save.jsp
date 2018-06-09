<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Contest Management</title>
	</head>
	<body>
		<div>
			<s:form action="insertCandidate.action" method="post">
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
		</div>
	</body>
</html>