package it.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Katerina
 * @version 1.0
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto implements Serializable {
    /**
     * Primary key field.
     */
    private Integer id;

    /**
     * Name person.
     */
    private String name;

    /**
     * Surname person.
     */
    private String surname;

    /**
     * Gender person.
     */
    private GenderDto gender;
}
