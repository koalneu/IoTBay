<%-- 
    Document   : viewOrder.jsp
    Created on : 16/05/2023, 11:22:20 AM
    Author     : mjra9
--%>

<%@page import="java.text.DecimalFormat"%>
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
            DecimalFormat format = new DecimalFormat("$#.00");
            String delete = "delete";
            String edit = "edit";
            String add ="set";
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
                        <td> <%=format.format(price)%> </td>
                    </tr>
                <% }%>
            </table>
            <p>Total price = <%=format.format(totalPrice)%></p>

            <%if (type.equals("saved")){%>
            <button> <a href = "UpdateOrderController?orderID=<%=order.getOrderID()%>&action=<%=delete%>"> Delete Order</a> </button>
            <button> <a href = "UpdateOrderController?orderID=<%=order.getOrderID()%>&action=<%=edit%>"> Edit Order </a> </button>
            <%} else {%>
                <button> <a href = "UpdateOrderController?orderID=<%=order.getOrderID()%>&action=<%=add%>"> Set  current order</a> </button>
            <%}%>
            <button><a href = "index.jsp"> Return to home page </a> </button>
        </div>
    </body>
</html>
