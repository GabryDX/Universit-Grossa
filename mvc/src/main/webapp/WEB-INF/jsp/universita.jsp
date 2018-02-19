<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
	<link href="/corso/resources/css/grafica.css" rel="stylesheet" type="text/css">
</head>
<body>
<img width="100" align=right src="https://image.spreadshirtmedia.com/image-server/v1/mp/designs/103850169,width=178,height=178/illuminatipng-long-sleeve-shirts.png">
<p id="uni"><spring:message code="label.universita"/></p>

<h1 id="st"><spring:message code="label.menti"/></h1>

<ul>
  <li><a href="/corso/universita/studenti"><spring:message code="label.studenti"/></a></li>
  <li><a href="/corso/universita/corsi"><spring:message code="label.corsi"/></a></li>
  <li><a href="/corso/universita/materie"><spring:message code="label.materie"/></a></li>
  <li><a href="/corso/universita/corsoform"><spring:message code="label.corso.cerca"/></a></li>
  <li><a href="/corso/universita/aggiungiStudente"><spring:message code="label.studente.add"/></a></li>
  <li><a href="/corso/universita/aggiungiStudenteRandom"><spring:message code="label.studente.addr"/></a></li>
  <li><a href="/corso/universita/cercaStudente"><spring:message code="label.studente.remove"/></a></li>
  <li><a href="/corso/hello"><spring:message code="label.ciao"/></a></li>
  <li><a href="/corso/book/get"><spring:message code="label.libro"/></a></li>
</ul>

</body>
</html>
