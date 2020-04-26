package ua.nure.chornyi.SummaryTask4.web.command;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import ua.nure.chornyi.SummaryTask4.web.command.common.LogoutCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandTest {
    static Command command;

    @BeforeClass
    public static void setUpBeforeClass() {
        command = new LogoutCommand();
    }

    @Test
    public void execute() throws IOException, ServletException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        command.execute(request, response);
    }

    @Test
    public void testToString() {
        System.out.println(command);
    }
}