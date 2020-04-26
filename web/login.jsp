<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jspf/taglib.jspf"%>

<html>
<fmt:message key="login.title" var="title"/>
<c:set var="title" value="${title}" scope="page" />
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="style/login.css" media="screen" type="text/css" />
    <link rel="stylesheet" href="style/error.css" media="screen" type="text/css" />

</head>
<body>

<c:if test="${not empty param.error}">
    <script type="text/javascript">
        window.alert("INVALID DATA! PLEASE CHECK DATA!")
    </script>
</c:if>

<div id="login">
    <form action="controller" method="post">
        <fieldset class="clearfix">
            <p><span class="fontawesome-user"></span>
                <input type="hidden" name="command" value="login"/>
                <fmt:message key="login.login" var="Login"/>
                <input type="text" name="login" placeholder="${Login}" required="required"/><br/>
            <p><span class="fontawesome-lock"></span>
                <fmt:message key="login.password" var="Password"/>
                <input type="password" placeholder="${Password}" name="password" required="required"/><br/>
            <p><input type="submit" value="<fmt:message key="login.submit.login"/>"></p>
        </fieldset>
    </form>
</div>
</body>
</html>