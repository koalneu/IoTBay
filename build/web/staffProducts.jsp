<%-- 
    Document   : staffProducts
    Created on : 14/05/2023, 11:10:23 PM
    Author     : benjamin
--%>

<%@page import="java.util.Objects"%>
<%@page import="java.util.HashSet"%>
<%@page import="models.dao.DBManager"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.User" %>
<%@page import="models.Product" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Products Page</title>
    </head>
    <body>
        <%
            String searchProductErr = (String) session.getAttribute("searchProductErr");
            User user = (User) session.getAttribute("user");
            boolean isLoggedIn = (user != null && !Objects.equals(user.getUserEmail(), ""));
            DBManager manager = (DBManager) session.getAttribute("manager");
            ArrayList productList = manager.fetchProducts();
            session.setAttribute("products", productList);
            String saved = "saved";
            String payed = "payed";
        %>
        <div class="header">
            <a href="index.jsp" class="left">IOT Store</a>
            <div class="right">
                <%
                    if (isLoggedIn) {
                        //Logged In
                %>
                    <button class="headerBtn"><a href="logout.jsp">Logout</a></button>
                    <button class="headerBtn"><a href="profile.jsp">Profile</a></button>
                    <button class="headerBtn"><a href="cart.jsp">View Order</a></button>
                    <button class="headerBtn"><a href="OrderHistoryController?action=<%=saved%>">View Saved orders</a></button>
                    <button class="headerBtn"><a href="OrderHistoryController?action=<%=payed%>"">View Order History</a></button>
                <%
                    } else {
                        //Guest User ${pageContext.request.contextPath}/
                %>

                    <th><button class="headerBtn"><a href="login.jsp">Login</a></button></th>
                    <th><button class="headerBtn"><a href="register.jsp">Register</a></button></th>
                    <th><button class="headerBtn"><a href="cart.jsp">View Order</a></button></th>
                    <th><button class="headerBtn"><a href="GuestOrderHistoryController?action=<%=saved%>">View Saved orders</a></button></th>
                    <th><button class="headerBtn"><a href="GuestOrderHistoryController?action=<%=payed%>">View Order History</a></button></th>
                <%
                    }
                %>
            </div>
        </div>
        <h1>IoTBay Product Catalogue</h1>
        <form method="get" action="SearchProductController">
            <table align="center">
                <th>
                    <tr>
                        <th><label>Product Name: </label></th>
                        <th><input type="text" name="productName"></th>
                    </tr>
                </th>
                <th>
                    <tr>
                        <td colspan="2" style="text-align: center;">
                            <label style=""color: red;"><%=(searchProductErr != null ? searchProductErr : "")%></label>
                        </td>
                    </tr>
                </th>
            </table>
        </form>
        <% 
            if (isLoggedIn && user.getUserType().equals("Staff")) {
        %>
        <div class="centerBttn">
            <button class="bttn"><a href="newProduct.jsp">Add product</a></button>
        </div>
        <%
            }
        %>
            <%
                if (productList.isEmpty()) {
                    System.out.println("No products found");
            %>
            <tr>
                <h1 align="center">No products found</h1>
            </tr>
            <%
                } else {
                    System.out.println("Products found");
            %>        
        <table align="center">
            <c:forEach items="${products}" var="product">
                
                <tr>
                    <td><img src="${product.getProductImage()}" alt="Image cannot be loaded" width="100" height="50"/></td>
                    <td><a href="ProductClickController?productID=${product.productID}"><c:out value="${product.productName}"/></a></td>
                </tr>
                
            </c:forEach>
        </table>
            <%
                }
            %>        
                        
        <div class="centerBttn" align="center">
            <button><a href="index.jsp">Back to home page</a></button>
        </div>
    </body>
</html>
<link rel="stylesheet" href="./css/index.css"/>

