<%@ page import="com.hvalenti.freelance.inmobiliariaDC.GrupoLiquidacion" %>
<%@ page import="com.hvalenti.freelance.inmobiliariaDC.Liquidacion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'grupoLiquidacion.label', default: 'GrupoLiquidacion')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><a class="list" href="#" onclick="print();"><g:message code="default.print.label"/></a></li>
			</ul>
		</div>
		<div class="content scaffold-show" role="main">
			<h1>Liquidacion de Locatario</h1>
			<ol class="property-list">
			
				<li class="fieldcontain">
					<span id="id-label" class="property-label">
						Contrato
					</span>
					<span class="property-value" aria-labelledby="id-label">
						Congolani - Valenti / Bv San Juan 19 TIII 12 A
					</span>
				</li>
				
				<li class="fieldcontain">
					<span id="id-label" class="">
						Contrato
					</span>
					<span class="" aria-labelledby="id-label">
						Congolani - Valenti / Bv San Juan 19 TIII 12 A
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
					<tr>
						<td>Municipalidad</td>
						<td>01/01/2016</td>
						<td>$ 433.33</td>
					</tr>
					<tr>
						<td>Alquiler</td>
						<td>01/01/2016</td>
						<td>$ 3300.00</td>
					</tr>
					<tr>
						<td>Rentas</td>
						<td>01/01/2016</td>
						<td>$ 123.99</td>
					</tr>
					<tr>
						<td></td>
						<td>Total</td>
						<td>$ 9999.99</td>
					</tr>
				</tbody>
			</table>
			
		</div>
	</body>
</html>
