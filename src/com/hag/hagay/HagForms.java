package com.hag.hagay;




import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public final class HagForms {
/*

	public static final String envsNewForm(){
		HagStringList ans = new	HagStringList(); 
    	String stm = "select project,bis_server,db,party,lastGoodInst,lastInst,version,patch,env,sql_server,emerge_port,connection_port,server_port,debug_port,note  from dbo.ri_environments_new where project <> 'HAGWIDTH'";
		String rc= HagJdbc.selectShortEnvs("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans,true,null);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSort1T1.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSort1B1.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}
	}
	*/
	public static final String hatPackRequest(){
		HagStringList codeHagPackReq1 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\code\\HatPackReq1.html",false,"xxssss",false);
		HagStringList codeHagPackReq2 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\code\\HatPackReq2.html",false,"xxssss",false);
		String codeHagPackReq1S = codeHagPackReq1.convertToString("\n");
		String codeHagPackReq2S = codeHagPackReq2.convertToString("\n");
		
		HagStringList list = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\customers.txt",false,"xxssss",false);
		HagStringList list1=new HagStringList();
		HagStringList list2=new HagStringList();
		for(int i = 1 ; i < list.size();i++){
			String temp = list.get(i);
			String w1 = temp.substring(0,temp.indexOf("("));
			String w2 = temp.substring(temp.indexOf("(")+1,temp.indexOf(")"));
			list1.add(w1);
			list2.add(w2);
		}
		StringBuilder ans = new	StringBuilder();
		ans.append("<html><head>\n<style type=\"text/css\">\n");
		for(int i = 0 ; i < list1.size();i++){
			ans.append("#hagLevel1_").append(list1.get(i)).append(" {visibility:hidden} \n");
			ans.append("#hagLevel2a_").append(list1.get(i)).append(" {visibility:hidden} \n");
			ans.append("#hagLevel2b_").append(list1.get(i)).append(" {visibility:hidden} \n");
			ans.append("#hagLevel3_").append(list1.get(i)).append(" {visibility:hidden} \n");
		}
		ans.append("</style>\n");
		ans.append(codeHagPackReq1S);
		ans.append("</head><body>\n<form><table border=2  cellpadding=\"10\">\n");
		ans.append("<tr bgColor =#cccccc><td>Customer</td><td>New/Existing</td><td>Actions</td><td>Details</td></tr>\n");
		for(int i =0;i<list1.size();i++){
			String str1= list1.get(i);
			String str2= list2.get(i);
			ans.append("\n<!--").append(str2).append("-->");
			String codeHagPackReq2S1=HagUtil.replaceStr(codeHagPackReq2S, "{param1}", str1);
			if(str1.equals("AXA") || str1.equals("LB") ||  str1.equals("MNR"))
				codeHagPackReq2S1=HagUtil.replaceStr(codeHagPackReq2S1, "{param2}", str2+" (AS400)");
			else
				codeHagPackReq2S1=HagUtil.replaceStr(codeHagPackReq2S1, "{param2}", str2);
			ans.append(codeHagPackReq2S1);
		}
		ans.append("</table></form></body></html>"); 
		return ans.toString();
	}
	
	public static final String riEnvsForm(String cfgMenuWebUser,String cfgMenuWebPass){
		HagStringList ans = new	HagStringList(); 
    	String stm = "select project," +
    				"bis_server," +
    				"batchName," +
    				"party," +
    				"lastInst," +
    				"lastGoodInst," +
    				"version," +
    				"patch," +
    				"oded," +
    				"env," +
    				"sql_server," +
    				"db," +
    				"owner," +
        			"iWayServer," +
    				"iWayLink," +
    				"Last_Installed_stamp," +
    				"emerge_port," +
    				"connection_port," +
    				"server_port," +
    				"note, " +
    				"locks, " +
    				"sql_server_version "+
    				" from dbo.ri_environments_new where project <> 'HAGWIDTH' and status='A' order by bis_server,batchName" ;
		String rc= HagJdbc.selectRiEnvs("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans,true,null,cfgMenuWebUser,cfgMenuWebPass);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSortT1.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSortB1.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}
	}
	public static final String riEnvsFormAzure(String cfgMenuWebUser,String cfgMenuWebPass){
		HagStringList ans = new	HagStringList();
		
    	String stm = "select project," +
    				"bis_server," +
    				"batchName," +
    				"party," +
    				"lastInst," +
    				"lastGoodInst," +
    				"version," +
    				"patch," +
    				"oded," +
    				"env," +
    				"sql_server," +
    				"db," +
    				"owner," +
        			"iWayServer," +
    				"iWayLink," +
    				"emerge_port," +
    				"connection_port," +
    				"server_port," +
    				"debug_port," +
    				"note, " +
    				"locks " +
    				" from dbo.ri_environments_EXT where project <> 'HAGWIDTH' and status='A' order by bis_server,batchName" ;
    	
    	/*
		String stm = "select row1.project," +
				"RTRIM(LTRIM(row1.bis_server))+'-'+RTRIM(LTRIM(srvr1.servername)), "+
				"batchName," +
				"party," +
				"lastInst," +
				"lastGoodInst," +
				"version," +
				"patch," +
				"oded," +
				"env," +
				"RTRIM(LTRIM(row1.sql_server))+'-'+RTRIM(LTRIM(srvr2.servername)), "+ 
				"db," +
				"owner," +
    			"iWayServer," +
				"iWayLink," +
				"emerge_port," +
				"connection_port," +
				"server_port," +
				"debug_port," +
				"note, " +
				"locks " +
				"  FROM [prod_cfg].[dbo].[ri_environments_EXT] row1 "+
				"  INNER JOIN  [dbo].[ri_servers] srvr1 "+
			    "  ON row1.bis_server = srvr1.serverID "+	 
				"  INNER JOIN  [dbo].[ri_servers] srvr2 "+
				" ON row1.sql_server = srvr2.serverID "	 +
				" where row1.project <> 'HAGWIDTH' and status='A' order by bis_server,batchName";
					 

    	
    	*/
		 
		 
    	
    	
		String rc= HagJdbc.selectRiEnvsAzure("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans,true,null,cfgMenuWebUser,cfgMenuWebPass);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSortT1.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSortB1.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}
	}
	//spr1008del
	
	
	public static final String gardEnvsForm(String cfgMenuWebUser,String cfgMenuWebPass){
		HagStringList ans = new	HagStringList(); 
    	String stm = "select project," +
    				"envName," +
    				"batchName," +
    				"appIp," +
    				"appName," +
    				"appDisk," +
    				"sqlIp," +
    				"sqlName," +
    				"sqlDisk," +
    				"adminU," +
    				"ePort," +
    				"sPort," +
    				"cPort," +
    				"dPort," +
    				"sapWeb," +
    				"note " +
    				" from dbo.gard_cloud where project <> 'HAGWIDTH' ";
		String rc= HagJdbc.selectGardEnvs("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans,true,null,cfgMenuWebUser,cfgMenuWebPass);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSortT1.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSortB1.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}
	}
	public static final String riServersForm(String cfgMenuWebUser,String cfgMenuWebPass){
		HagStringList ans = new	HagStringList(); 
    	String stm = "select project," +
    				"serverName," +
    				"serverIp," +
    				"type," +
    				"note " +
    				" from dbo.ri_servers where project <> 'HAGWIDTH' ";
		String rc= HagJdbc.selectRiServers("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans,true,null,cfgMenuWebUser,cfgMenuWebPass);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSortT1.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSortB1.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}
	}
	public static final String migsListForm(String cfgMenuWebUser,String cfgMenuWebPass){
		HagStringList ans = new	HagStringList(); 
    	String stm = "select jira," +
    				"ver," +
    				"id," +
    				"jclass," +
    				"run_type," +
    				"pack2ver," +
    				"dev," +
    				"dt_dev," +
    				"cm," +
    				"dt_cm," +
    				"manager," +
    				"dt_manager," +
    				"location, " +
    				
    				"note " +
    				" from dbo.add_mig order by dt_dev desc";
		String rc= HagJdbc.selectMigsList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans,true,null,cfgMenuWebUser,cfgMenuWebPass);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSortT12.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSortB11.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}
	}
	public static final String deleteFromMigDetails3(){
		//disp history
		HagStringList ans3 = new	HagStringList(); 
		ans3.add("<h3>History:</h3>");
	 	ans3.add("<table  bgcolor=\"#D8D8D8\" border=\"1\"  CELLPADDING=\"3\"  style=\"width:100%\"><tr bgcolor=\"#848484\"><td >version</td> <td >jclass</td> <td>location</td> <td>customer</td> <td>owner</td><td>from date</td><td>status</td><td>relevant</td>  <td>note</td></tr>");
		String stm3 = "select version," +
	    				"jclass," +
	    				"location," +
	    				"customer," +
	    				"owner," +
	    				"date," +
	    				"status," +
	    				"relevant," +
	       				"note " +
	    				" from dbo.DeleteFromMigDetails order by date DESC";
    	HagSQL hagSQL = new HagSQL();
    	HagStringList results = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm3,9,"|",null,null);
		for(int j=0;j<results.size();j++){
			String temp1 = results.get(j);
			String temp2 = temp1.replace("|","</td><td>");
			String w7 = HagUtil.getWord0(temp1,"|",7,true);
			if(w7.equals("YES"))
				ans3.add("<tr bgcolor=\"#D8D8D8\"><td>"+temp2+"</td></tr>");
			else
				ans3.add("<tr bgcolor=\"#F5A9E1\" ><td>"+temp2+"</td></tr>");
		}				
		ans3.add("</table>");
		String str3 = ans3.convertToString(" ");
		return str3;
				
	}
	public static final String deleteFromMigDetails2(String cfgMenuWebUser,String cfgMenuWebPass){
		//change existing
		
		HagStringList ans2 = new	HagStringList(); 
		ans2.add("<h3>Remove mig from list:</h3>");
		ans2.add("<table bgColor=\"D8D8D8\" id=\"table2\" cellpadding=\"1\" cellspacing=\"1\" border=\"1\" width = \"100%\">");
	
		  	String stm2 = "select relevant,"+
		  				"version," +
		  				"jclass," +
		  				"location," +
		  				"customer," +
		  				"owner," +
		  				"date," +
		  				"status," +
		     				"note " +
		  				" from dbo.DeleteFromMigDetails where relevant='YES' AND status ='ON' order by date";
		String rc= HagJdbc.selectDeleteFromMigDetailsList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm2,ans2,true,null,cfgMenuWebUser,cfgMenuWebPass);
		if(rc.startsWith("0~")){
			ans2.add("</table><br><INPUT TYPE=SUBMIT value=\"Delete this mig from the DeleteFromMigDetails table\" style=\"background-color:LightCoral\"    ></FORM>");
			String str2 = ans2.convertToString(" ");
		//	String ffT1 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\htmls\\tableSortT_deleteFromMigDetails1.html";
		//	HagStringList listT1 = new HagStringList(ffT1,false,"xxssss",false);
		//	String strT1 = listT1.convertToString("\n");
			//String ffT2 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\htmls\\tableSortT_deleteFromMigDetails2.html";
		//		HagStringList listT2 = new HagStringList(ffT2,false,"xxssss",false);
		//	strT2 = listT2.convertToString("\n");
		return str2;			
					
		}
		else {
			return "ERR";
		}
				
	}
	public static final String deleteFromMigDetails1(String cfgMenuWebUser,String cfgMenuWebPass){
		//add new
		HagSQL hagSQL = new HagSQL();
		String stm = "select CUSTOMER_CODE,PARTY_ID from dbo.RICUSTOMER order by CUSTOMER_CODE ";
		HagStringList customers = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,2,"|",null,null);
		customers.set(0, "ALL|ALL");
		
		
		HagStringList ans1 = new	HagStringList(); 
		String deleteFromMigDetailsVersionsFile = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\deleteFromMigDetailsVersions.txt";
		HagStringList deleteFromMigDetailsVersionsList= new HagStringList(deleteFromMigDetailsVersionsFile,true,"*",false);
		
		ans1.add("<h3>Add new mig to the list:</h3>");
	
		ans1.add("<table bgColor=\"D8D8D8\" id=\"table2\" cellpadding=\"1\" cellspacing=\"1\" border=\"1\" >");
		ans1.add("<FORM METHOD=POST name=\"Form\" ACTION=\"deleteFromMigDetails_insert.jsp\">");
		ans1.add("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+cfgMenuWebPass+"\" >");
		ans1.add("<tr bgColor=\"848484\"><td>ver</td><td>mig</td><td>for customer</td><td>where to run</td><td>who am i</td><td>Short note to describe this mig</td></tr>");
	
		ans1.add("<tr>");

		ans1.add("<td>");
		ans1.add("<select   id=\"ver\" name =\"ver\" >");
		for(int i=0;i<deleteFromMigDetailsVersionsList.size();i++) {
			String w0 = HagUtil.getWord0(deleteFromMigDetailsVersionsList.get(i), "|",0,true); 
			ans1.add("<option  value=\"" + deleteFromMigDetailsVersionsList.get(i)+"\">"+w0+"</option>");
		}
		ans1.add("</select>");
		ans1.add("</td>");
		
		ans1.add("<td>");
		ans1.add("<input type=\"text\" name=\"mig\" id=\"mig\"  maxlength=\"20\" size=\"20\">");
		ans1.add("</td>");

		ans1.add("<td>");
		ans1.add("<select   id=\"customer\" name =\"customer\" >");
		for(int i=0;i<customers.size();i++) {//ALL or party-id
			String w0 = HagUtil.getWord0(customers.get(i), "|",0,true); 
			String w1 = HagUtil.getWord0(customers.get(i), "|",1,true); 
			ans1.add("<option  value=\"" + w1+"\">"+w0+"</option>");
		}
		ans1.add("</select>");
		ans1.add("</td>");
		
		ans1.add("<td>");
		ans1.add("<select   id=\"location\" name =\"location\" >");  //LOCAL,NOT-LOCAL or ALL
		ans1.add("<option  value=\"LOCAL\">LOCAL</option>");
		ans1.add("<option  value=\"NOT-LOCAL\">NOT-LOCAL</option>");
		ans1.add("<option  value=\"ALL\">ALL</option>");
		ans1.add("</select>");
		ans1.add("</td>");
		
		
		ans1.add("<td  bgColor=\"ffffff\">");
		ans1.add("<input type=\"text\" readonly=\"readonly\" name=\"sentBy\" id=\"sentBy\"  value = "+cfgMenuWebUser+" maxlength=\"30\" size=\"30\">");
		ans1.add("</td>");

		ans1.add("<td>");
		ans1.add("<input type=\"text\" name=\"note\" id=\"note\"  maxlength=\"50\" size=\"140\">");
		ans1.add("</td>");

		
		ans1.add("</tr>");
		ans1.add("</table><br>");
		ans1.add("<INPUT TYPE=SUBMIT value=\"Insert this mig to the DeleteFromMigDetails table\" style=\"background-color:LightGreen\" ></FORM>");
		
//		ans1.append("<body bgColor=\"#cccccc\" >");
	//	ans1.append("<u><h3>Run SVN merge and Jenkins build</h3></u>");
	//	ans1.append("<FORM METHOD=POST name=\"Form\" ACTION=\"mergeAndBuild.jsp\">");
//		ans1.append("<br><br>");
//		ans1.append("<h4>select version:</h4>");

		//ans1.append("<select  class=\"c30\" id=\"ver\" name =\"ver\" >");
		//for(int i=0;i<versionsList.size();i++) {
		//	String w1 = HagUtil.getWord0(versionsList.get(i), "|",0,true); 
		//	ans.append("<option  value=\"").append(versionsList.get(i)).append("\">").append(w1).append("</option>");
	//	}
	//	ans1.append("</select></td>");
	//	ans1.append("<br><br><br>");
	//	ans1.append("<h4>select action:</h4>");
	//	ans1.add("<table bgcolor=\"#aaaacc\" cellpadding=\"5\" border =\"1\">");
	//	ans1.add("</table>");
		String str1 = ans1.convertToString(" ");
	
		return str1;	
				
	}
	public static final String deleteFromMigDetails(String cfgMenuWebUser,String cfgMenuWebPass){
		String ffT1 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\htmls\\tableSortT_deleteFromMigDetails1.html";
		HagStringList listT1 = new HagStringList(ffT1,false,"xxssss",false);
		String strT1 = listT1.convertToString("\n");
	
		
		String str1= deleteFromMigDetails1(cfgMenuWebUser,cfgMenuWebPass);
		String str2= deleteFromMigDetails2(cfgMenuWebUser, cfgMenuWebPass);
		String str3= deleteFromMigDetails3();
		StringBuilder aaa = new StringBuilder();
		aaa.append(strT1);

		aaa.append("<table bgColor=\"CEECF5\" cellpadding=\"10\" cellspacing=\"1\" border=\"2\"  style=\"width:100%\"><tr><td>");
		aaa.append(str1);
		aaa.append("</td></tr></table>");
		
		aaa.append("<br><br>");
		
		
		aaa.append("<table bgColor=\"F5A9BC\" cellpadding=\"10\" cellspacing=\"1\" border=\"2\"  style=\"width:100%\"><tr><td>");
		aaa.append(str2);
		aaa.append("</td></tr></table>");
		
		aaa.append("<br><br>");
		
		aaa.append("<table bgColor=\"C8FE2E\" cellpadding=\"10\" cellspacing=\"1\" border=\"2\"  style=\"width:100%\"><tr><td>");
		aaa.append(str3);
		aaa.append("</td></tr></table>");
		
		
		return aaa.toString();
		/*
		String strT1,str1,strT2,str2,str3,strB;
		//add new
		StringBuilder ans1 = new StringBuilder(); 
		ans1.append("<h3>Add new mig to the list:</h3>");
		ans1.append("<body bgColor=\"#cccccc\" >");
		ans1.append("<u><h3>Run SVN merge and Jenkins build</h3></u>");
		ans1.append("<FORM METHOD=POST name=\"Form\" ACTION=\"mergeAndBuild.jsp\">");
//		ans1.append("<br><br>");
//		ans1.append("<h4>select version:</h4>");

		//ans1.append("<select  class=\"c30\" id=\"ver\" name =\"ver\" >");
		//for(int i=0;i<versionsList.size();i++) {
		//	String w1 = HagUtil.getWord0(versionsList.get(i), "|",0,true); 
		//	ans.append("<option  value=\"").append(versionsList.get(i)).append("\">").append(w1).append("</option>");
	//	}
	//	ans1.append("</select></td>");
	//	ans1.append("<br><br><br>");
	//	ans1.append("<h4>select action:</h4>");
		ans1.append("<table bgcolor=\"#aaaacc\" cellpadding=\"5\" border =\"1\">");
		ans1.append("</form></table>");
		str1 = ans1.toString();
		
		//change existing
		HagStringList ans2 = new	HagStringList(); 
		ans2.add("<h3>Remove mig from list:</h3>");
    	String stm2 = "select relevant,"+
    				"version," +
    				"jclass," +
    				"location," +
    				"customer," +
    				"owner," +
    				"date," +
    				"status," +
       				"note " +
    				" from dbo.DeleteFromMigDetails order by date";
		String rc= HagJdbc.selectDeleteFromMigDetailsList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm2,ans2,true,null,cfgMenuWebUser,cfgMenuWebPass);
		if(rc.startsWith("0~")){
			ans2.add("</form></table>");
			str2 = ans2.convertToString(" ");
			String ffT1 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\htmls\\tableSortT_deleteFromMigDetails1.html";
			HagStringList listT1 = new HagStringList(ffT1,false,"xxssss",false);
			strT1 = listT1.convertToString("\n");
		//	String ffT2 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\htmls\\tableSortT_deleteFromMigDetails2.html";
	//		HagStringList listT2 = new HagStringList(ffT2,false,"xxssss",false);
		//	strT2 = listT2.convertToString("\n");
			
			
			
		//	String ffB = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\htmls\\tableSortB_deleteFromMigDetails.html";
		//	HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			//strB = listB.convertToString("\n");
	//		return strT1+str1+strT2+str2+strB;
		}else{
			return rc;
		}
		
		//disp history
		HagStringList ans3 = new	HagStringList(); 
		ans3.add("<br>History:<br>");
	 	ans3.add("<table  bgcolor=\"#82E0AA\" border=\"1\"  CELLPADDING=\"3\"><tr bgcolor=\"#27AE60\"><td >version</td> <td >jclass</td> <td>location</td> <td>customer</td> <td>owner</td><td>from date</td><td>status</td><td>relevant</td>  <td>note</td></tr>");
	    
		String stm3 = "select version," +
    				"jclass," +
    				"location," +
    				"customer," +
    				"owner," +
    				"date," +
    				"status," +
    				"relevant," +
       				"note " +
    				" from dbo.DeleteFromMigDetails order by date DESC";
    	HagSQL hagSQL = new HagSQL();
    	HagStringList results = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm3,9,"</td><td>",null,null);
		for(int j=0;j<results.size();j++){
			String temp1 = results.get(j);
			ans3.add("<tr><td>"+temp1+"</td></tr>");
		}				
		ans3.add("</table>");
		
		str3 = ans3.convertToString(" ");
		return strT1+str1+str2+str3+"</body>";
		*/
	}
	public static final String clientModuleMigsListForm(String cfgMenuWebUser,String cfgMenuWebPass){
		HagStringList ans = new	HagStringList(); 
    	String stm = "select jira," +
    				"ver," +
    				"id," +
    				"jclass," +
    				"run_type," +
    				"pack2ver," +
    				"dev," +
    				"dt_dev," +
    				"cm," +
    				"dt_cm," +
    				"manager," +
    				"dt_manager," +
    				"location, " +
    				
    				"note " +
    				" from dbo.add_mig_clientModule order by dt_dev desc";
		String rc= HagJdbc.selectClientModuleMigsList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans,true,null,cfgMenuWebUser,cfgMenuWebPass);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSortT11.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSortB11.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}
	}
	
	
	public static final String migsListForm1(String cfgMenuWebUser,String cfgMenuWebPass){
		HagStringList ans = new	HagStringList(); 
    	String stm = "select jira," +
    				"ver," +
    				"customer," +
    				"run_type," +
    				"pack2ver," +
    				"dev," +
    				"dt_dev," +
    				"cm," +
    				"dt_cm," +
    				"manager," +
    				"dt_manager," +
    				"location, " +
    				"dist, " +
    				"jclass," +
    				"id," +
    				"note " +
    				" from dbo.add_mig order by dt_dev desc";
		String rc= HagJdbc.selectMigsList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans,true,null,cfgMenuWebUser,cfgMenuWebPass);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSortT11.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSortB11.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}
	}
	
	
	public static final String riEnvsDontMessForm(String cfgMenuWebUser,String cfgMenuWebPass){
		HagStringList ans = new	HagStringList(); 
    	String stm = "select project," +
    				"bis_server," +
    				"batchName," +
    				"party," +
    				"lastInst," +
    				"lastGoodInst," +
    				"version," +
    				"patch," +
    				"oded," +
    				"env," +
    				"sql_server," +
    				"db," +
    				"locks," +
    				"locksDetails " +
    				" from dbo.ri_environments_new where project <> 'HAGWIDTH' and status='A'";
		String rc= HagJdbc.selectRiEnvsDontMess("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans,true,null,cfgMenuWebUser,cfgMenuWebPass);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSortT_dontMess.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSortB_dontMess.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}
	}
	//spr1008
	public static final String mergeAndBuildPre(String cfgMenuWebUser,String cfgMenuWebPass){
		String mergeAndBuildFile = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\mergeAndBuild.txt";
		String mergeAndBuildVersionsFile = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\mergeAndBuildVersions.txt";
		HagStringList actList= new HagStringList(mergeAndBuildFile,true,"*",false);
		HagStringList versionsList= new HagStringList(mergeAndBuildVersionsFile,true,"*",false);
		StringBuilder ans = new StringBuilder(); 
		
		ans.append("<body bgColor=\"#cccccc\" >");
		ans.append("<u><h3>Run SVN merge and Jenkins build</h3></u>");
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"mergeAndBuild.jsp\">");
		ans.append("<br><br>");
		ans.append("<h4>select version:</h4>");

		ans.append("<select  class=\"c30\" id=\"ver\" name =\"ver\" >");
		for(int i=0;i<versionsList.size();i++) {
			String w1 = HagUtil.getWord0(versionsList.get(i), "|",0,true); 
			ans.append("<option  value=\"").append(versionsList.get(i)).append("\">").append(w1).append("</option>");
		}ans.append("</select></td>");
		ans.append("<br><br><br>");
		ans.append("<h4>select action:</h4>");
		ans.append("<table bgcolor=\"#aaaacc\" cellpadding=\"5\" border =\"1\">");
		for(int i=0;i<actList.size();i++){
			String temp = actList.get(i);
			String w0 = HagUtil.getWord0(temp,"|",0,true);
			String w1 = HagUtil.getWord0(temp,"|",1,true);
			if(i==actList.size()-1) {
				ans.append("<tr><td><input type=\"checkbox\" name=\"mergeAndBuildSteps\" id=\"mergeAndBuildSteps\" value=\"").append(w1).append("\" >").append(w0).append("</td></tr>");
			}else {
				ans.append("<tr><td><input type=\"checkbox\" name=\"mergeAndBuildSteps\" id=\"mergeAndBuildSteps\" value=\"").append(w1).append("\" checked>").append(w0).append("</td></tr>");
			}
				
		}
		ans.append("</table><br>");
//		ans.append("&nbsp;&nbsp;<INPUT TYPE=SUBMIT value=\"Run\"></FORM></BODY>");
	//	Xans.append("<br><INPUT TYPE=SUBMIT value=\"Run\" onclick=\"document.getElementById('loader1').style.display = ''; this.style.display = 'none'\" ></FORM></BODY>");
		ans.append("</table><br><INPUT TYPE=SUBMIT value=\"Run\" onclick=\"this.disabled=true;this.value='Running, please wait...';this.form.submit();\"></form></body>");

		return ans.toString();
	}
	

	
	public static final String riEnvsI5osForm(String cfgMenuWebUser,String cfgMenuWebPass){
		HagStringList ans = new	HagStringList(); 
    	String stm = "select project," +
    				"server," +
    				"batchName," +
    				"customer," +
    				"version," +
    				"installed," +
    				"rc," +
    				"oded, " +
    				"eMergePort," +
    				"serverPort," +
    				"connPort," +
    				"iWayServer," +
    				"iWayLink," +
    				"note," +
    				"status " +
    				" from dbo.ri_environments_i5os where project <> 'HAGWIDTH' ";
		String rc= HagJdbc.selectRiEnvsI5os("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans,true,null,cfgMenuWebUser,cfgMenuWebPass);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSortT2.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSortB2.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}
	}
	
	public static final String prcForm(String[] filter ,int num){
		//HagStringList ans = new	HagStringList(); 
		String stm = "select TOP "+num+" * from dbo.RI_PRC  order by dateTime DESC";
    	if(num==-1)
			stm = "select * from dbo.RI_PRC  order by dateTime DESC";
    	
		HagStringList ans1= new HagStringList();
	//	 System.out.println("start1"+HagUtil.getDateTime("mm:ss:SSS"));
		String rc= HagJdbc.selectShortPrcs("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans1,true,filter,num);
		// System.out.println("end1"+HagUtil.getDateTime("mm:ss:SSS"));
		if(rc.startsWith("0~")){
		//	 System.out.println("a1"+HagUtil.getDateTime("mm:ss:SSS"));
			String str = ans1.convertToString(" ");
		//	 System.out.println("a2"+HagUtil.getDateTime("mm:ss:SSS"));
			String ffT = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSort1T2.html";
			// System.out.println("a3"+HagUtil.getDateTime("mm:ss:SSS"));
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			// System.out.println("a4"+HagUtil.getDateTime("mm:ss:SSS"));
			String strT = listT.convertToString("\n");
			// System.out.println("a5"+HagUtil.getDateTime("mm:ss:SSS"));
			String ffB = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSort1B2.html";
			// System.out.println("a6"+HagUtil.getDateTime("mm:ss:SSS"));
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			// System.out.println("a7"+HagUtil.getDateTime("mm:ss:SSS"));
			String strB = listB.convertToString("\n");
			// System.out.println("end2"+HagUtil.getDateTime("mm:ss:SSS"));
			return strT+str+strB;
	
		}else{
			return rc;
		}
		
	}
	//req9733
	public static final String prcFormNew(String[] filter ,int num){
		HagRc hagRc = new HagRc();
	   	HagSQL hagSql = new HagSQL();
    	String rowCount1 = hagSql.selectCount(hagRc, "CONFG1", "cfg","cfg09c", HagParam.getConfg1DB(), "dbo.RI_PRC");
    //	System.out.println("rowCount1="+rowCount1);
    	int rowCount1i=HagUtil.s2i(rowCount1);
	//	String stm = "select TOP "+num+" * from dbo.RI_PRC  order by dateTime DESC";
    //	if(num==-1)
		String	stm = "select * from dbo.RI_PRC  order by dateTime DESC";
    	
		HagStringList ans1= new HagStringList();
		String rc= HagJdbc.selectShortPrcsNew("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans1,true,filter,0,1000,rowCount1i+1);
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
	public static final String tasksForm(String[] filter ,String table){
		String stm = "select VERSION,TASKNO,USERNAME,STATUS,OPENDATE,OPENTIME,CLOSEDATE,CLOSETIME from dbo."+table+" where VERSION<>'0000' order by OPENDATE DESC,OPENTIME DESC";
		if(table.endsWith("0000"))
			 stm = "select VERSION,TASKNO,USERNAME,STATUS,OPENDATE,OPENTIME,CLOSEDATE,CLOSETIME from dbo."+table+" order by OPENDATE DESC,OPENTIME DESC";
		
		HagStringList ans1= new HagStringList();
		String rc= HagJdbc.selectTasks("CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans1,true,filter,table);
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
	public static final String tomcatsStatus(String[] filter ){
		String stm = "select version,bis_server,DB,connection_port from dbo.ri_environments_new where status='A' and project <>'HAGWIDTH' order by DB ASC ";
    	HagStringList ans1= new HagStringList();
		String rc= HagJdbc.selectShortTomcats("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans1,true,filter);
		if(rc.startsWith("0~")){
			String str = ans1.convertToString(" ");
			String ffT = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSort2T2.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSort2B2.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}
	}
	
	public static final String iWayLinks(String[] filter ){
		String stm = "select appServer,dbName,riVersion,riMaint,location,customer,note,href from dbo.iWay where project <> 'HAGWIDTH' and (appServer='SCENIC' or appServer='TERANO' or appServer='PASSAT' or appServer='CLIO')  order by appServer ASC ";
    	HagStringList ans1= new HagStringList();
		String rc= HagJdbc.selectShortIway("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans1,true,filter);
		if(rc.startsWith("0~")){
			String str = ans1.convertToString(" ");
			String ffT = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSort2T3.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\tableSort2B2.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}
	}
	
	public static final String generalRequest(String user,String pass,String w1){
		String auth = HagUtil.isAuthorized(w1,user,pass);
		if(!auth.equals("OK")){
			return HagUtil.shortHtml(auth , "red", "bg");
		}
		StringBuilder ans = new StringBuilder("<html>");
		ans.append("<body bgColor=\"#cccccc\" >");
		ans.append("<u><h3>General request</h3></u>");
		ans.append("<br>");
		ans.append("<br>");
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"generalRequest.jsp\">");
		ans.append("<input type=\"text\" name=\"subject\" id=\"subject\" value = \"enter request subject\" maxlength=\"100\" size=\"100\">");
		ans.append("<br>");
		ans.append("<br>");
	
		ans.append("<textarea name=\"note\" id=\"note\" rows=8 cols=50>Please use this General request ONLY when other Requests do not match</textarea>"); 
		ans.append("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebUser\" value = \""+user+"\" >");
		ans.append("<br>");
		ans.append("<br>");
		ans.append("&nbsp;&nbsp;<INPUT TYPE=SUBMIT value=\"Run\"></FORM></BODY>");
		ans.append("</body></html>");
		return ans.toString();
	}
	
	public static final String requestsNew_betore1(String user,String opt){
		HagRequests request = new HagRequests();
		String ans=request.build(opt,user);
		return ans;
	}
	public static final String requestsNew_betoreaaaaaaaaaaaaaaa1(String user,String opt){
	//	String auth = HagUtil.isAuthorized(w1,user,pass);
		//if(!auth.equals("OK")){
		//	return HagUtil.shortHtml(auth , "red", "bg");
		//}
	
		
		StringBuilder ans = new StringBuilder("<html>");
		ans.append("<script  type=\"text/javascript\">");
		ans.append("function getComboA() {");
		ans.append("alert(\"zzzzzzzzzzzz\");");
		///ans.append("dd = document.getElementById(\"reqType\");");
		//ans.append("var d = dd.value;");
		//ans.append("alert(d);");
		//ans.append("var ee= document.getElementById(\"iframe_\"+d	);	  ");
		//ans.append("e = ee.value;");
		//ans.append("alert(e);");

		//ans.append("var doc = document.getElementById('reqIframe').contentWindow.document;");
		//ans.append("doc.open();");
		//ans.append("doc.write(ee.value);");
		//ans.append("doc.close();");
		   
	ans.append("}");
		ans.append("</script>");
		ans.append("<body bgColor=\"#cccccc\" >");
		ans.append("<u><h3>CM requests</h3><br></u>");
	
		ans.append("<select  onchange=\"getComboA()\" class=\"c30\" id=\"reqType\" name =\"reqType\"  >");
		
		HagSQL hagSQL = new HagSQL();
		String stm = "SELECT [Req_Type]"+
			      ",[Req_Desc]"+
			      ",[Req_Subject]"+
			      ",[Req_Note]"+
			      ",[Req_Platform]"+
			      ",[Req_Target_Env]"+
			      ",[Req_Target_Version]"+
			      ",[Req_Source_Env]"+
			      ",[Req_BackupFile]"+
			      ",[Req_BackupFile_SQL_Version]"+
			      ",[Req_Customer_Code]"+
			      ",[Req_Customer_Desc]"+
			      ",[Req_Customer_Party]"+
			      ",[Req_Subject_Template_Main]"+
			      ",[Req_User_Name]"+
			      ",[Req_User_Email]"+
			      ",[Req_Requester]"+
			      ",[Req_BackupFile_RI_Version]"+
			      ",[Req_Subject_Template_Sec]"+
			      ",[Req_FTP_Package_Name]"+
			      ",[Req_DBID]"+
			      ",[Req_Server]"+
			      ",[Req_Envs_To_Install] from dbo.Requests_Types order by Req_Type + 0";
	
		HagStringList results = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,23,"|",null,null);
		for(int j=0;j<results.size();j++){
			String temp1 = results.get(j);
			String w0a = HagUtil.getWord0(temp1,"|",0,true);
			String w1a = HagUtil.getWord0(temp1,"|",1,true);
			//ans.append("<option  value=\"").append("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\iframe\\iframe_"+w0a+".html").append("\">").append(w0a+"-"+w1a).append("</option>");
			ans.append("<option  value=\"").append(w0a).append("\">").append(w0a+"-"+w1a).append("</option>");
					
		}				
	//	HagStringList ver1= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riVersionsCmInstaller.txt",true,"*",false);
		//for(int i=0;i<ver1.size();i++)
		//	ans.append("<option  value=\"").append(ver1.get(i)).append("\">").append(ver1.get(i)).append("</option>");
		ans.append("</select>");
		ans.append("<br>select the request, fill the parameters and click the submit button");
		ans.append("<br><br>");
		
	//	StringBuilder ans = new StringBuilder("<html>");
	//	ans.append("<body bgColor=\"#cccccc\" >");
	//	ans.append("<u><h3>new request test</h3></u>");
		//ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"requestNew.jsp\">");
	//	ans.append("<table bgcolor=\"#aaaacc\" border=\"1\">");
		//ans.append("<tr><td><input type=\"text\" name=\"reqName\" id=\"reqName\"  ></td>");

	//	ans.append("</table>");
	
		//ans.append("&nbsp;&nbsp;<INPUT TYPE=SUBMIT value=\"send   request\"></FORM></BODY>");
		//String paramIframe = " <iframe  id=\"reqIframe\" name =\"reqIframe\" src=\"\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\htmls\\iframeSkel.html\" width=\"100%\" height=\"500\"></iframe> ";
	
		
		String paramIframe = " <iframe  id=\"reqIframe\" name =\"reqIframe\"  width=\"100%\" height=\"500\"></iframe> ";
		
		//srcdoc="
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\" style=\"width:96%\">");
		ans.append("<tr><td>"+paramIframe+"</td></tr></table>");
		
		//ans.append("<input type=\"hidden\" name=\"iframe_1\" id=\"iframe_init\" value = \"aaaa\" >");
		//ans.append("<input type=\"hidden\" name=\"iframe_10\" id=\"iframe_iniqt\" value = \"aaaabbb\" >");
		//HagRequests hagRequests = new HagRequests(); 
		//String[][] requests=hagRequests.getRequests();
		//for(int i = 0 ;i < requests.length;i++) {
		////	ans.append("<input type=\"hidden\" name=\"iframe_"+requests[i][0]+"\" id=\"iframe_init\" value = \""+requests[i][1]+"\" >");
			//String aa="aa";
		//}
			
			
			
//		String iframe_1="dfff</p>";
//		String iframe_10="ggg</p>";
//		ans.append("<input type=\"hidden\" name=\"iframe_init\" id=\"iframe_init\" value = \""+iframe_init+"\" >");
//		ans.append("<input type=\"hidden\" name=\"iframe_1\" id=\"iframe_1\" value = \""+iframe_1+"\" >");
//		ans.append("<input type=\"hidden\" name=\"iframe_10\" id=\"iframe_10\" value = \""+iframe_10+"\" >");
		return ans.toString();
	}
	
	public static final String addNewCustomer_before(String user,String pass,String w1){
		String auth = HagUtil.isAuthorized(w1,user,pass);
		if(!auth.equals("OK")){
			return HagUtil.shortHtml(auth , "red", "bg");
		}
		StringBuilder ans = new StringBuilder("<html>");
		ans.append("<body bgColor=\"#cccccc\" >");
		ans.append("<u><h3>Add new customer</h3></u>");
		ans.append("<br>Fill CUSTOMER_TITLE,CUSTOMER_CODE and PARTY_ID fields<br>");
		ans.append("(like in the existing customers table below)<br>");
		ans.append("and click <i><b>send  addNewCustomer request</i></b> button");
		ans.append("<br>");
		ans.append("<u><h4>new customer:</h4></u>");
		ans.append("<table bgcolor=\"#aaaacc\" border=\"1\">");
		ans.append("<tr bgColor=#737373><td>CUSTOMER_TITLE</td><td>CUSTOMER_CODE</td><td>PARTY_ID</td></tr>");
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"addNewCustomer.jsp\">");
		ans.append("<tr><td><input type=\"text\" name=\"longName\" id=\"longName\"  ></td>");
		ans.append("<td><input type=\"text\" name=\"shortName\" id=\"shortName\"  ></td>");
		ans.append("<td><input type=\"text\" name=\"partyId\" id=\"partyId\"  ></td></tr>");
		ans.append("</table>");
		ans.append("<br>");
		ans.append("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebUser\" value = \""+user+"\" >");
		ans.append("&nbsp;&nbsp;<INPUT TYPE=SUBMIT value=\"send  addNewCustomer request\"></FORM></BODY>");
		ans.append("<br>");
		ans.append("<u><h4>customers list:</h4></u>");
		ans.append("<table bgcolor=\"#aaaacc\" border=\"1\">");
		ans.append("<tr bgColor=#737373><td>CUSTOMER_TITLE</td><td>CUSTOMER_CODE</td><td>PARTY_ID</td></tr>");
		HagSQL hagSQL = new HagSQL();
		String stm = "select CUSTOMER_TITLE,CUSTOMER_CODE,PARTY_ID from dbo.RICUSTOMER order by PARTY_ID";
		HagStringList results = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,3,"|",null,null);
		for(int j=0;j<results.size();j++){
			String temp1 = results.get(j);
			String w0a = HagUtil.getWord0(temp1,"|",0,true);
			String w1a = HagUtil.getWord0(temp1,"|",1,true);
			String w2a = HagUtil.getWord0(temp1,"|",2,true);
			ans.append("<tr><td>"+w0a+"</td><td>"+w1a+"</td><td>"+w2a+"</td></tr>");
		}				
		ans.append("</table>");
	
	
		ans.append("</body></html>");
		return ans.toString();
	}

	


	public static final String registerForm(String w1,String user,String pass){
		//if(1==1)
		//	return w1+"<Br>"+user+"<Br>"+pass;
		String auth = HagUtil.isAuthorized(w1,user,pass);
		if(!auth.equals("OK")){
			return HagUtil.shortHtml(auth , "red", "bg");
		}
		if(w1.equals("changePassword"))
			return HagUtil.changePassword(user,pass);
		else if(w1.equals("authorizationsRequest"))
			return authorizationsRequest(user);
			return "wrong option";
	}
	public static final String tasksForm(String w2,String user,String pass){
		if(w2.startsWith("dsp!")){
			String table = HagUtil.getWord0(w2,"!",1,true);
			 return HagForms.tasksForm(null,table);
		}
		return HagUtil.shortHtml("future option" , "green", "bg");
/*
		if(w2.equals("try")){
			HagStringList ans = new HagStringList();
			ans.add("tasktable,VERSION,TASKNO,USERNAME,STATUS,OPENDATE,OPENTIME,CLOSEDATE,CLOSETIME");
			HagStringList list= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\versions.txt",true,"*",false);
			HagSQL hagSQL = new HagSQL();
			for(int i=0;i<list.size();i++){
				String temp = list.get(i);
				String w6= HagUtil.getWord0(temp,"|",6,true);
				if(w6.equals("NN"))
					continue;
				String stm = "select VERSION,TASKNO,USERNAME,STATUS,OPENDATE,OPENTIME,CLOSEDATE,CLOSETIME from dbo."+w6+" where VERSION<>'0000' order by OPENDATE,OPENTIME";
				HagStringList results = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,8,"|",null,null);
				for(int j=0;j<results.size();j++){
					String temp1 = results.get(j);
					ans.add(w6+"|"+temp1);
				}				
			}
			
			
			return HagJdbc.dspTasksTableNotDynamic(ans ,null);
		}else if(w2.equals("dsp")){
			StringBuilder ans = new StringBuilder();
			ans.append("<table bgcolor=\"#aaaacc\" border=\"1\">");
			ans.append("<tr bgColor=#737373><td>Table</td><td>opened in version</td><td>Task</td><td>User</td><td>openDate</td><td>openTime</td><td>closeDate</td><td>closeTime</td><td>status</td></tr>");
			HagStringList list= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\versions.txt",true,"*",false);
			HagSQL hagSQL = new HagSQL();
			for(int i=0;i<list.size();i++){
				String temp = list.get(i);
				String w6= HagUtil.getWord0(temp,"|",6,true);
				if(w6.equals("NN"))
					continue;
				String stm = "select VERSION,TASKNO,USERNAME,OPENDATE,OPENTIME,CLOSEDATE,CLOSETIME,STATUS from dbo."+w6+" where VERSION<>'0000' order by OPENDATE,OPENTIME";
				HagStringList results = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,8,"|",null,null);
				ans.append("<tr bgColor=#8a8a8a><td>").append(w6).append("</td><td>.</td><td>.</td><td>.</td><td>.</td><td>.</td><td>.</td><td>.</td><td>.</td></tr>");
				for(int j=0;j<results.size();j++){
					String temp1 = results.get(j);
					if (temp1.endsWith("OPN")){
						temp1 = HagUtil.replaceStr(temp1,"|OPN","</td><td bgColor=#FF2222>OPN</td><td>"); 
					}
					temp1 = HagUtil.replaceStr(temp1,"|","</td><td>"); 
					ans.append("<tr><td>.</td><td>").append(temp1).append("</td></tr>");
				}				
			}
			return ans.toString();
		}
		//	String auth = HagUtil.isAuthorized(w1,user,pass);
//		if(!auth.equals("OK")){
//			return HagUtil.shortHtml(auth , "red", "bg");
//		}
			return  "*"+w2+"*";
			*/
	}
	
	public static final String reqCreateHotfixBefore1(String user,String pass){
		StringBuilder ans =new StringBuilder();
		HagStringList pre= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\reqPre3.html",true,"*",false);
		String temp1 = pre.convertToString("");
		ans.append(temp1);
		
		
		
		
		ans.append("<body bgColor=\"#cccccc\" >");
		ans.append("<u><h3>Request to create HotFix (cmInstaller)</h3></u>");
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"distRequests.jsp\">");
		ans.append("<input type=\"hidden\" name=\"act\" id=\"act\" value = \"reqCreateHotfixCI\" >");
		ans.append("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebUser\" value = \""+user+"\" >");
		ans.append("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+pass+"\" >");
		ans.append("<br>");
		ans.append("<table bgcolor=\"#aaaacc\" border=\"1\">");
		ans.append("<tr bgcolor=\"#aaaaaa\"><td>Version</td><td>Maintenance</td><td>Update</td><td>Hotfix</td><td>Platform</td></tr>");
		ans.append("<tr>");
		
		//ver
		ans.append("<td><select  class=\"c30\" id=\"ver\" name =\"ver\" >");
		HagStringList ver1= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riVersionsCmInstaller.txt",true,"*",false);
		for(int i=0;i<ver1.size();i++)
			ans.append("<option  value=\"").append(ver1.get(i)).append("\">").append(ver1.get(i)).append("</option>");
		ans.append("</select></td>");
		
		//maint
		ans.append("<td><select  class=\"c30\" id=\"maint\" name =\"maint\" >");
		HagStringList maint1= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riNum2.txt",true,"*",false);
		for(int i=0;i<maint1.size();i++)
			ans.append("<option  value=\"").append(maint1.get(i)).append("\">").append(maint1.get(i)).append("</option>");
		ans.append("</select></td>");
		
		//update
		ans.append("<td><select  class=\"c30\" id=\"update\" name =\"update\" >");
		HagStringList update1= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riNum2.txt",true,"*",false);
		for(int i=0;i<update1.size();i++)
			ans.append("<option  value=\"").append(update1.get(i)).append("\">").append(update1.get(i)).append("</option>");
		ans.append("</select></td>");
		
		//hotfix
		ans.append("<td><select  class=\"c30\" id=\"hotfix\" name =\"hotfix\" >");
		HagStringList hotfix1= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riNum2.txt",true,"*",false);
		for(int i=0;i<hotfix1.size();i++)
			ans.append("<option  value=\"").append(hotfix1.get(i)).append("\">").append(update1.get(i)).append("</option>");
		ans.append("</select></td>");
	
		//platform
		ans.append("<td><select  class=\"c30\" id=\"platform\" name =\"platform\" >");
		//ans.append("<option  value=\"").append("I5OS Only").append("\">").append("I5OS Only").append("</option>");
		ans.append("<option  value=\"").append("WIN Only").append("\">").append("WIN Only").append("</option>");
		//ans.append("<option  value=\"").append("SQL&I5OS").append("\">").append("SQL&I5OS").append("</option>");
		ans.append("</select></td>");
		ans.append("</tr></table><br>");
		ans.append("<table bgcolor=\"#aaaacc\" border=\"1\">");
		//customer
		//ans.append("<tr><td>Customer</td><td><select  class=\"c30\" id=\"customer\" name =\"customer\" >");
		HagStringList customer1= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\customersCmInstaller.txt",true,"*",false);
		
		ans.append("<tr><td>Customer</td><td>Select <a href=\"javascript:selectToggle(true, 'Form');\">All</a> | <a href=\"javascript:selectToggle(false, 'Form');\">None</a><br>");
		//ans.append("<option  value=\"").append("ALL").append("\">").append("All").append("</option>");
		//ans.append("<option  value=\"").append("MULTI").append("\">").append("Multi").append("</option>");
		int count =0;
		ans.append("<table bgcolor=\"#aaaacc\" cellpadding=\"5\" border =\"1\"><tr><td>");
		for(int i=0;i<customer1.size();i++){
			String temp = customer1.get(i);
			String w0 = HagUtil.getWord0(temp,"|",0,true);
			String w1 = HagUtil.getWord0(temp,"|",1,true);
			String w2 = HagUtil.getWord0(temp,"|",2,true);
			if(w2.equals("WIN")){
				ans.append("<input type=\"checkbox\" name=\"customers\" id=\"customers\" value=\"").append(w0).append("\">").append(w1).append("<br>");
				count++;	
				if(count == 2 || count == 4 || count == 6|| count == 8)
					ans.append("</td><td>");		
				}
				//ans.append("<option  value=\"").append(w1).append("\">").append(w1).append("</option>");
		}
		ans.append("</td></tr></table>");
		//ans.append("</select></td></tr>");
		ans.append("</td></tr>");

		//note
		ans.append("<tr><td>Note:</td><td> <input type=\"text\" name=\"note\" id=\"note\"  maxlength=\"140\" size=\"140\"></td></tr></table>");
		
		
		//content
		ans.append("<br><br>HotFix content:<br>");
		ans.append("<table bgcolor=\"#ff0000\" border=\"1\"><tr><td><h3>Double check if it is UR(5) or U only(6) mig:<h3></td></tr></table>");
		ans.append("<table bgcolor=\"#aaaacc\" border=\"1\">");
		ans.append("<tr><td bgcolor=\"#aacccc\">Pre</td><td>");
		ans.append("<textarea name=\"pre\" id=\"pre\" rows=10 cols=101></textarea>"); 
		ans.append("</td></tr><tr><td  bgcolor=\"#aacccc\">Message</td><td>");
		ans.append("<input type=\"text\" name=\"msg\" id=\"msg\"   size=\"130\" value=\"\" style=\"color:red\" >");
		ans.append("</td></tr><tr><td  bgcolor=\"#aacccc\">Control</td><td>");
		ans.append("<button style=\"background-color:#6699ff\" onclick=\"replaceDdc()\">1-Replace DDC</button>"); 
		ans.append("<button style=\"background-color:#6699ff\" onclick=\"replaceIom()\">2-Replace IOM</button>"); 
		ans.append("<button style=\"background-color:#00ff00\" onclick=\"replaceWar()\">3-Replace War&Jasper</button>");  
		ans.append("<button style=\"background-color:#6699ff\" onclick=\"replaceJar()\">4-Replace Jar</button>");
		ans.append("<br>");
		ans.append("<button style=\"background-color:#6699ff\" onclick=\"runManualMig()\">5-Run manual mig</button>");
		ans.append("<button style=\"background-color:#6699ff\" onclick=\"setMigDetailsTable()\">6-Set MIG_DETAILS table only</button>");
		ans.append("<button style=\"background-color:#00ff00\" onclick=\"replaceAddScripts()\">8-Replace/Add Scripts</button>"); 
		//ans.append("<button style=\"background-color:#00ff00\" onclick=\"replaceWebProxy()\">9-Replace webProxy</button>"); 
		ans.append("</td></tr><tr><td  bgcolor=\"#aacccc\">Done</td><td>");
		ans.append("<textarea name=\"done\" id=\"done\" rows=10 cols=101></textarea>"); 
		ans.append("</td></tr></table>");
		ans.append("<br>Install on: <input type=\"text\" name=\"instOn\" id=\"instOn\" value = \"??????-??\" maxlength=\"140\" size=\"140\"><br>");
		ans.append("<br><select name=\"act1\" class=\"c30\">");
		ans.append("<option  value=\"").append("Send_request").append("\">").append("Send request").append("</option>");    
		//ans.append("<option  value=\"").append("Save_request").append("\">").append("Save request").append("</option>");    
		//ans.append("<option  value=\"").append("Load_request").append("\">").append("Load request").append("</option>");    
		ans.append("</select>");
		ans.append("&nbsp;&nbsp;<INPUT TYPE=SUBMIT value=\"Run\"></FORM></BODY>");
		
		return ans.toString();
	}
	
	public static final String reqCreateHotfixAriaBefore1(String user,String pass){
		StringBuilder ans =new StringBuilder();
		HagStringList pre= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\reqPre3.html",true,"*",false);
		String temp1 = pre.convertToString("");
		ans.append(temp1);
		
		
		
		
		ans.append("<body bgColor=\"#cccccc\" >");
		ans.append("<u><h3>Request to create HotFix (ARIA)</h3></u>");
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"distRequests.jsp\">");
		ans.append("<input type=\"hidden\" name=\"act\" id=\"act\" value = \"reqCreateHotfixA\" >");
		ans.append("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebUser\" value = \""+user+"\" >");
		ans.append("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+pass+"\" >");
		ans.append("<br>");
		ans.append("<table bgcolor=\"#aaaacc\" border=\"1\">");
		ans.append("<tr bgcolor=\"#aaaaaa\"><td>Version</td><td>Maintenance</td><td>Update</td><td>Hotfix</td><td>Platform</td></tr>");
		ans.append("<tr>");
		
		//ver
		ans.append("<td><select  class=\"c30\" id=\"ver\" name =\"ver\" >");
		HagStringList ver1= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riVersionsAria.txt",true,"*",false);
		for(int i=0;i<ver1.size();i++)
			ans.append("<option  value=\"").append(ver1.get(i)).append("\">").append(ver1.get(i)).append("</option>");
		ans.append("</select></td>");
		
		//maint
		ans.append("<td><select  class=\"c30\" id=\"maint\" name =\"maint\" >");
		HagStringList maint1= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riNum2.txt",true,"*",false);
		for(int i=0;i<maint1.size();i++)
			ans.append("<option  value=\"").append(maint1.get(i)).append("\">").append(maint1.get(i)).append("</option>");
		ans.append("</select></td>");
		
		//update
		ans.append("<td><select  class=\"c30\" id=\"update\" name =\"update\" >");
		HagStringList update1= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riNum2.txt",true,"*",false);
		for(int i=0;i<update1.size();i++)
			ans.append("<option  value=\"").append(update1.get(i)).append("\">").append(update1.get(i)).append("</option>");
		ans.append("</select></td>");
		
		
		//hotfix
		ans.append("<td><select  class=\"c30\" id=\"hotfix\" name =\"hotfix\" >");
		HagStringList hotfix1= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riNum2.txt",true,"*",false);
		for(int i=0;i<hotfix1.size();i++)
			ans.append("<option  value=\"").append(hotfix1.get(i)).append("\">").append(update1.get(i)).append("</option>");
		ans.append("</select></td>");
	
		//platform
		ans.append("<td><select  class=\"c30\" id=\"platform\" name =\"platform\" >");
		//ans.append("<option  value=\"").append("I5OS Only").append("\">").append("I5OS Only").append("</option>");
		ans.append("<option  value=\"").append("WIN Only").append("\">").append("WIN Only").append("</option>");
		//ans.append("<option  value=\"").append("SQL&I5OS").append("\">").append("SQL&I5OS").append("</option>");
		ans.append("</select></td>");
		ans.append("</tr></table><br>");
		ans.append("<table bgcolor=\"#aaaacc\" border=\"1\">");
		//customer
		//ans.append("<tr><td>Customer</td><td><select  class=\"c30\" id=\"customer\" name =\"customer\" >");
		HagStringList customer1= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\customersCmInstaller.txt",true,"*",false);
		
		ans.append("<tr><td>Customer</td><td>Select <a href=\"javascript:selectToggle(true, 'Form');\">All</a> | <a href=\"javascript:selectToggle(false, 'Form');\">None</a><br>");
		//ans.append("<option  value=\"").append("ALL").append("\">").append("All").append("</option>");
		//ans.append("<option  value=\"").append("MULTI").append("\">").append("Multi").append("</option>");
		int count =0;
		ans.append("<table bgcolor=\"#aaaacc\" cellpadding=\"5\" border =\"1\"><tr><td>");
		for(int i=0;i<customer1.size();i++){
			String temp = customer1.get(i);
			String w0 = HagUtil.getWord0(temp,"|",0,true);
			String w1 = HagUtil.getWord0(temp,"|",1,true);
			String w2 = HagUtil.getWord0(temp,"|",2,true);
			if(w2.equals("WIN")){
				ans.append("<input type=\"checkbox\" name=\"customers\" id=\"customers\" value=\"").append(w0).append("\">").append(w1).append("<br>");
				count++;	
				if(count == 2 || count == 4 || count == 6|| count == 8)
					ans.append("</td><td>");		
				}
				//ans.append("<option  value=\"").append(w1).append("\">").append(w1).append("</option>");
		}
		ans.append("</td></tr></table>");
		//ans.append("</select></td></tr>");
		ans.append("</td></tr>");

		//note
		ans.append("<tr><td>Note:</td><td> <input type=\"text\" name=\"note\" id=\"note\"  maxlength=\"140\" size=\"140\"></td></tr></table>");
		
		
		//content
		ans.append("<br><br>HotFix content:<br>");
		ans.append("<table bgcolor=\"#aaaacc\" border=\"1\">");
		ans.append("<tr><td bgcolor=\"#aacccc\">Pre</td><td>");
		ans.append("<textarea name=\"pre\" id=\"pre\" rows=10 cols=101></textarea>"); 
		ans.append("</td></tr><tr><td  bgcolor=\"#aacccc\">Message</td><td>");
		ans.append("<input type=\"text\" name=\"msg\" id=\"msg\"   size=\"130\" value=\"\" style=\"color:red\" >");
		ans.append("</td></tr><tr><td  bgcolor=\"#aacccc\">Control</td><td>");
		ans.append("<button style=\"background-color:#6699ff\" onclick=\"replaceDdc()\">1-Replace DDC</button>"); 
		ans.append("<button style=\"background-color:#6699ff\" onclick=\"replaceIom()\">2-Replace IOM</button>"); 
		ans.append("<button style=\"background-color:#00ff00\" onclick=\"replaceWar()\">3-Replace War&Jasper</button>");  
		ans.append("<button style=\"background-color:#6699ff\" onclick=\"replaceJar()\">4-Replace Jar</button>");
		ans.append("<br>");
		ans.append("<button style=\"background-color:#6699ff\" onclick=\"runManualMig()\">5-Run manual mig&copy migmng.jar</button>");
		ans.append("<button style=\"background-color:#6699ff\" onclick=\"setMigDetailsTable()\">6-Set MIG_DETAILS table only</button>");
		ans.append("<button style=\"background-color:#00ff00\" onclick=\"replaceAddScripts()\">8-Replace/Add Scripts</button>"); 
		//ans.append("<button style=\"background-color:#00ff00\" onclick=\"replaceWebProxy()\">9-Replace webProxy</button>"); 
		ans.append("</td></tr><tr><td  bgcolor=\"#aacccc\">Done</td><td>");
		ans.append("<textarea name=\"done\" id=\"done\" rows=10 cols=101></textarea>"); 
		ans.append("</td></tr></table>");
		ans.append("<br>Install on: <input type=\"text\" name=\"instOn\" id=\"instOn\" value = \"??????-??\" maxlength=\"140\" size=\"140\"><br>");
		ans.append("<br><select name=\"act1\" class=\"c30\">");
		ans.append("<option  value=\"").append("Send_request").append("\">").append("Send request").append("</option>");    
		//ans.append("<option  value=\"").append("Save_request").append("\">").append("Save request").append("</option>");    
		//ans.append("<option  value=\"").append("Load_request").append("\">").append("Load request").append("</option>");    
		ans.append("</select>");
		ans.append("&nbsp;&nbsp;<INPUT TYPE=SUBMIT value=\"Run\"></FORM></BODY>");
		
		return ans.toString();
	}
	public static final String newUser(){
		HagStringList bbb =HagUtil.getAuth();
	
		HagStringList list = new HagStringList();
		list.add("<HTML><body bgcolor=\"#ccccbb\">");
		list.add("<font size=4 color=\"blue\"><u>New user:</u></font><br><br>");
		list.add("<FORM METHOD=POST name=\"Form\" ACTION=\"Register1.jsp\">");
		list.add("<table bgcolor=\"#aaaacc\" border=\"1\"><tr>");
		list.add("<td>User</td>");
		list.add("<td><input type=\"text\" name=\"user\" size=\"10\"> </td>");
		list.add("<td><font color=\"yellow\">Recommended: firstName.lastName</font></td>");
		list.add("</tr><tr>");
		list.add("<td>Password</td>");
		list.add("<td><input type=\"password\" name=\"password\" size=\"10\"> </td>");
		list.add("<td><font color=\"yellow\">.</font></td>");
		list.add("</tr><tr>");
		list.add("<td>Password confirmation</td>");
		list.add("<td><input type=\"password\" name=\"password2\" size=\"10\"> </td>");
		list.add("<td><font color=\"yellow\">Enter the same password</font></td>");
		list.add("</tr></table>");
		list.add("<br><br><font size=2 color=\"blue\">Autorization request:</font><br><br>");
		list.add("<table border=\"1\">");
		for(int i = 0;i<bbb.size();i++){
			list.add("<tr><td><input type=\"checkbox\" name=\"auth\" value=\""+bbb.get(i)+"\" >"+bbb.get(i)+"</td><td>");
		}
		list.add("</table>");
		list.add("<br><INPUT TYPE=SUBMIT value=\"Submit\"></FORM></BODY></HTML>");
		String str=list.convertToString("\n");
		return str;
	}	
	
	
	public static final String authorizationsRequest(String user){
		HagStringList bbb =HagUtil.getAuth();
	
		HagStringList list = new HagStringList();
		list.add("<HTML><body bgcolor=\"#ccccbb\">");
		list.add("<font size=4 color=\"blue\"><u>authorizations request:</u></font>");
		list.add("<FORM METHOD=POST name=\"Form\" ACTION=\"Register3.jsp\">");
		list.add("<input type=\"hidden\" name=\"user\" value=\""+user+"\"  size=\"10\"><br>");
		list.add("<table bgcolor=\"#aaaacc\" border=\"1\"><tr>");
		for(int i = 0;i<bbb.size();i++){
			list.add("<tr><td><input type=\"checkbox\" name=\"auth\" value=\""+bbb.get(i)+"\" >"+bbb.get(i)+"</td><td>");
		}
		list.add("</table>");
		list.add("<br><INPUT TYPE=SUBMIT value=\"Submit\"></FORM></BODY></HTML>");
		String str=list.convertToString("\n");
		return str;
	}	
	public static final String riCrtNewEnv(String plat,String user){
		String from = HagUtil.getEnvLine("from",null,null);
		
		HagStringList ans2 = new HagStringList();
	 	String stm2 = "select distinct bis_server from dbo.ri_environments_new where  status='A' and project <> 'HAGWIDTH'";
		HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm2, ans2, null);
		StringBuilder to1 =new StringBuilder("<select name=\"to1\">");
		for (int i=0;i<ans2.size();i++){
			String temp = ans2.get(i);
			to1.append("<option value=\"").append(temp).append("\">").append(temp).append("</option>");
		}
		to1.append("</select>");	
		String to2="<input type=\"text\" id=\"to2\"  name=\"to2\" size=\"8\">";	
		String note="<input type=\"text\" id=\"note\"  name=\"note\" size=\"80\">";	

		HagStringList ans3 = new HagStringList();
	 	String stm3 = "select distinct lastInst from dbo.ri_environments_new where  status='A' and project <> 'HAGWIDTH' order by lastInst";
		HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm3, ans3, null);
		StringBuilder target =new StringBuilder("<select name=\"target\">");
		HagJdbc.updateListRemoveDate(ans3,true);
		for (int i=0;i<ans3.size();i++){
			String temp = ans3.get(i);
			target.append("<option value=\"").append(temp).append("\">").append(temp).append("</option>");
		}
		String file = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riPackagesOnFtp.txt";
		HagStringList ans3a = new HagStringList(file,true,"*",false);
		for (int i=0;i<ans3a.size();i++){
			String temp = ans3a.get(i);
			target.append("<option value=\"").append(temp).append("\">").append(temp).append("</option>");
		}
		target.append("</select>");	
		
		StringBuilder sentBy =new StringBuilder("<select name=\"sentBy\"><option value=\"").append(user).append("\">").append(user).append("</option></select>");	

		StringBuilder sb = new StringBuilder("<HTML><body bgcolor=\"#ccccbb\">")	
		.append("<font size=6 color=\"GREEN\"><u>Request form:").append("</u></font><br><br>")
		.append("<font size=4 color=\"blue\"><u>create new environment: (").append(plat).append(")</u></font><br><br>")
		.append("<font size=2 color=\"blue\">Select the correct option and submit the form</font><br><br>")
		.append("<font size=2 color=\"black\">Please double check target version and installed version (downgrade is problematic)</font><br><br>")
		.append("<FORM METHOD=POST name=\"Form\" ACTION=\"RiCrtNewEnvWin.jsp\">")
		.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:70%\">")
		.append("<tr><td bgcolor=\"#ccccaa\">From</td><td>").append(from).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">To</td><td>").append(to1).append(" -> ").append(to2).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Target version</td><td>").append(target.toString()).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Note</td><td>").append(note).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Sent by</td><td>").append(sentBy.toString()).append("</td></tr>")
		.append("</table><br><INPUT TYPE=SUBMIT value=\"send the request\"></form></body></html>");
		
		
		
		return sb.toString();
	}
	
	
	public static final String riRefreshEnv(String plat,String user){
		String from = HagUtil.getEnvLine("from",null,null);
		String to = HagUtil.getEnvLine("to",null,null);
		HagStringList ans3 = new HagStringList();
	 	String stm3 = "select distinct lastInst from dbo.ri_environments_new where  status='A' and project <> 'HAGWIDTH' order by lastInst";
		HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm3, ans3, null);
		StringBuilder target =new StringBuilder("<select name=\"target\">");
		HagJdbc.updateListRemoveDate(ans3,true);
		for (int i=0;i<ans3.size();i++){
			String temp = ans3.get(i);
			target.append("<option value=\"").append(temp).append("\">").append(temp).append("</option>");
		}
		String file = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riPackagesOnFtp.txt";
		HagStringList ans3a = new HagStringList(file,true,"*",false);
		for (int i=0;i<ans3a.size();i++){
			String temp = ans3a.get(i);
			target.append("<option value=\"").append(temp).append("\">").append(temp).append("</option>");
		}
		target.append("</select>");	
		String note="<input type=\"text\" id=\"note\"  name=\"note\" size=\"80\">";	

		StringBuilder sentBy =new StringBuilder("<select name=\"sentBy\"><option value=\"").append(user).append("\">").append(user).append("</option></select>");	

		StringBuilder sb = new StringBuilder("<HTML><body bgcolor=\"#ccccbb\">")	
		.append("<font size=6 color=\"GREEN\"><u>Request form:").append("</u></font><br><br>")
		.append("<font size=4 color=\"blue\"><u>refresh environment: (").append(plat).append(")</u></font><br><br>")
		.append("<font size=2 color=\"blue\">Select the correct option and submit the form</font><br><br>")
		.append("<font size=2 color=\"black\">Please double check target version and installed version (downgrade is problematic)</font><br><br>")
		.append("<FORM METHOD=POST name=\"Form\" ACTION=\"RiRefreshEnvWin.jsp\">")
		.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:70%\">")
		.append("<tr><td bgcolor=\"#ccccaa\">From</td><td>").append(from).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">To</td><td>").append(to).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Target version</td><td>").append(target.toString()).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Note</td><td>").append(note).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Sent by</td><td>").append(sentBy.toString()).append("</td></tr>")
		.append("</table><br><INPUT TYPE=SUBMIT value=\"send the request\"></form></body></html>");
		
		
		
		return sb.toString();
	}
	public static final String insertClientModuleMig_before(String plat,String user){
		String sentBy	="<input type=\"text\" id=\"sentBy\"  name=\"sentBy\" readonly size=\"40\" style=\"background:black; color:gold;\" value = \""+user+"\">";	
		String clientModuleVer 	= getClientModuleVerLines();
		String runTypeHotfix 	= getRunTypeHotfixLine();
		String jClass	="<input type=\"text\" id=\"jClass\"  name=\"jClass\" size=\"30\">";	
		String jiraTask	="<input type=\"text\" id=\"jiraTask\"  name=\"jiraTask\" size=\"30\">";	
		String note		="<input type=\"text\" id=\"note\"  name=\"note\" size=\"80\">";	
		
		StringBuilder sb = new StringBuilder("<HTML><body bgcolor=\"#ccccbb\">")	;
		
		sb.append("<font size=6 color=\"GREEN\"><u>Add ClientModule-mig request form:").append("</u></font><br><br>")
		.append("<font size=2 color=\"blue\">Select the correct option and submit the form</font><br><br>")
		.append("<FORM METHOD=POST name=\"Form\" ACTION=\"clientModuleAddMigRequest.jsp\" >")
		.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:70%\">")
		.append("<tr><td bgcolor=\"#ccccaa\">ClientModule version</td><td>").append(clientModuleVer).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Sent by</td><td>").append(sentBy.toString()).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">jClass</td><td>").append(jClass).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">JiraTask</td><td>").append(jiraTask).append("</td></tr>")	
		.append("<tr><td bgcolor=\"#ccccaa\">RunType for hotfix release</td><td>").append(runTypeHotfix).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Note</td><td>").append(note).append("</td></tr>")
		.append("</table><br><INPUT TYPE=SUBMIT value=\"add this mig to the selected version bank\" onclick=\"this.disabled=true;this.value='Sending, please wait...';this.form.submit();\"><font color=red></form></body></html>");
		return sb.toString();
	}
	public static final String getReleaseVersions(){
		StringBuilder jiraVer =new StringBuilder("<select name=\"jiraVer\">");	
		String file = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\releaseVersions.txt";
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
		jiraVer.append("</select>");	return "hag";
	}
	
	public static final String reqRelease(String user){
		//00001
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
		.append("<font size=6 color=\"GREEN\"><u>Release-Request form:").append("</u></font><br><br>")
		.append("<font size=4 color=\"blue\"><u>send a Release-Request:4").append("</u></font><br><br>")
		.append("<font size=2 color=\"blue\">Select the correct option and submit the form</font><br><br>")
		.append("<FORM METHOD=POST name=\"Form\" ACTION=\"RiReleaseRequest.jsp\">")
		.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:70%\">")
		.append("<tr><td bgcolor=\"#ccccaa\">jira version</td><td>").append(jiraVer.toString()).append("</td></tr>")
	//	.append("<tr><td bgcolor=\"#ccccaa\">jira version</td><td>").append(jiraVer1).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Sent by</td><td>").append(sentBy.toString()).append("</td></tr>")
		.append("</table><br><INPUT TYPE=SUBMIT value=\"check the request\"></form></body></html>");
		return sb.toString();
	}
	
	
	public static final String getJiraVerLines(){
		HagStringList list = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\svnFoldersNew.txt",false,"xxssss",false);
		//HagStringList list1=new HagStringList();
		//HagStringList list2=new HagStringList();
		StringBuilder vers = new StringBuilder();
		vers.append("<table   bgColor =\"#66ff66\" border = \"2\" cellpadding=\"2\"><tr  valign=\"top\"><td>");
		vers.append("<tr bgColor =\"#3399ff\"><td>Displayed version (for the migs table)</td><td>Svn folder name (for the commit)</td><td>Real version name</td><td>Will be released as</td></tr>");
		for(int i = 0 ; i < list.size();i++){
			String temp = list.get(i);
//			temp = HagUtil.replaceStr(temp, " ", "&nbsp;");
			
			//String w1 = HagUtil.getWord0(temp,"~", 0,true);
			//String w2 = HagUtil.getWord0(temp,"~", 1,true);
			//String env = w1+"-"+w2;
			String dsp = HagUtil.getWord0(temp,"|", 0,true);
			String svn = HagUtil.getWord0(temp,"|", 1,true);
			String real = HagUtil.getWord0(temp,"|", 2,true);
			String type = HagUtil.getWord0(temp,"|", 3,true);
		if(svn.equals("None"))
			vers.append("<tr bgColor =\"#9966ff\"><td><input type=\"checkbox\" name=\"cb1\" id=\"cb1\" value=\"").append(temp).append("\" >").append(dsp+"</td><td>"+svn+"</td><td>"+real+"</td><td>"+type+"</td></tr>");
		else if(real.equals("HAGAY-test"))
			vers.append("<tr bgColor =\"#ff0000\"><td><input type=\"checkbox\" name=\"cb1\" id=\"cb1\" value=\"").append(temp).append("\" >").append(dsp+"</td><td>"+svn+"</td><td>"+real+"</td><td>"+type+"</td></tr>");
		else
			vers.append("<tr><td><input type=\"checkbox\" name=\"cb1\" id=\"cb1\" value=\"").append(temp).append("\" >").append(dsp+"</td><td>"+svn+"</td><td>"+real+"</td><td  bgColor =\"#ffff66\">"+type+"</td></tr>");
			
			
		///	if(i>0)
		///		vers.append("<br>");
			////vers.append("<input type=\"checkbox\" name=\"cb1\" id=\"cb1\" value=\"").append(temp).append("\" >"+"<tr><td>"+temp+"</td><td>"+temp+"</td></tr>");
			//else if(w1.equals(prev))
			//	winEnvToInstall.append("<br><input type=\"checkbox\" name=\"cb1\" id=\"cb1\" value=\"").append(env).append("\" checked>"+env);
		//	else
			//	winEnvToInstall.append("</td><td><input type=\"checkbox\" name=\"cb1\" id=\"cb1\" value=\"").append(env).append("\" checked>"+env);
		//	prev=w1;
		}
		vers.append("</td></tr></table>");
		return vers.toString();
	}
	public static final String getClientModuleVerLines(){
		HagStringList list = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\clientModuleVersions.txt",false,"xxssss",false);
		StringBuilder vers = new StringBuilder();
		vers.append("<table   bgColor =\"#66ff66\" border = \"2\" cellpadding=\"2\"><tr  valign=\"top\">");
		vers.append("<tr bgColor =\"#3399ff\"><td>ClientModule version</td><td>RI version</td></tr>");
		for(int i = 0 ; i < list.size();i++){
			String temp = list.get(i);
			String cmVer = HagUtil.getWord0(temp,"|", 0,true);
			String riVer = HagUtil.getWord0(temp,"|", 1,true);
			vers.append("<tr><td><input type=\"checkbox\" name=\"cb1\" id=\"cb1\" value=\"").append(temp).append("\" >").append(cmVer+"</td><td>"+riVer+"</td></tr>");
		}
		vers.append("</td></tr></table>");
		return vers.toString();
	}
	public static final String getJiraVerLineSingle(String ver){
		HagStringList list = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\svnFoldersNew.txt",false,"xxssss",false);
		StringBuilder vers = new StringBuilder();
		vers.append("<table   bgColor =\"#66ff66\" border = \"2\" cellpadding=\"2\"><tr  valign=\"top\">");
		vers.append("<tr bgColor =\"#3399ff\"><td>Displayed version (for the migs table)</td><td>Svn folder name (for the commit)</td><td>Real version name</td><td>Will be released as</td></tr>");
		for(int i = 0 ; i < list.size();i++){
			String temp = list.get(i);
			String dsp = HagUtil.getWord0(temp,"|", 0,true);
			String svn = HagUtil.getWord0(temp,"|", 1,true);
			String real = HagUtil.getWord0(temp,"|", 2,true);
			String type = HagUtil.getWord0(temp,"|", 3,true);
		if(dsp.trim().equals(ver.trim()))
			return temp;
		}
		return null;
	}
	public static final String checkIfExist(String jclass,String ver){
		String ans="";
		String stm = "select " +
				"ver," +
				"id," +
				"jclass " +
			" from dbo.add_mig where ver='"+ver+"' and jclass='"+jclass+"' and run_type <> '40'"  ;
		HagSQL hagSQL = new HagSQL();
		HagStringList exists = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,3,"~",null,null);
		
		if(exists.size()>0){
			return "checked disabled";
		}
		return ans;
	}
	public static final String getJiraVerLineDup(String jclass){
		HagStringList list = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\svnFoldersNew.txt",false,"xxssss",false);
		StringBuilder vers = new StringBuilder();
		vers.append("<table   bgColor =\"#66ff66\" border = \"2\" cellpadding=\"2\"><tr  valign=\"top\"><td>");
		vers.append("<tr bgColor =\"#3399ff\"><td>Displayed version (for the migs table)</td><td>Svn folder name (for the commit)</td><td>Real version name</td><td>Will be released as</td></tr>");
		for(int i = 0 ; i < list.size();i++){
			String temp = list.get(i);
			String dsp = HagUtil.getWord0(temp,"|", 0,true);
			String svn = HagUtil.getWord0(temp,"|", 1,true);
			String real = HagUtil.getWord0(temp,"|", 2,true);
			String type = HagUtil.getWord0(temp,"|", 3,true);
			String readOnly = checkIfExist(jclass,dsp);
			if(real.equals("HAGAY-test")) {
				vers.append("<tr bgColor =\"#ff0000\"><td><input type=\"checkbox\" name=\"cb1\" id=\"cb1\" value=\"").append(temp).append("\" "+readOnly+">").append(dsp+"</td><td>"+svn+"</td><td>"+real+"</td><td>"+type+"</td></tr>");
			}else {
				vers.append("<tr><td><input type=\"checkbox\" name=\"cb1\" id=\"cb1\" value=\"").append(temp).append("\" "+readOnly+">").append(dsp+"</td><td>"+svn+"</td><td>"+real+"</td><td   bgColor =\"#ffff66\">"+type+"</td></tr>");
			}
		}
		vers.append("</td></tr></table>");
		return vers.toString();
	}
	public static final String getJiraVerLine1(){
		StringBuilder jiraVer =new StringBuilder("<select name=\"jiraVer\">");	
		String file = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\addMigsVersions.txt";
		HagStringList ans3a = new HagStringList(file,true,"*",false);
		for (int i=0;i<ans3a.size();i++){
			String temp = ans3a.get(i);
		//	String active =HagUtil.getWord0(temp, "|", 0, true);
		//	if(active.equalsIgnoreCase("Y")){
		//		String jiraVer1 =  HagUtil.getWord0(temp, "|", 1, true);
				//String key1 =  HagUtil.getWord0(temp, "|", 2, true);
				jiraVer.append("<option class=\"c30\" value=\"").append(temp).append("\">").append(temp).append("</option>");
		//	}
		}
		jiraVer.append("</select>");	
		return jiraVer.toString();
	}
	public static final String getLocation(){
		StringBuilder location =new StringBuilder("<select name=\"location\">");	
		location.append("<option class=\"c30\" value=\"").append("before_metaData").append("\">").append("before_metaData").append("</option>");
		location.append("<option class=\"c30\" value=\"").append("after_metaData").append("\">").append("after_metaData").append("</option>");
		location.append("</select>");	
		return location.toString();
	}
	public static final String getDist(){
		StringBuilder dist =new StringBuilder("<select name=\"dist\">");	
		dist.append("<option class=\"c30\" value=\"").append("this_ver_only").append("\">").append("this_ver_only").append("</option>");
		dist.append("<option class=\"c30\" value=\"").append("this_ver_and_trunk").append("\">").append("this_ver_and_trunk").append("</option>");
		dist.append("</select>");	
		return dist.toString();
	}
	public static final String getRunTypeHotfixLine(){
		StringBuilder runTypeHotfix =new StringBuilder("<select name=\"runTypeHotfix\">");	
		String file = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\migRunTypesNew.txt";
		HagStringList ans3a = new HagStringList(file,true,"*",false);
		for (int i=0;i<ans3a.size();i++){
			String temp = ans3a.get(i);
			runTypeHotfix.append("<option class=\"c30\" value=\"").append(temp).append("\">").append(temp).append("</option>");
		
		}
		runTypeHotfix.append("</select>");	
		return runTypeHotfix.toString();
	}
	public static final String getRunTypeMajorLine(){
		StringBuilder runTypeMajor =new StringBuilder("<select name=\"runTypeMajor\">");	
		String file = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\migRunTypesTrunk.txt";
		HagStringList ans3a = new HagStringList(file,true,"*",false);
		for (int i=0;i<ans3a.size();i++){
			String temp = ans3a.get(i);
			runTypeMajor.append("<option class=\"c30\" value=\"").append(temp).append("\">").append(temp).append("</option>");
		
		}
		runTypeMajor.append("</select>");	
		return runTypeMajor.toString();
	}
	/*
	public static final String getRunTypeLine1(){
		StringBuilder runType =new StringBuilder("<select name=\"runType\">");	
		String file = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\migRunTypes.txt";
		HagStringList ans3a = new HagStringList(file,true,"*",false);
		for (int i=0;i<ans3a.size();i++){
			String temp = ans3a.get(i);
			runType.append("<option class=\"c30\" value=\"").append(temp).append("\">").append(temp).append("</option>");
		
		}
		runType.append("</select>");	
		return runType.toString();
	}
	*/
	public static final String getCustomersLine(){
		HagStringList list = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\customers.txt",false,"xxssss",false);
		HagStringList list1=new HagStringList();
		HagStringList list2=new HagStringList();
		StringBuilder cust = new StringBuilder();
		cust.append("<table  cellpadding=\"10\"><tr  valign=\"top\"><td>");
		cust.append("<input type=\"checkbox\" name=\"cb1\" id=\"cb1\" value=\"").append("ALL").append("\"  checked onclick=\"return false;\">"+"ALL<br>");
		for(int i = 0 ; i < list.size();i++){
			String temp = list.get(i);
			//String w1 = HagUtil.getWord0(temp,"~", 0,true);
			//String w2 = HagUtil.getWord0(temp,"~", 1,true);
			//String env = w1+"-"+w2;
		
			if(i>0)
				cust.append("<br>");
			cust.append("<input type=\"checkbox\" name=\"cb1\" id=\"cb1\" value=\"").append(temp).append("\" >"+temp);
			//else if(w1.equals(prev))
			//	winEnvToInstall.append("<br><input type=\"checkbox\" name=\"cb1\" id=\"cb1\" value=\"").append(env).append("\" checked>"+env);
		//	else
			//	winEnvToInstall.append("</td><td><input type=\"checkbox\" name=\"cb1\" id=\"cb1\" value=\"").append(env).append("\" checked>"+env);
		//	prev=w1;
		}
		cust.append("</td></tr></table>");
		return cust.toString();
	}
	/*
	public static final String addMigRequest1(String user){
		//00001
		String sentBy	="<input type=\"text\" id=\"sentBy\"  name=\"sentBy\" readonly size=\"40\" style=\"background:black; color:gold;\" value = \""+user+"\">";	
		String jiraVer 	= getJiraVerLine();
		String location	= getLocation();
		String dist	= getDist();
		String runType 	= getRunTypeLine();
		String cust 	= getCustomersLine();
		String jClass	="<input type=\"text\" id=\"jClass\"  name=\"jClass\" size=\"30\">";	
		String jiraTask	="<input type=\"text\" id=\"jiraTask\"  name=\"jiraTask\" size=\"30\">";	
		String note		="<input type=\"text\" id=\"note\"  name=\"note\" size=\"80\">";	
		
	
		
//		for(int i = 0 ; i <ans2.size();i++ ){
		//		String temp = ans2.get(i);
		//		ans1.append("<input type=\"checkbox\" onclick=\"return false\" name=\"cb3\" id=\"cb3\" value=\"").append(temp).append("\" checked >"+temp).append("<br>");
		//	}
		
		
		StringBuilder sb = new StringBuilder("<HTML><body bgcolor=\"#ccccbb\">")	;
		
		
		sb.append("<font size=6 color=\"GREEN\"><u>Add-mig request form:").append("</u></font><br><br>")
		.append("<font size=4 color=\"blue\"><u>send the Request:").append("</u></font><br><br>")
		.append("<font size=2 color=\"blue\">Select the correct option and submit the form</font><br><br>")
		.append("<FORM METHOD=POST name=\"Form\" ACTION=\"RiDevAddMigRequest.jsp\" >")
		.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:70%\">")
		.append("<tr><td bgcolor=\"#ccccaa\">Jira version</td><td>").append(jiraVer).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Sent by</td><td>").append(sentBy.toString()).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">jClass</td><td>").append(jClass).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">JiraTask</td><td>").append(jiraTask).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">RunType</td><td>").append(runType).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Location</td><td>").append(location).append("</td></tr>")
			.append("<tr><td bgcolor=\"#ccccaa\">Distribute</td><td>").append(dist).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Note</td><td>").append(note).append("</td></tr>")
			.append("<tr><td bgcolor=\"#ccccaa\">Customer</td><td>").append(cust).append("</td></tr>")
		.append("</table><br><INPUT TYPE=SUBMIT value=\"add this mig to the selected version bank\" onclick=\"this.disabled=true;this.value='Sending, please wait...';this.form.submit();\"></form></body></html>");
		return sb.toString();
	}
*/
	public static final String addMigRequest(String user){
		//00001
		String sentBy	="<input type=\"text\" id=\"sentBy\"  name=\"sentBy\" readonly size=\"40\" style=\"background:black; color:gold;\" value = \""+user+"\">";	
		String jiraVer 	= getJiraVerLines();
		String location	= getLocation();
		//String dist	= getDist();
		String runTypeHotfix 	= getRunTypeHotfixLine();
		String runTypeMajor 	= getRunTypeMajorLine();
		//String cust 	= getCustomersLine();
		String jClass	="<input type=\"text\" id=\"jClass\"  name=\"jClass\" size=\"30\">";	
		String jiraTask	="<input type=\"text\" id=\"jiraTask\"  name=\"jiraTask\" size=\"30\">";	
		String note		="<input type=\"text\" id=\"note\"  name=\"note\" size=\"80\">";	
		
	
		
//		for(int i = 0 ; i <ans2.size();i++ ){
		//		String temp = ans2.get(i);
		//		ans1.append("<input type=\"checkbox\" onclick=\"return false\" name=\"cb3\" id=\"cb3\" value=\"").append(temp).append("\" checked >"+temp).append("<br>");
		//	}
		
		
		StringBuilder sb = new StringBuilder("<HTML><body bgcolor=\"#ccccbb\">")	;
		
		
		sb.append("<font size=6 color=\"GREEN\"><u>Add-mig request form:").append("</u></font><br><br>")
		.append("<font size=4 color=\"blue\"><u>send the Request:").append("</u></font><br><br>")
		.append("<font size=2 color=\"blue\">Select the correct option and submit the form</font><br><br>")
		.append("<FORM METHOD=POST name=\"Form\" ACTION=\"RiDevAddMigRequest.jsp\" >")
		.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:70%\">")
		.append("<tr><td bgcolor=\"#ccccaa\">Jira version</td><td>").append(jiraVer).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Sent by</td><td>").append(sentBy.toString()).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">jClass</td><td>").append(jClass).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">JiraTask</td><td>").append(jiraTask).append("</td></tr>")	
		.append("<tr><td bgcolor=\"#ccccaa\">RunType for hotfix release</td><td>").append(runTypeHotfix).append(" for Hofix release only </td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">RunType for major & update releases</td><td>").append(runTypeMajor).append(" for Major and Update releases only </td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Location</td><td>").append(location).append("</td></tr>")
		//	.append("<tr><td bgcolor=\"#ccccaa\">Distribute</td><td>").append(dist).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Note</td><td>").append(note).append("</td></tr>")
	//		.append("<tr><td bgcolor=\"#ccccaa\">Customer</td><td>").append(cust).append("</td></tr>")
		.append("</table><br><INPUT TYPE=SUBMIT value=\"add this mig to the selected version bank\" onclick=\"this.disabled=true;this.value='Sending, please wait...';this.form.submit();\"><font color=red>this process takes 15sec+ , please wait</font></form></body></html>");
		return sb.toString();
	}
	public static final String dupMigRequest(String user,String jclass,String id){
		//00001
		String sentBy	="<input type=\"text\" id=\"sentBy\"  name=\"sentBy\" readonly size=\"40\" style=\"background:black; color:gold;\" value = \""+user+"\">";	
		String jiraVer 	= getJiraVerLineDup(jclass);
		String location	= getLocation();
		//String dist	= getDist();
		String runTypeHotfix 	= getRunTypeHotfixLine();
		String runTypeMajor 	= getRunTypeMajorLine();
		//String cust 	= getCustomersLine();
		String jClass	="<input type=\"text\" id=\"jClass\"  name=\"jClass\" class=\"style1\" value="+jclass+" readOnly size=\"30\">";	
		String Id	="<input type=\"text\" id=\"Id\"  name=\"Id\"  class=\"style1\" value="+id+" readOnly size=\"30\">";	
		String jiraTask	="<input type=\"text\" id=\"jiraTask\"  name=\"jiraTask\" size=\"30\">";	
		String note		="<input type=\"text\" id=\"note\"  name=\"note\" value=\"duplicated\" size=\"80\">";	
		
	
		
//		for(int i = 0 ; i <ans2.size();i++ ){
		//		String temp = ans2.get(i);
		//		ans1.append("<input type=\"checkbox\" onclick=\"return false\" name=\"cb3\" id=\"cb3\" value=\"").append(temp).append("\" checked >"+temp).append("<br>");
		//	}
		
		HagStringList styleList = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\skel\\style.txt",true,"*",false);
		
		StringBuilder sb = new StringBuilder("<HTML><head>"+styleList.convertToString("")+"</head><body bgcolor=\"#ccccbb\">")	;
		
		
		sb.append("<font size=6 color=\"GREEN\"><u>Duplicate-mig request form:").append("</u></font><br><br>")
		.append("<font size=4 color=\"blue\"><u>send the Request:").append("</u></font><br><br>")
		.append("<font size=2 color=\"blue\">Select the correct option and submit the form</font><br><br>")
		.append("<FORM METHOD=POST name=\"Form\" ACTION=\"dupMigsEntry.jsp\" >")
		.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:70%\">")
		.append("<tr><td bgcolor=\"#ccccaa\">Jira version</td><td>").append(jiraVer).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Sent by</td><td>").append(sentBy.toString()).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">id</td><td>").append(Id).append("duplicated</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">jClass</td><td>").append(jClass).append("duplicated</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">JiraTask</td><td>").append(jiraTask).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">RunType for hotfix release</td><td>").append(runTypeHotfix).append(" for Hofix release only </td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">RunType for major & update releases</td><td>").append(runTypeMajor).append(" for Major and Update releases only </td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Location</td><td>").append(location).append("</td></tr>")
		//	.append("<tr><td bgcolor=\"#ccccaa\">Distribute</td><td>").append(dist).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Note</td><td>").append(note).append("</td></tr>")
	//		.append("<tr><td bgcolor=\"#ccccaa\">Customer</td><td>").append(cust).append("</td></tr>")
		.append("</table><br><INPUT TYPE=SUBMIT value=\"add this mig to the selected version bank\" onclick=\"this.disabled=true;this.value='Sending, please wait...';this.form.submit();\"></form></body></html>");
		String aa=sb.toString();
		return sb.toString();
	}
	
	public static final String reqReleaseSend(String jiraVerWithKey,String user){
		//00001
		
		
		
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
		
		String topack =HagJira.checkRequest_topack(hagRc, jiraVer1,key1);
		if(hagRc.rc!=0){
			hagRc.log.add("<font color=red>checkRequest in topack folder failed.</font>");
			hagRc.log.add(topack);
		}
		String components =HagJira.checkRequest_components(hagRc, jiraVer1,key1,extraDev);	
		//HagStringList components =HagJira.checkRequest_components(hagRc, jiraVer1,key1,extraDev);	
		//String componentsStr ="";	
		
		extraDev.append("\">");
		//StringBuilder porting =new StringBuilder("<select name=\"porting\"><option value=\"NO\">NO</option><option value=\"YES\">YES</option></select>");	
		
		//
		//components
		//if(hagRc.rc!=0)
	//		componentsStr=hagRc.log.get(0);
	//	StringBuilder ans1 = new StringBuilder();
	//	for(int i = 0 ; i <ans2.size();i++ ){
	//		String temp = ans2.get(i);
	//		ans1.append("<input type=\"checkbox\" onclick=\"return false\" name=\"cb3\" id=\"cb3\" value=\"").append(temp).append("\" checked >"+temp).append("<br>");
	//	}
		
		
		
		//return ans1.toString();
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
			/*
			winEnvToInstall.append("<table  cellpadding=\"10\"><tr  valign=\"top\"><td>");
			for(int i = 0 ; i < results1.size();i++){
				String temp = results1.get(i);
				String w1 = HagUtil.getWord0(temp,"~", 0,true);
				String w2 = HagUtil.getWord0(temp,"~", 1,true);
				String env = w1+"-"+w2;
				if(prev.equals("000"))
					winEnvToInstall.append("<input type=\"checkbox\" name=\"cb1\" id=\"cb1\" value=\"").append(env).append("\" checked>"+env);
				else if(w1.equals(prev))
					winEnvToInstall.append("<br><input type=\"checkbox\" name=\"cb1\" id=\"cb1\" value=\"").append(env).append("\" checked>"+env);
				else
					winEnvToInstall.append("</td><td><input type=\"checkbox\" name=\"cb1\" id=\"cb1\" value=\"").append(env).append("\" checked>"+env);
				prev=w1;
			}
			winEnvToInstall.append("</td></tr></table>");
			 */
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
			.append("<font size=6 color=\"GREEN\"><u>Release-Request form1:").append("</u></font><br><br>")
			.append("<font size=4 color=\"blue\"><u>send a Release-Request:2").append("</u></font><br><br>")
			.append("<FORM METHOD=POST name=\"Form\" ACTION=\"RiReleaseRequestSend.jsp\">")
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
	public static final String reqReleasePreVersionSend1111111111111111111(String preVer,String user){
		//00001
		String preVer1 = HagUtil.getWord0(preVer,"|",0,true);
		String preVer2 = HagUtil.getWord0(preVer,"|",1,true);
		boolean rcc=true;
		HagRc hagRc=new HagRc();
		String note="<input type=\"text\" id=\"note\"  name=\"note\" size=\"80\">";	
		StringBuilder extraDev=new StringBuilder("<input type=\"text\" id=\"extraDev\"  name=\"extraDev\" readonly size=\"80\" style=\"background:black; color:gold;\" value = \"");
		
		StringBuilder whenTo =new StringBuilder("<select name=\"whenTo\">")
		.append("<option value=\"").append("At night (if possible)").append("\">").append("At night (if possible)").append("</option>")
		.append("<option value=\"").append("ASAP").append("\">").append("ASAP").append("</option>")
		.append("<option value=\"").append("When you are bored").append("\">").append("When you are bored").append("</option>")
		.append("</select>");	
		
		


		HagSQL hagSQL = new HagSQL();
		String stm1 = "select bis_server,batchName,locks,locksDetails from dbo.ri_environments_new where  status='A' and oded='"+preVer1+"' order by bis_server";
		//stm = HagUtil.replaceStr(stm,"{SERVER}","'"+server+"'");
		ArrayList<String> results1 = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,4,"~",null,null);

	
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
			.append("function toggle(source) {")	
			.append("checkboxesW = document.getElementsByName('cb1');")
			.append("checkboxesA = document.getElementsByName('cb2');")	
			.append("for(var i=0, n=checkboxesW.length;i<n;i++) { checkboxesW[i].checked = !source.checked;  }")
			.append("for(var i=0, n=checkboxesA.length;i<n;i++) {  checkboxesA[i].checked = !source.checked;  }")	
			.append("first_extra_dev.value=source.checked")	

			
			.append("}</script>")
			.append("<body bgcolor=\"#ccccbb\">")	
			.append("<font size=6 color=\"GREEN\"><u>Release-Request form1:").append("</u></font><br><br>")
			.append("<font size=4 color=\"blue\"><u>send a Release-Request:3").append("</u></font><br><br>")
			.append("<FORM METHOD=POST name=\"Form\" ACTION=\"RiReleaseRequestPreVersionSend.jsp\">")
			.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:100%\">")
			.append("<tr><td bgcolor=\"#ccccaa\">preVersion</td><td>").append(preVer1).append("</td></tr>")
			.append("<tr><td bgcolor=\"#ccccaa\">Sent by</td><td>").append(user).append("</td></tr>")
			.append("<tr><td bgcolor=\"#ccccaa\">Topack folder check</td><td>").append(preVer2).append("</td></tr>")
			.append("<tr><td bgcolor=\"#ccccaa\">Components check</td><td>").append("components").append("</h4></td></tr>")
			//.append("<tr><td bgcolor=\"#ccccaa\">porting</td><td>").append(porting.toString()).append("</td></tr>")
			.append("<tr><td bgcolor=\"#ccccaa\">Note</td><td>").append(note).append("</td></tr>")
			.append("<tr><td bgcolor=\"#ccccaa\">Extra DEV</td><td>").append(extraDev)
			.append("&nbsp;&nbsp;&nbsp;<input type=\"checkbox\"   onClick=\"toggle(this)\" >first request of extra-dev multi request")
			.append("</td>").append("</tr>")
			
			.append("</tr>")
			.append("<tr><td bgcolor=\"#ccccaa\">win environments<br>to install</td><td>").append(winEnvToInstall.toString()).append("</td></tr>")
			.append("<tr><td bgcolor=\"#ccccaa\">as400 environments<br>to install</td><td>").append(as400EnvToInstall.toString()).append("</td></tr>")
			.append("<tr><td bgcolor=\"#ccccaa\">when to run<br>the installations</td><td>").append(whenTo).append("</td></tr>")
		 // .append("<input type=\"hidden\" name=\"first_extra_dev\" id=\"first_extra_dev\"  >")
			.append("</table><br><INPUT TYPE=SUBMIT value=\"send the request\"></form></body></html>");
			String aa= sb.toString();
			return sb.toString();
		}
	}	
	
	
	
	public static final int 		autoCommit40(StringBuilder ans,String id,String jClass,String svnFolder,String svnType ,String newType,String sentBy ){
		
		ans.append("<br>SVN commit:");
		ans.append("<table bgcolor=\"#aaaacc\" border=\"1\" >");
			
	
		String stm99 = "select auto_commit  from dbo.add_mig_ind where type=1";
		HagSQL hagSQL99=new HagSQL();			
		ArrayList<String> ans99 = hagSQL99.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm99);
		int commitI = HagUtil.s2i(ans99.get(0));
		ans.append("<tr><td bgcolor=\"#ccccaa\">commit flag</td><td>").append(commitI).append("</td></tr>");
		
		if(commitI==0){
			ans.append("</table><br>");
			ans.append("auto_commit flag = 0 -> need to run svn steps manually");
			return 0;
		}
		
		String dateTime = HagUtil.getDateTime("yyyyMMdd_HHmmsss");
		String ffd = "\\\\ri-archive\\Saptech-Archive\\RI\\svn\\"+svnFolder+"\\ri-mig\\src\\main\\resources\\load";
		String ff = "\\\\ri-archive\\Saptech-Archive\\RI\\svn\\"+svnFolder+"\\ri-mig\\src\\main\\resources\\load\\v050m00_mig.mig";
		System.out.println(ff);
		String ff_bk = "\\\\ri-archive\\Saptech-Archive\\RI\\svn\\before_commit\\"+svnFolder+"_"+dateTime+".txt";
		//String newLine = "zzzzzz";
		
		//if(svnType.equalsIgnoreCase("Hotfix")){
		//	newLine = id+","+id+",com.sapiens.mig.ri.v050m00."+jClass+","+runTypeHotfix0;
		//}else{
		//	if(runTypeMajor0.equals(".")){
		//		newLine = id+","+id+",com.sapiens.mig.ri.v050m00."+jClass;
		//	}else{
		//		newLine = id+","+id+",com.sapiens.mig.ri.v050m00."+jClass+","+runTypeMajor0;	
		//	}
		//}
		String newLine = id+","+id+",com.sapiens.mig.ri.v050m00."+jClass+","+newType;
		//first delete
		//ans.append("Delete local v050m00_mig.mig file:").append("<br>");
		String ans1 = HagUtil.deleteFile(ff,  false);
		ans.append("<tr><td bgcolor=\"#ccccaa\">first delete</td><td>").append(ans1).append("</td></tr>");

		//svn update
		//ans.append("Get v050m00_mig.mig file to local (SVN Update):").append("<br>");
		String ans2 = HagUtil.runCmdRemote("rii-5os","d:\\CFG\\TortoiseSVN\\bin\\svn update --non-interactive --no-auth-cache --trust-server-cert --username cfg-burner --password gon09c \\\\ri-archive\\Saptech-Archive\\RI\\svn\\"+svnFolder+"\\ri-mig\\src\\main\\resources\\load\\v050m00_mig.mig\n","Y");

		ans.append("<tr><td bgcolor=\"#ccccaa\">svn update</td><td>").append(ans2).append("</td></tr>");
		//backup
		//ans.append("backup v050m00_mig.mig to before_commit folder:").append("<br>");
		String ans3 = HagUtil.copyFileViaDos( ff,ff_bk);
		ans.append("<tr><td bgcolor=\"#ccccaa\">backup before changes</td><td>").append(ans3).append("</td></tr>");
		
		//add line
		//ans.append("Add new line:").append("<br>");
		HagStringList list = new HagStringList(ff,false,"adfAF",true);
		HagStringList list1 = new HagStringList();
		boolean flag = false;
		String ansN="";
		for(int i = 0;i<list.size();i++){
			String temp = list.get(i);
			String w0 = HagUtil.getWord0(temp,",",0,true);
			String w2 = HagUtil.getWord0(temp,",",2,true);
			if(w0.equals(id) && w2.endsWith("."+jClass)){
				list1.add(newLine);
				ansN=temp+" replaced by "+newLine;
				flag  =true;
			}else{
				list1.add(temp);
			}
		}
		if(!flag){
			return -2;

		}
		
		//second delete
		//ans.append("Delete local v050m00_mig.mig file again:").append("<br>");
		String ans4 = HagUtil.deleteFile(ff,  false);
		//ans.append(ans4).append("<br><br>");
		ans.append("<tr><td bgcolor=\"#ccccaa\">Delete local file</td><td>").append(ans4).append("</td></tr>");

	
	//	String ans4a = HagUtil.deleteFolder(ffd, null, false, false);
	//	ans.append("<tr><td bgcolor=\"#ccccaa\">Delete local folder</td><td>").append(ans4a).append("</td></tr>");


	//	String ans4b = HagUtil.createFolder(ffd);
	//	ans.append("<tr><td bgcolor=\"#ccccaa\">Create local folder</td><td>").append(ans4b).append("</td></tr>");

		
		//save local
		//ans.append("Save new local file:").append("<br>");
		String list1s = list1.convertToString("\n");
		String ans4c = HagUtil.writeStringToFileNoNewLine(ff, list1s);
		ans.append("<tr><td bgcolor=\"#ccccaa\">Write the new local file</td><td>").append(ans4c).append("</td></tr>");
	
		//		list1.writeToFile(ff,null,false);
	//	ans.append(newLine).append("<br><br>");
	//	ans.append("<tr><td bgcolor=\"#ccccaa\">Save list to local file</td><td>").append(ans4).append("</td></tr>");
		
		//svn commit
		//ans.append("Commit changes:  (SVN Update)").append("<br>");
		
		HagUtil.sleep(12000);
		
		String userSvn = "cfg-burner";
		String ans4d = HagUtil.runCmdRemote("rii-5os","d:\\CFG\\TortoiseSVN\\bin\\svn status --non-interactive --no-auth-cache --trust-server-cert  --username "+userSvn+" --password gon09c \\\\ri-archive\\Saptech-Archive\\RI\\svn\\"+svnFolder+"\\ri-mig\\src\\main\\resources\\load\\v050m00_mig.mig \n","Y");
		
		//String ans4d = HagUtil.runCmdRemote("rii-5os","d:\\cfg\\cfgmenu_service\\svnStatus.bat "+svnFolder+"\n","Y");
		ans.append("<tr><td bgcolor=\"#ccccaa\">Svn status:  (need to start with M)</td><td>").append(ans4d).append("</td></tr>");

		
		//		String ans5 = HagUtil.runCmdRemote("rii-5os","d:\\CFG\\TortoiseSVN\\bin\\svn commit -m \"admin - "+dateTime+"\"  --non-interactive --no-auth-cache --username cfg-burner --password gon09c \\\\ri-archive\\Saptech-Archive\\RI\\svn\\"+svnFolder+"\\ri-mig\\src\\main\\resources\\load\n","Y");
		String ans5 = HagUtil.runCmdRemote("rii-5os","d:\\CFG\\TortoiseSVN\\bin\\svn commit --non-interactive --no-auth-cache --trust-server-cert  --username "+userSvn+" --password gon09c \\\\ri-archive\\Saptech-Archive\\RI\\svn\\"+svnFolder+"\\ri-mig\\src\\main\\resources\\load\\v050m00_mig.mig -m \"admin - "+dateTime+"\" \n","Y");
		//String ans5 = HagUtil.runCmdRemote("rii-5os","d:\\\\cfg\\\\cfgmenu_service\\\\svnCommit.bat "+svnFolder+" "+dateTime+"\n","Y");

		//ans.append(ans5).append("<br><br>");
		ans.append("<tr><td bgcolor=\"#ccccaa\">Commit changes:  (SVN Commit)</td><td>").append(ans5).append("</td></tr>");
		if(ans5.startsWith("0~") && !ans5.trim().equals("0~")){
			ans.append("<tr><td bgcolor=\"#ccccaa\">RC</td><td>").append("OK").append("</td></tr></table>");
					
			return 1;
		}else{
			HagUtil.sleep(5000);
			//String ans5b = HagUtil.runCmdRemote("cfg-ri","d:\\CFG\\TortoiseSVN\\bin\\svn commit -m \"admin - "+dateTime+"\"  --non-interactive --no-auth-cache --username cfg-burner --password gon09c \\\\ri-archive\\Saptech-Archive\\RI\\svn\\"+svnFolder+"\\ri-mig\\src\\main\\resources\\load\\v050m00_mig.mig\n","Y");
			String ans5b = HagUtil.runCmdRemote("rii-5os","d:\\CFG\\TortoiseSVN\\bin\\svn commit -m \"admin - "+dateTime+"\"  --non-interactive --no-auth-cache --username cfg-burner --password gon09c \\\\ri-archive\\Saptech-Archive\\RI\\svn\\"+svnFolder+"\\ri-mig\\src\\main\\resources\\load\\v050m00_mig.mig\n","Y");
			
			ans.append("<tr><td bgcolor=\"#ccccaa\">Commit changes:  (SVN Commit2)</td><td>").append(ans5b).append("</td></tr>");
			if(!ans5b.startsWith("0~") || ans5b.trim().equals("0~")){
				//ans.append("<tr><td bgcolor=\"#ccccaa\">RC</td><td>").append("Failed second try").append("</td></tr></table>");
				//return -1;
				HagUtil.sleep(5000);
				String ans5c = HagUtil.runCmdRemote("rii-5os","d:\\CFG\\TortoiseSVN\\bin\\svn commit -m \"admin - "+dateTime+"\"  --non-interactive --no-auth-cache --username cfg-burner --password gon09c \\\\ri-archive\\Saptech-Archive\\RI\\svn\\"+svnFolder+"\\ri-mig\\src\\main\\resources\\load\\v050m00_mig.mig\n","Y");
				ans.append("<tr><td bgcolor=\"#ccccaa\">Commit changes:  (SVN Commit3)</td><td>").append(ans5b).append("</td></tr>");
				if(!ans5c.startsWith("0~") || ans5c.trim().equals("0~")){
					ans.append("<tr><td bgcolor=\"#ccccaa\">RC</td><td>").append("Failed third try").append("</td></tr></table>");
					commitFailed(sentBy,id,jClass,svnFolder);
					return -1;
				}else {
					ans.append("<tr><td bgcolor=\"#ccccaa\">RC</td><td>").append("OK - third try").append("</td></tr></table>");
					return 1;

				}
				
			}else {
				ans.append("<tr><td bgcolor=\"#ccccaa\">RC</td><td>").append("OK - second try").append("</td></tr></table>");
				return 1;

			}
			
			
		}
	}
	public static final int 		autoCommit(StringBuilder ans,String id,String jClass,String svnFolder,String svnType,String runTypeHotfix0,String runTypeMajor0,String sentBy){
		
		ans.append("<br>SVN commit:");
		ans.append("<table bgcolor=\"#aaaacc\" border=\"1\" >");
			
		int idI = HagUtil.s2i(id);
		String stm99 = "select auto_commit  from dbo.add_mig_ind where type=1";
		HagSQL hagSQL99=new HagSQL();			
		ArrayList<String> ans99 = hagSQL99.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm99);
		int commitI = HagUtil.s2i(ans99.get(0));
		ans.append("<tr><td bgcolor=\"#ccccaa\">commit flag</td><td>").append(commitI).append("</td></tr>");
		
		if(commitI==0){
			ans.append("</table><br>");
			ans.append("auto_commit flag = 0 -> need to run svn steps manually");
			return 0;
		}
		
		String dateTime = HagUtil.getDateTime("yyyyMMdd_HHmmsss");
		String ffd = "\\\\ri-archive\\Saptech-Archive\\RI\\svn\\"+svnFolder+"\\ri-mig\\src\\main\\resources\\load";
		String ff = "\\\\ri-archive\\Saptech-Archive\\RI\\svn\\"+svnFolder+"\\ri-mig\\src\\main\\resources\\load\\v050m00_mig.mig";
		String ff_bk = "\\\\ri-archive\\Saptech-Archive\\RI\\svn\\before_commit\\"+svnFolder+"_"+dateTime+".txt";
		String newLine = "zzzzzz";
		if(svnType.equalsIgnoreCase("Hotfix")){
			newLine = id+","+id+",com.sapiens.mig.ri.v050m00."+jClass+","+runTypeHotfix0;
		}else{
			if(runTypeMajor0.equals(".")){
				newLine = id+","+id+",com.sapiens.mig.ri.v050m00."+jClass;
			}else{
				newLine = id+","+id+",com.sapiens.mig.ri.v050m00."+jClass+","+runTypeMajor0;	
			}
		}

		//first delete
		//ans.append("Delete local v050m00_mig.mig file:").append("<br>");
		String ans1 = HagUtil.deleteFile(ff,  false);
		ans.append("<tr><td bgcolor=\"#ccccaa\">first delete</td><td>").append(ans1).append("</td></tr>");

		//svn update
		//ans.append("Get v050m00_mig.mig file to local (SVN Update):").append("<br>");
		String ans2 = HagUtil.runCmdRemote("rii-5os","d:\\CFG\\TortoiseSVN\\bin\\svn update --non-interactive --no-auth-cache --trust-server-cert --username cfg-burner --password gon09c \\\\ri-archive\\Saptech-Archive\\RI\\svn\\"+svnFolder+"\\ri-mig\\src\\main\\resources\\load\\v050m00_mig.mig\n","Y");

		ans.append("<tr><td bgcolor=\"#ccccaa\">svn update</td><td>").append(ans2).append("</td></tr>");
		//backup
		//ans.append("backup v050m00_mig.mig to before_commit folder:").append("<br>");
		String ans3 = HagUtil.copyFileViaDos( ff,ff_bk);
		ans.append("<tr><td bgcolor=\"#ccccaa\">backup before changes</td><td>").append(ans3).append("</td></tr>");
		
		//add line
		//ans.append("Add new line:").append("<br>");
		HagStringList list = new HagStringList(ff,false,"adfAF",true);
		HagStringList list1 = new HagStringList();
		boolean flag = false;
		String ansN="";
		for(int i = 0;i<list.size();i++){
			String temp = list.get(i);
			String w0 = HagUtil.getWord0(temp,",",0,true);
			int w0I = HagUtil.s2i(w0);
			if(w0I>idI && !flag){
				list1.add(newLine);
				list1.add(temp);
				ansN=newLine+"  added to at line "+i;
				flag  =true;
			}else{
				list1.add(temp);
			}
		}
		if(!flag){
			list1.add(newLine);
			ansN=newLine+"  added to at the end of the file.";
		}
		
		//second delete
		//ans.append("Delete local v050m00_mig.mig file again:").append("<br>");
		String ans4 = HagUtil.deleteFile(ff,  false);
		//ans.append(ans4).append("<br><br>");
		ans.append("<tr><td bgcolor=\"#ccccaa\">Delete local file</td><td>").append(ans4).append("</td></tr>");

	
	//	String ans4a = HagUtil.deleteFolder(ffd, null, false, false);
	//	ans.append("<tr><td bgcolor=\"#ccccaa\">Delete local folder</td><td>").append(ans4a).append("</td></tr>");


	//	String ans4b = HagUtil.createFolder(ffd);
	//	ans.append("<tr><td bgcolor=\"#ccccaa\">Create local folder</td><td>").append(ans4b).append("</td></tr>");

		
		//save local
		//ans.append("Save new local file:").append("<br>");
		String list1s = list1.convertToString("\n");
		String ans4c = HagUtil.writeStringToFileNoNewLine(ff, list1s);
		ans.append("<tr><td bgcolor=\"#ccccaa\">Write the new local file</td><td>").append(ans4c).append("</td></tr>");
	
		//		list1.writeToFile(ff,null,false);
	//	ans.append(newLine).append("<br><br>");
	//	ans.append("<tr><td bgcolor=\"#ccccaa\">Save list to local file</td><td>").append(ans4).append("</td></tr>");
		
		//svn commit
		//ans.append("Commit changes:  (SVN Update)").append("<br>");
		
		HagUtil.sleep(12000);
		
		String userSvn = "cfg-burner";
		String ans4d = HagUtil.runCmdRemote("rii-5os","d:\\CFG\\TortoiseSVN\\bin\\svn status --non-interactive --no-auth-cache --trust-server-cert  --username "+userSvn+" --password gon09c \\\\ri-archive\\Saptech-Archive\\RI\\svn\\"+svnFolder+"\\ri-mig\\src\\main\\resources\\load\\v050m00_mig.mig \n","Y");
		
		//String ans4d = HagUtil.runCmdRemote("rii-5os","d:\\cfg\\cfgmenu_service\\svnStatus.bat "+svnFolder+"\n","Y");
		ans.append("<tr><td bgcolor=\"#ccccaa\">Svn status:  (need to start with M)</td><td>").append(ans4d).append("</td></tr>");

		
		//		String ans5 = HagUtil.runCmdRemote("rii-5os","d:\\CFG\\TortoiseSVN\\bin\\svn commit -m \"admin - "+dateTime+"\"  --non-interactive --no-auth-cache --username cfg-burner --password gon09c \\\\ri-archive\\Saptech-Archive\\RI\\svn\\"+svnFolder+"\\ri-mig\\src\\main\\resources\\load\n","Y");
		String ans5 = HagUtil.runCmdRemote("rii-5os","d:\\CFG\\TortoiseSVN\\bin\\svn commit --non-interactive --no-auth-cache --trust-server-cert  --username "+userSvn+" --password gon09c \\\\ri-archive\\Saptech-Archive\\RI\\svn\\"+svnFolder+"\\ri-mig\\src\\main\\resources\\load\\v050m00_mig.mig -m \"admin - "+dateTime+"\" \n","Y");
		//String ans5 = HagUtil.runCmdRemote("rii-5os","d:\\\\cfg\\\\cfgmenu_service\\\\svnCommit.bat "+svnFolder+" "+dateTime+"\n","Y");

		//ans.append(ans5).append("<br><br>");
		ans.append("<tr><td bgcolor=\"#ccccaa\">Commit changes:  (SVN Commit)</td><td>").append(ans5).append("</td></tr>");
		if(ans5.startsWith("0~") && !ans5.trim().equals("0~")){
			ans.append("<tr><td bgcolor=\"#ccccaa\">RC</td><td>").append("OK").append("</td></tr></table>");
					
			return 1;
		}else{
			HagUtil.sleep(5000);
			//String ans5b = HagUtil.runCmdRemote("cfg-ri","d:\\CFG\\TortoiseSVN\\bin\\svn commit -m \"admin - "+dateTime+"\"  --non-interactive --no-auth-cache --username cfg-burner --password gon09c \\\\ri-archive\\Saptech-Archive\\RI\\svn\\"+svnFolder+"\\ri-mig\\src\\main\\resources\\load\\v050m00_mig.mig\n","Y");
			String ans5b = HagUtil.runCmdRemote("rii-5os","d:\\CFG\\TortoiseSVN\\bin\\svn commit -m \"admin - "+dateTime+"\"  --non-interactive --no-auth-cache --username cfg-burner --password gon09c \\\\ri-archive\\Saptech-Archive\\RI\\svn\\"+svnFolder+"\\ri-mig\\src\\main\\resources\\load\\v050m00_mig.mig\n","Y");
			
			ans.append("<tr><td bgcolor=\"#ccccaa\">Commit changes:  (SVN Commit2)</td><td>").append(ans5b).append("</td></tr>");
			if(!ans5b.startsWith("0~") || ans5b.trim().equals("0~")){
				//ans.append("<tr><td bgcolor=\"#ccccaa\">RC</td><td>").append("Failed second try").append("</td></tr></table>");
				//return -1;
				HagUtil.sleep(5000);
				String ans5c = HagUtil.runCmdRemote("rii-5os","d:\\CFG\\TortoiseSVN\\bin\\svn commit -m \"admin - "+dateTime+"\"  --non-interactive --no-auth-cache --username cfg-burner --password gon09c \\\\ri-archive\\Saptech-Archive\\RI\\svn\\"+svnFolder+"\\ri-mig\\src\\main\\resources\\load\\v050m00_mig.mig\n","Y");
				ans.append("<tr><td bgcolor=\"#ccccaa\">Commit changes:  (SVN Commit3)</td><td>").append(ans5b).append("</td></tr>");
				if(!ans5c.startsWith("0~") || ans5c.trim().equals("0~")){
					ans.append("<tr><td bgcolor=\"#ccccaa\">RC</td><td>").append("Failed third try").append("</td></tr></table>");
					commitFailed(sentBy,id,jClass,svnFolder);
					return -1;
				}else {
					ans.append("<tr><td bgcolor=\"#ccccaa\">RC</td><td>").append("OK - third try").append("</td></tr></table>");
					return 1;

				}
				
			}else {
				ans.append("<tr><td bgcolor=\"#ccccaa\">RC</td><td>").append("OK - second try").append("</td></tr></table>");
				return 1;

			}
			
			
		}
	}
	public static final void commitFailed	(String sentBy,String id,String jClass,String svnFolder) {
		String to = "david.ha@sapiens.com;gonen.s@sapiens.com;david.b@sapiens.com;stanislav.p@sapiens.com;"+sentBy+"@sapiens.com";
		String cc = "david.ha@sapiens.com";
		String subject = "failed to commit mig "+id+","+jClass+" to "+svnFolder+" svn folder (released by "+sentBy+")- please call hagay 2527";
		String content=".";
		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+sentBy,HagUtil.mailList_hag,subject,content);
		String ans2		= HagUtil.sendSms("0528399706;0528399727;0524570748;0523673421", "david.ha@sapiens.com",subject, "cfgMenuRequest");

	}

