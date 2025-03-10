package com.hag.hagay;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

public final class HagJdbc {
	
	public static Connection getSqlConn(String server,String user,String pass,String db,String creator){
		String url=null;
		Connection con = null;
		try {
			String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			url = "jdbc:sqlserver://"+server+";Database="+db;
			Class.forName(driverName);
			con = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			System.out.println("Could not find the database driver - "+e.getMessage());
		} catch (SQLException e) {
			System.out.println("Could not connect to the database - "+e.getMessage());
		}
		return con;
	}
	public static final String 	replaceColName(String name){
		if(name.equals("version")){
			return "RI<br>version";
		}else if(name.equals("patch")){
			return "RI<br>patch";
		}else if(name.equals("lastGoodInst")){
			return "Installation<br>OK/failed";
		}else if(name.equals("batchName")){
			return "batch<br>name";
		}else if(name.equals("lastInst")){
			return "last installation<font color=#ff2222>(includes falseInst)</font><br>RiVersion - CDdate - packageName";
		}else if(name.equals("party")){
			return "customer";
		}else if(name.equals("emerge_port")){
			return "emerge<br>port";
		}else if(name.equals("connection_port")){
			return "conn<br>port";
		}else if(name.equals("server_port")){
			return "server<br>port";
		}else if(name.equals("debug_port")){
			return "debug<br>port";
		}else if(name.equals("connPort")){
			return "conn<br>port";
		}else if(name.equals("serverPort")){
			return "server<br>port";
		}else if(name.equals("eMergePort")){
			return "eMerge<br>port";
		}else if(name.equals("iWayServer")){
			return "iWay<br>server";
		}else if(name.equals("iWayLink")){
			return "iWay<br>link";

		}else if(name.equals("bis_server")){
			return "app<br>server";
		}else if(name.equals("sql_server")){
			return "sql<br>server";
		}
		return name;
	}
	public static final String 	replaceColNameAzure(String name,int i){
		if(name.equals("version")){
			return ".";
		}else if(name.equals("patch")){
			return ".";
		}else if(name.equals("lastGoodInst")){
			return "Installation<br>OK/failed";
		}else if(name.equals("batchName")){
			return "batch<br>name";
		}else if(name.equals("lastInst")){
			return "last installation<font color=#ff2222>(includes falseInst)</font><br>RiVersion - CDdate - packageName";
		}else if(name.equals("party")){
			return "customer";
		}else if(name.equals("emerge_port")){
			return ".";
		}else if(name.equals("connection_port")){
			return ".";
		}else if(name.equals("server_port")){
			return ".";
		}else if(name.equals("debug_port")){
			return ".";
		}else if(name.equals("connPort")){
			return ".";
		}else if(name.equals("serverPort")){
			return ".";
		}else if(name.equals("eMergePort")){
			return ".";
		}else if(name.equals("iWayServer")){
			return ".";
		}else if(name.equals("iWayLink")){
			return "iWay<br>link";

		}else if(name.equals("bis_server")){
			return "app<br>server";
		}else if(name.equals("sql_server")){
			return "sql<br>server";
		}else if(name.equals("oded")){
			return ".";
		
	}else if(name.equals("")){
		if(i==1)
		return "APP server";
		else if(i==10)
			return "SQL server";
		else
			return ".";
	}
		return name;
	}
	public static final String 	replaceVal(int ind,String val,String lastGoodInst){
		val=val.trim();
		//lastGoodInst=lastGoodInst.trim();
		if(ind == 3){
			return HagUtil.getCustomerByPartyShort(val);
		
		
		//}else if(ind ==4){
		//	if(val.endsWith("NA"))
			//	return val;
	//		return val.substring(0,val.indexOf("-")).trim();
	//	}else if(ind ==5){
	//		if(val.endsWith("NA"))
	//			return val;
	//		if(lastGoodInst.endsWith(val)){
	//			return "NO";
	//		}else{
	//			return "YES";
	//		}
		}
		return val;
	}
	public static final String 	replaceValLinks(int ind ,String val,String dateTime,String act,String subAct){
		val=val.trim();
		dateTime=dateTime.trim();
		String ff = HagUtil.replaceStr(dateTime,"  "," ");
		ff = HagUtil.replaceStr(ff," ","_");
		ff = HagUtil.replaceStr(ff,":","_");
		ff = HagUtil.replaceStr(ff,".","_");
		ff = HagUtil.replaceStr(ff,"/","_");
		ff = HagUtil.replaceStr(ff,"-","_");
	
		//String ff1 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\logs\\"+ff+".html";
		//String ff1 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\logs\\aaa.html";
		//String ff1 = "\\\\ri-archive\\cfgMenuWeb_logs\\"+ff+".html";
		//String ff2="##hyundai.sapiens.com#hyun01log#cfg#cfgmenuWeb#logs#"+ff+"#.html";
		//System.out.println(ff1);
		//String ff1 = "l:\\cfg\\cfgMenuWeb\\logs\\"+ff+".html";
		String key = val+"~"+dateTime;
		if(ind ==10){
			if(val.equals("."))
				return val;
			if(act.equals("Install")){ 															//open folder
				//HagUtil.p(key+val);
				return "<a href=\"openFolderT.jsp?value="+key+"\">"+val+"</a>";	
			}else if(	(act.equals("Release")&& subAct.equals("CREONE")||subAct.equals("Request")) || act.equals("Replace")	){
				return "<a href=\"openFileFixed.jsp?value="+ff+"\">"+val+"</a>";			
			}else
				return "<a href=\"prcLink.jsp?value="+key+"\">"+val+"</a>";

		}else
			return val;
	}
	public static final String 	getPerWidth(int i){
	
		switch(i){
			case 0 : return "width=:1px ";
			case 1 : return "width=:2px ";
			case 2 : return "width=:1px ";
			case 3 : return "width=:1px ";
			case 4 : return "width=:1px ";
			case 5 : return "width=:1px ";
			case 6 : return "width=:1px ";
			case 7 : return "width=:1px ";
			case 8 : return "width=:1px ";
			case 9 : return "width=:1px ";
			case 10 : return "width=:1px ";
			case 11 : return "width=:1px ";
			case 12 : return "width=:1px ";
			case 13 : return "width=:1px ";
			case 14 : return "width=:1px ";
			case 15 : return "width=:1px ";
			case 16 : return "width=:1px ";
			case 17 : return "width=:1px ";
			case 18 : return "width=:21px ";
			default : return "width=:1px ";
		}
	}
	public static final void 	updateListRemoveDate(HagStringList list,boolean keepM){
		for(int i = 0;i<list.size();i++){
			String temp = list.get(i);
			if(keepM)
				temp=temp.substring(0,temp.indexOf("-")+1);
			else
				temp=temp.substring(0,temp.indexOf("-"));
			
			list.set(i,temp);
		}
		list.dropDup();
	}
	

