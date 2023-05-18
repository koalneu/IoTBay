package controllers;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;

/**
 *
 * @author benjamin
 */
public class ProductValidator {
    //Patterns
    private final String namePattern = "^.{0,50}$";
    //private final String descPattern = "^[a-zA-Z0-9]{0,32700}$"; ^[a-zA-Z0-9_@./#&+-]
    private final String pricePattern = "[+-]?([0-9]*[.])?[0-9]+{0,52}";
    private final String imagePattern = "[a-zA-Z0-9@./#&+-]{0,50}$";
    private final String stockPattern = "^[0-9]{0,59}$";
    
    public boolean validate(String pattern, String input) {
        Pattern regex = Pattern.compile(pattern);
        Matcher match = regex.matcher(input);
        return match.matches();
    }
    public boolean validateName(String name) {
        //return validate(namePattern, name);
        if (name.length() > 0 && name.length() < 50) {
            return true;
        }
        return false;
    }
    public boolean validatePrice(String price) {
        return validate(pricePattern, price);
    }
    public boolean validateImage(String image) {
        //return validate(imagePattern, image);
        if (image.length() > 0 && image.length() < 50) {
            return true;
        }
        return false;
    }
    public boolean validateStock(String stock) {
        return validate(stockPattern, stock);
    }
    public void clear(HttpSession session) {
        session.removeAttribute("productNameErr");
        session.removeAttribute("productDescErr");
        session.removeAttribute("productPriceErr");
        session.removeAttribute("productImageErr");
        session.removeAttribute("productStockErr");
    }
}
