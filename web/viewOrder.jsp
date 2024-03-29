<%-- 
    Document   : viewOrder.jsp
    Created on : 16/05/2023, 11:22:20 AM
    Author     : mjra9
--%>

<%@page import="models.dao.DBManager"%>
<%@page import="java.util.Objects"%>
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
<link rel="stylesheet" href="./css/index.css"/>