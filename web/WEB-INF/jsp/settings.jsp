<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jspf/taglib.jspf"%>

<html>
<fmt:message key="settings.title" var="title"/>
<c:set var="title" value="${title}" scope="page" />

<head>
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" media="screen" href="style/add_subscriber.css"/>
</head>
<body>

<c:if test="${not empty param.error}">
    <script type="text/javascript">
        window.alert("INVALID DATA! PLEASE CHECK DATA!")
    </script>
</c:if>

<%@ include file="/WEB-INF/jspf/header.jspf"%>

<c:if test="${userRole.name == 'admin' }">
    <%@ include file="/WEB-INF/jspf/admin_menu.jspf"%>
</c:if>
<c:if test="${userRole.name == 'subscriber' }">
    <%@ include file="/WEB-INF/jspf/user_menu.jspf"%>
</c:if>
<div class="form">
<form action="controller" method="post">
    <input type="hidden" name="command" value="updateSettings" />

    <input name="login" type="text" value="${login}" required="required" placeholder="<fmt:message key="settings.login"/>">

    <input name="password" type="text" value="${password}" required="required" placeholder="<fmt:message key="settings.password"/>" />

    <select name="language">
        <c:set var="k" value="0" />
        <c:forEach var="item" items="${locales}">
            <c:set var="k" value="${k+1}" />
            <option value="${item}">${item}</option>
        </c:forEach>
    </select>
    <input type="submit" value="<fmt:message key="settings.submit.change"/>" />
</form>
</div>
</body>
</html>
