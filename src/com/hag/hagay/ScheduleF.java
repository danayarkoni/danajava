package com.hag.hagay;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class ScheduleF {
public static 		String 			insertScheduleFMig(HttpServletRequest request, HttpServletResponse response,String plat,String user){
		return insertScheduleFMig_before(plat,user);
	}
public static final String 			insertScheduleFMig_before(String plat,String user){
		String sentBy	="<input type=\"text\" id=\"sentBy\"  name=\"sentBy\" readonly size=\"40\" style=\"background:black; color:gold;\" value = \""+user+"\">";	
		String scheduleFVer 	= getScheduleFVerLines();
		String runTypeHotfix 	= getRunTypeHotfixLine();
		String jClass	="<input type=\"text\" id=\"jClass\"  name=\"jClass\" size=\"30\">";	
		String jiraTask	="<input type=\"text\" id=\"jiraTask\"  name=\"jiraTask\" size=\"30\">";	
		String note		="<input type=\"text\" id=\"note\"  name=\"note\" size=\"80\">";	
		
		StringBuilder sb = new StringBuilder("<HTML><body bgcolor=\"#ccccbb\">")	;
		
		sb.append("<font size=6 color=\"GREEN\"><u>Add ScheduleF-mig request form:").append("</u></font><br><br>")
		.append("<font size=2 color=\"blue\">Select the correct option and submit the form</font><br><br>")
		.append("<FORM METHOD=POST name=\"Form\" ACTION=\"scheduleFAddMigRequest.jsp\" >")
		.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:70%\">")
		.append("<tr><td bgcolor=\"#ccccaa\">ScheduleF version</td><td>").append(scheduleFVer).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Sent by</td><td>").append(sentBy.toString()).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">jClass</td><td>").append(jClass).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">JiraTask</td><td>").append(jiraTask).append("</td></tr>")	
		.append("<tr><td bgcolor=\"#ccccaa\">RunType for hotfix release</td><td>").append(runTypeHotfix).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Note</td><td>").append(note).append("</td></tr>")
		.append("</table><br><INPUT TYPE=SUBMIT value=\"add this mig to the selected version bank\" onclick=\"this.disabled=true;this.value='Sending, please wait...';this.form.submit();\"><font color=red></form></body></html>");
		return sb.toString();
	}
public static final String 			getScheduleFVerLines(){
		HagStringList list = new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\scheduleFVersions.txt",false,"xxssss",false);
		StringBuilder vers = new StringBuilder();
		vers.append("<table   bgColor =\"#66ff66\" border = \"2\" cellpadding=\"2\"><tr  valign=\"top\">");
		vers.append("<tr bgColor =\"#3399ff\"><td>ScheduleF version</td><td>RI version</td></tr>");
		for(int i = 0 ; i < list.size();i++){
			String temp = list.get(i);
			String cmVer = HagUtil.getWord0(temp,"|", 0,true);
			String riVer = HagUtil.getWord0(temp,"|", 1,true);
			vers.append("<tr><td><input type=\"checkbox\" name=\"cb1\" id=\"cb1\" value=\"").append(temp).append("\" >").append(cmVer+"</td><td>"+riVer+"</td></tr>");
		}
		vers.append("</td></tr></table>");
		return vers.toString();
	}
public static final String 			getRunTypeHotfixLine(){
		StringBuilder runTypeHotfix =new StringBuilder("<select name=\"runTypeHotfix\">");	
		String file = HagUtil.cfgMenuWebLoc+"\\lists\\migRunTypesNew.txt";
		HagStringList ans3a = new HagStringList(file,true,"*",false);
		for (int i=0;i<ans3a.size();i++){
			String temp = ans3a.get(i);
			runTypeHotfix.append("<option class=\"c30\" value=\"").append(temp).append("\">").append(temp).append("</option>");
		
		}
		runTypeHotfix.append("</select>");	
		return runTypeHotfix.toString();
	}
public static final String 			scheduleFMigsListForm(String cfgMenuWebUser,String cfgMenuWebPass){
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
    				" from dbo.[add_mig_scheduleF] order by dt_dev desc";
		String rc= selectScheduleFMigsList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans,true,null,cfgMenuWebUser,cfgMenuWebPass);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = HagUtil.cfgMenuWebLoc+"\\tableSortT13.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = HagUtil.cfgMenuWebLoc+"\\tableSortB13.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}
	}
