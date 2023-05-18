/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tylershienlim
 */
public class PaymentValidator {
    //patterns
    private final String cvvPattern = "^\\d{3}+$";
    private final String namePattern = "([A-Z][a-z]+[\\s])+[A-Z][a-z]*";
    private final String cardNoPattern = "\\d{8}+$";
    
    public boolean validate(String pattern, String input){
        Pattern regex = Pattern.compile(pattern);
        Matcher match = regex.matcher(input);
        return match.matches();
    }
    
    public boolean validateName(String name){
        return validate(namePattern,name);
    }
    
    public boolean validateCVV(String cvv){
        return validate(cvvPattern, cvv);
    }
    
    public boolean validateCardNo(String cardNo){
        return validate(cardNoPattern, cardNo);
    }
    
    public boolean validateExpiry(Date cardDate){
        Date today = new Date();
        if (cardDate.compareTo(today)>0){
            return true;
        }
        return false;
    }
    
    public void clear(HttpSession session){
        session.removeAttribute("cardName");
        session.removeAttribute("cvvNo");
        session.removeAttribute("cardNo");
        session.removeAttribute("cardDate");
    }
}
