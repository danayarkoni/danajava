package com.hag.hagay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class 		RequestsFlowPerCust {
	public 	static final 	String 			requestsStatusForm(String cfgMenuWebUser,String cfgMenuWebPass,String keyString,String sum){
		
		HagStringList ans = new	HagStringList(); 
		String stm = "select act,appServer,dbid,sms,hotfix,subAct,rc,byWho,dateTime,note from dbo.RI_PRC where act = 'Request' and mail='AIG'  order by dateTime DESC";
    	
		
		

		String rc= selectRequestStatus("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans,true,null,cfgMenuWebUser,cfgMenuWebPass,null,sum);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = HagUtil.cfgMenuWebLoc+"\\htmls\\tableSortT_requestsByCust.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = HagUtil.cfgMenuWebLoc+"\\htmls\\tableSortB_requestsByCust.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}
	}
	
	public 	static final 	String 			selectRequestStatus(String dbms,String server,String user,String pass,String db,
			String stm,HagStringList ans,boolean withTitles,String[] filter,String cfgMenuWebUser,
			String cfgMenuWebPass,String keyString,String sum){
		Connection con = getSqlConn( server, user, pass, db,"dbo");
		if(con==null)
			return "1~failed to create connection";
		Statement statement = null;
    	ResultSet rs = null;
    	String rc = "init";
    	try {
    		String countTotal = getTotalRequests();
    		if(countTotal.startsWith("Error"))
    			return countTotal;
    	
    				
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
	        	   String rowColor ="";
	         	if(first){
	         		ans.add("<FORM METHOD=POST STYLE=\"margin: 0px; padding: 0px;\" name=\"Form11\" id=\"Form11\"  ACTION=\"filterRequestsPerCustStatus.jsp\">");
	         		//combo
	         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebUser\" value = \""+cfgMenuWebUser+"\" >");
	         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+cfgMenuWebPass+"\" >");
	         		ans.add("<input type=\"hidden\" name=\"sum\" id=\"sum\" value = \""+sum+"\" >");
	         		StringBuilder temp0 = new StringBuilder();
		            for (int i = 0; i < colCount; i++) {
		            	//perWidth = getPerWidth(i);
		              	String colName = metaData.getColumnName(i + 1);
		            
		               	if(i==0) //0
		           			temp0.append("<tr><td  >.</td>");		               	
		               	else if(i==9) { //8
		               		if (keyString==null) 
		               			keyString="";
		            		temp0.append("<td  colspan=\"9\">Extra search by string:<input type=\"text\" name=\"keyString\" id=\"keyString\"  value=\""+keyString+"\" maxlength=\"20\" size=\"20\"> <button onclick=\"this.form.submit()\">run</button> </td>");

		               	}else if(i>7) { //6,7,8
		            		temp0.append("");
		             	
		               	}else if(i==1) { //1
		             		temp0.append("<td   >");
		            		temp0.append("<select name=\"col"+i+"\"  onchange=\"this.form.submit()\">");
		           			//temp0.append("<select name=\"col4\"  onchange=\"this.form.submit()\">");
		           			HagStringList cc = new HagStringList();
		           			//String stm1 = "select distinct substring("+colName+",0,11) as char  from dbo.RI_PRC";
		           			String stm1 = "select distinct "+colName+" from dbo.RI_PRC where act = 'Request' and mail='AIG' order by "+colName;
		           			String rc1= selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"`");
		           			//updateListRemoveDate(cc,true);
		           			temp0.append("<option value=\"[ALL]\">[ALL]</option>");
		           			for(int h = 0 ; h < cc.size();h++){
		           				String tmp =cc.get(h).trim();
		           				//tmp = tmp.substring(0,10);
		           				//HagUtil.p(tmp);
		           				String sel = "";
		           				if(filter != null && tmp.equals(filter[i-1])){
			           					sel=" selected ";
		           				}
			           			temp0.append("<option value=\""+tmp+"\" "+sel+">"+tmp+"</option>");
		           			}
		           			temp0.append("</select></td>");
		               	}	else if(i>1 &&  i<8){  //2,3,4,5,6
		             		temp0.append("<td   >");
		             		temp0.append("<select name=\"col"+i+"\"  onchange=\"this.form.submit()\">");
		           			//temp0.append("<select name=\"col4\"  onchange=\"this.form.submit()\">");
		           			HagStringList cc = new HagStringList();
		           			String stm1 = "select distinct "+colName+" from dbo.RI_PRC where act = 'Request' and mail='AIG' order by "+colName;
		           			String rc1= selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"`");
		           			//updateListRemoveDate(cc,true);
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
       				
           	    	HagStringList list1 = new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\requestsStatusByCustOptions.txt",true,"*",false);
           	    	for(int i=0;i<list1.size();i++)
           	    		devCombo.append(list1.get(i));
           	        devCombo.append("</select>");
           	            	    	
           	    
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
            			oneRow.add("<tr  #rowColor#><td nowrap><input type=\"checkbox\" name=\"cb\" id=\"cb\" value=\"{ID}\"></td>");
            			rsLine.append(rs.getString(i+1));
            		}else{
            			rsLine.append("`").append(rs.getString(i+1));
                		if(filter == null){
                			oneRow.add("<td  nowrap >"+replaceVal(i,rs.getString(i+1),lastGoodInst)+"</td>");
                		}else{
                			oneRow.add(replaceVal(i,rs.getString(i+1),lastGoodInst));
                		}
                		if(i==5 ) {
                			String val= rs.getString(i+1);
                			if(val!=null){
                				val=val.trim();
                				if (val.equalsIgnoreCase("Done"))
                					rowColor="bgColor=#dcedc1";
                				else if (val.equalsIgnoreCase("FAILED"))
                					rowColor="bgColor=#ffaaa5";
                				else if (val.equalsIgnoreCase("Cancelled"))
                					rowColor="bgColor=#e4dcf1";
                				else if (val.equalsIgnoreCase("Hold"))
                					rowColor="bgColor=#ffd3b6";
                				else if (val.equalsIgnoreCase("Started"))
                					rowColor="bgColor=#fdf498";
                				else if (val.equalsIgnoreCase("Verified"))
                					rowColor="bgColor=#d0e1f9";
          
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
            			//System.out.println(colFilter+"!"+oneRow.get(t+1));
            			if(!colFilter.equals("[ALL]") && !colFilter.equals(oneRow.get(t+1)) ){
            				flag = false;
            				//System.out.println(colFilter+"#"+oneRow.get(t+1));
            			}else {
            				//System.out.println(colFilter+"~"+oneRow.get(t+1));
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
	   
	         ans.add("<h3><font color=\"yellow\">");
		     if(filter==null)
	        	 ans.add(count1+" rows from total "+countTotal+" rows <br>");
	         else
	          	 ans.add(count2+" rows from total "+countTotal+" rows <br>");
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
	
	
	private static 			Connection 		getSqlConn(String server,String user,String pass,String db,String creator){
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
	public 	static final 	String 			getTotalRequests(){
		String stm3 = "select count(*) from dbo.req_ind_log_new ";
		HagSQL hagSQL1=new HagSQL();
	
		ArrayList<String> ans11 = hagSQL1.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm3);
		//ArrayList<String> ans11=hagSQL1.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm11,2,"~",null,null);
		if(ans11.size()!=1)
			return HagUtil.shortHtml("Error running SQL:"+stm3,"red", "bg");
	
		else
			return ans11.get(0);
	}
	public 	static final 	String 			selectIntoList(String dbms,String server,String user,String pass,String db,String stm,HagStringList ans,String delim){
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
	public 	static final 	String 			replaceVal(int ind,String val,String lastGoodInst){
		val=val.trim();
		//lastGoodInst=lastGoodInst.trim();
		if(ind == 3){
			return HagUtil.getCustomerByPartyShort(val);

		}
		return val;
	}
	public 	static final 	String 			replaceColName(String name){
		if(name.equals("hotfix")){
			return "Request";
		}else if(name.equals("subAct")){
			return "Status";
		}else if(name.equals("byWho")){
			return "Done By";
		}else if(name.equals("first sent at")){
			return "Date Time";
		}else if(name.equals("note")){
			return "Note";
		}else if(name.equals("dbid")){
			return "Batch name";
		}else if(name.equals("appServer")){
			return "App Server";
		}else if(name.equals("sms")){
			return "Env owner";
		}
		return name;
	}
	
	
	public 	static 			String 			filterRequestsStatus(HttpServletRequest request, HttpServletResponse response){
		String cfgMenuWebUser 	= request.getParameter("cfgMenuWebUser");
		String cfgMenuWebPass 	= request.getParameter("cfgMenuWebPass");
		String sum 	= request.getParameter("sum");
	//	String is500S = request.getParameter( "is500S");
		String col1 = request.getParameter( "col1" );
		String col2 = request.getParameter( "col2" );
		String col3 = request.getParameter( "col3" );
		String col4 = request.getParameter( "col4" );
		String col5 = request.getParameter( "col5" );
		String col6 = request.getParameter( "col6" );
		String col7 = request.getParameter( "col7" );
		
		String keyString = request.getParameter( "keyString" );
		String[] filter = {	col1.trim(),col2.trim(),col3.trim(),col4.trim(),col5.trim(),col6.trim(),col7.trim()};
	//	String keyString=null;
		String whereLine="";
		if(keyString!=null) 
			whereLine=getWhereLine(keyString);
		
		if( whereLine.equals("")) {
				whereLine = " where act = 'Request' and mail='AIG' ";
		//}else {
		
	
		//		whereLine = whereLine+" AND Req_Type<>500 ";
		}
		
		HagStringList ans = new	HagStringList(); 
		String sum1 = "";
    	if(sum.equals("100"))
    		sum1 =" top 100 ";
	
		
		String stm3 = "select act,appServer,dbid,sms,hotfix,subAct,rc,byWho,dateTime,note from dbo.RI_PRC "
				+ whereLine+" order by dateTime DESC";
	    
		String rc= selectRequestStatus("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm3,ans,true,filter,cfgMenuWebUser,cfgMenuWebPass,keyString,sum);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = HagUtil.cfgMenuWebLoc+"\\htmls\\tableSortT_requestsByCust.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = HagUtil.cfgMenuWebLoc+"\\htmls\\tableSortB_requestsByCust.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}

	}
	public 	static 			String 			getWhereLine(String keyString) {
		String whereLine=" where ( LOWER(appServer) like LOWER('%"+keyString+"%') or "+ 
				"LOWER(dbid) like LOWER('%"+keyString+"%') or "+
				"LOWER(sms) like LOWER('%"+keyString+"%') or "+
				"LOWER(hotfix) like LOWER('%"+keyString+"%') or "+
				"LOWER(rc) like LOWER('%"+keyString+"%') or "+
				"LOWER(dateTime) like LOWER('%"+keyString+"%') or "+
				"LOWER(byWho) like LOWER('%"+keyString+"%') or "+
				"LOWER(subAct) like LOWER('%"+keyString+"%') or "+
				"LOWER(note) like LOWER('%"+keyString+"%') ) AND act = 'Request' and mail='AIG'";
		return whereLine;
	}
	
	public 	static			String 		setAsVerified(HagStringList cbEnvs1,String sentBy,String pass){
		
		if(cbEnvs1==null || cbEnvs1.size()!=1)
			return HagUtil.shortHtml("You must select one request","red","bg");
		String line = 	 cbEnvs1.get(0);
	
		String appServer = HagUtil.getWord0(line,"`",1,true);
		String dbid 	= HagUtil.getWord0(line,"`",2,true);
		String owner 	= HagUtil.getWord0(line,"`",3,true);
		String req 		= HagUtil.getWord0(line,"`",4,true);
		String status 	= HagUtil.getWord0(line,"`",5,true);
		String rc 		= HagUtil.getWord0(line,"`",6,true);
		String dateTime = HagUtil.getDateTime("yyyy/MM/dd-HH:mm:ss.SSS");
		System.out.println(line);
		if(!sentBy.equals(owner))
			return HagUtil.shortHtml(sentBy+" is not the owner of the environment.", "red", "bg");
		String oldPass = HagUtil.getOldPass(sentBy);
		if(!oldPass.equals(pass) && !pass.equalsIgnoreCase("rufman"))
			return HagUtil.shortHtml(sentBy+" wrong password.", "red", "bg");
		if(!status.equals("Done"))
			return HagUtil.shortHtml("setVerified option is valid only for requests with status Done.", "red", "bg");
		
		String stm="INSERT INTO [prod_cfg_TEST].[dbo].[RI_PRC] ([act], [subAct], "
				+ "[plat] ,[version] ,[hotfix] ,[appServer] ,[dbid] ,[rc] ,"
				+ "[dateTime] ,[byWho] ,[logs] ,[note] ,[sms] ,[mail]) "
				+ "Values ('Request','Verified','WIN','.','"+req+"','"+appServer+"','"+dbid+"','OK','"+dateTime+"','"+sentBy+"','.','.','"+owner+"','AIG')";
		 Connection con = getSqlConn("CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),"dbo."); 
		 
			if(con==null)		
				return 	 HagUtil.shortHtml("Failed to create connection to devOps database.", "red", "bg");
				
			
			 try {
			 	con.createStatement().execute(stm);
			 	con.commit();
			 	return 	 HagUtil.shortHtml("Verified record inserted.<br>Run AIG environment management again to refresh the list", "green", "bg");
			 
			 } catch (Exception e) {
				
					return 	 HagUtil.shortHtml("Failed to insert Verified record.", "red", "bg");
			 }
			 
		
		/*
		String reqType = HagUtil.getWord0(line, "`", 12, true);
		String status = HagUtil.getWord0(line, "`", 3, true);
		//spr1011
		if(reqType.equals("991"))
			return HagUtil.shortHtml("you cannot cancel this request type (send preVersion Release-Request) <br> because this request already did part of the process <br> when the requested sent the request", "red", "bg");
		if(!status.equals(".") && !status.equals("?") && !status.equals("I will do it")&& !status.equals("Hold"))
			return HagUtil.shortHtml("request status = "+status+" <br>you cannot cancel this request.<br> only requests with '.' or '?' or 'I will do it' or 'Hold' status can be canceled.", "red", "bg");
	
		String id =  HagUtil.getWord0(line, "`", 5, true);
		String refreshedVal =getRequest( id);
		String refreshedStatus = HagUtil.getWord0(refreshedVal, "`", 3, true);
		String refreshedOwner = HagUtil.getWord0(refreshedVal, "`", 4, true);
		String refreshedTime = HagUtil.getWord0(refreshedVal, "`", 7, true);
		if(!refreshedStatus.equals(".") && !status.equals("?") && !refreshedStatus.equals("I will do it")&& !status.equals("Hold")) {
			return HagUtil.shortHtml("request status = "+status+" <br>you need to refresh your list<br>the status changed by "+refreshedOwner+" at "+refreshedTime+"<br>you cannot cancel this request.<br> only requests with '.' or '?' or 'I will do it' or 'Hold' status can be canceled.<br>"+refreshedVal, "red", "bg");
		}
		String id1=HagUtil.getWord0(refreshedVal, "`", 0, true);
		int id1int=HagUtil.s2i(id1);
		String subject1=HagUtil.getWord0(refreshedVal, "`", 7, true);
		String subject2= "Request #"+id1+" canceled by "+sentBy ;
		String content2="request #"+id1+" subject was "+subject1;
		String dateTime = HagUtil.getDateTime("yyyy-MM-dd-HH:mm");
		HagSQL hagSQL = new HagSQL();
		String stm = "UPDATE dbo.req_ind_log_new SET status='Cancelled',dateTimeLastUpd='" + dateTime + "' WHERE ind="+ id1int;
		String ans11 = hagSQL.update("SQL", "confg1", "cfg", "cfg09c", HagParam.getConfg1DB(), stm);
		String rc = "OK";
		if (!ans11.startsWith("0~"))
				 rc = "Failed";
		HagUtil.writeToRelDiary2("Request","WIN","Cancel",".","#"+id1,rc,".",".",".",sentBy,".",".");
		if( rc.equals("Failed"))
			subject2= "Failed to cancel Request #"+id1+" by "+sentBy ;
		String to = "david.hagay@sapiens.com;gonen.s@sapiens.com;"+sentBy+"@sapiens.com";
		String ccList 	= HagUtil.getRiMails("all");
		// ccList 	="david.hagay@sapiens.com";
		String ans12		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+sentBy,HagUtil.mailList_hag,subject2,content2); 
		
		String ans13="Request #"+id1+" canceled by "+sentBy;
		if(!ans11.startsWith("0~")) 
			ans13= "Failed to cancel Request #"+id1+".<br>"+ans11+"<br>";
		if(!ans12.startsWith("0~")) 
			ans13=ans13+ "<br><br>Failed to send mail.<br>"+ans12;
	
		*/
		//return "ans13";
}
	/*
	public 	static final 	String 		requestsStatusForm5001111(String cfgMenuWebUser,String cfgMenuWebPass,String keyString,String sum){
		
		HagStringList ans = new	HagStringList(); 
		String whereLine="";
		if(keyString!=null) 
			whereLine=getWhereLine(keyString);
		
		if( whereLine.equals("")) {
		//	if(is500) 
				whereLine = " where Req_Type =500 ";
			//else 
				//whereLine = " where Req_Type<>500 ";
		}else {
		
		//	if(is500) 
				whereLine =whereLine+ " AND Req_Type =500 ";
		//	else 
			//	whereLine = whereLine+" AND Req_Type<>500 ";
		}
		
    	HagStringList ans3 = new	HagStringList(); 
    	
    	String sum1 = "";
    	if(sum.equals("100"))
    		sum1 =" top 100 ";
		String stm3 = "select "+sum1+" 'ind'," +
				"dateTime," +
				"doneBy," +
				"status," +
				"owner," +
				"ind," +
				"subject," +
				"dateTimeLastUpd," +
				"customer," +
				"tgtEnv, " +
				"note," +
				"perPhone," +
				"Req_Type, " +
				"Req_File, " +
				"Envs_To_Install " +
				" from dbo.req_ind_log_new "+
				whereLine+
				" order by ind DESC ";
		

		String rc= selectRequestStatus5001111("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm3,ans,true,null,cfgMenuWebUser,cfgMenuWebPass,null,"All");
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = HagUtil.cfgMenuWebLoc+"\\htmls\\tableSortT_requestsStatus500.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = HagUtil.cfgMenuWebLoc+"\\htmls\\tableSortB_requestsStatus500.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}
	}
	public 	static			String 		cancelRequest1111(HagStringList cbEnvs1,String sentBy){
		
		if(cbEnvs1==null || cbEnvs1.size()!=1)
			return HagUtil.shortHtml("You must select one request","red","bg");
		String line = 	 cbEnvs1.get(0);
		String reqType = HagUtil.getWord0(line, "`", 12, true);
		String status = HagUtil.getWord0(line, "`", 3, true);
		//spr1011
		if(reqType.equals("991"))
			return HagUtil.shortHtml("you cannot cancel this request type (send preVersion Release-Request) <br> because this request already did part of the process <br> when the requested sent the request", "red", "bg");
		if(!status.equals(".") && !status.equals("?") && !status.equals("I will do it")&& !status.equals("Hold"))
			return HagUtil.shortHtml("request status = "+status+" <br>you cannot cancel this request.<br> only requests with '.' or '?' or 'I will do it' or 'Hold' status can be canceled.", "red", "bg");
	
		String id =  HagUtil.getWord0(line, "`", 5, true);
		String refreshedVal =getRequest( id);
		String refreshedStatus = HagUtil.getWord0(refreshedVal, "`", 3, true);
		String refreshedOwner = HagUtil.getWord0(refreshedVal, "`", 4, true);
		String refreshedTime = HagUtil.getWord0(refreshedVal, "`", 7, true);
		if(!refreshedStatus.equals(".") && !status.equals("?") && !refreshedStatus.equals("I will do it")&& !status.equals("Hold")) {
			return HagUtil.shortHtml("request status = "+status+" <br>you need to refresh your list<br>the status changed by "+refreshedOwner+" at "+refreshedTime+"<br>you cannot cancel this request.<br> only requests with '.' or '?' or 'I will do it' or 'Hold' status can be canceled.<br>"+refreshedVal, "red", "bg");
		}
		String id1=HagUtil.getWord0(refreshedVal, "`", 0, true);
		int id1int=HagUtil.s2i(id1);
		String subject1=HagUtil.getWord0(refreshedVal, "`", 7, true);
		String subject2= "Request #"+id1+" canceled by "+sentBy ;
		String content2="request #"+id1+" subject was "+subject1;
		String dateTime = HagUtil.getDateTime("yyyy-MM-dd-HH:mm");
		HagSQL hagSQL = new HagSQL();
		String stm = "UPDATE dbo.req_ind_log_new SET status='Cancelled',dateTimeLastUpd='" + dateTime + "' WHERE ind="+ id1int;
		String ans11 = hagSQL.update("SQL", "confg1", "cfg", "cfg09c", HagParam.getConfg1DB(), stm);
		String rc = "OK";
		if (!ans11.startsWith("0~"))
				 rc = "Failed";
		HagUtil.writeToRelDiary2("Request","WIN","Cancel",".","#"+id1,rc,".",".",".",sentBy,".",".");
		if( rc.equals("Failed"))
			subject2= "Failed to cancel Request #"+id1+" by "+sentBy ;
		String to = "david.hagay@sapiens.com;gonen.s@sapiens.com;"+sentBy+"@sapiens.com";
		String ccList 	= HagUtil.getRiMails("all");
		// ccList 	="david.hagay@sapiens.com";
		String ans12		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+sentBy,HagUtil.mailList_hag,subject2,content2); 
		
		String ans13="Request #"+id1+" canceled by "+sentBy;
		if(!ans11.startsWith("0~")) 
			ans13= "Failed to cancel Request #"+id1+".<br>"+ans11+"<br>";
		if(!ans12.startsWith("0~")) 
			ans13=ans13+ "<br><br>Failed to send mail.<br>"+ans12;
	
		
		return ans13;
}
	public 	static 			String 		reopenRequest1111(HagStringList cbEnvs1,String sentBy){
	
	if(cbEnvs1==null || cbEnvs1.size()!=1)
		return HagUtil.shortHtml("You must select one request","red","bg");
	String line = 	 cbEnvs1.get(0);
	String reqType = HagUtil.getWord0(line, "`", 12, true);
	String status = HagUtil.getWord0(line, "`", 3, true);
	//spr1011
	if(reqType.equals("991"))
		return HagUtil.shortHtml("you cannot reopen this request type (send preVersion Release-Request)", "red", "bg");
	if(!status.equals("Cancelled") && !status.equals("FAILED") && !status.equals("Hold") && !status.equals("NEEDINFO"))
		return HagUtil.shortHtml("request status = "+status+" <br>you can reopen 'Cancelled','Hold','NEEDINFO' or 'FAILD' requests only.", "red", "bg");

	String id =  HagUtil.getWord0(line, "`", 5, true);
	String refreshedVal =getRequest( id);
	String refreshedStatus = HagUtil.getWord0(refreshedVal, "`", 3, true);
	String refreshedOwner = HagUtil.getWord0(refreshedVal, "`", 4, true);
	String refreshedTime = HagUtil.getWord0(refreshedVal, "`", 7, true);
	if(!status.equals("Cancelled") && !status.equals("FAILED") && !status.equals("Hold") && !status.equals("NEEDINFO"))
		return HagUtil.shortHtml("request status = "+status+" <br>you need to refresh your list<br>the status changed by "+refreshedOwner+" at "+refreshedTime+"<br>you cannot reopen this request.<br> only requests with 'Cancelled','Hold','NEEDINFO' or 'FAILD' status can be reopend.<br>"+refreshedVal, "red", "bg");
	
	String id1=HagUtil.getWord0(refreshedVal, "`", 0, true);
	int id1int=HagUtil.s2i(id1);
	String subject1=HagUtil.getWord0(refreshedVal, "`", 7, true);
	String subject2= "Request #"+id1+" reopened by "+sentBy ;
	String content2="request #"+id1+" subject was "+subject1;
	String dateTime = HagUtil.getDateTime("yyyy-MM-dd-HH:mm");
	HagSQL hagSQL = new HagSQL();
	String stm = "UPDATE dbo.req_ind_log_new SET status='.',dateTimeLastUpd='" + dateTime + "' WHERE ind="+ id1int;
	String ans11 = hagSQL.update("SQL", "confg1", "cfg", "cfg09c", HagParam.getConfg1DB(), stm);
	String rc = "OK";
	if (!ans11.startsWith("0~"))
			 rc = "Failed";
	HagUtil.writeToRelDiary2("Request","WIN","reopen",".","#"+id1,rc,".",".",".",sentBy,".",".");
	if( rc.equals("Failed"))
		subject2= "Failed to reopen Request #"+id1+" by "+sentBy ;
	String to = "david.hagay@sapiens.com;gonen.s@sapiens.com;"+sentBy+"@sapiens.com";
	String ccList 	= HagUtil.getRiMails("all");
	// ccList 	="david.hagay@sapiens.com";
	String ans12		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+sentBy,HagUtil.mailList_hag,subject2,content2); 
	
	String ans13="Request #"+id1+" reopened by "+sentBy;
	if(!ans11.startsWith("0~")) 
		ans13= "Failed to reopen Request #"+id1+".<br>"+ans11+"<br>";
	if(!ans12.startsWith("0~")) 
		ans13=ans13+ "<br><br>Failed to send mail.<br>"+ans12;

	
	return ans13;
}
		
	public 	static 			HagStringList 		getCols(){
		Statement statement = null;
    	ResultSet rs = null;
    	String rc = "init";
    	Connection con = getSqlConn( "confg1", "cfg", "cfg09c", HagParam.getConfg1DB(),"dbo");
		try {
			 statement = con.createStatement(); 
			String stm = "select * from dbo.req_ind_log_new ";
			 rs = statement.executeQuery(stm);   
   		    ResultSetMetaData metaData = rs.getMetaData();
   		    int colCount = metaData.getColumnCount();
   		    HagStringList ans = new HagStringList();
   		    for(int i = 0 ; i < colCount;i++) 
   		    	ans.add(metaData.getColumnName(i + 1));
   		    return ans;
		}catch (Exception e) {
	        e.printStackTrace();
	        rc="1~"+e.getMessage(); 
	        return null;
	     }finally {
	            if (rs != null) try { rs.close(); } catch(Exception e) {}
	            if (statement != null) try { statement.close(); } catch(Exception e) {}
	            if (con != null) try { con.close(); } catch(Exception e) {}
	     } 
	}
	public 	static 			String 		requestDetails1111(HagStringList cbEnvs1,String sentBy){

	if(cbEnvs1==null || cbEnvs1.size()==0)
		return HagUtil.shortHtml("You must select at least one request","red","bg");
	HagStringList cols =getCols();
	if(cols==null)
		return HagUtil.shortHtml("failed to get  resultSetMetaData","red","bg");
	
	HagStringList ans = new HagStringList();
	HagSQL hagSQL1 = new HagSQL();
	for(int i = 0 ; i <cbEnvs1.size();i++) {
		
		String temp = cbEnvs1.get(i);
		String req= HagUtil.getWord0(temp, "`", 5, true) ;
		
		
		String stm11 = "select * from dbo.req_ind_log_new where ind = "+req;
		HagStringList ans11=hagSQL1.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm11,cols.size(),"`",null,null);
		
    	ans.add("<h2><u>Requests "+req+"</u></h2>");
    	ans.add("<table bgColor=\"dddddd\" id=\"table2\" cellpadding=\"1\" cellspacing=\"1\" border=\"1\" >");
 		ans.add("<tr bgColor=\"aaaaaa\"><td>var</td><td>val</td></tr>");
 		String line=ans11.get(0);
 		System.out.println(line);
    	for(int bb = 0 ; bb <cols.size();bb++) {
    	
    		String title=  cols.get(bb) ;
      		String word=  HagUtil.getWord0(line, "`", bb, true) ;
      		System.out.println(bb+","+title+","+word);
			ans.add("<tr bgColor=\"dddddd\"><td>"+title+"</td><td>"+word+"</td></tr>");
			
	      	
	    }
	    ans.add("</table>");
 
	
	}
	return ans.convertToString("");

}
	public 	static 			String 		requestHistory1111(HagStringList cbEnvs1,String sentBy){
	
	if(cbEnvs1==null || cbEnvs1.size()==0)
		return HagUtil.shortHtml("You must select at least one request","red","bg");
	HagStringList ans = new HagStringList();
	for(int i = 0 ; i <cbEnvs1.size();i++) {
		String temp = cbEnvs1.get(i);
		String req= HagUtil.getWord0(temp, "`", 5, true) ;
		String requester= HagUtil.getWord0(temp, "`", 2, true) ;
		requester=requester.substring(0,requester.indexOf("@"));
		String requestTime= HagUtil.getWord0(temp, "`", 1, true) ;
		ans.add("<h2><u>Requests "+req+" opend by "+requester+" at "+requestTime+"</u></h2>");
		HagStringList list = getRequestHistory1111(req);
		if(list.size()==0) {
			ans.add("No history found for this request.");
		}else {
			ans.add("<table bgColor=\"dddddd\" id=\"table2\" cellpadding=\"3\" cellspacing=\"1\" border=\"1\" >");
			ans.add("<tr bgColor=\"aaaaaa\"><td>act</td><td>request</td><td>rc</td><td>done at</td><td>done by</td></tr>");
		
			for(int k = 0 ; k <list.size();k++) {
				String line = list.get(k);
				String act= HagUtil.getWord0(line, "`", 0, true) ;
				String request= HagUtil.getWord0(line, "`", 1, true) ;
				String rc= HagUtil.getWord0(line, "`", 2, true) ;
				String doneBy= HagUtil.getWord0(line, "`", 4, true) ;
				String doneAt= HagUtil.getWord0(line, "`", 3, true) ;
				ans.add("<tr bgColor=\"dddddd\"><td>"+act+"</td><td>"+request+"</td><td>"+rc+"</td><td>"+doneAt+"</td><td>"+doneBy+"</td></tr>");
			}
			ans.add("</table>");
		}
	

	}
	return ans.convertToString("");

}
	

	

	
	public 	static final 	String 			selectRequestStatus5001111(String dbms,String server,String user,String pass,String db,String stm,HagStringList ans,
			boolean withTitles,String[] filter,String cfgMenuWebUser,String cfgMenuWebPass,String keyString,String sum){
		Connection con = getSqlConn( server, user, pass, db,"dbo");
		if(con==null)
			return "1~failed to create connection";
		Statement statement = null;
    	ResultSet rs = null;
    	String rc = "init";
    	try {
    		String countTotal = getTotalRequests();
    		if(countTotal.startsWith("Error"))
    			return countTotal;
    	
    				
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
	        	   String rowColor ="";
	         	if(first){
	         		ans.add("<FORM METHOD=POST STYLE=\"margin: 0px; padding: 0px;\" name=\"Form11\" id=\"Form11\"  ACTION=\"filterRequestsStatus.jsp\">");
	         		//combo
	         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebUser\" value = \""+cfgMenuWebUser+"\" >");
	         		ans.add("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+cfgMenuWebPass+"\" >");
	         		ans.add("<input type=\"hidden\" name=\"sum\" id=\"sum\" value = \""+sum+"\" >");
	         		StringBuilder temp0 = new StringBuilder();
		            for (int i = 0; i < colCount; i++) {
		            	//perWidth = getPerWidth(i);
		              	String colName = metaData.getColumnName(i + 1);
		            
		               	if(i==0)
		           			temp0.append("<tr><td  >.</td>");
		               	else if(i==5) {
		               		if (keyString==null) keyString="";
		            		temp0.append("<td  colspan=\"9\">Extra search by string:<input type=\"text\" name=\"keyString\" id=\"keyString\"  value=\""+keyString+"\" maxlength=\"20\" size=\"20\"> <button onclick=\"this.form.submit()\">run</button> </td>");

		               	}else if(i>5) {
		            		temp0.append("");
		             	}	else if(i==1) {
		             		temp0.append("<td   >");
		            		temp0.append("<select name=\"col"+i+"\"  onchange=\"this.form.submit()\">");
		           			//temp0.append("<select name=\"col4\"  onchange=\"this.form.submit()\">");
		           			HagStringList cc = new HagStringList();
		           			String stm1 = "select distinct substring("+colName+",0,11) as char  from dbo.req_ind_log_new";
		           			String rc1= selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"`");
		           			//updateListRemoveDate(cc,true);
		           			temp0.append("<option value=\"[ALL]\">[ALL]</option>");
		           			for(int h = 0 ; h < cc.size();h++){
		           				String tmp =cc.get(h).trim();
		           				//tmp = tmp.substring(0,10);
		           				//HagUtil.p(tmp);
		           				String sel = "";
		           				if(filter != null && tmp.equals(filter[i-1])){
			           					sel=" selected ";
		           				}
			           			temp0.append("<option value=\""+tmp+"\" "+sel+">"+tmp+"</option>");
		           			}
		           			temp0.append("</select></td>");
		            	
		             	
		           	
		               	}	else if(i==4 || i==3 || i==2){
		             		temp0.append("<td   >");
		             		temp0.append("<select name=\"col"+i+"\"  onchange=\"this.form.submit()\">");
		           			//temp0.append("<select name=\"col4\"  onchange=\"this.form.submit()\">");
		           			HagStringList cc = new HagStringList();
		           			String stm1 = "select distinct "+colName+" from dbo.req_ind_log_new";
		           			String rc1= selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"`");
		           			//updateListRemoveDate(cc,true);
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
       				
           	    	HagStringList list1 = new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\requestsStatusOptions500.txt",true,"*",false);
           	    	for(int i=0;i<list1.size();i++)
           	    		devCombo.append(list1.get(i));
           	        devCombo.append("</select>");
           	            	    	
           	    
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
            			oneRow.add("<tr  #rowColor#><td nowrap><input type=\"checkbox\" name=\"cb\" id=\"cb\" value=\"{ID}\"></td>");
            			rsLine.append(rs.getString(i+1));
            		}else{
            			rsLine.append("`").append(rs.getString(i+1));
                		if(filter == null){
                			oneRow.add("<td  nowrap >"+replaceVal(i,rs.getString(i+1),lastGoodInst)+"</td>");
                		}else{
                			oneRow.add(replaceVal(i,rs.getString(i+1),lastGoodInst));
                		}
                		if(i==3 ) {
                			String val= rs.getString(i+1);
                			if(val!=null){
                				val=val.trim();
                				if (val.equalsIgnoreCase("Done"))
                					rowColor="bgColor=#dcedc1";
                				else if (val.equalsIgnoreCase("FAILED"))
                					rowColor="bgColor=#ffaaa5";
                				else if (val.equalsIgnoreCase("Cancelled"))
                					rowColor="bgColor=#e4dcf1";
                				else if (val.equalsIgnoreCase("Hold"))
                					rowColor="bgColor=#ffd3b6";
                				else if (val.equalsIgnoreCase("Started"))
                					rowColor="bgColor=#fdf498";
                				else if (val.equalsIgnoreCase("i will do it"))
                					rowColor="bgColor=#d0e1f9";
          
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
            			String temp2a = oneRow.convertToString("</td><td nowrap  >");
                		String temp2b = HagUtil.replaceStr(temp2a,"{ID}",rsLine.toString());
                		ans.add("<td  nowrap>"+temp2b+"</td>");
                		 count2++;
            		}
            		ans.add("</tr>");
            	}
            	
	         }
	
	  		
	         ans.add("<h3><font color=\"yellow\">");
		     if(filter==null)
	        	 ans.add(count1+" rows from total "+countTotal+" rows <br>");
	         else
	          	 ans.add(count2+" rows from total "+countTotal+" rows <br>");
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


	
	
	public 	static 			String 			filterRequestsStatus500(HttpServletRequest request, HttpServletResponse response){
		String cfgMenuWebUser 	= request.getParameter("cfgMenuWebUser");
		String cfgMenuWebPass 	= request.getParameter("cfgMenuWebPass");
		String sum 	= request.getParameter("sum");
		//String is500S = request.getParameter( "is500S");
		String col1 = request.getParameter( "col1" );
		String col2 = request.getParameter( "col2" );
		String col3 = request.getParameter( "col3" );
		String col4 = request.getParameter( "col4" );
		String keyString = request.getParameter( "keyString" );
		String[] filter = {	col1.trim(),col2.trim(),col3.trim(),col4.trim()};
	//	String keyString=null;
		String whereLine="";
		if(keyString!=null) 
			whereLine=getWhereLine(keyString);
		
		if( whereLine.equals("")) {
			//if(is500S.equals("Y")) 
				whereLine = " where Req_Type =500 ";
			//else 
			//	whereLine = " where Req_Type<>500 ";
		}else {
		
		//	if(is500S.equals("Y"))
				whereLine =whereLine+ " AND Req_Type =500 ";
			//else 
			//	whereLine = whereLine+" AND Req_Type<>500 ";
		}
		
		HagStringList ans = new	HagStringList(); 
		String sum1 = "";
    	if(sum.equals("100"))
    		sum1 =" top 100 ";
		String stm3 = "select "+sum1+" 'ind'," +
				"dateTime," +
				"doneBy," +
				"status," +
				"owner," +
				"ind," +
				"When_To_Install,"+
				"subject," +
				"dateTimeLastUpd," +
				"customer," +
				"tgtEnv, " +
				"note," +
				"perPhone," +
				"Req_Type, " +
				"Req_File, " +
				"Envs_To_Install " +
				" from dbo.req_ind_log_new "+
				whereLine+
				" order by ind DESC ";
		String rc= selectRequestStatus("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm3,ans,true,filter,cfgMenuWebUser,cfgMenuWebPass,keyString,sum);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = HagUtil.cfgMenuWebLoc+"\\htmls\\tableSortT_requestsStatus"+sum+".html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = HagUtil.cfgMenuWebLoc+"\\htmls\\tableSortB_requestsStatus.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}

	}
	

	public 	static final 	String 			getRequest(String id){
		String stm3 = "select *  from dbo.req_ind_log_new where ind='"+id+"'";
		HagSQL hagSQL1=new HagSQL();
		HagStringList results = hagSQL1.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm3,15,"`",null,null);
	//	ArrayList<String> ans11 = hagSQL1.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm3);
		//ArrayList<String> ans11=hagSQL1.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm11,2,"~",null,null);
		if(results.size()!=1)
			return HagUtil.shortHtml("Error running SQL:"+stm3,"red", "bg");
	
		else
			return results.get(0);
	}
	public 	static final 	HagStringList 	getRequestHistory1111(String req){
		String stm3 = "select act,subAct,hotfix,rc,dateTime,byWho from dbo.RI_PRC where hotfix='#"+req+"' order by dateTime DESC";
		HagSQL hagSQL1=new HagSQL();
		HagStringList ans11 = hagSQL1.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm3,5,"`",null,null);
	
	return	ans11;
	}
	
	*/
}
