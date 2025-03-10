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

public class HagIKnowWhatYouDid {
	
	public static final String getFilterVals(String var,String note) {
		StringBuilder temp0=new StringBuilder();
		temp0.append("<tr>");
		temp0.append("<td>");
		temp0.append(var);
		temp0.append("</td>");
		temp0.append("<td>");
	  	temp0.append("<select name="+var+" >");
   		HagStringList cc = new HagStringList();
   		String stm1 = "select distinct "+var+" from dbo.RI_PRC order by "+var;
   		String rc1= HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,cc,"~");
   		temp0.append("<option value=\"[ALL]\" selected>[ALL]</option>");
   	
   		for(int h = 0 ; h < cc.size();h++){
   			String tmp =cc.get(h);
   			if(tmp!=null)
   				tmp =tmp.trim();
   			temp0.append("<option value=\""+tmp+"\" >"+tmp+"</option>");
   		}
   		temp0.append("</select></td>");
   		temp0.append("</td>");		
   		temp0.append("<td>");
   		temp0.append(note);
   		temp0.append("</td>");		
   		temp0.append("</tr>");
   		return temp0.toString();
	}
	public static final String getFilterVals1(String var,String note) {
		StringBuilder temp0=new StringBuilder();
		temp0.append("<tr>");
		temp0.append("<td>");
		temp0.append(var);
		temp0.append("</td>");
		temp0.append("<td>");
	  	temp0.append("<select name="+var+" >");
   		
  
   	
   		temp0.append("<option value=\"[ALL]\" selected>[ALL]</option>");
   	
   		for(int h = 2022 ; h >2018;h--){
   			temp0.append("<option value=\""+h+"\" >"+h+"</option>");
   			for(int h1 = 12 ; h1 >0;h1--) {
   				String h2=""+h1;
   				if(h1<10)
   					h2="0"+h1;
   				temp0.append("<option value=\""+h+"/"+h2+"\" >"+h+"/"+h2+"</option>");
   			}
   		}
   		temp0.append("</select></td>");
   		temp0.append("</td>");		
   		temp0.append("<td>");
   		temp0.append(note);
   		temp0.append("</td>");		
   		temp0.append("</tr>");
   		return temp0.toString();
	}
	public static final String 	before(String user,String pass) {
		HagSQL hagSql = new HagSQL();
		HagRc hagRc = new HagRc();
    	String rowCount1 = hagSql.selectCount(hagRc, "CONFG1", "cfg","cfg09c", HagParam.getConfg1DB(), "dbo.RI_PRC");
    	if(hagRc.rc!=0)
    		return HagUtil.shortHtml("Failed go connect to RI_PRC table", "color", "bg");
		HagStringList ans1 = new	HagStringList(); 
		ans1.add("<body  bgColor=\"f9f9f9D8D8\">");
		ans1.add("<h2>I know what you did</h3>");
		ans1.add("<h3>in order to save time ,please run the filter here<br>we have <font color=\"blue\">"+rowCount1+" </font>records </h3>");
		ans1.add("<h3>if you will not filter the list it will be too slow.</h3>");
		ans1.add("<h3>please wait 5 sec for the filters to load</h3>");
	
		ans1.add("<table bgColor=\"D8D8D8\" id=\"table2\" cellpadding=\"1\" cellspacing=\"1\" border=\"1\" >");
		ans1.add("<FORM METHOD=POST name=\"Form\" ACTION=\"iKnowWhatYouDidNew.jsp\">");
		ans1.add("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+pass+"\" >");
		ans1.add("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebPass\" value = \""+user+"\" >");
		ans1.add("<input type=\"hidden\" name=\"total\" id=\"total\" value = \""+rowCount1+"\" >");
		ans1.add("<tr bgColor=\"848484\"><td>var</td><td>val</td><td>note</td></tr>");
	
		ans1.add(getFilterVals("act","."));
		ans1.add(getFilterVals("subAct","."));
		ans1.add(getFilterVals("plat","."));
		ans1.add(getFilterVals("version","."));
		ans1.add(getFilterVals("hotfix","hotfix or request"));
		ans1.add(getFilterVals("appServer","."));
		ans1.add(getFilterVals("dbid","batchName"));
		ans1.add(getFilterVals("rc","."));
		ans1.add(getFilterVals1("dateTime","."));
		ans1.add(getFilterVals("byWho","."));		
		
		ans1.add("</table><br>");
		ans1.add("<INPUT TYPE=SUBMIT value=\"Filter\" style=\"background-color:LightGreen\" ></FORM></body>");

		String str1 = ans1.convertToString(" ");
	
		return str1;	
	}
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
	public String[] getWhere(String act,String subAct,String plat,String version,String hotfix,
			String appServer,String dbid,String rc,String dateTime,String byWho) {
		String whereCols = "<tr><td>Act</td><td>SubAct</td><td>Plat</td><td>Version</td><td>Hotfix#Req</td><td>AppServer</td><td>BatchName</td><td>Rc</td><td>DateTime</td><td>ByWho</td><td>logs</td><td>note</td><td>sms</td><td>mail</td></tr>";
		String[] whereArr = new String[2];
		String whereS="";
		HagStringList whereSL = new HagStringList();
		if(!act.equals("[ALL]")) {
			whereSL.add(" act=LTRIM(RTRIM('"+act+"')) ");
			HagUtil.replaceStr(whereCols, ">Act<", " bgColor=\"#ee82ee\">Act<");
		}
		if(!subAct.equals("[ALL]")) {
			whereSL.add(" subAct=LTRIM(RTRIM('"+subAct+"')) ");
			whereCols=HagUtil.replaceStr(whereCols, ">SubAct<", " bgColor=\"#ee82ee\">SubAct<");
		}
		if(!version.equals("[ALL]")) {
			whereSL.add(" plat=LTRIM(RTRIM('"+plat+"')) ");
			whereCols=HagUtil.replaceStr(whereCols, ">Plat<", " bgColor=\"#ee82ee\">Plat<");
		}
		if(!version.equals("[ALL]")) {
			whereSL.add(" version=LTRIM(RTRIM('"+version+"')) ");
			whereCols=HagUtil.replaceStr(whereCols, ">Version<", " bgColor=\"#ee82ee\">Version<");
		}
		if(!hotfix.equals("[ALL]")) {
			whereSL.add(" hotfix=LTRIM(RTRIM('"+hotfix+"')) ");
			whereCols=HagUtil.replaceStr(whereCols, ">Hotfix#Req<", " bgColor=\"#ee82ee\">Hotfix#Req<");
		}
		if(!appServer.equals("[ALL]")) {
			whereSL.add(" appServer=LTRIM(RTRIM('"+appServer+"')) ");
			whereCols=HagUtil.replaceStr(whereCols, ">AppServer<", " bgColor=\"#ee82ee\">AppServer<");
		}
		if(!dbid.equals("[ALL]")) {
			whereSL.add(" dbid=LTRIM(RTRIM('"+dbid+"')) ");
			whereCols=HagUtil.replaceStr(whereCols, ">BatchName<", " bgColor=\"#ee82ee\">BatchName<");
		}
		if(!rc.equals("[ALL]")) {
			whereSL.add(" rc=LTRIM(RTRIM('"+rc+"')) ");
			whereCols=HagUtil.replaceStr(whereCols, ">Rc<", " bgColor=\"#ee82ee\">Rc<");
		}
		if(!dateTime.equals("[ALL]")) {
			whereSL.add(" dateTime like LTRIM(RTRIM('"+dateTime+"%')) ");
			whereCols=HagUtil.replaceStr(whereCols, ">DateTime<", " bgColor=\"#ee82ee\">DateTime<");
		}
		if(!byWho.equals("[ALL]")) {
			whereSL.add(" byWho=LTRIM(RTRIM('"+byWho+"')) ");
			whereCols=HagUtil.replaceStr(whereCols, ">ByWho<", " bgColor=\"#ee82ee\">ByWho<");
		}
		if(whereSL.size()>0)
			whereS=" where "+whereSL.convertToString(" AND ");
			
			whereArr[0]=whereS;
			whereArr[1]=whereCols;
			
		return whereArr;
	}
	public String after(HttpServletRequest request, HttpServletResponse response) {
		String cfgMenuUser 	= request.getParameter("cfgMenuUser");
		String act 		= request.getParameter("act");
		String subAct 	= request.getParameter("subAct");
		String plat 	= request.getParameter("plat");
		String version 	= request.getParameter("version");
		String hotfix 	= request.getParameter("hotfix");
		String appServer= request.getParameter("appServer");
		String dbid 	= request.getParameter("dbid");
		String rc 		= request.getParameter("rc");
		String dateTime = request.getParameter("dateTime");
		String byWho 	= request.getParameter("byWho");
		String total 	= request.getParameter("total");
		
		String[] whereArr = getWhere(act, subAct, plat, version, hotfix,appServer, dbid, rc, dateTime, byWho) ;
			String whereS = whereArr[0];
			String cols =  whereArr[1];
		Connection con = getSqlConn( "CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),"dbo");
		if(con==null)
			return "1~failed to create connection";
		Statement statement = null;
    	ResultSet rs = null;
    	String rc11 = "init";
    
    	String stm = "select * from dbo.RI_PRC "+whereS+" order by dateTime DESC";
    	HagStringList ansRows0=new HagStringList();
    	HagStringList ansRows1=new HagStringList();
    	
    	ansRows0.add("<body  bgColor=\"999999\">");
    	ansRows0.add("<h2> <font color=\"blue\" >I know what you did (new) </font></h2>");
    	
    	
      
     
    	ansRows1.add("<table  bgcolor = \"#8DC7BB\" border = 1 CELLPADDING=0 style=\"white-space:nowrap;\" width = \"100%\">");
    	ansRows1.add(cols);
    	int count1=0;
		
      	try {
    		 statement = con.createStatement();   
    		 rs = statement.executeQuery(stm);   
	         ResultSetMetaData metaData = rs.getMetaData();
	         int colCount = metaData.getColumnCount();
	     
	   		String ans9=null;
	         while (rs.next()) {
	           	 count1++;
	           	 if(count1==1)
	         	    continue;
	           	

	         	//data
                HagStringList oneRow = new HagStringList();
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
           		ans9 = setStyle(oneRow);
                	if(ans9 != null)//{
                	  ansRows1.add(ans9);
            }
	         rc11="0~done."; 	    
    	 }catch (Exception e) {
	        e.printStackTrace();
	        rc11="1~"+e.getMessage(); 	
	     }finally {
	            if (rs != null) try { rs.close(); } catch(Exception e) {}
	            if (statement != null) try { statement.close(); } catch(Exception e) {}
	            if (con != null) try { con.close(); } catch(Exception e) {}
	     } 
      	
    
      			ansRows1.add("</table>");
      					ansRows1.add("</body>");

      	if(rc11.startsWith("0~")) {
      		String ans0= "<h3><font color=\"yellow\"> <b><u>"+count1+"</b></u> rows from total <b><u>"+total+"</b></u> rows <br></font></h3>";
      	    
      	     
      		 return ansRows0.convertToString(" ")+ans0+ansRows1.convertToString(" ");
      	}
    	return rc11; 	
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
				//return val;
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
		//str.append("</tr>");
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
}
