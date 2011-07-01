<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>
	<spring:message code="account.list.title" />
</h1>
<ul id="comptes">
	<c:forEach var="compte" items="${listComptes}">
		<li><a href="<%=request.getContextPath()%>/user/compte/${compte.idCompte}/detail.html"><strong>
					${compte.libelle} </strong> <span class="account_number">(<spring:message code="number" />
					${compte.numero})</span> <span class="soldeListCompte"> <c:choose>
						<c:when test="${ compte.montant < 0 }">
							<span class="red"> ${compte.montant} &euro;</span>
						</c:when>
						<c:otherwise>
								${compte.montant} &euro;
								</c:otherwise>
					</c:choose> </span> </a>
		</li>
	</c:forEach>
</ul>
<c:if test="${messageConfirmation != null}"><p class="error" id="messageConfirmation"><spring:message code="${messageConfirmation}" /></p></c:if>
<c:if test="${messageErrorValidationServer != null}"><p class="red error" id="messageErrorValidationServer"><spring:message code="${messageErrorValidationServer}" /></p></c:if>