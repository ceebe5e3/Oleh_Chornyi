package ua.nure.chornyi.SummaryTask4.db.dao;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class TariffDaoImplTest {

    private static Statement statement;
    private static ResultSet resultSet;

    @Test
    public void getAll() throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM tariffs");
        assertNotNull(resultSet.next());
    }

    @Test
    public void extractTariff() throws SQLException{
    }

    @Test
    public void delete() throws SQLException{
        resultSet = statement.executeQuery("DELETE tariffs \"\n" +
                "+ \"FROM tariffs \"\n" +
                "+ \"LEFT JOIN service_tariffs \"\n" +
                "+ \"ON tariffs.id=service_tariffs.tariff_id \"\n" +
                "+ \"WHERE service_tariffs.tariff_id IS NULL");
        assertNotNull(resultSet.next());
    }

    @Test
    public void delete1() throws SQLException{
        resultSet = statement.executeQuery("DELETE FROM contracts WHERE service_tariff_id=?");
        assertNotNull(resultSet.next());
    }

    @Test
    public void delete2() throws SQLException{
        resultSet = statement.executeQuery("DELETE FROM service_tariffs WHERE id=?");
        assertNotNull(resultSet.next());
    }

    @Test
    public void insertTariff() throws SQLException{
        resultSet = statement.executeQuery("INSERT INTO tariffs VALUES (DEFAULT, ?)");
        assertNotNull(resultSet.next());
    }

    @Test
    public void getTariffId() throws SQLException{
        resultSet = statement.executeQuery("SELECT * FROM tariffs WHERE name=?");
        assertNotNull(resultSet.next());
    }

    @Test
    public void insertTariffWithService() throws SQLException{
        resultSet = statement.executeQuery("INSERT INTO service_tariffs VALUES (DEFAULT, ?, ?, ?, ?)");
        assertNotNull(resultSet.next());
    }

}