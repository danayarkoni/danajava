<%@page import="com.hag.hagay.ClientModule"%>
<jsp:useBean id="clientModule" class="com.hag.hagay.ClientModule" scope="session" />

<HTML>
<BODY>
	
	<%=	clientModule.clientModuleFutureReleaseAddMigRequest(request, response) %><BR>
</BODY>
</HTML>