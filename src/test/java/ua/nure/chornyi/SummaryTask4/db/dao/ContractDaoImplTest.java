package ua.nure.chornyi.SummaryTask4.db.dao;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class ContractDaoImplTest {

    private static Statement statement;
    private static ResultSet resultSet;

    @Test
    public void getAll() throws SQLException {
        resultSet = statement.executeQuery("SELECT contracts.id AS contractID, contracts.user_id, service_tariffs.id AS stID, services.name AS services, \"\n" +
                "+ \"services.id AS servicesID, tariffs.name AS tariffs, tariffs.id AS tariffsID, service_tariffs.price, \"\n" +
                "+ \"service_tariffs.description, contracts.date, users.id, full_name, email, date_of_birth, address, login, password, role_id  FROM contracts \"\n" +
                "+ \"JOIN users ON users.id=contracts.user_id \"\n" +
                "+ \"JOIN service_tariffs ON service_tariffs.id=service_tariff_id \"\n" +
                "+ \"JOIN services ON services.id=service_tariffs.service_id \"\n" +
                "+ \"JOIN tariffs ON tariffs.id= service_tariffs.tariff_id");
        assertNotNull(resultSet.next());
    }

    @Test
    public void findUserContracts() throws SQLException{
        resultSet = statement.executeQuery("SELECT contracts.id AS contractID, contracts.user_id, service_tariffs.id AS stID, services.name AS services, \"\n" +
                "+ \"services.id AS servicesID, tariffs.name AS tariffs, tariffs.id AS tariffsID, service_tariffs.price, \"\n" +
                "+ \"service_tariffs.description, contracts.date, users.id, full_name, email, date_of_birth, address, login, password, role_id  FROM contracts \"\n" +
                "+ \"JOIN users ON users.id=contracts.user_id \"\n" +
                "+ \"JOIN service_tariffs ON service_tariffs.id=service_tariff_id \"\n" +
                "+ \"JOIN services ON services.id=service_tariffs.service_id \"\n" +
                "+ \"JOIN tariffs ON tariffs.id= service_tariffs.tariff_id \"\n" +
                "+ \"WHERE contracts.user_id=?");
        assertNotNull(resultSet.next());
    }

    @Test
    public void createNewContract() throws SQLException{
        resultSet = statement.executeQuery("INSERT INTO contracts \"\n" +
                "            + \"VALUES (DEFAULT, ?, (SELECT id FROM service_tariffs WHERE service_id=? AND tariff_id=?), ?)");
        assertNotNull(resultSet.next());
    }
}