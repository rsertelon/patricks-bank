<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/loginPage.css" />
<link rel="icon"
	href="<%=request.getContextPath()%>/images/favicon.ico"
	type="image/x-icon" />
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/images/favicon.ico"
	type="image/x-icon" />
<tiles:useAttribute id="key" name="title" />
<title><spring:message code="webapp.name" /> - <spring:message
		code="${key}" />
</title>
</head>
<body>

	<tiles:insertAttribute name="header" />

	<div id="drapeaux">
		<a href="?locale=fr"><img src="<%=request.getContextPath()%>/images/DrapeauFr.png" alt="fr" /></a>
		<a href="?locale=en"><img src="<%=request.getContextPath()%>/images/DrapeauEn.png" alt="en" /></a>
	</div>
	<div id="corps_login_clover">
		<div id="corps_login">
			<tiles:insertAttribute name="body" />
		</div>
	</div>

	<tiles:insertAttribute name="footer" />

</body>
</html>