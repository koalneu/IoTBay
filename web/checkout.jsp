<%-- 
    Document   : checkout
    Created on : 08/05/2023, 6:42:45 PM
    Author     : mjra9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>
    </head>
    <body>
        <%
            String saveOrder = "save";
            String payOrder = "pay";
        %>
        <h1>Insert checkout here!</h1>
        <button><a href = "CheckoutController?action=<%=saveOrder%>"> Pay later</a></button>
        <button><a href = "CheckoutController?action=<%=payOrder%>"> Pay Order</a></button>
    </body>
</html>
