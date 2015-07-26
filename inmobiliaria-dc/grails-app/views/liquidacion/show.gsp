
<%@ page import="com.hvalenti.freelance.inmobiliariaDC.Liquidacion" %>
<%@ page import="com.hvalenti.freelance.inmobiliariaDC.Vencimiento" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'liquidacion.label', default: 'Liquidacion')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-instanciaObligacion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><a class="list" href="#" onclick="print();"><g:message code="default.print.label"/></a></li>
<%--				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>--%>
<%--				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--%>
			</ul>
		</div>
		<div id="show-instanciaObligacion" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list liquidacion">
			
				<g:if test="${liquidacionInstance?.id}">
				<li class="fieldcontain">
					<span id="id-label" class="property-label">
						<g:message code="com.hvalenti.freelance.inmobiliariaDC.Liquidacion.id.label" default="Id" />
					</span>
					<span class="property-value" aria-labelledby="id-label">
						<g:fieldValue bean="${liquidacionInstance}" field="id"/>
					</span>
				</li>
				</g:if>
			
				<g:if test="${liquidacionInstance?.concepto}">
				<li class="fieldcontain">
					<span id="concepto-label" class="property-label">
						<g:message code="liquidacion.concepto.label" default="Concepto" />
					</span>
					<span class="property-value" aria-labelledby="concepto-label">
						<g:fieldValue bean="${liquidacionInstance}" field="concepto"/>
					</span>
				</li>
				</g:if>
				
				<g:if test="${liquidacionInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label">
						<g:message code="liquidacionInstance.fecha.label" default="Fecha" />
					</span>
					<span class="property-value" aria-labelledby="fecha-label">
						<g:formatDate date="${ liquidacionInstance?.fecha }" format="dd-MM-yyyy" />
					</span>
				</li>
				</g:if>
				
				<g:if test="${liquidacionInstance?.total}">
				<li class="fieldcontain">
					<span id="total-label" class="property-label">
						<g:message code="liquidacionInstance.total.label" default="Total" />
					</span>
					<span class="property-value" aria-labelledby="total-label">
						$ <g:fieldValue bean="${liquidacionInstance}" field="total"/>
					</span>
				</li>
				</g:if>
			
			</ol>
			
			<table>
				<thead>
					<tr>
						<th><g:message code="com.hvalenti.freelance.inmobiliariaDC.DetalleLiquidacion.obligacion.label" default="Obligacion" /></th>	
						<th><g:message code="com.hvalenti.freelance.inmobiliariaDC.InstanciaObligacion.vencimiento.label" default="Vencimiento" /></th>
						<th><g:message code="com.hvalenti.freelance.inmobiliariaDC.DetalleLiquidacion.monto.label" default="Monto" /></th>
						<th><g:message code="com.hvalenti.freelance.inmobiliariaDC.DetalleLiquidacion.observacion.label" default="Observacion" /></th>
					</tr>
				</thead>
					<tbody>
					<g:each in="${liquidacionInstance.detalles}" status="i" var="instanciaDetalle">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							<td>${ instanciaDetalle.obligacion }</td>
							<td><g:formatDate date="${instanciaDetalle.obligacion.vencimiento}" format="dd/MM/yyyy" /></td>
							<td>$ ${ instanciaDetalle.monto }</td>
							<td>${ instanciaDetalle.observacion }</td>
						</tr>
					</g:each>
				</tbody>
			</table>
			
		</div>
	</body>
</html>
