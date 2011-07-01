<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div id="top">
	<div id="top_bonjour">
		<p>
			<spring:message code="usernav.hello" />
			<br /> ${utilisateur.prenom} ${utilisateur.nom}
		</p>
		<a href="<%= request.getContextPath() %>/j_spring_security_logout"><spring:message code="login.logout" /> </a>
	</div>
</div>