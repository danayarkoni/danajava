<%@page import="com.hag.hagay.HagJiraNew"%>
<jsp:useBean id="hagJiraNew" class="com.hag.hagay.HagJiraNew" scope="session" />

<HTML>
<BODY>
	<%=	HagJiraNew.after(request, response) %><BR>
</BODY>
</HTML>