public static final int 		autoCommit1(StringBuilder ans,String id,String jClass,String svnFolder,String svnType,String runType0){
		
		ans.append("<br>SVN commit:");
		ans.append("<table bgcolor=\"#aaaacc\" border=\"1\" >");
			
		int idI = HagUtil.s2i(id);
		String stm99 = "select auto_commit  from dbo.add_mig_ind where type=1";
		HagSQL hagSQL99=new HagSQL();			
		ArrayList<String> ans99 = hagSQL99.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm99);
		int commitI = HagUtil.s2i(ans99.get(0));
		ans.append("<tr><td bgcolor=\"#ccccaa\">commit flag</td><td>").append(commitI).append("</td></tr>");
		
		if(commitI==0){
			ans.append("</table><br>");
			ans.append("auto_commit flag = 0 -> need to run svn steps manually");
			return 0;
		}
		
		String dateTime = HagUtil.getDateTime("yyyyMMdd_HHmmsss");
		String ff = "\\\\ri-archive\\Saptech-Archive\\RI\\svn\\"+svnFolder+"\\ri-mig\\src\\main\\resources\\load\\v050m00_mig.mig";
		String ff_bk = "\\\\ri-archive\\Saptech-Archive\\RI\\svn\\before_commit\\"+svnFolder+"_"+dateTime+".txt";
		String newLine = id+","+id+",com.sapiens.mig.ri.v050m00."+jClass;
		if(svnType.equalsIgnoreCase("Hotfix"))
			newLine = id+","+id+",com.sapiens.mig.ri.v050m00."+jClass+","+runType0;
				

		//first delete
		//ans.append("Delete local v050m00_mig.mig file:").append("<br>");
		String ans1 = HagUtil.deleteFile(ff,  false);
		ans.append("<tr><td bgcolor=\"#ccccaa\">first delete</td><td>").append(ans1).append("</td></tr>");

		//svn update
		//ans.append("Get v050m00_mig.mig file to local (SVN Update):").append("<br>");
	//	String ans2 = HagUtil.runCmdRemote("CFG-RI","d:\\CFG\\TortoiseSVN\\bin\\svn update --non-interactive --no-auth-cache --username SAPIENS\\cfg-burner --password gon09c \\\\ri-archive\\Saptech-Archive\\RI\\svn\\"+svnFolder+"\\ri-mig\\src\\main\\resources\\load\n","Y");
		String ans2 = HagUtil.runCmdRemote("rii-5os","d:\\CFG\\TortoiseSVN\\bin\\svn update --non-interactive --no-auth-cache --username SAPIENS\\cfg-burner --password gon09c \\\\ri-archive\\Saptech-Archive\\RI\\svn\\"+svnFolder+"\\ri-mig\\src\\main\\resources\\load\n","Y");
		
		ans.append("<tr><td bgcolor=\"#ccccaa\">svn update</td><td>").append(ans2).append("</td></tr>");
		//backup
		//ans.append("backup v050m00_mig.mig to before_commit folder:").append("<br>");
		String ans3 = HagUtil.copyFileViaDos( ff,ff_bk);
		ans.append("<tr><td bgcolor=\"#ccccaa\">backup before changes</td><td>").append(ans3).append("</td></tr>");
		
		//add line
		//ans.append("Add new line:").append("<br>");
		HagStringList list = new HagStringList(ff,false,"adfAF",true);
		HagStringList list1 = new HagStringList();
		boolean flag = false;
		String ansN="";
		for(int i = 0;i<list.size();i++){
			String temp = list.get(i);
			String w0 = HagUtil.getWord0(temp,",",0,true);
			int w0I = HagUtil.s2i(w0);
			if(w0I>idI && !flag){
				list1.add(newLine);
				list1.add(temp);
				ansN=newLine+"  added to at line "+i;
				flag  =true;
			}else{
				list1.add(temp);
			}
		}
		if(!flag){
			list1.add(newLine);
			ansN=newLine+"  added to at the end of the file.";
		}
		ans.append("<tr><td bgcolor=\"#ccccaa\">add new line to list</td><td>").append(ansN).append("</td></tr>");
		
		//second delete
		//ans.append("Delete local v050m00_mig.mig file again:").append("<br>");
		String ans4 = HagUtil.deleteFile(ff,  false);
		//ans.append(ans4).append("<br><br>");
		ans.append("<tr><td bgcolor=\"#ccccaa\">Delete local file</td><td>").append(ans4).append("</td></tr>");
		
		//save local
		//ans.append("Save new local file:").append("<br>");
		String list1s = list1.convertToString("\n");
		HagUtil.writeStringToFileNoNewLine(ff, list1s);
		//		list1.writeToFile(ff,null,false);
	//	ans.append(newLine).append("<br><br>");
	//	ans.append("<tr><td bgcolor=\"#ccccaa\">Save list to local file</td><td>").append(ans4).append("</td></tr>");
		
		//svn commit
		//ans.append("Commit changes:  (SVN Update)").append("<br>");
		String ans5 = HagUtil.runCmdRemote("rii-5os","d:\\CFG\\TortoiseSVN\\bin\\svn commit -m \"admin - "+dateTime+"\"  --non-interactive --no-auth-cache --username SAPIENS\\cfg-burner --password gon09c \\\\ri-archive\\Saptech-Archive\\RI\\svn\\"+svnFolder+"\\ri-mig\\src\\main\\resources\\load\n","Y");
		//ans.append(ans5).append("<br><br>");
		ans.append("<tr><td bgcolor=\"#ccccaa\">Commit changes:  (SVN Update)</td><td>").append(ans5).append("</td></tr>");
		if(ans5.startsWith("0~")){
			ans.append("<tr><td bgcolor=\"#ccccaa\">RC</td><td>").append("OK").append("</td></tr></table>");
					
			return 1;
		}else{
			ans.append("<tr><td bgcolor=\"#ccccaa\">RC</td><td>").append("Failed").append("</td></tr></table>");
			
			return -1;
		}
	}
