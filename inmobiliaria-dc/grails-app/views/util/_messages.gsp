<g:if test="${ flash.message }">
	<div class="message">
		${ flash.message }
	</div>
</g:if>
<g:if test="${ flash.errors }">
	<ul class="errors">
		<g:each in="${ flash.errors }" var="error">
			<li><g:message error="${error}" /></li>
		</g:each>
	</ul>
</g:if>
<g:if test="${ flash.exception }">
	<ul class="errors">
		<li>
			${ flash.exception.message }
		</li>
	</ul>
</g:if>
<g:hasErrors bean="${instance}">
	<ul class="errors" role="alert">
		<g:eachError bean="${instance}" var="error">
		<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
		</g:eachError>
	</ul>
</g:hasErrors>