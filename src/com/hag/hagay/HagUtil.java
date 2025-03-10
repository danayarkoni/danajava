package com.hag.hagay;










import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public final class HagUtil {
	static final String cfgMenuWebLoc = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb";
	static final String cfgMenuLoc = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu";
	
	
	public static final String [] uu = {"U01","U02","U03","U04","U05","U06","U07","U08","U09","U10","U11","U12","U13","U14","U15","U16","U17","U18","U19","U20"	};
	static final String s2="&nbsp;&nbsp;";
	static final String s4="&nbsp;&nbsp;&nbsp;&nbsp;";
	static final String s6="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	static final String cfgTeamMail = "cfgTeam@sapiens.com";

	
	static String mailList_devOps="david.ha@sapiens.com;gonen.s@sapiens.com;Danielle.Sapir@sapiens.com";
	static String mailList_gonHag="david.ha@sapiens.com;gonen.s@sapiens.com";
	static String mailList_hag="david.ha@sapiens.com";
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	static String replaceCmEnvApp="init";
	static String replaceCmEnvSql="init";
	static String replaceCmEnvDB="init";
	static String replaceCmEnvBatchName="init";
	static String replaceCmEnvUser="init";
	static String replaceCmEnvLine="init";

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
static String serverName="init";
public  static final  String 	updateCatalinaLines(String catalinaS,String ver,String debugFolder,HagStringList ans){
	HagStringList catalinaList = new HagStringList( catalinaS, false,"sadafsgdf",true);
	catalinaList.writeToFile(debugFolder+"\\catalina.org", false);
	if(ver.equals("0700") || ver.startsWith("06")){
		ans.add("need to be old tomcat");
		HagStringList catalinaList1=new HagStringList();
		boolean flag = false;
		for (int i =0;i<catalinaList.size();i++) {
			String line = catalinaList.get(i);
			if(line==null || line.trim().length()==0 || line.indexOf("@echo off")>-1 || line.toLowerCase().equals("rem")|| line.toLowerCase().startsWith("rem ") ||line.indexOf("setlocal")>-1) {
				catalinaList1.add(line);
			}else  {
				if( !flag) {
					catalinaList1.add("SET \"JAVA_HOME=c:\\Program Files\\Java\\jdk1.7.0_80\"");
					flag=true;
				}
				if( line.indexOf("JAVA_HOME=")<0) {
					catalinaList1.add(line);
				}					
			}
		}
		catalinaList1.writeToFile(catalinaS, false);
		
		catalinaList1.writeToFile(debugFolder+"\\catalina.old", false);
	}else {
		ans.add("need to be new tomcat");
		HagStringList catalinaList1=new HagStringList();
		//boolean flag = false;
		for (int i =0;i<catalinaList.size();i++) {
			String line = catalinaList.get(i);
			if(line==null || line.trim().length()==0 || line.indexOf("@echo off")>-1 || line.toLowerCase().startsWith("rem ") ||line.indexOf("setlocal")>-1) {
				catalinaList1.add(line);
			}else  {
				//if( !flag) {
				//	catalinaList1.add("SET \"JAVA_HOME=c:\\Program Files\\Java\\jdk1.7.0_80\"");
				//	flag=true;
				//}
				if( line.indexOf("JAVA_HOME=")<0) {
					catalinaList1.add(line);
				}					
			}
		}
		catalinaList1.writeToFile(catalinaS, false);
		
		catalinaList1.writeToFile(debugFolder+"\\catalina.new", false);
		//SET "JAVA_HOME=c:\Program Files\Java\jdk1.8.0_121"
	}
	return "temp";
}
	//A
	public static final String 			appendStringtoPropFile(String file,String strS,String strI,HagStringList strN){
		HagStringList list = new HagStringList(file,false,"zzaaz",true);
		HagStringList list1 = new HagStringList();
		for(int i = 0 ; i < list.size();i++){
			String temp = list.get(i);
			if(temp.startsWith(strS) && temp.indexOf(strI)>0){
				list1.add(temp);
				for(int k=0;k<strN.size();k++){
					String tempN = strN.get(k);
					list1.add(tempN);
				}
			}else
				list1.add(temp);
		}
		String str = list1.convertToString("\n");
		String ans = HagUtil.writeStringToFile(file, str);
		if(ans.startsWith("0~"))
			return "0~appendStringtoPropFile("+file+")";
		else
			return "1~appendStringtoPropFile("+file+")";
	}
	public static final String 			appendStringto(String file,String str){
		HagStringList list = new HagStringList(file,false,"zzaaz",true);
		list.add(str);
		String strS = list.convertToString("\n");
		String ans = HagUtil.writeStringToFile(file, strS);
		if(ans.startsWith("0~"))
			return "0~appendStringFile("+file+")";
		else
			return "1~appendStringtoFile("+file+")";
	}
	public static final boolean isInArray(String str,ArrayList<String> arr,boolean fullLine,boolean caseMatch){
		for(int i = 0; i< arr.size();i++){
			String temp=arr.get(i).trim();
			if(fullLine){
				if(caseMatch){
					if(temp.equals(str))
						return true;
				}else{ 
					if(temp.equalsIgnoreCase(str))
						return true;
				}
			}else{
				String temp1 = temp.toLowerCase();
				String str1 = str.toLowerCase();
				if(caseMatch){
					if(temp.indexOf(str) > -1)
						return true;
				}else{ 
					if(temp1.indexOf(str1)>-1)
						return true;
				}
			}
		}
		return false;
	}
	public static final HagStringList getAppServersList(String plat,boolean inclueExt,boolean allPlat){
	
		String stm1 = "select distinct bis_server FROM [prod_cfg].[dbo].ri_environments_new  where project<> 'HAGWIDTH' order by  bis_server";
		String stm2 = "select distinct bis_server FROM [prod_cfg].[dbo].ri_environments_EXT  where project<> 'HAGWIDTH' order by  bis_server";
		HagSQL hagSQL=new HagSQL();			
		HagStringList appServersList1 = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm1);
		HagStringList appServersList2 = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm2);
		HagStringList appServersList3 = new HagStringList();
		appServersList3.add("CLIO");
		appServersList3.add("FALCON");
		appServersList3.add("PASSAT");
		appServersList3.add("SCENIC");
		appServersList3.add("TERANO");
		if(allPlat) {
			appServersList1.append(appServersList2);
			appServersList1.append(appServersList3);
			return appServersList1;
		}
		if(plat.equalsIgnoreCase("I5OS"))
			return appServersList3;
		if(plat.equalsIgnoreCase("WIN")) {
			if(inclueExt) {
				appServersList1.append(appServersList2);
				return appServersList1;
			}else {
				return appServersList1;
			}
		}
		if(inclueExt) {
			appServersList1.append(appServersList2);
			appServersList1.append(appServersList3);
			return appServersList1;
		}else {
			appServersList1.append(appServersList3);
			return appServersList1;
		}	
		
	
		
	}
	public static final HagStringList getSqlServersList(String plat,boolean inclueExt){
		
		String stm1 = "select distinct sql_server FROM [prod_cfg].[dbo].ri_environments_new  where project<> 'HAGWIDTH' order by  sql_server";
		String stm2 = "select distinct sql_server FROM [prod_cfg].[dbo].ri_environments_EXT  where project<> 'HAGWIDTH' order by  sql_server";
		HagSQL hagSQL=new HagSQL();			
		HagStringList appServersList1 = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm1);
		HagStringList appServersList2 = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm2);
		HagStringList appServersList3 = new HagStringList();
		appServersList3.add("CLIO");
		appServersList3.add("FALCON");
		appServersList3.add("PASSAT");
		appServersList3.add("SCENIC");
		appServersList3.add("TERANO");
		if(plat.equalsIgnoreCase("I5OS"))
			return appServersList3;
		if(plat.equalsIgnoreCase("WIN")) {
			if(inclueExt) {
				appServersList1.append(appServersList2);
				return appServersList1;
			}else {
				return appServersList1;
			}
		}
		if(inclueExt) {
			appServersList1.append(appServersList2);
			appServersList1.append(appServersList3);
			return appServersList1;
		}else {
			appServersList1.append(appServersList3);
			return appServersList1;
		}	
		
	
		
	}
	
	public static final String 			addUser(String user,String pass){/*
		//String ff = cfgMenuWebLoc+"\\temp.txt";
		String ff = cfgMenuWebLoc+"\\lists\\users.txt";
		HagStringList aaa = new HagStringList(ff,true,"*",false);
		aaa.add("??~"+user+"~"+pass+"~"+user+"~{}");

		String str = aaa.convertToString("\n");
		String ans = HagUtil.writeStringToFile(ff, str);
		if(ans.startsWith("0~"))
			return "0~addUser";
		else
			*/
			return "1~addUser";
	}
	public static final void 			appendArrayList2ArrayList(ArrayList<String> addto, ArrayList<String> toadd){
	    for(int i =0; i<toadd.size();i++)
	    	addto.add(""+toadd.get(i));
		    
	 //   return cur1;
	}
	public static final void debugPrint(String aa) {
		String dateTime = HagUtil.getDateTime("yyyy-MM-dd-HH:mm");
		System.out.println(dateTime+"     "+aa);
	}
	//gonen
	public static final String 			rplStr(String str) {
		str=HagUtil.replaceStr(str, "'"," ");
		str=HagUtil.replaceStr(str, "~"," ");
		str=HagUtil.replaceStr(str, ","," ");
		return str;
			
}
	public static final boolean 			addRequest(
			int ind,
			String doneBy,
			String note,
			String subject,
			String tgtEnv,
			String cust,
			String fileName,
			String Envs_To_Install,
			String Envs_To_Install_As400 ,
			String When_To_Install ,
			String tgtVer ,
			int reqType   ){
	
		doneBy=rplStr(doneBy);
		note=rplStr(note);
		subject=rplStr(subject);
		tgtEnv=rplStr(tgtEnv);
		cust=rplStr(cust);
		fileName=rplStr(fileName);
		Envs_To_Install=rplStr(Envs_To_Install);
		Envs_To_Install_As400=rplStr(Envs_To_Install_As400);
		When_To_Install=rplStr(When_To_Install);
		tgtVer=rplStr(tgtVer);
		
		 
		 //spr1007
		 String Envs_To_Install1 =".";
		 String Envs_To_Install_As4001 =".";
		 String When_To_Install1 =".";
		 String tgtVer1 =".";
		 if(Envs_To_Install!=null)		 	Envs_To_Install1 =Envs_To_Install;
		 if(Envs_To_Install_As400!=null)	Envs_To_Install_As4001 =Envs_To_Install_As400;
		 if(When_To_Install!=null)		 	When_To_Install1 =When_To_Install;
		 if(tgtVer!=null)		 	tgtVer1 =tgtVer;
		 
		 
		if(!doneBy.toLowerCase().endsWith("sapiens.com"))
			doneBy=doneBy+"@sapiens.com";
		String dateTime = HagUtil.getDateTime("yyyy-MM-dd-HH:mm");
		StringBuilder stm3= new StringBuilder("INSERT INTO "+HagParam.getConfg1DB()+".[dbo].[req_ind_log_new] values(")
				.append(ind).append(",'")                       //[ind]
				.append(dateTime).append("','")                 //[dateTime] request
				.append(doneBy).append("','")					//[doneBy] requester 
				.append(".").append("','")						//[status]
				.append(note).append("','")                     //[note]
				.append(".").append("','")						//[owner] handler 
				.append(".").append("','")						//[perPhone]
				.append(subject).append("','")			        //[subject]
				.append(".").append("','")						//[dateTimeLastUpd] 
				.append(cust).append("','")						//[customer]
				.append(tgtEnv).append("',")	           		//[tgtEnv]
				.append(reqType).append(",'")                        //[Req_Type]
				.append(fileName).append("','")                  //[Req_File] xxx
				.append(Envs_To_Install1).append("','")	         //[Envs_To_Install]
				.append(".").append("','")	                     //[ExtraDev] xxx
				.append(".").append("','")	                     //[Components_to_release] 
				.append(Envs_To_Install_As4001).append("','")	 //[Envs_To_Install_As400]
				.append(When_To_Install1).append("','")	         //[When_To_Install]
				.append(".").append("','")	                     //[Final_Pack]
				.append(".").append("','")	                     //[Pre_Req]
				.append(".").append("','")	                     //[Customer_code_Party_desc]
				.append(".").append("','")	                     //[User_Name_eMail]
				.append(".").append("','")	                     //[srcEnv]
				.append(tgtVer1).append("','")	                     //[tgtVer]
				.append(".").append("','")	                     //[FTP_package_Name]
				.append(".").append("','")	                     //[Pref_Server]
				.append(".").append("','")	                     //[Pref_DBID]
				.append(".").append("','")	                     //[Spare1] 
				.append(".").append("','")	                     //[Spare2]
				.append(".").append("','")	                     //[OurNote]
				.append(".").append("','")	                     //[Spare4]
				.append(".")                                     //[Spare5]
				.append("')");

		String url="jdbc:sqlserver://CONFG1;Database="+HagParam.getConfg1DB();
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		Connection con = null;
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, "cfg", "cfg09c");
			con.createStatement().execute(stm3.toString());
		 	con.commit();
		 	HagUtil.writeToRelDiary2("Request","WIN","Open",".","#"+ind,"OK",".",".",".",doneBy.substring(0,doneBy.indexOf("@")),".",".");
			return true;
		} catch (ClassNotFoundException e) {
			sendMail_hagPre(cfgTeamMail,mailList_gonHag+";"+doneBy,mailList_hag,"#REQUEST-ERROR "+ind+" error1:","<HTML>error="+e.getMessage()+"<br>subject="+subject+"<br>note="+note+"<br>sent by="+doneBy+"<br>sql="+stm3+"</html>");
			return false;
		} catch (SQLException e) {
			sendMail_hagPre(cfgTeamMail,mailList_gonHag+";"+doneBy,mailList_hag,"#REQUEST-ERROR "+ind+" error2a:","<HTML>error="+e.getMessage()+"<br>subject="+subject+"<br>note="+note+"<br>sent by="+doneBy+"<br>sql="+stm3+"</html>");
			return false;
		} catch (Exception e) {
			sendMail_hagPre(cfgTeamMail,mailList_gonHag+";"+doneBy,mailList_hag,"#REQUEST-ERROR "+ind+" error3:","<HTML>error="+e.getMessage()+"<br>subject="+subject+"<br>note="+note+"<br>sent by="+doneBy+"<br>sql="+stm3+"</html>");
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public static final boolean 			addRequestNew(String[][] varVal) {

		 

		String dateTime = HagUtil.getDateTime("yyyy-MM-dd-HH:mm");
		StringBuilder stm4= new StringBuilder("INSERT INTO "+HagParam.getConfg1DB()+".[dbo].[req_ind_log_new] values(")
				.append("[ind]"						).append(",'")                	//[ind]
				.append(dateTime					).append("','")               	//[dateTime] request
				.append("[doneBy]"					).append("','")					//[doneBy] requester 
				.append("[status]"					).append("','")					//[status]
				.append("[note]"					).append("','")                 //[note]
				.append("[owner]"					).append("','")					//[owner] handler 
				.append("[perPhone]"				).append("','")					//[perPhone]
				.append("[subject]"					).append("','")			      	//[subject]
				.append("[dateTimeLastUpd]"			).append("','")					//[dateTimeLastUpd] 
				.append("[customer]"				).append("','")					//[customer]
				.append("[tgtEnv]"					).append("',")	           		//[tgtEnv]
				.append("[Req_Type]"				).append(",'")                  //[Req_Type]
				.append("[Req_File]"				).append("','")                 //[Req_File] xxx
				.append("[Envs_To_Install]"			).append("','")	         		//[Envs_To_Install]
				.append("[ExtraDev]"				).append("','")	                //[ExtraDev] xxx
				.append("[Components_to_release]"	).append("','")	                //[Components_to_release] 
				.append("[Envs_To_Install_As400]"	).append("','")	 				//[Envs_To_Install_As400]
				.append("[When_To_Install]"			).append("','")	         		//[When_To_Install]
				.append("[Final_Pack]"				).append("','")	                //[Final_Pack]
				.append("[Pre_Req]"					).append("','")	                //[Pre_Req]
				.append("[Customer_code_Party_desc]").append("','")	                //[Customer_code_Party_desc]
				.append("[User_Name_eMail]"			).append("','")	                //[User_Name_eMail]
				.append("[srcEnv]"					).append("','")	                //[srcEnv]
				.append("[tgtVer]"					).append("','")	                //[tgtVer]
				.append("[FTP_package_Name]"		).append("','")	                //[FTP_package_Name]
				.append("[Pref_Server]"				).append("','")	                //[Pref_Server]
				.append("[Pref_DBID]"				).append("','")	                //[Pref_DBID]
				.append("[Spare1]"					).append("','")	                //[Spare1] 
				.append("[Spare2]"					).append("','")	                //[Spare2]
				.append("[OurNote]"					).append("','")	                //[OurNote]
				.append("[Spare4]"					).append("','")	                //[Spare4]
				.append("[Spare5]"					)                               //[Spare5]
				.append("')");
		String stm4s = stm4.toString();
		String doneBy = ".";
		String subject =  ".";
		String note =  ".";
		String ind = "0";
		for(int i=0;i<varVal.length;i++) {
			if(varVal[i][0].equals("[doneBy]")) {
				if(!varVal[i][1].toLowerCase().endsWith("sapiens.com"))
					varVal[i][1]=varVal[i][1]+"@sapiens.com";
				doneBy = varVal[i][1];
			}
			if(varVal[i][0].equals("[subject]")) subject = varVal[i][1];
			if(varVal[i][0].equals("[note]")) note = varVal[i][1];
			if(varVal[i][0].equals("[ind]")) ind = varVal[i][1];
			
			stm4s =HagUtil.replaceStr(stm4s, varVal[i][0], varVal[i][1]);
		}
		stm4s =HagUtil.replaceStr(stm4s,"[ind]","0");
		stm4s =HagUtil.replaceStr(stm4s,"[dateTime]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[doneBy]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[status]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[note]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[owner]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[perPhone]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[subject]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[dateTimeLastUpd]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[tgtEnv]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[customer]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[Req_Type]","999");
		stm4s =HagUtil.replaceStr(stm4s,"[Req_File]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[Envs_To_Install]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[ExtraDev]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[Components_to_release]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[Envs_To_Install_As400]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[When_To_Install]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[Final_Pack]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[Pre_Req]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[Customer_code_Party_desc]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[User_Name_eMail]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[srcEnv]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[tgtVer]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[FTP_package_Name]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[Pref_Server]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[Pref_DBID]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[Spare1]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[Spare2]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[OurNote]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[Spare4]",".");
		stm4s =HagUtil.replaceStr(stm4s,"[Spare5]",".");
		
		String url="jdbc:sqlserver://CONFG1;Database="+HagParam.getConfg1DB();
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		Connection con = null;
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, "cfg", "cfg09c");
			con.createStatement().execute(stm4s);
		 	con.commit();
		 	HagUtil.writeToRelDiary2("Request","WIN","Open",".","#"+ind,"OK",".",".",".",doneBy.substring(0,doneBy.indexOf("@")),".",".");
			return true;
		} catch (ClassNotFoundException e) {
			sendMail_hagPre(cfgTeamMail,mailList_gonHag+";"+doneBy,mailList_hag,"#REQUEST-ERROR "+ind+" error1:","<HTML>error="+e.getMessage()+"<br>subject="+subject+"<br>note="+note+"<br>sent by="+doneBy+"<br>sql="+stm4s+"</html>");
			return false;
		} catch (SQLException e) {
			sendMail_hagPre(cfgTeamMail,mailList_gonHag+";"+doneBy,mailList_hag,"#REQUEST-ERROR "+ind+" error2b:","<HTML>error="+e.getMessage()+"<br>subject="+subject+"<br>note="+note+"<br>sent by="+doneBy+"<br>sql="+stm4s+"</html>");
			return false;
		} catch (Exception e) {
			sendMail_hagPre(cfgTeamMail,mailList_gonHag+";"+doneBy,mailList_hag,"#REQUEST-ERROR "+ind+" error3:","<HTML>error="+e.getMessage()+"<br>subject="+subject+"<br>note="+note+"<br>sent by="+doneBy+"<br>sql="+stm4s+"</html>");
			return false;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public static String addNumDelim( String str,int width)   {
		StringBuilder str1 = new StringBuilder();
		for(int i = str.length()-1;i>0;i--){
			//if(i==width-3 || i==width-6 ||i==width-9 ||i==width-12 ||i==width-15 ||i==width-18)
			//	str1.insert(0,',');
			str1.insert(0,str.charAt(i));
		}
		return str1.toString();
	}
	//B
	//C
	public static final String     		checkTOPACK(String topackFolderS,String listS) {
	StringBuilder ans = new StringBuilder();
		HagStringList filesInList = new HagStringList(listS,true,"*",false);
		HagStringList filesInDir = HagUtil.getFilesInDir(topackFolderS);
		Collections.sort(filesInList); 
		Collections.sort(filesInDir); 

		ans.append("<font size=4 color=\"blue\"><u>list of file that cfgMenuWeb expected to find in "+topackFolderS+" folder:</u></font><br>");
		for(int i =0;i<filesInList.size();i++) {
			ans.append("<font size=3>"+filesInList.get(i)+"</font>");
			ans.append("<br>");
		}
		ans.append("<br><font size=4 color=\"blue\"><u>list of file that cfgMenuWeb found in "+topackFolderS+" folder:</u></font><br>");
		
		
		for(int i =0;i<filesInDir.size();i++) {
			ans.append("<font size=3>"+filesInDir.get(i)+"</font>");
			ans.append("<br>");
		}
		ans.append("<br><br><font size=6 color=\"red\">request canceled, please fix(Delete extra files or add missing files).</font>");
		
		if(filesInList.size()!=filesInDir.size())
			return ans.toString();
		
		for(int i =0;i<filesInDir.size();i++) {
			if(!filesInList.get(i).equals(filesInDir.get(i)))
				return ans.toString();
		}
		return null;
	}
	public static final String     		checkServiceStatus1(String ansStr){
		String STATE_PREFIX = "STATE              : ";
		int ix = ansStr.indexOf(STATE_PREFIX);
		if (ix < 0) {
			return "1~ service not found.";
		}else{
			String stateStr = ansStr.substring(ix+STATE_PREFIX.length(), ix+STATE_PREFIX.length() + 1);
			int state = Integer.parseInt(stateStr);
			switch(state) {
				case (1): // service stopped
					return "2~ stopped.";
				case (4): // service started
					return "0~ started.";
			}
			return "3~ unknown status.";
		}
	}
	public static final String   		checkEmergeListener(String bisServer,String dbName){
		String ans4 = HagUtil.runCmdRemote(bisServer,"strbisl -t\n","Y");
		int pos = ans4.indexOf("Entry \""+dbName.toUpperCase()+"\" ProcessId");
		if(pos<0)
			return "<td bgColor=\"#FF0000\">NOT FOUND</td>";
		else{
			int pos1 = ans4.indexOf(".",pos+1);
			if(pos1<0)
				pos1=ans4.length();
			String val = ans4.substring(pos+20,pos1);
			val=val.trim();
			if (val.equals(""))
				return "<td bgColor=\"#FF0000\">NOT FOUND</td>";
			else if(val.equals("0"))	
				return "<td bgColor=\"#FF6600\">DOWN</td>";
			else 	
				return "<td bgColor=\"#00FF00\">UP("+val+")</td>";

		}

	}
	public static final String   		checkTomcatStatus(String bisServer,String batchName){
		//service
		String ans4 = HagUtil.runCmdRemote(bisServer,"sc query RILS-"+batchName+"\n","Y");
		if(ans4 == null || ans4.trim().equals("")){
			return "<td bgColor=\"#FF0000\">PROBLEM</td>";
		}
		if(ans4.indexOf("0~")<0){
			return "<td bgColor=\"#FF0000\">PROBLEM</td>";
		}

		String ans2 = checkServiceStatus1(ans4);
		if (ans2.startsWith("2~")){
			return "<td bgColor=\"#FF6600\">DOWN</td>";
		}else if (ans2.startsWith("1~")){
			return "<td bgColor=\"#FF0000\">NOT FOUND</td>";
		}else if (ans2.startsWith("3~")){
			return "<td bgColor=\"#FF0000\">UNKNOWN</td>";
		}
		return "<td bgColor=\"#00FF00\">UP(RILS-"+batchName+")</td>";

	}
	
	

	public static final String   		checkTomcatProcess(String bisServer,String batchName){
		String riJava = "d:\\ri_java\\RIjava_"+batchName; 
		HagStringList list = HagUtil.getWmicByName(bisServer, "java",batchName);
		
		for (int i = 0; i < list.size(); i++) {
			String temp2 = list.get(i).toUpperCase();
			String find = (riJava+"\\tomcat\\").toUpperCase();
			int pos = temp2.lastIndexOf(find);
			int pos1 = temp2.lastIndexOf("JAVA");
			if (pos > -1) {
				String zz = (temp2.substring(pos1+9, temp2.length())).trim();
				return "<td bgColor=\"#00FF00\">RUNNING ("+zz+")</td>";
			}
		}
		return "<td bgColor=\"#FF6600\">NOT FOUND</td>";
	}
	public static final String 			changeRirelProperties(String file,String[][] varsVals){
		HagStringList list = new HagStringList(file,false,"zzaaz",true);
		HagStringList list1 = new HagStringList();
		for(int i = 0 ; i < list.size();i++){
			String temp = list.get(i);
			boolean found = false;
			for(int k = 0 ; k < varsVals.length;k++){
				if(temp.startsWith(varsVals[k][0]+"=")){
					list1.add(HagUtil.left(varsVals[k][0]+"=", " ", 38, ".")+varsVals[k][1]);
					found =true;
				}
			}
			if(!found)
				list1.add(temp);
		}
		String str = list1.convertToString("\n");
		String ans = HagUtil.writeStringToFile(file, str);
		if(ans.startsWith("0~"))
			return "0~setProperties("+file+")";
		else
			return "1~setProperties("+file+")";
	}
	public static final String 			changeStringInPropFile(String file,String var,String oldStr,String newStr) {
		HagStringList list = new HagStringList(file,false,"zzaaz",true);
		HagStringList list1 = new HagStringList();
		for(int i = 0 ; i < list.size();i++){
			String temp = list.get(i);
			if(temp.startsWith(var+"=")){
				String temp1 = replaceStr(temp, oldStr, newStr);
				list1.add(temp1);
			}else
				list1.add(temp);
		}
		String str = list1.convertToString("\n");
		String ans = HagUtil.writeStringToFile(file, str);
		if(ans.startsWith("0~"))
			return "0~changeStringInPropFile("+file+")";
		else
			return "1~changeStringInPropFile("+file+")";
	}
	public static final boolean 		checkVerToInst(String curVer,String newVer){
		String curVer1=curVer.substring(0,curVer.indexOf("-"));
		String newVer1 = newVer;
		if(newVer.indexOf("(")>-1)
			newVer1 = newVer.substring(0,newVer.indexOf("("));

		String curVer2=HagUtil.replaceStr(curVer1,".","").trim();
		String newVer2=HagUtil.replaceStr(newVer1,".","").trim(); 

		long curVer3 = HagUtil.s2l(curVer2);
		long newVer3 = HagUtil.s2l(newVer2);
		if(newVer3 < curVer3 )
			return false;

		return true;

	}
	public static final String 			createHtmlAccumulativeAddLogs(String value) {
		if(value.equals("NONE") || value.equals("INIT"))
			return "<html><body></body></html>";

		StringBuilder ans = new StringBuilder()
		.append("<br>").append(HagUtil.loadFileIntoString(cfgMenuWebLoc+"\\AccumulativeAddLogs"+value+".html"));
		return ans.toString();


	}
	public static final String 			createHtmlAccumulativeRiFtpLogs() {
		//	if(value.equals("NONE") || value.equals("INIT"))
		//		return "<html><body></body></html>";

		StringBuilder ans = new StringBuilder()
		.append("<br>").append(HagUtil.loadFileIntoString(cfgMenuWebLoc+"\\RiFtpPackLogs.html"));
		return ans.toString();


	}
	public static final String 			createFolder(String dirStr){
		File folder = new File(dirStr);
		if(!folder.exists()){
			if(!folder.mkdir())
				return "1~Failed to create"+dirStr;
			else
				return "0~"+dirStr+" created.";
		}else
			return "0~"+dirStr+" exists.";
	}
	public static final String 			changePassword(String user,String pass){
		HagStringList list = new HagStringList();
		list.add("<HTML><body bgcolor=\"#ccccbb\">");
		list.add("<font size=4 color=\"blue\"><u>Change password:</u></font><br><br>");
		list.add("<FORM METHOD=POST name=\"Form\" ACTION=\"Register2.jsp\">");
		list.add("<input type=\"hidden\" name=\"oldPass\" id=\"oldPass\" value = \""+pass+"\" maxlength=\"140\" size=\"140\">");
		
		list.add("<table bgcolor=\"#aaaacc\" border=\"1\"><tr>");
		list.add("<td>User</td>");
		//
		list.add("<td><fontcolor=\"yellow\"><select name=\"user\"></font>");
		list.add("<option value=\""+user+"\">"+user+"</option>");
		list.add("</select></font></td>");
		list.add("<td><font color=\"yellow\">.</font></td>");
		//

		list.add("</tr><tr>");
		list.add("<td>New Password</td>");
		list.add("<td><input type=\"password\" name=\"passwordNew\" size=\"10\"> </td>");
		list.add("<td><font color=\"yellow\">.</font></td>");
		list.add("</tr><tr>");
		list.add("<td>Password confirmation</td>");
		list.add("<td><input type=\"password\" name=\"passwordNew2\" size=\"10\"> </td>");
		list.add("<td><font color=\"yellow\">Enter the same password</font></td>");
		list.add("</tr></table>");
		list.add("<br><INPUT TYPE=SUBMIT value=\"Submit\"></FORM></BODY></HTML>");
		String str=list.convertToString("\n");
		return str;
	}	
	public static final String 			convertArrayListOfStringListToString(ArrayList<HagStringList> arr,String delimOut,String delimIn) {
		StringBuilder ans = new StringBuilder();
		for(int i = 0 ; i <arr.size();i++){
			String temp = arr.get(i).convertToString(delimIn);
			if(i==0)
				ans.append(temp);
			else
				ans.append(delimOut).append(temp);
		}
		return ans.toString();
	}
	public static final String 			changePassNew(String user,String pass){
		HagSQL hagSql = new HagSQL();
	
		String stm = "UPDATE dbo.hagCfgMenuWebUsers set pass1='"+pass+"' WHERE name1='"+ user+"'";
		String ans11 = hagSql.update("SQL", "confg1", "cfg", "cfg09c", HagParam.getConfg1DB(), stm);
	
		if (!ans11.startsWith("0~"))
				return "1~Failed to change password,please call hagay";
		return "0~password";
	}
	public static final String 			changePass(String user,String pass){
		if(1==1)
			return changePassNew( user, pass);
		return changePassNew( user, pass);
		/*
	//	String ff = cfgMenuWebLoc+"\\temp.txt";
		String ff = cfgMenuWebLoc+"\\lists\\users.txt";
		HagStringList aaa = new HagStringList(ff,true,"*",false);
		for(int i = 1 ; i < aaa.size();i++){
			String temp = aaa.get(i);
			String w0 = HagUtil.getWord0(temp, "~",0,true);
			String w1 = HagUtil.getWord0(temp, "~",1,true);
			String w2 = HagUtil.getWord0(temp, "~",2,true);
			String w3 = HagUtil.getWord0(temp, "~",3,true);
			String w4 = HagUtil.getWord0(temp, "~",4,true);
			if(w1.endsWith(user)){
				aaa.set(i,w0+"~"+w1+"~"+pass+"~"+w3+"~"+w4);
				break;
			}
		}
		String str = aaa.convertToString("\n");
		String ans = HagUtil.writeStringToFile(ff, str);
		if(ans.startsWith("0~"))
			return "0~changePass";
		else
			return "1~changePass";
			*/
	}
	public static final String 			convertServersNameDsp(String str){
		str = str.replaceAll("RI3QA","RI<FONT COLOR=#DD1111>0</FONT>3QA");
		str = str.replaceAll("RI4QA","RI<FONT COLOR=#DD1111>0</FONT>4QA");
		str = str.replaceAll("RI6QA","RI<FONT COLOR=#DD1111>0</FONT>6QA");
		str = str.replaceAll("RI7QA","RI<FONT COLOR=#DD1111>0</FONT>7QA");
		str = str.replaceAll("RI8QA","RI<FONT COLOR=#DD1111>0</FONT>8QA");
		str = str.replaceAll("RI9QA","RI<FONT COLOR=#DD1111>0</FONT>9QA");
		return str;
	}
	
	
	public static final void 			createHotfixCmInstaller(HagRc hagRc,String packagePrep,
													String ver,String maint,String update,String hotfix,
													HagStringList components,String customer){
		File ff = new File(packagePrep);
		//rename existing package
		if(ff.exists()){
			String dateTime = HagUtil.getDateTime("yyyyMMddHHssSSS");
			String newName = packagePrep.substring(0,packagePrep.lastIndexOf("\\")+1)+"bk"+packagePrep.substring(packagePrep.lastIndexOf("\\")+1,packagePrep.length())+"_"+dateTime;
			String ren = renameFile(packagePrep,newName);
			hagRc.log.add(ren);
			if(!ren.startsWith("0~")){
				hagRc.rc=1;
				return ;
			}
		}
		//String fromFolder = "\\\\ri-archive\\Saptech-Archive\\RI\\cmInstaller_pack\\cmInstaller_VVVVmMMuUUhfHH";
		//if (!HagUtil.copyFolder(fromFolder, packagePrep + "\\", false, true, "1").startsWith("0~")){
		//	hagRc.log.add("Failed to copy cmInstaller_VVVVmMMuUUhfHH skeleton");
		//	hagRc.rc=1;
		//	return ;
		//}
		
		//create package
		String ans = HagUtil.createFolder(packagePrep);
		hagRc.log.add(ans);
		if(!ans.startsWith("0~")){
			hagRc.rc=1;
			return ;
		}
		ans = HagUtil.createFolder(packagePrep+"\\struct1");
		hagRc.log.add(ans);
		if(!ans.startsWith("0~")){
			hagRc.rc=1;
			return ;
		}
		ans = HagUtil.createFolder(packagePrep+"\\struct2");
		hagRc.log.add(ans);
		if(!ans.startsWith("0~")){
			hagRc.rc=1;
			return ;
		}
		HagStringList dummyF = new HagStringList();
		dummyF.add("dummy");
		dummyF.writeToFile(packagePrep+"\\struct2\\dummyFile.txt",null,false);
		
		ans = HagUtil.createFolder(packagePrep+"\\txt");
		hagRc.log.add(ans);
		if(!ans.startsWith("0~")){
			hagRc.rc=1;
			return ;
		}

		//hotfix_toDo.txt
		int count =0;
		HagStringList toDoList = new HagStringList();
		toDoList.add("Check validity                           | CheckValidity");  
		toDoList.add("Update properties file 2                 | Update2PropertiesFile");  
		toDoList.add("Stop tomcat service                      | StopTomcat");			  
		toDoList.add("Stop eMerge listener(and sapop)          | StopListener");		
		//HagUtil.p(ver);
		if(ver.equals("0606"))
			toDoList.add("Replace eMerge Modules                   | ReplaceEmergeModules");		
		else
			toDoList.add("Replace eMerge Modules                   | ReplaceEmergeModules4530M00");		
		boolean isManualMigFound=false;
		StringBuilder migD =new StringBuilder("select * from RI.MIG_DETAILS where JCLASS IN(");
		
		for(int i =0;i<components.size();i++){
			String temp = components.get(i);
			String w0 = HagUtil.getWord0(temp,",",0,true);
		//	HagUtil.p(temp);
		//	HagUtil.p(w0);
			if(w0.equals("Run_manual_mig") || w0.equals("Set_MIG_DETAILS_table")){
				if(!isManualMigFound){
					toDoList.add("hotFixMigDetails_before                  | hotFixMigDetails_before");
					isManualMigFound=true;
				}
				count++;
				String date2 = HagUtil.getDateTime("yyyy-MM-dd");
				String w1 = HagUtil.getWord0(temp,",",1,true);
				String w2 = HagUtil.getWord0(temp,",",2,true);
				String w3 = HagUtil.getWord0(temp,",",3,true);
				HagStringList tempList = new HagStringList();
				tempList.add("DELETE RI.MIG_DETAILS ");
				tempList.add("WHERE ID <>'"+w1+"' AND JCLASS = 'com.sapiens.mig.ri.v050m00."+w2+"'");
				tempList.add("IF EXISTS (SELECT 1 from MIG_DETAILS where ID='"+w1+"')");
				tempList.add("begin");
				tempList.add("UPDATE RI.MIG_DETAILS");
				tempList.add("SET MIG_TYPE = "+w3);
				tempList.add("WHERE ID ='"+w1+"'");
				tempList.add("end");
				tempList.add("INSERT INTO MIG_DETAILS");                                   
				tempList.add("(ID,RUN_ORDER,JCLASS,MIG_TYPE,STATUS,UPDATED_DATE)");        
				tempList.add("VALUES");                                                    
				tempList.add("("+w1+","+w1+",'com.sapiens.mig.ri.v050m00."+w2+"',"+w3+",10,'"+date2+"')");
				tempList.writeToFile(packagePrep+"\\struct2\\"+count+"_"+w1+"_"+w2+"_"+w3+".sql",null,false);
				toDoList.add("hotfix Update MigDetails                 | hotfixUpdateMigDetails~"+count+"~"+count+"_"+w1+"_"+w2+"_"+w3+".sql");
				if(w0.equals("Run_manual_mig"))
						toDoList.add("hotfix Run Manual Mig                    | hotfixRunManualMig~"+count+"~"+w2);
				migD.append("'com.sapiens.mig.ri.v050m00.").append(w2).append("',");
			}else if(w0.equals("Copy_migmng_jar")){
				toDoList.add("Copy RiMig files                         | CopyRiMigfiles");
			}else if(w0.equals("Replace jar")){
				String w1 = HagUtil.getWord0(temp,",",1,true);
				toDoList.add("hotfix Replace Jar                       | hotfixReplaceJar~1~"+w1);
			}else if(w0.equals("Replace war")){
				toDoList.add("Replace war file                         | ReplaceWar");
			}else if(w0.equals("Replace jasper")){
				toDoList.add("Jasper update                            | JasperUpdate");
			}else if(w0.equals("Replace ddc")){
				toDoList.add("hotfix Replace DDC table                 | hotfixReplaceDDC~1~YYY");
			}else if(w0.equals("Replace IOM")){
				toDoList.add("hotfix Replace IOM                       | CopyIOM");
			//}else if(w0.equals("Copy migMng.jar")){
			//	toDoList.add("Copy RiMig files                         | CopyRiMigfiles");
			}else if(w0.equals("ReplaceAdd Scripts")){
				toDoList.add("hotfix ReplaceAdd Scripts                | hotfixReplaceAddScripts~1");
			}
		}
		
		if(isManualMigFound){
			String migDstr=migD.toString();
			migDstr=migDstr.substring(0,migDstr.length()-1)+")";
			HagStringList temp122 = new HagStringList();
			temp122.add(migDstr);
			temp122.writeToFile(packagePrep+"\\struct2\\hotFixMigDetails.sql",null,false);
			toDoList.add("hotFixMigDetails_after                   | hotFixMigDetails_after");
		}
		if(ver.equals("0607"))
			toDoList.add("Set LangEncoding                         | SetLangEncoding");  
		
		toDoList.add("hotfix Update MigDetails                 | hotfixUpdateMigDetails~72~72_900072_Man500004831_80.sql");
		toDoList.add("hotfix Run Manual Mig                    | hotfixRunManualMig~72~Man500004831");
		toDoList.add("hotfix Update MigDetails                 | hotfixUpdateMigDetails~73~73_900073_Man10506_1_80.sql");
		toDoList.add("hotfix Run Manual Mig                    | hotfixRunManualMig~73~Man10506_1");
		toDoList.add("hotfix Update MigDetails                 | hotfixUpdateMigDetails~74~74_900074_Man10859_80.sql");
		toDoList.add("hotfix Run Manual Mig                    | hotfixRunManualMig~74~Man10859");
		toDoList.add("ClearDB						           | clearDb");  
		toDoList.add("Update environment status                | UpdateEnvironmentStatus");  
		toDoList.add("Start eMerge listener                    | StartListener");  
		toDoList.add("Start tomcat service                     | StartTomcat");  
		toDoList.add("Save display area                        | SaveDisplayArea");  
		toDoList.writeToFile(packagePrep+"\\struct1\\hotfix_toDo.txt",null,false);
		//title.txt
		String CDdate = HagUtil.getDateTime("yyyy/MM/dd");
		String CDtime = HagUtil.getDateTime("HH:mm:ss");
		HagStringList title = new HagStringList();
		title.add("b       Installs the Reinsurance Logic Server software(use for Logic Server on Windows platform only)");
		title.add("x       The installation cannot run from the release CD - The files must be copied to a writable location");
		title.add("r       RI - V"+ver.charAt(1)+"R"+ver.substring(2,4)+"M"+maint+"-update"+update+"-hotfix"+hotfix);	
		title.writeToFile(packagePrep+"\\struct1\\title.txt",null,false);
	//	String ans2 = simpleDosCmd("javaFunc~simpleDosCmd~copy~"+packagePrep+"\\cfg\\hotfix_toDo.txt~"+packagePrep+"\\bin\\struct1\\HagGridPanelRiInstaller\\hotfix_toDo.txt",false);
	//	if(!ans2.startsWith("0~")){
	//		hagRc.rc=1;
	//		hagRc.log.add(ans2);
	//	}
		//config.txt
		
		String bisV = "4530";
		String bisM = "00";
		if(ver.equals("0606")){
			bisV = "4520";
			bisM = "05";
		}
		String verS = ver.substring(0,2)+"."+ver.substring(2,4)+"."+maint+"."+update+"."+hotfix;
		HagStringList config = new HagStringList();
		config.add("CDversion="+verS);
		config.add("CDdate="+CDdate);
		config.add("CDtime="+CDtime);
		config.add("REQversion="+verS);
		config.add("REQdate="+CDdate);
		config.add("REQtime="+CDtime);
		config.add("jasperUpdate=Y");
		config.add("hotfix="+hotfix);
		config.add("bisVersion="+bisV);
		config.add("bisMaint="+bisM);
		config.add("hagLog="+ver+"M"+maint);
		config.add("hagJiraVer="+ver+"M"+maint+"u"+update+"hf"+hotfix+"(FTP)");
		config.add("customer="+customer);
		config.writeToFile(packagePrep+"\\txt\\config.txt",null,false);
		
		return ;
	}
	public static final int getService(String server){
		String serverU1 = server.toUpperCase();
		String serverU = serverU1;
		int pos = serverU1.indexOf(".");
		if(pos>0)
			serverU=serverU1.substring(0,pos);
		
		String loc = cfgMenuWebLoc+"\\lists\\cfg.list";
		String newServiceStr=HagUtil.getPropertyVal(loc, "newService");
		String newServiceStrU= newServiceStr.toUpperCase();
		HagStringList list = new HagStringList(newServiceStrU,"~",true);
		for(int i=0;i<list.size();i++) {
			String temp=list.get(i);
			if(temp.equals(serverU))
				return 2001;
		}
		return 2000; 
	}
	public static final void createHotfixCmInstallerSpr1003(HagRc hagRc, String packagePrep, String ver, String maint,
			String update, String hotfix, HagStringList components, String customer) {
		File ff = new File(packagePrep);
		// rename existing package
		if (ff.exists()) {
			String dateTime = HagUtil.getDateTime("yyyyMMddHHssSSS");
			String newName = packagePrep.substring(0, packagePrep.lastIndexOf("\\") + 1) + "bk"
					+ packagePrep.substring(packagePrep.lastIndexOf("\\") + 1, packagePrep.length()) + "_" + dateTime;
			String ren = renameFile(packagePrep, newName);
			hagRc.log.add(ren);
			if (!ren.startsWith("0~")) {
				hagRc.rc = 1;
				return;
			}
		}
		// String fromFolder =
		// "\\\\ri-archive\\Saptech-Archive\\RI\\cmInstaller_pack\\cmInstaller_VVVVmMMuUUhfHH";
		// if (!HagUtil.copyFolder(fromFolder, packagePrep + "\\", false, true,
		// "1").startsWith("0~")){
		// hagRc.log.add("Failed to copy cmInstaller_VVVVmMMuUUhfHH skeleton");
		// hagRc.rc=1;
		// return ;
		// }

		// create package
		HagUtil.reCreateFolder(hagRc,packagePrep);
		//String ans = HagUtil.createFolder(packagePrep);
	//	hagR.log.add(ans);
		if (hagRc.rc!=0) {
			
			return;
		}
		String ans = HagUtil.createFolder(packagePrep + "\\struct1");
		hagRc.log.add(ans);
		if (!ans.startsWith("0~")) {
			hagRc.rc = 1;
			return;
		}
		ans = HagUtil.createFolder(packagePrep + "\\struct2");
		hagRc.log.add(ans);
		if (!ans.startsWith("0~")) {
			hagRc.rc = 1;
			return;
		}
		HagStringList dummyF = new HagStringList();
		dummyF.add("dummy");
		dummyF.writeToFile(packagePrep + "\\struct2\\dummyFile.txt", null, false);

		ans = HagUtil.createFolder(packagePrep + "\\txt");
		hagRc.log.add(ans);
		if (!ans.startsWith("0~")) {
			hagRc.rc = 1;
			return;
		}

		// hotfix_toDo.txt
		int count = 0;
		HagStringList toDoList = new HagStringList();
		toDoList.add("Check validity                           | CheckValidity");
		toDoList.add("Update properties file 2                 | Update2PropertiesFile");
		toDoList.add("Stop tomcat service                      | StopTomcat");
		toDoList.add("Stop eMerge listener(and sapop)          | StopListener");
		// HagUtil.p(ver);
		if (ver.equals("0606"))
			toDoList.add("Replace eMerge Modules                   | ReplaceEmergeModules");
		else
			toDoList.add("Replace eMerge Modules                   | ReplaceEmergeModules4530M00");
		boolean isManualMigFound = false;
		StringBuilder migD = new StringBuilder("select * from RI.MIG_DETAILS where JCLASS IN(");

		for (int i = 0; i < components.size(); i++) {
			String temp = components.get(i);
			String w0 = HagUtil.getWord0(temp, "~", 0, true);
			// HagUtil.p(temp);
			// HagUtil.p(w0);
			if (w0.equals("manualMigs") ) {
				if (!isManualMigFound) {
					toDoList.add("hotFixMigDetails_before                  | hotFixMigDetails_before");
					isManualMigFound = true;
				}
				count++;
				String date2 = HagUtil.getDateTime("yyyy-MM-dd");
				String ww = HagUtil.getWord0(temp, "~", 1, true);
				String id = HagUtil.getWord0(ww, "#", 0, true);
				String jClass = HagUtil.getWord0(ww, "#", 1, true);
				String type = HagUtil.getWord0(ww, "#", 2, true);
				String runOrUpdate = HagUtil.getWord0(ww, "#", 3, true);
				
				HagStringList tempList = new HagStringList();
				tempList.add("DELETE RI.MIG_DETAILS ");
				tempList.add("WHERE ID <>'" + id + "' AND JCLASS = 'com.sapiens.mig.ri.v050m00." + jClass + "'");
				tempList.add("IF EXISTS (SELECT 1 from MIG_DETAILS where ID='" + id + "')");
				tempList.add("begin");
				tempList.add("UPDATE RI.MIG_DETAILS");
				tempList.add("SET MIG_TYPE = " + type);
				tempList.add("WHERE ID ='" + id + "'");
				tempList.add("end");
				tempList.add("INSERT INTO MIG_DETAILS");
				tempList.add("(ID,RUN_ORDER,JCLASS,MIG_TYPE,STATUS,UPDATED_DATE)");
				tempList.add("VALUES");
				tempList.add("(" + id + "," + id + ",'com.sapiens.mig.ri.v050m00." + jClass + "'," + type + ",10,'" + date2
						+ "')");
				tempList.writeToFile(packagePrep + "\\struct2\\" + count + "_" + id + "_" + jClass + "_" + type + ".sql",
						null, false);
				toDoList.add("hotfix Update MigDetails                 | hotfixUpdateMigDetails~" + count + "~" + count	+ "_" + id + "_" + jClass + "_" + type + ".sql");
				
				if (runOrUpdate.equals("UR")) {
					//toDoList.add("hotfix Run Manual Mig                    | hotfixRunManualMig~" + count + "~" + jClass);
				
					toDoList.add("hotfix Run Manual Mig                    | hotfixRunManualMig~"+count+"~"+jClass);
				
			
				}
				migD.append("'com.sapiens.mig.ri.v050m00.").append(jClass).append("',");
			} else if (w0.equals("Copy_migmng_jar")) {
				toDoList.add("Copy RiMig files                         | CopyRiMigfiles");
			} else if (w0.equals("replaceJar")) {
				String w1 = HagUtil.getWord0(temp, "~", 1, true);
				toDoList.add("hotfix Replace Jar                       | hotfixReplaceJar~1~" + w1);
			
			} else if (w0.equals("replaceWarAndJasper")) {
				toDoList.add("Replace war file                         | ReplaceWar");
				toDoList.add("Jasper update                            | JasperUpdate");
			} else if (w0.equals("Replace jasper")) {
				toDoList.add("Jasper update                            | JasperUpdate");
			} else if (w0.equals("replaceDdc")) {
				toDoList.add("hotfix Replace DDC table                 | hotfixReplaceDDC~1~YYY");
			} else if (w0.equals("replaceIom")) {
				toDoList.add("hotfix Replace IOM                       | CopyIOM");
				// }else if(w0.equals("Copy migMng.jar")){
				// toDoList.add("Copy RiMig files | CopyRiMigfiles");
			} else if (w0.equals("replaceScripts")) {
				toDoList.add("hotfix ReplaceAdd Scripts                | hotfixReplaceAddScripts~1");
			}
		}

		if (isManualMigFound) {
			String migDstr = migD.toString();
			migDstr = migDstr.substring(0, migDstr.length() - 1) + ")";
			HagStringList temp122 = new HagStringList();
			temp122.add(migDstr);
			temp122.writeToFile(packagePrep + "\\struct2\\hotFixMigDetails.sql", null, false);
			
			toDoList.add("Copy RiMig files                         | CopyRiMigfiles");
			toDoList.add("hotFixMigDetails_after                   | hotFixMigDetails_after");
		}
		if (ver.equals("0607"))
			toDoList.add("Set LangEncoding                         | SetLangEncoding");

		toDoList.add(
				"hotfix Update MigDetails                 | hotfixUpdateMigDetails~72~72_900072_Man500004831_80.sql");
		toDoList.add("hotfix Run Manual Mig                    | hotfixRunManualMig~72~Man500004831");
		toDoList.add(
				"hotfix Update MigDetails                 | hotfixUpdateMigDetails~73~73_900073_Man10506_1_80.sql");
		toDoList.add("hotfix Run Manual Mig                    | hotfixRunManualMig~73~Man10506_1");
		toDoList.add("hotfix Update MigDetails                 | hotfixUpdateMigDetails~74~74_900074_Man10859_80.sql");
		toDoList.add("hotfix Run Manual Mig                    | hotfixRunManualMig~74~Man10859");
		toDoList.add("ClearDB						           | clearDb");
		toDoList.add("Update environment status                | UpdateEnvironmentStatus");
		toDoList.add("Start eMerge listener                    | StartListener");
		toDoList.add("Start tomcat service                     | StartTomcat");
		toDoList.add("Save display area                        | SaveDisplayArea");
		toDoList.writeToFile(packagePrep + "\\struct1\\hotfix_toDo.txt", null, false);
		// title.txt
		String CDdate = HagUtil.getDateTime("yyyy/MM/dd");
		String CDtime = HagUtil.getDateTime("HH:mm:ss");
		HagStringList title = new HagStringList();
		title.add(
				"b       Installs the Reinsurance Logic Server software(use for Logic Server on Windows platform only)");
		title.add(
				"x       The installation cannot run from the release CD - The files must be copied to a writable location");
		title.add("r       RI - V" + ver.charAt(1) + "R" + ver.substring(2, 4) + "M" + maint + "-update" + update
				+ "-hotfix" + hotfix);
		title.writeToFile(packagePrep + "\\struct1\\title.txt", null, false);
		// String ans2 =
		// simpleDosCmd("javaFunc~simpleDosCmd~copy~"+packagePrep+"\\cfg\\hotfix_toDo.txt~"+packagePrep+"\\bin\\struct1\\HagGridPanelRiInstaller\\hotfix_toDo.txt",false);
		// if(!ans2.startsWith("0~")){
		// hagRc.rc=1;
		// hagRc.log.add(ans2);
		// }
		// config.txt

		String bisV = "4530";
		String bisM = "00";
		if (ver.equals("0606")) {
			bisV = "4520";
			bisM = "05";
		}
		String verS = ver.substring(0, 2) + "." + ver.substring(2, 4) + "." + maint + "." + update + "." + hotfix;
		HagStringList config = new HagStringList();
		config.add("CDversion=" + verS);
		config.add("CDdate=" + CDdate);
		config.add("CDtime=" + CDtime);
		config.add("REQversion=" + verS);
		config.add("REQdate=" + CDdate);
		config.add("REQtime=" + CDtime);
		config.add("jasperUpdate=Y");
		config.add("hotfix=" + hotfix);
		config.add("bisVersion=" + bisV);
		config.add("bisMaint=" + bisM);
		config.add("hagLog=" + ver + "M" + maint);
		config.add("hagJiraVer=" + ver + "M" + maint + "u" + update + "hf" + hotfix + "(FTP)");
		config.add("customer=" + customer);
		config.writeToFile(packagePrep + "\\txt\\config.txt", null, false);

		return;
	}


	public static final void 			createHotfixAria(HagRc hagRc,String packagePrep,
			String ver,String maint,String update,String hotfix,
			HagStringList components,String customer){
		File ff = new File(packagePrep);
		//rename existing package
		if(ff.exists()){
			String dateTime = HagUtil.getDateTime("yyyyMMddHHssSSS");
			String newName = packagePrep.substring(0,packagePrep.lastIndexOf("\\")+1)+"bk"+packagePrep.substring(packagePrep.lastIndexOf("\\")+1,packagePrep.length())+"_"+dateTime;
			String ren = renameFile(packagePrep,newName);
			hagRc.log.add(ren);
			if(!ren.startsWith("0~")){
				hagRc.rc=1;
				return ;
			}
		}
		//create package
		String ans = HagUtil.createFolder(packagePrep);
		hagRc.log.add(ans);
		if(!ans.startsWith("0~")){
			hagRc.rc=1;
			return ;
		}
		ans = HagUtil.createFolder(packagePrep+"\\fromTopack");
		hagRc.log.add(ans);
		if(!ans.startsWith("0~")){
			hagRc.rc=1;
			return ;
		}
		ans = HagUtil.createFolder(packagePrep+"\\config");
		hagRc.log.add(ans);
		if(!ans.startsWith("0~")){
			hagRc.rc=1;
			return ;
		}
		//HagStringList dummyF = new HagStringList();
		//dummyF.add("dummy");
		//dummyF.writeToFile(packagePrep+"\\struct2\\dummyFile.txt",null,false);
		ans = HagUtil.createFolder(packagePrep+"\\skel");
		hagRc.log.add(ans);
		if(!ans.startsWith("0~")){
			hagRc.rc=1;
			return ;
		}
		
		// create ddc folder with dummy file
		String ansDDC = HagUtil.createFolder(packagePrep+"\\DDC");
		hagRc.log.add(ansDDC);
		if(!ansDDC.startsWith("0~")){
			hagRc.rc=1;
			return ;
		}
		HagStringList dummyF = new HagStringList();
		dummyF.add("dummy");
		dummyF.writeToFile(packagePrep+"\\DDC\\dummyFile.txt",null,false);

		
		//toDo.txt
		String toDo1 = "\\\\ri-archive\\Saptech-Archive\\RI\\HAT_pack\\skel_hotfix\\bin\\config\\tree\\riInstallHotfix\\todo1.txt";
		String toDo2 = "\\\\ri-archive\\Saptech-Archive\\RI\\HAT_pack\\skel_hotfix\\bin\\config\\tree\\riInstallHotfix\\todo2.txt";
		HagStringList toDoList1 = new HagStringList(toDo1,false,"asasa",false);
		HagStringList toDoList2 = new HagStringList(toDo2,false,"asasa",false);
		
		int count =0;
		HagStringList toDoList = new HagStringList();
		toDoList.append(toDoList1);
		boolean isManualMigFound=false;
		StringBuilder migD =new StringBuilder("select * from RI.MIG_DETAILS where JCLASS IN(");
		for(int i =0;i<components.size();i++){
			String temp = components.get(i);
			String w0 = HagUtil.getWord0(temp,",",0,true);
			//	HagUtil.p(temp);
			//	HagUtil.p(w0);
			if(w0.equals("Run_manual_mig") || w0.equals("Set_MIG_DETAILS_table")){
				if(!isManualMigFound){
					toDoList.add("hotFixMigDetails_before| hotFixMigDetails_before   | Y | N | Y | 50 | hotFixMigDetails_before| #RiPackage,SQL_server,Bulk_user,Bulk_pass,Database_name                                                    | 1 | N");     
					isManualMigFound=true;
				}
				count++;
				String date2 = HagUtil.getDateTime("yyyy-MM-dd");
				String w1 = HagUtil.getWord0(temp,",",1,true);
				String w2 = HagUtil.getWord0(temp,",",2,true);
				String w3 = HagUtil.getWord0(temp,",",3,true);
				HagStringList tempList = new HagStringList();
				tempList.add("DELETE RI.MIG_DETAILS ");
				tempList.add("WHERE ID <>'"+w1+"' AND JCLASS = 'com.sapiens.mig.ri.v050m00."+w2+"'");
				tempList.add("IF EXISTS (SELECT 1 from MIG_DETAILS where ID='"+w1+"')");
				tempList.add("begin");
				tempList.add("UPDATE RI.MIG_DETAILS");
				tempList.add("SET MIG_TYPE = "+w3);
				tempList.add("WHERE ID ='"+w1+"'");
				tempList.add("end");
				tempList.add("INSERT INTO MIG_DETAILS");                                   
				tempList.add("(ID,RUN_ORDER,JCLASS,MIG_TYPE,STATUS,UPDATED_DATE)");        
				tempList.add("VALUES");                                                    
				tempList.add("("+w1+","+w1+",'com.sapiens.mig.ri.v050m00."+w2+"',"+w3+",10,'"+date2+"')");
				tempList.writeToFile(packagePrep+"\\fromTopack\\"+count+"_"+w1+"_"+w2+"_"+w3+".sql",null,false);
				toDoList.add("Update MigDetails     | UpdateMigDetails#"+w1+"  | Y | N | Y | 50 | Update MigDetails  | riJava_folder,#RiPackage,SQL_server,Bulk_user,Bulk_pass,Database_name,!"+count+"_"+w1+"_"+w2+"_"+w3+".sql,!{ALL}      | 1 | N");                                                                                                                                                                                                                       
				if(w0.equals("Run_manual_mig")) 
					toDoList.add("Run Manual Mig        | RunManualMig#"+w2+"      | Y | N | Y | 50 | Run Manual Mig     | riJava_folder,Batch_name,#RiPackage,!"+w2+",SQL_server,Database_name,Bulk_user,Bulk_pass,!{ALL}         | 1 | N");                                                                                                                                                                                                                       
				migD.append("'com.sapiens.mig.ri.v050m00.").append(w2).append("',");
			}else if(w0.equals("Replace jar")){
				String w1 = HagUtil.getWord0(temp,",",1,true);
				toDoList.add("Replace Jar           | ReplaceJar#batch-MSS     | Y | N | Y | 50 | Replace Jar        | !"+w1+",riJava_folder,#RiPackage                                                                           | 1 | N");      
			}else if(w0.equals("Replace war")){
				toDoList.add("Replace war           | ReplaceRiWebWar          | Y | N | Y | 50 | Replace war        | riJava_folder,Batch_name,#RiPackage                                                                        | 1 | N");    
			}else if(w0.equals("Replace jasper")){
				toDoList.add("Jasper update         | JasperUpdate             | Y | N | Y | 50 | Jasper update      | #InstallationTypeHotFix,#RiPackage,riJava_folder,Batch_name,SQL_server,Bulk_user,Bulk_pass,Database_name   | 1 | N");  
			}else if(w0.equals("Replace ddc")){
				toDoList.add("Replace DDC tables    | ReplaceDDC               | Y | N | Y | 50 | Replace DDC tables | #InstallationTypeHotFix,#RiPackage,SQL_server,Bulk_user,Bulk_pass,Database_name                            | 1 | N");  
			}else if(w0.equals("Replace IOM")){
				toDoList.add("Copy IOM              | CopyIom                  | Y | N | Y | 50 | Copy IOM           | IOM_folder,#RiPackage,!iom"+hotfix+".zip                                                                                      | 1 | N");  
			}else if(w0.equals("Copy migMng.jar")){
				toDoList.add("ReplaceJar            | ReplaceJar#mig-mng       | Y | N | Y | 50 | ReplaceJar         | !mig-mng.jar,riJava_folder,#RiPackage                                                                      | 1 | N");     
			}else if(w0.equals("ReplaceAdd Scripts")){
				toDoList.add("ReplaceAdd Scripts    | ReplaceAddScripts        | Y | N | Y | 50 | ReplaceAdd Scripts | Batch_name,#RiPackage                                                                                      | 1 | N");     
			}
		}
		if(isManualMigFound){
			String migDstr=migD.toString();
			migDstr=migDstr.substring(0,migDstr.length()-1)+")";
			HagStringList temp122 = new HagStringList();
			temp122.add(migDstr);
			temp122.writeToFile(packagePrep+"\\fromTopack\\hotFixMigDetails.sql",null,false);
			toDoList.add("hotFixMigDetails_after| hotFixMigDetails_after   | Y | N | Y | 50 | hotFixMigDetails_after| #RiPackage,SQL_server,Bulk_user,Bulk_pass,Database_name                                                    | 1 | N");     
		}
		toDoList.append(toDoList2);
		toDoList.writeToFile(packagePrep+"\\config\\toDo.txt",null,false);
		////title.txt
		String CDdate = HagUtil.getDateTime("yyyy/MM/dd");
		String CDtime = HagUtil.getDateTime("HH:mm:ss");
		//HagStringList title = new HagStringList();
		//title.add("b       Installs the Reinsurance Logic Server software(use for Logic Server on Windows platform only)");
		//title.add("x       The installation cannot run from the release CD - The files must be copied to a writable location");
		//title.add("r       RI - V"+ver.charAt(1)+"R"+ver.substring(2,4)+"M"+maint+"-update"+update+"-hotfix"+hotfix);	
		//title.writeToFile(packagePrep+"\\struct1\\title.txt",null,false);
	
		String bisV = "4530";
		String bisM = "00";
		if(ver.equals("0606")){
			bisV = "4520";
			bisM = "05";
		}
		String verS = ver.substring(0,2)+"."+ver.substring(2,4)+"."+maint+"."+update+"."+hotfix;
		HagStringList config = new HagStringList();
		config.add("CDversion="+verS);
		config.add("CDdate="+CDdate);
		config.add("CDtime="+CDtime);
		config.add("REQversion="+verS);
		config.add("REQdate="+CDdate);
		config.add("REQtime="+CDtime);
		config.add("jasperUpdate=Y");
		config.add("hotfix="+hotfix);
		config.add("bisVersion="+bisV);
		config.add("bisMaint="+bisM);
		config.add("hagLog="+ver+"M"+maint);
		config.add("hagJiraVer="+ver+"M"+maint+"u"+update+"hf"+hotfix+"(FTP)");
		config.add("customer="+customer);
		config.writeToFile(packagePrep+"\\skel\\config.txt",null,false);

		return ;
	}
	public static 		String getEnvLine(String name,String defApp,String defDbid){
		HagStringList ans1 = new HagStringList();
	 	String stm1 = "select bis_server,batchName,sql_server,party,lastInst,lastGoodInst from dbo.ri_environments_new where  status='A' and project <> 'HAGWIDTH' order by bis_server,batchName";
		HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1, ans1, "~");
		HagStringList ans0 = new HagStringList();
	 	String stm0 = "select PARTY_ID,CUSTOMER_CODE from dbo.RICUSTOMER";
		HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm0, ans0, "~");

		StringBuilder from =new StringBuilder("<select name=\""+name+"\">");
		for (int i=0;i<ans1.size();i++){
			String temp = ans1.get(i);
			String server= HagUtil.getWord0(temp, "~", 0,true);
			String dbid= HagUtil.getWord0(temp, "~", 1,true);
			String sql= HagUtil.getWord0(temp, "~", 2,true);
			String customer= HagUtil.getWord0(temp, "~", 3,true);
			//HagUtil.p(customer);
			String customer1 = ans0.lookFor(customer, "~",true,true);
			//HagUtil.p(customer1);
			if(customer1!=null)
				customer = customer1;
			String status= HagUtil.getWord0ToEnd(temp,"~",4,true);
	

			StringBuilder temp1 = new StringBuilder("APP(").append(server).append(") , ")
					.append("DBID(").append(dbid).append(") , ")
					.append("SQL(").append(sql).append(") , ")
					.append("CUSTOMER(").append(customer).append(") , ")
					.append("STATUS(").append(status).append(")");
			String temp2 = temp1.toString();
			if(defApp!=null && defDbid!=null && defApp.equals(server) && defDbid.equals(dbid)) {
				//StringBuilder aa =new StringBuilder().append("<option value=\"").append(temp2).append(" selected ").append("\">").append(temp2).append("</option>");
				//StringBuilder bb =new StringBuilder().append("<option value=\"").append(temp2).append("\">").append(temp2).append("</option>");
				//String aa1=aa.toString();
				//String bb1=bb.toString();
				//String zz=aa1+bb1;
				from.append("<option value=\"").append(temp2).append("\"").append(" selected ").append(">").append(temp2).append("</option>");
			}
		//	else
			//	from.append("<option value=\"").append(temp2).append("\">").append(temp2).append("</option>");
		}
		from.append("</select>");	
		return from.toString();
		
	}
	public static final String 			copyFileViaDos(String fromFile,String toFile){
		String rc = deleteFile(toFile,  false);
		if(!rc.startsWith("0~")) 			
			return rc;
		
		rc = HagUtil.simpleDosCmd("javaFunc~simpleDosCmd~copy~/Y~\""+fromFile+"\" \""+toFile+"\"",false);
		if(!rc.startsWith("0~")){
			
			return "1~Failed to copy "+fromFile+" to "+toFile;
		}else
			return "0~"+fromFile+" copied to "+toFile;
	}
	public static final void 			copyFileViaDos1(HagRc hagRc,String fromFile,String toFile,boolean gui,boolean mustExist,boolean deleteFirst){
		  String rc ="0~";
		  if(deleteFirst){
			  deleteFile(hagRc,toFile, false);
				  if(hagRc.rc!=0){
					  return;
				  }
			}
			
		  	String cmd = "copy~/Y~\""+fromFile+"\" \""+toFile+"\"";
		//  	HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", cmd,"aaa"  ) ;
			rc = simpleDosCmd(cmd, false);
		//	HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", ""+rc,"aaa"  ) ;
			if(!rc.startsWith("0~")){
				if(gui)
					JOptionPane.showMessageDialog(
							null,
							"Failed to copy "+fromFile+" to "+toFile,
							"I/O error",
							JOptionPane.ERROR_MESSAGE);
						hagRc.log.add("1~Failed to copy "+fromFile+" to "+toFile);
				hagRc.rc=1;
				return ;
			}else{
				hagRc.log.add("0~"+fromFile+" copied to "+toFile);
				hagRc.rc=0;
				return ;
			}
	}
	public static final void    		deleteFile(HagRc hagRc,String fileStr,boolean mustExistBefore){
		File file = new File(fileStr);
		if(file.exists()){
			if(!file.delete()){
				hagRc.rc=1;
				hagRc.log.add("1~Failed to delete "+fileStr);
				return ;
			}else{
				hagRc.rc=0;
				hagRc.log.add("0~"+fileStr+" deleted");
				return ;
			}
		}else{
			if(mustExistBefore){
				hagRc.rc=1;
				hagRc.log.add("1~"+fileStr+" file not found");
				return ;
			}else{
				hagRc.rc=0;
				hagRc.log.add("0~"+fileStr+" file not found (not a mustExist)");
				return ;
			}
		}
	}
	public static final String 			copyFolderGonen(String from,String to,String toApp,String toDb,String log){

		String batch = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\robocopy.bat "+from+" "+to+" "+toApp+" "+toDb+" "+log;
		//String ansB = HagUtil.runBatch(batch, true);
		//return ansB;
		
		String ans1=runCmdRemote("RI13QA",batch+"\n","N");
		HagStringList logList = new HagStringList(log,false,"asdasda",false);
		if(logList==null || logList.size()==0 )
			return "?~log file not found";
		String logListS = logList.convertToString("<br>");
		String rc = logList.get(logList.size()-1);
		if(rc!=null) {
			rc=rc.trim();
			if(rc.equals("0") || rc.equals("1") || rc.equals("2") || rc.equals("3") )
				return "0~"+logListS;
			else
				return "1~"+logListS	;
		}else
			return "?~"+logListS;
		
		
	}
	public static final String 			copyFolderGonen111(String from,String to){
		String loc ="c:\\Windows\\SysWOW64\\Robocopy.exe";
		String flags="/E /Z /ZB /R:5 /W:5 /TBD /NP /MT:16 /V /nfl";
		// flags="";
		StringBuilder cmdSB = new StringBuilder(loc).append(" ").append(from).append(" ").append(to).append(" ").append(flags);
		String cmd = cmdSB.toString();
			
		
		HagStringList ans= HagUtil.runCmdGonen(cmd,true);
		//String ans = simpleDosCmd(cmd.toString(),false);
		String ans1=ans.convertToString(" ");
		if(ans1.indexOf("0~")>-1)
			return "0~cmd";
		else
			return "1~cmd";
	
	}
	public static final String 			copyFolder(String from,String to,boolean totalCommanderPreBox,boolean gui,
									String errorLevel,String exclude){
		from=from.trim();
		to=to.trim();
		//String ff = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\list\\riMail1.list";
		//HagStringList list = new HagStringList(ff,true,"*",false);
		//HagStringList roboCopyExcludeList = new HagStringList();
		String excludeXcopy1 = " ";
		String excludeRobocopy2 = " ";	
		//String excludeXcopy1 = "~/exclude:"+exclude;
		//String excludeRobocopy2 = "~/xf from.log ";	
	if(exclude==null || exclude.trim().length()==0) {
		excludeXcopy1="";
		excludeRobocopy2="";
	}else {
		//roboCopyExcludeList = new HagStringList(exclude,true,"*",false);
		excludeRobocopy2 = "~/xd~"+from+"\\logs~"+from+"\\temp";
	//	for(int i =0;i<roboCopyExcludeList.size();i++ ) {
	//		excludeRobocopy2=excludeRobocopy2+from+"\\*"+roboCopyExcludeList.get(i)+" ";
			
	//	}
		excludeXcopy1 = "~/exclude:"+exclude;
	}
	//excludeXcopy1="";
	//excludeRobocopy2="";

	// error level:
		//       robocopy  0: only rc
		//				   1: rc+job summary.
		//                 2: rc+job summary+fileList
		//                 3: all
		
		if(from.length()<2)
			return "0~no file to copy";
		
		String last =from.substring(from.lastIndexOf("\\")+1,from.length());
		if(last.indexOf("*") < 0 && last.indexOf("?") < 0 ){
			//folder or specific file
			File fromF = new File(from);
			if(!fromF.exists()){
			//	HagUtil.p("2source path not found. "+fromF);
				return "1~source not found. "+fromF;
			}
		}else{
			String path =from.substring(0,from.lastIndexOf("\\"));
			File fromP = new File(path);
			if(!fromP.exists() || !fromP.isDirectory()){
			//	HagUtil.p("1source path not found. "+fromP);
				return "1~source path not found. "+fromP;
			}
		}
		///
		String os = System.getProperty("os.name");
		String ans = "wrong OS";
		if( os.equalsIgnoreCase("Windows 2000") ||
			os.equalsIgnoreCase("Windows 2003") ||
			os.equalsIgnoreCase("Windows XP") ){
				StringBuilder cmd = new StringBuilder("javaFunc~simpleDosCmd~xcopy~").append(from).append("~").append(to).append("~/E~/I~/K~/Y"+excludeXcopy1);
				ans = simpleDosCmd(cmd.toString(),false);
		}else{
			from=from.trim();
			to=to.trim();
			File ff = new File(from);
			String noDsp = "~/njh~/njs~/np~/ndl~/nfl~/nc~/ns";
			if(errorLevel.equals("1"))
				noDsp = "~/njh~/np~/ndl~/nfl~/nc~/ns";
			else if(errorLevel.equals("2"))
				noDsp = "~/njh~/np~/nc~/ns";
			else if(errorLevel.equals("3"))
				noDsp = "";
			StringBuilder cmd = new StringBuilder("javaFunc~simpleDosCmd~robocopy~").append(from).append("~").append(to).append("~/E").append(noDsp+excludeRobocopy2);
			if(!ff.isDirectory()){
				int pos = from.lastIndexOf("\\");
				if(pos<0){
					//HagUtil.dspMsgBox("please call hagay-2831<newLine>robocopy<newLine>"+from+"<newLine>"+to,JOptionPane.ERROR_MESSAGE ,null,null);
					return "2~robocopy "+from+" "+to;
				}
				String ddd = from.substring(0,pos);
				String fff = from.substring(pos+1,from.length());
			
				
				cmd = new StringBuilder("javaFunc~simpleDosCmd~robocopy~").append(ddd).append("~").append(to).append("~").append(fff).append("~/E").append(noDsp+excludeRobocopy2);
			}
				
			ans = simpleDosCmd(cmd.toString(),false);
		}
		if(totalCommanderPreBox)
			JOptionPane.showMessageDialog(null,
	 	             "Please check/rename via total commander\nwhen done\nclose total commander to continue. " , "XCopy",
		             JOptionPane.INFORMATION_MESSAGE);
		return ans;
	}
	public static final void 			appendToFile(String fileS, String line, String flag  ) {

		
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter out = null;
		String date = HagUtil.getDateTime("dd/MM/yyyy - HH:MM:ss");
		try {
		    fw = new FileWriter(fileS, true);
		    bw = new BufferedWriter(fw);
		    out = new PrintWriter(bw);
		    out.println(date+"    "+line);
		    out.close();
		} catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
		finally {
		    if(out != null)
			    out.close();
		    try {
		        if(bw != null)
		            bw.close();
		    } catch (IOException e) {
		        //exception handling left as an exercise for the reader
		    }
		    try {
		        if(fw != null)
		            fw.close();
		    } catch (IOException e) {
		        //exception handling left as an exercise for the reader
		    }
		}
	}
	
	public static final void 			appendToDebugFile( String line ) {
		if(1==1)
			return;
		String fileS = "\\\\ri-archive\\Saptech-Archive\\hag\\debug\\cfgMenuWeb\\debug.txt";
		String dirS1 = "\\\\ri-archive\\Saptech-Archive\\hag";
		String dirS2 = "\\\\ri-archive\\Saptech-Archive\\hag\\debug";
		String dirS3 = "\\\\ri-archive\\Saptech-Archive\\hag\\debug\\cfgMenuWeb";
		
		File dir1 = new File(dirS1);
		File dir2 = new File(dirS2);
		File dir3 = new File(dirS3);
		File ff = new File(fileS);
		if(!dir1.exists() || !dir1.isDirectory()) 
			createFolder(dirS1);
		if(!dir2.exists() || !dir2.isDirectory()) 
			createFolder(dirS2);
		if(!dir3.exists() || !dir3.isDirectory()) 
			createFolder(dirS3);
		if(!ff.exists() || !ff.isFile()) 
			HagUtil.writeStringToFile(fileS, "start");
		
				
		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter out = null;
		String date = HagUtil.getDateTime("dd/MM/yyyy - HH:MM:ss");
		try {
		    fw = new FileWriter(fileS, true);
		    bw = new BufferedWriter(fw);
		    out = new PrintWriter(bw);
		    out.println(date+"    "+line);
		    out.close();
		} catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
		finally {
		    if(out != null)
			    out.close();
		    try {
		        if(bw != null)
		            bw.close();
		    } catch (IOException e) {
		        //exception handling left as an exercise for the reader
		    }
		    try {
		        if(fw != null)
		            fw.close();
		    } catch (IOException e) {
		        //exception handling left as an exercise for the reader
		    }
		}
	}
	public static final String 			copyDb_olddddddddd(String fromDb, String toDb, String fromSql_server, String toSql_server  ) {

		// String bkupFile = "\\\\confg1\\d$\\ri\\manage\\temp\\" + fromDb +
		// ".bk";
		String ans = "";
		String color = "bgColor = \"#00ff00\"";
		String bkupFile = "\\\\ri-archive\\Saptech-Archive\\RI\\copyDbTemp\\" + fromDb + ".bk";

		HagUtil.deleteFile(bkupFile,  false);
		ey_Gsetlibl ey_gsetlibl = new ey_Gsetlibl();
		//String rcStr0 = ey_gsetlibl.SimpleBK(fromSql_server, fromDb, bkupFile, "WITH INIT");
		String rcStr0 = ey_gsetlibl.SimpleBK(fromSql_server, fromDb, bkupFile, "WITH COPY_ONLY");
		if (rcStr0.indexOf("1~") > -1){
			ans = ans+"sourceBkup RC =FAILED<br>"+rcStr0;
			color = "bgColor = \"#ff0000\"";
			
		}else{
			ans = ans+"sourceBkup RC =OK<br>"+rcStr0;
		}
		
//		String datLoc = "D:\\Database\\";
//		String logLoc = "D:\\Database\\";
		
		String datLoc =HagUtil.getSqlLocation(toSql_server,4).trim();
		String logLoc =HagUtil.getSqlLocation(toSql_server,5).trim();
		ans = ans+"<br><br>";
		String rcStr1 = ey_gsetlibl.Create_Restore_with_dbfpath(toSql_server, toDb, bkupFile, datLoc, logLoc);
		if (rcStr1.indexOf("1~") > -1){
			ans = ans+", targetRst RC =FAILED<br>"+rcStr1;
			color = "bgColor = \"#ff0000\"";

		}else{
			ans = ans+", targetRst RC =OK<br>"+rcStr1 ;

		}
		return "<td "+color+">"+ans+"</td>";
	}
	

	public static final void  			crtBcps(String ddcfolder, String db, String schema, String split, String sqlServer,HagRc hagRc) {
		// replace ddc
		String splitDdc 		= ddcfolder + split + ".DDC";
		String splitDdcLog 		= ddcfolder + split + "_DDC_log.txt";
		String splitFmt 		= ddcfolder + split + ".FMT";
		String splitFmtLog 		= ddcfolder + split + "_FMT_log.txt";
		String splitName 		= db + "." + schema + "." + split;
		StringBuilder splitFmtCmd = new StringBuilder("bcp ").append(splitName).append(" format null -f ").append(splitFmt).append(" -n -m 0 -S ").append(sqlServer).append(" -U cfg -P cfg09c -o ").append(splitFmtLog);
		StringBuilder splitDatCmd = new StringBuilder("bcp ").append(splitName).append(" out ").append(splitDdc).append(" -n -m 0 -S ")	.append(sqlServer).append(" -U cfg -P cfg09c -o ").append(splitDdcLog);

		// delete ddc files
		String stepRC = HagUtil.deleteFile(splitDdc,  false);
		if (!stepRC.startsWith("0~")) {
			hagRc.log.add("1-Failed to delete " + splitDdc);
			hagRc.rc=1;
			return;
		}
		stepRC = HagUtil.deleteFile(splitDdcLog,  false);
		if (!stepRC.startsWith("0~")) {
			hagRc.log.add("1-Failed to delete " + splitDdcLog);
			hagRc.rc=1;
			return;
		}
		stepRC = HagUtil.deleteFile(splitFmt,  false);
		if (!stepRC.startsWith("0~")) {
			hagRc.log.add("1-Failed to delete " + splitFmt);
			hagRc.rc=1;
			return;
		
		}
		stepRC = HagUtil.deleteFile(splitFmtLog,  false);
		if (!stepRC.startsWith("0~")) {
			hagRc.log.add("1-Failed to delete " + splitFmtLog);
			hagRc.rc=1;
			return;
		}

		// run bcp
		String stepRC1 = HagUtil.runCmd2(splitFmtCmd.toString(), true);
		String stepRC2 = HagUtil.runCmd2(splitDatCmd.toString(), true);
		if (!stepRC1.startsWith("RC=0") || !stepRC2.startsWith("RC=0")) {
			hagRc.log.add("1-Failed to create bcp file for " + splitName + " table.");
			hagRc.rc=1;
			return;
		} else {
			hagRc.log.add("0-"+splitName+" bcp file created.");
			return;
		}
	}
	public static final String 			copyDb(String fromDb, String toDb, String fromSql_server, String toSql_server  ) {

		// String bkupFile = "\\\\confg1\\d$\\ri\\manage\\temp\\" + fromDb +
		// ".bk";
		String ans = "";
		String color = "bgColor = \"#00ff00\"";
		String bkupFile = "\\\\ri-archive\\Saptech-Archive\\RI\\copyDbTemp\\" + fromDb + ".bk";

		HagUtil.deleteFile(bkupFile,  false);
		ey_Gsetlibl ey_gsetlibl = new ey_Gsetlibl();
		//String rcStr0 = ey_gsetlibl.SimpleBK(fromSql_server, fromDb, bkupFile, "WITH INIT");
		String rcStr0 = ey_gsetlibl.SimpleBK(fromSql_server, fromDb, bkupFile, "WITH COPY_ONLY");
		if (rcStr0.indexOf("1~") > -1){
			ans = ans+"sourceBkup RC =FAILED<br>"+rcStr0;
			color = "bgColor = \"#ff0000\"";
			
		}else{
			ans = ans+"sourceBkup RC =OK<br>"+rcStr0;
		}
		
//		String datLoc = "D:\\Database\\";
//		String logLoc = "D:\\Database\\";
		
		String datLoc =HagUtil.getSqlLocation(toSql_server,4).trim();
		String logLoc =HagUtil.getSqlLocation(toSql_server,5).trim();
		ans = ans+"<br><br>";
		String rcStr1 = ey_gsetlibl.Create_Restore_with_dbfpath(toSql_server, toDb, bkupFile, datLoc, logLoc);
		if (rcStr1.indexOf("1~") > -1){
			ans = ans+", targetRst RC =FAILED<br>"+rcStr1;
			color = "bgColor = \"#ff0000\"";

		}else{
			ans = ans+", targetRst RC =OK<br>"+rcStr1 ;

		}
		return "<td "+color+">"+ans+"</td>";
	}	
	
	//D
	public static final String 			deleteFile11111111111111(String fileStr,boolean gui,boolean mustExist){
		File file = new File(fileStr);

		if(file.exists()){
			if(!file.delete()){
				if(gui) {
					JOptionPane.showMessageDialog(
							null,
							"Failed to delete "+fileStr,
							"I/O error",
							JOptionPane.ERROR_MESSAGE);
					}
				else {
					return "1~Failed to delete"+fileStr;
				}
			}else
				return "0~"+fileStr+" deleted";
		}else{
			if(mustExist)
				return "1~"+fileStr+" not found";	
		}

		return "0~"+fileStr+" not found";
	}
	public static final String 			deleteFile(String fileStr,boolean mustExist){
		File file = new File(fileStr);

		if(file.exists()){
			if(!file.delete()){
					return "1~Failed to delete"+fileStr;
			}else
				return "0~"+fileStr+" deleted";
		}else{
			if(mustExist)
				return "1~"+fileStr+" not found";	
		}

		return "0~"+fileStr+" not found";
	}
	public static final boolean deleteDir(File dir, JPanel panel) {
		//boolean flag = true;
        if (dir.isDirectory()) {
            String[] children = dir.list();
            if(children.length > 0){
            	for (int i=0; i<children.length; i++) {
            		boolean success = deleteDir(new File(dir, children[i]),panel);
            		
            	}
            }
	 	}
        
        boolean rc = dir.delete();
       
        return rc;
	}
	public static final String 			deleteFolder(String dirStr, JPanel panel,boolean mustExist,boolean isConsist) {
		File dir = new File(dirStr);
		if (!dir.exists()){
			if (mustExist)
				return "1~"+dirStr+" not found.";
			else
				return "0~"+dirStr+" not found.";
		}
		boolean rc=false;
		if(isConsist)
			rc = deleteDir(dir,panel);
		else
			rc = deleteWebAppsDir(dir,panel);
			
		if(rc)
			return "0~"+dirStr+" deleted.";
		else
			return "1~failed to delete "+dirStr+" folder.";
	}
	public static final boolean 		deleteWebAppsDir(File dir, JPanel panel) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			if(children.length > 0){
				for (int i=0; i<children.length; i++) {
					boolean success = deleteWebAppsDir(new File(dir, children[i]),panel);
					if (!success) {
						//JOptionPane.showMessageDialog(panel,
						//		"Can't delete "+dir+"\\"+children[i], 
						//		"I/O Error",
						//		JOptionPane.ERROR_MESSAGE);
						return false;
					}
				}
			}
		}
		
		boolean rc=false;
		try{
			rc = dir.delete();
			if(rc == false)
				return false;
				//JOptionPane.showMessageDialog(panel,
				//	"Can't delete "+dir, 
				//	"I/O Error",
				//	JOptionPane.ERROR_MESSAGE);
		}catch (SecurityException se) {
			//JOptionPane.showMessageDialog(panel,
			//		se.getMessage(), 
			//		"(SecurityException) Unable to delete " + dir ,
			//		JOptionPane.ERROR_MESSAGE);
			return false;
		
		}catch (Exception e) {
			//JOptionPane.showMessageDialog(panel,
			//	e.getMessage(), 
			//	"(Exception) Unable to delete " + dir ,
			//	JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return rc;
		
	}
	//E
	//F
	public static final	String 			future(HttpServletRequest request, HttpServletResponse response,String str,String user){
		return "a future option";
	}
	public static final String  findKeyLineInArrayList(ArrayList<String> arr,String key,int pos,String delim){
		String ans = "NOTFOUND";
		for(int i = 0 ; i < arr.size();i++){
			String str = arr.get(i);
			String key1 = getWord0(str, delim, pos, true);
			if(key1.equals(key))
				return str;
		}
		return ans;
	
	}
	//G
	public static final String 			getprcId(String str,String env){
		ArrayList<String> arr = splitStr2ArrayList(str,"\r\n");
		String prcFound = "-1";
		for(int i = 1 ; i < arr.size();i++){
			String temp = arr.get(i);
			String dbid = getWord0(temp," ",1, true);
			String prc = getWord0(temp," ",3, true);
			if(dbid.equalsIgnoreCase("\""+env+"\""))
				prcFound=prc;
		}
		//System.out.println("prcFound="+prcFound);
		if(prcFound.equals("-1"))
			return "1~"+env +" not found.";
		else if(prcFound.equals("0."))
			return "2~"+env +" is down.";
		else{
			return "0~"+prcFound.substring(0,prcFound.length()-1);
		}
	}
	public static final HagStringList  getTaskListToFile(	String server,
			String wmicFile_asc,
			String wmicFile_utf8,
			String cmd					) {
		// delete file

		deleteFile(wmicFile_asc,  false);
		deleteFile(wmicFile_utf8,  false);
		// run batch
		//HagUtil.p(cmd);
		String ansB = HagUtil.runBatch(cmd, true);
		//HagStringList  ansB = HagUtil.runCmd3(cmd, true);
		//if(1==1)
		//	return ansB;
					
		//HagClient hagClient = new HagClient();
		//ArrayList<String>  ans = hagClient.runNotSudo(server,cmd);
		//try {
		//	Thread.currentThread();
		//	Thread.sleep(2500);
		//} catch (InterruptedException e) {
		//	e.printStackTrace();
		//	}
		//HagUtil.runCmd("type " + wmicFile_asc + " > " + wmicFile_utf8, true);
		sleep(4500);
		HagUtil.convertEncoding(wmicFile_asc,"utf-16le",wmicFile_utf8,"UTF-8");
		sleep(2500);
		try {
			Thread.currentThread();
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// read file
		sleep(2500);
		HagStringList list = new HagStringList(wmicFile_utf8, false, "*",true);
		int num = 0;
		while(list.size()<2 && num < 3){
			sleep(2500);
			
			deleteFile(wmicFile_utf8,  false);
			HagUtil.convertEncoding(wmicFile_asc,"utf-16le",wmicFile_utf8,"UTF-8");	
		
			list = new HagStringList(wmicFile_utf8, false, "*",true);
			num++;
		}
		if(list.get(0) == null || list.get(0).indexOf("CommandLine")<0){
			JOptionPane.showMessageDialog(null,"Convert to UTF8 format failed - please call hagay 2831", "Convert to UTF8 ",JOptionPane.ERROR_MESSAGE);
			return null ;
		}

		return list;
	}
	public static final void convertEncoding(String fileIn,String encodingIn,String fileOut,String encodingOut){
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileIn),encodingIn));
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileOut),encodingOut));
			char[] buffer = new char[16384];
			int read;
			try {
				while ((read = br.read(buffer)) != -1)
					bw.write(buffer, 0, read);
				
				  if (br != null) br.close();
				  if (bw != null) bw.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static final HagStringList 	getWmicByName(String server,String name1,String dbid){
		
		HagStringList ans1 = new HagStringList();
		HagStringList ans = new HagStringList();
		String line;
		//String riJava = "d:\\ri_java\\RIjava_"+batchName; 
		String server1 = server+".sapiens.int";
		String server2 = HagUtil.replaceStr(server1, "-", "_");
		String date = HagUtil.getDateTime("yyyyMMddHHmmsss");
		String wmicFile1 = "D:\\CFG\\temp\\taskManager_" + server2 + "_"+date+"_asc.txt";
		String wmicFile2 = "D:\\CFG\\temp\\taskManager_" + server2 + "_"+date+ "_utf8.txt";
		//String cmd = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\wmic_list.bat " + wmicFile1 + " " + server1
			//	+ " gon09c"+" process where name='"+"java.exe"+"' get Commandline,processid,name";
	
		
		/////
		String cmd = "WMIC /OUTPUT:STDOUT   process where name=\'java.exe\' get Commandline,processid,name";
		System.out.println(cmd);
		String aaa =HagUtil.runNotSudo9(server, cmd, "sapiens\\cfg-burner");
		System.out.println(aaa);
		HagStringList list9 =new HagStringList(aaa,"\n",false);
		//if(1==1) {
			
		//	HagStringList list =new HagStringList(aaa,"\n",false);
		//	return list;
		//}
		/////
		String riJava="D:\\RI_JAVA\\RIjava_"+dbid+"\\tomcat\\";
		//HagStringList list = HagUtil.getTaskListToFile(server, wmicFile1, wmicFile2, cmd);
		for (int i = 0; i < list9.size(); i++) {
			String temp2 = list9.get(i).toUpperCase();
			String find = (riJava).toUpperCase();
			int pos = temp2.lastIndexOf(find);
			int pos1 = temp2.lastIndexOf(name1.toUpperCase());
			if (pos > -1 && pos1 > -1) {
				ans.add(temp2);
				//String zz = (temp2.substring(pos1+9, temp2.length())).trim();
			//	//return "<td bgColor=\"#00FF00\">RUNNING ("+zz+")</td>";
			}
		}
		//return "<td bgColor=\"#FF6600\">NOT FOUND</td>";

		//for(int i = 0 ; i < ans1.size();i++){
		//	String temp1 = ans1.get(i);
		//	if(temp1.length()>0)
		//		ans.add(temp1);
		//}
		
		return ans;
	}
	
public static final HagStringList 	getWmicByName9(String server,String name1){
		
		HagStringList ans1 = new HagStringList();
		HagStringList ans = new HagStringList();
		String line;
		//String riJava = "d:\\ri_java\\RIjava_"+batchName; 
		String server1 = server+".sapiens.int";
		String server2 = HagUtil.replaceStr(server1, "-", "_");
		String date = HagUtil.getDateTime("yyyyMMddHHmmsss");
		String wmicFile1 = "D:\\CFG\\temp\\taskManager_" + server2 + "_"+date+"_asc.txt";
		String wmicFile2 = "D:\\CFG\\temp\\taskManager_" + server2 + "_"+date+ "_utf8.txt";
		//String cmd = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\wmic_list.bat " + wmicFile1 + " " + server1
		//		+ " gon09c"+" process where name='"+"java.exe"+"' get Commandline,processid,name";
	
		
		String cmd = "WMIC /OUTPUT:STDOUT   process where name=\'java.exe\' get Commandline,processid,name";
		System.out.println(cmd);
		String aaa =HagUtil.runNotSudo9(server, cmd, "sapiens\\cfg-burner");
		System.out.println(aaa);
		 //bbb = aaa.convertToString("<br>");
		//return aaa;
		if(1==1) {
			
			HagStringList list =new HagStringList(aaa,"\n",false);
			return list;
		}
		
		HagStringList list = HagUtil.getTaskListToFile(server, wmicFile1, wmicFile2, cmd);
		for (int i = 0; i < list.size(); i++) {
			String temp2 = list.get(i).toUpperCase();
			//String find = (riJava+"\\tomcat\\").toUpperCase();
			//int pos = temp2.lastIndexOf(find);
			//int pos1 = temp2.lastIndexOf(name1);
			//if (pos > -1) {
				ans.add(temp2);
				//String zz = (temp2.substring(pos1+9, temp2.length())).trim();
			//	//return "<td bgColor=\"#00FF00\">RUNNING ("+zz+")</td>";
			//}
		}
		//return "<td bgColor=\"#FF6600\">NOT FOUND</td>";

		//for(int i = 0 ; i < ans1.size();i++){
		//	String temp1 = ans1.get(i);
		//	if(temp1.length()>0)
		//		ans.add(temp1);
		//}
		return ans;
	}
	public static final HagStringList 	getWmicByName1111111111(String server,String name){
		HagStringList ans1 = new HagStringList();
		HagStringList ans = new HagStringList();
		String line;
		try {
			Process proc = Runtime.getRuntime().exec("wmic.exe");
			BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			OutputStreamWriter oStream = new OutputStreamWriter(proc.getOutputStream());
			if(name==null)
				oStream .write("/node:\""+server+"\" /user:\"sapiens\\cfg-burner\" /password:\"gon09c\" PROCESS get Commandline,processid,name,WorkingSetSize");
			else
				oStream .write("/node:\""+server+"\" /user:\"sapiens\\cfg-burner\" /password:\"gon09c\" process where name='"+name+"' get Commandline,processid,name");
			oStream .flush();
			oStream .close();
			while ((line = input.readLine()) != null) {
				ans1.add(line.trim());
			}
			input.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		for(int i = 0 ; i < ans1.size();i++){
			String temp1 = ans1.get(i);
			if(temp1.length()>0)
				ans.add(temp1);
		}
		return ans;
	}
	public static final String 			getWord0(String str,String delim,int pos,boolean trim){
		/*
			String ret = "";
			StringTokenizer words = new StringTokenizer(str,delim);
			if (words.countTokens()<pos)
				return "";
			for(int i = 0 ; i <= pos; i++){
				try {
					ret = words.nextToken();
				}
				catch(NoSuchElementException nsee){
				}
			}
			if(trim)
				ret = ret.trim();
			return ret;
		 */
		ArrayList<String> aa = splitStr2ArrayList( str, delim);
		if(aa.size()<=pos)
			return "";
		String temp = aa.get(pos);
		if(trim && temp!=null)
			return temp.trim();
		return temp;
	}
	public static final String 			getWord0ToEnd(String str,String delim,int pos,boolean trim){
		/*
			StringBuilder retAll =new StringBuilder();
			String ret = "";
			StringTokenizer words = new StringTokenizer(str,delim);
			int last = words.countTokens()-1;
			if (last < pos)
				return "";

			for(int i = 0 ; i <= last; i++){
				try {
					ret = words.nextToken();
					if(i<pos)
						continue;
					if(trim)
						ret = ret.trim();

					retAll.append(ret);
					if(i<last){
						retAll.append(delim);
					}
				}
				catch(NoSuchElementException nsee){
				}
			}

			return retAll.toString();
		 */
		String hagay = str.trim();
		if(delim.equals("|") && hagay.startsWith("|") && hagay.endsWith("|")) 
			str=str.substring(1,str.length()-1);
		ArrayList<String> aa = splitStr2ArrayList( str, delim);
		if(aa.size()<=pos)
			return "";
		StringBuilder bb = new StringBuilder();
		for(int i = pos ; i <aa.size(); i++){
			String temp = aa.get(i);
			if(trim && temp !=null)
				temp=temp.trim();
			bb.append(temp);
			if(i<aa.size()-1)
				bb.append(delim);
		}

		return bb.toString();
	}
	public static final String 			getContentRiFtpPack(String [] riRel,
			StringBuilder winS,
			String done_by,
			String action,
			String[] cbgroup1,
			String instFromFtp,
			String[] cbgroup2,
			String appendToMail,
			String to,
			String toCc	) {

		boolean error = false;
		StringBuilder errorStr = new StringBuilder("<html><body>ERROR:<br>");	
		String to1 = to.replaceAll(";","<br>");
		String cc1 = toCc.replaceAll(";","<br>");
		String Link_to_cfgMenuWeb = "http://cfg-ri:8080/hagay/indexRiFtpPack"+riRel[0]+".html";
		setContentRiFtpPackWin(winS,	cbgroup1, cbgroup2,instFromFtp);


		//String rel = riRel[0]+"M"+riRel[1]+"U"+riRel[2]+"HF"+riRel[3];
		String[][] vals = 	{
				{"Version",				null,	null, 		riRel[0]			,"red",	"yellow"	},
				{"Maintenance",			null,	null, 		riRel[1]			,"red",	"yellow"	},
				{"Update",				null,	null, 		riRel[2]			,"red",	"yellow"	},
				{"Hotfix",				null,	null, 		"."					,"red",	"yellow"	},
				{"Done by",				null,	null, 		done_by				,null,	null		},
				{"Step",				null,	null, 		action				,"red",	"yellow"	},
				{"Description",			null,	null, 		appendToMail		,null,	null		},
				{"Link to cfgMenuWeb",	null,	null, 		Link_to_cfgMenuWeb	,null,	null		},
				{"Win",					null,	null, 		winS.toString()		,null,	null		},
				{"Sent",				null,	null, 		to1					,null,	null		},
				{"cc",					null,	null, 		cc1					,null,	null		}
		};
		if(riRel.length==4)
			vals[3][3]=riRel[3];

		StringBuilder contentS = getAsTable(vals,"PaleGreen");
		if(error){
			return errorStr.toString();
		}
		return contentS.toString();
	}
	public static final String 			getContentBisAdd(String version,String add,String add_description,String step,
			String modulesList,String done_by,String appendToMail,String Link_to_cfgMenuWeb,
			String to,String cc) {
		String to1 = to.replaceAll(";","<br>");
		String cc1 = cc.replaceAll(";","<br>");
		String appendToMail1 = appendToMail.replaceAll("\n","<br>");

		String[][] vals = 	{
				{"Version",				null,	null, 		version				,"red",	"yellow"	},
				{"Add",					null,	null, 		add					,"red",	"yellow"	},
				{"Description",			null,	null, 		add_description		,"red",	"yellow"	},
				{"Step",				null,	null, 		step				,null,	null		},
				{"Modules",				null,	null, 		modulesList			,null,	null		},
				{"Done by",				null,	null, 		done_by				,null,	null		},
				{"Link to cfgMenuWeb",	null,	null, 		Link_to_cfgMenuWeb	,null,	null		},
				{"Appended to mail",	null,	null, 		appendToMail1		,null,	null		},
				{"Sent to",				null,	null, 		to1					,null,	null		},
				{"cc",					null,	null, 		cc1					,null,	null		}
		};


		StringBuilder contentS = getAsTable(vals,"PaleGreen");
		return contentS.toString();

	}
	public static final StringBuilder	getAsTable(String [][] vals,String bgColor){
		StringBuilder contentS= new StringBuilder("<table style=\"background-color: ")
		.append(bgColor).append("\" border=\"1\" CELLPADDING=4 >");

		for(int i = 0 ; i < vals.length;i++){
			StringBuilder extra1 =new StringBuilder("");
			if(vals[i][1]!=null || vals[i][2]!=null ){
				extra1.append(" style=\"");
				if(vals[i][1]!=null ){
					extra1.append("color: ").append(vals[i][1]).append(";");
				}
				if(vals[i][2]!=null ){
					extra1.append("background-color: ").append(vals[i][2]).append(";");
				}
				extra1.append("\" ");	
			}
			StringBuilder extra2 =new StringBuilder("");
			if(vals[i][4]!=null || vals[i][5]!=null ){
				extra2.append(" style=\"");
				if(vals[i][4]!=null ){
					extra2.append("color: ").append(vals[i][4]).append(";");
				}
				if(vals[i][5]!=null ){
					extra2.append("background-color: ").append(vals[i][5]).append(";");
				}
				extra2.append("\" ");	
			}

			contentS.append("<tr><td").append(extra1).append(">");
			contentS.append(vals[i][0]);
			contentS.append("</td><td").append(extra2).append(">");
			contentS.append(vals[i][3]);
			contentS.append("</td></tr>");

		}
		contentS.append("</table>");
		return contentS;
	}
	public static final String 			getRequestedAddDescription(String eMergeVer,String add) {
		String lastAddDesc = "&nbsp;";
		String file = cfgMenuWebLoc+"\\AccumulativeAddLogs"+eMergeVer+".html";
		HagStringList iframeLine = new HagStringList(file,false,"asasasa",false);
		for (int i = 0; i < iframeLine.size(); i++) {
			String temp = iframeLine.get(i);
			if (temp.indexOf("<td>" + add + "</td>") > 0 && temp.indexOf("<td>Request to pack add</td>") > 0) {
				String temp1 = temp.substring(temp.lastIndexOf("<td>") + 4, temp.lastIndexOf("</td>"));
				return temp1;
			}
		}
		return lastAddDesc;
	}
	public static final void 			getMailList(String eMergeVer,String add,String step,String add_description,String[] ans){
		if (step.equals("Request to pack add")) {
			ans[0] = "david.ha@sapiens.com;gonen.s@sapiens.com;david.b@sapiens.com";
			ans[1] = "#FFBBBB";
			ans[2] = add_description;
		} else if (step.equals("Cancel a request")) {
			ans[0] = "david.ha@sapiens.com;gonen.s@sapiens.com;david.b@sapiens.com";
			ans[1] = "#FFCE73";
			ans[2] = getRequestedAddDescription(eMergeVer,add);
		} else if (step.equals("Add packed")) {
			ans[0] = "david.ha@sapiens.com;gonen.s@sapiens.com;yael.b@sapiens.com;david.b@sapiens.com";
			ans[1] = "#8CD1E6";
			ans[2] = getRequestedAddDescription(eMergeVer,add);
		} else if (step.equals("Add approved by QA")) {
			ans[0] = "david.ha@sapiens.com;gonen.s@sapiens.com;ronen.s@sapiens.com";
			ans[1] = "#C0FF97";
			ans[2] = getRequestedAddDescription(eMergeVer,add);
		} else if (step.equals("Add released to customer")) {
			ans[0] = "david.ha@sapiens.com;gonen.s@sapiens.com;yael.b@sapiens.com";
			ans[1] = "#FFFF99";
			ans[2] = getRequestedAddDescription(eMergeVer,add);
		}
	}
	public static final String  		getDateTime1111111111111111111(String format){
		Date date = new Date();
		TimeZone tz = TimeZone.getTimeZone("Asia/Jerusalem");
		SimpleDateFormat logFolder  = new SimpleDateFormat(format);
		logFolder.setTimeZone(tz);	
		String dateStr = logFolder.format(date);
		return dateStr;
	}	
	public static final String 			getDateTime(String format){
		Date date = new Date();
	//	TimeZone tz = TimeZone.getTimeZone("Asia/Jerusalem");
		SimpleDateFormat logFolder  = new SimpleDateFormat(format);
	//	logFolder.setTimeZone(tz);	
		String dateStr = logFolder.format(date);
		return dateStr;
	}
	public static final String 			getPropertyVal(String file,String var){
		Properties prop1 = new Properties();
		//HagUtil.hagDebugToFile("d:\\temp\\1.txt", "201" );
		try {
			//	HagFinalDebug.p2(file);
			//	HagFinalDebug.p2(var);
			//HagUtil.hagDebugToFile("d:\\temp\\1.txt", "202" );
			//HagUtil.hagDebugToFile("d:\\temp\\1.txt", file );
			//HagUtil.hagDebugToFile("d:\\temp\\1.txt", var );
			prop1.load(new FileInputStream(file));
			String dd1 = prop1.getProperty(var);
			//HagUtil.hagDebugToFile("d:\\temp\\1.txt", dd1 );
			if(dd1==null)
				return null;
			//HagUtil.hagDebugToFile("d:\\temp\\1.txt", "203" );
			String dd = dd1.trim();
			//HagUtil.hagDebugToFile("d:\\temp\\1.txt",dd );
			return dd;
		} catch (IOException ex) {
			//HagUtil.hagDebugToFile("d:\\temp\\1.txt",ex.getMessage() );
			ex.printStackTrace();
			return null;
		}
	}
	public static final void 			getFilesInDir(File file, Collection<File> all, ArrayList<String> ll,int dirLen) {
		File[] children = file.listFiles();
		
		
		if (children != null) {
		//
			Arrays.sort(children, new Comparator<File>(){
			    public int compare(File f1, File f2)
			    {
			        return Long.valueOf(f2.lastModified()).compareTo(f1.lastModified());
			    } 
			});
		
			//
			for (File child : children) {
				all.add(child);
				String childStr = child.toString();
				ll.add(childStr.substring(dirLen,childStr.length()));
				getFilesInDir(child, all,ll,dirLen);
			}
		}
	}
	public static final HagStringList 	getFilesInDir(String dir) {
		dir= dir.trim();
		while(dir.charAt(dir.length()-1) =='\\')
			dir=dir.substring(0,dir.length()-1);	
		int dirLen = dir.length();
		Collection<File> all = new ArrayList<File>();
		HagStringList ll = new HagStringList();
		getFilesInDir(new File(dir), all,ll,dirLen);
		//for(int i = 0 ; i < ll.size();i++){
		return ll;
	}
	public static final String 			getRiMails(String jiraVer) {
		if(jiraVer.startsWith("09.80"))
			return "david.ha@sapiens.com;gonen.s@sapiens.com";
		String ff = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\list\\riMail1.list";
		HagStringList list = new HagStringList(ff,true,"*",false);
		String list1 = list.convertToString(";");
		return list1;
	}
	public static final HagStringList 	getFilesInDirOneLevel(String dir) {
		HagStringList list = new HagStringList();
		dir= dir.trim();
		while(dir.charAt(dir.length()-1) =='\\')
			dir=dir.substring(0,dir.length()-1);	
		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				list.add(listOfFiles[i].getName());
				//	      } else if (listOfFiles[i].isDirectory()) {
				//		        System.out.println("Directory " + listOfFiles[i].getName());
				//		      }
			}
		}
		return list;
	}
	public static final HagStringList 	getFoldersInDirOneLevel(String dir) {
		HagStringList list = new HagStringList();
		dir= dir.trim();
		while(dir.charAt(dir.length()-1) =='\\')
			dir=dir.substring(0,dir.length()-1);	
		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles();
		if(listOfFiles==null) 
			return  new HagStringList();
		
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isDirectory()) {
				list.add(listOfFiles[i].getName());
			
			}
		}
		return list;
		
	}
	public static final HagStringList 	getAuth(){
//		String ff = cfgMenuWebLoc+"\\temp.txt";
		//String ff = cfgMenuWebLoc+"\\lists\\users.txt";
		//HagStringList aaa = new HagStringList(ff,false,"xxssss",false);
		//String temp = aaa.get(0);
		//HagStringList bbb = new HagStringList(temp,",",true);
		//return bbb;
		
		HagSQL hagSQL = new HagSQL();
		String stm = "select * from dbo.hagCfgMenuWebAuth";
		HagStringList authsList =hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,2,"|",null,null);
		return authsList;
	
	}
	public static final HagStringList 	getUsers(){
		HagSQL hagSQL = new HagSQL();
		String stm = "select * from dbo.hagCfgMenuWebUsers";
		HagStringList usersList =hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,6,"|",null,null);
		return usersList;
		/*
		//String ff = cfgMenuWebLoc+"\\temp.txt";
		String ff = cfgMenuWebLoc+"\\lists\\users.txt";
		HagStringList aaa = new HagStringList(ff,true,"*",false);
		HagStringList bbb = new HagStringList();
		for(int i = 1 ;i <aaa.size();i++){
			String temp = aaa.get(i);
			String w1=getWord0(temp,"~",1,true);
			//String w1=getWord0(temp,"~",0,true);
			bbb.add(w1);
		}
		return bbb;
		*/
	}
	public static final HagStringList 	getUsersAndPass(){
		HagSQL hagSQL = new HagSQL();
		String stm = "select name1,pass1 from dbo.hagCfgMenuWebUsers";
		HagStringList usersList =hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,2,"~",null,null);
		return usersList;
	
		/*
//		String ff = cfgMenuWebLoc+"\\temp.txt";
		String ff = cfgMenuWebLoc+"\\lists\\users.txt";
		HagStringList aaa = new HagStringList(ff,true,"*",false);
		HagStringList bbb = new HagStringList();
		for(int i = 1 ;i <aaa.size();i++){
			String temp = aaa.get(i);
			//String w1=getWord0(temp,"~",0,true);
			//String w2=getWord0(temp,"~",1,true);
			String w1=getWord0(temp,"~",1,true);
			String w2=getWord0(temp,"~",2,true);
			bbb.add(w1+"~"+w2);
		}
		return bbb;
		*/
		
	}
	/*
	public static final String 			getPrivateDb(String user){
			String ff = cfgMenuWebLoc+"\\lists\\users.txt";
			HagStringList aaa = new HagStringList(ff,true,"*",false);
			for(int i = 1 ; i < aaa.size();i++){
				String temp = aaa.get(i);
				String w0 = HagUtil.getWord0(temp, "~",0,true);
				String w1 = HagUtil.getWord0(temp, "~",1,true);
				
				if(w1.equals(user)){
					return w0;
				}
			}
			return user+" user not found";
		}
		*/
	public static final String 			getTempJarFile(String dirStr, String prefix) {
		File dir = new File(dirStr);
		if (!dir.exists()) 
			return "1~"+ dirStr + " folder not found.";
		if (!dir.isDirectory()) 
			return "1~"+ dirStr + " is not a directory.";

		String[] children = dir.list();
		if (children.length < 1) 
			return "1~"+dirStr + " directory is empty.";
		
		HagStringList filesList = new HagStringList();
		for (int i = 0; i < children.length; i++) {
			String temp = children[i].toLowerCase();
			if (temp.startsWith(prefix.toLowerCase()))
				filesList.add(children[i]);
		}
		if (filesList.size() < 1) 
			return "1~No files starting with\n" + prefix + "\nfound in\n" + dirStr + " directory.";
		if (filesList.size() > 1) 
			return "1~More then one file starting with\n" + prefix + "\nfound in\n" + dirStr + " directory.";
		if (filesList.size() == 1) {
			return "0~"+filesList.get(0);
		}
		return "1~problem 10002";

	}
	public static final String 			getJiraVer(String ver, String patch) { 
		if(patch.equalsIgnoreCase("m00")){
			HagRiRel riRel = new HagRiRel(ver);
			return riRel.getRiVerJira();
		}
		HagRiRel riRel = new HagRiRel(ver+(patch.substring(0,3)).toUpperCase());
		return riRel.getRiVerJira();
	
	}
	public static final String 			getVersionTxtVal(String var,int pos){ 
		HagStringList ver1= new HagStringList(cfgMenuWebLoc+"\\lists\\versions.txt",true,"*",false);
		for(int i=0;i<ver1.size();i++){
			String temp = ver1.get(i).trim();
			String w1=getWord0(temp,"|",1,true);
			if (w1.equals(var))
				return getWord0(temp,"|",pos,true);
		}
		return null;
	}
	public static final String 			getParameters(String[] list){
		if(list[0].equals("Replace jar"))			return list[2];
		else if(list[0].equals("Replace war")) 		return ".";
		else if(list[0].equals("Run_manual_mig"))	return list[2]+","+list[4]+","+list[6];
		else if(list[0].equals("Ct-Lex")) 			return ".";
		else if(list[0].equals("Db-Lex"))			return ".";
		else if(list[0].equals("Copy migMng.jar")) 	return ".";
		else if(list[0].equals("Replace jasper"))	return ".";
		else if(list[0].equals("Run sql stm")) 		return list[2];
		else if(list[0].equals("Replace ddc"))		return list[2]+","+list[4];
		else if(list[0].equals("Run journal")) 		return ".";
		else if(list[0].equals("Run tran"))			return list[2];

		return "error 1002";
	}
	public static final int 			getRequestInd(){
		String stm = "select ind  from dbo.req_ind where type=0";
		HagSQL hagSQL=new HagSQL();			
		ArrayList<String> ans = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm);
		int ind = HagUtil.s2i(ans.get(0));
		int nextInd = ind+1;
		String stm1 = "update dbo.req_ind set ind ="+nextInd+" where type=0";
		int changed = hagSQL.updateStm("confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm1);
		return nextInd;
	}
	public static final String 	getCustomerByParty11111111111111(String party){
		party=party.trim();
		if(party.equals("310000")){
			return "AXA";
		}else if(party.equals("270000")){
			return "DMO";
		}else if(party.equals("230000")){
			return "HOL";
		}else if(party.equals("320000")){
			return "IAT";
		}else if(party.equals("290000")){
			return "ING";
		}else if(party.equals("260000")){
			return "LB";
		}else if(party.equals("300000")){
			return "MNR";
		}else if(party.equals("250000")){
			return "RS";
		}else if(party.equals("240000")){
			return "RSA";
		}else if(party.equals("280000")){
			return "TMM";
		}else if(party.equals("210000")){
			return "GIC";
		}else if(party.equals("200000")){
			return "C&F";
		}else if(party.equals("220000")){
			return "WB";
		}else if(party.equals("190000")){
			return "RML";
		}else if(party.equals("180000")){
			return "GRD";
		}else if(party.equals("170000")){
			return "CHB";
		}else if(party.equals("160000")){
			return "AFI";
		}else if(party.equals("150000")){
			return "ECC";
		}else if(party.equals("140000")){
			return "LMI";
		}else if(party.equals("330000")){
			return "GEB";
		}else if(party.equals("340000")){
			return "FRM";
		}else if(party.equals("350000")){
			return "ITG";
		}else if(party.equals("360000")){
			return "AIG";
		}else if(party.equals("370000")){
			return "CRO";
		}else if(party.equals("380000")){
			return "OMI";
		}else if(party.equals("390000")){
			return "FID";
		}else if(party.equals("400000")){
			return "BKI";
		}else if(party.equals("410000")){
			return "PRD";
		}else if(party.equals("420000")){
			return "AIC";
		}else if(party.equals("430000")){
			return "PUR";
		}else if(party.equals("440000")) {
			return "TRV";
		}
		return party;
	}
	public static final String 	getCustomerByPartyLong(String party){
		party=party.trim();
		if(party.equals("310000")){
			return "AXA";
		}else if(party.equals("430000")){
			return "PUR";
		}else if(party.equals("420000")){
			return "AIC";
		}else if(party.equals("410000")){
			return "PRD";
		}else if(party.equals("400000")){
			return "BKI";
		}else if(party.equals("390000")){
			return "FID";
		}else if(party.equals("380000")){
			return "OMI";
		}else if(party.equals("370000")){
			return "CRO";
		}else if(party.equals("360000")){
			return "AIG";
		}else if(party.equals("350000")){
			return "IFFCO Tokio General Insurance Company Limited";
		}else if(party.equals("340000")){
			return "Farmers";
		}else if(party.equals("330000")){
			return "Generali";
		}else if(party.equals("320000")){
			return "IAT";
		}else if(party.equals("300000")){
			return "Menorah";
		}else if(party.equals("290000")){
			return "ING";
		}else if(party.equals("280000")){
			return "TMM";
		}else if(party.equals("270000")){
			return "Demo";
		}else if(party.equals("260000")){
			return "LB";
		}else if(party.equals("250000")){
			return "River stone";
		}else if(party.equals("240000")){
			return "RSA";
		}else if(party.equals("230000")){
			return "Hollard";
		}else if(party.equals("220000")){
			return "West Bend";
		}else if(party.equals("210000")){
			return "GIC";
		}else if(party.equals("200000")){
			return "Crum & Forster";
		}else if(party.equals("190000")){
			return "RML";
		}else if(party.equals("180000")){
			return "Gard";
		}else if(party.equals("170000")){
			return "Chubb";
		}else if(party.equals("160000")){
			return "AFI";
		}else if(party.equals("150000")){
			return "Ecclesiastical";
		}else if(party.equals("140000")){
			return "Liberty";
		}else if(party.equals("130000")){
			return "Warta";
		}else if(party.equals("120000")){
			return "Navigatores";
		}else if(party.equals("110000")){
			return "MSIGEU";
		}else if(party.equals("440000")) {
			return "TRV";
		}
		return party;
	}
	public static final String 	getCustomerByPartyShort(String party){
		party=party.trim();
		if(party.equals("310000")){
			return "AXA";
		}else if(party.equals("430000")){
			return "PUR";
		}else if(party.equals("420000")){
			return "AIC";
		}else if(party.equals("410000")){
			return "PRD";
		}else if(party.equals("400000")){
			return "BKI";
		}else if(party.equals("390000")){
			return "FID";
		}else if(party.equals("380000")){
			return "OMI";
		}else if(party.equals("370000")){
			return "CRO";
		}else if(party.equals("360000")){
			return "AIG";
		}else if(party.equals("350000")){
			return "ITG";
		}else if(party.equals("340000")){
			return "FRM";
		}else if(party.equals("330000")){
			return "GEB";
		}else if(party.equals("320000")){
			return "IAT";
		}else if(party.equals("300000")){
			return "MNR";
		}else if(party.equals("290000")){
			return "ING";
		}else if(party.equals("280000")){
			return "TMM";
		}else if(party.equals("270000")){
			return "DMO";
		}else if(party.equals("260000")){
			return "LB";
		}else if(party.equals("250000")){
			return "RS";
		}else if(party.equals("240000")){
			return "RSA";
		}else if(party.equals("230000")){
			return "HOL";
		}else if(party.equals("220000")){
			return "WB";
		}else if(party.equals("210000")){
			return "GIC";
		}else if(party.equals("200000")){
			return "CF";
		}else if(party.equals("190000")){
			return "RML";
		}else if(party.equals("180000")){
			return "GRD";
		}else if(party.equals("170000")){
			return "CHB";
		}else if(party.equals("160000")){
			return "AFI";
		}else if(party.equals("150000")){
			return "ECC";
		}else if(party.equals("140000")){
			return "LMI";
		}else if(party.equals("130000")){
			return "WRT";
		}else if(party.equals("120000")){
			return "NAV";
		}else if(party.equals("110000")){
			return "MSG";
		}else if(party.equals("440000")) {
			return "TRV";
		}
		return party;
	}
	public static final long getFolderSize(File folder) {
	    long length = 0;
	    File[] files = folder.listFiles();
	 
	    int count = files.length;
	 
	    for (int i = 0; i < count; i++) {
	        if (files[i].isFile()) {
	            length += files[i].length();
	        }
	        else {
	            length += getFolderSize(files[i]);
	        }
	    }
	    return length;
	}
	public static final String getFileSize(String fileS,char type) {
		File file = new File(fileS);
		double bytes = file.length(); //b
		double kilobytes = (bytes / 1024); //k
		double megabytes = (kilobytes / 1024); //m
		double gigabytes = (megabytes / 1024); //g

		switch (type) {
			case 'b': return new DecimalFormat("##.##").format(bytes)+" b";
			case 'k': return new DecimalFormat("##.##").format(kilobytes)+" kb";
			case 'm': return new DecimalFormat("##.##").format(megabytes)+" mb";
			case 'g': return new DecimalFormat("##.##").format(gigabytes)+" gb";
		}
	    return "wrong type";
	}
	
	public static final int getErrorLevel(HagStringList list){
		int ans = 0;
		String file = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\list\\errors.list";
		HagStringList errorsSkel = new HagStringList(file,true,"*",false);
		for(int i =0;i<list.size();i++) {
			String temp=list.get(i);
			int ans1 = getErrorLevel(temp,errorsSkel);
			if(ans!=0)
				return ans;
			
		}
		return 0;
	}
	public static final int getErrorLevel(String str,HagStringList errorsSkel){
		//String file = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\list\\errors.list";
		//HagStringList errorsSkel = new HagStringList(file,true,"*",false);
		if(	str.startsWith("1~") ||	
			str.startsWith("2~") ||	
			str.startsWith("3~") ||
			str.startsWith("4~") ||
			str.startsWith("5~") ||
			str.startsWith("6~") ||
			str.startsWith("7~") ||
			str.startsWith("8~") ||
			str.startsWith("9~") ||
			str.startsWith("10~") ||
			str.startsWith("11~") ||
			str.startsWith("12~") ||
			str.startsWith("13~") ||
			str.startsWith("14~") ||
			str.startsWith("15~") ||
			str.startsWith("16~") 		) {
			return 3;
		}
		for(int i = 0 ; i <errorsSkel.size();i++ ){
			String temp =  errorsSkel.get(i);
			String errorLevel  = temp.substring(0,1);
			String lookAt = temp.substring(1,2);
			String msg =  temp.substring(3,temp.length()).trim();
						
			if(lookAt.equalsIgnoreCase("S")){
				if(str.startsWith(msg)){
					return HagUtil.s2i(errorLevel);
				}
			}else if(lookAt.equalsIgnoreCase("I")){
				if(str.indexOf(msg) > -1){
					return HagUtil.s2i(errorLevel);
				}
			}	
		}
		return 0;
	}	
	
	public static final String getSqlLocation(String sqlServer, int pos){
		ArrayList<String> lines = HagUtil.loadFileIntoArrayList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\list\\allServers.list", true, "*", false);
		String ans = findKeyLineInArrayList(lines, sqlServer.trim(), 0, "|");
		if(ans.equals("NOTFOUND"))
			return "???";
		return getWord0(ans, "|", pos, true);
	
	}
	public static final boolean isIE(HttpServletRequest request) {
		 String[] ans=getOsAndBrowser( request);
		 if(ans[1].startsWith("IE-"))
			 return true;
		 else
			 return true; 
	}
	public static final String[] getOsAndBrowser(HttpServletRequest request) {
		String[] ans = {"INIT","INIT","INIT","INIT"};
		String  browserDetails  =   request.getHeader("User-Agent");
        String  userAgent       =   browserDetails;
        String  user            =   userAgent.toLowerCase();

        String os = "";
        String browser = "";

     //   log.info("User Agent for the request is===>"+browserDetails);
        //=================OS=======================
         if (userAgent.toLowerCase().indexOf("windows") >= 0 )
         {
             os = "Windows";
         } else if(userAgent.toLowerCase().indexOf("mac") >= 0)
         {
             os = "Mac";
         } else if(userAgent.toLowerCase().indexOf("x11") >= 0)
         {
             os = "Unix";
         } else if(userAgent.toLowerCase().indexOf("android") >= 0)
         {
             os = "Android";
         } else if(userAgent.toLowerCase().indexOf("iphone") >= 0)
         {
             os = "IPhone";
         }else{
             os = "UnKnown, More-Info: "+userAgent;
         }
         //===============Browser===========================
        if (user.contains("msie"))
        {
            String substring=userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
            browser=substring.split(" ")[0].replace("MSIE", "IE")+"-"+substring.split(" ")[1];
        } else if (user.contains("safari") && user.contains("version"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
        } else if ( user.contains("opr") || user.contains("opera"))
        {
            if(user.contains("opera"))
                browser=(userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
            else if(user.contains("opr"))
                browser=((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
        } else if (user.contains("chrome"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)  || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1) )
        {
            //browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
            browser = "Netscape-?";

        } else if (user.contains("firefox"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if(user.contains("rv"))
        {
            browser="IE-" + user.substring(user.indexOf("rv") + 3, user.indexOf(")"));
        } else
        {
            browser = "UnKnown, More-Info: "+userAgent;
        }
        ans[0]=os;
        ans[1]=browser;
        ans[2]=userAgent;
        ans[3]=user;
        return ans;
      //  log.info("Operating System======>"+os);
     //   log.info("Browser Name==========>"+browser);
	}
	
	public static int 	getPreferdId(String idS){
		int id = HagUtil.s2i(idS);
		if(id==12330)
			return 9;
		if(id==15718)
			return 9;
		if(id==12972)
			return 5;
		
		if(id<12964)
			return 5;
		else
			return 6;
	}
	//H
	public static final boolean deleteDir(File dir, JPanel panel,boolean gui) {
		//boolean flag = true;
        if (dir.isDirectory()) {
            String[] children = dir.list();
            if(children.length > 0){
            	for (int i=0; i<children.length; i++) {
            		boolean success = deleteDir(new File(dir, children[i]),panel,gui);
            		if (!success) {
            			if(gui){
            				JOptionPane.showMessageDialog(panel,
    			 	  	       "Can't delete "+dir+"\\"+children[i], 
    						   "I/O Error",
    				  	       JOptionPane.ERROR_MESSAGE);
            			}
            		//	flag = false;;
            		}
            	}
            }
	 	}
        
        boolean rc = dir.delete();
        if(rc == false)
        	if(gui){
       		JOptionPane.showMessageDialog(panel,
	 	  	       "Can't delete "+dir, 
				   "I/O Error",
		  	       JOptionPane.ERROR_MESSAGE);
        	}
        return rc;
	}
	public static final String deleteFolderTree(String folder,boolean mustExist){
		File ff = new File(folder);
		if(!ff.exists() && mustExist)
			return "1~"+folder+" folder not found.";
		if(!ff.exists())
			return "0~"+folder+" folder not found.";
		if(deleteDir(new File(folder),null,true))
			return "0~"+folder+" deleted.";
		else
			return "1~Failed to delete "+folder+" folder.";
	
	}
	public static final String 			hagXcopy(String from,String to,boolean totalCommanderPreBox,boolean gui,String errorLevel){
		// error level:
		//       robocopy  0: only rc
		//				   1: rc+job summary.
		//                 2: rc+job summary+fileList
		//                 3: all
		from=from.trim();
		to=to.trim();
	//	if(from.trim().indexOf("*") > -1)
	//		return "1~please call hagay-2831 (xcopy *)";
		
		if(from.length()<2)
			return "0~no file to copy";
		
		String last =from.substring(from.lastIndexOf("\\")+1,from.length());
		if(last.indexOf("*") < 0 && last.indexOf("?") < 0 ){
			//folder or specific file
			File fromF = new File(from);
			if(!fromF.exists()){
			//	HagUtil.p("2source path not found. "+fromF);
				return "1~source not found. "+fromF;
			}
		}else{
			String path =from.substring(0,from.lastIndexOf("\\"));
			File fromP = new File(path);
			if(!fromP.exists() || !fromP.isDirectory()){
			//	HagUtil.p("1source path not found. "+fromP);
				return "1~source path not found. "+fromP;
			}
		}
		///
		String os = System.getProperty("os.name");
		String ans = "wrong OS";
		if( os.equalsIgnoreCase("Windows 2000") ||
			os.equalsIgnoreCase("Windows 2003") ||
			os.equalsIgnoreCase("Windows XP") ){
				StringBuilder cmd = new StringBuilder("javaFunc~simpleDosCmd~xcopy~").append(from).append("~").append(to).append("~/E~/I~/K~/Y");
				ans = simpleDosCmd(cmd.toString(),gui,"java",false);
		}else{
			//if(from.trim().indexOf(" ") > -1 || to.trim().indexOf(" ") > -1){
			//	HagUtil.dspMsgBox("please call hagay-2831<newLine>robocopy<newLine>"+from+"<newLine>"+to,JOptionPane.ERROR_MESSAGE ,null,null);
			//	return "1~robocopy "+from+" "+to;
			//}
			//from=from.replace('"',' ');
			//to=to.replace('"',' ');
		
			
			from=from.trim();
			to=to.trim();
			File ff = new File(from);
			String noDsp = "~/njh~/njs~/np~/ndl~/nfl~/nc~/ns";
			if(errorLevel.equals("1"))
				noDsp = "~/njh~/np~/ndl~/nfl~/nc~/ns";
			else if(errorLevel.equals("2"))
				noDsp = "~/njh~/np~/nc~/ns";
			else if(errorLevel.equals("3"))
				noDsp = "";
			StringBuilder cmd = new StringBuilder("javaFunc~simpleDosCmd~robocopy~").append(from).append("~").append(to).append("~/E").append(noDsp);
			if(!ff.isDirectory()){
				int pos = from.lastIndexOf("\\");
				if(pos<0){
					//HagUtil.dspMsgBox("please call hagay-2831<newLine>robocopy<newLine>"+from+"<newLine>"+to,JOptionPane.ERROR_MESSAGE ,null,null);
					return "2~robocopy "+from+" "+to;
				}
				String ddd = from.substring(0,pos);
				String fff = from.substring(pos+1,from.length());
			
				
				cmd = new StringBuilder("javaFunc~simpleDosCmd~robocopy~").append(ddd).append("~").append(to).append("~").append(fff).append("~/E").append(noDsp);
			}
				
			ans = simpleDosCmd(cmd.toString(),gui,"java",false);
		}
		if(totalCommanderPreBox)
			JOptionPane.showMessageDialog(null,
	 	             "Please check/rename via total commander\nwhen done\nclose total commander to continue. " , "XCopy",
		             JOptionPane.INFORMATION_MESSAGE);
		return ans;
	}
	
	public static final String 			hagXcopyD(String from,String to,boolean totalCommanderPreBox,boolean gui,String errorLevel){
		// error level:
		//       robocopy  0: only rc
		//				   1: rc+job summary.
		//                 2: rc+job summary+fileList
		//                 3: all
		from=from.trim();
		to=to.trim();
	//	if(from.trim().indexOf("*") > -1)
	//		return "1~please call hagay-2831 (xcopy *)";
		String delTgt = HagUtil.deleteFolderTree(to, false);
		if(!delTgt.startsWith("0~"))
			return "1~failed to delete "+to;
		if(from.length()<2)
			return "0~no file to copy";
		
		String last =from.substring(from.lastIndexOf("\\")+1,from.length());
		if(last.indexOf("*") < 0 && last.indexOf("?") < 0 ){
			//folder or specific file
			File fromF = new File(from);
			if(!fromF.exists()){
			//	HagUtil.p("2source path not found. "+fromF);
				return "1~source not found. "+fromF;
			}
		}else{
			String path =from.substring(0,from.lastIndexOf("\\"));
			File fromP = new File(path);
			if(!fromP.exists() || !fromP.isDirectory()){
			//	HagUtil.p("1source path not found. "+fromP);
				return "1~source path not found. "+fromP;
			}
		}
		///
		String os = System.getProperty("os.name");
		String ans = "wrong OS";
		if( os.equalsIgnoreCase("Windows 2000") ||
			os.equalsIgnoreCase("Windows 2003") ||
			os.equalsIgnoreCase("Windows XP") ){
				StringBuilder cmd = new StringBuilder("javaFunc~simpleDosCmd~xcopy~").append(from).append("~").append(to).append("~/E~/I~/K~/Y");
				ans = simpleDosCmd(cmd.toString(),gui,"java",false);
		}else{
			//if(from.trim().indexOf(" ") > -1 || to.trim().indexOf(" ") > -1){
			//	HagUtil.dspMsgBox("please call hagay-2831<newLine>robocopy<newLine>"+from+"<newLine>"+to,JOptionPane.ERROR_MESSAGE ,null,null);
			//	return "1~robocopy "+from+" "+to;
			//}
			//from=from.replace('"',' ');
			//to=to.replace('"',' ');
		
			
			from=from.trim();
			to=to.trim();
			File ff = new File(from);
			String noDsp = "~/njh~/njs~/np~/ndl~/nfl~/nc~/ns";
			if(errorLevel.equals("1"))
				noDsp = "~/njh~/np~/ndl~/nfl~/nc~/ns";
			else if(errorLevel.equals("2"))
				noDsp = "~/njh~/np~/nc~/ns";
			else if(errorLevel.equals("3"))
				noDsp = "";
			StringBuilder cmd = new StringBuilder("javaFunc~simpleDosCmd~robocopy~").append(from).append("~").append(to).append("~/E").append(noDsp);
			if(!ff.isDirectory()){
				int pos = from.lastIndexOf("\\");
				if(pos<0){
					//HagUtil.dspMsgBox("please call hagay-2831<newLine>robocopy<newLine>"+from+"<newLine>"+to,JOptionPane.ERROR_MESSAGE ,null,null);
					return "2~robocopy "+from+" "+to;
				}
				String ddd = from.substring(0,pos);
				String fff = from.substring(pos+1,from.length());
			
				
				cmd = new StringBuilder("javaFunc~simpleDosCmd~robocopy~").append(ddd).append("~").append(to).append("~").append(fff).append("~/E").append(noDsp);
			}
				
			ans = simpleDosCmd(cmd.toString(),gui,"java",false);
		}
		if(totalCommanderPreBox)
			JOptionPane.showMessageDialog(null,
	 	             "Please check/rename via total commander\nwhen done\nclose total commander to continue. " , "XCopy",
		             JOptionPane.INFORMATION_MESSAGE);
		return ans;
	}
	
	//I
	public static final boolean 		isCmLockI5os(String server2,String dbid2) {
		String winRiInstFile = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\list\\winRiInst.list";
		//	ArrayList<String> winRiInstList = HagUtil.loadFileIntoArrayList(winRiInstFile,true,"*",true);
		HagStringList winRiInstList = new HagStringList(winRiInstFile,true,"*",false);
		for(int i = 0 ; i < winRiInstList.size();i++){
			String temp = winRiInstList.get(i);
			String w1 = HagUtil.getWord0(temp, "~",1,true);
			String w2 = HagUtil.getWord0(temp, "~",2,true);
			String w5 = HagUtil.getWord0(temp, "~",5,true);
			if(w1.equalsIgnoreCase(server2) && w2.equalsIgnoreCase(dbid2)){
				if(w5.equals("LOCK")){
					return true;
				}else{
					return false;
				}
			}
		}
		return false;
	}
	public static final boolean 		isCmLock(String server2,String dbid2) {
		
		String stm = "SELECT locks from dbo.ri_environments_new WHERE  status='A' and  bis_server='"+server2+"' and batchName='"+dbid2+"'";
		HagSQL hagSQL = new HagSQL();
		HagStringList ans = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm);
		if(ans==null || ans.size()!=1 )
				return true;		
		String flag = ans.get(0);
		if(flag==null )
			return true;
		if(flag.trim().equals("free"))
			return false;
		
		return true;
	}
	public static final String 		setCmLock(String server2,String dbid2,String val) {
		StringBuilder stm3= new StringBuilder("UPDATE "+HagParam.getConfg1DB()+".[dbo].[ri_environments_new] SET locks='"+val+"' where bis_server='"+server2+"' and batchName='"+dbid2+"' ");
		
		String url="jdbc:sqlserver://CONFG1;Database="+HagParam.getConfg1DB();
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		Connection con = null;
		String msg="2~init";
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, "cfg", "cfg09c");
			con.createStatement().execute(stm3.toString());
		 	con.commit();
		 	msg= "0~locks value is now "+val;
		} catch (ClassNotFoundException e) {
			//sendMail_hag(cfgTeamMail,"david.ha@sapiens.com",null,"error1:"+e.getMessage(),null);
			msg= "1~failed to set locks value to "+val+"-"+e.getMessage();
		} catch (SQLException e) {
			//sendMail_hag(cfgTeamMail,"david.ha@sapiens.com",null,"error2:"+e.getMessage(),null);
			msg= "1~failed to set locks value to "+val+"-"+e.getMessage();
		} catch (Exception e) {
			//sendMail_hag(cfgTeamMail,"david.ha@sapiens.com",null,"error3:"+e.getMessage(),null);
			msg= "1~failed to set locks value to "+val+"-"+e.getMessage();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return msg;
	}
	public static final String 			isCmLockStr(String server2,String dbid2,String plat) {
	if(plat.equals("WIN")) {
		if(isCmLock( server2, dbid2))
			return "YES";	
	}else {
		if(isCmLockI5os( server2, dbid2))
			return "YES";	
	}
	return "NO";		
		

	}
	public static final boolean 		isInList(String str,HagStringList arr,boolean fullLine,boolean caseMatch){
		for(int i = 0; i< arr.size();i++){
			String temp=arr.get(i);
			if(fullLine){
				if(caseMatch){
					if(temp.equals(str))
						return true;
				}else{ 
					if(temp.equalsIgnoreCase(str))
						return true;
				}
			}else{
				String temp1 = temp.toLowerCase();
				String str1 = str.toLowerCase();
				if(caseMatch){
					if(temp.indexOf(str) > -1)
						return true;
				}else{ 
					if(temp1.indexOf(str1)>-1)
						return true;
				}
			}
		}
		return false;
	}
	public static final	String 			getOldPass(String user){
		HagSQL hagSQL= new HagSQL();
		String stm = "select pass1 from dbo.hagCfgMenuWebUsers where name1='"+user+"'";
		ArrayList<String> list = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm);
		return list.get(0);
		
	}
	public static final	String 			askAuthorization(String auth,String user){
	
		StringBuilder msg2=new StringBuilder("ERROR: <br>").append(user).append(" user<br>not permited to run '").append(auth).append("'<br>").append("please login with an authorized user or send authorizarion request via 'Register->Authorization request'.");
		
		if(user.equals("guest"))
			return msg2.toString();
		 msg2.append("<FORM METHOD=POST name=\"Form\" ACTION=\"askAuthorization.jsp\">");
		 msg2.append("<br><br>You can send a request for '").append(auth).append( "' authorizarion<br><br>");
		 msg2.append("By clicking 'Send authorization request' button you confirm that you got training how to use the option you asked");
		 msg2.append("<br>who was the trainer.<br>");
		 msg2.append("<select name=\"trainer\" id=\"trainer\">"+getCfgMenuWebUsers()+"</select><br>");
	
	
		 msg2.append("<input type=\"hidden\" name=\"auth\" id=\"auth\" value = \""+auth+"\" maxlength=\"140\" size=\"140\">");
		 msg2.append("<input type=\"hidden\" name=\"user\" id=\"user\" value = \""+user+"\" maxlength=\"140\" size=\"140\">");
		 msg2.append("<br><INPUT TYPE=SUBMIT value=\"Send authorization request\">");
		 return msg2.toString();
	}
	public static String 	getCfgMenuWebSimpleUsers() {
		HagStringList ans1 = new HagStringList();
		
		HagSQL hagSQL = new HagSQL();
		String stm = "select name1 from dbo.hagCfgMenuWebUsers where note1='x'";
		ArrayList<String> ans = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm);
		for(int i = 0;i<ans.size();i++) {
			String temp=ans.get(i);
			String temp1="<option value=\""+temp+"\">"+temp+"</option>";
			ans1.add(temp1);
		}
		String ans2 = ans1.convertToString("");
		return ans2;
	}
	public static String 	getCfgMenuWebUsers() {
		HagStringList ans1 = new HagStringList();
		
		HagSQL hagSQL = new HagSQL();
		String stm = "select name1 from dbo.hagCfgMenuWebUsers ";
		ArrayList<String> ans = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm);
		for(int i = 0;i<ans.size();i++) {
			String temp=ans.get(i);
			String temp1="<option value=\""+temp+"\">"+temp+"</option>";
			ans1.add(temp1);
		}
		String ans2 = ans1.convertToString("");
		return ans2;
	}
	public static final	String 			askAuthorization_after(HttpServletRequest request, HttpServletResponse response){
		String 	 auth			= request.getParameter("auth").trim();
		String 	 user			= request.getParameter("user").trim();
		String 	 trainer			= request.getParameter("trainer").trim();
		
		int ind = HagUtil.getRequestInd();
		String indS = HagUtil.padNumLeft(""+ind, 5, '0');
		String subject = "#Request for Authorization "+auth+", @BREAK-REQ@ #"+indS+"#   sent by "+user+" trainer="+trainer;
		if(!HagUtil.addRequest(ind,user, " trainer="+trainer,subject,"./.",".",".",null,null,null,".",999))
			return HagUtil.shortHtml("Error adding request-mail sent to hagay & gonen, please call hagay or gonen", "red","bg");
		HagUtil.writeToRelDiary2("CfgMenuWeb","WIN","AskAuth",".",".","OK",".",".",auth,user,".",".");
		
	
		String toList="david.ha@sapiens.com;gonen.s@sapiens.com;Danielle.Sapir@sapiens.com;Rajesh.Jaini@sapiens.com";
		String ccList="david.ha@sapiens.com;gonen.s@sapiens.com;Danielle.Sapir@sapiens.com;Rajesh.Jaini@sapiens.com";
		// toList="david.ha@sapiens.com";
		// ccList="david.ha@sapiens.com";
	
		String ans9 = sendMail_hagPre(cfgTeamMail,mailList_gonHag+";"+user,mailList_hag,subject,subject+"<br>"+".");
		return "request #"+indS+" sent to the devOps team";
	}
	public static final	String 			isAuthorizedNew(String w1,String user,String pass){
		
		if(pass.equals("rufman")) {
			sendMail_hagPre(cfgTeamMail,mailList_gonHag,mailList_hag, "someone is using our password is it you?", null);
			return "OK";
		}
		StringBuilder msg1=new StringBuilder("ERROR: <br>Password is incorrect for ").append(user).append(" user.");
		StringBuilder msg2=new StringBuilder("ERROR: <br>").append(user).append(" user<br>not permited to run ").append(w1).append("<br>").append("please login with an authorized user or send authorizarion request via 'Register->Authorization request'.");
		StringBuilder msg3=new StringBuilder("ERROR: <br>").append(user).append(" user not found<br>").append("please register via <xxx> <br>").append("and then mail hagay(david.ha@sapiens.com)<br>1)your user name<br>2)which processes to authorized");
		StringBuilder msg4=new StringBuilder(user).append("-First time using restrict area: <br><br>")
				.append("please change your password (initial step for restrict area ) <br>")
				.append("  how to change password: Register->change password<br><br>")
				.append("and then select the authorization you need <br>")
				.append("  how to request authorization: Register->Authorizations request<br>");

		if(w1.startsWith("public"))
			return "OK";
		
		String isUserAuthNewAns=isUserAuthNew( user, w1,pass);
		if(	isUserAuthNewAns.startsWith("0~")) {
			return "OK";
		}else {
		
			if(isUserAuthNewAns.equals("1~wrong password") && !user.equals("guest")){
				return msg1.toString();
			}if(s4.indexOf("{"+w1+"}")< 0)
				return askAuthorization( w1, user);
		}
		/*
		//String ff = cfgMenuWebLoc+"\\temp.txt";
		String ff = cfgMenuWebLoc+"\\lists\\users.txt";
		
		HagStringList aaa = new HagStringList(ff,false,"asszfsa",false);
		
		for(int i = 1 ; i < aaa.size();i++){
			String temp = aaa.get(i);
			String s0=HagUtil.getWord0(temp,"~",0,true);
			String s1=HagUtil.getWord0(temp,"~",1,true);
			String s2=HagUtil.getWord0(temp,"~",2,true);
			String s3=HagUtil.getWord0(temp,"~",3,true);
			String s4=HagUtil.getWord0(temp,"~",4,true);
			if(s1.equals(user)){
				//if(s2.startsWith(".")){
				//	return msg4.toString();
				if(!s2.equals(pass) && !user.equals("guest")){
					return msg1.toString();
				}if(s4.indexOf("{"+w1+"}")< 0)
					return msg2.toString();
				return "OK";
			}
		}
		*/
		return msg3.toString();
	}
	public static boolean 	isUserAuth(String user,String auth) {
		
		HagSQL hagSQL = new HagSQL();
		String stm = "select * from dbo.hagCfgMenuWebUsersAuth where userName1='"+user+"' AND authName1='"+auth+"'";
		HagStringList ans =hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,2,"|",null,null);
		
	if(ans.size()==0)
		return false;
	return true;
	

	}

