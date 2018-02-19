<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<link href="/corso/resources/css/grafica.css" rel="stylesheet" type="text/css">
</head>
<body>
<a href="../universita">
	<img width="100" align=right src="https://image.spreadshirtmedia.com/image-server/v1/mp/designs/103850169,width=178,height=178/illuminatipng-long-sleeve-shirts.png">
</a>
<p id="uni"><spring:message code="label.universita"/></p>


<form:form method="POST" action="/corso/universita/deleteStudenteQuery" modelAttribute="sdf2">

<form:hidden path="delete"/>

<h1>Eliminare ${sdf2.studenti.size()} studenti?</h1>

<table style="font-size: 20px" class="t01">
    <tr align="left">
      <th>Matricola</th>
      <th>Cognome</th>
      <th>Nome</th>
      <th>Data d'iscrizione</th>
      <th>Sesso</th>
      <th>Data di nascita</th>
      <th>Luogo di nascita</th>
      <th>Corso di laurea</th>
    </tr>
  <c:forEach var="stu" items="${sdf2.studenti}">
  	<tr align="left">
  	  <td>${stu.stuMatricola}</td>
      <td><a href="https://www.google.it/search?q=${stu.getStuCognome()}+${stu.getStuNome()}" target="_blank">${stu.getStuCognome()}</a></td>
      <td>${stu.stuNome}</td>
      <td>${stu.stuIscrizione}</td>
      <td>${stu.stuSesso}</td>
      <td><fmt:formatDate value="${stu.stuDataNascita}" pattern="dd/MM/yyyy" /></td>
      <td>${stu.stuLuogoNascita}</td>
      <td>${stu.stuCorsoDiLaurea.getCdlNome()}<br></td>
      
    </tr>
	</c:forEach>
	<tr>
		<spring:message code="label.submit" var="btnlabel"/>
		<td colspan="9"><input type="submit" value="${btnlabel}" style="height:50px;width:100%"></td>		
	</tr>
</table>

</form:form>

</body>
</html>
