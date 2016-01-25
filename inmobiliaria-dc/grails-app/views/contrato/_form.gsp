<%@ page import="com.hvalenti.freelance.inmobiliariaDC.Contrato" %>



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

<div class="fieldcontain ${hasErrors(bean: contratoInstance, field: 'inicio', 'error')} required">
	<label for="inicio">
		<g:message code="contrato.inicio.label" default="Inicio" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="inicio" precision="day"  value="${contratoInstance?.inicio}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: contratoInstance, field: 'fin', 'error')} ">
	<label for="fin">
		<g:message code="contrato.fin.label" default="Fin" />
		
	</label>
	<g:datePicker name="fin" precision="day"  value="${contratoInstance?.fin}" default="none" noSelection="['': '']" />

</div>

<div class="fieldcontain ${hasErrors(bean: contratoInstance, field: 'inmueble', 'error')} required">
	<label for="inmueble">
		<g:message code="contrato.inmueble.label" default="Inmueble" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="inmueble" name="inmueble.id" from="${com.hvalenti.freelance.inmobiliariaDC.Inmueble.list()}" optionKey="id" required="" value="${contratoInstance?.inmueble?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: contratoInstance, field: 'liquidaciones', 'error')} ">
	<label for="liquidaciones">
		<g:message code="contrato.liquidaciones.label" default="Liquidaciones" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${contratoInstance?.liquidaciones?}" var="l">
    <li><g:link controller="liquidacion" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="liquidacion" action="create" params="['contrato.id': contratoInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'liquidacion.label', default: 'Liquidacion')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: contratoInstance, field: 'obligaciones', 'error')} ">
	<label for="obligaciones">
		<g:message code="contrato.obligaciones.label" default="Obligaciones" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${contratoInstance?.obligaciones?}" var="o">
    <li><g:link controller="obligacion" action="show" id="${o.id}">${o?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="obligacion" action="create" params="['contrato.id': contratoInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'obligacion.label', default: 'Obligacion')])}</g:link>
</li>
</ul>


</div>