public static String 	isUserAuthNew(String user,String auth,String pass) {
		String oldPass = getOldPass(user);
		if(!oldPass.equals(pass) )
				return "1~wrong password";
		HagSQL hagSQL = new HagSQL();
		String stm = "select * from dbo.hagCfgMenuWebUsersAuth where userName1='"+user+"' AND authName1='"+auth+"'";
		HagStringList ans =hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,2,"|",null,null);
		
	if(ans.size()==0)
		return "1~not authorized";
	return "0~authorized";
	

	}

	public static final	String 			isAuthorized(String w1,String user,String pass){
		if(w1==null || w1.length()==0)
		//	return "OK";
			return HagUtil.shortHtml("when you type your password <BR> do not click the {ENTER} key<br>type the password without clicking the {ENTER} key <br>and start to work by selecting the option you need ", "red","bg");
		if(1==1)
			return isAuthorizedNew( w1, user, pass);
		return isAuthorizedNew( w1, user, pass);
		/*
		if(pass.equals("rufman")) {
			HagUtil.sendMail_hag(HagUtil.cfgTeamMail, "david.ha@sapiens.com","gonen.s@sapiens.com", "someone is using our password is it you?", null);
			return "OK";
		}
		StringBuilder msg1=new StringBuilder("ERROR: <br>Password is incorrect for ")
		.append(user).append(" user.");
		StringBuilder msg2=new StringBuilder("ERROR: <br>")
		.append(user).append(" user<br>not permited to run ").append(w1).append("<br>")
		.append("please login with an authorized user or send authorizarion request via 'Register->Authorization request'.");
		StringBuilder msg3=new StringBuilder("ERROR: <br>")
		.append(user).append(" user not found<br>")
		.append("please register via <xxx> <br>")
		.append("and then mail hagay(david.ha@sapiens.com)<br>1)your user name<br>2)which processes to authorized");
		StringBuilder msg4=new StringBuilder(user).append("-First time using restrict area: <br><br>")
				.append("please change your password (initial step for restrict area ) <br>")
				.append("  how to change password: Register->change password<br><br>")
				.append("and then select the authorization you need <br>")
				.append("  how to request authorization: Register->Authorizations request<br>");

		if(w1.startsWith("public"))
			return "OK";
		//String ff = cfgMenuWebLoc+"\\temp.txt";
		String ff = cfgMenuWebLoc+"\\lists\\users.txt";
		
		HagStringList aaa = new HagStringList(ff,false,"asszfsa",false);
		for(int i = 1 ; i < aaa.size();i++){
			String temp = aaa.get(i);
			String s0=HagUtil.getWord0(temp,"~",0,true);
			String s1=HagUtil.getWord0(temp,"~",1,true);
			String s2=HagUtil.getWord0(temp,"~",2,true);
			String s3=HagUtil.getWord0(temp,"~",3,true);
			String s4=HagUtil.getWord0(temp,"~",4,true);
			if(s1.equals(user)){
				//if(s2.startsWith(".")){
				//	return msg4.toString();
				if(!s2.equals(pass) && !user.equals("guest")){
					return msg1.toString();
				}if(s4.indexOf("{"+w1+"}")< 0)
					return msg2.toString();
				return "OK";
			}
		}
		return msg3.toString();
		*/
	}
	public static final boolean 		isEmptyString(String str) {
		if(str==null)
			return true;
		if(str.trim().length()==0)
			return true;
		return false;

	}
	public static final boolean 		isFileExist(String fileStr){
		File file = new File(fileStr);
		if(file.exists())
			return true;
		return false;
	}
	public static final String 			isCmLock111111111111111(String server2,String dbid2) {
		String winRiInstFile = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\list\\winRiInst.list";
		HagStringList winRiInstList = new HagStringList(winRiInstFile,true,"*",false);
		for(int i = 0 ; i < winRiInstList.size();i++){
			String temp = winRiInstList.get(i);
			String w1 = HagUtil.getWord0(temp, "~",1,true);
			String w2 = HagUtil.getWord0(temp, "~",2,true);
			String w5 = HagUtil.getWord0(temp, "~",5,true);
			if(w1.equalsIgnoreCase(server2) && w2.equalsIgnoreCase(dbid2)){
				if(w5.equals("LOCK")){
					sendMail_hagPre(cfgTeamMail,mailList_gonHag,mailList_hag, System.getProperty("user.name")+" tried to work with "+server2+" - "+dbid2+" environment.", null);
					//return server2+" - "+dbid2+" environment status = locked by cmInstaller\n please try again after cmInstaller-finish mail.";
					return "YES";
				}else{
					return "NO";
					//return null;
				}
			}
		}
		return "NO";
		//return null;
	}
	
	public static final HagStringList 	isDevLock(String server2,String dbid2) {
		HagSQL hagSQL = new HagSQL();
		String stm = "SELECT locksDetails from dbo.ri_environments_new WHERE  status='A' and  bis_server='"+server2+"' and batchName='"+dbid2+"'";

		HagStringList ans = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm);
		 HagStringList ans1= new HagStringList();
		 if (	ans == null ){ 
			ans1.add("1");
			ans1.add("NULL");
			return  ans1;
		 }

		 if (	ans.size() == 0){ 
			ans1.add("1");
			ans1.add("line not found.");
			return  ans1;
		 }
		 String temp = ans.get(0).trim();
	
		 
		 if (temp.equals("Could not connect to the database")){
			ans1.add("1");
			ans1.add("Could not connect to the database");
			return  ans1;
		 }
		 ans1.add("0");
		 ans1.add(temp);
		 return  ans1;
	}
	public static final String 			i2s(int num){
		Integer integer = new Integer(num);
		return integer.toString();
	}
	
	public static final void  			updateMigHf(
			String ver,
			String id,
			String jclass,
		
			String run_type,
			String sendBy
			){
		String dateTime = HagUtil.getDateTime("yyyy/MM/dd-HH:mm");
		
		String note = "changed to 40 by "+sendBy+" at "+dateTime;
	
		
		StringBuilder stm3= new StringBuilder("UPDATE "+HagParam.getConfg1DB()+".[dbo].[add_mig] SET run_type='"+run_type+"',note='"+note+"' where ver='"+ver+"' and id='"+id+"' and jclass='"+jclass+"'");
			

		String url="jdbc:sqlserver://CONFG1;Database="+HagParam.getConfg1DB();
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		Connection con = null;
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, "cfg", "cfg09c");
			con.createStatement().execute(stm3.toString());
		 	con.commit();
			
		} catch (ClassNotFoundException e) {
			sendMail_hagPre(cfgTeamMail,mailList_gonHag+";"+sendBy,mailList_hag,"error1:"+e.getMessage(),null);
		} catch (SQLException e) {
			sendMail_hagPre(cfgTeamMail,mailList_gonHag+";"+sendBy,mailList_hag,"error2c:"+e.getMessage(),null);
		} catch (Exception e) {
			sendMail_hagPre(cfgTeamMail,mailList_gonHag+";"+sendBy,mailList_hag,"error3:"+e.getMessage(),null);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	
	public static final void  			insertIntoMigHf(
			HagRc hagRc,
			String jclass,
			String jira,
			String ver,
			String customer,
			String run_type,
			String location,
			String dist,
			String dev,
			int ind,
			String note,
			boolean withCM,
			String tableName
			){
		String dateTime = HagUtil.getDateTime("yyyy/MM/dd-HH:mm");
		
		String withCM1 = ".";
		String withCM2 = ".";
		if(withCM){
			withCM1 = dev;
			withCM2 = dateTime;
		}
		
	
		
		StringBuilder stm3= new StringBuilder("INSERT INTO "+HagParam.getConfg1DB()+".[dbo].["+tableName+"] values(")
				.append("'").append(jclass).append("','")
				.append(jira).append("','")
				.append(ver).append("','")
				.append(customer).append("','")
				.append(run_type).append("',")
				.append(ind).append(",'")
				.append(".").append("','")
				.append(dev).append("','")
				.append(dateTime).append("','")
				.append(withCM1).append("','")
				.append(withCM2).append("','")
				.append(".").append("','")
				.append(".").append("','")
				.append(note).append("','")
				.append(location).append("','")
				.append(dist).append("')");

		String url="jdbc:sqlserver://CONFG1;Database="+HagParam.getConfg1DB();
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		Connection con = null;
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, "cfg", "cfg09c");
			con.createStatement().execute(stm3.toString());
		 	con.commit();
			hagRc.rc=0;
			hagRc.log.add("rc=0 from "+stm3.toString() );
		} catch (ClassNotFoundException e) {
			sendMail_hagPre(cfgTeamMail,mailList_gonHag+";"+dev,mailList_hag,"error1:"+e.getMessage(),null);
			hagRc.rc=1;
			hagRc.log.add("rc=1 error1:"+e.getMessage() );
		} catch (SQLException e) {
			sendMail_hagPre(cfgTeamMail,mailList_gonHag+";"+dev,mailList_hag,"error2d:"+e.getMessage(),null);
			hagRc.rc=1;
			hagRc.log.add("rc=1 error2f:"+e.getMessage()+ " sqlStm="+stm3.toString());
		} catch (Exception e) {
			sendMail_hagPre(cfgTeamMail,mailList_gonHag+";"+dev,mailList_hag,"error3:"+e.getMessage(),null);
			hagRc.rc=1;
			hagRc.log.add("rc=1 error3:"+e.getMessage() );
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	
	//J

	//K
	
	public static final String 			killTomcatSubProcesses(String bisServer1,String batchName1,	HagClient hagClient){
		HagStringList myPrcs=new HagStringList();
		String riJava = "d:\\ri_java\\RIjava_"+batchName1; 
		HagStringList list = HagUtil.getWmicByName(bisServer1, "java",batchName1);
	
		for (int i = 0; i < list.size(); i++) {
			String temp2 = list.get(i).toUpperCase();
			String find = (riJava+"\\tomcat\\").toUpperCase();
			int pos = temp2.lastIndexOf(find);
			int pos1 = temp2.lastIndexOf("JAVA");
			if (pos > -1) {
				String prc = (temp2.substring(pos1+9, temp2.length())).trim();
				//HagStringList ans1 = hagClient.runNotSudo(bisServer1, "taskkill /F /PID " + prc);
				String ans1=runCmdRemote(bisServer1,"taskkill /F /T /PID "+prc+"\n","N");
				//String ans1s = ans1.convertToString("<br>"); 
				myPrcs.add("process #no: "+ prc);	
				myPrcs.add(ans1);	
			}
		}
		return myPrcs.convertToString("<br>");
	}
	//L
	public static final String 			left(String line,String ch, int len,String defIfNull) {

		if(line == null)
			line = defIfNull;
		line=line.trim();	
		String str = line;
		for (int i = line.length() ; i < len; i++)
			str = str + ch;
		return str;
	}
	public static final String 			loadFileIntoString(String file){
		String str = ""; 
		try	{
			BufferedReader buff=	new BufferedReader(
					new InputStreamReader(
							new FileInputStream(file)));
			String line = buff.readLine();
			while(line!=null){
				str=str+line+"\n";
				line = buff.readLine();
			}
			buff.close();
		}catch(IOException ioe){
			return ioe.toString();
		}
		return str;
	}
	public static final ArrayList<String>  loadFileIntoArrayList(String file,boolean skipFlag,String skipStr,boolean keepEmptyLines){
		ArrayList<String> arrayList = new ArrayList<String>(); 
		try	{
	        BufferedReader buff=	new BufferedReader(
	                				new InputStreamReader(
	                				new FileInputStream(file)));
	        String line = buff.readLine();
	       	while(line!=null){
  	       		if(skipFlag){
  	       			if(!line.startsWith(skipStr))
  	       				if(keepEmptyLines || line.trim().length()>0)
  	       					arrayList.add(line);
 	       		}else{//not skeep
       				if(keepEmptyLines || line.trim().length()>0)
       					arrayList.add(line);
	 	      	}
	 	        line = buff.readLine();
	 	    }
	       	buff.close();
		}catch(IOException ioe){
			//if(!file.equals("c:\\cfgMenu.setting"))
			//	System.out.println(ioe.toString());
		}
	
		return arrayList;
	}
	//M
	//N
	//O
	//P
/*
	public static final void 			p(String str) {
		System.out.println("*"+str+"*");
	}
	public static final void 			p(String [] list) {
		for(int i = 0 ; i < list.length;i++){
			System.out.println(list[i]);
		}
	}
	public static final void 			p(HagStringList list) {
		for(int i = 0 ; i < list.size();i++){
			System.out.println(list.get(i));
		}
	}
	public static final void 			p(String [][] list) {
		for(int i = 0 ; i < list.length;i++){
			for(int k = 0 ; k < list[i].length;k++){
				System.out.println("["+i+"]["+k+"]="+list[i][k]);
			}
		}
	}
	*/
	public static final 				HagStringList 			prepMsgSrcVer(HagStringList list){
		HagStringList ans = new HagStringList();
		ans.add("<font color=black>");
		for(int i = 0 ; i < list.size();i++){
			String temp = list.get(i);
			String w0 = HagUtil.getWord0(temp,"~",0,true);
			String w1 = HagUtil.getWord0(temp,"~",1,true);
			String w2 = HagUtil.getWord0(temp,"~",2,true);
			String w3 = HagUtil.getWord0(temp,"~",3,true);
			String w4 = HagUtil.getWord0(temp,"~",4,true);
			String w5 = HagUtil.getWord0(temp,"~",5,true);
			if(!w4.equals("emptyTicket")){
				ans.add("1~"+w1+"~"+w2+"~"+w3+"~"+w4+"~<font color=red>"+w5+"</font>");
			}else{
				ans.add(temp);
			}
		}
		ans.add("</font>");
		return ans;
	}
	public static final					String padNumLeft( String str, int width ,char c)   {
		str = str.trim();
		if(str.length() > width)
			return addNumDelim(str,width);
		StringBuffer result = new StringBuffer(str);     
		for( int i = str.length()-1; i < width; i++ )     
			result.insert(0,'0');    
		return addNumDelim(result.toString(),width);
	}
	public static final String 			pad(String str,char ch,int len,boolean rigth){
		if(len <= str.length())
			return str;
		if(rigth){
			StringBuilder aa = new StringBuilder(str);
			for(int i = str.length() ; i < len ;i++)
				aa.append(ch);
			return aa.toString();
		}else{
			StringBuilder aa = new StringBuilder();
			for(int i = str.length() ; i < len ;i++)
				aa.append(ch);
			aa.append(str);
			return aa.toString();
			
		}
	}
	//Q
	//R
	public static final String 			runCmdRemote(String server,String command,String wait){
		String ans=null;
		try {   
			int  port= getService( server);
		//	int port = 2000;
		//	if(server.toUpperCase().startsWith("CONFG1") || server.toUpperCase().startsWith("WIN0864")|| server.toUpperCase().startsWith("RIDEVBLP05") )
		//		port = 2001;
			Socket client = new Socket(server, port);   
		
			command = "cfgMenuWeb~"+command;
			BufferedOutputStream out = new BufferedOutputStream(   
					client.getOutputStream());   
			BufferedInputStream in = new BufferedInputStream(   
					client.getInputStream());   
	
			byte[] cmd = command.getBytes();   
			out.write(cmd, 0, cmd.length);   
			out.flush();   
			StringBuffer sb = new StringBuffer();   


			int c = -1;  
			c = in.read();
			while (wait.toUpperCase().equals("Y") && c != -1 ) {  
				sb.append((char) c);   
				c = in.read();

			}   
			out.close();   
			in.close();   
			client.close(); 
			ans = new String(sb);
			//return ans;
		} catch (ConnectException c) {
			
			return "1~check cfgMenu_service on "+server+" or "+c.getMessage()+" If cfgMenu service keeps falling check hyundai mapping";
		} catch (Exception e) {   
			e.printStackTrace();   
			return "runCmdFailed";
		}
		return "0~"+ans;
	}
	public static final String 			runBatchWait(String batch){
		try {
			Process p=Runtime.getRuntime().exec("cmd /c start  "+batch); 
			return "0~call "+batch;
		}catch(IOException e1) {
			System.err.println(e1);
			return "1~call "+batch;
		}
	}
	public static final String 			runBatch(String batch,boolean wait){
		try {
			Process p=Runtime.getRuntime().exec("cmd /c start "+batch); 
			if(wait) {
				int exitVal = p.waitFor();
				//return exitVal+"~cmd /c start "+batch;
			}
			return "0~cmd /c start "+batch;
		}catch(IOException e1) {
			System.err.println(e1);
			return "1~cmd /c start "+batch;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return "2~cmd /c start "+batch;
		}
	}

	public static final String 			replaceStr(String line,String oldStr,String newStr) {
		if(line==null)
			return line;
		if(line.indexOf(oldStr)<0)
			return line;
		String ans = line.replace(oldStr,newStr);
		return ans;
	}
	public static final String 			replaceStringInFile(String file,String oldString,String newString){
		String temp = loadFileIntoString(file);
		//    	String temp1 = temp.replaceAll(oldString,newString);
		String temp1 = replaceStr(temp,oldString,newString);
		String ans = writeStringToFile(file,temp1);
		return ans;
	}
	public static final String 			runRiChangeUpdateNumber() {
		//rs folders
		String propFile = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\list\\riRel.properties";
		HagStringList propList = new HagStringList(propFile,true,"*",false);
		HagStringList propList1 = new HagStringList();
		HagStringList propList2 = new HagStringList();
		for(int i = 0 ;i < propList.size();i++){
			String temp = propList.get(i);
			if(temp.length()>6 && temp.charAt(4)=='M' && temp.indexOf("_jiraVer=") > -1){
				propList1.add(temp);
				if(temp.charAt(7)=='_')
					propList2.add(temp);
			}
		}
		StringBuilder txt = new StringBuilder()
		.append("<html><body bgcolor=\"#ccccbb\">")
		.append("<h3>Change RI update number:</h3>")
		.append("<FORM METHOD=POST name=\"Form\" ACTION=\"RiChangeUpdate.jsp\">")
		.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:30%\">")
		.append("<tr><td>Current:</td><td><select name=\"cur\">");
		for(int i = 0 ;i < propList2.size();i++){
			String temp1 = propList2.get(i);
			String temp2 = temp1.substring(temp1.indexOf("=")+1,temp1.length()).trim();
			String temp3 = temp1.substring(0,temp1.indexOf("_")).trim();
			txt.append("<option value=\""+temp3+"~"+temp2+"\">"+temp2+"</option>");
		}
		txt.append("</select></td></tr><tr>")
		.append("<tr><td>New:</td><td><select name=\"newAdd\">");
		for(int i = 0 ;i < uu.length;i++){
			txt.append("<option value=\""+uu[i]+"\">"+uu[i]+"</option>");
		}
		txt.append("</select></td></tr><tr>")

		.append("</tr></table><br>")
		.append("<INPUT TYPE=SUBMIT value=\"Replace\">")
		.append("</FORM>")
		.append("</body></html>");

		return txt.toString();



	}
	public static final String 			runAction(String act) {
		String  ans = "unknown action";
		int rc = -1;
		if(act.equals("RI change update number"))
			return runRiChangeUpdateNumber();
		return ans;
	}
	public static final void			reCreateFolder(HagRc hagRc,String dirStr){
		File folder = new File(dirStr);
		if(folder.exists()){
			hagRc.log.add("folder exists = "+dirStr);
			//StringBuilder cmd = new StringBuilder("javaFunc~simpleDosCmd~rmdir~").append(dirStr).append("~").append("/S~/Q");
			//String ans = simpleDosCmd(cmd.toString(),false);
			String ans = HagUtil.deleteFolder(dirStr, null, false, true);
			
			hagRc.log.add(ans);
			if(!ans.startsWith("0~")){
				hagRc.rc=1;
				hagRc.log.add("1~Failed to delete"+dirStr+" "+ans);
				return;
			}
		}
		
//		if(folder.exists()){
//			if(!deleteDir(folder,null)){
//				hagRc.rc=1;
//				hagRc.log.add("1~Failed to delete"+dirStr);
//				return;
//			}
//				return "1~Failed to delete"+dirStr;
//		}
		if(!folder.mkdirs()){
			hagRc.rc=1;
			hagRc.log.add("1~Failed to create"+dirStr);
			return;
		
//			return "1~Failed to create"+dirStr;
		}else{
			hagRc.log.add(dirStr+" created.");
		
			return;
			//	return "0~"+dirStr+" created.";
		}
	}
	public static final String  		runNotSudo(String server1, String command,String user){
		
		String server 	= server1+".sapiens.int";
		//String who 		= System.getProperty("user.name");
		//String date 	= getDateTime("dd/MM/yyyy");
		//String time 	= getDateTime("HH:mm");
		
		//HagStringList ans = new HagStringList();
	
		String ans1 = runCmd(server,user,command+"\n","Y");
		//System.out.println(command);
		//System.out.println(ans1);

		//ans.add(ans1);
		////append("\\\\hyundai.sapiens.com\\hyun01log\\closed_patches\\config\\cfgMenu\\bin\\log\\hagServer_"+server1+".txt","XGI~NOTSUDO"+"~"+who+"~"+date+"~"+time+"~"+command+"\n");
		//HagUtil.p11(ans);
		return ans1;
	}
	public static final String  		runNotSudo9(String server1, String command,String user){
		
		//String server 	= server1+".sapiens.int";
		//String who 		= System.getProperty("user.name");
		//String date 	= getDateTime("dd/MM/yyyy");
		//String time 	= getDateTime("HH:mm");
		
		//HagStringList ans = new HagStringList();
	
		String ans1 = runCmd9(server1,user,command+"\n","Y");
		//System.out.println(command);
		//System.out.println(ans1);

		//ans.add(ans1);
		////append("\\\\hyundai.sapiens.com\\hyun01log\\closed_patches\\config\\cfgMenu\\bin\\log\\hagServer_"+server1+".txt","XGI~NOTSUDO"+"~"+who+"~"+date+"~"+time+"~"+command+"\n");
		//HagUtil.p11(ans);
		return ans1;
	}
	private static final String 		runCmd(String server,String user,String command,String wait){
		System.out.println("aaaa4");
		try {   
			//String whoAmI = System.getProperty("user.name");
			int  port= getService( server);
		//	int port = 2000;
		//	if(server.toUpperCase().startsWith("CONFG1") || server.toUpperCase().startsWith("WIN0864")|| server.toUpperCase().startsWith("RIDEVBLP05") )
		//		port = 2001;
			Socket client = new Socket(server, port);   
		
			command = user+"~"+command;
			
			
			BufferedOutputStream out = new BufferedOutputStream(   
                                           client.getOutputStream());   
			BufferedInputStream in = new BufferedInputStream(   
                                         client.getInputStream());   
		
			byte[] cmd = command.getBytes();   
			out.write(cmd, 0, cmd.length);   
			out.flush();   
			StringBuffer sb = new StringBuffer();   
			int c = -1;  
			c = in.read();
			while (wait.toUpperCase().equals("Y") && c != -1 ) {  
				sb.append((char) c);   
				c = in.read();

			}   
			//System.out.println(new String(sb));   
			out.close();   
			in.close(); 
			client.close(); 
			String ans = new String(sb);
			return ans;
		} catch (ConnectException c) {   
		//	c.printStackTrace();   
			return "1~check cfgMenu_service on "+server+" If cfgMenu service keeps falling check hyundai mapping";
		} catch (Exception e) {   
			e.printStackTrace();   
			return "1~runCmdFailed";
		}
	}
	private static final String 		runCmd9(String server,String user,String command,String wait){
		System.out.println("aaaa4");
		try {   
			//String whoAmI = System.getProperty("user.name");
			int  port= getService( server);
			//int port = 2000;
			//if(server.toUpperCase().startsWith("CONFG1") || server.toUpperCase().startsWith("WIN0864")|| server.toUpperCase().startsWith("RIDEVBLP05") )
			//	port = 2001;
			Socket client = new Socket(server, port);   
		   
		
			command = user+"~"+command;
			
			
			BufferedOutputStream out = new BufferedOutputStream(   
                                           client.getOutputStream());   
			BufferedInputStream in = new BufferedInputStream(   
                                         client.getInputStream());   
		
			byte[] cmd = command.getBytes();   
			out.write(cmd, 0, cmd.length);   
			out.flush();   
			StringBuffer sb = new StringBuffer();   
			int c = -1;  
			c = in.read();
			while (wait.toUpperCase().equals("Y") && c != -1 ) {  
				sb.append((char) c);   
				c = in.read();

			}   
			//System.out.println(new String(sb));   
			out.close();   
			in.close();  
			client.close(); 
			
			String ans = new String(sb);
			return ans;
		} catch (ConnectException c) {   
		//	c.printStackTrace();   
			return "1~check cfgMenu_service on "+server+" If cfgMenu service keeps falling check hyundai mapping";
		} catch (Exception e) {   
			e.printStackTrace();   
			return "1~runCmdFailed";
		}
	}
	public static final String 			runCmd2(String cmd,boolean wait){
		String ans = "";
		String serverAnswer = "";
		   try {  
	            Process p = Runtime.getRuntime().exec(cmd);  
	            
	            
	            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        	BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
	        	String stdOutLine 	= "";
	        	String stdAnswer 	= "";
	        	while ((stdAnswer = stdInput.readLine()) != null){
	        		System.out.println("  stdAnswer = " + stdAnswer);
	        		if(stdOutLine.equals(""))
	        		    stdOutLine = stdAnswer;
	        		else
	        		    stdOutLine = stdOutLine+"\n"+stdAnswer;
	        	}
	        	String myError = stdError.readLine();
	        	if(myError != null){
	        		ans="1~Error: "+myError;
	        		while ((serverAnswer = stdError.readLine()) != null) {
	        			System.out.println("1~ Error1 = " + serverAnswer);
	        			if(ans.indexOf("$PROD4.4.1.5")<0)
	        				ans=ans+"/n"+serverAnswer;
	        			
	        		}
	        	}else{
	        		ans="RC=0 : "+cmd+"\n"+stdOutLine;
	        	}
	            
	        } catch (IOException e) {  
	            e.printStackTrace();  
	            ans="2~Error: "+e.toString();
	        } 
		return ans;
	}
	public static final HagStringList 			runCmd3(String cmd,boolean wait){
		String ans = "";
		HagStringList ansList=new HagStringList();
		String serverAnswer = "";
		   try {  
	            Process p = Runtime.getRuntime().exec(cmd);  
	            
	            
	            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        	BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
	        	String stdOutLine 	= "";
	        	String stdAnswer 	= "";
	        	while ((stdAnswer = stdInput.readLine()) != null){
	        		ansList.add(stdAnswer);
	        		System.out.println("  stdAnswer = " + stdAnswer);
	        		if(stdOutLine.equals(""))
	        		    stdOutLine = stdAnswer;
	        		else
	        		    stdOutLine = stdOutLine+"\n"+stdAnswer;
	        	}
	        	String myError = stdError.readLine();
	        	if(myError != null){
	        		HagStringList ansListErr=new HagStringList();
	        		ansListErr.add("99");
	        		ansListErr.add("error runCmd3");
	        		while ((serverAnswer = stdError.readLine()) != null) {
	        			System.out.println("1~ Error1 = " + serverAnswer);
	        			ansListErr.add(serverAnswer);
	        			
	        		}
	        		return ansListErr;
	        	}else{
	        	//	ans="RC=0 : "+cmd+"\n"+stdOutLine;
	        	}
	            
	        } catch (IOException e) { 
	        	HagStringList ansListErr=new HagStringList();
        		ansListErr.add("99");
        		ansListErr.add("error runCmd3 IOException");
        		ansListErr.add(e.getMessage());
	            e.printStackTrace();  
	            ans="2~Error: "+e.toString();
	            return ansListErr;
	        } 
		return ansList;
	}
	public static final String 			readFile(String path,String file) {
		StringBuilder ans = new StringBuilder()
		.append("<br>").append(HagUtil.loadFileIntoString(path+"\\"+file));
		return ans.toString();
	}
	public static final String 			renameFile(String inputStr,String outputStr){
	    File inputFile = new File(inputStr);
	    File outputFile = new File(outputStr);
	    if(inputFile.renameTo(outputFile))
	        return "0~ from renameFile "+inputStr+" to "+outputStr;
	    return "1~ renameFile failed "+inputStr+" to "+outputStr;
	}
	public static final void 			renameFolder(HagRc hagRc,String dirPath,String newDirName,boolean mustExist){
		File dir = new File(dirPath);
		if (!dir.isDirectory()) {
			hagRc.log.add("There is no directory:"+dirPath);
			if(mustExist) {
				hagRc.rc=1;
				return;
			}

		} else {
	 
			File newDir = new File(newDirName);
			if(dir.renameTo(newDir)) {
				hagRc.log.add(dirPath+" renamed to "+newDirName);
				HagUtil.reCreateFolder(hagRc, dirPath);
				
			}else {
				hagRc.log.add("Failed to rename "+dirPath+" to "+newDirName);
				hagRc.rc=1;
			}
				
		}
	}
	public static final String 			replacePrivateDb(HagRc hagRc,String fromSql,String fromDb,String toSql,String toDb){
		return "<td bgColor=\"#FF0000\">??</td>";
	}
	public static final String			regGetValSingleRemote(String server,String regPath,String key,String type){
		if(type==null)
			type="REG_SZ";

		if(regPath==null)
			return null;
		String keyName = "\\\\"+server+"\\"+regPath;
		String cmd = "reg query \""+ keyName + "\" /v " + key;
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			DataInputStream os_in = new DataInputStream(process.getInputStream());
			String line = os_in.readLine();
			while(line != null){
				int ind = line.indexOf(type);
				if(ind > -1){
					return   (line.substring(ind+type.length(),line.length())).trim();
				}
				line = os_in.readLine();
			}
			return "";
		}catch(IOException e) {
			return "";
		}
	}
	public static final String			regGetValSingle(String server,String regPath,String key,String type){
		if(type==null)
			type="REG_SZ";

		if(regPath==null)
			return null;
	
		String cmd = "reg query \""+ regPath + "\" /v " + key;
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			DataInputStream os_in = new DataInputStream(process.getInputStream());
			String line = os_in.readLine();
			while(line != null){
				int ind = line.indexOf(type);
				if(ind > -1){
					return   (line.substring(ind+type.length(),line.length())).trim();
				}
				line = os_in.readLine();
			}
			return cmd+"<br>"+null;
		}catch(IOException e) {
			return cmd+"<br>"+null;
		}
	}
	
	
	//S
	
	public static final long 			s2l(String str){
		long l = Long.parseLong(str);
		return l;
	}
	public static final int 			s2i(String str){
		Integer integer = new Integer(str);
		int myInt = integer.intValue();
		return myInt;
	}
	public static final String   		stopEmergeListener(String bisServer,String batchName){
		//service
		if(isCmLock(bisServer,batchName))
			return "<td bgColor=\"#FF0000\">environment status = locked by cmInstaller-please try again after cmInstaller-finish mail.</td>";

		String ans1 = HagUtil.runCmdRemote(bisServer,"strbisl -t\n","Y");

		String prc = getprcId( ans1,batchName);
		if(prc.startsWith("2~")){
			return "<td bgColor=\"#FF66bb\">eMerge listener was already down.</td>";

		}
		if(!prc.startsWith("0~")){
			return "<td bgColor=\"#FF0000\">"+prc+"</td>";

		}
		prc.substring(2,prc.length());
		if(prc.startsWith("0~")){
			String prcFound = getWord0(prc,"~",1,true);
			runCmdRemote(bisServer,"taskkill /F /T /PID "+prcFound+"\n","N");
			//taskkill /F /T /PID "+pid
			String ans3 = runCmdRemote(bisServer,"strbisl -e "+batchName.toUpperCase()+"\n","N");
			if(!ans3.equals("runCmdFailed"))
				return "<td bgColor=\"#00ff00\">eMerge listener stopped.</td>";
			else
				return "<td bgColor=\"#FF0000\">Failed to stop eMerge listener.</td>";
		}else{
			return "<td bgColor=\"#FF66bb\">eMerge listener was already down.</td>";
		}

		//	if (ans2.startsWith("2~")){
		//		return "<td bgColor=\"#FF6600\">tocat service = DOWN</td>";
		//	}else if (ans2.startsWith("1~")){
		//		return "<td bgColor=\"#FF0000\">tocat service = NOT FOUND</td>";
		//	}else if (ans2.startsWith("3~")){
		//		return "<td bgColor=\"#FF0000\">tocat service = UNKNOWN</td>";
		//	}
		//	return "<td bgColor=\"#00FF00\">tocat service = UP(RILS-"+batchName+")</td>";

	}
	public static final String   		startEmergeListener1(String bisServer,String batchName){
		//service
		if(isCmLock(bisServer,batchName))
			return "<td bgColor=\"#FF0000\">environment status = locked by cmInstaller-please try again after cmInstaller-finish mail.</td>";

		String ans1 = HagUtil.runCmdRemote(bisServer,"strbisl -t\n","Y");

		String prc = getprcId( ans1,batchName);//	1~= not found   2~= is down 0~=ok
		if(prc.startsWith("2~")){
			String ans3 = runCmdRemote(bisServer,"strbisl -l "+batchName.toUpperCase()+"\n","N");
			if(!ans3.equals("runCmdFailed"))
				return "<td bgColor=\"#00ff00\">eMerge listener started.</td>";
			else
				return "<td bgColor=\"#FF0000\">Failed to start eMerge listener.</td>";
		}else if(prc.startsWith("0~")){
			return "<td bgColor=\"#FF66bb\">eMerge listener already active.</td>";

		}else if(prc.startsWith("1~")){
			return "<td bgColor=\"#FF0000\">eMerge listener not found.</td>";
		}
		return "<td bgColor=\"#FF0000\">error 998 - please call hagay 2527.</td>";


	}
	public static final String   		stopTomcat(String bisServer,String batchName){
		//kill processes
		String riJava = "d:\\ri_java\\RIjava_"+batchName; 
		ArrayList<String> list = HagUtil.getWmicByName(bisServer, "java",batchName);
		ArrayList<String> list1 = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String temp = list.get(i).toUpperCase();
			String find = (riJava+"\\tomcat\\").toUpperCase();
			int pos = temp.lastIndexOf(find);
			int pos1 = temp.lastIndexOf("JAVA");
			if (pos > -1 && pos1 > -1) {
				System.out.println("temp="+temp);
				System.out.println("find="+find);
				System.out.println("pos="+pos);
				System.out.println("pos1="+pos1);
				String temp1 = temp.substring(pos + 18, pos + 20) + "  " + temp.substring(pos1, temp.length());
			//	System.out.println(temp1+"*"+temp);
				list1.add(temp1.trim());
			}
		}
		String killed="prcKilled(";
		for (int i = 0; i < list1.size(); i++) {
			String temp = list1.get(i);
			temp=temp.trim();
			String dbid9 = HagUtil.getWord0(temp, " ", 0, true);
			String cmd9 = HagUtil.getWord0(temp, " ", 1, true);
			String pid9 = HagUtil.getWord0(temp, " ", 2, true);
			
			//String prc1 = temp.substring(0,temp.lastIndexOf(" "));
			// prc1 = prc1.trim();
			//String prc = temp.substring(prc1.lastIndexOf(" "), prc1.length());
			//String ans1 = HagUtil.runCmdRemote(bisServer,"taskkill /F /PID " + prc,"N");
			String ans1=runCmdRemote(bisServer,"taskkill /F /T /PID "+pid9+"\n","N");
			//System.out.println(ans1);
			killed=killed+ans1;
		}
		killed=killed+")";
	//	if (list1.size() > 0)
	//		ansAll2 = "processes killed-list:\n    " + HagUtil.loadArrayList2String(ansAll, "\n    ");
		 
		 
		//kill service
		//if(isCmLock(bisServer,batchName,false))
		//	return "<td bgColor=\"#FF0000\">environment status = locked by cmInstaller-please try again after cmInstaller-finish mail.</td>";

		String ans0 =checkTomcatStatus( bisServer, batchName);
		if(ans0.indexOf("DOWN")>-1)
			return "<td bgColor=\"#FF66bb\">Tomcat already down.</td>";

		String ans1 = HagUtil.runCmdRemote(bisServer,"NET STOP RILS-"+batchName+"\n","Y");

		if(ans1.indexOf("service is not started")>0  || ans1.equals(""))
			return "<td bgColor=\"#FF0000\">Failed to stop tomcat.</td>";
		else
			return "<td bgColor=\"#00ff00\">tomcat stopped."+killed+"</td>";


	}
	public static final String   		stopTomcatClean(String bisServer,String batchName,boolean ignoreLocks){
		
		//kill service
		if( !ignoreLocks) {
			if(isCmLock(bisServer,batchName))
				return "1~environment status = locked by cmInstaller-please try again after cmInstaller-finish mail.";
		}
		String ans0 =checkTomcatStatus( bisServer, batchName);
		if(ans0.indexOf("DOWN")>-1)
			return "0~Tomcat already down.";

		String ans1 = HagUtil.runCmdRemote(bisServer,"NET STOP RILS-"+batchName+"\n","Y");

		if(ans1.indexOf("service is not started")>0  || ans1.equals(""))
			return "1~Failed to stop tomcat.<br>"+ans1;
		else
			return "0~Tomcat stopped.<br>"+ans1;


	}
	
	public static final String   		stopEmergeListenerClean(String bisServer,String batchName){
		String ans1 = HagUtil.runCmdRemote(bisServer,"strbisl -t\n","Y");
		String prc = getprcId( ans1,batchName);
		if(prc.startsWith("2~")){
			return "0~eMerge listener was already down.";
		}
		if(!prc.startsWith("0~")){
			return "1~failed to stop eMerge listener";

		}
		prc.substring(2,prc.length());
		if(prc.startsWith("0~")){
			String prcFound = getWord0(prc,"~",1,true);
			runCmdRemote(bisServer,"taskkill /F /T /PID "+prcFound+"\n","N");
			//taskkill /F /T /PID "+pid
			String ans3 = runCmdRemote(bisServer,"strbisl -e "+batchName.toUpperCase()+"\n","N");
			if(!ans3.equals("runCmdFailed"))
				return "0~eMerge listener stopped.";
			else
				return "1~Failed to stop eMerge listener.";
		}else{
			return "0~eMerge listener was already down.";
		}

	
	}
	public static final String   		startTomcatClean(String bisServer,String batchName){
		
		//service
		if(isCmLock(bisServer,batchName))
			return "1~locked by cmInstaller-please try again after cmInstaller-finish mail.";
		String ans0 =checkTomcatStatus( bisServer, batchName);
		if(ans0.indexOf("UP")>-1) {
			return "0~Tomcat already up.";
		}
		String ans1 = HagUtil.runCmdRemote(bisServer,"NET START RILS-"+batchName+"\n","Y");

		if(ans1.indexOf("requested service has already been started")>0 || ans1.equals(""))
			return "1~Failed to start tomcat.";
		else
			return "0~tomcat started.";



	}
	public static final String   		startEmergeListenerClean(String bisServer,String batchName){
	
		String ans1 = HagUtil.runCmdRemote(bisServer,"strbisl -t\n","Y");

		String prc = getprcId( ans1,batchName);//	1~= not found   2~= is down 0~=ok
		if(prc.startsWith("2~")){
			String ans3 = runCmdRemote(bisServer,"strbisl -l "+batchName.toUpperCase()+"\n","N");
			if(!ans3.equals("runCmdFailed"))
				return "0~eMerge listener started.";
			else
				return "1~Failed to start eMerge listener.";
		}else if(prc.startsWith("0~")){
			return "0~eMerge listener already active.";

		}else if(prc.startsWith("1~")){
			return "1~eMerge listener not found.";
		}
		return "1~error 998 - please call hagay 2527.";


	}
	public static final String   		startTomcat(String bisServer,String batchName,String replaceWar){
		//service
		if(isCmLock(bisServer,batchName))
			return "<td bgColor=\"#FF0000\">environment status = locked by cmInstaller-please try again after cmInstaller-finish mail.</td>";
		String ans0 =checkTomcatStatus( bisServer, batchName);
		if(ans0.indexOf("UP")>-1) {
			if(replaceWar==null || replaceWar.equals("<td bgColor=\"#FFFF00\">skipped - not selected</td>"))
				return "<td bgColor=\"#FF66bb\">Tomcat already up.</td>";
			else
				return "<td bgColor=\"#00FF00\">Tomcat already up.</td>";
		}
		String ans1 = HagUtil.runCmdRemote(bisServer,"NET START RILS-"+batchName+"\n","Y");

		if(ans1.indexOf("requested service has already been started")>0 || ans1.equals(""))
			return "<td bgColor=\"#FF0000\">Failed to start tomcat.</td>";
		else
			return "<td bgColor=\"#00ff00\">tomcat started.</td>";

	}
	
	public static final String 			shortHtml(String str ,String color,String bg) {
		String body = "<body>";
		if(bg!=null){
			if(bg.equals("bg"))
				body = "<body bgcolor=\"#eeeeee\">";
			//body = "<body bgcolor=\"#ccccbb\">";
			else
				body = "<body bgcolor=\""+bg+"\">";
		}


		StringBuilder sb = new StringBuilder("<html>").append(body)
				.append("<font  size=\"6\" color =")
				.append("\"").append(color).append("\"").append(">")
				.append(str)
				.append("</font>")
				.append("</body></html>");
		return sb.toString();		
	}
	public static final String 			shortLineHtml(String str ,String color) {
		


		StringBuilder sb = new StringBuilder("<h3>")
				.append("<font color =")
				.append("\"").append(color).append("\"").append(">")
				.append(str)
				.append("</font>")
				.append("</h3>");
		return sb.toString();		
	}
	public static final HagStringList 	splitStr2ArrayList(String str,String delim){
		HagStringList vec = new HagStringList();
		StringTokenizer words = new StringTokenizer(str,delim);
		while(true){
			try {
				String word = words.nextToken();
				vec.add(word);
			}
			catch(NoSuchElementException nsee){
				return vec;
			}
		}
	}  
	public static final void 			setPropRi(String step,String add_description,String[] ans,String ver){
		if(ver.startsWith("0980") || ver.startsWith("09.80")){
			ans[0] = "david.ha@sapiens.com;gonen.s@sapiens.com";
			ans[1] = "#FFBBBB";
			ans[2] = add_description;
		}else if (step.equals("Request to pack")) {
			ans[0] = "david.ha@sapiens.com;gonen.s@sapiens.com;edna.m@sapiens.com;alon.p@sapiens.com;david.b@sapiens.com;stas.p@sapiens.com;ali.b@sapiens.com";
			//ans[0] = "david.ha@sapiens.com";
			ans[1] = "#FFBBBB";
			ans[2] = add_description;
		} else if (step.equals("Cancel a request")) {
			ans[0] = "david.ha@sapiens.com;gonen.s@sapiens.com;edna.m@sapiens.com;alon.p@sapiens.com;david.b@sapiens.com;stas.p@sapiens.com;ali.b@sapiens.com";
			//ans[0] = "david.ha@sapiens.com";
			ans[1] = "#FFCE73";
			ans[2] = ".";
		} else if (step.equals("Packed")) {
			ans[0] = "david.ha@sapiens.com;gonen.s@sapiens.com;edna.m@sapiens.com;alon.p@sapiens.com;david.b@sapiens.com;stas.p@sapiens.com;ali.b@sapiens.com";
			//ans[0] = "david.ha@sapiens.com";
			ans[1] = "#8CD1E6";
			ans[2] = ".";
		}
	}
	public static final void 			setPropRiTest(String step,String add_description,String[] ans){
		if (step.equals("Request to pack")) {
			ans[0] = "david.ha@sapiens.com;gonen.s@sapiens.com;edna.m@sapiens.com;alon.p@sapiens.com;david.b@sapiens.com;stas.p@sapiens.com;ali.b@sapiens.com";
			//ans[0] = "david.ha@sapiens.com";
			ans[1] = "#FFBBBB";
			ans[2] = add_description;
		} else if (step.equals("Cancel a request")) {
			ans[0] = "david.ha@sapiens.com;gonen.s@sapiens.com;edna.m@sapiens.com;alon.p@sapiens.com;david.b@sapiens.com;stas.p@sapiens.com;ali.b@sapiens.com";
			//ans[0] = "david.ha@sapiens.com";
			ans[1] = "#FFCE73";
			ans[2] = ".";
		} else if (step.equals("Packed")) {
			ans[0] = "david.ha@sapiens.com;gonen.s@sapiens.com;edna.m@sapiens.com;alon.p@sapiens.com;david.b@sapiens.com;stas.p@sapiens.com;ali.b@sapiens.com";
			//ans[0] = "david.ha@sapiens.com";
			ans[1] = "#8CD1E6";
			ans[2] = ".";
		}
	}
	public static final ArrayList<String> runCmd(String cmd,boolean wait){
		ArrayList<String> ans = new  ArrayList<String>();
		try { 
		    Process p=Runtime.getRuntime().exec("cmd /c "+cmd); 
		    if(!wait){
		    	ans.add("Done.");
		    	return ans;
		    }else	
		    	p.waitFor();
		   
		
			BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
			String line=reader.readLine(); 
			while(line!=null) { 
				ans.add(line);
				line=reader.readLine(); 
			} 
		}catch(IOException e1) {
			ans.add("1~"+cmd+" - "+e1.toString());
			return ans;
		}catch(InterruptedException e2) {
			ans.add("1~"+cmd+" - "+e2.toString());
			return ans;
		} 
		ans.add( "0~"+cmd+"\n"+ans);
		return ans;
	} 
	public static final HagStringList runCmdGonen(String cmd,boolean wait){
		HagStringList ans = new HagStringList();
		try { 
		    Process p=Runtime.getRuntime().exec("cmd /c "+cmd); 
		    if(!wait){
		    	ans.add("Done.");
		    	return ans;
		    }else	
		    	p.waitFor();
		   
		
			BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
			String line=reader.readLine(); 
			while(line!=null) { 
				ans.add(line);
				line=reader.readLine(); 
			} 
		}catch(IOException e1) {
			ans.add("1~"+cmd+" - "+e1.toString());
			return ans;
		}catch(InterruptedException e2) {
			ans.add("1~"+cmd+" - "+e2.toString());
			return ans;
		} 
		ans.add( "0~"+cmd+"\n"+ans);
		return ans;
	} 
	public static final ArrayList<String> runCmdWithErr(String cmd,boolean wait){
		ArrayList<String> ans = new  ArrayList<String>();
		try { 
		    Process p=Runtime.getRuntime().exec("cmd /c "+cmd); 
		    if(!wait){
		    	ans.add("Done.");
		    	return ans;
		    }else	
		    	p.waitFor();
		   
			ans.add("STD_ERROR:");
		    BufferedReader readerE=new BufferedReader(new InputStreamReader(p.getErrorStream())); 
			String lineE=readerE.readLine(); 
			while(lineE!=null) { 
				ans.add(lineE);
				lineE=readerE.readLine(); 
			} 
			ans.add("STD_OUT:");
			BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
			String line=reader.readLine(); 
			while(line!=null) { 
				ans.add(line);
				line=reader.readLine(); 
			} 
		}catch(IOException e1) {
			ans.add("1~"+cmd+" - "+e1.toString());
			return ans;
		}catch(InterruptedException e2) {
			ans.add("1~"+cmd+" - "+e2.toString());
			return ans;
		} 
		ans.add( "0~"+cmd+"\n"+ans);
		return ans;
	} 
	public static  ArrayList<String> sendMailViaVb(	String to,
			String cc,
			String subject,
			String content,
			String contentFile
			 )
{

	String outF="\\\\orsayserver\\d\\temp\\sendmail.txt";
	//String contentInFile="\\\\confg1\\d\\make_iso\\vbs_email\\test.txt";
	StringBuilder strB= new StringBuilder("cscript \\\\orsayserver\\vb\\prod\\sendmail.vbs");
	strB.append(" /ToAddress:");
	strB.append("\"").append(to).append("\"");
	strB.append(" /CCAddress:");
	strB.append("\"").append(cc).append("\"");
	strB.append(" /OutputFile:");
	strB.append("\"").append(outF).append("\"");
	strB.append(" /MessageSubject:");
	strB.append("\"").append(subject).append("\"");
	strB.append(" /MessageBody:");
	strB.append("\"").append(content).append("\"");
	if(contentFile!=null) {
		strB.append(" /MessageBodyInFile:");
		strB.append("\"").append(contentFile).append("\"");
	}
	String ccc= strB.toString();
	 ArrayList<String> aa = runCmd(strB.toString(),true);
	
//	\\orsayserver\vb\test\sendmail.vbs /ToAddress:"Gonen.Shoham@sapiens.com"  /OutputFile:"\\orsayserver\d\temp\senmail.txt"  /MessageSubject:"This is the eMail Subject" /MessageBody:"aaa" /MessageBodyInFile:"\\confg1\d:\make_iso\vbs_email\test.txt"	
	
	return aa;
}
	public static String sendMailViaHost1(String from,
			String to,
			String cc,
			String subject,
			String content
			 ) {
	String dateTime = HagUtil.getDateTime("yyyyMMddHHmmssSSS");
	String contentInFile="\\\\confg1\\d\\make_iso\\vbs_email\\"+dateTime+".txt";
	String contentInFile1="``confg1`d`make_iso`vbs_email`"+dateTime+".txt";
	
	HagStringList contentList = new HagStringList();
	contentList.add(content);
	contentList.writeToFile(contentInFile, null,false);
	HagUtil.sleep(2000);
	
	ArrayList<String> bb= HagUtil.sendMailViaVb(to,cc,subject,content,contentInFile1);
	if(bb==null || bb.size()<4) {
		return ("1~running sendMailViaVb with the following params to="+to+", cc="+cc+", subject="+subject+", content="+content+", contentInFile1="+contentInFile1+", the answer should be atleast 4 lines but the answer is "+bb.toString());
	}
	return bb.get(3);
}
	public static final String 			sendMail_hagPre(	String from,String to1,String cc1,String subject,String content){
		if(to1.toUpperCase().endsWith("@SAPIENS.COM"))
			return sendMail_hagNew(	 from, to1, cc1, subject, content);
		else
			return sendMail_hagNew(	 from, to1+"@sapiens.com", cc1, subject, content);
	}
	public static final String 			sendMail_hagNew(	String from,String to1,String cc1,String subject,String content){
		//to1="david.ha@sapiens.com";
		//cc1="david.ha@sapiens.com";
	//	if(HagUtil.serverName != null) {
		//	if(HagUtil.serverName.equalsIgnoreCase("cfg-ri2")) 
		//		return "0~mail not sent sent (cfg-ri2)."; 
		//}
		String mailTo=HagParam.getMailTo();
		if(!mailTo.equalsIgnoreCase("CODE")) {
			to1=mailTo;
			cc1="david.ha@sapiens.com";
		}
			
		if(content==null )
			content=".";
		

		content="Direct link to i_know_what_you_did (logs)<br>http://cfg-ri:8080/hagay/index_i_know_what_you_did.html<br>"+content;
		//@spr1001
	if(1==2) {
			//vbs
			if(to1==null) to1="david.ha@sapiens.com";
			if(cc1==null) cc1="david.ha@sapiens.com";
			if(subject==null) subject=".";
			if(subject==null) subject=".";
			return sendMailViaHost1(cfgTeamMail,to1,cc1,subject,content);
		
	}
		
		try{
			//  System.setProperty("mail.mime.charset", "UTF-8");
			  
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.host", "VM2.sapiens.int");
			//props.setProperty("mail.host", "hqex10.sapiens.int");
			props.setProperty("mail.user", "aaaa");
			props.setProperty("mail.password", "bbbb");
			
			
			  
			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(false);
			Transport transport = mailSession.getTransport();

			MimeMessage message = new MimeMessage(mailSession);
			message.setSubject(subject);
			message.setFrom(new InternetAddress(from));
			message.setContent(content, "text/html");
		
			
			//message.setHeader("Content-Type", "text/plain; charset=UTF-8");
			//message.setSubject(subject, "UTF-8"); //IMPORTANT
			//message.setText(content, "UTF-8"); //IMPORTANT 
			    
			//message.setContent(message, "text/plain; charset=UTF-8");
			HagStringList vec = splitStr2ArrayList(to1,";");
			for(int i =0; i<vec.size();i++)
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(vec.get(i).toString()));
			HagStringList vec1 = splitStr2ArrayList(cc1,";");
			for(int i =0; i<vec1.size();i++){
				message.addRecipient(Message.RecipientType.CC,new InternetAddress(vec1.get(i).toString()));
			}
			transport.connect();
			Transport.send(message);
		}catch(Exception e){
			System.out.println(e.toString());
			return "1~failed to send mail.";
		}
		return "0~mail sent.";
	}
	public static final String 			sendSms(String phoneList,String MailList,String message,String from){
		if(message.indexOf("Run by gonen.s") >-1)
			from="0528399727";
		if(message.indexOf("Run by david.ha") >-1)
			from="0528399706";

		HagStringList sms = new HagStringList();
		StringBuilder ff = new StringBuilder("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\temp\\")
		.append(getDateTime("yyyyMMddHHssSSS")).append(".txt");
		deleteFile(ff.toString(),  false);
		sms.add("SMS_To:"+phoneList);
		sms.add("Mail_To:"+MailList);
		sms.add("Message:"+message);
		sms.add("SMS_From:"+from);
		sms.writeToFile(ff.toString(), null, false);
		//String ans1 = HagUtil.writeArrayListToFileWithRc(ff.toString(), sms, null,false,true);
	//	if(!ans1.startsWith("0~"))
	//		return "1~Failed to create the sms file.";
		String ans2 = HagFtp.put("HQEX10.sapiens.int","SMS_to_Send","Send_SMS",null, ff.toString(),"BIN");
		//HagUtil.p(ans2);
		if(!ans2.startsWith("0~"))
			return "1~Failed to ftp the sms file.";
		sleep(2000);	
		String ans3 = deleteFile(ff.toString(),  true);
		if(!ans3.startsWith("0~"))
			return "1~Failed to delete the sms file after ftp.";
		return "0~Sms will be sent in 5m";
	}		
	public static final void 			sleep(int tt){
		try {
			Thread.currentThread();
			Thread.sleep(tt);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//spr1005
	public static void 		setPreVersion_configTxt(HagRc hagRc,String folderS,String ver1,String ver2,String maint,String update,String hotfix,String dummyHotfix,String svnName){
		HagStringList config = new HagStringList();
		String verS = ver1+"."+ver2+"."+maint+"."+update+"."+hotfix;
		String CDdate = HagUtil.getDateTime("yyyy/MM/dd");
		String CDtime = HagUtil.getDateTime("HH:mm:ss");
		String bisV = "4530";
		String bisM = "00";
		config.add("CDversion="+verS);
		config.add("CDdate="+CDdate);
		config.add("CDtime="+CDtime);
		config.add("REQversion="+verS);
		config.add("REQdate="+CDdate);
		config.add("REQtime="+CDtime);
		config.add("jasperUpdate=Y");
		config.add("hotfix="+hotfix);
		config.add("bisVersion="+bisV);
		config.add("bisMaint="+bisM);
		config.add("hagLog="+ver1+ver2);
		config.add("hagJiraVer="+ver1+ver2+"M"+maint+"("+svnName+")");
		config.add("packageName="+svnName);
		config.add("changeNote="+".");
		config.add("rcVal="+"OK");
		config.add("rcNote="+svnName);
		config.writeToFile(folderS+"\\skel\\config.txt",null,false);
	}
	
	public static void 		setPreVersion_packageTxt(HagRc hagRc,String folderS,String ver1,String ver2,String maint,String svnName){
		String CDdate = HagUtil.getDateTime("yyyy/MM/dd");
		String CDtime = HagUtil.getDateTime("HH:mm:ss");
		HagStringList package1 = new HagStringList();
		package1.add("label="+ver1+ver2+"M"+maint+"("+svnName+") ("+CDdate+"_"+CDtime+")");
		package1.writeToFile(folderS+"\\package.txt",null,false);
	}
	
	public static final String 			simpleDosCmd(String cmdStr,boolean withoutPrefix)	{
		//must start with two words fx:start with javaFunc~simpleDosCmd~
		ArrayList<String> cmd = HagUtil.splitStr2ArrayList(cmdStr,"~");
		int num = cmd.size();
		String[] command = new String[num];
		command[0] = "cmd"; 
		command[1] = "/C"; 
		
		//
		for(int i = 2 ; i< num ; i++){
			if((i==2 || i==3)&& cmd.get(1).equals("robocopy") && cmd.get(i).indexOf(" ")>-1)
				command[i] = "\""+cmd.get(i)+"\"";
			else
				command[i] = cmd.get(i); 
		}
		//
		
		String str =  ""; 
		String err =  ""; 
		StringBuilder result = new StringBuilder(); 
		StringBuilder error = new StringBuilder(); 
		int rc =1;
		try{
		
			Process p = Runtime.getRuntime().exec(command);                                         
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while ((str = stdInput.readLine()) != null)                                             
				result.append("\n    ").append(str);
			while ((err = stdError.readLine()) != null) 
				error.append("\n").append(err);  
			rc = p.exitValue();
		}catch(Exception e){ 
			if(withoutPrefix) return e.getMessage();   
			return rc+"~"+cmdStr+" failed.\n"+e.getMessage();                                            
		} 
		if(error.length()>0){
			if(withoutPrefix) 
				return "failed. "+error;   
			return "1~"+cmdStr+" failed. "+error;    
		}
		String rcStr=rc+"~";
		if(cmdStr.startsWith("javaFunc~simpleDosCmd~robocopy~")){
			if(rc==0 || rc==1 || rc==3)
				rcStr="0~";
			
		}
		if(result.length()>0){
			if(withoutPrefix) return result.toString();   
			return rcStr+cmdStr+result;    
		}
		if(withoutPrefix) return cmdStr; 
		return rcStr+cmdStr;    
	}  
	public static final String 			simpleDosCmd(String cmdStr,boolean gui,String from,boolean withoutPrefix)	{
		if(from.equals("txt") && cmdStr.indexOf("xcopy")>-1){
			if(gui)
				JOptionPane.showMessageDialog(
						null,
						"please call hagay-2831 (xcopy from txt)",
						"Dos command error",
						JOptionPane.ERROR_MESSAGE);
			return "1~please call hagay-2831 (xcopy from txt)";
		}
		//must start with two words fx:start with javaFunc~simpleDosCmd~
		ArrayList<String> cmd = HagUtil.splitStr2ArrayList(cmdStr,"~");
		int num = cmd.size();
		String[] command = new String[num];
		command[0] = "cmd"; 
		command[1] = "/C"; 
		
		//
		for(int i = 2 ; i< num ; i++){
			if((i==2 || i==3)&& cmd.get(1).equals("robocopy") && cmd.get(i).indexOf(" ")>-1)
				command[i] = "\""+cmd.get(i)+"\"";
			else
				command[i] = cmd.get(i); 
		}
		//
		
		String str =  ""; 
		String err =  ""; 
		StringBuilder result = new StringBuilder(); 
		StringBuilder error = new StringBuilder(); 
		int rc =1;
		try{
		
			Process p = Runtime.getRuntime().exec(command);                                         
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while ((str = stdInput.readLine()) != null)                                             
				result.append("\n    ").append(str);
			while ((err = stdError.readLine()) != null) 
				error.append("\n").append(err);  
			rc = p.exitValue();
			String aa="aa";
		}catch(Exception e){ 
			if(gui)
			
				JOptionPane.showMessageDialog(
						null,
						"Failed to run "+cmdStr,
						"Dos command error",
						JOptionPane.ERROR_MESSAGE);
			if(withoutPrefix) return e.getMessage();   
			return rc+"~"+cmdStr+" failed.\n"+e.getMessage();                                            
		} 
		if(error.length()>0){
			if(gui)
				JOptionPane.showMessageDialog(
						null,
						"Failed to run "+cmdStr,
						"Dos command error",
						JOptionPane.ERROR_MESSAGE);
			if(withoutPrefix) return "failed";   
			return "1~"+cmdStr+" failed."+error;    
		}
		String rcStr=rc+"~";
		if(cmdStr.startsWith("javaFunc~simpleDosCmd~robocopy~")){
			if(rc==0 || rc==1 || rc==3)
				rcStr="0~";
			
		}
		if(result.length()>0){
			if(withoutPrefix) return result.toString();   
			return rcStr+cmdStr+result;    
		}
		if(withoutPrefix) return cmdStr; 
		return rcStr+cmdStr;    
	}   
	public static final String 			splitLines(String line,int num,String delim ){
		int len = line.length();
		String line1="";
		for(int i = 0 ; i < len;i=i+50){
			int to = i+num;
			if(len < to)
				to=len;
				
			String temp = line.substring(i,to);
			if(line1.length() == 0)
				line1 = temp;
			else
				line1 = line1+delim+temp;
		}
		return  line1;
	}	
	public static final void 			setContentRiFtpPackWin(StringBuilder winS,	String[] cbgroup1,String[] cbgroup2,String instFromFtp){

		if (cbgroup1 != null && cbgroup1.length != 0) {
			winS.append("Tabs to expose:");
			for (int i = 0; i < cbgroup1.length; i++) {
				winS.append("<br>...");
				winS.append(cbgroup1[i]);
			}
			winS.append("<br>RIFLARE folder:");
			winS.append("<br>...");
			if(cbgroup2 != null && cbgroup2.length > 0 )
				winS.append(cbgroup2[0]);
			else
				winS.append("NO");
			if(instFromFtp.trim().length()>0){
				winS.append("<br>FTP installations:");
				winS.append("<br>...");
				winS.append(instFromFtp);
			}
		}else{
			winS.append("NO");
		}

	}
	//T
	//U
	public static final String 			updateLogFile(	String ver,String add,String step,String done_by,String add_description,String ff,String[] ans){

		String date = getDateTime("dd/MM/yyyy");
		String time = getDateTime("HH:mm:ss");

		String ans1 = replaceStringInFile(
				ff,
				"<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>",
				"<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>\n"
						+ "<tr bgcolor=\""
						+ ans[1]
								+ "\"><td>"
								+ add
								+ "</td><td>"
								+ "ALL"
								+ "</td><td>"
								+ date
								+ "</td><td>"
								+ time
								+ "</td><td>" 
								+ step + "</td><td>" 
								+ done_by + "</td><td>" 
								+ ans[2]
										+ "</td></tr>");

		if (!ans1.startsWith("0~")) {
			sendMail_hagPre(cfgTeamMail,mailList_hag,mailList_hag,
					"cfgMenuWeb_problem","problem in HagUtil.replaceStringInFile - "+ans1);
			return "1~failed to update log.";
		}
		return "0~log updated.";

	}
	public static final String 			updateLogFileRiFtp(	String ver,String platWin,String kkk,String step,String done_by,String ff,String[] ans){

		String date = getDateTime("dd/MM/yyyy");
		String time = getDateTime("HH:mm:ss");
		String color = "#FFFF00";
		if(step.equals("Cancel a request"))
			color = "#FFBBBB";


		String ans1 = replaceStringInFile(
				ff,
				"<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>",
				"<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>\n"
						+ "<tr bgcolor=\""
						+ color+ "\"><td>"
						+ ver+ "</td><td>"
						+ platWin+ "</td><td>"
						+ kkk+ "</td><td>"
						+ date+ "</td><td>"
						+ time+ "</td><td>" 
						+ step + "</td><td>" 
						+ done_by + "</td><td>" 
						+ ans[2]+ "</td></tr>");

		if (!ans1.startsWith("0~")) {
			String ans2 = sendMail_hagPre(cfgTeamMail,mailList_hag,mailList_hag,
					"cfgMenuWeb_problem","problem in HagUtil.replaceStringInFile - "+ans1);
		}
		return "0~";

	}
	public static final void 			updateTimeStamp(	String ff,String str,String rcStr){
		
		String timeStamp = HagUtil.getDateTime("HH:mm:ss");		
		if(str.equals("replaceTomcat_START111111111111")){
			String sourceTomcatFolderS = rcStr;
			File sourceTomcatFolder = new File(sourceTomcatFolderS);
			long sourceTomcatSize = HagUtil.getFolderSize(sourceTomcatFolder)/1000000;
			if(sourceTomcatSize>1000){
//				sourceTomcatSizeS ="<td bgColor=\"#ff0000\">"+sourceTomcatSize+" mb</td>"; 
				replaceStringInFile(ff, str, timeStamp+"<br>will take time<br>tomcat folder size = "+sourceTomcatSize+" mb");
				return ;
			}else{
				replaceStringInFile(ff, str, timeStamp);
				return ;
			}
		}else{
			replaceStringInFile(ff, str, timeStamp);
		}
		if(str.endsWith("_END")){
			String rrrc= "<td>"+str.substring(0,str.length()-3)+"RC";
			if(rcStr.indexOf("#FF0000")<0)
				replaceStringInFile(ff, rrrc, "<td bgColor=\"#00ff00\">OK");
			else
				replaceStringInFile(ff, rrrc, "<td bgColor=\"#ff0000\">Failed-please wait for logs");
		}
		return ;
	}
	//V
	//W
	public static final String 			writeStringToFile(String file,String str){
		try	{
			PrintWriter out =new PrintWriter(
					new BufferedWriter(
							new FileWriter(file)));

			out.println(str);
			out.flush();
			out.close();
		}catch(IOException ioe)	{
			return ioe.toString();
		}
		return "0~writeStringToFile";
	}
	public static final String getFileLastModified(File ff ,String format){
		Date date = new Date(ff.lastModified());
		TimeZone tz = TimeZone.getTimeZone("Asia/Jerusalem");
		SimpleDateFormat logFolder  = new SimpleDateFormat(format);
		logFolder.setTimeZone(tz);	
		String dateStr = logFolder.format(date);
		return dateStr;
	}
	public static final String 			writeStringToFileNoNewLine(String file,String str){
		try	{
			PrintWriter out =new PrintWriter(
					new BufferedWriter(
							new FileWriter(file)));

			out.print(str);
			out.flush();
			out.close();
		}catch(IOException ioe)	{
			return ioe.toString();
		}
		return "0~writeStringToFile";
	}
	public static final void  			writeToRelDiary2(
			String act,
			String plat,
			String subAct,
			String hagJiraVer,
			String hotfix,
			String rc,
			String link2distOrStep,
			String link2jira,
			String note,
			String whoAmI,
			String bisServer,
			String dbid
			){
	
		String diary=HagParam.getDiary();
		if(diary.equalsIgnoreCase("NO")) {
			return;
		}
		//String act="Release";
		String dateTime = HagUtil.getDateTime("yyyy/MM/dd-HH:mm:ss.SSS");
		String environment=dbid;
		//String instLog=".";
		//String distribute="link2dist";
		//String jiraList="link2jira";
		String key1="link2logs~"+dateTime;
		String relLog="link2logs";
		
		StringBuilder stm1=null;
		StringBuilder stm3=null;
		String link2rel = ".";
		if(hotfix.equals("00"))
			hotfix=".";
		if(act.equals("Pack")||act.equals("Replace")){
			link2rel=link2distOrStep;
			link2distOrStep=link2distOrStep;
		}else if(subAct.equals("Request")){
			link2rel="distribute to:<br>"+link2distOrStep+"<br><br>Jira list:<br>"+link2jira;
			//stm1 = new StringBuilder("INSERT INTO "+HagParam.getConfg1DB()+".[dbo].[RI_PRCLINKS] values(")
			//		.append("'").append(key1).append("','")
			//		.append(link2rel).append("')");
			link2distOrStep="distribute to:<br>"+link2distOrStep+"<br><br>Jira list:<br>"+link2jira;
		}
		if(	(act.equals("Pack")) 	|| 
			(act.equals("Replace")) 	|| 
			(act.equals("Release") && plat.equals("WIN") && (subAct.equals("CREONE")||subAct.equals("Request")) )
				){
			String ff = HagUtil.replaceStr(dateTime,"  "," ");
			ff = HagUtil.replaceStr(ff," ","_");
			ff = HagUtil.replaceStr(ff,":","_");
			ff = HagUtil.replaceStr(ff,".","_");
			ff = HagUtil.replaceStr(ff,"/","_");
			ff = HagUtil.replaceStr(ff,"-","_");
				
			String ff1 = cfgMenuWebLoc+"\\logs\\"+ff+".html";
			HagUtil.writeStringToFile(ff1, link2distOrStep);
			//	stm1 = new StringBuilder("INSERT INTO "+HagParam.getConfg1DB()+".[dbo].[RI_PRCLINKS] values(")
			//			.append("'").append(key1).append("','")
			//			.append(link2distOrStep).append("')");
		}else{
			relLog=".";
		}
		
		stm3 = new StringBuilder("INSERT INTO "+HagParam.getConfg1DB()+".[dbo].[RI_PRC] values(")
				.append("'").append(act).append("','")
				.append(subAct).append("','")
				.append(plat).append("','")
				.append(hagJiraVer).append("','")
				.append(hotfix).append("','")
				.append(bisServer).append("','")
				.append(environment).append("','")
				.append(rc).append("','")
				.append(dateTime).append("','")
				.append(whoAmI).append("','")
				.append(relLog).append("','")
				.append(note).append("','")
				.append(".").append("','")
			.append(".").append("')");

		String url="jdbc:sqlserver://CONFG1;Database="+HagParam.getConfg1DB();
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		Connection con = null;
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, "cfg", "cfg09c");
			if(stm1!=null){
				con.createStatement().execute(stm1.toString());
		 		con.commit();
			}
		
			con.createStatement().execute(stm3.toString());
		 	con.commit();
			
		} catch (ClassNotFoundException e) {
			sendMail_hagPre(cfgTeamMail,mailList_devOps+";"+whoAmI,mailList_hag,"error1:"+e.getMessage(),null);
		} catch (SQLException e) {
			sendMail_hagPre(cfgTeamMail,mailList_devOps+";"+whoAmI,mailList_hag,"error2e:"+e.getMessage()+"<br>stm1="+stm1+"<br>stm3="+stm3,null);
		} catch (Exception e) {
			sendMail_hagPre(cfgTeamMail,mailList_devOps+";"+whoAmI,mailList_hag,"error3:"+e.getMessage(),null);
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	
	
	
	//X
	//X
	public static final String 			xCopy(String from,String to){
		from=from.trim();
		to=to.trim();
		if(from.trim().length()<2)
			return "0~no file to copy";
		StringBuilder cmd = new StringBuilder("xcopy~").append(from).append("~").append(to).append("~/E~/I~/K~/Y");
		String ans = simpleDosCmd(cmd.toString(),false);
		return ans;
	} 
	//Y
	//Z
	 public static String  zip(String zipFileName, String dir, String log, String includeFolder,boolean print,boolean gui){
			
			 ArrayList<String> ans =  omtZip( dir, zipFileName,includeFolder,print);
			 writeArrayListToFile(log, ans, null, false);
			 //writeStringToFile(log,ans, null);
			 if(ans.get(ans.size()-1).startsWith("0~")){
				 return "0~zip:\n";
			 }else{
				 if(gui){
			    	JOptionPane.showMessageDialog(
					null,
					"Failed to zip "+dir+" to "+zipFileName,
					"zip error",
					JOptionPane.ERROR_MESSAGE);
		    	}
				 return "1~zip:\n";
			 }
				 
		 }
	 public static ArrayList<String> omtZip(String path,String outputFile,String includeFolder,boolean print)
	 {
		 ArrayList<String> ans= new ArrayList<String>();
	     final int BUFFER = 2048;
	     boolean isEntry = false;
	     ArrayList<String> directoryList = new ArrayList<String>();
	     File f = new File(path);
	    
	     if(f.exists()){
	    	 try {
	             FileOutputStream fos = new FileOutputStream(outputFile);
	             ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(fos));
	             byte data[] = new byte[BUFFER];
	            
	             if(f.isDirectory()){
	                //This is Directory
	            	 if(includeFolder.equalsIgnoreCase("Y")){
	            		 System.out.println("zzz1"+f.getName());
	            		   long lastModify = f.lastModified();
	            		 String directoryName1 = f.getName();
	            		 if(print)
                    		 System.out.println("ZIP: New Directory Entry :"+directoryName1+"/");
	            		 ZipEntry entry = new ZipEntry(directoryName1+"/");
                         String  entryN = entry.getName();
                         ans.add(entryN);
                         entry.setTime(lastModify);
                         zos.putNextEntry(entry);
                         isEntry = true;
                         directoryList.add(directoryName1+"/");
	            	 }
	            	 boolean firstFlag=true;
	                 do{
	                     String directoryName = "";
	                     if(directoryList.size() > 0)
	                     {
	                         directoryName = directoryList.get(0);
	                         //System.out.println("Directory Name At 0 :"+directoryName);
	                     }
	                     String fullPath = path+directoryName; 
	                     if(includeFolder.equalsIgnoreCase("Y") && firstFlag ){
	                    	 fullPath = path;
	                    	 firstFlag=false;
	                     }
	                     File fileList = null;
	                     if(directoryList.size() == 0)
	                     {
	                         //Main path (Root Directory)
	                         fileList = f;
	                     }else
	                     {
	                         //Child Directory
	                         fileList = new File(fullPath);
	                     }
	                     String[] filesName = fileList.list();

	                     int totalFiles = filesName.length;
	                     for(int i = 0 ; i < totalFiles ; i++) {
	                         String name = filesName[i];
	                         File filesOrDir = new File(fullPath+name);
	                         long lastModify = filesOrDir.lastModified();
	                         if(filesOrDir.isDirectory())
	                         {
	                        	 if(print)
	                        		 System.out.println("ZIP: New Directory Entry :"+directoryName+name+"/");
	                             ZipEntry entry = new ZipEntry(directoryName+name+"/");
	                             String  entryN = entry.getName();
	                             ans.add(entryN);
	                             entry.setTime(lastModify);
	                             zos.putNextEntry(entry);
	                             isEntry = true;
	                             directoryList.add(directoryName+name+"/");
	                         }else{
	                        	 if(print)
	                        		 System.out.println("ZIP: New File Entry :"+directoryName+name);
	                             ZipEntry entry = new ZipEntry(directoryName+name);
	                             String  entryN = entry.getName();
	                             ans.add(entryN);
	                             entry.setTime(lastModify);
	                             zos.putNextEntry(entry);
	                             isEntry = true;
	                             FileInputStream fileInputStream = new FileInputStream(filesOrDir);
	                             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, BUFFER);
	                             int size = -1;
	                             while(  (size = bufferedInputStream.read(data, 0, BUFFER)) != -1  ){
	                                 zos.write(data, 0, size);
	                             }
	                             bufferedInputStream.close();
	                             fileInputStream.close();
	                         }
	                        
	                     }
	                     if(directoryList.size() > 0 && directoryName.trim().length() > 0)
	                     {	
	                    	 if(print)
	                    		 System.out.println("Directory removed :"+directoryName);
	                         directoryList.remove(0);
	                     }

	                 }while(directoryList.size() > 0);
	             }else {
	                 //This is File
	                 //Zip this file
	            	 if(print)
	            		 System.out.println("ZIP: Zip this file :"+f.getPath());
	            	 long lastModify = f.lastModified();
	                 FileInputStream fis = new FileInputStream(f);
	                 BufferedInputStream bis = new BufferedInputStream(fis,BUFFER);
	                 ZipEntry entry = new ZipEntry(f.getName());
	                 String  entryN = entry.getName();
	                 ans.add(entryN);
	                 entry.setTime(lastModify);
	                 zos.putNextEntry(entry);
	                 isEntry = true;
	                 int size = -1 ;
	                 while(( size = bis.read(data,0,BUFFER)) != -1)
	                 {
	                     zos.write(data, 0, size);
	                 }
	                 bis.close();
	                 fis.close();
	             }               

	             //CHECK IS THERE ANY ENTRY IN ZIP ? ----START
	             if(isEntry)
	             {
	               zos.close();
	               fos.close();
	              
		         }else{
	                 zos = null;
	                System.out.println("No Entry Found in Zip");
	             }
	             //CHECK IS THERE ANY ENTRY IN ZIP ? ----START
	             ans.add("0~");
	             return ans;
	         }catch(Exception e){
	             e.printStackTrace();
		         ans.add("1~"+e.getMessage());
	             return ans;
	         }
	   
	     }else {
	         System.out.println("File or Directory not found");
	         ans.add("1~File or Directory not found");
             return ans;
	     }
	    
	  }    
	 public static final void writeArrayListToFile(String file,ArrayList<String> list,JPanel panel,boolean unixFormat){
			try	{
				PrintWriter out =new PrintWriter(
									new BufferedWriter(
										 new FileWriter(file)));
				for(int i=0 ; i<list.size();i++){
					String str=list.get(i);
					if(unixFormat)
						if(i==list.size()-1 )
							out.print(str);
						else
							out.print(str+"\n");
					else
						out.println(str);
						
				}
				out.flush();
				out.close();
			}catch(IOException ioe)	{
				if(panel!=null)
				 JOptionPane.showMessageDialog(panel,
		  	             "Can't create file "+file, "I/O Error",
		  	             JOptionPane.ERROR_MESSAGE);
			}
		}
	 public static ArrayList<String>  unzip(String filename,String directory,boolean gui){
		 ArrayList<String> ans=new ArrayList<String>();
		 ans.add("ffffff");
		 try {
			  File zipF = new File(filename);
			  File destinationF = new File(directory);
			  hagUnzip( zipF, destinationF, ans);
			  ans.set(0,"0~unzip:");
			  return ans;
		    } catch (IOException ioe) {
		    	if(gui){
		    		JOptionPane.showMessageDialog(
					null,
					"Failed to extract "+filename+" to "+directory,
					"Extract error",
					JOptionPane.ERROR_MESSAGE);
		    	}
			  ans.set(0, "1~unzip:");
			  return ans;
		
		    }
	 }	

	  public static ArrayList<String> hagUnzip(File zipfile, File directory, ArrayList<String> ans) throws IOException {
			 // ArrayList<String> ans=new ArrayList<String>();
			    ZipFile zfile = new ZipFile(zipfile);
			    Enumeration<? extends ZipEntry> entries = zfile.entries();
			    while (entries.hasMoreElements()) {
			      ZipEntry entry = entries.nextElement();
			      long lastModify=entry.getTime();
			      String  entryN = entry.getName();
			     // if(entryN==null)
			    	  //continue;
			      ans.add(entryN);
			      File file = new File(directory, entry.getName());
			     // file.setLastModified(lastModify);
			      if (entry.isDirectory()) {
			        file.mkdirs();
			        file.setLastModified(lastModify);
			      } else {
			    	  file.getParentFile().mkdirs();
			          InputStream in = zfile.getInputStream(entry);
			        try {
			        	zipCopy(in, file);
			        	 file.setLastModified(lastModify);
			        } finally {
			          in.close();
			          
			         
			        }
			      }
			    }
			    zfile.close();
			    
			  //  while(ans.get(ans.size()-1) == null)
			 //   	ans.remove(ans.size()-1);
			    return ans;
			  }
	  private static void zipCopy(InputStream in, File file) throws IOException {
		    OutputStream out = new FileOutputStream(file);
		    try {
		    	zipCopy(in, out);
		    } finally {
		      out.close();
		    }
		  }
	  private static void zipCopy(InputStream in, OutputStream out) throws IOException {
		    byte[] buffer = new byte[1024];
		    while (true) {
		      int readCount = in.read(buffer);
		      if (readCount < 0) {
		        break;
		      }
		      out.write(buffer, 0, readCount);
		    }
		  }
	  public 	static final void 			hagDebugToFile(String fn,String str){
			//HagUtil.appendToFile(fn, str,"aaa"  ) ;
	  }
	  
		public 	static final void 			setSapiensRegCharSet(HagRc hagRc,String appServer,String sqlServer,String user,String pass,String db,String batchName){
			//check customer
		//	HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "1","aaa"  ) ;
			hagRc.log.add("!*! get partyId from, RIADMIN user !*!");
			String stm1 = "SELECT CURRENT_PARTY_ID FROM RI.RIUSER where USER_IDENTITY = 'RIADMIN'";
			hagRc.log.add("stm = "+stm1);
			HagSQL hagSQL = new HagSQL();
			HagStringList partyId1 = hagSQL.select1col( "SQL" , sqlServer,	 user, pass,db, stm1);
			String partyId= partyId1.get(0).trim();
			hagRc.log.add("answer = "+partyId);
		//	HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "2","aaa"  ) ;
			if(partyId.startsWith("1~") || partyId.startsWith("2~")) {
		//		HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "3","aaa"  ) ;
				hagRc.log.add("1~Failed to get RIADMIN CURRENT_PARTY_ID from RIUSER table.");
				hagRc.rc=1;
				return;
			}
		//	HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "4","aaa"  ) ;
			String change="";
			//warta=130000
			//msig=110000
			//bki=400000
			partyId=partyId.trim();
		//	if(!partyId.equals("130000") && !partyId.equals("110000") && !partyId.equals("400000")) {
		//		HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "5","aaa"  ) ;
		//		hagRc.log.add("0~no need to change sapiens.reg - neither Warta nor MSIG nor BKI environment.");
		//		hagRc.rc=0;
		//		return;
		//	}
			//HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "6","aaa"  ) ;
			if(partyId.equals("130000")){
		//		HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "7","aaa"  ) ;
				change="\"CharSet\"=\"windows-1250\"";
				
			}else if(partyId.equals("400000")){
		//		HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "8","aaa"  ) ;
				change="\"CharSet\"=\"windows-874\"";
			}else if(partyId.equals("110000")){
		//		HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "9","aaa"  ) ;
				change="\"CharSet\"=\"CP1252\"";
			}else {
			//	HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "9a","aaa"  ) ;
				change="\"CharSet\"=\"\"";
			}
			hagRc.log.add("CharSet new value for  is "+change );
		//	HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "10","aaa"  ) ;
			hagRc.log.add(" ");
			///hagRc.log.add("!*! get BIS Location !*!");
			//change sapiens.reg
			///String bisLocation = regGetVal("HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Sapiens\\eMerge Business Integrity Server V4.5R3.0",
			///		"HKEY_LOCAL_MACHINE\\SOFTWARE\\Sapiens\\eMerge Business Integrity Server V4.5R3.0",
			///		"INSTALLDIR");
			//get bis location
			///if(bisLocation==null){
			///	hagRc.log.add("Failed to get BIS INSTALLDIR from registry.");
			///	hagRc.rc=1;
			///	return;
			///}else{
			///	hagRc.log.add("BIS location = "+bisLocation);
			///}
			///hagRc.log.add(" ");
			String bisLocation="\\\\"+appServer+"\\RI\\eMerge\\BIS\\";
			if(appServer.equalsIgnoreCase("ridevblp06"))
				bisLocation="\\\\"+appServer+"\\RI\\eMerge\\eMergeBIS4530\\";
			
			String sapiensRegS = bisLocation+"bin\\sapiens.reg";
			HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", sapiensRegS,"aaa"  ) ;
			HagStringList sapReg = new HagStringList(sapiensRegS,false,"asdas",true);
			HagStringList newSapReg = new HagStringList();
			String lineStatus ="INIT";
			String lastFolder ="INIT";
			String lastVal ="INIT";
			hagRc.log.add(" ");
			hagRc.log.add("!*! Set Charset value in sapiens.reg !*!");
			boolean changeFlag = false;
			boolean oldValueExists = false;
			//HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "11","aaa"  ) ;
			for(int i = 0 ; i < sapReg.size();i++) {
				//HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "12","aaa"  ) ;
				//read line
				String temp =sapReg.get(i);
			
				//check if it is the start of our folders
//				if(temp.indexOf("RI")>0) {
//					System.out.println("temp0="+temp);
//					System.out.println("temp1=[Environment\\"+batchName.toUpperCase()+"]");
//					System.out.println("temp2=[Catalog\\"+batchName.toUpperCase()+"]");
//				}
				if(		temp!=null && 
						temp.trim().length()>0 && 
						(	temp.trim().toUpperCase().equals("[ENVIRONMENT\\"+batchName.toUpperCase()+"]") ||  temp.trim().toUpperCase().equals("[CATALOG\\"+batchName.toUpperCase()+"]") ))  {
					//it is the start of one of our folders
					//HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "13","aaa"  ) ;
					lineStatus ="ENTER_THE_FOLDER";
					lastFolder=temp;
					lastVal ="INIT";
				}else {
					//it is not the start of our folders
					//HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "14","aaa"  ) ;
					if(		temp!=null && 
							temp.trim().length()>0 && 
							temp.startsWith("[")){
						//it  is a start of a folder
						if(lineStatus.equals("IN_THE_FOLDER") || lineStatus.equals("ENTER_THE_FOLDER") ) {
							//HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "15","aaa"  ) ;
							//this starts ends our folders
							lineStatus ="EXIT_THE_FOLDER";
						}
					}else {
						//it is a simple line 
						//HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "16","aaa"  ) ;
						if(lineStatus.equals("IN_THE_FOLDER") || lineStatus.equals("ENTER_THE_FOLDER") ) {
							//a simple line in our folders
							//HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "17","aaa"  ) ;
							lineStatus ="IN_THE_FOLDER";
							if(		temp!=null && 
									temp.trim().length()>0 && 
									temp.startsWith("\"CharSet\"=")){
								//HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "18","aaa"  ) ;
								hagRc.log.add("CharSet Old value for "+lastFolder +" is "+temp );
								//always replace customer value if not our value - changed by at david.b 19/12//2019
								//if(temp.trim().equals("\"CharSet\"=\"windows-1250\"")) {sss
								if(temp.trim().equals(change)) {
									//HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "19","aaa"  ) ;
									hagRc.log.add("!#! ARIA will not change the value is OK. !#!" );
								}else {
									//HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "20","aaa"  ) ;
									oldValueExists = true;
								}
								//HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "21","aaa"  ) ;
								lastVal=temp;
							}
						}
					}
				}
				//HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "22","aaa"  ) ;
				if(lineStatus.equals("EXIT_THE_FOLDER") && lastVal.equals("INIT")) {
					//in my folders charset not found need to add 
					//String newVal = "\"CharSet\"=\"ISO-8859-2\"";
				//sss	String newVal = "\"CharSet\"=\"windows-1250\"";  //david.b 18/12/2019 13:06 mail
					//HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "23","aaa"  ) ;
					String newVal = change;  
					hagRc.log.add("CharSet Old value for "+lastFolder +" not found.");
					hagRc.log.add("!#! ARIA will add the following value to sapiens.reg file. !#!" );
					hagRc.log.add("CharSet New value for "+lastFolder +" is "+newVal);
					newSapReg.add(newVal);
					lineStatus ="INIT";
					changeFlag=true;
				}
				//HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "24","aaa"  ) ;
				//add the line to the new reg
				if(oldValueExists) {
			//sss		newSapReg.add("\"CharSet\"=\"windows-1250\"");
					//HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "25","aaa"  ) ;
					newSapReg.add(change);
					
					oldValueExists=false;
					changeFlag=true;
				}else
					newSapReg.add(temp);
			}
			if(!changeFlag) {
				
				//HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "26","aaa"  ) ;
				hagRc.log.add("Nothing to change in sapiens.reg file, charset values already exists");
		//	}else {
			//	HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "27","aaa"  ) ;
			}
		//	HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "28","aaa"  ) ;
				hagRc.log.add(" ");
				//hagRc.log.add("Need to change in sapiens.reg file, need to add charset values");
				//hagRc.log.add(" ");
				hagRc.log.add("!*! backup sapiens.reg !*!");
				//backup old sapiens.reg
				String sapiensRegBkS = bisLocation+"bin\\sapiens.reg_bkup_"+getDateTime("yyyy_MM_dd_HH_mm_ss");
			//	HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", sapiensRegS,"aaa"  ) ;
				//HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", sapiensRegBkS,"aaa"  ) ;
				String rc1=copyFileViaDos(sapiensRegS,sapiensRegBkS);
			
				
				if(!rc1.startsWith("0~")) {
		//			HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "29","aaa"  ) ;
					hagRc.rc = 1;
					hagRc.log.add("Failed to backup "+sapiensRegS);
					return;
				}
		//		HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "30","aaa"  ) ;
				hagRc.log.add("sapiens.reg backup = "+sapiensRegBkS);
				
				HagUtil.sleep(4000);
				
				//String newSapRegS = newSapReg.convertToString("\n");
//				boolean rc = HagFinalUtil.writeStringToFile(sapiensRegS,newSapRegS, null);
				String rc=newSapReg.writeToFile1(sapiensRegS, null, false);
				if(rc.startsWith("0~")){
		//			HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "31","aaa"  ) ;
					hagRc.log.add("!@! sapiens.reg replaced. !@!");
					hagRc.log.add("setSapiensRegCharSet process done.");
					hagRc.rc=0;
				}else{
			//		HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "32","aaa"  ) ;
					hagRc.log.add("setSapiensRegCharSet process failed.");
					hagRc.rc=1;
				}
		//		HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "33","aaa"  ) ;

		}
		public static final String			regGetVal(String regPath32,String regPath64,String key){
			String ans64 = regGetValSingle(regPath64,key,null);
			if(ans64 != null) return  ans64;
			return regGetValSingle(regPath32,key,null);
		}
		public static final String			regGetValSingle(String regPath,String key,String type){
			if(type==null)
				type="REG_SZ";

			if(regPath==null)
				return null;
			try {
				Process process = Runtime.getRuntime().exec("reg query \""+ regPath + "\" /v " + key);
				DataInputStream os_in = new DataInputStream(process.getInputStream());
				String line = os_in.readLine();
				while(line != null){
					int ind = line.indexOf(type);
					if(ind > -1){
						return   (line.substring(ind+type.length(),line.length())).trim();
					}
					line = os_in.readLine();
				}
				return null;
			}catch(IOException e) {
				return null;
			}
		}
	 //////////////
	
	
	
	
	/*
	public static final String 			createHtmlAccumulativeAddLogs11111111111111(String value) {
		String curr = ""+value;
		if(value.equals("NONE") || value.equals("INIT"))
			curr = "Empty";

		StringBuilder str= new StringBuilder()
		.append("<html><body>")
		.append("<p>Click on link bellow to change iframe content:</p>")
		.append("<a href=\"file:///d:/cfg/hagay/AccumulativeAddLogsV4520-M02-P00.html\" target=\"search_iframe\">AccumulativeAddLogsV4520-M02-P00</a><br>")
		.append("<a href=\"file:///d:/cfg/hagay/AccumulativeAddLogsV4520-M03-P00.html\" target=\"search_iframe\">AccumulativeAddLogsV4520-M03-P00</a><br>")
		.append("<a href=\"file:///d:/cfg/hagay/AccumulativeAddLogsV4520-M04-P00.html\" target=\"search_iframe\">AccumulativeAddLogsV4520-M04-P00</a><br>")
		.append("<a href=\"file:///d:/cfg/hagay/AccumulativeAddLogsV4520-M05-P00.html\" target=\"search_iframe\">AccumulativeAddLogsV4520-M05-P00</a>")
		.append("<table width=\"100%\" height=\"100%\">")
		.append("<tr><td>")
		.append("<iframe src=\"file:///d:/cfg/hagay/AccumulativeAddLogs").append(curr).append(".html\" width=\"100%\" height=\"100%\" name=\"search_iframe\"></iframe>")
		.append("</td></tr>")
		.append("</table></body></html>");
		return str.toString();
	}
	public static final String 			createHtmlAccumulativeAddLogs1111111(String value) {
		//val,str,sel
		String[][] vals = 	{
								{"","",""}, //Select the log:
								{"","",""},	//V4520-M02-P00
								{"","",""},	//V4520-M03-P00
								{"","",""},	//V4520-M04-P00
								{"","",""}	//V4520-M05-P00
							};
		String iframe = value;
		if(value.equals("NONE"))
			return "<html><body></body></html>";
		else if(value.equals("INIT")){
			vals[0][2]=" selected ";
			iframe = "Empty";
		}else if(value.equals("V4520-M02-P00")){
			vals[1][2]=" selected ";
		}else if(value.equals("V4520-M03-P00")){
			vals[2][2]=" selected ";
		}else if(value.equals("V4520-M04-P00")){
			vals[3][2]=" selected ";
		}else if(value.equals("V4520-M05-P00")){
			vals[4][2]=" selected ";
		}
		StringBuilder str= new StringBuilder()
		.append("<html><body bgcolor=\"#ccccbb\">")
		.append("<font size=4 color=\"blue\"><u>Accumulative Add Request:</u></font><br><br>")
		.append("<br><font size=2 color=\"blue\">Select the correct option and submit the form</font>")
		.append("<form name=\"change\">")
	//	.append("<SELECT NAME=\"options\" ONLOAD=\"AccumulativeAddLogsEmpty.html\" ONCHANGE=\"document.getElementById('youriframe').src = this.options[this.selectedIndex].value\">")
		.append("<SELECT NAME=\"options\" ONLOAD=\"document.getElementById('youriframe').src = this.options[this.selectedIndex].value\" ONCHANGE=\"document.getElementById('youriframe').src = this.options[this.selectedIndex].value\">")

		.append("<option value=\"AccumulativeAddLogsEmpty.html\""+vals[0][2]+">Select the log:</option>")
		.append("<option value=\"AccumulativeAddLogsV4520-M02-P00.html\""+vals[1][2]+">V4520-M02-P00</option>")
		.append("<option value=\"AccumulativeAddLogsV4520-M03-P00.html\""+vals[2][2]+">V4520-M03-P00</option>")
		.append("<option value=\"AccumulativeAddLogsV4520-M04-P00.html\""+vals[3][2]+">V4520-M04-P00</option>")
		.append("<option value=\"AccumulativeAddLogsV4520-M05-P00.html\""+vals[4][2]+">V4520-M05-P00</option>")
		.append("</SELECT>")
		.append("</form><br>")
		.append("<iframe src=\"d:\\cfg\\hagay\\AccumulativeAddLogs"+iframe+".html\"  id=\"youriframe\"  width=\"360%\" height=\"680\" scrolling=\"auto\"></iframe>")
		.append("</body></html>");	
		return str.toString();
		}
	 */
}
