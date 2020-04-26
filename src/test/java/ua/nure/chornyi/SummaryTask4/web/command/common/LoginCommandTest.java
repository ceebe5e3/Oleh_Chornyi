package ua.nure.chornyi.SummaryTask4.web.command.common;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import ua.nure.chornyi.SummaryTask4.db.dao.AccountDaoImpl;
import ua.nure.chornyi.SummaryTask4.db.dao.UserDaoImpl;
import ua.nure.chornyi.SummaryTask4.web.service.AccountServiceImpl;
import ua.nure.chornyi.SummaryTask4.web.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommandTest {

    static LoginCommand LoginCommand;

    @BeforeClass
    public static void setUpBeforeClass(){
        LoginCommand = new LoginCommand(new UserServiceImpl(new UserDaoImpl()), new AccountServiceImpl(new AccountDaoImpl()));
    }

    @Test
    public void execute() {
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        LoginCommand loginCommand1 = Mockito.mock(LoginCommand.class);
        loginCommand1.execute(request, response);
    }
}