<%-- 
    Document   : staff
    Created on : 09/05/2023, 11:10:45 AM
    Author     : Wilson
--%>

<%@page import="database.DBManager"%>
<%@page import="models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Page</title>
    </head>
    <body>
        <h1>Staff Page</h1>
        <%
            //Set the current user object by retrieving data from the session
            User user = (User)session.getAttribute("user");
            //Handle the admin view and the staff view
            String userType = DBManager.userType(user.getUserEmail());
            
            if(userType.equals("Admin")){
                //HANDLE THE ADMIN VIEW HERE
            }
            else if(userType.equals("Staff")){
                //HANDLE THE STAFF VIEW HERE
            }
        %>
    </body>
</html>
