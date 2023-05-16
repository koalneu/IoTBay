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
        %>
        <div align = "center">
            <h1><%=type.equals("saved") ? "Saved Orders" : "Order History"%></h1>
            <%if (orders != null){%>
                <h2> User: <%=user.getUserFirstName() + " " + user.getUserLastName()%> </h2>
                <table cellspacing = "10">
                    <tr>
                        <th>Order Number</th>
                        <th>Date <%= type.equals("saved") ? "saved" : "ordered"%></th>
                    </tr>
                    <%for (Order order : orders){ %>
                    <tr>
                        <td><%= order.getOrderID()%></td>
                        <td><%= order.getDate()%></td>
                    </tr>
                    <% }%>
                </table>
            <%} else {%>
                <p>you have no saved orders/order history</p>
            <%}%>
        </div>
        
    </body>
</html>
