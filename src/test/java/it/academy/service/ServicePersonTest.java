package it.academy.service;

import it.academy.dto.GenderDto;
import it.academy.dto.PersonDto;
import it.academy.entity.Gender;
import it.academy.entity.Person;
import it.academy.repository.IDao;
import it.academy.repository.impl.DaoPerson;
import it.academy.util.ConnectionDB;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


class ServicePersonTest {
    public static final String IVAN = "Ivan";
    public static final String IVANOV = "Ivanov";
    public static final String MARIA = "Maria";
    public static final String PETROVA = "Petrova";
    public static final GenderDto FEMALE = GenderDto.FEMALE;
    public static final GenderDto MALE = GenderDto.MALE;
    public static IDao<Person> dao = new DaoPerson();
    public static Connection connection;
    private final IService<PersonDto> service=new ServicePerson();

    @BeforeAll
    public static void init() throws SQLException, FileNotFoundException {
        connection = ConnectionDB.getConnection();
        RunScript.execute(connection, new FileReader("src\\test\\resources\\testPerson.sql"));
    }

    @BeforeEach
    public void clearTable() {
        dao.deleteAll(Person.class);
    }

    @Test
    void isShouldSavePerson() {
        PersonDto personDTO = PersonDto.builder()
                .name(IVAN)
                .surname(IVANOV)
                .gender(FEMALE).build();

        Integer id = service.savePerson(personDTO);
        Assertions.assertNotNull(id);
    }

    @Test
    void isShouldUpdatePerson() {
        PersonDto personDto = PersonDto.builder()
                .name(IVAN)
                .surname(IVANOV)
                .gender(FEMALE).build();

        Integer id = service.savePerson(personDto);
        personDto.setId(id);
        personDto.setName(MARIA);
        personDto.setSurname(PETROVA);
        personDto.setGender(MALE);

        service.updatePerson(personDto);

        PersonDto findPerson = service.getPerson(id);
        assertAll(
                () -> assertNotNull(findPerson),
                () -> assertEquals(id, findPerson.getId()),
                () -> assertEquals(MARIA, findPerson.getName()),
                () -> assertEquals(PETROVA, findPerson.getSurname()),
                () -> assertEquals(MALE, findPerson.getGender())
        );
    }

    @Test
    void isShouldSelectById() {
        PersonDto personDto = PersonDto.builder()
                .name(IVAN)
                .surname(IVANOV)
                .gender(FEMALE).build();

        Integer id = service.savePerson(personDto);
        personDto.setId(id);

        PersonDto findPerson = service.getPerson(id);
        assertAll(
                () -> assertNotNull(findPerson),
                () -> assertEquals(id, findPerson.getId()),
                () -> assertEquals(IVAN, findPerson.getName()),
                () -> assertEquals(IVANOV, findPerson.getSurname()),
                () -> assertEquals(FEMALE, findPerson.getGender())
        );
    }

    @Test
    void isShouldSelectAllPeople() {
        PersonDto personDto = PersonDto.builder()
                .name(IVAN)
                .surname(IVANOV)
                .gender(FEMALE).build();
        PersonDto personDto2 = PersonDto.builder()
                .name(MARIA)
                .surname(PETROVA)
                .gender(MALE).build();

        service.savePerson(personDto);
        service.savePerson(personDto2);

        List<PersonDto> findPeople = service.getAllPeople();

        assertAll(
                () -> assertNotNull(findPeople),
                () -> assertEquals(2, findPeople.size()),
                () -> assertEquals(IVAN, findPeople.get(0).getName()),
                () -> assertEquals(IVANOV, findPeople.get(0).getSurname()),
                () -> assertEquals(FEMALE, findPeople.get(0).getGender()),
                () -> assertEquals(MARIA, findPeople.get(1).getName()),
                () -> assertEquals(PETROVA, findPeople.get(1).getSurname()),
                () -> assertEquals(MALE, findPeople.get(1).getGender())
        );
    }

    @Test
    void isShouldDeleteById() {
        PersonDto personDTO = PersonDto.builder()
                .name(IVAN)
                .surname(IVANOV)
                .gender(FEMALE).build();
        Integer id = service.savePerson(personDTO);

        service.deletePerson(id);
        PersonDto findPerson = service.getPerson(id);

        assertNull(findPerson);
    }


    @Test
    void isShouldDeleteAllPeople() {
        PersonDto personDto = PersonDto.builder()
                .name(IVAN)
                .surname(IVANOV)
                .gender(FEMALE).build();
        PersonDto personDto2 = PersonDto.builder()
                .name(MARIA)
                .surname(PETROVA)
                .gender(MALE).build();

        service.savePerson(personDto);
        service.savePerson(personDto2);

        List<PersonDto> findPeople = service.getAllPeople();
        service.deleteAllPeople();
        List<PersonDto> findPeopleAfterDelete = service.getAllPeople();
        assertAll(
                () -> assertNotNull(findPeople),
                () -> assertEquals(2, findPeople.size()),
                () -> assertNotNull(findPeopleAfterDelete),
                () -> assertEquals(0, findPeopleAfterDelete.size())
        );

    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

}