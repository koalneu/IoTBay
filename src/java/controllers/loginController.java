package controllers;

import models.dao.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import models.PaymentMethod;

/**
 *
 * @author Wilson
 */
public class loginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Retrieve the data from the input fields
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        DBManager manager = (DBManager) session.getAttribute("manager");
        Validator validator = new Validator();
        validator.clear(session);
        User user = null;
        PaymentMethod paymethod = null;
        try {
            user = manager.authenticateUser(email, password);
            paymethod = manager.getPayMethod(user.getUserEmail());
            if (user == null) {
                // Incorrect details
                // Take the user back to the login.jsp page
                // Provide an error message
                session.setAttribute("loginErr", "Invalid credentials. Please try again.");
                request.getRequestDispatcher("login.jsp").include(request, response);
            } else {
                // Correct details
                session.setAttribute("user", user);
                session.setAttribute("paymethod",paymethod);
                // Take them to the correct page
                String userType = manager.userType(email);
                if (userType.equals("Staff") || userType.equals("Admin")) {
                    // Staff or Admin -> staff page
                    request.getRequestDispatcher("index.jsp").include(request, response);

                } else {
                    // Customer -> index page
                    request.getRequestDispatcher("index.jsp").include(request, response);
                }
                try {
                    // Add to the access log
                    manager.addAccessLogEntry(email, user.getUserFirstName(), "Log in");
                } catch (SQLException ex) {
                    // Handle the exception
                }
            }
            session.setAttribute("order", null);
        } catch (ClassNotFoundException | SQLException e) {
            // Handle the exception
        }
    }
}
