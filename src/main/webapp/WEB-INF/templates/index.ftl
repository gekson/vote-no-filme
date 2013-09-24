<#import "spring.ftl" as spring />
<html>

	<head>
		<title>Vote No Filme</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="./resources/css/screen.css"/>
	</head>

	<body>
		<div id="container">
			<div class="dualbrand">
				<img src="./resources/gfx/dualbrand_logo.png"/>
			</div>
			<div id="content">
				<h1>Vote no seu Filme preferido</h1>

				<div>
					<p>Escolha o seu preferido dentre os 2 abaixo.</p>					
				</div>
				
				<h2>Filmes</h2>
				
				<form action="votar" method="POST">
				
				<table class="simpletablestyle">
					<thead>
						<tr>
							<th> </th>
							<th>Nome</th>
							<th>Ano</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><@spring.formRadioButtons "voto.id", cityMap, "" /></td>
							<td>${filme1.nome}</td>
							<td>${filme1.ano}</td>	
						</tr>	
						<tr>
							<td><input type="radio" name="filme" value="${filme2}" /></td>
							<td>${filme2.nome}</td>
							<td>${filme2.ano}</td>	
						</tr>									
					</tbody>
				</table>
				<input type="submit" value="Votar"/>
				</form>		
			</div>
			<div id="aside">
				
			</div>
			<div id="footer">
			    <p>
					This project was generated from a Maven archetype from
			        	JBoss.<br />
			    </p>
			</div>
		</div>
	</body>
</html>
