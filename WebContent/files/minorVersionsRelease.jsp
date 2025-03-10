<%@page import="com.hag.hagay.MinorVersions"%>
<jsp:useBean id="minorVersions" class="com.hag.hagay.MinorVersions" scope="session" />

<HTML>
<BODY>
	<%=	minorVersions.releaseReqSend(request, response) %><BR>
</BODY>
</HTML>