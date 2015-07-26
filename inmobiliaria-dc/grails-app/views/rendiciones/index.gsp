
<%@ page import="com.hvalenti.freelance.inmobiliariaDC.Vencimiento" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'instanciaObligacion.label', default: 'InstanciaObligacion')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-instanciaObligacion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" controller="contrato" action="show" id="${ id }">Ver contrato</g:link></li>
			</ul>
		</div>
		<div id="list-instanciaObligacion" class="content scaffold-list" role="main">
			<h1>${ titulo }</h1>
			<g:hasErrors bean="${liquidacion}">
				<ul class="errors" role="alert">
					<g:eachError bean="${liquidacion}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if> >
						<g:message error="${error}"/>
					</li>
					</g:eachError>
				</ul>
			</g:hasErrors>
			<g:form method="POST">
				<table>
					<thead>
						<tr>
							<th></th>
							<th><g:message code="com.hvalenti.freelance.inmobiliariaDC.DetalleLiquidacion.obligacion.label" default="Obligacion" /></th>	
							<th><g:message code="instanciaObligacion.vencimiento.label" default="Vencimiento" /></th>
							<th><g:message code="instanciaObligacion.monto.label" default="Monto" /></th>
							<th><g:message code="com.hvalenti.freelance.inmobiliariaDC.DetalleLiquidacion.observacion.label" default="Observacion" /></th>
						</tr>
					</thead>
						<tbody>
						<g:each in="${instanceList}" status="i" var="instanciaObligacionInstance">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
								<td><g:checkBox name="check-${ instanciaObligacionInstance.id }" onclick="totalizar(this)"/></td>
								<td><g:link controller="vencimiento" action="show"
									id="${instanciaObligacionInstance.id}">${ instanciaObligacionInstance }</g:link></td>
								<td><g:formatDate date="${instanciaObligacionInstance.vencimiento}" format="dd/MM/yyyy" /></td>
								<td>$ 
									<%--
									<g:field type="number" name="monto-${ instanciaObligacionInstance.id }" step="0.01"
										value="${fieldValue(bean: instanciaObligacionInstance, field: "monto")}" />
									--%>
									<g:field type="number" name="monto-${ instanciaObligacionInstance.id }" step="0.01"
										value="${ instanciaObligacionInstance.monto }" />
										
								</td>
								<td><g:textField name="observacion-${ instanciaObligacionInstance.id }" /></td>
							</tr>
						</g:each>
						<tr>
							<td colspan="3">TOTAL:</td>
							<td>$ <g:field type="number" name="total" id="total" disabled="true" value="0"/></td>
							<td></td>
						</tr>
					</tbody>
				</table>
				<fieldset class="buttons">
					<g:actionSubmit controller="${ controller }" action="generarLiquidacion" 
						class="save" value="Generar liquidacion" />
				</fieldset>
				<g:field name="id" type="hidden" value="${ id }" />
			</g:form>
		</div>
		<g:javascript src="totalizar.js" />
	</body>
</html>
