<%-- 
    Document   : editpaymentprofile
    Created on : 16/05/2023, 1:41:35 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Payment Details</title>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            PaymentMethod paymethod = (PaymentMethod) session.getAttribute("paymethod");
        %>
        <h1>Update Payment Details</h1>
        <form method="post" >
            <table class="payment-table" align="center" >
                <tr>
                    <th><label>Payment Amount:</label></th>
                    <th><input 
                            type="text" name="paymentAmount"
                            placeholder="Enter amount..." 
                        />
                    </th>
                </tr>
                <tr>
                    <th><label>Card Number:</label></th>
                    <th>
                        <input 
                            type="text" name="cardno" 
                            placeholder="Enter credit card number..." 
                            value="${paymethod.payMethodCardNo}"
                        />
                    </th>
                </tr>
                <tr>
                    <th><label>Credit Card Name:</label></th>
                    <th><input 
                            type="text" name="cardname" 
                            placeholder="Enter card holder name.." 
                            value="${paymethod.payMethodCardHolder}"
                        /></th>
                </tr>
                <tr>
                    <th><label>Card Expiration Date:</label></th>
                    <th><input 
                            type="date" name="cardexpiry" 
                            value="${paymethod.payMethodCardExpiry}"
                        /></th>
                </tr>
                <tr>
                    <th><label>CVV code:</label></th>
                    <th><input 
                            type="text" name="cardcvv" 
                            placeholder="Enter card 3 digits.." 
                            value="${paymethod.payMethodCardSecurity}"
                        /></th>
                </tr>
                <tr>
                    <th><button><a href="payment.jsp">Cancel</a></button></th>
                    <th><button><a>Edit payment details</a></button></th>
                </tr>
            </table>
        </form>
    </body>
</html>
<link rel="stylesheet" href="./css/editpaymentprofile.css"/>
