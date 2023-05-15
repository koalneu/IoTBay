package controllers;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;

public class Validator {
    
    //Place patterns here
    private final String stringPattern = "^[a-zA-Z ]{0,50}$"; //String size <= 50 and only chars (fname, lname, city, street, country)
    private final String emailPattern = "([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,2})*)"; //Copied from tut
    private final String passwordPattern = "^.{8,}$"; //Minimum string length = 8
    private final String postcodePattern = "\\d{4}"; //4 digits
    
    
    public boolean validate(String pattern, String input){
        Pattern regex = Pattern.compile(pattern);
        Matcher match = regex.matcher(input);
        return match.matches();
    }
    public boolean validateStringPattern(String input){
        // used on (fname, lname, city, street, country)
        return validate(stringPattern,input);
    }
    
    public boolean validateEmail(String email) {
        return validate(emailPattern,email);
    }
    
    public boolean validatePassword(String password) {
        return validate(passwordPattern,password);
    }
            
    public boolean validatepostCode(String postcode) {
        return validate(postcodePattern,postcode);
    }
    
    public void clear(HttpSession session){
        session.removeAttribute("loginErr");
        session.removeAttribute("fnameErr");
        session.removeAttribute("lnameErr");
        session.removeAttribute("emailErr");
        session.removeAttribute("passwordErr");
        session.removeAttribute("streetErr");
        session.removeAttribute("cityErr");
        session.removeAttribute("postcodeErr");
        session.removeAttribute("countryErr");
    }
}
