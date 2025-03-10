package com.hag.hagay;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HagAddUser {
	public static String 	before_addCfgMenuWebUser(String user) {
		
		//	String[][] vals = getValues();
			StringBuilder ans = new StringBuilder();
			HagSQL hagSQL = new HagSQL();
			String stm = "select * from dbo.hagCfgMenuWebUsers where note1='x'";
			HagStringList usersList =hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,6,"|",null,null);
			
			ans.append("<html><body bgcolor=\"#dddddd\">");
			ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"addNewCfgMenuWebUser_after1.jsp\">");
			ans.append("<input type=\"hidden\" name=\"doneBy\" id=\"doneBy\" value = \""+user+"\" maxlength=\"140\" size=\"140\">");
			
			String newUser 	= HagHtmlUtil.getTextField("newUser","80","","");
			String newDomain 	= HagHtmlUtil.getTextField("newDomain","80","","");
			//HagStringList usersList= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\hag\\usr.txt",true,"*",false);
			String[] usersArr1 = new String[usersList.size()];
			for(int i = 0 ;i < usersList.size();i++)
				usersArr1[i]=HagUtil.getWord0(usersList.get(i), "|", 0, true);
			String usersArr 	= HagHtmlUtil.getSelectField("usersArr",usersArr1);
			
			
			ans.append("<table bgcolor=\"#aaaac\" cellpadding=\"5\" border =\"1\"   >");
			ans.append("<h3>Insert new user to cfgMenuWeb</h3>");
			ans.append("<tr bgcolor=\"#9999bb\"> <td>Title</td><td>Value</td><td>Note</td><tr>");
			ans.append("<tr><td>new user</td><td>"+newUser+"</td><td>write the user without @sapiens.com</td><tr>");
			ans.append("<tr><td>domain name</td><td>"+newDomain+"</td><td>write the user in the domain, Usually it is the same user </td><tr>");
			ans.append("<tr><td>like user</td><td>"+usersArr+"</td><td>select like which user</td><tr>");
			ans.append("</table>");
			ans.append("</td></tr>");
			ans.append("</table><br><br>");	
			
			

			ans.append("<INPUT TYPE=SUBMIT value=\"Insert the user\"></FORM>");
			ans.append("</body></html>");
			return ans.toString();
		

		}
	public static String 	before_delCfgMenuWebUser(String user) {
		
		//	String[][] vals = getValues();
			StringBuilder ans = new StringBuilder();
			HagSQL hagSQL = new HagSQL();
			String stm = "select * from dbo.hagCfgMenuWebUsers where note1='x'";
			HagStringList usersList =hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,6,"|",null,null);
			
			ans.append("<html><body bgcolor=\"#dddddd\">");
			ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"delNewCfgMenuWebUser_after1.jsp\">");
			ans.append("<input type=\"hidden\" name=\"doneBy\" id=\"doneBy\" value = \""+user+"\" maxlength=\"140\" size=\"140\">");
			
			
			//HagStringList usersList= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\hag\\usr.txt",true,"*",false);
			String[] usersArr1 = new String[usersList.size()];
			for(int i = 0 ;i < usersList.size();i++)
				usersArr1[i]=HagUtil.getWord0(usersList.get(i), "|", 0, true);
			String usersArr 	= HagHtmlUtil.getSelectField("usersArr",usersArr1);
			
			
			ans.append("<table bgcolor=\"#aaaac\" cellpadding=\"5\" border =\"1\"   >");
			ans.append("<h3>Delete  user from cfgMenuWeb</h3>");
			ans.append("<tr bgcolor=\"#9999bb\"> <td>Title</td><td>Value</td><td>Note</td><tr>");
		
			ans.append("<tr><td>user</td><td>"+usersArr+"</td><td>select user to delete</td><tr>");
			ans.append("</table>");
			ans.append("</td></tr>");
			ans.append("</table><br><br>");	
			
			

			ans.append("<INPUT TYPE=SUBMIT value=\"Delete the user\"></FORM>");
			ans.append("</body></html>");
			return ans.toString();
		

		}
	public static String 	before_setAuthorizationsPerUser(String user) {
		//	String[][] vals = getValues();
			StringBuilder ans = new StringBuilder();
			HagSQL hagSQL = new HagSQL();
			String stm = "select * from dbo.hagCfgMenuWebUsers";
			HagStringList usersList =hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,6,"|",null,null);
			
			ans.append("<html><body bgcolor=\"#dddddd\">");
			ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"setAuthorizationsPerUser_after1.jsp\">");
			ans.append("<input type=\"hidden\" name=\"doneBy\" id=\"doneBy\" value = \""+user+"\" maxlength=\"140\" size=\"140\">");
			
			String[] usersArr1 = new String[usersList.size()];
			for(int i = 0 ;i < usersList.size();i++)
				usersArr1[i]=HagUtil.getWord0(usersList.get(i), "|", 0, true);
			String usersArr 	= HagHtmlUtil.getSelectField("usersArr",usersArr1);
			
			
			ans.append("<table bgcolor=\"#aaaac\" cellpadding=\"5\" border =\"1\"   >");
			ans.append("<h3>Set authorizations per user</h3>");
			ans.append("<tr bgcolor=\"#9999bb\"> <td>Title</td><td>Value</td><td>Note</td><tr>");
			ans.append("<tr><td>User</td><td>"+usersArr+"</td><td>select the user</td><tr>");
			ans.append("</table>");
			ans.append("</td></tr>");
			ans.append("</table><br><br>");	
			
			

			ans.append("<INPUT TYPE=SUBMIT value=\"Get list of authorizations for this user\"></FORM>");
			ans.append("</body></html>");
			return ans.toString();
		

		}
	public static String 	before_setUsersPerAuthorization(String user) {
		//	String[][] vals = getValues();
			StringBuilder ans = new StringBuilder();
			HagSQL hagSQL = new HagSQL();
			String stm = "select * from dbo.hagCfgMenuWebAuth";
			HagStringList authsList =hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,2,"|",null,null);
			
			ans.append("<html><body bgcolor=\"#dddddd\">");
			ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"setUsersPerAuthorization_after1.jsp\">");
			ans.append("<input type=\"hidden\" name=\"doneBy\" id=\"doneBy\" value = \""+user+"\" maxlength=\"140\" size=\"140\">");
			String[] authsArr1 = new String[authsList.size()];
			for(int i = 0 ;i < authsList.size();i++)
				authsArr1[i]=HagUtil.getWord0(authsList.get(i), "|", 0, true);
			String authsArr 	= HagHtmlUtil.getSelectField("authsArr",authsArr1);
			
			
			ans.append("<table bgcolor=\"#aaaac\" cellpadding=\"5\" border =\"1\"   >");
			ans.append("<h3>Set users per authorization</h3>");
			ans.append("<tr bgcolor=\"#9999bb\"> <td>Title</td><td>Value</td><td>Note</td><tr>");

			ans.append("<tr><td>Authorization</td><td>"+authsArr+"</td><td>select the authorization</td><tr>");
			ans.append("</table>");
			ans.append("</td></tr>");
			ans.append("</table><br><br>");	
			
			

			ans.append("<INPUT TYPE=SUBMIT value=\"Get list of users for this authorization\"></FORM>");
			ans.append("</body></html>");
			return ans.toString();
		

		}
	public 			String 	setAuthorizationsPerUser_after1(HttpServletRequest request, HttpServletResponse response){
		//if(1==1)
		//	return tempLoad();
		HagSQL hagSQL = new HagSQL();
		String stm = "select * from dbo.hagCfgMenuWebAuth";
		HagStringList authList =hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,2,"|",null,null);
	
		String 	 doneBy			= request.getParameter("doneBy").trim();
		String 	 user			= request.getParameter("usersArr").trim();
		//String 	 sentBy				= request.getParameter("sentBy").trim();
		StringBuilder ans = new StringBuilder();
		ans.append("<html> ");
		ans.append("<script language=\"JavaScript\">")
		.append("function toggle(source) {")	
		.append("checkboxesW = document.getElementsByName('cb1');")
		.append("checkboxesA = document.getElementsByName('cb2');")	
		.append("for(var i=0, n=checkboxesW.length;i<n;i++) { checkboxesW[i].checked = !source.checked;  }")
		.append("for(var i=0, n=checkboxesA.length;i<n;i++) {  checkboxesA[i].checked = !source.checked;  }")	
		.append("first_extra_dev.value=source.checked")	
		.append("}</script>");
		ans.append("<body bgcolor=\"#dddddd\"  >");
		ans.append("<h3>setAuthorizationsPerUser for <br><font color=#FF0000><b>"+user+"</b></font> user</h3>");
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"setAuthorizationsPerUser_after2.jsp\">");
		ans.append("<table bgcolor=\"#aaaac\" cellpadding=\"5\" border =\"1\"   >");
		ans.append("<input type=\"hidden\" name=\"user\" id=\"user\" value = \""+user+"\" maxlength=\"140\" size=\"140\">");
		ans.append("<input type=\"hidden\" name=\"doneBy\" id=\"doneBy\" value = \""+doneBy+"\" maxlength=\"140\" size=\"140\">");
		
		ans.append("<tr bgcolor=\"#9999bb\"> <td>Title</td><td>Note</td><tr>");
		for(int i = 0 ; i <authList.size();i++ ){
			String temp = authList.get(i);
			String auth = HagUtil.getWord0(temp,"|",0,true);
			String note = HagUtil.getWord0(temp,"|",1,true);
			if(note.equals("."))
				note="Will be explanation for <u><b> "+auth+"</b></u> authorization";
			ans.append("<tr> <td>");
			if(HagUtil.isUserAuth(user,auth))
				ans.append("<input type=\"checkbox\"  name=\"cb3\" id=\"cb3\" value=\"").append(auth).append("\" checked onClick=\"toggle(this)\" ><font color=#000000>"+auth+"</font>").append("<br>");
			else
				ans.append("<input type=\"checkbox\"  name=\"cb3\" id=\"cb3\" value=\"").append(auth).append("\"  onClick=\"toggle(this)\" ><font color=#ffffff>"+auth+"</font>").append("<br>");
			ans.append("</td><td>"+note+"</td><tr>");
		}
		ans.append("</table>");
		ans.append("<INPUT TYPE=SUBMIT value=\"Save changes\"></FORM>");
		ans.append("</body></html>");
		return ans.toString();
	
	}
	
	public String 	addNewCfgMenuWebUser_after1(HttpServletRequest request, HttpServletResponse response){
	
		String 	 newUser			= request.getParameter("newUser").trim();
		String 	 newDomain			= request.getParameter("newDomain").trim();

		String 	 doneBy			= request.getParameter("doneBy").trim();
		String 	 usersArr			= request.getParameter("usersArr").trim();
		HagRc hagRc =new HagRc();
		newUser = fixUserNew(hagRc,newUser);
		if(hagRc.rc!=0) {
			String msg=hagRc.log.convertToString("<br>");
			HagUtil.writeToRelDiary2("CfgMenuWeb","WIN","AddUser",".",".","Failed",".",".",newUser,doneBy,".",".");
			
			
			
			return HagUtil.shortHtml(msg, "red", "bg");
		}
		String ans =addUsrToTableNew(  newUser, usersArr,newDomain);
		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+doneBy,HagUtil.mailList_hag,"cfgMenuWeb-addNewCfgMenuWebUser by"+doneBy,ans);
		
		//
		if( !ans.startsWith("0~")) {
			HagUtil.writeToRelDiary2("CfgMenuWeb","WIN","AddUser",".",".","Failed",".",".",newUser,doneBy,".",".");
			
			return HagUtil.shortHtml("Failed to add new user to users table , please check again","reg","bg");
		}else {
			HagUtil.writeToRelDiary2("CfgMenuWeb","WIN","AddUser",".",".","OK",".",".",newUser,doneBy,".",".");
			//return HagUtil.shortHtml(newUser+" user added d","green","bg");
			return dist(newUser,"added");
		}
	
	//
	
	}
	public String 	delNewCfgMenuWebUser_after1(HttpServletRequest request, HttpServletResponse response){
		
	
		String 	 doneBy			= request.getParameter("doneBy").trim();
		String 	 usersArr			= request.getParameter("usersArr").trim();
		
		HagSQL hagSql = new HagSQL();
		String stm1= "delete from hagCfgMenuWebUsers where name1='"+usersArr+"'";
		int ans1 =hagSql.updateStm("confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm1);
		
		
		String stm2= "delete from hagCfgMenuWebUsersAuth where userName1='"+usersArr+"'";
		int ans2 =hagSql.updateStm("confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm2);
	//	if( ans1!=1)
		//	return HagUtil.shortHtml("Failed to delete  user from users table , please check again","reg","bg");
		//return HagUtil.shortHtml(usersArr+" user deleted","green","bg");
		//
		if( ans1!=1) {
			HagUtil.writeToRelDiary2("CfgMenuWeb","WIN","DelUser",".",".","Failed",".",".",usersArr,doneBy,".",".");
			String ans111		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+doneBy,HagUtil.mailList_hag,"cfgMenuWeb-delCfgMenuWebUser "+usersArr+" by "+doneBy+" failed",stm2);
			return HagUtil.shortHtml("Failed to add new user to users table , please check again","reg","bg");
		}else {
			HagUtil.writeToRelDiary2("CfgMenuWeb","WIN","DelUser",".",".","OK",".",".",usersArr,doneBy,".",".");
			String ans111		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+"'"+doneBy,HagUtil.mailList_hag,"cfgMenuWeb-delCfgMenuWebUser "+usersArr+" by "+doneBy+" done",stm2);

			//return HagUtil.shortHtml(newUser+" user added d","green","bg");
			return dist(usersArr,"deleted");
		}
	//	return dist(usersArr,"deleted");		
	
	}
	public String 	setUsersPerAuthorization_after1(HttpServletRequest request, HttpServletResponse response){
		HagSQL hagSQL = new HagSQL();
		String stm = "select * from dbo.hagCfgMenuWebUsers";
		HagStringList usersList =hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,6,"|",null,null);
	
		String 	 doneBy			= request.getParameter("doneBy").trim();
		String 	 auth			= request.getParameter("authsArr").trim();
		//String 	 sentBy				= request.getParameter("sentBy").trim();
		StringBuilder ans = new StringBuilder();
		ans.append("<html>  ");
		ans.append("<script language=\"JavaScript\">")
		.append("function toggle(source) {")	
		.append("checkboxesW = document.getElementsByName('cb1');")
		.append("checkboxesA = document.getElementsByName('cb2');")	
		.append("for(var i=0, n=checkboxesW.length;i<n;i++) { checkboxesW[i].checked = !source.checked;  }")
		.append("for(var i=0, n=checkboxesA.length;i<n;i++) {  checkboxesA[i].checked = !source.checked;  }")	
		.append("first_extra_dev.value=source.checked")	
		.append("}</script>");
		ans.append("<body bgcolor=\"#dddddd\"  >");
		
		ans.append("<h3>setUsersPerAuthorization for <br><font color=#FF0000><b>"+auth+"</b></font> authorization</h3>");
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"setUsersPerAuthorization_after2.jsp\">");
		ans.append("<input type=\"hidden\" name=\"auth\" id=\"auth\" value = \""+auth+"\" maxlength=\"140\" size=\"140\">");
		ans.append("<input type=\"hidden\" name=\"doneBy\" id=\"doneBy\" value = \""+doneBy+"\" maxlength=\"140\" size=\"140\">");
	
		ans.append("<table bgcolor=\"#aaaac\" cellpadding=\"5\" border =\"1\"   >");
		ans.append("<tr bgcolor=\"#9999bb\"> <td>User</td><td>Group</td><tr>");
		for(int i = 0 ; i <usersList.size();i++ ){
			String temp = usersList.get(i);
			String user = HagUtil.getWord0(temp,"|",0,true);
			String group = HagUtil.getWord0(temp,"|",3,true);
			//String note = HagUtil.getWord0(temp,"|",1,true);
			ans.append("<tr> <td>");
			if(HagUtil.isUserAuth(user,auth))
				ans.append("<input type=\"checkbox\" onClick=\"toggle(this)\" name=\"cb3\" id=\"cb3\" value=\"").append(user).append("\" checked ><font color=#000000>"+user+"</font>").append("<br>");
			else
				ans.append("<input type=\"checkbox\" onClick=\"toggle(this)\" name=\"cb3\" id=\"cb3\" value=\"").append(user).append("\"  ><font color=#FFFFFF>"+user+"</font>").append("<br>");
			ans.append("</td><td>"+group+"</td><tr>");
		}
		ans.append("</table>");
		ans.append("<INPUT TYPE=SUBMIT value=\"Save changes\"></FORM>");
		ans.append("</body></html>");
		return ans.toString();
	
	}
	public String 	setAuthorizationsPerUser_after2(HttpServletRequest request, HttpServletResponse response){
		String 	 user			= request.getParameter("user").trim();
		String 	 doneBy			= request.getParameter("doneBy").trim();
		String [] cbgroup1		= request.getParameterValues("cb3");
		HagSQL hagSql = new HagSQL();
		//String stm111b = "select authName1 from dbo.hagCfgMenuWebUsersAuth where userName1='"+user+"'";
		//ArrayList<String> ans111b = hagSql.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm111b);
	
		String stm= "delete from hagCfgMenuWebUsersAuth where userName1='"+user+"'";
		int ans1 =hagSql.updateStm("confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm);
		boolean flag =true;
		for(int i = 0 ; i < cbgroup1.length;i++) {
			StringBuilder authsLine = new StringBuilder("'").append(cbgroup1[i]).append("','").append(user).append("'");
			String ans2 = hagSql.cfgInsert("hagCfgMenuWebUsersAuth", authsLine.toString());
			if(!ans2.startsWith("0"))
				flag= false;
		}
		if(cbgroup1.length>0 && !flag)
			return HagUtil.shortHtml("Failed to add authorizations to user , please check again","reg","bg");
		
		
		StringBuilder ans = new StringBuilder(ans1+" authorizations deleted from <b><u>"+user+"</u></b> user")
				.append("<br>")
				.append(cbgroup1.length+" authorizations added to <b><u>"+user+"</u></b> user");
		String ans11		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+doneBy,HagUtil.mailList_hag,"cfgMenuWeb-setAuthorizationsPerUser by"+doneBy,ans.toString());
		
		
		if(cbgroup1.length>0 && !flag) {
			HagUtil.writeToRelDiary2("CfgMenuWeb","WIN","SetAuthsPerUser",".",".","Failed",".",".",user,doneBy,".",".");
			String ans111		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+"'"+doneBy,HagUtil.mailList_hag,"cfgMenuWeb-setAuthorizationsPerUser "+user+" by "+doneBy+" failed",stm);
			return HagUtil.shortHtml("Failed to add new user to users table , please check again","reg","bg");
		}else {
			HagUtil.writeToRelDiary2("CfgMenuWeb","WIN","SetAuthsPerUser",".",".","OK",".",".",user,doneBy,".",".");
			String ans111		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+"'"+doneBy,HagUtil.mailList_hag,"cfgMenuWeb-setAuthorizationsPerUser "+user+" by "+doneBy+" done",stm);

			return HagUtil.shortHtml("setAuthorizationsPerUser"+ user +" done","green","bg");
			//return dist(usersArr,"deleted");
		}
	
		
		
		//return ans.toString();
	}
	public String 	setUsersPerAuthorization_after2(HttpServletRequest request, HttpServletResponse response){
		String 	 auth			= request.getParameter("auth").trim();
		String 	 doneBy			= request.getParameter("doneBy").trim();
		String [] cbgroup1		= request.getParameterValues("cb3");
		HagSQL hagSql = new HagSQL();
		String stm= "delete from hagCfgMenuWebUsersAuth where authName1='"+auth+"'";
		int ans1 =hagSql.updateStm("confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm);
		boolean flag =true;
		for(int i = 0 ; i < cbgroup1.length;i++) {
			StringBuilder authsLine = new StringBuilder("'").append(auth).append("','").append(cbgroup1[i]).append("'");
			String ans2 = hagSql.cfgInsert("hagCfgMenuWebUsersAuth", authsLine.toString());
			if(!ans2.startsWith("0"))
				flag= false;
		}
		//if(cbgroup1.length>0 && !flag)
		//	return HagUtil.shortHtml("Failed to add users to authorization , please check again","reg","bg");
		StringBuilder ans = new StringBuilder(ans1+" users deleted from <b><u>"+auth+"</u></b> authorization")
				.append("<br>")
				.append(cbgroup1.length+" users added to <b><u>"+auth+"</u></b> authorization");
		
		
		
		if(cbgroup1.length>0 && !flag) {
			HagUtil.writeToRelDiary2("CfgMenuWeb","WIN","SetUsersPerAuth",".",".","Failed",".",".",auth,doneBy,".",".");
			String ans111		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+doneBy,HagUtil.mailList_hag,"cfgMenuWeb-setUsersPerAuthorization "+auth+" by "+doneBy+" failed",".");
			return HagUtil.shortHtml("Failed to add new user to users table , please check again","reg","bg");
		}else {
			HagUtil.writeToRelDiary2("CfgMenuWeb","WIN","SetUsersPerAuth",".",".","OK",".",".",auth,doneBy,".",".");
			String ans111		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+"'"+doneBy,HagUtil.mailList_hag,"cfgMenuWeb-setUsersPerAuthorization "+auth+" by "+doneBy+" done",ans.toString());

			return HagUtil.shortHtml(ans.toString(),"green","bg");
			//try {		
			//	return response.sendRedirect("done.jsp?value="+ans.toString());
			//} catch (IOException e) {
			//	// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
			//return dist(usersArr,"deleted");
		}
		//String ans11		= HagUtil.sendMail_hag(HagUtil.cfgTeamMail,"david.ha@sapiens.com","david.ha@sapiens.com","cfgMenuWeb-setUsersPerAuthorization by"+doneBy,ans.toString());
		
		//return ans.toString();
	}
	
	
	public String 	fixUserNew(HagRc hagRc  , String usr){
		if(usr==null) {
			hagRc.rc=1;
			hagRc.log.add("user name must not be null");
			return null;
		}
		usr=usr.trim();
		if(usr.indexOf("@")>-1) {
			hagRc.rc=1;
			hagRc.log.add("user name must be without @");
			return null;
		}
		HagStringList list = new HagStringList(usr,".",true);
		if(list.size()!=2) {
			hagRc.rc=1;
			hagRc.log.add("user name must be with one .  ");
			return null;
		}
		String firstName = list.get(0);		
		String lastName = list.get(1);
		//firstName= firstName.substring(0,1).toUpperCase()+firstName.substring(1,firstName.length());
		String newName1= firstName+"."+lastName;
		String newName= newName1.toLowerCase();
		HagSQL hagSQL= new HagSQL();
		String stm = "select * from dbo.hagCfgMenuWebUsers where name1='"+newName+"'";
		HagStringList usersList =hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,6,"|",null,null);
		if(usersList.size()>0) {
			hagRc.rc=1;
			hagRc.log.add("user name "+newName+" already exists ");
			return null;
		}
		return newName;
	}
	public String 	addUsrToTableNew( String usr,String like,String newDomain){
		HagSQL hagSQL = new HagSQL();
		String stm = "select * from dbo.hagCfgMenuWebUsers where name1='"+like+"'";
		HagStringList fromUser =hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,6,"|",null,null);
		
		StringBuilder usersLine = new StringBuilder("'").append(usr).append("','")
				.append("initPass").append("','")
				.append(newDomain).append("','")
				.append(HagUtil.getWord0(fromUser.get(0),"|",3,true)).append("','")
				.append(HagUtil.getWord0(fromUser.get(0),"|",4,true)).append("','")
				.append("x").append("'");
		String ans11 = hagSQL.cfgInsert("hagCfgMenuWebUsers", usersLine.toString());

		String stm2 = "select * from dbo.hagCfgMenuWebUsersAuth where userName1='"+like+"'";
		HagStringList authOfFromUser =hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm2,2,"|",null,null);
			
		boolean flag =true;
		for(int i = 0 ; i < authOfFromUser.size();i++) {
			String auth = HagUtil.getWord0(authOfFromUser.get(i), "|", 1, true);		
			StringBuilder authsLine = new StringBuilder("'").append(auth).append("','").append(usr).append("'");
			String ans2 = hagSQL.cfgInsert("hagCfgMenuWebUsersAuth", authsLine.toString());
			if(!ans2.startsWith("0"))
				flag= false;
		}
		if( !ans11.startsWith("0~")) {
			return "1~Failed to add new user to users table , please check again";
		}
		if(!flag ) {
			return "2~Failed to copy authorization to the new user , please check again";
		}
		return "0~user added";
		
	}	
	public String 	dist(String usr ,String sub) {
		String rc3 =createHtmlNew();
		if(!rc3.startsWith("0~"))
			return HagUtil.shortHtml("failed to create html users file please call hagay", "red", "bg");
		
		//dist();
		return "user "+usr+ " "+sub+" to cfgMenuWeb,please select i-know-what-you-did and only then refresh by clicking the PF(5) key.";
	}
	public  		String 			createHtmlNew(){
		HagSQL hagSQL = new HagSQL();
		String stm = "select * from dbo.hagCfgMenuWebUsers order by note1,name1";
		HagStringList usersList =hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,6,"|",null,null);
	
		
		//String file1 ="\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\hag\\usr.txt";
		String file2     ="\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\hag\\l2r1b.html";
		String file2skel ="\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\hag\\htm.skel";
		//String file21 ="\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\hag\\htm1.html";
		//HagStringList usersList1= new HagStringList(file1,true,"*",false);
		HagStringList usersList2= new HagStringList(file2skel,true,"*",false);
		
		for(int i = 0 ; i < usersList.size();i++) {
			String temp = usersList.get(i);
			String user = HagUtil.getWord0(temp, "|", 0, true);
			String flag = HagUtil.getWord0(temp, "|", 4, true);
			String temp1="<option class=\""+flag+"\"  value=\""+user+"\">"+user+"</option>";
			usersList.set(i, temp1);
		}
		String str1 = usersList.convertToString("\n");
		String str2 = usersList2.convertToString("\n");
		str2=HagUtil.replaceStr(str2, "{hagUsers}", str1);
		String rc9 =backupFile("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\hag","l2r1b.html");
		if(!rc9.startsWith("0~"))
			return HagUtil.shortHtml("failed to backup l2r1b.html file, please call hagay", "red", "bg");
		String rc = HagUtil.writeStringToFile(file2, str2);
		if(!rc.startsWith("0~"))
			return HagUtil.shortHtml("failed to replace l2r1b.html file, please call hagay", "red", "bg");
		String tgt1 = "\\\\CFG-RI2\\webapps\\hagay\\files\\l2r1b.html";
		String rc11 =HagUtil.copyFileViaDos(file2, tgt1);		
		if(!rc11.startsWith("0~"))
			return HagUtil.shortHtml("failed to replace l2r1b.html file on CFG-RI2, please call hagay", "red", "bg");
		
	
		String tgt2 = "\\\\CFG-RI\\webapps\\hagay\\files\\l2r1b.html";
		String rc12 =HagUtil.copyFileViaDos(file2, tgt2);
		if(!rc12.startsWith("0~"))
			return HagUtil.shortHtml("failed to replace l2r1b.html file on CFG-RI, please call hagay", "red", "bg");
		String tgt3 = "\\\\RII-5OS\\webapps\\hagay\\files\\l2r1b.html";
		String rc13 =HagUtil.copyFileViaDos(file2, tgt3);
		if(!rc13.startsWith("0~"))
			return HagUtil.shortHtml("failed to replace l2r1b.html file on RII-5OS, please call hagay", "red", "bg");
			
		return "0~done";
	}
	public  		String 			backupFile(String path,String fn){
		String date = HagUtil.getDateTime("yyyyMMddHHmmss");
		String ff1 = path+"\\"+fn;
		String ff2 = path+"\\"+fn+"_"+date;
		String rc =HagUtil.copyFileViaDos(ff1, ff2);
		return rc;
	}
	//////////////////////////////
	/*
	public static final String 	before_addUser(String user) {
	//	String[][] vals = getValues();
		StringBuilder ans = new StringBuilder();
		ans.append("<html><body bgcolor=\"#dddddd\">");
		
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"insertNewUser.jsp\">");
		ans.append("<input type=\"hidden\" name=\"sentBy\" id=\"sentBy\" value = \""+user+"\" maxlength=\"140\" size=\"140\">");
		
		String newUser 	= HagHtmlUtil.getTextField("newUser","80","","");
		HagStringList usersList= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\hag\\usr.txt",true,"*",false);
		String[] usersArr1 = new String[usersList.size()];
		for(int i = 0 ;i < usersList.size();i++)
			usersArr1[i]=HagUtil.getWord0(usersList.get(i), "~", 0, true);
		String usersArr 	= HagHtmlUtil.getSelectField("usersArr",usersArr1);
		
		
		ans.append("<table bgcolor=\"#aaaac\" cellpadding=\"5\" border =\"1\"   >");
		ans.append("<h3>Insert new user to cfgMenuWeb tool</h3>");
		ans.append("<tr bgcolor=\"#9999bb\"> <td>Title</td><td>Value</td><td>Note</td><tr>");
		ans.append("<tr><td>new user</td><td>"+newUser+"</td><td>write the user without @sapiens.com</td><tr>");
		ans.append("<tr><td>like user</td><td>"+usersArr+"</td><td>select like which user</td><tr>");
		ans.append("</table>");
		ans.append("</td></tr>");
		ans.append("</table><br><br>");	
		
		

		ans.append("<INPUT TYPE=SUBMIT value=\"Insert the user\"></FORM>");
		ans.append("</body></html>");
		return ans.toString();
	

	}
	public static final String 	before_setAuthorizations(String user) {
	//	String[][] vals = getValues();
		StringBuilder ans = new StringBuilder();
		ans.append("<html><body bgcolor=\"#dddddd\">");
		
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"setUserAuthorizations.jsp\">");
		ans.append("<input type=\"hidden\" name=\"sentBy\" id=\"sentBy\" value = \""+user+"\" maxlength=\"140\" size=\"140\">");
		

		HagStringList usersList= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\hag\\usr.txt",true,"*",false);
		String[] usersArr1 = new String[usersList.size()];
		for(int i = 0 ;i < usersList.size();i++)
			usersArr1[i]=HagUtil.getWord0(usersList.get(i), "~", 0, true);
		String usersArr 	= HagHtmlUtil.getSelectField("usersArr",usersArr1);
		
		
		ans.append("<table bgcolor=\"#aaaac\" cellpadding=\"5\" border =\"1\"   >");
		ans.append("<h3>set selected user authorizations</h3>");
		ans.append("<tr bgcolor=\"#9999bb\"> <td>Title</td><td>Value</td><td>Note</td><tr>");
	
		ans.append("<tr><td>User</td><td>"+usersArr+"</td><td>select the user</td><tr>");
		ans.append("</table>");
		ans.append("</td></tr>");
		ans.append("</table><br><br>");	
		
		

		ans.append("<INPUT TYPE=SUBMIT value=\"Set Authorizations \"></FORM>");
		ans.append("</body></html>");
		return ans.toString();
	

	}
	public  		String 			fixUser(HagRc hagRc  , String usr,HagStringList usersList){
		
		if(usr==null) {
			hagRc.rc=1;
			hagRc.log.add("user name must not be null");
			return null;
		}
		usr=usr.trim();
		if(usr.indexOf("@")>-1) {
			hagRc.rc=1;
			hagRc.log.add("user name must be without @");
			return null;
		}
		HagStringList list = new HagStringList(usr,".",true);
		if(list.size()!=2) {
			hagRc.rc=1;
			hagRc.log.add("user name must be with one .  ");
			return null;
		}
		String firstName = list.get(0);		
		String lastName = list.get(1);
		firstName= firstName.substring(0,1).toUpperCase()+firstName.substring(1,firstName.length());
		String newName= firstName+"."+lastName;
		for(int i=0;i<usersList.size();i++) {
			String temp = usersList.get(i);
			String temp1= temp.substring(0,temp.indexOf("~")).trim().toLowerCase();
			if(newName.toLowerCase().equals(temp1)) {
				hagRc.rc=1;
				hagRc.log.add("user name "+newName+" already exists ");
				return null;
			}
			
		}
		return newName;
		
	}
	public  		String 			getLike2( String like,HagStringList usersList){
		
		for(int i = 0 ; i < usersList.size();i++) {
			String temp = usersList.get(i).toLowerCase();
			if(temp.toLowerCase().startsWith(like.toLowerCase()))
				return HagUtil.getWord0(temp, "~", 1, true)	;
		}
		return "C1";
	}
	public  		String 			addUsrToList( String usr,String like,HagStringList usersList){
		String 			like2 = getLike2(  like, usersList);
		HagStringList list1 = new HagStringList();
		HagStringList list2 = new HagStringList();
		for(int i = 0 ; i < 3;i++)
			list1.add(usersList.get(i));
		for(int i = 3 ; i < usersList.size();i++)
			list2.add(usersList.get(i));
	
		list2.add(HagUtil.pad(usr,' ', 29, true)+"~"+like2);
		java.util.Collections.sort(list2);
		list1.append(list2);
		String file ="\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\hag\\usr.txt";
		String rc1 = list1.writeToFile(file, false);
		if(!rc1.startsWith("0~"))
			return HagUtil.shortHtml("failed to write usersFile1 please call hagay", "red", "bg");
		String file2 ="\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\hag\\users.txt";
		
		//String file3 ="\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\users3.txt";
		HagStringList usersList2= new HagStringList(file2,true,"*",false);
		//HagStringList usersList2a = new HagStringList();
		String likeLine = null;
		for(int i = 1 ; i < usersList2.size();i++) {
			String temp = usersList2.get(i);
			String user2 = HagUtil.getWord0(temp, "~", 1, true);
			if(user2.toLowerCase().equals(like.toLowerCase())){
					likeLine=temp;	
					break;
			}
		}
		if(likeLine==null)
			return HagUtil.shortHtml("failed to get likeLine please call hagay", "red", "bg");
		String rc9 =backupFile("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\hag","users.txt");
		if(!rc9.startsWith("0~"))
			return HagUtil.shortHtml("failed to backup users.txt file, please call hagay", "red", "bg");
		String w0=HagUtil.getWord0(likeLine, "~",0,true);
		String w4=HagUtil.getWord0(likeLine, "~",4,true);
		usersList2.add(w0+"~"+usr+"~initPass~"+usr+"@sapiens.com~"+w4);
		String rc2 = usersList2.writeToFile(file2, false);
		if(!rc2.startsWith("0~"))
			return HagUtil.shortHtml("failed to write usersFile2 please call hagay", "red", "bg");
		//return "done";
		String rc3 =createHtml();
		if(!rc3.startsWith("0~"))
			return HagUtil.shortHtml("failed to create html users file please call hagay", "red", "bg");
		
		//dist();
		return "user "+usr+ " added to cfgMenuWeb,please refresh by clicking the PF(5) key.";
	}
	
	public  		String 			backupFile(String path,String fn){
		String date = HagUtil.getDateTime("yyyyMMddHHmmss");
		String ff1 = path+"\\"+fn;
		String ff2 = path+"\\"+fn+"_"+date;
		String rc =HagUtil.copyFileViaDos(ff1, ff2);
		return rc;
	}
	public  		String 			createHtml(){
		String file1 ="\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\hag\\usr.txt";
		String file2     ="\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\hag\\htm.html";
		String file2skel ="\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\hag\\htm.skel";
		//String file21 ="\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\hag\\htm1.html";
		HagStringList usersList1= new HagStringList(file1,true,"*",false);
		HagStringList usersList2= new HagStringList(file2skel,true,"*",false);
		
		for(int i = 0 ; i < usersList1.size();i++) {
			String temp = usersList1.get(i);
			String user = HagUtil.getWord0(temp, "~", 0, true);
			String flag = HagUtil.getWord0(temp, "~", 1, true);
			String temp1="<option class=\""+flag+"\"  value=\""+user+"\">"+user+"</option>";
			usersList1.set(i, temp1);
		}
		String str1 = usersList1.convertToString("\n");
		String str2 = usersList2.convertToString("\n");
		str2=HagUtil.replaceStr(str2, "{hagUsers}", str1);
		String rc9 =backupFile("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\hag","htm.html");
		if(!rc9.startsWith("0~"))
			return HagUtil.shortHtml("failed to backup htm.html file, please call hagay", "red", "bg");
		String rc = HagUtil.writeStringToFile(file2, str2);
		if(!rc.startsWith("0~"))
			return HagUtil.shortHtml("failed to replace htm.html file, please call hagay", "red", "bg");
		String tgt1 = "\\\\CFG-RI2\\webapps\\hagay\\files\\l2r1b.html";
		String rc11 =HagUtil.copyFileViaDos(file2, tgt1);
		if(!rc11.startsWith("0~"))
			return HagUtil.shortHtml("failed to replace htm.html file on CFG-RI2, please call hagay", "red", "bg");
		String tgt2 = "\\\\CFG-RI\\webapps\\hagay\\files\\l2r1b.html";
		String rc12 =HagUtil.copyFileViaDos(file2, tgt2);
		if(!rc12.startsWith("0~"))
			return HagUtil.shortHtml("failed to replace htm.html file on CFG-RI, please call hagay", "red", "bg");
		String tgt3 = "\\\\RII-5OS\\webapps\\hagay\\files\\l2r1b.html";
		String rc13 =HagUtil.copyFileViaDos(file2, tgt3);
		if(!rc13.startsWith("0~"))
			return HagUtil.shortHtml("failed to replace htm.html file on RII-5OS, please call hagay", "red", "bg");
		return "0~done";
	}
	public  		String 			after(HttpServletRequest request, HttpServletResponse response){
		HagStringList usersList= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\hag\\usr.txt",true,"*",false);
		
		String 	 newUser 	= request.getParameter("newUser").trim();
		String 	 usersArr			= request.getParameter("usersArr").trim();
		String 	 sentBy				= request.getParameter("sentBy").trim();
		HagRc hagRc =new HagRc();
		newUser = fixUser(hagRc,newUser,usersList);
		if(hagRc.rc!=0) {
			String msg=hagRc.log.convertToString("<br>");
			return HagUtil.shortHtml(msg, "red", "bg");
		}
		String ans =addUsrToList(  newUser, usersArr, usersList);
		return ans;
	
		//addUsrToList(newUser,usersArr,usersList);
		
	
	
		
	}

	public  		String 			afterSetUserAuth(HttpServletRequest request, HttpServletResponse response){
		String 	 usersArr			= request.getParameter("usersArr").trim();
		HagStringList authList1= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\hag\\users.txt",true,"*",false);
		String authList1a = authList1.get(0);
		HagStringList authList2= new HagStringList(authList1a,"}",true);
		String userAuth="";
		for(int i = 1 ; i <authList1.size()-1;i++ ){
			String temp = authList1.get(i);
			if(temp.indexOf("~"+usersArr+"~")>0) {
				userAuth=HagUtil.getWord0(temp,"~",4,true);
				break;
			}
		}
		
		StringBuilder ans1 = new StringBuilder();
		for(int i = 0 ; i <authList2.size()-1;i++ ){
			String temp = authList2.get(i);
			temp=temp.substring(1,temp.length());
			if(userAuth.indexOf("{"+temp+"}")>-1)
				ans1.append("<input type=\"checkbox\" onclick=\"return false\" name=\"cb3\" id=\"cb3\" value=\"").append(temp).append("\" checked >"+temp).append("<br>");
			else
				ans1.append("<input type=\"checkbox\" onclick=\"return false\" name=\"cb3\" id=\"cb3\" value=\"").append(temp).append("\"  >"+temp).append("<br>");

		}

		return ans1.toString();
	
		//addUsrToList(newUser,usersArr,usersList);
		

	}
	public  		String 			tempLoad(){
		String file1 ="\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\hag\\usr.txt";
		String file2     ="\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\hag\\users.txt";
		HagStringList usersList1= new HagStringList(file1,true,"*",false);
		HagStringList usersList2= new HagStringList(file2,true,"*",false);
		HagSQL hagSQL = new HagSQL();
		HagStringList ans1=new HagStringList();
		HagStringList ans2=new HagStringList();
		HagStringList ans3=new HagStringList();
		
		for(int i = 0 ; i < usersList1.size();i++) {
			String temp = usersList1.get(i);
			//String tempU = temp.toUpperCase();
			String user = HagUtil.getWord0(temp, "~", 0, true);
			String userLine=getUserLine(user);
			System.out.println(user+"-"+userLine);
			String pass = HagUtil.getWord0(userLine, "~", 2, true);
			String type = HagUtil.getWord0(userLine, "~", 0, true);
			String auth = HagUtil.getWord0(userLine, "~", 4, true);
			String note=".";
			String domain=user;
			String color = HagUtil.getWord0(temp, "~", 1, true);
			StringBuilder usersLine = new StringBuilder("'").append(user).append("','")
									.append(pass).append("','")
									.append(domain).append("','")
									.append(type).append("','")
									.append(color).append("','")
									.append(note).append("'");
			String ans11 = hagSQL.cfgInsert("hagCfgMenuWebUsers", usersLine.toString());
			if(!ans11.startsWith("0~"))
				ans1.add(ans11);
			HagStringList userAuths=getAuths(auth);
			for(int k = 0 ; k < userAuths.size();k++) {
				String temp3 = userAuths.get(k);
				StringBuilder authsLine = new StringBuilder("'").append(temp3).append("','")
						.append(user).append("'");
				String ans13 = hagSQL.cfgInsert("hagCfgMenuWebUsersAuth", authsLine.toString());
				if(!ans13.startsWith("0~"))
					ans3.add(ans13);
			}
		}
	
	
		HagStringList auths=getAuths(usersList2.get(0));
		for(int i = 0 ; i < auths.size();i++) {
			String temp = auths.get(i);
			String note=".";
			StringBuilder authsLine = new StringBuilder("'")
					.append(temp).append("','")
					.append(note).append("'");
			String ans12 = hagSQL.cfgInsert("hagCfgMenuWebAuth", authsLine.toString());
			if(!ans12.startsWith("0~"))
				ans2.add(ans12);
		}
	
	
		return "ans1<br>"+ans1.convertToString("<br>")+"<br><br>ans2<br>"+ans1.convertToString("<br>")+"<br><br>ans3<br>"+ans1.convertToString("<br>")+"<br>";
	}
	
	public  		String 			getUserLine(String user){
	
		String file2     ="\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\hag\\users.txt";
		HagStringList usersList2= new HagStringList(file2,true,"*",false);
		for(int i = 0 ; i < usersList2.size();i++) {
			String temp = usersList2.get(i);
			//String tempU = temp.toUpperCase();
			//String userU = HagUtil.getWord0(temp, "~", 1, true).toUpperCase();
			String user1= HagUtil.getWord0(temp, "~", 1, true);
			if(user1.equals(user)) {
				return temp;
		
			}
			
		}
		return null;
	}
	public  		HagStringList 			getAuths(String line){
			
			HagStringList authList2= new HagStringList(line,"}",true);
			HagStringList authList3=new 	HagStringList();
			for(int i = 0 ; i <authList2.size()-1;i++ ){
				String temp = authList2.get(i);
				temp=temp.substring(1,temp.length());
				authList3.add(temp);
			}
			return authList3;
	}
	public  		HagStringList 			getAuthsUser(){
		
		HagStringList authList1= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\hag\\users.txt",true,"*",false);
		String authList1a = authList1.get(0);
		HagStringList authList2= new HagStringList(authList1a,"}",true);
		HagStringList authList3=new 	HagStringList();
		for(int i = 0 ; i <authList2.size()-1;i++ ){
			String temp = authList2.get(i);
			temp=temp.substring(1,temp.length());
			authList3.add(temp);
		}
		return authList3;
}
*/
}
