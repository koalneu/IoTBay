<%-- 
    Document   : viewOrder.jsp
    Created on : 16/05/2023, 11:22:20 AM
    Author     : mjra9
--%>

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
            Order order = (Order) session.getAttribute("orderToView");
            String type = (String) session.getAttribute("viewOrderType");
            String delete = "delete";
            String edit = "edit";
            String add ="add";
            double totalPrice = 0.0;
        %>
        <div align = "center">
            <h1>View Order <%=order.getOrderID()%></h1>
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
                        <td> $<%=price%> </td>
                    </tr>
                <% }%>
            </table>
            <p>Total price = <%=totalPrice%></p>
            <%if (type.equals("saved")){%>
            <button> <a href = "UpdateOrderController?orderID=<%=order.getOrderID()%>&action=<%=delete%>"> Delete Order</a> </button>
            <button> <a href = "UpdateOrderController?orderID=<%=order.getOrderID()%>&action=<%=edit%>"> Edit Order </a> </button>
            <%} else {%>
                <button> <a href = "UpdateOrderController?orderID=<%=order.getOrderID()%>&action=<%=add%>"> Add to current order</a> </button>
            <%}%>
        </div>
    </body>
</html>
