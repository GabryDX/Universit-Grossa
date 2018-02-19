<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<head>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
<script src="/corso/bootstrap/js/bootstrap.min.js"></script>

<link href="/corso/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">Università Grossa</a>

	<div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="../universita">Home <span class="sr-only">(current)</span></a>
      </li>
       <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Studenti
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="#">Lista</a>
          <a class="dropdown-item" href="./aggiungiStudente">Inserisci</a>
          <a class="dropdown-item" href="./aggiungiStudenteRandom">Inserisci random</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="http://example.com" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Corsi di laurea
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="./corsi">Lista</a>
          <a class="dropdown-item" href="./corsoform">Cerca</a>
        </div>
      </li>
    </ul>
	</div>
</nav>

<div class="container-fluid bg-3 text-center">
<a href="../universita">
	<img width="100" align=right src="https://image.spreadshirtmedia.com/image-server/v1/mp/designs/103850169,width=178,height=178/illuminatipng-long-sleeve-shirts.png">
</a>
<p class="h1"><spring:message code="label.universita"/></p>

<h1 class="badge badge-secondary">Numero studenti: ${stuList.size()}</h1>

<%@ include file="../tabella-studenti.html" %>

</div>

</body>
</html>
