<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<h1>
	<spring:message code="account.detail.title" />
	<strong> ${compte.libelle} </strong> <span class="account_number">(<spring:message
			code="number" />${compte.numero})</span>
</h1>

<form id="choixMois" method="post" action="">
	<label for="annees"><spring:message
			code="account.detail.filter" /> </label> <select id="annees" name="annee">
		<c:forEach var="monAnnee" items="${listeAnnees}">
			<option value="${monAnnee}"
				<c:if test="${monAnnee==annee}"> selected="selected"</c:if>>${monAnnee}</option>
		</c:forEach>
	</select> <select id="mois" name="mois">
		<c:forEach var="monMois" items="${listeMois}">
			<option value="${monMois.key}"
				<c:if test="${monMois.key==mois}"> selected="selected"</c:if>>
				<spring:message code="${monMois.value}" />
			</option>
		</c:forEach>
	</select> <input type="submit" value="Afficher" />
</form>

<table id="operation">
	<thead>
		<tr>
			<th class="thDate"><spring:message code="account.date" /></th>
			<th class="thType"><spring:message code="account.type" /></th>
			<th class="thDetail"><spring:message code="account.detail" /></th>
			<th class="thCreditDebit"><spring:message code="account.debit" /></th>
			<th class="thCreditDebit"><spring:message code="account.credit" /></th>
		</tr>
	</thead>

	<c:set var="tdClass" value="case1" />
	<tbody>
		<c:forEach var="operation" items="${listOperations}">
			<c:choose>
				<c:when test="${pageScope.tdClass == 'case1'}">
					<c:set var="tdClass" value="case2" />
				</c:when>
				<c:when test="${pageScope.tdClass == 'case2'}">
					<c:set var="tdClass" value="case1" />
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${ operation.montant < 0 }">
					<c:set var="debit" value="${operation.montant} &euro;" />
					<c:set var="credit" value="" />
				</c:when>
				<c:when test="${ operation.montant >= 0 }">
					<c:set var="credit" value="${operation.montant} &euro;" />
					<c:set var="debit" value="" />
				</c:when>
			</c:choose>
	
			<tr class="${tdClass}">
				<td>${operation.dateFormatee}</td>
				<td>${operation.typeOperation}</td>
				<td>${operation.libelle}</td>
				<td class="solde red">${debit}</td>
				<td class="solde">${credit}</td>
			</tr>
		</c:forEach>
		
		<tr></tr>
		<tr class="formatCase">
			<td colspan="3" class="lblTotal"><a href="#"
				id="deroulerOperationCarte"><spring:message
						code="account.totalCard" /> </a>
			</td>
			<td class="solde red total" colspan="2"><c:out
					value="${totalOperationsCarte}" /> &euro;</td>
		</tr>
	</tbody>

	<!-- 	########################### -->
	<!-- 	DETAIL DES OPERATIONS CARTE -->
	<!-- 	########################### -->
	<tfoot id="detailOperationsCarte">
		<c:set var="tdClass" value="case1" />
		<c:forEach var="operationCarte" items="${listOperationsCarte}">
			<c:choose>
				<c:when test="${pageScope.tdClass == 'case1'}">
					<c:set var="tdClass" value="case2" />
				</c:when>
				<c:when test="${pageScope.tdClass == 'case2'}">
					<c:set var="tdClass" value="case1" />
				</c:when>
			</c:choose>
	
			<tr class="${tdClass}">				
				<td>${operationCarte.dateFormatee}</td>
				<td>${operationCarte.typeOperation}</td>
				<td>${operationCarte.libelle}</td>
				<td class="solde red total" colspan="2">${operationCarte.montant} &euro;</td>
			</tr>
		</c:forEach>
	</tfoot>
</table>

<a id="back_link" href="<%=request.getContextPath()%>/user/home.html"><spring:message
		code="account.detail.back" /> </a>