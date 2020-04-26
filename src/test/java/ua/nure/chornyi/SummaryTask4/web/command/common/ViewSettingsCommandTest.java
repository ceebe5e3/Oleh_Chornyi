package ua.nure.chornyi.SummaryTask4.web.command.common;


import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewSettingsCommandTest {

    static ViewSettingsCommand viewSettingsCommand;

    @BeforeClass
    public static void setUpBeforeClass(){
        viewSettingsCommand = new ViewSettingsCommand();
    }

    @Test
    public void execute() {
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        ViewSettingsCommand viewSettingsCommand1 = Mockito.mock(ViewSettingsCommand.class);
        viewSettingsCommand1.execute(request, response);
    }
}