public static final String 			selectScheduleFMigsList(String dbms,String server,String user,String pass,String db,String stm,HagStringList ans,boolean withTitles,String[] filter,String cfgMenuWebUser,String cfgMenuWebPass){
		//	String perWidth = "width=\"5%\"";
			Connection con = HagJdbc.getSqlConn( server, user, pass, db,"dbo");
			if(con==null)
				return "1~failed to create connection";
			Statement statement = null;
	    	ResultSet rs = null;
	    	String rc = "init";
	    	try {
	    		 statement = con.createStatement();   
	    		 rs = statement.executeQuery(stm);   
		         ResultSetMetaData metaData = rs.getMetaData();
		         int colCount = metaData.getColumnCount();
		         boolean first =true;
		  		int count1=0;
		  		int count2=0;
		  		
		         
		  		while (rs.next()) {
		        	 StringBuilder rsLine=new StringBuilder();
		        	 count1++;
		         	if(first){
		         		ans.add("<FORM METHOD=POST STYLE=\"margin: 0px; padding: 0px;\" name=\"Form11\" id=\"Form11\"  ACTION=\"filterScheduleFAddMigs.jsp\">");
		         		//combo
		         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebUser\" value = \""+cfgMenuWebUser+"\" >");
		         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+cfgMenuWebPass+"\" >");
		         		
		         		StringBuilder temp0 = new StringBuilder();
			            for (int i = 0; i < colCount; i++) {
			            	//perWidth = getPerWidth(i);
			              	String colName = metaData.getColumnName(i + 1);
			               	if(i==0)
			           			temp0.append("<tr><td>.</td>");
			               	else if(i==13)
			            		temp0.append("<td  colspan=\"6\">&nbsp; </td>");
			               	else if(i>13)
			            		temp0.append("");
			               	else{
			           			temp0.append("<td   >");
			           			temp0.append("<select name=\"col"+i+"\"  onchange=\"this.form.submit()\">");
			           			HagStringList cc = new HagStringList();
			           			String stm1 = "select distinct "+colName+" from dbo.add_mig_scheduleF";
			           			String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
			           			temp0.append("<option value=\"[ALL]\">[ALL]</option>");
			           			for(int h = 0 ; h < cc.size();h++){
			           				String tmp =cc.get(h).trim();
			           				
			           				
			           				String sel="";
			           				if(filter != null && tmp.equals(filter[i-1])){
			           				//	HagUtil.p("t="+tmp+"-"+filter[i-1]);
			           					sel=" selected ";
			           				}
				           			temp0.append("<option value=\""+tmp+"\" "+sel+">"+tmp+"</option>");
			           			}
			           			temp0.append("</select></td>");
			           		}
		           			if(i==(colCount-1))
			         			temp0.append("</tr>");
			        	}   
			            first=false;
			           // HagUtil.p(temp0.toString());
			            ans.add(temp0.toString());
		         		   		            
			            //columns
		                StringBuilder temp1 = new StringBuilder();
		                for (int i = 0; i < colCount; i++) {
		                //	perWidth = getPerWidth(i);
		                	String colName = metaData.getColumnName(i + 1);
		                	if(i==0){
		                		StringBuilder ddd = new StringBuilder();
		               			//ddd.append("Select (<a href=\"javascript:selectToggle(true);\">All</a> | <a href=\"javascript:selectToggle(false);\">None</a>)");
		               			ddd.append("<a href=\"javascript:selectToggle(true);\">All</a><br><a href=\"javascript:selectToggle(false);\">None</a>");
		                		//ddd.append("<button onclick=\"selectToggle1(true)\">All</button>"); 
		                		temp1.append("<tr bgColor=\"#AAAAAA\"><th >"+ddd.toString()+"</th>");
		                	}else
		               			//temp1.append("<th >").append(replaceColName(colName)).append("</th>");
		                	temp1.append("<th >").append(colName).append("</th>");
		            		if(i==(colCount-1))
		            			temp1.append("</tr>");
		           		}   
		                first=false;
	           			ans.add(temp1.toString());
	           		//commands
	           	     //	StringBuilder ddd = new StringBuilder();
	           		//	ddd.append("Select (<a href=\"javascript:selectToggle(true);\">All</a> | <a href=\"javascript:selectToggle(false);\">None</a>)");
	           	
	           			StringBuilder devCombo = new  StringBuilder();
	           			StringBuilder requestCombo = new StringBuilder();
	           	    	StringBuilder managerCombo = new StringBuilder();

	           	    	ans.add("</form><FORM target=\"_blank\" STYLE=\"margin: 0px; padding: 0px;\" METHOD=POST name=\"Form3\" id=\"Form3\"  ACTION=\"distEnvs.jsp?value=WIN\">");
	           	 	ans.add("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebUser\" value = \""+cfgMenuWebUser+"\" >");
	        	 	ans.add("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+cfgMenuWebPass+"\" >");
	           	    
	        	 	devCombo.append("<select style=\"background-color: yellow\" id=\"act1\" name =\"act1\" onchange=\"this.form.submit();this.selectedIndex = 0\">");
	       			
	           	    	HagStringList list1 = new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\scheduleFMigDevOptions.txt",true,"*",false);
	           	    	for(int i=0;i<list1.size();i++)
	           	    		devCombo.append(list1.get(i));
	        	 	
	        	 	devCombo.append("</select>");
	           	            	    	
	           	    
	           	       requestCombo.append("<select style=\"background-color: yellow\" id=\"act2\" name =\"act2\" onchange=\"this.form.submit();this.selectedIndex = 0\">");
	           	        HagStringList list2 = new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\scheduleFMigCmOptions.txt",true,"*",false);
	           	    	for(int i=0;i<list2.size();i++)
	           	    		requestCombo.append(list2.get(i));
	           	   //requestCombo.append("Future");
	           	       requestCombo.append("</select>");

	           	    
	           	   	managerCombo.append("<select class=\"c30\" id=\"act3\" name =\"act3\" onchange=\"this.form.submit();this.selectedIndex = 0\">");
	         	    	HagStringList list3 = new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\scheduleFMigManagerOptions.txt",true,"*",false);
	           	    	for(int i=0;i<list3.size();i++)
	           	    		managerCombo.append(list3.get(i));
	           	    //	managerCombo.append("Future");
	           	    	
	           	    	managerCombo.append("</select>");
	      
	           		
	           	  	ans.add("<tr>" +devCombo.toString()+requestCombo.toString()+managerCombo.toString() +"</tr>");
		         	}
	                String lastGoodInst="xx";
	                //data
	                HagStringList oneRow = new HagStringList();
	               // HagUtil.p(oneRow);
	                for (int i = 0; i < colCount; i++) {
	                	//perWidth = getPerWidth(i);
	            		//if(i==4){
	            		////	lastGoodInst=rs.getString(i+1);
	            		////	rsLine.append("~").append(lastGoodInst);
	            		////}else if(i==0){
	            			//rsLine.append("~").append(rs.getString(i+1));
	                		//if(filter == null){
	                		//	oneRow.add("<td  "+perWidth+" >"+replaceVal(i,rs.getString(i+1),lastGoodInst)+"</td>");
	                		//}else{
	                		//	HagUtil.p("xxx"+rs.getString(i+1)+","+filter[i-1]);
	                		//	if(rs.getString(i+1).startsWith(filter[i-1])){
	                		//		HagUtil.p("yyy"+rs.getString(i+1)+","+filter[i-1]);
	                		//		oneRow.add(replaceVal(i,rs.getString(i+1),lastGoodInst));
	                		//	}
	               		//	}
	            		//}else 
	               // 	HagUtil.p(rs.getString(i+1));
	                	
	                	String val = rs.getString(i+1);
	                	if(i==0){     
	                		oneRow.add("<tr><td nowrap><input type=\"checkbox\" name=\"cb\" id=\"cb\" value=\"{ID}\"></td>");
	                		rsLine.append(val);
	            		}else{
	            			rsLine.append("~").append(val);
	                		if(filter == null){
	//                			if(i==5 && val.trim().equals("Failed")){
	                       			//oneRow.add("<td  nowrap bgColor=#ff0000>"+replaceVal(i,val,lastGoodInst)+"</td>");
	                			//}else if(i==9 && val.trim().equals("hag-env")){
	                       		//	oneRow.add("<td  nowrap bgColor=#ffff00>hag-env</td>");
	                			//}else if(i==9 && val.trim().equals("TO-DELETE")){
	                       	//		oneRow.add("<td  nowrap bgColor=#ff00ff>TO-DELETE</td>");
	                		//	}else
	                				oneRow.add("<td  nowrap >"+val.trim()+"</td>");
	                		}else{
	                				oneRow.add(val.trim());
	                			
	                		}
	                	
	            		}
	                	
	                }
	             
	                
	            	if(filter == null){
	            		//String temp2a1 = oneRow.convertToString("!");
	            		
	            		//System.out.println(temp2a1);
	            		String temp2a = oneRow.convertToString(" ");
	            		if(temp2a.indexOf(" <td  nowrap >40</td> ")>-1)
	            			temp2a= HagUtil.replaceStr(temp2a,"<tr>","<tr bgColor=#ff0000>");

	            		String temp2b = HagUtil.replaceStr(temp2a,"{ID}", rsLine.toString());
	            		//String temp2c = temp2b; 
//	            		System.out.println(temp2c);
	//            		if(temp2c.indexOf("~40        ~>")>-1)
	  //          			temp2c="<tr bgColor=#ff0000>"+temp2c.substring(4,temp2c.length());
	            
	            		ans.add(temp2b);
	            	//	System.out.println(temp2b);
	            	}else{
	            		boolean flag = true;
	            		for(int t = 0 ; t < filter.length;t++){
	            			String colFilter = filter[t];
	            			if(t==3){
	                        
	            				if(!colFilter.equals("[ALL]") && !oneRow.get(t+1).startsWith(colFilter) ){
	                				flag = false;
	                				
	            				}
	            			}else if(!colFilter.equals("[ALL]") && !colFilter.equals(oneRow.get(t+1)) ){
	            				flag = false;
	            			}
	            		}
	            		if(flag){
	            		//		 temp2a = oneRow.convertToString("</td><td nowrap  bgColor=#ff0000>");
	               			String	temp2a = oneRow.convertToString("</td><td nowrap  >");
	                	//	String temp2a1 = HagUtil.replaceStr(temp2a,"</td><td nowrap  >Failed","</td><td nowrap  bgColor=#ff0000>Failed");
	                	//	temp2a1 = HagUtil.replaceStr(temp2a1,"</td><td nowrap  >hag-env","</td><td nowrap  bgColor=#ffff00>hag-env");
	                	//	temp2a1 = HagUtil.replaceStr(temp2a1,"</td><td nowrap  >TO-DELETE","</td><td nowrap  bgColor=#ff00ff>TO-DELETE");
	                   		String temp2b = HagUtil.replaceStr(temp2a,"{ID}",rsLine.toString());
	                   		//System.out.println(temp2b);
	                   		
	                   		if(temp2b.indexOf("~40        ~")>-1)
	                   			temp2b= HagUtil.replaceStr(temp2b,"<tr>","<tr bgColor=#ff0000>");
	                   		
	                		ans.add("<td  nowrap>"+temp2b+"</td>");
	                		count2++;
	            		}
	            		ans.add("</tr>");
	            	}
	            //	String temp111 = ans.get(ans.size()-1);
	            //	System.out.println(temp111);
	            //	String hagC = "";
	        	//	if(temp111.indexOf("hag-env") >-1)
	            //		hagC = "bgColor=#\"4488aa\"";
	        	//	String temp112 = HagUtil.replaceStr(temp111,"!!!#!!!", hagC);
	        	//	System.out.println(temp112);
	        	//	ans.set(ans.size()-1, temp112);
	            	
		         }
		       /*  
		     	StringBuilder ddd = new StringBuilder();
	   			ddd.append("Select (<a href=\"javascript:selectToggle(true);\">All</a> | <a href=\"javascript:selectToggle(false);\">None</a>)");
	   		 ans.add("<h3>");
		         ans.add(ddd.toString());
		         ans.add("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Display=");
		        */
		  		
		         ans.add("<h3><font color=\"yellow\">");
			     if(filter==null)
		        	 ans.add(count1+" rows from total "+count1+" rows <br>");
		         else
		          	 ans.add(count2+" rows from total "+count1+" rows <br>");
		         ans.add("</font></h3>");
		    
		         String bbb= ans.toString();
			     rc="0~done."; 	    
	    	 }catch (Exception e) {
		        e.printStackTrace();
		        rc="1~"+e.getMessage(); 	
		     }finally {
		            if (rs != null) try { rs.close(); } catch(Exception e) {}
		            if (statement != null) try { statement.close(); } catch(Exception e) {}
		            if (con != null) try { con.close(); } catch(Exception e) {}
		     } 
	    	return rc; 	
		}	
