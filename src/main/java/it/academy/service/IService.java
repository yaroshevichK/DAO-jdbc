package it.academy.service;

import java.util.List;

/**
 * @param <TDto> type dto entity.
 * @author Katerina
 * @version 1.0
 */
public interface IService<TDto> {
    /**
     * Create person in database.
     *
     * @param dto object to save
     * @return value primary key
     */
    Integer savePerson(TDto dto);

    /**
     * Update person in database.
     *
     * @param dto object to save
     */
    void updatePerson(TDto dto);

    /**
     * Find person in database by id.
     *
     * @param id primary key
     * @return object which found
     */
    TDto getPerson(Integer id);

    /**
     * Find all people in database.
     *
     * @return list objects which found
     */
    List<TDto> getAllPeople();

    /**
     * Delete person in database by id.
     *
     * @param id primary key
     */
    void deletePerson(Integer id);

    /**
     * Delete all people in database.
     */
    void deleteAllPeople();
}
