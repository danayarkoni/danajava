<%@page import="com.hag.hagay.MinorVersions"%>
<jsp:useBean id="minorVersions" class="com.hag.hagay.MinorVersions" scope="session" />

<HTML>
<BODY>
	<%=	minorVersions.packReqSend(request, response) %><BR>
</BODY>
</HTML>