	public static final String  orderCustCombo(StringBuilder org,HagStringList custList , String custListFilter) {
		 Collections.sort(custList);
		 StringBuilder temp0= new StringBuilder();
		 for(int h = 0 ; h < custList.size();h++){
				String tmp =custList.get(h).trim();
							
				String sel="";
				if(custListFilter != null && tmp.equals(custListFilter)){
				//	HagUtil.p("t="+tmp+"-"+filter[i-1]);
					sel=" selected ";
				}
    			temp0.append("<option value=\""+tmp+"\" "+sel+">"+tmp+"</option>");
			}
			temp0.append("</select></td>");
			
			
		 String newS = temp0.toString();
		 String orgS = org.toString();
		 String tempS1 = HagUtil.replaceStr(orgS, "!!custListToReplace!!", newS);
		 return tempS1;
	}
			
	
	public static final String 	selectRiEnvs(String dbms,String server,String user,String pass,String db,String stm,HagStringList ans,boolean withTitles,String[] filter,String cfgMenuWebUser,String cfgMenuWebPass){
	//	String perWidth = "width=\"5%\"";
		HagStringList 	custList=new HagStringList();
		String			custListFilter = null;
		Connection con = getSqlConn( server, user, pass, db,"dbo");
	
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
	         		ans.add("<FORM METHOD=POST STYLE=\"margin: 0px; padding: 0px;\" name=\"Form11\" id=\"Form11\"  ACTION=\"filterRiEnvs.jsp\">");
	         		//combo
	         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebUser\" value = \""+cfgMenuWebUser+"\" >");
	         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+cfgMenuWebPass+"\" >");
	         		
	         		StringBuilder temp0 = new StringBuilder();
	         		//start for
		            for (int i = 0; i < colCount; i++) {
		            	//perWidth = getPerWidth(i);
		              	String colName = metaData.getColumnName(i + 1);
		               	if(i==0)
		           			temp0.append("<tr><td  >.</td>");
		               	else if(i==14)
		            		temp0.append("<td  colspan=\"7\">&nbsp; </td>");
		               	else if(i>14)
		            		temp0.append("");
		               	else if(i==4){//last inst
		             		temp0.append("<td   >");
		           			temp0.append("<select name=\"col4\"  onchange=\"this.form.submit()\">");
			           	//	temp0.append("<select name=\"col4\"  size=\"4\" onchange=\"this.form.submit()\">");
		             		
		             		HagStringList cc = new HagStringList();
		           			String stm1 = "select distinct "+colName+" from dbo.ri_environments_new where  status='A' and project <> 'HAGWIDTH'";
		           			String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
		           			updateListRemoveDate(cc,true);
		           			temp0.append("<option value=\"[ALL]\">[ALL]</option>");
		           			for(int h = 0 ; h < cc.size();h++){
		           				String tmp =cc.get(h).trim();
		           				//HagUtil.p(tmp);
		           				String sel = "";
		           				if(filter != null && tmp.equals(filter[i-1])){
			           					sel=" selected ";
		           				}
			           			temp0.append("<option value=\""+tmp+"\" "+sel+">"+tmp+"</option>");
		           			}
		           			temp0.append("</select></td>");
		            	}else if(i==5){ //false inst
		             		temp0.append("<td>");
		           			temp0.append("<select name=\"col5\"  onchange=\"this.form.submit()\">");
		           			HagStringList cc = new HagStringList();
		           			String stm1 = "select distinct "+colName+" from dbo.ri_environments_new where  status='A' and project <> 'HAGWIDTH'";
		           			String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
		           			temp0.append("<option value=\"[ALL]\">[ALL]</option>");
		           			for(int h = 0 ; h < cc.size();h++){
		           				String tmp =cc.get(h).trim();
		           				
		           				String sel = "";
		           				if(filter != null && tmp.equals(filter[i-1]))
		           					sel=" selected ";
			           			temp0.append("<option value=\""+tmp+"\" "+sel+">"+tmp+"</option>");
		           			}
		           			temp0.append("</select></td>");
		            	}else if(i==3){ 
		            		temp0.append("<td   >");
		           			temp0.append("<select name=\"col"+i+"\"  onchange=\"this.form.submit()\">");
		           			HagStringList cc = new HagStringList();
		           			String stm1 = "select distinct LEFT("+colName+", 10) from dbo.ri_environments_new where  status='A' and project <> 'HAGWIDTH'";
		           			String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
		           			temp0.append("<option value=\"[ALL]\">[ALL]</option>");
		           			for(int h = 0 ; h < cc.size();h++){
		           				String tmp11 =cc.get(h).trim();
		           				tmp11 =  HagUtil.getCustomerByPartyShort(tmp11).trim();
		           				custList.add(tmp11);
		           				if(filter != null && tmp11.equals(filter[i-1])){
			           				custListFilter=tmp11;
		           				}
		           			}
		           			
		           		
		           			temp0.append("!!custListToReplace!!");
		            	}else if(i==4){ 
		            		temp0.append("<td   >");
		           			temp0.append("<select name=\"col"+i+"\"  onchange=\"this.form.submit()\">");
		           			HagStringList cc = new HagStringList();
		           			String stm1 = "select distinct LEFT("+colName+", 10) from dbo.ri_environments_new where  status='A' and project <> 'HAGWIDTH'";
		           			String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
		           			temp0.append("<option value=\"[ALL]\">[ALL]</option>");
		           			for(int h = 0 ; h < cc.size();h++){
		           				String tmp =cc.get(h).trim();
		           				if(i == 3)
		           					tmp =  HagUtil.getCustomerByPartyShort(tmp).trim();
		           				if(i == 4){
		           					//HagUtil.p(tmp);
		           					tmp = tmp.substring(0,tmp.indexOf("-")).trim();
		           				}
		           				
		           				String sel="";
		           				if(filter != null && tmp.equals(filter[i-1])){
		           				//	HagUtil.p("t="+tmp+"-"+filter[i-1]);
		           					sel=" selected ";
		           				}
			           			temp0.append("<option value=\""+tmp+"\" "+sel+">"+tmp+"</option>");
		           			}
		           			temp0.append("</select></td>");
		            
		             	}else{
		           			temp0.append("<td   >");
		           			temp0.append("<select  name=\"col"+i+"\"  onchange=\"this.form.submit()\">");
		           			
		           			
		           			HagStringList cc = new HagStringList();
		           			String stm1 = "select distinct "+colName+" from dbo.ri_environments_new where  status='A' and project <> 'HAGWIDTH'";
		           			String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
		           			temp0.append("<option value=\"[ALL]\">[ALL]</option>");
		           			for(int h = 0 ; h < cc.size();h++){
		           				String tmp =cc.get(h).trim();
		           				if(i == 3)
		           					tmp =  HagUtil.getCustomerByPartyShort(tmp).trim();
		           				if(i == 4){
		           					//HagUtil.p(tmp);
		           					tmp = tmp.substring(0,tmp.indexOf("-")).trim();
		           				}
		           				
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
		            String temp0sorted = orderCustCombo(temp0,custList,custListFilter);
		            //for end
		            first=false;
		           // HagUtil.p(temp0.toString());
		           
		            ans.add(temp0sorted);
	         		   		            
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
	               			temp1.append("<th >").append(replaceColName(colName)).append("</th>");
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
       				/*
           	    	.append("<option class=\"c10\" value=\"Dev: Select action to run\">Dev: Select action to run</option>")
           			.append("<option class=\"c11\" value=\"----------Display\">----------Display</option>")
           			.append("<option class=\"c11\" value=\"Display env details\">Display env details</option>")
           			.append("<option class=\"c11\" value=\"Display env installations\">Display env installations</option>")
      				.append("<option class=\"c11\" value=\"Display env status\">Display env status</option>")
           			.append("<option class=\"c11\" value=\"Display db size\">Display db size</option>")
           			.append("<option class=\"c12\" value=\"----------Stop\">----------Stop</option>")
           			.append("<option class=\"c12\" value=\"Stop eMerge listener\">Stop eMerge listener</option>")
           			.append("<option class=\"c12\" value=\"Stop tomcat\">Stop tomcat</option>")
           			.append("<option class=\"c12\" value=\"Stop tomcat and eMerge listener\">Stop tomcat and eMerge listener</option>")
           			.append("<option class=\"c13\" value=\"----------Start\">----------Start</option>")
           			.append("<option class=\"c13\" value=\"Start eMerge listener\">Start eMerge listener</option>")
           			.append("<option class=\"c13\" value=\"Start tomcat\">Start tomcat</option>")
           			.append("<option class=\"c13\" value=\"Start tomcat and eMerge listener\">Start tomcat and eMerge listener</option>")
           			.append("<option class=\"c13\" value=\"----------Start\">----------Start</option>")
           			.append("<option class=\"c13\" value=\"Start eMerge listener\">Start eMerge listener</option>")
           			.append("<option class=\"c13\" value=\"Start tomcat\">Start tomcat</option>")
           			.append("<option class=\"c13\" value=\"Start tomcat and eMerge listener\">Start tomcat and eMerge listener</option>")
           			devCombo.append("<option class=\"c14\" value=\"----------I-WAY\">----------I-WAY</option>")
           			.append("<option class=\"c14\" value=\"Start environment I-WAY (RUN)\">Login to I-WAY environment(RUN)</option>")
           			.append("<option class=\"c14\" value=\"Start server I-WAY (Administration)\">Open I-WAY server(Administration)</option>");
           			//.append("<option style=\"background-color: yellow\" value=\"Display tomcat status\">Display tomcat status</option>")
           			//.append("<option style=\"background-color: yellow\" value=\"----------I-WAY\">----------I-WAY</option>")
           			//.append("<option style=\"background-color: yellow\" value=\"Login to env(i-way run)\">Login to env(i-way run)</option>")
          			//.append("<option style=\"background-color: yellow\" value=\"Login to server(i-way edit)\">Login to server(i-way edit)</option>")
           			*/
           	    	HagStringList list1 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riEnvsDevOptions.txt",true,"*",false);
           	    	for(int i=0;i<list1.size();i++)
           	    		devCombo.append(list1.get(i));
           	        devCombo.append("</select>");
           	            	    	
           	    	/*
           	    	.append("<option class=\"c20\" value=\"Request: Select action to run\">Request: Select action to run</option>")
           	    	.append("<option class=\"c21\" value=\"----------Installtion\">----------Installtion</option>")
           	    	.append("<option class=\"c21\" value=\"Install RI-logic\">Install RI-logic</option>")
           	    	*/
           	        requestCombo.append("<select style=\"background-color: yellow\" id=\"act2\" name =\"act2\" onchange=\"this.form.submit();this.selectedIndex = 0\">");
           	        HagStringList list2 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riEnvsReqOptions.txt",true,"*",false);
           	    	for(int i=0;i<list2.size();i++)
           	    		requestCombo.append(list2.get(i));
           	    	requestCombo.append("</select>");

           	    	/*
           	    	.append("<option class=\"c30\" value=\"Manager: Select action to run\">Manager: Select action to run</option>")
           	    	.append("<option class=\"c31\" value=\"----------Set\">----------Set</option>")
           	    	.append("<option class=\"c31\" value=\"Set oded.r key Win\">Set oded.r key</option>")
           	    	*/
           	    	managerCombo.append("<select class=\"c30\" id=\"act3\" name =\"act3\" onchange=\"this.form.submit();this.selectedIndex = 0\">");
         	    	HagStringList list3 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riEnvsManagerOptions.txt",true,"*",false);
           	    	for(int i=0;i<list3.size();i++)
           	    		managerCombo.append(list3.get(i));
           	    	managerCombo.append("</select>");
      
           			//.append("<INPUT TYPE=SUBMIT value=\"submit\">");
           	    	/*
		        	ans.add("<tr bgColor=\"#FFFF00\">" +
		        			"<td>"	+devCombo.toString()+"</td>" +
		        			
		        			"<td "+perWidth+" bgColor=\"#FFFF00\" colspan=\"1\">"+requestCombo.toString()+"</td>" +
		        		
		        			"<td "+perWidth+" bgColor=\"#FFFF00\" colspan=\"13\">"+managerCombo.toString()+"</td>" +
		        			"</tr>");
		        			*/
           	  	ans.add("<tr>" +devCombo.toString()+requestCombo.toString()+managerCombo.toString() +"</tr>");
	         	}
                String lastGoodInst="xx";
                //data
                HagStringList oneRow = new HagStringList();
               // HagUtil.p(oneRow);
                String rowColor ="";
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
                		oneRow.add("<tr #rowColor#><td nowrap><input type=\"checkbox\" name=\"cb\" id=\"cb\" value=\"{ID}\"></td>");
                		rsLine.append(val);
            		}else{
            			
            			rsLine.append("~").append(val);
                		if(filter == null){
                			if(i==5 && val.trim().equalsIgnoreCase("failed")){
                       			oneRow.add("<td  nowrap bgColor=#ff0000>"+replaceVal(i,val,lastGoodInst)+"</td>");
                	//		}else if(i==10 && val.trim().equals("failed-cm")){
                	//			rowColor="bgColor=#f39a27";
                	//			oneRow.add("<td  nowrap>failed-cm</td>");
                	//		}else if(i==10 && val.trim().equals("failed-dev")){
                	//			rowColor="bgColor=#f39a27";
                	//			oneRow.add("<td  nowrap>failed-dev</td>");
                			}else if(i==20 && val.trim().equalsIgnoreCase("lock-cm")){
                				rowColor="bgColor=#f39a27";
                				oneRow.add("<td  nowrap>lock-cm</td>");
                			}else if(i==20 && val.trim().equalsIgnoreCase("lock-dev")){
                				rowColor="bgColor=#976ed7";
                				oneRow.add("<td  nowrap>lock-dev</td>");
            				}else if(i==9 && val.trim().equalsIgnoreCase("hag-env")){
                				rowColor="bgColor=#ffff00";
                				oneRow.add("<td  nowrap>hag-env</td>");
                				//oneRow.add("<td  nowrap bgColor=#ffff00>hag-env</td>");
                			}else if(i==9 && val.trim().equalsIgnoreCase("to-delete")){
                   				rowColor="bgColor=#ff00ff";
                				oneRow.add("<td  nowrap>to-delete</td>");
                				//oneRow.add("<td  nowrap bgColor=#ff00ff>to-delete</td>");
                			}else if(i==9 && val.trim().equalsIgnoreCase("private")){
                   				rowColor="bgColor=#00ff00";
                				oneRow.add("<td  nowrap>private</td>");
                				//oneRow.add("<td  nowrap bgColor=#00ff00>private</td>");
                			
                		
                			
                			}else if(i==14){
                				String valsTrimed = val.trim();
                				int pos = valsTrimed.lastIndexOf("-");
                				String iwayServer=valsTrimed;
                				if(pos>0)
                					iwayServer = valsTrimed.substring(0,valsTrimed.lastIndexOf("-"));
                				oneRow.add("<td  nowrap>http://"+iwayServer+"/sapweb/Admin/Logon.htm?"+iwayServer+"&/sapweb&"+val.trim()+"</td>");
                			}else {
               					oneRow.add("<td  nowrap >"+replaceVal(i,val,lastGoodInst)+"</td>");
                			}
           					if(i==colCount-1)
                				oneRow.add("</tr>");
                		}else{
                			String aa = val;
                			String aaa = "val";
                			if(i==14 ){
                				String valsTrimed = val.trim();
                				int pos = valsTrimed.lastIndexOf("-");
                				String iwayServer=valsTrimed;
                				if(pos>0)
                					iwayServer = valsTrimed.substring(0,valsTrimed.lastIndexOf("-"));
                				oneRow.add("http://"+iwayServer+"/sapweb/Admin/Logon.htm?"+iwayServer+"&/sapweb&"+val.trim());
                			}else {
                				oneRow.add(replaceVal(i,val,lastGoodInst));
                			}
                		}
           
            		}
                
                }
                if(!rowColor.equals("")){
                	oneRow.replaceStr("#rowColor#", rowColor);
                }
                
            	if(filter == null){
            		String temp2a = oneRow.convertToString(" ");
            		String temp2b = HagUtil.replaceStr(temp2a,"{ID}", rsLine.toString());
            		ans.add(temp2b);
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
                		String temp2a1 = HagUtil.replaceStr(temp2a,"</td><td nowrap  >failed","</td><td nowrap  bgColor=#ff0000>failed");
                		//temp2a1 = HagUtil.replaceStr(temp2a1,"</td><td nowrap  >hag-env","</td><td nowrap  bgColor=#ffff00>hag-env");
                		//temp2a1 = HagUtil.replaceStr(temp2a1,"</td><td nowrap  >to-delete","</td><td nowrap  bgColor=#ff00ff>to-delete");
                	//	if(temp2a.indexOf(">failed-cm<")>-1)
                   	//		temp2a1 = HagUtil.replaceStr(temp2a1,"#rowColor#", "bgColor=#f39a27");
                	//	if(temp2a.indexOf(">failed-dev<")>-1)
                   	//		temp2a1 = HagUtil.replaceStr(temp2a1,"#rowColor#", "bgColor=#f39a27");
                		if(temp2a.indexOf(">lock-cm")>-1)
                   			temp2a1 = HagUtil.replaceStr(temp2a1,"#rowColor#", "bgColor=#f39a27");
                		if(temp2a.indexOf(">lock-dev")>-1)
                   			temp2a1 = HagUtil.replaceStr(temp2a1,"#rowColor#", "bgColor=#976ed7");
                		if(temp2a.indexOf(">hag-env<")>-1)
                   			temp2a1 = HagUtil.replaceStr(temp2a1,"#rowColor#", "bgColor=#ffff00");
                   		if(temp2a.indexOf(">to-delete<")>-1)
                   			temp2a1 = HagUtil.replaceStr(temp2a1,"#rowColor#", "bgColor=#ff00ff");
                   		if(temp2a.indexOf(">private<")>-1)
                   			temp2a1 = HagUtil.replaceStr(temp2a1,"#rowColor#", "bgColor=#00ff00");
                   	
                   		String temp2b = HagUtil.replaceStr(temp2a1,"{ID}",rsLine.toString());
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
	     //  	         HagUtil.p(ans);
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
	
	public static final String 	selectRiEnvsAzure(String dbms,String server,String user,String pass,String db,String stm,HagStringList ans,boolean withTitles,String[] filter,String cfgMenuWebUser,String cfgMenuWebPass){
		//	String perWidth = "width=\"5%\"";
			HagStringList 	custList=new HagStringList();
			String			custListFilter = null;
			Connection con = getSqlConn( server, user, pass, db,"dbo");
		
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
		         		ans.add("<FORM METHOD=POST STYLE=\"margin: 0px; padding: 0px;\" name=\"Form11\" id=\"Form11\"  ACTION=\"filterRiEnvsAzure.jsp\">");
		         		//combo
		         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebUser\" value = \""+cfgMenuWebUser+"\" >");
		         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+cfgMenuWebPass+"\" >");
		         		
		         		StringBuilder temp0 = new StringBuilder();
		         		//start for
			            for (int i = 0; i < colCount; i++) {
			            	//perWidth = getPerWidth(i);
			              	String colName = metaData.getColumnName(i + 1);
			            //	System.out.println("="+colName+"=");
			               	if(i==0)
			           			temp0.append("<tr><td  >.</td>");
			               	else if(i==14)
			            		temp0.append("<td  colspan=\"7\">&nbsp; </td>");
			               	else if(i>14)
			            		temp0.append("");
			               	else if(i==4){//last inst
			             		temp0.append("<td   >");
			           			temp0.append("<select name=\"col4\"  onchange=\"this.form.submit()\">");
			           			HagStringList cc = new HagStringList();
			           			String stm1 = "select distinct "+colName+" from dbo.ri_environments_EXT where  status='A' and project <> 'HAGWIDTH'";
			           			String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
			           			updateListRemoveDate(cc,true);
			           			temp0.append("<option value=\"[ALL]\">[ALL]</option>");
			           			for(int h = 0 ; h < cc.size();h++){
			           				String tmp =cc.get(h).trim();
			           				//HagUtil.p(tmp);
			           				String sel = "";
			           				if(filter != null && tmp.equals(filter[i-1])){
				           					sel=" selected ";
			           				}
				           			temp0.append("<option value=\""+tmp+"\" "+sel+">"+tmp+"</option>");
			           			}
			           			temp0.append("</select></td>");
			            	}else if(i==5){ //false inst
			             		temp0.append("<td>");
			           			temp0.append("<select name=\"col5\"  onchange=\"this.form.submit()\">");
			           			HagStringList cc = new HagStringList();
			           			String stm1 = "select distinct "+colName+" from dbo.ri_environments_EXT where  status='A' and project <> 'HAGWIDTH'";
			           			String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
			           			temp0.append("<option value=\"[ALL]\">[ALL]</option>");
			           			for(int h = 0 ; h < cc.size();h++){
			           				String tmp =cc.get(h).trim();
			           				
			           				String sel = "";
			           				if(filter != null && tmp.equals(filter[i-1]))
			           					sel=" selected ";
				           			temp0.append("<option value=\""+tmp+"\" "+sel+">"+tmp+"</option>");
			           			}
			           			temp0.append("</select></td>");
			            	}else if(i==3){ 
			            		temp0.append("<td   >");
			           			temp0.append("<select name=\"col"+i+"\"  onchange=\"this.form.submit()\">");
			           			HagStringList cc = new HagStringList();
			           			String stm1 = "select distinct LEFT("+colName+", 10) from dbo.ri_environments_EXT where  status='A' and project <> 'HAGWIDTH'";
			           			String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
			           			temp0.append("<option value=\"[ALL]\">[ALL]</option>");
			           			for(int h = 0 ; h < cc.size();h++){
			           				String tmp11 =cc.get(h).trim();
			           				tmp11 =  HagUtil.getCustomerByPartyShort(tmp11).trim();
			           				custList.add(tmp11);
			           				if(filter != null && tmp11.equals(filter[i-1])){
				           				custListFilter=tmp11;
			           				}
			           			}
			           			
			           		
			           			temp0.append("!!custListToReplace!!");
			            	}else if(i==4){ 
			            		temp0.append("<td   >");
			           			temp0.append("<select name=\"col"+i+"\"  onchange=\"this.form.submit()\">");
			           			HagStringList cc = new HagStringList();
			           			String stm1 = "select distinct LEFT("+colName+", 10) from dbo.ri_environments_EXT where  status='A' and project <> 'HAGWIDTH'";
			           			String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
			           			temp0.append("<option value=\"[ALL]\">[ALL]</option>");
			           			for(int h = 0 ; h < cc.size();h++){
			           				String tmp =cc.get(h).trim();
			           				if(i == 3)
			           					tmp =  HagUtil.getCustomerByPartyShort(tmp).trim();
			           				if(i == 4){
			           					//HagUtil.p(tmp);
			           					tmp = tmp.substring(0,tmp.indexOf("-")).trim();
			           				}
			           				
			           				String sel="";
			           				if(filter != null && tmp.equals(filter[i-1])){
			           				//	HagUtil.p("t="+tmp+"-"+filter[i-1]);
			           					sel=" selected ";
			           				}
				           			temp0.append("<option value=\""+tmp+"\" "+sel+">"+tmp+"</option>");
			           			}
			           			temp0.append("</select></td>");
			            
			             	}else{
			             		//System.out.println(i+"="+colName+"=");
			           			temp0.append("<td   >");
			           			temp0.append("<select name=\"col"+i+"\"  onchange=\"this.form.submit()\">");
			           			HagStringList cc = new HagStringList();
			           		//	System.out.println("colName="+colName);
			           			if(colName==null||colName.trim().length()==0 ) {
			           				temp0.append("<option value=\"[ALL]\">[ALL]</option>");
			           			}else {
			           			
			           				String stm1 = "select distinct "+colName+" from dbo.ri_environments_EXT where  status='A' and project <> 'HAGWIDTH'";
			           				String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
			           				temp0.append("<option value=\"[ALL]\">[ALL]</option>");
			           			}
			           			for(int h = 0 ; h < cc.size();h++){
			           				String tmp =cc.get(h).trim();
			           				if(i == 3)
			           					tmp =  HagUtil.getCustomerByPartyShort(tmp).trim();
			           				if(i == 4){
			           					//HagUtil.p(tmp);
			           					tmp = tmp.substring(0,tmp.indexOf("-")).trim();
			           				}
			           				
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
			            String temp0sorted = orderCustCombo(temp0,custList,custListFilter);
			            //for end
			            first=false;
			           // HagUtil.p(temp0.toString());
			           
			            ans.add(temp0sorted);
		         		   		            
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
		               			temp1.append("<th >").append(replaceColNameAzure(colName,i)).append("</th>");
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
	       				/*
	           	    	.append("<option class=\"c10\" value=\"Dev: Select action to run\">Dev: Select action to run</option>")
	           			.append("<option class=\"c11\" value=\"----------Display\">----------Display</option>")
	           			.append("<option class=\"c11\" value=\"Display env details\">Display env details</option>")
	           			.append("<option class=\"c11\" value=\"Display env installations\">Display env installations</option>")
	      				.append("<option class=\"c11\" value=\"Display env status\">Display env status</option>")
	           			.append("<option class=\"c11\" value=\"Display db size\">Display db size</option>")
	           			.append("<option class=\"c12\" value=\"----------Stop\">----------Stop</option>")
	           			.append("<option class=\"c12\" value=\"Stop eMerge listener\">Stop eMerge listener</option>")
	           			.append("<option class=\"c12\" value=\"Stop tomcat\">Stop tomcat</option>")
	           			.append("<option class=\"c12\" value=\"Stop tomcat and eMerge listener\">Stop tomcat and eMerge listener</option>")
	           			.append("<option class=\"c13\" value=\"----------Start\">----------Start</option>")
	           			.append("<option class=\"c13\" value=\"Start eMerge listener\">Start eMerge listener</option>")
	           			.append("<option class=\"c13\" value=\"Start tomcat\">Start tomcat</option>")
	           			.append("<option class=\"c13\" value=\"Start tomcat and eMerge listener\">Start tomcat and eMerge listener</option>")
	           			.append("<option class=\"c13\" value=\"----------Start\">----------Start</option>")
	           			.append("<option class=\"c13\" value=\"Start eMerge listener\">Start eMerge listener</option>")
	           			.append("<option class=\"c13\" value=\"Start tomcat\">Start tomcat</option>")
	           			.append("<option class=\"c13\" value=\"Start tomcat and eMerge listener\">Start tomcat and eMerge listener</option>")
	           			devCombo.append("<option class=\"c14\" value=\"----------I-WAY\">----------I-WAY</option>")
	           			.append("<option class=\"c14\" value=\"Start environment I-WAY (RUN)\">Login to I-WAY environment(RUN)</option>")
	           			.append("<option class=\"c14\" value=\"Start server I-WAY (Administration)\">Open I-WAY server(Administration)</option>");
	           			//.append("<option style=\"background-color: yellow\" value=\"Display tomcat status\">Display tomcat status</option>")
	           			//.append("<option style=\"background-color: yellow\" value=\"----------I-WAY\">----------I-WAY</option>")
	           			//.append("<option style=\"background-color: yellow\" value=\"Login to env(i-way run)\">Login to env(i-way run)</option>")
	          			//.append("<option style=\"background-color: yellow\" value=\"Login to server(i-way edit)\">Login to server(i-way edit)</option>")
	           			*/
	           	    	HagStringList list1 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riEnvsDevOptionsAzure.txt",true,"*",false);
	           	    	for(int i=0;i<list1.size();i++)
	           	    		devCombo.append(list1.get(i));
	           	        devCombo.append("</select>");
	           	            	    	
	           	    	/*
	           	    	.append("<option class=\"c20\" value=\"Request: Select action to run\">Request: Select action to run</option>")
	           	    	.append("<option class=\"c21\" value=\"----------Installtion\">----------Installtion</option>")
	           	    	.append("<option class=\"c21\" value=\"Install RI-logic\">Install RI-logic</option>")
	           	    	*/
	           	        requestCombo.append("<select style=\"background-color: yellow\" id=\"act2\" name =\"act2\" onchange=\"this.form.submit();this.selectedIndex = 0\">");
	           	        HagStringList list2 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riEnvsReqOptionsAzure.txt",true,"*",false);
	           	    	for(int i=0;i<list2.size();i++)
	           	    		requestCombo.append(list2.get(i));
	           	    	requestCombo.append("</select>");

	           	    	/*
	           	    	.append("<option class=\"c30\" value=\"Manager: Select action to run\">Manager: Select action to run</option>")
	           	    	.append("<option class=\"c31\" value=\"----------Set\">----------Set</option>")
	           	    	.append("<option class=\"c31\" value=\"Set oded.r key Win\">Set oded.r key</option>")
	           	    	*/
	           	    	managerCombo.append("<select class=\"c30\" id=\"act3\" name =\"act3\" onchange=\"this.form.submit();this.selectedIndex = 0\">");
	         	    	HagStringList list3 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riEnvsManagerOptionsAzure.txt",true,"*",false);
	           	    	for(int i=0;i<list3.size();i++)
	           	    		managerCombo.append(list3.get(i));
	           	    	managerCombo.append("</select>");
	      
	           			//.append("<INPUT TYPE=SUBMIT value=\"submit\">");
	           	    	/*
			        	ans.add("<tr bgColor=\"#FFFF00\">" +
			        			"<td>"	+devCombo.toString()+"</td>" +
			        			
			        			"<td "+perWidth+" bgColor=\"#FFFF00\" colspan=\"1\">"+requestCombo.toString()+"</td>" +
			        		
			        			"<td "+perWidth+" bgColor=\"#FFFF00\" colspan=\"13\">"+managerCombo.toString()+"</td>" +
			        			"</tr>");
			        			*/
	           	  	ans.add("<tr>" +devCombo.toString()+requestCombo.toString()+managerCombo.toString() +"</tr>");
		         	}
	                String lastGoodInst="xx";
	                //data
	                HagStringList oneRow = new HagStringList();
	               // HagUtil.p(oneRow);
	                String rowColor ="";
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
	                		oneRow.add("<tr #rowColor#><td nowrap><input type=\"checkbox\" name=\"cb\" id=\"cb\" value=\"{ID}\"></td>");
	                		rsLine.append(val);
	            		}else{
	            			
	            			rsLine.append("~").append(val);
	                		if(filter == null){
	                			if(i==5 && val.trim().equalsIgnoreCase("failed")){
	                       			oneRow.add("<td  nowrap bgColor=#ff0000>"+replaceVal(i,val,lastGoodInst)+"</td>");
	                	//		}else if(i==10 && val.trim().equals("failed-cm")){
	                	//			rowColor="bgColor=#f39a27";
	                	//			oneRow.add("<td  nowrap>failed-cm</td>");
	                	//		}else if(i==10 && val.trim().equals("failed-dev")){
	                	//			rowColor="bgColor=#f39a27";
	                	//			oneRow.add("<td  nowrap>failed-dev</td>");
	                			}else if(i==20 && val.trim().equalsIgnoreCase("lock-cm")){
	                				rowColor="bgColor=#f39a27";
	                				oneRow.add("<td  nowrap>lock-cm</td>");
	                			}else if(i==20 && val.trim().equalsIgnoreCase("lock-dev")){
	                				rowColor="bgColor=#976ed7";
	                				oneRow.add("<td  nowrap>lock-dev</td>");
	            				}else if(i==9 && val.trim().equalsIgnoreCase("hag-env")){
	                				rowColor="bgColor=#ffff00";
	                				oneRow.add("<td  nowrap>hag-env</td>");
	                				//oneRow.add("<td  nowrap bgColor=#ffff00>hag-env</td>");
	                			}else if(i==9 && val.trim().equalsIgnoreCase("to-delete")){
	                   				rowColor="bgColor=#ff00ff";
	                				oneRow.add("<td  nowrap>to-delete</td>");
	                				//oneRow.add("<td  nowrap bgColor=#ff00ff>to-delete</td>");
	                			}else if(i==9 && val.trim().equalsIgnoreCase("private")){
	                   				rowColor="bgColor=#00ff00";
	                				oneRow.add("<td  nowrap>private</td>");
	                				//oneRow.add("<td  nowrap bgColor=#00ff00>private</td>");
	                			
	                		
	                			
	                			}else if(i==114){
	                				String valsTrimed = val.trim();
	                				int pos = valsTrimed.lastIndexOf("-");
	                				String iwayServer=valsTrimed;
	                				if(pos>0)
	                					iwayServer = valsTrimed.substring(0,valsTrimed.lastIndexOf("-"));
	                				oneRow.add("<td  nowrap>http://"+iwayServer+"/sapweb/Admin/Logon.htm?"+iwayServer+"&/sapweb&"+val.trim()+"</td>");
	                			}else {
	               					oneRow.add("<td  nowrap >"+replaceVal(i,val,lastGoodInst)+"</td>");
	                			}
	           					if(i==colCount-1)
	                				oneRow.add("</tr>");
	                		}else{
	                			String aa = val;
	                			String aaa = "val";
	                			if(i==114 ){
	                				String valsTrimed = val.trim();
	                				int pos = valsTrimed.lastIndexOf("-");
	                				String iwayServer=valsTrimed;
	                				if(pos>0)
	                					iwayServer = valsTrimed.substring(0,valsTrimed.lastIndexOf("-"));
	                				oneRow.add("http://"+iwayServer+"/sapweb/Admin/Logon.htm?"+iwayServer+"&/sapweb&"+val.trim());
	                			}else {
	                				oneRow.add(replaceVal(i,val,lastGoodInst));
	                			}
	                		}
	           
	            		}
	                
	                }
	                if(!rowColor.equals("")){
	                	oneRow.replaceStr("#rowColor#", rowColor);
	                }
	                
	            	if(filter == null){
	            		String temp2a = oneRow.convertToString(" ");
	            		String temp2b = HagUtil.replaceStr(temp2a,"{ID}", rsLine.toString());
	            		ans.add(temp2b);
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
	                		String temp2a1 = HagUtil.replaceStr(temp2a,"</td><td nowrap  >failed","</td><td nowrap  bgColor=#ff0000>failed");
	                		//temp2a1 = HagUtil.replaceStr(temp2a1,"</td><td nowrap  >hag-env","</td><td nowrap  bgColor=#ffff00>hag-env");
	                		//temp2a1 = HagUtil.replaceStr(temp2a1,"</td><td nowrap  >to-delete","</td><td nowrap  bgColor=#ff00ff>to-delete");
	                	//	if(temp2a.indexOf(">failed-cm<")>-1)
	                   	//		temp2a1 = HagUtil.replaceStr(temp2a1,"#rowColor#", "bgColor=#f39a27");
	                	//	if(temp2a.indexOf(">failed-dev<")>-1)
	                   	//		temp2a1 = HagUtil.replaceStr(temp2a1,"#rowColor#", "bgColor=#f39a27");
	                		if(temp2a.indexOf(">lock-cm")>-1)
	                   			temp2a1 = HagUtil.replaceStr(temp2a1,"#rowColor#", "bgColor=#f39a27");
	                		if(temp2a.indexOf(">lock-dev")>-1)
	                   			temp2a1 = HagUtil.replaceStr(temp2a1,"#rowColor#", "bgColor=#976ed7");
	                		if(temp2a.indexOf(">hag-env<")>-1)
	                   			temp2a1 = HagUtil.replaceStr(temp2a1,"#rowColor#", "bgColor=#ffff00");
	                   		if(temp2a.indexOf(">to-delete<")>-1)
	                   			temp2a1 = HagUtil.replaceStr(temp2a1,"#rowColor#", "bgColor=#ff00ff");
	                   		if(temp2a.indexOf(">private<")>-1)
	                   			temp2a1 = HagUtil.replaceStr(temp2a1,"#rowColor#", "bgColor=#00ff00");
	                   	
	                   		String temp2b = HagUtil.replaceStr(temp2a1,"{ID}",rsLine.toString());
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
		     //  	         HagUtil.p(ans);
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
		
	public static final String 	selectGardEnvs(String dbms,String server,String user,String pass,String db,String stm,HagStringList ans,boolean withTitles,String[] filter,String cfgMenuWebUser,String cfgMenuWebPass){
	//	String perWidth = "width=\"5%\"";
		Connection con = getSqlConn( server, user, pass, db,"dbo");
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
	         		ans.add("<FORM METHOD=POST STYLE=\"margin: 0px; padding: 0px;\" name=\"Form11\" id=\"Form11\"  ACTION=\"filterGardEnvs.jsp\">");
	         		//combo
	         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebUser\" value = \""+cfgMenuWebUser+"\" >");
	         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+cfgMenuWebPass+"\" >");
	         		
	         		StringBuilder temp0 = new StringBuilder();
		            for (int i = 0; i < colCount; i++) {
		            	//perWidth = getPerWidth(i);
		              	String colName = metaData.getColumnName(i + 1);
		               	if(i==0)
		           			temp0.append("<tr><td  >.</td>");
		               	else if(i==10)
		            		temp0.append("<td  colspan=\"6\">&nbsp; </td>");
		               	else if(i>10) {
		            		temp0.append("");
		             	}else{
		           			temp0.append("<td   >");
		           			temp0.append("<select name=\"col"+i+"\"  onchange=\"this.form.submit()\">");
		           			HagStringList cc = new HagStringList();
		           			String stm1 = "select distinct "+colName+" from dbo.gard_cloud where project <> 'HAGWIDTH'";
		           			String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
		           			temp0.append("<option value=\"[ALL]\">[ALL]</option>");
		           			for(int h = 0 ; h < cc.size();h++){
		           				String tmp =cc.get(h).trim();
		           				if(i == 3)
		           					tmp =  HagUtil.getCustomerByPartyShort(tmp).trim();
		           				if(i == 4){
		           					//HagUtil.p(tmp);
		           					tmp = tmp.substring(0,tmp.indexOf("-")).trim();
		           				}
		           				
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
	               			temp1.append("<th >").append(replaceColName(colName)).append("</th>");
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
       				/*
           	    	.append("<option class=\"c10\" value=\"Dev: Select action to run\">Dev: Select action to run</option>")
           			.append("<option class=\"c11\" value=\"----------Display\">----------Display</option>")
           			.append("<option class=\"c11\" value=\"Display env details\">Display env details</option>")
           			.append("<option class=\"c11\" value=\"Display env installations\">Display env installations</option>")
      				.append("<option class=\"c11\" value=\"Display env status\">Display env status</option>")
           			.append("<option class=\"c11\" value=\"Display db size\">Display db size</option>")
           			.append("<option class=\"c12\" value=\"----------Stop\">----------Stop</option>")
           			.append("<option class=\"c12\" value=\"Stop eMerge listener\">Stop eMerge listener</option>")
           			.append("<option class=\"c12\" value=\"Stop tomcat\">Stop tomcat</option>")
           			.append("<option class=\"c12\" value=\"Stop tomcat and eMerge listener\">Stop tomcat and eMerge listener</option>")
           			.append("<option class=\"c13\" value=\"----------Start\">----------Start</option>")
           			.append("<option class=\"c13\" value=\"Start eMerge listener\">Start eMerge listener</option>")
           			.append("<option class=\"c13\" value=\"Start tomcat\">Start tomcat</option>")
           			.append("<option class=\"c13\" value=\"Start tomcat and eMerge listener\">Start tomcat and eMerge listener</option>")
           			.append("<option class=\"c13\" value=\"----------Start\">----------Start</option>")
           			.append("<option class=\"c13\" value=\"Start eMerge listener\">Start eMerge listener</option>")
           			.append("<option class=\"c13\" value=\"Start tomcat\">Start tomcat</option>")
           			.append("<option class=\"c13\" value=\"Start tomcat and eMerge listener\">Start tomcat and eMerge listener</option>")
           			devCombo.append("<option class=\"c14\" value=\"----------I-WAY\">----------I-WAY</option>")
           			.append("<option class=\"c14\" value=\"Start environment I-WAY (RUN)\">Login to I-WAY environment(RUN)</option>")
           			.append("<option class=\"c14\" value=\"Start server I-WAY (Administration)\">Open I-WAY server(Administration)</option>");
           			//.append("<option style=\"background-color: yellow\" value=\"Display tomcat status\">Display tomcat status</option>")
           			//.append("<option style=\"background-color: yellow\" value=\"----------I-WAY\">----------I-WAY</option>")
           			//.append("<option style=\"background-color: yellow\" value=\"Login to env(i-way run)\">Login to env(i-way run)</option>")
          			//.append("<option style=\"background-color: yellow\" value=\"Login to server(i-way edit)\">Login to server(i-way edit)</option>")
           			*/
           	    	HagStringList list1 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\gardEnvsDevOptions.txt",true,"*",false);
           	    	for(int i=0;i<list1.size();i++)
           	    		devCombo.append(list1.get(i));
           	        devCombo.append("</select>");
           	            	    	

      
           			//.append("<INPUT TYPE=SUBMIT value=\"submit\">");
           	    	/*
		        	ans.add("<tr bgColor=\"#FFFF00\">" +
		        			"<td>"	+devCombo.toString()+"</td>" +
		        			
		        			"<td "+perWidth+" bgColor=\"#FFFF00\" colspan=\"1\">"+requestCombo.toString()+"</td>" +
		        		
		        			"<td "+perWidth+" bgColor=\"#FFFF00\" colspan=\"13\">"+managerCombo.toString()+"</td>" +
		        			"</tr>");
		        			*/
           	  	ans.add("<tr>" +devCombo.toString()+requestCombo.toString()+managerCombo.toString() +"</tr>");
	         	}
                String lastGoodInst="xx";
                //data
                HagStringList oneRow = new HagStringList();
               // HagUtil.p(oneRow);
                String rowColor ="";
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
                		oneRow.add("<tr #rowColor#><td nowrap><input type=\"checkbox\" name=\"cb\" id=\"cb\" value=\"{ID}\"></td>");
                		rsLine.append(val);
            		}else{
            			rsLine.append("~").append(val);
                		if(filter == null){
                			if(i==5 && val.trim().equals("failed")){
                       			oneRow.add("<td  nowrap bgColor=#ff0000>"+replaceVal(i,val,lastGoodInst)+"</td>");
                			}else if(i==9 && val.trim().equals("hag-env")){
                				rowColor="bgColor=#ffff00";
                				oneRow.add("<td  nowrap>hag-env</td>");
                				//oneRow.add("<td  nowrap bgColor=#ffff00>hag-env</td>");
                			}else if(i==9 && val.trim().equals("to-delete")){
                   				rowColor="bgColor=#ff00ff";
                				oneRow.add("<td  nowrap>to-delete</td>");
                				//oneRow.add("<td  nowrap bgColor=#ff00ff>to-delete</td>");
                			}else if(i==9 && val.trim().equals("private")){
                   				rowColor="bgColor=#00ff00";
                				oneRow.add("<td  nowrap>private</td>");
                				//oneRow.add("<td  nowrap bgColor=#00ff00>private</td>");
                			}else
               					oneRow.add("<td  nowrap >"+replaceVal(i,val,lastGoodInst)+"</td>");
                		}else{
                			oneRow.add(replaceVal(i,val,lastGoodInst));
                		}
           
            		}
                	
                }
                if(!rowColor.equals("")){
                	oneRow.replaceStr("#rowColor#", rowColor);
                }
                
            	if(filter == null){
            		String temp2a = oneRow.convertToString(" ");
            		String temp2b = HagUtil.replaceStr(temp2a,"{ID}", rsLine.toString());
            		ans.add(temp2b);
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
                		String temp2a1 = HagUtil.replaceStr(temp2a,"</td><td nowrap  >failed","</td><td nowrap  bgColor=#ff0000>failed");
                		//temp2a1 = HagUtil.replaceStr(temp2a1,"</td><td nowrap  >hag-env","</td><td nowrap  bgColor=#ffff00>hag-env");
                		//temp2a1 = HagUtil.replaceStr(temp2a1,"</td><td nowrap  >to-delete","</td><td nowrap  bgColor=#ff00ff>to-delete");
                   		if(temp2a.indexOf(">hag-env<")>-1)
                   			temp2a1 = HagUtil.replaceStr(temp2a1,"#rowColor#", "bgColor=#ffff00");
                   		if(temp2a.indexOf(">to-delete<")>-1)
                   			temp2a1 = HagUtil.replaceStr(temp2a1,"#rowColor#", "bgColor=#ff00ff");
                   		if(temp2a.indexOf(">private<")>-1)
                   			temp2a1 = HagUtil.replaceStr(temp2a1,"#rowColor#", "bgColor=#00ff00");
                   		String temp2b = HagUtil.replaceStr(temp2a1,"{ID}",rsLine.toString());
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
	     //  	         HagUtil.p(ans);
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
	
	public static final String 	selectRiServers(String dbms,String server,String user,String pass,String db,String stm,HagStringList ans,boolean withTitles,String[] filter,String cfgMenuWebUser,String cfgMenuWebPass){
			Connection con = getSqlConn( server, user, pass, db,"dbo");
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
		         		ans.add("<FORM METHOD=POST STYLE=\"margin: 0px; padding: 0px;\" name=\"Form11\" id=\"Form11\"  ACTION=\"filterRiServers.jsp\">");
		         		//combo
		         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebUser\" value = \""+cfgMenuWebUser+"\" >");
		         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+cfgMenuWebPass+"\" >");
		         		
		         		StringBuilder temp0 = new StringBuilder();
			            for (int i = 0; i < colCount; i++) {
			            	//perWidth = getPerWidth(i);
			              	String colName = metaData.getColumnName(i + 1);
			               	if(i==0)
			           			temp0.append("<tr><td  >.</td>");
			               	else if(i==4)
			            		temp0.append("<td  colspan=\"1\">&nbsp; </td>");
			               	else if(i>4) {
			            		temp0.append("");
			             	}else{
			           			temp0.append("<td   >");
			           			temp0.append("<select name=\"col"+i+"\"  onchange=\"this.form.submit()\">");
			           			HagStringList cc = new HagStringList();
			           			String stm1 = "select distinct "+colName+" from dbo.ri_servers where project <> 'HAGWIDTH'";
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
		               			temp1.append("<th >").append(replaceColName(colName)).append("</th>");
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

	           	    	ans.add("</form><FORM target=\"_blank\" STYLE=\"margin: 0px; padding: 0px;\" METHOD=POST name=\"Form3\" id=\"Form3\"  ACTION=\"runRemoteCommand.jsp?value=WIN\">");
	           	 	ans.add("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebUser\" value = \""+cfgMenuWebUser+"\" >");
	        	 	ans.add("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+cfgMenuWebPass+"\" >");
	           	    	devCombo.append("<select style=\"background-color: yellow\" id=\"act1\" name =\"act1\" onchange=\"this.form.submit();this.selectedIndex = 0\">");
	       				/*
	           	    	.append("<option class=\"c10\" value=\"Dev: Select action to run\">Dev: Select action to run</option>")
	           			.append("<option class=\"c11\" value=\"----------Display\">----------Display</option>")
	           			.append("<option class=\"c11\" value=\"Display env details\">Display env details</option>")
	           			.append("<option class=\"c11\" value=\"Display env installations\">Display env installations</option>")
	      				.append("<option class=\"c11\" value=\"Display env status\">Display env status</option>")
	           			.append("<option class=\"c11\" value=\"Display db size\">Display db size</option>")
	           			.append("<option class=\"c12\" value=\"----------Stop\">----------Stop</option>")
	           			.append("<option class=\"c12\" value=\"Stop eMerge listener\">Stop eMerge listener</option>")
	           			.append("<option class=\"c12\" value=\"Stop tomcat\">Stop tomcat</option>")
	           			.append("<option class=\"c12\" value=\"Stop tomcat and eMerge listener\">Stop tomcat and eMerge listener</option>")
	           			.append("<option class=\"c13\" value=\"----------Start\">----------Start</option>")
	           			.append("<option class=\"c13\" value=\"Start eMerge listener\">Start eMerge listener</option>")
	           			.append("<option class=\"c13\" value=\"Start tomcat\">Start tomcat</option>")
	           			.append("<option class=\"c13\" value=\"Start tomcat and eMerge listener\">Start tomcat and eMerge listener</option>")
	           			.append("<option class=\"c13\" value=\"----------Start\">----------Start</option>")
	           			.append("<option class=\"c13\" value=\"Start eMerge listener\">Start eMerge listener</option>")
	           			.append("<option class=\"c13\" value=\"Start tomcat\">Start tomcat</option>")
	           			.append("<option class=\"c13\" value=\"Start tomcat and eMerge listener\">Start tomcat and eMerge listener</option>")
	           			devCombo.append("<option class=\"c14\" value=\"----------I-WAY\">----------I-WAY</option>")
	           			.append("<option class=\"c14\" value=\"Start environment I-WAY (RUN)\">Login to I-WAY environment(RUN)</option>")
	           			.append("<option class=\"c14\" value=\"Start server I-WAY (Administration)\">Open I-WAY server(Administration)</option>");
	           			//.append("<option style=\"background-color: yellow\" value=\"Display tomcat status\">Display tomcat status</option>")
	           			//.append("<option style=\"background-color: yellow\" value=\"----------I-WAY\">----------I-WAY</option>")
	           			//.append("<option style=\"background-color: yellow\" value=\"Login to env(i-way run)\">Login to env(i-way run)</option>")
	          			//.append("<option style=\"background-color: yellow\" value=\"Login to server(i-way edit)\">Login to server(i-way edit)</option>")
	           			*/
	           	    	HagStringList list1 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riServersDevOptions.txt",true,"*",false);
	           	    	for(int i=0;i<list1.size();i++)
	           	    		devCombo.append(list1.get(i));
	           	        devCombo.append("</select>");
	           	            	    	

	      
	           			//.append("<INPUT TYPE=SUBMIT value=\"submit\">");
	           	    	/*
			        	ans.add("<tr bgColor=\"#FFFF00\">" +
			        			"<td>"	+devCombo.toString()+"</td>" +
			        			
			        			"<td "+perWidth+" bgColor=\"#FFFF00\" colspan=\"1\">"+requestCombo.toString()+"</td>" +
			        		
			        			"<td "+perWidth+" bgColor=\"#FFFF00\" colspan=\"13\">"+managerCombo.toString()+"</td>" +
			        			"</tr>");
			        			*/
	           	  	ans.add("<tr>" +devCombo.toString()+requestCombo.toString()+managerCombo.toString() +"</tr>");
		         	}
	                String lastGoodInst="xx";
	                //data
	                HagStringList oneRow = new HagStringList();
	               // HagUtil.p(oneRow);
	                String rowColor ="";
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
	                		oneRow.add("<tr #rowColor#><td nowrap><input type=\"checkbox\" name=\"cb\" id=\"cb\" value=\"{ID}\"></td>");
	                		rsLine.append(val);
	            		}else{
	            			rsLine.append("~").append(val);
	                		if(filter == null){
	                			if(i==5 && val.trim().equals("failed")){
	                       			oneRow.add("<td  nowrap bgColor=#ff0000>"+replaceVal(i,val,lastGoodInst)+"</td>");
	                			}else if(i==9 && val.trim().equals("hag-env")){
	                				rowColor="bgColor=#ffff00";
	                				oneRow.add("<td  nowrap>hag-env</td>");
	                				//oneRow.add("<td  nowrap bgColor=#ffff00>hag-env</td>");
	                			}else if(i==9 && val.trim().equals("to-delete")){
	                   				rowColor="bgColor=#ff00ff";
	                				oneRow.add("<td  nowrap>to-delete</td>");
	                				//oneRow.add("<td  nowrap bgColor=#ff00ff>to-delete</td>");
	                			}else if(i==9 && val.trim().equals("private")){
	                   				rowColor="bgColor=#00ff00";
	                				oneRow.add("<td  nowrap>private</td>");
	                				//oneRow.add("<td  nowrap bgColor=#00ff00>private</td>");
	                			}else
	               					oneRow.add("<td  nowrap >"+replaceVal(i,val,lastGoodInst)+"</td>");
	                		}else{
	                			oneRow.add(replaceVal(i,val,lastGoodInst));
	                		}
	           
	            		}
	                	
	                }
	                if(!rowColor.equals("")){
	                	oneRow.replaceStr("#rowColor#", rowColor);
	                }
	                
	            	if(filter == null){
	            		String temp2a = oneRow.convertToString(" ");
	            		String temp2b = HagUtil.replaceStr(temp2a,"{ID}", rsLine.toString());
	            		ans.add(temp2b);
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
	                		String temp2a1 = HagUtil.replaceStr(temp2a,"</td><td nowrap  >failed","</td><td nowrap  bgColor=#ff0000>failed");
	                		//temp2a1 = HagUtil.replaceStr(temp2a1,"</td><td nowrap  >hag-env","</td><td nowrap  bgColor=#ffff00>hag-env");
	                		//temp2a1 = HagUtil.replaceStr(temp2a1,"</td><td nowrap  >to-delete","</td><td nowrap  bgColor=#ff00ff>to-delete");
	                   		if(temp2a.indexOf(">hag-env<")>-1)
	                   			temp2a1 = HagUtil.replaceStr(temp2a1,"#rowColor#", "bgColor=#ffff00");
	                   		if(temp2a.indexOf(">to-delete<")>-1)
	                   			temp2a1 = HagUtil.replaceStr(temp2a1,"#rowColor#", "bgColor=#ff00ff");
	                   		if(temp2a.indexOf(">private<")>-1)
	                   			temp2a1 = HagUtil.replaceStr(temp2a1,"#rowColor#", "bgColor=#00ff00");
	                   		String temp2b = HagUtil.replaceStr(temp2a1,"{ID}",rsLine.toString());
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
		     //  	         HagUtil.p(ans);
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

	public static final String 	selectMigsList(String dbms,String server,String user,String pass,String db,String stm,HagStringList ans,boolean withTitles,String[] filter,String cfgMenuWebUser,String cfgMenuWebPass){
		//	String perWidth = "width=\"5%\"";
			Connection con = getSqlConn( server, user, pass, db,"dbo");
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
		         		ans.add("<FORM METHOD=POST STYLE=\"margin: 0px; padding: 0px;\" name=\"Form11\" id=\"Form11\"  ACTION=\"filterAddMigs.jsp\">");
		         		//combo
		         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebUser\" value = \""+cfgMenuWebUser+"\" >");
		         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+cfgMenuWebPass+"\" >");
		         		
		         		StringBuilder temp0 = new StringBuilder();
			            for (int i = 0; i < colCount; i++) {
			            	//perWidth = getPerWidth(i);
			              	String colName = metaData.getColumnName(i + 1);
			               	if(i==0)
			           			temp0.append("<tr><td  >.</td>");
			               	else if(i==13)
			            		temp0.append("<td  colspan=\"6\">&nbsp; </td>");
			               	else if(i>13)
			            		temp0.append("");
			               	else{
			           			temp0.append("<td   >");
			           			temp0.append("<select name=\"col"+i+"\"  onchange=\"this.form.submit()\">");
			           			HagStringList cc = new HagStringList();
			           			String stm1 = "select distinct "+colName+" from dbo.add_mig order by "+colName;
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
		               			temp1.append("<th >").append(replaceColName(colName)).append("</th>");
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
	       			
	           	    	HagStringList list1 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\migDevOptions.txt",true,"*",false);
	           	    	for(int i=0;i<list1.size();i++)
	           	    		devCombo.append(list1.get(i));
	        	 	
	        	 	devCombo.append("</select>");
	           	            	    	
	           	    
	           	       requestCombo.append("<select style=\"background-color: yellow\" id=\"act2\" name =\"act2\" onchange=\"this.form.submit();this.selectedIndex = 0\">");
	           	        HagStringList list2 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\migCmOptions.txt",true,"*",false);
	           	    	for(int i=0;i<list2.size();i++)
	           	    		requestCombo.append(list2.get(i));
	           	    //requestCombo.append("Future");
	           	       requestCombo.append("</select>");

	           	    
	           	   	managerCombo.append("<select class=\"c30\" id=\"act3\" name =\"act3\" onchange=\"this.form.submit();this.selectedIndex = 0\">");
	         	    	HagStringList list3 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\migManagerOptions.txt",true,"*",false);
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
	//                			if(i==5 && val.trim().equals("failed")){
	                       			//oneRow.add("<td  nowrap bgColor=#ff0000>"+replaceVal(i,val,lastGoodInst)+"</td>");
	                			//}else if(i==9 && val.trim().equals("hag-env")){
	                       		//	oneRow.add("<td  nowrap bgColor=#ffff00>hag-env</td>");
	                			//}else if(i==9 && val.trim().equals("to-delete")){
	                       	//		oneRow.add("<td  nowrap bgColor=#ff00ff>to-delete</td>");
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
	                	//	String temp2a1 = HagUtil.replaceStr(temp2a,"</td><td nowrap  >failed","</td><td nowrap  bgColor=#ff0000>failed");
	                	//	temp2a1 = HagUtil.replaceStr(temp2a1,"</td><td nowrap  >hag-env","</td><td nowrap  bgColor=#ffff00>hag-env");
	                	//	temp2a1 = HagUtil.replaceStr(temp2a1,"</td><td nowrap  >to-delete","</td><td nowrap  bgColor=#ff00ff>to-delete");
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
		     //  	         HagUtil.p(ans);
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
	
	
	public static final String 	selectDeleteFromMigDetailsList(String dbms,String server,String user,String pass,String db,String stm,HagStringList ans,boolean withTitles,String[] filter,String cfgMenuWebUser,String cfgMenuWebPass){
		//	String perWidth = "width=\"5%\"";
			Connection con = getSqlConn( server, user, pass, db,"dbo");
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
		         		
		         		ans.add("<FORM METHOD=POST STYLE=\"margin: 0px; padding: 0px;\" name=\"Form11\" id=\"Form11\"  ACTION=\"deleteFromMigDetails_delete.jsp\">");
		         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+cfgMenuWebPass+"\" >");
		         		/*
		         	//	ans2.add("<FORM METHOD=POST name=\"Form\" ACTION=\"deleteFromMigDetails_delete.jsp\">");
/////////		         		//combo
		         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebUser\" value = \""+cfgMenuWebUser+"\" >");
		         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+cfgMenuWebPass+"\" >");
		         		
		         		StringBuilder temp0 = new StringBuilder();
			            for (int i = 0; i < colCount; i++) {
			              	String colName = metaData.getColumnName(i + 1);
			               	if(i==0)
			           			temp0.append("<tr><td  >.</td>");
			               	else if(i==8)
			            		temp0.append("<td  colspan=\"2\">&nbsp; </td>");
			               	else if(i>8)
			            		temp0.append("");
			               	else{
			           			temp0.append("<td   >");
			           			temp0.append("<select name=\"col"+i+"\"  onchange=\"this.form.submit()\">");
			           			HagStringList cc = new HagStringList();
			           			String stm1 = "select distinct "+colName+" from dbo.DeleteFromMigDetails";
			           			String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
			           			temp0.append("<option value=\"[ALL]\">[ALL]</option>");
			           			for(int h = 0 ; h < cc.size();h++){
			           				String tmp =cc.get(h).trim();
			           				
			           				
			           				String sel="";
			           				if(filter != null && tmp.equals(filter[i-1])){
			           			
			           					sel=" selected ";
			           				}
				           			temp0.append("<option value=\""+tmp+"\" "+sel+">"+tmp+"</option>");
			           			}
			           			temp0.append("</select></td>");
			           		}
		           			if(i==(colCount-1))
			         			temp0.append("</tr>");
			        	}   
			        	*/
			            first=false;
			         
			           // ans.add(temp0.toString());
		         		              
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
		                		temp1.append("<tr bgColor=\"#848484\"><th >"+ddd.toString()+"</th>");
		                	}else
		               			temp1.append("<th >").append(replaceColName(colName)).append("</th>");
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

	           	 //   	ans.add("</form><FORM target=\"_blank\" STYLE=\"margin: 0px; padding: 0px;\" METHOD=POST name=\"Form3\" id=\"Form3\"  ACTION=\"distEnvs.jsp?value=WIN\">");
	           	 	ans.add("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebUser\" value = \""+cfgMenuWebUser+"\" >");
	        	 	ans.add("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+cfgMenuWebPass+"\" >");
	           	    
	        	 //	devCombo.append("<select style=\"background-color: yellow\" id=\"act1\" name =\"act1\" onchange=\"this.form.submit();this.selectedIndex = 0\">");
	       			
	           	//    	HagStringList list1 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\migDevOptions.txt",true,"*",false);
	           	 //   	for(int i=0;i<list1.size();i++)
	           	  //  		devCombo.append(list1.get(i));
	        	// 	devCombo.append("Future");
		           	  	
	        	// 	devCombo.append("</select>");
	           	            	    	
	           	    
	           	     //  requestCombo.append("<select style=\"background-color: yellow\" id=\"act2\" name =\"act2\" onchange=\"this.form.submit();this.selectedIndex = 0\">");
	           	      //  HagStringList list2 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\migCmOptions.txt",true,"*",false);
	           	    //	for(int i=0;i<list2.size();i++)
	           	    //		requestCombo.append(list2.get(i));
	           	 //   requestCombo.append("Future");
	           	    //   requestCombo.append("</select>");

	           	    
	           	   //	managerCombo.append("<select class=\"c30\" id=\"act3\" name =\"act3\" onchange=\"this.form.submit();this.selectedIndex = 0\">");
	         	   // 	HagStringList list3 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\migManagerOptions.txt",true,"*",false);
	           	   // 	for(int i=0;i<list3.size();i++)
	           	   // 		managerCombo.append(list3.get(i));
	           	    //	managerCombo.append("Future");
	           	    	
	           	    //	managerCombo.append("</select>");
	      
	           		
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
	//                			if(i==5 && val.trim().equals("failed")){
	                       			//oneRow.add("<td  nowrap bgColor=#ff0000>"+replaceVal(i,val,lastGoodInst)+"</td>");
	                			//}else if(i==9 && val.trim().equals("hag-env")){
	                       		//	oneRow.add("<td  nowrap bgColor=#ffff00>hag-env</td>");
	                			//}else if(i==9 && val.trim().equals("to-delete")){
	                       	//		oneRow.add("<td  nowrap bgColor=#ff00ff>to-delete</td>");
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
	                	//	String temp2a1 = HagUtil.replaceStr(temp2a,"</td><td nowrap  >failed","</td><td nowrap  bgColor=#ff0000>failed");
	                	//	temp2a1 = HagUtil.replaceStr(temp2a1,"</td><td nowrap  >hag-env","</td><td nowrap  bgColor=#ffff00>hag-env");
	                	//	temp2a1 = HagUtil.replaceStr(temp2a1,"</td><td nowrap  >to-delete","</td><td nowrap  bgColor=#ff00ff>to-delete");
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
		  		/*
		         ans.add("<h5><font color=\"yellow\">");
			     if(filter==null)
		        	 ans.add(count1+" rows from total "+count1+" rows <br>");
		         else
		          	 ans.add(count2+" rows from total "+count1+" rows <br>");
		         ans.add("</font></h5>");
		         */
		     //  	         HagUtil.p(ans);
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
	
	
	public static final String 	selectClientModuleMigsList(String dbms,String server,String user,String pass,String db,String stm,HagStringList ans,boolean withTitles,String[] filter,String cfgMenuWebUser,String cfgMenuWebPass){
		//	String perWidth = "width=\"5%\"";
			Connection con = getSqlConn( server, user, pass, db,"dbo");
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
		         		ans.add("<FORM METHOD=POST STYLE=\"margin: 0px; padding: 0px;\" name=\"Form11\" id=\"Form11\"  ACTION=\"filterClientModuleAddMigs.jsp\">");
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
			           			String stm1 = "select distinct "+colName+" from dbo.add_mig_clientModule";
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
		               			temp1.append("<th >").append(replaceColName(colName)).append("</th>");
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
	       			
	           	    	HagStringList list1 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\clientModuleMigDevOptions.txt",true,"*",false);
	           	    	for(int i=0;i<list1.size();i++)
	           	    		devCombo.append(list1.get(i));
	        	 	
	        	 	devCombo.append("</select>");
	           	            	    	
	           	    
	           	       requestCombo.append("<select style=\"background-color: yellow\" id=\"act2\" name =\"act2\" onchange=\"this.form.submit();this.selectedIndex = 0\">");
	           	        HagStringList list2 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\clientModuleMigCmOptions.txt",true,"*",false);
	           	    	for(int i=0;i<list2.size();i++)
	           	    		requestCombo.append(list2.get(i));
	           	   //requestCombo.append("Future");
	           	       requestCombo.append("</select>");

	           	    
	           	   	managerCombo.append("<select class=\"c30\" id=\"act3\" name =\"act3\" onchange=\"this.form.submit();this.selectedIndex = 0\">");
	         	    	HagStringList list3 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\clientModuleMigManagerOptions.txt",true,"*",false);
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
	//                			if(i==5 && val.trim().equals("failed")){
	                       			//oneRow.add("<td  nowrap bgColor=#ff0000>"+replaceVal(i,val,lastGoodInst)+"</td>");
	                			//}else if(i==9 && val.trim().equals("hag-env")){
	                       		//	oneRow.add("<td  nowrap bgColor=#ffff00>hag-env</td>");
	                			//}else if(i==9 && val.trim().equals("to-delete")){
	                       	//		oneRow.add("<td  nowrap bgColor=#ff00ff>to-delete</td>");
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
	                	//	String temp2a1 = HagUtil.replaceStr(temp2a,"</td><td nowrap  >failed","</td><td nowrap  bgColor=#ff0000>failed");
	                	//	temp2a1 = HagUtil.replaceStr(temp2a1,"</td><td nowrap  >hag-env","</td><td nowrap  bgColor=#ffff00>hag-env");
	                	//	temp2a1 = HagUtil.replaceStr(temp2a1,"</td><td nowrap  >to-delete","</td><td nowrap  bgColor=#ff00ff>to-delete");
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
	
	
	/*
	public static final String 	selectBackupWithNotesList(String dbms,String server,String user,String pass,String db,String stm,HagStringList ans,boolean withTitles,String[] filter,String cfgMenuWebUser,String cfgMenuWebPass){
		//	String perWidth = "width=\"5%\"";
HagStringList backupsList=new HagStringList();
backupsList.add("a1~a2~a3~a4~a5");
backupsList.add("a1~a3~4a~5a~6a");
backupsList.add("a3~3a~3a~4a~4a");
		String rc = "init";
	    	try {
		         int colCount = 5;
		         boolean first =true;
		  		int count1=0;
		  		int count2=0;
		  		for(int r = 0 ;r<backupsList.size();r++) {
		        	 StringBuilder rsLine=new StringBuilder();
		        	 count1++;
		         	if(first){
		         		ans.add("<FORM METHOD=POST STYLE=\"margin: 0px; padding: 0px;\" name=\"Form11\" id=\"Form11\"  ACTION=\"filterAddMigs.jsp\">");
		         		//combo
		         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebUser\" value = \""+cfgMenuWebUser+"\" >");
		         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+cfgMenuWebPass+"\" >");
		         		
		         		StringBuilder temp0 = new StringBuilder();
			            for (int i = 0; i < colCount; i++) {
			            	//perWidth = getPerWidth(i);
			              	String colName = "xxxxxxxx";
			               	if(i==0)
			           			temp0.append("<tr><td  >.</td>");
			               	else if(i==2)
			            		temp0.append("<td  colspan=\"3\">&nbsp; </td>");ssssss
			               	else if(i>13)
			            		temp0.append("");
			               	else{
			           			temp0.append("<td   >");
			           			temp0.append("<select name=\"col"+i+"\"  onchange=\"this.form.submit()\">");
			           			HagStringList cc = new HagStringList();
			           			String stm1 = "select distinct "+colName+" from dbo.add_mig";
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
		               			temp1.append("<th >").append(replaceColName(colName)).append("</th>");
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
	       			
	           	    	HagStringList list1 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\migDevOptions.txt",true,"*",false);
	           	    	for(int i=0;i<list1.size();i++)
	           	    		devCombo.append(list1.get(i));
	        	 	
	        	 	devCombo.append("</select>");
	           	            	    	
	           	    
	           	       requestCombo.append("<select style=\"background-color: yellow\" id=\"act2\" name =\"act2\" onchange=\"this.form.submit();this.selectedIndex = 0\">");
	           	        HagStringList list2 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\migCmOptions.txt",true,"*",false);
	           	    	for(int i=0;i<list2.size();i++)
	           	    		requestCombo.append(list2.get(i));
	           	    //requestCombo.append("Future");
	           	       requestCombo.append("</select>");

	           	    
	           	   	managerCombo.append("<select class=\"c30\" id=\"act3\" name =\"act3\" onchange=\"this.form.submit();this.selectedIndex = 0\">");
	         	    	HagStringList list3 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\migManagerOptions.txt",true,"*",false);
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
	//                			if(i==5 && val.trim().equals("failed")){
	                       			//oneRow.add("<td  nowrap bgColor=#ff0000>"+replaceVal(i,val,lastGoodInst)+"</td>");
	                			//}else if(i==9 && val.trim().equals("hag-env")){
	                       		//	oneRow.add("<td  nowrap bgColor=#ffff00>hag-env</td>");
	                			//}else if(i==9 && val.trim().equals("to-delete")){
	                       	//		oneRow.add("<td  nowrap bgColor=#ff00ff>to-delete</td>");
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
	                	//	String temp2a1 = HagUtil.replaceStr(temp2a,"</td><td nowrap  >failed","</td><td nowrap  bgColor=#ff0000>failed");
	                	//	temp2a1 = HagUtil.replaceStr(temp2a1,"</td><td nowrap  >hag-env","</td><td nowrap  bgColor=#ffff00>hag-env");
	                	//	temp2a1 = HagUtil.replaceStr(temp2a1,"</td><td nowrap  >to-delete","</td><td nowrap  bgColor=#ff00ff>to-delete");
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
		        
		    // 	StringBuilder ddd = new StringBuilder();
	   		//	ddd.append("Select (<a href=\"javascript:selectToggle(true);\">All</a> | <a href=\"javascript:selectToggle(false);\">None</a>)");
	   		// ans.add("<h3>");
		    //     ans.add(ddd.toString());
		      //   ans.add("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Display=");
		       
		  		
		         ans.add("<h3><font color=\"yellow\">");
			     if(filter==null)
		        	 ans.add(count1+" rows from total "+count1+" rows <br>");
		         else
		          	 ans.add(count2+" rows from total "+count1+" rows <br>");
		         ans.add("</font></h3>");
		     //  	         HagUtil.p(ans);
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
	*/
	public static final String 	selectRiEnvsDontMess(String dbms,String server,String user,String pass,String db,String stm,HagStringList ans,boolean withTitles,String[] filter,String cfgMenuWebUser,String cfgMenuWebPass){
			Connection con = getSqlConn( server, user, pass, db,"dbo");
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
		         		ans.add("<FORM METHOD=POST STYLE=\"margin: 0px; padding: 0px;\" name=\"Form11\" id=\"Form11\"  ACTION=\"filterRiEnvsDontMess.jsp\">");
		         		//combo
		         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebUser\" value = \""+cfgMenuWebUser+"\" >");
		         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+cfgMenuWebPass+"\" >");
		         		
		         		StringBuilder temp0 = new StringBuilder();
			            for (int i = 0; i < colCount; i++) {
			            	//perWidth = getPerWidth(i);
			              	String colName = metaData.getColumnName(i + 1);
			               	if(i==0)
			           			temp0.append("<tr><td  >.</td>");
			               	else if(i==14)
			            		temp0.append("<td  colspan=\"6\">&nbsp; </td>");
			               	else if(i>14)
			            		temp0.append("");
			               	else if(i==4){//last inst
			             		temp0.append("<td   >");
			           			temp0.append("<select name=\"col4\"  onchange=\"this.form.submit()\">");
			           			HagStringList cc = new HagStringList();
			           			String stm1 = "select distinct "+colName+" from dbo.ri_environments_new where  status='A' and project <> 'HAGWIDTH'";
			           			String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
			           			updateListRemoveDate(cc,true);
			           			temp0.append("<option value=\"[ALL]\">[ALL]</option>");
			           			for(int h = 0 ; h < cc.size();h++){
			           				String tmp =cc.get(h).trim();
			           				//HagUtil.p(tmp);
			           				String sel = "";
			           				if(filter != null && tmp.equals(filter[i-1])){
				           					sel=" selected ";
			           				}
				           			temp0.append("<option value=\""+tmp+"\" "+sel+">"+tmp+"</option>");
			           			}
			           			temp0.append("</select></td>");
			            	}else if(i==5){ //false inst
			             		temp0.append("<td>");
			           			temp0.append("<select name=\"col5\"  onchange=\"this.form.submit()\">");
			           			HagStringList cc = new HagStringList();
			           			String stm1 = "select distinct "+colName+" from dbo.ri_environments_new where status='A' and  project <> 'HAGWIDTH'";
			           			String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
			           			temp0.append("<option value=\"[ALL]\">[ALL]</option>");
			           			for(int h = 0 ; h < cc.size();h++){
			           				String tmp =cc.get(h).trim();
			           				
			           				String sel = "";
			           				if(filter != null && tmp.equals(filter[i-1]))
			           					sel=" selected ";
				           			temp0.append("<option value=\""+tmp+"\" "+sel+">"+tmp+"</option>");
			           			}
			           			temp0.append("</select></td>");
			             	}else{
			           			temp0.append("<td   >");
			           			temp0.append("<select name=\"col"+i+"\"  onchange=\"this.form.submit()\">");
			           			HagStringList cc = new HagStringList();
			           			String stm1 = "select distinct "+colName+" from dbo.ri_environments_new where status='A' and  project <> 'HAGWIDTH'";
			           			String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
			           			temp0.append("<option value=\"[ALL]\">[ALL]</option>");
			           			for(int h = 0 ; h < cc.size();h++){
			           				String tmp =cc.get(h).trim();
			           				if(i == 3)
			           					tmp =  HagUtil.getCustomerByPartyShort(tmp).trim();
			           				if(i == 4){
			           					//HagUtil.p(tmp);
			           					tmp = tmp.substring(0,tmp.indexOf("-")).trim();
			           				}
			           				
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
		               			temp1.append("<th >").append(replaceColName(colName)).append("</th>");
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
	       				
	           	    	HagStringList list1 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riEnvsDontMessOptions.txt",true,"*",false);
	           	    	for(int i=0;i<list1.size();i++)
	           	    		devCombo.append(list1.get(i));
	           	        devCombo.append("</select>");
	           	            	    	
	           	    	/*
	           	    	.append("<option class=\"c20\" value=\"Request: Select action to run\">Request: Select action to run</option>")
	           	    	.append("<option class=\"c21\" value=\"----------Installtion\">----------Installtion</option>")
	           	    	.append("<option class=\"c21\" value=\"Install RI-logic\">Install RI-logic</option>")
	           	        requestCombo.append("<select style=\"background-color: yellow\" id=\"act2\" name =\"act2\" onchange=\"this.form.submit();this.selectedIndex = 0\">");
	           	        HagStringList list2 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riEnvsReqOptions.txt",true,"*",false);
	           	    	for(int i=0;i<list2.size();i++)
	           	    		requestCombo.append(list2.get(i));
	           	    	requestCombo.append("</select>");

	           	    	.append("<option class=\"c30\" value=\"Manager: Select action to run\">Manager: Select action to run</option>")
	           	    	.append("<option class=\"c31\" value=\"----------Set\">----------Set</option>")
	           	    	.append("<option class=\"c31\" value=\"Set oded.r key Win\">Set oded.r key</option>")
	           	    	managerCombo.append("<select class=\"c30\" id=\"act3\" name =\"act3\" onchange=\"this.form.submit();this.selectedIndex = 0\">");
	         	    	HagStringList list3 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riEnvsManagerOptions.txt",true,"*",false);
	           	    	for(int i=0;i<list3.size();i++)
	           	    		managerCombo.append(list3.get(i));
	           	    	managerCombo.append("</select>");
	      
	           			//.append("<INPUT TYPE=SUBMIT value=\"submit\">");
			        	ans.add("<tr bgColor=\"#FFFF00\">" +
			        			"<td>"	+devCombo.toString()+"</td>" +
			        			
			        			"<td "+perWidth+" bgColor=\"#FFFF00\" colspan=\"1\">"+requestCombo.toString()+"</td>" +
			        		
			        			"<td "+perWidth+" bgColor=\"#FFFF00\" colspan=\"13\">"+managerCombo.toString()+"</td>" +
			        			"</tr>");
			        			*/
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
	                	
	                	if(i==0){
	            			oneRow.add("<tr><td nowrap><input type=\"checkbox\" name=\"cb\" id=\"cb\" value=\"{ID}\"></td>");
	            			rsLine.append(rs.getString(i+1));
	            		}else{
	            			rsLine.append("~").append(rs.getString(i+1));
	                		if(filter == null){
	                			oneRow.add("<td  nowrap >"+replaceVal(i,rs.getString(i+1),lastGoodInst)+"</td>");
	                		}else{
	                			oneRow.add(replaceVal(i,rs.getString(i+1),lastGoodInst));
	                		}
	            		}
	                	
	                }
	             
	                
	            	if(filter == null){
	            		String temp2a = oneRow.convertToString(" ");
	            		String temp2b = HagUtil.replaceStr(temp2a,"{ID}", rsLine.toString());
	            		ans.add(temp2b);
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
	            			String temp2a = oneRow.convertToString("</td><td nowrap  >");
	                		String temp2b = HagUtil.replaceStr(temp2a,"{ID}",rsLine.toString());
	                		ans.add("<td  nowrap>"+temp2b+"</td>");
	                		 count2++;
	            		}
	            		ans.add("</tr>");
	            	}
	            	
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
		     //  	         HagUtil.p(ans);
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
	public static final String 	selectRiEnvsI5os(String dbms,String server,String user,String pass,String db,String stm,HagStringList ans,boolean withTitles,String[] filter,String cfgMenuWebUser,String cfgMenuWebPass){
		//String perWidth = "width=\"5%\"";
		Connection con = getSqlConn( server, user, pass, db,"dbo");
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
	         		ans.add("<FORM METHOD=POST STYLE=\"margin: 0px; padding: 0px;\" name=\"Form11\" id=\"Form11\"  ACTION=\"filterRiEnvsI5os.jsp\">");
	         		//combo
	         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebUser\" value = \""+cfgMenuWebUser+"\" >");
	         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+cfgMenuWebPass+"\" >");
	         		
	         		StringBuilder temp0 = new StringBuilder();
		            for (int i = 0; i < colCount; i++) {
		            	//perWidth = getPerWidth(i);
		              	String colName = metaData.getColumnName(i + 1);
		               	if(i==0)
		           			temp0.append("<tr><td  >.</td>");
		               	else if(i==8)
		            		temp0.append("<td  colspan=\"7\">&nbsp; </td>");
		               	else if(i>8)
		            		temp0.append("");
		               	else{
		           			temp0.append("<td   >");
		           			temp0.append("<select name=\"col"+i+"\"  onchange=\"this.form.submit()\">");
		           			HagStringList cc = new HagStringList();
		           			String stm1 = "select distinct "+colName+" from dbo.ri_environments_i5os where project <> 'HAGWIDTH'";
		           			//System.out.println(stm1);
		           			String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
		           			temp0.append("<option value=\"[ALL]\">[ALL]</option>");
		           			for(int h = 0 ; h < cc.size();h++){
		           				String tmp =cc.get(h).trim();
		           				
		           				String sel="";
		           				if(filter != null && tmp.equals(filter[i-1]))
		           					sel=" selected ";
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
	                	//perWidth = getPerWidth(i);
	                	String colName = metaData.getColumnName(i + 1);
	                	if(i==0){
	                		StringBuilder ddd = new StringBuilder();
	               			//ddd.append("Select (<a href=\"javascript:selectToggle(true);\">All</a> | <a href=\"javascript:selectToggle(false);\">None</a>)");
	               			ddd.append("<a href=\"javascript:selectToggle(true);\">All</a><br><a href=\"javascript:selectToggle(false);\">None</a>");
	                		//ddd.append("<button onclick=\"selectToggle1(true)\">All</button>"); 
	                		temp1.append("<tr bgColor=\"#AAAAAA\"><th >"+ddd.toString()+"</th>");
	                	}else
	               			temp1.append("<th >").append(replaceColName(colName)).append("</th>");
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

           	    	ans.add("</form><FORM target=\"_blank\" STYLE=\"margin: 0px; padding: 0px;\" METHOD=POST name=\"Form3\" id=\"Form3\"  ACTION=\"distEnvs.jsp?value=I5OS\">");
           	 	ans.add("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebUser\" value = \""+cfgMenuWebUser+"\" >");
        	 	ans.add("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+cfgMenuWebPass+"\" >");
           	    ////	devCombo.append("<select style=\"background-color: yellow\" id=\"act1\" name =\"act1\" onchange=\"this.form.submit();this.selectedIndex = 0\">")
       			////	.append("<option class=\"c10\" value=\"Dev: Select action to run\">Dev: Select action to run</option>")
           		////	.append("<option class=\"c11\" value=\"----------Display\">----------Display</option>")
           	//		.append("<option class=\"c11\" value=\"Display env details\">Display env details</option>")
           	//		.append("<option class=\"c11\" value=\"Display env installations\">Display env installations</option>")
      		//		.append("<option class=\"c11\" value=\"Display env status\">Display env status</option>")
           	//		.append("<option class=\"c11\" value=\"Display db size\">Display db size</option>")
           	//		.append("<option class=\"c12\" value=\"----------Stop\">----------Stop</option>")
           	//		.append("<option class=\"c12\" value=\"Stop eMerge listener\">Stop eMerge listener</option>")
           	//		.append("<option class=\"c12\" value=\"Stop tomcat\">Stop tomcat</option>")
           	//		.append("<option class=\"c12\" value=\"Stop tomcat and eMerge listener\">Stop tomcat and eMerge listener</option>")
           	//		.append("<option class=\"c13\" value=\"----------Start\">----------Start</option>")
           	//		.append("<option class=\"c13\" value=\"Start eMerge listener\">Start eMerge listener</option>")
           	//		.append("<option class=\"c13\" value=\"Start tomcat\">Start tomcat</option>")
           	//		.append("<option class=\"c13\" value=\"Start tomcat and eMerge listener\">Start tomcat and eMerge listener</option>")
           		////	.append("<option class=\"c14\" value=\"----------I-WAY\">----------I-WAY</option>")
           		////	.append("<option class=\"c14\" value=\"Start environment I-WAY (RUN)\">Login to I-WAY environment(RUN)</option>")
           		////	.append("<option class=\"c14\" value=\"Start server I-WAY (Administration)\">Open I-WAY server(Administration)</option>")
           			        			
           		////	.append("</select>");
        	 	
        	 	
        	 	
        	 	devCombo.append("<select style=\"background-color: yellow\" id=\"act1\" name =\"act1\" onchange=\"this.form.submit();this.selectedIndex = 0\">");
       	    	HagStringList list1 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riEnvsDevOptionsI5os.txt",true,"*",false);
       	    	for(int i=0;i<list1.size();i++)
       	    		devCombo.append(list1.get(i));
       	        devCombo.append("</select>");
           	                   	    	
           	    	requestCombo.append("<select style=\"background-color: yellow\" id=\"act2\" name =\"act2\" onchange=\"this.form.submit();this.selectedIndex = 0\">")
           	    	.append("<option class=\"c20\" value=\"Request: Select action to run\">Request: Select action to run</option>")
           	    	.append("<option class=\"c21\" value=\"----------Installtion\">----------Installtion</option>")
           	   // 	.append("<option class=\"c21\" value=\"Install RI-logic\">Install RI-logic</option>")
           	    	.append("</select>");

           	    	managerCombo.append("<select class=\"c30\" id=\"act3\" name =\"act3\" onchange=\"this.form.submit();this.selectedIndex = 0\">")
           	    	.append("<option class=\"c30\" value=\"Manager: Select action to run\">Manager: Select action to run</option>")
           	    	.append("<option class=\"c31\" value=\"----------Set\">----------Set</option>")
           	    	.append("<option class=\"c31\" value=\"Set oded.r key I5os\">Set oded.r key</option>")
           	    	
           	    	.append("</select>");
      
           			//.append("<INPUT TYPE=SUBMIT value=\"submit\">");
           	    	/*
		        	ans.add("<tr bgColor=\"#FFFF00\">" +
		        			"<td>"	+devCombo.toString()+"</td>" +
		        			
		        			"<td "+perWidth+" bgColor=\"#FFFF00\" colspan=\"1\">"+requestCombo.toString()+"</td>" +
		        		
		        			"<td "+perWidth+" bgColor=\"#FFFF00\" colspan=\"13\">"+managerCombo.toString()+"</td>" +
		        			"</tr>");
		        			*/
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
                	if(i==0){
                		oneRow.add("<tr><td nowrap ><input type=\"checkbox\" name=\"cb\" id=\"cb\" value=\"{ID}\"></td>");
            			rsLine.append(rs.getString(i+1));
            		}else{
            			rsLine.append("~").append(rs.getString(i+1));
                		if(filter == null){
                	
                			if(i==6 && rs.getString(i+1).trim().equals("failed")){
                       			oneRow.add("<td nowrap bgColor=#ff0000>"+replaceVal(i,rs.getString(i+1),lastGoodInst)+"</td>");
                			}else if(i==13 && rs.getString(i+1).trim().equals("CFG-ONLY")){
                       			oneRow.add("<td nowrap bgColor=#ffff00>CFG-ONLY</td>");
                			}else
                				oneRow.add("<td nowrap >"+replaceVal(i,rs.getString(i+1),lastGoodInst)+"</td>");
                      }else{
                			oneRow.add(replaceVal(i,rs.getString(i+1),lastGoodInst));
                		}
            		}
                	
                }
             
                
            	if(filter == null){
            		String temp2a = oneRow.convertToString(" ");
            		String temp2b = HagUtil.replaceStr(temp2a,"{ID}", rsLine.toString());
            		ans.add(temp2b);
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
            			String temp2a = oneRow.convertToString("</td><td  nowrap>");
            			String temp2a1 = HagUtil.replaceStr(temp2a,"</td><td  nowrap>failed","</td><td nowrap  bgColor=#ff0000>failed");
            	 		//System.out.println(temp2a1);
            			temp2a1 = HagUtil.replaceStr(temp2a1,"</td><td  nowrap>CFG-ONLY","</td><td  nowrap bgColor=#ffff00>CFG-ONLY");
                        
            			String temp2b = HagUtil.replaceStr(temp2a1,"{ID}",rsLine.toString());
                		ans.add("<td  nowrap >"+temp2b+"</td>");
                		 count2++;
            		}
            		ans.add("</tr>");
            	}
            	
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
	     //  	         HagUtil.p(ans);
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
	public static final String 	selectShortPrcs(String dbms,String server,String user,String pass,String db,String stm,HagStringList ansRows1 ,boolean withTitles,String[] filter,int num){
		Connection con = getSqlConn( server, user, pass, db,"dbo");
		if(con==null)
			return "1~failed to create connection";
		Statement statement = null;
    	ResultSet rs = null;
    	String rc = "init";

    	//String stm = "select project,bis_server,db,party,lastGoodInst,lastInst,version,patch,env,sql_server,emerge_port,connection_port,server_port,debug_port,note  from dbo.ri_environments_new where project <> 'HAGWIDTH'";
    //	String style = style="color:#009900; background: #E6E6FA;";
    	try {
    		 statement = con.createStatement();   
    		 rs = statement.executeQuery(stm);   
	         ResultSetMetaData metaData = rs.getMetaData();
	         int colCount = metaData.getColumnCount();
	         boolean first =true;
	  		int count1=0;
	  		int count2=0;
	  		 HagStringList ans3 = null;
	  		String ans9=null;
	         while (rs.next()) {
	        	// HagStringList ans2 =new HagStringList();
	        	 count1++;
	         	if(first){
	         		String aaa = "<FORM METHOD=POST STYLE=\"margin: 0px; padding: 0px;\" name=\"Form11\" id=\"Form11\"  ACTION=\"filterPrcs.jsp?value="+num+"\">";
		         	ansRows1.add(aaa);
	         		
	         		//combo
	         		StringBuilder temp0 = new StringBuilder();
	         	 	for (int i = 0; i < colCount; i++) {
		              	String colName = metaData.getColumnName(i+1);
		             	if(i==0)
		           			temp0.append("<tr>");
		         	
		           		if(i>9){
		           			continue;
		           		}
		           	  	temp0.append("<td>");
		           	  	int k = i+1;
		         	  	temp0.append("<select name=\"col"+k+"\"  onchange=\"this.form.submit()\">");
		           		HagStringList cc = new HagStringList();
		           		String stm1 = "select distinct "+colName+" from dbo.RI_PRC order by "+colName;
		           		String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
		           		temp0.append("<option value=\"[ALL]\">[ALL]</option>");
		           	
		           		for(int h = 0 ; h < cc.size();h++){
		           			String tmp =cc.get(h).trim();
		           			String sel="";
		           			if(filter != null && tmp.equals(filter[i]))
		           				sel=" selected ";
			           		temp0.append("<option value=\""+tmp+"\" "+sel+">"+tmp+"</option>");
		           		}
		           		temp0.append("</select></td>");
	         	 	}   
	         	 	temp0.append("<td colspan=\"4\">&nbsp;</td>");
         			temp0.append("</tr>");
	         	 	first=false;
	         	 	ansRows1.add(temp0.toString());  

	         	 	//columns
	                StringBuilder temp1 = new StringBuilder();
	                for (int i = 0; i < colCount; i++) {
	                	String colName = metaData.getColumnName(i + 1);
	                	if(i==0)
	                		temp1.append("<tr bgColor=\"#AAAAAA\">");
	                	if(colName.equals("hotfix")) 
	                		temp1.append("<th>").append("hotfix/#req").append("</th>");
	                	else
	                		temp1.append("<th>").append(colName).append("</th>");
	            		if(i==(colCount-1))
	            			temp1.append("</tr>");
	           		}   
	            	ansRows1.add(temp1.toString());
	         	}

	         	//data
                HagStringList oneRow = new HagStringList();
             //   System.out.println("data"+HagUtil.getDateTime("mm:ss:SSS"));
                boolean flag = true;
                String keepDate = "";
                String keepAct = "";
                String keepSubAct = "";
                for (int i = 0; i < colCount; i++) { //cols
                	String tmp3= rs.getString(i+1).trim();
                	if(i == 8)
                		keepDate=tmp3;
                	if(i == 0)
                		keepAct=tmp3;
                	if(i == 1)
                		keepSubAct=tmp3;
                 
                    if(i == 10)
                    	tmp3 = replaceValLinks(i ,tmp3,keepDate,keepAct,keepSubAct);
                    oneRow.add(tmp3);
                }
           //     System.out.println("filter1"+HagUtil.getDateTime("mm:ss:SSS"));
               	if(filter != null){	
               		flag = checkFilter(filter, oneRow);
              	}
            //    System.out.println("filter2"+HagUtil.getDateTime("mm:ss:SSS"));
            	if(filter == null || flag){
            		ans3=new HagStringList(oneRow,"dup");
            		count2++;
            		ans9 = setStyle(ans3);
                	if(ans9 != null){
                	  ansRows1.add(ans9);
                    }
            	}
            	
            }
	     
           	HagStringList ans4= new HagStringList();
            	ans4.add("<h3><font color=\"yellow\">");
		     if(filter==null)
		    	 ans4.add(count1+" rows from total "+count1+" rows <br>");
	         else
	        	 ans4.add(count2+" rows from total "+count1+" rows <br>");
		     ans4.add("</font></h3>");
		     String ans4str = ans4.convertToString(" ");
		     ansRows1.add(ans4str);
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
	
	public static final void	setArrAll(String[][] arrAll,String[] arrCols,HagRc hagRc,String stm,String server,String user,String pass,String db) {
		//String[][] arrAll=new String[total1][14];
		//String[] arrCols=new String[14];
		boolean first = true;
		Statement statement = null;
    	ResultSet rs = null;
    	Connection con = getSqlConn( server, user, pass, db,"dbo");
		if(con==null) {
			hagRc.log.add("1~failed to create connection");
			hagRc.rc=1;
			return ;
		}
		
		try {
   		 	statement = con.createStatement();   
   		 	rs = statement.executeQuery(stm);   
   		 	ResultSetMetaData metaData = rs.getMetaData();
   	 		int countNew=0;
	  	   while (rs.next()) {
	  		  for (int i = 0; i < 14; i++) {
	  			arrAll[countNew][i]= rs.getString(i+1).trim();
	  			if(first) 
	  				arrCols[i] = metaData.getColumnName(i + 1).trim();
	  		  }
	  		  first = false;
	  		  countNew++;
	  	   }
		 }catch (Exception e) {
		        e.printStackTrace();
		    	hagRc.log.add("1~"+e.getMessage());
				hagRc.rc=1;
		 }finally {
		       if (rs != null) try { rs.close(); } catch(Exception e) {}
		       if (statement != null) try { statement.close(); } catch(Exception e) {}
		       if (con != null) try { con.close(); } catch(Exception e) {}
		 } 
		return ;
	}
	public static final String 	selectShortPrcsNew(String dbms,String server,String user,String pass,String db,String stm,
			HagStringList ansRows1 ,boolean withTitles,String[] filter,int start1,int end1,int total1){
		int total2 = total1+1;
		HagRc hagRc = new HagRc();
		String[][] arrAll=new String[total2][14];
		String[] arrCols=new String[14];
		setArrAll(arrAll, arrCols,hagRc,  stm, server, user, pass, db);
		if(hagRc.rc!=0) {
			return HagUtil.shortHtml(hagRc.log.get(0),"red","bg");
		}
		
	//	Connection con = getSqlConn( server, user, pass, db,"dbo");
	//	if(con==null)
		//	return "1~failed to create connection";
		//Statement statement = null;
    	//ResultSet rs = null;
    	String rc = "init";

     //	String[][] arrAll=new String[total1][14];
    //	try {
    	//	 statement = con.createStatement();   
    	//	 rs = statement.executeQuery(stm);   
	    //     ResultSetMetaData metaData = rs.getMetaData();
	    //     int colCount = metaData.getColumnCount();
	         boolean first =true;
	  	//	int count1=0;
	  	//	int count2=0;
	  	//	int countNew=0;
	  	//	 HagStringList ans3 = null;
	  	//	String ans9=null;
	  	  // while (rs.next()) {
	  	//	  for (int i = 0; i < 14; i++) {
	  	//		arrAll[countNew][i]= rs.getString(i+1).trim();
	  		//	
	  		//  }
	  	//	countNew++;
	  	 //  }
	    //     while ( count1 <1000) {
	        	
	        	// count1++;
	         HagStringList ans3 = null;
	         String ans9=null;
	         int count2=0;
	         for(int kk =0;kk<1000;kk++) {
	         	if(first){
	         		String aaa = "<FORM METHOD=POST STYLE=\"margin: 0px; padding: 0px;\" name=\"Form11\" id=\"Form11\"  ACTION=\"filterPrcs.jsp?value=-1\">";
		         	ansRows1.add(aaa);
	         		
	         		//combo
	         		StringBuilder temp0 = new StringBuilder();
	         	 	for (int i = 0; i < 14; i++) {
		              	String colName = arrCols[i];
		             	if(i==0)
		           			temp0.append("<tr>");
		         	
		           		if(i>9){
		           			continue;
		           		}
		           	  	temp0.append("<td>");
		           	  	int k = i+1;
		         	  	temp0.append("<select name=\"col"+k+"\"  onchange=\"this.form.submit()\">");
		           		HagStringList cc = new HagStringList();
		           		String stm1 = "select distinct "+colName+" from dbo.RI_PRC order by "+colName;
		           		String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
		           		temp0.append("<option value=\"[ALL]\">[ALL]</option>");
		           	
		           		for(int h = 0 ; h < cc.size();h++){
		           			String tmp =cc.get(h).trim();
		           			String sel="";
		           			if(filter != null && tmp.equals(filter[i]))
		           				sel=" selected ";
			           		temp0.append("<option value=\""+tmp+"\" "+sel+">"+tmp+"</option>");
		           		}
		           		temp0.append("</select></td>");
	         	 	}   
	         	 	temp0.append("<td colspan=\"4\">&nbsp;</td>");
         			temp0.append("</tr>");
	         	 	first=false;
	         	 	ansRows1.add(temp0.toString());  

	         	 	//columns
	                StringBuilder temp1 = new StringBuilder();
	                for (int i = 0; i < 14; i++) {
	                	String colName = arrCols[i];
	                	if(i==0)
	                		temp1.append("<tr bgColor=\"#AAAAAA\">");
	                	if(colName.equals("hotfix")) 
	                		temp1.append("<th>").append("hotfix/#req").append("</th>");
	                	else
	                		temp1.append("<th>").append(colName).append("</th>");
	            		if(i==(13))
	            			temp1.append("</tr>");
	           		}   
	            	ansRows1.add(temp1.toString());
	         	}

	         	//data
                HagStringList oneRow = new HagStringList();
                boolean flag = true;
                String keepDate = "";
                String keepAct = "";
                String keepSubAct = "";
                for (int i = 0; i < 14; i++) { //cols
                	//String tmp3= rs1.getString(i+1).trim();
                	String tmp3=arrAll[kk][i];
                
                	if(i == 8)
                		keepDate=tmp3;
                	if(i == 0)
                		keepAct=tmp3;
                	if(i == 1)
                		keepSubAct=tmp3;
                 
                    if(i == 10)
                    	tmp3 = replaceValLinks(i ,tmp3,keepDate,keepAct,keepSubAct);
                    oneRow.add(tmp3);
                }
      
               	if(filter != null){	
               		flag = checkFilter(filter, oneRow);
              	}
       
            	if(filter == null || flag){
           		ans3=new HagStringList(oneRow,"dup");
            		count2++;
            		ans9 = setStyle(ans3);
                	if(ans9 != null){
                	  ansRows1.add(ans9);
                    }
            	}
            	
            }
	     
           	HagStringList ans4= new HagStringList();
            ans4.add("<h3><font color=\"yellow\">");
            
            
            StringBuilder bulkSizeSB =new StringBuilder("<select name=\"aaa\"  >")
           		.append("<option value=\"1000\">1000</option>")
           		.append("<option value=\"2000\">1000</option>")
           		.append("<option value=\"3000\">1000</option>")
           		.append("<option value=\"4000\">1000</option>")
           		.append("<option value=\"5000\">1000</option>")
           		.append("<option value=\"6000\">1000</option>")
           		.append("<option value=\"7000\">1000</option>")
           		.append("<option value=\"8000\">1000</option>")
           		.append("<option value=\"9000\">1000</option>")
           		.append("<option value=\"10000\">1000</option>")
           		.append("</select>");
            String bulkSize = "Bulk size="+bulkSizeSB.toString();
            
            
         	String prev1="<input type=\"button\" value=\"Previous bulk\"  >";	
        	String next1="<input type=\"button\" value=\"Next bulk\"  >";	
		     if(filter==null)
		    	 ans4.add(bulkSize+"  "+prev1+"  display rows from  "+start1+" to "+end1+" (total ="+total2+")  "+next1+"<br>");
	         else
	        	 ans4.add(bulkSize+"  "+prev1+"  display rows from  "+start1+" to "+end1+" (total after filtered ="+total2+")  "+next1+"<br>");
		     ans4.add("</font></h3>");
		     String ans4str = ans4.convertToString(" ");
		     ansRows1.add(ans4str);
		     rc="0~done."; 	    
    	// }catch (Exception e) {
	   //     e.printStackTrace();
	    //    rc="1~"+e.getMessage(); 	
	   //  }finally {
	          //  if (rs1 != null) try { rs1.close(); } catch(Exception e) {}
	    //        if (statement != null) try { statement.close(); } catch(Exception e) {}
	   //         if (con != null) try { con.close(); } catch(Exception e) {}
	   //  } 
    	return rc; 	
	}
	public static final String 	selectTasks(String server,String user,String pass,String db,String stm,HagStringList ansRows1 ,boolean withTitles,String[] filter,String table ){
		String[][] colsNames = {{"VERSION","Created for version"},
								{"TASKNO","Task number"},
								{"USERNAME","User name"},
								{"STATUS","Status"},
								{"OPENDATE","OPENDATE"},
								{"OPENTIME","OPENTIME"},
								{"CLOSEDATE","CLOSEDATE"},
								{"CLOSETIME","CLOSETIME"}};
		Connection con = getSqlConn( server, user, pass, db,"dbo");
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
	  		HagStringList ans3 = null;
	  		String ans9=null;
	        while (rs.next()) {
	        	count1++;
	         	if(first){
	         		String aaa = "<FORM METHOD=POST STYLE=\"margin: 0px; padding: 0px;\" name=\"Form11\" id=\"Form11\"  ACTION=\"filterTasks.jsp?value="+table+"\">";
		         	ansRows1.add(aaa);
	         		//combo
	         		StringBuilder temp0 = new StringBuilder();
	         	 	for (int i = 0; i < colCount; i++) {
		              //	String colName = metaData.getColumnName(i+1);
		             	if(i==0)
		           			temp0.append("<tr>");
		         	
		           		if(i>3){
		           			continue;
		           		}
		           	  	temp0.append("<td>");
		           	  	int k = i+1;
		         	  	temp0.append("<select name=\"col"+k+"\"  onchange=\"this.form.submit()\">");
		           		HagStringList cc = new HagStringList();
		           		//cc.add("aa1");
		           		//cc.add("aa2");
		           		//cc.add("aa3");
		           		//cc.add("aa4");
		           		//cc.add("aa5");
		           		String stm1 = "select distinct "+colsNames[i][0]+" from dbo."+table+" where VERSION <> '0000' order by "+colsNames[i][0];
		           	//	System.out.println(stm1);
		           		String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
		           		temp0.append("<option value=\"[ALL]\">[ALL]</option>");
		           	
		           		for(int h = 0 ; h < cc.size();h++){
		           			String tmp =cc.get(h).trim();
		           			String sel="";
		           			if(filter != null && tmp.equals(filter[i]))
		           				sel=" selected ";
			           		temp0.append("<option value=\""+tmp+"\" "+sel+">"+tmp+"</option>");
		           		}
		           		temp0.append("</select></td>");
	         	 	}   
	         	 	temp0.append("<td colspan=\"4\">&nbsp;</td>");
         			temp0.append("</tr>");
	         	 	first=false;
	         	 	ansRows1.add(temp0.toString());  

	         	 	//columns
	                StringBuilder temp1 = new StringBuilder();
	                for (int i = 0; i < colCount; i++) {
	                	//String colName = metaData.getColumnName(i + 1);
	                	if(i==0)
	                		temp1.append("<tr bgColor=\"#AAAAAA\">");
               			temp1.append("<th>").append(colsNames[i][1]).append("</th>");
	            		if(i==(colCount-1))
	            			temp1.append("</tr>");
	           		}   
	            	ansRows1.add(temp1.toString());
	         	}

	         	//data
                HagStringList oneRow = new HagStringList();
            
                boolean flag = true;
                for (int i = 0; i < colCount; i++) { //cols
                	String tmp3= rs.getString(i+1).trim();
                    oneRow.add(tmp3);
                }
               	if(filter != null){	
               		flag = checkFilter(filter, oneRow);
               	}
            	
            	if(filter == null || flag){
            		ans3=new HagStringList(oneRow,"dup");
           		 	count2++;
            		ans9 = setStyle(ans3);
                	if(ans9 != null){
                		ansRows1.add(ans9);
                  	}
            	}
	        }
	        
	        HagStringList ans4= new HagStringList();
            ans4.add("<h3><font color=\"yellow\">");
            if(filter==null)
		    	ans4.add(count1+" rows from total "+count1+" rows <br>");
	        else
	        	ans4.add(count2+" rows from total "+count1+" rows <br>");
		    ans4.add("</font></h3>");
		    String ans4str = ans4.convertToString(" ");
		    ansRows1.add(ans4str);
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
	public static final String 	selectShortTomcats(String dbms,String server,String user,String pass,String db,String stm,HagStringList ansRows1 ,boolean withTitles,String[] filter){
		Connection con = getSqlConn( server, user, pass, db,"dbo");
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
	  		 HagStringList ans3 = null;
	  		String ans9=null;
	         while (rs.next()) {
	        	// HagStringList ans2 =new HagStringList();
	        	 count1++;
	         	if(first){
	         		String aaa = "<FORM METHOD=POST STYLE=\"margin: 0px; padding: 0px;\" name=\"Form11\" id=\"Form11\"  ACTION=\"filterTomcats.jsp\">";
		         	ansRows1.add(aaa);
		         	ansRows1.add("<input type=\"button\" value=\"Refresh\"  onClick=\"this.form.submit()\">");
	         		//combo
	         		StringBuilder temp0 = new StringBuilder();
	         	 	for (int i = 0; i < colCount; i++) {
		              	String colName = metaData.getColumnName(i+1);
		             	if(i==0)
		           			temp0.append("<tr>");
		         	
		           		if(i>2){
		           			continue;
		           		}
		           	  	temp0.append("<td>");
		           	  	int k = i+1;
		         	  	temp0.append("<select name=\"col"+k+"\"  onchange=\"this.form.submit()\">");
		           		HagStringList cc = new HagStringList();
		           		String stm1 = "select distinct "+colName+" from dbo.ri_environments_new where  status='A'";
		           		String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
		           		temp0.append("<option value=\"[ALL]\">[ALL]</option>");
		           	
		           		for(int h = 0 ; h < cc.size();h++){
		           			String tmp =cc.get(h).trim();
		           			String sel="";
		           			if(filter != null && tmp.equals(filter[i]))
		           				sel=" selected ";
			           		temp0.append("<option value=\""+tmp+"\" "+sel+">"+tmp+"</option>");
		           		}
		           		temp0.append("</select></td>");
	         	 	}   
	         	 	temp0.append("<td colspan=\"5\">&nbsp;</td>");
         			temp0.append("</tr>");
	         	 	first=false;
	         	 	ansRows1.add(temp0.toString());  

	         	 	//columns
	                StringBuilder temp1 = new StringBuilder();
	                for (int i = 0; i < colCount; i++) {
	                	String colName = metaData.getColumnName(i + 1);
	                	if(i==0)
	                		temp1.append("<tr bgColor=\"#AAAAAA\">");
               			temp1.append("<th>").append(colName).append("</th>");
	            	
	           		}   
	            	//if(i==(colCount-1))
            			temp1.append("<td>Status</td></tr>");
	            	ansRows1.add(temp1.toString());
	         	}

	         	//data
                HagStringList oneRow = new HagStringList();
            
                boolean flag = true;
                String keepServer = "";
                String keepPort = "";
                for (int i = 0; i < colCount; i++) { //cols
                	String tmp3= rs.getString(i+1).trim();
                    oneRow.add(tmp3);
                    if( i == 1)
                    	keepServer=tmp3;
                    if( i == 3)
                    	keepPort=tmp3;
                    
                }
                oneRow.add(" <iframe src=\"http://"+keepServer+":"+keepPort+"/ri-web\" width=\"100%\" height=\"100\"></iframe> ");
               	if(filter != null){	
               		flag = checkFilter(filter, oneRow);
               	}
            	
            	if(filter == null || flag){
            		ans3=new HagStringList(oneRow,"dup");
        			 count2++;
            		 ans9 = setStyle(ans3);
                	 if(ans9 != null){
                  		  ansRows1.add(ans9);
                  	  }
            	}
            	
            	 
	         }
	        
           	HagStringList ans4= new HagStringList();
            	ans4.add("<h3><font color=\"yellow\">");
	         if(filter==null)
		    	 ans4.add(count1+" rows from total "+count1+" rows <br>");
	         else
	        	 ans4.add(count2+" rows from total "+count1+" rows <br>");
		     ans4.add("</font></h3>");
		     String ans4str = ans4.convertToString(" ");
		     ansRows1.add(ans4str);
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
	public static final String 	selectShortIway(String dbms,String server,String user,String pass,String db,String stm,HagStringList ansRows1 ,boolean withTitles,String[] filter){
		Connection con = getSqlConn( server, user, pass, db,"dbo");
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
	  		 HagStringList ans3 = null;
	  		String ans9=null;
	         while (rs.next()) {
	        	// HagStringList ans2 =new HagStringList();
	        	 count1++;
	         	if(first){
	         		String aaa = "<FORM METHOD=POST STYLE=\"margin: 0px; padding: 0px;\" name=\"Form11\" id=\"Form11\"  ACTION=\"filterIways.jsp\">";
		         	ansRows1.add(aaa);
		         //	ansRows1.add("<input type=\"button\" value=\"Refresh\"  onClick=\"this.form.submit()\">");
	         		//combo
	         		StringBuilder temp0 = new StringBuilder();
	         	 	for (int i = 0; i < colCount; i++) {
		              	String colName = metaData.getColumnName(i+1);
		             	if(i==0)
		           			temp0.append("<tr>");
		         	
		           		if(i>5){
		           			continue;
		           		}
		           	  	temp0.append("<td>");
		           	  	int k = i+1;
		         	  	temp0.append("<select name=\"col"+k+"\"  onchange=\"this.form.submit()\">");
		           		HagStringList cc = new HagStringList();
		           		String stm1 = "select distinct "+colName+" from dbo.iWay where project <> 'HAGWIDTH'  and (appServer='SCENIC' or appServer='TERANO' or appServer='PASSAT') ";
		          // 	HagUtil.p(stm1);
		           		
		           		String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
		           	//	HagUtil.p(cc);
		           		temp0.append("<option value=\"[ALL]\">[ALL]</option>");
		           	
		           		for(int h = 0 ; h < cc.size();h++){
		           			String tmp =cc.get(h).trim();
		           		//	HagUtil.p(tmp);
		           			String sel="";
		           			if(filter != null && tmp.equals(filter[i]))
		           				sel=" selected ";
			           		temp0.append("<option value=\""+tmp+"\" "+sel+">"+tmp+"</option>");
		           		}
		           		temp0.append("</select></td>");
	         	 	}   
	         	 	temp0.append("<td colspan=\"8\">&nbsp;</td>");
         			temp0.append("</tr>");
	         	 	first=false;
	         	 	ansRows1.add(temp0.toString());  

	         	 	//columns
	                StringBuilder temp1 = new StringBuilder();
	                for (int i = 0; i < colCount; i++) {
	                	String colName = metaData.getColumnName(i + 1);
	                	if(i==0)
	                		temp1.append("<tr bgColor=\"#AAAAAA\">");
               			temp1.append("<th>").append(colName).append("</th>");
	            	
	           		}   
	            	//if(i==(colCount-1))
//            			temp1.append("<td>Link to i-way</td></tr>");
            			temp1.append("<td>link to server</td></tr>");
	            	ansRows1.add(temp1.toString());
	         	}

	         	//data
                HagStringList oneRow = new HagStringList();
            
                boolean flag = true;
                String keepServer = "";
                String keepPort = "";
                for (int i = 0; i < colCount; i++) { //cols
                	String tmp3= rs.getString(i+1).trim();
                	 if( i == colCount-1)
                		 oneRow.add("<a href=\""+tmp3+"\" target=\"_blank\">link to env(run)</a>");
               		 else
                		 oneRow.add(tmp3);
                			 
                    if( i == 4)
                    	keepServer=tmp3;
                //   if( i == 3)
//                    	keepPort=tmp3;
 
                      
                }
               // oneRow.add(" <iframe src=\"http://"+keepServer+":"+keepPort+"/ri-web\" width=\"100%\" height=\"100\"></iframe> ");
                oneRow.add("<a href=\"http://"+keepServer+"/sapweb/Admin/AdmFrames.htm\" target=\"_blank\">link to server(edit)</a>");
               // HagUtil.p(keepServer);
                if(filter != null){	
               		flag = checkFilter(filter, oneRow);
               	}
            	
            	if(filter == null || flag){
            		ans3=new HagStringList(oneRow,"dup");
        			 count2++;
            		 ans9 = setStyle(ans3);
                	 if(ans9 != null){
                  		  ansRows1.add(ans9);
                  	  }
            	}
            	
            	 
	         }
	        
           	HagStringList ans4= new HagStringList();
            	ans4.add("<h3><font color=\"yellow\">");
	         if(filter==null)
		    	 ans4.add(count1+" rows from total "+count1+" rows <br>");
	         else
	        	 ans4.add(count2+" rows from total "+count1+" rows <br>");
		     ans4.add("</font></h3>");
		     String ans4str = ans4.convertToString(" ");
		     ansRows1.add(ans4str);
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
	public static final boolean checkFilter(String[]filter,HagStringList oneRow){
		for(int t = 0 ; t < filter.length;t++){
   			String colFilter = filter[t];
   			if(!colFilter.equals("[ALL]") && !colFilter.equals(oneRow.get(t)) ){
   				return false;
   			}
   		}
		return true;
	}
	public static final String  setStyle(HagStringList list1){
	
		if(list1==null || list1.size()==0)
			return null;
		StringBuilder str = new StringBuilder();
		
		String act = list1.get(0).trim();
		
		/*if(act.equals("Install")){
			str.append("<tr style=\"color:#006400; background: #E6E6FA;\">");
			//str.append("<tr style=\"color:#000000; background: #B2CAF6;\">");
		}else if(act.equals("Release")){
			str.append("<tr style=\"color:#8B4513; background: #E6E6FA;\">");
		}else if(act.equals("Restore")){
			str.append("<tr style=\"color:#FFA07A; background: #E6E6FA;\">");
		}else if(act.equals("Backup")){
			str.append("<tr style=\"color:#FF00FF; background: #E6E6FA;\">");
		}else{
			str.append("<tr style=\"color:#7B68EE; background: #E6E6FA;\">");
		}
		*/
		str.append("<tr style=\"color:#<FGCOLOR>; background: #E6E6FA;\">");
		if(act.equals("Install")){
			str.append("<td style=\"color:#000000; background: #B2CAF6;\">").append(act).append("</td>");
		}else if(act.equals("Release")){
			str.append("<td style=\"color:#000000; background: #B8FF70;\">").append(act).append("</td>");
		}else if(act.equals("Restore")){
			str.append("<td style=\"color:#000000; background: #70FFFF;\">").append(act).append("</td>");
		}else if(act.equals("Backup")){
			str.append("<td style=\"color:#000000; background: #FFFF66;\">").append(act).append("</td>");
		}else if(act.equals("Replace")){
			str.append("<td style=\"color:#000000; background: #CC99FF;\">").append(act).append("</td>");
		}else if(act.equals("Copy")){
			str.append("<td style=\"color:#000000; background: #FFCD82;\">").append(act).append("</td>");
		}else{
			str.append("<td>").append(act).append("</td>");
		}
		boolean rc =true;
		for(int i =1;i<list1.size();i++){
			String tmp = list1.get(i).trim();
			
			if(i==7){
				if(tmp.equalsIgnoreCase("OK"))
				//	str.append("<td style=\"color:#000000; background: #32CD32;\">").append(tmp).append("</td>");
					str.append("<td>").append(tmp).append("</td>");
				else  if(tmp.equalsIgnoreCase("failed")){
					str.append("<td style=\"color:#000000; background: #FF6347;\">").append(tmp).append("</td>");
					rc=false;
				}else
					str.append("<td>").append(tmp).append("</td>");
			}else
				str.append("<td>").append(tmp).append("</td>");
		}
		str.append("</tr>");
		String str1 = "";
		
		if(rc)
			str1 = HagUtil.replaceStr(str.toString(),"<FGCOLOR>", "009900");
		else
			str1 = HagUtil.replaceStr(str.toString(),"<FGCOLOR>", "ff0000");
		return str1;
		/*
		if(actKeep.equals("Install")){
			list.replaceStr("<style>","style=\"color:#8A2BE2; background: #cccccc;\";");
		}else if(actKeep.equals("Release")){
			list.replaceStr("<style>","style=\"color:#8B4513; background: #cccccc;\";");
		}else if(actKeep.equals("Restore")){
			list.replaceStr("<style>","style=\"color:#8B0000; background: #cccccc;\";");
		}else if(actKeep.equals("Backup")){
			list.replaceStr("<style>","style=\"color:#FF00FF; background: #cccccc;\";");
		}else{
			list.replaceStr("<style>","style=\"color:#009900; background: #dddddd;\";");
		}
		
		
		list.replaceStr("<style1>>OK","style=\"color:#000000; background: #32CD32;\";>OK");
		list.replaceStr("<style1>>failed","style=\"color:#000000; background: #FF6347;\";>failed");
		list.replaceStr(">!?!failed","style=\"color:#000000; background: #FF6347;\";>failed");
		list.replaceStr(">!?!OK","style=\"color:#000000; background: #32CD32;\";>OK");
	*/
	}
	public static final String 	selectShortEnvs1111js(String dbms,String server,String user,String pass,String db,String stm,HagStringList ans,boolean withTitles){
		Connection con = getSqlConn( server, user, pass, db,"dbo");
		if(con==null)
			return "1~failed to create connection";
		Statement statement = null;
    	ResultSet rs = null;
    	String rc = "init";
    	//String stm = "select project,bis_server,db,party,lastGoodInst,lastInst,version,patch,env,sql_server,emerge_port,connection_port,server_port,debug_port,note  from dbo.ri_environments_new where project <> 'HAGWIDTH'";
    	
    	try {
    		 statement = con.createStatement();   
    		 rs = statement.executeQuery(stm);   
	         ResultSetMetaData metaData = rs.getMetaData();
	         int colCount = metaData.getColumnCount();
	         boolean first =true;
	         while (rs.next()) {
	         	if(first){
	                StringBuilder temp1 = new StringBuilder();
	            //    temp1.append("<thead>");
	                for (int i = 0; i < colCount; i++) {
	                	String colName = metaData.getColumnName(i + 1);
	                	if(i==0)
	            			temp1.append("<tr><th width=\"4px\">sel</th>");
	            		else
	               			temp1.append("<th>").append(replaceColName(metaData.getColumnName(i + 1))).append("</th>");
	            		if(i==(colCount-1))
	            			temp1.append("</tr>");
	           		}   
	             //   temp1.append("</thead>");
	                first=false;
	           	//	if(withTitles){
            			ans.add(temp1.toString());
            	//	}
            	}
                StringBuilder temp2 = new StringBuilder();
         //   	temp2.append("<tbody class=\"scrollingContent\">");
                String temp2c="init";
                String lastGoodInst="xx";
            	for (int i = 0; i < colCount; i++) {
            		if(i==4){
            			lastGoodInst=rs.getString(i+1);
            		}
            		if(i==0)
            			temp2.append("<tr><td width=\"4px\"><input type=\"checkbox\" name=\"cb\" id=\"cb\" value=\"{ID}\"></td>");
            		else
            			temp2.append("<td>").append(replaceVal(i,rs.getString(i+1),lastGoodInst)).append("</td>");
            		if(i==(colCount-1))
            			temp2.append("</tr>");
            	}   
            	String temp2a = temp2.toString();
            	String temp2b = HagUtil.replaceStr(temp2a,"{ID}", rs.getString(2)+"~"+rs.getString(3)+"~"+rs.getString(10));
            //	temp2.append("</tbody>");
	           	ans.add(temp2b);
	        }
            rc="0~done."; 	    
    	 }catch (Exception e) {
	        e.printStackTrace();
	        rc="1~"+e.getMessage(); 	
	     }finally {
	            if (rs != null) try { rs.close(); } catch(Exception e) {}
	            if (statement != null) try { statement.close(); } catch(Exception e) {}
	            if (con != null) try { con.close(); } catch(Exception e) {}
	     } 
    	//ans.add("</table>");
    	return rc; 	
	}
	public static final String 	select(String dbms,String server,String user,String pass,String db,String stm,HagStringList ans,boolean withTitles){
		Connection con = getSqlConn( server, user, pass, db,"dbo");
		if(con==null)
			return "1~failed to create connection";
		Statement statement = null;
    	ResultSet rs = null;
    	String rc = "init";
    //	ans.add("<table id=\"myTable\" border=\"1\">");
    	//ans.add("<table id=\"table2\" cellpadding=\"0\" cellspacing=\"0\">");
    	try {
    		 statement = con.createStatement();   
    		 rs = statement.executeQuery(stm);   
	         ResultSetMetaData metaData = rs.getMetaData();
	         int colCount = metaData.getColumnCount();
	         boolean first =true;
	         while (rs.next()) {
	         	if(first){
	                StringBuilder temp1 = new StringBuilder();
	            //    temp1.append("<thead>");
	                for (int i = 0; i < colCount; i++) {
	            		if(i==0)
	            			temp1.append("<tr><th width=\"4px\">sel</th>");
	            		else
	               			temp1.append("<th>< ").append(metaData.getColumnName(i + 1)).append(" ></th>");
	            		if(i==(colCount-1))
	            			temp1.append("</tr>");
	           		}   
	             //   temp1.append("</thead>");
	                first=false;
	           	//	if(withTitles){
            			ans.add(temp1.toString());
            	//	}
            	}
                StringBuilder temp2 = new StringBuilder();
         //   	temp2.append("<tbody class=\"scrollingContent\">");
                String temp2c="init";
            	for (int i = 0; i < colCount; i++) {
            		if(i==0)
            			temp2.append("<tr><td width=\"4px\"><input type=\"checkbox\" name=\"cb\" id=\"cb\" value=\"{ID}\"></td>");
            		else
            			temp2.append("<td>").append(rs.getString(i+1)).append("</td>");
            		if(i==(colCount-1))
            			temp2.append("</tr>");
            	}   
            	String temp2a = temp2.toString();
            	String temp2b = HagUtil.replaceStr(temp2a,"{ID}", rs.getString(5)+"~"+rs.getString(7));
            //	temp2.append("</tbody>");
	           	ans.add(temp2b);
	        }
    	 }catch (Exception e) {
	        e.printStackTrace();
	        rc="1~"+e.getMessage(); 	
	     }finally {
	            if (rs != null) try { rs.close(); } catch(Exception e) {}
	            if (statement != null) try { statement.close(); } catch(Exception e) {}
	            if (con != null) try { con.close(); } catch(Exception e) {}
	     } 
    	//ans.add("</table>");
    	return rc; 	
	}
	public static final String 	selectSimple(String dbms,String server,String user,String pass,String db,String stm,HagStringList ans,boolean withTitles){
		Connection con = getSqlConn( server, user, pass, db,"dbo");
		if(con==null)
			return "1~failed to create connection";
		Statement statement = null;
    	ResultSet rs = null;
    	String rc = "init";
    	ans.add("<table border=\"1\">");
    	try {
    		 statement = con.createStatement();   
    		 rs = statement.executeQuery(stm);   
	         ResultSetMetaData metaData = rs.getMetaData();
	         int colCount = metaData.getColumnCount();
	         boolean first =true;
	         while (rs.next()) {
	         	if(first){
	                StringBuilder temp1 = new StringBuilder();
	                temp1.append("<thead>");
	                for (int i = 0; i < colCount; i++) {
	            		if(i==0)
	            			temp1.append("<tr>");
	           			temp1.append("<td>").append(metaData.getColumnName(i + 1)).append("</td>");
	            		if(i==(colCount-1))
	            			temp1.append("</tr>");
	           		}   
	                temp1.append("</thead>");
	                first=false;
	           		if(withTitles){
            			ans.add(temp1.toString());
            		}
            	}
                StringBuilder temp2 = new StringBuilder();
            	temp2.append("<tbody class=\"scrollingContent\">");
            	for (int i = 0; i < colCount; i++) {
            		if(i==0)
            			temp2.append("<tr>");
           			temp2.append("<td>").append(rs.getString(i+1)).append("</td>");
            		if(i==(colCount-1))
            			temp2.append("</tr>");
            	}   
            	temp2.append("</tbody>");
	           	ans.add(temp2.toString());
	        
	        }
	        rc="0~done."; 
    	 }catch (com.microsoft.sqlserver.jdbc.SQLServerException sse) {
    		// sse.printStackTrace();
 	        rc="9~"+sse.getMessage(); 	
    	 }catch (Exception e) {
	     //   e.printStackTrace();
	        rc="1~"+e.getMessage(); 	
	     }finally {
	            if (rs != null) try { rs.close(); } catch(Exception e) {}
	            if (statement != null) try { statement.close(); } catch(Exception e) {}
	            if (con != null) try { con.close(); } catch(Exception e) {}
	     } 
    	ans.add("</table>");
    	return rc; 	
	}
	public static final String 	selectIntoList(String dbms,String server,String user,String pass,String db,String stm,HagStringList ans,String delim){
		Connection con = getSqlConn( server, user, pass, db,"dbo");
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
	         while (rs.next()) {
	            StringBuilder temp2 = new StringBuilder();
            	for (int i = 0; i < colCount; i++) {
            		if(i==0)
            			temp2.append(rs.getString(i+1));
            		else
            			temp2.append(delim).append(rs.getString(i+1));
            	}   
	           	ans.add(temp2.toString());
	        }
	       //  HagUtil.p(ans);
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
	public static final String 	selectSimple1(String dbms,String server,String user,String pass,String db,String stm,HagStringList ans,boolean withTitles){
		Connection con = getSqlConn( server, user, pass, db,"dbo");
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
	         while (rs.next()) {
	         	if(first){
	                StringBuilder temp1 = new StringBuilder();
	                temp1.append("<thead>");
	                for (int i = 0; i < colCount; i++) {
	            		if(i==0)
	            			temp1.append("<tr>");
	           			temp1.append("<td>").append(metaData.getColumnName(i + 1)).append("</td>");
	            		if(i==(colCount-1))
	            			temp1.append("</tr>");
	           		}   
	                temp1.append("</thead>");
	                first=false;
	           		if(withTitles){
            			ans.add(temp1.toString());
            		}
            	}
                StringBuilder temp2 = new StringBuilder();
            	temp2.append("<tbody class=\"scrollingContent\">");
            	for (int i = 0; i < colCount; i++) {
            		if(i==0)
            			temp2.append("<tr>");
           			temp2.append("<td>").append(rs.getString(i+1)).append("</td>");
            		if(i==(colCount-1))
            			temp2.append("</tr>");
            	}   
            	temp2.append("</tbody>");
	           	ans.add(temp2.toString());
	        
	        }
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
	public static final String 	selectVertical(String dbms,String server,String user,String pass,String db,String stm,HagStringList ans){
		Connection con = getSqlConn( server, user, pass, db,"dbo");
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
	         boolean  bgColor= true;
	         while (rs.next()) {
	        	if(bgColor){
	        		 ans.add("<table  bgColor=#\"FBF58B\" border=\"1\">");
	        		 bgColor=false;
	        	}else{
	        		 ans.add("<table  bgColor=#\"B9FCBF\" border=\"1\">");
	           		 bgColor=true;
	        	     
	        	}
	        	StringBuilder temp1 = new StringBuilder();
	        	String sqlServer=".";
	        	String db1=".";
                for (int i = 0; i < colCount; i++) {
            		temp1.append("<tr><td>").append(metaData.getColumnName(i + 1)).append("</td><td>").append(rs.getString(i+1)).append("</td></tr>");
            		if(metaData.getColumnName(i + 1).equals("sql_server"))
            			sqlServer=rs.getString(i+1);
            		if(metaData.getColumnName(i + 1).equals("db"))
            			db1=rs.getString(i+1);
            	}
                ans.add(temp1.toString());
                //add RID-226 - cmInstaller line
                System.out.println(sqlServer+"/"+db1);
        		String stm2 ="SELECT TOP (1) * from RI.cmInstaller order by instDate DESC,instTime DESC";
        		HagSQL hagSQL=new HagSQL();
        		HagStringList ans2 =hagSQL.selectAll("SQL",sqlServer,"RIADMIN","ADMINRI",db1,stm2,"|",null,null);
        		String temp2a = ans2.convertToString(" "); 
        		StringBuilder temp2 =new StringBuilder("<tr><td>").append("last installtion details").append("</td><td>").append(temp2a).append("</td></tr>");
                ans.add(temp2.toString());
            	ans.add("</table>");
            	ans.add("<br>");
	         }
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
    	
    	
    	//add RID-226 - cmInstaller line
		//String stm2 ="SELECT TOP (1) from RI.cmInstaller order by instDate DESC,instTime DESC";
		//HagSQL hagSQL=new HagSQL();
		//HagStringList ans2 =hagSQL.select("SQL",sqlServer,"cfg","cfg09c",HagParam.getConfg1DB(),stm2,10,"|",null,null);
		//String ans12 
		
	}
	public static final String 	getDbSize(String dbms,String sqlServer,String user,String pass,String db,HagStringList ans){
		StringBuilder stm = new StringBuilder("SELECT ")
		.append( "DB_NAME(db.database_id) DatabaseName, ")
		.append( "(CAST(mfrows.RowSize AS FLOAT)*8)/1024 RowSizeMB, ")
		.append( "(CAST(mflog.LogSize AS FLOAT)*8)/1024 LogSizeMB ")
		.append( "FROM sys.databases db ")
		.append( "LEFT JOIN (SELECT database_id, SUM(size) RowSize FROM sys.master_files WHERE type = 0 GROUP BY database_id, type) mfrows ON mfrows.database_id = db.database_id ")
		.append( "LEFT JOIN (SELECT database_id, SUM(size) LogSize FROM sys.master_files WHERE type = 1 GROUP BY database_id, type) mflog ON mflog.database_id = db.database_id ")
		.append( "LEFT JOIN (SELECT database_id, SUM(size) StreamSize FROM sys.master_files WHERE type = 2 GROUP BY database_id, type) mfstream ON mfstream.database_id = db.database_id ")
		.append( "LEFT JOIN (SELECT database_id, SUM(size) TextIndexSize FROM sys.master_files WHERE type = 4 GROUP BY database_id, type) mftext ON mftext.database_id = db.database_id ")
		.append( "where DB_NAME(db.database_id)='").append(db).append("'"); 
		
		//String stm1 ="SELECT SUM(size / 1024 * 8) ,  X1 = (SELECT crdate from master.dbo.sysdatabases WHERE name = 'MC') FROM sys.database_files";
	//	System.out.println(stm.toString());
		String rc = selectSimple1(dbms, sqlServer, "cfg", "cfg09c", "master", stm.toString(), ans,false);
	//	String rc = selectSimple1(dbms, sqlServer, "cfg", "cfg09c", "MASTER", stm1, ans,false);
		String ans11=ans.convertToString(" ");
		return ans11;
	}
	
	public static final String 	updateStm(String server,
			String username,
			String password,
			String db,
			String stm	,String app	,String bn			){
		
		Connection con=null;
		con = getSqlConn(server,username,password,db,"dbo.");
		if(con==null)	return "aaaaaaaaaaaaaaaaa";
		try {
			Statement statement = con.createStatement();
			int un=statement.executeUpdate(stm);
			return app+"-"+bn+" updated.("+un+" rows)";
		} catch (SQLException e) {
			System.out.println("Could not connect to the database - "+e.getMessage());
			return e.getMessage();

		}
	}
	
	
	//spr1008
	
	//public static final String filterTable(int ind,String val,String lastGoodInst,String[] filter){
//	String temp = replaceVal( ind, val, lastGoodInst);
///		if()
//		return "<td>"+temp+"</td>";
//	else
//		return "<td>"+temp+"</td>";/
//}

	/*
	public static final String selectShortEnvs(String dbms,String server,String user,String pass,String db,String stm,HagStringList ans,boolean withTitles,String[] filter){
		Connection con = getSqlConn( server, user, pass, db,"dbo");
		if(con==null)
			return "1~failed to create connection";
		Statement statement = null;
    	ResultSet rs = null;
    	String rc = "init";
    	//String stm = "select project,bis_server,db,party,lastGoodInst,lastInst,version,patch,env,sql_server,emerge_port,connection_port,server_port,debug_port,note  from dbo.ri_environments_new where project <> 'HAGWIDTH'";
    	
    	try {
    		 statement = con.createStatement();   
    		 rs = statement.executeQuery(stm);   
	         ResultSetMetaData metaData = rs.getMetaData();
	         int colCount = metaData.getColumnCount();
	         boolean first =true;
	  		int count1=0;
	  		int count2=0;
	         while (rs.next()) {
	        	 count1++;
	         	if(first){
	         		ans.add("<FORM METHOD=POST STYLE=\"margin: 0px; padding: 0px;\" name=\"Form11\" id=\"Form11\"  ACTION=\"filterEnvs.jsp\">");
	         		//combo
	         		StringBuilder temp0 = new StringBuilder();
		            for (int i = 0; i < colCount; i++) {
		              	String colName = metaData.getColumnName(i + 1);
		               	if(i==0)
		           			temp0.append("<tr><td width=\"4px\">.</td>");
		               	else if(i==10)
		            		temp0.append("<td colspan=\"5\">&nbsp; </td>");
		               	else if(i>10)
		            		temp0.append("");
		               	else if(i==4){
		             		temp0.append("<td>");
		           			temp0.append("<select name=\"col4\"  onchange=\"this.form.submit()\">");
		           			HagStringList cc = new HagStringList();
		           			String stm1 = "select distinct "+colName+" from dbo.ri_environments_new where project <> 'HAGWIDTH'";
		           			String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
		           			
		           			cc.dropDup("-");
		           			temp0.append("<option value=\"[ALL]\">[ALL]</option>");
		           		//	HagUtil.p("xxxxxxxxxxxxxxxxxxxxxx");
		           			for(int h = 0 ; h < cc.size();h++){
		           				String tmp =cc.get(h);
		           				String sel = "";
		           				if(filter != null && tmp.equals(filter[i-1]))
		           					sel=" selected ";
			           			temp0.append("<option value=\""+tmp+"\" "+sel+">"+tmp+"</option>");
		           			}
		           			temp0.append("</select></td>");
		            	}else if(i==5){
		             		temp0.append("<td>");
		           			temp0.append("<select name=\"col5\"  onchange=\"this.form.submit()\">");
		           			temp0.append("<option value=\"[ALL]\">[ALL]</option>");
		           			String sel1="";
		           			String sel2="";
		           			if(filter != null && filter[i-1].equals("YES"))
	           					sel1=" selected ";
		           			if(filter != null && filter[i-1].equals("NO"))
	           					sel2=" selected ";
			           		temp0.append("<option value=\"NO\""+sel2+" >NO</option>");
		           			temp0.append("<option value=\"YES\""+sel1+">YES</option>");
		           			temp0.append("</select></td>");
		             	}else{
		           			temp0.append("<td>");
		           			temp0.append("<select name=\"col"+i+"\"  onchange=\"this.form.submit()\">");
		           			HagStringList cc = new HagStringList();
		           			String stm1 = "select distinct "+colName+" from dbo.ri_environments_new where project <> 'HAGWIDTH'";
		           			String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
		           			temp0.append("<option value=\"[ALL]\">[ALL]</option>");
		           			for(int h = 0 ; h < cc.size();h++){
		           				String tmp =cc.get(h).trim();
		           				if(i == 3)
		           					tmp =  replaceCustomer(tmp);
		           				if(i == 4){
		           					//HagUtil.p(tmp);
		           					tmp = tmp.substring(0,tmp.indexOf("-")).trim();
		           				}
		           				String sel="";
		           				if(filter != null && tmp.equals(filter[i-1]))
		           					sel=" selected ";
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
	                	String colName = metaData.getColumnName(i + 1);
	                	if(i==0){
	                		StringBuilder ddd = new StringBuilder();
	               		//	ddd.append("Select (<a href=\"javascript:selectToggle(true);\">All</a> | <a href=\"javascript:selectToggle(false);\">None</a>)");
	               			ddd.append("<a href=\"javascript:selectToggle(true);\">All</a><br><a href=\"javascript:selectToggle(false);\">None</a>");
	    	               	
	                		temp1.append("<tr bgColor=\"#AAAAAA\"><th width=\"4px\">"+ddd.toString()+"</th>");
	                	}else
	               			temp1.append("<th>").append(replaceColName(colName)).append("</th>");
	            		if(i==(colCount-1))
	            			temp1.append("</tr>");
	           		}   
	                first=false;
           			ans.add(temp1.toString());
           		
           		//commands
           	     //	StringBuilder ddd = new StringBuilder();
           		//	ddd.append("Select (<a href=\"javascript:selectToggle(true);\">All</a> | <a href=\"javascript:selectToggle(false);\">None</a>)");
           	
           			StringBuilder vvv = new StringBuilder();
           	    	vvv.append("</form><FORM target=\"_blank\" STYLE=\"margin: 0px; padding: 0px;\" METHOD=POST name=\"Form3\" id=\"Form3\"  ACTION=\"distEnvs.jsp\">")
           			.append("<select style=\"background-color: yellow\" id=\"act\" name =\"act\" onchange=\"this.form.submit();this.selectedIndex = 0\">")
       				.append("<option style=\"background-color: yellow\" value=\"Select action to run\">Select action to run</option>")
           			.append("<option style=\"background-color: yellow\" value=\"----------Display\">----------Display</option>")
           			.append("<option style=\"background-color: yellow\" value=\"Display environment details\">Display environment details</option>")
           			.append("<option style=\"background-color: yellow\" value=\"Display environment installations\">Display environment installations</option>")
      				.append("<option style=\"background-color: yellow\" value=\"Display environment status\">Display environment status</option>")
           			.append("<option style=\"background-color: yellow\" value=\"Display database size\">Display database size</option>")
           			.append("<option style=\"background-color: yellow\" value=\"----------Start\">----------Start</option>")
           			.append("<option style=\"background-color: yellow\" value=\"Start eMerge listener\">Start eMerge listener</option>")
           			.append("<option style=\"background-color: yellow\" value=\"----------Stop\">----------Stop</option>")
           			.append("<option style=\"background-color: yellow\" value=\"----------Stop eMerge listener\">----------Stop eMerge listener</option>")
           			.append("</select>");
           			//.append("<INPUT TYPE=SUBMIT value=\"submit\">");
		        	ans.add("<tr bgColor=\"#DDDDDD\"><td colspan=\"15\">"+vvv.toString()+"</td></tr>");
		         
           		
	         	}
                String lastGoodInst="xx";
                HagStringList oneRow = new HagStringList();
                for (int i = 0; i < colCount; i++) {
            		if(i==4)
            			lastGoodInst=rs.getString(i+1);
            		if(i==0)
            			oneRow.add("<tr><td width=\"4px\"><input type=\"checkbox\" name=\"cb\" id=\"cb\" value=\"{ID}\"></td>");
            		else{
                		if(filter == null){
                			oneRow.add("<td>"+replaceVal(i,rs.getString(i+1),lastGoodInst)+"</td>");
                		}else{
                			oneRow.add(replaceVal(i,rs.getString(i+1),lastGoodInst));
                		}
            		}
                }
            	if(filter == null){
            		String temp2a = oneRow.convertToString(" ");
            		String temp2b = HagUtil.replaceStr(temp2a,"{ID}", rs.getString(2)+"~"+rs.getString(3)+"~"+rs.getString(10));
            		ans.add(temp2b);
            	}else{
            		boolean flag = true;
            		for(int t = 0 ; t < filter.length;t++){
            			String colFilter = filter[t];
            			if(!colFilter.equals("[ALL]") && !colFilter.equals(oneRow.get(t+1)) ){
            				flag = false;
            			}
            		}
            		if(flag){
            			String temp2a = oneRow.convertToString("</td><td>");
                		String temp2b = HagUtil.replaceStr(temp2a,"{ID}", rs.getString(2)+"~"+rs.getString(3)+"~"+rs.getString(10));
                		ans.add("<td>"+temp2b+"</td>");
                		 count2++;
            		}
            		ans.add("</tr>");
            	}
            	
	         }
	         
	     //	StringBuilder ddd = new StringBuilder();
   		//	ddd.append("Select (<a href=\"javascript:selectToggle(true);\">All</a> | <a href=\"javascript:selectToggle(false);\">None</a>)");
   		 //ans.add("<h3>");
	      //   ans.add(ddd.toString());
	       //  ans.add("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Display=");
	        
	         ans.add("<h3><font color=\"yellow\">");
		     if(filter==null)
	        	 ans.add(count1+" rows from total "+count1+" rows <br>");
	         else
	          	 ans.add(count2+" rows from total "+count1+" rows <br>");
	         ans.add("</font></h3>");
	     //  	         HagUtil.p(ans);
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
	*/

}
