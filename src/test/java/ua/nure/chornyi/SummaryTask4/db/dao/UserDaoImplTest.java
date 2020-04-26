package ua.nure.chornyi.SummaryTask4.db.dao;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class UserDaoImplTest {

    private static Statement statement;
    private static ResultSet resultSet;

    @Test
    public void findUserByLogin() throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM users WHERE login=?");
        assertNotNull(resultSet.next());
    }

    @Test
    public void checkLogin() throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM users WHERE login=?");
        assertNotNull(resultSet.next());
    }

    @Test
    public void update() throws SQLException{
        resultSet = statement.executeQuery("UPDATE users SET login=?, password=? WHERE id=?");
        assertNotNull(resultSet.next());
    }

    @Test
    public void create() throws SQLException{
        resultSet = statement.executeQuery("INSERT INTO users (full_name, date_of_birth, address, email, login, password, role_id) \"\n" +
                "+ \"VALUES (?,?,?,?,?,?,?)");
        assertNotNull(resultSet.next());
    }

    @Test
    public void findSubscribers() throws SQLException{
        resultSet = statement.executeQuery("SELECT * FROM users WHERE role_id=?");
        assertNotNull(resultSet.next());
    }

}