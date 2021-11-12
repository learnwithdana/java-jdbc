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

<%
// if this JSP is being generated as a result of the POST request from the find button...
// get the selectedCategoryId so we can re-select the right option in the dropdown
   boolean isPostResponse = request.getAttribute("selectedCategory") == null ? false : true;
   int selectedCategoryId = -1;
   if (isPostResponse) {
	   selectedCategoryId = (int) request.getAttribute("selectedCategory");
   }
%>

<form method="POST" action="productsbycategory">
   <select name="category">
<%
   List<Category> list = (List<Category>) request.getAttribute("categories");
   for(Category category : list) {
	   if (isPostResponse && category.getCategoryId() == selectedCategoryId) {
%>	   
	<option value="<%= category.getCategoryId() %>"  selected> <%= category.getCategoryName() %> </option>
<%	
		}
		else {
%>	   
		<option value="<%= category.getCategoryId() %>" > <%= category.getCategoryName() %> </option>
<%	
		}
	}
%>
   </select>
  <input type="submit" value="Look up products">
</form>

<%
if (request.getAttribute("products") != null)  {
    List<Product> productList = (List<Product>) request.getAttribute("products");
    for(Product product : productList) {
%>
    <p> * <%= product.getProductName() %> </p>
<%
    }
}
%>




</body>
</html>