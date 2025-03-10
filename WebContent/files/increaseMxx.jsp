<%@page import="com.hag.hagay.ChangeMxx"%>
<jsp:useBean id="changeMxx" class="com.hag.hagay.ChangeMxx" scope="session" />

<HTML>
<BODY>
	
	<%=	changeMxx.increaseMxx(request, response) %><BR>
</BODY>
</HTML>