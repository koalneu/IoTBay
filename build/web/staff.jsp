<%-- 
    Document   : staff
    Created on : 09/05/2023, 11:10:45 AM
    Author     : Wilson
--%>

<%@page import="DAO.DBManager"%>
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
        <form action="staffServlet" method="post">
            <%
                /* GOING TO HAVE TO CHANGE THIS IN THE CONTROLLER BUT TO DIFFERENTIATE VIEW LOOK AT BELOW

                String userType = DBManager.userType(user.getUserEmail());

                if(userType.equals("Admin")){
                    //HANDLE THE ADMIN VIEW HERE
                }
                else if(userType.equals("Staff")){
                    //HANDLE THE STAFF VIEW HERE
                }
                */
            %>
        </form>
    </body>
</html>
