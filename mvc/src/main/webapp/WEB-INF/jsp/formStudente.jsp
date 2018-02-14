<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<body>
<img width="100" align=right src="https://image.spreadshirtmedia.com/image-server/v1/mp/designs/103850169,width=178,height=178/illuminatipng-long-sleeve-shirts.png">
<p style="font-size: 100px" align="center">UNIVERSITÀ GROSSA</p>

<h2>Aggiungi un nuovo studente:<br></h2>
<form:form method="POST" action="/corso/universita/studentequery" modelAttribute="stucdl">
	<table>
		<%-- <tr>
			<td><form:label path="stuMatricola">Matricola</form:label></td>
			<td><form:input path="stuMatricola"/></td>
		</tr> --%>
		<tr>
			<td><form:label path="studente.stuCognome"><b>Cognome</b></form:label></td>
			<td><form:input path="studente.stuCognome"/></td>
			<td><form:errors path="studente.stuCognome"/></td>
		</tr>
		<tr>
			<td><form:label path="studente.stuNome"><b>Nome</b></form:label></td>
			<td><form:input path="studente.stuNome"/></td>
			<td><form:errors path="studente.stuNome"/></td>
		</tr>
		<%-- <tr>
			<td><form:label path="stuDataNascita">Data di nascita</form:label></td>
			<td><form:input path="stuDataNascita"/></td>
		</tr>
		<tr>
			<td><form:label path="stuLuogoNascita">Luogo di nascita</form:label></td>
			<td><form:input path="stuLuogoNascita"/></td>
		</tr> --%>
		<tr>
			<td><b>Sesso</b></td>
			<td>Maschio<form:radiobutton path="studente.stuSesso" value="M"/></td>
			<td>Femmina<form:radiobutton path="studente.stuSesso" value="F"/></td>
			<td><form:errors path="studente.stuSesso"/></td>
		</tr>
		<%-- <tr>
			<td><form:label path="stuIscrizione">Iscrizione</form:label></td>
			<td><form:input path="stuIscrizione"/></td>
		</tr>
		<tr>
			<td><form:label path="stuCorsoDiLaurea">Corso di Laurea</form:label></td>
			<td><form:input path="stuCorsoDiLaurea"/></td>
		</tr> --%>
		<tr>
			<td><b>Corso di Studi</b></td>
			<td>
				<form:select path="cdlId">
					<form:option value="-" label="--Seleziona--"/>
					<form:options items="${stucdl.cdlList}" itemValue="cdlId" itemLabel="cdlNome"/>
				</form:select>
			</td>
		</tr>
		<tr>
			<td><br><br><br></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="Invia"></td>
		</tr>
	</table>
</form:form>

</body>
</html>
