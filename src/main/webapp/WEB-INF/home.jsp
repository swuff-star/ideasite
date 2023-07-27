<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Home Page</title>
  <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
  <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <link href="css/main.css" rel="stylesheet"/>
</head>
<body>
  <div class="header-container">
    <h1 class="text-muted">Welcome, <%= request.getAttribute("username") %>!</h1>
    <form action="logout" method="get">
      <input type="submit" value="Log Out" class="log-out-button">
    </form>
  </div>
    
  <div class="container-fluid p-3 p-md-5">
    <h2 class="d-flex justify-content-center">Current Concepts</h2>
    <ul class="list-group list-group-flush">
      <c:forEach var="idea" items="${ideas}">
        <li class="list-group-item list-group-item-primary d-flex justify-content-center">
          Name: <a href="<c:url value='/ideas/${idea.id}'/>">${idea.pName}</a> - Description: ${idea.pDesc}
        </li>
      </c:forEach>
    </ul>
    
    <form action="ideas/new" method="get" class="d-flex justify-content-center">
      <input type="submit" value="Add New Concept">
    </form>
  </div>
</body>
</html>
