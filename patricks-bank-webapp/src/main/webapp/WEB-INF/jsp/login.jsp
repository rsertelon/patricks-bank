<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<form action="j_spring_security_check" method="post" id="form_login">
	<fieldset>
		<legend>
			<spring:message code="login.title" />
		</legend>
		<label for="j_username"><spring:message code="login.label.login" /><span class="mandatory">*</span> </label> <input
			type="text" name="j_username" id="j_username"/> <label for="j_password"><spring:message
				code="login.label.password" /><span class="mandatory">*</span> </label> <input type="password" name="j_password"
			id="j_password"/>

		<c:if test="${empty sessionScope['SPRING_SECURITY_CONTEXT'] and !empty sessionScope['SPRING_SECURITY_LAST_USERNAME']}">
			<p class="errorMessage">
				<spring:message code="login.error.credentials" />
			</p>
		</c:if>

		<input type="submit" value="<spring:message code="login.button.connection" />" />
	</fieldset>
</form>