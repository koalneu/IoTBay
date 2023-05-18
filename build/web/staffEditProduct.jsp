<%-- 
    Document   : staffEditProduct
    Created on : 14/05/2023, 11:10:40 PM
    Author     : benjamin
--%>

<%@page import="models.dao.DBManager"%>
<%@page import="java.util.Objects"%>
<%@page import="models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.Product" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Product Page</title>
    </head>
    <body>
        <%
            String errorMessage = (String) request.getSession().getAttribute("errorMessage");
            //set product object
            Product product = (Product) session.getAttribute("product");
            String productName = product.getProductName();
            String productDesc = product.getProductDesc();
            double productPrice = product.getProductPrice();
            String productImage = product.getProductImage();
            int productStock = product.getProductStock();
            //retrieve product errors
            String nameErr = (String) session.getAttribute("productNameErr");
            String descErr = (String) session.getAttribute("productDescErr");
            String priceErr = (String) session.getAttribute("productPriceErr");
            String imageErr = (String) session.getAttribute("productImageErr");
            String stockErr = (String) session.getAttribute("productStockErr");
        %>
        <div class="header">
        <a href="index.jsp" class="left">IOT Store</a>
        <div class="right">
            <%
            User user = (User) session.getAttribute("user");
            boolean isLoggedIn = (user != null && !Objects.equals(user.getUserEmail(), ""));
            DBManager manager = (DBManager) session.getAttribute("manager");
            String saved = "saved";
            String payed = "payed";
             
            if (isLoggedIn) {
                //Logged In
        %>
            <button class="headerBtn"><a href="logout.jsp">Logout</a></button>
            <button class="headerBtn"><a href="profile.jsp">Profile</a></button>
            <button class="headerBtn"><a href="cart.jsp">View Order</a></button>
            <button class="headerBtn"><a href="OrderHistoryController?action=<%=saved%>">View Saved orders</a></button>
            <button class="headerBtn"><a href="OrderHistoryController?action=<%=payed%>"">View Order History</a></button>
            <button class="headerBtn"><a href="staffProducts.jsp">Products</a></button>
            <button class="headerBtn"><a href="payment.jsp">Payment</a></button>

        <%
            } else {
                //Guest User ${pageContext.request.contextPath}/
        %>

            <th><button class="headerBtn"><a href="login.jsp">Login</a></button></th>
            <th><button class="headerBtn"><a href="register.jsp">Register</a></button></th>
            <th><button class="headerBtn"><a href="cart.jsp">View Order</a></button></th>
            <th><button class="headerBtn"><a href="GuestOrderHistoryController?action=<%=saved%>">View Saved orders</a></button></th>
            <th><button class="headerBtn"><a href="GuestOrderHistoryController?action=<%=payed%>">View Order History</a></button></th>
            <button class="headerBtn"><a href="staffProducts.jsp">Products</a></button>
        <%
            }
        %>
                        
        </div>
        </div>
        
        <h1><%= "Edit " + productName + " product details" %></h1>
        <form method="post" action="EditProductController">
            <table align="center">
                <tr>
                    <th><label>Product Name:</label></th>
                    <th><input type="text" name="productName" value="${product.productName}" required/></th>
                </tr>
                <tr>
                    <th><label>Product Description:</label></th>
                    <th><input type="text" name="productDesc" value="${product.productDesc}" required/></th>
                </tr>
                <tr>
                    <th><label>Product Price: </label></th>
                    <th><input type="text" name="productPrice" value="${product.productPrice}" required/></th>
                </tr>
                <tr>
                    <th><label>Product Image Location:</label></th>
                    <th><input type="text" name="productImage" value="${product.productImage}" required/></th>
                </tr>
                <tr>
                    <th><label>Product Stock: </label></th>
                    <th><input type="text" name="productStock" value="${product.productStock}" required/></th>
                </tr>
                <tr>
                    <th><button><a href="productDetails.jsp">Cancel</a></button></th>
                    <th><input type="submit" value="Save Changes" /></th>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <label style="color: red;"><%=(nameErr != null ? nameErr : "")%></label>
                        <label style="color: red;"><%=(descErr != null ? descErr : "")%></label>
                        <label style="color: red;"><%=(priceErr != null ? priceErr : "")%></label>
                        <label style="color: red;"><%=(imageErr != null ? imageErr : "")%></label>
                        <label style="color: red;"><%=(stockErr != null ? stockErr : "")%></label>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
<link rel="stylesheet" href="./css/index.css"/>