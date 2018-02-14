<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<body>
<img width="100" align=right src="https://image.spreadshirtmedia.com/image-server/v1/mp/designs/103850169,width=178,height=178/illuminatipng-long-sleeve-shirts.png">
<p style="font-size: 100px" align="center"><spring:message code="label.universita"/></p>

<h2><spring:message code="label.corso.titolo"/><br></h2>
<form:form method="POST" action="/corso/universita/corsoquery" modelAttribute="corso">
	<table>
		<tr>
			<td><form:label path="cdlId">ID</form:label></td>
			<td><form:input path="cdlId"/></td>
			<td><form:errors path="cdlId"/></td>
		</tr>
		<tr>
			<spring:message code="label.submit" var="btnlabel"/>
			<td colspan="2"><input type="submit" value="${btnlabel}"></td>
		</tr>
	</table>
</form:form>

</body>
</html>
