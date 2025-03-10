package com.hag.hagay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
//\\orsayserver\d\gonteam\#VBscript_and_SQL\TEST\Requests\
public class HagRequests {
	public String[] titles1111111111111111111111111111111 = { 	"Req_Type",
							"Req_Desc",
							"Req_Subject",
							"Req_Note",
							"Req_Platform",
							"Req_Target_Env",
							"Req_Target_Version",
							"Req_Source_Env",
							"Req_BackupFile",
							"Req_BackupFile_SQL_Version",
							"Req_Customer_Code",
							"Req_Customer_Desc",
							"Req_Customer_Party",
							"Req_Subject_Template_Main",
							"Req_User_Name",
							"Req_User_Email",
							"Req_Requester",
							"Req_BackupFile_RI_Version",
							"Req_Subject_Template_Sec",
							"Req_FTP_Package_Name",
							"Req_DBID", 
							"Req_Server",
							"Req_Envs_To_Install"
						};
	
	public String[][] params = {
			{ "0",	".", "Req_Type",					"Req_Type",						"."                      },
			{ "1",	".", "Req_Desc",					"Req_Desc",						"."                      },
			{ "2",	".", "Req_Subject",					"Req_Subject",					"subject"                },
			{ "3",	".", "Req_Note",					"Req_Note",						"note"                   },
			{ "4",	".", "Req_Platform",				"Req_Platform",					"."                      },
			{ "5",	".", "Req_Target_Env",				"Req_Target_Env",				"tgtEnv"                 },
			{ "6",	".", "Req_Target_Version",			"Req_Target_Version",			"tgtVer"                 },
			{ "7",	".", "Req_Source_Env",				"Req_Source_Env",				"srcEnv"                 },
			{ "8",	".", "Req_BackupFile",				"Req_BackupFile",				"Req_File"               },
			{ "9",	".", "Req_BackupFile_SQL_Version",	"Req_BackupFile_SQL_Version",	"."                      },
			{ "10",	".", "Req_Customer_Code",			"Req_Customer_Code",			"customer"               },
			{ "11",	".", "Req_Customer_Desc",			"Req_Customer_Desc",			"."                      },
			{ "12",	".", "Req_Customer_Party",			"Req_Customer_Party",			"."                      },
			{ "13",	".", "Req_Subject_Template_Main",	"Req_Subject_Template_Main",	"."                      },
			{ "14",	".", "Req_User_Name",				"Req_User_Name",				"."                      },
			{ "15",	".", "Req_User_Email",				"Req_User_Email",				"."                      },
			{ "16",	".", "Req_Requester",				"Req_Requester",				"owner"                  },
			{ "17",	".", "Req_BackupFile_RI_Version",	"Req_BackupFile_RI_Version",	"."                      },
			{ "18",	".", "Req_Subject_Template_Sec",	"Req_Subject_Template_Sec",		"."                      },
			{ "19",	".", "Req_FTP_Package_Name",		"Req_FTP_Package_Name",			"FTP_Package_Name"       },
			{ "20",	".", "Req_DBID",					"Req_DBID",						"Pref_DBID"              },
			{ "21",	".", "Req_Server",					"Req_Server",					"Pref_Server"            },
			{ "22",	".", "Req_Envs_To_Install",			"Req_Envs_To_Install",			"Envs_To_Install"        },
			{ "23",	".", "Req_Long_Desc",				"Req_Long_Desc",				"."                      },
			{ "24",	".", "Req_ExtraDev",				"Req_ExtraDev",					"."                      },
			{ "25",	".", "Req_Comp_To_Release",			"Req_Comp_To_Release",			"Components_To_Release"  },
			{ "26",	".", "Req_As400_Env_To_Install",	"Req_As400_Env_To_Install",		"Envs_To_Install_As400"  },
			{ "27",	".", "Req_When_To_Install",			"Req_When_To_Install",			"."                      },
			{ "28",	".", "Req_Final_Pack",				"Req_Final_Pack",				"."                      },
			{ "29",	".", "Req_Pre_Req",					"Req_Pre_Req",					"Pre_Req"                },
			{ "30",	".", "Req_Aprv",					"Req_Aprv",						"perPhone"                }
			};
	public String[][] paramsExtra = {
			{ "0",	".", "Req_When_To_Install_date",	"Req_When_To_Install_date",		"."                      },
			{ "0",	".", "Req_When_To_Install_time",	"Req_When_To_Install_time",		"."                      }
	};
	//String vals="";
	
	public  HagRequests() {
		
	}
	
	
	public static final String requestsNew_before(String user,String pass,String w1){
		String auth = HagUtil.isAuthorized(w1,user,pass);
		if(!auth.equals("OK")){
			return HagUtil.shortHtml(auth , "red", "bg");
		}
		                                          
		//HagStringList preF= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\requests.txt",true,"*",false);
		  HagSQL hagSQL = new HagSQL();
		  	String stm = "SELECT Req_Type,Req_Desc,Req_Long_Desc from dbo.Requests_Types "
		  			+ "where "
		  			+ "Req_Type_To_Display<>'N' and "
		  			+ "Req_Type_To_Display<>'T' and "
		  			+ "Req_Type_To_Display<>'U' "
		  			+ "order by Req_Type + 0";
		if(user.equalsIgnoreCase("David.Hagay") || user.equalsIgnoreCase("Gonen.Shoham")|| user.equalsIgnoreCase("Danielle.Sapir"))
		  	 stm = "SELECT Req_Type,Req_Desc,Req_Long_Desc from dbo.Requests_Types where "
		  	 		+ "Req_Type_To_Display<>'N' and "
		  	 		+ "Req_Type_To_Display<>'T' "
		  	 		+ "order by Req_Type + 0";

		  	HagStringList results = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,3,"|",null,null);
		//	pre.add("<input type=\"hidden\" name=\"user\" id=\"user\" value = \""+user+"\" >");
	//	pre.add("<input type=\"hidden\" name=\"pass\" id=\"pass\" value = \""+pass+"\" >");
		

