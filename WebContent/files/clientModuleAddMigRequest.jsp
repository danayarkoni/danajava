<%@page import="com.hag.hagay.Init"%>
<jsp:useBean id="hagay" class="com.hag.hagay.Init" scope="session" />

<HTML>
<BODY>
	
	<%=	hagay.clientModuleAddMigRequest(request, response) %><BR>
</BODY>
</HTML>