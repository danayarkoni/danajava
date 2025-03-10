<%@page import="com.hag.hagay.MinorVersions"%>
<jsp:useBean id="minorVersions" class="com.hag.hagay.MinorVersions" scope="session" />

<HTML>
<BODY>
	<%=	minorVersions.split(request, response) %><BR>
</BODY>
</HTML>