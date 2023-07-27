<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Product Information</title>
  <link href="css/main.css" rel="stylesheet">
</head>

<body>
<div style="font-family: Monospace">
  <div class="header-container">
    <h1 class="product-header text-muted">${idea.pName}</h1>
    <div class="view-all-concepts">
      <a href="/home" class="text-white">View All Concepts</a>
    </div>
  </div>

  <div class="container-fluid p-3 p-md-5">

    <ul class="list-group ">

      <li class="list-group-item list-group-item-primary d-flex justify-content-center">Name: ${idea.pName}</li>
      <li class="list-group-item list-group-item-secondary d-flex justify-content-center">Creator: <a href="<c:url value='/users/${idea.user.id}/concepts' />">${idea.user.userName}</a></li>
      <li class="list-group-item list-group-item-success d-flex justify-content-center">Description: ${idea.pDesc}</li>

      <div class="d-flex justify-content-center list-group-item-dark">
        <img src="${idea.pImage}" alt="Product Image" width="100" height="100"/>
      </div>

    </ul>

    <div class="d-flex justify-content-center">
      <c:if test="${isOwner}">
        <form action="<c:url value='/ideas/${idea.id}/delete'/>" method="post" style="display: inline;">
          <input type="hidden" name="_method" value="DELETE">
          <input type="submit" value="Delete" class="btnSubmit">
        </form>

        <form action="<c:url value='/ideas/${idea.id}/edit'/>" method="get" style="display: inline;">
          <input type="hidden" name="_method" value=" EDIT ">
          <input type="submit" value=" Edit " class="btnSubmit">
        </form>
      </c:if>
    </div>
  </div>

  <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
  <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</div>
</body>
</html>
