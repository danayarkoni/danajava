<%@page import="com.hag.hagay.HagDebug"%>
<jsp:useBean id="hagDebug" class="com.hag.hagay.HagDebug" scope="session" />

<HTML>
<BODY>
	<%=	hagDebug.powershellDebug_after(request, response) %><BR>
</BODY>
</HTML>