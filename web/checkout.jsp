<%-- 
    Document   : checkout
    Created on : 08/05/2023, 6:42:45 PM
    Author     : mjra9
--%>

<%@page import="models.User"%>
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
            User user = (User) session.getAttribute("user");
        %>
        <div>
            <jsp:include page = "orderPreview.jsp" flush = "true"/>
        </div>
        
        <div align = "center">
            <button><a href = "CheckoutController?action=<%=saveOrder%>"> Pay later</a></button>
            <button><a href = "CheckoutController?action=<%=payOrder%>"> Pay Order</a></button>
            <button> <a href = "cart.jsp"> Back to cart </a></button>
            <button><a href = "index.jsp"> Return to home page </a> </button>
            <button><a href = "cancelOrderController"> Cancel order </a> </button>
        </div>
    </body>
</html>
