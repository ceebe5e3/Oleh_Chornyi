package ua.nure.chornyi.SummaryTask4.db.entity;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;


public class UserTest {

    private final User user = new User();

    @Before
    public void setUpUserData() throws ParseException {
        user.setFullName("Full name");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = "2020-01-01";
        Date dateTo = sdf.parse(dateInString);
        user.setDateOfBirth(dateTo);
        user.setAddress("Address");
        user.setEmail("Email");
        user.setLogin("Login");
        user.setPassword("Password");
        user.setRoleId(1);
    }

    @Test
    public void getFullName() {
        assertEquals(user.getFullName(), "Full name");
    }

    @Test
    public void setFullName() {
        user.setFullName("Full name1");
        assertEquals(user.getFullName(), "Full name1");
    }

    @Test
    public void getDateOfBirth() throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = "2020-01-01";
        Date dateTo = sdf1.parse(dateInString);
        assertEquals(user.getDateOfBirth(), dateTo);
    }

    @Test
    public void setDateOfBirth() throws ParseException {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = "2020-01-11";
        Date dateTo = sdf2.parse(dateInString);
        user.setDateOfBirth(dateTo);
        assertEquals(user.getDateOfBirth(), dateTo);
    }

    @Test
    public void getAddress() {
        assertEquals(user.getAddress(), "Address");
    }

    @Test
    public void setAddress() {
        user.setAddress("Address1");
        assertEquals(user.getAddress(), "Address1");
    }

    @Test
    public void getEmail() {
        assertEquals(user.getEmail(), "Email");
    }

    @Test
    public void setEmail() {
        user.setEmail("Email1");
        assertEquals(user.getEmail(), "Email1");
    }

    @Test
    public void getLogin() {
        assertEquals(user.getLogin(), "Login");
    }

    @Test
    public void setLogin() {
        user.setLogin("Login1");
        assertEquals(user.getLogin(), "Login1");
    }

    @Test
    public void getPassword() {
        assertEquals(user.getPassword(), "Password");
    }

    @Test
    public void setPassword() {
        user.setPassword("Password1");
        assertEquals(user.getPassword(), "Password1");
    }

    @Test
    public void getRoleId() {
        assertEquals(user.getRoleId(), 1);
    }

    @Test
    public void setRoleId() {
        user.setRoleId(3);
        assertEquals(user.getRoleId(), 3);
    }

    @Test
    public void testToString() {
        assertEquals(user.toString(),
                "User [fullName=Full name, dateOfBirth=yyyy-MM-dd, address=Address, email=Email, login=Login, password=Password, roleId=1]");
    }
}