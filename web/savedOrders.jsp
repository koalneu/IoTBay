<%-- 
    Document   : savedOrders
    Created on : 16/05/2023, 9:48:09 PM
    Author     : mjra9
--%>

<%@page import="models.Order"%>
<%@page import="models.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ArrayList<Order> orders = (ArrayList<Order>) session.getAttribute("orderHistory");
            String type = (String) session.getAttribute("action");
            User user = (User) session.getAttribute("user");
            if(user == null) user = new User();
        %>
        <div align = "center">
            <h1> Saved Orders </h1>
            <form method = "get" action = "/SavedOrderController">
                <table>
                    <td>Search by Date: <input type="date" name ="date"/></td>
                    <td> Search by ID: <input type = "text" name = "ID" pattern = "\d{0,8}"/></td>
                    <td><input type = "submit" value = "Search"/></td>
                </table>
            </form>
            <%if (!orders.isEmpty()){%>
                <h2> User: <%=user.getUserFirstName() + " " + user.getUserLastName()%> </h2>
                <table cellspacing = "10">
                    <tr>
                        <th>Order Number</th>
                        <th>Date saved</th>
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
