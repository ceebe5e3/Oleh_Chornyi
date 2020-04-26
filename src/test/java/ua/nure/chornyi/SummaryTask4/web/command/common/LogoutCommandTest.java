package ua.nure.chornyi.SummaryTask4.web.command.common;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommandTest {

    static LogoutCommand logoutCommand;

    @BeforeClass
    public static void setUpBeforeClass(){
        logoutCommand = new LogoutCommand();
    }

    @Test
    public void execute() {
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        LogoutCommand logoutCommand1 = Mockito.mock(LogoutCommand.class);
        logoutCommand1.execute(request, response);
    }
}