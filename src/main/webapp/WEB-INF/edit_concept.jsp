<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title >Edit Product</title>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 <style type="text/css">

</style>
<div style="font-family: Monospace">
 
</head>
<body>
  <h1>Edit Concept</h1>
  
  <div>
     <a href="/home">View All Concepts</a>
  </div>
  
  
 <div class="container p-5 p-md-8 edit-form">
  <form action="<c:url value='/ideas/${idea.id}/update'/>" method="post">
    <input type="hidden" name="_method" value="PUT">

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${ideaForm.name}" required><br>
    
    <label for="desc">Description:</label>
    <input type="text" id="desc" name="desc" value="${ideaForm.desc}" required><br>
    
    <input type="submit" value="Save Changes">
    
  </form>
  
</div>
</body>
</html>
