package controllers;

import models.dao.*;
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
import models.Product;

/**
 *
 * @author benjamin
 */
public class AddProductController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProductValidator validator = new ProductValidator();
        validator.clear(session);
        //Retrieve data from input fields
        String productName = request.getParameter("productName");
        String productDesc = request.getParameter("productDesc");
        String productPrice = request.getParameter("productPrice");
        String productImage = request.getParameter("productImage");
        String productStock = request.getParameter("productStock");
        
        DBManager manager = (DBManager) session.getAttribute("manager");
        //validate inputs
        if (!validator.validateName(productName)) {
            session.setAttribute("productNameErr", "Error: Product name must be less than 50 characters");
            request.getRequestDispatcher("newProduct.jsp").include(request, response);
        } else if (!validator.validatePrice(productPrice)) {
            session.setAttribute("productPriceErr", "Error: Product price must be a number less than 52 digits");
            request.getRequestDispatcher("staffEditProduct.jsp").include(request, response);
        } else if (!validator.validateImage(productImage)) {
            session.setAttribute("productImageErr", "Error: Product image location must be less than 50 characters");
            request.getRequestDispatcher("staffEditProduct.jsp").include(request, response);
        } else if (!validator.validateStock(productStock)) {
            session.setAttribute("productStockErr", "Error: Product stock must be less than 10 digits");
            request.getRequestDispatcher("staffEditProduct.jsp").include(request, response);
        } else {
            try {
                double price = Double.parseDouble(productPrice);
                int stock = Integer.parseInt(productStock);
                manager.addProduct(productName, productDesc, price, productImage, stock);
                Product product = manager.findProduct(productName);
                session.setAttribute("product", product);
                request.getRequestDispatcher("productDetails.jsp").include(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