		//pre.add("</FORM>");
		//pre.add("<script type=\"text/javascript\">");
		//pre.add("	ddtreemenu.createTree(\"treemenu1\", true)");
		//pre.add("ddtreemenu.createTree(\"treemenu2\", false)");
		//pre.add("</script>");
	//pre.add("</body>");
			//HagStringList preF= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\requests.txt",true,"*",false);
			
		HagStringList pre= new HagStringList();
		pre.add("<table bgcolor=\"#eeeeee\"  border=\"1\"  cellpadding=\"10\" width=\"90%\">");
		pre.add("<tr bgcolor=\"#bbbbbb\"><td>ind</td><td>request</td><td>request desc</td></tr>");
		
		for(int i = 0 ; i < results.size();i++) {
			String temp = results.get(i);
			String w0x=HagUtil.getWord0(temp,"|",0,true);
			String w1x=HagUtil.getWord0(temp,"|",1,true);
			String w2x=HagUtil.getWord0(temp,"|",2,true);
			pre.add("<tr><td>"+w0x+"</td><td><span> <a href=\"distReq.jsp?opt="+w0x+","+user+"\">"+w1x+"</a><span></td><td>"+w2x+"</td></tr>");

		}
		pre.add("</table>");
		return pre.convertToString("");
		
		
	}
	
	public String 	getVersions(String val,String where) {
		
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
		for(int i = 0;i<ans.size();i++) {
			String temp=ans.get(i);
			String temp1="<option value=\""+temp+"\">"+temp+"</option>";
			ans1.add(temp1);
		}
		//addJenkinsPackages(ans1);
		String ans2 = ans1.convertToString("");
		return ans2;
	}
	public void 	addJenkinsPackages(HagStringList ans1) {
		HagStringList list = HagUtil.getFoldersInDirOneLevel("m:\\customers_data\\RI-AIG\\Releases");
		for(int i = 0;i<list.size();i++) {
			String temp=list.get(i);
			String temp1="<option value=\"customers_data\\RI-AIG\\Releases\\"+temp+"(ftp)\">customers_data\\RI-AIG\\Releases\\"+temp+"(ftp)</option>";
			ans1.add(temp1);
		}
	}
	public String 	getCfgMenuWebSimpleUsers1111111111111111111() {
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
	public String 	getFutureDates(int num) {
		String  ans2 = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd  -  EEEEE");
		//	usersArr1[0] = startDate;
			Calendar c = Calendar.getInstance();
		//	c.setTime(sdf.parse(dt));
			String temp1a="<option value=\""+"."+"\">"+"."+"</option>";
			 ans2=ans2+temp1a;
			for(int i = 0 ;i < num;i++) {
				
				String dt = sdf.format(c.getTime());  // dt is now the new date
				String temp1="<option value=\""+dt.substring(0,10)+"\">"+dt+"</option>";
			
				 ans2=ans2+temp1;
				c.add(Calendar.DATE, 1);  // number of days to add
			}
		
			return ans2;
	}
	public String 	getEmpDate() {
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		String dt = sdf.format(c.getTime());  // dt is now the new date
		String temp1=dt+"-00:00";
			
			return temp1;
	}
	public String 	getFutureTimes() {
		String  ans2 = "";
		String[]  ans1 = {".","00:00","01:00","02:00","03:00","04:00","05:00","06:00",
				"07:00","08:00","09:00","10:00","11:00","12:00","13:00",
				"14:00","15:00","16:00","17:00","18:00","19:00","20:00",
				"21:00","22:00","23:00"};
		
		for(int i = 0 ;i < ans1.length;i++) {
				
				
				String temp1="<option value=\""+ans1[i]+"\">"+ans1[i]+"</option>";
			
				 ans2=ans2+temp1;
			
			}
		
			return ans2;
	}
	public String 	getVersions30(String val) {


		HagStringList ans1 = new HagStringList();
		//if(val.equals("O"))
		ans1.add("<option value=\"0800M01\">0800M01</option>");
		ans1.add("<option value=\"0801M01\">0801M01</option>");
		ans1.add("<option value=\"0000M01\">0000M01</option>");
		String ans2 = ans1.convertToString("");
		return ans2;
	}
	public String 	getVersions32(String val) {

		String ff = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\preVersions.txt";
		HagStringList aaa = new HagStringList(ff,true,"*",false);

		HagStringList ans1 = new HagStringList();
		for(int i = 0 ; i < aaa.size();i++){
			String temp = aaa.get(i);
			String w0 = HagUtil.getWord0(temp, "|",0,true);
			String w1 = HagUtil.getWord0(temp, "|",1,true);
			String w0p = pad(w0,30);
			String w1p = pad(w1,30);
			ans1.add("<option value=\""+w1+"|"+w0+"\">"+w1p+" "+w0p+"</option>");
			
		}
	
		String ans2 = ans1.convertToString("");
		return ans2;
	}
	private String 	pad(String org,int len) {
		if(org.length()>=len)
			return org;
		String tgt=""+org;
		for(int i=org.length();i<len;i++) {
			tgt=tgt+"&nbsp;";
		}
		return tgt;
		
	}
	private String 	getTargetEnv(String val) {
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
		  // if(val.equals("O"))
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
	public  String 	getServer(String val) {
        HagSQL hagSQL = new HagSQL();
        //win local
      
        String stm = "SELECT DISTINCT bis_server  FROM "+HagParam.getConfg1DB()+".[dbo].[ri_environments_new] where project <> 'HAGWIDTH' 	ORDER BY [bis_server]";
        HagStringList ans = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm);
        HagStringList ans1 = new HagStringList();
        if(val.equals("O"))
        	ans1.add("<option value=\".\">.</option>");
        for(int i = 0;i<ans.size();i++) {
        	String temp=ans.get(i);
        	String temp1="<option value=\""+temp+"\">"+temp+"</option>";
        	ans1.add(temp1);
        }
    
        String stmExt = "SELECT DISTINCT bis_server  FROM "+HagParam.getConfg1DB()+".[dbo].[ri_environments_EXT] where project <> 'HAGWIDTH' 	ORDER BY [bis_server]";
        HagStringList ansExt = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stmExt);
        HagStringList ansExt1 = new HagStringList();
        for(int i = 0;i<ansExt.size();i++) {
        	String temp=ansExt.get(i);
        	String temp1="<option value=\""+temp+"\">"+temp+"</option>";
        	ansExt1.add(temp1);
        }
        //ext
        String ans2 = ans1.convertToString("") +ansExt1.convertToString("") ;
        return ans2;
	}
	public  String 	getCustomers(String val) {
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
	public  String 	getSqlVersions() {
        HagStringList ans1 = new HagStringList();
       	String temp1="<option value=\"SQL2016\">SQL2016</option>";
       	ans1.add(temp1);
     	String temp2="<option value=\"SQL2017\">SQL2017</option>";
       	ans1.add(temp2);
     	String temp3="<option value=\"SQL2019\">SQL2019</option>";
       	ans1.add(temp3);
        String ans2 = ans1.convertToString("");
        return ans2;
	}
	public  String 	getSqlServer(String val) {
        HagSQL hagSQL = new HagSQL();
     
        
        String stm = "SELECT DISTINCT sql_server  FROM "+HagParam.getConfg1DB()+".[dbo].[ri_environments_new]  where	project <> 'HAGWIDTH' ORDER BY [sql_server]";
        HagStringList ans = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm);
         HagStringList ans1 = new HagStringList();
        if(val.equals("O"))
        	ans1.add("<option value=\".\">.</option>");
        for(int i = 0;i<ans.size();i++) {
        	String temp=ans.get(i);
        	String temp1="<option value=\""+temp+"\">"+temp+"</option>";
        	ans1.add(temp1);
        }
        String stmExt = "SELECT DISTINCT sql_server  FROM "+HagParam.getConfg1DB()+".[dbo].[ri_environments_Ext]  where	project <> 'HAGWIDTH' ORDER BY [sql_server]";
        HagStringList ansExt = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stmExt);
         HagStringList ansExt1 = new HagStringList();
         for(int i = 0;i<ansExt.size();i++) {
        	String temp=ansExt.get(i);
        	String temp1="<option value=\""+temp+"\">"+temp+"</option>";
        	ansExt1.add(temp1);
        }
        String ans2 = ans1.convertToString("")+ansExt1.convertToString("");
        return ans2;
	}
	public  String 	getUser1111111111(String val) {
        HagSQL hagSQL = new HagSQL();
        String stm = "SELECT [user] ,[password] ,[mail] FROM  "+HagParam.getConfg1DB()+".[dbo].[users] ORDER BY [user]";

      	HagStringList results = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,3,"|",null,null);
		   HagStringList ans1 = new HagStringList();
		   if(val.equals("O"))
	        	ans1.add("<option value=\".\">.</option>");
			for(int j=0;j<results.size();j++){
				String temp = results.get(j);
				String temp3 = HagUtil.replaceStr(temp, "|", "   |   ");
				String temp4 = HagUtil.getWord0(temp, "|", 0, true);
				String temp1="<option value=\""+temp4+"\">"+temp4+"</option>";
				ans1.add(temp1);
			}
			String ans2 = ans1.convertToString("");
			return ans2;
	}
	public  String 	getApprovalUser() {
    	   HagStringList ans1 = new HagStringList();
    	   String temp1="<option value=\".\">"+"."+"</option>";
    	   String temp2="<option value=\"YES\">"+"YES"+"</option>";
			ans1.add(temp1);
			ans1.add(temp2);
			
			String ans2 = ans1.convertToString("");
			return ans2;
	}
	public  String 	getNoYesCombo() {
 	   HagStringList ans1 = new HagStringList();
 	   String temp1="<option value=\"YES\">"+"YES"+"</option>";
 	   String temp2="<option value=\"NO\">"+"NO"+"</option>";
			ans1.add(temp1);
			ans1.add(temp2);
			
			String ans2 = ans1.convertToString("");
			return ans2;
	}
	public  void 	setVals(String opt) {
		HagSQL hagSQL = new HagSQL();
		StringBuilder stm = new StringBuilder("SELECT [").append(params[0][2]).append("]");
		for(int i = 1 ;i<params.length;i++)
			stm.append(",[").append(params[i][2]).append("]");
		stm.append(" from dbo.Requests_Types where Req_Type='").append(opt).append("'");
	
		HagStringList results = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm.toString(),params.length,"|",null,null);
		String resultsLine=results.get(0);
		for(int i = 0 ;i<params.length;i++) {
			params[i][1]=HagUtil.getWord0(resultsLine, "|",i,true);
		}
		
	}
	private String 	getInitParams() {
		HagStringList list = new HagStringList();
		for(int i = 0 ; i < params.length;i++) {
			list.add("<input type=\"hidden\" name=\""+params[i][2]+"\" id=\""+params[i][2]+"\" value = \".\" >");
		}
		return list.convertToString(" ");
	}
	public  String 	build(String opt,String user) {
		setVals(opt);
		
		HagStringList list = new HagStringList();
	
	//	list.add("<style>");
	//	list.add(".default tt { }");
		//list.add(".monospace tt { font-family: monospace; }");
		//list.add("</style>");
		//list.add("</head>\");
		list.add("<FORM METHOD=POST name=\"Form\" ACTION=\"requestNew.jsp\">");
		
		list.add(get_prefix(opt));
		//0
		//list.add(get00_Req_Type());
		//1
		//list.add(get01_Req_Desc());
		//2
		//String val = HagUtil.getWord0(vals, "|",2,true);
		//list.add(get02_Req_Subject(val));
		HagStringList list1 = new HagStringList();
		HagStringList list2 = new HagStringList();
		int srcPos=-1;
		int tgtPos=-1;
		
		for(int kk = 0;kk<params.length;kk++) {
			if( params[kk][1].trim().equalsIgnoreCase("I"))
				continue;
			list1.add(getReqLine( kk, user,opt,list2));
		
			if(params[kk][0].equals("5"))
					tgtPos=list1.size()-1;
			if(params[kk][0].equals("7"))
					srcPos=list1.size()-1;
		}
		if(srcPos>-1 && tgtPos>-1) {
			String tgt=list1.get(tgtPos);
			String src=list1.get(srcPos);
			list1.set(tgtPos,src);
			list1.set(srcPos,tgt);
		}
		list1.sortByColor();
		list.append(list1);
		/*
		list.add(get03_Req_Note(3));
		list.add(get04_Req_Platform(4));
		list.add(get05_Req_Target_Env(5));
		list.add(get06_Req_Target_Version(6));
		list.add(get07_Req_Source_Env(7));
		list.add(get08_Req_BackupFile(8));
		list.add(get09_Req_BackupFile_SQL_Version(9));
		list.add(get10_Req_Customer_Code(10));
		list.add(get11_Req_Customer_Desc(11));
		list.add(get12_Req_Customer_Party(12));
		list.add(get13_Req_Subject_Template_Main());
		list.add(get14_Req_User_Name(1));
		list.add(get15_Req_User_Email(15));
		list.add(get16_Req_Requester(16,user));	
		list.add(get17_Req_BackupFile_RI_Version(17));
		list.add(get18_Req_Subject_Template_Sec());
		list.add(get19_Req_FTP_Package_Name(19));
		list.add(get20_Req_DBID(20));
		list.add(get21_Req_Server(21));
		list.add(get22_Req_Envs_To_Install(22));
		*/
		list.add(get_suffix());
		list.add(getInitParams());
		//String aaa="aaa";
		String list2A=list2.convertToString("");
		list.add("<input type=\"hidden\" name=\""+"list2"+"\" id=\""+"list2"+"\" value = \""+list2A+"\" >");
		list.add("<input type=\"hidden\" name=\"user\" id=\"user\" value = \""+user+"\" >");
		String prodTest=HagParam.getStatus();
		if((user.equalsIgnoreCase("David.Hagay") ||user.equalsIgnoreCase("Gonen.Shoham") ) && prodTest.endsWith("PROD"))
			list.add("<input type=\"checkbox\" id=\"test\" name=\"test\" value=\"test\">test");
	
		//list.add("<br>&nbsp;&nbsp;<INPUT TYPE=SUBMIT value=\"send request\"></FORM>");
		
		list.add("<br>&nbsp;&nbsp;<INPUT TYPE=SUBMIT value=\"send request\" onclick=\"document.getElementById('loader').style.display = ''; this.style.display = 'none'\" ></FORM>");
		
		
		list.add("<img id=\"loader\" src=\"Please_wait.gif\" style=\"display: none;\"/>");
	
		return list.convertToString("");
	}
	private int 	getTotal() {
		HagSQL hagSQL = new HagSQL();
		String stm = "SELECT count(*) from dbo.Requests_Types";
	
		ArrayList<String> ans = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm);
		if (	ans == null || ans.size()==0) 
			return  -1;
		else
			return HagUtil.s2i(ans.get(0));
	}
	private String get_prefix(String opt) {
		StringBuilder ans = new StringBuilder();
	
		ans.append("<table >");
		ans.append("<tr >");
		ans.append("<td >");
		ans.append("<table bgColor=\"#cccccc\" border=\"2\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr bgColor=\"#aaaaaa\"><td bgColor=\"#7f8c8d\">protected</td><td bgColor=\"#93f03b\">must</td><td bgColor=\"#fffe21\">optional</td></tr>");
		ans.append("</table>"); 
		ans.append("</td >");
		ans.append("<td >");
		ans.append("&nbsp;&nbsp;&nbsp;&nbsp;");
		ans.append("</td >");
	
		ans.append("<td >");
		ans.append("<table bgColor=\"#86A7C3\" border=\"2\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr ><td ><h3>"+params[0][1]+" - "+	params[23][1]+"<h3></td></tr>");
		ans.append("</table>");
		ans.append("</td >");
		ans.append("</tr >");
		ans.append("</table>");  
		ans.append("<br>");
		if(opt.equals("30")) {
			
			ans.append("<h2><b>Note that Pack to FTP will pack the last released package to the FTP</b></h2>");
			
		}
		ans.append("<br><br>");
		ans.append("<table bgColor=\"#cccccc\" border=\"2\" cellpadding=\"3\" cellspacing=\"3\" style=\"width:95%\">");
		ans.append("<tr bgColor=\"#aaaaaa\">");
		ans.append("<td>field #no</td>");
		//spr1010
		//ans.append("<td>param name</td>");
		ans.append("<td>param note</td>");
		
		ans.append("<td>param </td>");
		//spr1010
		//ans.append("<td>param note</td>");
		ans.append("<td>param name</td>");
		
		ans.append("<td>param RC</td>");
		ans.append("</tr>");
		return ans.toString();
	}
	private String get_temp() {
		StringBuilder ans = new StringBuilder();
		ans.append("<tr><td></td><td></td><td></td><td></td></tr>");
		return ans.toString();
	}
	private String get_suffix() {
		StringBuilder ans = new StringBuilder();
		ans.append("</table>");
		return ans.toString();
	}/*
	private String get00_Req_Type() {
		StringBuilder ans = new StringBuilder();
		//String val = HagUtil.getWord0(vals,"|",0,true)+"-"+HagUtil.getWord0(vals,"|",1,true);
		String val = HagUtil.getWord0(vals,"|",0,true);
		ans.append("<tr bgColor=\"#7f8c8d\">");
		ans.append("<td>Req_Type</td>");
		ans.append("<td><input type=\"text\"  name=\"Req_Type\" id=\"Req_Type\" value=\""+val+"\" readonly style=\"color: #787878;background-color:#cccccc\"></td>");  
		ans.append("<td>index</td>");
		ans.append("<td>.</td>");
		ans.append("</tr>");
		return ans.toString();
	}
	private String get01_Req_Desc() {
		StringBuilder ans = new StringBuilder();
		//String val = HagUtil.getWord0(vals,"|",0,true)+"-"+HagUtil.getWord0(vals,"|",1,true);
		String val = HagUtil.getWord0(vals,"|",1,true);
		ans.append("<tr bgColor=\"#7f8c8d\">");
		ans.append("<td>Req_Desc</td>");
		ans.append("<td><input type=\"text\"  name=\"Req_Desc\" id=\"Req_Desc\" value=\""+val+"\" readonly style=\"color: #787878;background-color:#cccccc\"></td>");  
		ans.append("<td>"+val+"</td>");
		ans.append("<td>.</td>");
		ans.append("</tr>");
		return ans.toString();
	}*/
	private String getBg(String val) {
		if(val.equals("O")) {
			return "<tr bgColor=\"#fffe21\">";
		}
		if(val.equals("M")) {
			return "<tr bgColor=\"#93f03b\">";
		}
		if(val.equals("P")) {
			return "<tr bgColor=\"#7f8c8d\">";
		}
		return "<tr bgColor=\"#7f8c8d\">";

	}
	private String getExtra(String val) {
		
		if(!val.equals("M") && !val.equals("O") && !val.equals("-")) {
			return " readonly style=\"color: #787878;background-color:#cccccc\" ";
		}
		return " ";

	}
	private String getparamNotes(String opt,int ind) {
	//	String desc=HagUtil.getPropertyVal("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\requestsParamsNotes.properties", opt+"#"+params[ind][2]);
		//String desc=HagUtil.getPropertyVal("\\\\orsayserver\\d\\gonteam\\#HYUN01log\\cfg\\cfgMenuWeb\\lists\\requestsParamsNotes.properties", opt+"#"+params[ind][2]);
		String desc=HagUtil.getPropertyVal("\\\\orsayserver\\d\\gonteam\\#VBscript_and_SQL\\"+HagParam.getVbProdOrTest()+"\\#HYUN01log\\cfg\\cfgMenuWeb\\lists\\requestsParamsNotes.properties", opt+"#"+params[ind][2]);
		
		
		// System.out.println("xx="+desc); 	
		if(desc==null)
			return params[ind][3];
		int pos = desc.indexOf("#");
	//	 System.out.println("yy="+pos); 	
		if(pos>-1) {
		//	 System.out.println("zz="+desc); 
			return desc.substring(0,pos);
		}
		return desc;
	}
	private String getReqLine(int ind,String user,String opt,HagStringList list2 ) {
		String aaa=params[ind][1];
		if(params[ind][1].equals("-") )
			return "";
		//if(!params[ind][1].equals("O") && !params[ind][1].equals("M") && !params[ind][1].equals("P")) {
		//	String val = params[ind][1];
		//	String id = params[ind][2];
		//	String idName = " id=\""+id+"\" name=\""+id+"\" "; 
		//	String ext = getExtra(val);
		//	list2.add("<tr><td><input type=\"text\" "+idName+ext+" value=\""+val+"\" size=\"100\">\"</td><td>.</td></tr>");
		//return "";
		//}
		StringBuilder ans = new StringBuilder();
		ans.append("<style type=\"text/css\">");
		ans.append("P {margin-bottom: -0.1em;  margin-top: 0em;} ");
		ans.append("</style>");
		//ans.append("<script type=\"text/javascript\">");
		//ans.append("function GetSet(executionContext) {");
		//		ans.append("	var formContext = executionContext.getFormContext();");
		//		ans.append("	var date = formContext.getAttribute(\"Req_When_To_Install_date\").getValue();");
		//		ans.append("	var time = formContext.getAttribute(\"Req_When_To_Install_time\").getValue();");
		//		ans.append("	formContext.getAttribute(\"Req_When_To_Install\").setValue(\"http://newvalue.com\");");
		//		ans.append("	}");
	
		//ans.append("</script>");
		ans.append(getBg(params[ind][1]));
		ans.append("<td>").append(params[ind][0]).append("</td>");
		
		//spr1010
		//ans.append("<td>").append(params[ind][2]).append("</td>");
		ans.append("<td>").append(getparamNotes(opt,ind)).append("</td>");
		
		ans.append("<td>").append(getReqLineUnique(ind,user)).append("</td>");  
		
		//spr1010
		//ans.append("<td>").append(getparamNotes(opt,ind)).append("</td>");
		ans.append("<td>").append(params[ind][2]).append("</td>");
		
		
		ans.append("<td>.</td>");
		ans.append("</tr>");
		return ans.toString();
	}
	private String getTrgetEnvsTitle() {
		//String AppServer = pad(AppServer,5);
		String AppServer1 = pad("AppServer",13);
		String SqlServer1 = pad("SqlServer",13);
		String dbid1 = pad("Dbid",8);
		String cust1 = pad("Cust",5);
		String owner1 = pad("Owner",20);
		String lastInst1 = pad("lastInst",40);
		return "<P STYLE=\"font-family : monospace; font-size : 12pt\">"+AppServer1+"|"+dbid1+"|"+SqlServer1+"|"+cust1+"|"+owner1+"|"+lastInst1+"|Note</P>";
	}
	private String getReqLineUnique(int ind,String user) {
		String reqType = params[0][1];
		user = user+"@sapiens.com";
		String val = params[ind][1];
		
		String id = params[ind][2];
		String idName = " id=\""+id+"\" name=\""+id+"\" "; 
		String ext = getExtra(val);
		String emp = " value=\"\""; 
		if(!val.equals("-") && !val.equals("P") && !val.equals("M") && !val.equals("O")) {
			 return "<input type=\"text\" "+idName+ext+" value=\""+val+"\" size=\"100\">"; 
		}
		switch (ind) {
			case 0: return "<input type=\"text\" "+idName+ext+" value=\""+val+"\" >";
			case 1: return "<input type=\"text\" "+idName+ext+" value=\""+val+"\" >";
			case 2: return "<input type=\"text\" "+idName+ext+" value=\".\" size=\"100\" >";
			case 3: if( reqType.trim().equals("51")|| reqType.trim().equals("52"))
				return "<select "+idName +ext+">"+HagUtil.getCfgMenuWebSimpleUsers()+"</select>";  
					else 
						return "<input type=\"text\" "+idName+ext+emp+" size=\"100\" >";
			
			
			
			case 4: return "<select "+idName +ext+ "><option value=\"WIN\">WIN</option><option value=\"I5OS\">I5OS</option></select>";
			case 5: return getTrgetEnvsTitle()+ "<select  STYLE=\"font-family : monospace; font-size : 12pt\""+idName +ext+">"+getTargetEnv(val)+"</select>";  
			case 6:  if( reqType.trim().equals("30"))
						return "<select "+idName +ext+">"+getVersions30(val)+"</select>";  
			else if( reqType.trim().equals("32"))
				return "<select "+idName +ext+">"+getVersions32(val)+"</select>";  		
			else
						return "<select "+idName +ext+">"+getVersions(val," ")+"</select>";  
			
			case 7: return  getTrgetEnvsTitle()+ "<select  STYLE=\"font-family : monospace; font-size : 12pt\""+idName +ext+">"+getTargetEnv(val)+"</select>";  
			case 8: return "<input type=\"text\" "+idName+ext+emp+" size=\"100\" >";
			case 9:  if( reqType.trim().equals("101"))
							return "<select "+idName +ext+">"+getSqlVersions()+"</select>"; 
						else
							return "<input type=\"text\" "+idName+ext+emp+" size=\"50\" >";
			case 10: if( reqType.trim().equals("10")|| reqType.trim().equals("21")|| reqType.trim().equals("24")|| reqType.trim().equals("500"))
						return "<select "+idName +ext+">"+getCustomers(val)+"</select>";  
					else
						return "<input type=\"text\" "+idName+ext+emp+" size=\"30\" >";	
			case 11: return "<input type=\"text\" "+idName+ext+emp+" size=\"50\" >";	
			case 12: return "<input type=\"text\" "+idName+ext+emp+" size=\"50\" >";		
			case 13: return "<input type=\"text\" "+idName+ext+emp+" size=\"100\" >";
			case 14: if( reqType.trim().equals("51")|| reqType.trim().equals("52"))
						return "<input type=\"text\" "+idName+ext+emp+" size=\"100\" >";	
					else 
						return "<select "+idName +ext+">"+HagUtil.getCfgMenuWebSimpleUsers()+"</select>";  
			case 15: if( reqType.trim().equals("51") || reqType.trim().equals("52"))
				return "<input type=\"text\" "+idName+ext+emp+" size=\"100\" >";	
			else
				return "<select "+idName +ext+">"+HagUtil.getCfgMenuWebSimpleUsers()+"</select>";  
			case 16: return "<input type=\"text\" "+idName+ext+" value=\""+user+"\" size=\"50\" >";		
			case 17: return "<select "+idName +ext+">"+getVersions(val," ")+"</select>";  
			
			case 19: 
				if( reqType.trim().equals("31") ||reqType.trim().equals("33") )
					return "<select "+idName +ext+">"+getVersions(val," ")+"</select>";  
				else if(reqType.trim().equals("500"))
					return "<select "+idName +ext+">"+getVersions(val," where rtrim( Package_Name)  not like '%.00' ")+"</select>";  
				//return "<select "+idName +ext+">"+getVersions(val," where Package_Name like '%(from_FTP)%' ")+"</select>";
			 else
				return "<input type=\"text\" "+idName+ext+emp+" size=\"100\" >";		
			case 20: return "<input type=\"text\" "+idName+ext+emp+"  >";
			case 21: if( reqType.trim().equals("96") || reqType.trim().equals("24")) 
						return "<select "+idName +ext+">"+getSqlServer(val)+"</select>";  
					else
						return "<select "+idName +ext+">"+getServer(val)+"</select>";  
			case 22: //if(reqType.trim().equals("30") || reqType.trim().equals("31"))
						return "<input type=\"text\" "+idName+ext+" value=\".\"   size=\"100\">";  
					// else
						//return  getTrgetEnvsTitle()+ "<select  STYLE=\"font-family : monospace; font-size : 12pt\""+idName +ext+">"+getTargetEnv(val)+"</select>";  
			case 23: return "future";
			case 24: return "future";
			case 25: return "<input type=\"text\" "+idName+ext+emp+" size=\"100\" >";
			case 26: return "<input type=\"text\" "+idName+ext+" value=\".\"  size=\"100\">";
			case 27: //if( reqType.trim().equals("1") ) 
						return getPromptDate( id, ext); 
					 //else
						//return "<input type=\"text\" "+idName+ext+emp+" size=\"100\" >";
			case 28: return "<select "+idName +ext+">"+getNoYesCombo()+"</select>";  
			case 29: return "<input type=\"text\" "+idName+ext+emp+" size=\"100\" >";
			case 30: return "<select "+idName +ext+">"+getApprovalUser()+"</select>";  
		
		}
		return "";
	}
	private String getPromptDate(String id,String ext){
		
		String idDate = id+"_date";
		String idTime = id+"_time";
	
		String idNameD = " id=\""+idDate+"\" name=\""+idDate+"\" "; 
		String idNameT = " id=\""+idTime+"\" name=\""+idTime+"\" "; 

		String date = "<select "+idNameD +ext+">"+getFutureDates(100)+"</select>";
		String time = "<select "+idNameT +ext+">"+getFutureTimes()+"</select>";
		
		return date+"&nbsp;-&nbsp;"+time;  
	}
	/*
	private String get03_Req_Note(int ind) {
		if(params[ind][1].equals("-"))
			return "";
		StringBuilder ans = new StringBuilder();
		ans.append(getBg(val));
		ans.append("<td>Req_Note</td>");
		ans.append("<td><input type=\"text\"  size=\"100\" name=\"Req_Note\" id=\"Req_Note\" value=\"\" "+getExtra( val)+"></td>");  
		ans.append("<td>install  riLogic</td>");
		ans.append("<td>.</td>");
		ans.append("</tr>");
		return ans.toString();
	}
	private String get04_Req_Platform(int ind) {
		if(params[ind][1].equals("-"))
			return "";
		StringBuilder ans = new StringBuilder();
		ans.append(getBg(val));
		ans.append("<td>Req_Platform</td>");
		ans.append("<td><select name=\"Req_Platform\" "+getExtra( val)+"><option value=\"WIN\">WIN</option><option value=\"I5OS\">I5OS</option></select></td>");  
		ans.append("<td>platform</td>");
		ans.append("<td>.</td>");
		ans.append("</tr>");
		return ans.toString();
	}
	private String get05_Req_Target_Env(int ind) {
		if(params[ind][1].equals("-"))
			return "";
		StringBuilder ans = new StringBuilder();
		ans.append(getBg(val));
		ans.append("<td>Req_Target_Env</td>");
		ans.append("<td><select name=\"Req_Target_Env\" "+getExtra( val)+">"+getTargetEnv(val)+"</select></td>");  
		ans.append("<td>Target environment</td>");
		ans.append("<td>.</td>");
		ans.append("</tr>");
		return ans.toString();
	}	
	private String get06_Req_Target_Version(int ind) {
		if(params[ind][1].equals("-"))
			return "";
		StringBuilder ans = new StringBuilder();
		ans.append(getBg(val));
		ans.append("<td>Req_Target_Version</td>");
		ans.append("<td><select name=\"Req_Target_Version\" "+getExtra( val)+">"+getVersions(val)+"</select></td>");  
		ans.append("<td>select version to install</td>");
		ans.append("<td>.</td>");
		ans.append("</tr>");
		return ans.toString();
	}
	private String get07_Req_Source_Env(int ind) {
		if(params[ind][1].equals("-"))
			return "";
		StringBuilder ans = new StringBuilder();
		ans.append(getBg(params[ind][1]));
		ans.append("<td>Req_Source_Env</td>");
		ans.append("<td><select name=\"Req_Source_Env\" "+getExtra( params[ind][1])+">"+getTargetEnv(params[ind][1])+"</select></td>");  
		ans.append("<td>"+params[ind][3]+"</td>");
		ans.append("<td>.</td>");
		ans.append("</tr>");
		return ans.toString();
	}
	private String get08_Req_BackupFile(int ind) {
		if(val.equals("-"))
			return "";
		StringBuilder ans = new StringBuilder();
		ans.append(getBg(val));
		ans.append("<td>Req_BackupFile</td>");
		ans.append("<td><input type=\"text\"  size=\"100\" name=\"Req_BackupFile\" id=\"Req_BackupFile\" value=\"\" "+getExtra( val)+"></td>");  
		ans.append("<td>Req_BackupFile</td>");
		ans.append("<td>.</td>");
		ans.append("</tr>");
		return ans.toString();
	}
	private String get09_Req_BackupFile_SQL_Version(int ind) {
		if(val.equals("-"))
			return "";
		StringBuilder ans = new StringBuilder();
		ans.append(getBg(val));
		ans.append("<td>future</td>");
		ans.append("<td>future</td>");  
		ans.append("<td>future</td>");
		ans.append("<td>future</td>");
		ans.append("</tr>");
		return ans.toString();
	}
	private String get10_Req_Customer_Code(int ind) {
		if(val.equals("-"))
			return "";
		StringBuilder ans = new StringBuilder();
		ans.append(getBg(val));
		ans.append("<td>Req_Customer_Code</td>");
		ans.append("<td><input type=\"text\"  size=\"30\" name=\"Req_Customer_Code\" id=\"Req_Customer_Code\" value=\"\" "+getExtra( val)+"></td>");  
		ans.append("<td>Req_Customer_Code</td>");
		ans.append("<td>.</td>");
		ans.append("</tr>");
		return ans.toString();
	}
	private String get11_Req_Customer_Desc(int ind) {
		if(val.equals("-"))
			return "";
		StringBuilder ans = new StringBuilder();
		ans.append(getBg(val));
		ans.append("<td>Req_Customer_Desc</td>");
		ans.append("<td><input type=\"text\"  size=\"50\" name=\"Req_Customer_Desc\" id=\"Req_Customer_Desc\" value=\"\" "+getExtra( val)+"></td>");  
		ans.append("<td>Req_Customer_Desc</td>");
		ans.append("<td>.</td>");
		ans.append("</tr>");
		return ans.toString();
	}
	private String get12_Req_Customer_Party(int ind) {
		if(val.equals("-"))
			return "";
		StringBuilder ans = new StringBuilder();
		ans.append(getBg(val));
		ans.append("<td>Req_Customer_Party</td>");
		ans.append("<td><input type=\"text\"  size=\"50\" name=\"Req_Customer_Party\" id=\"Req_Customer_Party\" value=\"\" "+getExtra( val)+"></td>");  
		ans.append("<td>Req_Customer_Party</td>");
		ans.append("<td>.</td>");
		ans.append("</tr>");
		return ans.toString();
	}
	private String get13_Req_Subject_Template_Main() {
		StringBuilder ans = new StringBuilder();
		ans.append("<tr bgColor=\"#7f8c8d\">");
		ans.append("<td>Req_Subject_Template_Main</td>");
		ans.append("<td><input type=\"text\"  size=\"100\" name=\"Req_Desc\" id=\"Req_Desc\" value=\""+get1_Req_Subject_Template_Main()+"\" readonly style=\"color: #787878;background-color:#cccccc\"></td>");  
		ans.append("<td>cm internal</td>");
		ans.append("<td>.</td>");
		ans.append("</tr>");
		return ans.toString();
	}
	private String get14_Req_User_Name(int ind) {
		if(val.equals("-"))
			return "";
		StringBuilder ans = new StringBuilder();
		ans.append(getBg(val));
		ans.append("<td>Req_User_Name</td>");
		ans.append("<td><select name=\"Req_User_Name\" "+getExtra( val)+">"+getUser(val)+"</select></td>");  
		ans.append("<td>Req_User_Name</td>");
		ans.append("<td>.</td>");
		ans.append("</tr>");
		return ans.toString();
	}
	private String get15_Req_User_Email(int ind) {
		if(val.equals("-"))
			return "";
		StringBuilder ans = new StringBuilder();
		ans.append(getBg(val));
		ans.append("<td>Req_User_Email</td>");
		ans.append("<td><select name=\"Req_User_Email\" "+getExtra( val)+">"+getUser(val)+"</select></td>");  
		ans.append("<td>Req_User_Email</td>");
		ans.append("<td>.</td>");
		ans.append("</tr>");
		return ans.toString();
	}
	private String get16_Req_Requester(int ind,String user) {
		user="David.Hagay";
		if(val.equals("-"))
			return "";
		StringBuilder ans = new StringBuilder();
		ans.append(getBg(val));
		ans.append("<td>Req_Requester</td>");
	
		ans.append("<td><input type=\"text\"  name=\"Req_Requester\" id=\"Req_Requester\" value=\""+user+"\" "+getExtra( val)+"></td>");  
	
		ans.append("<td>sent by</td>");
		ans.append("<td>.</td>");
		ans.append("</tr>");
		return ans.toString();
	}
	private String get17_Req_BackupFile_RI_Version(int ind) {
		if(val.equals("-"))
			return "";
		StringBuilder ans = new StringBuilder();
		ans.append(getBg(val));
		ans.append("<td>Req_BackupFile_RI_Version</td>");
		ans.append("<td><input type=\"text\"  size=\"100\" name=\"Req_BackupFile_RI_Version\" id=\"Req_BackupFile_RI_Version\" value=\"\" "+getExtra( val)+"></td>");  
		ans.append("<td>Req_BackupFile_RI_Version</td>");
		ans.append("<td>.</td>");
		ans.append("</tr>");
		return ans.toString();
	}
	private String get18_Req_Subject_Template_Sec() {
		StringBuilder ans = new StringBuilder();
		ans.append("<tr bgColor=\"#7f8c8d\">");
		ans.append("<td>Req_Subject_Template_Sec</td>");
		ans.append("<td><input type=\"text\"  size=\"100\" name=\"Req_Desc\" id=\"Req_Desc\" value=\""+get1_Req_Subject_Template_Sec()+"\" readonly style=\"color: #787878;background-color:#cccccc\"></td>");  
		ans.append("<td>cm internal</td>");
		ans.append("<td>.</td>");
		ans.append("</tr>");
		return ans.toString();
	}
	private String get19_Req_FTP_Package_Name(int ind) {
		if(val.equals("-"))
			return "";
		StringBuilder ans = new StringBuilder();
		ans.append(getBg(val));
		ans.append("<td>Req_FTP_Package_Name</td>");
		ans.append("<td><input type=\"text\"  size=\"50\" name=\"Req_FTP_Package_Name\" id=\"Req_FTP_Package_Name\" value=\"\" "+getExtra( val)+"></td>");  
		ans.append("<td>Req_FTP_Package_Name</td>");
		ans.append("<td>.</td>");
		ans.append("</tr>");
		return ans.toString();
	}
	private String get20_Req_DBID(int ind) {
		if(val.equals("-"))
			return "";
		StringBuilder ans = new StringBuilder();
		ans.append(getBg(val));
		ans.append("<td>Req_DBID</td>");
		ans.append("<td><input type=\"text\"  name=\"Req_DBID\" id=\"Req_DBID\" value=\"\" "+getExtra( val)+"></td>");  
		ans.append("<td>Req_DBID</td>");
		ans.append("<td>.</td>");
		ans.append("</tr>");
		return ans.toString();
	}
	private String get21_Req_Server(int ind) {
		if(val.equals("-"))
			return "";
		StringBuilder ans = new StringBuilder();
		ans.append(getBg(val));
		ans.append("<td>Req_Server</td>");
		ans.append("<td><select name=\"Req_Server\" "+getExtra( val)+">"+getServer(val)+"</select></td>");  
		ans.append("<td>App Server</td>");
		ans.append("<td>.</td>");
		ans.append("</tr>");
		return ans.toString();
	}
	private String get22_Req_Envs_To_Install(int ind) {
		if(val.equals("-"))
			return "";
		StringBuilder ans = new StringBuilder();
		ans.append(getBg(val));
		ans.append("<td>Req_Envs_To_Install</td>");
		ans.append("<td><select name=\"Req_Envs_To_Install\" "+getExtra( val)+">"+getTargetEnv(val)+"</select></td>");  
		ans.append("<td>Req_Envs_To_Install</td>");
		ans.append("<td>.</td>");
		ans.append("</tr>");
		return ans.toString();
	}
	

	private String get1_Req_Subject_Template_Main() {
		return HagUtil.getWord0(vals, "|",13,true);
	}
	private String get1_Req_Subject_Template_Sec() {
		return HagUtil.getWord0(vals, "|",18,true);
	}
	*/
}