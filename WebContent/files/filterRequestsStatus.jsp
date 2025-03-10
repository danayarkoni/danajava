<%@page import="com.hag.hagay.RequestsStatus"%>
<jsp:useBean id="requestsStatus" class="com.hag.hagay.RequestsStatus" scope="session" />

<HTML>
<BODY>
	
	<%=	requestsStatus.filterRequestsStatus(request, response) %><BR>
</BODY>
</HTML>