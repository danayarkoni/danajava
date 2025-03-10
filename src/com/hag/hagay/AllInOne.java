package com.hag.hagay;

import java.io.File;

public final class  AllInOne {
	public static  String 	before(HagStringList cbEnvs){
		HagStringList ans = new HagStringList();
		if(cbEnvs.size()>1)
			return HagUtil.shortHtml("only one env (checkBox) can be selected for this option", "red","bg");
		String temp = cbEnvs.get(0);
		String bis_server = HagUtil.getWord0(temp,"~",1,true);
		String batchName = HagUtil.getWord0(temp,"~",2,true);
		String sql_server = HagUtil.getWord0(temp,"~",10,true);
			
		HagStringList details = getDetails( bis_server, batchName) ;
		HagRc hagRc=new HagRc();
		HagStringList list = HagServersInfo.runTaskManager(hagRc,bis_server);
		if(hagRc.rc!=0)
			return hagRc.log.get(0);
		String memTotalApp = HagServersInfo.runMemTotal(hagRc,bis_server);
		if(hagRc.rc!=0)
			return hagRc.log.get(0);
		String memFreeApp   = HagServersInfo.runMemFree(hagRc,bis_server);
		if(hagRc.rc!=0)
			return hagRc.log.get(0);
		String memTotalSql = HagServersInfo.runMemTotal(hagRc,sql_server);
		if(hagRc.rc!=0)
			return hagRc.log.get(0);
		String memFreeSql   = HagServersInfo.runMemFree(hagRc,sql_server);
		if(hagRc.rc!=0)
			return hagRc.log.get(0);
		String results = getInfo(details,list,memTotalApp,memFreeApp,memTotalSql,memFreeSql,cbEnvs);
	return results;
	}
	public static String 		getInfo(HagStringList details,HagStringList list,String memTotalApp,String memFreeApp,String memTotalSql,String memFreeSql,HagStringList cbEnvs) {
		String bis_server = HagUtil.getWord0(cbEnvs.get(0),"~",1,true);
		String batchName = HagUtil.getWord0(cbEnvs.get(0),"~",2,true);
		String sql_server = HagUtil.getWord0(cbEnvs.get(0),"~",10,true);
		
		HagStringList ans3 = new HagStringList();
		ans3.add("<h3>This screen will contain all the parameters which affects "+bis_server+"/"+batchName+" environment (under construction)</h3>");
	
		ans3.add(getScript());
	
		ans3.add("<table  id=\"myTableAll\"  bgColor=\"eeeeee\"  cellpadding=\"1\" cellspacing=\"4\" border=\"1\">");
		
		ans3.add("<tr >");
		ans3.add(getdetailsCell(details));
		ans3.add(getServersInfoCell(memTotalApp,memFreeApp,memTotalSql,memFreeSql,bis_server,sql_server));
		ans3.add(getTomcatsListCell(bis_server,list));
		ans3.add(getLinksListCell(cbEnvs));
		ans3.add("</tr>");
		
		ans3.add("</table>");
		return ans3.convertToString("");
	}
	
	
	public static HagStringList getDetails(String bis_server,String batchName) {
		StringBuilder stm =new StringBuilder("select * from dbo.ri_environments_new where  status='A'  and bis_server = '"+bis_server+"'  and batchName = '"+batchName+"' ");
		
		HagStringList ans1 = new HagStringList();
		String details= HagJdbc.selectVertical("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm.toString(),ans1);
		return ans1;
	}
	public static String 		getScript() {
		StringBuilder ans = new StringBuilder();
		ans.append("<script>");
		ans.append("function sortTable(col,type) {");
		ans.append("  var table, rows, switching, i, x, y, shouldSwitch;");
		ans.append("  table = document.getElementById(\"myTable\");");
		ans.append("  switching = true;");
		ans.append("  while (switching) {");
		ans.append("    switching = false;");
		ans.append("    rows = table.rows;");
		ans.append("    for (i = 2; i < (rows.length - 1); i++) {");
		ans.append("       shouldSwitch = false;");
		ans.append("       x = rows[i].getElementsByTagName(\"TD\")[col];");
		ans.append("       y = rows[i + 1].getElementsByTagName(\"TD\")[col];");
		ans.append("       if (type =='d') {");
		ans.append("      	 if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {");
		ans.append("       	  	shouldSwitch = true;");
		ans.append("         	break;");
		ans.append("       	 }");
		ans.append("       }else{");
		ans.append("      	 if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {");
		ans.append("       	  	shouldSwitch = true;");
		ans.append("         	break;");
		ans.append("       	 }");
		ans.append("       }");
		ans.append("    }");
		ans.append("    if (shouldSwitch) {");
		ans.append("       rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);");
		ans.append("       switching = true;");
		ans.append("    }");
		ans.append("  }");
		ans.append("}");
		ans.append("</script>");
		return ans.toString();
	}
	public static String 		getdetailsCell(HagStringList details) {
		HagStringList ans3 = new HagStringList();
		//ans3.add("<td  rowspan=2>");
		ans3.add("<td VALIGN = Top>");
		ans3.add("<h3>Env details from cfg table:</h3>");
		ans3.add("<p style=\"color:red\">Shows your environment details from cfgTeam table.</p>");
		for(int i = 0 ; i < details.size();i++) {
			ans3.add(details.get(i));
		}
		
		ans3.add("</td>");
		return ans3.convertToString("");
	}
	public static String 		getServersInfoCell(String memTotalApp,String memFreeApp,String memTotalSql,String memFreeSql,String bis_server,String sql_server) {
		HagStringList ans3 = new HagStringList();
		ans3.add("<td VALIGN = Top  >");
		ans3.add("<h3>Servers info:</h3>");
		ans3.add("<p style=\"color:red\">Shows your environment servers status.</p>");
		ans3.add("<table     bgColor=\"ffff99\"   cellpadding=\"4\" cellspacing=\"1\" border=\"1\">");
		ans3.add("<tr  bgColor=\"ffcc00\"><td>"+bis_server+"</td><td>APP server</td></tr>");
		ans3.add("<tr><td>Total MEM</td><td>"+memTotalApp+"</td></tr>");
		if(memFreeApp.indexOf(".")>-1 || memFreeApp.equals("0") || memFreeApp.equals("1")|| memFreeApp.equals("2"))
			ans3.add("<tr  bgColor=\"ff0000\"> <td>Free MEM</td><td>"+memFreeApp+"</td></tr>");
		else if(memFreeApp.equals("3"))
			ans3.add("<tr  bgColor=\"ff9999\"><td>Free MEM</td><td>"+memFreeApp+"</td></tr>");
		else
			ans3.add("<tr><td>Free MEM</td><td>"+memFreeApp+"</td></tr>");
		ans3.add("<tr><td>Total CPU</td><td>"+"future"+"</td></tr>");
		ans3.add("<tr><td>Used CPU</td><td>"+"future"+"</td></tr>");
		ans3.add("<tr><td>Total space disk C</td><td>"+"future"+"</td></tr>");
		ans3.add("<tr><td>Used space disk C</td><td>"+"future"+"</td></tr>");
		ans3.add("<tr><td>Total space disk D</td><td>"+"future"+"</td></tr>");
		ans3.add("<tr><td>Used space disk D</td><td>"+"future"+"</td></tr>");
		ans3.add("</table>");
		ans3.add("<br>");
		ans3.add("<table     bgColor=\"ffff99\"   cellpadding=\"4\" cellspacing=\"1\" border=\"1\">");
		ans3.add("<tr  bgColor=\"ffcc00\"><td>"+sql_server+"</td><td>SQL server</td></tr>");
		ans3.add("<tr><td>Total MEM</td><td>"+memTotalSql+"</td></tr>");
		if(memFreeSql.indexOf(".")>-1 || memFreeSql.equals("0") || memFreeSql.equals("1")|| memFreeSql.equals("2"))
			ans3.add("<tr  bgColor=\"ff0000\"> <td>Free MEM</td><td>"+memFreeSql+"</td></tr>");
		else if(memFreeSql.equals("3"))
			ans3.add("<tr  bgColor=\"ff9999\"><td>Free MEM</td><td>"+memFreeSql+"</td></tr>");
		else
			ans3.add("<tr><td>Free MEM</td><td>"+memFreeSql+"</td></tr>");
		ans3.add("<tr><td>Total CPU</td><td>"+"future"+"</td></tr>");
		ans3.add("<tr><td>Used CPU</td><td>"+"future"+"</td></tr>");
		ans3.add("<tr><td>Total space disk C</td><td>"+"future"+"</td></tr>");
		ans3.add("<tr><td>Used space disk C</td><td>"+"future"+"</td></tr>");
		ans3.add("<tr><td>Total space disk D</td><td>"+"future"+"</td></tr>");
		ans3.add("<tr><td>Used space disk D</td><td>"+"future"+"</td></tr>");
		ans3.add("</table>");
	
		
	

		ans3.add("</td>");
	
		return ans3.convertToString("");
	}
	public static String 		getLinksListCell(HagStringList cbEnvs) {
		String bis_server = HagUtil.getWord0(cbEnvs.get(0),"~",1,true);
		String batchName = HagUtil.getWord0(cbEnvs.get(0),"~",2,true);
		HagStringList ans3 = new HagStringList();
		ans3.add("<td VALIGN = Top  >");
		ans3.add("<h3>Useful links:</h3>");
		ans3.add("<p style=\"color:red\">Links to useful logs and lists.</p>");
		ans3.add("<table     bgColor=\"ffd8b1\"   cellpadding=\"4\" cellspacing=\"1\" border=\"1\">");
		ans3.add("<tr><td>tomcat_logs in RIJAVA folder</td><td><a href=\"file://///"+bis_server+"/RI_JAVA/RIjava_"+batchName+"/tomcat/logs/\">catalina log</a></td></tr>");
		ans3.add("<tr><td>tomcat_logs in RISHARE folder</td><td><a href=\"file://///"+bis_server+"/RI_SHARE/"+batchName+"/tomcatLogs/\">all log</a></td></tr>");
		ans3.add("<tr><td>i know what you did on my env</td><td>"+"future"+"</td></tr>");
		ans3.add("<tr><td>installations log on my env</td><td>"+"future"+"</td></tr>");
	
		ans3.add("</table>");
		ans3.add("<br>");
		ans3.add("<h3>Useful parameters:</h3>");
		ans3.add("<p style=\"color:red\">Useful env parameters.</p>");
		ans3.add("<table     bgColor=\"709A6324\"   cellpadding=\"4\" cellspacing=\"1\" border=\"1\">");
		ans3.add(getStatus1( cbEnvs));
		ans3.add("</table>");
		

		ans3.add("</td>");
	
		return ans3.convertToString("");
	}
	public static String 		getTomcatsListCell(String bis_server,HagStringList list) {
		HagStringList ans3 = new HagStringList();
		ans3.add("<td VALIGN = Top>");
		//ans3.add("<td  rowspan=2>");
		ans3.add("<h3>Server memory per tomcat:("+bis_server+")</h3>");
		ans3.add("<p style=\"color:red\">Shows tomcuts running on the same APP server.</p>");
		ans3.add("<table  id=\"myTable\"  bgColor=\"99ffcc\"  cellpadding=\"4\" cellspacing=\"1\" border=\"1\">");
		ans3.add("<tr  bgColor=\"009999\"><td>.</td>"
				+ "<td><img src=\"arrow_down.gif\" onclick=\"sortTable(1,'d')\"  width=\"25\" height=\"20\"> <img src=\"arrow_up.gif\"  onclick=\"sortTable(1,'u')\"  width=\"25\" height=\"20\"> </td>"
				+ "<td><img src=\"arrow_down.gif\" onclick=\"sortTable(2,'d')\"  width=\"25\" height=\"20\"> <img src=\"arrow_up.gif\"  onclick=\"sortTable(2,'u')\"  width=\"25\" height=\"20\"></td>"
				+ "</tr>");
	
		ans3.add("<tr bgColor=\"009999\" ><td>ProcessId</td><td>Mem Usage</td><td>env</td></tr>");
	
		for(int i = 0 ; i< list.size();i++) {
			String temp = list.get(i);
			String w1= HagUtil.getWord0(temp,"~",1,true);
			if(w1.trim().equals("java.exe")){
				String w0= HagUtil.getWord0(temp,"~",0,true);
				String w2= HagUtil.getWord0(temp,"~",2,true);
				String w3= HagUtil.getWord0(temp,"~",3,true);
				w3=w3.substring(0,w3.length()-6);
				int w3I = HagUtil.s2i(w3);
				String w4= HagUtil.getWord0(temp,"~",4,true);
				String  env=".";
				int pos = w4.indexOf("\\RIjava_");
				if(pos>-1) {
					env=w4.substring(pos+8,w4.indexOf("\\",pos+10));
				}
				if(w3I>4000)
					ans3.add("<tr bgColor=\"ff0000\"><td>"+w0+"</td><td>"+w3+"</td><td>"+env+"</td></tr>");
				else if(w3I>2000)
					ans3.add("<tr bgColor=\"ff9999\"><td>"+w0+"</td><td>"+w3+"</td><td>"+env+"</td></tr>");
				else if(w3I>1500)
					ans3.add("<tr bgColor=\"ffcccc\"><td>"+w0+"</td><td>"+w3+"</td><td>"+env+"</td></tr>");
				else
					ans3.add("<tr ><td>"+w0+"</td><td>"+w3+"</td><td>"+env+"</td></tr>");
					
				
			}
		}
		ans3.add("</table>");
		ans3.add("</td>");
	
		return ans3.convertToString("");
	}
	public static String 		getInstallationsCell(HagStringList cbEnvs){
		HagStringList ans3 = new HagStringList();
		//ans3.add("<td VALIGN = Top>");
		//ans3.add("<td  rowspan=2>");
		ans3.add("<td  VALIGN = Top colspan=3>");
		ans3.add("<h3>Installations log :</h3>");
		ans3.add("<p style=\"color:red\">Shows installation history from cmInstaller table.</p>");
		StringBuilder stm =new StringBuilder("select * from RI.cmInstaller order by instDate DESC,instTime DESC");
		StringBuilder ans =new StringBuilder();
		for(int i = 0;i<cbEnvs.size();i++){
			HagStringList ans1 = new HagStringList();
			String temp = cbEnvs.get(i);
			String database = HagUtil.getWord0(temp,"~",11,true);
			String sqlServer = HagUtil.getWord0(temp,"~",10,true);
			String rc= HagJdbc.selectSimple("SQL",sqlServer,"cfg","cfg09c",database,stm.toString(),ans1,true);

			ans.append("<table bgColor=\"#00ff00\"><tr><td>").append(sqlServer).append("-").append(database).append("</td></tr></table><br>");
			ans.append(ans1.convertToString(" ") );
			ans.append("<br><br>" );

		}
		ans3.add(ans.toString());
		ans3.add("</td>");
		return ans3.convertToString("");
	}
	public static String 		getLastLogCatalina(String bis_server,String batchName){
		HagStringList ans3 = new HagStringList();
		//ans3.add("<td VALIGN = Top>");
		//ans3.add("<td  rowspan=2>");
		ans3.add("<td  VALIGN = Top colspan=3>");
		ans3.add("<h3>tomcat log (catalina) :</h3>");
		ans3.add("<p style=\"color:red\">Shows last catalina log file.</p>");
		ans3.add("<iframe  id=\"iframe_id\" name=\"iframe_id\" src=\"file://///"+bis_server+"/RI_JAVA/RIjava_"+batchName+"/tomcatlogs/catalina.*.log\" height=\"850\" width=\"1500\"></iframe>");		
		ans3.add("</td>");
		return ans3.convertToString("");
	}
	public static String 		getLastLogAll(String bis_server,String batchName){
		HagStringList ans3 = new HagStringList();
		//ans3.add("<td VALIGN = Top>");
		//ans3.add("<td  rowspan=2>");
		ans3.add("<td  VALIGN = Top colspan=3>");
		ans3.add("<h3>tomcat log (all) :</h3>");
		ans3.add("<p style=\"color:red\">Shows last all log file.</p>");
		ans3.add("<iframe  id=\"iframe_id\" name=\"iframe_id\" src=\"file://///"+bis_server+"/RI_SHARE/"+batchName+"/tomcatlogs/all.log\" height=\"850\" width=\"1500\"></iframe>");		
		ans3.add("</td>");
		return ans3.convertToString("");
	}
	public static String 		getIKnowWhatYouDidCell(String bis_server,String batchName){
		HagStringList ans3 = new HagStringList();
		//ans3.add("<td VALIGN = Top>");
		ans3.add("<td  VALIGN = Top colspan=3>");
		ans3.add("<h3>I know what you did log :</h3>");
		ans3.add("<p style=\"color:red\">Shows i know what you did history on this environment.</p>");
		String[] filter = {"ALL","ALL","ALL","ALL","ALL",bis_server,batchName,"ALL","ALL","ALL"};

		String iKnowWhatYouDid = prcForm(filter ,-1);
		ans3.add(iKnowWhatYouDid);
		ans3.add("</td>");
		return ans3.convertToString("");
	}
	public static String 		prcForm(String[] filter ,int num){
		//HagStringList ans = new	HagStringList(); 
		String stm = "select TOP "+num+" * from dbo.RI_PRC  order by dateTime DESC";
    	if(num==-1)
			stm = "select * from dbo.RI_PRC  order by dateTime DESC";
    	
		HagStringList ans1= new HagStringList();
   	
		String rc= HagJdbc.selectShortPrcs("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans1,true,filter,num);
   
		if(rc.startsWith("0~")){
			String str = ans1.convertToString(" ");
			String ffT = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSort1T2.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSort1B2.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}
	}	
	public static String 		getStatus1(HagStringList cbEnvs){
			HagStringList ans=new HagStringList();
			String temp = cbEnvs.get(0);

			String lastGoodInst 	= HagUtil.getWord0(temp,"~",4,true);
			String falseInst="Failed";
			String falseInst1 	= HagUtil.getWord0(temp,"~",5,true);
			if(!falseInst1.equals(lastGoodInst))
				falseInst="OK";

			String bisServer 	= HagUtil.getWord0(temp,"~",1,true);
			String batchName 		= HagUtil.getWord0(temp,"~",2,true);
			String tomcatPort 		= HagUtil.getWord0(temp,"~",16,true);
			String tomcatAns = " <iframe src=\"http://"+bisServer+":"+tomcatPort+"/ri-web\" width=\"100%\" height=\"200\"></iframe> ";

			String tomcatStr = "http://"+bisServer+":"+tomcatPort+"/ri-web";

			String tomcatService = HagUtil.checkTomcatStatus( bisServer, batchName);
			String tomcatProcess = HagUtil.checkTomcatProcess( bisServer, batchName);
			String eMergeListener = HagUtil.checkEmergeListener( bisServer, batchName);
			
			ans.add("<tr><td>eMerge listener status</td>"+eMergeListener+"</tr>");
			ans.add("<tr><td>tomcat service status</td>"+tomcatService+"</tr>");
			ans.add("<tr><td>tomcat process status</td>"+tomcatProcess+"</tr>");
			ans.add("<tr><td>tomcat line</td><td>"+tomcatStr+"</td></tr>");
			
			String[] init = getTomcatStartupBat(bisServer,batchName);
			if(init==null) {
				ans.add("<tr><td bgcolor=\"ff0000\">tomcat mem xmx</td><td>Failed to read startup.bat file</td></tr>");
				ans.add("<tr><td bgcolor=\"ff0000\">tomcat mem xms</td><td>Failed to read startup.bat file</td></tr>");
				ans.add("<tr><td bgcolor=\"ff0000\">tomcat mem maxpermsize</td><td>Failed to read startup.bat file</td></tr>");
				ans.add("<tr><td bgcolor=\"ff0000\">tomcat mem permsizeline</td><td>Failed to read startup.bat file</td></tr>");
				ans.add("<tr><td bgcolor=\"ff0000\">tomcat opt/debug</td><td>Failed to read startup.bat file</td></tr>");
			
			}else {
			
				ans.add("<tr><td>tomcat mem xmx</td><td>"+init[0]+"</td></tr>");
				ans.add("<tr><td>tomcat mem xms</td><td>"+init[1]+"</td></tr>");
				ans.add("<tr><td>tomcat mem maxpermsize</td><td>"+init[2]+"</td></tr>");
				ans.add("<tr><td>tomcat mem permsizeline</td><td>"+init[3]+"</td></tr>");
				ans.add("<tr><td>tomcat opt/debug</td><td>"+init[4]+"</td></tr>");
			}
		
	
		
		return ans.convertToString("");
	}
	public static String[] 		getTomcatStartupBat(String sourceAPP,String sourceBN){
		StringBuilder ans = new StringBuilder();
		String startupBatFileS = "\\\\"+sourceAPP+"\\ri_java\\RIjava_"+sourceBN+"\\RILSService\\startUp.bat"; 
		File startupBatFile = new File(startupBatFileS);
		if(!startupBatFile.exists()) {
			return null;
		}
		HagStringList list = new HagStringList(startupBatFileS,false,"asdasdasdsa",false);
		String[] init = {"INIT","INIT","INIT","INIT","INIT"};
		for(int k=0;k<list.size();k++) {
			String temp = list.get(k);
			if(temp !=null) {
				temp=temp.toLowerCase().trim();
			}else {
				continue;
			}
			if(temp.toLowerCase().trim().startsWith("set")) {
				int pos1 = temp.indexOf("-xmx");
				int pos2 = temp.indexOf("-xms");
				int pos3 = temp.indexOf("maxpermsize=");
				int pos4 = temp.indexOf("permsize=");
					
				int tmp1 = pos1+4;
				int tmp2 = temp.indexOf("m",pos1+4);
				init[0]=temp.substring(tmp1,tmp2);
					
				tmp1 = pos2+4;
				tmp2 = temp.indexOf("m",pos2+4);
				init[1]=temp.substring(tmp1,tmp2);

				tmp1 = pos3+12;
				tmp2 = temp.indexOf("m",pos3+12);
				init[2]=temp.substring(tmp1,tmp2);

				tmp1 = pos4+9;
				tmp2 = temp.indexOf("m",pos4+9);
				init[3]=temp.substring(tmp1,tmp2);
			}
			if(temp.toLowerCase().trim().startsWith("call")) {
				init[4]=temp.substring(5,temp.length());
			}
		
			
		}	
		return init;
	}
}
