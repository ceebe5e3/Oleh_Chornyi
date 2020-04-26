package ua.nure.chornyi.SummaryTask4;

public class Path {

    // pages
    public static final String PAGE_LOGIN = "login.jsp";
    public static final String ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
    public static final String PAGE_LIST_SERVICE_TARIFFS_EDIT = "/WEB-INF/jsp/admin/list_tariffs_edit.jsp";
    public static final String PAGE_LIST_SERVICE_TARIFFS_DELETE = "/WEB-INF/jsp/admin/list_tariffs_delete.jsp";
    public static final String PAGE_LIST_TARIFFS_SERVICES = "/WEB-INF/jsp/common/list_service_tariffs.jsp";
    public static final String PAGE_SETTINGS = "/WEB-INF/jsp/settings.jsp";
    public static final String PAGE_ADD_SUBSCRIBER = "/WEB-INF/jsp/admin/add_subscriber.jsp";
    public static final String PAGE_LIST_SUBSCRIBERS = "/WEB-INF/jsp/admin/list_subscribers.jsp";
    public static final String PAGE_ACCOUNT = "/WEB-INF/jsp/subscriber/account.jsp";
    public static final String PAGE_INSERT_TARIFF = "WEB-INF/jsp/admin/insert_tariff.jsp";
    public static final String PAGE_EDITING_TARIFF = "/WEB-INF/jsp/admin/edit_tariff.jsp";
    public static final String PAGE_MAKE_ORDER = "/WEB-INF/jsp/subscriber/make_order.jsp";
    public static final String PAGE_VIEW_ADMIN = "/WEB-INF/jsp/admin/contracts_admin.jsp";

    // commands
    public static final String COMMAND_LIST_SERVICE_TARIFFS = "controller?command=listServiceTariffs";
    public static final String COMMAND_ADD_SUBSCRIBER_FORM = "controller?command=addSubscriberForm";
    public static final String COMMAND_LIST_SUBSCRIBERS = "controller?command=listSubscribers";
    public static final String COMMAND_INSERT_TARIFF_FORM = "controller?command=insertTariffForm";
    public static final String COMMAND_VIEW_ACCOUNT = "controller?command=viewAccount";
    public static final String COMMAND_VIEW_SETTINGS = "controller?command=viewSettings";
}
