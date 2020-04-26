package ua.nure.chornyi.SummaryTask4.web.command.subscriber;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import ua.nure.chornyi.SummaryTask4.db.dao.AccountDaoImpl;
import ua.nure.chornyi.SummaryTask4.web.service.AccountServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountPaymentCommandTest {
    static AccountPaymentCommand accountPaymentCommand;

    @BeforeClass
    public static void setUpBeforeClass(){
        accountPaymentCommand = new AccountPaymentCommand(new AccountServiceImpl(new AccountDaoImpl()));
    }

    @Test
    public void execute() {
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        AccountPaymentCommand accountPaymentCommand1 = Mockito.mock(AccountPaymentCommand.class);
        accountPaymentCommand1.execute(request, response);
    }

}