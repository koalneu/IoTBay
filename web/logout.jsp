<%-- 
    Document   : logout
    Created on : 7 Apr 2023, 7:18:32 pm
    Author     : @author (Tyler) Shi En Lim 13675919
    Author     : @author Wilson 14269118
--%>

<%@page import="java.util.Objects"%>
<%@page import="models.dao.DBManager"%>
<%@page import="models.AccessLog"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout Page</title>
    </head>
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
        <div>
    <form method="post" action="logoutController">
        <body>
                <h1 align="center">You have successfully logged out</h1>
                <h3 align="center"><button  type="submit" >Return to home page</button></h3>
        </body>
    </form>
        </div>
</html>
<link rel="stylesheet" href="./css/index.css"/>