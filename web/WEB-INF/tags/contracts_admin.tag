<%@tag pageEncoding="UTF-8"%>
<%@tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="contractAdmin" required="true" type="ua.nure.chornyi.SummaryTask4.db.entity.Contract"%>
<%@ include file="/WEB-INF/jspf/taglib.jspf"%>
<td>${contractAdmin.user.login}</td>
<td>${contractAdmin.serviceTariffs.tariff.name}</td>
<td>${contractAdmin.serviceTariffs.service.name}</td>
<td>${contractAdmin.serviceTariffs.price}</td>
<td>${contractAdmin.serviceTariffs.description}</td>
<td>${contractAdmin.date}</td>
