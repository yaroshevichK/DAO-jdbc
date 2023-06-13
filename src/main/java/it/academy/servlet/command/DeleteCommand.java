package it.academy.servlet.command;

import it.academy.dto.PersonDto;
import it.academy.service.IService;
import it.academy.service.ServicePerson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static it.academy.util.DataUI.PARAM_ID;
import static it.academy.util.DataUI.PARAM_PEOPLE;
import static it.academy.util.DataUI.PEOPLE_PATH;

/**
 * @author Katerina
 * @version 1.0
 */
public class DeleteCommand implements Command {
    /**
     * Service for request in database layer.
     */
    private final IService<PersonDto> servicePerson = new ServicePerson();

    /**
     * Delete person in database.
     *
     * @param req  servlet request
     * @param resp servlet response
     * @return request page
     */
    @Override
    public String execute(final HttpServletRequest req,
                          final HttpServletResponse resp) {
        Optional.ofNullable(req.getParameter(PARAM_ID))
                .map(Integer::valueOf)
                .ifPresent(servicePerson::deletePerson);

        List<PersonDto> allPeople = servicePerson.getAllPeople();
        req.setAttribute(PARAM_PEOPLE, allPeople);

        return PEOPLE_PATH;
    }
}
