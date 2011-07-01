<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/classicPage.css" />
<link rel="icon" href="<%=request.getContextPath()%>/images/favicon.ico"
	type="image/x-icon" />
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/images/favicon.ico"
	type="image/x-icon" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/<spring:message code="lang"/>.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.5.1.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/functions.js"></script>

<tiles:useAttribute id="key" name="title" />
<title><spring:message code="webapp.name" /> - <spring:message
		code="${key}" />
</title>
</head>
<body>
	<tiles:insertAttribute name="header" />


	<div id="menu">
		<div id="navBarre">
			<ul>
				<li<c:if test="${here == 'comptes'}"> class="here"</c:if>>
					<a href="<%=request.getContextPath()%>/user/home.html"
						title="Liste des comptes"><spring:message code="menu.accounts" /></a>
				</li>
				<li<c:if test="${here == 'virement'}"> class="here"</c:if>>
					<a	href="<%=request.getContextPath()%>/user/virement/virement.html"
						title="Faire un virement"><spring:message code="menu.transfer" /></a>
				</li>
			</ul>
		</div>

		<div id="drapeaux">
			<a href="?locale=fr"><img
				src="<%=request.getContextPath()%>/images/DrapeauFr.png" alt="fr" />
			</a> <a href="?locale=en"><img
				src="<%=request.getContextPath()%>/images/DrapeauEn.png" alt="en" />
			</a>
		</div>
	</div>

	<div id="corps_clover">
		<div id="corps">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<tiles:insertAttribute name="footer" />

</body>
</html>