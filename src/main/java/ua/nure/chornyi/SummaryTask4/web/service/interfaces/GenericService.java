package ua.nure.chornyi.SummaryTask4.web.service.interfaces;

import java.util.List;

/**
 * Interface for base working with data source
 * @param <T> Type of entry, which source has
 */
public interface GenericService<T> {

    /**
     * Add a new entry into data source
     * @param element object for insertion
     */
    void add(T element);

    /**
     * Returns all data source, which exist in data source
     * @return of data source
     */
    List<T> findAll();

    /**
     * Returns all table entries, which match to a key parameter
     * @param id  parameter to specify selection
     * @return of objects from data source
     */
    T getById(int id);

    /**
     * Deletes an entry from data source
     * @param id object to be deleted
     */
    void remove(int id);

    /**
     * Updates an entry in data source
     * @param element object, which contains all information for update
     * @return object
     */
    T update(T element);
}
