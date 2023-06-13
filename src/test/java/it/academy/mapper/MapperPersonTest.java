package it.academy.mapper;


import it.academy.dto.GenderDto;
import it.academy.dto.PersonDto;
import it.academy.entity.Gender;
import it.academy.entity.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MapperPersonTest {
    private static final int ID = 1;
    private static final String IVAN = "Ivan";
    private static final String IVANOV = "Ivanov";

    private static final Gender MALE = Gender.MALE;
    private static final GenderDto MALE_DTO = GenderDto.MALE;
    private static PersonDto personDto;
    private static Person person;

    private static final Mapper<Person, PersonDto> mapperPerson
            = new MapperPerson();


    @BeforeAll
    public static void init() {
        personDto = PersonDto.builder()
                .id(ID)
                .name(IVAN)
                .surname(IVANOV)
                .gender(MALE_DTO)
                .build();

        person = Person.builder()
                .id(ID)
                .name(IVAN)
                .surname(IVANOV)
                .gender(MALE)
                .build();
    }
    @Test
    void isShouldConvertDtoToEntity() {
        Person personExp = mapperPerson.dtoToEntity(personDto);

        assertAll(
                () -> assertEquals(personDto.getId(),personExp.getId()),
                () -> assertEquals(personDto.getName(),personExp.getName()),
                () -> assertEquals(personDto.getSurname(),personExp.getSurname()),
                () -> assertEquals(personDto.getGender().name(),personExp.getGender().name())
        );
    }

    @Test
    void isShouldConvertNullDtoToEntity() {
        PersonDto personDtoNull = null;
        Person person = mapperPerson.dtoToEntity(personDtoNull);
        assertNull(person);
    }

    @Test
    void isShouldConvertEntityToDto() {
        PersonDto personDtoExp = mapperPerson.entityToDto(person);

        assertAll(
                () -> assertEquals(personDtoExp.getId(),person.getId()),
                () -> assertEquals(personDtoExp.getName(),person.getName()),
                () -> assertEquals(personDtoExp.getSurname(),person.getSurname()),
                () -> assertEquals(personDtoExp.getGender().name(),person.getGender().name())
        );
    }

    @Test
    void isShouldConvertNullEntityToDto() {
        Person personNull = null;
        PersonDto person = mapperPerson.entityToDto(personNull);
        assertNull(person);
    }

}