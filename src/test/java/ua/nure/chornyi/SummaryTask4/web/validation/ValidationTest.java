package ua.nure.chornyi.SummaryTask4.web.validation;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidationTest {

    static Validation validation;

    @BeforeClass
    public static void validatorsTest() {
        validation = new Validation();
    }

    @Test
    public void validate() {
        boolean expected = false;
        boolean actual = Validation.validate(new String[]{"Java", null});
        assertEquals(expected, actual);
    }

    @Test
    public void addSubscriberValidate() {
        boolean expected = false;
        boolean actual = Validation.addSubscriberValidate("Antonov Anton Antonovich", "1999-01-01", "Kyiv", "anton@gmail", "anton", "qwerty");
        assertEquals(expected, actual);
    }

    @Test
    public void insertTariffValidate() {
        boolean expected = false;
        boolean actual = Validation.insertTariffValidate("Tariff", "-100", "Speed 14mb");
        assertEquals(expected, actual);
    }

    @Test
    public void insertTariffValidate1() {
        boolean expected = false;
        boolean actual = Validation.insertTariffValidate("Tariff", "100", "Speed 14mb");
        assertEquals(expected, actual);
    }

    @Test
    public void validateDateOfBirth() {
        boolean expected = false;
        boolean actual = Validation.validateDateOfBirth("4021-02-03");
        assertEquals(expected, actual);
    }

    @Test
    public void validateDateOfBirth1() {
        boolean expected = false;
        boolean actual = Validation.validateDateOfBirth("2021-01-01");
        assertEquals(expected, actual);
    }

    @Test
    public void validateLoginAndPassword() {
        boolean expected = false;
        boolean actual = Validation.validateLoginAndPassword("example", "example");
        assertEquals(expected, actual);
    }

    @Test
    public void validateLoginAndPassword1() {
        boolean expected = false;
        boolean actual = Validation.validateLoginAndPassword("ex", "ex");
        assertEquals(expected, actual);
    }

    @Test
    public void validateLogin() {
        boolean expected = false;
        boolean actual = Validation.validateLogin("example");
        assertEquals(expected, actual);
    }

    @Test
    public void validateLogin1() {
        boolean expected = false;
        boolean actual = Validation.validateLogin("exa");
        assertEquals(expected, actual);
    }


    @Test
    public void accountPaymentValidate() {
    }

    @Test
    public void validateSum() {
        boolean expected = false;
        boolean actual = Validation.validateSum("-10");
        assertEquals(expected, actual);
    }

    @Test
    public void validateSum1() {
        boolean expected = false;
        boolean actual = Validation.validateSum("10");
        assertEquals(expected, actual);
    }

    @Test
    public void validateEmail() {
        boolean expected = false;
        boolean actual = Validation.validateEmail("@gmailcom");
        assertEquals(expected, actual);
    }

    @Test
    public void validateEmail1() {
        boolean expected = false;
        boolean actual = Validation.validateEmail("example@gmail.com");
        assertEquals(expected, actual);
    }
}