package com.hag.hagay;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MinorVersions {
	
	public 		static final String 	before(String user,String act) {
		String disabled="";
		String msg = "";
		if(act.equals("REL"))
			msg="insert requested changes and install on local environments";
		else if(act.equals("PACK"))
			msg="pack the minor package to FTP";
		else if(act.equals("NEW")) {
			msg="create new minor version VxRxxMxx";
			disabled="disabled";
		}else if(act.equals("LOADTASK")) {
				msg="load task from trunk";
		}
		String[][] vals = getMinorVersionsList();
		StringBuilder ans = new StringBuilder();
		ans.append("<html><body bgcolor=\"#dddddd\">");
		ans.append("<h2>Minor versions list </h2>");
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"minorVersionsSplit.jsp\">");
		
		ans.append("<input type=\"hidden\" name=\"sentBy\" id=\"sentBy\" value = \""+user+"\" maxlength=\"140\" size=\"140\">");
		ans.append("<input type=\"hidden\" name=\"act\" id=\"act\" value = \""+act+"\" maxlength=\"140\" size=\"140\">");
		
	//ans.append("<font color=#ff0000><b><u>Select one line:</b></u></font><br><br>");
		ans.append("<table bgcolor=\"#9999bb\" cellpadding=\"5\" border =\"1\"  "+disabled+" >");
		ans.append("<tr><td>Minor version</td><td>topack folder</td><td>ri-version</td><td>APP-server</td><td>database</td><td>customer</td><td>note</td><tr>");
		for(int i = 0;i<vals.length;i++) {
			
			
			String rad="";
			String rad1 =vals[i][0]+"|"+vals[i][1]+"|"+vals[i][2]+"|"+vals[i][3]+"|"+vals[i][4]+"|"+vals[i][5]+"|"+vals[i][6]+"|"+vals[i][7]+"|"+vals[i][8];  
			if(vals[i][0].trim().equals("CM_TEST")) {
				 rad = "<input type=\"radio\" id=\"verRad\" name=\"verRad\" value=\""+rad1+"\" checked >";
				 ans.append("<tr bgcolor=\"#ccccee\"><td>").append(rad).append(vals[i][0]).append("</td><td>")
				 .append(vals[i][1]).append("</td><td>")
				 .append(vals[i][2]).append("</td><td>")
				 .append(vals[i][3]).append("</td><td>")
				 .append(vals[i][4]).append("</td><td>")
				 .append(vals[i][5]).append("</td><td>")
				 .append(vals[i][6]).append("</td></tr>");
					
			}else {
				 rad = "<input type=\"radio\" id=\"verRad\" name=\"verRad\" value=\""+rad1+"\"  >";
				 ans.append("<tr bgcolor=\"#ccccee\"><td>").append(rad).append(vals[i][0]).append("</td><td>")
				 .append(vals[i][1]).append("</td><td>")
				 .append(vals[i][2]).append("</td><td>")
				 .append(vals[i][3]).append("</td><td>")
				 .append(vals[i][4]).append("</td><td>")
				 .append(vals[i][5]).append("</td><td>")
				 .append(vals[i][6]).append("</td></tr>");
			}
		}
	
		ans.append("</table>");
		
		ans.append("<br><br>");
		ans.append("<table bgcolor=\"#ffcc99\" cellpadding=\"5\" border =\"1\">");
		ans.append("<tr><td><INPUT TYPE=SUBMIT 	name = \"SUBMIT\" 	value=\"Submit\">			</FORM></td><td>"+msg+"</td></tr>");
		ans.append("</table>");
	
		ans.append("</body></html>");
		return ans.toString();
	

	}
	public 	 	static final String 	split(HttpServletRequest request, HttpServletResponse response){
		
		String act 	= request.getParameter("act");
		
		String sentBy 	= request.getParameter("sentBy").trim();
	 if (act.equals("NEW")) 
	    	return createNewMinor(sentBy);
		
		//String preVer 	= request.getParameter("preVer").trim();
		String verRad 	= request.getParameter("verRad").trim();
		
		String preVer1 = HagUtil.getWord0(verRad,"|",0,true);
		String preVer2 = HagUtil.getWord0(verRad,"|",1,true);
		String appDef = HagUtil.getWord0(verRad,"|",3,true);
		String dbDef = HagUtil.getWord0(verRad,"|",4,true);
		String cust = HagUtil.getWord0(verRad,"|",5,true);
		String note = HagUtil.getWord0(verRad,"|",6,true);
		String riVer = HagUtil.getWord0(verRad,"|",7,true);
		if (act.equals("REL")) {
			return release(sentBy,verRad,preVer1,preVer2,appDef,dbDef);
	    } else if (act.equals("PACK")) {
	    	return packToFtp(sentBy,verRad,preVer1,riVer,appDef,dbDef);
	    } else if (act.equals("LOADTASK")) {
	    	return loadTask(sentBy,verRad,preVer1,riVer,appDef,dbDef);
	    }  
		 return "invalid option - minorVersions - split";
	}
	

	
	private  static final String 	packToFtp(String sentBy,String preVerOrg,String preVer1,String riVer,String appDef,String dbDef){
		String w5 = HagUtil.getWord0(preVerOrg, "|", 5, true);
		String w6 = HagUtil.getWord0(preVerOrg, "|", 6, true);
		if( w6.equals("jenkins process"))
			return HagUtil.shortHtml("Pack this minor version is running under Jenkins (please use "+w5+"_Trunk_CD [Jenkins])","red","bg");
	
		String plat				= getSelectField("plat",new String[] {"WIN","I5OS"});
		String finalPack		= getSelectField("finalPack",new String[] {"YES","NO"});
		String minorVersion 	= getTextField("minorVersion","40",preVer1,"disabled");
		String riVersion 		= getTextField("riVersion","40",riVer,"disabled");
		String packageName 		= getTextField("packageName","20","Uxx","");
		String note 			= getTextField("note","80","","");
		String env2inst 		= getTextField("env2inst","80","","");
		String preVer 			= getHiddenField("preVer","80",preVerOrg);
		String envs 			= getRiEnvSelectField("envs",getTargetEnv("O"));
		//String envs ="<select  STYLE=\"font-family : monospace; font-size : 12pt\" id=\"envs\"  name=\"envs\" >"+getTargetEnv("O")+"</select>";
		String cust 				= getRiEnvSelectField("cust",getCustomers("O"));
		
		StringBuilder ans = new StringBuilder();
		ans.append("<html><body bgcolor=\"#dddddd\">");
		ans.append("<h2>Pack this minor version to FTP </h2>");
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"minorVersionsPack.jsp\">");
		
		ans.append("<input type=\"hidden\" name=\"sentBy\" id=\"sentBy\" value = \""+sentBy+"\" maxlength=\"140\" size=\"140\">");
		
	//ans.append("<font color=#ff0000><b><u>Select one line:</b></u></font><br><br>");
		ans.append("<table bgcolor=\"#ccccff\" cellpadding=\"5\" border =\"1\">");
		ans.append("<tr><td>Title</td><td>value</td><td>must/optional</td><td>note</td><tr>");
		ans.append("<tr bgcolor=\"#ff9966\"><td>Minor version</td><td>"+minorVersion+"</td><td>Must		</td><td>.</td><tr>");
		ans.append("<tr bgcolor=\"#ff9966\"><td>Main version</td><td>"+riVersion+	"</td><td>Must		</td><td>.</td><tr>");
		ans.append("<tr bgcolor=\"#ff9966\"><td>Platform	</td><td>"+plat+		"</td><td>Must		</td><td>.</td><tr>");
		ans.append("<tr bgcolor=\"#ff9966\"><td>Package name</td><td>"+packageName+	"</td><td>Must		</td><td>Uxx only</td><tr>");
		ans.append("<tr bgcolor=\"#ff9966\"><td>Final pack	</td><td>"+finalPack+	"</td><td>Must		</td><td>.</td><tr>");
		ans.append("<tr bgcolor=\"#ffff99\"><td>Customer   	</td><td>"+cust+		"</td><td>Optional	</td><td>.</td><tr>");
		ans.append("<tr bgcolor=\"#ffff99\"><td>Note        </td><td>"+note+		"</td><td>Optional	</td><td>.</td><tr>");
		ans.append("<tr bgcolor=\"#ffff99\"><td>Install on  </td><td>"+env2inst+	"</td><td>Optional	</td><td>win env to install on:e.g  RI35-APP/AA  RI33Q/BB</td><tr>");
		
		ans.append("<tr bgcolor=\"#ffff99\"><td>add DB      </td><td>"+envs+"</td><td>Optional</td><td>only if needed</td><tr>");
		
		ans.append(preVer);
		ans.append("</table><br>");
		
		ans.append("<INPUT TYPE=SUBMIT  value=\"Submit the request\"></FORM>");
	
	
		ans.append("</body></html>");
		return ans.toString();
		
	}
	private  static final String 	loadTask(String sentBy,String preVerOrg,String preVer1,String riVer,String appDef,String dbDef){
		HagUtil.appendToDebugFile( "in loadTask" ) ;
		HagStringList loadFromTrunkList= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\minorVersionsTrunks.txt",true,"*",false);
		String[] loadFromTrunkArr=loadFromTrunkList.convertToArray();

		String plat				= getSelectField("plat",new String[] {"WIN","I5OS"});
		//String autoRestore		= getSelectField("autoRestore",new String[] {"YES","NO"});
		String autoRestore		= getSelectField("autoRestore",new String[] {"YES"});
		String minorVersion 	= getTextField("minorVersion","40",preVer1,"disabled");
		String riVersion 		= getTextField("riVersion","40",riVer,"disabled");
		String loadFromTrunk		= getSelectField("loadFromTrunk",loadFromTrunkArr);
		String taskNo 			= getTextField("taskNo","30","","");
	
		//String note 			= getTextField("note","80","","");
	
		String preVer 			= getHiddenField("preVer","80",preVerOrg);
		String autoRestoreNote 			= "in case of errors when running the loads,the default option will restore the target database automaticaly ";
	
	
		//String envs ="<select  STYLE=\"font-family : monospace; font-size : 12pt\" id=\"envs\"  name=\"envs\" >"+getTargetEnv("O")+"</select>";
	
		String iFrameFolder="\\\\ri-archive\\Saptech-Archive\\RI\\debug\\minor_tasks\\iframe";
		String iframeInit = HagUtil.copyFileViaDos(iFrameFolder+"\\iframeSkel.html", iFrameFolder+"\\"+preVer1+".html");
		
		if(!iframeInit.startsWith("0~"))
			return HagUtil.shortHtml("Failed to init iFrameFile6. callhagay - 2527<br>"+iframeInit, "red","bg");
		
		
		StringBuilder ans = new StringBuilder();
		ans.append("<head><script type=\"text/javascript\">");
		ans.append("function refreshIFrame() {");
		ans.append("var interval =setInterval(\"reloadIFrame();\", 3000);");
		ans.append("}");
		ans.append("function reloadIFrame() {");
		ans.append("document.getElementById('iframe_id').src += '';");
		ans.append("}");
		ans.append("function getBrowser()"); 
		ans.append("{");
		ans.append("}");
		ans.append("</script></head>	");
		ans.append("<body onload=\"refreshIFrame();getBrowser()\">");
		//ans.append("<html><body bgcolor=\"#dddddd\">");
		ans.append("<h2>Load task from trunk to  this minor version</h2>");
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"minorVersionsLoadTask.jsp\">");
		
		ans.append("<input type=\"hidden\" name=\"sentBy\" id=\"sentBy\" value = \""+sentBy+"\" maxlength=\"140\" size=\"140\">");
		
	//ans.append("<font color=#ff0000><b><u>Select one line:</b></u></font><br><br>");
		ans.append("<table bgcolor=\"#ccccff\" cellpadding=\"5\" border =\"1\">");
		ans.append("<tr><td>Title</td><td>value</td><td>must/optional</td><td>note</td><tr>");
		ans.append("<tr bgcolor=\"#ff9966\"><td>Minor version	</td><td>"+minorVersion+	"</td><td>Must		</td><td>.</td><tr>");
		ans.append("<tr bgcolor=\"#ff9966\"><td>Main version	</td><td>"+riVersion+		"</td><td>Must		</td><td>.</td><tr>");
		ans.append("<tr bgcolor=\"#ff9966\"><td>loadFromTrunk	</td><td>"+loadFromTrunk+	"</td><td>Must		</td><td>select source trunk</td><tr>");
		ans.append("<tr bgcolor=\"#ff9966\"><td>task			</td><td>"+taskNo+			"</td><td>Must		</td><td>task number (in the trunk source environment)</td><tr>");
		//ans.append("<tr bgcolor=\"#ffff99\"><td>Note        	</td><td>"+note+			"</td><td>Optional	</td><td>.</td><tr>");
		ans.append("<tr bgcolor=\"#ff9966\"><td>AutoRestore		</td><td>"+autoRestore+		"</td><td>Must		</td><td>"+autoRestoreNote+"</td><tr>");
		
		ans.append(preVer);
		ans.append("</table><br>");
		
		ans.append("<h3> The following process will run 5 minutes and will do the follwing:</h3>");
		ans.append("<h4>1) check if the target environment is lock or free<br>");
		ans.append("2) backup the target environment<br>");
		ans.append("3) extract the task from the source environemnt<br>");
		ans.append("4) lock the target environment<br>");
		ans.append("5) load the extracted task to the target environment<br>");
		ans.append("6) if load process failed then restore the target environment<br>");
		ans.append("7) if target environment not damaged - release the lock on the target environment<br><br><br>");
		
		ans.append("<INPUT TYPE=SUBMIT  value=\"Submit\"  onclick=\"this.style.display = 'none' \"></FORM>");
		//ans.append("<INPUT TYPE=SUBMIT  value=\"Submit\" style=\"font-size:14pt;color:white;background-color:green;border:2px solid #336600;padding:3px;[COLOR=\"Red\"]display:inline[/COLOR]\" onclick=\"this.disabled=true;this.value='Running, please wait...it takes 5 minutes to backup DB,extract task,load task and more..';document.getElementById('loader').style.display = 'block'\"></FORM>");
		//onclick=\"this.disabled=true;this.value='Running, please wait...';document.getElementById('loader').style.display = 'block';\"
		ans.append("<br><iframe  id=\"iframe_id\" name=\"iframe_id\" src=\"file://///ri-archive/Saptech-Archive/RI/debug/minor_tasks/iframe/"+preVer1+".html\" height=\"850\" width=\"1500\"></iframe>");		

		ans.append("</body></html>");
		return ans.toString();
		
	}
	private  static final String 	createNewMinor(String sentBy){
		String minorVersionName 	= getTextField("minorVersionName","40","","");
		String note 				= getTextField("note","80","","");
		String preAct 				= getTextField("preAct","80","","");
		String prefDb 				= getTextField("prefDb","40","","");
	
		String ariaBase3 			= getRiEnvSelectField("ariaBase",getVersionsMulti("  where Package_Name like '%(from_FTP)%' or Package_Name like '%(from_Jenkins)%'"));
	
		String envs 				= getRiEnvSelectField("envs",getTargetEnv("O"));
		String cust 				= getRiEnvSelectField("cust",getCustomers("O"));
		String riVers 				= getRiEnvSelectField("riVers",getVersionsFromFile());
		
		StringBuilder ans = new StringBuilder();
		ans.append("<html><body bgcolor=\"#dddddd\">");
		ans.append("<h2>Create a new Minor version </h2>");
		ans.append("<h3>base Env should be installed with base ARIA , if not, first send a request for it </h3>");
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"minorVersionsNew.jsp\">");
		
		ans.append("<table bgcolor=\"#ccccff\" cellpadding=\"5\" border =\"1\">");
		ans.append("<tr><td>Title</td><td>value</td><td>must/optional</td><td>note</td><tr>");
		ans.append("<tr bgcolor=\"#ff9966\"><td>Requester				</td><td>"+sentBy+			"</td><td>Must		</td><td>.			</td><tr>");
		ans.append("<tr bgcolor=\"#ff9966\"><td>Minor version name		</td><td>"+minorVersionName+"</td><td>Must		</td><td>VxRxxMxx	</td><tr>");
		ans.append("<tr bgcolor=\"#ff9966\"><td>base ARIA       		</td><td>"+ariaBase3+		"</td><td>Must		</td><td>which package should be the base for this minor version	</td><tr>");
		ans.append("<tr bgcolor=\"#ff9966\"><td>base Env       			</td><td>"+envs+			"</td><td>Must		</td><td>which environment should be the base for this minor version	</td><tr>");
		ans.append("<tr bgcolor=\"#ffff99\"><td>RIDEVBLP05 DB  			</td><td>"+prefDb+			"</td><td>Optional	</td><td>preferred DB name in RIDEVBLP05  		</td><tr>");
		ans.append("<tr bgcolor=\"#ffff99\"><td>Customer        		</td><td>"+cust+			"</td><td>Optional	</td><td>.  		</td><tr>");
		ans.append("<tr bgcolor=\"#ffff99\"><td>Note        			</td><td>"+note+			"</td><td>Optional	</td><td>.  		</td><tr>");
		ans.append("<tr bgcolor=\"#ffff99\"><td>preActions     			</td><td>"+preAct+			"</td><td>Optional	</td><td>what do we need to do before  		</td><tr>");
		ans.append("<input type=\"hidden\" name=\"sentBy\" id=\"sentBy\" value = \""+sentBy+"\" maxlength=\"140\" size=\"140\">");
		ans.append("</table><br>");
		ans.append("<INPUT TYPE=SUBMIT  value=\"submit the request\"></FORM>");
		ans.append("</body></html>");
		return ans.toString();
		
	}
	private  static final String 	release(String sentBy,String preVer,String preVer1,String preVer2,String appDef,String dbDef){
		String w5 = HagUtil.getWord0(preVer, "|", 5, true);
			
		String w6 = HagUtil.getWord0(preVer, "|", 6, true);
		if( w6.equals("jenkins process"))
			return HagUtil.shortHtml("Release to this minor version is running under Jenkins (please use "+w5+"_Trunk_CI-QA [Jenkins])","red","bg");
		//String preAct 				= getTextField("preAct","80","","");
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
		String preAct="<input type=\"text\" id=\"preAct\"  name=\"preAct\" size=\"80\">";	
		
	//	StringBuilder webProxy =new StringBuilder("<select name=\"webProxy\">");	
		//webProxy.append("<option class=\"c30\" value=\"").append("NO").append("\">").append("no need to reInstall webProxy").append("</option>");
	//	webProxy.append("<option class=\"c30\" value=\"").append("YES").append("\">").append("reInstall webProxy").append("</option>");
	//	webProxy.append("</select>");	
		
		String webProxy ="(msi) from \\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V0800m00\\TOPACK\\WebProxy\\WebProxy.msi";
		if(preVer2.equals("V0801m01U00")||preVer2.equals("V0800m01U00"))
			 webProxy ="(AsFolder) from \\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V0801m00\\TOPACK\\WebProxyAsFolder\\WebProxy\\";

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
				
				.append("<FORM METHOD=POST name=\"Form\" ACTION=\"minorVersionsRelease.jsp\">")
				.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:100%\">")
				.append("<tr><td bgcolor=\"#ccccaa\">Minor Version</td><td>").append(preVer1).append("</td></tr>")
				.append("<tr><td bgcolor=\"#ccccaa\">Sent by</td><td>").append(sentBy).append("</td></tr>")
				.append("<tr><td bgcolor=\"#ccccaa\">DDC from</td><td>").append(from).append("</td></tr>")
				.append("<tr><td bgcolor=\"#ccccaa\">Topack folder check</td><td>").append(topackFolder).append("</td></tr>")
				//.append("<tr><td bgcolor=\"#ccccaa\">WebProxy</td><td>").append(topackFolder).append("</td></tr>")
				.append("<tr><td bgcolor=\"#ccccaa\">Components check</td><td>").append(componentsList.toString()).append("</h4></td></tr>")
				.append("<tr><td bgcolor=\"#ccccaa\">webProxy</td><td>").append(webProxy).append("</td></tr>")
	//				.append("<tr><td bgcolor=\"#ccccaa\">install webProxy(if exists)</td><td>").append(webProxy).append("</td></tr>")
				//.append("<tr><td bgcolor=\"#ccccaa\">FTP folder name</td><td>").append(ftp).append("(noNeed=do not send to FTP)</td></tr>")
				.append("<tr><td bgcolor=\"#ccccaa\">FTP folder name</td><td>").append("Changed to release only request<br>in order to send this package to FTP you must finish this request<br>and then send a pack-to-ftp request via cfgMenuWeb->requests->req30</td></tr>")
				.append("<tr><td bgcolor=\"#ccccaa\">win environments<br>to install</td><td>").append(winEnvToInstall.toString()).append("</td></tr>")
				.append("<tr><td bgcolor=\"#ccccaa\">Note</td><td>").append(note).append("</td></tr>")
				.append("<tr><td bgcolor=\"#ccccaa\">PreAction</td><td>").append(preAct).append("</td></tr>");
				sb.append("<input type=\"hidden\" name=\"sentBy\" id=\"sentBy\" value = \""+sentBy+"\" maxlength=\"140\" size=\"140\">");
				sb.append("<input type=\"hidden\" name=\"preVer\" id=\"preVer\" value = \""+preVer+"\" maxlength=\"140\" size=\"140\">");
				sb.append("<input type=\"hidden\" name=\"preVer2\" id=\"preVer\" value = \""+preVer2+"\" maxlength=\"140\" size=\"140\">");
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
	
	
	
	public  		String 			releaseReqSend(HttpServletRequest request, HttpServletResponse response){//991
		String needToCheck="";
		String 	 preAct				= request.getParameter("preAct");
		String sentBy 	= request.getParameter("sentBy").trim();
		String 	 preVer	= request.getParameter("preVer").trim();
		String 	 preVer2	= request.getParameter("preVer2").trim();
	
			
		String ddcFrom 	= request.getParameter("from").trim();
		//String webProxy 	= request.getParameter("webProxy").trim();
		String noteFromUser 	= request.getParameter("note").trim();
		String iframeF 	= request.getParameter("iframeF").trim();
		HagUtil.updateTimeStamp(iframeF,"d1_START","");
		//String ftp 	= request.getParameter("ftp").trim();
		String [] cbgroup1		= request.getParameterValues("cb1");
		String [] cbgroup2		= request.getParameterValues("cb2");
		
		if(preAct==null 			|| preAct.trim().length()==0) 			preAct="."; 		else preAct=preAct.trim();
		
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
		String verPackages= HagUtil.getWord0(preVer,"|",7,true);
		String cust= HagUtil.getWord0(preVer,"|",5,true);
			
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
				///if(temp.equalsIgnoreCase("WebProxy.msi")) {
						
				///	String ans=packWebProxy(iframeF, topack,elements, requestNote1,flagWebProxy,into,sb1,verPackages);
				///	if(!ans.startsWith("0~"))
				///		return ans;
				///}
				//webProxy
				if(i==0) {
					if(preVer2.equals("V0801m01U00") || preVer2.equals("V0800m01U00")) {
						String ans=packWebProxyAsFolder(iframeF, topack,elements, requestNote1,flagWebProxy,into,sb1,verPackages);
						if(!ans.startsWith("0~")) {
							if(ans.startsWith("999~")) {
								String toPackWebProxy = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V0801m00\\TOPACK\\WebProxyAsFolder\\WebProxy";
							
								String copyTo1=into+"\\bin\\packages\\"+verPackages+"\\fromTopack\\WebProxy";
								needToCheck =" need to compare "+toPackWebProxy+" with "+copyTo1+" (ask hagay)";
							}else if(ans.startsWith("998~")) {
								needToCheck =" no need to replace webProxyAsFolder(same build number)";
							}else {
								return ans;
							}
						}
					}else {
						String ans=packWebProxyMsi(iframeF, topack,elements, requestNote1,flagWebProxy,into,sb1,verPackages);
						if(!ans.startsWith("0~"))
							return ans;
					}
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
				String ans=crtDeleteFromMigDetailsList(verSvn);
				if(!ans.startsWith("0~"))
					return ans;
				
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
	///	requestNote1.append(" install webProxy:(").append(webProxy).append(")");
		
	///	if(!flagWebProxy[0] && webProxy.equals("YES"))
	///		return "<td bgColor=\"#FF0000\">cannot select to reinstall webProxy when webProxy is not selected</td>";
	
	///	StringBuilder instWebProxy = new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">Install WebProxy</td><td>").append(webProxy).append("</td></tr>");
		
		elements.append("</td></tr>");
		
		
		if(!withDDCFlag[0]) {
			ddc.append("without DDC");
			requestNote1ddc[0]="ddc:(without DDC)";

		}
		ddc.append("</td></tr>");
		requestNote1.append(requestNote1ddc[0]);
		StringBuilder noteFromSender = new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">note from sender</td><td>").append(noteFromUser).append("</td></tr>");
		StringBuilder preAct1 		= new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">preAct</td><td>").append(preAct).append("</td></tr>");
		
			content.append(ddc.toString());
		content.append(elements.toString());
		content.append(installOn.toString());
	//	content.append(ftpFolder.toString());
		///content.append(instWebProxy.toString());
		content.append(noteFromSender.toString());
		content.append(preAct1.toString());
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
		String subject 	= "INSTALL MinorVersion "+verSvn;
		String subject1 	="#Request "+subject+" @BREAK-REQ@ #"+indS+"# sent by "+sentBy;

		
		String to = "david.ha@sapiens.com;gonen.s@sapiens.com;"+sentBy+"@sapiens.com";
		String ccMailList 	= HagUtil.getRiMails("all");
		// to = "david.ha@sapiens.com";
		// ccMailList 	= "david.ha@sapiens.com";
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
			//spr1011noteFromUser
			//if(!HagUtil.addRequest(ind,sentBy, requestNote1.toString(),subject1,".",".",".",winInstallOn,null,null,null,991))
	
			noteFromUser=noteFromUser+needToCheck;
			
			//if(!HagUtil.addRequest(ind,sentBy, noteFromUser,subject,".",".",".",winInstallOn,null,null,verSvn,991))
			//HagUtil.addRequest(ind,sentBy, noteFromUser,subject,".",".",".",winInstallOn,null,null,verSvn,991)
			String[][] varVal = {
					{"[ind]",""+ind},
					{"[doneBy]",sentBy},
					{"[note]",noteFromUser},
					{"[customer]",cust},
					{"[subject]",subject},
					{"[Customer_code_Party_desc]",cust+"~.~."},
					{"[Envs_To_Install]",winInstallOn},
					{"[tgtVer]",verSvn},
					{"[Pre_Req]",preAct},
					{"[Req_Type]","991"},
					};

			if(!HagUtil.addRequestNew(varVal))
				return HagUtil.shortHtml("Failed to update req_ind_log_new table.", "red","bg");
			HagUtil.writeToRelDiary2("preVersionRelease","WIN","Request",verSvn,".",".",".",".","#"+indS,sentBy,".",".");
	//	}
		
		HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\cfgMenuWeb\\preVersion.txt", "6","preVersionRequest"  ) ;	
		
		
		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+sentBy,HagUtil.mailList_hag,subject1,content.toString()+"<br>"+noteFromUser+"<br><br>Logs:<br>"+sb1.toString()); 
		HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\cfgMenuWeb\\preVersion.txt", "7","preVersionRequest"  ) ;	
		
		if(!ans1.startsWith("0~")) 
			return HagUtil.shortHtml("Failed to send mail.<br>"+ans1, "red","bg");
		HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\cfgMenuWeb\\preVersion.txt", "end","preVersionRequest"  ) ;	
		
		
	return content.toString()+"<br><br>Logs:<br>"+sb1.toString()+ "<br><br> #"+ind+" request sent.";
	}	
	public  		String 			packReqSend(HttpServletRequest request, HttpServletResponse response){//32
		String 	 sentBy 		= request.getParameter("sentBy").trim();
		String 	 preVer			= request.getParameter("preVer").trim();
		String 	 packageName	= request.getParameter("packageName").trim();
		String 	 env2inst		= request.getParameter("env2inst");
		String 	 note			= request.getParameter("note");
		String   envs1			= request.getParameter("envs");
		String 	 cust			= request.getParameter("cust");
		
		String baseEnvAppServer =HagUtil.getWord0(envs1, "|", 0, true).toUpperCase();
		String baseEnvDbid =HagUtil.getWord0(envs1, "|", 1, true).toUpperCase();	
		String baseEnvSqlServer =HagUtil.getWord0(envs1, "|", 2, true).toUpperCase();
		String baseEnvDbName =baseEnvDbid;
		if(baseEnvDbid.equals("RI"))
			baseEnvDbName ="RIAPPLDB";
		
		if(cust==null 				|| cust.trim().length()==0) 			cust="."; 		else cust=cust.trim();
		if(note==null 			|| note.trim().length()==0) 			note="."; 		else note=note.trim();
		if(env2inst==null 		|| env2inst.trim().length()==0) 		env2inst="."; 	else env2inst=env2inst.trim();
		if(packageName==null 	|| packageName.trim().length()==0) 		
			return HagUtil.shortHtml("Error: package name cannot be empty", "red", "bg");
		if(!checkEnv2inst(env2inst))
			return HagUtil.shortHtml("Error: check env to install field<br><br> should be: <br>appServer/batchName appServer/batchName ..<br>And the environments should be a working environments too<br><br>wrong value:<br>"+env2inst , "red", "bg");

		
		String 	minorVersion 	= HagUtil.getWord0(preVer, "|", 0, true);
		String riVersion = HagUtil.getWord0(preVer, "|", 1, true);
		int ind = HagUtil.getRequestInd();
		String indS = HagUtil.padNumLeft(""+ind, 5, '0');
		String subject 	= "PACK MinorVersion "+minorVersion+" to "+packageName;
		String subject1 	="#Request "+subject+" @BREAK-REQ@ #"+indS+"# sent by "+sentBy;
		String to = "david.ha@sapiens.com;gonen.s@sapiens.com;"+sentBy+"@sapiens.com";
		String ccMailList 	= HagUtil.getRiMails("all");
		//to = "david.ha@sapiens.com";
		//ccMailList = "david.ha@sapiens.com";
		
		StringBuilder sentBy1 		= new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">sent by</td><td>").append(sentBy).append("</td></tr>");
		StringBuilder minorVersion1 = new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">Minor Version</td><td>").append(minorVersion).append("</td></tr>");
		StringBuilder riVersion1 	= new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">RI version</td><td>").append(riVersion).append("</td></tr>");
		StringBuilder packageName1 	= new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">package name</td><td>").append(packageName).append("</td></tr>");
		StringBuilder env2inst1 	= new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">env to install</td><td>").append(env2inst).append("</td></tr>");
		StringBuilder note1 		= new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">note from sender</td><td>").append(note).append("</td></tr>");
		StringBuilder cust1 		= new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">Customer</td><td>").append(cust).append("</td></tr>");
		
		StringBuilder addDb1 		= new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">add DB to the package(will be sent as RIAPPLDB)</td><td>").append(envs1).append("</td></tr>");
		
		StringBuilder content=new StringBuilder("<table bgColor=\"#dddddd\" cellpadding=\"3\" border =\"1\">");
		content.append("<tr bgColor=\"#aaaaaa\"><td>Element</td><td>Value</td></tr>");
			
		content.append(sentBy1.toString());
		content.append(minorVersion1.toString());
		content.append(riVersion1.toString());
		content.append(packageName1.toString());
		content.append(env2inst1.toString());
		content.append(note1.toString());
		content.append(cust1.toString());
		content.append(addDb1.toString());
		
		content.append("</table>");
		String[][] varVal = {
							{"[ind]",""+ind},
							{"[doneBy]",sentBy},
							{"[note]",note},
							{"[Envs_To_Install]",env2inst},
							{"[subject]",subject},
							{"[customer]",cust},
							{"[tgtVer]",cust},
							{"[srcEnv]",baseEnvAppServer+"/"+baseEnvDbid},
							{"[FTP_package_Name]",riVersion+"|"+minorVersion},
							{"[Customer_code_Party_desc]",cust+"~.~."},
							{"[Req_Type]","32"},
							};
	
		if(!HagUtil.addRequestNew(varVal))
			return HagUtil.shortHtml("Failed to update req_ind_log_new table.", "red","bg");
		//if(!HagUtil.addRequest(ind,sentBy, note,subject1,".",".",".",env2inst,null,null,null,32))
		HagUtil.writeToRelDiary2("minorVersionPack","WIN","Request",minorVersion,"#"+ind,".",".",".",packageName,sentBy,".",".");
			
		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+sentBy,HagUtil.mailList_hag,subject1,content.toString()); 
		
		if(!ans1.startsWith("0~")) 
			return HagUtil.shortHtml("Failed to send mail.<br>"+ans1, "red","bg");
			
		return content.toString()+ "<br><br> #"+ind+" request sent.";
		
	}
	public  		String 			newReqSend(HttpServletRequest request, HttpServletResponse response){//104
	
		String 	 minorVersionName 	= request.getParameter("minorVersionName").trim();
		
		String 	 ariaBase			= request.getParameter("ariaBase").trim();
		String 	 envs1				= request.getParameter("envs").trim();
		String 	 cust				= request.getParameter("cust");
		String 	 note				= request.getParameter("note");
		String 	 preAct				= request.getParameter("preAct");
		String 	 prefDb				= request.getParameter("prefDb");
		String 	 sentBy				= request.getParameter("sentBy").trim();
	//	String 	 riVers				= request.getParameter("sentBy").trim();
		String baseEnvAppServer =HagUtil.getWord0(envs1, "|", 0, true).toUpperCase();
		String baseEnvDbid =HagUtil.getWord0(envs1, "|", 1, true).toUpperCase();	
		String baseEnvSqlServer =HagUtil.getWord0(envs1, "|", 2, true).toUpperCase();
		String baseEnvDbName =baseEnvDbid;
		if(baseEnvDbid.equals("RI"))
			baseEnvDbName ="RIAPPLDB";
		
		if(cust==null 				|| cust.trim().length()==0) 			cust="."; 		else cust=cust.trim();
		if(note==null 				|| note.trim().length()==0) 			note="."; 		else note=note.trim();
		if(prefDb==null 			|| prefDb.trim().length()==0) 			prefDb="."; 		else prefDb=prefDb.trim();
		if(preAct==null 			|| preAct.trim().length()==0) 			preAct="."; 		else preAct=preAct.trim();
		HagRc hagRc=new HagRc();
		checkAriaVsEnv( hagRc, ariaBase, baseEnvSqlServer, baseEnvDbName);
		if(hagRc.rc!=0) {
			return HagUtil.shortHtml("Error: "+hagRc.log.get(0), "red", "bg");
		}
		
		if(minorVersionName==null 	|| minorVersionName.trim().length()==0) 		
			return HagUtil.shortHtml("Error: minorVersionName cannot be empty", "red", "bg");
		if(ariaBase==null 	|| ariaBase.trim().length()==0) 		
			return HagUtil.shortHtml("Error: base ARIA  cannot be empty", "red", "bg");
		if(envs1==null 	|| envs1.trim().length()==0) 		
			return HagUtil.shortHtml("Error: base Env cannot be empty", "red", "bg");
		
	//	checkAriaVsEnv(baseEnvSqlServer,baseEnvDbName);
	
		int ind = HagUtil.getRequestInd();
		String indS = HagUtil.padNumLeft(""+ind, 5, '0');
		String subject 	= "CREATE MinorVersion "+minorVersionName;
		String subject1 	="#Request "+subject+" @BREAK-REQ@ #"+indS+"# sent by "+sentBy;
		String to = "david.ha@sapiens.com;gonen.s@sapiens.com;"+sentBy+"@sapiens.com";
		String ccMailList 	= HagUtil.getRiMails("all");
		//to = "david.ha@sapiens.com";
		//ccMailList = "david.ha@sapiens.com";
		
		StringBuilder sentBy1 		= new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">sent byl</td><td>").append(sentBy).append("</td></tr>");
		StringBuilder minorVersion1 = new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">Minor Version</td><td>").append(minorVersionName).append("</td></tr>");
		StringBuilder ariaBase1 	= new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">Base ARIA</td><td>").append(ariaBase).append("</td></tr>");
		StringBuilder baseEnvs1 	= new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">Base ENV</td><td>").append(envs1).append("</td></tr>");
		StringBuilder cust1 		= new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">Customer</td><td>").append(cust).append("</td></tr>");
		StringBuilder preAct1 		= new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">preAct</td><td>").append(preAct).append("</td></tr>");
		StringBuilder prefDb1 		= new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">preferred db name</td><td>").append(prefDb).append("</td></tr>");
		StringBuilder note1 		= new StringBuilder("<tr><td  bgColor=\"#aaaaaa\">note from sender</td><td>").append(note).append("</td></tr>");
		
		StringBuilder content=new StringBuilder("<table bgColor=\"#dddddd\" cellpadding=\"3\" border =\"1\">");
		content.append("<tr bgColor=\"#aaaaaa\"><td>Element</td><td>Value</td></tr>");
			
		content.append(sentBy1.toString());
		content.append(minorVersion1.toString());
		content.append(ariaBase1.toString());
		content.append(baseEnvs1.toString());
		content.append(cust1.toString());
		content.append(preAct1.toString());
		content.append(prefDb1.toString());
		content.append(note1.toString());
		
		content.append("</table>");
		
		String[][] varVal = {
				{"[ind]",""+ind},
				{"[doneBy]",sentBy},
				{"[note]",note},
				{"[customer]",cust},
				{"[subject]",subject},
				{"[Customer_code_Party_desc]",cust+"~.~."},
				{"[srcEnv]",baseEnvAppServer+"/"+baseEnvDbid},
				{"[Pref_DBID]",prefDb},
				{"[FTP_package_Name]",ariaBase},
				{"[Pref_DBID]",prefDb},
				{"[tgtEnv]","RIDEVBLP05/"+prefDb},
				{"[Pre_Req]",preAct},
				{"[Req_Type]","104"},
				};

		if(!HagUtil.addRequestNew(varVal))
			return HagUtil.shortHtml("Failed to update req_ind_log_new table.", "red","bg");
		//if(!HagUtil.addRequest(ind,sentBy, note,subject1,".",".",".",env2inst,null,null,null,32))
		HagUtil.writeToRelDiary2("minorVersionNew","WIN","Request",minorVersionName,"#"+ind,".",".",".",".",sentBy,".",".");

		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+sentBy,HagUtil.mailList_hag,subject1,content.toString()); 

		if(!ans1.startsWith("0~")) 
			return HagUtil.shortHtml("Failed to send mail.<br>"+ans1, "red","bg");

		return content.toString()+ "<br><br> #"+ind+" request sent.";
		
	}
	public  		String 			loadTaskReqSend(HttpServletRequest request, HttpServletResponse response){//32
		String 		sentBy 		= request.getParameter("sentBy").trim();
		String 		preVer			= request.getParameter("preVer").trim();
	//	String 	 	note			= request.getParameter("note");
		String   	loadFromTrunk	= request.getParameter("loadFromTrunk");
		String 	 	taskNo			= request.getParameter("taskNo");
		String 		trunk			= HagUtil.getWord0(loadFromTrunk, "|", 0, true);
		String 		bisServerS		= HagUtil.getWord0(loadFromTrunk, "|", 1, true);
		String 		batchNameS		= HagUtil.getWord0(loadFromTrunk, "|", 2, true);
		String 		sqlServerS		= HagUtil.getWord0(loadFromTrunk, "|", 3, true);
		String 		minorVer		= HagUtil.getWord0(preVer, "|", 0, true);
		String 		bisServerT		= HagUtil.getWord0(preVer, "|", 3, true);
		String 		batchNameT		= HagUtil.getWord0(preVer, "|", 4, true);
		String 		sqlServerT		= HagUtil.getWord0(preVer, "|", 8, true);
		HagRc hagRc = new HagRc();
		String 		ans 			= runLoadTaskProcesses( hagRc,minorVer,bisServerS, batchNameS, bisServerT, batchNameT, taskNo,sqlServerS,sqlServerT,sentBy);
		
		String rc= "OK";
		if(hagRc.rc==1)
			rc= "Failed";	
		
		String subject 	= "load task("+taskNo+") from trunk("+trunk+") to minorVersion("+minorVer+")";
		
		String to = "david.ha@sapiens.com;gonen.s@sapiens.com;"+sentBy+"@sapiens.com";
		String ccMailList 	= HagUtil.getRiMails("all");
		// to = "david.ha@sapiens.com";
		 //ccMailList 	= "david.ha@sapiens.com";
		HagUtil.writeToRelDiary2("loadTask","WIN","toMinor",minorVer,taskNo,rc,".",".","from "+trunk,sentBy,".",".");

		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+sentBy,HagUtil.mailList_hag,subject,ans); 

		if(!ans1.startsWith("0~")) 
			return HagUtil.shortHtml("Failed to send mail.<br>"+ans1, "red","bg");

		return ans+ "<br><br>mail sent.";
		
		
		//return ans;
	}

	

	public 	static  String 			prepIframe(String  iframeF){
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
	private 		HagStringList 	getDeleteFromMigDetails() {
		HagStringList list = new HagStringList();
		
		String stm ="SELECT version,jclass, customer,location    FROM dbo.DeleteFromMigDetails where relevant='YES' AND status='ON'";
		
		HagSQL hagSQL = new HagSQL();
		ArrayList<String> results = hagSQL.select("SQL", "confg1", "cfg", "cfg09c", HagParam.getConfg1DB(), stm, 4, "|", null, null);
		
		for(int i = 0 ; i < results.size();i++){
				list.add(results.get(i));
		}
		return list;
		
	}
	public  		String  		crtDeleteFromMigDetailsList(String minorVersion) {
		HagRc hagRc = new HagRc();
		String tgt = "\\\\ri-archive\\Saptech-Archive\\RI\\preVersionBases\\"+minorVersion+"\\";
		HagStringList deleteFromMigDetailsList  = getDeleteFromMigDetails();
		String deleteFromMigDetailsFile = tgt+"\\bin\\config\\lists\\deleteFromMigDetails.txt";
		deleteFromMigDetailsList.writeToFilewithRc(hagRc,deleteFromMigDetailsFile, null, false);
		if(hagRc.rc!=0) {
			return "1~"+hagRc.log.get(0);
		}
		return "0~DeleteFromMigDetailsList created";
	}
	public  		String  		packDdcAndIom(String iframeF,String ddcFrom ,String[] requestNote1ddc,StringBuilder ddc ,boolean[] withDDCFlag,String into,StringBuilder sb1,String verPackages) {
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
	public  		String  		packWebProxyAsFolder(String iframeF,String topack ,StringBuilder  elements ,StringBuilder requestNote1,boolean[] flagWebProxy,String into,StringBuilder sb1,String verPackages) {
		boolean needToCheck = false;
		String toPackWebProxy = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V0801m00\\TOPACK\\WebProxyAsFolder\\WebProxy";
		HagUtil.updateTimeStamp(iframeF,"copyWebProxy_START","");
		//String copyFrom1=topack+"\\ALL\\WebProxy.msi";
		//String copyTo1=into+"\\bin\\packages\\"+verPackages+"\\WebProxy\\WebProxy.msi";
		String copyTo1=into+"\\bin\\packages\\"+verPackages+"\\fromTopack\\WebProxy";
		//String ans3 = HagUtil.copyFileViaDos(copyFrom1, copyTo1);	
	//	HagRc hagRc = new HagRc();
	//	HagUtil.reCreateFolder(hagRc, copyTo1);
	//	if(hagRc.rc!=0){
		//	return "<td bgColor=\"#FF0000\">Failed to recreate webproxy folder "+copyTo1+"<br>"+hagRc.log.convertToString("<br>") +"</td>";
		//}
		//String ans3 = HagUtil.copyFolder( toPackWebProxy,copyTo1,false,false,"1","");
		//String ans3 = HagUtil.simpleDosCmd("javaFunc~simpleDosCmd~copy~/Y~\""+toPackWebProxy+"\" \""+copyTo1+"\\"+"\"",false);
		//String ans2 =HagUtil.deleteFolder(copyTo1, null, false, true);
		//if(!ans2.startsWith("0~"))
		//	return "<td bgColor=\"#FF0000\">Failed to delete "+copyTo1+"<br>"+ans2+"</td>";
	
		//String ans3 = HagUtil.copyFolder(toPackWebProxy, copyTo1, false, false,"1","");
		//sb1.append("copy WebProxy as folder from topack:").append("<br>");
		//if(!ans3.startsWith("0~"))
		//	return "<td bgColor=\"#FF0000\">Failed to copy "+toPackWebProxy+" to "+copyTo1+"<br>"+ans3+"</td>";
		String isWebProxyUpgradeNeededStr ="{no need-same build number}";
		HagRc hagRc=new HagRc();
		boolean isWebProxyUpgradeNeeded= isWebProxyUpgradeNeeded( hagRc,toPackWebProxy,copyTo1);
		if(hagRc.rc!=0)
			return "<td bgColor=\"#FF0000\">"+hagRc.log.get(0)+"</td>";
		
		
		if(isWebProxyUpgradeNeeded==true) {
			isWebProxyUpgradeNeededStr ="";
			String ans3 =HagUtil.hagXcopy(toPackWebProxy, copyTo1, false, false, "1");
			if(!ans3.startsWith("0~")) {
				needToCheck = true;
					//return "<td bgColor=\"#FF0000\">Failed to copy "+toPackWebProxy+" to "+copyTo1+"<br>"+ans3+"</td>";
			}
		}
		flagWebProxy[0]=true;
		sb1.append(toPackWebProxy + " copied into "+copyTo1).append("<br>");
			                            
		if(elements.toString().equals("<tr><td  bgColor=\"#aaaaaa\">elements from topack folder</td><td>")) {
			elements.append("WebProxyAsFolder "+isWebProxyUpgradeNeededStr);
			requestNote1.append(" toPack elements:(WebProxyAsFolder "+isWebProxyUpgradeNeededStr+" ");
		}else {
			elements.append("<br>").append("WebProxyAsFolder "+isWebProxyUpgradeNeededStr);
			requestNote1.append(",WebProxyAsFolder"+isWebProxyUpgradeNeededStr);
		}
		
		File ff = new File(copyTo1);
		if(ff.isDirectory() && ff.exists()) {
			HagUtil.updateTimeStamp(iframeF,"copyWebProxy_END","aaa");
			if(needToCheck)
				return "999~";
			if(isWebProxyUpgradeNeeded==false) {
				return "998~";
			}
			return "0~";
		}else {
			HagUtil.updateTimeStamp(iframeF,"copyWebProxy_END","#FF0000");
			return HagUtil.shortHtml("failed to copy "+toPackWebProxy+ " into "+copyTo1, "red", "bg");
		}
	}
	public 			int				getVersionNumber(HagRc hagRc,String fileS) {
		
		HagStringList list = new HagStringList(fileS,false,"asdasd",false);
		for(int i =0;i<list.size();i++ ) {
			String temp = list.get(i);
			if(temp.indexOf("<add key=\"version\"")>-1){
				int pos1 = temp.indexOf("value=\"");
				int pos2 = temp.lastIndexOf("\"");
				if(pos1==-1 || pos2==-1 || pos2<=pos1)
					return -1;
				String ver =temp.substring(pos1+7,pos2);
				hagRc.log.add(fileS+" line ="+temp);
				hagRc.log.add(ver+"="+ver);
				int verI = HagUtil.s2i(ver);
				return verI;
				
			}
		}
		return -1;
	}
	public 			boolean			isWebProxyUpgradeNeeded(HagRc hagRc,String webProxySource,String webProxyTarget) {
		
	
		
		String source = webProxySource+"\\Web.config";
		String target = webProxyTarget+"\\Web.config";
		int source_version_number 	= getVersionNumber(hagRc,source);
		int target_version_number 	= getVersionNumber(hagRc,target);

	
		hagRc.log.add("webProxySource="+webProxySource);
		hagRc.log.add("webProxyTarget="+webProxyTarget);
		hagRc.log.add("source_version_number="+source_version_number);
		//hagRc.log.add("source_build_number="+source_build_number);
		hagRc.log.add("target_version_number="+target_version_number);
	//	hagRc.log.add("target_build_number="+target_build_number);
		if(source_version_number<0) {
				hagRc.log.add("failed to get version_number from "+source+" file");
				hagRc.rc =1;
				return false;
		}
		if(target_version_number<0) {
			hagRc.log.add("failed to get version_number from "+target+" file");
			hagRc.rc =1;
			return false;
		}
		if(source_version_number>target_version_number)
			return true;
		if(source_version_number==target_version_number)
			return false;
		
		hagRc.rc=2;
		hagRc.log.add("source_version_number is smaller than target_version_number - call hagay");
		return false;
			

	}
	public  		String  		packWebProxyMsi(String iframeF,String topack ,StringBuilder  elements ,StringBuilder requestNote1,boolean[] flagWebProxy,String into,StringBuilder sb1,String verPackages) {
		String toPackWebProxy ="\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V0800m00\\TOPACK\\WebProxy\\WebProxy.msi";
		HagUtil.updateTimeStamp(iframeF,"copyWebProxy_START","");
		//String copyFrom1=topack+"\\ALL\\WebProxy.msi";
		String copyFrom1=toPackWebProxy;
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
	public  		String  		packMigMngJar(String iframeF,String topack ,StringBuilder  elements ,StringBuilder requestNote1,boolean[] flagWebProxy,String into,StringBuilder sb1,String verPackages) {
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
	public  		String  		packRiWebWar(String iframeF,String topack ,StringBuilder  elements ,StringBuilder requestNote1,boolean[] flagWebProxy,String into,StringBuilder sb1,String verPackages) {
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
	public  		String  		packDdcSplit(String iframeF,String split,String into,String verPackages,String ddcfolder,String ddcFromDb1,String ddcFromSql1,HagRc hagRcGetDdc,StringBuilder sb1,String schema) {
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
	public  		String  		packIom(String iframeF,String into,String verPackages,String ddcFromApp1,String ddcFromDb1,StringBuilder sb1) {
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
	public  		void 			composeReqFilePreVersion(HagStringList list,	String svnVer ) {
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
	public 	static 	String 			getTargetEnv(String val) {
		   HagSQL hagSQL = new HagSQL();
		   String stm = "SELECT Rowx.[bis_server], Rowx.[db],Rowx.[sql_server], Customer.[Customer_code], Rowx.[Owner], Rowx.[lastInst], Rowx.[Note], Rowx.[status]  "
		        +" From  "+HagParam.getConfg1DB()+".[dbo].[ri_environments_new]  Rowx "
		        +" INNER JOIN "+HagParam.getConfg1DB()+".[dbo].[RIcustomer]  Customer "
		        +" ON Rowx.Party = Customer.PARTY_ID "
		        +" Where len(Rowx.bis_server) > 3 and Rowx.[status]='A' "
		        +" order by bis_server, db";
		    	HagStringList results = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,7,"|",null,null);
		    	
		  String stm1 = "SELECT Rowx.[bis_server], Rowx.[db],Rowx.[sql_server], Customer.[Customer_code], Rowx.[Owner], Rowx.[lastInst], Rowx.[note], Rowx.[status]  "
		   		        +" From  "+HagParam.getConfg1DB()+".[dbo].[ri_environments_ext]  Rowx "
		   		        +" INNER JOIN "+HagParam.getConfg1DB()+".[dbo].[RIcustomer]  Customer "
		   		        +" ON Rowx.Party = Customer.PARTY_ID "
		   		        +" Where len(Rowx.bis_server) > 3 and Rowx.[status]='A' "
		   		        +" order by bis_server, db";
		   		    	HagStringList results1 = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,7,"|",null,null);
		   HagStringList ans1 = new HagStringList();
		   if(val.equals("O"))
	        	ans1.add("<option value=\".\">.</option>");
			for(int j=0;j<results.size();j++){
				String temp = results.get(j);
				String AppServer=HagUtil.getWord0(temp, "|", 0, true);
				String dbid=HagUtil.getWord0(temp, "|", 1, true);
				String sqlServer=HagUtil.getWord0(temp, "|", 2, true);
				String cust=HagUtil.getWord0(temp, "|", 3, true);
				String owner=HagUtil.getWord0(temp, "|",4, true);
				String lastInst=HagUtil.getWord0(temp, "|",5, true);
				String note=HagUtil.getWord0(temp, "|", 6, true);
			
				String AppServer1 = pad(AppServer,13);
				String SqlServer1 = pad(sqlServer,13);
				String dbid1 = pad(dbid,8);
				String cust1 = pad(cust,5);
				String owner1 = pad(owner,20);
				String lastInst1 = pad(lastInst,40);
				AppServer = pad(AppServer,5);
				if( note==null) {
					note=".";
				}
				String temp3 = HagUtil.replaceStr(temp, "|", "   |   ");
			//	String temp5 = AppServer1+"|"+ dbid1  ;
				//String temp4 = HagUtil.getWord0(temp,"|",0,true)+"/"+HagUtil.getWord0(temp,"|",1,true);
				String temp5 = AppServer1+"|"+ dbid1 +"|"+SqlServer1 ;
				String temp4 = HagUtil.getWord0(temp,"|",0,true)+"|"+HagUtil.getWord0(temp,"|",1,true)+"|"+HagUtil.getWord0(temp,"|",2,true);
			
				String temp1="<option    value=\""+temp4+"\">"+temp5+"</option>";
				
				ans1.add(temp1);
			}
			
			for(int k=0;k<results1.size();k++){
				String temp = results1.get(k);
				String AppServer=HagUtil.getWord0(temp, "|", 0, true);
				String dbid=HagUtil.getWord0(temp, "|", 1, true);
				String SqlServer=HagUtil.getWord0(temp, "|", 2, true);
				String lastInst=HagUtil.getWord0(temp, "|", 4, true);
				String note=HagUtil.getWord0(temp, "|", 5, true);
				String cust=HagUtil.getWord0(temp, "|", 2, true);
				String owner=HagUtil.getWord0(temp, "|",3, true);
				String AppServer1 = pad(AppServer,13);
				String SqlServer1 = pad(SqlServer,13);
				String dbid1 = pad(dbid,8);
				String cust1 = pad(cust,5);
				String lastInst1 = pad(lastInst,40);
				String owner1 = pad(owner,20);
				AppServer = pad(AppServer,5);
				if( note==null)
				note=".";
				String temp3 = HagUtil.replaceStr(temp, "|", "   |   ");
				String temp5 = AppServer1+"|"+ dbid1 +"|"+SqlServer1 ;
				String temp4 = HagUtil.getWord0(temp,"|",0,true)+"|"+HagUtil.getWord0(temp,"|",1,true)+"|"+HagUtil.getWord0(temp,"|",2,true);
				String temp1="<option    value=\""+temp4+"\">"+temp5+"</option>";
			
			
				ans1.add(temp1);
			}
			
			String ans2 = ans1.convertToString("");
			return ans2;
	}
	public 	static 	String 			getTargetEnvLong(String val) {
		   HagSQL hagSQL = new HagSQL();
		   String stm = "SELECT Rowx.[bis_server], Rowx.[db],Rowx.[sql_server], Customer.[Customer_code], Rowx.[Owner], Rowx.[lastInst], Rowx.[Note] "
		        +" From  "+HagParam.getConfg1DB()+".[dbo].[ri_environments_new]  Rowx "
		        +" INNER JOIN "+HagParam.getConfg1DB()+".[dbo].[RIcustomer]  Customer "
		        +" ON Rowx.Party = Customer.PARTY_ID "
		        +" Where len(Rowx.bis_server) > 3 "
		        +" order by bis_server, db";
		    	HagStringList results = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,7,"|",null,null);
		    	
		  String stm1 = "SELECT Rowx.[bis_server], Rowx.[db],Rowx.[sql_server], Customer.[Customer_code], Rowx.[Owner], Rowx.[lastInst], Rowx.[note] "
		   		        +" From  "+HagParam.getConfg1DB()+".[dbo].[ri_environments_ext]  Rowx "
		   		        +" INNER JOIN "+HagParam.getConfg1DB()+".[dbo].[RIcustomer]  Customer "
		   		        +" ON Rowx.Party = Customer.PARTY_ID "
		   		        +" Where len(Rowx.bis_server) > 3 "
		   		        +" order by bis_server, db";
		   		    	HagStringList results1 = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,7,"|",null,null);
		   HagStringList ans1 = new HagStringList();
		   if(val.equals("O"))
	        	ans1.add("<option value=\".\">.</option>");
			for(int j=0;j<results.size();j++){
				String temp = results.get(j);
				String AppServer=HagUtil.getWord0(temp, "|", 0, true);
				String dbid=HagUtil.getWord0(temp, "|", 1, true);
				String sqlServer=HagUtil.getWord0(temp, "|", 2, true);
				String cust=HagUtil.getWord0(temp, "|", 3, true);
				String owner=HagUtil.getWord0(temp, "|",4, true);
				String lastInst=HagUtil.getWord0(temp, "|",5, true);
				String note=HagUtil.getWord0(temp, "|", 6, true);
			
				String AppServer1 = pad(AppServer,13);
				String SqlServer1 = pad(sqlServer,13);
				String dbid1 = pad(dbid,8);
				String cust1 = pad(cust,5);
				String owner1 = pad(owner,20);
				String lastInst1 = pad(lastInst,40);
				AppServer = pad(AppServer,5);
				if( note==null) {
					note=".";
				}
				String temp3 = HagUtil.replaceStr(temp, "|", "   |   ");
				String temp5 = AppServer1+"|"+ dbid1   +"|"+  SqlServer1 +"|"+  cust1 +"|"+  owner1  +"|"+  lastInst1  +"|"+note;
				String temp4 = HagUtil.getWord0(temp,"|",0,true)+"/"+HagUtil.getWord0(temp,"|",1,true);
				String temp1="<option    value=\""+temp4+"\">"+temp5+"</option>";
				
				ans1.add(temp1);
			}
			
			for(int k=0;k<results1.size();k++){
				String temp = results1.get(k);
				String AppServer=HagUtil.getWord0(temp, "|", 0, true);
				String dbid=HagUtil.getWord0(temp, "|", 1, true);
				String lastInst=HagUtil.getWord0(temp, "|", 4, true);
				String note=HagUtil.getWord0(temp, "|", 5, true);
				String cust=HagUtil.getWord0(temp, "|", 2, true);
				String owner=HagUtil.getWord0(temp, "|",3, true);
				String AppServer1 = pad(AppServer,13);
				String dbid1 = pad(dbid,8);
				String cust1 = pad(cust,5);
				String lastInst1 = pad(lastInst,40);
				String owner1 = pad(owner,20);
				AppServer = pad(AppServer,5);
				if( note==null)
				note=".";
				String temp3 = HagUtil.replaceStr(temp, "|", "   |   ");
				String temp5 = AppServer1+"|"+ dbid1   +"|"+  cust1 +"|"+  owner1  +"|"+  lastInst1  +"|"+note;
				String temp4 = HagUtil.getWord0(temp,"|",0,true)+"/"+HagUtil.getWord0(temp,"|",1,true);
				String temp1="<option    value=\""+temp4+"\">"+temp5+"</option>";
			
			
				ans1.add(temp1);
			}
			
			String ans2 = ans1.convertToString("");
			return ans2;
	}
	public 	static  String 			pad(String org,int len) {
		if(org.length()>=len)
			return org;
		String tgt=""+org;
		for(int i=org.length();i<len;i++) {
			tgt=tgt+"&nbsp;";
		}
		return tgt;
		
	}
	public 	static  String[][] 		getMinorVersionsList(){
		HagStringList list = new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\minorVersions.txt",true,"*",false);
		String [][] ans  = new String [list.size()][9];
		for(int i = 0;i<list.size();i++) {
			String temp=list.get(i);
			ans[i][0] = HagUtil.getWord0(temp,"|",0,true);
			ans[i][1]  = HagUtil.getWord0(temp,"|",1,true);
			ans[i][2]  = HagUtil.getWord0(temp,"|",2,true);
			ans[i][3]  = HagUtil.getWord0(temp,"|",3,true);
			ans[i][4]  = HagUtil.getWord0(temp,"|",4,true);
			ans[i][5]  = HagUtil.getWord0(temp,"|",5,true);
			ans[i][6]  = HagUtil.getWord0(temp,"|",6,true);
			ans[i][7]  = HagUtil.getWord0(temp,"|",7,true);
			ans[i][8]  = HagUtil.getWord0(temp,"|",8,true);
		}
		return ans;
		
	}
	
	private static 	String			getSelectField(String name,String[] vals) {
		StringBuilder ansSB =new StringBuilder("<select name=\""+name+"\">");
		for(int i = 0;i<vals.length;i++)
			ansSB.append("<option class=\"c30\" value=\"").append(vals[i]).append("\">").append(vals[i]).append("</option>");
		ansSB.append("</select>");	
		String ans  = ansSB.toString();
		return ans;
	}
	private static 	String			getRiEnvSelectField(String name,String vals) {
		StringBuilder ansSB =new StringBuilder("<select name=\""+name+"\">");
		ansSB.append(vals);
		ansSB.append("</select>");	
		String ans  = ansSB.toString();
		return ans;
	}
	private static 	String			getRiEnvSelectFieldLong(String name,String vals) {
		String envs ="<select  STYLE=\"font-family : monospace; font-size : 10pt\" id=\""+name+"\"  name=\""+name+"\"  >"+vals+"</select>";
		return envs;
	}
	private static 	String			getTextField(String name,String len,String val,String disabled) {
		String ans ="<input type=\"text\" id=\""+name+"\"    name=\""+name+"\"    size=\""+len+"\"  value = \""+val+"\" "+disabled+">";
		return ans;
	}
	private static 	String			getHiddenField(String name,String len,String val) {
		String ans ="<input type=\"hidden\" id=\""+name+"\"    name=\""+name+"\"    size=\""+len+"\"  value = \""+val+"\" >";
		return ans;
	}
	
	private static 	String 			getVersionsFromFile() {

		
		HagStringList ans= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\riVersions.txt",true,"*",false);
		HagStringList ans1 = new HagStringList();
		//if(val.equals("O"))
		ans1.add("<option value=\".\">.</option>");
		for(int i = 0;i<ans.size();i++) {
			String temp=ans.get(i);
			String temp1="<option value=\""+temp+"\">"+temp+"</option>";
			ans1.add(temp1);
		}
		String ans2 = ans1.convertToString("");
		return ans2;
	
	}
	private static 	String 			getVersionsMulti(String where) {
		HagStringList trunkList1= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\releaseVersions.txt",true,"*",false);
		HagStringList minorList1= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\minorVersions.txt",true,"*",false);
		HagStringList trunkList=new HagStringList();
		HagStringList minorList=new HagStringList();
		for(int i = 0;i<trunkList1.size();i++) {
			String temp=trunkList1.get(i);
			String w1 = HagUtil.getWord0(temp, "|", 1, true);
			trunkList.add(w1+"(trunk)");
		}
		for(int i = 0;i<minorList1.size();i++) {
			String temp=minorList1.get(i);
			String w0 = HagUtil.getWord0(temp, "|", 0, true);
			minorList.add(w0+"(minor)");
		}
		HagSQL hagSQL = new HagSQL();
		String stm = "SELECT [Package_Name], [Package_Platform], [Package_Time] \r\n" + 
				"		           From "+HagParam.getConfg1DB()+".[dbo].[RI_Packages]\r\n" + 
				where+
				"		           Order by Package_Name";

		HagStringList ans = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm);

		//<option value="WIN">WIN</option><option value="I5OS">I5OS</option>
		HagStringList ans1 = new HagStringList();
		//if(val.equals("O"))
		ans1.add("<option value=\".\">.</option>");
		for(int i = 0;i<trunkList.size();i++) {
			String temp=trunkList.get(i);
			String temp1="<option value=\""+temp+"\">"+temp+"</option>";
			ans1.add(temp1);
		}
		for(int i = 0;i<minorList.size();i++) {
			String temp=minorList.get(i);
			String temp1="<option value=\""+temp+"\">"+temp+"</option>";
			ans1.add(temp1);
		}
		for(int i = 0;i<ans.size();i++) {
			String temp=ans.get(i);
			String temp1="<option value=\""+temp+"\">"+temp+"</option>";
			ans1.add(temp1);
		}
		String ans2 = ans1.convertToString("");
		return ans2;
	}
	public  static  String 			getCustomers(String val) {
        HagSQL hagSQL = new HagSQL();
        String stm = " SELECT distinct CUSTOMER_CODE from RIcustomer  where CUSTOMER_CODE <> '???' order by CUSTOMER_CODE";

        HagStringList ans = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm);
        //<option value="WIN">WIN</option><option value="I5OS">I5OS</option>
        HagStringList ans1 = new HagStringList();
        if(val.equals("O"))
        	ans1.add("<option value=\".\">.</option>");
        for(int i = 0;i<ans.size();i++) {
        	String temp=ans.get(i);
        	String temp1="<option value=\""+temp+"\">"+temp+"</option>";
        	ans1.add(temp1);
        }
        String ans2 = ans1.convertToString("");
        return ans2;
	}
	private  		boolean 		checkEnv(String bis,String dbid){
		bis=bis.toUpperCase();
		dbid=dbid.toUpperCase();
		HagSQL hagSQL = new HagSQL();
		String stm1 = "SELECT [sql_server] From  "+HagParam.getConfg1DB()+".[dbo].[ri_environments_new] Where bis_server='"+bis+"' AND batchName ='"+dbid+"' AND status='A' " ;
		HagStringList results1 = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,1,"|",null,null);
		String stm2 = "SELECT [sql_server] From  "+HagParam.getConfg1DB()+".[dbo].[ri_environments_ext] Where bis_server='"+bis+"' AND batchName ='"+dbid+"' AND status='A' " ;   	
	  	HagStringList results2 = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm2,1,"|",null,null);
		if(results1.size()==0 && results2.size()==0)
			return false;
		return true;
	}
	private  		boolean 		checkEnv2inst(String env2inst){
		if(env2inst.equals("."))
			return true;
		HagStringList list = new HagStringList(env2inst," ",true);
		for(int i = 0;i<list.size();i++) {
			String temp=list.get(i).trim();
			String bis=HagUtil.getWord0(temp, "/",0, true);
			String db=HagUtil.getWord0(temp, "/",1, true);
			if(!checkEnv(bis,db))
				return false;
		}
		return true;
	}
	private			void			checkAriaVsEnv(HagRc hagRc,String ariaPackage,String envSql,String envDb) {
		String aa="Aaa";
		String cmInstallerVals =getEnvInstDetail(hagRc ,envSql.toUpperCase(),envDb.toUpperCase());
		if(hagRc.rc!=0)
			return;
		
		
		////ariaPackage
		//08.00.01.03.12
		//name#version(from_FTP)
		//V9R01M02
		String loc = "trunk";
		if(ariaPackage.endsWith("(minor)"))
			loc = "minor";
		else if(ariaPackage.endsWith("(from_FTP)"))
			loc = "ftp";
		else if(ariaPackage.endsWith("(from_Jenkins)"))
			loc = "jenkinsFtp";
		String packageVals =getPackageDetail(hagRc ,loc,ariaPackage);
		if(hagRc.rc!=0)
			return;
		
		if(!cmInstallerVals.equals(packageVals)) {
			hagRc.rc=1;
			hagRc.log.add("env_status and ariaPackage must be the same, env_status="+cmInstallerVals+" ariaPackage="+packageVals);
		}
		
	}
	private			String			getPackageDetail(HagRc hagRc,String loc,String ariaPackage) {
		if(loc.equals("minor")){
			String ver = ariaPackage.substring(0,ariaPackage.indexOf("("));
			String path1 = "\\\\ri-archive\\Saptech-Archive\\RI\\preVersionBases\\"+ver+"\\config.txt";
			String cdVersion 	= HagUtil.getPropertyVal(path1,"CDversion");
			String cdDate 		= HagUtil.getPropertyVal(path1,"CDdate");
			String cdTime 		= HagUtil.getPropertyVal(path1,"CDtime");
			return cdVersion.trim()+"|"+cdDate.trim()+"|"+cdTime.trim();
		
		}else if(loc.equals("trunk")){
			String ver = ariaPackage.substring(0,ariaPackage.indexOf("("));
			String path1 = "\\\\ri-archive\\Saptech-Archive\\RI\\HAT_pack\\hat_"+ver+"m01\\bin\\packages\\"+ver+"m01\\skel\\config.txt";
			String cdVersion 	= HagUtil.getPropertyVal(path1,"CDversion");
			String cdDate 		= HagUtil.getPropertyVal(path1,"CDdate");
			String cdTime 		= HagUtil.getPropertyVal(path1,"CDtime");
			return cdVersion.trim()+"|"+cdDate.trim()+"|"+cdTime.trim();
		}else if(loc.equals("ftp")){
			String packName = ariaPackage.substring(0,ariaPackage.indexOf("#"));
			String ver1 = ariaPackage.substring(ariaPackage.indexOf("#")+1,ariaPackage.indexOf("("));
			String folder = ""+ver1.charAt(0)+ver1.charAt(1)+ver1.charAt(3)+ver1.charAt(4);
			String path1 ="m:\\RI_CORE\\"+folder+"\\"+packName+"\\config.txt";
		//	String path1 ="ftp://ri_core:j7nmA16@i-ftp.sapiens.com/"+folder+"/"+packName+"/config.txt";
			//File fff = new File(path1);
			//if(fff.isFile() && fff.exists()) {
			//	return HagUtil.shortLineHtml(path1+" exists","red");
			//}else {
				//return HagUtil.shortLineHtml(path1+" not exists","red");
			//}
			//String path1 ="\\\\i-ftp.sapiens.com\\config\\RI_CORE\\"+folder+"\\"+packName+"\\config.txt";
			String cdVersion 	= HagUtil.getPropertyVal(path1,"CDversion");
			String cdDate 		= HagUtil.getPropertyVal(path1,"CDdate");
			String cdTime 		= HagUtil.getPropertyVal(path1,"CDtime");
			if(cdVersion==null ||cdDate==null ||cdTime==null  ) {
				return HagUtil.shortLineHtml("failed to read the following file<br>"+path1,"red");
			}
			return cdVersion.trim()+"|"+cdDate.trim()+"|"+cdTime.trim();
		}else if(loc.equals("jenkinsFtp")){
			String packName = ariaPackage.substring(0,ariaPackage.indexOf("#"));
		
			String folder = getJenkinsCust(packName);
		
			String path1 ="m:\\customers_data\\"+folder+"\\Releases\\"+packName+"\\config.txt";
		//	String path1 ="ftp://ri_core:j7nmA16@i-ftp.sapiens.com/"+folder+"/"+packName+"/config.txt";
			//File fff = new File(path1);
			//if(fff.isFile() && fff.exists()) {
			//	return HagUtil.shortLineHtml(path1+" exists","red");
			//}else {
				//return HagUtil.shortLineHtml(path1+" not exists","red");
			//}
			//String path1 ="\\\\i-ftp.sapiens.com\\config\\RI_CORE\\"+folder+"\\"+packName+"\\config.txt";
			String cdVersion 	= HagUtil.getPropertyVal(path1,"CDversion");
			String cdDate 		= HagUtil.getPropertyVal(path1,"CDdate");
			String cdTime 		= HagUtil.getPropertyVal(path1,"CDtime");
			if(cdVersion==null ||cdDate==null ||cdTime==null  ) {
				return HagUtil.shortLineHtml("failed to read the following file<br>"+path1,"red");
			}
			return cdVersion.trim()+"|"+cdDate.trim()+"|"+cdTime.trim();
		}
		hagRc.rc=1;
		hagRc.log.add("ARIA package should be trunk,minor or ftp");
		return "";
	}
	private			String			getJenkinsCust(String packName) {
		int pos = packName.length();
		int pos1 = packName.indexOf("_");
		if(pos1>0)
			pos = pos1;
		String minor = packName.substring(0,pos);
		HagStringList minors= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\minorVersions.txt",true,"*",false);
		for(int i = 0 ; i< minors.size();i++) {
			String line = minors.get(i);
			String w0= HagUtil.getWord0(line, "|", 0, true);
			String w9= HagUtil.getWord0(line, "|", 9, true);
			if(minor.toUpperCase().equals(w0.toUpperCase()))
				return w9;
		}
		return null;
	}
	private			String			getEnvInstDetail(HagRc hagRc,String sqlServer,String db) {
		HagSQL hagSQL=new HagSQL();
		String stm = "select  TOP  1 CDversion,CDdate,CDtime,rc from RI.cmInstaller order By instDate DESC,instTime DESC";
		HagStringList list = hagSQL.select("SQL",sqlServer,"cfg","cfg09c",db,stm,4,"|",null,null);
		
		String line = list.get(0);
		String cdVer = HagUtil.getWord0(line, "|", 0, true);
		String cdDate = HagUtil.getWord0(line, "|", 1, true);
		String cdTime = HagUtil.getWord0(line, "|", 2, true);
			String rc = HagUtil.getWord0(line, "|", 3, true);
	
		
		if(!rc.equals("OK")) {
			hagRc.rc=1;
			hagRc.log.add("last installation rc != 0");
			return "";
		}
		return cdVer.trim()+"|"+cdDate.trim()+"|"+cdTime.trim();
	}

	public  String runLoadTaskProcesses(		HagRc hagRc1,String minorVersion,String bisServerS,String batchNameS,String bisServerT,String batchNameT,String taskNumber,String sqlServerS,String sqlServerT,String sentBy) {
		HagUtil.appendToDebugFile( "in runLoadTaskProcesses" ) ;
		//if(1==1)
		//	return "future";
		String rsFolder="\\\\ri-archive\\Saptech-Archive\\RI\\minorVersions\\loadTasks";
		String dateTime = HagUtil.getDateTime("yyyyMMdd_HHmmss");
		//dbjrndb -d RI -bn R0 -tn desg -tsn 63555 -o pb
		//-out \\HYUNDAI.SAPIENS.COM\HYUN05ri\rs\v08_01m01\win\int\63555_extract.tran
		//-lst \\HYUNDAI.SAPIENS.COM\HYUN05ri\rs\v08_01m01\win\int\63555_extract_lst.txt  
		//String 	hrefLogsFolder 	= "All logs folder</td><td><a href=\""+rsFolder+"\">"+rsFolder+"</a>";
		//String hrefLstRestore 	= "no need to restore target environment.";
		//String hrefLstExtract 	= "NA";
		//String hrefBkDbLog 		= "NA";
		//String hrefLstLoad 		= "NA";
		String prefix 			= rsFolder+"\\"+dateTime+"_"+ batchNameT+"_"+taskNumber;
		String tranS 			= prefix+"_extract.tran";
		String bkF	 			= prefix+".bk";
		String hrefLogsFolder 	="<tr bgcolor=\"#cccccc\"><td>step 0:</td><td>All logs folder			</td><td>"+"<a href=\""+rsFolder+"\">"+rsFolder+"</a>"+"</td></tr >";
		String hrefCheckLockLog ="<tr bgcolor=\"#cccccc\"><td>step 1:</td><td>check locks before		</td><td>"+"NA"+"</td></tr >";
		String hrefBkDbLog 		="<tr bgcolor=\"#cccccc\"><td>step 2:</td><td>backup target database	</td><td>"+"NA"+"</td></tr >";
		String hrefLstExtract 	="<tr bgcolor=\"#cccccc\"><td>step 3:</td><td>extract step log			</td><td>"+"NA"+"</td></tr >";
		String hrefLockLog 		="<tr bgcolor=\"#cccccc\"><td>step 4:</td><td>lock target env			</td><td>"+"NA"+"</td></tr >";
		String hrefLstLoad 		="<tr bgcolor=\"#cccccc\"><td>step 5:</td><td>load step log				</td><td>"+"NA"+"</td></tr >";
		String hrefLstStop  	="<tr bgcolor=\"#cccccc\"><td>step 6:</td><td>stop tomcat and listener	</td><td>"+"no need to stop target environment."+"</td></tr >";
		String hrefLstRestore 	="<tr bgcolor=\"#cccccc\"><td>step 7:</td><td>restore target database	</td><td>"+"no need to restore target environment."+"</td></tr >";
		String hrefLstStart 	="<tr bgcolor=\"#cccccc\"><td>step 8:</td><td>start target env	     	</td><td>"+"no need to start target environment."+"</td></tr >";
		String hrefReleaseLog 	="<tr bgcolor=\"#cccccc\"><td>step 9:</td><td>release target env		</td><td>"+"NA"+"</td></tr >";
		String src=bisServerS+"/"+batchNameS;
		String tgt=bisServerT+"/"+batchNameT;
		String tsk=taskNumber;
		//checkLock
		String debug1="\\\\ri-archive\\Saptech-Archive\\RI\\debug\\minor_tasks\\";
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","checkTargetEnvLock_START","");
		HagUtil.appendToDebugFile( "before checkLock" ) ;
		if(HagUtil.isCmLock( bisServerT, batchNameT)) {
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","checkTargetEnvLock_END","#FF0000");
			HagUtil.appendToDebugFile( " checkLock=lock" ) ;
			HagUtil.writeStringToFile(prefix+"_checkLock.txt", bisServerT+"/"+ batchNameT+" environment status = locked");
			hrefCheckLockLog 	= "<a href=\""+prefix+"_checkLock.txt"+"\">"+prefix+"_checkLock.txt</a>";
			hrefCheckLockLog 	="<tr bgcolor=\"#ff6600\"><td>step 1:</td><td>checks locks before</td><td>"+hrefCheckLockLog+"</td></tr >";
			hagRc1.rc=1;
			return  printLoadTaskLogs( sentBy,src,tgt,tsk,rsFolder, hrefLogsFolder, prefix, hrefLstExtract, hrefBkDbLog,hrefLstStop,hrefLstRestore,hrefLstStart, hrefLstLoad,hrefCheckLockLog,hrefLockLog,hrefReleaseLog,
					"<font color=#C11B17>Error: "+sqlServerT+"/"+batchNameT+" environment status = locked (call devops team)</font>") ;
		}else {
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","checkTargetEnvLock_END","#00FF00");
			HagUtil.appendToDebugFile( " checkLock=free" ) ;
			HagUtil.writeStringToFile(prefix+"_checkLock.txt", bisServerT+"/"+ batchNameT+" environment status = not locked");
			hrefCheckLockLog 	= "<a href=\""+prefix+"_checkLock.txt"+"\">"+prefix+"_checkLock.txt</a>";
			hrefCheckLockLog 	="<tr bgcolor=\"#66ff66\"><td>step 1:</td><td>check target env lock</td><td>"+hrefCheckLockLog+"</td></tr >";
			
		}
	
		
		//bkDb
		
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","backupTargetDatabase_START","");
		HagUtil.appendToDebugFile( "before bkup" ) ;
		ey_Gsetlibl ey_gsetlibl = new ey_Gsetlibl();
		String rc12 = ey_gsetlibl.SimpleBK(sqlServerT, batchNameT, bkF, "WITH COPY_ONLY");
		HagUtil.writeStringToFile(prefix+"_bkDb.txt", rc12);
		hrefBkDbLog 	= "<a href=\""+prefix+"_bkDb.txt"+"\">"+prefix+"_bkDb.txt</a>";
		hrefBkDbLog 	="<tr bgcolor=\"#66ff66\"><td>step 2:</td><td>backup target database</td><td>"+hrefBkDbLog+"</td></tr >";
		if (!rc12.startsWith("0~")){
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","backupTargetDatabase_END","#FF0000");
			HagUtil.appendToDebugFile( " backup failed" ) ;
			hrefBkDbLog 	="<tr bgcolor=\"#ff6600\"><td>step 2:</td><td>backup target database</td><td>"+hrefBkDbLog+"</td></tr >";
			hagRc1.rc=1;
			return  printLoadTaskLogs(sentBy, src,tgt,tsk,rsFolder, hrefLogsFolder, prefix, hrefLstExtract, hrefBkDbLog,hrefLstStop,hrefLstRestore,hrefLstStart, hrefLstLoad,hrefCheckLockLog,hrefLockLog,hrefReleaseLog,
					"<font color=#C11B17>Error: failed to backup the "+sqlServerT+"/"+batchNameT+" database(no changes done)</font>") ;
		}
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","backupTargetDatabase_END","#00FF00");
		
		//HagUtil.writeStringToFile(prefix+"_bkDb.txt", rc12);
		//hrefBkDbLog 	= "<a href=\""+prefix+"_bkDb.txt"+"\">"+prefix+"_bkDb.txt</a>";
		//if(!rc12.startsWith("0~")) {
		//	return  printLoadTaskLogs( rsFolder, hrefLogsFolder, prefix, hrefLstExtract, hrefBkDbLog, hrefLstLoad) ;
		//}
		
		//extract
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","extractTaskFromSource_START","");
		HagUtil.appendToDebugFile( "before extract" ) ;
		String cmd11 = "dbjrndb -d RI -bn "+batchNameS+" -o pb -tsn "+taskNumber+" -out "+prefix+"_extract.tran -lst "+prefix+"_extract.txt";
		String rc11 = HagUtil.runCmdRemote(bisServerS,cmd11+"\n","Y");
		hrefLstExtract 	= "<a href=\""+prefix+"_extract.txt"+"\">"+prefix+"_extract.txt</a>";
		if(rc11.startsWith("0~")) {
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","extractTaskFromSource_END","#00FF00");
			HagUtil.appendToDebugFile( "extract OK" ) ;
			hrefLstExtract 	="<tr bgcolor=\"#66ff66\"><td>step 3:</td><td>extract step log</td><td>"+hrefLstExtract+"</td></tr >";
		}else {
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","extractTaskFromSource_END","#FF0000");
			HagUtil.appendToDebugFile( "extract failed" ) ;
			hagRc1.rc=1;
			hrefLstExtract 	="<tr bgcolor=\"#ff6600\"><td>step 3:</td><td>extract step log</td><td>"+hrefLstExtract+"</td></tr >";
			return  printLoadTaskLogs( sentBy,src,tgt,tsk,rsFolder, hrefLogsFolder, prefix, hrefLstExtract, hrefBkDbLog,hrefLstStop,hrefLstRestore,hrefLstStart, hrefLstLoad,hrefCheckLockLog,hrefLockLog,hrefReleaseLog,
					"<font color=#C11B17>Error: failed to extract task number "+taskNumber+" from "+sqlServerS+"/"+batchNameS+" database (no changes done)</font>") ; 
		}
		
		
		//Lock
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","setLocksBefore_START","");
		HagUtil.appendToDebugFile( "before lock" ) ;
		String ans = HagUtil.setCmLock(bisServerT,batchNameT,"taskLock") ;
		if(!ans.startsWith("0~")) {
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","setLocksBefore_END","#FF0000");
			HagUtil.appendToDebugFile( " lock ok" ) ;
			HagUtil.writeStringToFile(prefix+"_setLock.txt", bisServerT+"/"+ batchNameT+"-"+ans);
			hrefLockLog 	= "<a href=\""+prefix+"_setLock.txt"+"\">"+prefix+"_setLock.txt</a>";
			hrefLockLog 	="<tr bgcolor=\"#ff6600\"><td>step 4:</td><td>set locks before</td><td>"+hrefLockLog+"</td></tr >";
			hagRc1.rc=1;
			return  printLoadTaskLogs(sentBy,src,tgt,tsk, rsFolder, hrefLogsFolder, prefix, hrefLstExtract, hrefBkDbLog,hrefLstStop,hrefLstRestore,hrefLstStart, hrefLstLoad,hrefCheckLockLog,hrefLockLog,hrefReleaseLog,
					"<font color=#C11B17>Error: "+sqlServerT+"/"+batchNameT+"-" +ans) ;
		}else {
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","setLocksBefore_END","#00FF00");
			HagUtil.appendToDebugFile( " lock failed" ) ;
			HagUtil.writeStringToFile(prefix+"_setLock.txt", bisServerT+"/"+ batchNameT+"-" +ans) ;
			hrefLockLog 	= "<a href=\""+prefix+"_setLock.txt"+"\">"+prefix+"_setLock.txt</a>";
			hrefLockLog 	="<tr bgcolor=\"#66ff66\"><td>step 4:</td><td>set locks before</td><td>"+hrefLockLog+"</td></tr >";
			
		}
		//load
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","loadTaskToTarget_START","");
		HagUtil.appendToDebugFile( "before load" ) ;
		String cmd13 = "dbblp -d RI -bn "+batchNameT+" -i "+tranS+" -us I8INT -u q2j -m 4000 -n 2000 -o "+prefix+"_load.txt";
		String rc13 = HagUtil.runCmdRemote(bisServerT,cmd13+"\n","Y");
		HagRc hagRc=new HagRc();
		checkLoad(hagRc,prefix+"_load.txt");
		hrefLstLoad 	= "<a href=\""+prefix+"_load.txt"+"\">"+prefix+"_load.txt</a>";
		if(rc13.startsWith("0~") &&  hagRc.rc==0) {
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","loadTaskToTarget_END","#00FF00");
			HagUtil.appendToDebugFile( " load ok" ) ;
			hrefLstLoad 	="<tr bgcolor=\"#66ff66\"><td>step 5:</td><td>load step log</td><td>"+hrefLstLoad+"</td></tr >";
		}else {
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","loadTaskToTarget_END","#FF0000");
			HagUtil.appendToDebugFile( " load failed" ) ;
			hagRc1.rc=1;
			hrefLstLoad 	="<tr bgcolor=\"#ff6600\"><td>step 5:</td><td>load step log</td><td>"+hrefLstLoad+"</td></tr >";
			
			
			//stop tomcat and listener
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","stopTargetEnv_START","");//hahahahaha
			HagUtil.appendToDebugFile( "before stop tomcat and listener" ) ;
			String rcStr1a = stopTomcatAndListener(bisServerT,batchNameT,true);
			HagUtil.writeStringToFile(prefix+"_stopEnv.txt", rcStr1a);
			hrefLstStop 		= "<a href=\""+prefix+"_stopEnv.txt"+"\">"+prefix+"_stopEnv.txt</a>";
			if (rcStr1a.indexOf("1~") > -1)
				HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","stopTargetEnv_END","#FF0000");
			else
				HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","stopTargetEnv_END","#00ff00");
			HagUtil.appendToDebugFile( " stopTargetEnv ok" ) ;		
			
			
			
			//restore
			String datLoc = "D:\\Database\\";
			String logLoc = "D:\\Database\\";
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","restoreTargetEnv_START","");
			HagUtil.appendToDebugFile( "before restore" ) ;
			
			
			String rcStr1 = ey_gsetlibl.Create_Restore_with_dbfpath(sqlServerT, batchNameT, bkF, datLoc, logLoc);
			
			HagUtil.writeStringToFile(prefix+"_rstDb.txt", rcStr1);
			hrefLstRestore 		= "<a href=\""+prefix+"_rstDb.txt"+"\">"+prefix+"_rstDb.txt</a>";
			if (rcStr1.indexOf("1~") > -1){
				HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","restoreTargetEnv_END","#FF0000");
				HagUtil.appendToDebugFile( " restore failed" ) ;
				hrefLstRestore 	="<tr bgcolor=\"#ff6600\"><td>step 7:</td><td>restore step log</td><td>"+hrefLstRestore+"</td></tr >";
				return  printLoadTaskLogs(sentBy,src,tgt,tsk,rsFolder, hrefLogsFolder, prefix, hrefLstExtract, hrefBkDbLog,hrefLstStop,hrefLstRestore,hrefLstStart, hrefLstLoad,hrefCheckLockLog,hrefLockLog,hrefReleaseLog,
						"<font color=#C11B17>Error:Failed to restore "+sqlServerT+"/"+batchNameT+" database after the load step failed (you must call devOps team)") ;
				
			}
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","restoreTargetEnv_END","#00ff00");
			HagUtil.appendToDebugFile( " restore ok" ) ;
			hrefLstRestore 	="<tr bgcolor=\"#66ff66\"><td>step 7:</td><td>restore step log</td><td>"+hrefLstRestore+"</td></tr >";
			
			//start tomcat and listener
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","startTargetEnv_START","");//hahahahaha
			HagUtil.appendToDebugFile( "before start tomcat and listener" ) ;
			String rcStr1b = startTomcatAndListener(bisServerT,batchNameT);
			HagUtil.writeStringToFile(prefix+"_startEnv.txt", rcStr1a);
			hrefLstStart 		= "<a href=\""+prefix+"_startEnv.txt"+"\">"+prefix+"_startEnv.txt</a>";
			if (rcStr1b.indexOf("1~") > -1)
				HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","startTargetEnv_END","#FF0000");
			else
				HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","startTargetEnv_END","#00ff00");
			HagUtil.appendToDebugFile( " startTargetEnv ok" ) ;		
			
			
			
			//release lock after good restore
			HagUtil.appendToDebugFile( "before release" ) ;
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","freeTargetEnvLock_START","");
			String ans1 = HagUtil.setCmLock(bisServerT,batchNameT,"free") ;
			if(!ans1.startsWith("0~")) {
				HagUtil.appendToDebugFile( "release failed" ) ;
				HagUtil.writeStringToFile(prefix+"_freeLock.txt", bisServerT+"/"+ batchNameT+"-"+ans1);
				hrefReleaseLog 	= "<a href=\""+prefix+"_freeLock.txt"+"\">"+prefix+"_freeLock.txt</a>";
				hrefReleaseLog 	="<tr bgcolor=\"#ff6600\"><td>step 9:</td><td>free target env lock</td><td>"+hrefReleaseLog+"</td></tr >";
				hagRc1.rc=1;
				HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","freeTargetEnvLock_END","#FF0000");
				return  printLoadTaskLogs( sentBy,src,tgt,tsk,rsFolder, hrefLogsFolder, prefix, hrefLstExtract, hrefBkDbLog,hrefLstStop,hrefLstRestore,hrefLstStart, hrefLstLoad,hrefCheckLockLog,hrefLockLog,hrefReleaseLog,
						"<font color=#C11B17>Error: "+sqlServerT+"/"+batchNameT+"-" +ans) ;
			}else {
				HagUtil.appendToDebugFile( "release ok" ) ;
				HagUtil.writeStringToFile(prefix+"_freeLock.txt", bisServerT+"/"+ batchNameT+"-" +ans1) ;
				hrefReleaseLog 	= "<a href=\""+prefix+"_freeLock.txt"+"\">"+prefix+"_freeLock.txt</a>";
				hrefReleaseLog 	="<tr bgcolor=\"#66ff66\"><td>step 9:</td><td>free target env lock</td><td>"+hrefReleaseLog+"</td></tr >";
				HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","freeTargetEnvLock_END","#00ff00");
	}
			//
			HagUtil.appendToDebugFile( "done" ) ;
			return  printLoadTaskLogs(sentBy, src,tgt,tsk,rsFolder, hrefLogsFolder, prefix, hrefLstExtract, hrefBkDbLog,hrefLstStop, hrefLstRestore,hrefLstStart,hrefLstLoad,hrefCheckLockLog,hrefLockLog,hrefReleaseLog,
					"<font color=#C11B17>Error:Failed to load task into "+sqlServerT+"/"+batchNameT+"(step 5)</font><br><font color=#254117>"+sqlServerT+"/"+batchNameT+" database successfully  restored(step 6)</font>") ;
			
		}
	
		//release lock after good load
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","freeTargetEnvLock_START","");
		String ans1 = HagUtil.setCmLock(bisServerT,batchNameT,"free") ;
		if(!ans1.startsWith("0~")) {
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","freeTargetEnvLock_END","#FF0000");
			HagUtil.writeStringToFile(prefix+"_freeLock.txt", bisServerT+"/"+ batchNameT+"-"+ans1);
			hrefReleaseLog 	= "<a href=\""+prefix+"_freeLock.txt"+"\">"+prefix+"_freeLock.txt</a>";
			hrefReleaseLog 	="<tr bgcolor=\"#ff6600\"><td>step 9:</td><td>free target env lock</td><td>"+hrefReleaseLog+"</td></tr >";
			hagRc1.rc=1;
			return  printLoadTaskLogs(sentBy,src,tgt,tsk, rsFolder, hrefLogsFolder, prefix, hrefLstExtract, hrefBkDbLog,hrefLstStop,hrefLstRestore,hrefLstStart, hrefLstLoad,hrefCheckLockLog,hrefLockLog,hrefReleaseLog,
					"<font color=#C11B17>Error: "+sqlServerT+"/"+batchNameT+"-" +ans) ;
		}else {
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+minorVersion+".html","freeTargetEnvLock_END","#00ff00");
			HagUtil.writeStringToFile(prefix+"_freeLock.txt", bisServerT+"/"+ batchNameT+"-" +ans1) ;
			hrefReleaseLog 	= "<a href=\""+prefix+"_freeLock.txt"+"\">"+prefix+"_freeLock.txt</a>";
			hrefReleaseLog 	="<tr bgcolor=\"#66ff66\"><td>step 9:</td><td>free target env lock</td><td>"+hrefReleaseLog+"</td></tr >";
			
		}
		return  printLoadTaskLogs(sentBy,src,tgt,tsk, rsFolder, hrefLogsFolder, prefix, hrefLstExtract, hrefBkDbLog,hrefLstStop, hrefLstRestore,hrefLstStart,hrefLstLoad,hrefCheckLockLog,hrefLockLog,hrefReleaseLog,
				"<font color=#254117>Done: please check the logs by clicking the links</font>") ;                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
		
	
			
	}
	public  String stopTomcatAndListener(String bisServer,String batchName,boolean ignoreLocks) {
		String stopT1=HagUtil.stopTomcatClean(bisServer,  batchName,ignoreLocks);
		String stopL1=HagUtil.stopEmergeListenerClean( bisServer, batchName);
		if(stopT1.startsWith("0~") && stopL1.startsWith("0~"))
			return "0~from stop tomcat and listener";
		else
			return "1~from stop tomcat and listener";
	}
	public  String startTomcatAndListener(String bisServer,String batchName) {
		String startT1=HagUtil.startTomcatClean(bisServer,  batchName);
		String startL1=HagUtil.startEmergeListenerClean( bisServer, batchName);
		if(startT1.startsWith("0~") && startL1.startsWith("0~"))
			return "0~from start tomcat and listener";
		else
			return "1~from start tomcat and listener";
	}
	public  void checkLoad(HagRc hagRc,String loadLog) {
		HagUtil.sleep(6000);
		HagStringList lst = new HagStringList(loadLog,true,"*",false);
		
		String ansCheck = checkSapiens(lst);
		hagRc.log.add(ansCheck);
		if (ansCheck.startsWith("0~")) {
			hagRc.rc=0;
		
		} else {
			hagRc.rc=1;
		
		}
		return ;
	}
	public 	String checkSapiens(HagStringList lst){
		String ans ="1~failed to check sapiens lst file.";
		String input ="";
		String good ="";
		for(int i = 0 ; i < lst.size();i++){
			String temp = lst.get(i);
			if(temp.indexOf("Input     :")>-1){
				input = ""+temp;
			}
			if(temp.indexOf("Good      :")>-1){
				good = ""+temp;
			}
			if(input.length()>0 && good.length()>0)
				break;
		}
		if(input.length()>0 && good.length()>0){
			input=input.trim();
			good=good.trim();
			String inputVal = input.substring(input.indexOf(":"),input.length());
			String goodVal 	= good.substring(good.indexOf(":"),good.length());
			inputVal 	= inputVal.trim();
			goodVal 	= goodVal.trim();
			if(inputVal.equals(goodVal))
				ans="0~sapiens lst check\n"+input+"\n"+good;	
			else
				ans="1~sapiens lst check\n"+input+"\n"+good;	
		}else {
			ans =ans+"lstLen="+lst.size()+",inputLen="+input.length()+",goodLength"+good.length();
		}
		
		return ans;
	}
	public  String printLoadTaskLogs(String sentBy,String src,String tgt,String tsk,
			String rsFolder,String hrefLogsFolder,String prefix,String hrefLstExtract,String hrefBkDbLog,String hrefLstStop,
			String hrefLstRestore,String hrefLstStart ,String hrefLstLoad,String hrefCheckLockLog,String hrefLockLog,String hrefReleaseLog,String msg
			) {
		//1failed to backup
		//3failed to restore
		//3failed to restore
		//4failed to extract
		//5all ok
		StringBuilder logsSb = new StringBuilder();
		logsSb.append("<body bgcolor=\"#cccccc\" >");
	
		logsSb.append("<h3>Load task from trunk to minor results:</h3><br>");
		
		logsSb.append("<h4>source environemnt:"+src+"<br>");
		logsSb.append("target environment:"+tgt+"<br>");
		logsSb.append("task number:"+tsk+"<br>");
		logsSb.append("sent by:"+sentBy+"</h4>");
		
		logsSb.append("<table bgcolor=\"#eeeeee\" border=\"1\" >");

		File folder = new File(rsFolder);
		if(folder.exists() && folder.isDirectory())
			logsSb.append(hrefLogsFolder);
		//checkLock
		File lstCheckLock = new File(prefix+"_checkLock.txt");
		if(lstCheckLock.exists() && lstCheckLock.isFile())
			logsSb.append(hrefCheckLockLog);
		//bk
		logsSb.append(hrefBkDbLog);
	
		//extract
		File lstExtract = new File(prefix+"_extract.txt");
		if(lstExtract.exists() && lstExtract.isFile())
			logsSb.append(hrefLstExtract);
		
		//lock
		File lstSetLock = new File(prefix+"_setLock.txt");
		if(lstSetLock.exists() && lstCheckLock.isFile())
			logsSb.append(hrefLockLog);
		
		//load
		File lstLoad = new File(prefix+"_load.txt");
		if(lstLoad.exists() && lstLoad.isFile())
			//logsSb.append("<tr bgcolor=\"#dddddd\"><td>step 4:</td><td>load step log</td><td>"+hrefLstLoad+"</td></tr >");
			logsSb.append(hrefLstLoad);
		logsSb.append(hrefLstStop);
		logsSb.append(hrefLstRestore);
		
		//free
		File lstfreeLock = new File(prefix+"_freeLock.txt");
		if(lstfreeLock.exists() && lstfreeLock.isFile())
			logsSb.append(hrefReleaseLog);
	
		logsSb.append("</table>");
		logsSb.append("<h3>"+msg+"</h3>");
		logsSb.append("</body>");
		return logsSb.toString();
	}
}
