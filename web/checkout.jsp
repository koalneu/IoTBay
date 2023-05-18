<%-- 
    Document   : checkout
    Created on : 08/05/2023, 6:42:45 PM
    Author     : mjra9
--%>

<%@page import="models.dao.DBManager"%>
<%@page import="java.util.Objects"%>
<%@page import="models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>
    </head>
    <body>
        <%
            String saveOrder = "save";
            String payOrder = "pay";
            User user = (User) session.getAttribute("user");
        %>
        <div class="header">
        <a href="index.jsp" class="left">IOT Store</a>
        <div class="right">
            <%
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
        <div>
            <jsp:include page = "orderPreview.jsp" flush = "true"/>
        </div>
        
        <div align = "center">
            <button><a href = "CheckoutController?action=<%=saveOrder%>"> Pay later</a></button>
            <button><a href = "CheckoutController?action=<%=payOrder%>"> Pay Order</a></button>
            <button> <a href = "cart.jsp"> Back to cart </a></button>
            <button><a href = "index.jsp"> Return to home page </a> </button>
            <button><a href = "cancelOrderController"> Cancel order </a> </button>
        </div>
    </body>
</html>
<link rel="stylesheet" href="./css/index.css"/>