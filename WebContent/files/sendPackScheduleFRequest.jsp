<%@page import="com.hag.hagay.ScheduleF"%>
<jsp:useBean id="scheduleF" class="com.hag.hagay.ScheduleF" scope="session" />

<HTML>
<BODY>
	
	<%=	scheduleF.sendPackScheduleFRequest(request, response) %><BR>
</BODY>
</HTML>