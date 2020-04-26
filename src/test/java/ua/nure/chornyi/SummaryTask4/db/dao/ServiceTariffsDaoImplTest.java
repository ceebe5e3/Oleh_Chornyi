package ua.nure.chornyi.SummaryTask4.db.dao;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class ServiceTariffsDaoImplTest {

    private static Statement statement;
    private static ResultSet resultSet;

    @Test
    public void findServiceTariffsByTariffID() throws SQLException{
        resultSet = statement.executeQuery("SELECT service_tariffs.id AS stID, services.id AS servicesID, services.name AS services, tariffs.id AS tariffsID, tariffs.name AS tariffs, service_tariffs.price, service_tariffs.description \"\n" +
                "+ \"FROM services \"\n" +
                "+ \"JOIN service_tariffs ON service_tariffs.service_id=services.id \"\n" +
                "+ \"JOIN tariffs ON tariffs.id=service_tariffs.tariff_id \"\n" +
                "+ \"WHERE tariff_id=?");
        assertNotNull(resultSet.next());
    }

    @Test
    public void getById() throws SQLException{
        resultSet = statement.executeQuery("SELECT service_tariffs.id AS stID, services.name AS services, services.id AS servicesID, \"\n" +
                "+ \"tariffs.name AS tariffs, tariffs.id AS tariffsID, service_tariffs.price, service_tariffs.description \"\n" +
                "+ \"FROM services \"\n" +
                "+ \"JOIN service_tariffs ON service_tariffs.service_id=services.id \"\n" +
                "+ \"JOIN tariffs ON tariffs.id=service_tariffs.tariff_id \"\n" +
                "+ \"WHERE service_tariffs.id=?;");
        assertNotNull(resultSet.next());
    }

    @Test
    public void editTariff() throws SQLException{
        resultSet = statement.executeQuery("UPDATE service_tariffs \"\n" +
                "+ \"JOIN tariffs ON service_tariffs.tariff_id=tariffs.id \"\n" +
                "+ \"SET tariffs.name=?, service_tariffs.price=?, service_tariffs.description=? \"\n" +
                "+ \"WHERE service_tariffs.service_id=? \"\n" +
                "+ \"AND service_tariffs.tariff_id=?");
        assertNotNull(resultSet.next());
    }

    @Test
    public void findServicesAndTariffs() throws SQLException {
        resultSet = statement.executeQuery("SELECT service_tariffs.id AS stID, services.name AS services, services.id AS servicesID, \"\n" +
                "+ \"tariffs.name AS tariffs, tariffs.id AS tariffsID, service_tariffs.price, service_tariffs.description \"\n" +
                "+ \"FROM services \"\n" +
                "+ \"JOIN service_tariffs ON service_tariffs.service_id=services.id \"\n" +
                "+ \"JOIN tariffs ON tariffs.id=service_tariffs.tariff_id");
        assertNotNull(resultSet.next());
    }
}