package ua.nure.chornyi.SummaryTask4.web.validation;

import ua.nure.chornyi.SummaryTask4.db.dao.UserDaoImpl;
import ua.nure.chornyi.SummaryTask4.exception.DBException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Class with methods for input data validation.
 */
public class Validation {

    private static final String DATE = "\\d{4}\\-\\d{2}\\-\\d{2}";
    private static final String POSITIVE_NUMBER = "^\\d+\\.?\\d*?$";
    private static final String CARD_NUMBER = "\\d{16}";
    private static final String EMAIL = "^\\w+@[a-z]+\\.[a-z]+$";

    public static boolean validate(String... values) {
        if (checkNull(values)) {
            return false;
        }
        return true;
    }

    public static boolean addSubscriberValidate(String fullName, String dateOfBirth, String address, String email,
                                                String login, String password) throws DBException {
        if (checkNull(fullName, dateOfBirth, address, email, login, password)) {
            return false;
        }
        if (!validateDateOfBirth(dateOfBirth)) {
            return false;
        }
        if (!validateEmail(email)){
            return false;
        }
        if (!validateLoginAndPassword(login, password)) {
            return false;
        }
        return true;
    }

    public static boolean insertTariffValidate(String name, String price, String description) {
        if (checkNull(name, price, description)) {
            return false;
        }
        if (!price.matches(POSITIVE_NUMBER)) {
            return false;
        }
        return true;
    }

    private static boolean checkNull(String... values) {
        if (values == null) {
            return true;
        } else {
            for (String value : values) {
                if (value == null) {
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean validateDateOfBirth(String dob) {
        Date dateOfBirth = null;
        if (!dob.matches(DATE)) {
            return false;
        }
        try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
            dateOfBirth = new Date(date.getTime());
        } catch (ParseException e) {
            return false;
        }
        if (dateOfBirth.after(new java.util.Date())) {
            return false;
        }
        return true;
    }

    public static boolean validateLoginAndPassword(String login, String password) throws DBException {
        if (!validateLogin(login)) {
            return false;
        }
        if (login.length() <= 4 || password.length() <= 4) {
            return false;
        }
        return true;
    }

    public static boolean validateLogin(String login) throws DBException {
        boolean isExist = new UserDaoImpl().checkLogin(login);
        if (isExist) {
            return false;
        }
        return true;
    }

    public static boolean accountPaymentValidate(String sum, String cardNumber, String code) {
        if (checkNull(sum, cardNumber, code)) {
            return false;
        }
        if (!validateSum(sum)) {
            return false;
        }
        if(!validateCardNumber(cardNumber)) {
            return false;
        }

        if(!validateCode(code)) {
            return false;
        }
        return true;
    }

    public static boolean validateSum(String sum) {
        if (!sum.matches(POSITIVE_NUMBER)) {
            return false;
        }
        return true;
    }

    private static boolean validateCardNumber(String cardNumber) {
        if (!cardNumber.matches(CARD_NUMBER)) {
            return false;
        }
        return true;
    }

    private static boolean validateCode(String code) {
        if (!code.matches(POSITIVE_NUMBER)) {
            return false;
        }
        return true;
    }

    public static boolean validateEmail(String email) {
        if (!email.matches(EMAIL)) {
            return false;
        }
        return true;
    }
}
