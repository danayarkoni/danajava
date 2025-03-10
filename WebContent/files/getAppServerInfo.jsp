<%@page import="com.hag.hagay.Init"%>
<jsp:useBean id="appServersInfo" class="com.hag.hagay.HagServersInfo" scope="session" />

<HTML>
<BODY>
	
	<%=	appServersInfo.getAppServersInfo_after(request, response) %><BR>
</BODY>
</HTML>