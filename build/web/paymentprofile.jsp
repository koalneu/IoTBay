<%-- 
    Document   : editpayment
    Created on : 16/05/2023, 12:59:47 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAO.DBManager"%>
<%@page import="controllers.Validator"%>
<%@page import="models.AccessLog"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="models.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Payment Page</title>
    </head>
    <body>
        <%
            //Set the current user object by retrieving data from the session
            Payment payment = (Payment)session.getAttribute("payment");
            //System.out.println(payment.getPaymentAmount());
            PaymentMethod paymentMethod = (PaymentMethod)session.getAttribute("paymentmethod");
            User user = (User)session.getAttribute("user");
        %>
        <h1>Payment Details</h1>
        <h2><%= user.getUserFirstName() + " " + user.getUserLastName() + "'s Payment Details:" %></h2> 
        <table id="paymentprofile-table">
           <thead>
               <tr>
                   <th>Amount</th>
                   <td>${payment.getPaymentAmount()}</td>
               </tr>
               <tr>
                   <th>Card Number</th>
                   <td>${payment.getPaymentAmount()}</td>
               </tr>
               <tr>
                   <th>Credit Card Name</th>
                   <td>${payment.getPaymentAmount()}</td>
               </tr>
               <tr>
                   <th>Card Expiration Date</th>
                   <td>${payment.getPaymentAmount()}</td>
               </tr>
               <tr>
                   <th>CVV Code</th>
                   <td>${payment.getPaymentAmount()}</td>
               </tr>
            </thead>
        </table>
        <button><a href="index.jsp">Back</a></button>
        <button><a href="editpaymentprofile.jsp" >Edit Payment Details</a></button>
    </body>
</html>
<link rel="stylesheet" href="./css/paymentprofile.css"/>