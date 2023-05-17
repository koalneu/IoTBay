<%-- 
    Document   : guestCheckout
    Created on : 17/05/2023, 1:33:59 PM
    Author     : mjra9
--%>

<%@page import="controllers.Validator"%>
<%@page import="models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String emailErr = (String) session.getAttribute("emailErr");
            Validator validator = new Validator();
            validator.clear(session);
        %>
        <jsp:include page = "orderPreview.jsp" flush = "true"/>
        <div align = "center">
            <p>Please select to pay or save your order and enter an email</p>
            <form method = "post" action = "/GuestCheckOutController">
               <label> Enter email: </label> <input type = "email" name = "email" required><br>
               <label style="color: red;"><%=(emailErr != null ? emailErr : "")%></label><br>
               <input type = "radio" name = "action" value = "pay" required>
               <label for = "pay"> Pay for Order </label><br> 
               <input type = "radio" name = "action" value = "save">
               <label for = "save"> Save Order </label><br> 
               <input type = "submit" value = "Submit" >
            </form>
            <br>
            <button> <a href = "cart.jsp"> Back to cart </a></button>
            <button><a href = "index.jsp"> Return to home page </a> </button>
            <button><a href = "cancelOrderController"> Cancel Order </a> </button>
        </div>
    </body>
</html>
