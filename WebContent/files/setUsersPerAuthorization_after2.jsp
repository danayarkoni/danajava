<%@page import="com.hag.hagay.HagAddUser"%>
<jsp:useBean id="hagAddUser" class="com.hag.hagay.HagAddUser" scope="session" />

<HTML>
<BODY>
	<%=	hagAddUser.setUsersPerAuthorization_after2(request, response) %><BR>
</BODY>
</HTML>