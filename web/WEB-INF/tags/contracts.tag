<%@tag pageEncoding="UTF-8"%>
<%@tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="contract" required="true" type="ua.nure.chornyi.SummaryTask4.db.entity.Contract"%>
<%@ include file="/WEB-INF/jspf/taglib.jspf"%>
<td>${contract.serviceTariffs.tariff.name}</td>
<td>${contract.serviceTariffs.service.name}</td>
<td>${contract.serviceTariffs.price}</td>
<td>${contract.serviceTariffs.description}</td>
<td>${contract.date}</td>