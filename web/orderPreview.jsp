<%-- 
    Document   : orderPreview.jsp
    Created on : 17/05/2023, 4:06:40 PM
    Author     : mjra9
--%>

<%@page import="models.OrderLine"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="models.Order"%>
<%@page import="models.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Preview</title>
    </head>
    <body>
         <%
            Order order = (Order) session.getAttribute("order");
            DecimalFormat format = new DecimalFormat("$#.00");
            double totalPrice = 0.0;
        %>
        <div align = "center">
            <h1> Order Preview </h1>
            <table cellspacing ="10">
                <tr>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Price </th>
                </tr>
                <% 

                for(OrderLine product : order.getOrderLine()){ 
                    String name = product.getProduct().getProductName(); 
                    int quantity = product.getQuantity();
                    double price = product.getPrice();
                    totalPrice += price;

                %>
                    <tr>
                        <td> <%=name%> </td>
                        <td> <%=quantity%> </td>
                        <td> <%=format.format(price)%> </td>
                    </tr>
                <% }%>
            </table>
            <p>Total price = <%=format.format(totalPrice)%></p>
        </div>
    </body>
</html>
