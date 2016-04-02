
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
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><a class="list" href="#" onclick="print();"><g:message code="default.print.label"/></a></li>
<%--				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>--%>
<%--				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--%>
			</ul>
		</div>

		<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
		</g:if>

		<g:render template="liquidacion" model="['liquidacion': liquidacion]" />
		
	</body>
</html>
