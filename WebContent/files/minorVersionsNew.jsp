<%@page import="com.hag.hagay.MinorVersions"%>
<jsp:useBean id="minorVersions" class="com.hag.hagay.MinorVersions" scope="session" />

<HTML>
<BODY>
	<%=	minorVersions.newReqSend(request, response) %><BR>
</BODY>
</HTML>