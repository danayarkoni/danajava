<%@page import="com.hag.hagay.ReplaceEnvironment"%>
<jsp:useBean id="replaceEnvironment" class="com.hag.hagay.ReplaceEnvironment" scope="session" />

<HTML>
<BODY>
	<%=	replaceEnvironment.replaceCmDb(request, response) %><BR>
</BODY>
</HTML>