<%@page import="com.hag.hagay.RequestsFlowPerCust"%>
<jsp:useBean id="requestsFlowPerCust" class="com.hag.hagay.RequestsFlowPerCust" scope="session" />

<HTML>
<BODY>
	
	<%=	requestsFlowPerCust.filterRequestsStatus(request, response) %><BR>
</BODY>
</HTML>