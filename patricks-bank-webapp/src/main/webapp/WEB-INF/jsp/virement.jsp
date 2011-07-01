<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/virement.js"></script>
<h1>
	<spring:message code="transfer.title" />
</h1>
<c:choose>
	<c:when test="${messageErreurNbComptes != null}"><p id="messageErreurVirementNbCompte" class="red"><spring:message code="${messageErreurNbComptes}" /></p></c:when>
	<c:otherwise>
	
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/virement.js"></script>
		
		<form:form commandName="confirmationVirement">

			<label for="compteSource"><spring:message code="transfer.account.sender" />
			</label>
			<select name="compteSource" id="compteSource">
				<c:forEach var="compte" items="${listComptes}">
					<option value="${compte.idCompte}">${compte.libelle} / ${compte.numero} / ${compte.montant}&nbsp;&euro;</option>
				</c:forEach>
			</select>
		
			<label for="compteCible"><spring:message code="transfer.account.receiver" />
			</label>
			<select name="compteCible" id="compteCible">
				<c:forEach var="compte" items="${listComptes}">
					<option value="${compte.idCompte}">${compte.libelle} / ${compte.numero} / ${compte.montant}&nbsp;&euro;</option>
				</c:forEach>
			</select>
			
			<label for="montant" id="lblMontant"><spring:message code="transfer.amount" />
			</label>
			<input id="montant" name="montant" type="text" size="8" style="text-align: right;" />
		
			<c:if test="${messageDecouvert != null}"><p class="red error" id="soldeInsuffisant"><spring:message code="${messageDecouvert}" /></p></c:if>
			<input type="submit" value="<spring:message	code="transfer.submit" />" />
		</form:form>
		
	</c:otherwise>
</c:choose>