package ua.nure.chornyi.SummaryTask4.web.command.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import ua.nure.chornyi.SummaryTask4.db.dao.UserDaoImpl;
import ua.nure.chornyi.SummaryTask4.web.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddSubscriberCommandTest {

    static AddSubscriberCommand addSubscriberCommand;

    @BeforeClass
    public static void setUpBeforeClass(){
        addSubscriberCommand = new AddSubscriberCommand(new UserServiceImpl(new UserDaoImpl()));
    }

    @Test
    public void execute() {
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        AddSubscriberCommand addSubscriberCommand1 = Mockito.mock(AddSubscriberCommand.class);
        addSubscriberCommand1.execute(request, response);
    }
}