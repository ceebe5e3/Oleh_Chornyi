package ua.nure.chornyi.SummaryTask4.web.command.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import ua.nure.chornyi.SummaryTask4.db.dao.TariffDaoImpl;
import ua.nure.chornyi.SummaryTask4.web.service.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class InsertTariffCommandTest {
     static InsertTariffCommand insertTariffCommand;

    @BeforeClass
    public static void setUpBeforeClass(){
        insertTariffCommand = new InsertTariffCommand(new TariffServiceImpl(new TariffDaoImpl()));
    }

    @Test
    public void execute() {
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        InsertTariffCommand insertTariffCommand1 = Mockito.mock(InsertTariffCommand.class);
        insertTariffCommand1.execute(request, response);
    }

}