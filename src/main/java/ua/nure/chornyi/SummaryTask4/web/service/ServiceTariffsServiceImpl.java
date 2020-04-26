package ua.nure.chornyi.SummaryTask4.web.service;

import ua.nure.chornyi.SummaryTask4.db.dao.interfaces.ServiceTariffsDao;
import ua.nure.chornyi.SummaryTask4.db.entity.ServiceTariffs;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.ServiceTariffsService;

import java.util.List;

/**
 * {@link ServiceTariffsService} implementation for work with MySQL database
 */
public class ServiceTariffsServiceImpl implements ServiceTariffsService {

    private ServiceTariffsDao serviceTariffsDao;

    public ServiceTariffsServiceImpl(ServiceTariffsDao serviceTariffsDao) {
        this.serviceTariffsDao = serviceTariffsDao;
    }

    /**
     * Returns services and tariffs with the given id.
     * @param tariffID tariff id
     * @return ServiceTariffs serviceTariffs.
     */
    @Override
    public List<ServiceTariffs> findServiceTariffsByTariffID(int tariffID) {
        return serviceTariffsDao.findServiceTariffsByTariffID(tariffID);
    }

    /**
     * Edits tariff.
     * @param serviceTariffs serviceTariffs
     */
    @Override
    public void editTariff(ServiceTariffs serviceTariffs) {
        serviceTariffsDao.editTariff(serviceTariffs);
    }

    /**
     * Returns all services with tariffs.
     * @return List of serviceTariffs entities.
     */
    @Override
    public List<ServiceTariffs> findServicesAndTariffs() {
        return serviceTariffsDao.findServicesAndTariffs();
    }


    @Override
    public void add(ServiceTariffs serviceTariffs) {
        serviceTariffsDao.create(serviceTariffs);
    }


    @Override
    public List<ServiceTariffs> findAll() {
        return serviceTariffsDao.getAll();
    }

    /**
     * Returns all serviceTariffs, which match to a key parameter
     * @param id parameter to specify selection
     * @return ServiceTariffs serviceTariffs
     */
    @Override
    public ServiceTariffs getById(int id) {
        return serviceTariffsDao.getById(id);
    }

    /**
     * @param id object to be deleted
     */
    @Override
    public void remove(int id) {
        serviceTariffsDao.delete(id);
    }


    @Override
    public ServiceTariffs update(ServiceTariffs serviceTariffs) {
        serviceTariffsDao.update(serviceTariffs);
        return serviceTariffs;
    }
}
