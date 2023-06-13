package it.academy.mapper;

import it.academy.dto.GenderDto;
import it.academy.entity.Gender;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MapperGenderTest {
    Mapper<Gender, GenderDto> mapperGender = new MapperGender();
    public static final Gender MALE = Gender.MALE;
    public static final GenderDto MALE_DTO = GenderDto.MALE;
    public static final Gender FEMALE = Gender.FEMALE;
    public static final GenderDto FEMALE_DTO = GenderDto.FEMALE;
    public static final GenderDto GENDER_DTO_NULL = null;
    public static final Gender GENDER_NULL = null;

    @Test
    void isShouldConvertDtoToEntity() {
        Gender genderMale = mapperGender.dtoToEntity(MALE_DTO);
        Gender genderFemale = mapperGender.dtoToEntity(FEMALE_DTO);
        Gender genderNull = mapperGender.dtoToEntity(GENDER_DTO_NULL);

        assertAll(
                () -> assertEquals(genderMale, MALE),
                () -> assertEquals(genderFemale, FEMALE),
                () -> assertNull(genderNull)
        );
    }

    @Test
    void isShouldConvertEntityToDto() {
        GenderDto genderDtoMale = mapperGender.entityToDto(MALE);
        GenderDto genderDtoFemale = mapperGender.entityToDto(FEMALE);
        GenderDto genderDtoNull = mapperGender.entityToDto(GENDER_NULL);

        assertAll(
                () -> assertEquals(genderDtoMale, MALE_DTO),
                () -> assertEquals(genderDtoFemale, FEMALE_DTO),
                () -> assertNull(genderDtoNull)
        );
    }
}