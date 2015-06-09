
<%@ page import="com.hvalenti.freelance.inmobiliariaDC.Contrato" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'contrato.label', default: 'Contrato')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-contrato" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" controller="rendicionLocatario" action="index" id="${contratoInstance.id }">Rendicion locatario</g:link></li>
				<li><g:link class="create" controller="rendicionLocador" action="index" id="${contratoInstance.id }">Rendicion a locador</g:link></li>
				<li><g:link class="create" controller="pagoImpuesto" action="index" id="${contratoInstance.id }">Pago de impuestos</g:link></li>
			</ul>
		</div>
		<div id="show-contrato" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list contrato">
			
				<g:if test="${contratoInstance?.inicio}">
				<li class="fieldcontain">
					<span id="inicio-label" class="property-label"><g:message code="contrato.inicio.label" default="Inicio" /></span>
					
						<span class="property-value" aria-labelledby="inicio-label"><g:formatDate date="${contratoInstance?.inicio}" format="dd-MM-yyyy" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${contratoInstance?.fin}">
				<li class="fieldcontain">
					<span id="fin-label" class="property-label"><g:message code="contrato.fin.label" default="Fin" /></span>
					
						<span class="property-value" aria-labelledby="fin-label"><g:formatDate date="${contratoInstance?.fin}" format="dd-MM-yyyy" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${contratoInstance?.locador}">
				<li class="fieldcontain">
					<span id="locador-label" class="property-label"><g:message code="contrato.locador.label" default="Locador" /></span>
					
						<span class="property-value" aria-labelledby="locador-label"><g:link controller="persona" action="show" id="${contratoInstance?.locador?.id}">${contratoInstance?.locador?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${contratoInstance?.locatario}">
				<li class="fieldcontain">
					<span id="locatario-label" class="property-label"><g:message code="contrato.locatario.label" default="Locatario" /></span>
					
						<span class="property-value" aria-labelledby="locatario-label"><g:link controller="persona" action="show" id="${contratoInstance?.locatario?.id}">${contratoInstance?.locatario?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${contratoInstance?.inmueble}">
				<li class="fieldcontain">
					<span id="inmueble-label" class="property-label"><g:message code="contrato.inmueble.label" default="Inmueble" /></span>
					
						<span class="property-value" aria-labelledby="inmueble-label"><g:link controller="inmueble" action="show" id="${contratoInstance?.inmueble?.id}">${contratoInstance?.inmueble?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${contratoInstance?.liquidaciones}">
				<li class="fieldcontain">
					<span id="liquidaciones-label" class="property-label"><g:message code="contrato.liquidaciones.label" default="Liquidaciones" /></span>
					
						<g:each in="${contratoInstance.liquidaciones}" var="l">
						<span class="property-value" aria-labelledby="liquidaciones-label"><g:link controller="liquidacion" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${contratoInstance?.obligaciones}">
				<li class="fieldcontain">
					<span id="obligaciones-label" class="property-label"><g:message code="contrato.obligaciones.label" default="Obligaciones" /></span>
					
						<g:each in="${contratoInstance.obligaciones}" var="o">
						<span class="property-value" aria-labelledby="obligaciones-label"><g:link controller="obligacion" action="show" id="${o.id}">${o?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:contratoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${contratoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
