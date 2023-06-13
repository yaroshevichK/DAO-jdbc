package it.academy.mapper;

import it.academy.dto.GenderDto;
import it.academy.dto.PersonDto;
import it.academy.entity.Gender;
import it.academy.entity.Person;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static it.academy.util.DataUI.PARAM_GENDER;
import static it.academy.util.DataUI.PARAM_ID;
import static it.academy.util.DataUI.PARAM_NAME;
import static it.academy.util.DataUI.PARAM_SURNAME;

/**
 * @author Katerina
 * @version 1.0
 */
public class MapperPerson implements Mapper<Person, PersonDto> {
    /**
     * Mapper for gender.
     */
    private final Mapper<Gender, GenderDto> mapperGender = new MapperGender();

    /**
     * Convert PersonDto object in Person object.
     *
     * @param personDto object in client layer
     * @return Person object or null if not found value
     */
    @Override
    public Person dtoToEntity(final PersonDto personDto) {
        return Optional
                .ofNullable(personDto)
                .map(person -> Person
                        .builder()
                        .id(person.getId())
                        .name(person.getName())
                        .surname(person.getSurname())
                        .gender(mapperGender.dtoToEntity(personDto.getGender()))
                        .build())
                .orElse(null);
    }

    /**
     * Convert Person object in PersonDto object.
     *
     * @param person object in database layer
     * @return PersonDto object (client layer) or null if not found
     */
    @Override
    public PersonDto entityToDto(final Person person) {
        return Optional
                .ofNullable(person)
                .map(personDto -> PersonDto
                        .builder()
                        .id(personDto.getId())
                        .name(personDto.getName())
                        .surname(personDto.getSurname())
                        .gender(mapperGender.entityToDto(person.getGender()))
                        .build())
                .orElse(null);
    }

    /**
     * Convert request parameters in PersonDto object.
     *
     * @param request servlet request
     *                PersonDto object (client layer) or null if not found
     */
    @Override
    public PersonDto requestToDto(final HttpServletRequest request) {
        String idStr = request.getParameter(PARAM_ID);

        GenderDto gender = Optional
                .ofNullable(request.getParameter(PARAM_GENDER))
                .map(value -> StringUtils
                        .isBlank(value) ? null : GenderDto.valueOf(value))
                .orElse(null);

        Integer id = Optional.ofNullable(idStr)
                .map(Integer::valueOf)
                .orElse(null);

        return PersonDto.builder()
                .id(id)
                .name(request.getParameter(PARAM_NAME))
                .surname(request.getParameter(PARAM_SURNAME))
                .gender(gender)
                .build();
    }
}
