package ua.nure.chornyi.SummaryTask4.db.dao;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class AccountDaoImplTest {

    private static Statement statement;
    private static ResultSet resultSet;

    @Test
    public void getById() throws SQLException {
        resultSet = statement.executeQuery("SELECT account.id AS accountID, user_id, money, is_blocked, is_blocked_by_admin, users.id, full_name, email, date_of_birth, address, login, password, role_id \"\n" +
                "+ \"FROM account JOIN users ON users.id=account.user_id \"\n" +
                "+ \"WHERE user_id=?");
        assertNotNull(resultSet.next());
    }

    @Test
    public void accountPayment() throws SQLException{
        resultSet = statement.executeQuery("UPDATE account SET money=(money+?) WHERE user_id=?");
        assertNotNull(resultSet.next());
    }

}