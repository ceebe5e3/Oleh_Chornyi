<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jspf/taglib.jspf"%>

<html>
    <fmt:message key="list_tariffs_delete.title" var="title"/>
    <c:set var="title" value="${title}" scope="page" />

    <head>
        <title>${title}</title>
        <link rel="stylesheet" type="text/css" media="screen" href="style/list_tariffs_edit-delete.css"/>
    </head>

<body>
    <%@ include file="/WEB-INF/jspf/header.jspf"%>
    <%@ include file="/WEB-INF/jspf/admin_menu.jspf"%>

    <form action="controller">
        <input type="hidden" name="command" value="editTariffForm" />
        <table id="content-table3" class="table-responsive-full sort-table">
            <thead>
            <tr>
                <th style="width: 1%;">№</th>
                <th><fmt:message key="list_tariffs_edit.tariff"/></th>
                <th><fmt:message key="list_tariffs_edit.service"/></th>
                <th><fmt:message key="list_tariffs_edit.price"/></th>
                <th><fmt:message key="list_tariffs_edit.description"/></th>
                <th style="width: 5%;"><input class="tableSubmit" type="submit" value="<fmt:message key="list_tariffs_edit.submit.edit"/>"></th>
            </tr>
            </thead>
            <tbody>
            <c:set var="k" value="0" />
            <c:forEach var="item" items="${tariffServices}">
                <c:set var="k" value="${k+1}" />
                <tr>
                    <td style="width: 1%;"><c:out value="${k}" /></td>
                    <td>${item.tariff.name}</td>
                    <td>${item.service.name}</td>
                    <td>${item.price}</td>
                    <td>${item.description}</td>
                    <td style="width: 5%;"><input type="radio" name="editItemId" value="${item.id}" /></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</body>
</html>