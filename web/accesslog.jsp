<%@page import="models.dao.DBManager"%>
<%@page import="java.util.Objects"%>
<%@page import="models.User"%>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Access Logs</title>
</head>
<body>
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
    <h1 align="center">Access logs</h1>
    
    <form action="accesslogController" method="post">
        <div style="text-align: center;">
            <input type="submit" value="Show Access Logs">
        </div>

    </form>
    
    <% ArrayList<ArrayList<String>> accessLogs = (ArrayList<ArrayList<String>>) request.getAttribute("accessLogs");
    

    if (accessLogs != null && !accessLogs.isEmpty()) {
    %>
        <table align="center">
            <thead>
                <tr align="center">
                    <th>Access Log ID</th>
                    <th>User ID</th>
                    <th>Name</th>
                    <th>Access Time</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody align="center">
                <% for (ArrayList<String> logEntry : accessLogs) { %>
                    <tr>
                        <% for (String value : logEntry) { %>
                            <td>
                                <%= value %>
                            </td>
                        <% } %>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% 
    } else {
        //should never get here
    }
    %>
    
</body>
</html>
<link rel="stylesheet" href="./css/index.css"/>
