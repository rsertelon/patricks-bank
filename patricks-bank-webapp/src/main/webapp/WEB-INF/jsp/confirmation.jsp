<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>
	<spring:message code="confirmation.title" />
</h1>

<div class="confirmation">
	<p>
		<spring:message code="confirmation.text1" />
		<br /> <br />
		<spring:message code="confirmation.text2" />
		<strong>${compteSource.libelle}</strong> <span class="account_number">(<spring:message code="number" />${compteSource.numero})</span>
		${compteSource.montant} &euro;<br />
		<spring:message code="confirmation.text5" />
		${montantSourceApres} &euro;<br /> <br />
		<spring:message code="confirmation.text3" />
		<strong>${compteCible.libelle}</strong> <span class="account_number">(<spring:message code="number" />${compteCible.numero})</span>
		${compteCible.montant}&nbsp;&euro;<br />
		<spring:message code="confirmation.text5" />
		${montantCibleApres} &euro;<br /> <br />
		<spring:message code="confirmation.text4" />
		<strong>${montant}&nbsp;&euro;</strong>
	</p>
</div>

<form action="./confirme.html" method="POST" id="confirmer">
	<input name="compteSource" type="hidden" value="${compteSource.idCompte}" /> <input name="compteCible" type="hidden" value="${compteCible.idCompte}" />
	<input name="montant" type="hidden" value="${montant}" /> <input type="submit" value="Confirmation" />
</form>
<form action="./virement.html" method="GET" id="annuler">
	<input type="submit" value="Annulation" />
</form>



<%-- <spring:message	code="confirmation.submit" /> --%>