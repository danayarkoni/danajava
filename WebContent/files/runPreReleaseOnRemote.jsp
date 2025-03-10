<%@page import="com.hag.hagay.RunPreReleaseOnRemote"%>
<jsp:useBean id="runPreReleaseOnRemote" class="com.hag.hagay.RunPreReleaseOnRemote" scope="session" />

<HTML>
<BODY>
	
	<%=	runPreReleaseOnRemote.before2(request, response) %><BR>
</BODY>
</HTML>