<html>
<body>
	<img
		src="https://images.wired.it/wp-content/uploads/2016/04/1460976679_ciaone.png">
	<p style="font-size: 200px">
		<font color=red>SCRITTA ENORME</font>
	</p>
	<h2>Non dovresti essere qui... pt2</h2>
	<img
		src="https://morgatta.files.wordpress.com/2015/06/schermata-2015-06-06-a-14-03-09.png">
	<img
		src="https://ilgargantua.files.wordpress.com/2016/06/good.jpg?w=900&h=900&crop=1">
	<img src="http://i.imgur.com/HKi5noF.jpg">
	<br>
	<button onclick="clickCounter()" type="button"
		style="height: 500px; width: 1000px">
		<p style="font-size: 100px">Cliccami!</p>
	</button>
	<div id="result"></div>
	<p>Clicca il bottone per vedere il counter incrementare.</p>

	<script>
		function clickCounter() {
			if (typeof (Storage) !== "undefined") {
				if (localStorage.clickcount) {
					localStorage.clickcount = Number(localStorage.clickcount) + 1;
				} else {
					localStorage.clickcount = 1;
				}
				document.getElementById("result").innerHTML = "Hai cliccato il bottone "
						+ localStorage.clickcount + " volte.";
			} else {
				document.getElementById("result").innerHTML = "Spiacente, il tuo browser non supporta web storage...";
			}
		}
	</script>

</body>
</html>
