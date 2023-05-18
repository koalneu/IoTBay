/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import models.dao.DBManager;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.*;
/**
 *
 * @author tylershienlim
 */
public class EditPaymentController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PaymentValidator payvalidator = new PaymentValidator();

        //Retrieve updated payment details
        //int amount = Integer.parseInt(request.getParameter("paymentAmount"));
        int cardNo = Integer.parseInt(request.getParameter("cardno"));
        int cvv = Integer.parseInt(request.getParameter("cardcvv"));
        String cardName = request.getParameter("cardname");
        String cardDate = request.getParameter("cardexpiry");        
        DBManager manager = (DBManager) session.getAttribute("manager");
        User user = (User)session.getAttribute("user");

        payvalidator.clear(session);
        //validate
        if (!payvalidator.validateCardNo(request.getParameter("cardno"))){
            session.setAttribute("cardNo", "Error: must be numeric input and 16 digits only");
            request.getRequestDispatcher("editpaymentprofile.jsp").include(request, response);
        } else if (!payvalidator.validateName(cardName)){
            session.setAttribute("cardName", "Error: must be string input, with last and first name");
            request.getRequestDispatcher("editpaymentprofile.jsp").include(request, response);
        } else if (!payvalidator.validateExpiry(java.sql.Date.valueOf(cardDate))){
            session.setAttribute("cardDate", "Error: card must not be expired");
            request.getRequestDispatcher("editpaymentprofile.jsp").include(request, response);
        } else if (!payvalidator.validateCVV(request.getParameter("cardcvv"))){
            session.setAttribute("cvvNo", "Error: must be 3 numeric input only");
            request.getRequestDispatcher("editpaymentprofile.jsp").include(request, response);
        } else {
            try {
                //update payment details to customer's payment details using email value column
                manager.updatePaymentMethod(user.getUserEmail(),cardNo, cardName, cardDate, cvv);
                //set session attribute for payment to be the returned payment object
                PaymentMethod paymethod = manager.getPayMethod(user.getUserEmail());
                session.setAttribute("paymethod", paymethod);
                request.getRequestDispatcher("index.jsp").include(request, response);
            } catch (SQLException ex) {
                 Logger.getLogger(EditPaymentController.class.getName()).log(Level.SEVERE, null, ex);
             } catch (Exception ex) {
                Logger.getLogger(EditPaymentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
