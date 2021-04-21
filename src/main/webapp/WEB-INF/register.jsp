<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="js/app.js"></script>
<link rel="stylesheet" href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>

<meta charset="UTF-8">
<title>Registration Page</title>
</head>
<body>	
	<div class = "container">
		<div class="row">
			<div class="col">
			<h1>Register!</h1>
    		<form:form class="form-group" method="POST" action="/registration" modelAttribute="user">
    		<p>
            	<form:label path="firstName">First Name:</form:label>
            	<form:input type="text" path="firstName"/>
            	<form:errors path="firstName" class="text-danger"/>
        	</p>
        	<p>
            	<form:label path="lastName">Last Name:</form:label>
            	<form:input type="text" path="lastName"/>
            	<form:errors path="lastName" class="text-danger"/>
        	</p>
        	<p>
            	<form:label path="email">Email:</form:label>
            	<form:input type="email" path="email"/>
            	<form:errors path="email" class="text-danger"/>
        	</p>
        	<p>
            	<form:label path="password">Password:</form:label>
            	<form:password path="password"/>
            	<form:errors path="password" class="text-danger"/>
        	</p>
        	<p>
            	<form:label path="passwordConfirmation">Password Confirmation:</form:label>
            	<form:password path="passwordConfirmation"/>
            	<form:errors path="passwordConfirmation" class="text-danger"/>
        	</p>
        		<input class="btn btn-primary mb-3" type="submit" value="Register!"/>
    		</form:form>
    		<a href="/login">Login Here</a>
			</div>
		</div>
	</div>
</body>
</html>