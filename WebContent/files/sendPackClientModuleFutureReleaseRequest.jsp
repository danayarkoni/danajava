<%@page import="com.hag.hagay.ClientModule"%>
<jsp:useBean id="clientModule" class="com.hag.hagay.ClientModule" scope="session" />

<HTML>
<BODY>
	
	<%=	clientModule.sendPackClientModuleFutureReleaseRequest(request, response) %><BR>
</BODY>
</HTML>