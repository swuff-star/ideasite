<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
   <%@ page isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>MyCoolSite</title>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link href="css/main.css" rel="stylesheet"/>
<style>
  .login-container {
    font-family: Monospace;
  }
</style>
</head>
<body>

<h1 class="jumbo text-center text-muted">MyCoolSite</h1>

<div class="container login-container bg-info">
  <div class="login-form-1">
    <h2>Register</h2>
    <form:form action="register" method="post" modelAttribute="newUser">
      <div class="form-group">
        <form:label path="userName">Username:</form:label>
        <form:input type="text" path="userName" required="true" class="form-control" /><br>
        <form:errors path="userName" cssClass="error" /><br>
      </div>
      
      <div class="form-group">
        <form:label path="email">Email:</form:label>
        <form:input type="email" path="email" required="true" class="form-control"/><br>
        <form:errors path="email" cssClass="error" /><br>
      </div>
      
      <div class="form-group">
        <form:label path="password">Password:</form:label>
        <form:input type="password" path="password" required="true" class="form-control"/><br>
        <form:errors path="password" cssClass="error" /><br>
      </div>
      
      <div class="form-group">
        <form:label path="confirm">Confirm Password:</form:label>
        <form:input type="password" path="confirm" required="true" class="form-control" /><br>
        <form:errors path="confirm" cssClass="error" /><br>
      </div>
      
      <input type="submit" value="Register" class="btnSubmit">
    </form:form>
  </div>
  
  <div class="login-form-2">
    <h2>Log In</h2>
    <form:form action="login" method="post" modelAttribute="newLogin">
    
      <div class="form-group">
        <form:label path="email">Email:</form:label>
        <form:input type="email" path="email" required="true" class="form-control"/><br><br>
        <form:errors path="email" cssClass="error" /><br>
      </div>
      
      <div class="form-group">
        <form:label path="password">Password:</form:label>
        <form:input type="password" path="password" required="true" class="form-control" /><br><br>
        <form:errors path="password" cssClass="error" /><br>
      </div>
      
      <input type="submit" value="Log In" class="btnSubmit" >
    </form:form>
  </div>
</div>
  

</body>
</html>
