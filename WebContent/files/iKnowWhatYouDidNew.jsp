<%@page import="com.hag.hagay.HagIKnowWhatYouDid"%>
<jsp:useBean id="iKnowWhatYouDid" class="com.hag.hagay.HagIKnowWhatYouDid" scope="session" />

<HTML>
<BODY>
	
	<%=	iKnowWhatYouDid.after(request, response) %><BR>
</BODY>
</HTML>