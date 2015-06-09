<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="default.access.label" default="Acceso" /></title>
	</head>
	<body>
		<div id="create-contrato" class="content scaffold-create" role="main">
			<h1><g:message code="acceso.title.label" default="Ingreso al sistema" /></h1>
			
			<g:if test="${flash.message}">
			<ul class="errors" role="alert">
				<li>${flash.message}</li>
			</ul>
			</g:if>
			
			<g:hasErrors bean="${contratoInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${contratoInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			
			<g:form method="POST" action="login" >
				<fieldset class="form">
				
					<div class="fieldcontain required">
						<label for="user">
							<g:message code="acceso.user.label" default="Usuario" />
						</label>
						<g:field name="user" type="text" />
					</div>
					
					<div class="fieldcontain required">
						<label for="password">
							<g:message code="acceso.password.label" default="Password" />
						</label>
						<g:field name="password" type="password"/>
					</div>
					
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="submit" class="submit" value="${message(code: 'acceso.button.submit.label', default: 'Ingresar')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
