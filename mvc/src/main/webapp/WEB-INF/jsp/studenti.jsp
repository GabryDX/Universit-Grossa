<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<link href="/corso/resources/css/grafica.css" rel="stylesheet" type="text/css">
</head>
<body>
<img width="100" align=right src="https://image.spreadshirtmedia.com/image-server/v1/mp/designs/103850169,width=178,height=178/illuminatipng-long-sleeve-shirts.png">
<p id="uni">UNIVERSITÀ GROSSA</p>

<h1>Numero studenti: ${stuList.size()}</h1>

<table style="font-size: 20px" class="t01">
    <tr align="left">
      <th>Matricola</th>
      <th>Cognome</th>
      <th>Nome</th>
      <th>Iscrizione</th>
      <th>Sesso</th>
      <th>Data di nascita</th>
      <th>Luogo di nascita</th>
      <th>Codice fiscale</th>
      <th>Corso di laurea</th>
    </tr>
  <c:forEach var="stu" items="${stuList}">
  	<tr align="left">
  	  <td>${stu.stuMatricola}</td>
      <td><a href="https://www.google.it/search?q=${stu.stuCognome}+${stu.stuNome}">${stu.stuCognome}</a></td>
      <td>${stu.stuNome}</td>
      <td>${stu.stuIscrizione}</td>
      <td>${stu.stuSesso}</td>
      <td><fmt:formatDate value="${stu.stuDataNascita}" pattern="dd/MM/yyyy" /></td>
      <td>${stu.stuLuogoNascita}</td>
      <td>${stu.stuCodiceFiscale}</td>
      <td>${stu.stuCorsoDiLaurea.getCdlNome()}<br></td>
      
    </tr>
	</c:forEach>
</table>

</body>
</html>
