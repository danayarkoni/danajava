package com.hag.hagay;



import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class ClientModuleBuild {
	
	//String riTOPACK="\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V0801m00\\TOPACK\\";
	String riTOPACK="\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V0980m00\\TOPACK\\";
	String[] steps = {
			"send starting mail",
			"reCreate version folder",
			"copy files from  \\\\rialm\\local_snapshot to TOPACK folder",
			"stop tomcat",
			"clean folders and delete war",
			"Copy from TOPACK to target environment",
			"extract ri-cm.zip",
			"start tomcat",
			"send done mail"};
	
	
	
public   static String 			clientModuleBuild(HttpServletRequest request, HttpServletResponse response,String user){
		return clientModuleBuild_before(user);
	}
public   static String 			clientModuleBuild_before(String user){
		String ff1 = HagUtil.cfgMenuWebLoc+"\\lists\\clientModuleBuildMailListTo.txt";
		String ff2 = HagUtil.cfgMenuWebLoc+"\\lists\\clientModuleBuildMailListCc.txt";
		HagStringList mailListListTo= new HagStringList(ff1,true,"*",false);
		HagStringList mailListListCc= new HagStringList(ff2,true,"*",false);
		String mailList1a = mailListListTo.convertToString(";");
		String mailList2a = mailListListCc.convertToString(";");
		//mailList1a="david.ha@sapiens.com";
		//mailList2a="david.ha@sapiens.com";
		
		String sentBy	="<input type=\"text\" id=\"sentBy\"  name=\"sentBy\" readonly size=\"40\" style=\"background:black; color:gold;\" value = \""+user+"\">";	

		String version	="<input type=\"text\" id=\"version\"  name=\"version\" size=\"30\">";	
		String mailList1	="<input type=\"text\" id=\"mailList1\"  name=\"mailList1\" value=\""+mailList1a+"\" size=\"130\">";
		String mailList2	="<input type=\"text\" id=\"mailList2\"  name=\"mailList2\" value=\""+mailList2a+"\" size=\"130\">";	
		
		StringBuilder sb = new StringBuilder("<HTML><body bgcolor=\"#ccccbb\">")	;
		
		sb.append("<font size=6 color=\"GREEN\"><u>Build client module:").append("</u></font><br><br>")
		.append("<font size=2 color=\"blue\">write the version (for example 5.0.0)</font><br><br>")
		.append("<FORM METHOD=POST name=\"Form\" ACTION=\"ClientModuleBuild_Before.jsp\" >")
		.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:70%\">")
		.append("<tr><td bgcolor=\"#ccccaa\">Sent by</td><td>").append(sentBy.toString()).append("</td></tr>")
	
		
		.append("<tr><td bgcolor=\"#ccccaa\">mail to</td><td>").append(mailList1).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">mail cc</td><td>").append(mailList2).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">version</td><td>").append(version).append("</td></tr>")
			.append("</table><br><INPUT TYPE=SUBMIT value=\"open clientModuleBuild next form\" onclick=\"this.disabled=true;this.value='Sending, please wait...';this.form.submit();\"><font color=red></form></body></html>");
		return sb.toString();
	}
