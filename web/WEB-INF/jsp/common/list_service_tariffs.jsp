<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jspf/taglib.jspf"%>

<fmt:message key="list_service_tariffs.title" var="title"/>
<c:set var="title" value="${title}" scope="page" />

<html>
<head>
<title>${title}</title>
    <link rel="stylesheet" type="text/css" media="screen" href="style/list_service_tariffs.css"/>
</head>

<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>

<c:if test="${userRole.name == 'admin' }">
    <%@ include file="/WEB-INF/jspf/admin_menu.jspf"%>
</c:if>
<c:if test="${userRole.name == 'subscriber' }">
    <%@ include file="/WEB-INF/jspf/user_menu.jspf"%>
</c:if>

<table id="content-table3" class="table-responsive-full sort-table">
    <thead>
    <tr>
        <th>№</th>
        <th class="tariff"><fmt:message key="list_service_tariffs.tariff"/>
            <form action="controller">
                <input type="hidden" name="command" value="listServiceTariffs" /> <input
                    type="hidden" name="sort" value="nameZA" />
                <button type="submit" class="button" style="font-size: 15px"><i class="fa fa-chevron-up"></i><fmt:message key="list_service_tariffs.sort.name.ZA"/></button>
            </form>
            <form action="controller">
                <input type="hidden" name="command" value="listServiceTariffs" /> <input
                    type="hidden" name="sort" value="nameAZ" />
                <button type="submit" class="button" style="font-size: 15px"><i class="fa fa-chevron-down"></i><fmt:message key="list_service_tariffs.sort.name.AZ"/></button>
            </form>
        </th>
        <th class="service"><fmt:message key="list_service_tariffs.service"/>
            <div class="sort-table-arrows">
                <form action="controller">
                    <input type="hidden" name="command" value="listServiceTariffs" /> <input
                        type="hidden" name="sort" value="nameZA" />
                    <button type="submit" class="button" style="font-size: 15px"><i class="fa fa-chevron-up"></i><fmt:message key="list_service_tariffs.sort.name.ZA"/></button>
                </form>
                <form action="controller">
                    <input type="hidden" name="command" value="listServiceTariffs" /> <input
                        type="hidden" name="sort" value="nameAZ" />
                    <button type="submit" class="button" style="font-size: 15px"><i class="fa fa-chevron-down"></i><fmt:message key="list_service_tariffs.sort.name.AZ"/></button>
                </form>
            </div>
        </th>
        <th class="price"><fmt:message key="list_service_tariffs.price"/>
            <div class="sort-table-arrows">
                <form action="controller">
                    <input type="hidden" name="command" value="listServiceTariffs" /> <input
                        type="hidden" name="sort" value="price" />
                    <button type="submit" class="button"><i class="fa fa-sort"></i><fmt:message key="list_service_tariffs.sort.price"/></button>
                </form>
            </div>
        </th>
        <th class="description"><fmt:message key="list_service_tariffs.description"/></th>
    </tr>
    </thead>
    <tbody>
        <c:set var="k" value="0" />
        <c:forEach var="item" items="${tariffServices}">
        <c:set var="k" value="${k+1}" />
    <tr>
            <td><c:out value="${k}" /></td>
            <td>${item.tariff.name}</td>
            <td>${item.service.name}</td>
            <td>${item.price}</td>
            <td>${item.description}</td>
    </tr>
        </c:forEach>
    </tbody>
</table>

<c:if test="${userRole.name == 'subscriber' }">
    <form action="controller">
        <input type="hidden" name="command" value="downloadPDF" />
        <button type="submit" class="tableSubmit1"><fmt:message key="list_service_tariffs.download"/></button>
    </form>
</c:if>
</body>
</html>