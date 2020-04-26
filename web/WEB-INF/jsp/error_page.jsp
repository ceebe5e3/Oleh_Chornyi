<%@ page isErrorPage="true" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jspf/taglib.jspf"%>

<html>
<c:set var="title" value="Error" scope="page" />
<head>
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" media="screen" href="style/error.css"/>
</head>
<body>
    <%@include file="/WEB-INF/jspf/header.jspf"%>
    <section>
        <h2 class="error" style="text-align: center">
            The following error occurred
        </h2>

        <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
        <c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>
        <c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>

        <c:if test="${not empty code}">
            <h3>Error code: ${code}</h3>
        </c:if>

        <c:if test="${not empty message}">
            <h3>${message}</h3>
        </c:if>

        <c:if test="${not empty exception}">
            <% exception.printStackTrace(new PrintWriter(out)); %>
        </c:if>

        <c:if test="${not empty requestScope.errorMessage}">
            <h3>${requestScope.errorMessage}</h3>
        </c:if>

    </section>
</body>
</html>
