<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<link href="/corso/resources/css/grafica.css" rel="stylesheet" type="text/css">
</head>
<body>
<a href="../universita">
	<img width="100" align=right src="https://image.spreadshirtmedia.com/image-server/v1/mp/designs/103850169,width=178,height=178/illuminatipng-long-sleeve-shirts.png">
</a>
<p id="uni"><spring:message code="label.universita"/></p>

<h2><spring:message code="label.corso.titolo"/><br></h2>
<form:form method="POST" action="/corso/universita/corsoquery" modelAttribute="cc">
	<table class="t01">
		<tr>
			<td style="font-size: 30px;">
				<form:label path="cdl.cdlId">Scegli il corso di laurea:</form:label>
			</td>
			<%-- <td><form:input path="cdlId"/><form:errors path="cdlId"/></td> --%>
			<td>
				<form:select path="cdl.cdlId" style="font-size: 30px;">
					<form:options items="${cc.corsi}" itemValue="cdlId" itemLabel="cdlNome"/>
				</form:select>
			</td>
		</tr>
		<tr>
			<spring:message code="label.submit" var="btnlabel"/>
			<td colspan="2"><input type="submit" value="${btnlabel}" style="height:50px;width:100%"></td>
		</tr>
	</table>
</form:form>

</body>
</html>
