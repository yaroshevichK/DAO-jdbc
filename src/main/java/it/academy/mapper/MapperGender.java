package it.academy.mapper;

import it.academy.dto.GenderDto;
import it.academy.entity.Gender;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static it.academy.util.DataUI.PARAM_GENDER;

/**
 * @author Katerina
 * @version 1.0
 */
public class MapperGender implements Mapper<Gender, GenderDto> {
    /**
     * Convert GenderDto object in Gender object.
     *
     * @param genderDto object in client layer
     * @return Gender object or null if not found value
     */
    @Override
    public Gender dtoToEntity(final GenderDto genderDto) {
        if (GenderDto.MALE.equals(genderDto)) {
            return Gender.MALE;
        } else if (GenderDto.FEMALE.equals(genderDto)) {
            return Gender.FEMALE;
        }
        return null;
    }

    /**
     * Convert Gender object in genderDto object.
     *
     * @param gender object in database layer
     * @return GenderDto object (client layer) or null if not found
     */
    @Override
    public GenderDto entityToDto(final Gender gender) {
        if (Gender.MALE.equals(gender)) {
            return GenderDto.MALE;
        } else if (Gender.FEMALE.equals(gender)) {
            return GenderDto.FEMALE;
        }
        return null;
    }

    /**
     * Convert request parameters in genderDto object.
     *
     * @param request servlet request
     *                GenderDto object (client layer) or null if not found
     */
    @Override
    public GenderDto requestToDto(final HttpServletRequest request) {
        return Optional
                .ofNullable(request.getParameter(PARAM_GENDER))
                .map(gender -> StringUtils
                        .isBlank(gender) ? null : GenderDto.valueOf(gender))
                .orElse(null);
    }
}
