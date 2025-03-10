<%@page import="com.hag.hagay.Init"%>
<jsp:useBean id="tasks" class="com.hag.hagay.HagTasks" scope="session" />

<HTML>
<BODY>
	
	<%=	tasks.closeTask_after(request, response) %><BR>
</BODY>
</HTML>