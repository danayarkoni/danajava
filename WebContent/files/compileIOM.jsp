<%@page import="com.hag.hagay.Init"%>
<jsp:useBean id="iom" class="com.hag.hagay.HagIOM" scope="session" />

<HTML>
<BODY>
	
	<%=	iom.compileIOM_after(request, response) %><BR>
</BODY>
</HTML>