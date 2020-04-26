package ua.nure.chornyi.SummaryTask4.web.command.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import ua.nure.chornyi.SummaryTask4.db.dao.UserDaoImpl;
import ua.nure.chornyi.SummaryTask4.web.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;

public class ListSubscribersCommandTest {

    static ListSubscribersCommand listSubscribersCommand;

    @BeforeClass
    public static void setUpBeforeClass(){
        listSubscribersCommand = new ListSubscribersCommand(new UserServiceImpl(new UserDaoImpl()));
    }

    @Test
    public void execute() {
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        ListSubscribersCommand listSubscribersCommand1 = Mockito.mock(ListSubscribersCommand.class);
        listSubscribersCommand1.execute(request, response);
    }
}