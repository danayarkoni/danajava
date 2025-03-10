<%@page import="com.hag.hagay.HagRunTran"%>
<jsp:useBean id="hagRunTran" class="com.hag.hagay.HagRunTran" scope="session" />

<HTML>
<BODY>
	
	<%=	hagRunTran.runTran_after(request, response) %><BR>
</BODY>
</HTML>