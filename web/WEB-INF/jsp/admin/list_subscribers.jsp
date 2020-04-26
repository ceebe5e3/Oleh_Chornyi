<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jspf/taglib.jspf"%>

<html>
    <fmt:message key="list_subscribers.title" var="title"/>
    <c:set var="title" value="${title}" scope="page" />

    <head>
        <title>${title}</title>
        <link rel="stylesheet" type="text/css" media="screen" href="style/list_subscribers.css"/>
    </head>

<body>
    <%@ include file="/WEB-INF/jspf/header.jspf"%>
    <%@ include file="/WEB-INF/jspf/admin_menu.jspf"%>

    <section>
        <table class="table-fill">
            <thead>
            <tr>
                <th class="text-center1">№</th>
                <th class="text-center1"><fmt:message key="list_subscribers.full_name"/></th>
                <th class="text-center1"><fmt:message key="list_subscribers.date_of_birth"/></th>
                <th class="text-center1"><fmt:message key="list_subscribers.address"/></th>
                <th class="text-center1"><fmt:message key="list_subscribers.email"/></th>
                <th class="text-center1"><fmt:message key="list_subscribers.login"/></th>
                <th class="text-center1"><fmt:message key="list_subscribers.password"/></th>
                <th class="text-center1"></th>
                <th class="text-center1"></th>
                <th class="text-center1"></th>
                <th class="text-center1"></th>
            </tr>
            </thead>
            <tbody class="table-hover">
            <c:set var="k" value="0" />
            <c:forEach var="item" items="${subscribers}">
            <c:set var="k" value="${k+1}" />
            <tr>
                <td><c:out value="${k}" /></td>
                <td>${item.fullName}</td>
                <td>${item.dateOfBirth}</td>
                <td>${item.address}</td>
                <td>${item.email}</td>
                <td>${item.login}</td>
                <td class="hidetext">${item.password}</td>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="block" />
                    <td><input class="checkBox" type="checkbox" onclick="myFunction('block')" name="block" value="${item.id}" /></td>
                    <td><input id="block" type="submit" value="<fmt:message key="list_subscribers.submit.block"/>"></td>
                </form>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="unblock" />
                    <td><input class="checkBox" type="checkbox" onclick="myFunction('unblock')" name="unblock" value="${item.id}" /></td>
                    <td><input id="unblock" type="submit" value="<fmt:message key="list_subscribers.submit.unblock"/>"></td>
                </form>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>

    <script type="text/javascript">
        function myFunction(s){
            var input = document.getElementsByTagName("input");
            for (i = 0; i < (input.length + 0); i++) {
                if (input[i].getAttribute("name") == s) {
                    continue;
                } else {
                    input[i].checked = false;
                }
            }
        }
    </script>
</body>
</html>
