<%@page import="com.hag.hagay.PreVersion"%>
<jsp:useBean id="preVersion" class="com.hag.hagay.PreVersion" scope="session" />

<HTML>
<BODY>
	<%=	preVersion.RiReleaseRequestPreVersionSend(request, response) %><BR>
</BODY>
</HTML>