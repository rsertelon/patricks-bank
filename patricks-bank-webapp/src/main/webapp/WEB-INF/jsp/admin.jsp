<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<h1><spring:message code="webapp.workinprogress"/></h1>

<center><img src="<%= request.getContextPath() %>/images/work_in_progress.png" alt="<spring:message code="webapp.workinprogress"/>" /></center>