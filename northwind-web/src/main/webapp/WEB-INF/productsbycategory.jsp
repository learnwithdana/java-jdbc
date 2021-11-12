<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.hca.nwind.models.Product"%>
<%@ page import="com.hca.nwind.models.Category"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products By Category</title>
</head>
<body>


<form method="POST" action="productsbycategory">
   <select name="category">
<%
   List<Category> list = (List<Category>) request.getAttribute("categories");
   for(Category category : list) {
%>	   
	<option value="<%= category.getCategoryId() %>" > <%= category.getCategoryName() %> </option>
<%	   
   }
%>
   </select>
  <input type="submit" value="Look up products">
</form>

</body>
</html>