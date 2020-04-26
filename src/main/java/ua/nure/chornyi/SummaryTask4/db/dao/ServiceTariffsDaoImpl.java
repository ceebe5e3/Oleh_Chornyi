package ua.nure.chornyi.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.db.DBManager;
import ua.nure.chornyi.SummaryTask4.db.constant.Fields;
import ua.nure.chornyi.SummaryTask4.db.dao.interfaces.ServiceTariffsDao;
import ua.nure.chornyi.SummaryTask4.db.entity.Service;
import ua.nure.chornyi.SummaryTask4.db.entity.ServiceTariffs;
import ua.nure.chornyi.SummaryTask4.db.entity.Tariff;
import ua.nure.chornyi.SummaryTask4.exception.DBException;
import ua.nure.chornyi.SummaryTask4.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.chornyi.SummaryTask4.db.DBUtil.close;
import static ua.nure.chornyi.SummaryTask4.db.DBUtil.rollback;

/**
 * {@link ServiceTariffsDao} implementation, provided DAO-logic for {@link ServiceTariffs}
 * entity with MySQL DataBase
 */
public class ServiceTariffsDaoImpl implements ServiceTariffsDao {

    private static final Logger logger = Logger.getLogger(ServiceTariffsDaoImpl.class);

    private static final String SELECT_SERVICE_AND_TARIFF = "SELECT service_tariffs.id AS stID, services.name AS services, services.id AS servicesID, "
            + "tariffs.name AS tariffs, tariffs.id AS tariffsID, service_tariffs.price, service_tariffs.description "
            + "FROM services "
            + "JOIN service_tariffs ON service_tariffs.service_id=services.id "
            + "JOIN tariffs ON tariffs.id=service_tariffs.tariff_id";
    private static final String SELECT_SERVICE_TARIFFS_BY_ID = "SELECT service_tariffs.id AS stID, services.name AS services, services.id AS servicesID, "
            + "tariffs.name AS tariffs, tariffs.id AS tariffsID, service_tariffs.price, service_tariffs.description "
            + "FROM services "
            + "JOIN service_tariffs ON service_tariffs.service_id=services.id "
            + "JOIN tariffs ON tariffs.id=service_tariffs.tariff_id "
            + "WHERE service_tariffs.id=?;";
    private static final String UPDATE_TARIFF = "UPDATE service_tariffs "
            + "JOIN tariffs ON service_tariffs.tariff_id=tariffs.id "
            + "SET tariffs.name=?, service_tariffs.price=?, service_tariffs.description=? "
            + "WHERE service_tariffs.service_id=? "
            + "AND service_tariffs.tariff_id=?";
    private static final String SELECT_SERVICE_BY_TARIFF_ID = "SELECT service_tariffs.id AS stID, services.id AS servicesID, services.name AS services, tariffs.id AS tariffsID, tariffs.name AS tariffs, service_tariffs.price, service_tariffs.description "
            + "FROM services "
            + "JOIN service_tariffs ON service_tariffs.service_id=services.id "
            + "JOIN tariffs ON tariffs.id=service_tariffs.tariff_id "
            + "WHERE tariff_id=?";