public 				String 			scheduleFAddMigRequest(HttpServletRequest request, HttpServletResponse response){

		
		String jClass 		= request.getParameter("jClass").trim();
		String jiraTask 	= request.getParameter("jiraTask").trim();
		String sentBy 		= request.getParameter("sentBy").trim();
		//String jiraVer 		= request.getParameter("jiraVer").trim();
		String note 		= request.getParameter("note").trim();
		String runTypeHotfix 		= request.getParameter("runTypeHotfix").trim();
		//String dist 		= request.getParameter("dist").trim();
		
		String [] cbgroup1		= request.getParameterValues("cb1");
		if(cbgroup1==null || cbgroup1.length!=1)
			return HagUtil.shortHtml("You must select  one SheduleF version","red","bg");

		return requestsSheduleFAddMigRequestSent(sentBy,jClass,jiraTask,note,runTypeHotfix,cbgroup1,null);
		
//		return HagForms.requestDevAddMigRequestSent(sentBy,jClass,jiraTask,note,runTypeHotfix,runTypeMajor,location,cbgroup1,null);
}
public static final String 			requestsSheduleFAddMigRequestSent(String sentBy,String jClass,String jiraTask,String note,String runTypeHotfix,String[] jiraVers,String dupId){
	
	
	if(note==null || note.trim().length()==0) 
		note=".";

	if(jClass==null || jClass.trim().length()==0 || jiraTask==null || jiraTask.trim().length()==0  ){
		return HagUtil.shortHtml("jClass and jiraTask fields are required" , "red", "bg");
	}
	
	//if(jiraVers.length==0)
	//	return HagUtil.shortHtml("please select client Version" , "red", "bg");
	jClass=jClass.trim();
	String stm1 = "select id from dbo.add_mig_scheduleF where jclass='"+jClass+"' and run_type<>'40'";
	//stm = HagUtil.replaceStr(stm,"{SERVER}","'"+server+"'");
	int ind = 0;
	if(dupId ==null){
		HagSQL hagSQL=new HagSQL();
		HagStringList jClassExist = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,1,"~",null,null);
		if(jClassExist.size()>0)
			return HagUtil.shortHtml(jClass+" jClass already exist for id's "+ jClassExist.convertToString(",")+" please call hagay-2527", "red", "bg");
		ind = HagForms.getMigId("before_metaData");
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
		
		checkIfMigLineExists(hagRcs[i],sentBy,jClass,cmVer,"add_mig_scheduleF");
		if(hagRcs[i].rc !=0){
			continue;
		}
		
		String runTypeW0a=runTypeHotfixW0;
	
		HagUtil.insertIntoMigHf(hagRcInsertToMigTable,jClass, jiraTask, cmVer, "{ALL}", runTypeW0a,".","this_ver_only", sentBy,ind, note,true,"add_mig_scheduleF");
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
	ans.append("<tr bgcolor=\"#ccccaa\"><td>ScheduleF version</td><td>RC</td><td>log</td></tr>");
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
	String to = "david.b@sapiens.com;david.ha@sapiens.com;gonen.s@sapiens.com";
	String ccList = sentBy+"@sapiens.com";
	//String to = "david.ha@sapiens.com";
	//String ccList = "david.ha@sapiens.com";
	
	String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+sentBy,HagUtil.mailList_hag,subject,ans.toString());

	return ans.toString();		
		

}	
public static final void 			checkIfMigLineExists(HagRc hagRc,String sentBy,String jClass, String jiraVer1,String tableName){
	
	HagSQL hagSQL1=new HagSQL();
	String stm11 = "select id from dbo."+tableName+" where jclass='"+jClass+"' and ver = '"+jiraVer1+"' and run_type<>40";
	ArrayList<String> ans11 = hagSQL1.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm11);

	if(ans11.size()>0){
		hagRc.rc=1;
		hagRc.log.add(HagUtil.shortHtml("ver "+jiraVer1+" jClass "+jClass+" already exist for id = "+ans11.get(0) , "red", "bg"));
	}else{
		hagRc.log.add("ver "+jiraVer1+" jClass "+jClass+" added to cfgMenuWeb migs table");
	}
}
public 				String 			filterScheduleFAddMigs(HttpServletRequest request, HttpServletResponse response){
	String cfgMenuWebUser 	= request.getParameter("cfgMenuWebUser");
	String cfgMenuWebPass 	= request.getParameter("cfgMenuWebPass");

	String col1 = request.getParameter( "col1" );
	String col2 = request.getParameter( "col2" );
	String col3 = request.getParameter( "col3" );
	String col4 = request.getParameter( "col4" );
	String col5 = request.getParameter( "col5" );
	String col6 = request.getParameter( "col6" );
	String col7 = request.getParameter( "col7" );
	String col8 = request.getParameter( "col8" );
	String col9 = request.getParameter( "col9" );
	String col10 = request.getParameter( "col10" );
	String col11 = request.getParameter( "col11" );
	String col12 = request.getParameter( "col12" );
	String[] filter = {	col1.trim(),col2.trim(),col3.trim(),
			col4.trim(),col5.trim(),col6.trim(),
			col7.trim(),col8.trim(),col9.trim(),
			col10.trim(),col11.trim(),col12.trim()};

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
			" from dbo.add_mig_scheduleF order by dt_dev desc";
	String rc= selectScheduleFMigsList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans,true,filter,cfgMenuWebUser,cfgMenuWebPass);
	if(rc.startsWith("0~")){
		String str = ans.convertToString(" ");
		String ffT = HagUtil.cfgMenuWebLoc+"\\tableSortT13.html";
		HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
		String strT = listT.convertToString("\n");
		String ffB = HagUtil.cfgMenuWebLoc+"\\tableSortB13.html";
		HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
		String strB = listB.convertToString("\n");
		return strT+str+strB;
	}else{
		return rc;
	}

}
public 				String 			scheduleFActs(HttpServletRequest request, HttpServletResponse response){
	String cfgMenuWebUser 	= request.getParameter("cfgMenuWebUser");
	String cfgMenuWebPass 	= request.getParameter("cfgMenuWebPass");
	String value = request.getParameter( "value" );

	
	String act = request.getParameter( "act3" );
return cfgMenuWebUser+"<br>"+cfgMenuWebPass+"<br>"+value+"<br>"+act;
	//if(act.equals("Dev: Select action to run"))
	//	act = request.getParameter( "act2" );
}

