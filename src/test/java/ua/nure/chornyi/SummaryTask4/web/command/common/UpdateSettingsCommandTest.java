package ua.nure.chornyi.SummaryTask4.web.command.common;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import ua.nure.chornyi.SummaryTask4.db.dao.UserDaoImpl;
import ua.nure.chornyi.SummaryTask4.web.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateSettingsCommandTest {
    static UpdateSettingsCommand updateSettingsCommand;

    @BeforeClass
    public static void setUpBeforeClass(){
        updateSettingsCommand = new UpdateSettingsCommand(new UserServiceImpl(new UserDaoImpl()));
    }

    @Test
    public void execute() {
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        UpdateSettingsCommand updateSettingsCommand1 = Mockito.mock(UpdateSettingsCommand.class);
        updateSettingsCommand1.execute(request, response);
    }

}