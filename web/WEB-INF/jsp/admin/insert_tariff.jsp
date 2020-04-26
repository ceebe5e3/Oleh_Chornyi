<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jspf/taglib.jspf"%>

<html>
<fmt:message key="insert_tariff.title" var="title"/>
<c:set var="title" value="${title}" scope="page" />

<head>
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" media="screen" href="style/add_subscriber.css"/>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>

<c:if test="${not empty param.error}">
    <script type="text/javascript">
        window.alert("INVALID DATA! PLEASE CHECK DATA!")
    </script>
</c:if>

<%@ include file="/WEB-INF/jspf/admin_menu.jspf"%>


<div class="form">
    <form class="add_form" action="controller" method="post">
        <input type="hidden" name="command" value="insertTariff" />

        <input name="name" type="text" required="required" placeholder="<fmt:message key="insert_tariff.name"/>">

        <select name="typeOfService">
            <c:set var="k" value="0" />
            <c:forEach var="item" items="${services}">
                <c:set var="k" value="${k+1}" />
                <option value="${item.id}">${item.name}</option>
            </c:forEach>
        </select>

        <input name="price" type="number" value="10.0" step="0.1" minlength="10.0" required="required" placeholder="<fmt:message key="insert_tariff.price"/>">

        <input name="description" type="text" required="required" placeholder="<fmt:message key="insert_tariff.description"/>">

        <input class="btn" type="submit" value="<fmt:message key="insert_tariff.submit.add"/>" />
    </form>
</div>
</body>
</html>