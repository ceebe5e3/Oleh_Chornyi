<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jspf/taglib.jspf"%>

<html>
    <fmt:message key="make_order.title" var="title"/>
    <c:set var="title" value="${title}" scope="page" />
    <head>
        <title>${title}</title>
        <link rel="stylesheet" type="text/css" media="screen" href="style/make_order.css"/>
    </head>
    <body>

    <%@ include file="/WEB-INF/jspf/header.jspf"%>
    <%@ include file="/WEB-INF/jspf/user_menu.jspf"%>
    
    <c:if test="${block eq 'BLOCKED'}">
        <h1><fmt:message key="message.contact"/></h1>
    </c:if>

    <c:if test="${block eq 'UNBLOCKED' }">
    <form action="controller">
        <input type="hidden" name="command" value="makeOrder" />

    <table id="content-table3" class="table-responsive-full sort-table">
        <thead>
        <tr>
            <th style="width: 1%;">№</th>
            <th><fmt:message key="make_order.tariff"/></th>
            <th style="width: 5%;"><input class="tableSubmit" type="submit" value="<fmt:message key="make_order.submit.select"/>"></th>
        </tr>
        </thead>
        <tbody>
        <c:set var="k" value="0" />
        <c:forEach var="tariff" items="${tariffs}">
            <c:set var="k" value="${k+1}" />
            <tr>
                <td style="width: 1%;"><c:out value="${k}" /></td>
                <td>${tariff.name}</td>
                <td style="width: 5%;"><input class="checkBox1" type="radio" name="tariffID" value="${tariff.id }"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </form>

    <c:if test="${not empty serviceTariffs}">
        <form class="make_order" action="controller" method="post">
        <input type="hidden" name="command" value="makeOrder" />
    <table id="content-table3" class="table-responsive-full sort-table">
        <thead>
        <tr>
            <th style="width: 1%;">№</th>
            <th><fmt:message key="make_order.service"/></th>
            <th><fmt:message key="make_order.price"/></th>
            <th><fmt:message key="make_order.description"/></th>
            <th style="width: 5%;"><input class="tableSubmit" type="submit" value="<fmt:message key="make_order.submit.order"/>"></th>
        </tr>
        </thead>
        <tbody>
        <c:set var="k" value="0" />
        <c:forEach var="item" items="${serviceTariffs}">
            <c:set var="k" value="${k+1}" />
            <tr>
                <td style="width: 1%;"><c:out value="${k}" /></td>
                <td>${item.service.name}</td>
                <td>${item.price}</td>
                <td>${item.description}</td>
                <td style="width: 5%;"><input class="checkBox1" type="checkbox" name="serviceID" value="${item.service.id}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
        </form>
    </c:if>
    </body>
    </c:if>
</html>