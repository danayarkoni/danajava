<%@page import="com.hag.hagay.HagUtil"%>
<jsp:useBean id="hagUtil" class="com.hag.hagay.HagUtil" scope="session" />

<HTML>
<BODY>
	<%=	hagUtil.askAuthorization_after(request, response) %><BR>
</BODY>
</HTML>