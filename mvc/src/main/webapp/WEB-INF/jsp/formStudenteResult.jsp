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

<table style="font-size: 30px" class="t01">
    <tr align="left">
      <th>Matricola</th>
      <th>Cognome</th>
      <th>Nome</th>
      <!-- <th>Data di nascita</th> -->
      <th>Data d'iscrizione</th>
      <th>Sesso</th>
      <!-- <th>Luogo di nascita</th>
      <th>Link</th> -->
      <th>Corso di Laurea</th>
    </tr>
  	<tr align="left">
  	  <td>${stu.getStuMatricola()}</td>
      <td>${stu.getStuCognome()}</td>
      <td>${stu.getStuNome()}</td>
      <td><fmt:formatDate value="${stu.stuDataNascita}" pattern="dd/MM/yyyy" /></td>
      <td>${stu.getStuIscrizione()}</td>
      <td>${stu.getStuSesso()}</td>
      <td>${stu.getStuCorsoDiLaurea().getCdlNome()}</td>
      <%-- <td>${stu.getStuLuogoNascita()}</td>
      <td><a href="https://www.google.it/search?q=${stu.getStuCognome()}">${stu.getStuCognome()}</a><br></td> --%>
    </tr>
</table>

</body>
</html>
