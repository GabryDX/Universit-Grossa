<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<style>
table {
    width:100%;
}
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: left;
}
table#t01 tr:nth-child(even) {
    background-color: #eee;
}
table#t01 tr:nth-child(odd) {
   background-color:#fff;
}
table#t01 th {
    background-color: black;
    color: white;
}

a {
	text-decoration : none;
	color : #000;
}

</style>
</head>
<body>
<img width="100" align=right src="https://image.spreadshirtmedia.com/image-server/v1/mp/designs/103850169,width=178,height=178/illuminatipng-long-sleeve-shirts.png">
<p style="font-size: 100px" align="center">UNIVERSITÀ GROSSA</p>


<form:form method="POST" action="/corso/universita/deleteStudenteQuery" modelAttribute="sdf2">

<form:hidden path="delete"/>

<h1>Eliminare ${sdf2.studenti.size()} studenti?</h1>

<table style="font-size: 20px" id="t01">
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
      <td><a href="https://www.google.it/search?q=${stu.getStuCognome()}+${stu.getStuNome()}">${stu.getStuCognome()}</a></td>
      <td>${stu.stuNome}</td>
      <td>${stu.stuIscrizione}</td>
      <td>${stu.stuSesso}</td>
      <td>${stu.stuDataNascita}</td>
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
