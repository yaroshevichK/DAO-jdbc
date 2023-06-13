package it.academy.servlet.command;

import it.academy.dto.PersonDto;
import it.academy.entity.Person;
import it.academy.mapper.Mapper;
import it.academy.mapper.MapperPerson;
import it.academy.service.IService;
import it.academy.service.ServicePerson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static it.academy.util.DataUI.ERROR_PATH;
import static it.academy.util.DataUI.GET;
import static it.academy.util.DataUI.PARAM_PEOPLE;
import static it.academy.util.DataUI.PEOPLE_PATH;
import static it.academy.util.DataUI.POST;
import static it.academy.util.DataUI.SAVE_PERSON_PATH;

/**
 * @author Katerina
 * @version 1.0
 */
public class SaveCommand implements Command {
    /**
     * Service for request in database layer.
     */
    private final IService<PersonDto> servicePerson = new ServicePerson();
    /**
     * Converter.
     */
    private final Mapper<Person, PersonDto> mapperPerson = new MapperPerson();

    /**
     * Save person in database.
     *
     * @param req  servlet request
     * @param resp servlet response
     * @return request page
     */
    @Override
    public String execute(final HttpServletRequest req,
                          final HttpServletResponse resp) {
        if (GET.equals(req.getMethod())) {
            return SAVE_PERSON_PATH;
        } else if (POST.equals(req.getMethod())) {
            PersonDto person = mapperPerson.requestToDto(req);
            servicePerson.savePerson(person);

            List<PersonDto> allPeople = servicePerson.getAllPeople();
            req.setAttribute(PARAM_PEOPLE, allPeople);

            return PEOPLE_PATH;
        } else {
            return ERROR_PATH;
        }
    }
}
