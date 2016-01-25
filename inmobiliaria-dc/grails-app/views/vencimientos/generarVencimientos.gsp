<%@ page import="com.hvalenti.freelance.inmobiliariaDC.Contrato" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Generar vencimientos</title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="generarVencimientos">Generar todos</g:link></li>
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
				<g:each in="${contratoInstanceList}" status="i" var="contratoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link controller="contrato" action="show" id="${contratoInstance.id}">${contratoInstance}</g:link></td>
					
						<td>${fieldValue(bean: contratoInstance, field: "inmueble")}</td>
					
						<td><g:link action="generarVencimientosContrato" id="${contratoInstance.id}">Generar</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
		</div>
	</body>
</html>
