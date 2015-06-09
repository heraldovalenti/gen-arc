<%@ page import="com.hvalenti.freelance.inmobiliariaDC.InstanciaObligacion" %>

<div class="fieldcontain ${hasErrors(bean: alquilerObligacionInstance, field: 'montoEstandar', 'error')} required">
	<label for="montoAlquiler">
		<g:message code="com.hvalenti.freelance.inmobiliariaDC.Contrato.montoAlquiler" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="montoAlquiler" value="${ formatNumber(number: alquilerObligacionInstance?.montoEstandar, format: '######') }" 
		required=""	type="number" min="0" placeholder="\$" />
</div>

<div class="fieldcontain ${hasErrors(bean: comisionObligacionInstance, field: 'montoEstandar', 'error')} required">
	<label for="comision">
		<g:message code="com.hvalenti.freelance.inmobiliariaDC.Contrato.comision" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="comision" value="${ formatNumber(number: comisionObligacionInstance?.montoEstandar, format: '######') }" 
		required=""	type="number" min="0" placeholder="\$" />
</div>