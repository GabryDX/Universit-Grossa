<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<style>
body {
   background-image: url("https://www.thurrott.com/wp-content/uploads/2017/10/outlook-data.jpg");
   background-color: #cccccc;
   background-repeat: no-repeat, repeat;
   background-size: cover; /* for IE9+, Safari 4.1+, Chrome 3.0+, Firefox 3.6+ */
}
</style>
</head>
<body>
<h1 align="center">Libreria</h1>
<h2 align="center">Libro: ${book.title}</h2>
<h2 align="center">ISBN: ${book.isbn}</h2>
</body>
</html>
