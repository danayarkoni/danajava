<%@page import="com.hag.hagay.ClientModuleBuild"%>
<jsp:useBean id="clientModuleBuild" class="com.hag.hagay.ClientModuleBuild" scope="session" />

<HTML>
<BODY>
	<%=	clientModuleBuild.clientModuleBuild_main(request, response) %><BR>
</BODY>
</HTML>