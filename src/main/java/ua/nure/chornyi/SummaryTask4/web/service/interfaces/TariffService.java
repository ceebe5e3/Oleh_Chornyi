package ua.nure.chornyi.SummaryTask4.web.service.interfaces;

import ua.nure.chornyi.SummaryTask4.db.entity.Tariff;

/**
 * Provides work with {@link Tariff} entity and related to it
 */
public interface TariffService extends GenericService<Tariff> {

    /**
     * Returns tariff id with the given name.
     * @param tariff tariff
     * @return Tariff tariff
     */
    int getTariffId(String tariff);

    /**
     * Insert tariff into 'tariffs' and 'service_tariffs' tables.
     * @param tariff tariff name.
     * @param typeOfService service id.
     * @param price price tariff.
     * @param description
     */
    void insertTariffWithService(String tariff, int typeOfService, double price, String description);
}
