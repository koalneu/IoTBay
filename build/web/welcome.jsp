<%-- 
    Document   : welcome
    Created on : 7 Apr 2023, 12:11:22 am
    Author     : @author (Tyler) Shi En Lim 13675919
    Author     : @author Wilson 14269118
--%>

<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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
            //Retrieve data from Register page
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String street = request.getParameter("street");
            String city = request.getParameter("city");
            String postcode = request.getParameter("postcode");
            String state = request.getParameter("state");
            String country = request.getParameter("country");
            String paymethod = request.getParameter("payment-method");
            
            
            if (fname==null ) {
                //User is coming from the Login Page
                User user = new User(null, null, email, password, null,
                null, null,null, null);
                //check if the credentials match any in the database and return a user
                user = user.authenticateUser(email, password);
                if (user == null){
                    //Incorrect Login Credentials
                    //return to the login.jsp
                    response.sendRedirect("login.jsp");
                } 
                else {
                    //Credentials Passed authentication
                    //Save data to variables for a new user object with the retrieved information
                    fname = user.getUserFirstName();
                    lname = user.getUserLastName();
                    street = user.getUserStreet();
                    postcode = user.getUserPostCode();
                    city = user.getUserCity();
                    state = user.getUserState();
                    country = user.getUserCountry();
                }
            } else {
                //Set variables for the connection to DB
                String dbuser = "iotadmin";
                String dbpass = "password";
                String driver = "org.apache.derby.jdbc.ClientDriver";
                //establish connection: 
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/IoTDatabase", dbuser, dbpass);
                //statement
                Statement statement = conn.createStatement();
                String command = "INSERT INTO CUSTOMER(CUSTOMERID,CUSTOMERFIRSTNAME,CUSTOMERLASTNAME,CUSTOMEREMAIL,CUSTOMERPASSWORD,CUSTOMERSTREET,CUSTOMERPOSTCODE,CUSTOMERCITY,CUSTOMERSTATE,CUSTOMERCOUNTRY) VALUES(?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = conn.prepareStatement(command);
                //calculate the new ID
                String rows = "select count(*) from CUSTOMER";
                ResultSet retrieveResult = statement.executeQuery(rows);
                retrieveResult.next();
                int ID = retrieveResult.getInt(1);
                pst.setObject(1, ID);
                pst.setObject(2, fname);
                pst.setObject(3, lname);
                pst.setObject(4, email);
                pst.setObject(5, password);
                pst.setObject(6, street);
                pst.setObject(7, postcode);
                pst.setObject(8, city);
                pst.setObject(9, state);
                pst.setObject(10, country);
                pst.executeUpdate();
                conn.close();
            }
        %>
        <h1>
            <%= "Welcome, " + fname + " " + lname %>
        </h1>
        <%
            //create a new user object and set it as the current session
            User user = new User(fname, lname, email, password, street, city,postcode, state, country);
            session.setAttribute("user", user);
        %>
        <button><a href="main.jsp">Main Page</a></button>
    </body>
</html>
<link rel="stylesheet" href="./css/welcome.css">
