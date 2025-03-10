package com.hag.hagay;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class PreVersion {
	
	public static String 	reqRelease4PreVersion(HttpServletRequest request, HttpServletResponse response,String user){
		return reqRelease4PreVersion(user);
	}
	public static final String reqRelease4PreVersion(String user){
		//00001
		String sentBy="<input type=\"text\" id=\"sentBy\"  name=\"sentBy\" readonly size=\"40\" style=\"background:black; color:gold;\" value = \""+user+"\">";	
		StringBuilder preVer =new StringBuilder("<select name=\"preVer\">");	
		String file = HagUtil.cfgMenuWebLoc+"\\lists\\preVersions.txt";
		HagStringList ans3a = new HagStringList(file,true,"*",false);

		for (int i=0;i<ans3a.size();i++){
			String temp = ans3a.get(i);
			String folder =HagUtil.getWord0(temp, "|", 0, true);
			String verTopack =HagUtil.getWord0(temp, "|", 1, true);
			String verJira =HagUtil.getWord0(temp, "|", 2, true);
			String ddcAppDef =HagUtil.getWord0(temp, "|", 3, true);
			String ddcDbDef =HagUtil.getWord0(temp, "|", 4, true);
			preVer.append("<option class=\"c30\" value=\"").append(temp).append("\">").append(folder+" for "+verJira).append("</option>");
		}
		preVer.append("</select>");	
		
		
		
		StringBuilder sb = new StringBuilder("<HTML><body bgcolor=\"#ccccbb\">")	
		.append("<font size=6 color=\"GREEN\"><u>Minor Version Release-Request form:").append("</u></font><br><br>")
		.append("<font size=4 color=\"blue\"><u>send a Minor Version Release-Request:").append("</u></font><br><br>")
		.append("<font size=2 color=\"blue\">Select the correct option and submit the form</font><br><br>")
		.append("<FORM METHOD=POST name=\"Form\" ACTION=\"RiReleaseRequestPreVersion.jsp\">")
		.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:70%\">")
		.append("<tr><td bgcolor=\"#ccccaa\">jira version</td><td>").append(preVer.toString()).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Sent by</td><td>").append(sentBy.toString()).append("</td></tr>")
		.append("</table><br><INPUT TYPE=SUBMIT value=\"continue\"></form></body></html>");
		return sb.toString();
	}
	public  String 	prepIframe(String  iframeF){
		String skelStr = "\\\\ri-archive\\Saptech-Archive\\RI\\debug\\preVersion\\iframeSkel.html";
	
		String rc1=HagUtil.deleteFile(iframeF, false);
		String rc2=HagUtil.copyFileViaDos(skelStr, iframeF);
		if(rc1.startsWith("0~") && rc2.startsWith("0~"))
			return "0~prepIframe";
		if(!rc1.startsWith("0~") )
			return rc1;
		else
			return rc2;
	}
	public  String 	riReleaseRequestPreVersion(HttpServletRequest request, HttpServletResponse response){
		String sentBy 	= request.getParameter("sentBy").trim();
		String preVer 	= request.getParameter("preVer").trim();

		String preVer1 = HagUtil.getWord0(preVer,"|",0,true);
		String preVer2 = HagUtil.getWord0(preVer,"|",1,true);
		String appDef = HagUtil.getWord0(preVer,"|",3,true);
		String dbDef = HagUtil.getWord0(preVer,"|",4,true);
		String from = HagUtil.getEnvLine("from",appDef,dbDef);
		String ftp="<input type=\"text\" id=\"ftp\"  name=\"ftp\"  size=\"80\"  value = \"noNeed\">";	
		
		String iframeF = "\\\\ri-archive\\Saptech-Archive\\RI\\debug\\preVersion\\iframe\\iframe_"+preVer1+".html";
		String iframeF1 = "file://///ri-archive/Saptech-Archive/RI/debug/preVersion/iframe/iframe_"+preVer1+".html";
		String rc = prepIframe(iframeF);
		if(!rc.startsWith("0~") )
			return HagUtil.shortHtml("Failed to prepare preVersion iframe","red","bg");
		//checkCm
		String checkCmS = "\\\\ri-archive\\Saptech-Archive\\RI\\preVersionBases\\"+preVer1;
		File checkCmF = new File(checkCmS);
		
		if(!checkCmF.isDirectory() ||!checkCmF.exists() ) {
			return HagUtil.shortHtml("the Minor Version Folder ("+preVer1+") is not free- please call hagay or gonen <br>.", "red","bg");
		}
		
		
		HagRc hagRc=new HagRc();
		String note="<input type=\"text\" id=\"note\"  name=\"note\" size=\"80\">";	
		
		StringBuilder webProxy =new StringBuilder("<select name=\"webProxy\">");	
		webProxy.append("<option class=\"c30\" value=\"").append("NO").append("\">").append("no need to reInstall webProxy").append("</option>");
		webProxy.append("<option class=\"c30\" value=\"").append("YES").append("\">").append("reInstall webProxy").append("</option>");
		webProxy.append("</select>");	
		
		
		
		//components
		
		StringBuilder componentsList = new StringBuilder();
		componentsList.append("<table  cellpadding=\"10\"><tr  valign=\"top\"><td>");
		componentsList.append("<input type=\"checkbox\" name=\"cb2\" id=\"cb2\" value=\"").append("DDC").append("\" checked>"+"Pure,CT,DDC splits + IOM").append("<br>");
		//componentsList.append("<input type=\"checkbox\" name=\"cb2\" id=\"cb2\" value=\"").append("DDC").append("\" checked>"+"DDC splits + IOM").append("<br>");

		String topackFolder="\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\"+preVer2+"\\U00\\"+preVer1+"\\ALL";
		File topackSrc = new File(topackFolder);
		File[] children = topackSrc.listFiles();
		if (children == null || children.length==0 ) {
			componentsList.append("<br>no components in the TOPACK folder");
		}else {
			for(int i = 0 ; i < children.length;i++) {
				String temp = children[i].toString();
				File ff = new File(temp);
				String lastModified = HagUtil.getFileLastModified(ff,"dd/MM/yyyy-HH:ss");
				String temp1 = temp.substring(temp.lastIndexOf("\\")+1,temp.length());
				componentsList.append("<input type=\"checkbox\" name=\"cb2\" id=\"cb2\" value=\"").append(temp1).append("\" checked>"+temp1+"<FONT COLOR=\"0000FF\">  -  (last modified at "+lastModified+")</Font>").append("<br>");
			}
		}
		componentsList.append("</td></tr></table>");
			
			//
			
		//where to install
		HagSQL hagSQL = new HagSQL();
		String stm1 = "select bis_server,batchName,locks,locksDetails from dbo.ri_environments_new where  status='A' and oded='"+preVer1+"' order by bis_server";
		ArrayList<String> results1 = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,4,"~",null,null);
		StringBuilder winEnvToInstall = new StringBuilder();
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
			//
		
		
			prev="000";
			
			if(hagRc.log.size()>0){
				StringBuilder sb = new StringBuilder("<HTML><body bgcolor=\"#ccccbb\">")	
				.append("<font size=6 color=\"RED\"><u>Release-Request failed:").append("</u></font><br><br>");
				for(int i = 0 ; i <hagRc.log.size();i++ ){	
					sb.append(hagRc.log.get(i)+"<br>");
				}
				sb.append("</body></html>");
				return sb.toString();	
			}else{
				StringBuilder sb = new StringBuilder("<HTML>")	
				.append("<script language=\"JavaScript\">")
				.append("function refreshIFrame() {")
				.append("var interval =setInterval(\"reloadIFrame();\", 3000);")
				.append("}")
				.append("function reloadIFrame() {")
				.append("document.getElementById('iframe_id').src += '';")
				.append("}")
				.append("function toggle(source) {")	
				.append("checkboxesW = document.getElementsByName('cb1');")
				.append("checkboxesA = document.getElementsByName('cb2');")	
				.append("for(var i=0, n=checkboxesW.length;i<n;i++) { checkboxesW[i].checked = !source.checked;  }")
				.append("for(var i=0, n=checkboxesA.length;i<n;i++) {  checkboxesA[i].checked = !source.checked;  }")	
				.append("first_extra_dev.value=source.checked")	
				.append("}</script>")
				//.append("<body bgcolor=\"#ccccbb\">")	
				.append("<body onload=\"refreshIFrame()\">")
				.append("<font size=6 color=\"GREEN\"><u>Minor Version Release-Request form:").append("</u></font><br><br>")
				
				.append("<font size=4 color=\"blue\"><u>When you submit this request<br>An automated process will prepare the package following your selections <br>and a request to install this package will be sent to devOps").append("</u></font><br><br>")
				.append("<FORM METHOD=POST name=\"Form\" ACTION=\"RiReleaseRequestPreVersionSend.jsp\">")
				.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:100%\">")
				.append("<tr><td bgcolor=\"#ccccaa\">Minor Version</td><td>").append(preVer1).append("</td></tr>")
				.append("<tr><td bgcolor=\"#ccccaa\">Sent by</td><td>").append(sentBy).append("</td></tr>")
				.append("<tr><td bgcolor=\"#ccccaa\">DDC from</td><td>").append(from).append("</td></tr>")
				.append("<tr><td bgcolor=\"#ccccaa\">Topack folder check</td><td>").append(topackFolder).append("</td></tr>")
				.append("<tr><td bgcolor=\"#ccccaa\">Components check</td><td>").append(componentsList.toString()).append("</h4></td></tr>")
				.append("<tr><td bgcolor=\"#ccccaa\">install webProxy(if exists)</td><td>").append(webProxy).append("</td></tr>")
				//.append("<tr><td bgcolor=\"#ccccaa\">FTP folder name</td><td>").append(ftp).append("(noNeed=do not send to FTP)</td></tr>")
				.append("<tr><td bgcolor=\"#ccccaa\">FTP folder name</td><td>").append("Changed to release only request<br>in order to send this package to FTP you must finish this request<br>and then send a pack-to-ftp request via cfgMenuWeb->requests->req30</td></tr>")
				.append("<tr><td bgcolor=\"#ccccaa\">win environments<br>to install</td><td>").append(winEnvToInstall.toString()).append("</td></tr>")
				.append("<tr><td bgcolor=\"#ccccaa\">Note</td><td>").append(note).append("</td></tr>");
				
				sb.append("<input type=\"hidden\" name=\"sentBy\" id=\"sentBy\" value = \""+sentBy+"\" maxlength=\"140\" size=\"140\">");
				sb.append("<input type=\"hidden\" name=\"preVer\" id=\"preVer\" value = \""+preVer+"\" maxlength=\"140\" size=\"140\">");
				sb.append("<input type=\"hidden\" name=\"iframeF\" id=\"preiframeF1Ver\" value = \""+iframeF+"\" maxlength=\"140\" size=\"140\">");
				
				
				
				//onclick=\"this.disabled=true;this.value='Running, please wait...';
					sb.append("</table><br><INPUT TYPE=SUBMIT value=\"Submit\" onclick=\"this.style.display = 'none' \"></FORM>");
				
			///	sb.append("</table><br><INPUT TYPE=SUBMIT value=\"Submit\"  onclick=\"this.disabled=true;this.value='Running, please wait...';document.getElementById('loader').style.display = 'block';\" ></FORM>");
				sb.append("<table bgColor=\"#ffff00\" cellpadding=\"3\" cellspacing=\"3\" border=\"1\">");
				///sb.append("<tr><td><img id=\"loader\" src=\"Please_wait.gif\" style=\"display: none;\"/></td></tr>");
			//	ans.append("<tr><td>Please click the \"Submit\" button and wait, it will take few seconds</td></tr>");
				
				
				sb.append("</table></BODY>");
				
				sb.append("<br><iframe  id=\"iframe_id\" name=\"iframe_id\" src=\""+iframeF1+"\" height=\"850\" width=\"1500\"></iframe>");		

				//ans.add("<br><iframe  id=\"iframe_id\" name=\"iframe_id\" src=\"file://///ri-archive/Saptech-Archive/RI/debug/"+targetAPP+"_private_envs/iframe/"+privateDb+".html\" height=\"850\" width=\"1500\"></iframe>");		

				
				
				
				return sb.toString();
				}
	
	}
	public  String RiReleaseRequestPreVersionSend(HttpServletRequest request, HttpServletResponse response){
		
		String sentBy 	= request.getParameter("sentBy").trim();
		String 	 preVer	= request.getParameter("preVer").trim();
		String ddcFrom 	= request.getParameter("from").trim();
		String webProxy 	= request.getParameter("webProxy").trim();
		String noteFromUser 	= request.getParameter("note").trim();
		String iframeF 	= request.getParameter("iframeF").trim();
		HagUtil.updateTimeStamp(iframeF,"d1_START","");
		//String ftp 	= request.getParameter("ftp").trim();
		String [] cbgroup1		= request.getParameterValues("cb1");
		String [] cbgroup2		= request.getParameterValues("cb2");
		
		StringBuilder requestNote1 =new StringBuilder();
		StringBuilder content=new StringBuilder("<table bgColor=\"#dddddd\" cellpadding=\"3\" border =\"1\">");
		content.append("<tr bgColor=\"#aaaaaa\"><td>Element</td><td>Value</td></tr>");
		
		
		StringBuilder installOn=new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">Install on</td><td>");
		StringBuilder installOn1 =new StringBuilder();
		HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\cfgMenuWeb\\preVersion.txt", "1","preVersionRequest"  ) ;
		HagUtil.updateTimeStamp(iframeF,"d2_START","");
		if(cbgroup1!=null) {
		for(int i = 0 ; i < cbgroup1.length;i++) {
			if(i==0) {
				installOn.append(cbgroup1[i]);
				//String converted1 = convert1(cbgroup1[i]);
				installOn1.append(cbgroup1[i]);
			}else {
				installOn.append("<br>").append(cbgroup1[i]);
				//String converted1 = convert1(cbgroup1[i]);
				installOn1.append(",").append(cbgroup1[i]);
			}
		}
		}
		HagUtil.updateTimeStamp(iframeF,"d3_START","");
		HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\cfgMenuWeb\\preVersion.txt", "2","preVersionRequest"  ) ;	
		if(installOn.toString().equals("<tr><td>Install on</td><td>")) {
			installOn.append("nowhare");
			installOn1.append("nowhare");
			
		}
		HagUtil.updateTimeStamp(iframeF,"d4_START","");
		installOn.append("</td></tr>");
		
		
	//	StringBuilder ftpFolder=new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">FTP folder</td><td>").append(ftp).append("</td></tr>");
		
		
		
		
		String verSvn= HagUtil.getWord0(preVer,"|",0,true);
		String verTopack= HagUtil.getWord0(preVer,"|",1,true);
		String verVer= HagUtil.getWord0(preVer,"|",2,true);
		String verPackages= HagUtil.getWord0(preVer,"|",5,true);
		
			
		String topack="\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\"+verTopack+"\\U00\\"+verSvn;
		String into="\\\\ri-archive\\Saptech-Archive\\RI\\preVersionBases\\"+verSvn;
		
		//String ddcFromApp1= ddcFromApp.substring(ddcFromApp.indexOf("(")+1,ddcFromApp.indexOf(")"));
		//String ddcFromDb1= ddcFromDb.substring(ddcFromDb.indexOf("(")+1,ddcFromDb.indexOf(")"));
		
		//StringBuilder requestNote =new StringBuilder("With DDC from:(").append(ddcFromApp1).append("/").append(ddcFromDb1).append(")");
		//StringBuilder content=new StringBuilder("With DDC from:(").append(ddcFromApp1).append("/").append(ddcFromDb1).append(")");
		StringBuilder sb1 = new StringBuilder();
		

		
		String ver1 = HagUtil.getWord0(verVer, ".",0,true);
		String ver2 = HagUtil.getWord0(verVer, ".",1,true);
		String maint = HagUtil.getWord0(verVer, ".",2,true);
		String update = HagUtil.getWord0(verVer, ".",3,true);
		String hotfix = HagUtil.getWord0(verVer, ".",4,true);
		HagRc hagRc = new HagRc();

		//package.txt
		String folderValues=into+"\\bin\\config\\values\\";
		HagUtil.setPreVersion_packageTxt(hagRc,folderValues,ver1,ver2,maint,verSvn);
		//config.txt
		String folderSkel=into+"\\bin\\packages\\"+verPackages+"\\";
		HagUtil.setPreVersion_configTxt(hagRc,folderSkel,ver1,ver2,maint,update,hotfix,"01",verSvn);
		HagUtil.updateTimeStamp(iframeF,"d5_START","");
		//spr1006
		String copyFrom12=folderSkel+"\\skel\\config.txt";
		String copyTo12=into+"\\config.txt";
		String ans12 = HagUtil.copyFileViaDos(copyFrom12, copyTo12);		
		//sb.append("copy WebProxy.msi from topack:").append("<br>");
		if(!ans12.startsWith("0~")) {
			return HagUtil.shortHtml("failed to copy "+copyFrom12+" to "+copyTo12+" please cal hagay 2527", "red", "bg");
		}else {
			sb1.append("copy "+copyFrom12+" to "+copyTo12).append("<br>");
		}
	//	if(1==1)
		//	return"aa";

		HagUtil.updateTimeStamp(iframeF,"d6_START","");
		//copy
		HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\cfgMenuWeb\\preVersion.txt", "before loop","preVersionRequest"  ) ;	
		StringBuilder elements = new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">elements from topack folder</td><td>");
		
		StringBuilder ddc = new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">DDC + IOM</td><td>");
		String[] 	requestNote1ddc= {""};
		boolean[] withDDCFlag= {false};
		boolean[] flagWebProxy= {false};
		if (cbgroup2 != null && cbgroup2.length > 0){
			HagUtil.updateTimeStamp(iframeF,"d7_START","");
			for(int i = 0 ; i < cbgroup2.length ; i++){
				String temp = cbgroup2[i];
				HagUtil.updateTimeStamp(iframeF,"d8_START","");
				HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\cfgMenuWeb\\preVersion.txt", temp,"preVersionRequest"  ) ;	
				if(temp.equalsIgnoreCase("DDC")) {
					String ans=packDdcAndIom(iframeF, ddcFrom,requestNote1ddc, ddc,withDDCFlag,into,sb1,verPackages);
					if(!ans.startsWith("0~"))
						return ans;
				}
				if(temp.equalsIgnoreCase("WebProxy.msi")) {
						
					String ans=packWebProxy(iframeF, topack,elements, requestNote1,flagWebProxy,into,sb1,verPackages);
					if(!ans.startsWith("0~"))
						return ans;
				}
				if(temp.equalsIgnoreCase("mig-mng.jar")) {
					
					String ans=packMigMngJar(iframeF, topack,elements, requestNote1,flagWebProxy,into,sb1,verPackages);
					if(!ans.startsWith("0~"))
						return ans;
				}
				if(temp.equalsIgnoreCase("ri-web.war")) {
					
					String ans=packRiWebWar(iframeF, topack,elements, requestNote1,flagWebProxy,into,sb1,verPackages);
					if(!ans.startsWith("0~"))
						return ans;
				}
				/*
				if(temp.equalsIgnoreCase("ri-web.war") || temp.equalsIgnoreCase("mig-mng.jar")) {
					String copyFrom1=topack+"\\ALL\\"+temp;
					String copyTo1=into+"\\bin\\packages\\"+verPackages+"\\fromTopack\\"+temp;
					String ans1 = HagUtil.copyFileViaDos(copyFrom1, copyTo1);		
					sb1.append("copy "+temp+" from topack:").append("<br>");
					if(!ans1.startsWith("0~"))
						return "<td bgColor=\"#FF0000\">Failed to copy "+copyFrom1+" to "+copyTo1+"<br>"+ans1+"</td>";
					else {
						sb1.append(copyFrom1 + " copied into "+copyTo1).append("<br>");
						if(elements.toString().equals("<tr><td><tr><td  bgColor=\"#aaaaaa\">elements from topack folder</td><td>")) {
							elements.append(temp);
							requestNote1.append(" toPack elements:(").append(temp);
						}else {
							elements.append("<br>").append(temp);
							requestNote1.append(",").append(temp);
						}
					}
				}
				*/
			}
		}
		HagUtil.updateTimeStamp(iframeF,"d9_START","");
		HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\cfgMenuWeb\\preVersion.txt", "after loop","preVersionRequest"  ) ;	
		
		requestNote1.append(")");
		requestNote1.append(" installOn:(").append(installOn1).append(")");
		//requestNote1.append(" sendToFtp:(").append(ftp).append(")");
		requestNote1.append(" install webProxy:(").append(webProxy).append(")");
		
		if(!flagWebProxy[0] && webProxy.equals("YES"))
			return "<td bgColor=\"#FF0000\">cannot select to reinstall webProxy when webProxy is not selected</td>";
	
		StringBuilder instWebProxy = new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">Install WebProxy</td><td>").append(webProxy).append("</td></tr>");
		
		elements.append("</td></tr>");
		
		
		if(!withDDCFlag[0]) {
			ddc.append("without DDC");
			requestNote1ddc[0]="ddc:(without DDC)";

		}
		ddc.append("</td></tr>");
		requestNote1.append(requestNote1ddc[0]);
		
		StringBuilder noteFromSender = new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">note from sender</td><td>").append(noteFromUser).append("</td></tr>");
		content.append(ddc.toString());
		content.append(elements.toString());
		content.append(installOn.toString());
	//	content.append(ftpFolder.toString());
		content.append(instWebProxy.toString());
		content.append(noteFromSender.toString());
		
		content.append("</table>");

		content.append("<br><br>***").append(requestNote1.toString()).append("***<br>");

		HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\cfgMenuWeb\\preVersion.txt", "3","preVersionRequest"  ) ;	
		
		
		HagStringList listWin = new HagStringList();
		if (cbgroup1 != null && cbgroup1.length > 0){
			for(int i = 0 ; i < cbgroup1.length ; i++){
				listWin.add(cbgroup1[i]);
			}
		}
		composeReqFilePreVersion( listWin,verSvn);
		
		HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\cfgMenuWeb\\preVersion.txt", "4","preVersionRequest"  ) ;	
		
		
		int ind = HagUtil.getRequestInd();
		String indS = HagUtil.padNumLeft(""+ind, 5, '0');
		String subject 	= "install preVersion "+verSvn;
		String subject1 	="#Request "+subject+" @BREAK-REQ@ #"+indS+"# sent by "+sentBy;

		
		String to = "david.ha@sapiens.com;gonen.s@sapiens.com;"+sentBy+"@sapiens.com";
		String ccMailList 	= HagUtil.getRiMails("all");
		HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\cfgMenuWeb\\preVersion.txt", "5","preVersionRequest"  ) ;	
		
	//	if(verSvn.equals("CM_TEST")) {
	//		to = "david.ha@sapiens.com";
	//		ccMailList 	= "david.ha@sapiens.com";
	//	}else {
			//spr1007
			String winInstallOn =null;
			String installOn1S=installOn1.toString();
			if(installOn1S!=null && !installOn1S.equals("nowhare"))
				winInstallOn=HagUtil.replaceStr(installOn1S, ",", " ");
			//spr1011
			if(!HagUtil.addRequest(ind,sentBy, requestNote1.toString(),subject1,".",".",".",winInstallOn,null,null,null,991))
				return HagUtil.shortHtml("Failed to update req_ind_log_new table.", "red","bg");
			HagUtil.writeToRelDiary2("preVersionRelease","WIN","Request",verSvn,".",".",".",".","#"+indS,sentBy,".",".");
	//	}
		
		HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\cfgMenuWeb\\preVersion.txt", "6","preVersionRequest"  ) ;	
		
		
		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+sentBy,HagUtil.mailList_hag,subject1,content.toString()+"<br><br>Logs:<br>"+sb1.toString()); 
		HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\cfgMenuWeb\\preVersion.txt", "7","preVersionRequest"  ) ;	
		
		if(!ans1.startsWith("0~")) 
			return HagUtil.shortHtml("Failed to send mail.<br>"+ans1, "red","bg");
		HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\cfgMenuWeb\\preVersion.txt", "end","preVersionRequest"  ) ;	
		
		
	return content.toString()+"<br><br>Logs:<br>"+sb1.toString();
	}	
	
	public  String  packDdcSplit(String iframeF,String split,String into,String verPackages,String ddcfolder,String ddcFromDb1,String ddcFromSql1,HagRc hagRcGetDdc,StringBuilder sb1,String schema) {
		HagUtil.updateTimeStamp(iframeF,"copy"+split+"_START","");
	
	
		String splitDDC = into+"\\bin\\packages\\"+verPackages+"\\DDC\\"+split+".DDC";
		String delCo1a = HagUtil.deleteFile(into+"\\bin\\packages\\"+verPackages+"\\DDC\\"+split+".DDC",  false);
		String delCo1b = HagUtil.deleteFile(into+"\\bin\\packages\\"+verPackages+"\\DDC\\"+split+".FMT",  false);
		String delCo1c = HagUtil.deleteFile(into+"\\bin\\packages\\"+verPackages+"\\DDC\\"+split+"_DDC_log.txt",  false);
		String delCo1d = HagUtil.deleteFile(into+"\\bin\\packages\\"+verPackages+"\\DDC\\"+split+"_FMT_log.txt",  false);
		if(!delCo1a.startsWith("0~") || !delCo1b.startsWith("0~") || !delCo1c.startsWith("0~") || !delCo1d.startsWith("0~")){
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to delete "+split+" old files from "+into+"\\bin\\packages\\"+verPackages+"\\DDC\\"+"</td>";
			return ansTemp2;
		}
		HagUtil.crtBcps( ddcfolder, ddcFromDb1, schema, split, ddcFromSql1, hagRcGetDdc);
		if(hagRcGetDdc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create "+split+" bcp file<br>"+hagRcGetDdc.log.get(hagRcGetDdc.log.size()-1) +"</td>";
			return ansTemp2;
		}else {
			sb1.append(split+" recreated").append("<br>");;
		}
		
		File ff = new File(splitDDC);
		if(ff.isFile() && ff.exists()) {
			HagUtil.updateTimeStamp(iframeF,"copy"+split+"_END","aaa");
			return "0~";
		}else {
			HagUtil.updateTimeStamp(iframeF,"copy"+split+"_END","#FF0000");
			return HagUtil.shortHtml("failed to create "+split+ " split ", "red", "bg");
		}
	}
	public  String  packIom(String iframeF,String into,String verPackages,String ddcFromApp1,String ddcFromDb1,StringBuilder sb1) {
		HagUtil.updateTimeStamp(iframeF,"copyIOM_START","");
		HagRc hagRcGetIOM = new HagRc();
		//String ddcFromApp= HagUtil.getWord0(ddcFrom,",",0,true);
		//String ddcFromDb= HagUtil.getWord0(ddcFrom,",",1,true);
		String iomFolderSrc = "\\\\"+ddcFromApp1+"\\RI_SHARE\\"+ddcFromDb1+"\\IOM_Core\\";
		String iomFoldertgt = into +"\\"+"bin\\packages\\"+verPackages+"\\fromTopack\\iom.zip";
		if(!HagUtil.deleteFile(iomFoldertgt, false).startsWith("0~")){
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to delete "+iomFoldertgt+" file."+"</td>";
			return ansTemp2;
		}
		String ans2 = HagUtil.zip(iomFoldertgt,iomFolderSrc,into+"_debug\\iom_zip_log.txt", "N", false, true);
		if(!ans2.startsWith("0~")){
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to zip "+iomFolderSrc+" into "+iomFoldertgt+"</td>";
			return ansTemp2;
		}
		sb1.append("IOM.zip recreated").append("<br>");
		HagUtil.updateTimeStamp(iframeF,"copyIOM_END",ddcFromApp1+"("+ddcFromDb1+")  "+"aaa");
		
		File ff = new File(iomFoldertgt);
		if(ff.isFile() && ff.exists()) {
			HagUtil.updateTimeStamp(iframeF,"copyIOM_END","aaa");
			return "0~";
		}else {
			HagUtil.updateTimeStamp(iframeF,"copyIOM_END","#FF0000");
			return HagUtil.shortHtml("failed to zip "+iomFolderSrc+ " into "+iomFoldertgt, "red", "bg");
		}
	}
	public  String  packDdcAndIom(String iframeF,String ddcFrom ,String[] requestNote1ddc,StringBuilder ddc ,boolean[] withDDCFlag,String into,StringBuilder sb1,String verPackages) {
		String ddcFromApp= HagUtil.getWord0(ddcFrom,",",0,true);
		String ddcFromDb= HagUtil.getWord0(ddcFrom,",",1,true);
		String ddcFromSql= HagUtil.getWord0(ddcFrom,",",2,true);
		//ddc
		
		String ddcFromApp1= ddcFromApp.substring(ddcFromApp.indexOf("(")+1,ddcFromApp.indexOf(")"));
		String ddcFromDb1= ddcFromDb.substring(ddcFromDb.indexOf("(")+1,ddcFromDb.indexOf(")"));
		//requestNote1.append("ddc:(").append(ddcFromApp1).append("/").append(ddcFromDb1).append(")");
		requestNote1ddc[0]="ddc:("+ddcFromApp1+"/"+ddcFromDb1+")";
		ddc.append("from ").append(ddcFromApp1).append("/").append(ddcFromDb1);
		withDDCFlag[0]=true;
		HagRc hagRcGetDdc = new HagRc();
		String debugFolder =into+"_debug"; 
		String ddcFromSql1 = ddcFromSql.substring(ddcFromSql.indexOf("(")+1,ddcFromSql.indexOf(")"));
		String ddcfolder = into+"\\bin\\packages\\"+verPackages+"\\DDC\\";
		
	
		String ans=packDdcSplit(iframeF,"PR4530EN", into, verPackages, ddcfolder, ddcFromDb1, ddcFromSql1, hagRcGetDdc, sb1,"SAPIENS") ;
		if(!ans.startsWith("0~"))
			return ans;
		ans=packDdcSplit(iframeF,"PR4530ENJ", into, verPackages, ddcfolder, ddcFromDb1, ddcFromSql1, hagRcGetDdc, sb1,"SAPIENS") ;
		if(!ans.startsWith("0~"))
			return ans;
		ans=packDdcSplit(iframeF,"PR4530ENL", into, verPackages, ddcfolder, ddcFromDb1, ddcFromSql1, hagRcGetDdc, sb1,"SAPIENS") ;
		if(!ans.startsWith("0~"))
			return ans;
		ans=packDdcSplit(iframeF,"CT", into, verPackages, ddcfolder, ddcFromDb1, ddcFromSql1, hagRcGetDdc, sb1,"RI") ;
		if(!ans.startsWith("0~"))
			return ans;
	
		ans=packDdcSplit(iframeF,"CTCAT", into, verPackages, ddcfolder, ddcFromDb1, ddcFromSql1, hagRcGetDdc, sb1,"RI") ;
		if(!ans.startsWith("0~"))
			return ans;
	
		ans=packDdcSplit(iframeF,"CTCM1", into, verPackages, ddcfolder, ddcFromDb1, ddcFromSql1, hagRcGetDdc, sb1,"RI") ;
		if(!ans.startsWith("0~"))
			return ans;
	
		ans=packDdcSplit(iframeF,"CTCMN", into, verPackages, ddcfolder, ddcFromDb1, ddcFromSql1, hagRcGetDdc, sb1,"RI") ;
		if(!ans.startsWith("0~"))
			return ans;
	
		ans=packDdcSplit(iframeF,"CTCTU", into, verPackages, ddcfolder, ddcFromDb1, ddcFromSql1, hagRcGetDdc, sb1,"RI") ;
		if(!ans.startsWith("0~"))
			return ans;
	
		ans=packDdcSplit(iframeF,"CTCU0", into, verPackages, ddcfolder, ddcFromDb1, ddcFromSql1, hagRcGetDdc, sb1,"RI") ;
		if(!ans.startsWith("0~"))
			return ans;
	
		ans=packDdcSplit(iframeF,"CTCU1", into, verPackages, ddcfolder, ddcFromDb1, ddcFromSql1, hagRcGetDdc, sb1,"RI") ;
		if(!ans.startsWith("0~"))
			return ans;
	
		ans=packDdcSplit(iframeF,"CTDAT", into, verPackages, ddcfolder, ddcFromDb1, ddcFromSql1, hagRcGetDdc, sb1,"RI") ;
		if(!ans.startsWith("0~"))
			return ans;
	
		ans=packDdcSplit(iframeF,"CTDLK", into, verPackages, ddcfolder, ddcFromDb1, ddcFromSql1, hagRcGetDdc, sb1,"RI") ;
		if(!ans.startsWith("0~"))
			return ans;
	
		ans=packDdcSplit(iframeF,"CTENU", into, verPackages, ddcfolder, ddcFromDb1, ddcFromSql1, hagRcGetDdc, sb1,"RI") ;
		if(!ans.startsWith("0~"))
			return ans;
	
		ans=packDdcSplit(iframeF,"CTJSQ", into, verPackages, ddcfolder, ddcFromDb1, ddcFromSql1, hagRcGetDdc, sb1,"RI") ;
		if(!ans.startsWith("0~"))
			return ans;
	
		ans=packDdcSplit(iframeF,"CTLCL", into, verPackages, ddcfolder, ddcFromDb1, ddcFromSql1, hagRcGetDdc, sb1,"RI") ;
		if(!ans.startsWith("0~"))
			return ans;
	
		ans=packDdcSplit(iframeF,"CTLEX", into, verPackages, ddcfolder, ddcFromDb1, ddcFromSql1, hagRcGetDdc, sb1,"RI") ;
		if(!ans.startsWith("0~"))
			return ans;
	
		ans=packDdcSplit(iframeF,"CTSEC", into, verPackages, ddcfolder, ddcFromDb1, ddcFromSql1, hagRcGetDdc, sb1,"RI") ;
		if(!ans.startsWith("0~"))
			return ans;
	
		ans=packDdcSplit(iframeF,"CTSWS", into, verPackages, ddcfolder, ddcFromDb1, ddcFromSql1, hagRcGetDdc, sb1,"RI") ;
		if(!ans.startsWith("0~"))
			return ans;
	
		
		ans=packDdcSplit(iframeF,"CO1", into, verPackages, ddcfolder, ddcFromDb1, ddcFromSql1, hagRcGetDdc, sb1,"RI") ;
		if(!ans.startsWith("0~"))
			return ans;
		ans=packDdcSplit(iframeF,"CO0", into, verPackages, ddcfolder, ddcFromDb1, ddcFromSql1, hagRcGetDdc, sb1,"RI") ;
		if(!ans.startsWith("0~"))
			return ans;
		ans=packDdcSplit(iframeF,"CAT", into, verPackages, ddcfolder, ddcFromDb1, ddcFromSql1, hagRcGetDdc, sb1,"RI") ;
		if(!ans.startsWith("0~"))
			return ans;
		ans= packIom( iframeF,into, verPackages, ddcFromApp1, ddcFromDb1, sb1);
		if(!ans.startsWith("0~"))
			return ans;
		return "0~";
		
		
		/*
		String delCo0a = HagUtil.deleteFile(into+"\\bin\\packages\\"+verPackages+"\\DDC\\CO0.DDC",  false);
		String delCo0b = HagUtil.deleteFile(into+"\\bin\\packages\\"+verPackages+"\\DDC\\CO0.FMT",  false);
		String delCo0c = HagUtil.deleteFile(into+"\\bin\\packages\\"+verPackages+"\\DDC\\CO0_DDC_log.txt",  false);
		String delCo0d = HagUtil.deleteFile(into+"\\bin\\packages\\"+verPackages+"\\DDC\\CO0_FMT_log.txt",  false);
		if(!delCo0a.startsWith("0~") || !delCo0b.startsWith("0~") || !delCo0c.startsWith("0~") || !delCo0d.startsWith("0~")){
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to delete CO0 old files from "+into+"\\bin\\packages\\"+verPackages+"\\DDC\\"+"</td>";
			return ansTemp2;
		}
		HagUtil.crtBcps( ddcfolder, ddcFromDb1, "RI", "CO0", ddcFromSql1, hagRcGetDdc);
		if(hagRcGetDdc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CO0 bcp file<br>"+hagRcGetDdc.log.get(hagRcGetDdc.log.size()-1) +"</td>";
			return ansTemp2;
		}else {
		
			sb1.append("CO0 recreated").append("<br>");
		}


		String delCo1a = HagUtil.deleteFile(into+"\\bin\\packages\\"+verPackages+"\\DDC\\CO1.DDC",  false);
		String delCo1b = HagUtil.deleteFile(into+"\\bin\\packages\\"+verPackages+"\\DDC\\CO1.FMT",  false);
		String delCo1c = HagUtil.deleteFile(into+"\\bin\\packages\\"+verPackages+"\\DDC\\CO1_DDC_log.txt",  false);
		String delCo1d = HagUtil.deleteFile(into+"\\bin\\packages\\"+verPackages+"\\DDC\\CO1_FMT_log.txt",  false);
		if(!delCo1a.startsWith("0~") || !delCo1b.startsWith("0~") || !delCo1c.startsWith("0~") || !delCo1d.startsWith("0~")){
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to delete CO1 old files from "+into+"\\bin\\packages\\"+verPackages+"\\DDC\\"+"</td>";
			return ansTemp2;
		}
		HagUtil.crtBcps( ddcfolder, ddcFromDb1, "RI", "CO1", ddcFromSql1, hagRcGetDdc);
		if(hagRcGetDdc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CO1 bcp file<br>"+hagRcGetDdc.log.get(hagRcGetDdc.log.size()-1) +"</td>";
			return ansTemp2;
		}else {
			sb1.append("CO1 recreated").append("<br>");;
		}

		String delCata = HagUtil.deleteFile(into+"\\bin\\packages\\"+verPackages+"\\DDC\\CAT.DDC",  false);
		String delCatb = HagUtil.deleteFile(into+"\\bin\\packages\\"+verPackages+"\\DDC\\CAT.FMT",  false);
		String delCatc = HagUtil.deleteFile(into+"\\bin\\packages\\"+verPackages+"\\DDC\\CAT_DDC_log.txt",  false);
		String delCatd = HagUtil.deleteFile(into+"\\bin\\packages\\"+verPackages+"\\DDC\\CAT_FMT_log.txt",  false);
		if(!delCata.startsWith("0~") || !delCatb.startsWith("0~") || !delCatc.startsWith("0~") || !delCatd.startsWith("0~")){
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to delete CAT old files from "+into+"\\bin\\packages\\"+verPackages+"\\DDC\\"+"</td>";
			return ansTemp2;
		}
		HagUtil.crtBcps( ddcfolder, ddcFromDb1, "RI", "CAT", ddcFromSql1, hagRcGetDdc);
		if(hagRcGetDdc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CAT bcp file<br>"+hagRcGetDdc.log.get(hagRcGetDdc.log.size()-1) +"</td>";
			return ansTemp2;
		}else {
			sb1.append("CAT recreated").append("<br>");;
		}
		
		//iom
		HagRc hagRcGetIOM = new HagRc();
		//String ddcFromApp= HagUtil.getWord0(ddcFrom,",",0,true);
		//String ddcFromDb= HagUtil.getWord0(ddcFrom,",",1,true);
		String iomFolderSrc = "\\\\"+ddcFromApp1+"\\RI_SHARE\\"+ddcFromDb1+"\\IOM_Core\\";
		String iomFoldertgt = into +"\\"+"bin\\packages\\"+verPackages+"\\fromTopack\\iom.zip";
		if(!HagUtil.deleteFile(iomFoldertgt, false).startsWith("0~")){
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to delete "+iomFoldertgt+" file."+"</td>";
			return ansTemp2;
		}
		String ans2 = HagUtil.zip(iomFoldertgt,iomFolderSrc,into+"_debug\\iom_zip_log.txt", "N", false, true);
		if(!ans2.startsWith("0~")){
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to zip "+iomFolderSrc+" into "+iomFoldertgt+"</td>";
			return ansTemp2;
		}
		sb1.append("IOM.zip recreated").append("<br>");;
		*/
		//return "0~";
	}
	public  void composeReqFilePreVersion(HagStringList list,	String svnVer ) {
		HagStringList servers = new HagStringList();
		for (int i = 0; i < list.size(); i++) {
			String temp = list.get(i);
			String temp1 = temp.substring(0,temp.lastIndexOf("-"));
			if (!servers.isInList(temp1)) {
				servers.add(temp1);
			}
		}
	//	String serversLine = servers.convertToString("&nbsp;&nbsp;&nbsp;");
		for (int i = 0; i < list.size(); i++) {
			String temp = list.get(i);
//			String temp1 = HagUtil.getWord0(temp, "-", 0, true);
	//		String temp2 = HagUtil.getWord0(temp, "-", 1, true);

			String temp1 = temp.substring(0,temp.lastIndexOf("-"));
			String temp2 = temp.substring(temp.lastIndexOf("-")+1,temp.length());

			
			if( temp2.equalsIgnoreCase("RI"))
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
		//distTo.append(servers.convertToString("&nbsp;")).append("<br>"); 
		//cfg.append("=>=>cb&nbsp;").append(verJira).append("&nbsp;&nbsp;&nbsp;").append(servers.convertToString("&nbsp;")).append("&nbsp;<=<=<br>=>=>ms&nbsp;").append(serversLine).append("&nbsp;<=<=<br>");

		String hyun = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\";
			String ff1 = hyun+"preVersion_inst\\" + svnVer + ".req";
			HagUtil.deleteFile(ff1,  false);
			servers.writeToFile(ff1, null, false);
		
		
	}
	
	public  String  packWebProxy(String iframeF,String topack ,StringBuilder  elements ,StringBuilder requestNote1,boolean[] flagWebProxy,String into,StringBuilder sb1,String verPackages) {
		HagUtil.updateTimeStamp(iframeF,"copyWebProxy_START","");
		String copyFrom1=topack+"\\ALL\\WebProxy.msi";
		String copyTo1=into+"\\bin\\packages\\"+verPackages+"\\WebProxy\\WebProxy.msi";
		String ans3 = HagUtil.copyFileViaDos(copyFrom1, copyTo1);		
		sb1.append("copy WebProxy.msi from topack:").append("<br>");
		if(!ans3.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to copy "+copyFrom1+" to "+copyTo1+"<br>"+ans3+"</td>";
		else {
			flagWebProxy[0]=true;
			sb1.append(copyFrom1 + " copied into "+copyTo1).append("<br>");
			                            
			if(elements.toString().equals("<tr><td  bgColor=\"#aaaaaa\">elements from topack folder</td><td>")) {
				elements.append("WebProxy.msi");
				requestNote1.append(" toPack elements:(WebProxy.msi");
			}else {
				elements.append("<br>").append("WebProxy.msi");
				requestNote1.append(",WebProxy.msi");
			}
		}
		File ff = new File(copyTo1);
		if(ff.isFile() && ff.exists()) {
			HagUtil.updateTimeStamp(iframeF,"copyWebProxy_END","aaa");
			return "0~";
		}else {
			HagUtil.updateTimeStamp(iframeF,"copyWebProxy_END","#FF0000");
			return HagUtil.shortHtml("failed to copy "+copyFrom1+ " into "+copyTo1, "red", "bg");
		}
	}
	public  String  packMigMngJar(String iframeF,String topack ,StringBuilder  elements ,StringBuilder requestNote1,boolean[] flagWebProxy,String into,StringBuilder sb1,String verPackages) {
		String temp="mig-mng.jar";
		HagUtil.updateTimeStamp(iframeF,"copyMigMngJar_START","");
		String copyFrom1=topack+"\\ALL\\"+temp;
		String copyTo1=into+"\\bin\\packages\\"+verPackages+"\\fromTopack\\"+temp;
		String ans1 = HagUtil.copyFileViaDos(copyFrom1, copyTo1);		
		sb1.append("copy "+temp+" from topack:").append("<br>");
		if(!ans1.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to copy "+copyFrom1+" to "+copyTo1+"<br>"+ans1+"</td>";
		else {
			sb1.append(copyFrom1 + " copied into "+copyTo1).append("<br>");
			if(elements.toString().equals("<tr><td><tr><td  bgColor=\"#aaaaaa\">elements from topack folder</td><td>")) {
				elements.append(temp);
				requestNote1.append(" toPack elements:(").append(temp);
			}else {
				elements.append("<br>").append(temp);
				requestNote1.append(",").append(temp);
			}
		}
		File ff = new File(copyTo1);
		if(ff.isFile() && ff.exists()) {
			HagUtil.updateTimeStamp(iframeF,"copyMigMngJar_END","aaa");
			return "0~";
		}else {
			HagUtil.updateTimeStamp(iframeF,"copyMigMngJar_END","#FF0000");
			return HagUtil.shortHtml("failed to copy "+copyFrom1+ " into "+copyTo1, "red", "bg");
		}
	}
	public  String  packRiWebWar(String iframeF,String topack ,StringBuilder  elements ,StringBuilder requestNote1,boolean[] flagWebProxy,String into,StringBuilder sb1,String verPackages) {
		String temp="ri-web.war";
		HagUtil.updateTimeStamp(iframeF,"copyRiWebWar_START","");
		String copyFrom1=topack+"\\ALL\\"+temp;
		String copyTo1=into+"\\bin\\packages\\"+verPackages+"\\fromTopack\\"+temp;
		String ans1 = HagUtil.copyFileViaDos(copyFrom1, copyTo1);		
		sb1.append("copy "+temp+" from topack:").append("<br>");
		if(!ans1.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to copy "+copyFrom1+" to "+copyTo1+"<br>"+ans1+"</td>";
		else {
			sb1.append(copyFrom1 + " copied into "+copyTo1).append("<br>");
			if(elements.toString().equals("<tr><td><tr><td  bgColor=\"#aaaaaa\">elements from topack folder</td><td>")) {
				elements.append(temp);
				requestNote1.append(" toPack elements:(").append(temp);
			}else {
				elements.append("<br>").append(temp);
				requestNote1.append(",").append(temp);
			}
		}
		HagUtil.updateTimeStamp(iframeF,"copyRiWebWar_END","aaa");
		File ff = new File(copyTo1);
		if(ff.isFile() && ff.exists()) {
			HagUtil.updateTimeStamp(iframeF,"copyRiWebWar_END","aaa");
			return "0~";
		}else {
			HagUtil.updateTimeStamp(iframeF,"copyRiWebWar_END","#FF0000");
			return HagUtil.shortHtml("failed to copy "+copyFrom1+ " into "+copyTo1, "red", "bg");
		}
	}
}
