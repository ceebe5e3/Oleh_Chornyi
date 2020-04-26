package ua.nure.chornyi.SummaryTask4.web.command.subscriber;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import ua.nure.chornyi.SummaryTask4.db.dao.AccountDaoImpl;
import ua.nure.chornyi.SummaryTask4.db.dao.ContractDaoImpl;
import ua.nure.chornyi.SummaryTask4.web.service.AccountServiceImpl;
import ua.nure.chornyi.SummaryTask4.web.service.ContractServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;

public class ViewAccountCommandTest {

    static ViewAccountCommand viewAccountCommand;

    @BeforeClass
    public static void setUpBeforeClass(){
        viewAccountCommand = new ViewAccountCommand(new AccountServiceImpl(new AccountDaoImpl()),new ContractServiceImpl(new ContractDaoImpl()));
    }

    @Test
    public void execute() {
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        ViewAccountCommand viewAccountCommand1 = Mockito.mock(ViewAccountCommand.class);
        viewAccountCommand1.execute(request, response);
    }
}