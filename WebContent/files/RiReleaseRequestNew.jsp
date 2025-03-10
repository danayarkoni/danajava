<%@page import="com.hag.hagay.HagReqRelease"%>
<jsp:useBean id="hagReqRelease" class="com.hag.hagay.HagReqRelease" scope="session" />

<HTML>
<BODY>
	<%=	hagReqRelease.riReleaseRequest(request, response) %><BR>
</BODY>
</HTML>