<%@ page import="com.hvalenti.freelance.inmobiliariaDC.Contrato" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Generar liquidaciones</title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li>
					Generar liquidacion para:
					<g:link action="generarLiquidaciones"
						params="[responsable: 'inmobiliaria']">Inmobiliaria</g:link>
					<g:link action="generarLiquidaciones" 
						params="[responsable: 'locador']">Locatario</g:link>
					<g:link action="generarLiquidaciones" 
						params="[responsable: 'locatario']">Locador</g:link>
				</li>
			</ul>
		</div>
		<div class="content scaffold-list">
			<h1>Contratos con vencimientos pendientes de generar</h1>
			
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<table>
				<thead>
					<tr>
						<th><g:message code="contrato.id.label" default="Nro contrato" /></th>
					
						<th><g:message code="contrato.inmueble.label" default="Inmueble" /></th>
					
						<th></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${contratoInstanceList}" status="i" var="contrato">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link controller="contrato" action="show" id="${contrato.id}">${contrato}</g:link></td>
					
						<td>${fieldValue(bean: contrato, field: "inmueble")}</td>
					
						<td>
							Generar liquidacion para:
							<g:link action="generarLiquidacionesParaContrato"
								params="[idContrato: contrato.id, responsable: 'inmobiliaria']">Inmobiliaria</g:link>
							<g:link action="generarLiquidacionesParaContrato" 
								params="[idContrato: contrato.id, responsable: 'locador']">Locatario</g:link>
							<g:link action="generarLiquidacionesParaContrato" 
								params="[idContrato: contrato.id, responsable: 'locatario']">Locador</g:link>
						</td>
						
					</tr>
				</g:each>
				</tbody>
			</table>
		</div>
	</body>
</html>