/*
	public static final String requestDevAddMigRequestSent1(String jiraVer,String sentBy,String jClass,String jiraTask,String note,String runType,String location,String dist,String[] cbgroup1){
		
		HagSQL hagSQL1=new HagSQL();
		String stm11 = "select id from dbo.add_mig where jclass='"+jClass+"'";
		ArrayList<String> ans11 = hagSQL1.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm11);
		if(ans11.size()>0){
			String subject =sentBy+" request to add mig failed";
			String content =sentBy+" request to add mig failed , because "+jClass+" jClass already exist for id = "+ans11.get(0);
			String to ="david.b@sapiens.com;stanislav.p@sapiens.com;david.ha@sapiens.com;gonen.s@sapiens.com";
			//String to ="david.ha@sapiens.com";
			String cc=sentBy+"@sapiens.com";
			String ans1		= HagUtil.sendMail_hag(HagUtil.cfgTeamMail,to,cc,subject,content);
			return HagUtil.shortHtml("jClass already exist for id = "+ans11.get(0) , "red", "bg");
			
		}
		
		
		
		if(note==null || note.trim().length()==0) note=".";
		if(jClass==null || jClass.trim().length()==0 || jiraTask==null || jiraTask.trim().length()==0  ){
			return HagUtil.shortHtml("jClass and jiraTask fields are required" , "red", "bg");
		}
		HagStringList list = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\svnFolders.txt",true,"*",false);
		
		String svnFolder="";
	
		for(int i = 0 ; i<list.size();i++ ){
			String temp = list.get(i);
			String w0 = HagUtil.getWord0(temp,"|",0,true);
			String w1 = HagUtil.getWord0(temp,"|",1,true);
			if( jiraVer.equals(w0)){
				svnFolder=svnFolder+w1;
			
				break;
			}
		}
		
		
		
		StringBuilder sb = new StringBuilder("<HTML><body >")	
			.append("<font size=6 color=\"GREEN\"><u>Release-Request form:").append("</u></font><br><br>")
			.append("<font size=4 color=\"blue\"><u>send a Release-Request:").append("</u></font><br><br>")
	
			.append("<table bgcolor=\"#aaaacc\" border=\"1\" >")
			
			.append("<tr><td bgcolor=\"#ccccaa\">Jira version</td><td>").append(jiraVer).append("</td></tr>")
			.append("<tr><td bgcolor=\"#ccccaa\">Sent by</td><td>").append(sentBy).append("</td></tr>")
			.append("<tr><td bgcolor=\"#ccccaa\">jiraTask</td><td>").append(jiraTask).append("</td></tr>")
			.append("<tr><td bgcolor=\"#ccccaa\">jClass</td><td>").append(jClass).append("</td></tr>")
			.append("<tr><td bgcolor=\"#ccccaa\">RunType</td><td>").append(runType).append("</td></tr>")
		    .append("<tr><td bgcolor=\"#ccccaa\">Location</td><td>").append(location).append("</td></tr>")
		  .append("<tr><td bgcolor=\"#ccccaa\">Distribute</td><td>").append(dist).append("</td></tr>");
			sb.append("<tr><td bgcolor=\"#ccccaa\">Customer</td><td>");
			StringBuilder custSuort = new StringBuilder();	
			if (cbgroup1 != null && cbgroup1.length > 0){
				
				for(int i = 0 ; i < cbgroup1.length ; i++){
					//ans.append(cbgroup1[i]).append("<br>");
					sb.append("{").append(cbgroup1[i]).append("}<br>");
					String ttt = HagUtil.getWord0(cbgroup1[i], "|", 0, true);
					custSuort.append("{").append(ttt).append("}");
				}
			}else{
				sb.append("{ALL}");
			}
			sb.append("</td></tr>");
			
			//String ccList 	= HagUtil.getRiMails(jiraVer);
			String ccList 	= sentBy+"@sapiens.com";
//get ind 
			
			String stm = "select lowInd  from dbo.add_mig_ind where type=1";
			if(location.equals("after_metaData"))
				stm = "select highInd  from dbo.add_mig_ind where type=1" ;
			HagSQL hagSQL=new HagSQL();			
			ArrayList<String> ans = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm);
			int ind = HagUtil.s2i(ans.get(0));
			int nextInd = ind+2;
			String stm1 = "update dbo.add_mig_ind set lowInd ="+nextInd+" where type=1";
			
			if(location.equals("after_metaData"))
				stm1 = "update dbo.add_mig_ind set highInd ="+nextInd+" where type=1";
			
			int changed = hagSQL.updateStm("confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm1);
				
			
			sb.append("<tr><td bgcolor=\"#ccccaa\">ID</td><td>").append(ind).append("</td></tr>");
			sb.append("<tr><td bgcolor=\"#ccccaa\">ID-INC</td><td>").append(changed).append("</td></tr>");
			sb.append("</table><br><br>");
			
			StringBuilder mailOnly = new StringBuilder();
			//
			String svnFolder1 = HagUtil.replaceStr(svnFolder,"\\\\","");
			svnFolder1 = HagUtil.replaceStr(svnFolder1,"\\","/");
			
			
			String runTypeW0=HagUtil.getWord0(runType, "|",0,true);

			int auto_commit99=-2;
			int auto_commit98=-2;
			StringBuilder ans99 = new StringBuilder();
			
			auto_commit99=autoCommit(ans99,""+ind,jClass,svnFolder,runTypeW0);
			if(auto_commit99==-1)
				ans99.append("SVN commit to "+svnFolder+" failed hagay/gonen = need to check");

			//String link99 ="<a href=\"file://///ri-archive/Saptech-Archive/RI/svn/"+svnFolder+"/ri-mig/src/main/resources/load/v050m00_mig.mig\">Link to v050m00_mig.mig</a>";
			//mailOnly.append("<br>link to "+svnFolder+" local file:<br>").append(link99);
			
			//
			StringBuilder ans98 = new StringBuilder();
			if(dist.equals("this_ver_and_trunk")){
				
				auto_commit98=autoCommit(ans98,""+ind,jClass,"trunk",runTypeW0);
				if(auto_commit98==-1)
					ans98.append("SVN commit to trunk failed hagay/gonen = need to check");

				//String link98 ="<a href=\"file://///ri-archive/Saptech-Archive/RI/svn/trunk/ri-mig/src/main/resources/load/v050m00_mig.mig\">Link to v050m00_mig.mig</a>";
				//mailOnly.append("<br>link to trunk local file:<br>").append(link98);
			}
			
			
			
			
			
//			String runTypeW1=HagUtil.getWord0(runType, "|",1,true);
			String runTypeStr = "";
			if(!runTypeW0.equals("."))
				runTypeStr= ","+runTypeW0;
			
			
			sb.append(ans99.toString()+ans98.toString());
			if(dist.equals("this_ver_and_trunk"))
				svnFolder=svnFolder+" + trunk";
			
			sb.append("#migLine#  ").append(ind).append(",").append(ind).append(",com.sapiens.mig.ri.v050m00.").append(jClass).append(runTypeStr);
			sb.append("<br>#svnFolder#  ").append(svnFolder);
			
			sb.append("</body></html>");
			
			String subject = "#Request to add mig into "+jiraVer+" bank, @BREAK-REQ@  sent by "+sentBy;
			//String to = "david.ha@sapiens.com";
			String to = "david.b@sapiens.com;stanislav.p@sapiens.com;david.ha@sapiens.com;gonen.s@sapiens.com";
			// ccList = "david.ha@sapiens.com";
			
			String ans1		= HagUtil.sendMail_hag(HagUtil.cfgTeamMail,to,ccList,subject,sb.toString()+"<br>"+mailOnly.toString());

			if((auto_commit99==1 || auto_commit99==-2) && (auto_commit98==1 || auto_commit98==-2) ){
				HagUtil.insertIntoMigHf(jClass, jiraTask, jiraVer, custSuort.toString(), runTypeW0,location,dist, sentBy,ind, note,true);
			}else{
				HagUtil.insertIntoMigHf(jClass, jiraTask, jiraVer, custSuort.toString(), runTypeW0,location,dist, sentBy,ind, note,false);
							
			}
			
			
			return sb.toString();		
	}	
	*/
	public static final void checkIfMigLineExists(HagRc hagRc,String sentBy,String jClass, String jiraVer1,String tableName){
		//StringBuilder jiraVersStr = new StringBuilder();
		//for(int i =0 ;i<jiraVers.length;i++){
		//	String temp = jiraVers[i];
		//	String w0 = HagUtil.getWord0(temp,"|",0,true);
		//	if(i==0)
		//		jiraVersStr.append("'").append(w0).append("'");
		//	else
		//		jiraVersStr.append(",'").append(w0).append("'");
					
		//}
		HagSQL hagSQL1=new HagSQL();
		String stm11 = "select id from dbo."+tableName+" where jclass='"+jClass+"' and ver = '"+jiraVer1+"' and run_type<>40";
		ArrayList<String> ans11 = hagSQL1.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm11);
		//ArrayList<String> ans11=hagSQL1.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm11,2,"~",null,null);
		if(ans11.size()>0){
//			String subject =sentBy+" request to add mig failed";
//			String content =sentBy+" request to add mig failed , because "+jClass+" jClass already exist for id = "+ans11.get(0);
			//String to ="david.b@sapiens.com;stanislav.p@sapiens.com;david.ha@sapiens.com;gonen.s@sapiens.com";
//			String to ="david.ha@sapiens.com";
//			String cc=sentBy+"@sapiens.com";
//			String ans1		= HagUtil.sendMail_hag(HagUtil.cfgTeamMail,to,cc,subject,content);
			hagRc.rc=1;
			hagRc.log.add(HagUtil.shortHtml("ver "+jiraVer1+" jClass "+jClass+" already exist for id = "+ans11.get(0) , "red", "bg"));
		}else{
			hagRc.log.add("ver "+jiraVer1+" jClass "+jClass+" added to cfgMenuWeb migs table");
		}
	}
	public static final int getMigId(String location){
		String stm = "select lowInd  from dbo.add_mig_ind where type=1";
		if(location.equals("after_metaData"))
			stm = "select highInd  from dbo.add_mig_ind where type=1" ;
		HagSQL hagSQL=new HagSQL();			
		ArrayList<String> ans = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm);
		int ind = HagUtil.s2i(ans.get(0));
		int nextInd = ind+2;
		String stm1 = "update dbo.add_mig_ind set lowInd ="+nextInd+" where type=1";
		
		if(location.equals("after_metaData"))
			stm1 = "update dbo.add_mig_ind set highInd ="+nextInd+" where type=1";
		
		int changed = hagSQL.updateStm("confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm1);
		return ind;
	}
	public static final void addToSvn(HagRc hagRc, String svnFolder ,String svnType,String runTypeHotfixW0 ,String runTypeMajorW0,int ind,String jClass,String sentBy){
		String svnFolder1 = HagUtil.replaceStr(svnFolder,"\\\\","");
		svnFolder1 = HagUtil.replaceStr(svnFolder1,"\\","/");
	//String runTypeW0=HagUtil.getWord0(runType, "|",0,true);

		int auto_commit99=-2;
		StringBuilder ans99 = new StringBuilder();
		auto_commit99=autoCommit(ans99,""+ind,jClass,svnFolder,svnType,runTypeHotfixW0,runTypeMajorW0,sentBy);
		if(auto_commit99==-1){
			hagRc.log.add("SVN commit to "+svnFolder+" failed hagay/gonen = need to check");
			hagRc.rc=1;
		}else{
			//hagRc.log.add("SVN commit to "+svnFolder+" done");
		}
		hagRc.log.add(ans99.toString());
		
	}
	public static final void changeInSvn(HagRc hagRc, String svnFolder ,String svnType,String id , String jClass,String newType,String sentBy ){
		String svnFolder1 = HagUtil.replaceStr(svnFolder,"\\\\","");
		svnFolder1 = HagUtil.replaceStr(svnFolder1,"\\","/");

		int auto_commit99=-2;
		StringBuilder ans99 = new StringBuilder();
		auto_commit99=autoCommit40(ans99,id,jClass,svnFolder,svnType,newType,sentBy);
		if(auto_commit99==-2){
			hagRc.log.add(id+","+jClass+" not found in "+svnFolder);
			hagRc.rc=1;
		}else if(auto_commit99==-1){
			hagRc.log.add("SVN commit to "+svnFolder+" failed hagay/gonen = need to check");
			hagRc.rc=1;
		}else{
			//hagRc.log.add("SVN commit to "+svnFolder+" done");
		}
		hagRc.log.add(ans99.toString());
		
	}
	public static final String requestDevAddMigRequestSent(String sentBy,String jClass,String jiraTask,String note,String runTypeHotfix,String runTypeMajor,String location,String[] jiraVers,String dupId){
		
		
		if(note==null || note.trim().length()==0) 
			note=".";
		if(note.length()>99)
			note=note.substring(0,98);

		if(jClass==null || jClass.trim().length()==0 || jiraTask==null || jiraTask.trim().length()==0  ){
			return HagUtil.shortHtml("jClass and jiraTask fields are required" , "red", "bg");
		}
		
		if(jiraVers.length==0)
			return HagUtil.shortHtml("please select jira Version" , "red", "bg");
		jClass=jClass.trim();
		String stm1 = "select id from dbo.add_mig where jclass='"+jClass+"' and run_type<>'40'";
		//stm = HagUtil.replaceStr(stm,"{SERVER}","'"+server+"'");
		int ind = 0;
		if(dupId ==null){
			HagSQL hagSQL=new HagSQL();
			HagStringList jClassExist = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,1,"~",null,null);
			if(jClassExist.size()>0)
				return HagUtil.shortHtml(jClass+" jClass already exist for id's "+ jClassExist.convertToString(",")+" please call hagay-2527", "red", "bg");
			ind = getMigId(location);
		}else{
			ind=HagUtil.s2i(dupId);
		}
		HagRc[] hagRcs = new HagRc[jiraVers.length];
		String runTypeHotfixW0=HagUtil.getWord0(runTypeHotfix, "|",0,true);
		String runTypeMajorW0=HagUtil.getWord0(runTypeMajor, "|",0,true);
		HagStringList errMsg= new HagStringList();
		HagRc hagRcInsertToMigTable=new HagRc();
		for(int i=0;i< jiraVers.length;i++){
			hagRcs[i]= new HagRc();
			String jiraVerLine = jiraVers[i];
			String jiraVer0 = HagUtil.getWord0(jiraVerLine,"|",0, true);
			String svnFolder = HagUtil.getWord0(jiraVerLine,"|",1, true);
			String svnType = HagUtil.getWord0(jiraVerLine,"|",3, true);
			
			checkIfMigLineExists(hagRcs[i],sentBy,jClass,jiraVer0,"add_mig");
			if(hagRcs[i].rc !=0){
				continue;
			}
			
			addToSvn( hagRcs[i],svnFolder ,svnType, runTypeHotfixW0,runTypeMajorW0, ind, jClass,sentBy);
			String runTypeW0a=runTypeHotfixW0;
			if(!svnType.equalsIgnoreCase("Hotfix")){
				runTypeW0a=runTypeMajorW0;
			}
			
		
		
			HagUtil.insertIntoMigHf(hagRcInsertToMigTable,jClass, jiraTask, jiraVer0, "{ALL}", runTypeW0a,location,"this_ver_only", sentBy,ind, note,true,"add_mig");
			if(hagRcs[i].rc!=0)
				errMsg.add(jiraVer0+" commit failed - "+hagRcs[i].log.get(0));
			
			if(hagRcInsertToMigTable.rc!=0)
				errMsg.add(jiraVer0+" add line to migs table failed - "+hagRcInsertToMigTable.log.get(0));
    	}
		
		StringBuilder ans = new StringBuilder();
		if(errMsg.size()>0) {
			ans.append("<font color=\"red\">").append(errMsg.convertToString("<br>")).append("</font>");	
		}
		ans.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:100%\">");
		ans.append("<tr bgcolor=\"#ccccaa\"><td>Jira version</td><td>SVN folder</td><td>RC</td><td>log</td></tr>");
		for(int i=0;i<hagRcs.length;i++){
			String jiraVerLine = jiraVers[i];
			String jiraVer0 = HagUtil.getWord0(jiraVerLine,"|",0, true);
			String svnFolder = HagUtil.getWord0(jiraVerLine,"|",1, true);

			HagRc hagRcTemp = hagRcs[i];
			String rcS = "OK";
			if(hagRcTemp.rc!=0)
				rcS = "FAILED";
			ans.append("<tr><td>"+jiraVer0+"</td><td>"+svnFolder+"</td><td>"+rcS+"</td><td>"+hagRcTemp.log.convertToString("<br>")+"</td></tr>");
		}	
		if(+hagRcInsertToMigTable.rc==0)
			ans.append("<tr><td>"+""+"</td><td>"+""+"</td><td>"+hagRcInsertToMigTable.rc+"</td><td>"+"mig record inserted to migs table"+"</td></tr>");
		else {
			ans.append("<tr bgcolor=\"#ff0000\"><td>"+""+"</td><td>"+""+"</td><td>"+hagRcInsertToMigTable.rc+"</td><td>"+hagRcInsertToMigTable.log.convertToString("<br>")+"</td></tr>");
			String haghaga		= HagUtil.sendMail_hagPre("david.ha@sapiens.com","david.ha@sapiens.com","david.ha@sapiens.com","check the mig release to svn","code line =haghaga in hagForm ");

		}	
		ans.append("</table>");
		StringBuilder ansMail = new StringBuilder();
		ansMail.append(ans);
		
		

		
		String subject = "Request to add mig  "+ind+","+jClass+" @BREAK-REQ@  sent by "+sentBy;
		if(dupId !=null){
			subject = "Request to duplicate mig  "+ind+","+jClass+" @BREAK-REQ@  sent by "+sentBy;
		}
	//	String to = "david.b@sapiens.com;stanislav.p@sapiens.com;david.ha@sapiens.com;gonen.s@sapiens.com";
		//String ccList = sentBy+"@sapiens.com";
		//String to = "david.ha@sapiens.com";
		//String ccList = "david.ha@sapiens.com";
		
		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+sentBy,HagUtil.mailList_hag,subject,ans.toString());
	
		return ans.toString();		
			

	}	

	
	public static final String requestClientModuleAddMigRequestSent(String sentBy,String jClass,String jiraTask,String note,String runTypeHotfix,String[] jiraVers,String dupId){
		
		
		if(note==null || note.trim().length()==0) 
			note=".";
		if(note.length()>99)
			note=note.substring(0,98);

		if(jClass==null || jClass.trim().length()==0 || jiraTask==null || jiraTask.trim().length()==0  ){
			return HagUtil.shortHtml("jClass and jiraTask fields are required" , "red", "bg");
		}
		
		//if(jiraVers.length==0)
		//	return HagUtil.shortHtml("please select client Version" , "red", "bg");
		jClass=jClass.trim();
		String stm1 = "select id from dbo.add_mig_clientModule where jclass='"+jClass+"' and run_type<>'40'";
		//stm = HagUtil.replaceStr(stm,"{SERVER}","'"+server+"'");
		int ind = 0;
		if(dupId ==null){
			HagSQL hagSQL=new HagSQL();
			HagStringList jClassExist = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,1,"~",null,null);
			if(jClassExist.size()>0)
				return HagUtil.shortHtml(jClass+" jClass already exist for id's "+ jClassExist.convertToString(",")+" please call hagay-2527", "red", "bg");
			ind = getMigId("before_metaData");
		}else{
			ind=HagUtil.s2i(dupId);
		}
		HagRc[] hagRcs = new HagRc[jiraVers.length];
		String runTypeHotfixW0=HagUtil.getWord0(runTypeHotfix, "|",0,true);
		HagStringList errMsg= new HagStringList();
		HagRc hagRcInsertToMigTable = new HagRc();
		for(int i=0;i< jiraVers.length;i++){
			hagRcs[i]= new HagRc();
			String jiraVerLine = jiraVers[i];
			String cmVer = HagUtil.getWord0(jiraVerLine,"|",0, true);
			String riVer = HagUtil.getWord0(jiraVerLine,"|",1, true);
			
			checkIfMigLineExists(hagRcs[i],sentBy,jClass,cmVer,"add_mig_clientModule");
			if(hagRcs[i].rc !=0){
				continue;
			}
			
			String runTypeW0a=runTypeHotfixW0;
		
			HagUtil.insertIntoMigHf(hagRcInsertToMigTable,jClass, jiraTask, cmVer, "{ALL}", runTypeW0a,".","this_ver_only", sentBy,ind, note,true,"add_mig_clientModule");
			if(hagRcs[i].rc!=0)
				errMsg.add(cmVer+" insert mig failed - "+hagRcs[i].log.get(0));
			if(hagRcInsertToMigTable.rc!=0)
				errMsg.add(cmVer+" add line to migs table failed - "+hagRcInsertToMigTable.log.get(0));
    	}
		
		StringBuilder ans = new StringBuilder();
		if(errMsg.size()>0) {
			ans.append("<font color=\"red\">").append(errMsg.convertToString("<br>")).append("</font>");	
		}
		ans.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:100%\">");
		ans.append("<tr bgcolor=\"#ccccaa\"><td>ClientModule version</td><td>RC</td><td>log</td></tr>");
		for(int i=0;i<hagRcs.length;i++){
			String jiraVerLine = jiraVers[i];
			String cmVer = HagUtil.getWord0(jiraVerLine,"|",0, true);
			String riVer = HagUtil.getWord0(jiraVerLine,"|",1, true);

			HagRc hagRcTemp = hagRcs[i];
			String rcS = "OK";
			if(hagRcTemp.rc!=0)
				rcS = "FAILED";
			ans.append("<tr><td>"+cmVer+"</td><td>"+rcS+"</td><td>"+hagRcTemp.log.convertToString("<br>")+"</td></tr>");
		}	
		if(+hagRcInsertToMigTable.rc==0)
			ans.append("<tr><td>"+""+"</td><td>"+""+"</td><td>"+hagRcInsertToMigTable.rc+"</td><td>"+"mig record inserted to migs table"+"</td></tr>");
		else
			ans.append("<tr bgcolor=\"#ff0000\"><td>"+""+"</td><td>"+""+"</td><td>"+hagRcInsertToMigTable.rc+"</td><td>"+hagRcInsertToMigTable.log.convertToString("<br>")+"</td></tr>");
	
		ans.append("</table>");
		StringBuilder ansMail = new StringBuilder();
		ansMail.append(ans);
		
		

		
		String subject = "Request to add mig  "+ind+","+jClass+" @BREAK-REQ@  sent by "+sentBy;
		if(dupId !=null){
			subject = "Request to duplicate mig  "+ind+","+jClass+" @BREAK-REQ@  sent by "+sentBy;
		}
		String to = "david.b@sapiens.com;stanislav.p@sapiens.com;david.ha@sapiens.com;gonen.s@sapiens.com";
		String ccList = sentBy+"@sapiens.com";
		//String to = "david.ha@sapiens.com";
		//String ccList = "david.ha@sapiens.com";
		
		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+sentBy,HagUtil.mailList_hag,subject,ans.toString());

		return ans.toString();		
			

	}	
	
}