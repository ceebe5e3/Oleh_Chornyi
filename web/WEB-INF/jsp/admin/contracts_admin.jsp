<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jspf/taglib.jspf"%>
<%@ taglib prefix="subscriber" tagdir="/WEB-INF/tags"%>

<html>
<fmt:message key="account.title" var="title"/>
<c:set var="title" value="${title}" scope="page" />

<head>
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" media="screen" href="style/list_service_tariffs.css"/>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<%@ include file="/WEB-INF/jspf/admin_menu.jspf"%>

<table id="content-table3" class="table-responsive-full sort-table">
    <thead>
    <tr>
        <th><fmt:message key="settings.login"/></th>
        <th><fmt:message key="account.tariff"/></th>
        <th><fmt:message key="account.service"/></th>
        <th><fmt:message key="account.price"/></th>
        <th><fmt:message key="account.description"/></th>
        <th><fmt:message key="account.date"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="contractAdmin" items="${contracts}">
        <tr>
            <subscriber:contracts_admin contractAdmin="${contractAdmin}"/>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>