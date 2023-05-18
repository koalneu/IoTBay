package controllers;

import models.dao.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import models.dao.DBManager;

/**
 *
 * @author Wilson
 */
public class editController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        User user = (User) session.getAttribute("user");
        String originalEmail = user.getUserEmail();
        validator.clear(session);
        //retrieve data from input fields
        
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String postcode = request.getParameter("postcode");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        
        DBManager manager = (DBManager) session.getAttribute("manager");
        //Validate inputs
        if(!validator.validateStringPattern(fname)){
            session.setAttribute("fnameErr", "Error: first name must be less than 50 characters");
            request.getRequestDispatcher("edit.jsp").include(request, response);
        }
        else if(!validator.validateStringPattern(lname)){
            session.setAttribute("lnameErr", "Error: last name must be less than 50 characters");
            request.getRequestDispatcher("edit.jsp").include(request, response);
        }
        else if(!validator.validateEmail(email)){
            session.setAttribute("emailErr", "Error: email format incorrect");
            request.getRequestDispatcher("edit.jsp").include(request, response);
        }
        else if(!validator.validatePassword(password)){
            session.setAttribute("passwordErr", "Error: password must be at least 8 characters");
            request.getRequestDispatcher("edit.jsp").include(request, response);
        }
        else if(!validator.validateStringPattern(street)){
            session.setAttribute("streetErr", "Error: street name must be less than 50 characters");
            request.getRequestDispatcher("edit.jsp").include(request, response);
        }
        else if(!validator.validateStringPattern(city)){
            session.setAttribute("cityErr", "Error: city name must be less than 50 characters");
            request.getRequestDispatcher("edit.jsp").include(request, response);
        }
        else if(!validator.validatepostCode(postcode)){
            session.setAttribute("postcodeErr", "Error: postcode format incorrect");
            request.getRequestDispatcher("edit.jsp").include(request, response);
        }
        else if(!validator.validateStringPattern(country)){
            session.setAttribute("countryErr", "Error: country name must be less than 50 characters");
            request.getRequestDispatcher("edit.jsp").include(request, response);
        }
        else{
            try{
                manager.updateUser(originalEmail,fname,lname,email,password,street,postcode,city,state,country);
                manager.addAccessLogEntry(email, fname, "Update Details");
                user = new User(fname, lname, email, password, street, postcode,city, state, country, null);
                session.setAttribute("user", user);
                request.getRequestDispatcher("profile.jsp").include(request, response);
            }
            catch (SQLException ex) {
                Logger.getLogger(editController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(editController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

   
}
