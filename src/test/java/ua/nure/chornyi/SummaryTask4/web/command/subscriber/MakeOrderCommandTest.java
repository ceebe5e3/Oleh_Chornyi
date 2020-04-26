package ua.nure.chornyi.SummaryTask4.web.command.subscriber;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import ua.nure.chornyi.SummaryTask4.db.dao.ContractDaoImpl;
import ua.nure.chornyi.SummaryTask4.db.dao.ServiceTariffsDaoImpl;
import ua.nure.chornyi.SummaryTask4.db.dao.TariffDaoImpl;
import ua.nure.chornyi.SummaryTask4.web.service.ContractServiceImpl;
import ua.nure.chornyi.SummaryTask4.web.service.ServiceTariffsServiceImpl;
import ua.nure.chornyi.SummaryTask4.web.service.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;

public class MakeOrderCommandTest {

    static MakeOrderCommand makeOrderCommand;

    @BeforeClass
    public static void setUpBeforeClass(){
        makeOrderCommand = new MakeOrderCommand(new TariffServiceImpl(new TariffDaoImpl()), new ServiceTariffsServiceImpl(new ServiceTariffsDaoImpl()), new ContractServiceImpl(new ContractDaoImpl()));
    }

    @Test
    public void execute() {
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        MakeOrderCommand makeOrderCommand1 = Mockito.mock(MakeOrderCommand.class);
        makeOrderCommand1.execute(request, response);
    }
}