    /**
     * Returns services and tariffs with the given id.
     * @param tariffID tariff id
     * @return ServiceTariffs serviceTariffs.
     * @throws DBException
     */
    @Override
    public List<ServiceTariffs> findServiceTariffsByTariffID(int tariffID) throws DBException {
        List<ServiceTariffs> serviceTariffs = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_SERVICE_BY_TARIFF_ID);
            preparedStatement.setInt(1, tariffID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                serviceTariffs.add(extractServiceTariffs(resultSet));
            }
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            logger.error(Messages.ERROR_CANNOT_OBTAIN_LIST_SERVICE_TARIFFS, ex);
            throw new DBException(Messages.ERROR_CANNOT_OBTAIN_LIST_SERVICE_TARIFFS, ex);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return serviceTariffs;
    }

    @Override
    public ServiceTariffs create(ServiceTariffs serviceTariffs) {
        return null;
    }

    @Override
    public void update(ServiceTariffs serviceTariffs) {
    }

    /**
     * Returns all serviceTariffs, which match to a key parameter
     * @param id, parameter to specify selection
     * @return ServiceTariffs serviceTariffs
     */
    @Override
    public ServiceTariffs getById(int id) {
        ServiceTariffs serviceTariffs = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_SERVICE_TARIFFS_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                serviceTariffs = extractServiceTariffs(resultSet);
            }
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            logger.error(Messages.ERROR_CANNOT_OBTAIN_LIST_SERVICE_TARIFFS, ex);
            throw new DBException(Messages.ERROR_CANNOT_OBTAIN_LIST_SERVICE_TARIFFS, ex);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return serviceTariffs;
    }

    /**
     * Edits tariff.
     * @param serviceTariffs serviceTariffs
     * @throws DBException
     */
    @Override
    public void editTariff(ServiceTariffs serviceTariffs) throws DBException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_TARIFF);
            preparedStatement.setString(1, serviceTariffs.getTariff().getName());
            preparedStatement.setDouble(2, serviceTariffs.getPrice());
            preparedStatement.setString(3, serviceTariffs.getDescription());
            preparedStatement.setInt(4, serviceTariffs.getService().getId());
            preparedStatement.setInt(5, serviceTariffs.getTariff().getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            logger.error(Messages.ERROR_CANNOT_UPDATE_TARIFF, ex);
            throw new DBException(Messages.ERROR_CANNOT_UPDATE_TARIFF, ex);
        } finally {
            close(connection, preparedStatement, resultSet);
        }
    }

    /**
     * Returns all services with tariffs.
     * @return List of serviceTariffs entities.
     * @throws DBException
     */
    @Override
    public List<ServiceTariffs> findServicesAndTariffs() throws DBException {
        List<ServiceTariffs> serviceTariffs = new ArrayList<ServiceTariffs>();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_SERVICE_AND_TARIFF);
            while (resultSet.next()) {
                serviceTariffs.add(extractServiceTariffs(resultSet));
            }
            connection.commit();
        } catch (SQLException ex) {
            rollback(connection);
            logger.error(Messages.ERROR_CANNOT_OBTAIN_LIST_SERVICE_TARIFFS, ex);
            throw new DBException(Messages.ERROR_CANNOT_OBTAIN_LIST_SERVICE_TARIFFS, ex);
        } finally {
            close(connection, statement, resultSet);
        }
        return serviceTariffs;
    }

    /**
     * Extracts a ServicesTariffs entity from the result set.
     * @param resultSet Result set from which a ServiceTariffs entity will be
     *                  *            extracted.
     * @return ServiceTariffs serviceTariffs
     * @throws SQLException
     */
    @Override
    public ServiceTariffs extractServiceTariffs(ResultSet resultSet) throws SQLException {
        ServiceTariffs serviceTariffs = new ServiceTariffs();
        serviceTariffs.setId(resultSet.getInt(Fields.SERVICE_TARIFF_ID));

        Service service = new Service();
        service.setId(Integer.parseInt(resultSet.getString(Fields.SERVICE_ID)));
        service.setName(resultSet.getString(Fields.SERVICE_TARIFF_SERVICES));
        serviceTariffs.setService(service);

        Tariff tariff = new Tariff();
        tariff.setId(Integer.parseInt(resultSet.getString(Fields.TARIFF_ID)));
        tariff.setName(resultSet.getString(Fields.SERVICE_TARIFF_TARIFFS));
        serviceTariffs.setTariff(tariff);
        serviceTariffs.setPrice(resultSet.getDouble(Fields.SERVICE_TARIFF_PRICE));
        serviceTariffs.setDescription(resultSet.getString(Fields.SERVICE_TARIFF_DESC));

        return serviceTariffs;
    }

    @Override
    public List<ServiceTariffs> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
