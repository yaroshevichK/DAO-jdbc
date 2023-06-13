package it.academy.servlet.command;

import it.academy.dto.PersonDto;
import it.academy.service.IService;
import it.academy.service.ServicePerson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static it.academy.util.DataUI.PARAM_PEOPLE;
import static it.academy.util.DataUI.PEOPLE_PATH;

/**
 * @author Katerina
 * @version 1.0
 */
public class DeleteAllCommand implements Command {
    /**
     * Service for request in database layer.
     */
    private final IService<PersonDto> servicePerson = new ServicePerson();

    /**
     * Delete all people in database.
     *
     * @param req  servlet request
     * @param resp servlet response
     * @return request page
     */
    @Override
    public String execute(final HttpServletRequest req,
                          final HttpServletResponse resp) {
        servicePerson.deleteAllPeople();

        List<PersonDto> allPeople = servicePerson.getAllPeople();
        req.setAttribute(PARAM_PEOPLE, allPeople);

        return PEOPLE_PATH;
    }
}
