<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Add Product</title>
  <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
  <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <link href="css/main.css" rel="stylesheet">
</head>
<body>
  <div class="banner">
    <h1 class="text-muted">List a New Product</h1>
    <a href="/home">View All Products</a>
  </div>

  <div class="container p-5 p-md-8 edit-form">
    <form action="/ideas" method="post" enctype="multipart/form-data">
      <label for="name">Concept Name:</label>
      <input type="text" id="name" name="name" required><br>
    
      <label for="desc">Description:</label>
      <input type="text" id="desc" name="desc" required><br>
    
      <label for="image">Image:</label>
      <input type="file" id="image" name="imageFile" accept="image/png, image/jpeg" required><br>
    
      <input type="submit" value="Add Product">
    </form>
  </div>
</body>
</html>
