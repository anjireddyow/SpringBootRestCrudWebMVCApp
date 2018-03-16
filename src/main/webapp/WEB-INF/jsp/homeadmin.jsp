<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<title>Spring Boot Rest CRUD Web MVC App</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- Access the bootstrap Css like this, 
		Spring boot will handle the resource mapping automcatically -->
	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/4.0.0-2/css/bootstrap.min.css" />
	
		<c:url value="${pageContext.request.contextPath}/css/main.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />
</head>
<body>Welcome to Admin Home Message: ${message}
</br>
<a href='<spring:url value="/signout"/>'>Logout</a>
</body>
</html>