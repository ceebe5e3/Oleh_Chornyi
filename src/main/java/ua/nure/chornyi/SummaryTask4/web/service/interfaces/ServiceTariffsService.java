package ua.nure.chornyi.SummaryTask4.web.service.interfaces;

import ua.nure.chornyi.SummaryTask4.db.entity.ServiceTariffs;

import java.util.List;

/**
 * Provides work with {@link ServiceTariffs} entity and related to it
 */
public interface ServiceTariffsService extends GenericService<ServiceTariffs> {

    /**
     * Returns services and tariffs with the given id.
     * @param tariffID tariff id
     * @return ServiceTariffs serviceTariffs
     */
    List<ServiceTariffs> findServiceTariffsByTariffID(int tariffID);

    /**
     * Edits tariff.
     * @param serviceTariffs serviceTariffs
     */
    void editTariff(ServiceTariffs serviceTariffs);

    /**
     * Returns all services with tariffs.
     * @return List of serviceTariffs entities.
     */
    List<ServiceTariffs> findServicesAndTariffs();
}
