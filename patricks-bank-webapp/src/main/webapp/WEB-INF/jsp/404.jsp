<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/classicPage.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/404Page.css" />

<title><spring:message code="webapp.name" /> - <spring:message code="404.title" />
</title>
</head>
<body>
	<div id="top"></div>
	<div id="corps_clover">
		<div id="corps">
			<h1>
				<spring:message code="404.title" />
			</h1>
			<div id="err404">
				<p>
					<spring:message code="404.message" />
					<a href="<%=request.getContextPath()%>/user/home.html"><spring:message code="404.lien" /></a>
			</div>
		</div>
	</div>
	<div id="bottom"></div>
</body>
</html>