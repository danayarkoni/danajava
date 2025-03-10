<%@page import="com.hag.hagay.HagPrivate"%>
<jsp:useBean id="hagPrivate" class="com.hag.hagay.HagPrivate" scope="session" />

<HTML>
<BODY>
	
	<%=	hagPrivate.before2(request, response) %><BR>
</BODY>
</HTML>