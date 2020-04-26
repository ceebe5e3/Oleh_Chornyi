package ua.nure.chornyi.SummaryTask4.db.dao;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class ServiceDaoImplTest {

    private static Statement statement;
    private static ResultSet resultSet;

    @Test
    public void getAll() throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM services");
        assertNotNull(resultSet.next());
    }

}