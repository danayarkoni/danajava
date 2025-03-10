package com.hag.hagay;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.X509Certificate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HagReqRelease {
	public static final String 	before(String user) {
		String sentBy="<input type=\"text\" id=\"sentBy\"  name=\"sentBy\" readonly size=\"40\" style=\"background:black; color:gold;\" value = \""+user+"\">";	
		
		StringBuilder jiraVer =new StringBuilder("<select name=\"jiraVer\">");	
		String file = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\versions.txt";
		HagStringList ans3a = new HagStringList(file,true,"*",false);
		for (int i=0;i<ans3a.size();i++){
			String temp = ans3a.get(i);
			String active =HagUtil.getWord0(temp, "|", 0, true);
			if(active.equalsIgnoreCase("Y")){
				String jiraVer1 =  HagUtil.getWord0(temp, "|", 1, true);
				String key1 =  HagUtil.getWord0(temp, "|", 2, true);
				String configVer =  HagUtil.getWord0(temp, "|", 6, true);
				jiraVer.append("<option class=\"c30\" value=\"").append(jiraVer1+"|"+key1+"|"+configVer).append("\">").append(jiraVer1).append("</option>");
			}
		}
		jiraVer.append("</select>");	
		
	//	String jiraVer1 = getReleaseVersions(); 
		
		StringBuilder sb = new StringBuilder("<HTML><body bgcolor=\"#ccccbb\">")	
		.append("<font size=6 color=\"GREEN\"><u>Release-Request (new) form:").append("</u></font><br><br>")
		.append("<font size=4 color=\"blue\"><u>send a Release-Request:1").append("</u></font><br><br>")
		.append("<font size=2 color=\"blue\">Select the correct option and submit the form</font><br><br>")
		.append("<FORM METHOD=POST name=\"Form\" ACTION=\"RiReleaseRequestNew.jsp\">")
		.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:70%\">")
		.append("<tr><td bgcolor=\"#ccccaa\">jira version</td><td>").append(jiraVer.toString()).append("</td></tr>")
	//	.append("<tr><td bgcolor=\"#ccccaa\">jira version</td><td>").append(jiraVer1).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Sent by</td><td>").append(sentBy.toString()).append("</td></tr>")
		.append("</table><br><INPUT TYPE=SUBMIT value=\"check the request\"></form></body></html>");
		return sb.toString();
	

	}
	public String 	riReleaseRequest(HttpServletRequest request, HttpServletResponse response){
		String sentBy 	= request.getParameter("sentBy").trim();
		String jiraVer 	= request.getParameter("jiraVer").trim();
		//if(jiraVer.equals("00.00M01"))
	//		jiraVer="Trunk";
		String jiraVer0000 =""+jiraVer;
		if(jiraVer.equals("00.00M01"))
			jiraVer0000="Trunk";
		return reqReleaseSend(jiraVer0000,sentBy);
	}
	public   String reqReleaseSend(String jiraVerWithKey,String user){
		boolean rcc=true;
		HagRc hagRc=new HagRc();
		String note="<input type=\"text\" id=\"note\"  name=\"note\" size=\"80\">";	
		StringBuilder extraDev=new StringBuilder("<input type=\"text\" id=\"extraDev\"  name=\"extraDev\" readonly size=\"80\" style=\"background:black; color:gold;\" value = \"");
		
		StringBuilder whenTo =new StringBuilder("<select name=\"whenTo\">")
		.append("<option value=\"").append("At night (if possible)").append("\">").append("At night (if possible)").append("</option>")
		.append("<option value=\"").append("ASAP").append("\">").append("ASAP").append("</option>")
		.append("<option value=\"").append("When you are bored").append("\">").append("When you are bored").append("</option>")
		.append("</select>");	
		
		


		String jiraVer1=HagUtil.getWord0(jiraVerWithKey, "|", 0,true);
		
		String key1=HagUtil.getWord0(jiraVerWithKey, "|", 1,true);
		String deleleFromMigDetailsVer=HagUtil.getWord0(jiraVerWithKey, "|", 2,true);
		String sentBy="<input type=\"text\" id=\"sentBy\"  name=\"sentBy\" readonly size=\"40\" style=\"background:black; color:gold;\" value = \""+user+"\">";	
		//StringBuilder sentBy =new StringBuilder("<select name=\"sentBy\"><option value=\"").append(user).append("\">").append(user).append("</option></select>");	
		String jiraVer="<input type=\"text\" id=\"jiraVer\"  name=\"jiraVer\" readonly size=\"40\" style=\"background:black; color:gold;\" value = \""+jiraVer1+"\">";	
		//StringBuilder jiraVer =new StringBuilder("<select name=\"jiraVer\"><option value=\"").append(jiraVer1).append("\">").append(jiraVer1).append("</option></select>");	
		
		String topack =checkRequest_topack(hagRc, jiraVer1,key1);
		if(hagRc.rc!=0){
			hagRc.log.add("<font color=red>checkRequest in topack folder failed.</font>");
			hagRc.log.add(topack);
		}
		String components =checkRequest_components(hagRc, jiraVer1,key1,extraDev);	
	
		
		extraDev.append("\">");
	
		//
		HagSQL hagSQL = new HagSQL();
		String stm1 = "select bis_server,batchName,locks,locksDetails from dbo.ri_environments_new where  status='A' and oded='"+key1+"' order by bis_server";
		String stm2 = "select server,batchName from dbo.ri_environments_i5os where oded='"+key1+"' order by server";
		//stm = HagUtil.replaceStr(stm,"{SERVER}","'"+server+"'");
		ArrayList<String> results1 = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,4,"~",null,null);
		ArrayList<String> results2 = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm2,2,"~",null,null);

	
		StringBuilder winEnvToInstall = new StringBuilder();
		StringBuilder  as400EnvToInstall = new StringBuilder();
		String prev="000";
		if(results1.size()==0)
			winEnvToInstall.append("No environments to install.");
		else{
			winEnvToInstall.append("<table  cellpadding=\"10\"><tr  valign=\"top\"><td>");
			for(int i = 0 ; i < results1.size();i++){
				String temp = results1.get(i);
				String w1 = HagUtil.getWord0(temp,"~", 0,true);
				String w2 = HagUtil.getWord0(temp,"~", 1,true);
				String w3 = HagUtil.getWord0(temp,"~", 2,true);
				String w4 = HagUtil.getWord0(temp,"~", 3,true);
				String env = w1+"-"+w2;
				String locks = "";
				if(w3.equals("LOCKED")){
					locks="<font color=#ff0000>locked:"+w4+"</font>";
				}
				
				if(prev.equals("000"))
					winEnvToInstall.append("<input type=\"checkbox\" name=\"cb1\" id=\"cb1\" value=\"").append(env).append("\" checked>"+env+locks);
				else if(w1.equals(prev))
					winEnvToInstall.append("<br><input type=\"checkbox\" name=\"cb1\" id=\"cb1\" value=\"").append(env).append("\" checked>"+env+locks);
				else
					winEnvToInstall.append("</td><td><input type=\"checkbox\" name=\"cb1\" id=\"cb1\" value=\"").append(env).append("\" checked>"+env+locks);
				prev=w1;
			}			
			winEnvToInstall.append("</td></tr></table>");
		
		}
		
		 prev="000";
		if(results2.size()==0)
			as400EnvToInstall.append("No environments to install.");
		else{
			as400EnvToInstall.append("<table  cellpadding=\"10\"><tr  valign=\"top\"><td>");
			for(int i = 0 ; i < results2.size();i++){
				String temp = results2.get(i);
				String w1 = HagUtil.getWord0(temp,"~", 0,true);
				String w2 = HagUtil.getWord0(temp,"~", 1,true);
				String env = w1+"-"+w2;
				if(prev.equals("000"))
					as400EnvToInstall.append("<input type=\"checkbox\" name=\"cb2\" id=\"cb2\" value=\"").append(env).append("\" checked>"+env);
				else if(w1.equals(prev))
					as400EnvToInstall.append("<br><input type=\"checkbox\" name=\"cb2\" id=\"cb2\" value=\"").append(env).append("\" checked>"+env);
				else
					as400EnvToInstall.append("</td><td><input type=\"checkbox\" name=\"cb2\" id=\"cb2\" value=\"").append(env).append("\" checked>"+env);
				prev=w1;
			}
			as400EnvToInstall.append("</td></tr></table>");
		}
		if(hagRc.log.size()>0){
			StringBuilder sb = new StringBuilder("<HTML><body bgcolor=\"#ccccbb\">")	
			.append("<font size=6 color=\"RED\"><u>Release-Request failed:").append("</u></font><br><br>");
			for(int i = 0 ; i <hagRc.log.size();i++ ){	
				sb.append(hagRc.log.get(i)+"<br>");
			}
			sb.append("</body></html>");
			return sb.toString();	
		}else{
			String deleteFromMigDetailsListS = getFromMigDetailsTable( deleleFromMigDetailsVer);
			StringBuilder sb = new StringBuilder("<HTML>")	
			.append("<script language=\"JavaScript\">")
			.append("function toggle(source) {")	
			.append("checkboxesW = document.getElementsByName('cb1');")
			.append("checkboxesA = document.getElementsByName('cb2');")	
			.append("for(var i=0, n=checkboxesW.length;i<n;i++) { checkboxesW[i].checked = !source.checked;  }")
			.append("for(var i=0, n=checkboxesA.length;i<n;i++) {  checkboxesA[i].checked = !source.checked;  }")	
			.append("first_extra_dev.value=source.checked")	

			
			.append("}</script>")
			.append("<body bgcolor=\"#ccccbb\">")	
			.append("<font size=6 color=\"GREEN\"><u>Release-Request new form:").append("</u></font><br><br>")
			.append("<font size=4 color=\"blue\"><u>send a Release-Request:5").append("</u></font><br><br>")
			.append("<FORM METHOD=POST name=\"Form\" ACTION=\"RiReleaseRequestSendNew.jsp\">")
			.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:100%\">")
			.append("<tr><td bgcolor=\"#ccccaa\">jira version</td><td>").append(jiraVer).append("</td></tr>")
			.append("<tr><td bgcolor=\"#ccccaa\">Sent by</td><td>").append(sentBy).append("</td></tr>")
			.append("<tr><td bgcolor=\"#ccccaa\">Topack folder check</td><td>").append(topack).append("</td></tr>")
			.append("<tr><td bgcolor=\"#ccccaa\">Components check</td><td>").append(components).append("</h4></td></tr>")
			//.append("<tr><td bgcolor=\"#ccccaa\">porting</td><td>").append(porting.toString()).append("</td></tr>")
			.append("<tr><td bgcolor=\"#ccccaa\">Note</td><td>").append(note).append("</td></tr>")
			.append("<tr><td bgcolor=\"#ccccaa\">Extra DEV</td><td>").append(extraDev)
			.append("&nbsp;&nbsp;&nbsp;<input type=\"checkbox\"   onClick=\"toggle(this)\" >first request of extra-dev multi request")
			.append("</td>").append("</tr>")
			
			.append("</tr>")
			.append("<tr><td bgcolor=\"#ccccaa\">win environments<br>to install</td><td>").append(winEnvToInstall.toString()).append("</td></tr>")
			.append("<tr><td bgcolor=\"#ccccaa\">as400 environments<br>to install</td><td>").append(as400EnvToInstall.toString()).append("</td></tr>")
			.append("<tr><td bgcolor=\"#ccccaa\">when to run<br>the installations</td><td>").append(whenTo).append("</td></tr>")
			.append("<tr><td bgcolor=\"#ccccaa\">Migs to pre-delete from MigDetails table<br>you can change this list via cfgMenuWeb RI->Release->DeleteFromMigDetails option</td><td>").append(deleteFromMigDetailsListS).append("</td></tr>")
		 // .append("<input type=\"hidden\" name=\"first_extra_dev\" id=\"first_extra_dev\"  >")
			.append("</table><br><INPUT TYPE=SUBMIT value=\"send the request\"></form></body></html>");
			String aa= sb.toString();
			return sb.toString();
		}
	}	
	public static String checkRequest_topack(HagRc hagRc,String jiraVer1,String jiraVer){
		HagRiRel hagRiRel = new HagRiRel(jiraVer);
		String ver 			= hagRiRel.getRiVer();
		HagStringList ans=new HagStringList();
	//	ans.add("TOPACK folder check:");
		HagStringList ans1 = checkTopackFolder(hagRc, ver);
		ans.append(ans1);
		return ans.convertToString("<br>");
	}
	public static final String getFromMigDetailsTable(String ver){
    	String stm = "select jclass," +
    				"location," +
    				"customer," +
    				"owner," +
    				"date," +
       				"note " +
    				" from dbo.DeleteFromMigDetails where version ='"+ver+"' AND relevant='YES' AND status='ON'";
    	HagSQL hagSql=new HagSQL();
    	HagStringList results = hagSql.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,6,"|",null,null);
    	HagStringList list = new HagStringList();
    	list.add("<table  bgcolor=\"#82E0AA\" border=\"1\"  CELLPADDING=\"3\"><tr bgcolor=\"#27AE60\"><td >jclass</td> <td>location</td> <td>customer</td> <td>owner</td><td>from date</td>  <td>note</td></tr>");
    	for(int i = 0 ;i < results.size();i++) {
    		String temp = results.get(i);
    		String temp1 = temp.replace("|","</td><td>");
    		list.add("<tr><td>"+temp1+"</td></tr>");
    		
    	}
    	list.add("</table>");
    	String ans = list.convertToString(" ");
    	return ans;
		
	}
	public static HagStringList checkTopackFolder(HagRc hagRc,String ver) {
		hagRc.rc=0;
		HagStringList ans = new HagStringList();
		String folder1 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V" + ver + "m00\\TOPACK\\ri-components\\";
		String folder = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V" + ver + "m00\\TOPACK\\ri-components\\RiInstaller\\";
		if (ver.length() > 4)
			folder = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V" + ver + "u00\\TOPACK\\ri-components\\RiInstaller\\";

		// ri-web.war
		String war = folder + "ri-web.war";
		File warF = new File(war);
		if (warF.exists()) {
			String lastModified = HagUtil.getFileLastModified(warF,"dd/MM/yyyy-HH:ss");
			ans.add("0~ ri-web.war file found in " + folder+"<FONT color=\"#FF0000\"> (last modified at: "+lastModified+")</Font>");
			ans.add("<font color=green>0~ ri-web.war file found in " + folder+"</font>");
		}else{
			ans.add("<font color=red>1~ ri-web.war file not found in " + folder+"</font>");
			hagRc.rc=1;
		}
		// mig-mng.jar
		String jar = folder + "mig-mng.jar";
		File jarF = new File(jar);
		if (jarF.exists()) {
			String lastModified = HagUtil.getFileLastModified(jarF,"dd/MM/yyyy-HH:ss");
			ans.add("0~ mig-mng.jar file found in " + folder+"<FONT color=\"#FF0000\"> (last modified at: "+lastModified+")</Font>");
			ans.add("<font color=green>0~ mig-mng.jar file found in " + folder+"</font>");
		}else{
			ans.add("<font color=red>1~ mig-mng.jar file not found in " + folder+"</font>");
			hagRc.rc=1;
		}
		// eObject
		String script = folder1 + "EOBJECTS\\metadata.htm";
		File scriptF = new File(script);
		if (scriptF.exists())
			ans.add("<font color=green>0~ EOBJECTS\\metadata.htm file found in " + folder1+"</font>");
		else{
			ans.add("<font color=red>1~ EOBJECTS\\metadata.htm file not found in " + folder1+"</font>");
			hagRc.rc=1;
		}
		// webProxy as folder
		if (ver.equals("0801")) {
			String folder12 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V" + ver + "m00";
			String webProxyS = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V" + ver + "m00\\TOPACK\\WebProxyAsFolder\\WebProxy\\web.config";
			
				File webProxyF = new File(webProxyS);
				if (webProxyF.exists() && webProxyF.isFile() )
					ans.add("<font color=green>0~ (0801 ver) TOPACK\\WebProxyAsFolder\\WebProxy\\web.config file found in " + folder12+"</font>");
				else{
					ans.add("<font color=red>1~ (0801 ver) TOPACK\\WebProxyAsFolder\\WebProxy\\web.config file not found in " + folder12+"</font>");
					hagRc.rc=1;
				}
		}
		return ans;
	}
	public String 	riReleaseRequestSend(HttpServletRequest request, HttpServletResponse response){
		StringBuilder ans = new StringBuilder("<h3>Release-Request</h3><table bgColor=\"#aaaaaa\" cellpadding=\"3\" border =\"1\">");
		String sentBy 	= request.getParameter("sentBy").trim();
		String jiraVer 	= request.getParameter("jiraVer").trim();
		String whenTo 	= request.getParameter("whenTo").trim();
		String note 	= request.getParameter("note").trim();
	//	String first_extra_dev 	= request.getParameter("first_extra_dev").trim();


		if(note==null || note.trim().length()==0)
			note="None";
		
		String extraDev 	= request.getParameter("extraDev").trim();
	//	String porting = request.getParameter("porting").trim();
		String [] cbgroup1		= request.getParameterValues("cb1");
		String [] cbgroup2		= request.getParameterValues("cb2");
		String [] cbgroup3		= request.getParameterValues("cb3");
		StringBuilder jiraTasks=new StringBuilder();
		//		String winInstEnv = "No environments to install.";
		//		String as400InstEnv = "No environments to install.";
		//		String components = "No components to release.";
		ans.append("<tr><td>jiraVer</td><td>").append(jiraVer).append("</td></tr>");
		ans.append("<tr><td>Sent by</td><td>").append(sentBy).append("</td></tr>");
	//	ans.append("<tr><td>Porting</td><td>").append(porting).append("</td></tr>");
		ans.append("<tr><td>Note</td><td>").append(note).append("</td></tr>");
		ans.append("<tr><td>extraDev</td><td>").append(extraDev).append("</td></tr>");
		ans.append("<tr><td>Components to release</td><td>");
		if (cbgroup3 != null && cbgroup3.length > 0){
			for(int i = 0 ; i < cbgroup3.length ; i++){
				ans.append(cbgroup3[i]).append("<br>");
				jiraTasks.append(cbgroup3[i]).append("<br>");
			}
		}
		ans.append("</td></tr>");
		ans.append("<tr><td>Win envs to install</td><td>");
		HagStringList listWin = new HagStringList();
		if (cbgroup1 != null && cbgroup1.length > 0){
			for(int i = 0 ; i < cbgroup1.length ; i++){
				ans.append(cbgroup1[i]).append("<br>");
				//String converted1 = convert1(cbgroup1[i]);
				listWin.add(cbgroup1[i]);
			}
		}else{
			ans.append("None");
		}
		ans.append("</td></tr>");
		ans.append("<tr><td>as400 envs to install</td><td>");
		HagStringList listAs400= new HagStringList();
		if (cbgroup2 != null && cbgroup2.length > 0){
			for(int i = 0 ; i < cbgroup2.length ; i++){
				ans.append(cbgroup2[i]).append("<br>");
			//	String converted1 = convert1(cbgroup2[i]);
				listAs400.add(cbgroup2[i]);
			}
		}else{
			ans.append("None");
		}

		String extraDevFlag=" ";
		if(listWin.size()==0 && listAs400.size()==0)
			extraDevFlag=" (no envs to install) ";

		ans.append("</td></tr>");
		ans.append("<tr><td>When to install</td><td>").append(whenTo).append("</td></tr>");
		ans.append("</table>");
		StringBuilder cfg = new StringBuilder("<h3>cm team:</h3>");
	//	StringBuilder distTo = new StringBuilder("PORTING=").append(porting).append("<br>"); 
	
		StringBuilder distTo = new StringBuilder();
		composeReqFile( listWin, jiraVer,	 cfg ,true,distTo);
		cfg.append("<br>");
		composeReqFile( listAs400, jiraVer,	 cfg ,false,distTo);
//
		String int1= HagUtil.getVersionTxtVal(jiraVer, 4);
		String server1= HagUtil.getVersionTxtVal(jiraVer, 5);
	//	copied = "<td bgColor=\""+colorG+"\">0~"+jarSrc+" copied to "+jarTgtToCopy+"</td></tr>"; 
		
		HagUtil.writeToRelDiary2("Release","WIN","Request",jiraVer,"00","OK",distTo.toString(),jiraTasks.toString(),"cfgMenuWeb",sentBy,server1,int1);

		String ccList 	= HagUtil.getRiMails(jiraVer);
		//String ccList 	= "david.ha@sapiens.com";
		int ind = HagUtil.getRequestInd();
		
		String indS = HagUtil.padNumLeft(""+ind, 5, '0');
//
	//	String subject = "#Request to install "+jiraVer+" on INT-WIN , @BREAK-REQ@ #"+indS+"#  ("+whenTo+") "+extraDevFlag+" sent by "+sentBy;
			String subject = "#Request to install "+jiraVer+" on INT-WIN ("+whenTo+") - @BREAK-REQ@ #"+indS+"#"+extraDevFlag+"sent by "+sentBy;
		//spr1007
			String winInstallOn=null;
			String as400InstallOn=null;
			String  whenToInstall="ASAP";
			if(listAs400!=null && listAs400.size()>0)
				as400InstallOn=listAs400.convertToString(" ");
			if(listWin!=null && listWin.size()>0)
				winInstallOn=listWin.convertToString(" ");
			if(whenTo!=null && whenTo.indexOf("night")>-1)
				whenToInstall="Night";
		if(!HagUtil.addRequest(ind,sentBy, ".",subject,"./.",".",".",winInstallOn,as400InstallOn,whenToInstall,null,997))
			return HagUtil.shortHtml("Error adding request-mail sent to hagay & gonen, please call hagay or gonen", "red","bg");
		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+sentBy,HagUtil.mailList_hag,subject,cfg.toString()+ans.toString());
		String ans2		= HagUtil.sendSms("0528399706;0528399727", "david.ha@sapiens.com",subject, "cfgMenuRequest");

		return ans1+"<br>"+ans2+"<br>"+ans.toString();
	
	}
	private void composeReqFile(HagStringList list,String verJira,	StringBuilder cfg,boolean isWin,StringBuilder distTo ) {
		HagStringList servers = new HagStringList();
		for (int i = 0; i < list.size(); i++) {
			String temp = list.get(i);
			
			String temp1 = temp.substring(0,temp.lastIndexOf("-"));
			if (!servers.isInList(temp1)) {
				servers.add(temp1);
			}
		}
		String serversLine = servers.convertToString("&nbsp;&nbsp;&nbsp;");
		for (int i = 0; i < list.size(); i++) {
			String temp = list.get(i);
//			String temp1 = HagUtil.getWord0(temp, "-", 0, true);
	//		String temp2 = HagUtil.getWord0(temp, "-", 1, true);

			String temp1 = temp.substring(0,temp.lastIndexOf("-"));
			String temp2 = temp.substring(temp.lastIndexOf("-")+1,temp.length());

			
			if(isWin  && temp2.equalsIgnoreCase("RI"))
				temp2 = "RIAPPLDB";
			for (int k = 0; k < servers.size(); k++) {
				String temp3 = servers.get(k);
				if (temp3.startsWith(temp1)) {
					String temp4 = servers.get(k);
					String delim = ",";
					if (temp4.equals(temp1))
						delim = "(";
					servers.set(k, temp4 + delim + temp2);
				}
			}
		}
		for (int i = 0; i < servers.size(); i++) {
			servers.set(i, servers.get(i) + ") ");
			//str.append(servers.get(i));
		}
		distTo.append(servers.convertToString("&nbsp;")).append("<br>"); 
		cfg.append("=>=>cb&nbsp;").append(verJira).append("&nbsp;&nbsp;&nbsp;").append(servers.convertToString("&nbsp;")).append("&nbsp;<=<=<br>=>=>ms&nbsp;").append(serversLine).append("&nbsp;<=<=<br>");
		if(isWin){
			String hyun = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\";
			String ff1 = hyun+"temp\\" + verJira + "_new.req";
			String ff2 = hyun+"temp\\" + verJira + "_hat.req";
			HagUtil.deleteFile(ff1,  false);
			servers.writeToFile(ff1, null, false);
			HagUtil.deleteFile(ff2,  false);
			servers.writeToFile(ff2, null, false);
		}
	}
	public  		String checkRequest_components(HagRc hagRc,String jiraVer1,String jiraVer,StringBuilder extraDev9){
		hagRc.rc=0;
		HagRiRel hagRiRel = new HagRiRel(jiraVer);
		String extraDev1 = hagRiRel.getExtraDev();
		String extraDev = ""+extraDev1;
		String affectsVersion = "";
		if(extraDev1.indexOf("!")>-1){
			extraDev = HagUtil.getWord0(extraDev1, "!", 0, true);
			affectsVersion = HagUtil.getWord0(extraDev1, "!", 1, true);
			extraDev9.append(extraDev1);
		}else{
			if(!extraDev.equalsIgnoreCase("NO")){
				extraDev9.append("Error: ri.properties");
				return HagUtil.shortHtml("Error: ri.properties extraDev - please call hagay","red","bg");
				//hagRc.rc=1;
				//hagRc.log.add("Error: ri.properties extraDev - please call hagay");
			//	return null;
			}
			extraDev9.append("NO");
		}
		//ans.add("Components check:");
		//hagay1
		HagStringList ans2 = riWinJira_components(hagRc,hagRiRel,  extraDev,affectsVersion,extraDev9);
		if(hagRc.rc==3 ){
			hagRc.log.add("<font color=red>Jira \"Affects versions\"  field must be the same in all the tickets</font>");
			HagStringList ans3 = HagUtil.prepMsgSrcVer(ans2);
			hagRc.log.append(ans3);
		}
		//affVerCheck
		hagRc.rc=0;
		String affCurVal="hagInit";
		
		for(int i = 0 ; i<ans2.size();i++){
			String temp = ans2.get(i);
			String aff = HagUtil.getWord0(temp,"~",4,true);
			if(affCurVal.equals("hagInit") || affCurVal.equals("emptyTicket"))
				affCurVal=aff;
			else{
			//	System.out.println("*"+aff+"!"+affCurVal+"*");
				if(!aff.equalsIgnoreCase(affCurVal) && !aff.equals("emptyTicket") ){
					hagRc.rc=1;
				}
			}
		}
		//if(hagRc.rc!=0 && !extraDev.equalsIgnoreCase("NO")){
		//	hagRc.log.add("<font color=red>Jira \"Affects versions\"  field must be the same in all the tickets</font>");
		//	HagStringList ans3 = HagUtil.prepMsgSrcVer(ans2);
		//	hagRc.log.append(ans3);
		//}
		//return ans2;
		StringBuilder ans1 = new StringBuilder();
		for(int i = 0 ; i <ans2.size();i++ ){
			String temp = ans2.get(i);
			ans1.append("<input type=\"checkbox\" onclick=\"return false\" name=\"cb3\" id=\"cb3\" value=\"").append(temp).append("\" checked >"+temp).append("<br>");
		}
		
		
		
		return ans1.toString();
	}
	public  			HagStringList riWinJira_components(HagRc hagRc,HagRiRel hagRiRel,String extraDev,String affectsVersion,StringBuilder extraDev9) {
		String ver 			= hagRiRel.getRiVer();
		String jiraVer 		= hagRiRel.getRiVerJira();
		String iomFolder 	= hagRiRel.getIomUncDev();
		String tasksTable 	= hagRiRel.getTasksTable();
	//	String devBatchName = hagRiRel.getDevBatchName();
		String jiraVer0000 =""+jiraVer;
		if(jiraVer.equals("00.00M01"))
			jiraVer0000="Trunk";
		HagStringList listJira = new HagStringList();
		//	listJira.add("Components to release:<br>");
		
		//HagJiraWin hagJiraWin = new HagJiraWin();
		HagStringList ans2 = rismt2(ver, jiraVer0000 ,extraDev);
		
		String checkHag = checkExtraDevCurDev(ans2,extraDev,jiraVer,extraDev9);
		
		if(checkHag.equals("error")){
			for(int i=0;i<ans2.size();i++)
				listJira.add(ans2.get(i));
			hagRc.rc=3;
			//hagRc.log.add("aaaaaaaaaaaaaaaa");
			return listJira;
		}
		//replace the task table
		if(!extraDev.equalsIgnoreCase("NO")){  //define with extra dev from hagay
			if(!checkHag.equalsIgnoreCase("NN")){ //extra dev from oded = yes
				if( !checkHag.equalsIgnoreCase(hagRiRel.getRiVerJira()) ){ //extra dev in ticket != jiradev
					HagRiRel hagRiRelExtraDev = new HagRiRel(extraDev);
					iomFolder = hagRiRelExtraDev.getIomUncDev();
					tasksTable = hagRiRelExtraDev.getTasksTable();
				}
			}
		}
		
		checkComponents( hagRc,ans2, iomFolder,tasksTable);
		if(hagRc.rc!=0)
			hagRc.log.add("components check failed.");
		//String checkRCS = "0~Check components";
		if (ans2.size() == 0) {
			listJira.add("<font color=red>No components to release.</font>");
			return listJira;
		}
		for (int i = 0; i < ans2.size(); i++) {
			String temp = ans2.get(i);
			listJira.add(temp);
		//		if (!temp.startsWith("0~"))
		//			checkRCS = "1~Check components";
		}
		return listJira;
	
	}
	private static 		String checkExtraDevCurDev(ArrayList<String> list,String extraDev,String jiraVer,StringBuilder extraDev9){
		for(int i = 0 ; i< list.size();i++){
			String temp= list.get(i);
			if(temp.indexOf("error:")>-1)
				if(temp.indexOf("will be ignored")<0)
					return "error";
		}
		if(extraDev.equals("NO"))
			return "NN";
		HagRiRel hagRiRel1 = new HagRiRel(extraDev);
		String extraJiraVer = hagRiRel1.riVerJira;
		int extraCount=0;
		int curCount=0;
		for(int i = 0 ; i< list.size();i++){
			String temp= list.get(i);
			if(temp.endsWith("~"+jiraVer))
				curCount++;
			if(temp.endsWith("~"+extraJiraVer))
				extraCount++;

		}
			
		if(extraCount==0 && curCount == 0)
			return "NN";
		if(extraCount>0 && curCount > 0)
			return "error";
		if(extraCount>curCount){
			extraDev9.append("(ACTIVE)");
			return extraJiraVer;
		}else{
			extraDev9.append("(NOT ACTIVE)");
			return jiraVer;
		}
				
	
	}
	public static final void checkComponents(HagRc hagRc,HagStringList ans2 ,String iomFolder,String tasksTable){
		//"0~check components to release";
		//HagUtil.p(tasksTable);
		hagRc.rc=0;
		for(int i = 0 ; i < ans2.size();i++){
			String temp = ans2.get(i);
			String ticket= HagUtil.getWord0(temp,"~",1,true);
			String type= HagUtil.getWord0(temp,"~",2,true);
			String name= HagUtil.getWord0(temp,"~",3,true);
			String affVer= HagUtil.getWord0(temp,"~",4,true);
			ArrayList<String> ww= new ArrayList<String>();
			ww.add("0");
			ww.add("no checks");
			if(type.equals("TSK")) {
				ww = checkComponerntsTask(name,ans2,tasksTable);
				}
			if(type.equals("IOM")) {
				ww = checkComponerntsIom(iomFolder,name);
			}
			if(type.equals("CMD") || type.equals("RX")) {
				ww = checkComponerntsAs400(name);
			}
			String pre = HagUtil.left(ww.get(0)+"~"+ticket+"~"+type+"~"+name+"~"+affVer," ", 50,".");
			
			String color = "<font color=red>";
			if(ww.get(0).endsWith("0"))
				color = "<font color=green>";
			ans2.set(i,color+pre+"~"+ww.get(1)+"</font>");
			if(!ww.get(0).equals("0")){
				hagRc.log.add(color+pre+"~"+ww.get(1)+"</font>");
				hagRc.rc=1;
			}
		}
	}
	public static final ArrayList<String> checkComponerntsTask(String task,ArrayList<String> list,String tasksTable ){
		HagSQL hagSQL = new HagSQL();
		 String stm = "SELECT STATUS FROM "+tasksTable+" WHERE TASKNO='"+task+"'";

		 ArrayList<String> ans = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm);
		 ArrayList<String> ans1= new ArrayList<String>();
		 if (	ans == null ){ 
			ans1.add("1");
			ans1.add("NULL");
			return  ans1;
		 }

		 if (	ans.size() == 0){ 
			ans1.add("1");
			ans1.add("task not found in "+tasksTable);
			return  ans1;
		 }
		 String temp = ans.get(0).trim();
	
		 
		 if (temp.equals("Could not connect to the database")){
			ans1.add("1");
			ans1.add("Could not connect to the database");
			return  ans1;
		 }
		 if(temp.equals("OPN")){
			ans1.add("1");
			ans1.add("task status = OPN.("+tasksTable+")");
			return  ans1;
		 }
		 HagStringList listT =getExistInTaskList(task, list);
		 if(listT.size() > 1){
			 String ansT = listT.convertToString(" and ");
			 ans1.add("1");
		     ans1.add("task status = DUP.("+tasksTable+") "+ansT);
		     return  ans1;
		 }
		ans1.add("0");
		ans1.add("task status = CLS.("+tasksTable+")");
		return  ans1;
		 
	}
	public static final HagStringList getExistInTaskList(String task,ArrayList<String> list ){
		HagStringList rc = new HagStringList();
		for(int i = 0 ; i < list.size();i++){
			String temp = list.get(i);
			String w1 = HagUtil.getWord0(temp,"~", 1, true);
			String w2 = HagUtil.getWord0(temp,"~", 2, true);
			String w3 = HagUtil.getWord0(temp,"~", 3, true);
			if(w2.endsWith("TSK") && w3.equals(task)){
				rc.add(w1+"~"+w2+"~"+w3);
			}
		}
		return rc;
	}
	public static final ArrayList<String> checkComponerntsIom(String iomFolder,String iom ){
		ArrayList<String> ans1= new ArrayList<String>();
		File ff = new File(iomFolder+"\\"+iom+".dll");
       
		 if (!ff.exists()){
			ans1.add("1");
			ans1.add("IOM not found in DEV");
			return  ans1;
		 }
		ans1.add("0");
		ans1.add("IOM found in DEV");
		return  ans1;
		 
	}	
	public static final ArrayList<String> checkComponerntsAs400(String name ){
		ArrayList<String> ans1= new ArrayList<String>();
		String chk = isAs400ValidName(name);
		if (!chk.startsWith("0~")){
			ans1.add("1");
			ans1.add(chk.substring(2,chk.length()));
			return  ans1;
		}
		ans1.add("0");
		ans1.add("valid as400 name");
		return  ans1;
		 
	}	
	public static final String isAs400ValidName(String name){
		String valid = "ABCDEFGFIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz#";
		if(name==null)								return "1~name is null";
		if(name.length()==0)						return "1~name length = 0";
		if(name.length()>10)						return "1~name exceeds 10 characters";
		if(valid.indexOf(name.substring(0,1))<0)	return "1~name first character not valid";
		return "0~valid As400 name";
	}
	public  			HagStringList rismt2(String ver,String jiraVer,String extraDev	){
		String w2 = "QRAPRQA";

		w2=w2.replace(' ', '!');
		w2 = replaceFromReqFile(w2);

		//get components
		//HagStringList ans2 = sendReq(ver,"RISTM2B",jiraVer,w2,extraDev	);
		String ans1 = getIssuesByJql("json","REQUEST FOR INT",true,jiraVer,null,extraDev);
		if(ans1!=null && ans1.trim().length()>0) {
			HagStringList ans2 = new HagStringList(ans1,"<br>",true);
			HagStringList ans3 = convertToOldFormat(ans2);
			return ans3;
		}else
			return new HagStringList();
		
	}
	public  		HagStringList convertToOldFormat(HagStringList oldF){
		HagStringList newF = new HagStringList();
		//AIG-72~!~TSK~!~55007~!~08.01M01~!~08.01M01
		//INIT~35628~TSK~55001~08.01M01
		for(int i=0;i<oldF.size();i++) {
			String temp = oldF.get(i);
			String w0 = HagUtil.getWord0(temp, "~!~", 0, true);
			String w1 = HagUtil.getWord0(temp, "~!~", 1, true);
			String w2 = HagUtil.getWord0(temp, "~!~", 2, true);
			String w3 = HagUtil.getWord0(temp, "~!~", 3, true);
			String w4 = HagUtil.getWord0(temp, "~!~", 4, true);
			String temp1 = "INIT~"+w0+"~"+w1+"~"+w2+"~"+w3+"~"+w4;
			newF.add(temp1);
		}
		return newF;
	}
	//public  String sendReq(String ver){
		//0~35628~IOM~RI00051~08.01M01 ~IOM found in DEV
		//0~35628~JVA~78036~08.01M01 ~no checks
		//0~35628~TSK~55001~08.01M01 ~task status = CLS.(dbo.RITASKS0801)
		
	//	getIssuesByJql("json","REQUEST FOR INT",true,ver,null);
	//}
	 public  		String 						getIssuesByJql(String type,String val,boolean withComponents,String ver,String ticket,String extraDev) {
			//	String req = "http://ridevblp05:8089/jira/getIssuesByJql?jql=project=AIG";
				String ans2 = sendReq(ver,"RISTM2B","jiraVer","w2","extraDev"	, val,ticket);
				if(ans2==null)
					return "not found1";
				if(type.equals("string"))
					return ans2;
				//String ans2S= ans2.convertToString("<br>");
				ArrayList<HagStringList> arrList=null;
				if(withComponents)
					arrList= loadJsonWithComponents( ans2, extraDev);
				else
					arrList= loadJsonWithoutComponents( ans2);
				HagStringList ans = new HagStringList();
				 for (int i = 0; i < arrList.size(); i++) {
						HagStringList temp = arrList.get(i);
						ans.append(temp);
				 }
				String ans2S= ans.convertToString("<br>");
				return ans2S;
			}
	 public  		InputStream 				get(String urlStr) throws Exception {
	        return readFrom(urlStr, null);
	    }
	 public  		InputStream 				readFrom(String urlStr, String postStr)
	    	    throws MalformedURLException, IOException
	    	{
	    	//final HagStringList aaa= new HagStringList();
	    	//
	    	//aaa.add("start");
	    	TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
	    	      public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	    	    //	 	aaa.add("1");
	    	        return null;
	    	      }

	    	      public void checkClientTrusted(X509Certificate[] certs, String authType) {
	    	    //	  aaa.add("2");
	    	      }

	    	      public void checkServerTrusted(X509Certificate[] certs, String authType) {
	    	    	//  aaa.add("3");
	    	      }

				public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {
					//aaa.add("4");
					// TODO Auto-generated method stub
					
				}

				public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {
				//	aaa.add("5");
					// TODO Auto-generated method stub
					
				}
	    	    } };
	    //	aaa.add("6");
	    	    SSLContext sc;
	    	//    aaa.add("7");
				try {
				//	aaa.add("8");
					sc = SSLContext.getInstance("SSL");
				//	aaa.add("9");
					sc.init(null, trustAllCerts, new java.security.SecureRandom());
					//aaa.add("10");
				  HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
				//	aaa.add("11");
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
				//	aaa.add("12");
					e.printStackTrace();
				} catch (KeyManagementException e) {
					//aaa.add("13");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//	aaa.add("14");
	    	 
	    	//
	    	
				//System.out.println("urlStr="+urlStr);
	    	    URLConnection conn = new URL(urlStr).openConnection();
	    	//	aaa.add("15");
	    	    conn.setDoInput(true);
	    		//aaa.add("16");
	    	    if (postStr != null && postStr.length() > 0) {
	    	    //	aaa.add("17");
	    	        conn.setDoOutput(true);
	    	    //	aaa.add("18");
	    	        DataOutputStream output =  new DataOutputStream(conn.getOutputStream());
	    	    //	aaa.add("19");
	    	        output.writeBytes(postStr);
	    	    	//aaa.add("20");
	    	        output.flush();
	    	    	//aaa.add("21");
	    	        output.close();
	    	    //	aaa.add("22");
	    	    }
	    		//aaa.add("23");
	    		//aaa.add(urlStr);
	    		//aaa.add("24");
	    	//	aaa.add(postStr);
	    		
	    		//aaa.writeToFile("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\debug\\1.txt", true);
	    	    return conn.getInputStream();
	    	}
	 public  		InputStream 				httpReq(String ver,String verJira,String status,String val,String ticket){
		//String key99="AIG";
		String key99="RI%20Product%20(SCRUM)";
		// String key99="RI Product (SCRUM)";
	    	//String  jiraServer=
			//String  url = jiraServer+"/sr/jira.issueviews:searchrequest-xml/temp/SearchRequest.xml?jqlQuery=project+%3D+%22RI+Product+(SCRUM)%22+and+status+%3D+\""+status+"\"+and+fixVersion+in+%28%22"+verJira+"%22%29&tempMax=1000&os_username=cfg-burner&os_password=gon09c";
	    String val1 ="aa";
	    String  url ="aaaa";
	    if(val.equals("get version status"))
	    	url ="http://ridevblp05:8089/jira/getVersionStatus?projectKey="+key99+"&version="+ver;
	    //if(val.equals("get version status"))
	    //	url ="http://ridevblp05:8089/jira/getVersionStatus?version="+ver;
	  
	    if(val.equals("move ticket to 'REQUEST FOR INT'"))
	    	url ="http://ridevblp05:8089/jira/updateIssueStatusById?id="+ticket+"&status=REQUEST%20FOR%20INT";
	    if(val.equals("move ticket to 'INSTALLED IN INT'"))
	    	url ="http://ridevblp05:8089/jira/updateIssueStatusById?id="+ticket+"&status=INSTALLED%20IN%20INT";
	    if(val.equals("move ticket to 'IN TEST'"))
	    	url ="http://ridevblp05:8089/jira/updateIssueStatusById?id="+ticket+"&status=IN%20TEST";
	   
	    if(val.equals("REQUEST FOR INT"))
	   		//url ="http://ridevblp05:8089/jira/getIssuesByProjectAndVersionAndStatus?project="+key99+"&status=REQUEST%20FOR%20INT&version="+ver;
    	url ="http://ridevblp05:8089/jira/getIssuesByJql?jql=fixVersion="+ver+"%20and%20'RI%20Integration'='REQUEST%20FOR%20INT'";
 
	    if(val.equals("INSTALLED IN INT"))
	    	//url ="http://ridevblp05:8089/jira/getIssuesByProjectAndVersionAndStatus?project="+key99+"&status=INSTALLED%20IN%20INT&version="+ver;
    	url ="http://ridevblp05:8089/jira/getIssuesByJql?jql=fixVersion="+ver+"%20and%20'RI%20Integration'='INSTALLED%20IN%20INT'";

	    // if(val.equals("REQUEST FOR INT"))
	 //  		url ="http://ridevblp05:8089/jira/getIssuesByProjectAndVersionAndStatus?status=REQUEST%20FOR%20INT&version="+ver;
	   // if(val.equals("INSTALLED IN INT"))
	    //	url ="http://ridevblp05:8089/jira/getIssuesByProjectAndVersionAndStatus?status=INSTALLED%20IN%20INT&version="+ver;
	
	    
	    try {
			   		InputStream is = get(url);
									      
					return  is ;
			    } catch (Exception e) {
			    	e.printStackTrace();
			    	return null;
			    }
			}
	 public  		String 						sendReq(String ver,String key,String verJira,String status,String extraDev,String val,String ticket){
	    	String hagVersion;
	    	String hagVersion1;
	    //	int hagFromR6=0;
	    //	int hagFromD1=0;
	    	
	    
	    	
			InputStream input  = httpReq(ver,verJira,status, val,ticket);
			if(input==null)
				return null;
			
			StringBuilder sb = new StringBuilder();
			try {
				for (int ch; (ch = input.read()) != -1; ) {
				    sb.append((char) ch);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return sb.toString();
			 
		
			
		}   
	 public  		ArrayList<HagStringList> 	loadJsonWithComponents                      (String jsonStr,String extraDev) {
		 
		 ArrayList<HagStringList> arrList = new ArrayList<HagStringList>();
		 jsonStr=" {\"jira\": "+jsonStr+"  }";
		
	     
	        JSONArray jsonarray1 = null;
	        JSONObject  jsonobject1 = null;
	        try {
	            jsonobject1 = new JSONObject(jsonStr);
	            jsonarray1 = jsonobject1.getJSONArray("jira");
	            //loop tickets
	            for (int i = 0; i < jsonarray1.length(); i++) {
	            	 HagStringList list = new HagStringList();
	                JSONObject jsonobject2 = jsonarray1.getJSONObject(i);
	                String key          = jsonobject2.getString("key");
	            //System.out.println(key);
	                JSONArray jsonarray2 = jsonobject2.getJSONArray("issueFields");
	                //loop fixVersions
	                JSONArray jsonarray21 = jsonobject2.getJSONArray("fixVersions");
	                JSONObject jsonobject21 = jsonarray21.getJSONObject(0);
	                String name21          = jsonobject21.getString("name");
	                //loop affectedVersions
	              
	                String name22="error getting affectedVersions ";
	                try {
	                	 JSONArray jsonarray22 = jsonobject2.getJSONArray("affectedVersions");
	                	JSONObject jsonobject22 = jsonarray22.getJSONObject(0);
	                	name22          = jsonobject22.getString("name");
	                }catch(org.json.JSONException oj) {
	                	if(!extraDev.equals("NO"))
	                		name22          = "Error no affectedVersions found";
	                }
	              
	                //loop components
	                for (int k = 0; k < jsonarray2.length(); k++) {
		                JSONObject jsonobject3 = jsonarray2.getJSONObject(k);
		                String name          = jsonobject3.getString("name");
		                if(		name.equals("RI IOM Release Components") 		||
		                		name.equals("RI Java Release Components") 		||
		                		name.equals("RI JavaScript Release Components") ||
		                		name.equals("RI MD Release Components") 		||
		                		name.equals("RI UTP Release Components")		||
		                		name.equals("RI REX Release Components") 		||
		                		name.equals("RI CMD Release Components") 		||
		                   		name.equals("RI emptyTicket")             		){
		                	String value          = jsonobject3.getString("value");
		                	if(value!=null && !value.equals("null")) {
		                		HagStringList list1 =getValsLines ( value, key, name,name21,name22);
		                		list.append(list1);
		                	}
		                }
	                }
	                if(list.size()>0)
	                	arrList.add(list);
	            }
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	        return arrList;
	    }
	 public  		HagStringList 				getValsLines (String value,String key,String name,String fixVersions,String affectedVersions) {
	 HagStringList valuesList = new HagStringList(value,",",true);
	 HagStringList ans = new HagStringList();
	 	for (int m = 0; m < valuesList.size(); m++) {
	 		String val = valuesList.get(m);
	 		String line=key+"~!~"+ getShortName(name)+"~!~"+val+"~!~"+fixVersions+"~!~"+affectedVersions;
	 		ans.add(line);
	 	}
	 	return ans;
	 }
	 public static 	String 						getShortName (String name) {
		if(name.equals("RI IOM Release Components")) 		return "IOM";
		if(name.equals("RI Java Release Components")) 		return "JVA";
		if(name.equals("RI JavaScript Release Components")) return "JVAS";
		if(name.equals("RI MD Release Components")) 		return "MTD";
		if(name.equals("RI UTP Release Components")) 		return "TSK";
		if(name.equals("RI REX Release Components")) 		return "RX";
		if(name.equals("RI CMD Release Components")) 		return "CMD";
		if(name.equals("RI emptyTicket")) 					return "emptyTicket";
		return "no Ri components";
}	
	 public static 	ArrayList<HagStringList> 	loadJsonWithoutComponents                      (String jsonStr) {
		 
		 ArrayList<HagStringList> arrList = new ArrayList<HagStringList>();
		 jsonStr=" {\"jira\": "+jsonStr+"  }";
		
	     
	        JSONArray jsonarray1 = null;
	        JSONObject  jsonobject1 = null;
	        try {
	            jsonobject1 = new JSONObject(jsonStr);
	            jsonarray1 = jsonobject1.getJSONArray("jira");
	            //loop tickets
	            
	            for (int i = 0; i < jsonarray1.length(); i++) {
	            	
	            	 HagStringList list = new HagStringList();
	                JSONObject jsonobject2 = jsonarray1.getJSONObject(i);
	                String key          = jsonobject2.getString("key");
	                HagStringList list1 =new HagStringList ( );
	                list1.add(key);
	            	list.append(list1);
	            	arrList.add(list);
	            
	                
	            }
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	        return arrList;
	    }
	 public  		String 						replaceFromReqFile(String org){
		String reqFile  	= "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\list\\req.txt";
		HagStringList reqList = new HagStringList(reqFile,true,"*",false);
		String req = getType(reqList,org);
		if(req==null) 
			req=""+org;
		return req;
	}
	 public 		String 						getType(ArrayList<String> types,String title){
	 	  for(int i = 0 ; i < types.size();i++){
	 		  String temp = types.get(i);
	 		  String w1 = HagUtil.getWord0(temp,"|",0,true);
	 		  String w2 = HagUtil.getWord0(temp,"|",1,true);
	 		  if(title.equals(w2))
	 				  return w1;
	 	  }
	 	  return null;
	   }
	
}
