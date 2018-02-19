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

<table style="font-size: 30px" class="t01">
    <tr align="left">
      <th>Matricola</th>
      <th>Cognome</th>
      <th>Nome</th>
      
    </tr>
  	<tr align="left">
  	  <td>${stu.stuMatricola}</td>
      <td><a href="https://www.google.it/search?q=${stu.stuCognome}+${stu.stuNome}" target="_blank">${stu.stuCognome}</a></td>
      <td>${stu.stuNome}</td>
  	</tr>
  	<tr align="left">
  	  <th>Iscrizione</th>
      <th>Sesso</th>
      <th>Data di nascita</th>
    </tr>
    <tr align="left">
      <td>${stu.stuIscrizione}</td>
      <td>${stu.stuSesso}</td>
      <td><fmt:formatDate value="${stu.stuDataNascita}" pattern="dd/MM/yyyy" /></td>
  	</tr>
  	<tr align="left">
      <th>Luogo di nascita</th>
      <th>Codice fiscale</th>
      <th>Corso di laurea</th>
    </tr>
    <tr align="left">
      <td>${stu.stuLuogoNascita}</td>
      <td>${stu.stuCodiceFiscale}</td>
      <td>${stu.stuCorsoDiLaurea.getCdlNome()}<br></td>  
  	</tr>
</table>

</body>
</html>
