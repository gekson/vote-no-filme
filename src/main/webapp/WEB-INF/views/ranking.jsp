<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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

					<div class="alert alert-success">
						Votos realizados com sucesso.					
					</div>								
																	
					<c:choose>
						<c:when test="${ranking.size()==0}">
							
						</c:when>
						<c:otherwise>
						<h2>Ranking</h2>
						<h6>Em caso de empate, o filme mais antigo fica melhor rankeado.</h6>
							<table class="table table-striped">
								<thead>
									<tr>									
										<th>Nome</th>
										<th>Ano</th>
										<th>Votos</th>									
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${ranking}" var="filme">
										<tr>										
											<td>${filme.nome}</td>
											<td>${filme.ano}</td>
											<td>${filme.voto}</td>		
										</tr>								
									</c:forEach>
								</tbody>
							</table>						
						</c:otherwise>
					</c:choose>	
					
					<a href="<c:url value="/"/>">Nova pesquisa</a>
					
				</div>	
				<div class="span1"></div>
			</div>
		</div>
		</div>
	</body>
</html>