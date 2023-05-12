<%-- 
    Document   : register
    Created on : 6 Apr 2023, 11:20:54 pm
    Author     : @author (Tyler) Shi En Lim 13675919
    Author     : @author Wilson 14269118
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IotBay Register Page</title>
    </head>
    <body>
        <h1>IoTBay Customer Register Page</h1>
    </body>
    <%  //Retrieve errors
        String fnameErr = (String) session.getAttribute("fnameErr");
        String lnameErr = (String) session.getAttribute("lnameErr");
        String emailErr = (String) session.getAttribute("emailErr");
        String passwordErr = (String) session.getAttribute("passwordErr");
        String streetErr = (String) session.getAttribute("streetErr");
        String cityErr = (String) session.getAttribute("cityErr");
        String postcodeErr = (String) session.getAttribute("postcodeErr");
        String countryErr = (String) session.getAttribute("countryErr");
    %>
    <form method="post" action="registerController" >
        <table align="center" >
            <tr>
                <th><label>First Name:</label></th>
                <th><input type="text" name="fname" placeholder="Enter your first name..." required/></th>
            </tr>
            <tr>
                <th><label>Last Name:</label></th>
                <th><input type="text" name="lname" placeholder="Enter your last name..." required/></th>
            </tr>
            <tr>
                <th><label>Email:</label></th>
                <th><input type="email" name="email" placeholder="Enter your email..." required/></th>
            </tr>
            <tr>
                <th><label>Password:</label></th>
                <th><input type="password" name="password" required/></th>
            </tr>
            <tr>
                <th><label>Street:</label></th>
                <th><input type="text" name="street" required/></th>
            </tr>
            <tr>
                <th><label>City:</label></th>
                <th><input type="text" name="city" required/></th>
            </tr>
            <tr>
                <th><label>PostCode:</label></th>
                <th><input type="text" name="postcode" required/></th>
            </tr>
            <tr>
                <th><label>State:</label>
                <th>
                    <select name="state" required>
                        <option value="">Select a state...</option>
                        <option value="ACT">ACT</option>
                        <option value="NSW">NSW</option>
                        <option value="NT">NT</option>
                        <option value="QLD">QLD</option>
                        <option value="SA">SA</option>
                        <option value="VIC">VIC</option>
                        <option value="TAS">TAS</option>
                        <option value="WA">WA</option>
                    </select>
                </th>
            </tr>
            <tr>
                <th><label>Country:</label></th>
                <th><input type="text" name="country" placeholder="Enter your country.." required/></th>
            </tr>
            <tr>
                <th><button type="button" onClick="history.back()" >Cancel</button></th>
                <th><input type="submit" value="Register" /></th>
            </tr>
            <tr>
                    <td colspan="2" style="text-align: center;">
                        <label style="color: red;"><%=(fnameErr != null ? fnameErr : "")%></label>
                        <label style="color: red;"><%=(lnameErr != null ? lnameErr : "")%></label>
                        <label style="color: red;"><%=(emailErr != null ? emailErr : "")%></label>
                        <label style="color: red;"><%=(passwordErr != null ? passwordErr : "")%></label>
                        <label style="color: red;"><%=(streetErr != null ? streetErr : "")%></label>
                        <label style="color: red;"><%=(cityErr != null ? cityErr : "")%></label>
                        <label style="color: red;"><%=(postcodeErr != null ? postcodeErr : "")%></label>
                        <label style="color: red;"><%=(countryErr != null ? countryErr : "")%></label>
                    </td>
            </tr>

        </table>
    </form>
</html>
<link rel="stylesheet" href="./css/register.css"/>