<%-- 
    Document   : welcome
    Created on : 7 Apr 2023, 12:11:22 am
    Author     : @author (Tyler) Shi En Lim 13675919
    Author     : @author Wilsan 14269118
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
    </head>
    <body>
        <%
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String street = request.getParameter("street");
            String postcode = request.getParameter("postcode");
            String state = request.getParameter("state");
            String country = request.getParameter("country");
            String paymethod = request.getParameter("payment-method");
            
            //check if fname is null
            //if null -> registered user trying to login
            //else -> coming from register page -> use the normal parameters
            if (fname==null ) {
                //create an user object
                //set email, password to input
                //call function
                //if passes, set session
                //if fails, wipe object
                User user = new User(null, null, email, password, null,
                null, null, null);
                user = user.authenticateUser(email, password);
                if (user == null ){
                    //stay on login.jsp instead
                    //provide error message
                    System.out.println("empty");
                    response.sendRedirect("login.jsp");
                } else {
                    //get obj variables? and set
                    fname = user.getUserFirstName();
                    lname = user.getUserLastName();
                    street = user.getUserStreet();
                    postcode = user.getUserPostCode();
                    state = user.getUserState();
                    country = user.getUserCountry();
                    System.out.println("can log in");
                }
            } else {
                System.out.println("come from register page ");
            }
        %>
        <h1>
            <%= "Welcome, " + fname + " " + lname %>
        </h1>
        <%
            User user = new User(fname, lname, email, password, street,
            postcode, state, country);
            session.setAttribute("user", user);
        %>
        <button><a href="main.jsp">Main Page</a></button>
    </body>
</html>
<link rel="stylesheet" href="./css/welcome.css">