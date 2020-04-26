package ua.nure.chornyi.SummaryTask4.db.dao.interfaces;

import java.util.List;

/**
 * Interface for base working with data source, supported CRUD methods
 * @param <T> Type of entry, which source has
 */
public interface AbstractDao<T> {

    /**
     * Create a new entry into data source
     * @param entity object for insertion
     * @return identity key, which this entry got in data source
     */
    T create(T entity);

    /**
     * Updates an entry in data source
     * @param entity object, which contains all information for update
     */
    void update(T entity);

    /**
     * Returns all table entries, which match to a key parameter
     * @param id, parameter to specify selection
     * @return of objects from data source
     */
    T getById(int id);

    /**
     * Returns all data source, which exist in data source
     * @return of data source
     */
    List<T> getAll();

    /**
     * Deletes an entry from data source
     * @param id object to be deleted
     */
    void delete(int id);
}
