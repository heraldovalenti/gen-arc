<%@ page import="com.hvalenti.freelance.inmobiliariaDC.GrupoLiquidacion" %>
<%@ page import="com.hvalenti.freelance.inmobiliariaDC.Liquidacion" %>

<div id="show-instanciaObligacion" class="content scaffold-show liquidacion" role="main">
	<h1>
		<g:message code="com.hvalenti.freelance.inmobiliariaDC.Liquidacion.responsable.label" default="Liquidación de" />
		 <g:fieldValue bean="${liquidacionInstance}" field="responsable"/>
	</h1>
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
		
		<g:if test="${liquidacionInstance?.contrato}">
		<li class="fieldcontain">
			<span id="contrato-label" class="property-label">
				<g:message code="com.hvalenti.freelance.inmobiliariaDC.Liquidacion.contrato.label" default="Contrato" />
			</span>
			<span class="property-value" aria-labelledby="id-label">
				<g:fieldValue bean="${liquidacionInstance}" field="contrato"/>
			</span>
		</li>
		</g:if>
		
						
		<g:if test="${liquidacionInstance?.contrato?.inmueble}">
		<li class="fieldcontain">
			<span id="inmueble-label" class="property-label">
				<g:message code="com.hvalenti.freelance.inmobiliariaDC.Liquidacion.inmueble.label" default="Inmueble" />
			</span>
			<span class="property-value" aria-labelledby="id-label">
				<g:fieldValue bean="${liquidacionInstance}" field="contrato.inmueble"/>
			</span>
		</li>
		</g:if>
		
		<g:if test="${liquidacionInstance?.contrato?.locador}">
		<li class="fieldcontain">
			<span id="locador-label" class="property-label">
				<g:message code="com.hvalenti.freelance.inmobiliariaDC.Liquidacion.locador.label" default="Locador" />
			</span>
			<span class="property-value" aria-labelledby="id-label">
				<g:fieldValue bean="${liquidacionInstance}" field="contrato.locador"/>
			</span>
		</li>
		</g:if>
		
		<g:if test="${liquidacionInstance?.contrato?.locatario}">
		<li class="fieldcontain">
			<span id="locatario-label" class="property-label">
				<g:message code="com.hvalenti.freelance.inmobiliariaDC.Liquidacion.locatario.label" default="Locatario" />
			</span>
			<span class="property-value" aria-labelledby="id-label">
				<g:fieldValue bean="${liquidacionInstance}" field="contrato.locatario"/>
			</span>
		</li>
		</g:if>
	
		<g:if test="${liquidacionInstance?.fecha}">
		<li class="fieldcontain">
			<span id="fecha-label" class="property-label">
				<g:message code="liquidacionInstance.fecha.label" default="Fecha" />
			</span>
			<span class="property-value" aria-labelledby="fecha-label">
				<g:formatDate date="${ liquidacionInstance?.fecha }" formatName="default.date.format" />
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
					<td>${ instanciaDetalle.vencimiento.responsableObligacion.obligacion }</td>
					<td><g:formatDate date="${instanciaDetalle.vencimiento.vencimiento}" formatName="default.date.format" /></td>
					<td>$ ${ instanciaDetalle.monto }</td>
					<td>${ instanciaDetalle.observacion }</td>
				</tr>
			</g:each>
		</tbody>
	</table>
	
</div>