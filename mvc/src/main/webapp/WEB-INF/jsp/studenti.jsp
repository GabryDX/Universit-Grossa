<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<link href="/corso/resources/css/grafica.css" rel="stylesheet" type="text/css">
</head>
<body>
<img width="100" align=right src="https://image.spreadshirtmedia.com/image-server/v1/mp/designs/103850169,width=178,height=178/illuminatipng-long-sleeve-shirts.png">
<p style="font-size: 100px" align="center">UNIVERSITÀ GROSSA</p>

<h1>Numero studenti: ${stuList.size()}</h1>

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
  <c:forEach var="stu" items="${stuList}">
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
</table>

</body>
</html>
