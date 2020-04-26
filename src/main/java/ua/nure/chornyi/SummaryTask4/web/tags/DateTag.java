package ua.nure.chornyi.SummaryTask4.web.tags;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Tag to get the current time and date
 */
public class DateTag extends SimpleTagSupport {

    private static final String DATE_FORMAT_RU = "dd.MM.yyyy    HH:mm";
    private static final String DATE_FORMAT_EN = "MM/dd/yyyy    HH:mm";

    @Override
    public void doTag() throws IOException {
        JspWriter jspWriter = getJspContext().getOut();

        Object lang = getJspContext().getAttribute("language", PageContext.SESSION_SCOPE);

        if ("en".equals(lang)) {
            jspWriter.print(new SimpleDateFormat(DATE_FORMAT_EN).format(Calendar.getInstance().getTime()));
        } else {
            jspWriter.print(new SimpleDateFormat(DATE_FORMAT_RU).format(Calendar.getInstance().getTime()));
        }
    }
}
