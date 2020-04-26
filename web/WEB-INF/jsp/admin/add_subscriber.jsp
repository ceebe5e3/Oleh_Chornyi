<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jspf/taglib.jspf"%>
<html>
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
        <form action="controller" method="post">
            <input type="hidden" name="command" value="addSubscriber" />

            <input name="fullName" type="text" required pattern="^(\w\w+)\s(\w+)$" placeholder="<fmt:message key="add_subscriber.full_name"/>">

            <input name="dob" type="text" maxlength="10" required pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" placeholder="YYYY-MM-DD">

            <input name="address" type="text" required="required" placeholder="<fmt:message key="add_subscriber.address"/>">

            <input name="email" type="email" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2, 4}$" placeholder="<fmt:message key="add_subscriber.email"/>">

            <input name="login" type="text" minlength="4" required="required" placeholder="<fmt:message key="add_subscriber.login"/>">

            <input name="password" type="password" minlength="4" required="required" placeholder="<fmt:message key="add_subscriber.password"/>">

            <input class="btn" type="submit" value="<fmt:message key="add_subscriber.submit.add"/>" />
        </form>
    </div>
</body>
</html>