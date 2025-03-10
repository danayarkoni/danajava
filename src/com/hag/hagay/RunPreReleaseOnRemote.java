package com.hag.hagay;

import java.io.File;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class RunPreReleaseOnRemote {
	public static final String 			before(HagStringList cbEnvs,String cfgMenuWebUser ){
		if(cbEnvs.size()>1)
			return HagUtil.shortHtml("only one env (checkBox) can be selected for this option", "red","bg");
		return before1(cbEnvs.get(0),cfgMenuWebUser);
	}
	public static String[] 		getEnvVals(String appServer ,String batchName, int[] inds){
		String[] ans = new String[inds.length];
		HagSQL hagSQL = new HagSQL();
		String stm = "select * from dbo.ri_environments_new where status='A' and bis_server='"+appServer+"' and batchName='"+batchName+"'";
		HagStringList list = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,37,"~",null,null);
		for(int i = 0 ; i < inds.length;i++) {
			ans[i]=HagUtil.getWord0(list.get(0),"~",inds[i],true);
		}
		return ans;
	}
	public static String[] 		getTopackFromVer(String ver){
		String[] ans = {".","."};
		StringBuilder ans0 = new StringBuilder("\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V");
		ans0.append(ver.charAt(0)).append(ver.charAt(1)).append(ver.charAt(3)).append(ver.charAt(4));

		StringBuilder ans1 = new StringBuilder("\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\<font color='red'>V");
		ans1.append(ver.charAt(0)).append(ver.charAt(1)).append(ver.charAt(3)).append(ver.charAt(4));
	
		if(ver.endsWith("00.00")) {
			ans0.append("m00\\TOPACK");
			ans1.append("m00</font>\\TOPACK");
			ans[0]=ans0.toString();
			ans[1]=ans1.toString();
			return ans;
		}
		
		ans0.append("m").append(ver.charAt(6)).append(ver.charAt(7)).append("U00\\TOPACK");
		ans1.append("m").append(ver.charAt(6)).append(ver.charAt(7)).append("U00</font>\\TOPACK");
		ans[0]=ans0.toString();
		ans[1]=ans1.toString();
		return ans;

	}
	public static String 		before1(String line,String cfgMenuWebUser){
		

		//if(!isMyPrivateEnv(cfgMenuWebUser,privateDb,privateAppServer))
		//	return HagUtil.shortHtml(privateAppServer+"/"+privateDb+" is not your private environment", "red","bg");
	String toAPP 		= HagUtil.getWord0(line,"~",1,true); 
	String toBatchName 		= HagUtil.getWord0(line,"~",2,true); 
	String partyId 			= HagUtil.getWord0(line,"~",3,true); 
	String lastInst 		= HagUtil.getWord0(line,"~",4,true); 
	String ladtRc 			= HagUtil.getWord0(line,"~",5,true); 
	String verRi			= HagUtil.getWord0(line,"~",6,true); 
	String maint 			= HagUtil.getWord0(line,"~",7,true); 
	String oded 			= HagUtil.getWord0(line,"~",8,true); 
	String envType 			= HagUtil.getWord0(line,"~",9,true); 
	String toSQL 			= HagUtil.getWord0(line,"~",10,true); 
	String db 				= HagUtil.getWord0(line,"~",11,true); 
	String owner 			= HagUtil.getWord0(line,"~",12,true); 
	String iwayServer 		= HagUtil.getWord0(line,"~",13,true); 
	String iwayLink 		= HagUtil.getWord0(line,"~",14,true); 
	String eMergePort 		= HagUtil.getWord0(line,"~",15,true); 
	String connectionPort 	= HagUtil.getWord0(line,"~",16,true); 
	String serverPort 		= HagUtil.getWord0(line,"~",17,true); 
	String debugPort 		= HagUtil.getWord0(line,"~",18,true); 
	String note		 		= HagUtil.getWord0(line,"~",19,true); 
		int[] inds = {7,8};
		String[] vals = getEnvVals(toAPP,toBatchName,inds);
		String verLine= vals[0];
		String ver = HagUtil.getWord0(verLine,"-",0, true);
		String verRC= vals[1];
		if(!verRC.equals("OK"))
			return HagUtil.shortHtml(toAPP+"/"+toBatchName+" last installation = failed", "red","bg");
		
		String[] toPackArr = getTopackFromVer(ver);
		String toPack0 = toPackArr[0];
		String toPack1 = toPackArr[1];
		///String dev = getDevDB(ver);
	///	if(dev==null)
		///	return HagUtil.shortHtml("DEV DB not found for "+ver.substring(0, 5)+" (devIntPerVersions.list)", "red","bg");
		///
		
		//String iFrameFolder="\\\\ri-archive\\Saptech-Archive\\RI\\debug\\dev2private\\iframe";
		//String iframeInit = HagUtil.copyFileViaDos(iFrameFolder+"\\iframeSkel.html", iFrameFolder+"\\"+privateDb+".html");
		
		String iFrameTarget="\\\\ri-archive\\Saptech-Archive\\RI\\debug\\dev2privateNew\\iframes\\"+toAPP+"_"+toBatchName+".html";
		String iFrameSkel="\\\\ri-archive\\Saptech-Archive\\RI\\debug\\dev2privateNew\\iframeSkel.html";
		String iframeInit = HagUtil.copyFileViaDos(iFrameSkel, iFrameTarget);
		
		
		
		if(!iframeInit.startsWith("0~"))
			return HagUtil.shortHtml("Failed to init iFrameFile2. callhagay - 2527<br>"+iframeInit, "red","bg");
		String toAppendFolder ="\\\\"+toAPP+"\\RI_SHARE\\"+toBatchName+"\\TO_APPEND";
		HagStringList toAppendFiles = HagUtil.getFilesInDir(toAppendFolder);
		String toAppend=toAppendFiles.convertToString(" "); 
		toAppend = HagUtil.replaceStr(toAppend, "\\", " , ");
		toAppend= toAppend.trim();
		if(toAppend.startsWith(","))
			toAppend= toAppend.substring(1, toAppend.length());
		HagStringList ans = new HagStringList();
		ans.add("<head><script type=\"text/javascript\">");
		ans.add("function refreshIFrame() {");
		ans.add("var interval =setInterval(\"reloadIFrame();\", 3000);");
		ans.add("}");
		ans.add("function reloadIFrame() {");
		ans.add("document.getElementById('iframe_id').src += '';");
		ans.add("}");
		ans.add("function getBrowser()"); 
		ans.add("{");
		//ans.add("var ua = window.navigator.userAgent;");
		//ans.add("document.getElementById(\"myBrowser\").value = ua;");
		ans.add("}");
		ans.add("</script></head>	");
		ans.add("<body onload=\"refreshIFrame();getBrowser()\">");
	
		ans.add("<h2>run preRelease elements on a remote envronemnt</h2>");
		ans.add("<h3><font color=blue>Target environment properties</font></h3>");
		ans.add("<FORM METHOD=POST name=\".\" ACTION=\"runPreReleaseOnRemote.jsp\">");
		ans.add("<table bgColor=\"#666666\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.add("<tr><td bgColor=\"#bbbbbb\">APP server						</td><td bgColor=\"#dddddd\">"+toAPP+"         		</td></tr>");
		ans.add("<tr><td bgColor=\"#bbbbbb\">SQL server						</td><td bgColor=\"#dddddd\">"+toSQL+"         		</td></tr>");
		ans.add("<tr><td bgColor=\"#bbbbbb\">batchName						</td><td bgColor=\"#dddddd\">"+toBatchName+"		</td></tr>");
		ans.add("<tr><td bgColor=\"#bbbbbb\">last installed  				</td><td bgColor=\"#dddddd\">"+verLine+"			</td></tr>");
		ans.add("<tr><td bgColor=\"#bbbbbb\">TOPACK folder              	</td><td bgColor=\"#ccff99\">"+toPack1+"			</td></tr>");
		ans.add("<tr><td bgColor=\"#bbbbbb\">TO_APPEND folder				</td><td bgColor=\"#ccff99\">"+toAppendFolder+"		</td></tr>");

		if(toAppend.length()==0)
			ans.add("<tr><td bgColor=\"#bbbbbb\">elements found in your TO_APPEND folder</td><td bgColor=\"#dddddd\">"+toAppend+"	         								</td></tr>");
		else
			ans.add("<tr><td bgColor=\"#bbbbbb\">elements found in your TO_APPEND folder</td><td bgColor=\"#ccff99\">"+toAppend+"	         								</td></tr>");
		
		ans.add("</table>");
		ans.add("<br>");
		ans.add("<h3><font color=blue>Target environment properties</font></h3>");
		ans.add("<table bgColor=\"#999999\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.add("<tr bgColor=\"#aaaaaa\"><td>process		</td><td>from 			</td><td>note			</td></tr>");
		ans.add("<tr bgColor=\"#dddddd\"><td><input type=\"checkbox\" name=\"prc8\" id=\"prc8\" value=\"Replace IOM folder\" 	checked>Replace IOM folder	</td><td>from DEV environmnet					</td><td>.			</td></tr>");
		ans.add("<tr bgColor=\"#dddddd\"><td><input type=\"checkbox\" name=\"prc8\" id=\"prc8\" value=\"Replace DDC splits\" 	checked>Replace DDC splits	</td><td>from DEV database						</td><td bgColor=\"#ff8866\">Only CO0,CO1 and CAT splits, without running dblex	</td></tr>");
		ans.add("<tr bgColor=\"#dddddd\"><td><input type=\"checkbox\" name=\"prc8\" id=\"prc8\" value=\"Replace war\" 			checked>Replace war			</td><td>from TO_APPEND or in TOPACK folders	</td><td bgColor=\"#ff8866\">ri-web.war must exist	in TO_APPEND or in TOPACK </td></tr>");
		ans.add("<tr bgColor=\"#dddddd\"><td><input type=\"checkbox\" name=\"prc8\" id=\"prc8\" value=\"Run mig-mng\" 			checked>Run mig-mng			</td><td>from TO_APPEND or in TOPACK folders	</td><td bgColor=\"#ff8866\">RIAdapter.dll and mig-mng.jar must exist in TO_APPEND or in TOPACK<br>a cmd windows will be prompted - do not close it</td></tr>");
		ans.add("</table>");
	
		
		ans.add("<input type=\"hidden\" name=\"user\" 				id=\"user\" 				value = \""+cfgMenuWebUser+"\" maxlength=\"140\" size=\"140\">");
	///	ans.add("<input type=\"hidden\" name=\"devDb\" 				id=\"devDb\" 				value = \""+dev+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"devAppServer\" 		id=\"devAppServer\" 		value = \"RIDEVBLP06\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"devSqlServer\" 		id=\"devSqlServer\" 		value = \"RI7QA\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"privateDb\" 			id=\"privateDb\" 			value = \""+toBatchName+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"privateAppServer\" 	id=\"privateAppServer\" 	value = \""+toAPP+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"privateSqlServer\" 	id=\"privateSqlServer\" 	value = \""+toSQL+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"ver\" 				id=\"ver\" 					value = \""+ver+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"toPack\" 			id=\"toPack\" 				value = \""+toPack0+"\" maxlength=\"140\" size=\"140\">");
		//ans.add("<br><INPUT TYPE=SUBMIT value=\"Run\" onclick=\"this.style.display = 'none'\" ></FORM>");
		//	ans.add("<br><br><INPUT TYPE=SUBMIT value=\"Run\"   onclick=\"this.disabled=true; this.value='Please Wait...';\"></FORM>");
		//ans.add("<br><INPUT TYPE=SUBMIT value=\"Replace my private environment\" onclick=\"document.getElementById('loader1').style.display = ''; this.style.display = 'none'\" ></FORM>");
		ans.add("<br><INPUT TYPE=SUBMIT value=\"Run\" onclick=\"document.getElementById('loader1').style.display = ''; this.style.display = 'none'\" ></FORM>");
		
		ans.add("<table bgColor=\"#00ff00\"><tr><td bgColor=\"#00ffff\"><textarea readonly style=\"color: red; background-color: lightyellow;display: none; \" cols=\"100\" rows=\"5\" id=\"loader1\">Please wait&#13;&#10;wait time depends on the DB and APP elements size&#13;&#10;usually takes a few minutes&#13;&#10;Take a coffee and relax&#13;&#10;HAKUNA MATATA</textarea></td></tr></table>");
		ans.add("<br><iframe  id=\"iframe_id\" name=\"iframe_id\" src=\"file://///ri-archive/Saptech-Archive/RI/debug/dev2privateNew/iframes/"+toAPP+"_"+toBatchName+".html\" height=\"850\" width=\"1500\"></iframe>");		
		ans.add("</BODY>");
		return ans.convertToString(" ");
	
	}
	public String 				before2(HttpServletRequest request, HttpServletResponse response){
		
		String [] prc8		= request.getParameterValues("prc8");
		String user			= request.getParameter( "user" );
		String devDb		= request.getParameter( "devDb" );
		String devAppServer	= request.getParameter( "devAppServer" );
		String devSqlServer	= request.getParameter( "devSqlServer" );
		String toBatchName	= request.getParameter( "privateDb" );
		String toAPP		= request.getParameter( "privateAppServer" );
		String toSQL		= request.getParameter( "privateSqlServer" );
		String ver			= request.getParameter( "ver" );
		String toPack		= request.getParameter( "toPack" );
		String toIomNfs 	= "\\\\"+toAPP+"\\RI_SHARE\\"+toBatchName+"\\IOM_Core";
		String devIomNfs 	= "\\\\"+devAppServer+"\\RI_SHARE\\"+devDb+"\\IOM";
		//String devIomCustNfs = "\\\\"+devAppServer+"\\RI_SHARE\\"+devDb+"\\IOM_Cust";
		String debugBase 	= "\\\\ri-archive\\Saptech-Archive\\RI\\debug\\dev2privateNew";
		String debugFolder 	= debugBase+"\\"+toAPP+"_"+toBatchName;
		String debugHtmlFile 	= debugBase+"\\iframes\\"+toAPP+"_"+toBatchName+".html";
		HagUtil.replaceStringInFile(debugHtmlFile, "Process", "Process:");
		if(prc8 == null || prc8.length==0) {
			return HagUtil.shortHtml("No action selected.", "red","bg");
		}
		
		HagUtil.replaceStringInFile(debugHtmlFile, "Estimated time", "Estimated time:");
		boolean continueFlag 		= true;
		String stopTomcat			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String stopEmergeListener	="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String deleteIom			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String replaceIom			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String deleteIomCust		="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String replaceIomCust		="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String getDdc				="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String loadDdc				="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String replaceWar			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String runMigMng			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String startEmergeListener	="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String startTomcat			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		HagUtil.replaceStringInFile(debugHtmlFile, "Started at", "Started at:");

		//String[] timeReportStart = {".",".",".",".",".",".",".",".",".","."};
		//String[] timeReportEnd = {".",".",".",".",".",".",".",".",".","."};
		
		//stopTomcat
		HagUtil.updateTimeStamp(debugHtmlFile,"stopTomcat_START","");
		stopTomcat= HagUtil.stopTomcat(toAPP, toBatchName);
		HagUtil.updateTimeStamp(debugHtmlFile,"stopTomcat_END",toAPP+"("+toBatchName+")  "+stopTomcat);
		HagUtil.writeStringToFile(debugFolder+"\\stopTomcat.txt",stopTomcat);
		if(stopTomcat.startsWith("<td bgColor=\"#FF0000\">"))
			continueFlag=false;

		//stopEmergeListener
		HagUtil.updateTimeStamp(debugHtmlFile,"stopEmergeListener_START","");
		stopEmergeListener= HagUtil.stopEmergeListener(toAPP, toBatchName);
		HagUtil.updateTimeStamp(debugHtmlFile,"stopEmergeListener_END",toAPP+"("+toBatchName+")  "+stopEmergeListener);
		HagUtil.writeStringToFile(debugFolder+"\\stopEmergeListener.txt",stopEmergeListener);
		if(stopEmergeListener.startsWith("<td bgColor=\"#FF0000\">"))
			continueFlag=false;
		
		if(1==1)
			return"aaa";
		if(continueFlag && Arrays.asList(prc8).contains("Replace IOM folder")) {
			//delete iom
			HagUtil.updateTimeStamp(debugHtmlFile,"deleteIom_START","");
			HagRc hagRcDelIom = new HagRc();
			deleteIom = replacePrivateDb_deleteIom(toAPP,toBatchName,toIomNfs,hagRcDelIom);
			HagUtil.updateTimeStamp(debugHtmlFile,"deleteIom_END",deleteIom);
			HagUtil.writeStringToFile(debugFolder+"\\deleteIom.txt",hagRcDelIom.log.convertToString("\n"));
			if(deleteIom.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
			
			
			//replace iom
			HagRc hagRcCopyIom = new HagRc();
			HagUtil.updateTimeStamp(debugHtmlFile,"replaceIom_START","");
			replaceIom = replacePrivateDb_replaceIom(toAPP,toBatchName,devIomNfs,hagRcCopyIom);
			HagUtil.updateTimeStamp(debugHtmlFile,"replaceIom_END",replaceIom);
			HagUtil.writeStringToFile(debugFolder+"\\replaceIom.txt",hagRcCopyIom.log.convertToString("\n"));
			if(replaceIom.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
			
		}
		if(continueFlag && Arrays.asList(prc8).contains("Replace IOM_Cust folder")) {
			//delete iom cust
			HagUtil.updateTimeStamp(debugHtmlFile,"deleteIomCust_START","");
			HagRc hagRcDelIom = new HagRc();
			deleteIomCust = replacePrivateDb_deleteIomCust(toAPP,toBatchName,hagRcDelIom);
			HagUtil.updateTimeStamp(debugHtmlFile,"deleteIomCust_END",deleteIomCust);
			HagUtil.writeStringToFile(debugFolder+"\\deleteIomCust.txt",hagRcDelIom.log.convertToString("\n"));
			if(deleteIomCust.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
			
			
			//replace iom cust
			HagRc hagRcCopyIomCust = new HagRc();
			HagUtil.updateTimeStamp(debugHtmlFile,"replaceIomCust_START","");
			replaceIomCust = replacePrivateDb_replaceIomCust(toAPP,toBatchName,devIomNfs,hagRcCopyIomCust);
			HagUtil.updateTimeStamp(debugHtmlFile,"replaceIomCust_END",replaceIomCust);
			HagUtil.writeStringToFile(debugFolder+"\\replaceIomCust.txt",hagRcCopyIomCust.log.convertToString("\n"));
			if(replaceIom.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
			
		}
		if(continueFlag && Arrays.asList(prc8).contains("Replace DDC splits")) {
			//get ddc bcp
			HagRc hagRcGetDdc = new HagRc();
			HagUtil.updateTimeStamp(debugHtmlFile,"getDdc_START","");
			getDdc = getreplacePrivateDb_getDDC(devDb,devSqlServer,debugFolder,hagRcGetDdc);
			HagUtil.updateTimeStamp(debugHtmlFile,"getDdc_END",getDdc);
			HagUtil.writeStringToFile(debugFolder+"\\getDdc.txt",hagRcGetDdc.log.convertToString("\n"));
			if(getDdc.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
			
			//load ddc bcp
			HagRc hagRcLoadDdc = new HagRc();
			HagUtil.updateTimeStamp(debugHtmlFile,"loadDdc_START","");
			loadDdc = getreplacePrivateDb_loadDDC(toBatchName,toSQL,debugFolder,hagRcLoadDdc);
			HagUtil.updateTimeStamp(debugHtmlFile,"loadDdc_END",loadDdc);
			HagUtil.writeStringToFile(debugFolder+"\\loadDdc.txt",hagRcLoadDdc.log.convertToString("\n"));
			if(loadDdc.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
		}
		if(continueFlag && Arrays.asList(prc8).contains("Replace war")) {
			//replace war
			HagRc hagRcReplaceWar = new HagRc();
			HagUtil.updateTimeStamp(debugHtmlFile,"replaceWar_START","");
			replaceWar= replacePrivateDb_replaceWar(toBatchName,toAPP,hagRcReplaceWar,toPack);
			HagUtil.updateTimeStamp(debugHtmlFile,"replaceWar_END",toAPP+"("+toBatchName+")  "+replaceWar);
			HagUtil.writeStringToFile(debugFolder+"\\replaceWar.txt",hagRcReplaceWar.log.convertToString("\n"));
			if(replaceWar.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
		}
		if(continueFlag && Arrays.asList(prc8).contains("Run mig-mng")) {
			//run mig-mng
			HagRc hagRcRunMigMng = new HagRc();
			HagUtil.updateTimeStamp(debugHtmlFile,"runMigMng_START","");
			runMigMng= replacePrivateDb_runMigMng(toBatchName,toAPP,hagRcRunMigMng,ver,debugBase,debugHtmlFile,toPack);
			HagUtil.updateTimeStamp(debugHtmlFile,"runMigMng_END",toAPP+"("+toBatchName+")  "+runMigMng);
			HagUtil.writeStringToFile(debugFolder+"\\runMigMng.txt",hagRcRunMigMng.log.convertToString("\n"));
			if(runMigMng.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
		}
			
		//startEmergeListener1
		HagUtil.updateTimeStamp(debugHtmlFile,"startEmergeListener_START","");
		startEmergeListener= HagUtil.startEmergeListener1(toAPP, toBatchName);
		HagUtil.updateTimeStamp(debugHtmlFile,"startEmergeListener_END",startEmergeListener);
		HagUtil.writeStringToFile(debugFolder+"\\startEmergeListener.txt",startEmergeListener);
		
		//startTomcat
		HagUtil.updateTimeStamp(debugHtmlFile,"startTomcat_START","");
		startTomcat= HagUtil.startTomcat(toAPP, toBatchName,replaceWar);
		HagUtil.updateTimeStamp(debugHtmlFile,"startTomcat_END",startTomcat);
		HagUtil.writeStringToFile(debugFolder+"\\startTomcat.txt",startTomcat);	
			
				
				
		StringBuilder ans = new StringBuilder("<h3>Replace private environment</h3><table bgColor=\"#aaaaaa\" cellpadding=\"3\" border =\"1\" >");
		ans.append("<tr><td>Stop tomcat					</td>"+stopTomcat+"</tr>");
		ans.append("<tr><td>Stop eMerge listener		</td>"+stopEmergeListener+"</tr>");
		ans.append("<tr><td>Replace IOM					</td>"+replaceIom+"</tr>");
		ans.append("<tr><td>Replace IOM	cust				</td>"+replaceIomCust+"</tr>");
		ans.append("<tr><td>Get DDC bcp files			</td>"+getDdc+"</tr>");
		ans.append("<tr><td>Load DDC bcp files			</td>"+loadDdc+"</tr>");
		ans.append("<tr><td>Replace War					</td>"+replaceWar+"</tr>");
		ans.append("<tr><td>Run mig-mng					</td>"+runMigMng+"</tr>");
		ans.append("<tr><td>Start eMerge listener		</td>"+startEmergeListener+"</tr>");
		ans.append("<tr><td>Start tomcat				</td>"+startTomcat+"</tr>");
		ans.append("<tr><td>Start iWay					</td><td><a href=\"http://"+toAPP+"/sapweb/Admin/Logon.htm?"+toAPP+"&/sapweb&"+toAPP+"-"+toBatchName+"\">http://"+toAPP+"/sapweb/Admin/Logon.htm?"+toAPP+"&/sapweb&"+toAPP+"-"+toBatchName+"</a></td></tr>");
		ans.append("</table>");
		
				
		//String linkToDebug 	= "<a href=\"file:///ri-archive/Saptech-Archive/RI/debug/private_envs\">link to debug</a>";
		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+user,HagUtil.mailList_hag,"Append DEV elements to my private env "+devAppServer+"("+devDb+") to "+toAPP+"("+toBatchName+") by "+user,ans.toString()); 
		return ans.toString();

	}
	public String 				replacePrivateDb_deleteIom(String toApp,String toDb,String iomNfs,HagRc hagRcIom){
		//iom

		String to ="\\\\"+toApp+"\\RI_SHARE\\"+toDb+"\\IOM_Core";
		hagRcIom.log.add("folder to delete = "+to);
		HagUtil.reCreateFolder(hagRcIom, to);
		if(hagRcIom.rc!=0){
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to recreate IOM folder "+to+"<br>"+hagRcIom.log.convertToString("<br>") +"</td>";
			return ansTemp2;
		}else{
			String ansTemp2 = "<td bgColor=\"#00FF00\">IOM folder "+to+"reCreated<br>"+hagRcIom.log.convertToString("<br>") +"</td>";
			return ansTemp2;
		}
	}
	public String 				replacePrivateDb_deleteIomCust(String toApp,String toDb,HagRc hagRcIom){
		//iom

		String to ="\\\\"+toApp+"\\RI_SHARE\\"+toDb+"\\IOM_Cust";
		hagRcIom.log.add("folder to delete = "+to);
		HagUtil.reCreateFolder(hagRcIom, to);
		if(hagRcIom.rc!=0){
			String ansTemp2 = "<td bgColor=\"#E2F30C\">Failed to recreate IOM folder "+to+"<br>"+hagRcIom.log.convertToString("<br>") +"</td>";
			return ansTemp2;
		}else{
			String ansTemp2 = "<td bgColor=\"#00FF00\">IOM folder "+to+"reCreated<br>"+hagRcIom.log.convertToString("<br>") +"</td>";
			return ansTemp2;
		}
	}
	public String 				replacePrivateDb_replaceIom(String toApp,String toDb,String iomNfs,HagRc hagRcCopyIom){
		//iom

		String to ="\\\\"+toApp+"\\RI_SHARE\\"+toDb+"\\IOM_Core";
	
	
		
			String ans12i = HagUtil.copyFolder( iomNfs,to,false,false,"3","");
			hagRcCopyIom.log.add(ans12i);

			if(!ans12i.startsWith("0~"))
				return "<td bgColor=\"#FF0000\">Failed to copy IOM from "+iomNfs+" to "+to+"<br>"+ans12i+"</td>";
			else
				return "<td bgColor=\"#00FF00\">copy IOM from "+iomNfs+" to "+to+"<br> done.</td>";
	
		
		
	}
	public String 				replacePrivateDb_replaceIomCust(String toApp,String toDb,String iomNfs,HagRc hagRcCopyIom){
		//iom
		if(iomNfs.endsWith("_Core"))
			iomNfs=iomNfs.substring(0,iomNfs.length()-5);
		iomNfs=iomNfs+"_Cust";
			String to ="\\\\"+toApp+"\\RI_SHARE\\"+toDb+"\\IOM_Cust";
			String ans12i = HagUtil.copyFolder( iomNfs,to,false,false,"3","");
			hagRcCopyIom.log.add(ans12i);
			if(!ans12i.startsWith("0~"))
				return "<td bgColor=\"#E2F30C\">Failed to copy IOM from "+iomNfs+" to "+to+"<br>"+ans12i+"</td>";
			else
				return "<td bgColor=\"#00FF00\">copy IOM from "+iomNfs+" to "+to+"<br> done.</td>";
	}
	public String  				getreplacePrivateDb_getDDC(String fromDb,String fromSqlServer,String logsFolder,HagRc hagRc){
	
		
		// replace ddc + pure
		
		String ddcfolder = logsFolder + "\\DDC\\";
		HagUtil.reCreateFolder(hagRc,ddcfolder);
		if(hagRc.rc!=0){
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to recreate DDC folder "+ddcfolder+"<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}
		
		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CO0", fromSqlServer, hagRc);
		
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CO0 bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CO1", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CO1 bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CAT", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CAT bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}
/*
		HagUtil.crtBcps( ddcfolder, fromDb, "SAPIENS", "PR4530EN", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create PR4530EN bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "SAPIENS", "PR4530ENL", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create PR4530ENL bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "SAPIENS", "PR4530ENJ", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create PR4530ENJ bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CT", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CT bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}
		
		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTCAT", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTCAT bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTCM1", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTCM1 bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTCMN", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTCMN bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTCTU", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTCTU bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTCU0", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTCU0 bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTCU1", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTCU1 bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTDAT", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTDAT bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTDLK", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTDLK bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}
	
		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTENU", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTENU bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTJSQ", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTJSQ bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTLCL", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTLCL bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTLEX", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTLEX bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTSWS", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTSWS bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTSEC", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTSEC bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}
*/
	
		String ansTemp2 = "<td bgColor=\"#00FF00\">bcp files created</td>";
		return ansTemp2;
	}
	public String  				getreplacePrivateDb_loadDDC(String toDb,String toSQL,String debug2,HagRc hagRc){
		String ddcfolder = debug2 + "\\DDC\\";
	
		HagSQL hagSql = new HagSQL();
		hagSql.runSqlGroupDDC(hagRc,ddcfolder, toSQL,"cfg","cfg09c",toDb);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to load DDC to "+toDb+"<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}
		String ansTemp2 = "<td bgColor=\"#00FF00\">bcp files loaded</td>";
		return ansTemp2;
	}
	public String 				replacePrivateDb_replaceWar(String toBatchName,String toApp,HagRc hagRcReplaceWar,String toPack){
		

		String to ="\\\\"+toApp+"\\RI_SHARE\\"+toBatchName+"\\TO_APPEND";
		String warSrc =to+"\\ri-web.war";
		
//		File dir = new File(to);
//		if(!dir.exists() || !dir.isDirectory())
//			return "<td bgColor=\"#FFFF00\">Skipped - "+to +" folder not found.</td>";

		File war1 = new File(warSrc);
		if(!war1.exists() || !war1.isFile()) {
			warSrc =toPack+"\\ri-components\\RiInstaller\\ri-web.war";
			File war2 = new File(warSrc);
			if(!war2.exists() || !war2.isFile()) 
			 return "<td bgColor=\"#FFFF00\">Skipped - ri-web.war file not found (in "+to+" and in "+toPack+"\\ri-components\\RiInstaller).</td>";
		
		}
		String target = "d:\\RI_JAVA\\RIjava_"+toBatchName+"\\tomcat\\";
		String warLoc = target + "\\webapps\\";
		String warTgt = target + "\\webapps\\ri-web.war";
		String warChk = target + "\\webapps\\ri-web\\index.jsp";
		String warTgt1 = "\\\\" + toApp + "\\" + warTgt.substring(3, warTgt.length());
		String warChk1 = "\\\\" + toApp + "\\" + warChk.substring(3, warChk.length());
		String riwebLoc1 = "\\\\" + toApp + "\\RI_JAVA\\RIjava_"+toBatchName+"\\tomcat\\webapps\\ri-web";
		HagClient hagClient= new HagClient();;
		// deleted1
		String command1 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\runcmd.bat del /Q " + warLoc	+ "ri-web.war";
		HagStringList tmp1 = hagClient.runNotSudo(toApp, command1);
		HagUtil.appendArrayList2ArrayList(hagRcReplaceWar.log,tmp1);
		String tmp1s = tmp1.convertToString("<br>");
		if (tmp1s.startsWith("1~")) {
			//ans[1]  = tmp1s; 
			//ans[0]	="STOP";
			//return ans;
			return "<td bgColor=\"FF0000\">failed to delete "+warLoc+"\\ri-web.war.</td>";
		}else{
			hagRcReplaceWar.log.add(warLoc+"\\ri-web.war deleted."); 
		}			
		
		//delete ri-web dir
		String command2 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\runcmd.bat rmdir " + warLoc+ "ri-web\\ /s /q";
		HagStringList tmp2 = hagClient.runNotSudo(toApp, command2);
		HagUtil.appendArrayList2ArrayList(hagRcReplaceWar.log,tmp2);
	//	String tmp2s = tmp2.convertToString("<br>");
		if (HagUtil.isFileExist(riwebLoc1)) {
			//ans[1]  = "Failed to delete+"+riwebLoc1; 
			//ans[0]	="STOP";
			//return ans;
			return "<td bgColor=\"FF0000\">failed to delete "+warLoc+"\\ri-web folder.</td>";
		}else{
			hagRcReplaceWar.log.add(warLoc+"\\ri-web folder deleted."); 
			//ans1.append(tmp2s).append("<br>"); 
		}

		// copy war file
		String ans9="aaaaaaaaaa";
		HagStringList ans6 =null;
		String command3 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\runcmd.bat copy " + warSrc + " "+ warTgt;
		HagStringList tmp3 = hagClient.runNotSudo(toApp, command3);
		HagUtil.appendArrayList2ArrayList(hagRcReplaceWar.log,tmp3);
		//String tmp3s = tmp3.convertToString("<br>");
//		ans1.append(tmp3s).append("<br>"); 
		//start tomcat
		ans6 =hagClient.run(toApp, "startTomcat " + toBatchName + "\n");
		HagUtil.appendArrayList2ArrayList(hagRcReplaceWar.log,ans6);
//		ans1.append(ans6).append("<br>"); 
		HagUtil.sleep(5000);
		if (!HagUtil.isFileExist(warChk1)) {
//			ans[1]  = "cfgMenuWeb-Failed to copy war";
//			ans[0]	="STOP";
//			return ans;
			HagUtil.sleep(15000);
			if (!HagUtil.isFileExist(warChk1)) 
				return "<td bgColor=\"FF0000\">failed to copy "+warSrc+" to "+warTgt+"</td>";
		}
			hagRcReplaceWar.log.add(warSrc+" copied to "+warTgt); 
		//	ans1.append("War replaced.<br>"); 
			//String command4 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\runcmd.bat del /Q " + warLoc	+ "ri-web.war";
			//HagStringList tmp4 = hagClient.runNotSudo(server1, command4);
			//String tmp4s = tmp4.convertToString("<br>");
			//if (tmp4s.startsWith("1~")) {
			//		ans1.append("Failed to delete war file in " + server1 + " " + batchName1+ " environment (after)").append("<br>");
			//	}else{
			//		ans1.append(warLoc).append("ri-web.war deleted.<br>"); 
			//	}
		//	ans[1]  = ans1.toString();
		//	ans[0]	="GOOD";
			return "<td bgColor=\"00FF00\">War replaced.</td>";
		
		
	}
	public String 				replacePrivateDb_runMigMng(String toBatchName,String toApp,HagRc hagRcRunMigMng,String ver5,String debugBase,String debugHtmlFile,String toPack){
		String to ="\\\\"+toApp+"\\RI_SHARE\\"+toBatchName+"\\TO_APPEND";
		String migMngSrc =to+"\\mig-mng.jar";
		String RIAdapterSrc =to+"\\RIAdapter.dll";
		String folder2 = "d:\\cfg\\TO_APPEND_DEV2PRIVATE\\"+toApp+"_"+toBatchName;
		//check
		File dir = new File(to);
		if(!dir.exists() || !dir.isDirectory())
			return "<td bgColor=\"#FFFF00\">Skipped - "+to +" folder not found.</td>";
		
		
			
		File migMng1 = new File(migMngSrc);
		if(!migMng1.exists() || !migMng1.isFile()) {
			migMngSrc =toPack+"\\ri-components\\RiInstaller\\mig-mng.jar";
			File migMng2 = new File(migMngSrc);
			if(!migMng2.exists() || !migMng2.isFile()) 
			 return "<td bgColor=\"#FFFF00\">Skipped - mig-mng.jar file not found (in "+to+" and in "+toPack+"\\ri-components\\RiInstaller).</td>";
		}
		
		File adapter1 = new File(RIAdapterSrc);
		if(!adapter1.exists() || !adapter1.isFile()) {
			RIAdapterSrc =toPack+"\\ri-components\\RiInstaller\\RIAdapter.dll";
			File adapter2 = new File(RIAdapterSrc);
			if(!adapter2.exists() || !adapter2.isFile()) 
			 return "<td bgColor=\"#FFFF00\">Skipped - RIAdapter.dll file not found (in "+to+" and in "+toPack+"\\ri-components\\RiInstaller).</td>";
		}
		//recreat folder2
		HagRc hagRcRunMigMng1 = new HagRc();
		HagUtil.reCreateFolder(hagRcRunMigMng1, folder2);
		if(hagRcRunMigMng1.rc!=0)
			return "<td bgColor=\"#FF0000\">Failed to reCreate "+folder2+" folder - call hagay 2527.</td>";

		//copy migMngSrc
		String copyRc1=HagUtil.copyFileViaDos(migMngSrc, folder2+"\\mig-mng.jar");
		if(!copyRc1.startsWith("0~")){
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to copy mig-mng.jar file - call hagay 2527</td>";
			return ansTemp2;
		}
		
		//copy adapter
		String copyRc=HagUtil.copyFileViaDos(RIAdapterSrc, folder2+"\\RIAdapter.dll");
		if(!copyRc.startsWith("0~")){
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to copy RIAdapter.dll file - call hagay 2527</td>";
			return ansTemp2;
		}
		//check webapps\\ri-web\\WEB-INF\\lib exist
		String javaFolder = "\\\\"+toApp+"\\RI_JAVA\\RIjava_"+toBatchName;
		String libStr = javaFolder + "\\tomcat\\webapps\\ri-web\\WEB-INF\\lib";
		File check1 = new File(libStr);
		int num = 0;
		while(!check1.exists() && num < 6) {
			HagUtil.replaceStringInFile(debugHtmlFile, "-}", "--}");
			HagUtil.sleep(5000);
			num++;
		}
		if(num==6) {
			return "<td bgColor=\"#FF0000\">Failed to create mig-mng command because "+libStr+" not found - call hagay 2527.</td>";	
		}
		HagUtil.sleep(5000);
		//build command
		buildRiMigCmd(hagRcRunMigMng,toBatchName, toApp,folder2,null,null,null);
		if(hagRcRunMigMng.rc!=0){
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create mig-mng command - call hagya 2527</td>";
			return ansTemp2;
		}
	
		String writeRc=hagRcRunMigMng.log.writeToFile(folder2+"\\run.bat", false);
		if(writeRc.startsWith("1~"))
			return "<td bgColor=\"#FF0000\">Failed to write java migmng command into batch file - call hagay 2527.</td>";
		//String rcc = HagUtil.runBatch(folder2 + "\\run.bat > "+folder2+"\\log.txt",true);
		String rcc = HagUtil.runBatch(folder2 + "\\run.bat",true);
		
		File check = new File(folder2+"\\done.txt");
		HagUtil.replaceStringInFile(debugHtmlFile, "Wait.", "<font color=red>Waiting for mig-mng to finish<br>-}</font>");

		while(!check.exists()) {
			HagUtil.replaceStringInFile(debugHtmlFile, "-}", "--}");
			HagUtil.sleep(5000);
		}
		//runmigMng(hagRcRunMigMng,toBatchName, riMigCmd,ver5) ;
		String tgtLog = debugBase+"\\logs\\"+toApp+"_"+toBatchName+"_mig-mng.log";
		String link = "<br>link to log will not work , failed to delete old log file.";
		
		String ans2 = HagUtil.deleteFile(tgtLog, false);
		if(!ans2.startsWith("0~")) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to to delete mig-mng old log file</td>";
			return ansTemp2;
		}
		String ans3 = HagUtil.copyFileViaDos( "d:\\cfg\\TO_APPEND_DEV2PRIVATE\\"+toApp+"_"+toBatchName+"\\mig-mng.log",tgtLog);
		if(ans3.startsWith("0~")) {
			link = "<br><a href=\""+tgtLog+"\">mig-mng log</a>";
		}else {
			link = "<br>link to log will not work , failed to copy "+"d:\\cfg\\TO_APPEND_DEV2PRIVATE\\"+toApp+"_"+toBatchName+"\\mig-mng.log";
		}
		
		return "<td bgColor=\"#00FF00\">"+rcc+link+"</td>";
	}
	private void  				buildRiMigCmd(HagRc hagRc,String batchName,String serverAPP,String migMngLoc, String migName,String bulkUser,String bulkPass) {
		//String riInst = javaFolder + "\\properties\\ri.install";
		String javaFolder = "\\\\"+serverAPP+"\\RI_JAVA\\RIjava_"+batchName;
		String dbidProperties = javaFolder + "\\properties\\" + batchName + ".properties";
		String libStr = javaFolder + "\\tomcat\\webapps\\ri-web\\WEB-INF\\lib";
		File lib = new File(libStr);
		if (!lib.exists()) {
			hagRc.log.add(libStr + " folder not found.");
			hagRc.rc=1;	
			return;
		}if (!lib.isDirectory()) {
			hagRc.log.add(libStr + " not a directory.");
			hagRc.rc=1;	
			return;

		}
		String[] children = lib.list();
		String classPath = migMngLoc+"\\mig-mng.jar;";
	//	classPath = classPath+"packages\\"+riPackage+"\\fromTopack;";
		for (int i = 0; i < children.length; i++)
			classPath = classPath + libStr + "\\" + children[i] + ";";
	//	hagRc.log.add("@echo off");
		hagRc.log.add("d:");
		hagRc.log.add("cd \\");
		hagRc.log.add("cd cfg");
		hagRc.log.add("cd TO_APPEND_DEV2PRIVATE");
		hagRc.log.add("cd "+serverAPP+"_"+batchName);
		if(migName==null) {
			hagRc.log.add("d:\\cfg\\TO_APPEND_DEV2PRIVATE\\jre\\bin\\java -cp \"" + classPath + "\" com.sapiens.mig.core.MigMngLauncher \"" + dbidProperties + "\" ");

		}else{
			hagRc.log.add("d:\\cfg\\TO_APPEND_DEV2PRIVATE\\jre\\bin\\java -cp \"" + classPath + "\" com.sapiens.mig.core.MigMngLauncher \"" + dbidProperties + "\" "+migName+" "+bulkUser+" "+bulkPass);
		}
		hagRc.log.add("echo done >done.txt");
		hagRc.log.add("exit");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	public static String 		before1(String line){
		String toAPP 		= HagUtil.getWord0(line,"~",1,true); 
		String toBatchName 		= HagUtil.getWord0(line,"~",2,true); 
		String partyId 			= HagUtil.getWord0(line,"~",3,true); 
		String lastInst 		= HagUtil.getWord0(line,"~",4,true); 
		String ladtRc 			= HagUtil.getWord0(line,"~",5,true); 
		String ver 				= HagUtil.getWord0(line,"~",6,true); 
		String maint 			= HagUtil.getWord0(line,"~",7,true); 
		String oded 			= HagUtil.getWord0(line,"~",8,true); 
		String envType 			= HagUtil.getWord0(line,"~",9,true); 
		String toSQL 		= HagUtil.getWord0(line,"~",10,true); 
		String db 				= HagUtil.getWord0(line,"~",11,true); 
		String owner 			= HagUtil.getWord0(line,"~",12,true); 
		String iwayServer 		= HagUtil.getWord0(line,"~",13,true); 
		String iwayLink 		= HagUtil.getWord0(line,"~",14,true); 
		String eMergePort 		= HagUtil.getWord0(line,"~",15,true); 
		String connectionPort 	= HagUtil.getWord0(line,"~",16,true); 
		String serverPort 		= HagUtil.getWord0(line,"~",17,true); 
		String debugPort 		= HagUtil.getWord0(line,"~",18,true); 
		String note		 		= HagUtil.getWord0(line,"~",19,true); 
		
		//String toPack	= request.getParameter( "toPack" );
		String toIomNfs = "\\\\"+toAPP+"\\RI_SHARE\\"+toBatchName+"\\IOM_Core";
		//String devIomNfs = "\\\\"+devAppServer+"\\RI_SHARE\\"+devDb+"\\IOM";
		
		
		String debugBase 		= "\\\\ri-archive\\Saptech-Archive\\RI\\debug\\dev2privateNew";
		String debugFolder 		= debugBase+"\\"+toAPP+"_"+toBatchName;
		String debugHtmlFile 	= debugBase+"\\iframes\\"+toAPP+"_"+toBatchName+".html";
		
		HagUtil.replaceStringInFile(debugHtmlFile, "Estimated time", "Estimated time:");
		boolean continueFlag = true;
		String stopTomcat			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String stopEmergeListener	="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String deleteIom			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String replaceIom			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String deleteIomCust		="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String replaceIomCust		="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String getDdc				="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String loadDdc				="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String replaceWar			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String runMigMng			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String startEmergeListener	="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String startTomcat			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		HagUtil.replaceStringInFile(debugHtmlFile, "Started at", "Started at:");

		//stopTomcat
		HagUtil.updateTimeStamp(debugHtmlFile,"stopTomcat_START","");
		stopTomcat= HagUtil.stopTomcat(toAPP, toBatchName);
		HagUtil.updateTimeStamp(debugHtmlFile,"stopTomcat_END",toAPP+"("+toBatchName+")  "+stopTomcat);
		HagUtil.writeStringToFile(debugFolder+"\\stopTomcat.txt",stopTomcat);
		if(stopTomcat.startsWith("<td bgColor=\"#FF0000\">"))
			continueFlag=false;

		//stopEmergeListener
		HagUtil.updateTimeStamp(debugHtmlFile,"stopEmergeListener_START","");
		stopEmergeListener= HagUtil.stopEmergeListener(toAPP, toBatchName);
		HagUtil.updateTimeStamp(debugHtmlFile,"stopEmergeListener_END",toAPP+"("+toBatchName+")  "+stopEmergeListener);
		HagUtil.writeStringToFile(debugFolder+"\\stopEmergeListener.txt",stopEmergeListener);
		if(stopEmergeListener.startsWith("<td bgColor=\"#FF0000\">"))
			continueFlag=false;
		
	
		if(continueFlag && Arrays.asList(prc8).contains("Replace IOM folder")) {
			//delete iom
			HagUtil.updateTimeStamp(debugHtmlFile,"deleteIom_START","");
			HagRc hagRcDelIom = new HagRc();
			deleteIom = replacePrivateDb_deleteIom(toAPP,toBatchName,toIomNfs,hagRcDelIom);
			HagUtil.updateTimeStamp(debugHtmlFile,"deleteIom_END",deleteIom);
			HagUtil.writeStringToFile(debugFolder+"\\deleteIom.txt",hagRcDelIom.log.convertToString("\n"));
			if(deleteIom.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
			
			
			//replace iom
			HagRc hagRcCopyIom = new HagRc();
			HagUtil.updateTimeStamp(debugHtmlFile,"replaceIom_START","");
			replaceIom = replacePrivateDb_replaceIom(toAPP,toBatchName,devIomNfs,hagRcCopyIom);
			HagUtil.updateTimeStamp(debugHtmlFile,"replaceIom_END",replaceIom);
			HagUtil.writeStringToFile(debugFolder+"\\replaceIom.txt",hagRcCopyIom.log.convertToString("\n"));
			if(replaceIom.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
			
		}
		if(continueFlag && Arrays.asList(prc8).contains("Replace IOM_Cust folder")) {
			//delete iom cust
			HagUtil.updateTimeStamp(debugHtmlFile,"deleteIomCust_START","");
			HagRc hagRcDelIom = new HagRc();
			deleteIomCust = replacePrivateDb_deleteIomCust(toAPP,toBatchName,hagRcDelIom);
			HagUtil.updateTimeStamp(debugHtmlFile,"deleteIomCust_END",deleteIomCust);
			HagUtil.writeStringToFile(debugFolder+"\\deleteIomCust.txt",hagRcDelIom.log.convertToString("\n"));
			if(deleteIomCust.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
			
			
			//replace iom cust
			HagRc hagRcCopyIomCust = new HagRc();
			HagUtil.updateTimeStamp(debugHtmlFile,"replaceIomCust_START","");
			replaceIomCust = replacePrivateDb_replaceIomCust(toAPP,toBatchName,devIomNfs,hagRcCopyIomCust);
			HagUtil.updateTimeStamp(debugHtmlFile,"replaceIomCust_END",replaceIomCust);
			HagUtil.writeStringToFile(debugFolder+"\\replaceIomCust.txt",hagRcCopyIomCust.log.convertToString("\n"));
			if(replaceIom.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
			
		}
		if(continueFlag && Arrays.asList(prc8).contains("Replace DDC splits")) {
			//get ddc bcp
			HagRc hagRcGetDdc = new HagRc();
			HagUtil.updateTimeStamp(debugHtmlFile,"getDdc_START","");
			getDdc = getreplacePrivateDb_getDDC(devDb,devSqlServer,debugFolder,hagRcGetDdc);
			HagUtil.updateTimeStamp(debugHtmlFile,"getDdc_END",getDdc);
			HagUtil.writeStringToFile(debugFolder+"\\getDdc.txt",hagRcGetDdc.log.convertToString("\n"));
			if(getDdc.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
			
			//load ddc bcp
			HagRc hagRcLoadDdc = new HagRc();
			HagUtil.updateTimeStamp(debugHtmlFile,"loadDdc_START","");
			loadDdc = getreplacePrivateDb_loadDDC(toBatchName,toSQL,debugFolder,hagRcLoadDdc);
			HagUtil.updateTimeStamp(debugHtmlFile,"loadDdc_END",loadDdc);
			HagUtil.writeStringToFile(debugFolder+"\\loadDdc.txt",hagRcLoadDdc.log.convertToString("\n"));
			if(loadDdc.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
		}
		if(continueFlag && Arrays.asList(prc8).contains("Replace war")) {
			//replace war
			HagRc hagRcReplaceWar = new HagRc();
			HagUtil.updateTimeStamp(debugHtmlFile,"replaceWar_START","");
			replaceWar= replacePrivateDb_replaceWar(toBatchName,toAPP,hagRcReplaceWar,toPack);
			HagUtil.updateTimeStamp(debugHtmlFile,"replaceWar_END",toAPP+"("+toBatchName+")  "+replaceWar);
			HagUtil.writeStringToFile(debugFolder+"\\replaceWar.txt",hagRcReplaceWar.log.convertToString("\n"));
			if(replaceWar.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
		}
		if(continueFlag && Arrays.asList(prc8).contains("Run mig-mng")) {
			//run mig-mng
			HagRc hagRcRunMigMng = new HagRc();
			HagUtil.updateTimeStamp(debugHtmlFile,"runMigMng_START","");
			runMigMng= replacePrivateDb_runMigMng(toBatchName,toAPP,hagRcRunMigMng,ver,debugBase,debugHtmlFile,toPack);
			HagUtil.updateTimeStamp(debugHtmlFile,"runMigMng_END",toAPP+"("+toBatchName+")  "+runMigMng);
			HagUtil.writeStringToFile(debugFolder+"\\runMigMng.txt",hagRcRunMigMng.log.convertToString("\n"));
			if(runMigMng.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
		}
			
		//startEmergeListener1
		HagUtil.updateTimeStamp(debugHtmlFile,"startEmergeListener_START","");
		startEmergeListener= HagUtil.startEmergeListener1(toAPP, toBatchName);
		HagUtil.updateTimeStamp(debugHtmlFile,"startEmergeListener_END",startEmergeListener);
		HagUtil.writeStringToFile(debugFolder+"\\startEmergeListener.txt",startEmergeListener);
		
		//startTomcat
		HagUtil.updateTimeStamp(debugHtmlFile,"startTomcat_START","");
		startTomcat= HagUtil.startTomcat(toAPP, toBatchName,replaceWar);
		HagUtil.updateTimeStamp(debugHtmlFile,"startTomcat_END",startTomcat);
		HagUtil.writeStringToFile(debugFolder+"\\startTomcat.txt",startTomcat);	
			
				
				
		StringBuilder ans = new StringBuilder("<h3>Replace private environment</h3><table bgColor=\"#aaaaaa\" cellpadding=\"3\" border =\"1\" >");
		ans.append("<tr><td>Stop tomcat					</td>"+stopTomcat+"</tr>");
		ans.append("<tr><td>Stop eMerge listener		</td>"+stopEmergeListener+"</tr>");
		ans.append("<tr><td>Replace IOM					</td>"+replaceIom+"</tr>");
		ans.append("<tr><td>Replace IOM	cust				</td>"+replaceIomCust+"</tr>");
		ans.append("<tr><td>Get DDC bcp files			</td>"+getDdc+"</tr>");
		ans.append("<tr><td>Load DDC bcp files			</td>"+loadDdc+"</tr>");
		ans.append("<tr><td>Replace War					</td>"+replaceWar+"</tr>");
		ans.append("<tr><td>Run mig-mng					</td>"+runMigMng+"</tr>");
		ans.append("<tr><td>Start eMerge listener		</td>"+startEmergeListener+"</tr>");
		ans.append("<tr><td>Start tomcat				</td>"+startTomcat+"</tr>");
		ans.append("<tr><td>Start iWay					</td><td><a href=\"http://"+toAPP+"/sapweb/Admin/Logon.htm?"+toAPP+"&/sapweb&"+toAPP+"-"+toBatchName+"\">http://"+toAPP+"/sapweb/Admin/Logon.htm?"+toAPP+"&/sapweb&"+toAPP+"-"+toBatchName+"</a></td></tr>");
		ans.append("</table>");
		
				
		//String linkToDebug 	= "<a href=\"file:///ri-archive/Saptech-Archive/RI/debug/private_envs\">link to debug</a>";
		String ans1		= HagUtil.sendMail_hag(HagUtil.cfgTeamMail,user+"@sapiens.com","david.ha@sapiens.com;gonen.s@sapiens.com","Append DEV elements to my private env "+devAppServer+"("+devDb+") to "+toAPP+"("+toBatchName+") by "+user,ans.toString()); 
		return ans.toString();
	}
	*/
	/*
	public String 		before2(String line){
		return line;
	
		
		String [] prc8	= request.getParameterValues("prc8");
		String user		= request.getParameter( "user" );
		String devDb		= request.getParameter( "devDb" );
		String devAppServer	= request.getParameter( "devAppServer" );
		String devSqlServer	= request.getParameter( "devSqlServer" );
		String toBatchName		= request.getParameter( "privateDb" );
		String toAPP	= request.getParameter( "privateAppServer" );
		String toSQL	= request.getParameter( "privateSqlServer" );
		String ver	= request.getParameter( "ver" );
		String toPack	= request.getParameter( "toPack" );
		String toIomNfs = "\\\\"+toAPP+"\\RI_SHARE\\"+toBatchName+"\\IOM_Core";
		String devIomNfs = "\\\\"+devAppServer+"\\RI_SHARE\\"+devDb+"\\IOM";
		//String devIomCustNfs = "\\\\"+devAppServer+"\\RI_SHARE\\"+devDb+"\\IOM_Cust";
		String debugBase = "\\\\ri-archive\\Saptech-Archive\\RI\\debug\\dev2privateNew";
		String debugFolder = debugBase+"\\"+toAPP+"_"+toBatchName;
		String debugHtmlFile = debugBase+"\\iframes\\"+toAPP+"_"+toBatchName+".html";
		HagUtil.replaceStringInFile(debugHtmlFile, "Process", "Process:");
		if(prc8 == null || prc8.length==0) {
			return HagUtil.shortHtml("No action selected.", "red","bg");
		}
		HagUtil.replaceStringInFile(debugHtmlFile, "Estimated time", "Estimated time:");
		boolean continueFlag = true;
		String stopTomcat			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String stopEmergeListener	="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String deleteIom			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String replaceIom			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String deleteIomCust			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String replaceIomCust			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String getDdc				="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String loadDdc				="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String replaceWar			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String runMigMng			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String startEmergeListener	="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		String startTomcat			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
		HagUtil.replaceStringInFile(debugHtmlFile, "Started at", "Started at:");

		//String[] timeReportStart = {".",".",".",".",".",".",".",".",".","."};
		//String[] timeReportEnd = {".",".",".",".",".",".",".",".",".","."};
		
		//stopTomcat
		HagUtil.updateTimeStamp(debugHtmlFile,"stopTomcat_START","");
		stopTomcat= HagUtil.stopTomcat(toAPP, toBatchName);
		HagUtil.updateTimeStamp(debugHtmlFile,"stopTomcat_END",toAPP+"("+toBatchName+")  "+stopTomcat);
		HagUtil.writeStringToFile(debugFolder+"\\stopTomcat.txt",stopTomcat);
		if(stopTomcat.startsWith("<td bgColor=\"#FF0000\">"))
			continueFlag=false;

		//stopEmergeListener
		HagUtil.updateTimeStamp(debugHtmlFile,"stopEmergeListener_START","");
		stopEmergeListener= HagUtil.stopEmergeListener(toAPP, toBatchName);
		HagUtil.updateTimeStamp(debugHtmlFile,"stopEmergeListener_END",toAPP+"("+toBatchName+")  "+stopEmergeListener);
		HagUtil.writeStringToFile(debugFolder+"\\stopEmergeListener.txt",stopEmergeListener);
		if(stopEmergeListener.startsWith("<td bgColor=\"#FF0000\">"))
			continueFlag=false;
		
	
		if(continueFlag && Arrays.asList(prc8).contains("Replace IOM folder")) {
			//delete iom
			HagUtil.updateTimeStamp(debugHtmlFile,"deleteIom_START","");
			HagRc hagRcDelIom = new HagRc();
			deleteIom = replacePrivateDb_deleteIom(toAPP,toBatchName,toIomNfs,hagRcDelIom);
			HagUtil.updateTimeStamp(debugHtmlFile,"deleteIom_END",deleteIom);
			HagUtil.writeStringToFile(debugFolder+"\\deleteIom.txt",hagRcDelIom.log.convertToString("\n"));
			if(deleteIom.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
			
			
			//replace iom
			HagRc hagRcCopyIom = new HagRc();
			HagUtil.updateTimeStamp(debugHtmlFile,"replaceIom_START","");
			replaceIom = replacePrivateDb_replaceIom(toAPP,toBatchName,devIomNfs,hagRcCopyIom);
			HagUtil.updateTimeStamp(debugHtmlFile,"replaceIom_END",replaceIom);
			HagUtil.writeStringToFile(debugFolder+"\\replaceIom.txt",hagRcCopyIom.log.convertToString("\n"));
			if(replaceIom.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
			
		}
		if(continueFlag && Arrays.asList(prc8).contains("Replace IOM_Cust folder")) {
			//delete iom cust
			HagUtil.updateTimeStamp(debugHtmlFile,"deleteIomCust_START","");
			HagRc hagRcDelIom = new HagRc();
			deleteIomCust = replacePrivateDb_deleteIomCust(toAPP,toBatchName,hagRcDelIom);
			HagUtil.updateTimeStamp(debugHtmlFile,"deleteIomCust_END",deleteIomCust);
			HagUtil.writeStringToFile(debugFolder+"\\deleteIomCust.txt",hagRcDelIom.log.convertToString("\n"));
			if(deleteIomCust.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
			
			
			//replace iom cust
			HagRc hagRcCopyIomCust = new HagRc();
			HagUtil.updateTimeStamp(debugHtmlFile,"replaceIomCust_START","");
			replaceIomCust = replacePrivateDb_replaceIomCust(toAPP,toBatchName,devIomNfs,hagRcCopyIomCust);
			HagUtil.updateTimeStamp(debugHtmlFile,"replaceIomCust_END",replaceIomCust);
			HagUtil.writeStringToFile(debugFolder+"\\replaceIomCust.txt",hagRcCopyIomCust.log.convertToString("\n"));
			if(replaceIom.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
			
		}
		if(continueFlag && Arrays.asList(prc8).contains("Replace DDC splits")) {
			//get ddc bcp
			HagRc hagRcGetDdc = new HagRc();
			HagUtil.updateTimeStamp(debugHtmlFile,"getDdc_START","");
			getDdc = getreplacePrivateDb_getDDC(devDb,devSqlServer,debugFolder,hagRcGetDdc);
			HagUtil.updateTimeStamp(debugHtmlFile,"getDdc_END",getDdc);
			HagUtil.writeStringToFile(debugFolder+"\\getDdc.txt",hagRcGetDdc.log.convertToString("\n"));
			if(getDdc.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
			
			//load ddc bcp
			HagRc hagRcLoadDdc = new HagRc();
			HagUtil.updateTimeStamp(debugHtmlFile,"loadDdc_START","");
			loadDdc = getreplacePrivateDb_loadDDC(toBatchName,toSQL,debugFolder,hagRcLoadDdc);
			HagUtil.updateTimeStamp(debugHtmlFile,"loadDdc_END",loadDdc);
			HagUtil.writeStringToFile(debugFolder+"\\loadDdc.txt",hagRcLoadDdc.log.convertToString("\n"));
			if(loadDdc.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
		}
		if(continueFlag && Arrays.asList(prc8).contains("Replace war")) {
			//replace war
			HagRc hagRcReplaceWar = new HagRc();
			HagUtil.updateTimeStamp(debugHtmlFile,"replaceWar_START","");
			replaceWar= replacePrivateDb_replaceWar(toBatchName,toAPP,hagRcReplaceWar,toPack);
			HagUtil.updateTimeStamp(debugHtmlFile,"replaceWar_END",toAPP+"("+toBatchName+")  "+replaceWar);
			HagUtil.writeStringToFile(debugFolder+"\\replaceWar.txt",hagRcReplaceWar.log.convertToString("\n"));
			if(replaceWar.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
		}
		if(continueFlag && Arrays.asList(prc8).contains("Run mig-mng")) {
			//run mig-mng
			HagRc hagRcRunMigMng = new HagRc();
			HagUtil.updateTimeStamp(debugHtmlFile,"runMigMng_START","");
			runMigMng= replacePrivateDb_runMigMng(toBatchName,toAPP,hagRcRunMigMng,ver,debugBase,debugHtmlFile,toPack);
			HagUtil.updateTimeStamp(debugHtmlFile,"runMigMng_END",toAPP+"("+toBatchName+")  "+runMigMng);
			HagUtil.writeStringToFile(debugFolder+"\\runMigMng.txt",hagRcRunMigMng.log.convertToString("\n"));
			if(runMigMng.startsWith("<td bgColor=\"#FF0000\">"))
				continueFlag=false;
		}
			
		//startEmergeListener1
		HagUtil.updateTimeStamp(debugHtmlFile,"startEmergeListener_START","");
		startEmergeListener= HagUtil.startEmergeListener1(toAPP, toBatchName);
		HagUtil.updateTimeStamp(debugHtmlFile,"startEmergeListener_END",startEmergeListener);
		HagUtil.writeStringToFile(debugFolder+"\\startEmergeListener.txt",startEmergeListener);
		
		//startTomcat
		HagUtil.updateTimeStamp(debugHtmlFile,"startTomcat_START","");
		startTomcat= HagUtil.startTomcat(toAPP, toBatchName,replaceWar);
		HagUtil.updateTimeStamp(debugHtmlFile,"startTomcat_END",startTomcat);
		HagUtil.writeStringToFile(debugFolder+"\\startTomcat.txt",startTomcat);	
			
				
				
		StringBuilder ans = new StringBuilder("<h3>Replace private environment</h3><table bgColor=\"#aaaaaa\" cellpadding=\"3\" border =\"1\" >");
		ans.append("<tr><td>Stop tomcat					</td>"+stopTomcat+"</tr>");
		ans.append("<tr><td>Stop eMerge listener		</td>"+stopEmergeListener+"</tr>");
		ans.append("<tr><td>Replace IOM					</td>"+replaceIom+"</tr>");
		ans.append("<tr><td>Replace IOM	cust				</td>"+replaceIomCust+"</tr>");
		ans.append("<tr><td>Get DDC bcp files			</td>"+getDdc+"</tr>");
		ans.append("<tr><td>Load DDC bcp files			</td>"+loadDdc+"</tr>");
		ans.append("<tr><td>Replace War					</td>"+replaceWar+"</tr>");
		ans.append("<tr><td>Run mig-mng					</td>"+runMigMng+"</tr>");
		ans.append("<tr><td>Start eMerge listener		</td>"+startEmergeListener+"</tr>");
		ans.append("<tr><td>Start tomcat				</td>"+startTomcat+"</tr>");
		ans.append("<tr><td>Start iWay					</td><td><a href=\"http://"+toAPP+"/sapweb/Admin/Logon.htm?"+toAPP+"&/sapweb&"+toAPP+"-"+toBatchName+"\">http://"+toAPP+"/sapweb/Admin/Logon.htm?"+toAPP+"&/sapweb&"+toAPP+"-"+toBatchName+"</a></td></tr>");
		ans.append("</table>");
		
				
		//String linkToDebug 	= "<a href=\"file:///ri-archive/Saptech-Archive/RI/debug/private_envs\">link to debug</a>";
		String ans1		= HagUtil.sendMail_hag(HagUtil.cfgTeamMail,user+"@sapiens.com","david.ha@sapiens.com;gonen.s@sapiens.com","Append DEV elements to my private env "+devAppServer+"("+devDb+") to "+toAPP+"("+toBatchName+") by "+user,ans.toString()); 
		return ans.toString();

	}
	*/
}
