<%@ page import="com.hvalenti.freelance.inmobiliariaDC.GrupoLiquidacion" %>
<%@ page import="com.hvalenti.freelance.inmobiliariaDC.Liquidacion" %>

<div class="content scaffold-show liquidacion" role="main">
	<h1>Liquidación de ${ liquidacion.responsable }</h1>
	<ol class="property-list">
	
		<li class="fieldcontain">
			<span id="id-label" class="property-label">
				Nro Contrato
			</span>
			<span class="property-value" aria-labelledby="id-label">
				${ liquidacion.contrato.id }
			</span>
		</li>
		
		<li class="fieldcontain">
			<span id="id-label" class="property-label">
				Locador
			</span>
			<span class="property-value" aria-labelledby="id-label">
				${ liquidacion.contrato.locador }
			</span>
		</li>
		
		<li class="fieldcontain">
			<span id="id-label" class="property-label">
				Locatario
			</span>
			<span class="property-value" aria-labelledby="id-label">
				${ liquidacion.contrato.locatario }
			</span>
		</li>
	
		<li class="fieldcontain">
			<span id="id-label" class="property-label">
				Inmueble
			</span>
			<span class="property-value" aria-labelledby="id-label">
				${ liquidacion.contrato.inmueble }
			</span>
		</li>
		
	</ol>
	
	<table>
		<thead>
			<tr>
				<th><g:message code="com.hvalenti.freelance.inmobiliariaDC.DetalleLiquidacion.obligacion.label" default="Obligacion" /></th>	
				<th><g:message code="com.hvalenti.freelance.inmobiliariaDC.InstanciaObligacion.vencimiento.label" default="Vencimiento" /></th>
				<th><g:message code="com.hvalenti.freelance.inmobiliariaDC.DetalleLiquidacion.monto.label" default="Monto" /></th>
			</tr>
		</thead>
		<tbody>
		
			<g:each in="${ liquidacion.detalles }" var="detalle">
			<tr>
				<td>${ detalle.vencimiento.responsableObligacion.obligacion.concepto }</td>
				<td><g:formatDate date="${ detalle.vencimiento.vencimiento }" formatName="default.date.format" /></td>
				<td>$ ${ detalle.vencimiento.monto }</td>
			</tr>
			</g:each>
			
		</tbody>
	</table>
	
</div>