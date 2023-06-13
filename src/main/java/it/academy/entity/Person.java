package it.academy.entity;

import it.academy.anno.Column;
import it.academy.anno.Id;
import it.academy.anno.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static it.academy.util.DataDB.DB_COLUMN_GENDER;
import static it.academy.util.DataDB.DB_COLUMN_ID;
import static it.academy.util.DataDB.DB_COLUMN_NAME;
import static it.academy.util.DataDB.DB_COLUMN_SURNAME;
import static it.academy.util.DataDB.DB_TABLE_PERSON;

/**
 * @author Katerina
 * @version 1.0
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = DB_TABLE_PERSON)
public class Person implements Serializable {
    /**
     * Primary key field.
     */
    @Id(name = DB_COLUMN_ID)
    private Integer id;

    /**
     * Name person.
     */
    @Column(name = DB_COLUMN_NAME)
    private String name;

    /**
     * Surname person.
     */
    @Column(name = DB_COLUMN_SURNAME)
    private String surname;

    /**
     * Gender person.
     */
    @Column(name = DB_COLUMN_GENDER)
    private Gender gender;
}
