<%-- 
    Document   : OrderHistory
    Created on : 15/05/2023, 10:51:12 PM
    Author     : mjra9
--%>

<%@page import="models.dao.DBManager"%>
<%@page import="java.util.Objects"%>
<%@page import="models.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Order History</title>
    </head>
    <body>
        <%
            ArrayList<Order> orders = (ArrayList<Order>) session.getAttribute("orderHistory");
            String type = (String) session.getAttribute("action");
            User user = (User) session.getAttribute("user");
            if(user == null) user = new User();
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
        <div align = "center">
            <h1>Order History</h1>
            <form method = "get" action = "/PayedOrderSearchController">
                <table>
                    <td>Search by Date: <input type="date" name ="date"/></td>
                    <td> Search by ID: <input type = "text" name = "ID"/></td>
                    <td><input type = "submit" value = "Search"/></td>
                </table>
            </form>
            <%if (!orders.isEmpty()){%>
                <h2> User: <%=user.getUserFirstName() + " " + user.getUserLastName()%> </h2>
                <table cellspacing = "10">
                    <tr>
                        <th>Order Number</th>
                        <th>Date ordered</th>
                    </tr>
                    <%for (Order order : orders){ %>
                    <tr>
                        <td><%= order.getOrderID()%></td>
                        <td><%= order.getDate()%></td>
                        <td><button><a href = "ViewOrderController?type=<%=type%>&orderID=<%=order.getOrderID()%>"> View Order</a></button></td>
                    </tr>
                    <% }%>
                </table>
                <button><a href = "index.jsp"> Return to home page </a> </button>
            <%} else {%>
                <p>you have no saved orders/order history</p>
                <p> <a href = "index.jsp"> Click Here </a> to go back to home page</p>
            <%}%>
        </div>
        
    </body>
</html>
<link rel="stylesheet" href="./css/index.css"/>