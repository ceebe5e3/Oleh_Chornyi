<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jspf/taglib.jspf"%>
<%@ taglib prefix="subscriber" tagdir="/WEB-INF/tags"%>

<html>
    <fmt:message key="account.title" var="title"/>
    <c:set var="title" value="${title}" scope="page" />

    <head>
        <title>${title}</title>
        <link rel="stylesheet" type="text/css" media="screen" href="style/list_service_tariffs.css"/>
        <link rel="stylesheet" type="text/css" media="screen" href="style/account.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
<body>
<c:if test="${not empty param.error}">
    <script type="text/javascript">
        window.alert("INVALID DATA! PLEASE CHECK DATA!")
    </script>
</c:if>
    <%@ include file="/WEB-INF/jspf/header.jspf"%>
    <%@ include file="/WEB-INF/jspf/user_menu.jspf"%>

    <c:if test="${not empty contracts}">

        <h2 style="text-align: center; font-size: 18px;color: #2c3338; text-transform: uppercase">
            <fmt:message key="account.contracts"/>:
        </h2>

        <table id="content-table3" class="table-responsive-full sort-table">
        <thead>
        <tr>
        <th><fmt:message key="account.tariff"/></th>
        <th><fmt:message key="account.service"/></th>
        <th><fmt:message key="account.price"/></th>
        <th><fmt:message key="account.description"/></th>
        <th><fmt:message key="account.date"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="contract" items="${contracts}">
            <tr>
                <subscriber:contracts contract="${contract}"/>
            </tr>
        </c:forEach>
        </tbody>
        </table>
    </c:if>

    <div class="wrapper">
        <div class="payment">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="accountPayment" />
            <div class="form">
                <div class="card space icon-relative">
                    <label class="label"><fmt:message key="account.amount"/></label>
                    <input type="number" value="0.0" step="0.1" minlength="0.0" name="sum" required pattern="/^[0-9]$/" class="input" placeholder="<fmt:message key="account.amount"/>">
                    <i class="fa fa-money"></i>
                </div>
                <div class="card space icon-relative">
                    <label class="label"><fmt:message key="account.card_number"/> </label>
                    <input type="text" name="cardNumber" class="input" maxlength="16" required="required" data-mask="0000 0000 0000 0000" placeholder="<fmt:message key="account.card_number"/>">
                    <i class="fa fa-credit-card"></i>
                </div>
                <div class="card-grp space">
                    <div class="card-item icon-relative">
                        <label class="label">CVC:</label>
                        <input type="password" name="code" size="3" maxlength="3" required="required" class="input" data-mask="000" placeholder="000">
                        <i class="fa fa-lock"></i>
                    </div>
                </div>
                 <input class="btn" type="submit" value="<fmt:message key="account.submit.payment"/> "/>
            </div>
        </div>
    </div>
</body>
</html>
