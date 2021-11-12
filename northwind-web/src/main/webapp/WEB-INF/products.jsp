<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="com.hca.nwind.models.Product"%>
<%@ page import="com.hca.nwind.models.Category"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products</title>
</head>
<body>

	<table>
		<thead>
			<tr>
				<th>Product ID</th>
				<th>Product Name</th>
				<th>Unit Price</th>
				<th>Units in Stock</th>
			</tr>
		</thead>
		<tbody>
<%
   List<Product> list = (List<Product>) request.getAttribute("products");
   for(Product product : list) {
%>	   
	   <tr>
		   <td><%= product.getProductId() %></td>
		   <td><%= product.getProductName() %></td>
		   <td><%= String.format("$%.2f", product.getUnitPrice()) %></td>
		   <td><%= product.getUnitsInStock() %></td>
	   </tr> 
<%	   
   }
%>


		</tbody>
	</table>



</body>
</html>