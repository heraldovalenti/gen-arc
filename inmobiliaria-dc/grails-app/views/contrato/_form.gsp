<%@ page import="com.hvalenti.freelance.inmobiliariaDC.Contrato" %>

<div class="fieldcontain ${hasErrors(bean: contratoInstance, field: 'inmueble', 'error')} required">
	<label for="inmueble">
		<g:message code="contrato.inmueble.label" default="Inmueble" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="inmueble" name="inmueble.id" from="${com.hvalenti.freelance.inmobiliariaDC.Inmueble.list()}" 
		optionKey="id" required="" value="${contratoInstance?.inmueble?.id}" class="many-to-one" noSelection="['null': '---']" />
</div>

<div class="fieldcontain ${hasErrors(bean: contratoInstance, field: 'locador', 'error')} ">
	<label for="locador">
		<g:message code="contrato.locador.label" default="Locador" />
	</label>
	<g:select id="locador" name="locador.id" from="${com.hvalenti.freelance.inmobiliariaDC.Persona.list()}" optionKey="id" value="${contratoInstance?.locador?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contratoInstance, field: 'locatario', 'error')} ">
	<label for="locatario">
		<g:message code="contrato.locatario.label" default="Locatario" />
	</label>
	<g:select id="locatario" name="locatario.id" from="${com.hvalenti.freelance.inmobiliariaDC.Persona.list()}" optionKey="id" value="${contratoInstance?.locatario?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contratoInstance, field: 'inicio', 'error')} ">
	<label for="inicio">
		<g:message code="contrato.inicio.label" default="Inicio" />
	</label>
	<g:datePicker name="inicio" value="${contratoInstance?.inicio}" precision="day" 
		years="${ 2010..2020 }" noSelection="['': '---']" />
</div>

<div class="fieldcontain ${hasErrors(bean: contratoInstance, field: 'fin', 'error')} ">
	<label for="fin">
		<g:message code="contrato.fin.label" default="Fin" />
	</label>
	<g:datePicker name="fin" value="${contratoInstance?.fin}" precision="day"
		years="${ 2010..2020 }" noSelection="['': '---']" />
</div>