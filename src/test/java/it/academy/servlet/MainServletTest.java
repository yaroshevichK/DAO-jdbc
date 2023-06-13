package it.academy.servlet;

import it.academy.servlet.command.Command;
import it.academy.servlet.command.DeleteCommand;
import it.academy.servlet.command.GetAllCommand;
import it.academy.servlet.command.Receiver;
import it.academy.servlet.command.SaveCommand;
import it.academy.servlet.command.UpdateCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class MainServletTest {
    public static final String GET_ALL = "getAll";
    public static final String SAVE = "save";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";
    public static Receiver receiver = Receiver.getInstance();

    private static Stream<Arguments> DataTest() {
        return Stream.of(
                Arguments.of(GET_ALL, new GetAllCommand()),
                Arguments.of(SAVE, new SaveCommand()),
                Arguments.of(UPDATE, new UpdateCommand()),
                Arguments.of(DELETE, new DeleteCommand())
        );
    }

    @ParameterizedTest
    @MethodSource("DataTest")
    void isShouldCreateCommandTypeWhichSendInStringInput(String input, Command command) {
        Command actualCommand = receiver.getCommand(input);
        Assertions.assertEquals(command.getClass(),actualCommand.getClass());
    }

    @Test
    void isShouldCreateNotNullReceiver() {
        receiver = Receiver.getInstance();
        assertNotNull(receiver);
    }

    @Test
    void isShouldRunExecute() throws ServletException, IOException {
        MainServlet servlet = mock(MainServlet.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        servlet.doGet(request,response);
        servlet.doPost(request,response);
        servlet.process(request,response);
        verify(servlet,times(1)).doGet(request,response);
        verify(servlet,times(1)).doPost(request,response);
        verify(servlet,times(1)).process(request,response);
    }

}