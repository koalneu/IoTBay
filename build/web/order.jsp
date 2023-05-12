<%-- 
    Document   : order
    Created on : 08/05/2023, 6:42:16 PM
    Author     : mjra9
--%>

<%@page import="java.util.Map"%>
<%@page import="models.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Order</title>
    </head>
    <body>
        <%
            Order order = (Order) session.getAttribute("order");
            User user = (User) session.getAttribute("user");
            double totalPrice = 0;
            
        %>
            <div align = "center">
                <h1>Current Order:</h1>

                <h2 align> User:<%= user.getUserFirstName() + user.getUserLastName()%></h2>
                <%if(order != null) {%>
                <form method = "post" action = "/OrderController">
                    <table cellspacing = "10">
                        <tr>
                            <th> Product: </th>
                            <th> Quantity: </th>
                            <th> Price: </th>
                        </tr>
                            <% for(Map.Entry<Product, Integer> product : order.getProducts().entrySet()){ 
                            String name = product.getKey().getName(); 
                            int quantity = product.getValue();
                            double price =product.getKey().getPrice();
                            totalPrice += price * quantity;
                            %>
                                <tr>
                                    <td><%=name%></td>
                                    <td><%=quantity%></td>
                                    <td>$<%=price * quantity%></td>
                                </tr>
                            <% }%>
                    </table>
                            <p> Total = $<%= totalPrice%> </p>
                    <input type = "submit" value = "Proceed to checkout"/>
                </form>
            </div>
            
        <%}else { %>
            <p> Order is currently empty please add products to your order using the previous page</p>
        <%}%>
       
    </body>
</html>
