<%-- 
    Document   : productDetails
    Created on : 15/05/2023, 12:24:40 PM
    Author     : legob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="models.Product" %>
<%@page import="models.User" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Page</title>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            Product product = (Product) session.getAttribute("product");
        %>
        <h1 align="center"><%= product.getProductName() %></h1>
        <img class="center" src="${product.getProductImage()}" alt="Image cannot be loaded"/>
        <h2 align="center"><%= product.getProductDesc() %></h2>
        <h2 align="center"><%= "$" + product.getProductPrice() %></h2>
        <h2 align="center"><%= "Stock remaining: " + product.getProductStock() %></h2>
        <button><a href="staffProducts.jsp">Return to products page</a></button>
        
        <%
            if (user.getUserType().equals("Staff")) {
        %>
        <button><a href="staffEditProduct.jsp">Edit product details</a></button>
        <button><a href="DeleteProductController?productID=${product.productID}">Delete product</a></button>
        <%
            } else {
        %>
        <button><a href="!!!!!!!!!CART VIEW GOES HERE!!!!!!!!!.jsp">Add to cart</a></button>
        <%
            }
        %>
        
    </body>
</html>
<link rel="stylesheet" href="./css/productPage.css"/>