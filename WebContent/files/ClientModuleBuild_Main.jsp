<%@page import="com.hag.hagay.ClientModuleBuild"%>
<jsp:useBean id="clientModuleBuild1" class="com.hag.hagay.ClientModuleBuild" scope="session" />

<HTML>
<BODY>
	<%=	clientModuleBuild1.clientModuleBuild_after(request, response) %><BR>
</BODY>
</HTML>