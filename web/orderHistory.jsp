<%-- 
    Document   : OrderHistory
    Created on : 15/05/2023, 10:51:12 PM
    Author     : mjra9
--%>

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
