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
		
		<g:each in="${ grupoLiquidacion.liquidaciones }" var="liquidacion" status="i" >
		
		<g:render template="liquidacion" model="['liquidacion': liquidacion]" />
		<g:if test="${ i < ( grupoLiquidacion.liquidaciones.size() - 1 )}">
		<div class="page-break" ></div>
		</g:if>
		
		</g:each>
		
	</body>
</html>
