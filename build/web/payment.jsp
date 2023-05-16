<%-- 
    Document   : payment
    Created on : 14/05/2023, 10:11:34 PM
    Author     : tylershienlim
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

            if (paymethod != null) {
                System.out.println("is not null");
                //has payment details created
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
                    <th><button type="button" onClick="history.back()" >Cancel</button></th>
                    <th><input type="submit" value="Save payment details" /></th>
                </tr>
            </table>
        </form>
        <%
            } else {
                System.out.println("is null");
                //System.out.println(paymethod.getPayMethodCardNo());
                //No payment details
        %>
        <h1>Add New Payment Details</h1>
        <form method="post" action="PaymentController" >
            <table align="center" >
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
                        />
                    </th>
                </tr>
                <tr>
                    <th><label>Credit Card Name:</label></th>
                    <th><input 
                            type="text" name="cardname" 
                            placeholder="Enter card holder name.." 

                        /></th>
                </tr>
                <tr>
                    <th><label>Card Expiration Date:</label></th>
                    <th><input 
                            type="date" name="cardexpiry" 
                            
                        /></th>
                </tr>
                <tr>
                    <th><label>CVV code:</label></th>
                    <th><input 
                            type="text" name="cardcvv" 
                            placeholder="Enter card 3 digits.." 
                            
                        /></th>
                </tr>
                <tr>
                    <th><button type="button" onClick="history.back()" >Cancel</button></th>
                    <th><input type="submit" value="Save payment details" /></th>
                </tr>
            </table>
        </form>
        <%
            }
        %>
    </body>
</html>
<link rel="stylesheet" href="./css/payment.css"/>