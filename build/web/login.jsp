<%-- 
    Document   : login
    Created on : 6 Apr 2023, 11:21:17 pm
    Author     : @author (Tyler) Shi En Lim 13675919
    Author     : @author Wilson 14269118
--%>


<%@page import="java.util.Objects"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.User" %>
<%@page import="models.dao.DBManager" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IotBay Login Page</title>
    </head>
    
    <body>
        <%  //Retrieve errors
        String loginErr = (String) session.getAttribute("loginErr");
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
        <h1>Login Page</h1>        
    </body>
    
    
        <div>
    <form method="post" action="loginController">          
        <table align="center">
            <th>
                <tr>
                    <th><label>Email: </label></th>
                    <th><input type="text" name="email" required=""/></th>
                </tr>
                <tr>
                    <th><label>Password: </label></th>
                    <th><input type="Password" name="password" required/></th>
                </tr>
                <tr>
                    <th><button><a href="index.jsp">Cancel</a></button></th>
                    <th><button type="submit" >Login</button></th>
                </tr>

                <tr>
                    <td colspan="2" style="text-align: center;">
                        <label style="color: red;"><%=(loginErr != null ? loginErr : "")%></label>
                    </td>
                </tr>
            </th>
        </table>
    </form>
        </div>
</html>
<link rel="stylesheet" href="./css/index.css"/>
