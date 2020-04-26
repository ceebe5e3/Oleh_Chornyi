package ua.nure.chornyi.SummaryTask4.web.service;

import ua.nure.chornyi.SummaryTask4.db.dao.interfaces.ServiceDao;
import ua.nure.chornyi.SummaryTask4.db.entity.Service;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.ServiceIf;

import java.util.List;

/**
 * {@link ServiceIf} implementation for work with MySQL database
 */
public class ServiceIfImpl implements ServiceIf {

    private ServiceDao serviceDao;

    public ServiceIfImpl(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    @Override
    public void add(Service service) {
        serviceDao.create(service);
    }

    /**
     * Returns service, which exist in data source
     * @return Service service.
     */
    @Override
    public List<Service> findAll() {
        List<Service> all = serviceDao.getAll();
        return all;
    }
    @Override
    public Service getById(int id) {
        return serviceDao.getById(id);
    }

    @Override
    public void remove(int id) {
        serviceDao.delete(id);
    }


    @Override
    public Service update(Service service) {
        serviceDao.update(service);
        return service;
    }
}
