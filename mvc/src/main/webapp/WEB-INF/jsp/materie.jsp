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
</style>
</head>
<body>
<img width="100" align=right src="https://image.spreadshirtmedia.com/image-server/v1/mp/designs/103850169,width=178,height=178/illuminatipng-long-sleeve-shirts.png">
<p style="font-size: 100px" align="center">UNIVERSITÀ GROSSA</p>

<h1>Numero materie: ${materie.size()}</h1>

<table style="font-size: 30px" id="t01">
    <tr align="left">
      <th>ID</th>
      <th>Nome</th>
      <th>Crediti</th>
      <th>Anno Accademico</th>
      <th>Corso di Laurea</th>
    </tr>
  <c:forEach var="mat" items="${materie}">
  	<tr align="left">
      <td>${mat.id.matId}</td>
      <td>${mat.matNome}</td>
      <td>${mat.matCrediti}</td>
      <td>${mat.id.matAnnoAccademico}</td>
      <td>${mat.matCorsoDiLaurea.cdlNome}<br></td>
    </tr>
	</c:forEach>
</table>

</body>
</html>
