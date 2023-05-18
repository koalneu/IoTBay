<%-- 
    Document   : guestOrderHistory
    Created on : 17/05/2023, 1:34:12 PM
    Author     : mjra9
--%>

<%@page import="controllers.Validator"%>
<%@page import="models.User"%>
<%@page import="models.User"%>
<%@page import="models.Order"%>
<%@page import="models.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Guest Order History</title>
    </head>
    <body>
        <%
            ArrayList<Order> orders = (ArrayList<Order>) session.getAttribute("orderHistory");
            String type = (String) session.getAttribute("action");
            User user = (User) session.getAttribute("user");
            String emailErr = (String) session.getAttribute("emailErr");
            Validator validator = new Validator();
            validator.clear(session);
            if(user == null) user = new User();
        %>
        <div align = "center">
            <h1>Order History</h1>
            <form method = "get" action = "/GuestPayedHistoryController">
                <label>Enter email: </label><input type ="email" name ="email" required><br>
                <label style="color: red;"><%=(emailErr != null ? emailErr : "")%></label><br>
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
                <button align><a href = "index.jsp"> Return to home page </a> </button>
            <%} else {%>
                <p>you have no saved orders/order history</p>
                <p> <a href = "index.jsp"> Click Here </a> to go back to home page</p>
            <%}%>
        </div>
        
    </body>
</html>