public static 		String 			requestScheduleFPack(HagStringList cbEnvs1,String sentBy){
	//String listBackUpsS=Init.getAllBackups();
	String ansCheck = HagUtil.checkTOPACK("\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V0801m00\\TOPACK\\ScheduleF\\SFv1.0\\",HagUtil.cfgMenuWebLoc+"\\lists\\schedulefFiles.txt");
	if (ansCheck != null)
		return ansCheck;

	
	
	StringBuilder ans =new StringBuilder();
	

	String bkupF = HagUtil.cfgMenuWebLoc+"\\getBackups.html";
		ans.append("<h3>Pack ScheduleF request</h3>");

		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"sendPackScheduleFRequest.jsp\">");
	
		ans.append("<table bgcolor=\"#aaaacc\" border=\"1\">");
		ans.append("<tr bgcolor=\"#aaaaaa\"><td>Version</td><td>sent by</td></tr>");
		ans.append("<tr>");
		
		//ver
		String scheduleFVer = "";
		String riVer = "";
		ans.append("<td><select  class=\"c30\" id=\"ver\" name =\"ver\" >");
		HagStringList ver1= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\scheduleFVersions.txt",true,"*",false);
		for(int i=0;i<ver1.size();i++) {
			String scheduleFVerLine = ver1.get(i);
			scheduleFVer = HagUtil.getWord0(scheduleFVerLine,"|", 0,true);
			riVer = HagUtil.getWord0(scheduleFVerLine,"|", 1,true);
			ans.append("<option  value=\"").append(scheduleFVer).append("\">").append(scheduleFVer).append("</option>");
		}
		ans.append("</select></td>");
		
		//sentby
		ans.append("<td><select name=\"sentBy\"><option value=\"").append(sentBy).append("\">").append(sentBy).append("</option></select></td>");	

		
		ans.append("</tr></table><br>");
		
		
		
		//note 
		ans.append("<table  bgcolor = \"#E6E6FA\" border = 1 CELLPADDING=3 style=\"white-space:nowrap;\">");
		ans.append("<tr><td>Note</td><td><input type=\"text\" name=\"note\" value=\"\"  size=\"150\"></td></tr>"); 
		ans.append("</table><br>");	

		
		ans.append("content:<br>");
		//migs
		ans.append("manual migs: (sorted)");
		ans.append("<table  bgcolor = \"#E6E6FA\" border = 1 CELLPADDING=3 style=\"white-space:nowrap;\">");
		ans.append("<tr bgcolor = \"#c4c4d8\">");
		ans.append("<td>Order</td>"); 
		ans.append("<td>Update or Run</td>"); 
		ans.append("<td>Version</td>");
		ans.append("<td>jClass</td>");
		ans.append("<td>ID</td>");
		ans.append("<td>Customer</td>");
		ans.append("<td>runType</td>");
		ans.append("<td>packed to hf</td>");
		ans.append("<td>Dev</td>");
		ans.append("<td>dt_Dev</td>");
		ans.append("<td>CM</td>");
		ans.append("<td>dt_CM</td>");
		ans.append("<td>Location</td>");
		ans.append("<td>Dist</td>");
		
		ans.append("</tr>");

		String style4 = "STYLE=\"color: #888888; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #E6E6FA;\" size=\"4\" maxlength=\"80\"";
		String style8 = "STYLE=\"color: #888888; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #E6E6FA;\" size=\"8\" maxlength=\"80\"";
		String style10 = "STYLE=\"color: #888888; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #E6E6FA;\" size=\"10\" maxlength=\"80\"";
		String style12 = "STYLE=\"color: #888888; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #E6E6FA;\" size=\"12\" maxlength=\"80\"";
		String style15 = "STYLE=\"color: #888888; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #E6E6FA;\" size=\"15\" maxlength=\"80\"";
		String style16 = "STYLE=\"color: #888888; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #E6E6FA;\" size=\"17\" maxlength=\"80\"";
		
		HagStringList cbEnvsSorted = sort(cbEnvs1);
		for(int i = 0;i<cbEnvsSorted.size();i++){
			String temp = cbEnvsSorted.get(i);
			HagStringList list = new HagStringList(temp,"~",true);
			ans.append("<tr>");
			ans.append("<td><select class=\"c30\" id=\"order\" name =\"order\" >");
			for(int k = 0 ; k < 10;k++){
				if(k==5)
					ans.append("<option class=\"c30\" value=\""+k+"\" selected>"+k+"</option>");
				else
					ans.append("<option class=\"c30\" value=\""+k+"\" >"+k+"</option>");
			}
			ans.append("</select></td>");
		
			ans.append("<td><select class=\"c30\" id=\"runOrUpdate\" name =\"runOrUpdate\" >");
			ans.append("<option class=\"c30\" value=\"UR\" selected>UpdateOrInsert+Run</option>");
			ans.append("<option class=\"c30\" value=\"U\" >UpdateOrInsert only</option>");
			ans.append("<option class=\"c30\" value=\"D\" >Delete only</option>");
			ans.append("<option class=\"c30\" value=\"DUR\" >Delete+Insert+Run</option>");
			ans.append("<option class=\"c30\" value=\"DU\" >Delete+Insert only</option>");
			ans.append("</select></td>");

			
			
			//ans.append("<td><input type=\"text\" name=\"odedKey8\" value=\""+list.get(8)+"\"  size=\"15\"></td>"); 
			
			ans.append("<td><input  "+style12+" type=\"text\" name=\"hf1\" value=\""+list.get(1)+"\"    size=\"20\"></td>");
			ans.append("<td><input  "+style10+" type=\"text\" name=\"hf2\" value=\""+list.get(3)+"\"    size=\"15\"></td>");
			ans.append("<td><input  "+style8+" type=\"text\"  name=\"hf3\" value=\""+list.get(2)+"\"    size=\"15\"></td>");
			ans.append("<td><input  "+style10+" type=\"text\" name=\"hf4\" value=\"{ALL}\"   size=\"15\"></td>");
			ans.append("<td><input  "+style4+" type=\"text\"  name=\"hf5\" value=\""+list.get(4)+"\"   ></td>");
			ans.append("<td><input  "+style10+" type=\"text\" name=\"hf6\" value=\""+list.get(5)+"\"    size=\"25\"></td>");
			ans.append("<td><input  "+style10+" type=\"text\" name=\"hf7\" value=\""+list.get(6)+"\"    size=\"25\"></td>");
			ans.append("<td><input  "+style16+" type=\"text\" name=\"hf8\" value=\""+list.get(7)+"\"    size=\"15\"></td>");
			ans.append("<td><input  "+style10+" type=\"text\" name=\"hf9\" value=\""+list.get(8)+"\"    size=\"15\"></td>");
			ans.append("<td><input  "+style16+" type=\"text\" name=\"hf10\" value=\""+list.get(9)+"\"    size=\"15\"></td>");
			ans.append("<td><input  "+style15+" type=\"text\" name=\"hf11\" value=\""+list.get(12)+"\"    size=\"15\"></td>");
			ans.append("<td><input  "+style15+" type=\"text\" name=\"hf12\" value=\"this_ver_only\"    ></td>");
			ans.append("</tr>");
		}
		ans.append("</table><br>");

		ans.append("other components:");
		ans.append("<br><table  bgcolor = \"#dddddd\" border = 1 CELLPADDING=3 style=\"white-space:nowrap;\">");
		ans.append("<tr bgcolor = \"#c4c4d8\"><td>Which component</td><td>What to do</td><td>Last modified </td></tr>");
		int[] error = {0,0};
		String location = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V"+riVer+"\\TOPACK\\ScheduleF\\SF"+scheduleFVer.substring(scheduleFVer.indexOf("_")+1,scheduleFVer.length());
		
		
		//jar
		ans.append("<tr bgcolor = \"#E6E6FA\"><td>sf-mng.jar:</td>");
		ans.append("<td>replace sf-mng.jar from "+location+"\\cm-mng.jar</td>");
		File sfMigF = new File(location+"\\sf-mng.jar");
		if(!sfMigF.exists() || !sfMigF.isFile()) {
			error[0]=1;
			ans.append("<td bgcolor = \"#ff4444\">File not found</td><tr>");
		}else {
			String sfMigFlastModified =  HagUtil.getFileLastModified(sfMigF, "dd/MM/yyyy-HH:ss");
			ans.append("<td>"+sfMigFlastModified+"</td><tr>");
		}

		//war
	//	ans.append("<tr bgcolor = \"#E6E6FA\"><td>cm-srv.war:</td>");
	//	ans.append("<td>replace cm-srv.war from "+location+"\\cm-srv.war</td>");
	//	File cmWarF = new File(location+"\\cm-srv.war");
	//	if(!cmWarF.exists() || !cmWarF.isFile()) {
	//		error[0]=1;
	//		ans.append("<td bgcolor = \"#ff4444\">File not found</td><tr>");
	//	}else {
	//		String cmWarFLasttModified =  HagUtil.getFileLastModified(cmWarF, "dd/MM/yyyy-HH:ss");
	//		ans.append("<td>"+cmWarFLasttModified+"</td><tr>");
	//	}

		
		//scripts
	//	ans.append("<tr bgcolor = \"#E6E6FA\"><td>ri-cm.zip:</td>");
	//	ans.append("<td>replace ri-cm.zip from "+location+"\\ri-cm.zip</td>");
	//	File cmZipF = new File(location+"\\ri-cm.zip");
	//	if(!cmZipF.exists() || !cmZipF.isFile()) {
	//		error[0]=1;
	//		ans.append("<td bgcolor = \"#ff4444\">File not found</td><tr>");
	//	}else {
	//		String cmZipFlastModified =  HagUtil.getFileLastModified(cmMigF, "dd/MM/yyyy-HH:ss");
	//		ans.append("<td>"+cmZipFlastModified+"</td><tr>");
	//	}
		
		ans.append("</table>");
		
		
		
		//INSTALL ON
		ans.append("<br>install on:");
		ans.append("<br><table  bgcolor = \"#dddddd\" border = 1 CELLPADDING=3 style=\"white-space:nowrap;\">");
		ans.append("<tr bgcolor = \"#c4c4d8\"><td>APP_Server</td><td>dbid's with , as delimiter.(for example:AB,GA,CF) </td></tr>");
		//HagStringList appServersList = new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\appServersList.txt",false,"xxssss",false);
		HagStringList appServersList =HagUtil.getAppServersList("WIN",false,false);
		
		String[] installOnId = {"installOn0","installOn1","installOn2","installOn3","installOn4","installOn5","installOn6","installOn7","installOn8","installOn9"};
		String[] installOnDb = {"installDb0","installDb1","installDb2","installDb3","installDb4","installDb5","installDb6","installDb7","installDb8","installDb9"};
		HagStringList selectedServers = new HagStringList();
		for(int kk=0;kk<10;kk++) {
			ans.append("<tr bgcolor = \"#E6E6FA\">");
			ans.append("<td><select class=\"c30\" id=\""+installOnId[kk]+"\" name =\""+installOnId[kk]+"\" >");
			ans.append("<option class=\"c30\" value=\"\"></option>");
			for(int kkk=0;kkk<appServersList.size();kkk++) {
					String tempServer =appServersList.get(kkk).trim(); 
				
					ans.append("<option class=\"c30\" value=\""+tempServer+"\">"+tempServer+"</option>");
			}
			ans.append("</select></td>");
			ans.append("<td><input type=\"text\" name=\""+installOnDb[kk]+"\" value=\"\"  size=\"65\"></td>"); 
			ans.append("</tr>");
		}
		ans.append("</table>");
		
		//if(error[0] ==1 || error[1] ==1 )
		//	ans.append("<br><br><font color = \"#ff4444\">cannot send the request - you need to fix the errors</font></FORM>");
	//	else
			ans.append("<br><br><INPUT TYPE=SUBMIT value=\"Send request\"></FORM>");
		return ans.toString();

	}
