package it.academy.service;

import it.academy.dto.PersonDto;
import it.academy.entity.Person;
import it.academy.mapper.Mapper;
import it.academy.mapper.MapperPerson;
import it.academy.repository.IDao;
import it.academy.repository.impl.DaoPerson;

import java.util.List;

/**
 * @author Katerina
 * @version 1.0
 */
public class ServicePerson implements IService<PersonDto> {
    /**
     * Converter.
     */
    private final Mapper<Person, PersonDto> mapperPerson = new MapperPerson();
    /**
     * Dao.
     */
    private final IDao<Person> dao = new DaoPerson();

    /**
     * Create person in database.
     *
     * @param personDto object to save
     * @return value primary key
     */
    @Override
    public Integer savePerson(final PersonDto personDto) {
        return dao.save(mapperPerson.dtoToEntity(personDto));
    }

    /**
     * Update person in database.
     *
     * @param personDto object to save
     */
    @Override
    public void updatePerson(final PersonDto personDto) {
        dao.update(mapperPerson.dtoToEntity(personDto));
    }

    /**
     * Find person in database by id.
     *
     * @param id primary key
     * @return object which found
     */
    @Override
    public PersonDto getPerson(final Integer id) {
        return mapperPerson.entityToDto(dao.get(Person.class, id));
    }

    /**
     * Find all people in database.
     *
     * @return list objects which found
     */
    @Override
    public List<PersonDto> getAllPeople() {
        return dao.getAll(Person.class)
                .stream()
                .map(mapperPerson::entityToDto)
                .toList();
    }

    /**
     * Delete person in database by id.
     *
     * @param id primary key
     */
    @Override
    public void deletePerson(final Integer id) {
        dao.delete(Person.class, id);
    }

    /**
     * Delete all people in database.
     */
    @Override
    public void deleteAllPeople() {
        dao.deleteAll(Person.class);
    }
}