public    String 			clientModuleBuild_main(HttpServletRequest request, HttpServletResponse response){
	String sentBy 		= request.getParameter("sentBy").trim();
	String version 	= request.getParameter("version").trim();
	String mailListTo 	= request.getParameter("mailList1").trim();
	String mailListCc 	= request.getParameter("mailList2").trim();
	
	String sentBy1	="<input type=\"text\" id=\"sentBy\"  name=\"sentBy\" readonly size=\"40\" style=\"background:black; color:gold;\" value = \""+sentBy+"\">";	

	String version1	="<input type=\"text\" id=\"version\"  name=\"version\" readonly size=\"40\" style=\"background:black; color:gold;\" value = \""+version+"\">";	
	String mailListTo1	="<input type=\"text\" id=\"mailListTo\"  name=\"mailListTo\" readonly size=\"140\" style=\"background:black; color:gold;\" value = \""+mailListTo+"\">";	
	String mailListCc1	="<input type=\"text\" id=\"mailListCc\"  name=\"mailListCc\" readonly size=\"140\" style=\"background:black; color:gold;\" value = \""+mailListCc+"\">";	

	
	
	
	String path1="path="+riTOPACK+"ClientModule\\CM-RCs\\CMv"+version;
	
	String file = HagUtil.cfgMenuWebLoc+"\\lists\\clientModuleBuildTargetEnvironments.list";
	StringBuilder clientModuleBuildTargetEnvironments =new StringBuilder("<select name=\"targetEnv\">");	
	HagStringList ans3a = new HagStringList(file,true,"*",false);
	for (int i=0;i<ans3a.size();i++){
		String temp = ans3a.get(i);
		clientModuleBuildTargetEnvironments.append("<option class=\"c30\" value=\"").append(temp).append("\">").append(temp).append("</option>");
	
	}
	clientModuleBuildTargetEnvironments.append("</select>");	
	
	
	StringBuilder sb = new StringBuilder("<HTML><body bgcolor=\"#ccccbb\">")	;
	
	sb.append("<font size=6 color=\"GREEN\"><u>Build client module version:"+ version).append("</u></font><br><br>")
	.append("<FORM METHOD=POST name=\"Form\" ACTION=\"ClientModuleBuild_Main.jsp\" >")
	.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:70%\">")
	.append("<tr><td bgcolor=\"#ccccaa\">Sent by</td><td>").append(sentBy1).append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">version</td><td>").append(version1).append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">mailTo</td><td>").append(mailListTo1).append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">mailCC</td><td>").append(mailListCc1).append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">targetEnv</td><td>").append(clientModuleBuildTargetEnvironments).append("</td></tr>")
	.append("</table><br>");
	sb.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:90%\">")
	.append("<tr><td bgcolor=\"#ccccaa\">Step 1</td><td>").append("send starting mail<br>to the mail-list above").append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">step 2</td><td>").append("reCreate version folder<br>"+path1).append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">Step 3</td><td>").append("copy files <br><font color=blue>cm-mng-MSS-CM-V"+version+"-SNAPSHOT.jar"+"<br>cm-srv-"+version+"-SNAPSHOT.war"+"<br>GebConverter-"+version+"-SNAPSHOT.jar"+"<br>GebLoopSF-"+version+"-SNAPSHOT.jar"+"<br>ri-cm-"+version+"-SNAPSHOT.zip<br></font>from  \\\\rialm\\local_snapshot\\ <br>to "+path1).append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">step 4</td><td>").append("stop tomcat").append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">step 5</td><td>").append("delete:<br><font color=blue>tomcat\\webapps\\cm-srv (dir),<br>tomcat\\webapps\\ri-cm (dir),<br>tomcat\\webapps\\cm-srv.war (file)").append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">step 6</td><td>").append("remove content of:<br><font color=blue>tomcat\\cm_logs\\<br>tomcat\\logs").append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">step 7</td><td>").append("Copy "+riTOPACK+"ClientModule\\CM-RCs\\CMv"+version+"\\cm-srv-"+version+".war <br>to tomcat\\webapps\\cm-srv.war</font>").append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">step 8</td><td>").append("extract "+riTOPACK+"ClientModule\\CM-RCs\\CMv"+version+"\\ri-cm-"+version+"-SNAPSHOT.zip<br>to tomcat\\webapps\\ri-cm folder").append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">step 9</td><td>").append("start tomcat").append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">step 10</td><td>").append("send done mail<br>to the mail-list above").append("</td></tr>")
	.append("</table><br>");
	sb.append("<INPUT TYPE=SUBMIT value=\"Run build processes(takes 2 minutes)\" onclick=\"this.disabled=true;this.value='Sending, please wait 2-3 minutes...';this.form.submit();\"><font color=red></form></body></html>");
	return sb.toString();
}
public    String 			clientModuleBuild_after(HttpServletRequest request, HttpServletResponse response){

	String sentBy 		= request.getParameter("sentBy").trim();
	String version 		= request.getParameter("version").trim();
	String targetEnv 		= request.getParameter("targetEnv").trim();
	String mailListTo 		= request.getParameter("mailListTo").trim();
	String mailListCc 		= request.getParameter("mailListCc").trim();

	String path1="path="+riTOPACK+"ClientModule\\CM-RCs\\CMv"+version;
	HagStringList mailEnd = new HagStringList();
	String resultsStep1=runStep19(mailListTo+";david.ha@sapiens.com",mailListCc+";david.ha@sapiens.com","Starting clientModule-"+version+"-build by "+sentBy ,"<html><body><h2>target envetonment = "+targetEnv+"</h2></body></html>");
	String resultsStep2=runStep2(version,riTOPACK);
	String resultsStep3=runStep3(version,riTOPACK);
	String resultsStep4=runStep4(targetEnv);
	String resultsStep5=runStep5(targetEnv);
	String resultsStep6=runStep6(targetEnv);
	String resultsStep7=runStep7(version,targetEnv);
	String resultsStep8=runStep8(version,targetEnv);
	String resultsStep9=runStep9(targetEnv);
	mailEnd.add("html><body><table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:70%\">");
	mailEnd.add("<tr><td>version</td><td>"+version+"</td></tr>");
	mailEnd.add("<tr><td>targetEnv</td><td>"+targetEnv+"</td></tr>");
	mailEnd.add("<tr><td>sentBy</td><td>"+sentBy+"</td></tr>");	
	mailEnd.add("<tr><td>"+HagUtil.getDateTime("dd/MM/yyyy - HH:mm:ss")+"<br>"+steps[0]+"</td>"+resultsStep1+"</td></tr>");
	mailEnd.add("<tr><td>"+HagUtil.getDateTime("dd/MM/yyyy - HH:mm:ss")+"<br>"+steps[1]+"</td>"+resultsStep2+"</td></tr>");
	mailEnd.add("<tr><td>"+HagUtil.getDateTime("dd/MM/yyyy - HH:mm:ss")+"<br>"+steps[2]+"</td>"+resultsStep3+"</td></tr>");
	mailEnd.add("<tr><td>"+HagUtil.getDateTime("dd/MM/yyyy - HH:mm:ss")+"<br>"+steps[3]+"</td>"+resultsStep4+"</td></tr>");
	mailEnd.add("<tr><td>"+HagUtil.getDateTime("dd/MM/yyyy - HH:mm:ss")+"<br>"+steps[4]+"</td>"+resultsStep5+"</td></tr>");
	mailEnd.add("<tr><td>"+HagUtil.getDateTime("dd/MM/yyyy - HH:mm:ss")+"<br>"+steps[5]+"</td>"+resultsStep6+"</td></tr>");
	mailEnd.add("<tr><td>"+HagUtil.getDateTime("dd/MM/yyyy - HH:mm:ss")+"<br>"+steps[6]+"</td>"+resultsStep7+"</td></tr>");
	mailEnd.add("<tr><td>"+HagUtil.getDateTime("dd/MM/yyyy - HH:mm:ss")+"<br>"+steps[7]+"</td>"+resultsStep8+"</td></tr>");
	mailEnd.add("<tr><td>"+HagUtil.getDateTime("dd/MM/yyyy - HH:mm:ss")+"<br>"+steps[8]+"</td>"+resultsStep9+"</td></tr></table>");


	String mailEndS=mailEnd.convertToString("");
	String resultsStep10=runStep19(mailListTo+";david.ha@sapiens.com",mailListCc+";david.ha@sapiens.com","ClientModule-"+version+"-build DONE , by "+sentBy ,mailEndS);

	String file = HagUtil.cfgMenuWebLoc+"\\lists\\clientModuleBuildTargetEnvironments.list";
	StringBuilder clientModuleBuildTargetEnvironments =new StringBuilder("<select name=\"runTypeHotfix\">");	
	HagStringList ans3a = new HagStringList(file,true,"*",false);
	for (int i=0;i<ans3a.size();i++){
		String temp = ans3a.get(i);
		clientModuleBuildTargetEnvironments.append("<option class=\"c30\" value=\"").append(temp).append("\">").append(temp).append("</option>");
	
	}
	clientModuleBuildTargetEnvironments.append("</select>");	
	
	
	StringBuilder sb = new StringBuilder("<HTML><body bgcolor=\"#ccccbb\">")	;
	
	sb.append("<font size=6 color=\"GREEN\"><u>Build client module version:"+ version).append("</u></font><br><br>")

	.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:70%\">")
	.append("<tr><td bgcolor=\"#ccccaa\">Sent by</td><td>").append(sentBy.toString()).append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">version</td><td>").append(version).append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">mailTo</td><td>").append(mailListTo).append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">mailCc</td><td>").append(mailListCc).append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">targetEnv</td><td>").append(targetEnv).append("</td></tr>")
	.append("</table><br>");
	sb.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:100%\">")
	.append("<tr><td bgcolor=\"#ccccaa\">Step 1</td><td>").append("send starting mail<br>to the mail-list above").append("</td>").append(resultsStep1).append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">step 2</td><td>").append("reCreate version folder<br>"+path1).append("</td>").append(resultsStep2).append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">Step 3</td><td>").append("copy files <br><font color=blue>cm-mng-MSS-CM-V"+version+"-SNAPSHOT.jar"+"<br>cm-srv-"+version+"-SNAPSHOT.war"+"<br>GebConverter-"+version+"-SNAPSHOT.jar"+"<br>GebLoopSF-"+version+"-SNAPSHOT.jar"+"<br>ri-cm-"+version+"-SNAPSHOT.zip<br></font>from  \\\\rialm\\local_snapshot\\ <br>to "+path1).append("</td>").append(resultsStep3).append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">step 4</td><td>").append("stop tomcat").append("</td>").append(resultsStep4).append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">step 5</td><td>").append("delete:<br><font color=blue>tomcat\\webapps\\cm-srv (dir),<br>tomcat\\webapps\\ri-cm (dir),<br>tomcat\\webapps\\cm-srv.war (file)").append("</td>").append(resultsStep5).append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">step 6</td><td>").append("clean content of:<br><font color=blue>tomcat\\cm_logs\\<br>tomcat\\logs").append("</td>").append(resultsStep6).append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">step 7</td><td>").append("Copy "+riTOPACK+"ClientModule\\CM-RCs\\CMv"+version+"\\cm-srv-"+version+"-SNAPSHOT.war <br>to tomcat\\webapps\\cm-srv.war</font>").append("</td>").append(resultsStep7).append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">step 8</td><td>").append("extract "+riTOPACK+"ClientModule\\CM-RCs\\CMv"+version+"\\ri-cm-"+version+"-SNAPSHOT.zip<br>to tomcat\\webapps\\ri-cm folder").append("</td>").append(resultsStep8).append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">step 9</td><td>").append("start tomcat").append("</td>").append(resultsStep9).append("</td></tr>")
	.append("<tr><td bgcolor=\"#ccccaa\">step 10</td><td>").append("send done mail<br>to the mail-list above").append("</td>").append(resultsStep10).append("</td></tr>")
	.append("</table><br>");
	return sb.toString();
}
	public 		String			runStep9(String targetEnv) {
		String server = HagUtil.getWord0(targetEnv,"/", 0, true);
		String batchName = HagUtil.getWord0(targetEnv,"/", 1, true);
		String startTomcat= HagUtil.startTomcatClean(server, batchName);
		if(!startTomcat.startsWith("0~"))		
			return "<td bgcolor=\"#ff0000\">Failed to start "+targetEnv+" tomcat "+startTomcat+"<br>";
		return "<td bgcolor=\"#00ff00\">"+targetEnv+" tomcat started<br>";
	}
	public 		String			runStep8(String version,String targetEnv) {
		String server = HagUtil.getWord0(targetEnv,"/", 0, true);
		String batchName = HagUtil.getWord0(targetEnv,"/", 1, true);
		String fromFile = riTOPACK+"ClientModule\\CM-RCs\\CMv"+version+"\\ri-cm-"+version+"-SNAPSHOT.zip";
		String toFolder = "\\\\"+server+"\\RI_JAVA\\RIjava_"+batchName+"\\tomcat\\webapps\\";
		ArrayList<String> ans4 = HagUtil.unzip(fromFile, toFolder, false);
		StringBuilder aaaw= new StringBuilder();
		for(int i =0;i<ans4.size();i++) {
			aaaw.append(ans4.get(i)).append("<br>");
		}
		if(ans4.get(0).startsWith("0~"))
			return "<td bgcolor=\"#00ff00\">"+aaaw.toString();
		else
			return "<td bgcolor=\"#ff0000\">"+aaaw.toString();
//		if(!rc.startsWith("0~"))	
//			return "<td bgcolor=\"#ff0000\">"+rc+"<br>";
	//	return "<td bgcolor=\"#00ff00\">"+rc+"<br>";
	}
	public 		String			runStep7(String version,String targetEnv) {
		String server = HagUtil.getWord0(targetEnv,"/", 0, true);
		String batchName = HagUtil.getWord0(targetEnv,"/", 1, true);
		String fromFile = riTOPACK+"ClientModule\\CM-RCs\\CMv"+version+"\\cm-srv-"+version+"-SNAPSHOT.war";
		String toFile = "\\\\"+server+"\\RI_JAVA\\RIjava_"+batchName+"\\tomcat\\webapps\\cm-srv.war";
		//String rc=HagUtil.copyFileViaDos(fromFile, toFile);
		HagClient hagClient = new HagClient();
		
		String command3 = HagUtil.cfgMenuLoc+"\\bin\\bat\\runcmd.bat copy " + fromFile + " "+ toFile;
		HagStringList tmp3 = hagClient.runNotSudo(server, command3);
		String tmp3s = tmp3.convertToString("<br>");
		
		
		
		//if(!rc.startsWith("0~"))	
		//	return "<td bgcolor=\"#ff0000\">"+rc+"<br>";
		return "<td bgcolor=\"#00ff00\">"+tmp3s+"<br>";
	}
	public 		String			runStep5(String targetEnv) {
		String server = HagUtil.getWord0(targetEnv,"/", 0, true);
		String batchName = HagUtil.getWord0(targetEnv,"/", 1, true);
		String timeStamp=HagUtil.getDateTime("yyyyMMddHHmmss");
		String[][] dirs = {
							
							{"\\\\"+server+"\\RI_JAVA\\RIjava_"+batchName+"\\tomcat\\webapps","cm-srv"},
							{"\\\\"+server+"\\RI_JAVA\\RIjava_"+batchName+"\\tomcat\\webapps","ri-cm"}
							};
						//			{"\\\\"+server+"\\RI_JAVA\\RIjava_"+batchName+"\\tomcat\\webapps\\cm-srv.war"};
	
	
		StringBuilder rcc = new StringBuilder();
		boolean err=false;
		HagRc hagRc = new HagRc();
		for(int i = 0 ; i < dirs.length;i++) {
			//String rc = HagUtil.simpleDosCmd("javaFunc~simpleDosCmd~del~\""+dirs[i]+"\"",false);
			//if(!rc.startsWith("0~"))	{
			//	rcc.append("<u>Failed to clean ").append(dirs[i]).append(" folder </u><br>");
			//	err=true;
			//}
			//rcc.append(dirs[i]).append(" folder cleaned </u><br>");
			
			String ans = HagUtil.deleteFolder(dirs[i][0]+"\\"+dirs[i][1], null, false, true);
			if(!ans.startsWith("0~"))	{
				rcc.append("<u>").append(ans).append("</u><br><br>");
				err=true;
			}else {
				rcc.append(ans).append("<br><br>");
			}
		}
		String warF="\\\\"+server+"\\RI_JAVA\\RIjava_"+batchName+"\\tomcat\\webapps\\cm-srv.war";
		String rc1 =HagUtil.deleteFile(warF, false);
		//String rc1 = HagUtil.simpleDosCmd("javaFunc~simpleDosCmd~del~\""+warF+"\"",false);
		if(!rc1.startsWith("0~"))	{
			rcc.append("<u>").append(rc1).append("</u><br>");
			err=true;
		}else {
			rcc.append(rc1).append("<br>");
		}
		
	
		if(err)	
			return "<td bgcolor=\"#ff0000\">"+rcc.toString();
		return "<td bgcolor=\"#00ff00\">"+rcc.toString();
	}
	public 		String			runStep6(String targetEnv) {
		String server = HagUtil.getWord0(targetEnv,"/", 0, true);
		String batchName = HagUtil.getWord0(targetEnv,"/", 1, true);
		String timeStamp=HagUtil.getDateTime("yyyyMMddHHmmss");
		String[][] dirs = {
							{"\\\\"+server+"\\RI_JAVA\\RIjava_"+batchName+"\\tomcat\\webapps","cm-srv"},
							{"\\\\"+server+"\\RI_JAVA\\RIjava_"+batchName+"\\tomcat\\webapps","ri-cm"}
							};
						//			{"\\\\"+server+"\\RI_JAVA\\RIjava_"+batchName+"\\tomcat\\webapps\\cm-srv.war"};
	
	
		StringBuilder rcc = new StringBuilder();
		boolean err=false;
		HagRc hagRc = new HagRc();
		for(int i = 0 ; i < dirs.length;i++) {
			//String rc = HagUtil.simpleDosCmd("javaFunc~simpleDosCmd~del~\""+dirs[i]+"\"",false);
			//if(!rc.startsWith("0~"))	{
			//	rcc.append("<u>Failed to clean ").append(dirs[i]).append(" folder </u><br>");
			//	err=true;
			//}
			//rcc.append(dirs[i]).append(" folder cleaned </u><br>");
			HagUtil.renameFolder(hagRc, dirs[i][0]+"\\"+dirs[i][1], dirs[i][0]+"\\toDelete_"+dirs[i][1]+"_"+timeStamp, false);	
			if(hagRc.rc!=0)	{
				rcc.append("<u>Failed to rename and recreate").append(dirs[i][0]+"\\"+dirs[i][1]).append(" folder </u><br><br>");
				err=true;
			}else {
				rcc.append(dirs[i][0]+"\\"+dirs[i][1]).append(" folder renamed and recreated <br><br>");
			}
		}
	
		
	
		if(err)	
			return "<td bgcolor=\"#ffff00\">"+rcc.toString()+"<br> (rename failed but it is OK to continue)";
		return "<td bgcolor=\"#00ff00\">"+rcc.toString();
	}
	public 		String			runStep4(String targetEnv) {
		String server = HagUtil.getWord0(targetEnv,"/", 0, true);
		String batchName = HagUtil.getWord0(targetEnv,"/", 1, true);
		String stopT1=HagUtil.stopTomcatClean(server,  batchName,false);
		if(!stopT1.startsWith("0~"))	
			return "<td bgcolor=\"#ff0000\">Failed to stop "+targetEnv+" tomcat "+stopT1+"<br>";
		return "<td bgcolor=\"#00ff00\">"+targetEnv+" tomcat stopped<br>";
	}
	public    String 			runStep3(String version,String topack){
		boolean rc =true;
		StringBuilder ans = new StringBuilder();
		String [] from = {	"\\\\rialm\\local_snapshot\\cm-mng-MSS-CM-V"+version+"-SNAPSHOT.jar",		
							"\\\\rialm\\local_snapshot\\cm-srv-"+version+"-SNAPSHOT.war",	
							"\\\\rialm\\local_snapshot\\GebConverter-"+version+"-SNAPSHOT.jar",
							"\\\\rialm\\local_snapshot\\GebLoopSF-"+version+"-SNAPSHOT.jar",
							"\\\\rialm\\local_snapshot\\ri-cm-"+version+"-SNAPSHOT.zip"};
		String [] to = {	topack+"ClientModule\\CM-RCs\\CMv"+version+"\\cm-mng-MSS-CM-V"+version+"-SNAPSHOT.jar",		
							topack+"ClientModule\\CM-RCs\\CMv"+version+"\\cm-srv-"+version+"-SNAPSHOT.war",
							topack+"ClientModule\\CM-RCs\\CMv"+version+"\\GebConverter-"+version+"-SNAPSHOT.jar",
							topack+"ClientModule\\CM-RCs\\CMv"+version+"\\GebLoopSF-"+version+"-SNAPSHOT.jar",
							topack+"ClientModule\\CM-RCs\\CMv"+version+"\\ri-cm-"+version+"-SNAPSHOT.zip",};
		for(int i =0;i<from.length;i++) {
			String ans1 = HagUtil.copyFileViaDos(from[i], to[i]);	
			if(!ans1.startsWith("0~")) {
				rc=false;
				ans.append("<u>Failed to copy ").append(from[i]).append(" to ").append(to[i]).append("</u><br><br>");
			}else {
				ans.append(from[i]).append(" copied to ").append(to[i]).append("<br><br>");
			}
		}
		if(rc)
			return "<td bgcolor=\"#00ff00\">"+ans.toString();
					
		return "<td bgcolor=\"#ff0000\">"+ans.toString();
	}
	public    String 			runStep2(String version,String topack){
		HagRc hagRc = new HagRc();
		String folder = topack+"ClientModule\\CM-RCs\\CMv"+version;
		HagUtil.reCreateFolder(hagRc,folder);
		
		if(hagRc.rc!=0)
			return "<td bgcolor=\"#ff0000\">"+hagRc.log.convertToString("<br>");
		return "<td bgcolor=\"#00ff00\">"+hagRc.log.convertToString("<br>");
	}
	
	public    String 			runStep19(String to,String cc,String subject,String body){
		
		String from = "cfgTeam@sapiens.com";
		String ans =HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps,HagUtil.mailList_hag,subject,body);
		if(ans.startsWith("0~"))
				return "<td bgcolor=\"#00ff00\">"+ans;
		return "<td bgcolor=\"#ff0000\">"+ans;
	}
	
	
}

