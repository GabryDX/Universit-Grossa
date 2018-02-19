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
<!--  <h2>Corso di Laurea: ${corsoDiLaurea.get(0).cdlNome}</h2>
<h2>ID: ${corsoDiLaurea.get(0).cdlId}</h2> -->

<h1>Numero corsi: ${corsi.size()}</h1>

<table style="font-size: 30px" class="t01">
    <tr align="left">
      <th>ID</th>
      <th>Nome</th>
      <th>Durata</th>
    </tr>
  <c:forEach var="corso" items="${corsi}">
  	<tr align="left">
      <td>${corso.cdlId}</td>
      <td>${corso.cdlNome}</td>
      <td>${corso.cdlDurata}<br></td>
    </tr>
	</c:forEach>
</table>

</body>
</html>
