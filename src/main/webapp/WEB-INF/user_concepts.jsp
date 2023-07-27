<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${userProducts[0].user.userName}'s Concepts</title>
<link href="css/main.css" rel="stylesheet">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<style type="text/css">

</style>
<div style="font-family: Monospace">
</head>
<body>
    <h1 class="text-muted">Concepts uploaded by ${userProducts[0].user.userName}</h1>
      <div>
     <a href="/home">View All Concepts</a>
  </div>
    <div class="container-fluid p-3 p-md-5">
    <ul>
        <c:forEach items="${userIdeas}" var="idea">
          <li class="list-group-item list-group-item-primary d-flex justify-content-center">
      	  	<a href="<c:url value='/ideas/${idea.id}'/>">${idea.pName}</a> - ${idea.pDesc}
    	  </li>
        </c:forEach>
    </ul>
    </div>
</body>
</html>
