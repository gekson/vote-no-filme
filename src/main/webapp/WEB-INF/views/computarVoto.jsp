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
					<div class="span2"></div>
					<div class="span8"> 																							
												
						<form:form commandName="usuario" action="computarVoto" id="reg" class="form-horizontal ng-pristine ng-valid">
						<fieldset>
						<legend>Preencha seus dados.</legend>
													
							<div id="erro" class="alert alert-error">${erro}</div>
											
							<div class="control-group"> 							
								<form:label path="nome" cssClass="control-label">Nome</form:label>
								<div class="controls">
									<form:input path="nome" cssClass="input-xlarge focused"/>
									<form:errors class="help-inline" path="nome" cssStyle="color: red;"/>
								</div>							 
							</div>
							<div class="control-group"> 
								<form:label path="email" cssClass="control-label">E-mail</form:label> 	
								<div class="controls">						
									<form:input path="email" cssClass="input-xlarge focused"/>
									<form:errors class="help-inline" path="email" cssStyle="color: red;"/>
								</div>							 
							</div> 				
							<div class="form-actions">						
								<input type="submit" value="Computar voto" class="btn btn-success"/>
							</div>
						</fieldset> 											
						</form:form>										
					</div>
					<div class="span2"></div>
				</div>
			</div>
		</div>
	</body>
</html>