public static 		HagStringList	sort(HagStringList list){
	HashMap<Integer, String> myMap = new HashMap<Integer,String>();
	
	
	HagStringList list1 = new HagStringList();
	for(int i=0;i<list.size();i++){
		String temp = list.get(i);
		String w2= HagUtil.getWord0(temp,"~",2,true);
		int w2i = HagUtil.s2i(w2);
		myMap.put(w2i, temp);
	}
	  TreeMap<Integer, String> sorted = new TreeMap<Integer, String>(myMap);
	for(Integer key : sorted.keySet()) {
		list1.add(myMap.get(key));
	}
	return list1;
	
}
/*
public 				String 			sendPackScheduleRequest111111111111111111111111111(HttpServletRequest request, HttpServletResponse response){
	
	HagStringList ans = new HagStringList();
	HagStringList contentM = new HagStringList();
	StringBuilder ans1 = new StringBuilder("<br><br>cm team extra:<br>");
	HagStringList migsAndIom = new HagStringList();

	//ans.append("***********************<br>");
	//ans.append("* for future use only *<br>");
	//ans.append("***********************<br>");
	
	String sentBy 	= request.getParameter("sentBy");
	HagRc hagRc= new HagRc();
	
	String ver 	= request.getParameter("ver");
	
	
	String note 	= request.getParameter("note");
	//String instOn 	= request.getParameter("instOn");
	String[] installOnId = new String[10];
	String[] installOnDb = new String[10];
	//String aa = request.getParameter("installOn0");
	installOnId[0]= request.getParameter("installOn0");
	installOnId[1]= request.getParameter("installOn1");
	installOnId[2]= request.getParameter("installOn2");
	installOnId[3]= request.getParameter("installOn3");
	installOnId[4]= request.getParameter("installOn4");
	installOnId[5]= request.getParameter("installOn5");
	installOnId[6]= request.getParameter("installOn6");
	installOnId[7]= request.getParameter("installOn7");
	installOnId[8]= request.getParameter("installOn8");
	installOnId[9]= request.getParameter("installOn9");
	//String aa1 = request.getParameter("installDb0");
	installOnDb[0]= request.getParameter("installDb0");
	installOnDb[1]= request.getParameter("installDb1");
	installOnDb[2]= request.getParameter("installDb2");
	installOnDb[3]= request.getParameter("installDb3");
	installOnDb[4]= request.getParameter("installDb4");
	installOnDb[5]= request.getParameter("installDb5");
	installOnDb[6]= request.getParameter("installDb6");
	installOnDb[7]= request.getParameter("installDb7");
	installOnDb[8]= request.getParameter("installDb8");
	installOnDb[9]= request.getParameter("installDb9");
	String ff = ver+".req";
	String instOn1 = setInstOn(installOnId,installOnDb,ff,"cm_inst");
	
	String instOn2 =null;
	if(instOn1!=null) {
	 instOn2 = HagUtil.replaceStr(instOn1, "~", " ");
	 instOn2 = HagUtil.replaceStr(instOn2, ")", "");
	 instOn2 = HagUtil.replaceStr(instOn2, "(", "/");
	}
	

	
	String [] order	= request.getParameterValues("order");
	String [] runOrUpdate	= request.getParameterValues("runOrUpdate");
	
	String [] hf1	= request.getParameterValues("hf1");
	String [] hf2	= request.getParameterValues("hf2");
	String [] hf3	= request.getParameterValues("hf3");
	String [] hf4	= request.getParameterValues("hf4");
	String [] hf5	= request.getParameterValues("hf5");

	//String package1 = ver+"."+maint+"."+update+"."+hotfix; 
	//String ver1=ver.substring(0,2);
//	String ver2=ver.substring(2,4);
//	String package2 = ver1+"."+ver2+"M"+maint+"U"+update+"HF"+hotfix; 

//	HagRc hagRcCheck = new HagRc();
//	if(hf1!=null){
//		checkMigsList(hagRcCheck,hf1,hf2,hf3,hf4,hf5,package2);
//		if(hagRcCheck.rc!=0){
//			String printLog = hagRcCheck.log.convertToString("<br>");
//			return printLog;
//		}
//	}
	
	if (instOn2==null || instOn2.trim().length()==0)
		instOn2=".";
	if (note==null || note.trim().length()==0)
		note=".";
	
	contentM.add("Request to create scheduleF package<br>");
	ans.add("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\" border =\"1\">");
	contentM.add(ans.get(ans.size()-1));
	
	contentM.add("<tr><td bgColor=\"#cccccc\">Package</td><td>"+ver+"</td></tr>");
	contentM.add("<tr><td bgColor=\"#cccccc\">Date</td><td>$packageHrefDate$</td></tr>");
	
	ans.add("<tr><td bgColor=\"#cccccc\">Sent by</td><td>"+sentBy+"</td></tr>");
	contentM.add(ans.get(ans.size()-1));
	ans.add("<tr><td bgColor=\"#cccccc\">Install on</td><td>"+instOn2+"</td></tr>");
	contentM.add(ans.get(ans.size()-1));
	ans.add("<tr><td bgColor=\"#cccccc\">Note</td><td>"+note+"</td></tr>");
	contentM.add(ans.get(ans.size()-1));
	
	contentM.add("<tr><td bgColor=\"#cccccc\">Link to FTP</td><td><a href=\"$packageHref$\">$packageHref$</a></td></tr>");
	
	String packagePrep111111111 		= "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\cfg\\hotfix\\ARIA\\"+ver;
	ans.add("</table><br><br>");
	contentM.add(ans.get(ans.size()-1));
	contentM.add("Package content:<br>");
	ans.add("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\" border =\"1\">");
	contentM.add(ans.get(ans.size()-1));
	HagStringList toDoList = new HagStringList();
	//war
	//manual mig
	String[][] migs = null;
	String iomLoc = null;
	
	if(hf1!=null)
		migs = new String[hf1.length][2];
	
	
	
	//
	String dateTime = HagUtil.getDateTime("yyyy/MM/dd-HH:mm");
	StringBuilder stm3= new StringBuilder("Update "+HagParam.getConfg1DB()+".[dbo].[add_mig_scheduleF] ")
		.append("set manager='"+sentBy+"',dt_manager='"+dateTime+"',pack2ver='"+ver+"' where ");
	//.append("set manager='"+sentBy+"',dt_manager='"+dateTime+"' where ");
	//
	String stm3S ="";	
	StringBuilder migD =null;
	if(hf1!=null){
		migD =new StringBuilder("select * from RI.MIG_DETAILS where JCLASS IN(");
		for(int i = 0 ; i < hf1.length;i++){
			migs[i][1] = hf3[i]+","+hf3[i]+",com.sapiens.mig.ri.v050m00."+hf2[i]+","+hf5[i]+"---"+runOrUpdate[i]+"!"+hf4[i];
			migs[i][0]=order[i];
			if(i==0){
				stm3.append("(id=").append(hf3[i]+" and ver ='"+hf1[i]+"')");
				migD.append("'com.sapiens.mig.ri.v050m00.").append(hf2[i]).append("'");
			}else{
				stm3.append(" OR (id=").append(hf3[i]+" and ver ='"+hf1[i]+"')");
				migD.append(",'com.sapiens.mig.ri.v050m00.").append(hf2[i]).append("'");
			}
		}
		migD.append(")");
		stm3S = stm3.toString();		
		for(int k = 0 ; k < 10;k++){
			for(int i = 0 ; i < hf1.length;i++){
				if(migs[i][0].equals(""+k)){
					ans.add("<tr><td bgColor=\"#cccccc\">ManualMig - order="+k+"</td><td>"+migs[i][1]+"</td></tr>");
					contentM.add(ans.get(ans.size()-1));
					migsAndIom.add(migs[i][1]);
				}
				
			}
			
		}
		
	}
	//contentM.add("<tr><td bgColor=\"#cccccc\">War</td><td>cm-srv.war</td></tr>");
	//contentM.add("<tr><td bgColor=\"#cccccc\">Scripts</td><td>ri-cm.zip</td></tr>");
	//ans.add("<tr><td bgColor=\"#cccccc\">War</td><td>cm-srv.war</td></tr>");
	//ans.add("<tr><td bgColor=\"#cccccc\">Scripts</td><td>ri-cm.zip</td></tr>");
	ans.add("</table><br>");
	contentM.add(ans.get(ans.size()-1));
	

	
	
	
	
	
	
	ans1.append("<br>Content:");
		
		if(migsAndIom.size()>0)
			ans1.append("<br>Manual mig");
		//ans1.append("<br>cm-srv.war");
		//ans1.append("<br>ri-cm.zip");
		
		
		ans1.append("<br><br>Actions:");
	
		if(migsAndIom.size()>0)
			ans1.append("<br>Run manual migs");
		//ans1.append("<br>Replace cm-srv.war");
	//	ans1.append("<br>Replace ri-cm scripts");
		ans1.append("<br><br>Manual mig:");
		for(int i = 0 ; i < migsAndIom.size();i++){
			
			String temp = migsAndIom.get(i);
			
				ans1.append("<br>").append(migsAndIom.get(i).substring(0,migsAndIom.get(i).indexOf("---")));
				String wa = HagUtil.getWord0(temp,"---",0,true);
				String wb = HagUtil.getWord0(temp,"---",1,true);
				String run = HagUtil.getWord0(wb,"!",0,true);
				String cust = HagUtil.getWord0(wb,"!",1,true);
				String id = HagUtil.getWord0(wa,",",0,true);
				String jClass1 = HagUtil.getWord0(wa,",",2,true);
				String runType = HagUtil.getWord0(wa,",",3,true);
				String jClass = HagUtil.getWord0(jClass1,".",5,true);
				String www = 	id+"#"+jClass+"#"+runType+"#"+run+"#"+cust;
				toDoList.add("manualMigs~"+www);
			
		}
		
	
	
	String folder = "";
	folder = sendScheduleFRequest_prepPackage( hagRc,ver,   migD,toDoList);
	
	if(hagRc.rc!=0){
		String msg = "Failed to create "+folder+" folder.<br>"+hagRc.log.convertToString("<br>");
		return HagUtil.shortHtml(msg , "red", "bg");
	}
			
	contentM.writeToFile(folder+"\\mail.html",null,false);
	
	//String subject 	= "#Request to create hotfix "+ver+" M"+maint+" U"+update+" HF"+hotfix+" @BREAK-REQ@ sent by "+sentBy;
	String ccList 	= HagUtil.getRiMails("all");
	String toList 	= "david.ha@sapiens.com;gonen.s@sapiens.com;david.b@sapiens.com;stas.p@sapiens.com";
	
	
	toList 	= "david.ha@sapiens.com";
	 ccList 	= "david.ha@sapiens.com";
	 
	 
	String pre="#";
	boolean testOnly =false;
	if(!testOnly) {
		if(hf1!=null && hf1.length>0){
			HagSQL hagSQL = new HagSQL();
			int changed = hagSQL.updateStm("confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm3S);
			ans.add(changed+" rows in scheduleF table change to "+ver+" by "+sentBy+"<br>");
		}
	}
	if(testOnly) {
		toList="david.ha@sapiens.com";
		ccList="gonen.s@sapiens.com";
		pre="";
	}
	int ind = HagUtil.getRequestInd();
	
	String indS = HagUtil.padNumLeft(""+ind, 5, '0');
	
	//hagayhagay
	HagUtil.writeToRelDiary2("Request","WIN","PACK","scheduleF",".",".",".",".",".",sentBy,".",".");
	String subject 	= pre+"Request to create scheduleF "+ver+" package @BREAK-REQ@ #"+indS+"# sent by "+sentBy;
	if(!HagUtil.addRequest(ind,sentBy, ".",subject,"./.",".",".",instOn2,null,null,null,999))
		return HagUtil.shortHtml("Error adding request-mail sent to hagay & gonen, please call hagay or gonen", "red","bg");
	String ansS = ans.convertToString("");
	 String ans9=HagUtil.sendMail_hag(HagUtil.cfgTeamMail, toList,ccList, subject,ansS+"<br>"+ans1.toString());
	
		
	
		
	return ansS+"<br>"+ans9+"<br>"+ans1.toString();
}
*/
public 				String 			setInstOn(String[] installOnId, String[] installOnDb,String ff,String folder){
	HashMap<String, String> hmap = new HashMap<String, String>();	
	HagStringList keys = new HagStringList();
	StringBuilder ans = new StringBuilder();
	for(int i = 0;i<installOnId.length;i++) {
		if(installOnId[i]!=null && installOnId[i].trim().length() > 0) {
			if(installOnDb[i]!=null && installOnDb[i].trim().length() > 0) {
				String server = installOnId[i].trim();
				String val= hmap.get(server);
				String dbs = HagUtil.replaceStr(installOnDb[i].trim()," ",",");
				if(val==null) {
					hmap.put(server, dbs);
					keys.add(server);
				}else {
					hmap.put(server,hmap.get(server)+","+dbs);
				}
			}	
		}
	}
	for(int i = 0;i<keys.size();i++) {
		if(i>0)
			ans.append("~");
		ans.append(keys.get(i)).append("(").append(hmap.get(keys.get(i))).append(")");
	}
	
	HagStringList list = new HagStringList(ans.toString(),"~",true);
	
	list.writeToFile("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\"+folder+"\\"+ff, false);
	return ans.toString();
}
public 				String 			sendScheduleFRequest_prepPackage(HagRc hagRc,String ver,StringBuilder migD,HagStringList toDoList){
	String folderS = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\cfg\\scheduleF\\ARIA\\"+ver;
	String folderS1 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\cfg\\scheduleF\\ARIA\\backup\\";
	

	File folder = new File(folderS);
	if(folder.exists() && folder.isDirectory()){
		String date = HagUtil.getDateTime("yyyyMMdd_HHmmss");
		String  folderSto = folderS1+"_"+date;
		String ans2 = HagUtil.copyFolder( folderS,folderSto,false,false,"1","");
		hagRc.log.add(ans2);
		if(!ans2.startsWith("0~")){
			hagRc.rc=1;
			hagRc.log.add("Failed to backup "+folderS);
			return folderS;
		
		}
	}
	//create folders
	scheduleFRequest_buildFolders(hagRc,folderS);
	if(hagRc.rc!=0)	return folderS;
	

	//skel\\config.txt
	scheduleFRequest_configTxt(hagRc,folderS,ver);
	if(hagRc.rc!=0)	return folderS;
	
	
	//config\\todo.txt
	scheduleFRequest_todo(hagRc,folderS,toDoList,migD);
	if(hagRc.rc!=0)	return folderS;
	
	
	
	hagRc.log.writeToFile(folderS+"\\packLog.txt",null,false);
	return folderS;
	
}
public 				void 			scheduleFRequest_buildFolders(HagRc hagRc,String folderS){
	//folders
	HagUtil.reCreateFolder(hagRc,folderS);
	if(hagRc.rc!=0){
		hagRc.log.writeToFile(folderS+"\\packLog.txt",null,false);
		return;
	}
	HagUtil.reCreateFolder(hagRc,folderS+"\\config");
	HagUtil.reCreateFolder(hagRc,folderS+"\\fromTopack");
	HagUtil.reCreateFolder(hagRc,folderS+"\\skel");

	hagRc.log.writeToFile(folderS+"\\packLog.txt",null,false);
}
public 				void 			scheduleFRequest_configTxt(HagRc hagRc,String folderS,String ver){
	HagStringList config = new HagStringList();
	String CDdate = HagUtil.getDateTime("yyyy/MM/dd");
	String CDtime = HagUtil.getDateTime("HH:mm:ss");
	config.add("CDversion="+ver);
	config.add("CDdate="+CDdate);
	config.add("CDtime="+CDtime);
	config.add("REQversion="+ver);
	config.add("REQdate="+CDdate);
	config.add("REQtime="+CDtime);
	config.add("hagLog="+ver);
	config.add("hagJiraVer="+ver);
	config.writeToFile(folderS+"\\skel\\config.txt",null,false);
}
public 				void 			scheduleFRequest_todo(HagRc hagRc,String folderS,HagStringList list,StringBuilder migD){
	String toDo1 = "\\\\ri-archive\\Saptech-Archive\\RI\\HAT_pack\\skel_scheduleF\\bin\\config\\tree\\riInstallScheduleF\\todo1.txt";
	String toDo2 = "\\\\ri-archive\\Saptech-Archive\\RI\\HAT_pack\\skel_scheduleF\\bin\\config\\tree\\riInstallScheduleF\\todo2.txt";
	HagStringList toDoList1 = new HagStringList(toDo1,false,"asasa",false);
	HagStringList toDoList2 = new HagStringList(toDo2,false,"asasa",false);
	HagStringList toDoList = new HagStringList();
	HagStringList toDoListOrder1 = new HagStringList();
	HagStringList toDoListOrder2 = new HagStringList();
	HagStringList toDoListOrder3 = new HagStringList();
	HagStringList toDoListOrder4 = new HagStringList();
	HagStringList toDoListOrder5 = new HagStringList();
	//fromTopack\\hotFixMigDetails.sql
	if(migD!=null){
		String migDstr=migD.toString();
    	HagStringList temp122 = new HagStringList();
		temp122.add(migDstr);
		temp122.writeToFile(folderS+"\\fromTopack\\scheduleFMigDetails.sql",null,false);
		//toDoListOrder2.add("*clientModuleMigDetails_before  | clientModuleMigDetails_before   | Y | N | Y | 50 | clientModuleMigDetails_before| #RiPackage,SQL_server,Bulk_user,Bulk_pass,Database_name                                                    | 1 | N");     
		}
	//todo

	
	toDoList.append(toDoList1);
	
	for(int i = 0;i<list.size();i++){
		String temp = list.get(i);
		String  w0 = HagUtil.getWord0(temp,"~",0,true);
		String  w1 = HagUtil.getWord0(temp,"~",1,true);
		if(w0.equals("replaceJar")){
			toDoListOrder1.add("Replace Jar              | ReplaceJar#batch-MSS      | Y | N | Y | 50 | Replace Jar        | !"+w1+",riJava_folder,#RiPackage                                                                           | 1 | N");      
		}
		if(w0.equals("replaceScripts")){
			toDoListOrder4.add("ReplaceAdd Scripts    | ReplaceAddScripts        | Y | N | Y | 50 | ReplaceAdd Scripts | Batch_name,#RiPackage                                                                                      | 1 | N");
		}
		if(w0.equals("manualMigs")){
			//String www = 	id+"#"+jClass+"#"+runType+"#"+runType+"#"+cust;
			String  id = HagUtil.getWord0(w1,"#",0,true);
			String  jClass = HagUtil.getWord0(w1,"#",1,true);
			String  runType = HagUtil.getWord0(w1,"#",2,true);
			String  run = HagUtil.getWord0(w1,"#",3,true);
			String  cust = HagUtil.getWord0(w1,"#",4,true);
			String ww= id+"#"+jClass+"#"+runType;
			toDoListOrder2.add("Manual Mig "+ww+"          | sfManualMig#"+ww+"    | Y | N | Y | 50 |     Manual Mig     | riJava_folder,Batch_name,#sfPackage,!"+ww+",SQL_server,Database_name,Bulk_user,Bulk_pass,!"+run+",!"+cust+"                                            | 1 | N");                                                                                                                                                                                                                       

		}
	}
//	if(migD!=null){
//		toDoListOrder2.add("*clientModuleMigDetails_after   | clientModuleMigDetails_after    | Y | N | Y | 50 | clientModuleMigDetails_after| #RiPackage,SQL_server,Bulk_user,Bulk_pass,Database_name                                                    | 1 | N");     
//	}

	toDoList.append(toDoListOrder1);
	toDoList.append(toDoListOrder2);
	toDoList.append(toDoListOrder3);
	toDoList.append(toDoListOrder4);
	toDoList.append(toDoListOrder5);
	toDoList.append(toDoList2);

	toDoList.writeToFile(folderS+"\\config\\todo.txt",null,false);

}
public 				String 			sendPackScheduleFRequest(HttpServletRequest request, HttpServletResponse response){
	
	HagStringList ans = new HagStringList();
	HagStringList contentM = new HagStringList();
	StringBuilder ans1 = new StringBuilder("<br><br>cm team extra:<br>");
	HagStringList migsAndIom = new HagStringList();

	//ans.append("***********************<br>");
	//ans.append("* for future use only *<br>");
	//ans.append("***********************<br>");
	
	String sentBy 	= request.getParameter("sentBy");
	HagRc hagRc= new HagRc();
	
	String ver 	= request.getParameter("ver");
	
	
	String note 	= request.getParameter("note");
	//String instOn 	= request.getParameter("instOn");
	String[] installOnId = new String[10];
	String[] installOnDb = new String[10];
	//String aa = request.getParameter("installOn0");
	installOnId[0]= request.getParameter("installOn0");
	installOnId[1]= request.getParameter("installOn1");
	installOnId[2]= request.getParameter("installOn2");
	installOnId[3]= request.getParameter("installOn3");
	installOnId[4]= request.getParameter("installOn4");
	installOnId[5]= request.getParameter("installOn5");
	installOnId[6]= request.getParameter("installOn6");
	installOnId[7]= request.getParameter("installOn7");
	installOnId[8]= request.getParameter("installOn8");
	installOnId[9]= request.getParameter("installOn9");
	//String aa1 = request.getParameter("installDb0");
	installOnDb[0]= request.getParameter("installDb0");
	installOnDb[1]= request.getParameter("installDb1");
	installOnDb[2]= request.getParameter("installDb2");
	installOnDb[3]= request.getParameter("installDb3");
	installOnDb[4]= request.getParameter("installDb4");
	installOnDb[5]= request.getParameter("installDb5");
	installOnDb[6]= request.getParameter("installDb6");
	installOnDb[7]= request.getParameter("installDb7");
	installOnDb[8]= request.getParameter("installDb8");
	installOnDb[9]= request.getParameter("installDb9");
	String ff = ver+".req";
	String instOn1 = setInstOn(installOnId,installOnDb,ff,"cm_inst");
	
	String instOn2 =null;
	if(instOn1!=null) {
	 instOn2 = HagUtil.replaceStr(instOn1, "~", " ");
	 instOn2 = HagUtil.replaceStr(instOn2, ")", "");
	 instOn2 = HagUtil.replaceStr(instOn2, "(", "/");
	}
	

	
	String [] order	= request.getParameterValues("order");
	String [] runOrUpdate	= request.getParameterValues("runOrUpdate");
	
	String [] hf1	= request.getParameterValues("hf1");
	String [] hf2	= request.getParameterValues("hf2");
	String [] hf3	= request.getParameterValues("hf3");
	String [] hf4	= request.getParameterValues("hf4");
	String [] hf5	= request.getParameterValues("hf5");

	//String package1 = ver+"."+maint+"."+update+"."+hotfix; 
	//String ver1=ver.substring(0,2);
//	String ver2=ver.substring(2,4);
//	String package2 = ver1+"."+ver2+"M"+maint+"U"+update+"HF"+hotfix; 

//	HagRc hagRcCheck = new HagRc();
//	if(hf1!=null){
//		checkMigsList(hagRcCheck,hf1,hf2,hf3,hf4,hf5,package2);
//		if(hagRcCheck.rc!=0){
//			String printLog = hagRcCheck.log.convertToString("<br>");
//			return printLog;
//		}
//	}
	
	if (instOn2==null || instOn2.trim().length()==0)
		instOn2=".";
	if (note==null || note.trim().length()==0)
		note=".";
	
	contentM.add("Request to create ScheduleF package<br>");
	ans.add("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\" border =\"1\">");
	contentM.add(ans.get(ans.size()-1));
	
	contentM.add("<tr><td bgColor=\"#cccccc\">Package</td><td>"+ver+"</td></tr>");
	contentM.add("<tr><td bgColor=\"#cccccc\">Date</td><td>$packageHrefDate$</td></tr>");
	
	ans.add("<tr><td bgColor=\"#cccccc\">Sent by</td><td>"+sentBy+"</td></tr>");
	contentM.add(ans.get(ans.size()-1));
	ans.add("<tr><td bgColor=\"#cccccc\">Install on</td><td>"+instOn2+"</td></tr>");
	contentM.add(ans.get(ans.size()-1));
	ans.add("<tr><td bgColor=\"#cccccc\">Note</td><td>"+note+"</td></tr>");
	contentM.add(ans.get(ans.size()-1));
	
	contentM.add("<tr><td bgColor=\"#cccccc\">Link to FTP</td><td><a href=\"$packageHref$\">$packageHref$</a></td></tr>");
	
	String packagePrep111111111 		= "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\cfg\\hotfix\\ARIA\\"+ver;
	ans.add("</table><br><br>");
	contentM.add(ans.get(ans.size()-1));
	contentM.add("Package content:<br>");
	ans.add("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\" border =\"1\">");
	contentM.add(ans.get(ans.size()-1));
	HagStringList toDoList = new HagStringList();
	//war
	//manual mig
	String[][] migs = null;
	String iomLoc = null;
	
	if(hf1!=null)
		migs = new String[hf1.length][2];
	
	
	
	//
	String dateTime = HagUtil.getDateTime("yyyy/MM/dd-HH:mm");
	StringBuilder stm3= new StringBuilder("Update "+HagParam.getConfg1DB()+".[dbo].[add_mig_scheduleF] ")
		.append("set manager='"+sentBy+"',dt_manager='"+dateTime+"',pack2ver='"+ver+"' where ");
	//.append("set manager='"+sentBy+"',dt_manager='"+dateTime+"' where ");
	//
	String stm3S ="";	
	StringBuilder migD =null;
	if(hf1!=null){
		migD =new StringBuilder("select * from RI.MIG_DETAILS where JCLASS IN(");
		for(int i = 0 ; i < hf1.length;i++){
			migs[i][1] = hf3[i]+","+hf3[i]+",com.sapiens.mig.ri.v050m00."+hf2[i]+","+hf5[i]+"---"+runOrUpdate[i]+"!"+hf4[i];
			migs[i][0]=order[i];
			if(i==0){
				stm3.append("(id=").append(hf3[i]+" and ver ='"+hf1[i]+"')");
				migD.append("'com.sapiens.mig.ri.v050m00.").append(hf2[i]).append("'");
			}else{
				stm3.append(" OR (id=").append(hf3[i]+" and ver ='"+hf1[i]+"')");
				migD.append(",'com.sapiens.mig.ri.v050m00.").append(hf2[i]).append("'");
			}
		}
		migD.append(")");
		stm3S = stm3.toString();		
		for(int k = 0 ; k < 10;k++){
			for(int i = 0 ; i < hf1.length;i++){
				if(migs[i][0].equals(""+k)){
					ans.add("<tr><td bgColor=\"#cccccc\">ManualMig - order="+k+"</td><td>"+migs[i][1]+"</td></tr>");
					contentM.add(ans.get(ans.size()-1));
					migsAndIom.add(migs[i][1]);
				}
				
			}
			
		}
		
	}
	//contentM.add("<tr><td bgColor=\"#cccccc\">War</td><td>cm-srv.war</td></tr>");
	//contentM.add("<tr><td bgColor=\"#cccccc\">Scripts</td><td>ri-cm.zip</td></tr>");
	//ans.add("<tr><td bgColor=\"#cccccc\">War</td><td>cm-srv.war</td></tr>");
	//ans.add("<tr><td bgColor=\"#cccccc\">Scripts</td><td>ri-cm.zip</td></tr>");
	ans.add("</table><br>");
	contentM.add(ans.get(ans.size()-1));
	

	
	
	
	
	
	
	ans1.append("<br>Content:");
		
		if(migsAndIom.size()>0)
			ans1.append("<br>Manual mig");
	//	ans1.append("<br>cm-srv.war");
	//	ans1.append("<br>ri-cm.zip");
		
		
		ans1.append("<br><br>Actions:");
	
		if(migsAndIom.size()>0)
			ans1.append("<br>Run manual migs");
	//	ans1.append("<br>Replace cm-srv.war");
		//ans1.append("<br>Replace ri-cm scripts");
		ans1.append("<br><br>Manual mig:");
		for(int i = 0 ; i < migsAndIom.size();i++){
			
			String temp = migsAndIom.get(i);
			
				ans1.append("<br>").append(migsAndIom.get(i).substring(0,migsAndIom.get(i).indexOf("---")));
				String wa = HagUtil.getWord0(temp,"---",0,true);
				String wb = HagUtil.getWord0(temp,"---",1,true);
				String run = HagUtil.getWord0(wb,"!",0,true);
				String cust = HagUtil.getWord0(wb,"!",1,true);
				String id = HagUtil.getWord0(wa,",",0,true);
				String jClass1 = HagUtil.getWord0(wa,",",2,true);
				String runType = HagUtil.getWord0(wa,",",3,true);
				String jClass = HagUtil.getWord0(jClass1,".",5,true);
				String www = 	id+"#"+jClass+"#"+runType+"#"+run+"#"+cust;
				toDoList.add("manualMigs~"+www);
			
		}
		
	
	
	String folder = "";
	folder = sendScheduleFRequest_prepPackage( hagRc,ver,   migD,toDoList);
	
	if(hagRc.rc!=0){
		String msg = "Failed to create "+folder+" folder.<br>"+hagRc.log.convertToString("<br>");
		return HagUtil.shortHtml(msg , "red", "bg");
	}
			
	contentM.writeToFile(folder+"\\mail.html",null,false);
	
	//String subject 	= "#Request to create hotfix "+ver+" M"+maint+" U"+update+" HF"+hotfix+" @BREAK-REQ@ sent by "+sentBy;
	String ccList 	= HagUtil.getRiMails("all");
	String toList 	= "david.ha@sapiens.com;gonen.s@sapiens.com;david.b@sapiens.com;stas.p@sapiens.com";

	
	String pre="#";
	boolean testOnly =false;
	if(!testOnly) {
		if(hf1!=null && hf1.length>0){
			HagSQL hagSQL = new HagSQL();
			int changed = hagSQL.updateStm("confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm3S);
			ans.add(changed+" rows in ScheduleF table change to "+ver+" by "+sentBy+"<br>");
		}
	}
	if(testOnly) {
		toList="david.ha@sapiens.com";
		ccList="gonen.s@sapiens.com";
		pre="";
	}
	int ind = HagUtil.getRequestInd();
	
	String indS = HagUtil.padNumLeft(""+ind, 5, '0');
	
	//hagayhagay
	String subject 	= pre+"Request to create ScheduleF "+ver+" package @BREAK-REQ@ #"+indS+"# sent by "+sentBy;
	if(!HagUtil.addRequest(ind,sentBy, ".",subject,"./.",".",".",instOn2,null,null,null,999))
		return HagUtil.shortHtml("Error adding request-mail sent to hagay & gonen, please call hagay or gonen", "red","bg");
	String ansS = ans.convertToString("");
	 String ans9=HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+sentBy,HagUtil.mailList_hag,subject,ansS+"<br>"+ans1.toString());
	return ansS+"<br>"+ans9+"<br>"+ans1.toString();
}

}

