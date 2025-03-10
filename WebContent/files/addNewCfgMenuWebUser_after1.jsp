<%@page import="com.hag.hagay.HagAddUser"%>
<jsp:useBean id="hagAddUser" class="com.hag.hagay.HagAddUser" scope="session" />

<HTML>
<BODY>
	<%=	hagAddUser.addNewCfgMenuWebUser_after1(request, response) %><BR>
</BODY>
</HTML>