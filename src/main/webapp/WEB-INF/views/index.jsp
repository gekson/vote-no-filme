<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

	<head>
		<title>Vote No Filme</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="./resources/css/voto.css"/>
	</head>

	<body>
		<div class="container"> 
			<div class="row"> 
				<div class="span12">
				<div class="span1"></div>
				<div class="span10"> 					
						<h1>Enquete de filmes</h1>
						
						<form:form commandName="voto" cssClass="form-horizontal">
						<fieldset> 
						<legend>Qual seu Filme preferido?</legend> 
						<div class="alert alert-error"><form:errors path="id" /></div>																							
							<table class="table table-striped">							
								<tr>
									<th> </th>
									<th>Nome</th>
									<th>Ano</th>
								</tr>					
								<tr>
									<td><form:radiobutton path="id" value="${filme1.id}"/></td>
									<td>${filme1.nome}</td>
									<td>${filme1.ano}</td>	
								</tr>	
								<tr>
									<td><form:radiobutton path="id" value="${filme2.id}"/></td>
									<td>${filme2.nome}</td>
									<td>${filme2.ano}</td>	
								</tr>														
							</table>
							<div class="form-actions"> 
								<input type="submit" value="Votar" class="btn btn-success"/>
							 </div>
							 	</fieldset> 
						</form:form>					
					</div>
					<div class="span1"></div>
				</div> 
			</div> 
		</div>										
	</body>
</html>
