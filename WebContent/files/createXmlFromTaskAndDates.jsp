<%@page import="com.hag.hagay.HagCreateXmlFromTask"%>
<jsp:useBean id="hagCreateXmlFromTask" class="com.hag.hagay.HagCreateXmlFromTask" scope="session" />

<HTML>
<BODY>
	
	<%=	hagCreateXmlFromTask.createXmlFromTask_after(request, response) %><BR>
</BODY>
</HTML>