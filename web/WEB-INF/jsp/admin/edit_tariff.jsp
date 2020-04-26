<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jspf/taglib.jspf"%>

<html>
    <fmt:message key="edit_tariff.title" var="title"/>
    <c:set var="title" value="${title}" scope="page" />

    <head>
        <title>${title}</title>
        <link rel="stylesheet" type="text/css" media="screen" href="style/add_subscriber.css"/>
    </head>

<body>
    <%@ include file="/WEB-INF/jspf/header.jspf"%>
    <%@ include file="/WEB-INF/jspf/admin_menu.jspf"%>

    <div class="form">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="editTariff" />
        <input type="hidden" name="serviceTariffId" value="${id}" />

        <input name="tariffName" value="${tariffName}" type="text" required="required" placeholder="<fmt:message key="edit_tariff.name"/>"/>

        <input name="price" value="${price}" type="text" required="required" placeholder="<fmt:message key="edit_tariff.price"/>"/>

        <input name="description" value="${description}" type="text" required="required" placeholder="<fmt:message key="edit_tariff.description"/>"/>

        <input type="submit" value="<fmt:message key="edit_tariff.submit.edit"/>" />

    </form>
    </div>
</body>
</html>
