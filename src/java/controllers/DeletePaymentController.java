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
public class DeletePaymentController extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        User user = (User)session.getAttribute("user");

        //delete paymentmethod
        manager.deletePaymentMethod(user.getUserEmail());
        //set session attribute for payment to be the returned payment object
        //should be null since payment details are deleted
        PaymentMethod paymethod = manager.getPayMethod(user.getUserEmail());
        session.setAttribute("paymethod", paymethod);
        request.getRequestDispatcher("index.jsp").include(request, response); 
    }
}
