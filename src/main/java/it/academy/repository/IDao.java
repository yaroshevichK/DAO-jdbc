package it.academy.repository;

import java.util.List;

/**
 * @param <TEntity> type Entity
 * @author Katerina
 * @version 1.0
 */
public interface IDao<TEntity> {
    /**
     * Create record in database.
     *
     * @param entity object to save
     * @return value primary key
     */
    Integer save(TEntity entity);

    /**
     * Update record in database.
     *
     * @param entity object to save
     */
    void update(TEntity entity);

    /**
     * Find record in database by id.
     *
     * @param aClass class object
     * @param id     primary key
     * @return object which found in database
     */
    TEntity get(Class<TEntity> aClass, Integer id);

    /**
     * Find all records in database.
     *
     * @param aClass class object
     * @return list objects which found in database
     */
    List<TEntity> getAll(Class<TEntity> aClass);

    /**
     * Delete record in database by id.
     *
     * @param id     primary key
     * @param aClass class object
     */
    void delete(Class<TEntity> aClass, Integer id);

    /**
     * Delete all record in database.
     *
     * @param aClass class object
     */
    void deleteAll(Class<TEntity> aClass);
}
