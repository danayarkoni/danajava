/*
 			- add add to future-accumulative-add  combobox
 			- add final_pack combobox
 			- dsp backups list and enable adding backup before request to pack hotfix
08/04/18 	- add sql version check when refreshing environments 
 
*/

package com.hag.hagay;









import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Init extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;  
	String colorR 		= "#cccccc";
	String colorG 		= "#00FF00";
	String colorE 		= "#FF0000";
	
	String toAppHag 	;
	String toSqlHag 	;
	String toDbHag 		;
	String toClientHag ;
	String mustRefresh1="0";
	String mustRefresh2="0";
	//String cfgMenuWebVerKeep="init";
	//String toClient33 = "C:\\ProgramData\\Sapiens\\eMerge Client Tools\\DB\\4530.map";

	public Init()
	{
		//cfgMenuWebVerKeep=HagUtil.getPropertyVal(HagUtil.cfgMenuWebLoc+"\\lists\\cfg.list", "cfgMenuWebVer");
		//HagUtil.hagDebugToFile("d:\\temp\\1.txt", "11"  );
		//HagUtil.hagDebugToFile("d:\\temp\\1.txt", HagUtil.serverName  );
				//mustRefresh1=getRefreshFlag();
				String aaaa=HagUtil.runBatch("d:\\cfg\\map\\map.bat", true);
				try {
				//	HagUtil.hagDebugToFile("d:\\temp\\1.txt", "12"  );
					HagUtil.serverName = InetAddress.getLocalHost().getHostName();
				//	HagUtil.hagDebugToFile("d:\\temp\\1.txt", "13"  );
				//	HagUtil.hagDebugToFile("d:\\temp\\1.txt", HagUtil.serverName  );
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				//	HagUtil.hagDebugToFile("d:\\temp\\1.txt", "14"  );
				//	HagUtil.hagDebugToFile("d:\\temp\\1.txt",e.getMessage()  );
				}
				//HagUtil.hagDebugToFile("d:\\temp\\1.txt", "15"  );
			//	if(aaaa.startsWith("0~"))
				//	return "failed to run map file , call devOps team";
	}
	//public String getRefreshFlag() {
	//	HagStringList refresh= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\00Refresh.txt",true,"*",false);
	//	String ans=refresh.get(0).trim();
	//	return ans;
	//}
	public void initHag()
	{
		 toAppHag 	= "RI39-APP";
		 toSqlHag 	= "RI39-SQL";
		 toDbHag 		= "GG";
		 //toClientHag = "C:\\ProgramData\\Sapiens\\eMerge Client Tools\\DB\\4530.map";
		// toClientHag = "D:\\RI\\eMerge\\eMerge Client Tools\\DB\\4530.map";
	}

	
	//A
	//B
	//C
	//D
	
	public String 		debug1(HttpServletRequest request, HttpServletResponse response){
			//String ans2		= HagForms.hatPackRequest();
		//HagUtil.writeStringToFile("d:\\aaaa.html", ans2);
		//return ans2;x
		//String ans2="<HTML><HEAD><TITLE> A Simple Program </TITLE></HEAD><BODY><h3>aaa</h3>  <div >"+
		//"<APPLET CODE=\"HagRequest.class\" WIDTH=550 HEIGHT=125></APPLET>"+
		//"</div></BODY></HTML>";
		//return ans2;
		//String ff = "\\\\ri-archive\\Saptech-Archive\\RI\\svn\\V6R03M03-QA\\ri-mig\\src\\main\\resources\\load\\v050m00_mig.mig";
		//String rc = HagUtil.deleteFile(ff, false, false);
	
		
	//	String ff1 = "d:\\RI-CORE\\V6R03M03-QA\\ri-mig\\src\\main\\resources\\load\\v050m00_mig.mig";
		
	
//		ArrayList<String> cmd1 = new ArrayList<String>();
//		cmd1.add("RI3QA");
//		cmd1.add("cfg-burner");
//		cmd1.add("gon09c");
//		cmd1.add("notepad\n");
//		cmd1.add("d:\\CFG\\TortoiseSVN\\bin\\svn update "+ff1+"\n");
//		ArrayList<String> ans1 = HagRexec.runGroupCmd(cmd1, true);
//		StringBuilder ans44 = new StringBuilder();
	//	String ans4 = HagUtil.runBatch("d:\\CFG\\TortoiseSVN\\bin\\svn update --non-interactive --no-auth-cache --username cfg-burner@sapiens.int --password gon09c "+ff1+"\n",true);
	//	 for(int i = 0 ; i < ans1.size();i++){
	//		 ans44.append(ans1.get(i)).append("<br>");
	//	 }
		//if(true) {
		//	HagUtil.runCmdRemote("RIAPPTEST","sc query RILS-RI\n","Y");
		//crp0000
		 String[] ans=HagUtil.getOsAndBrowser( request);
		 //spr1000
		 
		 
		 
		 
		 
		 
		 
		 // do not forget to load
		// \\HYUNDAI.SAPIENS.COM\HYUN01log\cfg\cfgMenuWeb\hag\l2r1b.html
		 	return "hagtest 02/05/2023 17:58 - RID-2224<br><br>os:"+ans[0]+"<br><br>browser:"+ans[1]+"<br><br>userAgent:"+ans[2]+"<br><br>user:"+ans[3]+"<br><br>ProdTestLock:<br>"+
					HagParam.PrintTitle()+"<br>"+HagParam.PrintVB()+"<br>"+HagParam.PrintDB()+"<br>"+HagParam.PrintMail()+"<br>"+HagParam.PrintDiary()+"<br>";
		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			//}
			/*
		String dateTime = HagUtil.getDateTime("yyyyMMdd_HHmmsss");
		String ff = "\\\\ri-archive\\Saptech-Archive\\RI\\svn\\V6R03M03-QA\\ri-mig\\src\\main\\resources\\load\\v050m00_mig.mig";
		String ff_bk = "\\\\ri-archive\\Saptech-Archive\\RI\\svn\\before_commit\\V6R03M03-QA_"+dateTime+".txt";

		StringBuilder ans = new StringBuilder();

		ans.append("Delete local v050m00_mig.mig file:").append("<br>");
		String ans1 = HagUtil.deleteFile(ff,  false);
		ans.append(ans1).append("<br><br>");

		ans.append("Get v050m00_mig.mig file to local:").append("<br>");
		String ans2 = HagUtil.runCmdRemote("CFG-RI","d:\\CFG\\TortoiseSVN\\bin\\svn update --non-interactive --no-auth-cache --username SAPIENS\\cfg-burner --password gon09c \\\\ri-archive\\Saptech-Archive\\RI\\svn\\V6R03M03-QA\\ri-mig\\src\\main\\resources\\load\n","Y");
		ans.append(ans2).append("<br><br>");

		ans.append("backup v050m00_mig.mig to before_commit folder:").append("<br>");
		String ans3 = HagUtil.copyFileViaDos( ff,ff_bk);
		ans.append(ans3).append("<br><br>");

		HagStringList list = new HagStringList(ff,false,"adfAF",true);
		list.add("10140,10140,com.sapiens.mig.ri.v050m00.RI21804_1-test-"+dateTime);
		
		ans.append("Delete local v050m00_mig.mig file again:").append("<br>");
		String ans4 = HagUtil.deleteFile(ff,  false);
		ans.append(ans4).append("<br><br>");

		
		ans.append("Save new local file:").append("<br>");
		list.writeToFile(ff,null,false);
		ans.append("10140,10140,com.sapiens.mig.ri.v050m00.RI21804_1-test-"+dateTime).append("<br><br>");

	
		ans.append("Commit changes:").append("<br>");
		String ans5 = HagUtil.runCmdRemote("rii-5os","d:\\CFG\\TortoiseSVN\\bin\\svn commit -m \"admin - "+dateTime+"\"  --non-interactive --no-auth-cache --username SAPIENS\\cfg-burner --password gon09c \\\\ri-archive\\Saptech-Archive\\RI\\svn\\V6R03M03-QA\\ri-mig\\src\\main\\resources\\load\n","Y");
		ans.append(ans5).append("<br><br>");
		
		return ans.toString();
	*/
	
	}
	
	public String 		debug2(HttpServletRequest request, HttpServletResponse response){
		
		return "aaa";
		//return HagUtil.copyFolderGonen("\\\\ri39-app\\RI_JAVA\\RIjava_AA\\tomcat", "\\\\ri39-app\\RI_JAVA\\RIjava_AA\\tomcat23\\","ri39-app","AA");
		//return HagDebug.datePrompt_before();
		//return "aaa";
		//return HagAddUser.tempLoad();
	//	String server1="RI43QA";
	//	String bisServer="RI43QA";
		//		HagStringList list = HagUtil.getWmicByName9(bisServer, "java.exe");
	//		String aaa= list.convertToString("<br>");
		//				return aaa;
		//return HagJiraNew.before("a", "a");
		/*
		//String toApp ="RI93QA";
			//	String toDb ="AA";
			//	HagStringList list = HagUtil.getWmicByName(toApp, "java.exe");
			//	String aaa=list.convertToString("<br>");
		String batchName ="AA";
		String riJava = "d:\\ri_java\\RIjava_"+batchName; 
		String server = "RI93QA";
		String server1 = "RI93QA.sapiens.int";
		String server2 = HagUtil.replaceStr(server1, "-", "_");
		String date = HagUtil.getDateTime("yyyyMMddHHmmsss");
		String wmicFile1 = "D:\\CFG\\temp\\taskManager_" + server2 + "_"+date+"_asc.txt";
		String wmicFile2 = "D:\\CFG\\temp\\taskManager_" + server2 + "_"+date+ "_utf8.txt";
		String cmd = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\wmic_list.bat " + wmicFile1 + " " + server1
				+ " gon09c"+" process where name='"+"java.exe"+"' get Commandline,processid,name";
		// String cmd = "d:\\java\\workspace\\CfgMenu\\bat\\wmic_list.bat " +
		// wmicFile1 + " "+ server1 + " gon09c";

		// HagUtil.p(cmd);
		HagStringList list = HagUtil.getTaskListToFile(server, wmicFile1, wmicFile2, cmd);
		for (int i = 0; i < list.size(); i++) {
			String temp2 = list.get(i).toUpperCase();
			String find = (riJava+"\\tomcat\\").toUpperCase();
			int pos = temp2.lastIndexOf(find);
			int pos1 = temp2.lastIndexOf("JAVA.EXE");
			if (pos > -1) {
				String zz = (temp2.substring(pos1+9, temp2.length())).trim();
				return "<td bgColor=\"#00FF00\">RUNNING ("+zz+")</td>";
			}
		}
		return "<td bgColor=\"#FF6600\">NOT FOUND</td>";
	*/
	}
public String 		getServersStatus(HttpServletRequest request, HttpServletResponse response){
	StringBuilder ans = new StringBuilder();
	HagStringList serversList= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\list\\allServers.list",true,"*",false);
	ans.append("<html><body bgColor=\"#cccccc\" >");
	ans.append("<u><h3>VB test</h3></u>");
	ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"debug.jsp\">");
	ans.append("<input type=\"hidden\" name=\"act\" id=\"act\" value = \"debug2\" >");
    ans.append("<table bgcolor=\"#aaaacc\" border=\"1\">");
	ans.append("<tr bgcolor=\"#aaaaaa\"><td>Remote server name</td></tr>");


	for(int i = 0 ; i < serversList.size();i++) {
		String temp=serversList.get(i);
		String server =HagUtil.getWord0(temp, "|",0,true);
		String os =HagUtil.getWord0(temp, "|",1,true);
		if(os.equals("WIN")) {
			ans.append("<tr><td><input type=\"checkbox\" name=\"cb\" id=\"cb\" checked   value=\"").append(server).append("\">"+server+"</td></tr>");
		}
	}
	ans.append("</table><INPUT TYPE=SUBMIT value=\"Run\"></FORM></body></html>");
	return ans.toString();

	}

	public String 	runRemoteCommand_before(){
		StringBuilder ans = new StringBuilder();
		ans.append("<html><body bgColor=\"#cccccc\" >");
		ans.append("<u><h3>run remote command</h3></u>");
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"debug.jsp\">");
		ans.append("<input type=\"hidden\" name=\"act\" id=\"act\" value = \"debug2\" >");
	    ans.append("<table bgcolor=\"#aaaacc\" border=\"1\">");
		ans.append("<tr bgcolor=\"#aaaaaa\"><td>Remote server name</td><td><input type=\"text\" name=\"server\" id=\"server\" value = \"RII-5OS\" maxlength=\"100\" size=\"100\"></td></tr>");
		ans.append("<tr bgcolor=\"#aaaaaa\"><td>VB line</td><td><input type=\"text\" name=\"cmd\" id=\"cmd\" value = \"cscript //nologo d:\\vb\\test.vbs 'aaaaaaa' 'N' \" maxlength=\"100\" size=\"100\"></td></tr>");
		ans.append("<tr bgcolor=\"#aaaaaa\"><td>output file</td><td><input type=\"text\" name=\"output\" id=\"output\" value = \"\\\\RII-5OS\\temp\\vb_output.txt\" maxlength=\"100\" size=\"100\"></td></tr>");
		ans.append("</table><INPUT TYPE=SUBMIT value=\"Run\"></FORM></body></html>");
		return ans.toString();

	}		

	public String 	riServersDetails_before( HttpServletRequest request){
		StringBuilder ans = new StringBuilder();
		ans.append("<html><body bgColor=\"#cccccc\" >");
		ans.append("<u><h3>run remote command</h3></u>");
		String servers = "";
		
		String [] cbgroup1		= request.getParameterValues("cb");
		if(cbgroup1==null || cbgroup1.length==0)
			return HagUtil.shortHtml("You must select at least one version","red","bg");
		for(int i = 0 ; i < cbgroup1.length;i++) {
			String temp = cbgroup1[i];
			String w1 = HagUtil.getWord0(temp,"~", 1,true);
			servers= servers + w1+" ";
		}
		String outFile="\\\\ri-archive\\Saptech-Archive\\cm\\work\\aa\\aaa.txt";
		String vbCmd=" \\\\orsayserver\\vb\\prod\\Servers_Det.vbs ";
		String vbVals=" /Servers:\""+servers+"\" ";
		String vbLine="cscript "+vbCmd+" "+vbVals+" /OutFile:"+outFile;
		
		

		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"runRemoteCommand.jsp\">");
		ans.append("<input type=\"hidden\" name=\"act\" id=\"act\" value = \"runRemoteCommand\" >");
	    ans.append("<table bgcolor=\"#aaaacc\" border=\"1\">");
		ans.append("<tr bgcolor=\"#aaaaaa\"><td>Remote server name</td>"
				+ "<td><input type=\"text\" name=\"server\" id=\"server\" value = \"RII-5OS\" maxlength=\"100\" size=\"100\"></td></tr>");
		ans.append("<tr ><td>VB line</td><td>"
				+ "<input type=\"text\" name=\"cmd\" id=\"cmd\" value = \""+vbLine+"\" maxlength=\"100\" size=\"100\"></td></tr>");
		ans.append("<tr ><td>output file</td>"
				+ "<td><input type=\"text\" name=\"output\" id=\"output\" value = \""+outFile+"\" maxlength=\"100\" size=\"100\"></td></tr>");
		ans.append("</table><INPUT TYPE=SUBMIT value=\"Run\"></FORM></body></html>");
	
		
		
		
		
		
		
		
		
		//cscript  \\orsayserver\vb\prod\Servers_Det.vbs /Servers:"RI33QA RIPERF-APP"  /OutFile:\\ri-archive\Saptech-Archive\cm\work\aa\aaa.txt"
		return ans.toString();

	}	
	//public String 	runVbCmd_before( String str){
	//	return runVbCmd(str);
		

	//}	
	public String 		dspDetails(HagStringList cbEnvs){
		/*
		HagStringList ans1 = new HagStringList();
		StringBuilder stm =new StringBuilder("select * from dbo.ri_environments_new where project = 'kukumuku' ");
		for(int i = 0;i<cbEnvs.size();i++){

			String temp = cbEnvs.get(i);
			String bisServer = HagUtil.getWord0(temp,"~",0,true);
			String batchName = HagUtil.getWord0(temp,"~",1,true);
			stm.append("or (bis_server='").append(bisServer).append("' and db='").append(batchName).append("')");
		}
		String rc= HagJdbc.selectVertical("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm.toString(),ans1);


		return ans1.convertToString(" ");
		 */
		return "hagay - check dspDetails";
	}
	public String 		dspDetails1(HagStringList cbEnvs){
		HagStringList ans1 = new HagStringList();
		StringBuilder stm =new StringBuilder("select * from dbo.ri_environments_new where  status='A' and project = 'kukumuku' ");
		for(int i = 0;i<cbEnvs.size();i++){

			String temp = cbEnvs.get(i);
			String bisServer = HagUtil.getWord0(temp,"~",1,true);
			String batchName = HagUtil.getWord0(temp,"~",2,true);
			
			stm.append("or (bis_server='").append(bisServer).append("' and db='").append(batchName).append("')");
			
		
		}
		String rc= HagJdbc.selectVertical("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm.toString(),ans1);
		String ans11 = ans1.convertToString(" ");
		
		return ans11;
	}
	public String 		dspInstallations(HagStringList cbEnvs){
		/*
		StringBuilder stm =new StringBuilder("select * from RI.cmInstaller order by instDate,instTime ");
		StringBuilder ans =new StringBuilder();
		for(int i = 0;i<cbEnvs.size();i++){
			HagStringList ans1 = new HagStringList();
			String temp = cbEnvs.get(i);
			String batchName 	= HagUtil.getWord0(temp,"~",2,true);
			String appServer 	= HagUtil.getWord0(temp,"~",1,true);
			String sqlServer 	= HagUtil.getWord0(temp,"~",10,true);
			String dbName 		= HagUtil.getWord0(temp,"~",11,true);
			HagUtil.p("temp="+temp);
			HagUtil.p("batchName="+batchName);
			HagUtil.p("appServer="+appServer);
			HagUtil.p("sqlServer="+sqlServer);
			HagUtil.p("dbName="+dbName);
			String rc= HagJdbc.selectSimple("SQL",sqlServer,"cfg","cfg09c",dbName,stm.toString(),ans1,true);

			ans.append("<table bgColor=\"#00ff00\"><tr><td>").append(appServer).append("-").append(batchName).append("</td></tr></table><br>");
			ans.append(ans1.convertToString(" ") );
			ans.append("<br><br>" );

		}
		return ans.toString();
		 */
		return "hagay - check dspInstallations";
	}
	public String 		dspInstallations1(HagStringList cbEnvs){

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
		return ans.toString();
	}
	public String 		dspClientModuleInstallations1(HagStringList cbEnvs){

		StringBuilder stm =new StringBuilder("select * from RI.clientModuleInstaller order by instDate DESC,instTime DESC");
		StringBuilder ans =new StringBuilder();
		for(int i = 0;i<cbEnvs.size();i++){
			HagStringList ans1 = new HagStringList();
			String temp = cbEnvs.get(i);
			String database = HagUtil.getWord0(temp,"~",11,true);
			String sqlServer = HagUtil.getWord0(temp,"~",10,true);
			String rc= HagJdbc.selectSimple("SQL",sqlServer,"cfg","cfg09c",database,stm.toString(),ans1,true);

			ans.append("<table bgColor=\"#00ff00\"><tr><td>").append(sqlServer).append("-").append(database).append("</td></tr></table><br>");
			if(rc.startsWith("9~")){
				ans.append("no clientModule installtion on this environment." );
			}else {
					ans.append(ans1.convertToString(" ") );
			}
			
			
		
			ans.append("<br><br>" );

		}
		return ans.toString();
	}
	public String 		dspScheduleFInstallations1(HagStringList cbEnvs){

		StringBuilder stm =new StringBuilder("select * from RI.scheduleFInstaller order by instDate DESC,instTime DESC");
		StringBuilder ans =new StringBuilder();
		for(int i = 0;i<cbEnvs.size();i++){
			HagStringList ans1 = new HagStringList();
			String temp = cbEnvs.get(i);
			String database = HagUtil.getWord0(temp,"~",11,true);
			String sqlServer = HagUtil.getWord0(temp,"~",10,true);
			String rc= HagJdbc.selectSimple("SQL",sqlServer,"cfg","cfg09c",database,stm.toString(),ans1,true);
			ans.append("<table bgColor=\"#00ff00\"><tr><td>").append(sqlServer).append("-").append(database).append("</td></tr></table><br>");
			
			if(rc.startsWith("9~")){
				ans.append("no scheduleF installtion on this environment." );
			}else {
					ans.append(ans1.convertToString(" ") );
			}
			ans.append("<br><br>" );

		}
		
		return ans.toString();
	}
	public String 		dspStatus(HagStringList cbEnvs){
		/*
		HagStringList ans=new HagStringList();
		StringBuilder aa = new StringBuilder();

		aa.append("<h3>colors table</h3>");
		aa.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		aa.append("<tr border=\"2\"><td bgColor=\"#00ff00\">UP</td><td bgColor=\"#FF6600\">DOWN</td><td bgColor=\"#FF0000\">PROBLEM</td></tr>");
		aa.append("</table>");

		aa.append("<h3>environments status</h3>");
		aa.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		aa.append("<tr border=\"2\" bgColor=\"#ffff00\"><td>Environment</td><td>eMerge listener status</td><td>tomcat service status</td><td>tomcat child process</td></tr>");
		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			String bisServer 	= HagUtil.getWord0(temp,"~",0,true);
			String dbName 		= HagUtil.getWord0(temp,"~",1,true);
			String sqlServer 	= HagUtil.getWord0(temp,"~",2,true);
			String tomcatService = HagUtil.checkTomcatStatus( bisServer, dbName);
			String tomcatProcess = HagUtil.checkTomcatProcess( bisServer, dbName);
			String eMergeListener = HagUtil.checkEmergeListener( bisServer, dbName);
			aa.append("<tr><td bgColor=\"#cccccc\">"+bisServer+"-"+dbName+"</td>");
			aa.append(eMergeListener+tomcatService+tomcatProcess).append("</tr>");
		}
		aa.append("</table>");
		return aa.toString();
		 */
		return "hagay - check dspStatus";
	}
	public String 		dspStatus1(HagStringList cbEnvs){
		HagStringList ans=new HagStringList();
		StringBuilder aa = new StringBuilder();


		aa.append("<body><h3>colors table</h3>");
		aa.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		aa.append("<tr border=\"2\"><td bgColor=\"#00ff00\">green=UP</td><td bgColor=\"#FF6600\">orange=DOWN</td><td bgColor=\"#FF0000\">red=PROBLEM</td></tr>");
		aa.append("</table>");
		aa.append("<h4>if lastInstRC=Failed then there was a false installation after the lastGoodInst and the environment might be damaged.</h4>");
		aa.append("<h3>environments status</h3>");
		aa.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		aa.append("<tr border=\"2\" bgColor=\"#ffff00\"><td>Environment</td><td>eMerge listener status</td><td>tomcat service status</td><td>tomcat child process</td><td>lastGoodInst</td><td>lastInstRC</td><td>tomcat response</td><td>tomcat string</td><td>startup.bat settings</td></tr>");
		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);

			String lastGoodInst 	= HagUtil.getWord0(temp,"~",4,true);
			String falseInst="Failed";
			String falseInst1 	= HagUtil.getWord0(temp,"~",5,true);
			if(!falseInst1.equals(lastGoodInst))
				falseInst="OK";

			String bisServer 	= HagUtil.getWord0(temp,"~",1,true);
			String batchName 		= HagUtil.getWord0(temp,"~",2,true);
			String tomcatPort 		= HagUtil.getWord0(temp,"~",17,true); // 23062024
			String tomcatAns = " <iframe src=\"http://"+bisServer+":"+tomcatPort+"/ri-web\" width=\"100%\" height=\"200\"></iframe> ";

			String tomcatStr = "http://"+bisServer+":"+tomcatPort+"/ri-web";

			String tomcatService = HagUtil.checkTomcatStatus( bisServer, batchName);
			String tomcatProcess = HagUtil.checkTomcatProcess( bisServer, batchName);
			String eMergeListener = HagUtil.checkEmergeListener( bisServer, batchName);
			aa.append("<tr><td bgColor=\"#cccccc\">"+bisServer+"-"+batchName+"</td>");
			aa.append(eMergeListener+tomcatService+tomcatProcess);
			aa.append("<td bgColor=\"#cccccc\">"+lastGoodInst+"</td>");
			aa.append("<td bgColor=\"#cccccc\">"+falseInst+"</td>");
			aa.append("<td>"+tomcatAns+"</td>");
			aa.append("<td bgColor=\"#cccccc\">"+tomcatStr+"</td>");
			
			String javaHome = getJavaHome(bisServer,batchName);
			System.out.println("JAVA_HOME:" + javaHome);
			
			if (javaHome != "JDK-17") {
				aa.append("<td bgColor=\"#cccccc\">").append(getTomcatStartupBat(bisServer,batchName)).append("</td>");
			} else {
				aa.append("<td bgColor=\"#cccccc\">").append(getTomcatStartupBatForJava17(bisServer,batchName)).append("</td>");
			}
		
			
			aa.append("</tr>");
		}
		aa.append("</table></body>");
		return aa.toString();
	}
	
	private boolean isI5osEnvError(String bisServer,String batchName) {
		String riList = getLSTN(bisServer,batchName);
		if(riList==null)
			return true;
		return false;
	}
	public String 		dspStatusI5os1(HagStringList cbEnvs){
		HagStringList ans=new HagStringList();
		StringBuilder aa = new StringBuilder();


		aa.append("<body><h3>colors table</h3>");
		aa.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		aa.append("<tr border=\"2\"><td bgColor=\"#00ff00\">green=UP</td><td bgColor=\"#FF6600\">orange=DOWN</td><td bgColor=\"#FF0000\">red=PROBLEM</td></tr>");
		aa.append("</table>");
		aa.append("<h4>if lastInstRC=Failed then there was a false installation after the lastGoodInst and the environment might be damaged.</h4>");
		aa.append("<h3>environments status</h3>");
		aa.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		aa.append("<tr border=\"2\" bgColor=\"#ffff00\"><td>Environment</td><td>eMerge listener status</td><td>tomcat service status</td><td>lastGoodInst</td><td>lastInstRC</td><td>tomcat response</td><td>tomcat string</td></tr>");
		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);

			String lastGoodInst 	= HagUtil.getWord0(temp,"~",4,true);
			String falseInst="Failed";
			String falseInst1 	= HagUtil.getWord0(temp,"~",5,true);
			if(!falseInst1.equals(lastGoodInst))
				falseInst="OK";

			String bisServer 	= HagUtil.getWord0(temp,"~",1,true);
			String batchName 		= HagUtil.getWord0(temp,"~",2,true);
			String tomcatPort 		= HagUtil.getWord0(temp,"~",10,true);
			String tomcatAns = " <iframe src=\"http://"+bisServer+":"+tomcatPort+"/ri-web\" width=\"100%\" height=\"200\"></iframe> ";

			String tomcatStr = "http://"+bisServer+":"+tomcatPort+"/ri-web";

			String riList = getLSTN(bisServer,batchName);
			if(riList==null) {
				aa.append("<tr><td bgColor=\"#cccccc\">"+bisServer+"-"+batchName+"</td>");
				aa.append("<td bgColor=\"#FF0000\">Environment error,call Hagay or gonen</td><td bgColor=\"#FF0000\">Or exclude this environment from the selected list</td>");
				aa.append("<td bgColor=\"#cccccc\">"+lastGoodInst+"</td>");
				aa.append("<td bgColor=\"#cccccc\">"+falseInst+"</td>");
				aa.append("<td>"+tomcatAns+"</td>");
				aa.append("<td bgColor=\"#cccccc\">"+tomcatStr+"</td>");
				aa.append("</tr>");
			}else {
				String tomcatProcess = checkTomcatProcessI5os( bisServer, batchName);
				String eMergeListener = checkEmergeListenerI5os( bisServer, batchName,riList);
				aa.append("<tr><td bgColor=\"#cccccc\">"+bisServer+"-"+batchName+"</td>");
				aa.append(eMergeListener+tomcatProcess);
				aa.append("<td bgColor=\"#cccccc\">"+lastGoodInst+"</td>");
				aa.append("<td bgColor=\"#cccccc\">"+falseInst+"</td>");
				aa.append("<td>"+tomcatAns+"</td>");
				aa.append("<td bgColor=\"#cccccc\">"+tomcatStr+"</td>");
				aa.append("</tr>");
			}
		}
		aa.append("</table></body>");
		return aa.toString();
	}
	private  String   		checkTomcatProcessI5os(String bisServer,String batchName){
		String user ="RIGONEN1";
		String pass ="GON09C";
		String db2 = getDb2(bisServer,batchName,"LIBTYPE");
		HagI5OS  hagI5OS = new HagI5OS();
		boolean tomcatUp = hagI5OS.isProcessActive(bisServer, user,pass, db2, "QP0ZSPWT");
		if (tomcatUp) 
			return "<td bgColor=\"#00FF00\">UP</td>";
		return "<td bgColor=\"#FF6600\">DOWN</td>";
	}
	private  String   		checkEmergeListenerI5os(String bisServer,String batchName,String listener){
		String user ="RIGONEN1";
		String pass ="GON09C";
		String db2 = getDb2(bisServer,batchName,"LIBTYPE");
		HagI5OS  hagI5OS = new HagI5OS();
		boolean tomcatUp = hagI5OS.isProcessActive(bisServer, user,pass, db2, listener);
		if (tomcatUp) 
			return "<td bgColor=\"#00FF00\">UP</td>";
		return "<td bgColor=\"#FF6600\">DOWN</td>";
	}
	public String 		dspSize(HagStringList cbEnvs){
		/*
		StringBuilder ans =new StringBuilder();
		ans.append("<table border=\"1\"  cellpadding=\"10\">");
		ans.append("<tr bgColor =\"#aaaaaa\"><td>SQL server</td><td>Database</td><td>DB size</td><td>Logsize</td></tr>");
		for(int i = 0;i<cbEnvs.size();i++){
			HagStringList ans1 = new HagStringList();
			String temp = cbEnvs.get(i);
			String sqlServer = HagUtil.getWord0(temp,"~",10,true);
			String dbName = HagUtil.getWord0(temp,"~",11,true);
			String rc= HagJdbc.getDbSize("SQL",sqlServer,"cfg","cfg09c",dbName, ans1);
//			ans.append("<table bgColor=\"#00ff00\"><tr><td>").append(w3).append("-").append(w2).append("</td></tr></table><br>");
			String aa = ans1.convertToString(" ");
			String bb = HagUtil.replaceStr(aa,"<tr>","<tr><td>"+"w3"+"</td>");
			ans.append(bb );

		}
		ans.append("</table>");
		return ans.toString();
		 */
		return "hagay - check dspSize";
	}
	public String 		dspSize1(HagStringList cbEnvs){
		StringBuilder ans =new StringBuilder();
		ans.append("<table border=\"1\"  cellpadding=\"10\">");
		ans.append("<tr bgColor =\"#aaaaaa\"><td>SQL server</td><td>Database</td><td>DB size</td><td>Logsize</td></tr>");

		for(int i = 0;i<cbEnvs.size();i++){
			HagStringList ans1 = new HagStringList();
			String temp = cbEnvs.get(i);
			String db = HagUtil.getWord0(temp,"~",11,true);
			String sqlServer = HagUtil.getWord0(temp,"~",10,true);
			String rc= HagJdbc.getDbSize("SQL",sqlServer,"cfg","cfg09c",db, ans1);
			//			ans.append("<table bgColor=\"#00ff00\"><tr><td>").append(w3).append("-").append(w2).append("</td></tr></table><br>");
			String aa = ans1.convertToString(" ");
			String bb = HagUtil.replaceStr(aa,"<tr>","<tr><td>"+sqlServer+"</td>");
			ans.append(bb );

		}
		ans.append("</table>");
		return ans.toString();

	}
	public String 		dspTomcatResponse1(HagStringList cbEnvs){

		StringBuilder ans =new StringBuilder();
		ans.append("<body ><table border=\"1\"  cellpadding=\"10\">");
		ans.append("<tr bgColor =\"#aaaaaa\"><td>APP server</td><td>batch name</td><td>tomcat response</td><td>note</td></tr>");

		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			String batchName = HagUtil.getWord0(temp,"~",2,true);
			String appServer = HagUtil.getWord0(temp,"~",1,true);
			String tomcatPort = HagUtil.getWord0(temp,"~",15,true);
			//String tomcatAns = "aaaaaaaaaaaaaaaa";
			String tomcatAns = " <iframe src=\"http://"+appServer+":"+tomcatPort+"/ri-web\" width=\"100%\" height=\"100\"></iframe> ";
			ans.append("<tr><td>").append(appServer).append("</td><td>").append(batchName).append("</td><td>").append(tomcatAns).append("</td><td>").append("http://"+appServer+":"+tomcatPort+"/ri-web").append("</td></tr>");

		}
		ans.append("</table></body>");
		return ans.toString();

	}
	public void 		getOsAndBrowser(HttpServletRequest request, HttpServletResponse response){
		String[] ans = HagUtil.getOsAndBrowser(request);
		System.out.println("os="+ans[0]);
		System.out.println("browser="+ans[1]);
	}
	public String 		distEnvs(HttpServletRequest request, HttpServletResponse response){
		String cfgMenuWebUser 	= request.getParameter("cfgMenuWebUser");
		String cfgMenuWebPass 	= request.getParameter("cfgMenuWebPass");
		String value = request.getParameter( "value" );

		
		String act = request.getParameter( "act1" );
	
		if(act.equals("Dev: Select action to run"))
			act = request.getParameter( "act2" );
		if(act.equals("Request: Select action to run"))
			act = request.getParameter( "act3" );
		if(act.equals("CM: Select action to run"))
			act = request.getParameter( "act3" );
		act=act.trim();
		if(act.startsWith("Set oded.r key")){
			String auth = HagUtil.isAuthorized("Set oded.r key",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Set owner")){
			String auth = HagUtil.isAuthorized("Set owner",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Set note field")){
			String auth = HagUtil.isAuthorized("Set note field",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Run SQL stm on envs")){
			String auth = HagUtil.isAuthorized("Run SQL stm on envs",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Run SQL stm on envs")){
			String auth = HagUtil.isAuthorized("Run SQL stm on envs",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Commit load files to svn")){
			String auth = HagUtil.isAuthorized("Commit load files to svn",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Send hotfix request")){
			String auth = HagUtil.isAuthorized("Send hotfix request",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("clientModule - Send pack request")){
			String auth = HagUtil.isAuthorized("clientModule - Send pack request",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("clientModuleFutureRelease - Send pack request")){
			String auth = HagUtil.isAuthorized("clientModuleFutureRelease - Send pack request",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		
		if(act.startsWith("scheduleF - Send pack request")){
			String auth = HagUtil.isAuthorized("scheduleF - Send pack request",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("clientModule - Run mig")){
			String auth = HagUtil.isAuthorized("clientModule - Run mig",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Cancel request")){
			String auth = HagUtil.isAuthorized("Cancel request",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		
		
		if(act.startsWith("Reopen request")){
			String auth = HagUtil.isAuthorized("Cancel request",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("clientModule - D") || act.startsWith("clientModule - C") ){
				return HagUtil.shortHtml("Future option" , "red", "bg");
		}
	//	if(act.startsWith("Gard-Start tomcat and eMerge listener")){
	//		String auth = HagUtil.isAuthorized("Gard-Start tomcat and eMerge listener",cfgMenuWebUser,cfgMenuWebPass);
		//	if(!auth.equals("OK")){
	//			return HagUtil.shortHtml(auth , "red", "bg");
	//		}
	//	}
		if(act.startsWith("Build load file")){
			String auth = HagUtil.isAuthorized("Build load file",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
	
		if(act.startsWith("Backup DB With Notes (Initial,Vanilla...)")){
			String auth = HagUtil.isAuthorized("Backup DB With Notes (Initial,Vanilla...)",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Replace my RI33 private env with this env")){
			String auth = HagUtil.isAuthorized("Replace my RI33 private env with this env",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Replace my private env with this env")){
			String auth = HagUtil.isAuthorized("Replace my private env with this env",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		//if(act.startsWith("Read tomcat startup.bat")){
		//	String auth = HagUtil.isAuthorized("Read tomcat startup.bat",cfgMenuWebUser,cfgMenuWebPass);
		//	if(!auth.equals("OK")){
		//		return HagUtil.shortHtml(auth , "red", "bg");
		//	}
		//}
		if(act.startsWith("Replace my RI43 private env with this env")){
			String auth = HagUtil.isAuthorized("Replace my RI43 private env with this env",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Append Dev elements to my private env")){
			String auth = HagUtil.isAuthorized("Append Dev elements to my private env",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Change Env Owner")){
			String auth = HagUtil.isAuthorized("Change Env Owner",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Change AZURE Env Owner")){
			String auth = HagUtil.isAuthorized("Change AZURE Env Owner",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Append Dev elements to my RI33 private env")){
			String auth = HagUtil.isAuthorized("Append Dev elements to my RI33 private env",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Append Dev elements to my RI43 private env")){
			String auth = HagUtil.isAuthorized("Append Dev elements to my RI43 private env",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}

		if(act.startsWith("Replace env (CM)")){
			String auth = HagUtil.isAuthorized("Replace env (CM)",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Refresh env by owner")){
			String auth = HagUtil.isAuthorized("Refresh env by owner",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("ReplaceEnvironment")){
			String auth = HagUtil.isAuthorized("ReplaceEnvironment",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Duplicate mig entry")){
			String auth = HagUtil.isAuthorized("Duplicate mig entry",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Cancel mig (change type to 40)")){
			String auth = HagUtil.isAuthorized("Cancel mig (change type to 40)",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Gard-Install RI package")){
			String auth = HagUtil.isAuthorized("Gard-Install RI package",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Gard-Replace database from cloud")){
			String auth = HagUtil.isAuthorized("Gard-Replace database from cloud",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Gard-Replace database from RI-server")){
			String auth = HagUtil.isAuthorized("Gard-Replace database from RI-server",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Gard-Replace database from bk file")){
			String auth = HagUtil.isAuthorized("Gard-Replace database from bk file",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Stop tomcat")){
			String auth = HagUtil.isAuthorized("Stop tomcat",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Stop eMerge listener")){
			String auth = HagUtil.isAuthorized("Stop eMerge listener",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		if(act.startsWith("Stop tomcat and eMerge listener")){
			String auth = HagUtil.isAuthorized("Stop tomcat and eMerge listener",cfgMenuWebUser,cfgMenuWebPass);
			if(!auth.equals("OK")){
				return HagUtil.shortHtml(auth , "red", "bg");
			}
		}
		//no env
		//if(act.equals("Create HotFix package"))				    return sendReqCreateHotFix( cfgMenuWebUser,cfgMenuWebPass,  response);


		//with envs

		String [] cbEnvs	= request.getParameterValues("cb");
		if	(	(cbEnvs == null || cbEnvs.length == 0) && 
				!act.equals("Send hotfix request")  &&  
				!act.equals("clientModule - Send pack request")  &&  
				!act.equals("clientModuleFutureRelease - Send pack request")  &&  
				!act.equals("scheduleF - Send pack request")  &&  
				!act.equals("clientModule - Run mig")  &&  
				!act.equals("Append Dev elements to my private env")
			){
			return "Please select at least one environment";
		}
		String cbList="";
		HagStringList xxx = new HagStringList();
		if (cbEnvs != null && cbEnvs.length != 0) {
			for (int i = 0; i < cbEnvs.length; i++) {
				cbList = cbList + "<br>" + cbEnvs[i];
				xxx.add(cbEnvs[i]);
			}
		}


		if(	act.startsWith("----------")			 )
			return none(act);
		
		
		if(act.equals("Display environment details"))  			return dspDetails(xxx);//del
		if(act.equals("Display environment installations"))		return dspInstallations(xxx);//del
		if(act.equals("Display database size"))					return dspSize(xxx);//del
		if(act.equals("Display environment status"))			return dspStatus(xxx);//del
		if(act.equals("Display env details"))					return dspDetails1(xxx);
		if(act.equals("All in one"))							return AllInOne.before(xxx);
		if(act.equals("Display env installations"))				return dspInstallations1(xxx);
		if(act.equals("Display clientModule installations"))				return dspClientModuleInstallations1(xxx);
		if(act.equals("Display scheduleF installations"))				return dspScheduleFInstallations1(xxx);
		if(act.equals("Run preRelease installation on remote"))				return RunPreReleaseOnRemote.before(xxx,cfgMenuWebUser);
		if(act.equals("Display env status"))					return dspStatus1(xxx);
		if(act.equals("Display db size"))						return dspSize1(xxx);
		if(act.equals("Display tomcat response"))				return dspTomcatResponse1(xxx);
		if(act.equals("Stop eMerge listener"))					return stopListener1(xxx,cfgMenuWebUser);
		if(act.equals("Start eMerge listener"))					return startListener1(xxx);
		if(act.equals("Stop tomcat"))							return stopTomcat1(xxx,cfgMenuWebUser);
		if(act.equals("I5OS-Stop tomcat"))						return stopTomcatI5os1(xxx);
		if(act.equals("I5OS-Start tomcat"))						return startTomcatI5os1(xxx);
		if(act.equals("I5OS-Stop tomcat and eMerge listener"))	return stopTomcatAndListenerI5os1(xxx);
		if(act.equals("I5OS-Start tomcat and eMerge listener"))	return startTomcatAndListenerI5os1(xxx);
		if(act.equals("I5OS-Display env status"))				return dspStatusI5os1(xxx);
		if(act.equals("I5OS-Replace War"))						return runDevOptionsPre(xxx,cfgMenuWebUser,cfgMenuWebPass,"Replace War I5OS","devReplaceWarI5os","I5OS");
		if(act.equals("I5OS-Replace Jar"))						return runDevOptionsPre(xxx,cfgMenuWebUser,cfgMenuWebPass,"Replace Jar I5OS","devReplaceJarI5os","I5OS");
		//if(act.equals("I5OS-Replace War"))				        return replaceWarI5os1(xxx,cfgMenuWebUser);
		if(act.equals("Start tomcat"))							return startTomcat1(xxx,cfgMenuWebUser);
		if(act.equals("Start tomcat and eMerge listener"))		return startTomcatListener1(xxx,cfgMenuWebUser);
		if(act.equals("Stop tomcat and eMerge listener"))		return stopTomcatListener1(xxx,cfgMenuWebUser);
		//if(act.equals("Replace Jar"))							return replaceJar(xxx,cfgMenuWebUser,cfgMenuWebPass);
		//if(act.equals("Replace War"))							return replaceWar(xxx,cfgMenuWebUser,cfgMenuWebPass);
		if(act.equals("Mark environment as don't mess"))		return setDontMess(xxx,cfgMenuWebUser,cfgMenuWebPass,true);
		if(act.equals("Clear environment mark"))				return setDontMess(xxx,cfgMenuWebUser,cfgMenuWebPass,false);
		if(act.equals("Start environment I-WAY (RUN)"))			return startIwayEnv(xxx, response,value);
		if(act.equals("Start AZURE environment I-WAY (RUN)"))			return startAzureIwayEnv(xxx, response,value);
		if(act.equals("Start server I-WAY (Administration)"))	return startIwayServer( xxx, response,value);
		//if(act.equals("Replace my private env with this env"))	return replacePrivateEnv( xxx, cfgMenuWebUser,response,value,"RI33QA","RI34QA");
		if(act.equals("Backup DB With Notes (Initial,Vanilla...)"))	return backupDbWithNotes( xxx, cfgMenuWebUser,response,value);
		//if(act.equals("Replace my RI33 private env with this env"))	return replacePrivateEnv( xxx, cfgMenuWebUser,response,value,"RI33QA","RI34QA");
		///if(act.equals("Replace my RI43 private env with this env"))	return replacePrivateEnv( xxx, cfgMenuWebUser,response,value,"RI43QA","RI44QA");
		if(act.equals("Replace my private env with this env"))	return ReplaceEnvironment.ReplaceMyPrivateEnvWithThisEnv( xxx, cfgMenuWebUser,request,value);
		//if(act.equals("Replace my private env with this env"))	return "future";
		//if(act.equals("Append Dev elements to my RI33 private env"))	return appendDevElementsToMyPrivateEnv( xxx, cfgMenuWebUser,response,value);
		//if(act.equals("Append Dev elements to my RI43 private env"))	return appendDevElementsToMyPrivateEnv( xxx, cfgMenuWebUser,response,value);
		if(act.equals("Append Dev elements to my private env"))	return ReplaceEnvironment.appendDevElementsToMyPrivateEnv(  cfgMenuWebUser,request,value);
		if(act.equals("Change Env Owner"))						return changeEnvOwnerPre(xxx,  cfgMenuWebUser,response,value,"WIN");
		if(act.equals("Change AZURE Env Owner"))						return changeEnvOwnerPre(xxx,  cfgMenuWebUser,response,value,"AZURE");
		
		//if(act.equals("Append Dev elements to my private env"))	return "FUTURE";

		if(act.equals("Replace env (CM)"))						return ReplaceEnvironment.replaceCmEnvPre( xxx, cfgMenuWebUser,request,value,"devOps");
		if(act.equals("Refresh env by owner"))					return ReplaceEnvironment.replaceCmEnvPre( xxx, cfgMenuWebUser,request,value,"tls");
		
		if(act.equals("ReplaceEnvironment"))					return ReplaceEnvironment.ReplaceEnvironmentUsingVb( xxx, cfgMenuWebUser,request,value);
		if(act.equals("UpdateLastInstallation"))				return updateLastinstallation(xxx);
		if(act.equals("Check this env tomcat folder size"))		return getEnvTomcatSize( xxx, cfgMenuWebUser,response,value);
		
		//if(act.equals("Read tomcat startup.bat"))				return readTomcatStartupBat( xxx, cfgMenuWebUser,response,value);
		
		if(act.equals("Replace Jar"))							return runDevOptionsPre(xxx,cfgMenuWebUser,cfgMenuWebPass,"Replace Jar","devReplaceJar","WIN");
		if(act.equals("Replace War"))							return runDevOptionsPre(xxx,cfgMenuWebUser,cfgMenuWebPass,"Replace War","devReplaceWar","WIN");
		if(act.equals("Open Core Splits"))						return runDevOptionsPre(xxx,cfgMenuWebUser,cfgMenuWebPass,"Delete ri-logs","openCoreSplits","WIN");
		if(act.equals("Close Core Splits"))						return runDevOptionsPre(xxx,cfgMenuWebUser,cfgMenuWebPass,"Delete ri-logs","closeCoreSplits","WIN");
		if(act.equals("Compile cust IOM"))						return runDevOptionsPre(xxx,cfgMenuWebUser,cfgMenuWebPass,"Compile cust IOM","compileCustIom","WIN");
		if(act.equals("Delete ri-logs"))						return runDevOptionsPre(xxx,cfgMenuWebUser,cfgMenuWebPass,"Delete ri-logs","delRiLogs","WIN");
		if(act.equals("Set log4j set level=DEBUG"))				return runDevOptionsPre(xxx,cfgMenuWebUser,cfgMenuWebPass,"Set log4j set level=DEBUG","setLog4jDebug","WIN");
		if(act.equals("Set log4j set level=ERROR"))				return runDevOptionsPre(xxx,cfgMenuWebUser,cfgMenuWebPass,"Set log4j set level=ERROR","setLog4jError","WIN");
		if(act.equals("Set tomcat start DEBUG"))				return runDevOptionsPre(xxx,cfgMenuWebUser,cfgMenuWebPass,"Set tomcat start DEBUG","setTomcatDebug","WIN");
		if(act.equals("Set tomcat start OPT"))					return runDevOptionsPre(xxx,cfgMenuWebUser,cfgMenuWebPass,"Set tomcat start OPT","setTomcatOpt","WIN");
		//
		if(act.equals("Install RI-logic"))						return sendReqInstallLogic( xxx,cfgMenuWebUser,cfgMenuWebPass,  response);
		if(act.equals("Replace DB from backup"))						return sendReplaceDbFromBackup( xxx,cfgMenuWebUser,cfgMenuWebPass,  response);
		
		//
		if(act.equals("Set oded.r key Win"))					return setOdedKeyWin(xxx);
		if(act.equals("Set owner"))								return setOwnerBefore(xxx);
		if(act.equals("Set note field"))				   	return setNoteField(xxx);
		if(act.equals("Set oded.r key I5os"))					return setOdedKeyI5os(xxx);
		if(act.equals("Run SQL stm on envs(UPDATE)"))			return runSqlOnEnvs(xxx,"UPDATE");
		if(act.equals("Run SQL stm on envs(SELECT)"))			return runSqlOnEnvs(xxx,"SELECT");
		if(act.equals("Run VB"))			 					return runVb_before(xxx,cfgMenuWebUser);
		if(act.equals("Commit load files to svn"))				return runSqlOnEnvsMigLoadDone(xxx,cfgMenuWebUser);
		if(act.equals("Send hotfix request"))					return requestHotfixNew(xxx,cfgMenuWebUser);
		if(act.equals("clientModule - Send pack request"))					return requestClientModulePack(xxx,cfgMenuWebUser);
		if(act.equals("clientModuleFutureRelease - Send pack request"))					return ClientModule.requestClientModuleFutureReleasePack(xxx,cfgMenuWebUser);
		if(act.equals("scheduleF - Send pack request"))					return ScheduleF.requestScheduleFPack(xxx,cfgMenuWebUser);
		if(act.equals("clientModule - Run mig"))					return requestClientModuleRunMig(xxx,cfgMenuWebUser);
		if(act.equals("Cancel request"))					return RequestsStatus.cancelRequest(xxx,cfgMenuWebUser);
		if(act.equals("Reopen request"))					return RequestsStatus.reopenRequest(xxx,cfgMenuWebUser);
		if(act.equals("Request details"))					return RequestsStatus.requestDetails(xxx,cfgMenuWebUser);
		if(act.equals("Request history"))					return RequestsStatus.requestHistory(xxx,cfgMenuWebUser);
		if(act.equals("Set as verified"))					return RequestsFlowPerCust.setAsVerified(xxx,cfgMenuWebUser,cfgMenuWebPass);
		
		//if(act.equals("Send gard request"))					return requestGardNew(xxx,cfgMenuWebUser);
		if(act.equals("Build load file"))						return buildLoadFile(xxx,cfgMenuWebUser);
		if(act.equals("Duplicate mig entry"))					return duplicateMigEntry(xxx,cfgMenuWebUser);
		if(act.equals("Cancel mig (change type to 40)"))					return changeMigEntryPre(xxx,cfgMenuWebUser,"40");	
		
		if(act.equals("Gard-Install RI package"))						    return runGardOptionsPre(xxx,cfgMenuWebUser,cfgMenuWebPass,"Gard-Install RI package");
		if(act.equals("Gard-Replace database from cloud"))					return runGardOptionsPre(xxx,cfgMenuWebUser,cfgMenuWebPass,"Gard-Replace database from cloud");
		if(act.equals("Gard-Replace database from RI-server"))	 			return runGardOptionsPre(xxx,cfgMenuWebUser,cfgMenuWebPass,"Gard-Replace database from RI-server");
		if(act.equals("Gard-Replace database from bk file"))	 			return runGardOptionsPre(xxx,cfgMenuWebUser,cfgMenuWebPass,"Gard-Replace database from bk file");
		if(act.equals("RI-Servers details"))	 							return riServersDetails_before(request);
	//	if(act.equals("runVbCmd"))	 							return runVbCmd_before("rufman");
//		String server 	= request.getParameter("server");
//		String command 	= request.getParameter("cmd");dd
//		String output 	= request.getParameter("output");

return "not a valid option *"+act+"*";

	}
	public void 		checkMigsList(HagRc hagRcCheck, String[] hf1,String[] hf2,String[] hf3,String[] hf4,String[] hf5,String package2){
		String pack = HagUtil.getWord0(package2, "HF",0,true);
		for(int i = 0 ; i < hf1.length;i++){
			String ver11 = hf1[i];
			if(ver11.equals(pack) || ver11.equals(package2)){
				hagRcCheck.log.add("<font color=\"green\">OK - "+hf3[i]+" , "+hf2[i]+" released for version "+hf1[i]+".</font>");
				continue;
			}
			if(ver11.equals("GARD-IT4_5") && pack.equals("07.01M01U00")){
				hagRcCheck.log.add("<font color=\"green\">OK - "+hf3[i]+" , "+hf2[i]+" released for version "+hf1[i]+".</font>");
				continue;
			}
			if(ver11.equals("GARD-IT4_5")){
				hagRcCheck.log.add("<font color=\"red\">Wrong version - "+hf3[i]+" , "+hf2[i]+" release is for 07.01M01U00 (GARD-IT4_5) only.</font>");
				hagRcCheck.rc=1;
				continue;
			}
			hagRcCheck.log.add("<font color=\"red\">Wrong version - "+hf3[i]+" , "+hf2[i]+" released for version "+hf1[i]+".</font>");
			hagRcCheck.rc=1;
		}
	
	}
	/*
	public String 		checkBefore(String dupFrom, String dupTo,int[] dup,int ind){
		String jclass = HagUtil.getWord0(dupFrom,",", 2,true);
		String ans="<td >to duplicate</td>";
		String stm = "select " +
				"ver," +
				"id," +
				"jclass," +
				"run_type," +
				"dev " +
				" from dbo.add_mig where ver='"+dupTo+"' and jclass='"+jclass+"'" ;
		HagSQL hagSQL = new HagSQL();
		HagStringList exists = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,4,"~",null,null);
		
		if(exists.size()>0){
			dup[ind]=0;
			return "<td><font color =\"#FF0000\" >already exist</font></td>";
		}

		dup[ind]=1;
		return ans;
	}
	*/
	public String 		dupMigsEntry(HttpServletRequest request, HttpServletResponse response){
		String jClass 		= request.getParameter("jClass").trim();
		String id 		= request.getParameter("Id").trim();
		String jiraTask 	= request.getParameter("jiraTask").trim();
		String sentBy 		= request.getParameter("sentBy").trim();
		String note 		= request.getParameter("note").trim();
		String runTypeHotfix 		= request.getParameter("runTypeHotfix").trim();
		String runTypeMajor 		= request.getParameter("runTypeMajor").trim();
		String location 		= request.getParameter("location").trim();
		
		String [] cbgroup1		= request.getParameterValues("cb1");
		if(cbgroup1==null || cbgroup1.length==0)
			return HagUtil.shortHtml("You must select at least one version","red","bg");
		//String ans =sentBy+"<br>"+jClass+"<br>"+jiraTask+"<br>"+note+"<br>"+runType+"<br>"+runTypeTrunk+"<br>"+location+"<br>";
		//for(int i = 0;i<cbgroup1.length;i++)
		//	ans =ans +cbgroup1[i]+"<br>";
	
		return HagForms.requestDevAddMigRequestSent(sentBy,jClass,jiraTask,note,runTypeHotfix,runTypeMajor,location,cbgroup1,id);
	}
	public String 		changeMigsEntry(HttpServletRequest request, HttpServletResponse response){
		String jClass 		= request.getParameter("jClass").trim();
		String id 		= request.getParameter("Id").trim();
		String jiraTask 	= request.getParameter("jiraTask").trim();
		String sentBy 		= request.getParameter("sentBy").trim();
		String note 		= request.getParameter("note").trim();
		String runTypeHotfix 		= request.getParameter("runTypeHotfix").trim();
		String runTypeMajor 		= request.getParameter("runTypeMajor").trim();
		String location 		= request.getParameter("location").trim();
		
		String [] cbgroup1		= request.getParameterValues("cb1");
		if(cbgroup1==null || cbgroup1.length==0)
			return HagUtil.shortHtml("You must select at least one version","red","bg");
		//String ans =sentBy+"<br>"+jClass+"<br>"+jiraTask+"<br>"+note+"<br>"+runType+"<br>"+runTypeTrunk+"<br>"+location+"<br>";
		//for(int i = 0;i<cbgroup1.length;i++)
		//	ans =ans +cbgroup1[i]+"<br>";
	
		return HagForms.requestDevAddMigRequestSent(sentBy,jClass,jiraTask,note,runTypeHotfix,runTypeMajor,location,cbgroup1,id);
	}
	public String 		getMergeAndBuildActOutput(String title,String cmd,String rc){
		StringBuilder ans = new StringBuilder("<br><br>"+title+":");
		ans.append("<table bgcolor=\"#aaaacc\" cellpadding=\"5\" border =\"1\">");
		if(cmd==null) {
			ans.append("<tr><td>cmd</td><td>").append("Skipped").append("</td></tr>");
			ans.append("<tr bgcolor=\"#ffaaaa\"><td>rc</td><td >").append("---").append("</td></tr>");
			ans.append("<tr bgcolor=\"#ffaaaa\"><td>output</td><td >").append("---").append("</td></tr>");
		}else {
			ans.append("<tr><td>cmd</td><td>").append(cmd).append("</td></tr>");
			if(rc.toLowerCase().indexOf("failed")>-1 ) {
				ans.append("<tr bgcolor=\"#ff0099\"><td>rc</td><td>").append("-1").append("</td></tr>");
				ans.append("<tr bgcolor=\"#ff0099\"><td>output</td><td>").append(rc).append("</td></tr>");
			}else {			
				ans.append("<tr><td>rc</td><td>").append("0").append("</td></tr>");
				ans.append("<tr><td>output</td><td>").append(rc).append("</td></tr>");
			}
		}
		ans.append("</table>");
		return ans.toString();
	}
	public String 		getMergeAndBuildActOutputLists(String title,String cmd,String rc,String withNotToMerge,String cmExpFile,String startAfter,String finalList,int hagRcRc){
		if(cmd==null)
			return "<tr><td>"+title+"</td><td>"+rc+"</td></tr>";
		StringBuilder ans = new StringBuilder("<br><br>"+title+":");
		ans.append("<table bgcolor=\"#aaaacc\" cellpadding=\"5\" border =\"1\">");
		ans.append("<tr><td>cmd</td><td>").append(cmd).append("</td></tr>");
		if(hagRcRc==0)
			ans.append("<tr ><td>rc</td><td>").append("0").append("</td></tr>");
		else
			ans.append("<tr bgcolor=\"#ff0099\"><td>cmd</td><td>").append("no revisions to merge").append("</td></tr>");
		ans.append("<tr><td>list from svn</td><td>").append(rc).append("</td></tr>");
		ans.append("<tr><td>with notToMerge flag</td><td>").append(withNotToMerge).append("</td></tr>");
		ans.append("<tr><td>cm exceptions file</td><td>").append(cmExpFile).append("</td></tr>");
		ans.append("<tr><td>starting after</td><td>").append(startAfter).append("</td></tr>");
		ans.append("<tr><td>final list to merge</td><td>").append(finalList).append("</td></tr>");
		ans.append("</table>");
		return ans.toString();
	}

	public String 		callListOfAllEligibleRevisions(String verF,String verS,String startAfter,HagRc hagRc){
		
		HagStringList cmExpFileList= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\mergeAndBuildExceptions.txt",true,"*",false);
		String cmExpFile = cmExpFileList.convertToString(" ");
		
		String cmd = "\\\\cfg-ri\\cfg\\TortoiseSVN\\bin\\svn mergeinfo "+verS+" "+verF+" --show-revs eligible";
		String svnfullList = HagUtil.simpleDosCmd("0~1~"+cmd ,true);
		if(svnfullList.charAt(1)=='~') {
			if(svnfullList.equals("0~1~"+cmd)) {
				svnfullList="";
			
			}else {
				hagRc.rc=1;
				hagRc.log.add("failed rc from callListOfAllEligibleRevisions = "+svnfullList);
				return null;
			}
		}
		svnfullList=svnfullList.trim();
		while (svnfullList.indexOf("  ")>-1) {
			svnfullList=HagUtil.replaceStr(svnfullList,"  "," ");
		}
		
		HagStringList finalList = new HagStringList();
		if(!svnfullList.equals(""))
			finalList = new HagStringList(svnfullList," ",true);
		
		//start after
		String startAfter2 = HagUtil.replaceStr(startAfter,"r","");
		int startAfter3 = HagUtil.s2i(startAfter2);
		
		for(int i = 0 ; i <finalList.size();i++ ) {
			String temp1=finalList.get(i);
			String temp2 = HagUtil.replaceStr(temp1,"r","");
			int temp3 = HagUtil.s2i(temp2);
			if(temp3<=startAfter3) {
				finalList.set(i,"-"+temp1);
			}
		}

		HagStringList finalList1 = new HagStringList();
		HagStringList adminNotToMergeList = new HagStringList();
		for(int i = 0 ; i <finalList.size();i++ ) {
			String temp=finalList.get(i);
			if(!temp.startsWith("-"))
				finalList1.add(temp);
		}
		
		for(int i = 0 ; i <finalList1.size();i++ ) {
			String temp=finalList1.get(i);
			String cmdLog = "\\\\cfg-ri\\cfg\\TortoiseSVN\\bin\\svn log -r "+temp+" "+verS;
			String rcLog = HagUtil.simpleDosCmd("0~1~"+cmdLog ,true);
			String rcLogL = rcLog.toLowerCase();
			if(rcLogL.indexOf("nottomerge")>-1) {
				adminNotToMergeList.add(temp);
				finalList1.set(i,"-"+temp);
			}
		}
		HagStringList finalList2 = new HagStringList();
		for(int i = 0 ; i <finalList1.size();i++ ) {
			String temp=finalList1.get(i);
			if(!temp.startsWith("-"))
				finalList2.add(temp);
		}

		
		String adminNotToMerge = adminNotToMergeList.convertToString(" ");
		String finalListS = finalList2.convertToString(" ");
		if(finalList2.size()==0)
			hagRc.rc=1;
		hagRc.log.add(getMergeAndBuildActOutputLists("List of all eligible revisions",cmd,svnfullList,adminNotToMerge,cmExpFile,startAfter,finalListS,hagRc.rc));

	
		
		
		return finalListS;
	}
	
	public void 		callSvnUpdate(String verF,HagRc hagRc){
		String cmd = "\\\\cfg-ri\\cfg\\TortoiseSVN\\bin\\svn update  "+verF;
		System.out.println("###"+cmd+"###");
		String rc = HagUtil.simpleDosCmd("0~1~"+cmd,true);
		hagRc.log.add(getMergeAndBuildActOutput("Update svn folder",cmd,rc));

	}
	public void 		callSvnCleanUp(String verF,HagRc hagRc){
		String cmd = "\\\\cfg-ri\\cfg\\TortoiseSVN\\bin\\svn cleanup "+verF+"\\  --include-externals  --remove-ignored --remove-unversioned     --vacuum-pristines";
		String rc = HagUtil.simpleDosCmd("0~1~"+cmd,true);
		hagRc.log.add(getMergeAndBuildActOutput("CleanUp",cmd,rc));

	}
	public void 		callSvnRevert(String verF,HagRc hagRc){
		String cmd = "\\\\cfg-ri\\cfg\\TortoiseSVN\\bin\\svn revert "+verF+"\\";
		String rc = HagUtil.simpleDosCmd("0~1~"+cmd,true);
		hagRc.log.add(getMergeAndBuildActOutput("Revert",cmd,rc));
	}

	public void 		callSvnMerge(String verS,String verF,String revitionList,HagRc hagRc){
		if(revitionList==null || revitionList.trim().length()==0) {
			hagRc.log.add(getMergeAndBuildActOutput("Merge",null,"revitions List is empty"));
			return;
		}

		String list = HagUtil.replaceStr(revitionList,"r","");
		list = HagUtil.replaceStr(list," ",",");
		
		String cmd = "\\\\cfg-ri\\cfg\\TortoiseSVN\\bin\\svn merge -c "+list+" "+verS+" "+verF;
		String rc = HagUtil.simpleDosCmd("0~1~"+cmd,true);
		rc=HagUtil.replaceStr(rc,"---","<br>");
		if(rc.indexOf("failed")>-1)
			hagRc.rc=1;
		hagRc.log.add(getMergeAndBuildActOutput("Merge",cmd,rc));
	}
	public void 		callSvnCommit(String line,String lastRevisionOld,String verF,HagRc hagRc,String revitionList){
		if(revitionList==null || revitionList.trim().length()==0) {
			hagRc.log.add(getMergeAndBuildActOutput("Commit",null,"revitions List is empty"));
		return;
		}
		HagStringList lastRevisionNewList = new HagStringList(revitionList," ",true);
		String lastRevisionNew= lastRevisionNewList.get(lastRevisionNewList.size()-1);
		
		String date = HagUtil.getDateTime("dd/MM/yyyy-HH:mm");
		String cmd = "\\\\cfg-ri\\cfg\\TortoiseSVN\\bin\\svn commit "+verF+"\\ -m\"admin-cm "+date+"\"";
		System.out.println("***"+cmd+"***");
		String rc = HagUtil.simpleDosCmd("0~1~"+cmd,true);
		String ans="Commit failed LastRevision not changed"; 
		if(rc.indexOf("Committed revision")>-1) {
			ans = changeLastRevision(line,lastRevisionOld,lastRevisionNew);
		}
			
		hagRc.log.add(getMergeAndBuildActOutput("Commit",cmd,rc+" "+ans));
	}
	public String 		changeLastRevision(String line,String lastRevisionOld,String lastRevisionNew){
		String mergeAndBuildVersionsFile = HagUtil.cfgMenuWebLoc+"\\lists\\mergeAndBuildVersions.txt";
		HagStringList versionsList= new HagStringList(mergeAndBuildVersionsFile,false,"adfasf",false);
		line=line.trim();
		for(int i = 0 ; i < versionsList.size();i++) {
			String temp = versionsList.get(i).trim();
			if(temp.equals(line)) {
				String temp1 = HagUtil.replaceStr(temp, lastRevisionOld, lastRevisionNew);
				versionsList.set(i, temp1);
				versionsList.writeToFile(mergeAndBuildVersionsFile, false);
				return " commit done and last revision changed to "+ lastRevisionNew;
			}
		}
		return " commit done but last revision not changed";
	}
	public String 		mergeAndBuildPost(HttpServletRequest request, HttpServletResponse response){
		String ver 		= request.getParameter("ver").trim();
		String verD = HagUtil.getWord0(ver, "|",0,true); 
		String verF = HagUtil.getWord0(ver, "|",1,true); 
		String verS = HagUtil.getWord0(ver, "|",2,true); 
		String startAfter = HagUtil.getWord0(ver, "|",3,true); 
		StringBuilder ans=new StringBuilder();
		String [] mergeAndBuildSteps		= request.getParameterValues("mergeAndBuildSteps");
		if(mergeAndBuildSteps==null || mergeAndBuildSteps.length==0) {
			String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps,HagUtil.mailList_hag,"merge","You must select at least one line");
			return HagUtil.shortHtml("You must select at least one line","red","bg");
		}
		HagRc hagRc = new HagRc();
		String finalList = null;
		for(int i = 0;i<mergeAndBuildSteps.length;i++) {
			if(mergeAndBuildSteps[i].equals("ListOfAllEligibleRevisions")) {
				finalList = callListOfAllEligibleRevisions(verF,verS,startAfter,hagRc);
				if(hagRc.rc!=0) {
					String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps,HagUtil.mailList_hag,"merge",hagRc.log.convertToString(" "));
					return hagRc.log.convertToString(" ");
				}
			}else if(mergeAndBuildSteps[i].equals("SvnUpdate")) {
				callSvnUpdate(verF,hagRc);
				if(hagRc.rc!=0) {
					String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps,HagUtil.mailList_hag,"merge",hagRc.log.convertToString(" "));
					return hagRc.log.convertToString(" ");
				}
			}else if(mergeAndBuildSteps[i].equals("SvnCleanUp")) {
				callSvnCleanUp(verF,hagRc);
				if(hagRc.rc!=0) {
					String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps,HagUtil.mailList_hag,"merge",hagRc.log.convertToString(" "));
					return hagRc.log.convertToString(" ");
				}
			}else if(mergeAndBuildSteps[i].equals("SvnRevert")) {
				callSvnRevert(verF,hagRc);
				if(hagRc.rc!=0) {
					String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps,HagUtil.mailList_hag,"merge",hagRc.log.convertToString(" "));
					return hagRc.log.convertToString(" ");
				}
			}else if(mergeAndBuildSteps[i].equals("SvnMerge")) {
				callSvnMerge(verS,verF,finalList,hagRc);
				if(hagRc.rc!=0) {
					String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps,HagUtil.mailList_hag,"merge",hagRc.log.convertToString(" "));
					return hagRc.log.convertToString(" ");
				}
			}else if(mergeAndBuildSteps[i].equals("SvnCommit")) {
			//	callSvnCommit(ver,startAfter,verF,hagRc,finalList);
				callSvnCommit(ver,startAfter,verF,hagRc,finalList);
				if(hagRc.rc!=0) {
					String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps,HagUtil.mailList_hag,"merge",hagRc.log.convertToString(" "));
					return hagRc.log.convertToString(" ");
				}
			}else
				hagRc.log.add("<BR>*"+mergeAndBuildSteps[i]+"*not Eligible option");
		}
		
		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps,HagUtil.mailList_hag,"merge",hagRc.log.convertToString(" "));
		
		return hagRc.log.convertToString(" ");
	}
	public String 		setInstOn(String[] installOnId, String[] installOnDb,String ff,String folder){
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
/*
	public String 		setInstOn(String[] installOnId, String[] installOnDb,String ff){
		HashMap<String, String> hmap = new HashMap<String, String>();	
		
		StringBuilder ans = new StringBuilder();
		for(int i = 0;i<installOnId.length;i++) {
			if(installOnId[i]!=null && installOnId[i].trim().length() > 0) {
				if(installOnDb[i]!=null && installOnDb[i].trim().length() > 0) {
					String server = installOnId[i].trim();
					String key= hmap.get(server);
					if(key==null)
					if(ans.length()>0)
						ans.append("~");
					String dbs = HagUtil.replaceStr(installOnDb[i].trim()," ",",");
					ans.append(installOnId[i]).append("(").append(dbs).append(")");
				}	
			}
		}

		HagStringList list = new HagStringList(ans.toString(),"~",true);
		
		list.writeToFile("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\hf_inst\\"+ff, false);
		return ans.toString();
	}
*/
	public String 		sendHotfixRequestNew(HttpServletRequest request, HttpServletResponse response){
		
		
		HagStringList ans = new HagStringList();
		HagStringList contentM = new HagStringList();
		HagStringList contentMacc = new HagStringList();
		StringBuilder ans1 = new StringBuilder("<br><br>cm team extra:");
		HagStringList migsAndIom = new HagStringList();
	
		//ans.append("***********************<br>");
		//ans.append("* for future use only *<br>");
		//ans.append("***********************<br>");
		
		String sentBy 	= request.getParameter("sentBy");
		HagRc hagRc= new HagRc();
		
		String ver 	= request.getParameter("ver");
		String maint 	= request.getParameter("maint");
		String update 	= request.getParameter("update");
		String hotfix 	= request.getParameter("hotfix");
		String platform 	= request.getParameter("platform");
		String Accumulative 	= request.getParameter("Accumulative");
		String Final 	= request.getParameter("Final");
		String addToBkup= request.getParameter("addToBkup");
		
		
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
		String ff = ver+"M"+maint+"U"+update+"HF"+hotfix+".req";
		String instOn1 = setInstOn(installOnId,installOnDb,ff,"hf_inst");
		String instOn2 =null;
		if(instOn1!=null) {
		 instOn2 = HagUtil.replaceStr(instOn1, "~", " ");
		 instOn2 = HagUtil.replaceStr(instOn2, ")", "");
		 instOn2 = HagUtil.replaceStr(instOn2, "(", "/");
		}
		String replaceWarAndJasper 	= request.getParameter("replaceWarAndJasper");
		String replaceJar 	= request.getParameter("replaceJar");
		String replaceJarParms 	= request.getParameter("replaceJarParms");
		String replaceDdc 	= request.getParameter("replaceDdc");
		String replaceDdcParms 	= request.getParameter("replaceDdcParms");
		String replaceIom 	= request.getParameter("replaceIom");
		String replaceIomParms 	= request.getParameter("replaceIomParms");
		String replaceScripts 	= request.getParameter("replaceScripts");
		String replaceScriptsParms 	= request.getParameter("replaceScriptsParms");
		String glInterface 	= request.getParameter("GL-Interface");
		String eMergeClientSP3 	= request.getParameter("eMergeClientSP3");
		String replaceWebProxy 	= request.getParameter("replaceWebProxy");
		
		
	
		//CHECKS
		if(replaceJar.equals("f_t_t_a_t_r")){
			if(replaceJarParms==null || replaceJarParms.trim().length()==0) {
				return HagUtil.shortHtml("Error: jar file name cannot be empty" , "red", "bg");
			}else {
				replaceJarParms=replaceJarParms.trim();
			}
			if(replaceJarParms.indexOf(".")<0) {
				replaceJarParms=replaceJarParms+".jar";
			}else{
				if(!replaceJarParms.toUpperCase().endsWith(".JAR")) {
					return HagUtil.shortHtml("Error: jar file name must end with .jar" , "red", "bg");
				}		
			}
		}
		if(platform.equals("WIN") && !ver.startsWith("6")){
			String migMng = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V"+ver+"m"+maint+"U00\\U"+update+"\\Hotfix"+hotfix+"\\ALL\\mig-mng.jar";
			File migMngF = new File(migMng);
			if(!migMngF.exists() ||!migMngF.isFile() ){
				return HagUtil.shortHtml("Error: mig-mng.jar file not found in " + migMng   , "red", "bg");
			}
		}
		if(platform.equals("WIN") && !ver.startsWith("6")){
			String dirS = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V"+ver+"m"+maint+"U00\\U"+update+"\\Hotfix"+hotfix+"\\ALL";
			File dir = new File(dirS);	
			if (dir.isDirectory()) {
	           String[] children = dir.list();
	           for(int i = 0 ; i < children.length;i++) {
	        	   String temp = children[i];
	        	   if(		temp.endsWith("SNAPSHOT.war") || 
	        			   (temp.endsWith("SNAPSHOT.jar") && !temp.startsWith("batch-")  )) {
	        		   return HagUtil.shortHtml("Error: "+temp+" file not valid." , "red", "bg");
	        	   }
	           }
			}
		}
		if(!replaceScripts.equals("NO")){
			String all = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V"+ver+"m"+maint+"U00\\U"+update+"\\Hotfix"+hotfix+"\\ALL";
			String scripts = all+"\\scripts";
			File scriptsF = new File(scripts);
			if(!scriptsF.exists() ||!scriptsF.isDirectory() ){
				return HagUtil.shortHtml("Error: scripts folder not found in " + all+" folder"   , "red", "bg");
			}
			if(scriptsF.list().length == 0 ){
				return HagUtil.shortHtml("Error: "+scripts+" folder is empty"   , "red", "bg");
			}

		}
		if(!replaceWebProxy.equals("NO")){
			String all = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V"+ver+"m"+maint+"U00\\U"+update+"\\Hotfix"+hotfix+"\\ALL";
			String folder = all+"\\WebProxy";
			File webProxyF = new File(folder);
			if(!webProxyF.exists() ||!webProxyF.isDirectory() ){
				return HagUtil.shortHtml("Error: webProxy folder not found in " + all+" folder"   , "red", "bg");
			}
			if(webProxyF.list().length == 0 ){
				return HagUtil.shortHtml("Error: "+folder+" folder is empty"   , "red", "bg");
			}

		}
		
		String [] order	= request.getParameterValues("order");
		String orderIom 	= request.getParameter("orderIom");
		String iomFromEnv 	= request.getParameter("iomFromEnv");
		String iomFromServer 	= request.getParameter("iomFromServer");
		
		String [] runOrUpdate	= request.getParameterValues("runOrUpdate");
		
		String [] hf1	= request.getParameterValues("hf1");
		String [] hf2	= request.getParameterValues("hf2");
		String [] hf3	= request.getParameterValues("hf3");
		String [] hf4	= request.getParameterValues("hf4");
		String [] hf5	= request.getParameterValues("hf5");
		//String [] hf6	= request.getParameterValues("hf6");
		//String [] hf7	= request.getParameterValues("hf7");
		//String [] hf8	= request.getParameterValues("hf8");
		//String [] hf9	= request.getParameterValues("hf9");
		//String [] hf10	= request.getParameterValues("hf10");
		//String [] hf11	= request.getParameterValues("hf11");
		//String [] hf12	= request.getParameterValues("hf12");
		String package1 = ver+"."+maint+"."+update+"."+hotfix; 
		String ver1=ver.substring(0,2);
		String ver2=ver.substring(2,4);
		String package2 = ver1+"."+ver2+"M"+maint+"U"+update+"HF"+hotfix; 

		HagRc hagRcCheck = new HagRc();
		if(hf1!=null){
			checkMigsList(hagRcCheck,hf1,hf2,hf3,hf4,hf5,package2);
			if(hagRcCheck.rc!=0){
				String printLog = hagRcCheck.log.convertToString("<br>");
				return printLog;
			}
		}
		
		if (instOn2==null || instOn2.trim().length()==0)
			instOn2=".";
		if (note==null || note.trim().length()==0)
			note=".";
		
		contentM.add("Request to create hotfix (Single)<br>");
		contentMacc.add("Request to create hotfix (Accumulative)<br>");
		ans.add("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\" border =\"1\">");
		contentM.add(ans.get(ans.size()-1));
		contentMacc.add(ans.get(ans.size()-1));
		ans.add("<tr><td bgColor=\"#cccccc\">Package</td><td>"+package1+"</td></tr>");
		contentM.add(ans.get(ans.size()-1));
		contentMacc.add(ans.get(ans.size()-1));
		ans.add("<tr><td bgColor=\"#cccccc\">Platform</td><td bgColor=\"#FFFF00\">"+platform+"</td></tr>");
		contentM.add(ans.get(ans.size()-1));
		contentMacc.add(ans.get(ans.size()-1));
		int verI= HagUtil.s2i(ver);
		if(Accumulative.equals("YES")) {
			
			if(verI>=0701)
				ans.add("<tr><td bgColor=\"#cccccc\">Add to future Accumulative</td><td bgColor=\"#00FF00\">"+Accumulative+"</td></tr>");
			else
				ans.add("<tr><td bgColor=\"#cccccc\">Add to future Accumulative</td><td bgColor=\"#FF0000\">not an Accumulative version</td></tr>");
		}else
			ans.add("<tr><td bgColor=\"#cccccc\">Add to future Accumulative</td><td bgColor=\"#FF0000\">"+Accumulative+"</td></tr>");
		if(Final.equals("YES"))
			ans.add("<tr><td bgColor=\"#cccccc\">FInal pack</td><td bgColor=\"#00FF00\">"+Final+"</td></tr>");
		else
			ans.add("<tr><td bgColor=\"#cccccc\">FInal pack</td><td bgColor=\"#FF0000\">"+Final+"</td></tr>");
		contentM.add(ans.get(ans.size()-1));
		contentMacc.add(ans.get(ans.size()-1));
		
		//ans.add("<tr><td bgColor=\"#cccccc\">backup before</td><td>"+addToBkup+"</td></tr>");
		ans.add("<tr><td bgColor=\"#cccccc\">backup before</td><td bgColor=\"#FFFF00\">"+addToBkup+"</td></tr>");
		contentM.add(ans.get(ans.size()-1));
		contentMacc.add(ans.get(ans.size()-1));
		
		ans.add("<tr><td bgColor=\"#cccccc\">Sent by</td><td>"+sentBy+"</td></tr>");
		contentM.add(ans.get(ans.size()-1));
		contentMacc.add(ans.get(ans.size()-1));
		ans.add("<tr><td bgColor=\"#cccccc\">Install on</td><td>"+instOn2+"</td></tr>");
		contentM.add(ans.get(ans.size()-1));
		contentMacc.add(ans.get(ans.size()-1));
		ans.add("<tr><td bgColor=\"#cccccc\">Note</td><td>"+note+"</td></tr>");
		contentM.add(ans.get(ans.size()-1));
		
			ans.add("<tr><td bgColor=\"#cccccc\">Upgrade to eMergeClientSP03update01</td><td>"+eMergeClientSP3+"</td></tr>");
			contentM.add(ans.get(ans.size()-1));
	
		
		
		contentMacc.add(ans.get(ans.size()-1));
		String packagePrep 		= "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\cfg\\hotfix\\ARIA\\"+ver+"."+maint+"."+update+"."+hotfix;
		if(platform.equals("I5OS")) {
			packagePrep 		= "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\cfg\\hotfix\\I5OS\\"+ver+"."+maint+"."+update+"."+hotfix;
		}
		//String packagePrepLink 	= "<a href=\""+packagePrep+"\">"+packagePrep+"</a>";
		String packagePrepLinkAcc 	= "<a href=\"$packageHref$\">$packageHref$</a>";
		//contentM.add("<tr><td bgColor =\"#cccccc\">Link to packagePrep:</td><td>"+packagePrepLink+"</td></tr>");
	//	contentM.add("<!--link-->");
		contentM.add("<tr><td bgColor =\"#cccccc\">Link to FTP {$packageHrefDate$}:</td><td>"+packagePrepLinkAcc+"</td></tr>");
		contentMacc.add("<tr><td bgColor =\"#cccccc\">Link to FTP {$packageHrefDate$}:</td><td>"+packagePrepLinkAcc+"</td></tr>");
		ans.add("</table><br><br>");
		contentM.add(ans.get(ans.size()-1));
		contentMacc.add(ans.get(ans.size()-1));
		contentM.add("Hotfix content:<br>");
		ans.add("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\" border =\"1\">");
		contentM.add(ans.get(ans.size()-1));
		HagStringList toDoList = new HagStringList();
		//war
		if(replaceWarAndJasper.equals("YES")){
			ans.add("<tr><td bgColor=\"#cccccc\">replaceWarAndJasper</td><td>"+replaceWarAndJasper+"</td></tr>");
			contentM.add(ans.get(ans.size()-1));
			toDoList.add("replaceWarAndJasper~.");
		}
		//jar
		if(replaceJar.equals("f_t_t_a_t_r")){
			ans.add("<tr><td bgColor=\"#cccccc\">replaceJar</td><td>"+replaceJarParms+"</td></tr>");
			contentM.add(ans.get(ans.size()-1));
			toDoList.add("replaceJar~"+replaceJarParms);
		}else if(!replaceJar.equals("NO")){
			ans.add("<tr><td bgColor=\"#cccccc\">replaceJar</td><td>"+replaceJar+"</td></tr>");
			contentM.add(ans.get(ans.size()-1));
			toDoList.add("replaceJar~"+replaceJar);
		}
		//ddc
		if(!replaceDdc.equals("NO")){
			ans.add("<tr><td bgColor=\"#cccccc\">replaceDdc</td><td>"+replaceDdcParms+"</td></tr>");
			contentM.add(ans.get(ans.size()-1));
			toDoList.add("replaceDdc~"+replaceDdcParms);
		}
		//scripts
		if(!replaceScripts.equals("NO")){
			ans.add("<tr><td bgColor=\"#cccccc\">replaceScripts</td><td>"+replaceScripts+" - "+replaceScriptsParms+"<BR>(what written in the request , might be different from what is in the package , package is from what found in the TOPACK-hotfix -scripts folder)</td></tr>");
			contentM.add(ans.get(ans.size()-1));
			toDoList.add("replaceScripts~"+replaceScriptsParms);
		}
		//gl
		if(!glInterface.equals("NO")){
			ans.add("<tr><td bgColor=\"#cccccc\">NNRe GL-Interface</td><td bgColor=\"#FFFF00\">YES</td></tr>");
			contentM.add(ans.get(ans.size()-1));
			//toDoList.add("replaceScripts~"+replaceDdcParms);
		}
		//webProxy
		if(!replaceWebProxy.equals("NO")){
			ans.add("<tr><td bgColor=\"#cccccc\">replaceWebProxy</td><td bgColor=\"#FFFF00\">YES</td></tr>");
			contentM.add(ans.get(ans.size()-1));
			toDoList.add("replaceWebProxy~.");
		}
		//eMergeClientSP3
			if(!eMergeClientSP3.equals("NO")){
				ans.add("<tr><td bgColor=\"#cccccc\">upgrade to eMergeClientSP03U02</td><td bgColor=\"#FFFF00\">YES</td></tr>");
				contentM.add(ans.get(ans.size()-1));
				//toDoList.add("replaceScripts~"+replaceDdcParms);
			}
		//iom
	//	if(!replaceIom.equals("NO")){
	//		ans.add("<tr><td bgColor=\"#cccccc\">replaceIom</td><td>"+replaceIomParms+"</td></tr>");
	//		contentM.add(ans.get(ans.size()-1));
	//		toDoList.add("replaceIom~"+replaceIomParms);zzzz
	//	}
		//manual mig
		String[][] migs = null;
		String iomLoc = null;
		
		if(hf1!=null)
			migs = new String[hf1.length][2];
		
		
		
		//
		String dateTime = HagUtil.getDateTime("yyyy/MM/dd-HH:mm");
		StringBuilder stm3= new StringBuilder("Update "+HagParam.getConfg1DB()+".[dbo].[add_mig] ")
			.append("set manager='"+sentBy+"',dt_manager='"+dateTime+"',pack2ver='"+package2+"' where ");
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
				if(!replaceIom.equals("NO") && orderIom.equals(""+k)){
					ans.add("<tr><td bgColor=\"#cccccc\">replaceIom - order="+k+"</td><td>"+replaceIomParms+" copy from "+iomFromServer+"("+iomFromEnv+")</td></tr>");
					contentM.add(ans.get(ans.size()-1));
					//toDoList.add("replaceIom~"+replaceIomParms);
					migsAndIom.add("replaceIom~"+replaceIomParms);
				}
			}
			if(!replaceIom.equals("NO") && orderIom.equals("10")){
				ans.add("<tr><td bgColor=\"#cccccc\">replaceIom - order=10</td><td>"+replaceIomParms+" copy from "+iomFromServer+"("+iomFromEnv+")</td></tr>");
				contentM.add(ans.get(ans.size()-1));
				migsAndIom.add("replaceIom~"+replaceIomParms);
			}
		}else{
			if(!replaceIom.equals("NO")){
				ans.add("<tr><td bgColor=\"#cccccc\">replaceIom - order="+orderIom+"</td><td>"+replaceIomParms+" copy from "+iomFromServer+"("+iomFromEnv+")</td></tr>");
				contentM.add(ans.get(ans.size()-1));
				migsAndIom.add("replaceIom~"+replaceIomParms);
			}
		}
		ans.add("</table><br>");
		contentM.add(ans.get(ans.size()-1));
		//String ans1		= HagUtil.sendMail_hag(HagUtil.cfgTeamMail,"david.ha@sapiens.com","david.ha@sapiens.com","cfgMenuWeb new user",content.toString());
		/*
	
		
		aaa.append(replaceWarAndJasper).append("<br>");
		aaa.append(replaceJar).append("<br>");
		aaa.append(replaceDdc).append("<br>");
		aaa.append(replaceDdcParms).append("<br>");
		aaa.append(replaceIom).append("<br>");
		aaa.append(replaceIomParms).append("<br>");
		
		for(int i = 0 ; i < hf1.length;i++){
			aaa.append(hf1[i]).append(",");
			aaa.append(hf2[i]).append(",");
			aaa.append(hf3[i]).append(",");
			aaa.append(hf4[i]).append(",");
			aaa.append(hf5[i]).append(",");
			aaa.append(hf6[i]).append(",");
			aaa.append(hf7[i]).append(",");
			aaa.append(hf8[i]).append(",");
			aaa.append(hf9[i]).append(",");
			aaa.append(hf10[i]).append(",");
			aaa.append(hf11[i]).append(",");
			aaa.append(hf12[i]).append("<br>");
			
			
		}
		*/
		String iomToPack="";
		if(platform.equals("I5OS")){
			ans1.append("<br>Content:");
			if(replaceWarAndJasper.equals("YES"))
				ans1.append("<br>ri-web.war");
			if(replaceJar.equals("f_t_t_a_t_r"))
				ans1.append("<br>"+replaceJarParms);
			else if(!replaceJar.equals("NO")){
				ans1.append("<br>"+replaceJar);
				if(replaceJar.indexOf("-MSS-")>-1){
					return HagUtil.shortHtml("Error: Platform="+platform+" and jar = "+replaceJar , "red", "bg");
				}
			}
			if(migsAndIom.size()>0)
				ans1.append("<br>Run manual migs and iom");
			
			
			ans1.append("<br><br>Actions:");
			if(replaceWarAndJasper.equals("YES"))
				ans1.append("<br>replace war");
			if(replaceJar.equals("f_t_t_a_t_r"))
				ans1.append("<br>replace jar");
			else if(!replaceJar.equals("NO"))
				ans1.append("<br>replace jar");
			if(migsAndIom.size()>0)
				ans1.append("<br>Run manual migs and iom");
			
			
			ans1.append("<br><br>Manual migs and iom");
			for(int i = 0 ; i < migsAndIom.size();i++){
			
			
				String temp = migsAndIom.get(i);
				if(temp.startsWith("replaceIom")){
					//IOM      - 817  N   N 0   .@@@@RI13186
					String iomToPackI5os=HagUtil.getWord0(temp,"~",1,true);
					HagStringList iomI5osList = new HagStringList(iomToPackI5os,",",true);
					for(int k = 0;k < iomI5osList.size();k++){
						String temp1 = iomI5osList.get(k);
						//toDoList.add("IOM      - 817  N   N 0   .@@@@"+temp1);
						toDoList.add("replaceIom~"+temp1);
					}
					//ans1.append("<br>").append(temp).append(",ALL,RN,A");
				}else{
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
					ans1.append("<br>").append(wa).append(",ALL,RN,A");
				}
			}
		
			ans1.append("<br><br>convert table: UR=RN,U==UpdateOrInsert only,D=DO,DUR=DR,DU==DeleteAndInsert only");
			
		}else{
			ans1.append("<br>Content:");
			if(replaceWarAndJasper.equals("YES"))
				ans1.append("<br>ri-web.war");
			if(replaceJar.equals("f_t_t_a_t_r"))
				ans1.append("<br>"+replaceJarParms);
			else if(!replaceJar.equals("NO")){
				ans1.append("<br>"+replaceJar);
				if(replaceJar.indexOf("-DB2-")>-1){
					return HagUtil.shortHtml("Error: Platform="+platform+" and jar = "+replaceJar , "red", "bg");
				}
			}
			if(migsAndIom.size()>0)
				ans1.append("<br>Run manual mig and iom");
			
			
			ans1.append("<br><br>Actions:");
			if(replaceWarAndJasper.equals("YES"))
				ans1.append("<br>replace war");
			if(replaceJar.equals("f_t_t_a_t_r"))
				ans1.append("<br>replace jar");
			else if(!replaceJar.equals("NO"))
				ans1.append("<br>replace jar");
			if(migsAndIom.size()>0)
				ans1.append("<br>Run manual migs and iom");
						
			ans1.append("<br><br>Manual mig:");
			for(int i = 0 ; i < migsAndIom.size();i++){
				
				String temp = migsAndIom.get(i);
				if(temp.startsWith("replaceIom")){
				//	toDoList.add(temp);
					iomToPack=HagUtil.getWord0(temp,"~",1,true);
					iomToPack=HagUtil.replaceStr(iomToPack, ";",",");
					HagStringList iomL = new HagStringList(iomToPack,",",true);
					for(int k = 0 ; k < iomL.size();k++){
						toDoList.add("replaceIom~"+iomL.get(k));
					}		
				}else{
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
			}
			
		}

		
		String folder = "";
		if(platform.equals("WIN")){
			//if(ver.equals("0609"))
			//	folder = sendHotfixRequestNew_prepPackage_win0609( hagRc,ver, maint, update, hotfix, migD,toDoList);
			//else
				folder = sendHotfixRequestNew_prepPackage_win( hagRc,ver, maint, update, hotfix, migD,toDoList,iomToPack,iomFromEnv,iomFromServer,eMergeClientSP3);
		}else{					
			folder = sendHotfixRequestNew_prepPackage_I5OS( hagRc,ver, maint, update, hotfix, migD,toDoList);
		}
		if(hagRc.rc!=0){
			String msg = "Failed to create "+folder+" folder.<br>"+hagRc.log.convertToString("<br>");
			return HagUtil.shortHtml(msg , "red", "bg");
		}
				
		contentM.writeToFile(folder+"\\mail.html",null,false);
		contentMacc.writeToFile(folder+"\\mailAcc.html",null,false);
		
	
		
		
		//String subject 	= "#Request to create hotfix "+ver+" M"+maint+" U"+update+" HF"+hotfix+" @BREAK-REQ@ sent by "+sentBy;
		String ccList 	= HagUtil.getRiMails("all");
		String toList 	= "david.ha@sapiens.com;gonen.s@sapiens.com;david.b@sapiens.com;stas.p@sapiens.com";
		String pre="#";
		boolean testOnly =false;
		if(!testOnly) {
			if(hf1!=null && hf1.length>0){
				HagSQL hagSQL = new HagSQL();
				int changed = hagSQL.updateStm("confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm3S);
				ans.add(changed+" rows in addMigs table change to "+package2+" by "+sentBy+"<br>");
			}
		}
		if(testOnly) {
			toList="david.ha@sapiens.com";
			ccList="gonen.s@sapiens.com";
			pre="";
		}
		int ind = HagUtil.getRequestInd();
		
		String indS = HagUtil.padNumLeft(""+ind, 5, '0');
		//spr1003 start
		if(verI<700 || verI==980) {
			//StringBuilder migD,HagStringList toDoList
			requstsHandleAfterSpr1003( ver1+ver2, maint, update, hotfix, platform, migD, toDoList);
		}
			
		//spr1003 end
		//hagayhagay
		//spr1007
		
		
		String subject 	= pre+"Request to create hotfix "+ver+" M"+maint+" U"+update+" HF"+hotfix+" "+platform+" @BREAK-REQ@ #"+indS+"# sent by "+sentBy;
		if(!HagUtil.addRequest(ind,sentBy, note,subject,"./.",".",".",instOn2,null,null,null,998))
			return HagUtil.shortHtml("Error adding request-mail sent to hagay & gonen, please call hagay or gonen", "red","bg");
		String ansS = ans.convertToString("");
		 String ans9=HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+sentBy,HagUtil.mailList_hag, subject,ansS+"<br>"+ans1.toString());
		return ansS+"<br>"+ans9+"<br>"+ans1.toString();
	}

	
	public String 		runClientModuleMigs(HttpServletRequest request, HttpServletResponse response){
		String from = request.getParameter("from");
		String [] order	= request.getParameterValues("order");
		
		return "from="+from+"<br>order1="+order[0]+"<br>order2="+order[1];
	}

	public String 		sendGardRequestNew(HttpServletRequest request, HttpServletResponse response){
	//	String toList="david.ha@sapiens.com";
		//String ccList="david.ha@sapiens.com";
		//String subject="david.ha@sapiens.com1111";
		String user = request.getParameter("user");
		String title = request.getParameter("opt");
		String [] cb4	= request.getParameterValues("cb4");
		HagStringList ans = new HagStringList();
		String note = request.getParameter("note");
		String fromDb = request.getParameter("fromDb");
		String fromBk = request.getParameter("fromBk");
		String gardVersion = request.getParameter("gardVersion");
		ans.add("<h2>"+title+"</h2>");
		
		ans.add("<h3>target:</h3>");
		ans.add("<table bgColor=\"#dddddd\" cellpadding=\"3\" cellspacing=\"3\" border =\"1\">");
		ans.add("<tr  bgColor=\"#bbbbbb\"><td>Environment name</td><td>App server</td><td>Sql server</td><td>batch name</td></tr>");
		String env = "";
		String app = "";
		String sql = "";
		String batchName = "";
		for(int i=0;i< cb4.length;i++) {
		
			env = HagUtil.getWord0(cb4[i],"!",0,true);
			app = HagUtil.getWord0(cb4[i],"!",1,true);
			sql = HagUtil.getWord0(cb4[i],"!",2,true);
			batchName = HagUtil.getWord0(cb4[i],"!",3,true);
			ans.add("<tr><td>"+env+"</td><td>"+app+"</td><td>"+sql+"</td><td>"+batchName+"</td></tr>");


		}
		ans.add("</table><br>");
		if(title.equals("Gard-Install RI package") ) {
			ans.add("<table bgColor=\"#dddddd\" cellpadding=\"3\" cellspacing=\"3\" border =\"1\">");
		
			ans.add("<tr><td>Package to install</td><td>"+gardVersion+"</td></tr>");

			ans.add("</table><br>");
		}
		if(title.equals("Gard-Replace database from cloud") ) {
			ans.add("<h3>source:</h3>");

			ans.add("<table bgColor=\"#dddddd\" cellpadding=\"3\" cellspacing=\"3\" border =\"1\">");
			ans.add("<tr bgColor=\"#bbbbbb\" ><td>Environment name</td><td>App IP</td><td>Sql IP</td><td>batch name</td></tr>");
			
			String env1 = HagUtil.getWord0(fromDb,":",4,true);
			String app1 = HagUtil.getWord0(fromDb,":",2,true);
			String sql1 = HagUtil.getWord0(fromDb,":",3,true);
			String batchName1 = HagUtil.getWord0(fromDb,":",1,true);
			app1=HagUtil.replaceStr(app1,"_","");
			app1=app1.substring(0,app1.length()-5);

			sql1=HagUtil.replaceStr(sql1,"_","");
			sql1=sql1.substring(0,sql1.length()-7);

			batchName1=HagUtil.replaceStr(batchName1,"_","");
			batchName1=batchName1.substring(0,batchName1.length()-5);

			env1=HagUtil.replaceStr(env1,"_","");

			ans.add("<tr><td>"+env1+"</td><td>"+app1+"</td><td>"+sql1+"</td><td>"+batchName1+"</td></tr>");

			ans.add("</table><br>");
		}
		if(title.equals("Gard-Replace database from RI-server") ) {
			ans.add("<h3>source:</h3>");

			ans.add("<table bgColor=\"#dddddd\" cellpadding=\"3\" cellspacing=\"3\" border =\"1\">");
			ans.add("<tr  bgColor=\"#bbbbbb\"><td>App server</td><td>Sql server</td><td>batch name</td></tr>");
			String app1 = HagUtil.getWord0(fromDb,":",2,true);
			String sql1 = HagUtil.getWord0(fromDb,":",3,true);
			String batchName1 = HagUtil.getWord0(fromDb,":",1,true);
			app1=HagUtil.replaceStr(app1,"_","");
			app1=app1.substring(0,app1.length()-3);

			sql1=HagUtil.replaceStr(sql1,"_","");
			
			batchName1=HagUtil.replaceStr(batchName1,"_","");
			batchName1=batchName1.substring(0,batchName1.length()-3);

			ans.add("<tr><td>"+app1+"</td><td>"+sql1+"</td><td>"+batchName1+"</td></tr>");

			ans.add("</table><br>");
		}
		
		if(title.equals("Gard-Replace database from bk file") ) {
			ans.add("<h3>source:</h3>");
			ans.add("<table bgColor=\"#cccccc\" cellpadding=\"3\" cellspacing=\"3\" border =\"1\" size=\"180\">");
			ans.add("<tr><td>"+fromBk+"</td></tr>");

			ans.add("</table><br>");
		}

		ans.add("<h3>Note:</h3>");
		ans.add("<table bgColor=\"#cccccc\" cellpadding=\"3\" cellspacing=\"3\" border =\"1\" size=\"180\">");
		ans.add("<tr><td>"+note+"</td></tr>");

		ans.add("</table><br>");
		
		
		int ind = HagUtil.getRequestInd();
		
		String indS = HagUtil.padNumLeft(""+ind, 5, '0');
		String ccList 	= HagUtil.getRiMails("all");
		//String ccList 	= "david.ha@sapiens.com";
		String toList 	= "david.ha@sapiens.com;gonen.s@sapiens.com;liat.yanco@sapiens.com;david.b@sapiens.com";
		//String toList 	= "david.ha@sapiens.com";
		
		String subject = "#Request for REFRESH WIN ENV "+env+"("+app+") on GARD-environment , @BREAK-REQ@ #"+indS+"#   sent by "+user;
		if(!HagUtil.addRequest(ind,user, ".",subject,"./.",".",".",null,null,null,null,999))
			return HagUtil.shortHtml("Error adding request-mail sent to hagay & gonen, please call hagay or gonen", "red","bg");
		String ansS = ans.convertToString("");
		
		String ans9=HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+user,HagUtil.mailList_hag, subject,subject+"<br>"+ans.convertToString(" "));
		if(ans9.startsWith("0")) {
			return ansS+"<br><br> mail sent";
		}else {
			return ansS+"<br><br> "+HagUtil.shortHtml("failed to send mail - please call hagay -2527" , "red", "bg");
		}
	}


	public void 		HotfixRequestNew_buildFolders(HagRc hagRc,String folderS,String hotfix,String iomToPack){
		//folders
		HagUtil.reCreateFolder(hagRc,folderS);
		if(hagRc.rc!=0){
			hagRc.log.writeToFile(folderS+"\\packLog.txt",null,false);
			return;
		}
		HagUtil.reCreateFolder(hagRc,folderS+"\\config");
		HagUtil.reCreateFolder(hagRc,folderS+"\\DDC");
		HagUtil.reCreateFolder(hagRc,folderS+"\\fromTopack");
		HagUtil.reCreateFolder(hagRc,folderS+"\\skel");
		if(iomToPack.length()>0)
			HagUtil.reCreateFolder(hagRc,folderS+"\\iom"+hotfix);
		hagRc.log.writeToFile(folderS+"\\packLog.txt",null,false);
	}
	public void 		clientModulesRequest_buildFolders(HagRc hagRc,String folderS){
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
	public void 		HotfixRequestNew_buildFolders0609(HagRc hagRc,String folderS){
		//folders
		HagUtil.reCreateFolder(hagRc,folderS);
		if(hagRc.rc!=0){
			hagRc.log.writeToFile(folderS+"\\packLog.txt",null,false);
			return;
		}
		HagUtil.reCreateFolder(hagRc,folderS+"\\struct1");
		HagUtil.reCreateFolder(hagRc,folderS+"\\struct2");
		HagUtil.reCreateFolder(hagRc,folderS+"\\txt");
		hagRc.log.writeToFile(folderS+"\\packLog.txt",null,false);
	}
	public void 		HotfixRequestNew_ddcDummy(HagRc hagRc,String folderS){
		HagStringList dummyF = new HagStringList();
		dummyF.add("dummy");
		dummyF.writeToFile(folderS+"\\DDC\\dummyFile.txt",null,false);
	}
	public void 		HotfixRequestNew_iomZip(HagRc hagRc,String folderS,String hotfix,String iomToPack,String iomFromEnv,String iomFromServer){
		String iom = "IOM_Core";
		if(iomFromServer.equalsIgnoreCase("RIDEVBLP06"))
			iom = "iom";
		HagStringList iomList = new HagStringList(iomToPack,",",true);
		for(int i = 0 ;i < iomList.size();i++){
			String temp=iomList.get(i);
			String rc = HagUtil.simpleDosCmd("javaFunc~simpleDosCmd~copy~/Y~\""+"\\\\"+iomFromServer+"\\ri_share\\"+iomFromEnv+"\\"+iom+"\\"+temp+".*"+"\" \""+folderS+"\\iom"+hotfix+"\\"+"\"",false);
			if(!rc.startsWith("0~")){
				hagRc.rc=1;
				hagRc.log.add("failed to copy "+temp+".* from "+"\\\\"+iomFromServer+"\\ri_share\\"+iomFromEnv+"\\"+iom+"\\");
			}
		}
		if(hagRc.rc!=0)
			return;

		String ans2 = HagUtil.zip(folderS+"\\fromTopack\\iom"+hotfix+".zip",folderS+"\\iom"+hotfix+"\\", folderS + "\\iom"+hotfix+"zip_log.txt", "N", false, true);
		if(!ans2.startsWith("0~")){
			hagRc.rc=1;
			hagRc.log.add("failed to zip "+folderS+"\\iom"+hotfix+" into "+folderS+"\\fromTopack\\iom"+hotfix+".zip "+ans2);
		}
	}
	public void 		HotfixRequestNew_configTxt(HagRc hagRc,String folderS,String ver1,String ver2,String maint,String update,String hotfix){
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
		config.add("hagLog="+ver1+ver2+"M"+maint);
		config.add("hagJiraVer="+ver1+ver2+"M"+maint+"u"+update+"hf"+hotfix+"(FTP)");
		config.writeToFile(folderS+"\\skel\\config.txt",null,false);
	}
	public void 		clientModuleRequest_configTxt(HagRc hagRc,String folderS,String ver){
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
	public void 		HotfixRequestNew_configTxt0609(HagRc hagRc,String folderS,String ver1,String ver2,String maint,String update,String hotfix){
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
		config.add("hagLog="+ver1+ver2+"M"+maint);
		config.add("hagJiraVer="+ver1+ver2+"M"+maint+"u"+update+"hf"+hotfix+"(FTP)");
		config.writeToFile(folderS+"\\txt\\config.txt",null,false);
	}
	public void 		HotfixRequestNew_todo_win(HagRc hagRc,String folderS,HagStringList list,StringBuilder migD,String ver,String maint,String update,String hotfix,String eMergeClientSP3){
		String toDo1 = "\\\\ri-archive\\Saptech-Archive\\RI\\HAT_pack\\skel_hotfix\\bin\\config\\tree\\riInstallHotfix\\todo1.txt";
		if(eMergeClientSP3.equals("NO"))
			toDo1 = "\\\\ri-archive\\Saptech-Archive\\RI\\HAT_pack\\skel_hotfix\\bin\\config\\tree\\riInstallHotfix\\todo1a.txt";
		String toDo2 = "\\\\ri-archive\\Saptech-Archive\\RI\\HAT_pack\\skel_hotfix\\bin\\config\\tree\\riInstallHotfix\\todo2.txt";
		String tgtFile1="\\\\orsayserver\\d\\gonteam\\RI\\Hotfix_Final_Accum\\CFG\\WIN\\"+ver+"M"+maint+"U"+update+"_Hotfix"+hotfix+"_todo1.txt";
		String tgtFile2="\\\\orsayserver\\d\\gonteam\\RI\\Hotfix_Final_Accum\\CFG\\WIN\\"+ver+"M"+maint+"U"+update+"_Hotfix"+hotfix+"_todo2.txt";
		
		String copyRc1 = HagUtil.copyFileViaDos(toDo1, tgtFile1);
		String copyRc2 = HagUtil.copyFileViaDos(toDo2, tgtFile2);
		if(!copyRc1.startsWith("0~")) {
			hagRc.rc=1;
			hagRc.log.add(copyRc1);
		}
		if(!copyRc2.startsWith("0~")) {
			hagRc.rc=1;
			hagRc.log.add(copyRc2);
		}
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
			temp122.writeToFile(folderS+"\\fromTopack\\hotFixMigDetails.sql",null,false);
			toDoListOrder2.add("*hotFixMigDetails_before  | hotFixMigDetails_before   | Y | N | Y | 50 | hotFixMigDetails_before| #RiPackage,SQL_server,Bulk_user,Bulk_pass,Database_name                                                    | 1 | N");     
			}
		//todo
	
		
		toDoList.append(toDoList1);
		
		for(int i = 0;i<list.size();i++){
			String temp = list.get(i);
			String  w0 = HagUtil.getWord0(temp,"~",0,true);
			String  w1 = HagUtil.getWord0(temp,"~",1,true);
			if(w0.equals("replaceWarAndJasper")){
				toDoListOrder1.add("Replace war              | ReplaceRiWebWar           | Y | N | Y | 50 | Replace war        | riJava_folder,Batch_name,#RiPackage                                                                        | 1 | N");    
				toDoListOrder1.add("Jasper update            | JasperUpdate              | Y | N | Y | 50 | Jasper update      | #InstallationTypeHotFix,#RiPackage,riJava_folder,Batch_name,SQL_server,Bulk_user,Bulk_pass,Database_name   | 1 | N");  
			}
			if(w0.equals("replaceJar")){
				toDoListOrder1.add("Replace Jar              | ReplaceJar#batch-MSS      | Y | N | Y | 50 | Replace Jar        | !"+w1+",riJava_folder,#RiPackage                                                                           | 1 | N");      
			}
			if(w0.equals("replaceDdc")){
				toDoListOrder3.add("Replace DDC tables       | ReplaceDDC                | Y | N | Y | 50 | Replace DDC tables | #InstallationTypeHotFix,#RiPackage,SQL_server,Bulk_user,Bulk_pass,Database_name                            | 1 | N");  
			}
			if(w0.equals("replaceScripts")){
				toDoListOrder4.add("ReplaceAdd Scripts    | ReplaceAddScripts        | Y | N | Y | 50 | ReplaceAdd Scripts | Batch_name,#RiPackage                                                                                      | 1 | N");
			}
			if(w0.equals("replaceWebProxy")){
				toDoListOrder5.add("Copy WebConfig file       | copyWebConfig             | Y | N | Y | 50 | copy WebConfig file         |                                  | 1 | N");       
				toDoListOrder5.add("Set WebProxy-IIS (before) | SetWebProxyIIS_before     | Y | N | Y | 50 | Set WebProxy-IIS (before)   | riJava_folder                    | 1 | N");           
				toDoListOrder5.add("Call WebProxy installer   | callWebProxyInstaller     | Y | N | Y | 50 | Call WebProxy installer     | riJava_folder,#RiPackage         | 1 | N");           
				toDoListOrder5.add("Set WebProxy-IIS (after)  | SetWebProxyIIS_after      | Y | N | Y | 50 | Set WebProxy-IIS (after)    |                                  | 1 | N");           
				toDoListOrder5.add("Restore WebConfig file    | restoreWebConfig          | Y | N | Y | 50 | restore WebConfig file      |                                  | 1 | N");       
			}
			if(w0.equals("replaceIom")){
				toDoListOrder2.add("Copy IOM                 | CopyIom                   | Y | N | Y | 50 | Copy IOM           | IOM_folder,#RiPackage,!iom"+hotfix+".zip                                                                                      | 1 | N");  
			}
			if(w0.equals("manualMigs")){
				//String www = 	id+"#"+jClass+"#"+runType+"#"+runType+"#"+cust;
				String  id = HagUtil.getWord0(w1,"#",0,true);
				String  jClass = HagUtil.getWord0(w1,"#",1,true);
				String  runType = HagUtil.getWord0(w1,"#",2,true);
				String  run = HagUtil.getWord0(w1,"#",3,true);
				String  cust = HagUtil.getWord0(w1,"#",4,true);
				String ww= id+"#"+jClass+"#"+runType;
				toDoListOrder2.add("Manual Mig "+ww+"          | ManualMig#"+ww+"    | Y | N | Y | 50 |     Manual Mig     | riJava_folder,Batch_name,#RiPackage,!"+ww+",SQL_server,Database_name,Bulk_user,Bulk_pass,!"+run+",!"+cust+"                                            | 1 | N");                                                                                                                                                                                                                       
			}
		}
		if(migD!=null){
			toDoListOrder2.add("*hotFixMigDetails_after   | hotFixMigDetails_after    | Y | N | Y | 50 | hotFixMigDetails_after| #RiPackage,SQL_server,Bulk_user,Bulk_pass,Database_name                                                    | 1 | N");     
		}
	
		toDoList.append(toDoListOrder1);
		toDoList.append(toDoListOrder2);
		toDoList.append(toDoListOrder3);
		toDoList.append(toDoListOrder4);
		toDoList.append(toDoListOrder5);
		toDoList.append(toDoList2);
	
		toDoList.writeToFile(folderS+"\\config\\todo.txt",null,false);
	
		
	}

	public void 		HotfixRequestNew_todo_winSpr1003(HagRc hagRc,String folderS,HagStringList list,StringBuilder migD,String hotfix){
		String toDo1 = "\\\\ri-archive\\Saptech-Archive\\RI\\HAT_pack\\skel_hotfix\\bin\\config\\tree\\riInstallHotfix\\todo1.txt";
		String toDo2 = "\\\\ri-archive\\Saptech-Archive\\RI\\HAT_pack\\skel_hotfix\\bin\\config\\tree\\riInstallHotfix\\todo2.txt";
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
			temp122.writeToFile(folderS+"\\fromTopack\\hotFixMigDetails.sql",null,false);
			toDoListOrder2.add("*hotFixMigDetails_before  | hotFixMigDetails_before   | Y | N | Y | 50 | hotFixMigDetails_before| #RiPackage,SQL_server,Bulk_user,Bulk_pass,Database_name                                                    | 1 | N");     
			}
		//todo
	
		
		toDoList.append(toDoList1);
		
		for(int i = 0;i<list.size();i++){
			String temp = list.get(i);
			String  w0 = HagUtil.getWord0(temp,"~",0,true);
			String  w1 = HagUtil.getWord0(temp,"~",1,true);
			if(w0.equals("replaceWarAndJasper")){
				toDoListOrder1.add("Replace war              | ReplaceRiWebWar           | Y | N | Y | 50 | Replace war        | riJava_folder,Batch_name,#RiPackage                                                                        | 1 | N");    
				toDoListOrder1.add("Jasper update            | JasperUpdate              | Y | N | Y | 50 | Jasper update      | #InstallationTypeHotFix,#RiPackage,riJava_folder,Batch_name,SQL_server,Bulk_user,Bulk_pass,Database_name   | 1 | N");  
			}
			if(w0.equals("replaceJar")){
				toDoListOrder1.add("Replace Jar              | ReplaceJar#batch-MSS      | Y | N | Y | 50 | Replace Jar        | !"+w1+",riJava_folder,#RiPackage                                                                           | 1 | N");      
			}
			if(w0.equals("replaceDdc")){
				toDoListOrder3.add("Replace DDC tables       | ReplaceDDC                | Y | N | Y | 50 | Replace DDC tables | #InstallationTypeHotFix,#RiPackage,SQL_server,Bulk_user,Bulk_pass,Database_name                            | 1 | N");  
			}
			if(w0.equals("replaceScripts")){
				toDoListOrder4.add("ReplaceAdd Scripts    | ReplaceAddScripts        | Y | N | Y | 50 | ReplaceAdd Scripts | Batch_name,#RiPackage                                                                                      | 1 | N");
			}
			if(w0.equals("replaceWebProxy")){
				toDoListOrder5.add("Copy WebConfig file       | copyWebConfig             | Y | N | Y | 50 | copy WebConfig file         |                                  | 1 | N");       
				toDoListOrder5.add("Set WebProxy-IIS (before) | SetWebProxyIIS_before     | Y | N | Y | 50 | Set WebProxy-IIS (before)   | riJava_folder                    | 1 | N");           
				toDoListOrder5.add("Call WebProxy installer   | callWebProxyInstaller     | Y | N | Y | 50 | Call WebProxy installer     | riJava_folder,#RiPackage         | 1 | N");           
				toDoListOrder5.add("Set WebProxy-IIS (after)  | SetWebProxyIIS_after      | Y | N | Y | 50 | Set WebProxy-IIS (after)    |                                  | 1 | N");           
				toDoListOrder5.add("Restore WebConfig file    | restoreWebConfig          | Y | N | Y | 50 | restore WebConfig file      |                                  | 1 | N");       
			}
			if(w0.equals("replaceIom")){
				toDoListOrder2.add("Copy IOM                 | CopyIom                   | Y | N | Y | 50 | Copy IOM           | IOM_folder,#RiPackage,!iom"+hotfix+".zip                                                                                      | 1 | N");  
			}
			if(w0.equals("manualMigs")){
				//String www = 	id+"#"+jClass+"#"+runType+"#"+runType+"#"+cust;
				String  id = HagUtil.getWord0(w1,"#",0,true);
				String  jClass = HagUtil.getWord0(w1,"#",1,true);
				String  runType = HagUtil.getWord0(w1,"#",2,true);
				String  run = HagUtil.getWord0(w1,"#",3,true);
				String  cust = HagUtil.getWord0(w1,"#",4,true);
				String ww= id+"#"+jClass+"#"+runType;
				toDoListOrder2.add("Manual Mig "+ww+"          | ManualMig#"+ww+"    | Y | N | Y | 50 |     Manual Mig     | riJava_folder,Batch_name,#RiPackage,!"+ww+",SQL_server,Database_name,Bulk_user,Bulk_pass,!"+run+",!"+cust+"                                            | 1 | N");                                                                                                                                                                                                                       
			}
		}
		if(migD!=null){
			toDoListOrder2.add("*hotFixMigDetails_after   | hotFixMigDetails_after    | Y | N | Y | 50 | hotFixMigDetails_after| #RiPackage,SQL_server,Bulk_user,Bulk_pass,Database_name                                                    | 1 | N");     
		}
	
		toDoList.append(toDoListOrder1);
		toDoList.append(toDoListOrder2);
		toDoList.append(toDoListOrder3);
		toDoList.append(toDoListOrder4);
		toDoList.append(toDoListOrder5);
		toDoList.append(toDoList2);
	
		toDoList.writeToFile(folderS+"\\config\\todo.txt",null,false);
	
		
	}

	
	public void 		clientModuleRequest_todo(HagRc hagRc,String folderS,HagStringList list,StringBuilder migD){
		String toDo1 = "\\\\ri-archive\\Saptech-Archive\\RI\\HAT_pack\\skel_clientModule\\bin\\config\\tree\\riInstallClientModule\\todo1.txt";
		String toDo2 = "\\\\ri-archive\\Saptech-Archive\\RI\\HAT_pack\\skel_clientModule\\bin\\config\\tree\\riInstallClientModule\\todo2.txt";
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
			temp122.writeToFile(folderS+"\\fromTopack\\clientModuleMigDetails.sql",null,false);
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
				toDoListOrder2.add("Manual Mig "+ww+"          | cmManualMig#"+ww+"    | Y | N | Y | 50 |     Manual Mig     | riJava_folder,Batch_name,#cmPackage,!"+ww+",SQL_server,Database_name,Bulk_user,Bulk_pass,!"+run+",!"+cust+"                                            | 1 | N");                                                                                                                                                                                                                       
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

	public void 		HotfixRequestNew_todo_i5os(HagRc hagRc,String folderS,HagStringList list,StringBuilder migD){
		String toDo1 = "\\\\orsayserver\\d\\gonteam\\RI\\Hotfix_Final_Accum\\CFG\\AS4\\ToDo_Header.txt";
		String toDo2 = "\\\\orsayserver\\d\\gonteam\\RI\\Hotfix_Final_Accum\\CFG\\AS4\\ToDo_Trailer.txt";
		HagStringList toDoList1 = new HagStringList(toDo1,false,"asasa",false);
		HagStringList toDoList2 = new HagStringList(toDo2,false,"asasa",false);
		HagStringList toDoList = new HagStringList();
		HagStringList toDoListOrder1 = new HagStringList();
		HagStringList toDoListOrder2 = new HagStringList();
		HagStringList toDoListOrder3 = new HagStringList();
		
		//todo
	
		
		toDoList.append(toDoList1);
		toDoList.add("*JRN      - 814  N   N 0   GSINJRN@FID(~ACT_HI_IDENTITYLINK~)@JOPT(Y)@DB2LIB(#DB2#)");
		toDoList.add("*JRN      - 814  N   N 0   GSINJRN@FID(~ACT_HI_VARINST~)@JOPT(Y)@DB2LIB(#DB2#)");
		toDoList.add("*JRN      - 814  N   N 0   GSINJRN@FID(~ACT_RE_MODEL~)@JOPT(Y)@DB2LIB(#DB2#)");
		toDoList.add("*JRN      - 814  N   N 0   GSINJRN@FID(~ACT_RU_JOB~)@JOPT(Y)@DB2LIB(#DB2#)");
		toDoList.add("*JRN      - 814  N   N 0   GSINJRN@FID(~ACT_HI_ACTINST~)@JOPT(Y)@DB2LIB(#DB2#)");
		toDoList.add("*SQL      - 816  N   A @SQLA1 >0 @SQLA2");
		toDoList.add("*");
		
		if(migD!=null){
				toDoListOrder2.add(" CPY IFS  - 810  N   A mig-mng.jar  /CFGRI/#DB2#/M#MNT#");     
		}
		for(int i = 0;i<list.size();i++){
			String temp = list.get(i);
			String  w0 = HagUtil.getWord0(temp,"~",0,true);
			String  w1 = HagUtil.getWord0(temp,"~",1,true);
			if(w0.equals("replaceWarAndJasper")){
				toDoListOrder1.add(" REPL WAR - 815  N   A 0     replace_ri_web_war");    
			}
			if(w0.equals("replaceJar")){
				toDoListOrder1.add(" RUN CMD  - 814  N   N ANY RMVLNK@OBJLNK(~/CFGRI/#DB2#/M#MNT#/tomcat/webapps/ri-web/WEB-INF/lib/batch-DB2-*.jar~)");       
				toDoListOrder1.add(" CPY IFS  - 810  N   A "+w1+"  /CFGRI/#DB2#/M#MNT#/tomcat/webapps/ri-web/WEB-INF/lib/");
			}
			if(w0.equals("replaceDdc")){
				toDoListOrder3.add(" DDC      - 045  N   A");  
			}
			if(w0.equals("replaceIom")){
				toDoListOrder3.add(" IOM      - 817  N N 0 .@@@@"+w1); 
			}
			if(w0.equals("manualMigs")){
				String  id = HagUtil.getWord0(w1,"#",0,true);
				String  jClass = HagUtil.getWord0(w1,"#",1,true);
				String  runType = HagUtil.getWord0(w1,"#",2,true);
				String  run = HagUtil.getWord0(w1,"#",3,true);
				String runI5os = getRunI5os(run);
				if(runI5os==null){
					hagRc.rc=1;
					hagRc.log.add("runTypes U and DU not supported for as400 platform.<br> need to make sure the classes are in the mig-mng.jar<br>and it will be added to the mig_details table when running the manual mig(only in I5OS)");
					return;
				}
				toDoListOrder2.add(" GSINMIG  - 811  N   A "+id+" "+jClass+" "+runType+" ALL "+runI5os);
			}
		}
	
		toDoList.append(toDoListOrder1);
		toDoList.append(toDoListOrder2);
		toDoList.append(toDoListOrder3);
		toDoList.append(toDoList2);
	
		toDoList.writeToFile(folderS+"\\LOG.R_LOG_SCRB",null,false);
	
		
	}
	public String getRunI5os(String run){
		if(run.equals("UR"))
			return "RN";
		//if(run.equals("D"))
		//	return "DO";
		//if(run.equals("DUR"))
		//	return "DR";
		//if(run.equals("U"))
		//	return "U";
			//UR=RN,U==UpdateOrInsert only,D=DO,DUR=DR,DU==DeleteAndInsert only	
			return null;
	}
	public String 		sendHotfixRequestNew_prepPackage_win(HagRc hagRc,String ver,String maint,String update,String hotfix,StringBuilder migD,HagStringList toDoList,String iomToPack,String iomFromEnv,String iomFromServer,String eMergeClientSP3){
		String ver1=ver.substring(0,2);
		String ver2=ver.substring(2,4);
		String folderS = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\cfg\\hotfix\\ARIA\\"+ver1+"."+ver2+"."+maint+"."+update+"."+hotfix;
		String folderS1 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\cfg\\hotfix\\ARIA\\backup\\";
		

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
		HotfixRequestNew_buildFolders(hagRc,folderS,hotfix,iomToPack);
		if(hagRc.rc!=0)	return folderS;
		
		//ddc\\dummy.txt
		HotfixRequestNew_ddcDummy(hagRc,folderS);
		if(hagRc.rc!=0)	return folderS;
		
		//skel\\config.txt
		HotfixRequestNew_configTxt(hagRc,folderS,ver1,ver2,maint,update,hotfix);
		if(hagRc.rc!=0)	return folderS;
		
		
		//config\\todo.txt
		HotfixRequestNew_todo_win(hagRc,folderS,toDoList,migD, ver, maint, update,hotfix,eMergeClientSP3);
		if(hagRc.rc!=0)	return folderS;
		
		//iom.zip
		if(iomToPack.length()>0){
			HotfixRequestNew_iomZip(hagRc,folderS,hotfix,iomToPack,iomFromEnv,iomFromServer);
			if(hagRc.rc!=0)	return folderS;
		}	
		
		hagRc.log.writeToFile(folderS+"\\packLog.txt",null,false);
		return folderS;
		
	}
	public String 		sendClientModuleRequest_prepPackage(HagRc hagRc,String ver,StringBuilder migD,HagStringList toDoList){
		String folderS = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\cfg\\clientModule\\ARIA\\"+ver;
		String folderS1 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\cfg\\clientModule\\ARIA\\backup\\";
		

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
		clientModulesRequest_buildFolders(hagRc,folderS);
		if(hagRc.rc!=0)	return folderS;
		
	
		//skel\\config.txt
		clientModuleRequest_configTxt(hagRc,folderS,ver);
		if(hagRc.rc!=0)	return folderS;
		
		
		//config\\todo.txt
		clientModuleRequest_todo(hagRc,folderS,toDoList,migD);
		if(hagRc.rc!=0)	return folderS;
		
		
		
		hagRc.log.writeToFile(folderS+"\\packLog.txt",null,false);
		return folderS;
		
	}
	
	public String 		sendHotfixRequestNew_prepPackage_win0609(HagRc hagRc,String ver,String maint,String update,String hotfix,StringBuilder migD,HagStringList toDoList){
		String ver1=ver.substring(0,2);
		String ver2=ver.substring(2,4);
		String	folderS = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\cfg\\hotfix\\cmInstaller\\"+ver+"."+maint+"."+update+"."+hotfix;
		String	folderS1 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\cfg\\hotfix\\cmInstaller\\backup\\";
		

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
		HotfixRequestNew_buildFolders0609(hagRc,folderS);
		if(hagRc.rc!=0)	return folderS;
		//skel\\config.txt
		HotfixRequestNew_configTxt0609(hagRc,folderS,ver1,ver2,maint,update,hotfix);
		if(hagRc.rc!=0)	return folderS;
				
		//ddc\\dummy.txt
		//HotfixRequestNew_ddcDummy(hagRc,folderS);
		if(hagRc.rc!=0)	return folderS;
		
		
		
		
		//config\\todo.txt
		HotfixRequestNew_todo_win(hagRc,folderS,toDoList,migD,ver,maint,update,hotfix,"NO");

		
		hagRc.log.writeToFile(folderS+"\\packLog.txt",null,false);
		return folderS;
		
	}
	public String 		sendHotfixRequestNew_prepPackage_I5OS(HagRc hagRc,String ver,String maint,String update,String hotfix,StringBuilder migD,HagStringList toDoList){
		String ver1=ver.substring(0,2);
		String ver2=ver.substring(2,4);
		String	folderS = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V"+ver1+ver2+"m"+maint+"U00\\U"+update+"\\Hotfix"+hotfix+"\\ALL\\";

		File folder = new File(folderS);
		if(!folder.exists() || !folder.isDirectory()){
			HagUtil.reCreateFolder(hagRc,folderS);
		}
		if(hagRc.rc!=0)	return folderS;
		
		//config\\todo.txt
		HotfixRequestNew_todo_i5os(hagRc,folderS,toDoList,migD);

		
		hagRc.log.writeToFile(folderS+"\\packLog.txt",null,false);
		return folderS;
		
	}
	
	public String 		distRequests(HttpServletRequest request, HttpServletResponse response){
		String cfgMenuWebUser 	= request.getParameter("cfgMenuWebUser");
		String cfgMenuWebPass 	= request.getParameter("cfgMenuWebPass");
		String act = request.getParameter( "act" );

		String auth = HagUtil.isAuthorized(act,cfgMenuWebUser,cfgMenuWebPass);
		if(!auth.equals("OK")){
			return HagUtil.shortHtml(auth , "red", "bg");
		}

		if(act.equals("reqInstallLogic"))
			return requstsHandle( cfgMenuWebUser,request,  response);
		if(act.equals("reqReplaceDbFromBackup"))
			return requstsHandleReplaceDbFromBackup( cfgMenuWebUser,request,  response);
		if(act.equals("reqCreateHotfixCI")) //after
			//return requstsHandleAfter( cfgMenuWebUser,request,  response);
			return requstsHandleAfter1( cfgMenuWebUser,request,  response);
		if(act.equals("reqCreateHotfixA")) //after
			//return requstsHandleAfter( cfgMenuWebUser,request,  response);
			return requstsHandleAriaAfter1( cfgMenuWebUser,request,  response);
		return "xxx";
		/*
		String [] cbEnvs	= request.getParameterValues("cb");
		if (cbEnvs == null || cbEnvs.length == 0) {
			return "Please select at least one environment";
		}
		String cbList="";
		HagStringList xxx = new HagStringList();
		if (cbEnvs != null && cbEnvs.length != 0) {
			for (int i = 0; i < cbEnvs.length; i++) {
				cbList = cbList + "<br>" + cbEnvs[i];
				xxx.add(cbEnvs[i]);
			}
		}
		//HagUtil.p(act);
		act=act.trim();
		if(act.equals("reqInstallLogic"))
			return requstsHandle( xxx,  response);

		return "xxx";
		 */
	}
	
	//E
	//F
	public String 		filterRiEnvs(HttpServletRequest request, HttpServletResponse response){
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
		String col13 = request.getParameter( "col13" );

		String[] filter = {	col1.trim(),col2.trim(),col3.trim(),
				col4.trim(),col5.trim(),col6.trim(),
				col7.trim(),col8.trim(),col9.trim(),
				col10.trim(),col11.trim(),col12.trim(),col13.trim()};

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
				"sql_server_version " +
				" from dbo.ri_environments_new where project <> 'HAGWIDTH' and status ='A'  order by bis_server,batchName";
		String rc= HagJdbc.selectRiEnvs("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans,true,filter,cfgMenuWebUser,cfgMenuWebPass);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = HagUtil.cfgMenuWebLoc+"\\tableSortT1.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = HagUtil.cfgMenuWebLoc+"\\tableSortB1.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}

	}
	
	public String 		filterRiEnvsAzure(HttpServletRequest request, HttpServletResponse response){
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
		String col13 = request.getParameter( "col13" );

		String[] filter = {	col1.trim(),col2.trim(),col3.trim(),
				col4.trim(),col5.trim(),col6.trim(),
				col7.trim(),col8.trim(),col9.trim(),
				col10.trim(),col11.trim(),col12.trim(),col13.trim()};

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
				" from dbo.ri_environments_EXT where project <> 'HAGWIDTH' and status ='A'  order by bis_server,batchName";
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
		String rc= HagJdbc.selectRiEnvsAzure("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans,true,filter,cfgMenuWebUser,cfgMenuWebPass);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = HagUtil.cfgMenuWebLoc+"\\tableSortT1.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = HagUtil.cfgMenuWebLoc+"\\tableSortB1.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}

	}
	public String 		generalRequest(HttpServletRequest request, HttpServletResponse response){
		String cfgMenuWebUser 	= request.getParameter("cfgMenuWebUser");
		String subject 	= request.getParameter("subject");
		String note 	= request.getParameter("note");
		int ind = HagUtil.getRequestInd();
		
		String indS = HagUtil.padNumLeft(""+ind, 5, '0');
		String subject1 	= "#Request General "+subject+" @BREAK-REQ@ #"+indS+"# sent by "+cfgMenuWebUser;
	
		if(!HagUtil.addRequest(ind,cfgMenuWebUser, note,subject1,".",".",".",null,null,null,null,999))
			return HagUtil.shortHtml("Failed to update req_ind_log_new table.", "red","bg");
		HagUtil.writeToRelDiary2("General","WIN","Request",".",".",".",".",".","#"+indS,cfgMenuWebUser,".",".");
		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+cfgMenuWebUser,HagUtil.mailList_hag,subject1,note); 
		if(!ans1.startsWith("0~")) 
			return HagUtil.shortHtml("Failed to send mail.<br>"+ans1, "red","bg");
		
	
		StringBuilder ans = new StringBuilder("<h3>General request sent by "+cfgMenuWebUser+"</h3>");
		ans.append("<br><br>");
		ans.append("Subject:");
		ans.append("<br>");
		ans.append(subject);
		ans.append("<br><br>");
		ans.append("Content:");
		ans.append("<br>");
		ans.append(note);
		ans.append("<br><br>");
		return ans.toString();

	}
	public String 		filterGardEnvs(HttpServletRequest request, HttpServletResponse response){
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
	

		String[] filter = {	col1.trim(),col2.trim(),col3.trim(),
				col4.trim(),col5.trim(),col6.trim(),col7.trim(),col8.trim(),col9.trim()
	};

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
		String rc= HagJdbc.selectGardEnvs("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans,true,filter,cfgMenuWebUser,cfgMenuWebPass);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = HagUtil.cfgMenuWebLoc+"\\tableSortT1.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = HagUtil.cfgMenuWebLoc+"\\tableSortB1.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}

	}
	
	public String 		filterRiServers(HttpServletRequest request, HttpServletResponse response){
		String cfgMenuWebUser 	= request.getParameter("cfgMenuWebUser");
		String cfgMenuWebPass 	= request.getParameter("cfgMenuWebPass");

		String col1 = request.getParameter( "col1" );
		String col2 = request.getParameter( "col2" );
		String col3 = request.getParameter( "col3" );
	
	

		String[] filter = {	col1.trim(),col2.trim(),col3.trim()	};

		HagStringList ans = new	HagStringList(); 
		String stm = "select project," +
				"serverName," +
				"serverIp," +
				"type," +
				"note " +
				" from dbo.ri_servers where project <> 'HAGWIDTH' ";
		String rc= HagJdbc.selectRiServers("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans,true,filter,cfgMenuWebUser,cfgMenuWebPass);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = HagUtil.cfgMenuWebLoc+"\\tableSortT1.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = HagUtil.cfgMenuWebLoc+"\\tableSortB1.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}

	}
	public String 		filterAddMigs(HttpServletRequest request, HttpServletResponse response){
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
				" from dbo.add_mig order by dt_dev desc";
		String rc= HagJdbc.selectMigsList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans,true,filter,cfgMenuWebUser,cfgMenuWebPass);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = HagUtil.cfgMenuWebLoc+"\\tableSortT11.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = HagUtil.cfgMenuWebLoc+"\\tableSortB11.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}

	}
	public String 		filterDeleteFromMigDetailsAddMigs(HttpServletRequest request, HttpServletResponse response){
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
	
		String[] filter = {	col1.trim(),col2.trim(),col3.trim(),
				col4.trim(),col5.trim(),col6.trim(),
				col7.trim(),col8.trim()};

		HagStringList ans = new	HagStringList(); 
	  	String stm = "select relevant,"+
  				"version," +
  				"jclass," +
  				"location," +
  				"customer," +
  				"owner," +
  				"date," +
  				"status," +
     				"note " +
  				" from dbo.DeleteFromMigDetails where relevant='YES' AND status ='ON' order by date";
		String rc= HagJdbc.selectDeleteFromMigDetailsList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans,true,filter,cfgMenuWebUser,cfgMenuWebPass);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String strT="<table bgColor=\"C9EAF3\" id=\"table2\" cellpadding=\"1\" cellspacing=\"1\" border=\"1\" width = \"100%\">";
		//	String ffT = HagUtil.cfgMenuWebLoc+"\\tableSortT11.html";
		//	HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
		//	String strT = listT.convertToString("\n");
		//	String ffB = HagUtil.cfgMenuWebLoc+"\\tableSortB11.html";
		//	HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
		//	String strB = listB.convertToString("\n");
			return strT+str;
		}else{
			return rc;
		}

	}
	
	
	
	
	
	
	public String 		filterClientModuleAddMigs(HttpServletRequest request, HttpServletResponse response){
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
				" from dbo.add_mig_clientModule order by dt_dev desc";
		String rc= HagJdbc.selectClientModuleMigsList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans,true,filter,cfgMenuWebUser,cfgMenuWebPass);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = HagUtil.cfgMenuWebLoc+"\\tableSortT11.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = HagUtil.cfgMenuWebLoc+"\\tableSortB11.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}

	}
	
	public String 		filterAddMigs1(HttpServletRequest request, HttpServletResponse response){
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
		String rc= HagJdbc.selectMigsList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans,true,filter,cfgMenuWebUser,cfgMenuWebPass);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = HagUtil.cfgMenuWebLoc+"\\tableSortT11.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = HagUtil.cfgMenuWebLoc+"\\tableSortB11.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}

	}
	public String 		filterRiEnvsDontMess(HttpServletRequest request, HttpServletResponse response){
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
		String col13 = request.getParameter( "col13" );
		String[] filter = {	col1.trim(),col2.trim(),col3.trim(),
				col4.trim(),col5.trim(),col6.trim(),
				col7.trim(),col8.trim(),col9.trim(),
				col10.trim(),col11.trim(),col12.trim(),col13.trim()};

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
				" from dbo.ri_environments_new where project <> 'HAGWIDTH' and status ='A'";
		String rc= HagJdbc.selectRiEnvsDontMess("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans,true,filter,cfgMenuWebUser,cfgMenuWebPass);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = HagUtil.cfgMenuWebLoc+"\\tableSortT_dontMess.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = HagUtil.cfgMenuWebLoc+"\\tableSortB_dontMess.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}

	}
	//spr1008
	public String 		filterRiEnvsI5os(HttpServletRequest request, HttpServletResponse response){
		String cfgMenuWebUser 	= request.getParameter("cfgMenuWebUser");
		String cfgMenuWebPass 	= request.getParameter("cfgMenuWebPass");

		String col1 = request.getParameter( "col1" );
		String col2 = request.getParameter( "col2" );
		String col3 = request.getParameter( "col3" );
		String col4 = request.getParameter( "col4" );
		String col5 = request.getParameter( "col5" );
		String col6 = request.getParameter( "col6" );
		String col7 = request.getParameter( "col7" );

		String[] filter = {	col1.trim(),col2.trim(),col3.trim(),
				col4.trim(),col5.trim(),col6.trim(),col7.trim()
		};

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

				" from dbo.ri_environments_i5os where project <> 'HAGWIDTH'";
		String rc= HagJdbc.selectRiEnvsI5os("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans,true,filter,cfgMenuWebUser,cfgMenuWebPass);
		if(rc.startsWith("0~")){
			String str = ans.convertToString(" ");
			String ffT = HagUtil.cfgMenuWebLoc+"\\tableSortT2.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = HagUtil.cfgMenuWebLoc+"\\tableSortB2.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}

	}
	public String 		requestNewAfter1(HttpServletRequest request, HttpServletResponse response){
		StringBuilder ans = new StringBuilder();
		HagRequests hagRequests = new HagRequests();
		//String list2= request.getParameter( "list2" );
		String user= request.getParameter( "user" );
		String test= request.getParameter( "test" );
		String Req_When_To_Install_date= request.getParameter( "Req_When_To_Install_date" );
		String Req_When_To_Install_time= request.getParameter( "Req_When_To_Install_time" );
		String prodTest="test";
		if(test==null)
			prodTest="prod";
		String prodTest1=HagParam.getStatus();
		if(prodTest1.equals("TEST"))
			prodTest="test";
		String[] paramsVals = new String[hagRequests.params.length];
		for(int i =0;i<paramsVals.length;i++) {
			paramsVals[i]= request.getParameter( hagRequests.params[i][2] );
			if(paramsVals[i]==null || paramsVals[i].trim().length()==0)
				paramsVals[i]=".";
			//if(i==27 &&paramsVals[0].equals("1")) {
			if(i==27 ) {
				ans.append(hagRequests.params[i][4]).append("  ").append(hagRequests.params[i][2]).append(" = ").append(Req_When_To_Install_date+"-"+Req_When_To_Install_time).append("\n");
			}else {
				ans.append(hagRequests.params[i][4]).append("  ").append(hagRequests.params[i][2]).append(" = ").append(paramsVals[i]).append("\n");
				
			}
			//String temp=ans.toString();
			//String aa="aa";
				
		}

		String base = "\\\\orsayserver\\d\\gonteam\\#VBscript_and_SQL\\"+prodTest+"\\Requests\\";
		//String base = "o:\\gonteam\\#VBscript_and_SQL\\"+prodTest+"\\Requests\\";
		String date = HagUtil.getDateTime("yyyyMMdd_HHmmssSSS");
		HagRc hagRc=new HagRc();
		HagUtil.reCreateFolder(hagRc, base+date);
		if(hagRc.rc!=0) {
			return HagUtil.shortHtml("Failed to recreate "+base+date+" folder<br>"+hagRc.log.convertToString("<br>"), "red","bg");
		}
		String inFileS = base+date+"\\inFile.txt";
		String outFileS = base+date+"\\outFile.txt";
		String logFileS = base+date+"\\logFile.txt";
		String cmdFileS = base+date+"\\cmdFile.txt";
		String cmdFileS1 = base+date+"\\fileFromCfgWeb.txt";
		HagUtil.writeStringToFile(inFileS, ans.toString());
		HagStringList cmdList = new HagStringList(); 
			//cmdList.add("\\\\ORSAYSERVER\\System32\\cscript.exe /nologo \\\\orsayserver\\d\\gonteam\\#VBscript_and_SQL\\"+prodTest+"\\VB_Request.vbs"); //08.04.2024
			cmdList.add("cscript.exe /nologo \\\\orsayserver\\d\\gonteam\\#VBscript_and_SQL\\"+prodTest+"\\VB_Request.vbs");//08.04.2024
			cmdList.add("/Validate_Only:\"NO\"");
			cmdList.add("/InFile:\""+inFileS+"\"");
			cmdList.add("/OutputFile:\""+outFileS+"\"");
			cmdList.add("/LogFile:\""+logFileS+"\"");
			cmdList.add("/InsertNewRequest:\"YES\"");
			cmdList.add("/ProdTest:\""+prodTest+"\"");
			cmdList.add("/DEBUG:\"N\"");
		//	HagStringList cmdList = new HagStringList(); 
		//	cmdList.add("s:\\windows\\System32\\cscript.exe /nologo o:\\gonteam\\#VBscript_and_SQL\\"+prodTest+"\\VB_Request.vbs");
		//	cmdList.add("/Validate_Only:\"NO\"");
		//	cmdList.add("/InFile:\""+inFileS+"\"");
		//	cmdList.add("/OutputFile:\""+outFileS+"\"");
		//	cmdList.add("/LogFile:\""+logFileS+"\"");
		//	cmdList.add("/InsertNewRequest:\"YES\"");
		//	cmdList.add("/ProdTest:\""+prodTest+"\"");
		//	cmdList.add("/DEBUG:\"N\"");
		String vbLine = cmdList.convertToString(" ");
		HagUtil.writeStringToFile(cmdFileS, vbLine);
		HagUtil.writeStringToFile(cmdFileS1, vbLine);
		//HagStringList  ans2 =  HagUtil.runCmd3(vbLine+"\n",false);
		HagClient hagClient = new HagClient();
		String server1="orsayserver";
		HagStringList ans2 = hagClient.runNotSudo(server1, vbLine);
		//if(1==1)
		//	return ans2.convertToString("<br>");
		if(ans2==null || ans2.size()==0) {
			//String ffa ="\\\\orsayserver\\d\\gonteam\\#VBscript_and_SQL\\PROD\\Requests\\"+date+"\\Final_RC.txt";
		//	HagStringList  ans2a = new 	HagStringList( ffa,true ,"asdfad",true); 
		//	if(ans2a==null || ans2a.size()==0) {
				String ans1111		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+user,HagUtil.mailList_hag,"Empty request return from vb sent by"+user,"."); 
				return HagUtil.shortHtml("We have a problem:Please check cfgMenuWeb -> RI->Requests->requests log if the request was opened ", "red","bg"); 	
			//}
			//ans2.dup(ans2a);
		}
		String rcLine = ans2.get(0);
	//	if(prodTest1.equals("TEST"))
		//	return rcLine;
	if(ans2==null) 
		return HagUtil.shortHtml("ans from gonen=null", "red","bg"); 	
	else if(ans2.size()==0) 
		return HagUtil.shortHtml("ans from gonen size =0", "red","bg"); 	
	else if(rcLine==null) 
			return HagUtil.shortHtml("rcLine from gonen=null", "red","bg"); 	
	else if(rcLine.indexOf("RC~99")>-1) {
		StringBuilder err = new StringBuilder();
		err.append("errors found in your request<br>please click the back arrow and fix the following errors<br>");
		String ans2a= ans2.get(0);
		HagStringList ans21 = new HagStringList(ans2a,"`",true);
		for(int i = 1;i<ans21.size();i++) {
			String temp = ans21.get(i);
			String no = HagUtil.getWord0(temp,"~",1,true);
			String msg = HagUtil.getWord0(temp,"~",2,true);
			err.append("<br><br>field #no = ").append(no).append("<br>error = ").append(msg);
		}
		if(user.equals("david.hagay")|| user.equals("gonen.shoham")) {
			err.append("<br><br>  CM only - extra lines:");
			for(int k = 0 ; k<ans2.size();k++) {
				err.append("<br>").append(ans2.get(k));
			}
		}
		return HagUtil.shortHtml(err.toString(), "red","bg"); 
	}else {
		//RC~00~3295~#Request to INSTALL RI93QA/AA with 08.01.01.00.00 (WIN) 
		StringBuilder good = new StringBuilder();
		String req = HagUtil.getWord0(rcLine,"~",2,true);
		String msg = HagUtil.getWord0(rcLine,"~",3,true);
		good.append("The following request successfully opened<br><br>").append("request #no = ").append(req).append("<br>request subject = ").append(msg);
		if(user.equals("david.hagay")|| user.equals("gonen.shoham")) {
			good.append("<br><br>  CM only - extra lines:");
			for(int k = 0 ; k<ans2.size();k++) {
				good.append("<br>").append(ans2.get(k));
			}
		}
		return HagUtil.shortHtml(good.toString(), "green","bg"); 
	}
	//	return HagForms.prcForm(filter,val);
	//return ans2.convertToString("<br>");
	}
	public String 		filterPrcs(HttpServletRequest request, HttpServletResponse response){
		String value = request.getParameter( "value" );
		int val = HagUtil.s2i(value);
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

		String[] filter = {col1.trim(),col2.trim(),col3.trim(),col4.trim(),col5.trim(),col6.trim(),col7.trim(),col8.trim(),col9.trim(),col10.trim()};

		//HagStringList ans = new	HagStringList(); 
		System.gc();
		String aaaaa= HagForms.prcForm(filter,val);
		System.gc();
		return aaaaa;
		
		/*
   	  	HagStringList ans1= new HagStringList();
      	String stm = "select *  from dbo.RI_PRC  order by dateTime DESC";
		String rc= HagJdbc.selectShortPrcs("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm,ans1,true,filter);
		if(rc.startsWith("0~")){
			String str = ans1.convertToString(" ");
			String ffT = HagUtil.cfgMenuWebLoc+"\\tableSort1T2.html";
			HagStringList listT = new HagStringList(ffT,false,"xxssss",false);
			String strT = listT.convertToString("\n");
			String ffB = HagUtil.cfgMenuWebLoc+"\\tableSort1B2.html";
			HagStringList listB = new HagStringList(ffB,false,"xxssss",false);
			String strB = listB.convertToString("\n");
			return strT+str+strB;
		}else{
			return rc;
		}
		 */

	}
	
	public String 		filterTasks(HttpServletRequest request, HttpServletResponse response){
		String value = request.getParameter( "value" );
		String col1 = request.getParameter( "col1" );
		String col2 = request.getParameter( "col2" );
		String col3 = request.getParameter( "col3" );
		String col4 = request.getParameter( "col4" );
	
		String[] filter = {col1.trim(),col2.trim(),col3.trim(),col4.trim().trim()};
		return HagForms.tasksForm(filter,value);
		

	}
	public String 		filterPrcsFull(HttpServletRequest request, HttpServletResponse response){

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

		String[] filter = {col1.trim(),col2.trim(),col3.trim(),col4.trim(),col5.trim(),col6.trim(),col7.trim(),col8.trim(),col9.trim(),col10.trim()};

		System.gc();
		String aaaaa= HagForms.prcForm(filter,-1);
		System.gc();
		return aaaaa;
	}
	//req9733
	public String 		filterPrcsNew(HttpServletRequest request, HttpServletResponse response){

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

		String[] filter = {col1.trim(),col2.trim(),col3.trim(),col4.trim(),col5.trim(),col6.trim(),col7.trim(),col8.trim(),col9.trim(),col10.trim()};

		
		return HagForms.prcForm(filter,-1);
	
	}
	public String 		filterTomcats(HttpServletRequest request, HttpServletResponse response){
		String col1 = request.getParameter( "col1" );
		String col2 = request.getParameter( "col2" );
		String col3 = request.getParameter( "col3" );

		String[] filter = {col1.trim(),col2.trim(),col3.trim()};
		return HagForms.tomcatsStatus(filter);

	}
	public String 		filterIways(HttpServletRequest request, HttpServletResponse response){
		String col1 = request.getParameter( "col1" );
		String col2 = request.getParameter( "col2" );
		String col3 = request.getParameter( "col3" );
		String col4 = request.getParameter( "col4" );
		String col5 = request.getParameter( "col5" );
		String col6 = request.getParameter( "col6" );

		String[] filter = {col1.trim(),col2.trim(),col3.trim(),col4.trim(),col5.trim(),col6.trim()};
		return HagForms.iWayLinks(filter);

	}
	//G
	//H
	//I
	public String 		iKnowWhatYouDid(HttpServletRequest request, HttpServletResponse response){
		return HagForms.prcForm(null,1000);
	}
	public String 		iKnowWhatYouDidFull(HttpServletRequest request, HttpServletResponse response){
		return HagForms.prcForm(null,-1);
	}
	//J
	//K
	//L
	//M
	//N
	//O
	//P
	//Q
	//R
	public String 		runDevActI5os(HttpServletRequest request, HttpServletResponse response,
									String prc,String user,String diaryAct,String diarySubact,String diaryNote){

		//String diaryRc 		= ".";
		String server1 		= null;
		String batchName1 	= null;
		String jiraVer 		= null;
		String tomcatPort 		= null;
		String rc1="Failed";
		String [] cbgroup4		= request.getParameterValues("cb4");
		if (cbgroup4 == null || cbgroup4.length == 0)
			return 	 HagUtil.shortHtml("must select at least one environment", "red","bg");
		StringBuilder ans = new StringBuilder();
		for(int i = 0 ; i< cbgroup4.length ; i++){
			String colorR 		= "#cccccc";
			String colorG 		= "#00FF00";
			String colorE 		= "#FF0000";
			server1 		= HagUtil.getWord0(cbgroup4[i],"!",0,true);
			batchName1 	= HagUtil.getWord0(cbgroup4[i],"!",1,true);
			String db2 = getDb2(server1,batchName1,"LIBTYPE");
			String env1	 		= HagUtil.getWord0(cbgroup4[i],"!",2,true);
			if (env1.equals("INT"))
				return 	 HagUtil.shortHtml("you can not work on INT environment", "red","bg");
			String toPackPath 	= HagUtil.getWord0(cbgroup4[i],"!",3,true);
			jiraVer	 			= HagUtil.getWord0(cbgroup4[i],"!",4,true);
			tomcatPort	 			= HagUtil.getWord0(cbgroup4[i],"!",5,true);
			String mailList 	= "david.ha@sapiens.com";
			String ccList 		= HagUtil.getRiMails(jiraVer);
			String server2 		= "<td bgColor=\""+colorR+"\">"+server1+"</td></tr>";
			String batchName2 	= "<td bgColor=\""+colorR+"\">"+batchName1+"</td></tr>";
			String env		 	= "<td bgColor=\""+colorR+"\">"+env1+"</td></tr>";
			String isCmLock		= "<td bgColor=\""+colorR+"\">.</td></tr>";
			String isDevLock	= "<td bgColor=\""+colorR+"\">.</td></tr>";
			String killed		= "<td bgColor=\""+colorR+"\">.</td></tr>";
			String stopT		= "<td bgColor=\""+colorR+"\">.</td></tr>";
			String action		= "<td bgColor=\""+colorR+"\">.</td></tr>";
			//String deleted		= "<td bgColor=\""+colorR+"\">.</td></tr>";
			//String created		= "<td bgColor=\""+colorR+"\">.</td></tr>";
			String startT		= "<td bgColor=\""+colorR+"\">.</td></tr>";
			String rc			= "<td bgColor=\""+colorR+"\">.</td></tr>";
			String mail			= "<td bgColor=\""+colorR+"\">.</td></tr>";
			boolean stopFlag 	= false;	
			//stopTomcat
			if(!stopFlag){
				stopT=	stopTomcatI5os(server1, batchName1,db2,tomcatPort)+"</tr>";
				if(stopT.startsWith("<td bgColor=\"#FF0000\">Failed"))			
					stopFlag=true;
				//}else
				//	stopT = "<td bgColor=\""+colorG+"\">"+stopT1+"</td></tr>"; 
			}
			//action
			if(!stopFlag){
				String[] answer = runDevActInnerI5os(prc,server1,batchName1,jiraVer,toPackPath,user);
				if(answer[0].equals("STOP")){
					action	= "<td bgColor=\""+colorE+"\">"+answer[1]+"</td></tr>"; 
					stopFlag=true;
				}else{
					action	= "<td bgColor=\""+colorG+"\">"+answer[1]+"</td></tr>"; 
				}
			}


			//startTomcat
			if(!stopFlag && prc.equals("devReplaceWarI5os")){
				startT=	startTomcatI5os(server1, batchName1,db2,tomcatPort)+"</tr>";
				if(startT.startsWith("<td bgColor=\"#FF0000\">Failed"))				
					//startT = "<td bgColor=\""+colorE+"\">"+startT1+"</td></tr>"; 
					stopFlag=true;
				//}else
				//	startT = "<td bgColor=\""+colorG+"\">"+startT1+"</td></tr>"; 

			}

			if(!stopFlag){
				rc = "<td bgColor=\""+colorG+"\">"+server1+"-"+batchName1+" "+prc+" done.</td></tr>";
				rc1="OK";
			}else{
				rc = "<td bgColor=\""+colorE+"\">Failed to run "+prc+" on "+server1+"-"+batchName1+"</td></tr>";
			}

			ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\" border =\"1\">");
			ans.append("<tr><td bgColor=\"#cccccc\">server   			</td>"+server2);
			ans.append("<tr><td bgColor=\"#cccccc\">batchName			</td>"+batchName2);
			ans.append("<tr><td bgColor=\"#cccccc\">environment type    </td>"+env);
			ans.append("<tr><td bgColor=\"#cccccc\">cm lock  			</td>"+isCmLock);
			ans.append("<tr><td bgColor=\"#cccccc\">dev lock  			</td>"+isDevLock);
			ans.append("<tr><td bgColor=\"#cccccc\">killed(processes)	</td>"+killed);
			ans.append("<tr><td bgColor=\"#cccccc\">stop tomcat			</td>"+stopT);
			ans.append("<tr><td bgColor=\"#cccccc\">action        		</td>"+action);
			ans.append("<tr><td bgColor=\"#cccccc\">start tomcat		</td>"+startT);
			ans.append("<tr><td bgColor=\"#cccccc\">RC					</td>"+rc);
			ccList="David.ha@sapiens.com";
			String ans9=HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+user,HagUtil.mailList_hag, prc +" ran on " + server1 + " " + batchName1
					+ " environment by " + user + " rc= "+rc1,ans.toString()+"</table>");

			mail = 	"<td bgColor=\""+colorG+"\">Mail sent</td></tr>"; 
			if(!ans9.startsWith("0~"))
				mail = "<td bgColor=\""+colorE+"\">Failed to send mail</td></tr>"; 
			

			ans.append("<tr><td bgColor=\"#cccccc\">Mail				</td>"+mail);
			ans.append("</table><br>");
		}

	//	HagUtil.writeToRelDiary2(diaryAct,"WIN",diarySubact,jiraVer,"00",rc1,ans.toString(),"",diaryNote,user,server1,batchName1);
		return ans.toString();
	}
	public String 		runDevAct(HttpServletRequest request, HttpServletResponse response,
			String prc,String user,String diaryAct,String diarySubact,String diaryNote){

		//String diaryRc 		= ".";
		String server1 		= null;
		String batchName1 	= null;
		String jiraVer 		= null;
		String rc1="Failed";
		String [] cbgroup4		= request.getParameterValues("cb4");
		if (cbgroup4 == null || cbgroup4.length == 0)
			return 	 HagUtil.shortHtml("must select at least one environment", "red","bg");
		StringBuilder ans = new StringBuilder();
		for(int i = 0 ; i< cbgroup4.length ; i++){
			String colorR 		= "#cccccc";
			String colorG 		= "#00FF00";
			String colorE 		= "#FF0000";
			server1 		= HagUtil.getWord0(cbgroup4[i],"!",0,true);
			batchName1 	= HagUtil.getWord0(cbgroup4[i],"!",1,true);
			String env1	 		= HagUtil.getWord0(cbgroup4[i],"!",2,true);
			if (env1.equals("INT"))
				return 	 HagUtil.shortHtml("you can not work on INT environment", "red","bg");
			String toPackPath 	= HagUtil.getWord0(cbgroup4[i],"!",3,true);
			jiraVer	 			= HagUtil.getWord0(cbgroup4[i],"!",4,true);
			String mailList 	= "david.ha@sapiens.com";
			String ccList 		= HagUtil.getRiMails(jiraVer);
			String server2 		= "<td bgColor=\""+colorR+"\">"+server1+"</td></tr>";
			String batchName2 	= "<td bgColor=\""+colorR+"\">"+batchName1+"</td></tr>";
			String env		 	= "<td bgColor=\""+colorR+"\">"+env1+"</td></tr>";
			String isCmLock		= "<td bgColor=\""+colorR+"\">.</td></tr>";
			String isDevLock	= "<td bgColor=\""+colorR+"\">.</td></tr>";
			String killed		= "<td bgColor=\""+colorR+"\">.</td></tr>";
			String stopT		= "<td bgColor=\""+colorR+"\">.</td></tr>";
			String action		= "<td bgColor=\""+colorR+"\">.</td></tr>";
			//String deleted		= "<td bgColor=\""+colorR+"\">.</td></tr>";
			//String created		= "<td bgColor=\""+colorR+"\">.</td></tr>";
			String startT		= "<td bgColor=\""+colorR+"\">.</td></tr>";
			String rc			= "<td bgColor=\""+colorR+"\">.</td></tr>";
			String mail			= "<td bgColor=\""+colorR+"\">.</td></tr>";
			boolean stopFlag 	= false;
			HagClient hagClient = new HagClient();
			//kill
			if(!stopFlag){
				String killed1 = HagUtil.killTomcatSubProcesses( server1, batchName1,hagClient);
				if(killed1.startsWith("1~")){				
					killed = "<td bgColor=\""+colorE+"\">"+killed1+"</td></tr>"; 
					stopFlag=true;
				}else
					killed = "<td bgColor=\""+colorG+"\">"+killed1+"</td></tr>"; 
			}	

			//stopTomcat
			if(!stopFlag){
				String stopT1=HagUtil.stopTomcatClean(server1,  batchName1,false);
				if(!stopT1.startsWith("0~")){				
					stopT = "<td bgColor=\""+colorE+"\">"+stopT1+"</td></tr>"; 
					stopFlag=true;
				}else
					stopT = "<td bgColor=\""+colorG+"\">"+stopT1+"</td></tr>"; 
			}
			//action
			if(!stopFlag){
				String[] answer = runDevActInner(prc,server1,batchName1,jiraVer,toPackPath);
				if(answer[0].equals("STOP")){
					action	= "<td bgColor=\""+colorE+"\">"+answer[1]+"</td></tr>"; 
					stopFlag=true;
				}else{
					action	= "<td bgColor=\""+colorG+"\">"+answer[1]+"</td></tr>"; 
				}
			}


			//startTomcat
			if(!stopFlag && !prc.equals("devReplaceWar")){
				HagStringList ans5 = hagClient.run(server1, "startTomcat " + batchName1 + "\n");
				String startT1 = ans5.convertToString("<br>"); 
				if(!startT1.startsWith("0~") ){				
					startT = "<td bgColor=\""+colorE+"\">"+startT1+"</td></tr>"; 
					stopFlag=true;
				}else
					startT = "<td bgColor=\""+colorG+"\">"+startT1+"</td></tr>"; 

			}

			if(!stopFlag){
				rc = "<td bgColor=\""+colorG+"\">"+server1+"-"+batchName1+" "+prc+" done.</td></tr>";
				rc1="OK";
			}else{
				rc = "<td bgColor=\""+colorE+"\">Failed to run "+prc+" on "+server1+"-"+batchName1+"</td></tr>";
			}

			ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\" border =\"1\">");
			ans.append("<tr><td bgColor=\"#cccccc\">server   			</td>"+server2);
			ans.append("<tr><td bgColor=\"#cccccc\">batchName			</td>"+batchName2);
			ans.append("<tr><td bgColor=\"#cccccc\">environment type    </td>"+env);
			ans.append("<tr><td bgColor=\"#cccccc\">cm lock  			</td>"+isCmLock);
			ans.append("<tr><td bgColor=\"#cccccc\">dev lock  			</td>"+isDevLock);
			ans.append("<tr><td bgColor=\"#cccccc\">killed(processes)	</td>"+killed);
			ans.append("<tr><td bgColor=\"#cccccc\">stop tomcat			</td>"+stopT);
			ans.append("<tr><td bgColor=\"#cccccc\">action        		</td>"+action);
			ans.append("<tr><td bgColor=\"#cccccc\">start tomcat		</td>"+startT);
			ans.append("<tr><td bgColor=\"#cccccc\">RC					</td>"+rc);

			String ans9=HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+user,HagUtil.mailList_hag,prc +" ran on " + server1 + " " + batchName1
					+ " environment by " + user + " rc= "+rc1,ans.toString()+"</table>");

			mail = 	"<td bgColor=\""+colorG+"\">Mail sent</td></tr>"; 
			if(!ans9.startsWith("0~")){
				mail = "<td bgColor=\""+colorE+"\">Failed to send mail</td></tr>"; 
			}

			ans.append("<tr><td bgColor=\"#cccccc\">Mail				</td>"+mail);
			ans.append("</table><br>");
		}

		HagUtil.writeToRelDiary2(diaryAct,"WIN",diarySubact,jiraVer,"00",rc1,ans.toString(),"",diaryNote,user,server1,batchName1);

		return ans.toString();
	}
	public String[] 	runDevActInnerI5os(String prc,String server1,String batchName1,String jiraVer,String toPackPath,String cfgMenuWebUser){
		StringBuilder ans1 = new StringBuilder();
		HagClient hagClient = new HagClient();
		String[] ans = {"INIT","INIT"};
		HagI5OS hagI5OS = new HagI5OS();
		String user ="RIGONEN1";
		String pass ="GON09C";
		if(prc.equals("devReplaceWarI5os")){			
			HagStringList ans2=replaceWarI5os(server1,batchName1, cfgMenuWebUser);
			int ansRc = HagUtil.getErrorLevel(ans2);
			ans[1]  = ans2.convertToString("<br>");
			if(ansRc==0)
				ans[0]	="GOOD";
			else
				ans[0]	="STOP";
		}else if(prc.equals("devReplaceJarI5os")){			
			HagStringList ans2=replaceWarI5os(server1,batchName1, cfgMenuWebUser);
			int ansRc = HagUtil.getErrorLevel(ans2);
			ans[1]  = ans2.convertToString("<br>");
			if(ansRc==0)
				ans[0]	="GOOD";
			else
				ans[0]	="STOP";
		}
		return ans;
	}
	public String[] 	runDevActInner(String prc,String server1,String batchName1,String jiraVer,String toPackPath){
		StringBuilder ans1 = new StringBuilder();
		HagClient hagClient = new HagClient();
		String[] ans = {"INIT","INIT"};
		if(prc.equals("setTomcatOpt")|| prc.equals("setTomcatDebug")){
			String tgtFile = "\\\\"+server1+"\\RI_JAVA\\RIjava_"+batchName1+"\\RILSService\\startup.bat";
			String newVal = "call startUp.bat";
			if(prc.equals("setTomcatDebug"))
				newVal = "call debug.bat";
			//readFile
			HagStringList list1 = new HagStringList();
			ans1.append(tgtFile+" found<br>"); 
			HagStringList list = new HagStringList(tgtFile, false, "aaaxxaaxaxa", true);
			if(list==null || list.size()==0){
				ans[1] = tgtFile+" not found"; 
				ans[0]="STOP";
				return ans;
			}
			boolean found=false;
			for (int p = 0; p < list.size(); p++) {
				String temp = list.get(p);
				if (temp.indexOf("call ") > -1 && temp.indexOf(".bat") > -1){
					temp = " " + newVal;
					found = true;
				}
				list1.add(temp);
			}
			if(!found){
				ans[1]  = tgtFile+" call line not found"; 
				ans[0]="STOP";
				return ans;
			}
			//deleted
			String command1 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\runcmd.bat del /Q " + tgtFile;
			HagStringList ans3 = hagClient.runNotSudo(server1, command1);
			String ans3s = ans3.convertToString("<br>");
			if (ans3s.startsWith("1~")) {
				ans[1]  = ans3s; 
				ans[0]="STOP";
				return ans;
			}
			ans1.append(ans3s+"<br>"); 
			//create
			list1.writeToFile(tgtFile, null, false);
			ans1.append(tgtFile+" changed<br>"); 
			//check2
			HagStringList list3 = new HagStringList(tgtFile, false, "aaaxxaaxaxa", true);
			if(list3==null || list3.size()==0){
				ans[1]  = tgtFile+" not created"; 
				ans[0]="STOP";
				return ans;
			}	
			ans[1]  = ans1.toString(); 
			ans[0]="GOOD";
			return ans;
		}else if(prc.equals("setLog4jError")|| prc.equals("setLog4jDebug")){
			String tgtFile = "\\\\"+server1+"\\RI_JAVA\\RIjava_"+batchName1+"\\tomcat\\shared\\log4j2.xml";
			String skelFile = HagUtil.cfgMenuWebLoc+"\\skel\\tomcat8\\log4j2.xml";

			if(jiraVer.startsWith("06")||jiraVer.startsWith("07.00")){
				tgtFile = "\\\\"+server1+"\\RI_JAVA\\RIjava_"+batchName1+"\\tomcat\\shared\\log4j.xml";
				skelFile = HagUtil.cfgMenuWebLoc+"\\skel\\tomcat7\\log4j.xml";
			}
			String newVal = "error";
			if(prc.equals("setLog4jDebug"))
				newVal = "debug";
			//del old
			String rc = HagUtil.simpleDosCmd("javaFunc~simpleDosCmd~del~\""+tgtFile+"\"",false);
			if(rc.startsWith("0~"))
				ans1.append("<font color=\"#000000\">delete "+tgtFile+"~"+rc+"</font><br>");
			else
				ans1.append("<font color=\"#FF0001\">delete "+tgtFile+"~"+rc+"</font><br>");
			//copy skel
			String copyRc = HagUtil.copyFileViaDos(skelFile, tgtFile);
			if(!copyRc.startsWith("0~")){
				ans[1] = "Failed to replace "+tgtFile;
				ans[0]="STOP";
				ans1.append("<font color=\"#FF0001\">replace "+tgtFile+"~"+rc+"</font><br>");
				return ans;
			}
			ans1.append("<font color=\"#000000\">replace "+tgtFile+"~"+rc+"</font><br>");
			//configure log4j
			String ansTemp1 = HagUtil.replaceStringInFile(tgtFile, "{RI-TOMCAT-LOG}", "d://RI_SHARE//"+batchName1+"//TomcatLogs");
			if(!ansTemp1.startsWith("0~")){
				ans[1] = "Failed to configure "+tgtFile;
				ans[0]="STOP";
				ans1.append("<font color=\"#FF0001\">configure1 "+tgtFile+"~"+rc+"</font><br>");
				return ans;
			}
			ans1.append("<font color=\"#000000\">configure1 "+tgtFile+"~"+rc+"</font><br>");
			//configure log4j
			String ansTemp2 = HagUtil.replaceStringInFile(tgtFile, "{errorOrDebug}", newVal);
			if(!ansTemp2.startsWith("0~")){
				ans[1] = "Failed to configure "+tgtFile;
				ans[0]="STOP";
				ans1.append("<font color=\"#FF0001\">configure2 "+tgtFile+"~"+rc+"</font><br>");
				return ans;
			}
			ans1.append("<font color=\"#000000\">configure2 "+tgtFile+"~"+rc+"</font><br>");
			/*
			//readFile
			HagStringList list1 = new HagStringList();
			//ans1.append(tgtFile+" found<br>"); 
			HagStringList list = new HagStringList(tgtFile, false, "aaaxxaaxaxa", true);
			if(list==null || list.size()==0){
				ans[1] = tgtFile+" not found"; 
				ans[0]="STOP";
				return ans;
			}
			//read skelFile
			HagStringList listSkel1 = new HagStringList();
			//ans1.append(skelFile+" found<br>"); 
			HagStringList listSkel = new HagStringList(skelFile, false, "aaaxxaaxaxa", true);
			if(listSkel==null || listSkel.size()==0){
				ans[1] = listSkel+" not found"; 
				ans[0]="STOP";
				return ans;
			}
			String prevLine = "";
			for (int p = 0; p < list.size(); p++) {
				String temp = list.get(p);
				if (temp.indexOf("<level value=") > -1 && prevLine.indexOf("<root>") < 0)
					temp = "  <level value=\"" + newVal + "\" />";
				list1.add(temp);
				if (temp.trim().length() > 1)
					prevLine = "" + temp.toLowerCase();
			}
			
			//deleted
			String command1 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\runcmd.bat del /Q " + tgtFile;
			HagStringList ans3 = hagClient.runNotSudo(server1, command1);
			String ans3s = ans3.convertToString("<br>");
			if (ans3s.startsWith("1~")) {
				ans[1]  = ans3s; 
				ans[0]="STOP";
				return ans;
			}
			ans1.append(ans3s+"<br>"); 
			//create
			list1.writeToFile(tgtFile, null, false);
			ans1.append(tgtFile+" changed<br>"); 
			//check2
			HagStringList list3 = new HagStringList(tgtFile, false, "aaaxxaaxaxa", true);
			if(list3==null || list3.size()==0){
				ans[1]  = tgtFile+" not created"; 
				ans[0]="STOP";
				return ans;
			}
			*/	
			ans[1]  = ans1.toString(); 
			ans[0]="GOOD";
			return ans;
		}else if(prc.equals("delRiLogs")){
			String tgtToDel = "\\\\"+server1+"\\RI_SHARE\\"+batchName1+"\\tomcatLogs\\*.*";
			String command1 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\runcmd.bat del /Q " + tgtToDel;
			HagStringList ans3 = hagClient.runNotSudo(server1, command1);
			String ans3s = ans3.convertToString("<br>");
			if (ans3s.startsWith("1~")) {
				ans[1]  = "Failed to delete tomcat logs "+ tgtToDel; 
				ans[0]="STOP";
				return ans;
			}else{
				ans[1]  = "Tomcat logs deleted "+ tgtToDel; 
				ans[0]="GOOD";
				return ans;
			}
		}else if(prc.equals("devReplaceJar")){
			//deleted
			String jarTgtToDel = "\\\\"+server1+"\\RI_JAVA\\RIjava_"+batchName1+"\\tomcat\\webapps\\ri-web\\WEB-INF\\lib\\batch-MSS-*.jar";
			String command1 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\runcmd.bat del /Q " + jarTgtToDel;
			HagStringList ans3 = hagClient.runNotSudo(server1, command1);
			String ans3s = ans3.convertToString("<br>");
			if (ans3s.startsWith("1~")||(ans3s.indexOf("cannot")>-1 ||ans3s.indexOf("runCmdFailed")>-1)) {
				ans[1]  = "Failed to delete old jar file "+ jarTgtToDel+"-"+ans3s; 
				ans[0]="STOP";
				return ans;
			}else{
				ans1.append(jarTgtToDel+" deleted<br>"); 
			}
			
			//copied
			String ans9="xxxxxx";
			String winFolder = toPackPath + "\\TOPACK\\temp-jar";
			String tempJarFile = HagUtil.getTempJarFile(winFolder, "batch-MSS-");
			if (!tempJarFile.startsWith("0~")){
				ans[1]  = "Failed to copy jar file from "+ tempJarFile; 
				ans[0]	="STOP";
				return ans;
			}else{
				tempJarFile=tempJarFile.substring(2,tempJarFile.length());
				String jarTgtToCopy = "\\\\"+server1+"\\RI_JAVA\\RIjava_"+batchName1+"\\tomcat\\webapps\\ri-web\\WEB-INF\\lib\\" + tempJarFile;
				String jarSrc = winFolder + "\\" + tempJarFile;
				String command2 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\runcmd.bat copy " + jarSrc + " "+jarTgtToCopy;
				HagStringList ans33 =hagClient.runNotSudo(server1, command2);
				String ans33s = ans33.convertToString("<br>");
				if (ans33s.startsWith("1~")||(ans33s.indexOf("cannot")>-1)||(ans33s.indexOf("runCmdFailed")>-1)|| !HagUtil.isFileExist(jarTgtToCopy)) {
					ans[1]  = "Failed to copy jar file from "+ tempJarFile; 
					ans[0]	="STOP";
					return ans;
				}else
					ans1.append(jarTgtToCopy+" copied<br>"); 
				ans[1]  = ans1.toString(); 
				ans[0]="GOOD";
				return ans;
			}
		}else if(prc.equals("devReplaceWar")){
			String target = "d:\\RI_JAVA\\RIjava_"+batchName1+"\\tomcat\\";
			String warLoc = target + "\\webapps\\";
		//	String warSrc = toPackPath+ "\\TOPACK\\ri-components\\RiInstaller\\ri-web.war";
			String warSrc = toPackPath + "\\TOPACK\\temp-war\\ri-web.war";
			String warTgt = target + "\\webapps\\ri-web.war";
			String warChk = target + "\\webapps\\ri-web\\index.jsp";
			String warTgt1 = "\\\\" + server1 + "\\" + warTgt.substring(3, warTgt.length());
			String warChk1 = "\\\\" + server1 + "\\" + warChk.substring(3, warChk.length());
			String riwebLoc1 = "\\\\" + server1 + "\\RI_JAVA\\RIjava_"+batchName1+"\\tomcat\\webapps\\ri-web";

			// deleted1
			String command1 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\runcmd.bat del /Q " + warLoc	+ "ri-web.war";
			HagStringList tmp1 = hagClient.runNotSudo(server1, command1);
			String tmp1s = tmp1.convertToString("<br>");
			if (tmp1s.startsWith("1~")) {
				ans[1]  = tmp1s; 
				ans[0]	="STOP";
				return ans;
			}else{
				ans1.append(tmp1s).append("<br>"); 
			}			
			//delete ri-web dir
			String command2 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\runcmd.bat rmdir " + warLoc+ "ri-web\\ /s /q";
			HagStringList tmp2 = hagClient.runNotSudo(server1, command2);
			String tmp2s = tmp2.convertToString("<br>");
			if (HagUtil.isFileExist(riwebLoc1)) {
				ans[1]  = "Failed to delete+"+riwebLoc1; 
				ans[0]	="STOP";
				return ans;
			}else{
				ans1.append(tmp2s).append("<br>"); 
			}
			// copy war file
			String ans9="aaaaaaaaaa";
			HagStringList ans6 =null;
			String command3 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\runcmd.bat copy " + warSrc + " "+ warTgt;
			HagStringList tmp3 = hagClient.runNotSudo(server1, command3);
			//HagStringList tmp3 = HagUtil.runCmd3(command3, true);
			String tmp3s = tmp3.convertToString("<br>");
			ans1.append(tmp3s).append("<br>"); 
			//start tomcat
			ans6 =hagClient.run(server1, "startTomcat " + batchName1 + "\n");
			ans1.append(ans6).append("<br>"); 
			HagUtil.sleep(2000);
			if (!HagUtil.isFileExist(warChk1)) {
				ans[1]  = "cfgMenuWeb-Failed to copy war";
				ans[0]	="STOP";
				return ans;
			}else{
				ans1.append("War replaced.<br>"); 
				//String command4 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\runcmd.bat del /Q " + warLoc	+ "ri-web.war";
				//HagStringList tmp4 = hagClient.runNotSudo(server1, command4);
				//String tmp4s = tmp4.convertToString("<br>");
				//if (tmp4s.startsWith("1~")) {
				//		ans1.append("Failed to delete war file in " + server1 + " " + batchName1+ " environment (after)").append("<br>");
				//	}else{
				//		ans1.append(warLoc).append("ri-web.war deleted.<br>"); 
				//	}
				ans[1]  = ans1.toString();
				ans[0]	="GOOD";
				return ans;
			}

		}
		return ans;
	}
	public String 		startIwayServer(HagStringList cbEnvs , HttpServletResponse response,String plat){
		int iwayServerPos = 13;
		if(plat.equalsIgnoreCase("I5OS")){
			iwayServerPos = 11;
		}
		String iwayS1 =  "I-WAY server";
		if(cbEnvs.size()==1){
			String temp = cbEnvs.get(0);
			String iwayServer = HagUtil.getWord0(temp,"~",iwayServerPos,true);
			if(iwayServer.trim().equalsIgnoreCase("NN")){
				iwayS1 =  "not defined please call gonen or hagay";
				HagUtil.shortHtml(iwayS1, "red","bg");
				return "1~";
			}
			try {
				response.sendRedirect("http://"+iwayServer+"/sapweb/Admin/AdmFrames.htm");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "0~";
		}
		StringBuilder ans =new StringBuilder();
		ans.append("<h3>Open multiple links of I-WAY servers</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\" bgColor=\"#ffff00\"><td>APP server</td><td>Batch name</td><td>"+iwayS1+"</td><td>Link to I-WAY server</td></tr>");

		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			String appServer = HagUtil.getWord0(temp,"~",1,true);
			String batchName = HagUtil.getWord0(temp,"~",2,true);
			String iwayServer = HagUtil.getWord0(temp,"~",iwayServerPos,true);
			if(iwayServer.trim().equalsIgnoreCase("NN")){
				String str = "<a href=\"http://"+iwayServer+"/sapweb/Admin/AdmFrames.htm\" target=\"_blank\">not defined please call gonen or hagay</a>";
				ans.append("<tr bgColor=\"#ffffff\"><td>"+appServer+"</td><td>"+batchName+"</td><td>"+iwayServer+"</td><td>"+str+"</td></tr>");
			}else{
				String str = "<a href=\"http://"+iwayServer+"/sapweb/Admin/AdmFrames.htm\" target=\"_blank\">I-WAY Server</a>";
				ans.append("<tr bgColor=\"#ffffff\"><td>"+appServer+"</td><td>"+batchName+"</td><td>"+iwayServer+"</td><td>"+str+"</td></tr>");
			}
		}
		ans.append("</table>");
		return ans.toString();
	}
	//S
	public String 		startListener1(HagStringList cbEnvs){
		StringBuilder ans =new StringBuilder();
		ans.append("<h3>colors table</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\"><td bgColor=\"#00ff00\">RC=OK</td><td bgColor=\"#FF66bb\">RC=not changed</td><td bgColor=\"#FF0000\">RC=Error</td></tr>");
		ans.append("</table>");

		ans.append("<h3>Start eMerge listener</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\" bgColor=\"#ffff00\"><td>APP server</td><td>Batch name</td><td>Status</td></tr>");

		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			String appServer = HagUtil.getWord0(temp,"~",1,true);
			String batchName = HagUtil.getWord0(temp,"~",2,true);
			String isCmLock1 = HagUtil.isCmLockStr(appServer,batchName,"WIN");
			String ans1= "<td bgColor=\"#FF0000\">Locked by cmInstaller, Please try again after cmInstaller-finish mail.</td>";
			if(!isCmLock1.equals("YES")){
				//dev lock1
				HagStringList devLocks = HagUtil.isDevLock(appServer,batchName);
				if(!devLocks.get(0).equals("0")){
					ans1 = "<td bgColor=\"#FF0000\">CM Error - Please call hagay.</td>";
				}else if(devLocks.get(0).equals("0") && !devLocks.get(1).equals(".")){
					ans1 = "<td bgColor=\"#FF0000\">Locked: "+devLocks.get(1)+"</td>";
				}else
					ans1= HagUtil.startEmergeListener1(appServer, batchName);
			}
	
			
			ans.append("<tr bgColor=\"#ffffff\"><td>"+appServer+"</td><td>"+batchName+"</td>"+ans1+"</tr>");
			//ans.append("<tr><td>"+appServer+"</td><td>"+batchName+"</td><td>"+ans1+"</td></tr>" );
		}
		ans.append("</table>");
		return ans.toString();
	}
	public String 		startTomcat1(HagStringList cbEnvs,String user){
		StringBuilder ans =new StringBuilder();
		ans.append("<h3>colors table</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\"><td bgColor=\"#00ff00\">RC=OK</td><td bgColor=\"#FF66bb\">RC=not changed</td><td bgColor=\"#FF0000\">RC=Error</td></tr>");
		ans.append("</table>");

		ans.append("<h3>Start tomcat</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\" bgColor=\"#ffff00\"><td>APP server</td><td>Batch name</td><td>Status</td></tr>");

		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			String appServer = HagUtil.getWord0(temp,"~",1,true);
			String batchName = HagUtil.getWord0(temp,"~",2,true);
			String isCmLock1 = HagUtil.isCmLockStr(appServer,batchName,"WIN");
			String ans1= "<td bgColor=\"#FF0000\">Locked by cmInstaller, Please try again after cmInstaller-finish mail.</td>";
			if(!isCmLock1.equals("YES")){
				//dev lock1
				HagStringList devLocks = HagUtil.isDevLock(appServer,batchName);
				if(!devLocks.get(0).equals("0")){
					ans1 = "<td bgColor=\"#FF0000\">CM Error - Please call hagay.</td>";
				}else if(devLocks.get(0).equals("0") && !devLocks.get(1).equals(".")){
					ans1 = "<td bgColor=\"#FF0000\">Locked: "+devLocks.get(1)+"</td>";
				}else {
					ans1= HagUtil.startTomcat(appServer, batchName,null);
					HagUtil.writeToRelDiary2("Start","WIN","Tomcat",".",".",".",".",".",".",user,appServer,batchName);
					
				}
			}
	
			
			ans.append("<tr bgColor=\"#ffffff\"><td>"+appServer+"</td><td>"+batchName+"</td>"+ans1+"</tr>");
			//ans.append("<tr><td>"+appServer+"</td><td>"+batchName+"</td><td>"+ans1+"</td></tr>" );
		}
		ans.append("</table>");
		return ans.toString();
	}
	public String 		startTomcatListener1(HagStringList cbEnvs,String user){
		StringBuilder ans =new StringBuilder();
		ans.append("<h3>colors table</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\"><td bgColor=\"#00ff00\">RC=OK</td><td bgColor=\"#FF66bb\">RC=not changed</td><td bgColor=\"#FF0000\">RC=Error</td></tr>");
		ans.append("</table>");

		ans.append("<h3>Start tomcat</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\" bgColor=\"#ffff00\"><td>APP server</td><td>Batch name</td><td>tomcat status</td><td>eMerge listener status</td></tr>");

		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			String appServer = HagUtil.getWord0(temp,"~",1,true);
			String batchName = HagUtil.getWord0(temp,"~",2,true);
			String isCmLock1 = HagUtil.isCmLockStr(appServer,batchName,"WIN");
			String ans1= "<td bgColor=\"#FF0000\">Locked by cmInstaller</td>";
			String ans2= "<td bgColor=\"#FF0000\">Please try again after cmInstaller-finish mail.</td>";
			if(!isCmLock1.equals("YES")){
				//dev lock1
				HagStringList devLocks = HagUtil.isDevLock(appServer,batchName);
				if(!devLocks.get(0).equals("0")){
					ans1 = "<td bgColor=\"#FF0000\">CM Error</td>";
					ans2 = "<td bgColor=\"#FF0000\">Please call hagay.</td>";
				}else if(devLocks.get(0).equals("0") && !devLocks.get(1).equals(".")){
					ans1 = "<td bgColor=\"#FF0000\">Locked:</td>" ;
					ans2 = "<td bgColor=\"#FF0000\">"+devLocks.get(1)+"</td>";
				}else{
					ans1= HagUtil.startTomcat(appServer, batchName,null);
					ans2= HagUtil.startEmergeListener1(appServer, batchName);
					HagUtil.writeToRelDiary2("Start","WIN","TomcatAndListener",".",".",".",".",".",".",user,appServer,batchName);
					
				}
			
			}
			ans.append("<tr bgColor=\"#ffffff\"><td>"+appServer+"</td><td>"+batchName+"</td>"+ans1+ans2+"</tr>");
			//ans.append("<tr><td>"+appServer+"</td><td>"+batchName+"</td><td>"+ans1+"</td></tr>" );
		}
		ans.append("</table>");
		return ans.toString();
	}
	public String 		stopListener1(HagStringList cbEnvs,String user){
			StringBuilder ans =new StringBuilder();
			ans.append("<h3>colors table</h3>");
			ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
			ans.append("<tr border=\"2\"><td bgColor=\"#00ff00\">RC=OK</td><td bgColor=\"#FF66bb\">RC=not changed</td><td bgColor=\"#FF0000\">RC=Error</td></tr>");
			ans.append("</table>");

			ans.append("<h3>Stop eMerge listener</h3>");
			ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
			ans.append("<tr border=\"2\" bgColor=\"#ffff00\"><td>APP server</td><td>Batch name</td><td>Status</td></tr>");

			for(int i = 0;i<cbEnvs.size();i++){
				String temp = cbEnvs.get(i);
				String appServer = HagUtil.getWord0(temp,"~",1,true);
				String batchName = HagUtil.getWord0(temp,"~",2,true);
				String isCmLock1 = HagUtil.isCmLockStr(appServer,batchName,"WIN");
				String ans1= "<td bgColor=\"#FF0000\">Locked by cmInstaller, Please try again after cmInstaller-finish mail.</td>";
				if(!isCmLock1.equals("YES")){
					//dev lock1
					HagStringList devLocks = HagUtil.isDevLock(appServer,batchName);
					if(!devLocks.get(0).equals("0")){
						ans1 = "<td bgColor=\"#FF0000\">CM Error - Please call hagay.</td>";
					}else if(devLocks.get(0).equals("0") && !devLocks.get(1).equals(".")){
						ans1 = "<td bgColor=\"#FF0000\">Locked: "+devLocks.get(1)+"</td>";
					}else {
						ans1= HagUtil.stopEmergeListener(appServer, batchName);
						String mailList 	= HagUtil.getRiMails("all");
						String ccList 	= HagUtil.getRiMails("david.hagay@sapiens.com");
						String ans9=HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+user,HagUtil.mailList_hag, user +" ran stop listenr on " + appServer + " " + batchName	,".");
					}
				}
				ans.append("<tr bgColor=\"#ffffff\"><td>"+appServer+"</td><td>"+batchName+"</td>"+ans1+"</tr>");
				//ans.append("<tr><td>"+appServer+"</td><td>"+batchName+"</td><td>"+ans1+"</td></tr>" );
			}
			ans.append("</table>");
			return ans.toString();
		}
	public String 		stopTomcat1(HagStringList cbEnvs,String user){
		StringBuilder ans =new StringBuilder();
		ans.append("<h3>colors table</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\"><td bgColor=\"#00ff00\">RC=OK</td><td bgColor=\"#FF66bb\">RC=not changed</td><td bgColor=\"#FF0000\">RC=Error</td></tr>");
		ans.append("</table>");

		ans.append("<h3>Stop tomcat</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\" bgColor=\"#ffff00\"><td>APP server</td><td>Batch name</td><td>Status</td></tr>");
		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			String appServer = HagUtil.getWord0(temp,"~",1,true);
			String batchName = HagUtil.getWord0(temp,"~",2,true);
			String isCmLock1 = HagUtil.isCmLockStr(appServer,batchName,"WIN");
			String ans1= "<td bgColor=\"#FF0000\">Locked by cmInstaller, Please try again after cmInstaller-finish mail.</td>";
			if(!isCmLock1.equals("YES")){
				//dev lock1
				HagStringList devLocks = HagUtil.isDevLock(appServer,batchName);
				if(!devLocks.get(0).equals("0")){
					ans1 = "<td bgColor=\"#FF0000\">CM Error - Please call hagay.</td>";
				}else if(devLocks.get(0).equals("0") && !devLocks.get(1).equals(".")){
					ans1 = "<td bgColor=\"#FF0000\">Locked: "+devLocks.get(1)+"</td>";
				}else {
					ans1= HagUtil.stopTomcat(appServer, batchName);
					String mailList 	= HagUtil.getRiMails("all");
					String ccList 	= HagUtil.getRiMails("david.hagay@sapiens.com");
					String ans9=HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+user,HagUtil.mailList_hag, user +" ran stop tomcat on " + appServer + " " + batchName	,".");
					HagUtil.writeToRelDiary2("Stop","WIN","Tomcat",".",".",".",".",".",".",user,appServer,batchName);
				}
			}
				
			ans.append("<tr bgColor=\"#ffffff\"><td>"+appServer+"</td><td>"+batchName+"</td>"+ans1+"</tr>");
			//	ans.append("<tr><td>"+appServer+"</td><td>"+batchName+"</td><td>"+ans1+"</td></tr>" );
		}
		ans.append("</table>");
		return ans.toString();
	}
	public String 		getDb2(String server,String batchName,String libType){
		String user ="RIGONEN1";
		String pass ="GON09C";

		HagSQL hagSQL = new HagSQL();
		String srv ="";
		if(server.equals("CLIO") )	srv="'DEV' or 'INT'";
		if(server.equals("SCENIC") )	srv="'QA'";
		if(server.equals("TERANO") )	srv="'SQA'";
		if(server.equals("PASSAT") )	srv="'CST'";
		if(server.equals("FALCON") )	srv="'SQB'";
						
		String stm = "select LIB from CFG.GSETLIBL where (ENV="+srv+") and DBID='"+batchName+"' and LIBTYPE='DB2'";
		HagStringList ans = hagSQL.select1col("I5OS", server, user, pass, "CFG", stm);
		return ans.get(0).trim();
		
	}
	
	public String 		getLSTN(String server,String batchName){
		String user ="RIGONEN1";
		String pass ="GON09C";

		HagSQL hagSQL = new HagSQL();
		String srv ="";
		if(server.equals("CLIO") )	srv="'DEV' or 'INT'";
		if(server.equals("SCENIC") )	srv="'QA'";
		if(server.equals("TERANO") )	srv="'SQA'";
		if(server.equals("PASSAT") )	srv="'CST'";
		if(server.equals("FALCON") )	srv="'SQB'";
						
		String stm = "select LSTN from CFG.GSETLIBL where (ENV="+srv+") and DBID='"+batchName+"'";
		HagStringList ans = hagSQL.select1col("I5OS", server, user, pass, "CFG", stm);
		if(ans.size()==0)
			return null;
		return ans.get(0).trim();
		
	}
	public String 		devOrInt(String batchName){
		String user ="RIGONEN1";
		String pass ="GON09C";

		HagSQL hagSQL = new HagSQL();
						
		String stm = "select ENV from CFG.GSETLIBL where  DBID='"+batchName+"'";
		HagStringList ans = hagSQL.select1col("I5OS", "CLIO", user, pass, "CFG", stm);
		if(ans.size()==0)
			return "DEV";
		else
			return "INT";
		
	}
	public String 		getSubVer(String server,String batchName){
		String user ="RIGONEN1";
		String pass ="GON09C";

		HagSQL hagSQL = new HagSQL();
		String srv ="";
		if(server.equals("CLIO") )	srv="'DEV' or 'INT'";
		if(server.equals("SCENIC") )	srv="'QA'";
		if(server.equals("TERANO") )	srv="'SQA'";
		if(server.equals("PASSAT") )	srv="'CST'";
		if(server.equals("FALCON") )	srv="'SQB'";
						
		String stm = "select SUBVER from CFG.GSETLIBL where (ENV="+srv+") and DBID='"+batchName+"'";
		HagStringList ans = hagSQL.select1col("I5OS", server, user, pass, "CFG", stm);
		return ans.get(0);
		
	}
	public String 		getMainVer(String server,String batchName){
		String user ="RIGONEN1";
		String pass ="GON09C";

		HagSQL hagSQL = new HagSQL();
		String srv ="";
		if(server.equals("CLIO") )	srv="'DEV' or 'INT'";
		if(server.equals("SCENIC") )	srv="'QA'";
		if(server.equals("TERANO") )	srv="'SQA'";
		if(server.equals("PASSAT") )	srv="'CST'";
		if(server.equals("FALCON") )	srv="'SQB'";
						
		String stm = "select MAINVER from CFG.GSETLIBL where (ENV="+srv+") and DBID='"+batchName+"'";
		HagStringList ans = hagSQL.select1col("I5OS", server, user, pass, "CFG", stm);
		return ans.get(0);
		
	}
	public String 		stopTomcatAndListenerI5os1(HagStringList cbEnvs){
		StringBuilder ans =new StringBuilder();
		ans.append("<h3>colors table</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\"><td bgColor=\"#00ff00\">RC=OK</td><td bgColor=\"#FF66bb\">RC=not changed</td><td bgColor=\"#FF0000\">RC=Error</td></tr>");
		ans.append("</table>");

		ans.append("<h3>Stop tomcat</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\" bgColor=\"#ffff00\"><td>APP server</td><td>Batch name</td><td>Status</td></tr>");
		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			String appServer = HagUtil.getWord0(temp,"~",1,true);
			String batchName = HagUtil.getWord0(temp,"~",2,true);
			if(isI5osEnvError(appServer,batchName)) {
				ans.append("<tr bgColor=\"#ff0000\"><td>"+appServer+"</td><td>"+batchName+"</td><td>Environment error-Call Hagay or gonen or exclude this environment from the selected list</td> </tr>");
				continue;
			}

			String tomcatPort = HagUtil.getWord0(temp,"~",10,true);
			String isCmLock1 = HagUtil.isCmLockStr(appServer,batchName,"I5OS");
			String db2 = getDb2(appServer,batchName,"LIBTYPE");
			String ans1= "<td bgColor=\"#FF0000\">Locked by cmInstaller, Please try again after cmInstaller-finish mail.</td>";
			if(!isCmLock1.equals("YES")){
				//dev lock1
				//HagStringList devLocks = HagUtil.isDevLock(appServer,batchName);
				//if(!devLocks.get(0).equals("0")){
				//	ans1 = "<td bgColor=\"#FF0000\">CM Error - Please call hagay.</td>";
				//}else if(devLocks.get(0).equals("0") && !devLocks.get(1).equals(".")){
				//	ans1 = "<td bgColor=\"#FF0000\">Locked: "+devLocks.get(1)+"</td>";
				//}else
					ans1= stopTomcatAndListenerI5os(appServer, batchName,db2,tomcatPort,true,true);
			}
				
			ans.append("<tr bgColor=\"#ffffff\"><td>"+appServer+"</td><td>"+batchName+"</td>"+ans1+"</tr>");
			//	ans.append("<tr><td>"+appServer+"</td><td>"+batchName+"</td><td>"+ans1+"</td></tr>" );
		}
		ans.append("</table>");
		return ans.toString();
	}
	public String 		startTomcatAndListenerI5os1(HagStringList cbEnvs){
		StringBuilder ans =new StringBuilder();
		ans.append("<h3>colors table</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\"><td bgColor=\"#00ff00\">RC=OK</td><td bgColor=\"#FF66bb\">RC=not changed</td><td bgColor=\"#FF0000\">RC=Error</td></tr>");
		ans.append("</table>");

		ans.append("<h3>Start tomcat and eMerge listener</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\" bgColor=\"#ffff00\"><td>APP server</td><td>Batch name</td><td>Status</td></tr>");
		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			String appServer = HagUtil.getWord0(temp,"~",1,true);
			String batchName = HagUtil.getWord0(temp,"~",2,true);
			if(isI5osEnvError(appServer,batchName)) {
				ans.append("<tr bgColor=\"#ff0000\"><td>"+appServer+"</td><td>"+batchName+"</td><td>Environment error-Call Hagay or gonen or exclude this environment from the selected list</td> </tr>");
				continue;
			}
			String tomcatPort = HagUtil.getWord0(temp,"~",10,true);
			String isCmLock1 = HagUtil.isCmLockStr(appServer,batchName,"I5OS");
			String db2 = getDb2(appServer,batchName,"LIBTYPE");
			String ans1= "<td bgColor=\"#FF0000\">Locked by cmInstaller, Please try again after cmInstaller-finish mail.</td>";
			if(!isCmLock1.equals("YES")){
				//dev lock1
				//HagStringList devLocks = HagUtil.isDevLock(appServer,batchName);
				//if(!devLocks.get(0).equals("0")){
				//	ans1 = "<td bgColor=\"#FF0000\">CM Error - Please call hagay.</td>";
				//}else if(devLocks.get(0).equals("0") && !devLocks.get(1).equals(".")){
				//	ans1 = "<td bgColor=\"#FF0000\">Locked: "+devLocks.get(1)+"</td>";
				//}else
					ans1= startTomcatAndListenerI5os(appServer, batchName,db2,tomcatPort,true,true);
			}
				
			ans.append("<tr bgColor=\"#ffffff\"><td>"+appServer+"</td><td>"+batchName+"</td>"+ans1+"</tr>");
			//	ans.append("<tr><td>"+appServer+"</td><td>"+batchName+"</td><td>"+ans1+"</td></tr>" );
		}
		ans.append("</table>");
		return ans.toString();
	}
	public String 		stopTomcatAndListenerI5os(String appServer,String dbid,String db2,String tomcatPort,boolean killListener,boolean killTomcat){
		String user ="RIGONEN1";
		String pass ="GON09C";
		HagI5OS hagI5os = new HagI5OS();
		String riList = getLSTN(appServer,dbid);
		ArrayList<String[]> toKill = hagI5os.killProcessLopp(appServer,user,pass,db2,riList,killListener,killTomcat);
	    while (toKill !=null && toKill.size()>0){
	    	hagI5os.killList(appServer,user,pass,toKill);
    	    toKill = hagI5os.killProcessLopp(appServer,user,pass,db2,riList,killListener,killTomcat);
	    }
	    String  ans = "";
	    if(killListener) 
	    	ans = ans + "Listener "+riList+" killed";
	    if(killTomcat){
	    	ans = ans + "  Tomcat killed";
	    }
		
		
	
		return "<td bgColor=\"#FFFF00\">"+ans+"</td>";
	//	blueLine = "Tomcat already down.";
//	killI5osPortUse(i5osConn.get(0), i5osConn.get(1), i5osConn.get(2), tocatPort);
}
	private String changeListenerFlag(String server, String listener, String act) {
		String cur = "";
		HagSQL hagSQL = new HagSQL();
		String stm = "SELECT VERSION FROM QGPL.LISTENERS WHERE LISTENER='" + listener + "' ";
		ArrayList<String> ans = hagSQL.select1col("I5OS", server, "RIGONEN1", "GON09C", "QGPL",  stm);
		if (ans != null && ans.size() > 0)
			cur = ans.get(0);
		else
			return "listener " + listener + " not found in list.";

		if (act.equals("?"))
			return cur;

		stm = "UPDATE QGPL.LISTENERS SET VERSION='" + act + "' WHERE LISTENER='" + listener + "' ";
		String ans1 = hagSQL.updateI5os(server, "RIGONEN1", "GON09C", "QGPL", stm);
		if (ans1.equals("updated"))
			return act;
		else {
			return "listener " + listener + " not found in list.";

		}
	}
	private String rii5osGetAddNo(String server, String user, String pass, String db2) {
		HagI5OS hagI5OS = new HagI5OS();
		String add = hagI5OS.i5osDtaara(server, user, pass, db2, "INSTINFO", "Update");
		return add;
	}

	private String rii5osGetMaintNo(String server, String user, String pass, String db2) {
		HagI5OS hagI5OS = new HagI5OS();
		String maint = hagI5OS.i5osDtaara(server, user, pass, db2, "INSTINFO", "Maint");
		return maint;
	}
	public String 		getI5osEnv(String server,String batchName){
		String srv ="";
		if(server.equals("CLIO") ) 		srv=devOrInt(batchName);
		if(server.equals("SCENIC") )	srv="QA";
		if(server.equals("TERANO") )	srv="SQA";
		if(server.equals("PASSAT") )	srv="CST";
		if(server.equals("FALCON") )	srv="SQB";
		return srv;
		
	}
	public String 		startTomcatAndListenerI5os(String appServer,String dbid,String db2,String tomcatPort,boolean killListener,boolean killTomcat){
		String user ="RIGONEN1";
		String pass ="GON09C";
		String riList = getLSTN(appServer,dbid);
		HagSQL hagSQL = new HagSQL();
		String stm = "SELECT VERSION FROM QGPL.LISTENERS WHERE LISTENER='" + riList + "' ";
		ArrayList<String> ans2 = hagSQL.select1col("I5OS", appServer, user, pass, "QGPL",  stm);

		if (ans2 == null || ans2.size() < 1)
			return "<td bgColor=\"#FF0000\">listener " + riList + " not found in list.</td>";

		if (!ans2.get(0).equals("9")) {
			if (changeListenerFlag(appServer, riList, "9") == null)
				return "<td bgColor=\"#FF0000\">listener " + riList + " not found in list.</td>";
			//else
				//JOptionPane.showMessageDialog(this, "listener flag changed.", "Listener Flag", JOptionPane.INFORMATION_MESSAGE);
		}
		//
		String addNo = rii5osGetAddNo(appServer, user, pass, db2);
		String maintNo = rii5osGetMaintNo(appServer, user, pass, db2);
		if (addNo == null)
			addNo = "  ";
		if (maintNo == null)
			maintNo = "  ";

		if (addNo.length() > 0) {
		}
		String aa = " PATCH(" + maintNo + ") ADDNO(" + addNo + ") ";
		//
		HagStringList cmd = new HagStringList();
	//	cmd.add("|" + server + "|" + user + "|" + pass + "|");
		//if(db2.equalsIgnoreCase("H1_DB2") && server.equalsIgnoreCase("PASSAT") ){
		//	cmd.add("RMVLIBLE LIB(H1_DB2)");
		//	cmd.add("ADDLIBLE QGPL");
		//	cmd.add("ADDLIBLE QTEMP");
		//	cmd.add("ADDLIBLE SYSIBM");
		//	cmd.add("ADDLIBLE H1_DB2");
		//	cmd.add("ADDLIBLE H1_LIB");
		//	cmd.add("ADDLIBLE SAP4530P00");
		//	cmd.add("ADDLIBLE H1_REG");
		//	cmd.add("ADDLIBLE BAS4530P00");
		//	cmd.add("ADDLIBLE H1_RIA");
		//	cmd.add("STRLISTEN LISTENER(IL1922) USER(K90RIPRC)");
		if(db2.equalsIgnoreCase("ENRI") && appServer.equalsIgnoreCase("PASSAT") ){

			cmd.add("|" + appServer + "|ZSAVI2|YOSSI|");
			cmd.add("STRSBS SBSD(RIH1SBS)");
			
			cmd.add("|" + appServer + "|K90RIPRC|K90RI1|");
			
			cmd.add("CALL QGPL/INL" + dbid);
			cmd.add("STRLISTEN LISTENER(IL1922) JOBD(H1JOBD) USER(ILGROUP)");
		}else if(db2.equalsIgnoreCase("D3_DB2") && appServer.equalsIgnoreCase("PASSAT") ){
			cmd.add("|" + appServer + "|ZSAVI2|YOSSI|");
			cmd.add("STRSBS SBSD(RID3SBS)");
			
			cmd.add("|" + appServer + "|K90RIPRC|K90RI1|");
			
			cmd.add("CALL QGPL/INL" + dbid);
			cmd.add("STRLISTEN LISTENER(IL1934) JOBD(D3JOBD) USER(ILGROUP)");
		}else{
			cmd.add("|" + appServer + "|" + user + "|" + pass + "|");
			//HagUtil.p("b");
			cmd.add("RMVLIBLE LIB(CFG)");
			cmd.add("ADDLIBLE CFG");
			String env = getI5osEnv(appServer,dbid);
			String mainVer = getMainVer(appServer,dbid);
			String subVer = getSubVer(appServer,dbid);
			cmd.add("CFG/GSETLIBL OPT(UP) ENV(" + env + ") MAINVER(" + mainVer + ") SUBVER(" + subVer + ") " + aa + " SINDBID(" + dbid + ") PMT(N)");
		}
			// ArrayList<String> ans1 = hagI5OS.runCmd2(cmd);
		HagI5OS hagI5OS = new HagI5OS();
		HagStringList ans3 = hagI5OS.runCmd2(cmd, null);
		// HagUtil.p(ans3);
		int count = 0;
		while (!hagI5OS.isTomcatActive(appServer, db2) && count < 5) {
			HagUtil.sleep(2500);
			count++;
		}

		String ans = "Listener " + riList + " and tomcat started.";
		if (count == 5)
			ans = "Listener " + riList + " started.    -->  (tomcat problem, please check)";

		return "<td bgColor=\"#00FF00\">"+ans+"</td>";
	//	blueLine = "Tomcat already down.";
//	killI5osPortUse(i5osConn.get(0), i5osConn.get(1), i5osConn.get(2), tocatPort);
}
	/*
	public String 		replaceWarI5os1(HagStringList cbEnvs,String cfgMenuWebUser){
		StringBuilder ans =new StringBuilder();
		ans.append("<h3>colors table</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\"><td bgColor=\"#00ff00\">RC=OK</td><td bgColor=\"#FF66bb\">RC=not changed</td><td bgColor=\"#FF0000\">RC=Error</td></tr>");
		ans.append("</table>");

		ans.append("<h3>Stop tomcat</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\" bgColor=\"#ffff00\"><td>APP server</td><td>Batch name</td><td>Status</td></tr>");
		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			String appServer = HagUtil.getWord0(temp,"~",1,true);
			String batchName = HagUtil.getWord0(temp,"~",2,true);
			if(isI5osEnvError(appServer,batchName)) {
				ans.append("<tr bgColor=\"#ff0000\"><td>"+appServer+"</td><td>"+batchName+"</td><td>Environment error-Call Hagay or gonen or exclude this environment from the selected list</td> </tr>");
				continue;
			}

			String tomcatPort = HagUtil.getWord0(temp,"~",10,true);
			String isCmLock1 = HagUtil.isCmLock(appServer,batchName);
			String db2 = getDb2(appServer,batchName,"LIBTYPE");
			String ans1= "<td bgColor=\"#FF0000\">Locked by cmInstaller, Please try again after cmInstaller-finish mail.</td>";
			if(!isCmLock1.equals("YES")){
				ans1= replaceWarI5os(appServer, batchName,cfgMenuWebUser);
			}
				
			ans.append("<tr bgColor=\"#ffffff\"><td>"+appServer+"</td><td>"+batchName+"</td>"+ans1+"</tr>");
		}
		ans.append("</table>");
		return ans.toString();
	}
	*/
	public String 		stopTomcatI5os1(HagStringList cbEnvs){
		StringBuilder ans =new StringBuilder();
		ans.append("<h3>colors table</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\"><td bgColor=\"#00ff00\">RC=OK</td><td bgColor=\"#FF66bb\">RC=not changed</td><td bgColor=\"#FF0000\">RC=Error</td></tr>");
		ans.append("</table>");

		ans.append("<h3>Stop tomcat</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\" bgColor=\"#ffff00\"><td>APP server</td><td>Batch name</td><td>Status</td></tr>");
		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			String appServer = HagUtil.getWord0(temp,"~",1,true);
			String batchName = HagUtil.getWord0(temp,"~",2,true);
			if(isI5osEnvError(appServer,batchName)) {
				ans.append("<tr bgColor=\"#ff0000\"><td>"+appServer+"</td><td>"+batchName+"</td><td>Environment error-Call Hagay or gonen or exclude this environment from the selected list</td> </tr>");
				continue;
			}

			String tomcatPort = HagUtil.getWord0(temp,"~",10,true);
			String isCmLock1 = HagUtil.isCmLockStr(appServer,batchName,"I5OS");
			String db2 = getDb2(appServer,batchName,"LIBTYPE");
			String ans1= "<td bgColor=\"#FF0000\">Locked by cmInstaller, Please try again after cmInstaller-finish mail.</td>";
			if(!isCmLock1.equals("YES")){
				//dev lock1
				//HagStringList devLocks = HagUtil.isDevLock(appServer,batchName);
				//if(!devLocks.get(0).equals("0")){
				//	ans1 = "<td bgColor=\"#FF0000\">CM Error - Please call hagay.</td>";
				//}else if(devLocks.get(0).equals("0") && !devLocks.get(1).equals(".")){
				//	ans1 = "<td bgColor=\"#FF0000\">Locked: "+devLocks.get(1)+"</td>";
				//}else
					ans1= stopTomcatI5os(appServer, batchName,db2,tomcatPort);
			}
				
			ans.append("<tr bgColor=\"#ffffff\"><td>"+appServer+"</td><td>"+batchName+"</td>"+ans1+"</tr>");
			//	ans.append("<tr><td>"+appServer+"</td><td>"+batchName+"</td><td>"+ans1+"</td></tr>" );
		}
		ans.append("</table>");
		return ans.toString();
	}
	public String 		startTomcatI5os1(HagStringList cbEnvs){
		StringBuilder ans =new StringBuilder();
		ans.append("<h3>colors table</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\"><td bgColor=\"#00ff00\">RC=OK</td><td bgColor=\"#FF66bb\">RC=not changed</td><td bgColor=\"#FF0000\">RC=Error</td></tr>");
		ans.append("</table>");

		ans.append("<h3>Stop tomcat</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\" bgColor=\"#ffff00\"><td>APP server</td><td>Batch name</td><td>Status</td></tr>");
		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			String appServer = HagUtil.getWord0(temp,"~",1,true);
			String batchName = HagUtil.getWord0(temp,"~",2,true);
			if(isI5osEnvError(appServer,batchName)) {
				ans.append("<tr bgColor=\"#ff0000\"><td>"+appServer+"</td><td>"+batchName+"</td><td>Environment error-Call Hagay or gonen or exclude this environment from the selected list</td> </tr>");
				continue;
			}

			String tomcatPort = HagUtil.getWord0(temp,"~",10,true);
			String isCmLock1 = HagUtil.isCmLockStr(appServer,batchName,"I5OS");
			String db2 = getDb2(appServer,batchName,"LIBTYPE");
			String ans1= "<td bgColor=\"#FF0000\">Locked by cmInstaller, Please try again after cmInstaller-finish mail.</td>";
			if(!isCmLock1.equals("YES")){
				//dev lock1
				//HagStringList devLocks = HagUtil.isDevLock(appServer,batchName);
				//if(!devLocks.get(0).equals("0")){
				//	ans1 = "<td bgColor=\"#FF0000\">CM Error - Please call hagay.</td>";
				//}else if(devLocks.get(0).equals("0") && !devLocks.get(1).equals(".")){
				//	ans1 = "<td bgColor=\"#FF0000\">Locked: "+devLocks.get(1)+"</td>";
				//}else
					ans1= startTomcatI5os(appServer, batchName,db2,tomcatPort);
			}
				
			ans.append("<tr bgColor=\"#ffffff\"><td>"+appServer+"</td><td>"+batchName+"</td>"+ans1+"</tr>");
			//	ans.append("<tr><td>"+appServer+"</td><td>"+batchName+"</td><td>"+ans1+"</td></tr>" );
		}
		ans.append("</table>");
		return ans.toString();
	}
	private void killI5osPortUse(String server, String user, String pass, String tocatPort) {
		HagI5OS hagI5OS = new HagI5OS();
		HagStringList cmd = new HagStringList();
		cmd.add("|" + server + "|" + user + "|" + pass + "|");
		cmd.add("RMVLIBLE LIB(CFG)");
		cmd.add("AL CFG");
		cmd.add("GPORTJOBS PORT(" + tocatPort + ") OPTION(E)");
		HagStringList ans = hagI5OS.runCmd2(cmd, null);
		// HagUtil.p(ans);
	}
	private String riI5osTomcatStop(String server, String user, String pass, String dbid, String db2) {
		HagStringList cmd = new HagStringList();
		cmd.add("|" + server + "|" + user + "|" + pass + "|");
		cmd.add("CALL INL" + dbid);
		// cmd.add("RMVLIBLE LIB(CFG)");
		// cmd.add("AL CFG");
		cmd.add("GTOMCAT OPT(DOWN) DB2LIB(" + db2 + ") PORTSCHECK(Y)");
		// ArrayList<String> ans1 = hagI5OS.runCmd2(cmd);
		HagI5OS hagI5OS = new HagI5OS();

		ArrayList<String> ans1 = hagI5OS.runCmd2(cmd, null);
		// HagUtil.p(ans1);
		int count = 0;
		while (hagI5OS.isTomcatActive(server, db2) && count < 5) {
			HagUtil.sleep(5000);
			count++;
		}

		String ans = "<td bgColor=\"#00FF00\">"+server+"-"+dbid+" tomcat stopped.</td>";
		if (count == 5) {
			ans = "<td bgColor=\"#FF0000\">Failed to stop "+server+"-"+dbid+" tomcat.</td>";
			// HagUtil.p1(ans1);
		}
		return ans;
	}
	public String 		stopTomcatI5os(String appServer,String dbid,String db2,String tomcatPort){
	
			String user ="RIGONEN1";
		String pass ="GON09C";
			
		HagI5OS hagI5OS = new HagI5OS();
		boolean tomcatUp = hagI5OS.isProcessActive(appServer, user,pass, db2, "QP0ZSPWT");
		if (tomcatUp) {
			killI5osPortUse(appServer, user, pass, tomcatPort);
			String blueLine = riI5osTomcatStop(appServer, user, pass, dbid, db2);
			return blueLine;
		} else
			return "<td bgColor=\"#FFFF00\">Tomcat already down.</td>";
		//	blueLine = "Tomcat already down.";
	//	killI5osPortUse(i5osConn.get(0), i5osConn.get(1), i5osConn.get(2), tocatPort);
	}
	private ArrayList<String> rii5osGetServer(String server,String batchName) {
	
		String user = "RIGONEN1";
		String pass = "GON09C";

	
		 if (server.equalsIgnoreCase("PASSAT")){
		
			if(batchName.equalsIgnoreCase("H1")){
				 user = "K90RIPRC";
				 pass = "K90RI1";
			}
			if(batchName.equalsIgnoreCase("D3")){
				 user = "K90RIPRC";
				 pass = "K90RI1";
			}
	
		}

		ArrayList<String> ans = new ArrayList<String>();
		ans.add(server);
		ans.add(user);
		ans.add(pass);

		return ans;
	}	/*
	public HagStringList 		replaceJarI5os(String appServer,String dbid,String cfgMenuWebUser){
	
		String user ="RIGONEN1";
		String pass ="GON09C";
	
		String db2 = getDb2(appServer,dbid,"LIBTYPE");
		HagI5OS hagI5OS = new HagI5OS();
		//String codeFile = HagUtil.cfgMenuWebLoc+"\\skel\\replaceWarI5os.code";
	
		ArrayList<String> i5osConn = rii5osGetServer(appServer,dbid);
		String file = "batch-DB2-V*.jar";
		String ifs = hagI5OS.i5osDtaara(i5osConn.get(0), i5osConn.get(1), i5osConn.get(2), db2, "INSTINFO", "IFS");
		String ifsTrim = ifs.trim();
		String ifsLast = ifsTrim.substring(ifsTrim.lastIndexOf("/") + 1, ifsTrim.length());
		String winSuff = "m00";
		String winSuffNote = " 00";
	
		String addNo = rii5osGetAddNo(i5osConn.get(0), i5osConn.get(1), i5osConn.get(2), db2);
		if (addNo.length() > 0) {
			winSuff = (ifsLast.substring(0, 3)).toLowerCase() + "U00";
			winSuffNote = (ifsLast.substring(1, 3)).toLowerCase() + " 00";
		}
		String mainVer = getMainVer(appServer,dbid);
		String subVer = getSubVer(appServer,dbid);
		String folder = "//HYUNDAI.SAPIENS.COM/HYUN05ri/DEV/V" + mainVer + subVer + winSuff + "/";
		String topackFolder = "V" + mainVer + subVer + winSuff;

		HagStringList codeLines = new HagStringList(codeFile, true, "*", true);
		//codeLines.replaceStr("{ver}", mainVer +""+ subVer);
		codeLines.replaceStr("{server}", i5osConn.get(0));
		codeLines.replaceStr("{dbid}", dbid);
		codeLines.replaceStr("{db2}", db2);
		codeLines.replaceStr("{I5OS-ZSAVI2-PASS}", "yossi");
		codeLines.replaceStr("{user}", i5osConn.get(1));
		codeLines.replaceStr("{pass}", i5osConn.get(2));
		codeLines.replaceStr("{IFS}", ifs);
		codeLines.replaceStr("{CURRENTUSER}", cfgMenuWebUser);
		codeLines.replaceStr("{topackfolder}", topackFolder);
		codeLines.replaceStr("{riMailList1}", "david.ha@sapiens.com;david.ha@sapiens.com");
		HagStringList ans = hagI5OS.runCmd2(codeLines, null);
		return ans;
	
}
	*/
	public HagStringList 		replaceWarI5os(String appServer,String dbid,String cfgMenuWebUser){
		String user ="RIGONEN1";
		String pass ="GON09C";
	
		String db2 = getDb2(appServer,dbid,"LIBTYPE");
		HagI5OS hagI5OS = new HagI5OS();
		String codeFile = HagUtil.cfgMenuWebLoc+"\\skel\\replaceWarI5os.code";
	
		ArrayList<String> i5osConn = rii5osGetServer(appServer,dbid);
		String ifs = hagI5OS.i5osDtaara(i5osConn.get(0), i5osConn.get(1), i5osConn.get(2), db2, "INSTINFO", "IFS");
		String ifsTrim = ifs.trim();
		String ifsLast = ifsTrim.substring(ifsTrim.lastIndexOf("/") + 1, ifsTrim.length());
		String winSuff = "m00";
		String winSuffNote = " 00";
	
		String addNo = rii5osGetAddNo(i5osConn.get(0), i5osConn.get(1), i5osConn.get(2), db2);
		if (addNo.length() > 0) {
			winSuff = (ifsLast.substring(0, 3)).toLowerCase() + "U00";
			winSuffNote = (ifsLast.substring(1, 3)).toLowerCase() + " 00";
		}
		String mainVer = getMainVer(appServer,dbid);
		String subVer = getSubVer(appServer,dbid);
		String folder = "//HYUNDAI.SAPIENS.COM/HYUN05ri/DEV/V" + mainVer + subVer + winSuff + "/";
		String topackFolder = "V" + mainVer + subVer + winSuff;

		HagStringList codeLines = new HagStringList(codeFile, true, "*", true);
		//codeLines.replaceStr("{ver}", mainVer +""+ subVer);
		codeLines.replaceStr("{server}", i5osConn.get(0));
		codeLines.replaceStr("{dbid}", dbid);
		codeLines.replaceStr("{db2}", db2);
		codeLines.replaceStr("{I5OS-ZSAVI2-PASS}", "yossi");
		codeLines.replaceStr("{user}", i5osConn.get(1));
		codeLines.replaceStr("{pass}", i5osConn.get(2));
		codeLines.replaceStr("{IFS}", ifs);
		codeLines.replaceStr("{CURRENTUSER}", cfgMenuWebUser);
		codeLines.replaceStr("{topackfolder}", topackFolder);
		codeLines.replaceStr("{riMailList1}", "david.ha@sapiens.com;david.ha@sapiens.com");
		HagStringList ans = hagI5OS.runCmd2(codeLines, null);
		//String ansS= ans.convertToString("<br>");
		return ans;
		//ArrayList<String> cmd = new ArrayList<String>();
		//cmd.add("|hyundai|riadmin|adm00p|");
		//cmd.add("stat -c %A " + folder);
		//String readWrite1 = hagGridPanel.runCmd_only(cmd);
	//	String readWrite = readWrite1.substring(0, 10);

	//	if (!readWrite.equals("drwxrwxrwx")) {
	//		JOptionPane.showMessageDialog(this, folder +" authorization is not 777.\nplease call gonen or hagay",
	//				"Closed version", JOptionPane.ERROR_MESSAGE);
	//		return;
	//	}

	//	JLabel[][] labels = { { new JLabel(" Server:"), new JLabel(i5osConn.get(0)) }, { new JLabel(" Version:"), new JLabel(ver1 + ver2) },
	//			{ new JLabel(" DBID:"), new JLabel(dbid) }, { new JLabel(" DB2:"), new JLabel(db2) },
	//			{ new JLabel(" User:"), new JLabel(i5osConn.get(1)) }, { new JLabel(" toPack folder:"), new JLabel(topackFolder) },
	//			{ new JLabel(" IFS directory:"), new JLabel(ifs) }, { new JLabel(" R/W:"), new JLabel(readWrite) } };
	//	JLabel[] labelsNote = { new JLabel("---------------") };
	//	hagGridPanel.hagWorkerChange("add", "replace war:  prompt user\n", false, false, Color.black, null, 27);
	//	if (confirmBox("Replace war", "Going to replace war:", "Do you want to continue?", labels, labelsNote) == 0)
	//		hagGridPanel.hagWorkerChange("add", "replace war:  replace\n", false, false, Color.black, null, 37);
	//	hagGridPanel.runCmd_code(codeLines, "replaceWarI5OS", 1, "replaceWar output");
	//	StringBuilder prm = new StringBuilder().append("'RI',").append("'").append(dbid).append("',").append("'Replace war',").append("'")
	//			.append(whoAmI).append("',").append("'").append(i5osConn.get(0)).append("',").append("'").append(dest).append("',")
	//			.append("CONVERT(char(16), CURRENT_TIMESTAMP, 120),").append("'cfgMenu',").append("'").append(ver1).append(ver2).append(" ")
	//			.append(winSuffNote).append("'");

//		HagUtil.writeToRelDiary2("Replace","I5OS","War",".","00",".",".",".",".",whoAmI,i5osConn.get(0),dbid);
		
//		HagSQL hagSQL = new HagSQL();
//		String cfgInsertAns = hagSQL.cfgInsert("i5os_prc", prm.toString());
	}
	private String riI5osTomcatStart(String server, String user, String pass, String dbid, String db2) {
		HagStringList cmd = new HagStringList();
		cmd.add("|" + server + "|" + user + "|" + pass + "|");
	//	if(db2.equalsIgnoreCase("H1_DB2") && server.equalsIgnoreCase("PASSAT") ){
	//		cmd.add("RMVLIBLE LIB(H1_DB2)");
	//		cmd.add("ADDLIBLE QGPL");
	//		cmd.add("ADDLIBLE QTEMP");
	//		cmd.add("ADDLIBLE SYSIBM");
	//		cmd.add("ADDLIBLE H1_DB2");
	//		cmd.add("ADDLIBLE H1_LIB");
	//		cmd.add("ADDLIBLE SAP4530P00");
	//		cmd.add("ADDLIBLE H1_REG");
	//		cmd.add("ADDLIBLE BAS4530P00");
	//		cmd.add("ADDLIBLE H1_RIA");
	if(db2.equalsIgnoreCase("ENRI") && server.equalsIgnoreCase("PASSAT") ){
		
		cmd.add("|" + server + "|ZSAVI2|YOSSI|");
		cmd.add("STRSBS SBSD(RIH1SBS)");
		cmd.add("GRTOBJAUT OBJ(QGPL/*ALL) OBJTYPE(*ALL) USER(*PUBLIC) AUT(*USE)");
		
		
		cmd.add("|" + server + "|K90RIPRC|K90RI1|");
		
		cmd.add("CALL QGPL/INL" + dbid);
		
		
			//cmd.add("|" + server + "|K90RIPRC|K90RI1|");
		//cmd.add("STRSBS SBSD(RIH1SBS)");
		//	cmd.add("CALL INL" + dbid);
	}else{
			cmd.add("CALL QGPL/INL" + dbid);
		}
	
		// cmd.add("RMVLIBLE LIB(CFG)");
		// cmd.add("AL CFG");

		cmd.add("GTOMCAT OPT(UP) DB2LIB(" + db2 + ") PORTSCHECK(Y)");
		// HagUtil.p(cmd);
		// ArrayList<String> ans1 = hagI5OS.runCmd2(cmd);
		HagI5OS hagI5OS = new HagI5OS();

		HagStringList ans1 = hagI5OS.runCmd2(cmd, null);

		int count = 0;
		while (!hagI5OS.isTomcatActive(server, db2) && count < 5) {
			HagUtil.sleep(2500);
			count++;
		}

		String ans = "<td bgColor=\\\"#00FF00\\\">"+server+"-"+dbid+" tomcat started.</td>";
		if (count == 5) {
			ans = "<td bgColor=\\\"#FF0000\\\">Failed to start "+server+"-"+dbid+" tomcat.</td>";
			// HagUtil.p1(ans1);
		}
		return ans;
	}
	public String 		startTomcatI5os(String appServer,String dbid,String db2,String tomcatPort){
		
		String user ="RIGONEN1";
	String pass ="GON09C";
		
	HagI5OS hagI5OS = new HagI5OS();
	boolean tomcatUp = hagI5OS.isProcessActive(appServer, user,pass, db2, "QP0ZSPWT");
	if (!tomcatUp) {
		killI5osPortUse(appServer, user, pass, tomcatPort);
		String blueLine = riI5osTomcatStart(appServer, user, pass, dbid, db2);
		return blueLine;
	} else
		return "<td bgColor=\"#FFFF00\">Tomcat already up.</td>";
	//	blueLine = "Tomcat already down.";
//	killI5osPortUse(i5osConn.get(0), i5osConn.get(1), i5osConn.get(2), tocatPort);
}
	public String 		stopTomcatListener1(HagStringList cbEnvs,String user){
		StringBuilder ans =new StringBuilder();
		ans.append("<h3>colors table</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\"><td bgColor=\"#00ff00\">RC=OK</td><td bgColor=\"#FF66bb\">RC=not changed</td><td bgColor=\"#FF0000\">RC=Error</td></tr>");
		ans.append("</table>");

		ans.append("<h3>Stop tomcat and eMerge listener</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\" bgColor=\"#ffff00\"><td>APP server</td><td>Batch name</td><td>tomcat status</td><td>eMerge listener status</td></tr>");

		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			String appServer = HagUtil.getWord0(temp,"~",1,true);
			String batchName = HagUtil.getWord0(temp,"~",2,true);
			String isCmLock1 = HagUtil.isCmLockStr(appServer,batchName,"WIN");
			String ans1= "<td bgColor=\"#FF0000\">Locked by cmInstaller</td>";
			String ans2= "<td bgColor=\"#FF0000\">Please try again after cmInstaller-finish mail.</td>";
			if(!isCmLock1.equals("YES")){
				//dev lock1
				HagStringList devLocks = HagUtil.isDevLock(appServer,batchName);
				if(!devLocks.get(0).equals("0")){
					ans1 = "<td bgColor=\"#FF0000\">CM Error</td>";
					ans2 = "<td bgColor=\"#FF0000\">Please call hagay.</td>";
				}else if(devLocks.get(0).equals("0") && !devLocks.get(1).equals(".")){
					ans1 = "<td bgColor=\"#FF0000\">Locked:</td>" ;
					ans2 = "<td bgColor=\"#FF0000\">"+devLocks.get(1)+"</td>";
				}else{
					ans1= HagUtil.stopTomcat(appServer, batchName);
					ans2= HagUtil.stopEmergeListener(appServer, batchName);
					String mailList 	= HagUtil.getRiMails("all");
					String ccList 	= HagUtil.getRiMails("david.hagay@sapiens.com");
					String ans9=HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+user,HagUtil.mailList_hag,user +" ran stop tomcat & listenr on " + appServer + " " + batchName	,".");
					HagUtil.writeToRelDiary2("Stop","WIN","TomcatAndListener",".",".",".",".",".",".",user,appServer,batchName);
					
				}
			}
	
			ans.append("<tr bgColor=\"#ffffff\"><td>"+appServer+"</td><td>"+batchName+"</td>"+ans1+ans2+"</tr>");
			//ans.append("<tr><td>"+appServer+"</td><td>"+batchName+"</td><td>"+ans1+"</td></tr>" );
		}
		ans.append("</table>");
		return ans.toString();
	}
	public String 		setDontMess(HagStringList cbEnvs,String cfgMenuWebUser,String cfgMenuWebPass,boolean flag){
		StringBuilder ans =new StringBuilder();
		String flag1 = "free";
		String flag2 = ".";
		if (flag){
			flag1 = "LOCKED";
			flag2 = HagUtil.getDateTime("dd/MM/yyyy-HH:mm")+" ("+cfgMenuWebUser+")";
		}
		ans.append("<h3>Don't mess with my env table: flag changed to <font color = \"#FF0000\">").append(flag1).append("</font></h3>");
		ans.append("<table bgColor=\"#dddddd\" cellpadding=\"3\" cellspacing=\"3\" border=\"1\">");
		ans.append("<tr bgColor=\"#aaaaaa\"><td>APP Server</td><td>batch name</td><td>sql RC</td></tr>");
		
//		ans.append("<h3><i>Please note that Replace-Jar process will restart tomcat and kill the environment processes.</i></h3>");
//		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"dist.jsp\">");
		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			String appServer = HagUtil.getWord0(temp,"~",1,true);
			String batchName = HagUtil.getWord0(temp,"~",2,true);
			String isCmLock1 = HagUtil.isCmLockStr(appServer,batchName,"WIN");
			String rc= "Locked by cmInstaller, Please try again after cmInstaller-finish mail.";
			if(!isCmLock1.equals("YES")){
				StringBuilder stm = new StringBuilder("UPDATE "+HagParam.getConfg1DB()+".[dbo].[ri_environments_new] ")
				.append("SET  [locks] = '").append(flag1).append("' ,").append("[locksDetails] = '").append(flag2).append("' ")
				.append("WHERE [bis_server]= '").append(appServer).append("' AND  [batchName]='").append(batchName).append("'");
				rc = HagJdbc.updateStm("CONFG1","cfg","cfg09c",HagParam.getConfg1DB(), stm.toString(),appServer,batchName);		
			}
			ans.append("<tr bgcolor = \"#cccccc\"><td>"+appServer+"</td><td>"+batchName+"</td><td>"+rc+"</td></tr>");
		}
		ans.append("</table><br>");
		ans.append("<table><tr><td bgcolor = \"#ffff00\">");
		ans.append("This process did the changes you asked, but did not refresh the table displayed on the right panel of the main window.<br>");
		ans.append("In order to view the changes, you need to close this window and regenerate the table, by reselecting the option on the left panel.<br>"); 
		ans.append("</td></tr></table>");
		
		String mailList = "david.ha@sapiens.com";
		//String ccList = "david.ha@sapiens.com";

		String ccList 	= HagUtil.getRiMails("all");
		String ans2		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+cfgMenuWebUser,HagUtil.mailList_hag,"don't mess with my env table changed by "+cfgMenuWebUser,ans.toString());
		return ans.toString();
		
	}
	public String 		startIwayEnv(HagStringList cbEnvs, HttpServletResponse response,String plat){
		int iwayServerPos = 13;
		int linkPos = 14;
		if(plat.equalsIgnoreCase("I5OS")){
			iwayServerPos = 11;
			linkPos = 12;
		}

		if(cbEnvs.size()==1){
			String temp = cbEnvs.get(0);
			String iwayServer = HagUtil.getWord0(temp,"~",iwayServerPos,true);
			if(iwayServer.trim().equalsIgnoreCase("NN")){
				String iwayS1 =  "not defined please call gonen or hagay";
				HagUtil.shortHtml(iwayS1, "red","bg");
				return "1~";
			}
			String link = HagUtil.getWord0(temp,"~",linkPos,true);
			try {
				response.sendRedirect("http://"+iwayServer+"/sapweb/Admin/Logon.htm?"+iwayServer+"&/sapweb&"+link);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "0~";
		}

		StringBuilder ans =new StringBuilder();
		ans.append("<h3>Open multiple links of I-WAY environments</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\" bgColor=\"#ffff00\"><td>APP server</td><td>Batch name</td><td>I-WAY server</td><td>Link to I-WAY server</td></tr>");

		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			String appServer = HagUtil.getWord0(temp,"~",1,true);
			String batchName = HagUtil.getWord0(temp,"~",2,true);
			String iwayServer = HagUtil.getWord0(temp,"~",iwayServerPos,true);
			String link = HagUtil.getWord0(temp,"~",linkPos,true);
			if(iwayServer.trim().equalsIgnoreCase("NN")){
				String str = "<a href=\"http://"+iwayServer+"/sapweb/Admin/Logon.htm?"+iwayServer+"&/sapweb&"+link+"\" target=\"_blank\">not defined please call gonen or hagay</a>";
				ans.append("<tr bgColor=\"#ffffff\"><td>"+appServer+"</td><td>"+batchName+"</td><td>"+iwayServer+"</td><td>"+str.trim()+"</td></tr>");
			}else{
				String str = "<a href=\"http://"+iwayServer+"/sapweb/Admin/Logon.htm?"+iwayServer+"&/sapweb&"+link+"\" target=\"_blank\">I-WAY Link to env</a>";
				ans.append("<tr bgColor=\"#ffffff\"><td>"+appServer+"</td><td>"+batchName+"</td><td>"+iwayServer+"</td><td>"+str.trim()+"</td></tr>");
			}
		}
		ans.append("</table>");
		return ans.toString();		
		//	http://ri11qa/sapweb/Admin/Logon.htm?ri11qa&/sapweb&0607-RI11QA-TF
	}
	public String 		startAzureIwayEnv(HagStringList cbEnvs, HttpServletResponse response,String plat){
		int iwayServerPos = 13;
		int linkPos = 14;
		if(plat.equalsIgnoreCase("I5OS")){
			iwayServerPos = 11;
			linkPos = 12;
		}

		if(cbEnvs.size()==1){
			String temp = cbEnvs.get(0);
			String iwayServer = HagUtil.getWord0(temp,"~",iwayServerPos,true);
			if(iwayServer.trim().equalsIgnoreCase("NN")){
				String iwayS1 =  "not defined please call gonen or hagay";
				HagUtil.shortHtml(iwayS1, "red","bg");
				return "1~";
			}
			String link = HagUtil.getWord0(temp,"~",linkPos,true);
			try {
				//response.sendRedirect("http://"+iwayServer+"/sapweb/Admin/Logon.htm?"+iwayServer+"&/sapweb&"+link);
				response.sendRedirect(link);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "0~";
		}

		StringBuilder ans =new StringBuilder();
		ans.append("<h3>Open multiple links of I-WAY environments</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\" bgColor=\"#ffff00\"><td>APP server</td><td>Batch name</td><td>I-WAY server</td><td>Link to I-WAY server</td></tr>");

		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			String appServer = HagUtil.getWord0(temp,"~",1,true);
			String batchName = HagUtil.getWord0(temp,"~",2,true);
			String iwayServer = HagUtil.getWord0(temp,"~",iwayServerPos,true);
			String link = HagUtil.getWord0(temp,"~",linkPos,true);
			if(iwayServer.trim().equalsIgnoreCase("NN")){
			//	String str = "<a href=\"http://"+iwayServer+"/sapweb/Admin/Logon.htm?"+iwayServer+"&/sapweb&"+link+"\" target=\"_blank\">not defined please call gonen or hagay</a>";
				String str = "<a href=\""+link+"\" target=\"_blank\">not defined please call gonen or hagay</a>";
				ans.append("<tr bgColor=\"#ffffff\"><td>"+appServer+"</td><td>"+batchName+"</td><td>"+iwayServer+"</td><td>"+str.trim()+"</td></tr>");
			}else{
				String str = "<a href=\""+link+"\" target=\"_blank\">I-WAY Link to env</a>";
				ans.append("<tr bgColor=\"#ffffff\"><td>"+appServer+"</td><td>"+batchName+"</td><td>"+iwayServer+"</td><td>"+str.trim()+"</td></tr>");
			}
		}
		ans.append("</table>");
		return ans.toString();		
		//	http://ri11qa/sapweb/Admin/Logon.htm?ri11qa&/sapweb&0607-RI11QA-TF
	}
	public String 		sendReqInstallLogic(HagStringList cbEnvs,String cfgMenuWebUser,String cfgMenuWebPass, HttpServletResponse response){
		//	String user="test";
			if(cbEnvs.size()>1)
				return HagUtil.shortHtml("only one env (checkBox) can be selected for this option", "red","bg");
		StringBuilder ans =new StringBuilder();
		StringBuilder ans1 =new StringBuilder();
		ans.append("<body bgColor=\"#cccccc\" >");
		ans.append("<h3>Request to install RI-Logic</h3>");
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"distRequests.jsp\">");
		ans1.append("<table bgColor=\"#aa22aa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans1.append("<tr border=\"2\" bgColor=\"#ffff00\"><td>cb</td><td>APP server</td><td>Batch name</td><td>Customer</td><td>last Good Inst</td><td>false Inst</td><td>env</td><td>SQL server</td><td>database name server</td></tr>");

		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			String appServer = HagUtil.getWord0(temp,"~",1,true);
			String batchName = HagUtil.getWord0(temp,"~",2,true);
			String customer = HagUtil.getWord0(temp,"~",3,true);
			String lastGoodInst = HagUtil.getWord0(temp,"~",4,true);
			String falseInst = HagUtil.getWord0(temp,"~",5,true);
			String env = HagUtil.getWord0(temp,"~",9,true);
			String sqlServer = HagUtil.getWord0(temp,"~",10,true);
			String dbName = HagUtil.getWord0(temp,"~",11,true);
			//			if(i==0){
			//  			oneRow.add("<tr><td "+perWidth+"><input type=\"checkbox\" name=\"cb\" id=\"cb\" value=\"{ID}\"></td>");
			StringBuilder val= new StringBuilder(appServer).append("~")
					.append(batchName).append("~")
					.append(customer).append("~")
					.append(lastGoodInst).append("~")
					.append(falseInst).append("~")
					.append(env).append("~")
					.append(sqlServer).append("~")
					.append(dbName);
			ans1.append("<tr bgColor=\"#ffffff\">")
			.append("<td><input type=\"checkbox\" name=\"cb\" id=\"cb\" checked   value=\"").append(val.toString()).append("\"></td>")
			.append("<td>").append(appServer	).append("</td>")
			.append("<td>").append(batchName	).append("</td>")
			.append("<td>").append(customer		).append("</td>")
			.append("<td>").append(lastGoodInst	).append("</td>")
			.append("<td>").append(falseInst	).append("</td>")
			.append("<td>").append(env			).append("</td>")
			.append("<td>").append(sqlServer	).append("</td>")
			.append("<td>").append(dbName		).append("</td></tr>");

		}

		ans1.append("</table>");


		///HagStringList ans3 = new HagStringList();
		///String stm3 = "select distinct lastInst from dbo.ri_environments_new where project <> 'HAGWIDTH' and status='A' order by lastInst";
		///HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm3, ans3, null);
		
		String stm3 = "select Package_Name from dbo.RI_Packages where Package_Platform = 'WIN' order by Package_Name";
		HagSQL hagSQL =new HagSQL();
		HagStringList ans3= hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm3);
		StringBuilder target =new StringBuilder("<select name=\"verInst\">");
		///HagJdbc.updateListRemoveDate(ans3,true);
		for (int i=0;i<ans3.size();i++){
			String temp = ans3.get(i);
			target.append("<option class=\"c30\" value=\"").append(temp).append("\">").append(temp).append("</option>");
		}
	///	String file = HagUtil.cfgMenuWebLoc+"\\lists\\riPackagesOnFtp.txt";
	///	HagStringList ans3a = new HagStringList(file,true,"*",false);
	///	for (int i=0;i<ans3a.size();i++){
	///		String temp = ans3a.get(i);
	///		target.append("<option class=\"c30\" value=\"").append(temp).append("\">").append(temp).append("</option>");
	///	}
		target.append("</select>");	


		ans1.append("<h3>Select version to install<h3>");
		ans1.append(target.toString());
		//ans1.append("<select class=\"c30\" id=\"verInst\" name =\"verInst\" >");

		//		.append("<option class=\"c30\" value=\"06.09.01.00.00(latest release)\">06.09.01.00.00(latest release)</option>")
		//		.append("<option class=\"c30\" value=\"06.08.01.00.00(latest release)\">06.08.01.00.00(latest release)</option>")
		//		.append("<option class=\"c30\" value=\"06.07.01.04.00(latest release)\">06.07.01.04.00(latest release)</option>")
		//		.append("<option class=\"c30\" value=\"06.07.01.00.00(from FTP)\">		06.07.01.00.00(from FTP)</option>")
		//		.append("<option class=\"c30\" value=\"06.07.01.03.00(from FTP)\">		06.07.01.03.00(from FTP)</option>")
		//		.append("</select>");
		ans1.append("<h3>Note:<h3>");
		ans1.append("<input type=\"text\" name=\"noteInst\" id=\"noteInst\" maxlength=\"140\" size=\"140\">");
		ans1.append("<input type=\"hidden\" name=\"act\" id=\"act\" value = \"reqInstallLogic\" maxlength=\"140\" size=\"140\">");
		ans1.append("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebUser\" value = \""+cfgMenuWebUser+"\" maxlength=\"140\" size=\"140\">");
		ans1.append("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+cfgMenuWebPass+"\" maxlength=\"140\" size=\"140\">");

		ans.append(ans1);
		//	ans.append("<input type=\"hidden\" name=\"mailContent\" value=").append(ans1).append("\">");
		ans.append("<br><br><INPUT TYPE=SUBMIT value=\"Send request\"></FORM></BODY>");
		//	response.setAttribute("mailContent", "select ABC from ABC");
		//

		return ans.toString();
	}
	public String 		sendReplaceDbFromBackup(HagStringList cbEnvs,String cfgMenuWebUser,String cfgMenuWebPass, HttpServletResponse response){
		//	String user="test";
		StringBuilder ans =new StringBuilder();
		StringBuilder ans1 =new StringBuilder();
		ans.append("<body bgColor=\"#cccccc\" >");
		ans.append("<h3>Request to replace DB from backup</h3>");
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"distRequests.jsp\">");
		ans1.append("<table bgColor=\"#aa22aa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans1.append("<tr border=\"2\" bgColor=\"#ffff00\"><td>cb</td><td>APP server</td><td>Batch name</td><td>Customer</td><td>last Good Inst</td><td>false Inst</td><td>env</td><td>SQL server</td><td>database name server</td></tr>");

		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			String appServer = HagUtil.getWord0(temp,"~",1,true);
			String batchName = HagUtil.getWord0(temp,"~",2,true);
			String customer = HagUtil.getWord0(temp,"~",3,true);
			String lastGoodInst = HagUtil.getWord0(temp,"~",4,true);
			String falseInst = HagUtil.getWord0(temp,"~",5,true);
			String env = HagUtil.getWord0(temp,"~",9,true);
			String sqlServer = HagUtil.getWord0(temp,"~",10,true);
			String dbName = HagUtil.getWord0(temp,"~",11,true);
			//			if(i==0){
			//  			oneRow.add("<tr><td "+perWidth+"><input type=\"checkbox\" name=\"cb\" id=\"cb\" value=\"{ID}\"></td>");
			StringBuilder val= new StringBuilder(appServer).append("~")
					.append(batchName).append("~")
					.append(customer).append("~")
					.append(lastGoodInst).append("~")
					.append(falseInst).append("~")
					.append(env).append("~")
					.append(sqlServer).append("~")
					.append(dbName);
			ans1.append("<tr bgColor=\"#ffffff\">")
			.append("<td><input type=\"checkbox\" name=\"cb\" id=\"cb\" checked   value=\"").append(val.toString()).append("\"></td>")
			.append("<td>").append(appServer	).append("</td>")
			.append("<td>").append(batchName	).append("</td>")
			.append("<td>").append(customer		).append("</td>")
			.append("<td>").append(lastGoodInst	).append("</td>")
			.append("<td>").append(falseInst	).append("</td>")
			.append("<td>").append(env			).append("</td>")
			.append("<td>").append(sqlServer	).append("</td>")
			.append("<td>").append(dbName		).append("</td></tr>");

		}

		ans1.append("</table>");

		//spr1002
		String stm3 = "select Package_Name from dbo.RI_Packages where Package_Platform = 'WIN' order by Package_Name";
		HagSQL hagSQL =new HagSQL();
		HagStringList ans3= hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm3);
		StringBuilder target =new StringBuilder("<select name=\"verInst\">");
		///HagJdbc.updateListRemoveDate(ans3,true);
		for (int i=0;i<ans3.size();i++){
			String temp = ans3.get(i);
			target.append("<option class=\"c30\" value=\"").append(temp).append("\">").append(temp).append("</option>");
		}
		target.append("</select>");	
		/*
		HagStringList ans3 = new HagStringList();
		String stm3 = "select distinct lastInst from dbo.ri_environments_new where project <> 'HAGWIDTH' and status='A' order by lastInst";
		HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm3, ans3, null);
		StringBuilder target =new StringBuilder("<select name=\"verInst\">");
		target.append("<option class=\"c30\" value=\"").append("Keep same level as in backup file").append("\">").append("Keep same level as in backup file").append("</option>");
		HagJdbc.updateListRemoveDate(ans3,true);
		for (int i=0;i<ans3.size();i++){
			String temp = ans3.get(i);
			target.append("<option class=\"c30\" value=\"").append(temp).append("\">").append(temp).append("</option>");
		}
		*/
		//spr1002
		//String file = HagUtil.cfgMenuWebLoc+"\\lists\\riPackagesOnFtp.txt";
		//HagStringList ans3a = new HagStringList(file,true,"*",false);
		//for (int i=0;i<ans3a.size();i++){
		//	String temp = ans3a.get(i);
		//	target.append("<option class=\"c30\" value=\"").append(temp).append("\">").append(temp).append("</option>");
		//}
	//	target.append("</select>");	

		ans1.append("<br><br><table bgColor=\"#99ff99\" border =\"1\" cellpadding=\"3\" cellspacing=\"3\">");
		ans1.append("<tr><td>Version to install</td>");
		ans1.append("<td>").append(target.toString()).append("</td></tr>");
		//ans1.append("<select class=\"c30\" id=\"verInst\" name =\"verInst\" >");

		//		.append("<option class=\"c30\" value=\"06.09.01.00.00(latest release)\">06.09.01.00.00(latest release)</option>")
		//		.append("<option class=\"c30\" value=\"06.08.01.00.00(latest release)\">06.08.01.00.00(latest release)</option>")
		//		.append("<option class=\"c30\" value=\"06.07.01.04.00(latest release)\">06.07.01.04.00(latest release)</option>")
		//		.append("<option class=\"c30\" value=\"06.07.01.00.00(from FTP)\">		06.07.01.00.00(from FTP)</option>")
		//		.append("<option class=\"c30\" value=\"06.07.01.03.00(from FTP)\">		06.07.01.03.00(from FTP)</option>")
		//		.append("</select>");
		ans1.append("<tr><td>Backup location</td>");
		ans1.append("<td>").append("<input type=\"text\" name=\"backupLocation\" id=\"backupLocation\" maxlength=\"140\" size=\"140\">").append("</td></tr>");
		
		
		
		ans1.append("<tr><td>SQL version of the backup file</td>");
		ans1.append("<td><select name=\"sqlVersion\">");
		ans1.append("<option class=\"c30\" value=\"").append("SQL2016").append("\">").append("SQL2016").append("</option>");
		ans1.append("<option class=\"c30\" value=\"").append("SQL2012").append("\">").append("SQL2012").append("</option>");
		ans1.append("<option class=\"c30\" value=\"").append("SQL2008").append("\">").append("SQL2008").append("</option>");
		ans1.append("<option class=\"c30\" value=\"").append("i don't know").append("\">").append("i don't know").append("</option>");
		ans1.append("</select></td></tr>");	
		
		ans1.append("<tr><td>RI version of the backup file</td>");
		ans1.append("<td><input type=\"text\" name=\"riVersion\" id=\"riVersion\" maxlength=\"140\" size=\"140\"></td></tr>");
	
		
		ans1.append("<tr><td>Note</td>");
		ans1.append("<td><input type=\"text\" name=\"noteInst\" id=\"noteInst\" maxlength=\"140\" size=\"140\"></td></tr>");
		ans1.append("</table>");
		ans1.append("<input type=\"hidden\" name=\"act\" id=\"act\" value = \"reqReplaceDbFromBackup\" maxlength=\"140\" size=\"140\">");
		ans1.append("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebUser\" value = \""+cfgMenuWebUser+"\" maxlength=\"140\" size=\"140\">");
		ans1.append("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+cfgMenuWebPass+"\" maxlength=\"140\" size=\"140\">");

		ans.append(ans1);
		//	ans.append("<input type=\"hidden\" name=\"mailContent\" value=").append(ans1).append("\">");
		ans.append("<br><br><INPUT TYPE=SUBMIT value=\"Send request\"></FORM></BODY>");
		//	response.setAttribute("mailContent", "select ABC from ABC");
		//

		return ans.toString();
	}
	public boolean 		isMyPrivateEnv(String cfgMenuWebUser,String envBatchName,String server){
		if(1==1)
			return false;
		return false;
		/*
		if(!server.equals("RI33QA") && !server.equals("RI43QA"))
			return false;

		HagStringList list = new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\users.txt",true,"*",false);
		for(int i = 0 ; i <list.size();i++) {
			String temp = list.get(i);
			if(temp.startsWith(envBatchName+"~"+cfgMenuWebUser))
					return true;
		}
		return false;
		*/
	}
	
	
	public HagStringList 		getRiUsers(){
		
		HagSQL hagSQL = new HagSQL();
		String stm = "select name1  from dbo.hagCfgMenuWebUsers";
		HagStringList ans = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c",HagParam.getConfg1DB(),  stm);
	return ans;
	/*	
		HagStringList list1= new HagStringList();
		HagStringList list = new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\users.txt",true,"*",false);
		for(int i = 0 ; i < list.size();i++) {
			String temp = list.get(i);
			if(temp.startsWith("RU~")) {
				String w1 = HagUtil.getWord0(temp, "~", 1,true);
				list1.add(w1);
			}
		}
		return list1;
		
		*/
	}
		
	public String 		changeEnvOwnerAfter(HttpServletRequest request, HttpServletResponse response){
			StringBuilder ans =new StringBuilder();
			String user		= request.getParameter( "user" );
			String sourceAPP		= request.getParameter( "sourceAPP" );
			String ext		= request.getParameter( "ext" );
			String sourceBN	= request.getParameter( "sourceBN" );
			String sourceOwner	= request.getParameter( "sourceOwner" );
			String newOwner		= request.getParameter( "newOwner" );
		ans.append("User=").append(user).append("<br>");
		ans.append("sourceAPP=").append(sourceAPP).append("<br>");
		ans.append("sourceBN=").append(sourceBN).append("<br>");
		ans.append("sourceOwner=").append(sourceOwner).append("<br>");
		ans.append("newOwner=").append(newOwner).append("<br>");
		
		String tableName="dbo.ri_environments_new";
		if(ext.equals("AZURE")){
			 tableName="dbo.ri_environments_EXT";
		}
		
		String stm1 = "update "+tableName+"  set owner ='"+newOwner+"',authUsersList = '.' where status='A' and bis_server ='"+sourceAPP+"' and batchName = '"+sourceBN+"'";
		HagSQL hagSQL = new HagSQL();
		int changed = hagSQL.updateStm("confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm1);
		if(changed!=1) 
			return HagUtil.shortHtml("failed to change Env owner - please call hagay (2527)", "red","bg");
		String content =sourceAPP+"/"+sourceBN+" environment owner changed from "+sourceOwner+" to "+newOwner+" by "+user;
		String ccMailList 	= HagUtil.getRiMails("all");

		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+sourceOwner+"@sapiens.com;"+newOwner+"@sapiens.com",HagUtil.mailList_hag,content,content);
		HagUtil.writeToRelDiary2("Environment","WIN","ChangeOwner",".",".","OK",".",".",".",user,sourceAPP,sourceBN);
	
		
		return content;
	}
	public String 		changeEnvOwnerPre(HagStringList cbEnvs, String cfgMenuWebUser, HttpServletResponse response,String plat,String ext){
			if(cbEnvs.size()>1)
				return HagUtil.shortHtml("only one env (checkBox) can be selected for this option", "red","bg");
		
			
			String line = cbEnvs.get(0);
			
			String sourceAPP=HagUtil.getWord0(line,"~",1,true);
			String sourceBN=HagUtil.getWord0(line,"~",2,true);
			String sourceOwner=HagUtil.getWord0(line,"~",12,true);
		
			if(!sourceOwner.equals(cfgMenuWebUser))
				return HagUtil.shortHtml("only the ENV owner ("+sourceOwner+") can change the owner", "red","bg");
			
			StringBuilder sb = new StringBuilder();
			sb.append("<BR>Please select the new owner for "+sourceAPP+"/"+sourceBN+ " environment.<br>");
			sb.append("and click the <i>replace Owner</i> button.<br>"); 
			sb.append("<br>");

			sb.append("<FORM METHOD=POST name=\"Form\" ACTION=\"changeEnvOwnerAfter.jsp\">");
			sb.append("<table  bgcolor = \"#E6E6FA\" border = 1 CELLPADDING=3 >");
			sb.append("<tr ><td>New owner</td><td> <select class=\"c30\" id=\"newOwner\" name =\"newOwner\"  bgColor=\"#66ff66\">");
			HagStringList riUsers = getRiUsers();
			for (int i=0;i<riUsers.size();i++){
				String temp = riUsers.get(i);
				sb.append( "<option class=\"c30\" value=\""+temp+"\">"+temp+"</option>");
			}
			sb.append("</select><td></tr>");
			sb.append("<tr><td>Authorized list</td><td>.</td></tr>");
			sb.append("</table><br>");
			
			sb.append("<input type=\"hidden\" name=\"ext\" id=\"ext\" value = \""+ext+"\" maxlength=\"140\" size=\"140\">");
			sb.append("<input type=\"hidden\" name=\"user\" id=\"user\" value = \""+cfgMenuWebUser+"\" maxlength=\"140\" size=\"140\">");
			sb.append("<input type=\"hidden\" name=\"sourceAPP\" id=\"sourceAPP\" value = \""+sourceAPP+"\" maxlength=\"140\" size=\"140\">");
			sb.append("<input type=\"hidden\" name=\"sourceBN\" id=\"sourceBN\" value = \""+sourceBN+"\" maxlength=\"140\" size=\"140\">");
			sb.append("<input type=\"hidden\" name=\"sourceOwner\" id=\"sourceOwner\" value = \""+sourceOwner+"\" maxlength=\"140\" size=\"140\">");
			sb.append("<br><INPUT TYPE=SUBMIT value=\"replace Owner\" ></FORM>");
		
			return sb.toString();	
		}
	
	public String 		backupDbWithNotes(HagStringList cbEnvs ,String cfgMenuWebUser, HttpServletResponse response,String plat){
		if(cbEnvs.size()>1)
			return HagUtil.shortHtml("only one env (checkBox) can be selected for this option", "red","bg");
		
		String line = cbEnvs.get(0);
		String sourceAPP=HagUtil.getWord0(line,"~",1,true);
		String sourceDB=HagUtil.getWord0(line,"~",11,true);
		String sourceBN = sourceDB;
		if(sourceDB.equals("RIAPPLDB"))
			sourceBN = "RI";
		String w3=HagUtil.getWord0(line,"~",3,true);
		String sourceSQL=HagUtil.getWord0(line,"~",10,true);
		String sourceEnv=HagUtil.getWord0(line,"~",9,true);
		String customerLong = HagUtil.getCustomerByPartyLong(w3);
		String customerShort = HagUtil.getCustomerByPartyShort(w3);
	
		
		HagStringList ans1 = new HagStringList();
		String rc1= HagJdbc.getDbSize("SQL",sourceSQL,"cfg","cfg09c",sourceDB, ans1);
		String rc1a = HagUtil.replaceStr(rc1,"<tbody class=\"scrollingContent\"><tr><td>"+sourceDB+"</td><td>","");
		rc1a = HagUtil.replaceStr(rc1a,"</td></tr></tbody>","");
		rc1a = HagUtil.replaceStr(rc1a,"</td><td>","#");
		String sourceDatSize=HagUtil.getWord0(rc1a,"#",0,true);
		String sourceLogSize=HagUtil.getWord0(rc1a,"#",1,true);

		
		String propFile = HagUtil.cfgMenuWebLoc+"\\lists\\cfgMenuWeb.properties";
		String sizeLimit = HagUtil.getPropertyVal(propFile,"privateDbSize");
		int sizeLimitI = HagUtil.s2i(sizeLimit);
		String sourceDBsize = HagUtil.getWord0(sourceDatSize, ".",0,true);
		int sourceDBsizeI = HagUtil.s2i(sourceDBsize);
		
		if(sourceDBsizeI >sizeLimitI)
			return HagUtil.shortHtml("source-DB size is too big - please call hagay 2527 <BR>source-DB size = "+sourceDatSize+"<BR>DB size limit = "+sizeLimit, "red","bg");
		
		String note1 =      "<select class=\"c30\" id=\"note1\"      name =\"note1\"      bgColor=\"#66ff66\"><option class=\"c30\" value=\"Other\">Other</option><option class=\"c30\" value=\"Initial\">Initial</option><option class=\"c30\" value=\"Vanilla\">Vanilla</option><option class=\"c30\" value=\"Setup\">Setup</option></select>";
		String note2 = "<input type=\"text\" name=\"note2\" id=\"note2\" maxlength=\"140\" size=\"140\">";
	
		
				
		HagStringList ans2 = new HagStringList();
	
		
		//long tomcatFolderSize = HagUtil.getFolderSize(sourceTomcatFolder);
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
	//	ans.add("var ua = window.navigator.userAgent;");
	//	ans.add("document.getElementById(\"myBrowser\").value = ua;");
		ans.add("}");
		ans.add("</script></head>	");
		ans.add("<body onload=\"refreshIFrame();getBrowser()\">");
		ans.add("<h3>Backup database with notes (Initial,Vanilla or other ans extra free text note)</h3>");
		ans.add("<FORM METHOD=POST name=\"Form\" ACTION=\"backupDbWithNotes.jsp\">");
		ans.add("<table bgColor=\"#cccccc\" border=\"2\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.add("<tr><td></td><td>Environment</td></tr>");
		ans.add("<tr><td >APP server name</td><td bgColor=\"#66ff66\">"+sourceAPP+"</td></tr>");
		ans.add("<tr><td >SQL server name</td><td bgColor=\"#66ff66\">"+sourceSQL+"</td></tr>");
		ans.add("<tr><td >Database Dat size3</td><td bgColor=\"#66ff66\">"+sourceDatSize+" mb</td></tr>");
		ans.add("<tr><td >Database Log size</td><td bgColor=\"#66ff66\">"+sourceLogSize+" mb</td></tr>");
		ans.add("<tr><td >Customer name</td><td bgColor=\"#66ff66\">"+customerLong+"</td></tr>");
		ans.add("<tr><td >DB main type</td><td bgColor=\"#66ff66\">"+note1+" select Other,Initial or Vanilla</td></tr>");
		ans.add("<tr><td >Free text note</td><td bgColor=\"#66ff66\">"+note2+"</td></tr>");

		
		//ans.add("<tr><td >Source tomcat folder size</td><td bgColor=\"#66ff66\">"+tomcatFolderSize+"</td><td bgColor=\"#00ffff\">"+"."+"</td></tr>");
		
		ans.add("</table>");
		
		ans.add("<input type=\"hidden\" name=\"user\" id=\"user\" value = \""+cfgMenuWebUser+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromDb\" id=\"fromDb\" value = \""+sourceDB+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromBn\" id=\"fromBn\" value = \""+sourceBN+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromSql\" id=\"fromSql\" value = \""+sourceSQL+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromApp\" id=\"fromApp\" value = \""+sourceAPP+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromCust\" id=\"fromCust\" value = \""+customerShort+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"myBrowser\" id=\"myBrowser\" maxlength=\"140\" size=\"140\">");
		
	
	//	ans.add("<br><INPUT TYPE=SUBMIT value=\"Replace my private environment\"onclick=\"document.getElementById('loader').style.display = 'block';\" ></FORM>");
	//	ans.add("<br><INPUT TYPE=SUBMIT value=\"Replace my private environment\" onclick=\"document.getElementById('loader').style.display = 'block'; this.style.display = 'none'\" ></FORM>");

	
		ans.add("<br><INPUT TYPE=SUBMIT value=\"Backup the database\" onclick=\"document.getElementById('loader').style.display = ''; this.style.display = 'none'\" ></FORM>");
		
		
		ans.add("<img id=\"loader\" src=\"Please_wait.gif\" style=\"display: none;\"/>");
		//ans.add("<input type=\"text\" id=\"loader1\" value =\"Please_wait33\" style=\"display: none;\"/>");
//ans.add("</body>"); 
return ans.convertToString(" ");
}


	public String 		getEnvTomcatSize(HagStringList cbEnvs ,String cfgMenuWebUser, HttpServletResponse response,String plat){
		//if(cbEnvs.size()>1)
		//	return HagUtil.shortHtml("only one env (checkBox) can be selected for this option", "red","bg");
		StringBuilder ans = new StringBuilder();
		ans.append("<table cellpadding=\"3\" cellspacing=\"3\" border = \"2\">");
		ans.append("<tr  bgColor=\\\"#cccccc\\\"><td>normal size</td><td>500000000</td></tr>");
		for(int i = 0; i < cbEnvs.size();i++) {
			String line = cbEnvs.get(i);
			String sourceAPP=HagUtil.getWord0(line,"~",1,true);
			String sourceDB=HagUtil.getWord0(line,"~",11,true);
			String sourceBN=HagUtil.getWord0(line,"~",2,true);
			String tomcatFolderS = "\\\\"+sourceAPP+"\\ri_java\\RIjava_"+sourceBN+"\\tomcat"; 
			File tomcatFolder = new File(tomcatFolderS);
			long folderSize = HagUtil.getFolderSize(tomcatFolder);
			ans.append("<tr><td>"+sourceAPP+"("+sourceBN+") size</td><td>"+folderSize+"</td></tr>");
			//return folderSize+"b";
	}
		ans.append("</table>");
		return ans.toString();
	}
	
	public String 		updateLastinstallation(HagStringList cbEnvs){
		
		if(cbEnvs.size()>1)
			return HagUtil.shortHtml("only one env (checkBox) can be selected for this option", "red","bg");
	
		//HagSQL hagSQL = new HagSQL();
		//String stm = "select batchName,bis_server,sql_server,db,lastInst,lastGoodInst,owner,sql_server_version from dbo.ri_environments_new where status='A' and project='RI' order by bis_server,batchName";
		//HagStringList list = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,8,"~",null,null);
		
		//String host = ""; // Declare host outside the loop
	   // String schema = ""; // Declare schema outside the loop

		//for(int k = 0 ; k < list.size();k++){
			//String temp1 = list.get(k);
			//schema = HagUtil.getWord0(temp1, "~", 0,true);
			//host = HagUtil.getWord0(temp1, "~", 1,true);

		//}
		
		String line = cbEnvs.get(0);
		
		String sourceAPP=HagUtil.getWord0(line,"~",1,true);
		String sourceBN=HagUtil.getWord0(line,"~",2,true);
			
		///String vbLine="cscript //nologo \\\\orsayserver\\d\\gonteam\\#VBscript_and_SQL\\PROD\\Update_Row_Version_From_cmInstaller.vbs  ${host} ${schema} /ProdTest:\"PROD\"";
		//String vbLine = "cscript //nologo \"\\\\orsayserver\\d\\gonteam\\#VBscript_and_SQL\\PROD\\Update_Row_Version_From_cmInstaller.vbs\" " + sourceAPP  + " " + sourceBN + " /ProdTest:\"PROD\"";
		//String ansLock1 =  HagUtil.runCmd2(vbLine+"\n",false);
		///return "aaaa";
		//return HagUtil.shortHtml(vbLine, "red","bg");
		
		
		String vbScriptPath = "\\\\orsayserver\\d\\gonteam\\#VBscript_and_SQL\\PROD\\Update_Row_Version_From_cmInstaller.vbs";
		String vbLine = "cscript //nologo \"" + vbScriptPath + "\" " + sourceAPP + " " + sourceBN + " /ProdTest:\"PROD\"";
		String ansLock1 = HagUtil.runCmd2(vbLine, true);
		return HagUtil.shortHtml(vbLine, "red","bg");
	}
	
	
	
/*
 * 
	public String 		dspInstallations1(HagStringList cbEnvs){

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
		return ans.toString();
	}
 * 
 * 
 * 
 * 
 * 
 */
	
	
	
//	public String 		readTomcatStartupBat(HagStringList cbEnvs ,String cfgMenuWebUser, HttpServletResponse response,String plat){
	public String 		getTomcatStartupBatForJava17(String sourceAPP,String sourceBN){
		StringBuilder ans = new StringBuilder();
		String startupBatFileS = "\\\\"+sourceAPP+"\\ri_java\\RIjava_"+sourceBN+"\\RILSService\\startUp.bat"; 
		File startupBatFile = new File(startupBatFileS);
		if(!startupBatFile.exists()) {
			return HagUtil.shortLineHtml("start.bat file not found", "red");
		}
		HagStringList list = new HagStringList(startupBatFileS,false,"asdasdasdsa",false);
		String[] init = {"INIT","INIT","INIT"};
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
			
					
				int tmp1 = pos1+4;
				int tmp2 = temp.indexOf("m",pos1+4);
				init[0]=temp.substring(tmp1,tmp2);
					
				tmp1 = pos2+4;
				tmp2 = temp.indexOf("m",pos2+4);
				init[1]=temp.substring(tmp1,tmp2);

			
			}
			if(temp.toLowerCase().trim().startsWith("call")) {
				init[2]=temp.substring(5,temp.length());
			}
			
		}	
		if(HagUtil.s2i(init[0])<1025 )
			ans.append("<font color=#008000>").append("-xmx=").append(init[0]).append("</font>").append("<br>");
		else
			ans.append("<font color=#ff0000>").append("-xmx=").append(init[0]).append("</font>").append("<br>");
		
		if(HagUtil.s2i(init[1])<1025 )	
			ans.append("<font color=#008000>").append("-xms=").append(init[1]).append("</font>").append("<br>");
		else
			ans.append("<font color=#ff0000>").append("-xms=").append(init[1]).append("</font>").append("<br>");
		
		ans.append("opt/dbg=").append(init[2]).append("</font>");
		return ans.toString();
	}
	//T
	//U
	//V
	//W
	//X
	//Y
	//Z
	
	
	public String 		getTomcatStartupBat(String sourceAPP,String sourceBN){
		StringBuilder ans = new StringBuilder();
		String startupBatFileS = "\\\\"+sourceAPP+"\\ri_java\\RIjava_"+sourceBN+"\\RILSService\\startUp.bat"; 
		File startupBatFile = new File(startupBatFileS);
		if(!startupBatFile.exists()) {
			return HagUtil.shortLineHtml("start.bat file not found", "red");
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
		if(HagUtil.s2i(init[0])<1025 )
			ans.append("<font color=#008000>").append("-xmx=").append(init[0]).append("</font>").append("<br>");
		else
			ans.append("<font color=#ff0000>").append("-xmx=").append(init[0]).append("</font>").append("<br>");
		
		if(HagUtil.s2i(init[1])<1025 )	
			ans.append("<font color=#008000>").append("-xms=").append(init[1]).append("</font>").append("<br>");
		else
			ans.append("<font color=#ff0000>").append("-xms=").append(init[1]).append("</font>").append("<br>");
		
	     if(HagUtil.s2i(init[2])<1025 )	
			ans.append("<font color=#008000>").append("maxpermsize=").append(init[2]).append("</font>").append("<br>");
		else
			ans.append("<font color=#ff0000>").append("maxpermsize=").append(init[2]).append("</font>").append("<br>");

		if(HagUtil.s2i(init[3])<1025 )	
			ans.append("<font color=#008000>").append("permsize=").append(init[3]).append("</font>").append("<br>");
		else
			ans.append("<font color=#ff0000>").append("permsize=").append(init[3]).append("</font>").append("<br>");
				
		ans.append("opt/dbg=").append(init[4]).append("</font>");
		return ans.toString();
	}
	
	
	public String 		getJavaHome(String sourceAPP,String sourceBN){
		StringBuilder ans = new StringBuilder();
		String javaHome = "JDK-8";
		String startupBatFileS = "\\\\"+sourceAPP+"\\ri_java\\RIjava_"+sourceBN+"\\RILSService\\startUp.bat"; 
		File startupBatFile = new File(startupBatFileS);
		if(!startupBatFile.exists()) {
			return HagUtil.shortLineHtml("start.bat file not found", "red");
		}
		HagStringList list = new HagStringList(startupBatFileS,false,"asdasdasdsa",false);
		String batchContent = "";
		for (String str : list) {
			batchContent+= str + "\t";
		}
		System.out.println("Batch File List:"+ batchContent);
		if (batchContent.contains("--add-exports") &&  batchContent.contains("--add-opens")) {
			javaHome = "JDK-17";
		}
		return javaHome;
	}




	
	

	public String 	mailPassword(String cfgMenuWebUser, String cfgMenuWebPass){
		if(cfgMenuWebUser.equals("guest")){
			return "NO password from guest user<br>Please select user";
		}
		//HagStringList list = new HagStringList(HagUtil.cfgMenuWebLoc+"\\temp.txt",true,"*",false);
		//HagStringList list = new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\users.txt",true,"*",false);
		HagSQL hagSQL = new HagSQL();
		String stm = "select * from dbo.hagCfgMenuWebUsers where name1='"+cfgMenuWebUser+"'";
		HagStringList usersList =hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,6,"|",null,null);
		if(usersList.size()==0)
			return cfgMenuWebUser+" user not found <br> please call hagay (2527)";
		String u = HagUtil.getWord0(usersList.get(0), "|", 0, true);
		String p = HagUtil.getWord0(usersList.get(0), "|", 1, true);
		String e = u+"@sapiens.com";
		/*
		String d = "zzz";
		String u = "zzz";
		String p = "zzz";
		String e = "zzz";
		String a = "zzz";
		boolean found = false;
		for(int i= 0;i <list.size();i++){
			String temp=list.get(i);
			d = HagUtil.getWord0(temp,"~",0,true);
			u = HagUtil.getWord0(temp,"~",1,true);
			p = HagUtil.getWord0(temp,"~",2,true);
			e = HagUtil.getWord0(temp,"~",3,true);
			a = HagUtil.getWord0(temp,"~",4,true);
			if(u.equals(cfgMenuWebUser)){
				found = true;
				break;
			}
		}
		if(!found)
			return cfgMenuWebUser+" user not found <br> please call hagay (2527)";
			*/
		StringBuilder mailContent = new StringBuilder();
		if(p.equals(".")){
			mailContent.append(u).append("-").append("First time using restrict area:<br>")
			.append("please set your password (initial step for restrict area )<br><br>") 
			.append("<u>how to change password:</u> <br>")
			.append("Register->change password<br>")
			.append("select the authorizations you need<br><br>") 
			.append("<u>how to request authorization:</u><br>")
			.append("Register->Authorizations request");
			return mailContent.toString();
		}
		mailContent.append("user=").append(u).append("<br>")
		.append("password=").append(p).append("<br>")	;
	//	.append("Authorizations=").append(a);
		HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+cfgMenuWebUser,HagUtil.mailList_hag, "cfgWeb password", mailContent.toString());

		return "Your password sent to "+e;
	}	
	public String 	tomcatsStatus(HttpServletRequest request, HttpServletResponse response){
		return HagForms.tomcatsStatus(null);
	}
	public String 	iWayLinks(HttpServletRequest request, HttpServletResponse response){
		return HagForms.iWayLinks(null);
	}
	public String 	none(String act ){
		return act+"<br>is just a delimiter<br>please select active option";  
	}


	public HagStringList 	getProcesses(String server1,String dbid){
		HagStringList list = HagUtil.getWmicByName(server1, "java",dbid);
		HagStringList list1 = new HagStringList();
		for (int i = 0; i < list.size(); i++) {
			String temp = list.get(i);
			int pos = temp.lastIndexOf("\\RIjava_" + dbid.toUpperCase() + "\\tomcat\\");
			int pos1 = temp.lastIndexOf("java");
			if (pos > -1) {
				String temp1 = temp.substring(pos + 8, pos + 10) + "  " + temp.substring(pos1, temp.length());
				list1.add(temp1.trim());
			}
		}
		return list1;
	}
	

	
	public String 	runDevOptionsPre(HagStringList cbEnvs,String cfgMenuWebUser,String cfgMenuWebPass,String title,String opt,String plat){
		HagI5OS hagI5OS = new HagI5OS();
		String user ="RIGONEN1";
		String pass ="GON09C";
		StringBuilder ans =new StringBuilder();
		//ans.append("<h3>Replace Jar</h3>");
		ans.append("<h2><font color=\"#ff4444\">"+title+"</font></h2>");
		//ans.append("<h3><i>Please note that Replace-Jar process will restart tomcat and kill the environment processes.</i></h3>");
		ans.append("<h3><i>Please note that "+title+" process will restart tomcat and kill the environment processes.</i></h3>");
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"dist.jsp\">");
		ans.append("<table bgColor=\"#dddddd\" cellpadding=\"3\" cellspacing=\"3\" border=\"1\">");
		for(int i = 0;i<cbEnvs.size();i++){
			String version = "";
			String patch = "";
			String env = "";
			String myJiraVer = "";
			String ifs="";
			String temp = cbEnvs.get(i);
			String appServer = HagUtil.getWord0(temp,"~",1,true);
			String batchName = HagUtil.getWord0(temp,"~",2,true);
			String tomcatPort = "0000";
			if(plat.equals("WIN")) {
				version = HagUtil.getWord0(temp,"~",6,true);
				patch = HagUtil.getWord0(temp,"~",7,true);
				env = HagUtil.getWord0(temp,"~",9,true);
				myJiraVer=HagUtil.getJiraVer(version, patch);
			}else {
				String mainVer = getMainVer(appServer,batchName);
				String subVer = getSubVer(appServer,batchName);
				String db2 = getDb2(appServer,batchName,"LIBTYPE");
				String winSuff = "m00";
				ifs = hagI5OS.i5osDtaara(appServer, user, pass, db2, "INSTINFO", "IFS");
				String addNo = hagI5OS.i5osDtaara(appServer, user, pass, db2, "INSTINFO", "Update");
				String ifsTrim = ifs.trim();
				String ifsLast = ifsTrim.substring(ifsTrim.lastIndexOf("/") + 1, ifsTrim.length());
				if (addNo.length() > 0) {
					winSuff = (ifsLast.substring(0, 3)).toLowerCase() + "U00";
				
				}
				version = mainVer+""+subVer+ winSuff;
				patch = "";
				env = 	 getI5osEnv(appServer,batchName);
				myJiraVer=HagUtil.getJiraVer(mainVer+""+subVer, winSuff);
				tomcatPort = HagUtil.getWord0(temp,"~",10,true);
		
			}
			String patch1 = patch;
			if(patch.length()==5)
				patch1=patch.substring(0,3)+"U"+patch.substring(3,5);

			String 			topackFolder= "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V"+version+patch1;
			HagStringList 	killList1 	= getProcesses( appServer, batchName);
			String 			killList 	= killList1.convertToString("<br>");
			String 			isCmLock1 	= HagUtil.isCmLockStr(appServer,batchName,"WIN");
			String 			ans1		= "Locked by cmInstaller, Please try again after cmInstaller-finish mail.";
			
			if(!isCmLock1.equals("YES")){
				if(plat.equals("I5OS")) {
					ans1=".";
				}else {
					//dev lock1
					HagStringList devLocks = HagUtil.isDevLock(appServer,batchName);
					if(!devLocks.get(0).equals("0")){
						ans1 = "CM Error - Please call hagay.";
					}else if(devLocks.get(0).equals("0") && !devLocks.get(1).equals(".")){
						ans1 = "Locked: "+devLocks.get(1);
					}else
						ans1= ".";
				}
			}
			
			if(title.equals("Replace Jar")){
				String 			targetFolder= appServer+":   D:\\RI_JAVA\\RIjava_"+batchName+"\\tomcat\\webapps\\ri-web\\WEB-INF\\lib";
				String 			toDelete 	= targetFolder+"\\batch-MSS-*.jar";
				//ans.append("<table bgColor=\"#dddddd\" cellpadding=\"3\" cellspacing=\"3\" border=\"1\">");
				if(ans1.equals("."))
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"\" checked></td><td>Replace <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> Jar</td><td>.</td><td>.</td></tr>");
				else
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"\" disabled readonly></td><td>Replace <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> Jar</td><td>.</td><td bgColor=\"#FF0000\">"+ans1+"</td></tr>");
				ans.append("<tr><td>.</td><td>.</td><td bgColor=\"#bbbbbb\">topack folder</td><td>"+topackFolder+"</td></tr>");
				ans.append("<tr><td>.</td><td>.</td><td bgColor=\"#bbbbbb\">target folder</td><td>"+targetFolder+"</td></tr>");
				ans.append("<tr><td>.</td><td>.</td><td bgColor=\"#bbbbbb\">going to delete</td><td>"+toDelete+"</td></tr>");
				ans.append("<tr><td>.</td><td>.</td><td bgColor=\"#bbbbbb\">processes to kill</td><td>"+killList+"</td></tr>");
				//ans.append("</table><br>");
			}else if(title.equals("Replace Jar I5OS")){
				String 			targetFolder= appServer+":   "+ifs+"/tomcat/webapps/WEB-INF/lib";
				String 			toDelete 	= targetFolder+"/batch-DB2-*.jar";
				//ans.append("<table bgColor=\"#dddddd\" cellpadding=\"3\" cellspacing=\"3\" border=\"1\">");
				if(ans1.equals("."))
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"!"+tomcatPort+"\" checked></td><td>Replace <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> Jar</td><td>.</td><td>.</td></tr>");
				else
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"!"+tomcatPort+"\" disabled readonly></td><td>Replace <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> Jar</td><td>.</td><td bgColor=\"#FF0000\">"+ans1+"</td></tr>");
				ans.append("<tr><td>.</td><td>.</td><td bgColor=\"#bbbbbb\">topack folder</td><td>V"+version+patch1+"</td></tr>");
				ans.append("<tr><td>.</td><td>.</td><td bgColor=\"#bbbbbb\">JAR source</td><td>"+topackFolder+"\\TOPACK\\temp-jar\\batch-DB2*.jar </td></tr>");
				ans.append("<tr><td>.</td><td>.</td><td bgColor=\"#bbbbbb\">target folder</td><td>"+targetFolder+"</td></tr>");
				ans.append("<tr><td>.</td><td>.</td><td bgColor=\"#bbbbbb\">going to delete</td><td>"+toDelete+"</td></tr>");
			
				//ans.append("</table><br>");
			}else if(title.equals("Replace War")){
				String 			targetFolder= appServer+":   D:\\RI_JAVA\\RIjava_"+batchName+"\\tomcat\\webapps";
				String 			toDelete 	= targetFolder+"\\ri-web";
				//ans.append("<table bgColor=\"#dddddd\" cellpadding=\"3\" cellspacing=\"3\" border=\"1\">");
				if(ans1.equals("."))
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"\" checked></td><td>Replace <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> Jar</td><td>.</td><td>.</td></tr>");
				else
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"\" disabled readonly></td><td>Replace <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> Jar</td><td>.</td><td bgColor=\"#FF0000\">"+ans1+"</td></tr>");
				ans.append("<tr><td>.</td><td>.</td><td bgColor=\"#bbbbbb\">topack folder</td><td>"+topackFolder+"\\TOPACK\\temp-war</td></tr>");
				ans.append("<tr><td>.</td><td>.</td><td bgColor=\"#bbbbbb\">target folder</td><td>"+targetFolder+"</td></tr>");
				ans.append("<tr><td>.</td><td>.</td><td bgColor=\"#bbbbbb\">going to delete</td><td>"+toDelete+"</td></tr>");
				ans.append("<tr><td>.</td><td>.</td><td bgColor=\"#bbbbbb\">processes to kill</td><td>"+killList+"</td></tr>");
				//ans.append("</table><br>");
			}else if(title.equals("Replace War I5OS")){
				String 			targetFolder= appServer+":   "+ifs+"/tomcat/webapps";
				String 			toDelete 	= targetFolder+"/ri-web";
				//ans.append("<table bgColor=\"#dddddd\" cellpadding=\"3\" cellspacing=\"3\" border=\"1\">");
				if(ans1.equals("."))
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"!"+tomcatPort+"\" checked></td><td>Replace <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> Jar</td><td>.</td><td>.</td></tr>");
				else
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"!"+tomcatPort+"\" disabled readonly></td><td>Replace <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> Jar</td><td>.</td><td bgColor=\"#FF0000\">"+ans1+"</td></tr>");
				ans.append("<tr><td>.</td><td>.</td><td bgColor=\"#bbbbbb\">topack folder</td><td>V"+version+patch1+"</td></tr>");
				ans.append("<tr><td>.</td><td>.</td><td bgColor=\"#bbbbbb\">WAR source</td><td>"+topackFolder+"\\TOPACK\\ri-core\\ri-web.war </td></tr>");
				ans.append("<tr><td>.</td><td>.</td><td bgColor=\"#bbbbbb\">target folder</td><td>"+targetFolder+"</td></tr>");
				ans.append("<tr><td>.</td><td>.</td><td bgColor=\"#bbbbbb\">going to delete</td><td>"+toDelete+"</td></tr>");
			//	ans.append("<tr><td>.</td><td>.</td><td bgColor=\"#bbbbbb\">processes to kill</td><td>"+killList+"</td></tr>");
				//ans.append("</table><br>");
			}else if(title.equals("Delete ri-logs")){
			//	ans.append("<table bgColor=\"#dddddd\" cellpadding=\"3\" cellspacing=\"3\" border=\"1\">");
				if(ans1.equals("."))
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"\" checked></td><td>Delete <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> logs</td><td>.</td><td>.</td></tr>");
				else
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"\" disabled readonly></td><td>Delete <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> logs</td><td>.</td><td bgColor=\"#FF0000\">"+ans1+"</td></tr>");
				//ans.append("</table><br>");
			}else if(title.equals("Compile cust IOM")){
			//	ans.append("<table bgColor=\"#dddddd\" cellpadding=\"3\" cellspacing=\"3\" border=\"1\">");
				if(ans1.equals("."))
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"\" checked></td><td>Compile <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> cust IOM</td><td>.</td><td>.</td></tr>");
				else
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"\" disabled readonly></td><td>Compile <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> cust IOM</td><td>.</td><td bgColor=\"#FF0000\">"+ans1+"</td></tr>");
				//ans.append("</table><br>");
			}else if(title.equals("Open Core Splits")){
				//ans.append("<table bgColor=\"#dddddd\" cellpadding=\"3\" cellspacing=\"3\" border=\"1\">");
				if(ans1.equals("."))
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"\" checked></td><td>Open <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> Core Splits</td><td>.</td><td>.</td></tr>");
				else
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"\" disabled readonly></td><td>Open <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> Core Splits</td><td>.</td><td bgColor=\"#FF0000\">"+ans1+"</td></tr>");
				//ans.append("</table><br>");
			}else if(title.equals("Close Core Splits")){
				//ans.append("<table bgColor=\"#dddddd\" cellpadding=\"3\" cellspacing=\"3\" border=\"1\">");
				if(ans1.equals("."))
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"\" checked></td><td>Close <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> Core Splits</td><td>.</td><td>.</td></tr>");
				else
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"\" disabled readonly></td><td>Close <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> Core Splits</td><td>.</td><td bgColor=\"#FF0000\">"+ans1+"</td></tr>");
				//ans.append("</table><br>");
			}else if(title.equals("Set log4j set level=DEBUG")){
			//	ans.append("<table bgColor=\"#dddddd\" cellpadding=\"3\" cellspacing=\"3\" border=\"1\">");
				if(ans1.equals("."))
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"\" checked></td><td>Set <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> log4j set level=DEBUG</td><td>.</td><td>.</td></tr>");
				else
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"\" disabled readonly></td><td>Set <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> log4j set level=DEBUG</td><td>.</td><td bgColor=\"#FF0000\">"+ans1+"</td></tr>");
			//	ans.append("</table><br>");
			}else if(title.equals("Set log4j set level=ERROR")){
				//ans.append("<table bgColor=\"#dddddd\" cellpadding=\"3\" cellspacing=\"3\" border=\"1\">");
				if(ans1.equals("."))
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"\" checked></td><td>Set <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> log4j set level=ERROR</td><td>.</td><td>.</td></tr>");
				else
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"\" disabled readonly></td><td>Set <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> log4j set level=ERROR</td><td>.</td><td bgColor=\"#FF0000\">"+ans1+"</td></tr>");
				//ans.append("</table><br>");
			}else if(title.equals("Set tomcat start DEBUG")){
				//ans.append("<table bgColor=\"#dddddd\" cellpadding=\"3\" cellspacing=\"3\" border=\"1\">");
				if(ans1.equals("."))
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"\" checked></td><td>Set <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> tomcat start DEBUG</td><td>.</td><td>.</td></tr>");
				else
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"\" disabled readonly></td><td>Set <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> tomcat start DEBUG</td><td>.</td><td bgColor=\"#FF0000\">"+ans1+"</td></tr>");
				//ans.append("</table><br>");
			}else if(title.equals("Set tomcat start OPT")){
			//	ans.append("<table bgColor=\"#dddddd\" cellpadding=\"3\" cellspacing=\"3\" border=\"1\">");
				if(ans1.equals("."))
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"\" checked></td><td>Set <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> tomcat start OPT</td><td>.</td><td>.</td></tr>");
				else
					ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+appServer+"!"+batchName+"!"+env+"!"+topackFolder+"!"+myJiraVer+"\" disabled readonly></td><td>Set <b><font color=\"#800517\">" +appServer+"-"+batchName+"</b></font> tomcat start OPT</td><td>.</td><td bgColor=\"#FF0000\">"+ans1+"</td></tr>");
				//ans.append("</table><br>");
			}
			//ans.append("<tr><td>"+appServer+"</td><td>"+batchName+"</td><td>"+ans1+"</td></tr>" );
		}
		ans.append("</table><br>");
		//ans.append("<input type=\"hidden\" name=\"opt\" id=\"opt\" value = \"devReplaceJar~aa!aa\" maxlength=\"140\" size=\"140\">");
		ans.append("<input type=\"hidden\" name=\"opt\" id=\"opt\" value = \""+opt+"~aa!aa\" maxlength=\"140\" size=\"140\">");
		ans.append("<input type=\"hidden\" name=\"user\" id=\"user\" value = \""+cfgMenuWebUser+"\" maxlength=\"140\" size=\"140\">");
		ans.append("<input type=\"hidden\" name=\"pass\" id=\"pass\" value = \""+cfgMenuWebPass+"\" maxlength=\"140\" size=\"140\">");

		//ans.append("<br><br><INPUT TYPE=SUBMIT value=\"Replace\"></FORM>");
		ans.append("<br><br><INPUT TYPE=SUBMIT value=\"Submit\"   onclick=\"document.getElementById('loader').style.display = 'block';\" ></FORM>");
		ans.append("<table bgColor=\"#ffff00\" cellpadding=\"3\" cellspacing=\"3\" border=\"1\">");
		ans.append("<tr><td><img id=\"loader\" src=\"Please_wait.gif\" style=\"display: none;\"/></td></tr>");
	//	ans.append("<tr><td>Please click the \"Submit\" button and wait, it will take few seconds</td></tr>");
		ans.append("</table></BODY>");


		return ans.toString();
	}
	
	
	public String 	runGardOptionsPre(HagStringList cbEnvs,String cfgMenuWebUser,String cfgMenuWebPass,String title){
		StringBuilder ans =new StringBuilder();
		ans.append("<h2><font color=\"#ff4444\">"+title+"</font></h2>");
		//ans.append("<h3><i>Please note that "+title+" process will restart tomcat and kill the environment processes.</i></h3>");
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"sendGardRequestNew.jsp\">");
		ans.append("<table bgColor=\"#dddddd\" cellpadding=\"3\" cellspacing=\"3\" border=\"1\">");
		ans.append("<tr bgColor=\"#bbbbbb\">")
		.append("<td><input type=\"checkbox\" name=\"cb41\" id=\"cb41\" value=\"\" disabled readonly></td>")
		.append("<td><b><font color=\"#800517\">Environment name</b></font></td><td><b><font color=\"#800517\">APP ip</b></font></td><td><b><font color=\"#800517\">SQL ip</b></font></td><td><b><font color=\"#800517\">batch name</b></font></td>")
		.append("</tr>");
		if(cbEnvs.size()>1)
			return HagUtil.shortHtml("only one env (checkBox) can be selected for this option", "red","bg");
		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			String envName = HagUtil.getWord0(temp,"~",1,true);
			String batchName = HagUtil.getWord0(temp,"~",2,true);
			String appIp = HagUtil.getWord0(temp,"~",3,true);
			String sqlIp = HagUtil.getWord0(temp,"~",6,true);
			
			ans.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cb4\" id=\"cb4\" value=\""+envName+"!"+appIp+"!"+sqlIp+"!"+batchName+"\"  checked  ></td><td>"+envName+"</td><td>"+appIp+"</td><td>"+sqlIp+"</td><td>"+batchName+"</td></tr>");
			
		}
		
		ans.append("</table><br>");
		if(title.equals("Gard-Install RI package")) {
			String file = 	HagUtil.cfgMenuWebLoc+"\\lists\\gardVersions.txt" ;
			HagStringList gardVersionsList = new HagStringList ( file,true,"*",false);
			ans.append("<br><table bgColor=\"#dddddd\"cellpadding=\"3\" cellspacing=\"3\" border =\"1\" >");
			ans.append("<tr><td>RI package</td><td><select class=\"c30\" id=\"gardVersion\" name =\"gardVersion\" >");
			for(int k = 0 ; k < gardVersionsList.size();k++){
				String temp1 = gardVersionsList.get(k);
				ans.append("<option class=\"c30\" value=\""+temp1+"\" >"+temp1+" </option>");
			}
			ans.append("</td></tr>");
			ans.append("</table><br>");
		}
		

		if(title.equals("Gard-Replace database from cloud")) {
			HagSQL hagSQL = new HagSQL();
			String stm = "select envName,batchName,appIp,sqlIp  from dbo.gard_cloud where project='RI'";
			HagStringList list = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,4,"~",null,null);
			ans.append("<br><table bgColor=\"#dddddd\"cellpadding=\"3\" cellspacing=\"3\" border =\"1\" >");
			ans.append("<tr><td>copy DB from</td><td><select class=\"c30\" id=\"fromDb\" name =\"fromDb\" >");
			for(int k = 0 ; k < list.size();k++){
				String temp1 = list.get(k);
				String env= HagUtil.getWord0(temp1, "~", 0,true);
				String batchName = HagUtil.getWord0(temp1, "~", 1,true);
				String appIP = HagUtil.getWord0(temp1, "~", 2,true);
				String sqlIP = HagUtil.getWord0(temp1, "~", 3,true);
				String appIp1 = HagUtil.left("APP_IP: "+appIP, "_",25,".");
				String sqlIp1 = HagUtil.left("SQL_IP: "+sqlIP, "_", 25,".");
				String batchName1 = HagUtil.left("batch_Name: "+batchName,"_", 25,".");
				String line = batchName1+appIp1+sqlIp1+"env_Name: "+env;
				ans.append("<option class=\"c30\" value=\""+line+"\" >"+line+" </option>");
			}
			ans.append("</td></tr>");
			ans.append("</table><br>");
		}
		if(title.equals("Gard-Replace database from RI-server")) {
			HagSQL hagSQL = new HagSQL();
			String stm = "select batchName,bis_server,sql_server  from dbo.ri_environments_new where status='A' and project='RI' and party='180000'";
			HagStringList list = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,3,"~",null,null);
			ans.append("<br><table bgColor=\"#dddddd\"cellpadding=\"3\" cellspacing=\"3\" border =\"1\" >");
			ans.append("<tr><td>copy DB from</td><td><select class=\"c30\" id=\"fromDb\" name =\"fromDb\" >");
			for(int k = 0 ; k < list.size();k++){
				String temp1 = list.get(k);
				String batchName = HagUtil.getWord0(temp1, "~", 0,true);
				String appIP = HagUtil.getWord0(temp1, "~", 1,true);
				String sqlIP = HagUtil.getWord0(temp1, "~", 2,true);
				String appIp1 = HagUtil.left("APP: "+appIP, "_",25,".");
				String sqlIp1 = HagUtil.left("SQL: "+sqlIP, "_", 25,".");
				String batchName1 = HagUtil.left("batch_Name: "+batchName,"_", 25,".");
				String line = batchName1+appIp1+sqlIp1;
				ans.append("<option class=\"c30\" value=\""+line+"\" >"+line+" </option>");
			}
			ans.append("</td></tr>");
			ans.append("</table><br>");
		}
		if(title.equals("Gard-Replace database from bk file")) {
			ans.append("<table bgColor=\"#dddddd\" cellpadding=\"3\" cellspacing=\"3\" border =\"1\">");
			ans.append("<tr><td bgColor=\"#aaaaaa\">from bk file</td><td><input type=\"text\" name=\"fromBk\" id=\"fromBk\" maxlength=\"140\" size=\"140\"></td></tr>");

			ans.append("</table><br>");
		}

		ans.append("<table bgColor=\"#dddddd\" cellpadding=\"3\" cellspacing=\"3\" border =\"1\">");
		ans.append("<tr><td bgColor=\"#aaaaaa\">Note</td><td><input type=\"text\" name=\"note\" id=\"note\" maxlength=\"140\" size=\"140\"></td></tr>");

		ans.append("</table><br>");

		
		//ans.append("<input type=\"hidden\" name=\"opt\" id=\"opt\" value = \"devReplaceJar~aa!aa\" maxlength=\"140\" size=\"140\">");
		ans.append("<input type=\"hidden\" name=\"opt\" id=\"opt\" value = \""+title+"\" maxlength=\"140\" size=\"140\">");
		ans.append("<input type=\"hidden\" name=\"user\" id=\"user\" value = \""+cfgMenuWebUser+"\" maxlength=\"140\" size=\"140\">");
		ans.append("<input type=\"hidden\" name=\"pass\" id=\"pass\" value = \""+cfgMenuWebPass+"\" maxlength=\"140\" size=\"140\">");

		//ans.append("<br><br><INPUT TYPE=SUBMIT value=\"Replace\"></FORM>");
		ans.append("<br><br><INPUT TYPE=SUBMIT value=\"Submit\"   onclick=\"document.getElementById('loader').style.display = 'block';\" ></FORM>");
		//ans.append("<table bgColor=\"#ffff00\" cellpadding=\"3\" cellspacing=\"3\" border=\"1\">");
		//ans.append("<tr><td><img id=\"loader\" src=\"Please_wait.gif\" style=\"display: none;\"/></td></tr>");
	//	ans.append("<tr><td>Please click the \"Submit\" button and wait, it will take few seconds</td></tr>");
		ans.append("</BODY>");


		return ans.toString();
	}
	
	public String 	requstsHandleAfter1(String cfgMenuWebUser,HttpServletRequest request, HttpServletResponse response){
		String act1 = request.getParameter( "act1" );
/*
		String[][] list = new String[10][9];
		list[0]	= new String[9];
		list[1]	= new String[9];
		list[2]	= new String[9];
		list[3]	= new String[9];
		list[4]	= new String[9];
		list[5]	= new String[9];
		list[6]	= new String[9];
		list[7]	= new String[9];
		list[8]	= new String[9];
		list[9]	= new String[9];

		list[0] = request.getParameterValues("arr[0][]");
		list[1] = request.getParameterValues("arr[1][]");
		list[2] = request.getParameterValues("arr[2][]");
		list[3] = request.getParameterValues("arr[3][]");
		list[4] = request.getParameterValues("arr[4][]");
		list[5] = request.getParameterValues("arr[5][]");
		list[6] = request.getParameterValues("arr[6][]");
		list[7] = request.getParameterValues("arr[7][]");
		list[8] = request.getParameterValues("arr[8][]");
		list[9] = request.getParameterValues("arr[9][]");
*/
		if(act1.equals("Save_request") || act1.equals("Load_request") )
			return HagUtil.shortHtml("Info: Save_request & Load_request options for future use only", "Yellow", "bg");

		

		String to 		= "david.ha@sapiens.com";
		String from 	= "cfgTeam@sapiens.com";
		String ver1		= request.getParameter( "ver" );
		String maint1 	= request.getParameter( "maint" );
		String update1 	= request.getParameter( "update" );
		String hotfix1 	= request.getParameter( "hotfix" );
		String platform1 = request.getParameter( "platform" );
		String textA = request.getParameter( "done" );
		StringBuilder customer1 = new StringBuilder();
		//String custFolder = "ALL";
		String note1 	= request.getParameter( "note" );
		String instOn1 	= request.getParameter( "instOn" );
		String cfgMenuWebUser1 = request.getParameter( "cfgMenuWebUser" );
		String[] customers = request.getParameterValues("customers");
		
	
		String packagePrep = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\cfg\\hotfix\\cmInstaller\\"+ver1+"."+maint1+"."+update1+"."+hotfix1;
		String packagePrepLink = "<a href=\""+packagePrep+"\">"+packagePrep+"</a>";
	
		//int ind = HagUtil.getRequestInd();
		//HagUtil.addRequest(ind,cfgMenuWebUser1, ".");
		//String indS = HagUtil.padNumLeft(""+ind, 5, '0');
		//String subject 	= "#Request to create hotfix "+ver1+" M"+maint1+" U"+update1+" HF"+hotfix1+" @BREAK-REQ@ #"+indS+"# sent by "+cfgMenuWebUser1;

		if(customers.length ==0){
			return HagUtil.shortHtml("Must select at least one customer.", "red", "bg");
		}
		if(customers.length ==1){
			customer1.append(customers[0]);
			//custFolder=customers[0];
		}else{
			//custFolder="Multi";
			customer1.append("Multi{");
			for(int i = 0;i<customers.length;i++){
				customer1.append(customers[i]);
				if(i<customers.length-1)
					customer1.append(",");
			}
			customer1.append("}");
		}
		//String ftp = "ftp://ri_core:j7nmA16@i-ftp.sapiens.com/"+ver1+"m"+maint1+"u"+update1+"/HotFix"+hotfix1+"/win/"+custFolder;
		//String ftpLink = "<a href=\""+ftp+"_{DATETIME}\">"+ftp+"_{DATETIME}</a>";
		
		
		HagStringList 	content = new HagStringList();
		HagStringList 	contentM = new HagStringList();
		content.add("Request to create hotfix: <br>");
		content.add("<table bgColor =\"#aaaaaa\" border=\"1\"  cellpadding=\"10\">");
		contentM.add(content.get(content.size()-1));
		content.add("<tr><td bgColor =\"#eeeeee\">Package:</td><td>"+ver1+" M"+maint1+" U"+update1+" HF"+hotfix1+"</td></tr>");
		contentM.add(content.get(content.size()-1));
		content.add("<tr><td bgColor =\"#eeeeee\">Platform:</td><td>"+platform1+"</td></tr>");
		contentM.add(content.get(content.size()-1));
		content.add("<tr><td bgColor =\"#eeeeee\">Customer:</td><td>"+customer1.toString()+"</td></tr>");
		contentM.add(content.get(content.size()-1));
		content.add("<tr><td bgColor =\"#eeeeee\">Note:</td><td>"+note1+"</td></tr>");
		contentM.add(content.get(content.size()-1));
		content.add("<tr><td bgColor =\"#eeeeee\">Install on:</td><td>"+instOn1+"</td></tr>");
		contentM.add(content.get(content.size()-1));
		content.add("<tr><td bgColor =\"#eeeeee\">Requested by:</td><td>"+cfgMenuWebUser1+"</td></tr>");
		contentM.add(content.get(content.size()-1));
		content.add("<tr><td bgColor =\"#eeeeee\">Link to packagePrep:</td><td>"+packagePrepLink+"</td></tr>");
		//contentM.add("<tr><td bgColor =\"#eeeeee\">Link to FTP:</td><td>"+ftpLink+"</td></tr>");
		content.add("<!--link-->");
		contentM.add(content.get(content.size()-1));
		content.add("</table><br>");
		contentM.add(content.get(content.size()-1));
		content.add("<br>Hotfix content:<br><table bgColor =\"#aaaaaa\" border=\"1\"  cellpadding=\"10\">");
		contentM.add(content.get(content.size()-1));
		content.add("<tr bgColor =\"#eeeeee\"><td>Type</td><td>Parameters</td></tr>");
		contentM.add(content.get(content.size()-1));
		HagStringList content1 = new HagStringList();
		//for(int k=0;k<list.length;k++){
		//	if(list[k]!=null && !list[k][0].equals("None") ){
		//		String prm = HagUtil.getParameters(list[k]);
		//		content.add("<tr><td>"+list[k][0]+"</td><td>"+prm+"</td></tr>");
		//		contentM.add(content.get(content.size()-1));
		//		content1.add(list[k][0]+","+prm);
		//	}
		//}
		
		HagStringList bbb = new HagStringList(textA,"#",true);
		int[] isMigFound = {0};
		for(int k=0;k<bbb.size();k++){
			String temp = bbb.get(k);
			if(temp!=null && temp.trim().length()>0){
				String temp1 = HagUtil.getWord0(temp,":",0,true);
				String temp2 = HagUtil.getWord0(temp,":",1,true);
				content.add("<tr><td>"+temp1+"</td><td>"+temp2+"</td></tr>");
				contentM.add(content.get(content.size()-1));
				content1.add(getContent(temp1,temp2,isMigFound));
				
			}
		}
		if(isMigFound[0]>0){
			content1.add("Copy_migmng_jar,*,*,*");
			content.add("<tr><td>Copy migMng.jar</td><td>migMng.jar</td></tr>");
			contentM.add(content.get(content.size()-1));
		}
		content.add("</table><br>");
		contentM.add(content.get(content.size()-1));
		content.add("<b>cm team:</b><br>");
		content.add("=>=>cb "+ver1+"M"+maint1+"U"+update1+"HF"+hotfix1+"   "+instOn1+"  <=<=");
		HagRc hagRc=new HagRc();
		hagRc.rc=0;
		
		
		
		HagUtil.createHotfixCmInstaller(hagRc,packagePrep,ver1,maint1,update1,hotfix1,content1,customer1.toString());
		
		if(hagRc.rc!=0){		
			return HagUtil.shortHtml("Failed to create hotfix<br>"+hagRc.log.convertToString("<br>") , "red", "bg");
		}
		String [] riProp = {".",".","."};
		HagUtil.setPropRi( "Request to pack",".",riProp,ver1);
		//String ans1		= HagUtil.sendMail_hag(from,to,riProp[0],subject,content.convertToString(" "));
		
		
		HagUtil.writeToRelDiary2("Pack",platform1,"Request",ver1+"M"+maint1+"U"+update1+"HF"+hotfix1,"00","OK",content.convertToString(" "),".","cfgMenuWeb",cfgMenuWebUser,".",".");
		contentM.writeToFile(packagePrep+"\\mail.html",null,false);
		hagRc.log.writeToFile(packagePrep+"\\packLog.txt",null,false);
		//if(ans1.startsWith("0~"))		
			return HagUtil.shortHtml("Request sent:<BR><br> "+content.convertToString(" ") , "green", "bg");
		//else
		//	return HagUtil.shortHtml("Failed to send request" , "red", "bg");
	}
	
	
		public void 	requstsHandleAfterSpr1003(String ver1,String maint1,String update1,String hotfix1,String platform1,StringBuilder migD,HagStringList toDoList){
			HagRc hagRc=new HagRc();
			String packagePrep = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\cfg\\hotfix\\cmInstaller\\"+ver1+"."+maint1+"."+update1+"."+hotfix1;
			HagUtil.createHotfixCmInstallerSpr1003(hagRc,packagePrep,ver1,maint1,update1,hotfix1,toDoList,"Multi{Occ,ING,TMM,RS,RSA,HOL,WB,GIC,GRD,CHB,CF,RML}");
			//sendHotfixRequestNew_prepPackage_win0609( hagRc, ver1, maint1, update1, hotfix1, migD, toDoList);
			//CREATE \\HYUNDAI.SAPIENS.COM\HYUN05ri\cfg\hotfix\cmInstaller\0980.01.01.01\
			//refresh \\HYUNDAI.SAPIENS.COM\HYUN05ri\cfg\hotfix\cmInstaller\0980.01.01.01\ from \\HYUNDAI.SAPIENS.COM\HYUN05ri\cfg\hotfix\ARIA\09.80.01.01.01\
		
		}
		
	
		public String 	requstsHandleAriaAfter1(String cfgMenuWebUser,HttpServletRequest request, HttpServletResponse response){
			String act1 = request.getParameter( "act1" );
			if(act1.equals("Save_request") || act1.equals("Load_request") )
				return HagUtil.shortHtml("Info: Save_request & Load_request options for future use only", "Yellow", "bg");


			String to 		= "david.ha@sapiens.com";
			String from 	= "cfgTeam@sapiens.com";
			String ver1		= request.getParameter( "ver" );
			String maint1 	= request.getParameter( "maint" );
			String update1 	= request.getParameter( "update" );
			String hotfix1 	= request.getParameter( "hotfix" );
			String platform1 = request.getParameter( "platform" );
			String textA = request.getParameter( "done" );
			StringBuilder customer1 = new StringBuilder();
			//String custFolder = "ALL";
			String note1 	= request.getParameter( "note" );
			String instOn1 	= request.getParameter( "instOn" );
			String cfgMenuWebUser1 = request.getParameter( "cfgMenuWebUser" );
			String[] customers = request.getParameterValues("customers");
			
		String note2 = ".";
		if (note1!=null && note1.trim().length()>0)
			note2=note1;
			
			String packagePrep = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\cfg\\hotfix\\ARIA\\"+ver1+"."+maint1+"."+update1+"."+hotfix1;
			
			String packagePrepLink = "<a href=\""+packagePrep+"\">"+packagePrep+"</a>";
			String packagePrepLinkAcc = "<a href=\"$packageHref$\">$packageHref$</a>";
			int ind = HagUtil.getRequestInd();
			
			String indS = HagUtil.padNumLeft(""+ind, 5, '0');
			String subject 	= "#Request to create hotfix "+ver1+" M"+maint1+" U"+update1+" HF"+hotfix1+" @BREAK-REQ@ #"+indS+"# sent by "+cfgMenuWebUser1;
			if(!HagUtil.addRequest(ind,cfgMenuWebUser1, note2,subject,"./.",".",".",null,null,null,null,999))
				return HagUtil.shortHtml("Error adding request-mail sent to hagay & gonen, please call hagay or gonen", "red","bg");
			if(customers.length ==0){
				return HagUtil.shortHtml("Must select at least one customer.", "red", "bg");
			}
			if(customers.length ==1){
				customer1.append(customers[0]);
				//custFolder=customers[0];
			}else{
				//custFolder="Multi";
				customer1.append("Multi{");
				for(int i = 0;i<customers.length;i++){
					customer1.append(customers[i]);
					if(i<customers.length-1)
						customer1.append(",");
				}
				customer1.append("}");
			}
			//String ftp = "ftp://ri_core:j7nmA16@i-ftp.sapiens.com/"+ver1+"m"+maint1+"u"+update1+"/HotFix"+hotfix1+"/win/"+custFolder;
			//String ftpLink = "<a href=\""+ftp+"_{DATETIME}\">"+ftp+"_{DATETIME}</a>";
			
			
			HagStringList 	content = new HagStringList();
			HagStringList 	contentM = new HagStringList();
			HagStringList 	contentMacc = new HagStringList();
			content.add("Request to create hotfix: <br>");
			content.add("<table bgColor =\"#aaaaaa\" border=\"1\"  cellpadding=\"10\">");
			contentM.add(content.get(content.size()-1));
			contentMacc.add(content.get(content.size()-1));
			content.add("<tr><td bgColor =\"#eeeeee\">Package:</td><td>"+ver1+" M"+maint1+" U"+update1+" HF"+hotfix1+"</td></tr>");
			contentM.add(content.get(content.size()-1));
			contentMacc.add("<tr><td bgColor =\"#eeeeee\">Package:</td><td>"+ver1+" M"+maint1+" U"+update1+" HF"+hotfix1+" - Accumulative</td></tr>");
			
			content.add("<tr><td bgColor =\"#eeeeee\">Platform:</td><td>"+platform1+"</td></tr>");
			contentM.add(content.get(content.size()-1));
			contentMacc.add(content.get(content.size()-1));
			content.add("<tr><td bgColor =\"#eeeeee\">Customer:</td><td>"+customer1.toString()+"</td></tr>");
			contentM.add(content.get(content.size()-1));
			contentMacc.add(content.get(content.size()-1));
			content.add("<tr><td bgColor =\"#eeeeee\">Note:</td><td>"+note1+"</td></tr>");
			contentM.add(content.get(content.size()-1));
			contentMacc.add(content.get(content.size()-1));
			content.add("<tr><td bgColor =\"#eeeeee\">Install on:</td><td>"+instOn1+"</td></tr>");
			contentM.add(content.get(content.size()-1));
			contentMacc.add(content.get(content.size()-1));
			content.add("<tr><td bgColor =\"#eeeeee\">Requested by:</td><td>"+cfgMenuWebUser1+"</td></tr>");
			contentM.add(content.get(content.size()-1));
			contentMacc.add(content.get(content.size()-1));
			content.add("<tr><td bgColor =\"#eeeeee\">Link to packagePrep:</td><td>"+packagePrepLink+"</td></tr>");
			//contentM.add("<tr><td bgColor =\"#eeeeee\">Link to FTP:</td><td>"+ftpLink+"</td></tr>");
			content.add("<!--link-->");
			contentM.add(content.get(content.size()-1));
			contentMacc.add("<tr><td bgColor =\"#eeeeee\">Link to FTP:{$packageHrefDate$}</td><td>"+packagePrepLinkAcc+"</td></tr>");
			content.add("</table><br>");
			contentM.add(content.get(content.size()-1));
			contentMacc.add(content.get(content.size()-1));
			content.add("<br>Hotfix content:<br><table bgColor =\"#aaaaaa\" border=\"1\"  cellpadding=\"10\">");
			contentM.add(content.get(content.size()-1));
			content.add("<tr bgColor =\"#eeeeee\"><td>Type</td><td>Parameters</td></tr>");
			contentM.add(content.get(content.size()-1));
			HagStringList content1 = new HagStringList();
			//for(int k=0;k<list.length;k++){
			//	if(list[k]!=null && !list[k][0].equals("None") ){
			//		String prm = HagUtil.getParameters(list[k]);
			//		content.add("<tr><td>"+list[k][0]+"</td><td>"+prm+"</td></tr>");
			//		contentM.add(content.get(content.size()-1));
			//		content1.add(list[k][0]+","+prm);
			//	}
			//}
			
			HagStringList bbb = new HagStringList(textA,"#",true);
			int[] isMigFound ={0};
			for(int k=0;k<bbb.size();k++){
				String temp = bbb.get(k);
				if(temp!=null && temp.trim().length()>0){
					String temp1 = HagUtil.getWord0(temp,":",0,true);
					String temp2 = HagUtil.getWord0(temp,":",1,true);
					content.add("<tr><td>"+temp1+"</td><td>"+temp2+"</td></tr>");
					contentM.add(content.get(content.size()-1));
					content1.add(getContent(temp1,temp2,isMigFound));
					
				}
			}
			if(isMigFound[0]>0){
				content1.add("Copy_migmng_jar,*,*,*");
			}
			content.add("</table><br>");
			contentM.add(content.get(content.size()-1));
			content.add("<b>cm team:</b><br>");
			content.add("=>=>cb "+ver1+"M"+maint1+"U"+update1+"HF"+hotfix1+"   "+instOn1+"  <=<=");
			HagRc hagRc=new HagRc();
			hagRc.rc=0;
			
			
			
			HagUtil.createHotfixAria(hagRc,packagePrep,ver1,maint1,update1,hotfix1,content1,customer1.toString());
			
			
			if(hagRc.rc!=0){		
				return HagUtil.shortHtml("Failed to create hotfix<br>"+hagRc.log.convertToString("<br>") , "red", "bg");
			}
			String [] riProp = {".",".","."};
			HagUtil.setPropRi( "Request to pack",".",riProp,ver1);
			//String ans1		= HagUtil.sendMail_hag(from,to,riProp[0],subject,content.convertToString(" "));
			String ans1 =HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+cfgMenuWebUser,HagUtil.mailList_hag,subject,content.convertToString(" "));
			
			HagUtil.writeToRelDiary2("Pack",platform1,"Request",ver1+"M"+maint1+"U"+update1+"HF"+hotfix1,"00","OK",content.convertToString(" "),".","cfgMenuWeb",cfgMenuWebUser,".",".");
			contentM.writeToFile(packagePrep+"\\mail.html",null,false);
			contentMacc.writeToFile(packagePrep+"\\mailAcc.html",null,false);
			hagRc.log.writeToFile(packagePrep+"\\packLog.txt",null,false);
			if(ans1.startsWith("0~"))		
				return HagUtil.shortHtml("Request sent:<BR><br> "+content.convertToString(" ") , "green", "bg");
			else
				return HagUtil.shortHtml("Failed to send request" , "red", "bg");
		}
	public String 	getContent(String name,String parm,int isMigFound[]){
		String ans = "";
		
		if(name.equals("Run manual mig")){
			isMigFound[0]=1;
			String w0 = HagUtil.getWord0(parm,",",0,true);
			String w2 = HagUtil.getWord0(parm,",",2,true);
			String w2a = w2.substring(w2.lastIndexOf(".")+1,w2.length());
			String w3 = HagUtil.getWord0(parm,",",3,true);
			return "Run_manual_mig,"+w0+","+w2a+","+w3;
		}
		if(name.equals("Set MIG_DETAILS table")){
			isMigFound[0]=2;
			String w0 = HagUtil.getWord0(parm,",",0,true);
			String w2 = HagUtil.getWord0(parm,",",2,true);
			String w2a = w2.substring(w2.lastIndexOf(".")+1,w2.length());
			String w3 = HagUtil.getWord0(parm,",",3,true);
			return "Set_MIG_DETAILS_table,"+w0+","+w2a+","+w3;
		}
		if(name.equals("Replace Jar")){
			String w0 = HagUtil.getWord0(parm,",",0,true);
			return "Replace jar,"+w0+",*,*";
		}
		if(name.equals("Replace War")){
			return "Replace war,*,*,*";
		}
		if(name.equals("Replace Jasper")){
			return "Replace jasper,*,*,*";
		}
		if(name.equals("ReplaceAdd Scripts")){
			return "ReplaceAdd Scripts,*,*,*";
		}
		if(name.equals("Replace webProxy")){
			return "Replace webProxy,*,*,*";
		}
		if(name.equals("Replace DDC")){
			String w0 = HagUtil.getWord0(parm,",",0,true);
			return "Replace ddc,"+w0+",*,*";
		}
		if(name.equals("Replace IOM")){
			String w0 = HagUtil.getWord0(parm,",",0,true);
			return "Replace IOM,"+w0+",*,*";
		}
		if(name.equals("Copy migMng.jar")){	
			return "Copy migMng.jar,*,*,*";
		}
		
		return ans;
	}
	public String 	requstsHandle(String cfgMenuWebUser,HttpServletRequest request, HttpServletResponse response){

		String [] cbEnvs	= request.getParameterValues("cb");
		String noteInst = request.getParameter( "noteInst" );
		String verInst = request.getParameter( "verInst" );
		if (cbEnvs == null || cbEnvs.length == 0) {
			return "Please select at least one environment";
		}
		String cbList="";
		HagStringList selectedEnvsList = new HagStringList();
		if (cbEnvs != null && cbEnvs.length != 0) {
			for (int i = 0; i < cbEnvs.length; i++) {
				cbList = cbList + "<br>" + cbEnvs[i];
				selectedEnvsList.add(cbEnvs[i]);
			}
		}



		//String 			to 		= "david.ha@sapiens.com";
		//String 			from 	= "cfgMenuWeb@sapiens.com";
		//String cfgMenuWebUser1 = request.getParameter( "cfgMenuWebUser" );
		int ind = HagUtil.getRequestInd();
		String indS = HagUtil.padNumLeft(""+ind, 5, '0');
			
	

		String into ="";
		boolean rc=true;
		for(int i = 0 ; i < selectedEnvsList.size();i++){
			StringBuilder 	content = new StringBuilder("Request to install RiLogic: ").append("<br>");
			content.append("<table bgColor =\"#aaaaaa\" border=\"1\"  cellpadding=\"10\">");
			content.append("<tr><td>Version to install:</td><td>").append(verInst).append("</td></tr>");
			content.append("<tr><td>Environments list:</td><td>");
			
			String temp = selectedEnvsList.get(i);
			if(temp.trim().length()<1)
				continue;
			String appServer = HagUtil.getWord0(temp, "~", 0,true);
			String batchName = HagUtil.getWord0(temp, "~", 1,true);
			into=into+appServer+"("+batchName+") ";
			String lastGoodInst = HagUtil.getWord0(temp, "~", 3,true);
			//if(!HagUtil.checkVerToInst(lastGoodInst,verInst))
			//	wrong.add("<tr><td>"+appServer+"</td><td>"+batchName+"</td><td>"+lastGoodInst+"</td><td>"+verInst+"</td></tr>");
			content.append(appServer).append("-").append(batchName).append("(").append(lastGoodInst).append(")<br>");
			//String 			subject = "#Request to install RiLogic "+verInst+" @BREAK-REQ@ #"+indS+"# on "+appServer+"("+batchName+") "+" sent by "+cfgMenuWebUser;
			String 			subject = "#Request to install RiLogic "+verInst+" on "+appServer+"("+batchName+") - @BREAK-REQ@ #"+indS+"# "+"sent by "+cfgMenuWebUser;
			
			if(!HagUtil.addRequest(ind,cfgMenuWebUser, ".",subject,appServer+"/"+batchName,".",".",null,null,null,null,999))
				return HagUtil.shortHtml("Error adding request-mail sent to hagay & gonen, please call hagay or gonen", "red","bg");
		
			
			String mailList = "david.ha@sapiens.com;gonen.s@sapiens.com;david.b@sapiens.com";
			String ccList 	= HagUtil.getRiMails("all");
			content.append("</td></tr>");
			content.append("<tr><td>Note:</td><td>").append(noteInst).append("</td></tr>");		
			content.append("<tr><td>Requested by:</td><td>").append(cfgMenuWebUser).append("</td></tr>");		
			content.append("<tr><td>Sent to:</td><td>").append(mailList.replace(";","<br>")).append("</td></tr>");	
			content.append("<tr><td>CC:</td><td>").append(ccList.replace(";","<br>")).append("</td></tr>");
			content.append("</table>");

			//String ans1		= HagUtil.sendMail_hag(from,to,"david.ha@sapiens.com",subject,content.toString());


			String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+cfgMenuWebUser,HagUtil.mailList_hag,subject,content.toString());

			if(!ans1.startsWith("0~"))		
				rc=false;
		
		
		}
		if(rc)		
			return HagUtil.shortHtml("Request sent:<BR><br> "+into , "green", "bg");
		else
			return HagUtil.shortHtml("Failed to send request" , "red", "bg");

	
	}
	
	
	/*
	 public String 	requstsHandle(String cfgMenuWebUser,HttpServletRequest request, HttpServletResponse response){

		String [] cbEnvs	= request.getParameterValues("cb");
		String noteInst = request.getParameter( "noteInst" );
		String verInst = request.getParameter( "verInst" );
		if (cbEnvs == null || cbEnvs.length == 0) {
			return "Please select at least one environment";
		}
		String cbList="";
		HagStringList selectedEnvsList = new HagStringList();
		if (cbEnvs != null && cbEnvs.length != 0) {
			for (int i = 0; i < cbEnvs.length; i++) {
				cbList = cbList + "<br>" + cbEnvs[i];
				selectedEnvsList.add(cbEnvs[i]);
			}
		}



		//String 			to 		= "david.ha@sapiens.com";
		//String 			from 	= "cfgMenuWeb@sapiens.com";
		//String cfgMenuWebUser1 = request.getParameter( "cfgMenuWebUser" );
		int ind = HagUtil.getRequestInd();
		String indS = HagUtil.padNumLeft(""+ind, 5, '0');
			
		StringBuilder 	content = new StringBuilder("Request to install RiLogic: ").append("<br>");
		content.append("<table bgColor =\"#aaaaaa\" border=\"1\"  cellpadding=\"10\">");
		content.append("<tr><td>Version to install:</td><td>").append(verInst).append("</td></tr>");
		content.append("<tr><td>Environments list:</td><td>");
		HagStringList 	wrong =new HagStringList();
		String into ="";
		for(int i = 0 ; i < selectedEnvsList.size();i++){
			String temp = selectedEnvsList.get(i);
			if(temp.trim().length()<1)
				continue;
			String appServer = HagUtil.getWord0(temp, "~", 0,true);
			String batchName = HagUtil.getWord0(temp, "~", 1,true);
			into=into+appServer+"("+batchName+") ";
			String lastGoodInst = HagUtil.getWord0(temp, "~", 3,true);
			//if(!HagUtil.checkVerToInst(lastGoodInst,verInst))
			//	wrong.add("<tr><td>"+appServer+"</td><td>"+batchName+"</td><td>"+lastGoodInst+"</td><td>"+verInst+"</td></tr>");
			content.append(appServer).append("-").append(batchName).append("(").append(lastGoodInst).append(")<br>");
		}
		String 			subject = "#Request to install RiLogic "+verInst+" @BREAK-REQ@ #"+indS+"# on "+into+" sent by "+cfgMenuWebUser;
		HagUtil.addRequest(ind,cfgMenuWebUser, ".",subject,"./.");
	
		if(wrong.size()>0){
			StringBuilder wrongMsg = new StringBuilder("Error:<BR><br> Installed version is newer than the requested version.<br><table bgColor =\"#ffffff\" border=\"1\" cellpadding=\"10\">")
			.append("<tr bgColor =\"#dddddd\"><td>appServer</td><td>batchName</td><td>lastGoodInst</td><td>requested installation</td></tr>")
			.append(wrong.convertToString(" ")).append("</table>");
			return HagUtil.shortHtml(wrongMsg.toString(), "red", "bg");
		}
		String mailList = "david.ha@sapiens.com;gonen.s@sapiens.com;david.b@sapiens.com";
		String ccList 	= HagUtil.getRiMails("all");
		content.append("</td></tr>");
		content.append("<tr><td>Note:</td><td>").append(noteInst).append("</td></tr>");		
		content.append("<tr><td>Requested by:</td><td>").append(cfgMenuWebUser).append("</td></tr>");		
		content.append("<tr><td>Sent to:</td><td>").append(mailList.replace(";","<br>")).append("</td></tr>");	
		content.append("<tr><td>CC:</td><td>").append(ccList.replace(";","<br>")).append("</td></tr>");
		content.append("</table>");

		//String ans1		= HagUtil.sendMail_hag(from,to,"david.ha@sapiens.com",subject,content.toString());


		String ans1		= HagUtil.sendMail_hag(HagUtil.cfgTeamMail,mailList,ccList,subject,content.toString());

		if(ans1.startsWith("0~"))		
			return HagUtil.shortHtml("Request sent:<BR><br> "+content.toString() , "green", "bg");
		else
			return HagUtil.shortHtml("Failed to send request" , "red", "bg");

	}
	 */
	public String 	requstsHandleReplaceDbFromBackup(String cfgMenuWebUser,HttpServletRequest request, HttpServletResponse response){

		String [] cbEnvs	= request.getParameterValues("cb");
		String noteInst = request.getParameter( "noteInst" );
		String backupLocation = request.getParameter( "backupLocation" );
		String verInst = request.getParameter( "verInst" );
		String sqlVersion = request.getParameter( "sqlVersion" );
		String riVersion = request.getParameter( "riVersion" );
		
		
		if (cbEnvs == null || cbEnvs.length == 0) {
			return "Please select at least one environment";
		}
		//String backupLocationOrg = backupLocation;
		/*
		if(backupLocation.startsWith("ftp://RI-")){
			String w0 = HagUtil.getWord0(backupLocation, "@", 0, true);
			String w1 = HagUtil.getWord0(backupLocation, "@", 1, true);
			String w0b= HagUtil.getWord0(w0, "-", 1, true);
			String w0c= HagUtil.getWord0(w0b, ":", 0, true);
			String w1b= HagUtil.getWord0(w1, "/", 1, true);
			backupLocation="m:\\customers_data\\RI-"+w0c+"\\"+w1b;
			backupLocation=HagUtil.replaceStr(backupLocation, "/","\\");
			
			
		}
		File backupLocationF = new File(backupLocation);
		if(!backupLocationF.exists()){
			 return HagUtil.shortHtml("Failed to find backup at "+ backupLocationOrg, "red", "bg");
		}
		*/
		 String cbList="";
		HagStringList selectedEnvsList = new HagStringList();
		if (cbEnvs != null && cbEnvs.length != 0) {
			for (int i = 0; i < cbEnvs.length; i++) {
				cbList = cbList + "<br>" + cbEnvs[i];
				selectedEnvsList.add(cbEnvs[i]);
			}
		}

		String note2 = ".";
		if (noteInst!=null && noteInst.trim().length()>0)
			note2=noteInst;

		//String 			to 		= "david.ha@sapiens.com";
		//String 			from 	= "cfgMenuWeb@sapiens.com";
		//String cfgMenuWebUser1 = request.getParameter( "cfgMenuWebUser" );
	
			
		StringBuilder 	content = new StringBuilder("Request to replace DB from backup: ").append("<br>");
		content.append("<table bgColor =\"#aaaaaa\" border=\"1\"  cellpadding=\"10\">");
		content.append("<tr><td>Version to install:</td><td>").append(verInst).append("</td></tr>");
		content.append("<tr><td>Environments list:</td><td>");
	
		String into ="";
		String subject ="";
		boolean rc=true;
		for(int i = 0 ; i < selectedEnvsList.size();i++){
			int ind = HagUtil.getRequestInd();
			String indS = HagUtil.padNumLeft(""+ind, 5, '0');
			
			String temp = selectedEnvsList.get(i);
			if(temp.trim().length()<1)
				continue;
			String appServer = HagUtil.getWord0(temp, "~", 0,true);
			String batchName = HagUtil.getWord0(temp, "~", 1,true);
			into=into+appServer+"("+batchName+") ";
			String lastGoodInst = HagUtil.getWord0(temp, "~", 3,true);
			//if(!HagUtil.checkVerToInst(lastGoodInst,verInst))
			//	wrong.add("<tr><td>"+appServer+"</td><td>"+batchName+"</td><td>"+lastGoodInst+"</td><td>"+verInst+"</td></tr>");
			content.append(appServer).append("-").append(batchName).append("(").append(lastGoodInst).append(")<br>");
			
			subject = "#Request to replace DB from backup "+verInst+" @BREAK-REQ@ #"+indS+"# on "+appServer+"("+batchName+") "+ " sent by "+cfgMenuWebUser;
			String backupLocation1 = ".";
			if(backupLocation!=null && backupLocation.trim().length()>0)
				backupLocation1=backupLocation;
			if(!HagUtil.addRequest(ind,cfgMenuWebUser, note2,subject,appServer+"/"+batchName,".",backupLocation1,null,null,null,null,999))
				return HagUtil.shortHtml("Error adding request-mail sent to hagay & gonen, please call hagay or gonen", "red","bg");
			
			String mailList = "david.ha@sapiens.com;gonen.s@sapiens.com;david.b@sapiens.com";
			String ccList 	= HagUtil.getRiMails("all");
			//String mailList = "david.ha@sapiens.com";
			//	String ccList 	= "david.ha@sapiens.com";
		
			content.append("</td></tr>");
			content.append("<tr><td>Note:</td><td>").append(noteInst).append("</td></tr>");		
			content.append("<tr><td>Backup location:</td><td>").append(backupLocation).append("</td></tr>");
		//	content.append("<tr><td>Backup location(org):</td><td>").append(backupLocationOrg).append("</td></tr>");	
			content.append("<tr><td>SQL version of the backup file:</td><td>").append(sqlVersion).append("</td></tr>");	
			content.append("<tr><td>RI version of the backup file:</td><td>").append(riVersion).append("</td></tr>");	
			content.append("<tr><td>Requested by:</td><td>").append(cfgMenuWebUser).append("</td></tr>");		
			content.append("<tr><td>Sent to:</td><td>").append(mailList.replace(";","<br>")).append("</td></tr>");	
			content.append("<tr><td>CC:</td><td>").append(ccList.replace(";","<br>")).append("</td></tr>");
			content.append("</table>");
			String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+cfgMenuWebUser,HagUtil.mailList_hag,subject,content.toString());

			if(!ans1.startsWith("0~"))		
				rc=false;
		}
		
		if(rc)		
			return HagUtil.shortHtml("Request sent:<BR><br> "+content.toString() , "green", "bg");
		else
			return HagUtil.shortHtml("Failed to send request" , "red", "bg");
	
	
	

		//String ans1		= HagUtil.sendMail_hag(from,to,"david.ha@sapiens.com",subject,content.toString());

		//mailList = "david.ha@sapiens.com;david.ha@sapiens.com";
		//ccList 	= "david.ha@sapiens.com;david.ha@sapiens.com";

	

	}
	public String 	dspTomcatStatus(HagStringList cbEnvs){

		StringBuilder ans =new StringBuilder();
		ans.append("<table border=\"1\"  cellpadding=\"10\">");
		ans.append("<tr bgColor =\"#aaaaaa\"><td>SQL server</td><td>Database</td><td>DB size</td><td>Logsize</td></tr>");
		for(int i = 0;i<cbEnvs.size();i++){
			HagStringList ans1 = new HagStringList();
			String temp = cbEnvs.get(i);
			String db = HagUtil.getWord0(temp,"~",12,true);
			String sqlServer = HagUtil.getWord0(temp,"~",11,true);
			String rc= HagJdbc.getDbSize("SQL",sqlServer,"cfg","cfg09c",db, ans1);
			//			ans.append("<table bgColor=\"#00ff00\"><tr><td>").append(w3).append("-").append(w2).append("</td></tr></table><br>");
			String aa = ans1.convertToString(" ");
			String bb = HagUtil.replaceStr(aa,"<tr>","<tr><td>"+sqlServer+"</td>");
			ans.append(bb );

		}
		ans.append("</table>");
		return ans.toString();
	}
	public String 	setOdedKeyWin(HagStringList cbEnvs){
		String file = 	HagUtil.cfgMenuWebLoc+"\\lists\\oded.txt" ;
		HagStringList odedList = new HagStringList ( file,true,"*",false);
		StringBuilder ans =new StringBuilder();
		//	ans.append("<table border=\"1\"  cellpadding=\"10\">");c

		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"setOdedsKeyWin.jsp\">");
		ans.append("<table  bgcolor = \"#E6E6FA\" border = 1 CELLPADDING=3 style=\"white-space:nowrap;\">");
		ans.append("<tr bgcolor = \"#c4c4d8\">");
		ans.append("<td>APP Server</td>");
		ans.append("<td>batchName</td>");
		ans.append("<td>Customer</td>");
		ans.append("<td>RI version</td>");
		ans.append("<td>RI patch</td>");
		ans.append("<td>Oded's key</td>"); 
		ans.append("</tr>");
		String aaa = "STYLE=\"color: #888888; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #E6E6FA;\" size=\"10\" maxlength=\"30\"";
		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			HagStringList list = new HagStringList(temp,"~",true);

			ans.append("<tr>");
			ans.append("<td><input "+aaa+" type=\"text\" name=\"odedKey1\" value=\""+list.get(1)+"\"    size=\"15\"></td>");
			ans.append("<td><input  "+aaa+" type=\"text\" name=\"odedKey2\" value=\""+list.get(2)+"\"   size=\"15\"></td>");
			ans.append("<td><input  "+aaa+" type=\"text\" name=\"odedKey3\" value=\""+list.get(3)+"\"   size=\"15\"></td>");
			ans.append("<td><input  "+aaa+" type=\"text\" name=\"odedKey6\" value=\""+list.get(6)+"\"    size=\"15\"></td>");
			ans.append("<td><input  "+aaa+" type=\"text\" name=\"odedKey7\" value=\""+list.get(7)+"\"    size=\"15\"></td>");
			
			//ans.append("<td><input type=\"text\" name=\"odedKey3\" value=\""+list.get(8)+"\"  size=\"15\"></td>"); 
			ans.append("<td><select class=\"c30\" id=\"odedKey8\" name =\"odedKey8\" >");
			for(int k = 0 ; k < odedList.size();k++){
				String temp1 = odedList.get(k);
				String w1 = HagUtil.getWord0(temp1, "|", 0,true);
				String w2 = HagUtil.getWord0(temp1, "|", 1,true);
				String zzz = "&nbsp;&nbsp;&nbsp;&nbsp;";
				if(w1.length()==4)
					zzz=zzz+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				if(w1.length()==3)
					zzz=zzz+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				if(w1.equals(list.get(8).trim()))
					ans.append("<option class=\"c30\" value=\""+w1+"\" selected>"+w1+zzz+"("+w2+") </option>");
				else
					ans.append("<option class=\"c30\" value=\""+w1+"\">"+w1+zzz+"("+w2+")</option>");
			}
			ans.append("</tr>");
		}
		ans.append("</table>");
		ans.append("<br><INPUT TYPE=SUBMIT value=\"Submit\"></FORM>");
		return ans.toString();

	}
	
	public String 	setOwnerBefore(HagStringList cbEnvs){
		//String file = 	HagUtil.cfgMenuWebLoc+"\\lists\\users.txt" ;
		HagSQL hagSQL = new HagSQL();
		String stm = "select * from dbo.hagCfgMenuWebUsers";
		HagStringList usersList1 =hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,6,"|",null,null);
		
		
	//	HagStringList usersListAll = new HagStringList ( file,true,"*",false);
		HagStringList usersList = new HagStringList ();
		usersList.add("CM");
		for(int i=2;i< usersList1.size();i++) {
			String temp = usersList1.get(i);
			String w1 = HagUtil.getWord0(temp,"|",0,true);
			usersList.add(w1);
		}
		StringBuilder ans =new StringBuilder();
		//	ans.append("<table border=\"1\"  cellpadding=\"10\">");c

		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"setOwner.jsp\">");
		ans.append("<table  bgcolor = \"#E6E6FA\" border = 1 CELLPADDING=3 style=\"white-space:nowrap;\">");
		ans.append("<tr bgcolor = \"#c4c4d8\">");
		ans.append("<td>APP Server</td>");
		ans.append("<td>batchName</td>");
		ans.append("<td>Customer</td>");
		ans.append("<td>RI version</td>");
		ans.append("<td>RI patch</td>");
		ans.append("<td>Owner</td>"); 
		ans.append("</tr>");
		String aaa = "STYLE=\"color: #888888; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #E6E6FA;\" size=\"10\" maxlength=\"30\"";
		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			HagStringList list = new HagStringList(temp,"~",true);

			ans.append("<tr>");
			ans.append("<td><input "+aaa+" type=\"text\" name=\"key1\" value=\""+list.get(1)+"\"    size=\"15\"></td>");
			ans.append("<td><input  "+aaa+" type=\"text\" name=\"key2\" value=\""+list.get(2)+"\"   size=\"15\"></td>");
			ans.append("<td><input  "+aaa+" type=\"text\" name=\"key3\" value=\""+list.get(3)+"\"   size=\"15\"></td>");
			ans.append("<td><input  "+aaa+" type=\"text\" name=\"key6\" value=\""+list.get(6)+"\"    size=\"15\"></td>");
			ans.append("<td><input  "+aaa+" type=\"text\" name=\"key7\" value=\""+list.get(7)+"\"    size=\"15\"></td>");
			String own = list.get(12);

			//ans.append("<td><input type=\"text\" name=\"odedKey3\" value=\""+list.get(8)+"\"  size=\"15\"></td>"); 
			ans.append("<td><select class=\"c30\" id=\"key12\" name =\"key12\" >");
			for(int k = 0 ; k < usersList.size();k++){
				String temp1 = usersList.get(k);
				if(temp1.equalsIgnoreCase(own))
					ans.append("<option class=\"c30\" value=\""+temp1+"\" selected>"+temp1+"</option>");
				else
					ans.append("<option class=\"c30\" value=\""+temp1+"\" >"+temp1+"</option>");
			}
			ans.append("</tr>");
		}
		ans.append("</table>");
		ans.append("<br><INPUT TYPE=SUBMIT value=\"Submit\"></FORM>");
		return ans.toString();

	}
	
	public String 	setNoteField(HagStringList cbEnvs){
		StringBuilder ans =new StringBuilder();
		
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"setNoteField.jsp\">");
		ans.append("<table  bgcolor = \"#E6E6FA\" border = 1 CELLPADDING=3 style=\"white-space:nowrap;\">");
		ans.append("<tr bgcolor = \"#c4c4d8\">");
		ans.append("<td>APP Server</td>");
		ans.append("<td>batchName</td>");
		ans.append("<td>Customer</td>");
		ans.append("<td>RI version</td>");
		ans.append("<td>RI patch</td>");
		ans.append("<td>Note (up to 50 characters)</td>"); 
		ans.append("</tr>");
		String aaa = "STYLE=\"color: #888888; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #E6E6FA;\" size=\"10\" maxlength=\"30\"";
		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			HagStringList list = new HagStringList(temp,"~",true);

			ans.append("<tr>");
			ans.append("<td><input "+aaa+" type=\"text\" name=\"note1\" value=\""+list.get(1)+"\"    size=\"15\"></td>");
			ans.append("<td><input  "+aaa+" type=\"text\" name=\"note2\" value=\""+list.get(2)+"\"   size=\"15\"></td>");
			ans.append("<td><input  "+aaa+" type=\"text\" name=\"note3\" value=\""+list.get(3)+"\"   size=\"15\"></td>");
			ans.append("<td><input  "+aaa+" type=\"text\" name=\"note6\" value=\""+list.get(6)+"\"    size=\"15\"></td>");
			ans.append("<td><input  "+aaa+" type=\"text\" name=\"note7\" value=\""+list.get(7)+"\"    size=\"15\"></td>");
			ans.append("<td bgcolor = \"#ffffff\"><input  type=\"text\" name=\"note18\" value=\""+list.get(19)+"\"    size=\"50\"></td>");
			
			ans.append("</tr>");
		}
		ans.append("</table>");
		ans.append("<br><INPUT TYPE=SUBMIT value=\"Submit\"></FORM>");
		return ans.toString();

	}
	
	public String 	runSqlOnEnvs(HagStringList cbEnvs,String type){
		StringBuilder ans =new StringBuilder();
		if(type.equals("SELECT"))
			ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"runSqlStmOnEnvsSelect.jsp\">");
		else
			ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"runSqlStmOnEnvsUpdate.jsp\">");
		ans.append("<table  bgcolor = \"#E6E6FA\" border = 1 CELLPADDING=3 style=\"white-space:nowrap;\">");
		ans.append("<tr bgcolor = \"#c4c4d8\">");
		ans.append("<td>APP Server</td>");
		ans.append("<td>batchName</td>");
		ans.append("<td>SQL Server</td>");
		ans.append("<td>DB name</td>");
		ans.append("<td>SQL stm</td>"); 
		ans.append("</tr>");
		String aaa = "STYLE=\"color: #888888; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #E6E6FA;\" size=\"10\" maxlength=\"30\"";
		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			HagStringList list = new HagStringList(temp,"~",true);
			ans.append("<tr>");
			ans.append("<td><input "+aaa+" type=\"text\" name=\"odedKey1\" value=\""+list.get(1)+"\"    size=\"15\"></td>");
			ans.append("<td><input  "+aaa+" type=\"text\" name=\"odedKey2\" value=\""+list.get(2)+"\"   size=\"15\"></td>");
			ans.append("<td><input "+aaa+" type=\"text\" name=\"odedKey10\" value=\""+list.get(10)+"\"    size=\"15\"></td>");
			ans.append("<td><input  "+aaa+" type=\"text\" name=\"odedKey11\" value=\""+list.get(11)+"\"   size=\"15\"></td>");
			ans.append("<td><textarea rows=4 cols=50 id=\"sqlStm\" name =\"sqlStm\" ></textarea></td>");
			ans.append("</tr>");
		}
		ans.append("</table>");
		ans.append("<br><INPUT TYPE=SUBMIT value=\"Submit\"></FORM>");
		return ans.toString();
		
		
		

	}
	public String 	runVb_after(HttpServletRequest request, HttpServletResponse response){
		String user 	= request.getParameter("user");
		String [] bisServers 	= request.getParameterValues("bisServers");
		String [] batchNames 	= request.getParameterValues("batchNames");
	
		String vbAct 	= request.getParameter("vbAct");
	HagStringList list =new HagStringList();
	
		for(int i = 0 ; i <bisServers.length;i++) {
			//if(i>0)
			//	list=list+",";	
			list.add(bisServers[i]+"/"+batchNames[i]);
		}
	//	return user+"~"+vbAct+"~"+list;
			return runVbCmd(vbAct,list);
			
	}
	public String 	runVb_before(HagStringList cbEnvs,String user){
		
	//	if(cbEnvs.size()>1)
		//	return HagUtil.shortHtml("only one env (checkBox) can be selected for this option", "red","bg");
		//String temp = cbEnvs.get(0);
		//String bisServer = HagUtil.getWord0(temp,"~",1,true);
		//String batchName = HagUtil.getWord0(temp,"~",2,true);
		
		
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
	//	ans.add("var ua = window.navigator.userAgent;");
	//	ans.add("document.getElementById(\"myBrowser\").value = ua;");
		ans.add("}");
		ans.add("</script></head>	");
		
		ans.add("<body onload=\"refreshIFrame();getBrowser()\">");
		
		
	
		ans.add("<h3>run vb-script on ri-environment</h3>");
		ans.add("<FORM METHOD=POST name=\"Form\" ACTION=\"runVb.jsp\">");
		
		ans.add("<table bgcolor=\"#aaaacc\" border=\"1\" >");
		ans.add("<tr>");
		ans.add("<td>");
		ans.add("bis_server");
		ans.add("</td>");
		ans.add("<td>");
		ans.add("batch_name");
		ans.add("</td>");
		
		ans.add("</tr>");

for(int i = 0 ;i < cbEnvs.size();i++) {
	String temp = cbEnvs.get(i);
	String bisServer = HagUtil.getWord0(temp,"~",1,true);
	String batchName = HagUtil.getWord0(temp,"~",2,true);
		ans.add("<tr bgcolor=\"#ccccff\" border=\"1\">");
		ans.add("<td>");
		ans.add("<input type=\"text\" name=\"bisServers\" value=\""+bisServer+"\" readonly >"); 
		ans.add("</td>");
		ans.add("<td>");
		ans.add("<input type=\"text\" name=\"batchNames\" value=\""+batchName+"\" readonly >"); 
		ans.add("</td>");

		ans.add("</tr>");
}
		ans.add("</table>");
		ans.add("<br><br>");
		
		ans.add("<select  class=\"c30\" id=\"vbAct\" name =\"vbAct\" >");
		HagStringList actList= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\vbActions.list",true,"*",false);
		for(int i=0;i<actList.size();i++) {
			String opt = actList.get(i);
			String w0 = HagUtil.getWord0(opt,"|",0,true);
			String w1 = HagUtil.getWord0(opt,"|",1,true);
			ans.add("<option  value=\""+w0+"\">"+w0+"-"+w1+"</option>");
		}
		ans.add("</select>");
		
		ans.add("<input type=\"hidden\" name=\"user\" value=\""+user+"\"  >"); 
		ans.add("<br><br>");
		ans.add("<br><INPUT TYPE=SUBMIT value=\"run VB-script\" onclick=\"document.getElementById('loader').style.display = ''; this.style.display = 'none'\" ></FORM>");
		
		ans.add("<img id=\"loader\" src=\"Please_wait.gif\" style=\"display: none;\"/>");
		return  ans.convertToString("");
		

	}
	/*
	////////////////
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
		custSuort.append("None");
		sb.append("None");
	}
	sb.append("</td></tr>");
	
	//String ccList 	= HagUtil.getRiMails(jiraVer);
	String ccList 	= sentBy+"@sapiens.com";
//get ind 
	HagSQL hagSQL = new HagSQL();
	String stm = "select lowInd  from dbo.add_mig_ind where type=1";
	if(runType.equals("after_metaData"))
		stm = "select highnd  from dbo.add_mig_ind where type=1" ;
				
	ArrayList<String> ans = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm);
	int ind = HagUtil.s2i(ans.get(0));
	int nextInd = ind+1;
	String stm1 = "update dbo.add_mig_ind set lowInd ="+nextInd+" where type=1";
	
	if(runType.equals("after_metaData"))
		stm1 = "update dbo.add_mig_ind set highInd ="+nextInd+" where type=1";
	
	int changed = hagSQL.updateStm("confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm1);
		
	
	sb.append("<tr><td bgcolor=\"#ccccaa\">ID</td><td>").append(ind).append("</td></tr>");
	sb.append("<tr><td bgcolor=\"#ccccaa\">ID-INC</td><td>").append(changed).append("</td></tr>");
	sb.append("</table></body></html>");
	
	
	String subject = "#Request to add mig into "+jiraVer+" bank, @BREAK-REQ@  sent by "+sentBy;
	String ans1		= HagUtil.sendMail_hag(HagUtil.cfgTeamMail,"david.b@sapiens.com;stanislav.p@sapiens.com;david.ha@sapiens.com;gonen.s@sapiens.com",ccList,subject,sb.toString());

	HagUtil.insertIntoMigHf(jClass, jiraTask, jiraVer, custSuort.toString(), HagUtil.getWord0(runType, "|",0,true),location,dist, sentBy,ind, note);
	
	
	
	return sb.toString();	
	/////////
	*/
	
	public String 	runSqlOnEnvsMigLoadDone(HagStringList cbEnvs,String sentBy){
		StringBuilder ans =new StringBuilder();
		ans.append("<table border=\"1\"  cellpadding=\"10\">");
		ans.append("<tr bgColor =\"#aaaaaa\"><td>Version</td><td>Dev</td><td>DateTime-Dev</td><td>runType</td><td>Location</td><td>Distribute</td><td>jClass</td><td>Id</td><td>Cm</td><td>rc</td><td>Added line</td></tr>");

		String ccList 	= "david.ha@sapiens.com";
		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			String ver = HagUtil.getWord0(temp,"~",1,true);
			String dev = HagUtil.getWord0(temp,"~",6,true);
			String dt_dev = HagUtil.getWord0(temp,"~",7,true);
			String runType = HagUtil.getWord0(temp,"~",4,true);
			String location = HagUtil.getWord0(temp,"~",12,true);
			String dist = HagUtil.getWord0(temp,"~",13,true);
			String jClass = HagUtil.getWord0(temp,"~",3,true);
			String indS = HagUtil.getWord0(temp,"~",2,true);
			String runType1 = "";
			String runType2 = runType;
			if(!runType.equals("."))
				runType1=","+runType;
			else
				runType2 = "Major / Update version";
				
			
			int ind = HagUtil.s2i(indS);
			String dateTime = HagUtil.getDateTime("yyyy/MM/dd-HH:mm");
			StringBuilder stm3= new StringBuilder("Update "+HagParam.getConfg1DB()+".[dbo].[add_mig] ")
					.append("set cm='"+sentBy+"',dt_cm='"+dateTime+"' where ")
					.append("jclass='").append(jClass).append("' and ")
					.append("ver='").append(ver).append("' and ")
					.append("dev='").append(dev).append("' and ")
					.append("dt_dev='").append(dt_dev).append("' and ")
					.append("id=").append(ind);
			String stm3S = stm3.toString();
			HagSQL hagSQL = new HagSQL();
			int changed = hagSQL.updateStm("confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm3S);
			//int changed =-1;
			ans.append("<tr>");
			ans.append("<td>").append(ver).append("</td>");
			ans.append("<td>").append(dev).append("</td>");
			ans.append("<td>").append(dt_dev).append("</td>");
			ans.append("<td>").append(runType2).append("</td>");
			ans.append("<td>").append(location).append("</td>");
			ans.append("<td>").append(dist).append("</td>");
			ans.append("<td>").append(jClass).append("</td>");
			ans.append("<td>").append(indS).append("</td>");
			ans.append("<td>").append(sentBy).append("</td>");
			ans.append("<td>").append(changed).append("</td>");
			ans.append("<td>").append(indS+","+indS+",com.sapiens.mig.ri.v050m00."+jClass+runType1+"</td>");
			ans.append("</tr>");
			ccList=ccList+";"+dev+"@sapiens.com";

		}
		
		ans.append("</table>");
		String subject = "#Mig load file committed to svn, @BREAK-REQ@  sent by "+sentBy;
	
		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+sentBy,HagUtil.mailList_hag,subject,ans.toString());

		return ans.toString();
		
		

	}
	public String 	runSqlOnEnvsMigLoadDone1(HagStringList cbEnvs,String sentBy){
		StringBuilder ans =new StringBuilder();
		ans.append("<table border=\"1\"  cellpadding=\"10\">");
		ans.append("<tr bgColor =\"#aaaaaa\"><td>Version</td><td>Dev</td><td>DateTime-Dev</td><td>runType</td><td>Location</td><td>Distribute</td><td>jClass</td><td>Id</td><td>Cm</td><td>rc</td><td>Added line</td></tr>");

		String ccList 	= "david.ha@sapiens.com";
		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			String ver = HagUtil.getWord0(temp,"~",1,true);
			String dev = HagUtil.getWord0(temp,"~",5,true);
			String dt_dev = HagUtil.getWord0(temp,"~",6,true);
			String runType = HagUtil.getWord0(temp,"~",3,true);
			String location = HagUtil.getWord0(temp,"~",11,true);
			String dist = HagUtil.getWord0(temp,"~",12,true);
			String jClass = HagUtil.getWord0(temp,"~",13,true);
			String indS = HagUtil.getWord0(temp,"~",14,true);
			String runType1 = "";
			String runType2 = runType;
			if(!runType.equals("."))
				runType1=","+runType;
			else
				runType2 = "Major / Update version";
				
			
			int ind = HagUtil.s2i(indS);
			String dateTime = HagUtil.getDateTime("yyyy/MM/dd-HH:mm");
			StringBuilder stm3= new StringBuilder("Update "+HagParam.getConfg1DB()+".[dbo].[add_mig] ")
					.append("set cm='"+sentBy+"',dt_cm='"+dateTime+"' where ")
					.append("jclass='").append(jClass).append("' and ")
					.append("ver='").append(ver).append("' and ")
					.append("dev='").append(dev).append("' and ")
					.append("dt_dev='").append(dt_dev).append("' and ")
					.append("id=").append(ind);
			String stm3S = stm3.toString();
			HagSQL hagSQL = new HagSQL();
			int changed = hagSQL.updateStm("confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm3S);
			//int changed =-1;
			ans.append("<tr>");
			ans.append("<td>").append(ver).append("</td>");
			ans.append("<td>").append(dev).append("</td>");
			ans.append("<td>").append(dt_dev).append("</td>");
			ans.append("<td>").append(runType2).append("</td>");
			ans.append("<td>").append(location).append("</td>");
			ans.append("<td>").append(dist).append("</td>");
			ans.append("<td>").append(jClass).append("</td>");
			ans.append("<td>").append(indS).append("</td>");
			ans.append("<td>").append(sentBy).append("</td>");
			ans.append("<td>").append(changed).append("</td>");
			ans.append("<td>").append(indS+","+indS+",com.sapiens.mig.ri.v050m00."+jClass+runType1+"</td>");
			ans.append("</tr>");
			ccList=ccList+";"+dev+"@sapiens.com";

		}
		
		ans.append("</table>");
		String subject = "#Mig load file committed to svn, @BREAK-REQ@  sent by "+sentBy;
	
		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+sentBy,HagUtil.mailList_hag,subject,ans.toString());

		return ans.toString();
		
		

	}
	public HagStringList	sort(HagStringList list){
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
	public String getAllBackups(){
		HagStringList ansList = HagUtil.getFilesInDir("\\\\ri-archive\\Saptech-Archive\\RI\\PTH_beforeHF");
		HagStringList ansList1 = new HagStringList();
		for(int i =0;i<ansList.size();i++ ){
			String temp = ansList.get(i);
			if(temp.indexOf("\\Befinst_")>0 && !temp.endsWith(".zip") && !temp.endsWith(".bk"))
				ansList1.add(temp);
		}
		String ans = 	ansList1.convertToString("#");
		return ans;
	}
	public String 	requestHotfixNew(HagStringList cbEnvs1,String sentBy){

	String listBackUpsS=getAllBackups();

	StringBuilder ans =new StringBuilder();
		//	ans.append("<table border=\"1\"  cellpadding=\"10\">");c
		//ans.append("<head><SCRIPT language=\"javascript\">");
		//ans.append("function checkExistingBackups(ver,maint,upd,fh,listOfBk){");
		//ans.append("var rst = \"\";");
		//ans.append("var dirsList = listOfBk.value;");
		//ans.append("var dirs = dirsList.split(\"#\");");
		//ans.append("var str = String.concat(\"Befinst_\", ver.value,\".\", maint.value,\".\", upd.value,\".\", fh.value);");
		//ans.append("for (i = 0; i < dirs.length; i++) {");
		//	ans.append("var temp = dirs[i];");
			//ans.append("window.alert(temp);");
			//ans.append("if(temp.indexOf(str)>0){");
				//ans.append("rst= String.concat(rst,\"\n\",temp);");
			//ans.append("}");
	//	ans.append(" }");			
		//ans.append("var fs = require('fs')");
		//ans.append("var files = fs.readdirSync('//ri-archive/Saptech-Archive/RI/PTH_beforeHF'");
		//ans.append("window.alert(\"aaaa\")");
		//ans.append("window.alert(files)");
		//ans.append("for (i = 0; i < files.length; i++) {");
	//	ans.append("window.alert(files[i])");
         //     if (fs.statSync(path.join(dir, file)).isDirectory()) {
          //        filelist = walkSync(path.join(dir, file), filelist);
           //   }
           //   else {
           //       filelist.push(path.join(dir, file));
            //  }
		//ans.append("window.alert(\"+rst+\")");
		//ans.append("}");
		
		//ans.append("</SCRIPT></head>");
		
		String bkupF = HagUtil.cfgMenuWebLoc+"\\getBackups.html";
		HagStringList bkupL = new HagStringList(bkupF,false,"xxssss",false);
		String bkupS = bkupL.convertToString("\n");
		ans.append(bkupS);
		ans.append("<h3>Pack hotfix request</h3>");

		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"sendHotfixRequestNew.jsp\">");
	
		ans.append("<table bgcolor=\"#aaaacc\" border=\"1\">");
		ans.append("<tr bgcolor=\"#aaaaaa\"><td>Version</td><td>Maintenance</td><td>Update</td><td>Hotfix</td><td>Platform</td><td>sent by</td><td>Accumulative</td><td>Final pack</td></tr>");
		ans.append("<tr>");
		
		//ver
		ans.append("<td><select  class=\"c30\" id=\"ver\" name =\"ver\" >");
		HagStringList ver1= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\riVersions.txt",true,"*",false);
		for(int i=0;i<ver1.size();i++)
			ans.append("<option  value=\"").append(ver1.get(i)).append("\">").append(ver1.get(i)).append("</option>");
		ans.append("</select></td>");
		
		//maint
		ans.append("<td><select  class=\"c30\" id=\"maint\" name =\"maint\" >");
		HagStringList maint1= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\riNum2.txt",true,"*",false);
		for(int i=0;i<maint1.size();i++)
			ans.append("<option  value=\"").append(maint1.get(i)).append("\">").append(maint1.get(i)).append("</option>");
		ans.append("</select></td>");
		
		//update
		ans.append("<td><select  class=\"c30\" id=\"update\" name =\"update\" >");
		HagStringList update1= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\riNum2.txt",true,"*",false);
		for(int i=0;i<update1.size();i++)
			ans.append("<option  value=\"").append(update1.get(i)).append("\">").append(update1.get(i)).append("</option>");
		ans.append("</select></td>");
		
		//hotfix
		ans.append("<td><select  class=\"c30\" id=\"hotfix\" name =\"hotfix\" >");
		HagStringList hotfix1= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\riNum2.txt",true,"*",false);
		for(int i=0;i<hotfix1.size();i++)
			ans.append("<option  value=\"").append(hotfix1.get(i)).append("\">").append(update1.get(i)).append("</option>");
		ans.append("</select></td>");
	
		//platform
		ans.append("<td><select  class=\"c30\" id=\"platform\" name =\"platform\" >");
		//ans.append("<option  value=\"").append("I5OS Only").append("\">").append("I5OS Only").append("</option>");
		ans.append("<option  value=\"").append("WIN").append("\">").append("WIN").append("</option>");
		ans.append("<option  value=\"").append("I5OS").append("\">").append("I5OS").append("</option>");
		//ans.append("<option  value=\"").append("SQL&I5OS").append("\">").append("SQL&I5OS").append("</option>");
		ans.append("</select></td>");
		//sentby
		ans.append("<td><select name=\"sentBy\"><option value=\"").append(sentBy).append("\">").append(sentBy).append("</option></select></td>");	

		//Accumulative
		ans.append("<td><select  class=\"c30\" id=\"Accumulative\" name =\"Accumulative\" >");
		ans.append("<option  value=\"").append("YES").append("\">").append("YES").append("</option>");
		ans.append("<option  value=\"").append("NO").append("\">").append("NO").append("</option>");
		ans.append("</select></td>");
		//final
		ans.append("<td><select  class=\"c30\" id=\"Final\" name =\"Final\" >");
		ans.append("<option  value=\"").append("NO").append("\">").append("NO").append("</option>");
		ans.append("<option  value=\"").append("YES").append("\">").append("YES").append("</option>");
		ans.append("</select></td>");
		
		ans.append("</tr></table><br>");
		
		
		
		//note +dest installation
		
		ans.append("<table  bgcolor = \"#E6E6FA\" border = 1 CELLPADDING=3 style=\"white-space:nowrap;\">");
		ans.append("<tr><td>Note</td><td><input type=\"text\" name=\"note\" value=\"\"  size=\"150\"></td></tr>"); 
		//ans.append("<tr><td>Install on</td><td><input type=\"text\" name=\"instOn\" value=\"\"  size=\"150\"></td></tr>"); 
		ans.append("</table><br>");	

		//backup before
		ans.append("<input type=\"hidden\" name=\"listOfBk\" value=\""+listBackUpsS+"\"  >"); 
		
		ans.append("<br>Backup environmet before installation and keep for future restore:</br>");
		ans.append("<table  bgcolor = \"#E6E6FA\" border = 1 CELLPADDING=3 style=\"white-space:nowrap;\">");
		ans.append("<tr><td><button onclick=\"checkExistingBackups(ver,maint,update,hotfix,listOfBk)\">Check existing backups</button></td><td>add environments</td><td><input type=\"text\" name=\"addToBkup\" value=\"\"  size=\"150\"></td></tr>"); 
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
			ans.append("<option class=\"c30\" value=\"U\" >UpdateOrInsert only</option>");//not supported in i5os 
			//ans.append("<option class=\"c30\" value=\"D\" >Delete only</option>");
			//ans.append("<option class=\"c30\" value=\"DUR\" >Delete+Insert+Run</option>");
			//ans.append("<option class=\"c30\" value=\"DU\" >Delete+Insert only</option>");
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
		ans.append("<tr bgcolor = \"#E6E6FA\"><td>Type</td><td>Parameters</td><td>Note</td></tr>");
		//war
		ans.append("<tr bgcolor = \"#E6E6FA\"><td>Replace war:");
		ans.append("<td><select class=\"c30\" id=\"replaceWarAndJasper\" name =\"replaceWarAndJasper\" >");
		ans.append("<option class=\"c30\" value=\"NO\">NO</option>");
		ans.append("<option class=\"c30\" value=\"YES\">YES</option>");
		ans.append("</select>");
		ans.append("</td><td>replace War and Jasper</td><tr>");
		//jar
		String file = 	HagUtil.cfgMenuWebLoc+"\\lists\\jarSnapshots.txt" ;
		HagStringList jarsList = new HagStringList ( file,true,"*",false);
		ans.append("<tr bgcolor = \"#E6E6FA\"><td>Replace jar:");
		ans.append("<td><select class=\"c30\" id=\"replaceJar\" name =\"replaceJar\" >");
		ans.append("<option class=\"c30\" value=\"NO\">NO</option>");
		ans.append("<option class=\"c30\" value=\"f_t_t_a_t_r\">From the TextField at the right</option>");
		
		for(int i = 0 ; i < jarsList.size();i++){
			String name = jarsList.get(i);
			ans.append("<option class=\"c30\" value=\""+name+"\">"+name+"</option>");
		}
		ans.append("</select>");
		ans.append("&nbsp;&nbsp;<input type=\"text\" name=\"replaceJarParms\"   size=\"25\">"); 
		ans.append("</td><td>replace Jar snapshot file</td><tr>");
		//ddc
		ans.append("<tr bgcolor = \"#E6E6FA\"><td>Replace ddc:");
		ans.append("<td>");
		ans.append("<select class=\"c30\" id=\"replaceDdc\" name =\"replaceDdc\" >");
		ans.append("<option class=\"c30\" value=\"NO\">NO</option>");
		ans.append("<option class=\"c30\" value=\"YES\">YES</option>");
		ans.append("</select>");
		ans.append("&nbsp;&nbsp;<input type=\"text\" name=\"replaceDdcParms\" value=\"RI35-APP(CF)\"  size=\"25\">"); 
		ans.append("</td><td>replace DDC from QA environmnet for example: RI35-APP(CF)</td><tr>");
		//scripts
		ans.append("<tr bgcolor = \"#E6E6FA\"><td>Replace/add scripts:");
		ans.append("<td>");
		ans.append("<select class=\"c30\" id=\"replaceScripts\" name =\"replaceScripts\" >");
		ans.append("<option class=\"c30\" value=\"NO\">NO</option>");
		ans.append("<option class=\"c30\" value=\"YES\">YES</option>");
		ans.append("</select>");
		ans.append("&nbsp;&nbsp;<input type=\"text\" name=\"replaceScriptsParms\" value=\"test1.js,test2.js\"  size=\"25\">"); 
		ans.append("</td><td>replace scripts will take the scripts folder you prepared in the TOPACK-hotfix-folder<br>the names you wrote is for documentation only</td><tr>");
		//iom
		ans.append("<tr bgcolor = \"#E6E6FA\"><td>Replace IOM:");
		ans.append("<td>");
		ans.append("<select class=\"c30\" id=\"replaceIom\" name =\"replaceIom\" >");
		ans.append("<option class=\"c30\" value=\"NO\">NO</option>");
		ans.append("<option class=\"c30\" value=\"YES\">YES</option>");
		ans.append("</select>");
		ans.append("&nbsp;&nbsp;<input type=\"text\" name=\"replaceIomParms\" value=\"RI30011,RI30012\"  size=\"25\">"); 
		ans.append("&nbsp;&nbsp;order&nbsp;<select class=\"c30\" id=\"orderIom\" name =\"orderIom\" >");
		for(int k = 0 ; k < 10;k++)
			ans.append("<option class=\"c30\" value=\""+k+"\" >"+k+"</option>");
		ans.append("<option class=\"c30\" value=\"10\" selected>10</option>");
		ans.append("</select>");
		ans.append("&nbsp;&nbsp;from env:&nbsp;<input type=\"text\" name=\"iomFromEnv\" value=\"R0\"  size=\"5\">"); 
		ans.append("&nbsp;&nbsp;from server:&nbsp;<input type=\"text\" name=\"iomFromServer\" value=\"RIDEVBLP06\"  size=\"15\">"); 
		ans.append("</td><td>replace IOM for example: RI30011,RI30012 <br> select order like in migs lines</td><tr>");

	
		//gl
		ans.append("<tr bgcolor = \"#E6E6FA\"><td>NNRe GL interface:");
		ans.append("<td>");
		ans.append("<select class=\"c30\" id=\"GL-Interface\" name =\"GL-Interface\" >");
		ans.append("<option class=\"c30\" value=\"NO\">NO</option>");
		ans.append("<option class=\"c30\" value=\"YES\">YES</option>");
		ans.append("</select>");
		ans.append("</td><td>pack GL interface for NNRe</td><tr>");
		
		//webProxy
		ans.append("<tr bgcolor = \"#E6E6FA\"><td>replaceWebProxy:");
		ans.append("<td>");
		ans.append("<select class=\"c30\" id=\"replaceWebProxy\" name =\"replaceWebProxy\" >");
		ans.append("<option class=\"c30\" value=\"NO\">NO</option>");
		ans.append("<option class=\"c30\" value=\"YES\">YES</option>");
		ans.append("</select>");
		ans.append("</td><td>copy webProxy folder to this hotfix</td><tr>");	
		
		//eMerge client SP03
		ans.append("<tr bgcolor = \"#E6E6FA\"><td>upgrade to eMergeClientSP03U02:");
		ans.append("<td>");
		ans.append("<select class=\"c30\" id=\"eMergeClientSP3\" name =\"eMergeClientSP3\" >");
		ans.append("<option class=\"c30\" value=\"NO\">NO</option>");
		ans.append("<option class=\"c30\" value=\"YES\">YES</option>");
		ans.append("</select>");
		ans.append("</td><td>upgrade to eMergeClientSP03U02</td><tr>");

		
		ans.append("</table>");
		//INSTALL ON
		ans.append("<br>install on:");
		ans.append("<br><table  bgcolor = \"#dddddd\" border = 1 CELLPADDING=3 style=\"white-space:nowrap;\">");
		ans.append("<tr bgcolor = \"#E6E6FA\"><td>APP_Server</td><td>dbid's with , as delimiter.(for example:AB,GA,CF) </td></tr>");
	//	HagStringList appServersList = new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\appServersList.txt",false,"xxssss",false);
	
		HagStringList appServersList =HagUtil.getAppServersList("WIN",true,true);
		//appServersList.add("TERANO");
		//appServersList.add("PASSAT");
		
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
		
		
		ans.append("<br><br><INPUT TYPE=SUBMIT value=\"Send request\"></FORM>");
		return ans.toString();

	}
	
	
	
	public String 		sendPackClientModuleRequest(HttpServletRequest request, HttpServletResponse response){
		
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
//		String ver2=ver.substring(2,4);
//		String package2 = ver1+"."+ver2+"M"+maint+"U"+update+"HF"+hotfix; 

//		HagRc hagRcCheck = new HagRc();
//		if(hf1!=null){
//			checkMigsList(hagRcCheck,hf1,hf2,hf3,hf4,hf5,package2);
//			if(hagRcCheck.rc!=0){
//				String printLog = hagRcCheck.log.convertToString("<br>");
//				return printLog;
//			}
//		}
		
		if (instOn2==null || instOn2.trim().length()==0)
			instOn2=".";
		if (note==null || note.trim().length()==0)
			note=".";
		
		contentM.add("Request to create ClientModule package<br>");
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
		StringBuilder stm3= new StringBuilder("Update "+HagParam.getConfg1DB()+".[dbo].[add_mig_clientModule] ")
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
		contentM.add("<tr><td bgColor=\"#cccccc\">War</td><td>cm-srv.war</td></tr>");
		contentM.add("<tr><td bgColor=\"#cccccc\">Scripts</td><td>ri-cm.zip</td></tr>");
		ans.add("<tr><td bgColor=\"#cccccc\">War</td><td>cm-srv.war</td></tr>");
		ans.add("<tr><td bgColor=\"#cccccc\">Scripts</td><td>ri-cm.zip</td></tr>");
		ans.add("</table><br>");
		contentM.add(ans.get(ans.size()-1));
		

		
		
		
		
		
		
		ans1.append("<br>Content:");
			
			if(migsAndIom.size()>0)
				ans1.append("<br>Manual mig");
			ans1.append("<br>cm-srv.war");
			ans1.append("<br>ri-cm.zip");
			
			
			ans1.append("<br><br>Actions:");
		
			if(migsAndIom.size()>0)
				ans1.append("<br>Run manual migs");
			ans1.append("<br>Replace cm-srv.war");
			ans1.append("<br>Replace ri-cm scripts");
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
		folder = sendClientModuleRequest_prepPackage( hagRc,ver,   migD,toDoList);
		
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
				ans.add(changed+" rows in ClientModule table change to "+ver+" by "+sentBy+"<br>");
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
		String subject 	= pre+"Request to create ClientModule "+ver+" package @BREAK-REQ@ #"+indS+"# sent by "+sentBy;
		if(!HagUtil.addRequest(ind,sentBy, ".",subject,"./.",".",".",instOn2,null,null,null,999))
			return HagUtil.shortHtml("Error adding request-mail sent to hagay & gonen, please call hagay or gonen", "red","bg");
		String ansS = ans.convertToString("");
		 String ans9=HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+sentBy,HagUtil.mailList_hag,subject,ansS+"<br>"+ans1.toString());
		return ansS+"<br>"+ans9+"<br>"+ans1.toString();
	}
	
	
	
	
	
	
	
	public String 	requestClientModulePack(HagStringList cbEnvs1,String sentBy){
		
		String ansCheck = HagUtil.checkTOPACK("\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V0801m00\\TOPACK\\ClientModule\\CMv1.1\\",HagUtil.cfgMenuWebLoc+"\\lists\\clientmoduleFiles.txt");
		if (ansCheck != null)
			return ansCheck;
		
		
		
		
		String listBackUpsS=getAllBackups();
		StringBuilder ans =new StringBuilder();

		String bkupF = HagUtil.cfgMenuWebLoc+"\\getBackups.html";
			ans.append("<h3>Pack ClientModule request</h3>");

			ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"sendPackClientModuleRequest.jsp\">");
		
			ans.append("<table bgcolor=\"#aaaacc\" border=\"1\">");
			ans.append("<tr bgcolor=\"#aaaaaa\"><td>Version</td><td>sent by</td></tr>");
			ans.append("<tr>");
			
			//ver
			String clientModuleVer = "";
			String riVer = "";
			ans.append("<td><select  class=\"c30\" id=\"ver\" name =\"ver\" >");
			HagStringList ver1= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\clientModuleVersions.txt",true,"*",false);
			for(int i=0;i<ver1.size();i++) {
				String clientModuleVerLine = ver1.get(i);
				clientModuleVer = HagUtil.getWord0(clientModuleVerLine,"|", 0,true);
				riVer = HagUtil.getWord0(clientModuleVerLine,"|", 1,true);
				ans.append("<option  value=\"").append(clientModuleVer).append("\">").append(clientModuleVer).append("</option>");
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
				int preferdId=HagUtil.getPreferdId(list.get(2));
				for(int k = 0 ; k < 10;k++){
					if(k==preferdId)
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
			String location = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V"+riVer+"\\TOPACK\\ClientModule\\CM"+clientModuleVer.substring(clientModuleVer.indexOf("_")+1,clientModuleVer.length());
			
			
			//jar
			ans.append("<tr bgcolor = \"#E6E6FA\"><td>cm-mng.jar:</td>");
			ans.append("<td>replace cm-mng.jar from "+location+"\\cm-mng.jar</td>");
			File cmMigF = new File(location+"\\cm-mng.jar");
			if(!cmMigF.exists() || !cmMigF.isFile()) {
				error[0]=1;
				ans.append("<td bgcolor = \"#ff4444\">File not found</td><tr>");
			}else {
				String cmMigFlastModified =  HagUtil.getFileLastModified(cmMigF, "dd/MM/yyyy-HH:ss");
				ans.append("<td>"+cmMigFlastModified+"</td><tr>");
			}

			//war
			ans.append("<tr bgcolor = \"#E6E6FA\"><td>cm-srv.war:</td>");
			ans.append("<td>replace cm-srv.war from "+location+"\\cm-srv.war</td>");
			File cmWarF = new File(location+"\\cm-srv.war");
			if(!cmWarF.exists() || !cmWarF.isFile()) {
				error[0]=1;
				ans.append("<td bgcolor = \"#ff4444\">File not found</td><tr>");
			}else {
				String cmWarFLasttModified =  HagUtil.getFileLastModified(cmWarF, "dd/MM/yyyy-HH:ss");
				ans.append("<td>"+cmWarFLasttModified+"</td><tr>");
			}

			
			//scripts
			ans.append("<tr bgcolor = \"#E6E6FA\"><td>ri-cm.zip:</td>");
			ans.append("<td>replace ri-cm.zip from "+location+"\\ri-cm.zip</td>");
			File cmZipF = new File(location+"\\ri-cm.zip");
			if(!cmZipF.exists() || !cmZipF.isFile()) {
				error[0]=1;
				ans.append("<td bgcolor = \"#ff4444\">File not found</td><tr>");
			}else {
				String cmZipFlastModified =  HagUtil.getFileLastModified(cmMigF, "dd/MM/yyyy-HH:ss");
				ans.append("<td>"+cmZipFlastModified+"</td><tr>");
			}
			
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
			
			if(error[0] ==1 || error[1] ==1 )
				ans.append("<br><br><font color = \"#ff4444\">cannot send the request - you need to fix the errors</font></FORM>");
			else
				ans.append("<br><br><INPUT TYPE=SUBMIT value=\"Send request\"></FORM>");
			return ans.toString();

		}
		
	public String 	requestClientModuleRunMig(HagStringList cbEnvs1,String sentBy){
		
		if(cbEnvs1==null || cbEnvs1.size()==0)
			return HagUtil.shortHtml("You must select at least one mig","red","bg");

		String style4 = "STYLE=\"color: #888888; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #E6E6FA;\" size=\"4\" maxlength=\"80\"";
		String style8 = "STYLE=\"color: #888888; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #E6E6FA;\" size=\"8\" maxlength=\"80\"";
		String style10 = "STYLE=\"color: #888888; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #E6E6FA;\" size=\"10\" maxlength=\"80\"";
		String style12 = "STYLE=\"color: #888888; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #E6E6FA;\" size=\"12\" maxlength=\"80\"";
		String style15 = "STYLE=\"color: #888888; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #E6E6FA;\" size=\"15\" maxlength=\"80\"";
		String style16 = "STYLE=\"color: #888888; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #E6E6FA;\" size=\"17\" maxlength=\"80\"";
		

		StringBuilder ans =new StringBuilder();
		HagStringList cbEnvsSorted = sort(cbEnvs1);
		
		ans.append("<h3>Run ClientModule mig/s on one environment</h3>");

		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"runClientModuleMigs.jsp\">");

		//ver
		//ans.append("<table bgcolor=\"#aaaacc\" border=\"1\">");
		String clientModuleVer = "";
	//	String riVer = "";
	//	ans.append("<td><select  class=\"c30\" id=\"ver\" name =\"ver\" >");
	//	HagStringList ver1= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\clientModuleVersions.txt",true,"*",false);
		//for(int i=0;i<ver1.size();i++) {
		//	String clientModuleVerLine = ver1.get(i);
		//	clientModuleVer = HagUtil.getWord0(clientModuleVerLine,"|", 0,true);
		//	riVer = HagUtil.getWord0(clientModuleVerLine,"|", 1,true);
		//	ans.append("<option  value=\"").append(clientModuleVer).append("\">").append(clientModuleVer).append("</option>");
	//	}
	//	ans.append("</select></td>");
		
	//	//sentby
		//ans.append("<td><select name=\"sentBy\"><option value=\"").append(sentBy).append("\">").append(sentBy).append("</option></select></td>");	
	//	ans.append("</table>");
		
		
		//migs
		StringBuilder ans2 =new StringBuilder();
		ans2.append("<br>mig/s:<BR>");

		ans2.append("<table bgcolor=\"#aaaacc\" border=\"1\">");
		clientModuleVer = "init";
		for(int i = 0;i<cbEnvsSorted.size();i++){
			
				String temp = cbEnvsSorted.get(i);
				HagStringList list = new HagStringList(temp,"~",true);
				if(i==0) {
					clientModuleVer = list.get(1);
				}else {
					if(!clientModuleVer.equals(list.get(1)) )
						return HagUtil.shortHtml("all migs must be from the same clientModule version", "red","bg");	
					//else
					//	return HagUtil.shortHtml("only one env (checkBox) can be selected for this option", "green","bg");
				}
				ans2.append("<tr>");
				ans2.append("<td><select class=\"c30\" id=\"order\" name =\"order\" >");
				for(int k = 0 ; k < 10;k++){
					if(k==5)
						ans2.append("<option class=\"c30\" value=\""+k+"\" selected>"+k+"</option>");
					else
						ans2.append("<option class=\"c30\" value=\""+k+"\" >"+k+"</option>");
				}
				ans2.append("</select></td>");
			
				ans2.append("<td><select class=\"c30\" id=\"runOrUpdate\" name =\"runOrUpdate\" >");
				ans2.append("<option class=\"c30\" value=\"UR\" selected>UpdateOrInsert+Run</option>");
				ans2.append("<option class=\"c30\" value=\"U\" >UpdateOrInsert only</option>");
				ans2.append("<option class=\"c30\" value=\"D\" >Delete only</option>");
				ans2.append("<option class=\"c30\" value=\"DUR\" >Delete+Insert+Run</option>");
				ans2.append("<option class=\"c30\" value=\"DU\" >Delete+Insert only</option>");
				ans2.append("</select></td>");

				
				
				//ans.append("<td><input type=\"text\" name=\"odedKey8\" value=\""+list.get(8)+"\"  size=\"15\"></td>"); 
				
				ans2.append("<td><input  "+style12+" type=\"text\" name=\"hf1\" value=\""+list.get(1)+"\"    size=\"20\"></td>");
				ans2.append("<td><input  "+style10+" type=\"text\" name=\"hf2\" value=\""+list.get(3)+"\"    size=\"15\"></td>");
				ans2.append("<td><input  "+style8+" type=\"text\"  name=\"hf3\" value=\""+list.get(2)+"\"    size=\"15\"></td>");
				ans2.append("<td><input  "+style10+" type=\"text\" name=\"hf4\" value=\"{ALL}\"   size=\"15\"></td>");
				ans2.append("<td><input  "+style4+" type=\"text\"  name=\"hf5\" value=\""+list.get(4)+"\"   ></td>");
				ans2.append("<td><input  "+style10+" type=\"text\" name=\"hf6\" value=\""+list.get(5)+"\"    size=\"25\"></td>");
				ans2.append("<td><input  "+style10+" type=\"text\" name=\"hf7\" value=\""+list.get(6)+"\"    size=\"25\"></td>");
				ans2.append("<td><input  "+style16+" type=\"text\" name=\"hf8\" value=\""+list.get(7)+"\"    size=\"15\"></td>");
				ans2.append("<td><input  "+style10+" type=\"text\" name=\"hf9\" value=\""+list.get(8)+"\"    size=\"15\"></td>");
				ans2.append("<td><input  "+style16+" type=\"text\" name=\"hf10\" value=\""+list.get(9)+"\"    size=\"15\"></td>");
				ans2.append("<td><input  "+style15+" type=\"text\" name=\"hf11\" value=\""+list.get(12)+"\"    size=\"15\"></td>");
				ans2.append("<td><input  "+style15+" type=\"text\" name=\"hf12\" value=\"this_ver_only\"    ></td>");
				ans2.append("</tr>");
			}
			ans2.append("</table><br>");

			//ans1
			StringBuilder ans1 =new StringBuilder();
			ans1.append("<br>global parameters:");
			ans1.append("<br><table  bgcolor = \"#dddddd\" border = 1 CELLPADDING=3 style=\"white-space:nowrap;\">");
			ans1.append("<tr><td>Run by</td><td>").append(sentBy).append("</td></tr>");
			ans1.append("<tr><td>ClientModule version</td><td>").append(clientModuleVer).append("</td></tr>");
			String riVer1 = getRiVerFromClientModuleVer(clientModuleVer);
			if(riVer1==null)
				return HagUtil.shortHtml("failed to get Ri version of "+clientModuleVer+" clientModule version.", "red","bg");	
			ans1.append("<tr><td>Ri version</td><td>").append(riVer1).append("</td></tr>");
			String location = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V"+riVer1+"\\TOPACK\\ClientModule\\CM"+clientModuleVer.substring(clientModuleVer.indexOf("_")+1,clientModuleVer.length());
			ans1.append("<tr><td>TOPACK location(for the cm-mig.jar)</td><td>").append(location).append("</td></tr>");
			ans1.append("</table><br>");
			
			
			
			
			/*
			ans.append("other components:");
			ans.append("<br><table  bgcolor = \"#dddddd\" border = 1 CELLPADDING=3 style=\"white-space:nowrap;\">");
			ans.append("<tr bgcolor = \"#c4c4d8\"><td>Which component</td><td>What to do</td><td>Last modified </td></tr>");
			int[] error = {0,0};
			String location = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V"+riVer+"\\TOPACK\\ClientModule\\CM"+clientModuleVer.substring(clientModuleVer.indexOf("_")+1,clientModuleVer.length());
			
			
			//jar
			ans.append("<tr bgcolor = \"#E6E6FA\"><td>cm-mng.jar:</td>");
			ans.append("<td>replace cm-mng.jar from "+location+"\\cm-mng.jar</td>");
			File cmMigF = new File(location+"\\cm-mng.jar");
			if(!cmMigF.exists() || !cmMigF.isFile()) {
				error[0]=1;
				ans.append("<td bgcolor = \"#ff4444\">File not found</td><tr>");
			}else {
				String cmMigFlastModified =  HagUtil.getFileLastModified(cmMigF, "dd/MM/yyyy-HH:ss");
				ans.append("<td>"+cmMigFlastModified+"</td><tr>");
			}

			//war
			ans.append("<tr bgcolor = \"#E6E6FA\"><td>cm-srv.war:</td>");
			ans.append("<td>replace cm-srv.war from "+location+"\\cm-srv.war</td>");
			File cmWarF = new File(location+"\\cm-srv.war");
			if(!cmWarF.exists() || !cmWarF.isFile()) {
				error[0]=1;
				ans.append("<td bgcolor = \"#ff4444\">File not found</td><tr>");
			}else {
				String cmWarFLasttModified =  HagUtil.getFileLastModified(cmWarF, "dd/MM/yyyy-HH:ss");
				ans.append("<td>"+cmWarFLasttModified+"</td><tr>");
			}

			
			//scripts
			ans.append("<tr bgcolor = \"#E6E6FA\"><td>ri-cm.zip:</td>");
			ans.append("<td>replace ri-cm.zip from "+location+"\\ri-cm.zip</td>");
			File cmZipF = new File(location+"\\ri-cm.zip");
			if(!cmZipF.exists() || !cmZipF.isFile()) {
				error[0]=1;
				ans.append("<td bgcolor = \"#ff4444\">File not found</td><tr>");
			}else {
				String cmZipFlastModified =  HagUtil.getFileLastModified(cmMigF, "dd/MM/yyyy-HH:ss");
				ans.append("<td>"+cmZipFlastModified+"</td><tr>");
			}
			
			ans.append("</table>");
			*/
			
			
			//ans3
			StringBuilder ans3 =new StringBuilder();
			ans3.append("<br>install on:<BR>");
			String from = HagUtil.getEnvLine("from","RI93QA","GL");
			ans3.append("<table  bgcolor = \"#dddddd\" border = 1 CELLPADDING=4 style=\"white-space:nowrap;\"><tr><td>");
			ans3.append(from);
			ans3.append("</td></tr></table><br>");
			/*
			ans3.append("<br><table  bgcolor = \"#dddddd\" border = 1 CELLPADDING=3 style=\"white-space:nowrap;\">");
			ans3.append("<tr bgcolor = \"#c4c4d8\"><td>APP_Server</td><td>dbid's with , as delimiter.(for example:AB,GA,CF) </td></tr>");
			HagStringList appServersList = new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\appServersList.txt",false,"xxssss",false);
			String[] installOnId = {"installOn0","installOn1","installOn2","installOn3","installOn4","installOn5","installOn6","installOn7","installOn8","installOn9"};
			String[] installOnDb = {"installDb0","installDb1","installDb2","installDb3","installDb4","installDb5","installDb6","installDb7","installDb8","installDb9"};
			for(int kk=0;kk<10;kk++) {
				ans3.append("<tr bgcolor = \"#E6E6FA\">");
				ans3.append("<td><select class=\"c30\" id=\""+installOnId[kk]+"\" name =\""+installOnId[kk]+"\" >");
				ans3.append("<option class=\"c30\" value=\"\"></option>");
				for(int kkk=0;kkk<appServersList.size();kkk++) {
						String tempServer =appServersList.get(kkk).trim(); 
					
						ans3.append("<option class=\"c30\" value=\""+tempServer+"\">"+tempServer+"</option>");
				}
				ans3.append("</select></td>");
				ans3.append("<td><input type=\"text\" name=\""+installOnDb[kk]+"\" value=\"\"  size=\"65\"></td>"); 
				ans3.append("</tr>");
			}
			ans3.append("</table>");
	*/
			ans.append(ans1.toString()).append(ans2.toString()).append(ans3.toString());
			//if(error[0] ==1 || error[1] ==1 )
			//	ans.append("<br><br><font color = \"#ff4444\">cannot send the request - you need to fix the errors</font></FORM>");
			//else
				ans.append("<br><br><INPUT TYPE=SUBMIT value=\"Run mig/s\"></FORM>");
			return ans.toString();

		}
	public String  getRiVerFromClientModuleVer(String clientModuleVer) {
		HagStringList ver1= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\clientModuleVersions.txt",true,"*",false);
		
		for(int i=0;i<ver1.size();i++) {
			String clientModuleVerLine = ver1.get(i);
			String clientModuleVer1 = HagUtil.getWord0(clientModuleVerLine,"|", 0,true);
			if( clientModuleVer1.equals(clientModuleVer))
				return  HagUtil.getWord0(clientModuleVerLine,"|", 1,true);
		}
		return null;
	}
	public String 	getScriptsLines(String scriptsFS){
		StringBuilder ans = new StringBuilder();
		HagStringList files = HagUtil.getFilesInDir(scriptsFS);
		for(int i = 0 ; i < files.size();i++) {
			String temp = files.get(i);
			File ff = new File(scriptsFS+temp);
			String scriptsFlastModified =  HagUtil.getFileLastModified(ff, "dd/MM/yyyy-HH:ss");
			ans.append("<tr><td></td><td align=\"right\">").append(temp).append("</td><td>").append(scriptsFlastModified).append("</td></tr>");
		}
		return ans.toString();
	}
	
	public String 	requestGardNew(HagStringList cbEnvs1,String sentBy){
		StringBuilder ans =new StringBuilder();
			ans.append("<h3>Pack hotfix request</h3>");

			ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"sendHotfixRequestNew.jsp\">");
		
			ans.append("<table bgcolor=\"#aaaacc\" border=\"1\">");
			ans.append("<tr bgcolor=\"#aaaaaa\"><td>Version</td><td>Maintenance</td><td>Update</td><td>Hotfix</td><td>Platform</td><td>sent by</td><td>Accumulative</td><td>Final pack</td></tr>");
			ans.append("<tr>");
			
			//ver
			ans.append("<td><select  class=\"c30\" id=\"ver\" name =\"ver\" >");
			HagStringList ver1= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\riVersions.txt",true,"*",false);
			for(int i=0;i<ver1.size();i++)
				ans.append("<option  value=\"").append(ver1.get(i)).append("\">").append(ver1.get(i)).append("</option>");
			ans.append("</select></td>");
			
			//maint
			ans.append("<td><select  class=\"c30\" id=\"maint\" name =\"maint\" >");
			HagStringList maint1= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\riNum2.txt",true,"*",false);
			for(int i=0;i<maint1.size();i++)
				ans.append("<option  value=\"").append(maint1.get(i)).append("\">").append(maint1.get(i)).append("</option>");
			ans.append("</select></td>");
			
			//update
			ans.append("<td><select  class=\"c30\" id=\"update\" name =\"update\" >");
			HagStringList update1= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\riNum2.txt",true,"*",false);
			for(int i=0;i<update1.size();i++)
				ans.append("<option  value=\"").append(update1.get(i)).append("\">").append(update1.get(i)).append("</option>");
			ans.append("</select></td>");
			
			//hotfix
			ans.append("<td><select  class=\"c30\" id=\"hotfix\" name =\"hotfix\" >");
			HagStringList hotfix1= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\riNum2.txt",true,"*",false);
			for(int i=0;i<hotfix1.size();i++)
				ans.append("<option  value=\"").append(hotfix1.get(i)).append("\">").append(update1.get(i)).append("</option>");
			ans.append("</select></td>");
		
			//platform
			ans.append("<td><select  class=\"c30\" id=\"platform\" name =\"platform\" >");
			//ans.append("<option  value=\"").append("I5OS Only").append("\">").append("I5OS Only").append("</option>");
			ans.append("<option  value=\"").append("WIN").append("\">").append("WIN").append("</option>");
			ans.append("<option  value=\"").append("I5OS").append("\">").append("I5OS").append("</option>");
			//ans.append("<option  value=\"").append("SQL&I5OS").append("\">").append("SQL&I5OS").append("</option>");
			ans.append("</select></td>");
			//sentby
			ans.append("<td><select name=\"sentBy\"><option value=\"").append(sentBy).append("\">").append(sentBy).append("</option></select></td>");	

			//Accumulative
			ans.append("<td><select  class=\"c30\" id=\"Accumulative\" name =\"Accumulative\" >");
			ans.append("<option  value=\"").append("YES").append("\">").append("YES").append("</option>");
			ans.append("<option  value=\"").append("NO").append("\">").append("NO").append("</option>");
			ans.append("</select></td>");
			//final
			ans.append("<td><select  class=\"c30\" id=\"Final\" name =\"Final\" >");
			ans.append("<option  value=\"").append("NO").append("\">").append("NO").append("</option>");
			ans.append("<option  value=\"").append("YES").append("\">").append("YES").append("</option>");
			ans.append("</select></td>");
			
			ans.append("</tr></table><br>");
			
			
			
			//note +dest installation
			ans.append("<table  bgcolor = \"#E6E6FA\" border = 1 CELLPADDING=3 style=\"white-space:nowrap;\">");
			ans.append("<tr><td>Note</td><td><input type=\"text\" name=\"note\" value=\"\"  size=\"150\"></td></tr>"); 
			//ans.append("<tr><td>Install on</td><td><input type=\"text\" name=\"instOn\" value=\"\"  size=\"150\"></td></tr>"); 
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
			ans.append("<tr bgcolor = \"#E6E6FA\"><td>Type</td><td>Parameters</td><td>Note</td></tr>");
			//war
			ans.append("<tr bgcolor = \"#E6E6FA\"><td>Replace war:");
			ans.append("<td><select class=\"c30\" id=\"replaceWarAndJasper\" name =\"replaceWarAndJasper\" >");
			ans.append("<option class=\"c30\" value=\"NO\">NO</option>");
			ans.append("<option class=\"c30\" value=\"YES\">YES</option>");
			ans.append("</select>");
			ans.append("</td><td>replace War and Jasper</td><tr>");
			//jar
			String file = 	HagUtil.cfgMenuWebLoc+"\\lists\\jarSnapshots.txt" ;
			HagStringList jarsList = new HagStringList ( file,true,"*",false);
			ans.append("<tr bgcolor = \"#E6E6FA\"><td>Replace jar:");
			ans.append("<td><select class=\"c30\" id=\"replaceJar\" name =\"replaceJar\" >");
			ans.append("<option class=\"c30\" value=\"NO\">NO</option>");
			ans.append("<option class=\"c30\" value=\"f_t_t_a_t_r\">From the TextField at the right</option>");
			
			for(int i = 0 ; i < jarsList.size();i++){
				String name = jarsList.get(i);
				ans.append("<option class=\"c30\" value=\""+name+"\">"+name+"</option>");
			}
			ans.append("</select>");
			ans.append("&nbsp;&nbsp;<input type=\"text\" name=\"replaceJarParms\"   size=\"25\">"); 
			ans.append("</td><td>replace Jar snapshot file</td><tr>");
			//ddc
			ans.append("<tr bgcolor = \"#E6E6FA\"><td>Replace ddc:");
			ans.append("<td>");
			ans.append("<select class=\"c30\" id=\"replaceDdc\" name =\"replaceDdc\" >");
			ans.append("<option class=\"c30\" value=\"NO\">NO</option>");
			ans.append("<option class=\"c30\" value=\"YES\">YES</option>");
			ans.append("</select>");
			ans.append("&nbsp;&nbsp;<input type=\"text\" name=\"replaceDdcParms\" value=\"RI35-APP(CF)\"  size=\"25\">"); 
			ans.append("</td><td>replace DDC from QA environmnet for example: RI35-APP(CF)</td><tr>");
			//scripts
			ans.append("<tr bgcolor = \"#E6E6FA\"><td>Replace/add scripts:");
			ans.append("<td>");
			ans.append("<select class=\"c30\" id=\"replaceScripts\" name =\"replaceScripts\" >");
			ans.append("<option class=\"c30\" value=\"NO\">NO</option>");
			ans.append("<option class=\"c30\" value=\"YES\">YES</option>");
			ans.append("</select>");
			ans.append("&nbsp;&nbsp;<input type=\"text\" name=\"replaceScriptsParms\" value=\"test1.js,test2.js\"  size=\"25\">"); 
			ans.append("</td><td>replace scripts will take the scripts folder you prepared in the TOPACK-hotfix-folder<br>the names you wrote is for documentation only</td><tr>");
			//iom
			ans.append("<tr bgcolor = \"#E6E6FA\"><td>Replace IOM:");
			ans.append("<td>");
			ans.append("<select class=\"c30\" id=\"replaceIom\" name =\"replaceIom\" >");
			ans.append("<option class=\"c30\" value=\"NO\">NO</option>");
			ans.append("<option class=\"c30\" value=\"YES\">YES</option>");
			ans.append("</select>");
			ans.append("&nbsp;&nbsp;<input type=\"text\" name=\"replaceIomParms\" value=\"RI30011,RI30012\"  size=\"25\">"); 
			ans.append("&nbsp;&nbsp;order&nbsp;<select class=\"c30\" id=\"orderIom\" name =\"orderIom\" >");
			for(int k = 0 ; k < 10;k++)
				ans.append("<option class=\"c30\" value=\""+k+"\" >"+k+"</option>");
			ans.append("<option class=\"c30\" value=\"10\" selected>10</option>");
			ans.append("</select>");
			ans.append("&nbsp;&nbsp;from env:&nbsp;<input type=\"text\" name=\"iomFromEnv\" value=\"R0\"  size=\"5\">"); 
			ans.append("</td><td>replace IOM for example: RI30011,RI30012 <br> select order like in migs lines</td><tr>");
			//gl
			ans.append("<tr bgcolor = \"#E6E6FA\"><td>NNRe GL interface:");
			ans.append("<td>");
			ans.append("<select class=\"c30\" id=\"GL-Interface\" name =\"GL-Interface\" >");
			ans.append("<option class=\"c30\" value=\"NO\">NO</option>");
			ans.append("<option class=\"c30\" value=\"YES\">YES</option>");
			ans.append("</select>");
			ans.append("</td><td>pack GL interface for NNRe</td><tr>");
			
			//webProxy
			ans.append("<tr bgcolor = \"#E6E6FA\"><td>replaceWebProxy:");
			ans.append("<td>");
			ans.append("<select class=\"c30\" id=\"replaceWebProxy\" name =\"replaceWebProxy\" >");
			ans.append("<option class=\"c30\" value=\"NO\">NO</option>");
			ans.append("<option class=\"c30\" value=\"YES\">YES</option>");
			ans.append("</select>");
			ans.append("</td><td>copy webProxy folder to this hotfix</td><tr>");	
			//eMerge client SP03
			ans.append("<tr bgcolor = \"#E6E6FA\"><td>upgrade to eMergeClientSP03U02:");
			ans.append("<td>");
			ans.append("<select class=\"c30\" id=\"eMergeClientSP3\" name =\"eMergeClientSP3\" >");
			ans.append("<option class=\"c30\" value=\"NO\">NO</option>");
			ans.append("<option class=\"c30\" value=\"YES\">YES</option>");
			ans.append("</select>");
			ans.append("</td><td>upgrade to eMergeClientSP03U02</td><tr>");

			
			ans.append("</table>");
			//INSTALL ON
			ans.append("<br>install on:");
			ans.append("<br><table  bgcolor = \"#dddddd\" border = 1 CELLPADDING=3 style=\"white-space:nowrap;\">");
			ans.append("<tr bgcolor = \"#E6E6FA\"><td>APP_Server</td><td>dbid's with , as delimiter.(for example:AB,GA,CF) </td></tr>");
			//HagStringList appServersList = new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\appServersList.txt",false,"xxssss",false);
			HagStringList appServersList =HagUtil.getAppServersList("WIN",false,false);
			
			String[] installOnId = {"installOn0","installOn1","installOn2","installOn3","installOn4","installOn5","installOn6","installOn7","installOn8","installOn9"};
			String[] installOnDb = {"installDb0","installDb1","installDb2","installDb3","installDb4","installDb5","installDb6","installDb7","installDb8","installDb9"};
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
			
			
			ans.append("<br><br><INPUT TYPE=SUBMIT value=\"Send request\"></FORM>");
			return ans.toString();

		}
		
	
	public String 	buildLoadFile(HagStringList cbEnvs,String sentBy){
		return "future";

	}
	public String 	duplicateMigEntry(HagStringList cbEnvs,String sentBy){
		StringBuilder ans =new StringBuilder();
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"dupMigsEntry.jsp\">");
		ans.append("<table  bgcolor = \"#E6E6FA\" border = 1 CELLPADDING=3 style=\"white-space:nowrap;\">");
		ans.append("<tr bgcolor = \"#E6AAFA\"><td>Duplicate from</td><td>To version</td></tr> ");
		if(cbEnvs.size()>1)
			return HagUtil.shortHtml("only one env (checkBox) can be selected for this option", "red","bg");
		String temp = cbEnvs.get(0);
		HagStringList list = new HagStringList(temp,"~",true);
		String jclass =list.get(3);
		String id =list.get(2);
		
		return  HagForms.dupMigRequest(sentBy,jclass,id);
		/*
		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			HagStringList list = new HagStringList(temp,"~",true);
			String jclass =list.get(3);
			String id =list.get(2);
			String task =list.get(0);
			String ver =list.get(1);
			String runType =list.get(4);
			String from = ver+","+id+","+jclass+","+runType;
			addToSvn( hagRcs[i],svnFolder , runTypeW0,runTypeTrunkW0, ind, jClass);
			HagUtil.insertIntoMigHf(jClass, jiraTask, jiraVer0, "{ALL}", runTypeW0a,location,"this_ver_only", sentBy,ind, note,true);

			ans.append("<tr>");
			ans.append("<td><input type=\"checkbox\" name=\"dupFrom\" id=\"dupFrom\" value=\""+from+"\"   checked>"+from+"</td>");
			ans.append("<td><select class=\"c30\" id=\"dupTo\" name =\"dupTo\" >");
			HagStringList versions = new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\svnFoldersNew.txt",false,"xxssss",false);
			for(int k=0;k<10;k++){
				String temp1 = versions.get(k);
				String temp2 = HagUtil.getWord0(temp1,"|",0,true);
				ans.append("<option class=\"c30\" value=\""+temp2+"\" selected>"+temp2+"</option>");
			}
			ans.append("</select></td>");
			ans.append("</tr>");
		}
		ans.append("<input type=\"hidden\" name=\"sentBy\" id=\"sentBy\" value = \""+sentBy+"\" maxlength=\"140\" size=\"140\">");
		
		ans.append("</table>");
		ans.append("<br><INPUT TYPE=SUBMIT value=\"Submit\"></FORM>");
		return ans.toString();
		*/
	}
	
	
	public String 		changeMigEntryPre(HagStringList cbEnvs,String sentBy,String type){
		if(cbEnvs.size()>1)
			return HagUtil.shortHtml("only one env (checkBox) can be selected for this option", "red","bg");
		
		String line = cbEnvs.get(0);
/*
		String sourceAPP=HagUtil.getWord0(line,"~",1,true);
		String sourceDB=HagUtil.getWord0(line,"~",11,true);
		String sourceBN = sourceDB;
		if(sourceDB.equals("RIAPPLDB"))
			sourceBN = "RI";
		String w3=HagUtil.getWord0(line,"~",3,true);
		String sourceSQL=HagUtil.getWord0(line,"~",10,true);
		String sourceEnv=HagUtil.getWord0(line,"~",9,true);
		String customerLong = HagUtil.getCustomerByPartyLong(w3);
		String customerShort = HagUtil.getCustomerByPartyShort(w3);
	
		
		HagStringList ans1 = new HagStringList();
		String rc1= HagJdbc.getDbSize("SQL",sourceSQL,"cfg","cfg09c",sourceDB, ans1);
		String rc1a = HagUtil.replaceStr(rc1,"<tbody class=\"scrollingContent\"><tr><td>"+sourceDB+"</td><td>","");
		rc1a = HagUtil.replaceStr(rc1a,"</td></tr></tbody>","");
		rc1a = HagUtil.replaceStr(rc1a,"</td><td>","#");
		String sourceDatSize=HagUtil.getWord0(rc1a,"#",0,true);
		String sourceLogSize=HagUtil.getWord0(rc1a,"#",1,true);

		
		String propFile = HagUtil.cfgMenuWebLoc+"\\lists\\cfgMenuWeb.properties";
		String sizeLimit = HagUtil.getPropertyVal(propFile,"privateDbSize");
		int sizeLimitI = HagUtil.s2i(sizeLimit);
		String sourceDBsize = HagUtil.getWord0(sourceDatSize, ".",0,true);
		int sourceDBsizeI = HagUtil.s2i(sourceDBsize);
		
		if(sourceDBsizeI >sizeLimitI)
			return HagUtil.shortHtml("source-DB size is too big - please call hagay 2527 <BR>source-DB size = "+sourceDatSize+"<BR>DB size limit = "+sizeLimit, "red","bg");
		
		String note1 =      "<select class=\"c30\" id=\"note1\"      name =\"note1\"      bgColor=\"#66ff66\"><option class=\"c30\" value=\"Other\">Other</option><option class=\"c30\" value=\"Initial\">Initial</option><option class=\"c30\" value=\"Vanilla\">Vanilla</option><option class=\"c30\" value=\"Setup\">Setup</option></select>";
		String note2 = "<input type=\"text\" name=\"note2\" id=\"note2\" maxlength=\"140\" size=\"140\">";
	
		
				
		HagStringList ans2 = new HagStringList();
	
		
		//long tomcatFolderSize = HagUtil.getFolderSize(sourceTomcatFolder);
		*/
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
	//	ans.add("var ua = window.navigator.userAgent;");
	//	ans.add("document.getElementById(\"myBrowser\").value = ua;");
		ans.add("}");
		ans.add("</script></head>	");
		
		ans.add("<body onload=\"refreshIFrame();getBrowser()\">");
		ans.add("<h3>Delete mig (change run_type to 40)</h3>");
		ans.add("<FORM METHOD=POST name=\"Form\" ACTION=\"changeMigEntry.jsp\">");
		ans.add("<table bgColor=\"#cccccc\" border=\"2\" cellpadding=\"3\" cellspacing=\"3\">");
		//ans.add("<tr><td></td><td>MigLine</td></tr>");
		String line1 = HagUtil.replaceStr(line,"~","</td><td>");
		ans.add("<tr bgColor=\"#aaaaaa\"><td>task</td><td>ver</td><td>id</td><td>jclass</td><td>run_type</td><td>pack2ver</td><td>dev</td><td>dt_dev</td><td>cm</td><td>dt_cm</td><td>manager</td><td>dt_manager</td><td>location</td><td>note</td></tr>");
		ans.add("<tr bgColor=\"#66ff66\"><td>"+line1+"</td></tr>");
	

		
		//ans.add("<tr><td >Source tomcat folder size</td><td bgColor=\"#66ff66\">"+tomcatFolderSize+"</td><td bgColor=\"#00ffff\">"+"."+"</td></tr>");
		
		ans.add("</table>");
		
		ans.add("<input type=\"hidden\" name=\"user\" id=\"user\" value = \""+sentBy+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"type\" id=\"type\" value = \""+type+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"line\" id=\"line\" value = \""+line+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"myBrowser\" id=\"myBrowser\" maxlength=\"140\" size=\"140\">");
		
	
	//	ans.add("<br><INPUT TYPE=SUBMIT value=\"Replace my private environment\"onclick=\"document.getElementById('loader').style.display = 'block';\" ></FORM>");
	//	ans.add("<br><INPUT TYPE=SUBMIT value=\"Replace my private environment\" onclick=\"document.getElementById('loader').style.display = 'block'; this.style.display = 'none'\" ></FORM>");

	
		ans.add("<br><INPUT TYPE=SUBMIT value=\"Delete mig\" onclick=\"document.getElementById('loader').style.display = ''; this.style.display = 'none'\" ></FORM>");
		
		
		ans.add("<img id=\"loader\" src=\"Please_wait.gif\" style=\"display: none;\"/>");
		//ans.add("<input type=\"text\" id=\"loader1\" value =\"Please_wait33\" style=\"display: none;\"/>");
//ans.add("</body>"); 
			return ans.convertToString(" ");
	}
	public String 	changeMigEntry(HttpServletRequest request, HttpServletResponse response){
		String user 	= request.getParameter("user").trim();
		String type 	= request.getParameter("type").trim();
		String line 	= request.getParameter("line").trim();

		//	public String 	changeMigEntry(HagStringList cbEnvs,String sentBy,String type){
//		if(cbEnvs.size()>1)
//			return HagUtil.shortHtml("only one mig (checkBox) can be selected for this option", "red","bg");
		StringBuilder ans =new StringBuilder();
		//ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"dupMigsEntry.jsp\">");
		//ans.append("<table  bgcolor = \"#E6E6FA\" border = 1 CELLPADDING=3 style=\"white-space:nowrap;\">");
		//ans.append("<tr bgcolor = \"#E6AAFA\"><td>Duplicate from</td><td>To version</td></tr> ");
	
	//	String temp = cbEnvs.get(0);
		HagStringList list = new HagStringList(line,"~",true);
		if(list.get(4).equals("40"))
			return HagUtil.shortHtml("mig type already equals 40","red","bg");
		
		String jclass =list.get(3);
		String id =list.get(2);
		String ver =list.get(1);
		String svnType =list.get(4);
		HagRc hagRc =new HagRc();
		String jiraVerLine = HagForms.getJiraVerLineSingle(ver);
		if(jiraVerLine==null)
			return HagUtil.shortHtml("failed to find version in svn folders list","red","bg");
		String svnFolder =HagUtil.getWord0(jiraVerLine,"|",1,true); 
		
		
		HagForms.changeInSvn( hagRc,  svnFolder , svnType,  id, jclass,"40",user);
	
		
		HagUtil.updateMigHf(ver,id,jclass,"40", user);

		if(hagRc.rc!=0) {
			return "Failed to change commit failed - <br>"+hagRc.log.convertToString("<br>");
		}
		return ver+","+id+","+jclass+" run type change to 40 (deleted)";
			
	}
	
	
	public String 	setOdedKeyI5os(HagStringList cbEnvs){
		String file = 	HagUtil.cfgMenuWebLoc+"\\lists\\oded.txt" ;
		HagStringList odedList = new HagStringList ( file,true,"*",false);
		StringBuilder ans =new StringBuilder();
		//	ans.append("<table border=\"1\"  cellpadding=\"10\">");c

		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"setOdedsKeyI5os.jsp\">");
		ans.append("<table  bgcolor = \"#E6E6FA\" border = 1 CELLPADDING=3 style=\"white-space:nowrap;\">");
		ans.append("<tr bgcolor = \"#c4c4d8\">");
		ans.append("<td>APP Server</td>");
		ans.append("<td>batchName</td>");
		ans.append("<td>Customer</td>");
		ans.append("<td>Installed version</td>");
		ans.append("<td>Installation date</td>");
		ans.append("<td>Installation RC</td>");
		ans.append("<td>Oded's key</td>"); 
		ans.append("</tr>");
		String aaa = "STYLE=\"color: #888888; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #E6E6FA;\" size=\"10\" maxlength=\"80\"";
		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			HagStringList list = new HagStringList(temp,"~",true);

			ans.append("<tr>");
			ans.append("<td><input "+aaa+" type=\"text\" name=\"odedKey1\" value=\""+list.get(1)+"\"    size=\"15\"></td>");
			ans.append("<td><input  "+aaa+" type=\"text\" name=\"odedKey2\" value=\""+list.get(2)+"\"   size=\"15\"></td>");
			ans.append("<td><input  "+aaa+" type=\"text\" name=\"odedKey3\" value=\""+list.get(3)+"\"   size=\"15\"></td>");
			ans.append("<td><input  "+aaa+" type=\"text\" name=\"odedKey4\" value=\""+list.get(4)+"\"    size=\"25\"></td>");
			ans.append("<td><input  "+aaa+" type=\"text\" name=\"odedKey5\" value=\""+list.get(5)+"\"    size=\"25\"></td>");
			ans.append("<td><input  "+aaa+" type=\"text\" name=\"odedKey6\" value=\""+list.get(6)+"\"    size=\"15\"></td>");

			ans.append("<td><select class=\"c30\" id=\"odedKey7\" name =\"odedKey7\" >");
			for(int k = 0 ; k < odedList.size();k++){
				String temp1 = odedList.get(k);
				String w1 = HagUtil.getWord0(temp1, "|", 0,true);
				String w2 = HagUtil.getWord0(temp1, "|", 1,true);
				String zzz = "&nbsp;&nbsp;&nbsp;&nbsp;";
				if(w1.length()==4)
					zzz=zzz+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				if(w1.length()==3)
					zzz=zzz+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

				if(w1.equals(list.get(7).trim()))
					ans.append("<option class=\"c30\" value=\""+w1+"\" selected>"+w1+zzz+"("+w2+")</option>");
				else
					ans.append("<option class=\"c30\" value=\""+w1+"\">"+w1+zzz+"("+w2+")</option>");
			}
			ans.append("</select></td>");

			//ans.append("<td><input type=\"text\" name=\"odedKey8\" value=\""+list.get(8)+"\"  size=\"15\"></td>"); 
			ans.append("</tr>");
		}
		ans.append("</table>");
		ans.append("<br><INPUT TYPE=SUBMIT value=\"Submit\"></FORM>");
		return ans.toString();

	}
	//before


	public String 	setOdedsKeyWin(HttpServletRequest request, HttpServletResponse response){


		String [] odedKey1	= request.getParameterValues("odedKey1");
		String [] odedKey2	= request.getParameterValues("odedKey2");
		String [] odedKey8	= request.getParameterValues("odedKey8");
		StringBuilder sb = new StringBuilder();
		sb.append("<table  bgcolor = \"#E6E6FA\" border = 1 CELLPADDING=3 >");
		sb.append("<tr bgcolor = \"#00ff00\"><td>appServer</td><td>batchName</td><td>SQL</td><td>RC</td></tr>");
		for(int i = 0 ; i < odedKey1.length;i++){
			StringBuilder stm = new StringBuilder("UPDATE "+HagParam.getConfg1DB()+".[dbo].[ri_environments_new] ")
			.append("SET  [oded] = '").append(odedKey8[i].trim()).append("' ")
			.append("WHERE [bis_server]= '").append(odedKey1[i].trim()).append("' AND  [batchName]='").append(odedKey2[i].trim()).append("'");
			sb.append("<tr bgcolor = \"#aaaaaa\"><td>"+odedKey1[i].trim()+"</td><td>"+odedKey2[i].trim()+"</td><td>"+stm+"</td><td>");
			sb.append(HagJdbc.updateStm("CONFG1","cfg","cfg09c",HagParam.getConfg1DB(), stm.toString(),odedKey1[i].trim(),odedKey2[i].trim())).append("<br>");		
			sb.append("</td></tr>");
		}
		sb.append("</table><br>");
		sb.append("<table><tr><td bgcolor = \"#ffff00\">");
		sb.append("This process did the changes you asked, but did not refresh the table displayed on the right panel of the main window.<br>");
		sb.append("In order to view the changes, you need to close this window and regenerate the table, by reselecting the option on the left panel.<br>"); 
		sb.append("</td></tr></table>");
		return sb.toString();
	}
	
	public String 	setOwner(HttpServletRequest request, HttpServletResponse response){


		String [] key1	= request.getParameterValues("key1");
		String [] key2	= request.getParameterValues("key2");
		String [] key12	= request.getParameterValues("key12");
		StringBuilder sb = new StringBuilder();
		sb.append("<table  bgcolor = \"#E6E6FA\" border = 1 CELLPADDING=3 >");
		sb.append("<tr bgcolor = \"#00ff00\"><td>appServer</td><td>batchName</td><td>SQL</td><td>RC</td></tr>");
		for(int i = 0 ; i < key1.length;i++){
			StringBuilder stm = new StringBuilder("UPDATE "+HagParam.getConfg1DB()+".[dbo].[ri_environments_new] ")
			.append("SET  [owner] = '").append(key12[i].trim()).append("' ")
			.append("WHERE [bis_server]= '").append(key1[i].trim()).append("' AND  [batchName]='").append(key2[i].trim()).append("'");
			sb.append("<tr bgcolor = \"#aaaaaa\"><td>"+key1[i].trim()+"</td><td>"+key2[i].trim()+"</td><td>"+stm+"</td><td>");
			sb.append(HagJdbc.updateStm("CONFG1","cfg","cfg09c",HagParam.getConfg1DB(), stm.toString(),key1[i].trim(),key2[i].trim())).append("<br>");		
			sb.append("</td></tr>");
		}
		sb.append("</table><br>");
		sb.append("<table><tr><td bgcolor = \"#ffff00\">");
		sb.append("This process did the changes you asked, but did not refresh the table displayed on the right panel of the main window.<br>");
		sb.append("In order to view the changes, you need to close this window and regenerate the table, by reselecting the option on the left panel.<br>"); 
		sb.append("</td></tr></table>");
		return sb.toString();
	}
	public String 	setNoteFieldPost(HttpServletRequest request, HttpServletResponse response){


		
		String [] note1	= request.getParameterValues("note1");
		String [] note2	= request.getParameterValues("note2");
		String [] note18	= request.getParameterValues("note18");
		

		
		StringBuilder sb = new StringBuilder();
		sb.append("<table  bgcolor = \"#E6E6FA\" border = 1 CELLPADDING=3 >");
		sb.append("<tr bgcolor = \"#00ff00\"><td>appServer</td><td>batchName</td><td>SQL</td><td>RC</td></tr>");
		StringBuilder err = new StringBuilder();
		for(int i = 0 ; i < note1.length;i++){
			if(note18[i].length()>50) {
				String ttt = note18[i].substring(0, 50);
				err.append("note field length limit = 50 characters: "+note18[i]+" trancated to "+ttt+"<br>");
				note18[i]=ttt;
			}
			StringBuilder stm = new StringBuilder("UPDATE "+HagParam.getConfg1DB()+".[dbo].[ri_environments_new] ")
			.append("SET  [note] = '").append(note18[i].trim()).append("' ")
			.append("WHERE [bis_server]= '").append(note1[i].trim()).append("' AND  [batchName]='").append(note2[i].trim()).append("'");
			
			sb.append("<tr bgcolor = \"#aaaaaa\"><td>"+note1[i].trim()+"</td><td>"+note2[i].trim()+"</td><td>"+stm+"</td><td>");
			sb.append(HagJdbc.updateStm("CONFG1","cfg","cfg09c",HagParam.getConfg1DB(), stm.toString(),note1[i].trim(),note2[i].trim())).append("<br>");		
			sb.append("</td></tr>");
		}
		sb.append("</table><br>");
		sb.append(err.toString());
		sb.append("<table><tr><td bgcolor = \"#ffff00\">");
		sb.append("This process did the changes you asked, but did not refresh the table displayed on the right panel of the main window.<br>");
		sb.append("In order to view the changes, you need to close this window and regenerate the table, by reselecting WIN-envs option on the left panel.<br>"); 
		sb.append("</td></tr></table>");
		return sb.toString();
	}
	public String 	runSqlStmOnEnvs(HttpServletRequest request, HttpServletResponse response,String type){
		String [] odedKey1	= request.getParameterValues("odedKey1");
		String [] odedKey2	= request.getParameterValues("odedKey2");
		String [] odedKey10	= request.getParameterValues("odedKey10");
		String [] odedKey11	= request.getParameterValues("odedKey11");
		String [] sqlStm	= request.getParameterValues("sqlStm");
		StringBuilder sb = new StringBuilder();
		sb.append("<table  bgcolor = \"#E6E6FA\" border = 1 CELLPADDING=3 style=\"white-space:nowrap;\">");
		sb.append("<tr bgcolor = \"#c4c4d8\">");
		sb.append("<td>APP Server</td>");
		sb.append("<td>batchName</td>");
		sb.append("<td>SQL Server</td>");
		sb.append("<td>DB name</td>");
		sb.append("<td>SQL stm</td>"); 
		sb.append("<td>RC</td>"); 
		sb.append("</tr>");
		for(int i = 0 ; i < odedKey10.length;i++){
		//	StringBuilder stm = new StringBuilder("UPDATE "+HagParam.getConfg1DB()+".[dbo].[ri_environments_new] ")
		//	.append("SET  [oded] = '").append(odedKey8[i].trim()).append("' ")
		//	.append("WHERE [bis_server]= '").append(odedKey1[i].trim()).append("' AND  [batchName]='").append(odedKey2[i].trim()).append("'");
			String rc = "INIT";		
			if(type.equals("U"))
				rc = HagJdbc.updateStm(odedKey10[i],"cfg","cfg09c",odedKey11[i], sqlStm[i],odedKey1[i],odedKey2[i]);		
			else{
				HagStringList cc = new HagStringList();
				String rcc= HagJdbc.selectIntoList("SQL",odedKey10[i],"cfg","cfg09c",odedKey11[i], sqlStm[i],cc,"~");
				rc = cc.convertToString("<br>");
			}
						
			sb.append("<tr>");
			sb.append("<td>").append(odedKey1[i]).append("</td>");
			sb.append("<td>").append(odedKey2[i]).append("</td>");
			sb.append("<td>").append(odedKey10[i]).append("</td>");
			sb.append("<td>").append(odedKey11[i]).append("</td>");
			sb.append("<td>").append(sqlStm[i]).append("</td>");
			sb.append("<td>").append(rc).append("</td>");
			sb.append("</tr>");
		}
		return sb.toString();
	}
	public String 	setOdedsKeyI5os(HttpServletRequest request, HttpServletResponse response){
		String [] odedKey1	= request.getParameterValues("odedKey1");
		String [] odedKey2	= request.getParameterValues("odedKey2");
		String [] odedKey7	= request.getParameterValues("odedKey7");
		StringBuilder sb = new StringBuilder();
		sb.append("<table  bgcolor = \"#E6E6FA\" border = 1 CELLPADDING=3 >");
		sb.append("<tr bgcolor = \"#00ff00\"><td>appServer</td><td>batchName</td><td>SQL</td><td>RC</td></tr>");
		for(int i = 0 ; i < odedKey1.length;i++){
			StringBuilder stm = new StringBuilder("UPDATE "+HagParam.getConfg1DB()+".[dbo].[ri_environments_i5os] ")
			.append("SET  [oded] = '").append(odedKey7[i].trim()).append("' ")
			.append("WHERE [server]= '").append(odedKey1[i].trim()).append("' AND  [batchName]='").append(odedKey2[i].trim()).append("'");
			sb.append("<tr bgcolor = \"#aaaaaa\"><td>"+odedKey1[i].trim()+"</td><td>"+odedKey2[i].trim()+"</td><td>"+stm+"</td><td>");
			sb.append(HagJdbc.updateStm("CONFG1","cfg","cfg09c",HagParam.getConfg1DB(), stm.toString(),odedKey1[i].trim(),odedKey2[i].trim())).append("<br>");		
			sb.append("</td></tr>");
			
		}
		sb.append("</table><br>");
		sb.append("<table><tr><td bgcolor = \"#ffff00\">");
		sb.append("This process did the changes you asked, but did not refresh the table displayed on the right panel of the main window.<br>");
		sb.append("In order to view the changes, you need to close this window and regenerate the table, by reselecting the option on the left panel.<br>"); 
		sb.append("</td></tr></table>");
		return sb.toString();
	}
	


	public String 	prcLink(HttpServletRequest request, HttpServletResponse response){
		String val = request.getParameter( "value" );

		HagStringList ans = new HagStringList();
		String stm = "select hagLink from dbo.RI_PRCLINKS where hagKey='"+val+"'";
		HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm, ans, "!");
		String str = ans.convertToString("<br>");
		return str;


	}

	public String 	openFolder(HttpServletRequest request, HttpServletResponse response){
		String value = request.getParameter( "value" );
		//	HagUtil.p(value);
		HagStringList list = HagUtil.getFilesInDirOneLevel(value);
		StringBuilder str = new StringBuilder();
		HagStringList list1=new HagStringList(value+"/MainLog.txt",false ,"asjakyc",false);
		str.append("<html><body bgcolor=\"#ccccbb\">");
		str.append("colors table:<br>");
		str.append("<table  bgcolor = \"#E6E6FA\" border = 1 CELLPADDING=3 style=\"white-space:nowrap;\">");
		str.append("<tr>");
		str.append("<td>").append("<font color = \"#111111\">MainLog</font>").append("</td>");
		str.append("<td>").append("<font color = \"#ff0000\">rc=Failed</font>").append("</td>");
		str.append("<td>").append("<font color = \"#008000\">rc=OK</font>").append("</td>");
		str.append("<td>").append("<font color = \"#663399\">rc=Skipped</font>").append("</td>");
		str.append("<td>").append("<font color = \"#0000ff\">extra logs</font>").append("</td>");
		str.append("</tr>");
		str.append("</table><br>Links to logs<br>");


		str.append("<table  bgcolor = \"#8DC7BB\" border = 1 CELLPADDING=5 style=\"white-space:nowrap;\">");
		int i = 0 ;
		while (i < list.size()){
			String temp1 = list.get(i);
			i++;
			String temp2 = "";
			if(i<list.size())
				temp2 = list.get(i);

			String ff2 = "";
			String ff1 = value+"/"+temp1;
			if(i<list.size())
				ff2 = value+"/"+temp2;
			
			
			ff1 = HagUtil.replaceStr(ff1, "#","!!");
			ff2 = HagUtil.replaceStr(ff2, "#","!!");
			
			String aa2 = ".";
			String cc1 =getlogRc(temp1,  list1);
			String aa1 = "<a href=\"openLogFile.jsp?value="+ff1+"\">"+cc1+"</a>";
			if(temp1.equals("mainLog.txt"))
				aa1 = "<a href=\"openLogFile.jsp?value="+ff1+"\"><font color = \"#11111\">"+temp1+"</font></a>";

			if(i<list.size()){
				String cc2 =getlogRc(temp2,  list1);
				aa2 = "<a href=\"openLogFile.jsp?value="+ff2+"\">"+cc2+"</a>";
				if(temp2.equals("mainLog.txt"))
					aa2 = "<a href=\"openLogFile.jsp?value="+ff2+"\"><font color = \"#111111\">"+temp2+"</font></a>";
			}
			i++;
			str.append("<tr style=\"color:#009900; background: #E6E6FA;\"><td>"+aa1+"</td><td>"+aa2+"</td></tr>");
		}
		str.append("</table></body></html>");
		return str.toString();
	}
	

	public String 	openFolderT(HttpServletRequest request, HttpServletResponse response){
		String value = request.getParameter( "value" );
		//if(1==1)
		//	return value;
		HagStringList ans = new HagStringList();
		String stm = "select hagLink from dbo.RI_PRCLINKS where hagKey='"+value+"'";
		HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm, ans, "!");
		String str = ans.convertToString("").trim();
		str = HagUtil.replaceStr(str, "D:/", "");
		str = HagUtil.replaceStr(str, "C:/", "");
		str = HagUtil.replaceStr(str, "d:/", "");
		str = HagUtil.replaceStr(str, "c:/", "");
		//HagUtil.p(str);
		return openFolder1(str);
	}

	public String  	openFolder1(String folder){
		//if(1==1)
		//	return folder;
		HagStringList list = HagUtil.getFilesInDirOneLevel(folder);
		StringBuilder str = new StringBuilder();
		HagStringList list1=new HagStringList(folder+"/MainLog.txt",false ,"asjakyc",false);
		str.append("<html><body bgcolor=\"#ccccbb\">");
		str.append("colors table:<br>");
		str.append("<table  bgcolor = \"#E6E6FA\" border = 1 CELLPADDING=3 style=\"white-space:nowrap;\">");
		str.append("<tr>");
		str.append("<td>").append("<font color = \"#111111\">MainLog</font>").append("</td>");
		str.append("<td>").append("<font color = \"#ff0000\">rc=Failed</font>").append("</td>");
		str.append("<td>").append("<font color = \"#008000\">rc=OK</font>").append("</td>");
		str.append("<td>").append("<font color = \"#663399\">rc=Skipped</font>").append("</td>");
		str.append("<td>").append("<font color = \"#0000ff\">extra logs</font>").append("</td>");
		str.append("</tr>");
		str.append("</table><br>Links to logs<br>");


		str.append("<table  bgcolor = \"#8DC7BB\" border = 1 CELLPADDING=5 style=\"white-space:nowrap;\">");
		int i = 0 ;
		while (i < list.size()){
			String temp1 = list.get(i);
			i++;
			String temp2 = "";
			if(i<list.size())
				temp2 = list.get(i);

			String ff2 = "";
			String ff1 = folder+"/"+temp1;
			if(i<list.size())
				ff2 = folder+"/"+temp2;
			
			ff1 = HagUtil.replaceStr(ff1, "#","!!");
			ff2 = HagUtil.replaceStr(ff2, "#","!!");
			
			
			String aa2 = ".";
			String cc1 =getlogRc(temp1,  list1);
			String aa1 = "<a href=\"openLogFile.jsp?value="+ff1+"\">"+cc1+"</a>";
			if(temp1.equals("mainLog.txt"))
				aa1 = "<a href=\"openLogFile.jsp?value="+ff1+"\"><font color = \"#111111\">"+temp1+"</font></a>";

			if(i<list.size()){
				String cc2 =getlogRc(temp2,  list1);
				aa2 = "<a href=\"openLogFile.jsp?value="+ff2+"\">"+cc2+"</a>";
				if(temp2.equals("mainLog.txt"))
					aa2 = "<a href=\"openLogFile.jsp?value="+ff2+"\"><font color = \"#111111\">"+temp2+"</font></a>";
			}
			i++;
			str.append("<tr style=\"color:#009900; background: #E6E6FA;\"><td>"+aa1+"</td><td>"+aa2+"</td></tr>");
		}
		str.append("</table></body></html>");
		return str.toString();
	}

	public String  	openFolderLogs(String folder){
		HagStringList list = HagUtil.getFilesInDirOneLevel(folder);
		StringBuilder str = new StringBuilder();
	//	HagStringList list1=new HagStringList(folder+"/MainLog.txt",false ,"asjakyc",false);
		str.append("<html><body bgcolor=\"#ccccbb\">");


		str.append("<table  bgcolor = \"#8DC7BB\" border = 1 CELLPADDING=5 style=\"white-space:nowrap;\">");
		int i = 0 ;
		while (i < list.size()){
			String temp1 = list.get(i);
			i++;
			String temp2 = "";
			if(i<list.size())
				temp2 = list.get(i);

			String ff2 = "";
			String ff1 = folder+"/"+temp1;
			if(i<list.size())
				ff2 = folder+"/"+temp2;
			
			ff1 = HagUtil.replaceStr(ff1, "#","!!");
			ff2 = HagUtil.replaceStr(ff2, "#","!!");
			
			
			String aa2 = ".";
			//String cc1 =getlogRc(temp1,  list1);
			String cc1 =""+temp1;
			
			String aa1 = "<a href=\"openLogFile.jsp?value="+ff1+"\">"+cc1+"</a>";
			if(temp1.equals("mainLog.txt"))
				aa1 = "<a href=\"openLogFile.jsp?value="+ff1+"\"><font color = \"#111111\">"+temp1+"</font></a>";

			if(i<list.size()){
				String cc2 =""+temp2;
				//String cc2 =getlogRc(temp2,  list1);
				aa2 = "<a href=\"openLogFile.jsp?value="+ff2+"\">"+cc2+"</a>";
				if(temp2.equals("mainLog.txt"))
					aa2 = "<a href=\"openLogFile.jsp?value="+ff2+"\"><font color = \"#111111\">"+temp2+"</font></a>";
			}
			i++;
			str.append("<tr style=\"color:#009900; background: #E6E6FA;\"><td>"+aa1+"</td><td>"+aa2+"</td></tr>");
		}
		str.append("</table></body></html>");
		return str.toString();
	}
	

	public static final String  	getlogRc(String log , HagStringList list){
		String str = "<font color = \"#0000ff\">"+log+"</font>";
		String log1 = log.substring(0,log.indexOf("."));
		for(int i = 0 ; i < list.size();i++){
			String temp1 = list.get(i).trim();
			if(temp1.startsWith("OK")){
				if( temp1.indexOf("("+log1+")")>-1 || temp1.indexOf("~"+log1)>-1) {
					return "<font color = \"#008000\">"+log+"</font>";
				}
			}
			if(temp1.startsWith("Failed")){
				if( temp1.indexOf("("+log1+")")>-1 || temp1.indexOf("~"+log1)>-1){
					return "<font color = \"#ff0000\">"+log+"</font>";
				}
			}
			if(temp1.startsWith("Skipped")){
				if( temp1.indexOf("("+log1+")")>-1 || temp1.indexOf("~"+log1)>-1){
					return "<font color = \"#663399\">"+log+"</font>";
				}
			}

		}
		return str; 	
	}

	public String 	openLogFile(HttpServletRequest request, HttpServletResponse response){
		String aa="aa";
		String value = request.getParameter( "value" );
		value = HagUtil.replaceStr(value,"!!", "#");
		
		HagStringList list = new HagStringList(value,false,"1dssafda2",true);

		String str= "";
		if(value.endsWith("/mainLog.txt"))
			str= list.convertMainLogsToString();
		else if(value.endsWith("/mainLog.html"))
			str= list.convertToString("");
		else 		
			str= list.convertLogsToString();
		return str;
	}
	public String 	openLogFileFixed(HttpServletRequest request, HttpServletResponse response){
		String aa="aa";
		String value = request.getParameter( "value" );
		//value = HagUtil.replaceStr(value,"!!", "#");
		String ff1 = HagUtil.cfgMenuWebLoc+"\\logs\\"+value+".html";
		HagStringList list = new HagStringList(ff1,false,"1dssafda2",true);

		String str= "";
		if(value.endsWith("/mainLog.txt"))
			str= list.convertMainLogsToString();
		else if(value.endsWith("/mainLog.html"))
			str= list.convertToString("");
		else 		
			str= list.convertLogsToString();
		return str;
	}
	public String 	debug(HttpServletRequest request, HttpServletResponse response){
		String opt 		= request.getParameter("act");
		if(opt.equals("debug2")){
		//	String bisServer="RI43QA";
		//	HagStringList list = HagUtil.getWmicByName9(bisServer, "java.exe");
		//	String aaa= list.convertToString("<br>");
			//		return aaa;
			HagDebug.datePrompt_before();
			
		}
		return "aaa";
	}		

	
	public String 	runVbCmd(String opt,HagStringList list){
		

		//String opt = "GET_ROW";
		String vals = list.convertToString(" ");

	//	String outFile		="\\\\ri-archive\\Saptech-Archive\\cm\\work\\aa\\CCC\\Servers_Det.html";
	//	String outFolder	="\\\\ri-archive\\Saptech-Archive\\cm\\work";
		String vbCmd		="\\\\orsayserver\\d\\gonteam\\#VBscript_and_SQL\\test\\VB_from_CFGWEB.vbs";
			

		String vbLine="cscript "+vbCmd+" "+opt+" /DBIDS:\""+vals+"\"";

		
		System.out.println("before1**************:");
		System.out.println(vbLine);			
		System.out.println("before**************:");
		//	String vbLine="cscript //nologo \\\\orsayserver\\d\\gonteam\\#VBscript_and_SQL\\PROD\\Gopenfiles.vbs   /Opt:\"Kill\" /Server:\""+toApp+"\"    /Object:\"_"+toDb+"\"";

		//String vbLine="cscript "+vbCmd+" "+vbVals;
		String ans2 =  HagUtil.runCmd2(vbLine+"\n",false);
		System.out.println("after1**************:");
		System.out.println(ans2);			
		System.out.println("after2**************:");
		
		
//

	return "aaaa";
}		

	public String 	runRemoteCommand(HttpServletRequest request, HttpServletResponse response){
	
			//return HagUtil.runCmdRemote(server, command, wait);
		//	String server 	= request.getParameter("server");
		//	String command 	= request.getParameter("cmd");
		//	String output 	= request.getParameter("output");
			
			String [] cbgroup1		= request.getParameterValues("cb");
			if(cbgroup1==null || cbgroup1.length==0)
				return HagUtil.shortHtml("You must select at least one version","red","bg");
String servers="";
			for(int i = 0 ; i < cbgroup1.length;i++) {
				String temp = cbgroup1[i];
				String w1 = HagUtil.getWord0(temp,"~", 1,true);
				servers= servers + w1+" ";
			}

			String outFile		="\\\\ri-archive\\Saptech-Archive\\cm\\work\\aa\\CCC\\Servers_Det.html";
			String outFolder	="\\\\ri-archive\\Saptech-Archive\\cm\\work";
			String vbCmd		="\\\\ri-archive\\Saptech-Archive\\cm\\vb\\Servers_Det.vbs";
				
			//String outFile="\\\\orsayserver\\d\\gonteam\\#VBscript_and_SQL\\TEST\\VB_from_CFGWEB_Outputs\\work\\aa\\Servers_Det.html";
		//	String outFolder=" \\\\orsayserver\\d\\gonteam\\#VBscript_and_SQL\\TEST\\VB_from_CFGWEB_Outputs\\work ";
		//	String vbCmd=" \\\\orsayserver\\vb\\test\\Servers_Det.vbs ";
			
			String vbVals=" /Servers:\""+servers+"\" ";
			String vbLine="cscript "+vbCmd+" "+vbVals+" /OutputFolder:"+outFolder;

			System.out.println("before1**************:");
			System.out.println(vbLine);			
			System.out.println("before**************:");
			//	String vbLine="cscript //nologo \\\\orsayserver\\d\\gonteam\\#VBscript_and_SQL\\PROD\\Gopenfiles.vbs   /Opt:\"Kill\" /Server:\""+toApp+"\"    /Object:\"_"+toDb+"\"";

			//String vbLine="cscript "+vbCmd+" "+vbVals;
			String ans2 =  HagUtil.runCmd2(vbLine+"\n",false);
			System.out.println("after1**************:");
			System.out.println(ans2);			
			System.out.println("after2**************:");
			
//			String ans2 =  HagUtil.runCmdRemote("RII-5OS", vbLine+"\n","N");
			if(ans2.startsWith("RC=0 :")){
				HagStringList list = new HagStringList(outFile,false,"xxssss",false);
				if(list.size()==0)
					return "Done without output.";
				else
					return HagUtil.readFile(outFolder+"\\aa","Servers_Det.html");
					//return list.convertToString(" ");xxxx
								
			}
	
		return "failed";
	}		
	public String 	distReq(HttpServletRequest request, HttpServletResponse response){
		
		String opt 		= request.getParameter("opt");
		String w1 		= HagUtil.getWord0(opt,",", 0,true);
		String w2 		= HagUtil.getWord0(opt,",", 1,true);
		return HagForms.requestsNew_betore1(w2, w1);
	}
	//public void checkCfgMenuWebVer(HagRc hagRc) {
	//	String cfgMenuWebVer=HagUtil.getPropertyVal(HagUtil.cfgMenuWebLoc+"\\lists\\cfg.list", "cfgMenuWebVer");
		
	//	if((cfgMenuWebVer==null ||cfgMenuWebVerKeep==null) || (!cfgMenuWebVer.equals(cfgMenuWebVerKeep)) ){
	//		hagRc.rc=1;
	//		hagRc.log.add(	"CfgMenuWeb version is not the latest<br>"+
	//						"you are working with version "+cfgMenuWebVerKeep+" but the latest version is "+cfgMenuWebVer+"<br>"+
	//						"please refresh by clicking the PF5 key in the keyboard");
	//				return;
	//	}
		
	//	return ;
	//}
	//before hagdist
	public String 	dist(HttpServletRequest request, HttpServletResponse response){
	//	HagRc hagRcVer= new HagRc();
	//	checkCfgMenuWebVer(hagRcVer);
	//	if(hagRcVer.rc!=0)
	//		return HagUtil.shortHtml(hagRcVer.log.get(0), "red","bg");
//		HagUtil.appendToFile("\\\\ri-archive\\Saptech-Archive\\hag\\debug\\11.txt", "2","aaa"  ) ;
	//	HagUtil.hagDebugToFile("d:\\temp\\1.txt", "0"  );
		if(HagParam.getStatus().equalsIgnoreCase("LOCK"))
			return HagUtil.shortHtml("server status =LOCK, please call devOps team", "red","bg");
	///	mustRefresh2=getRefreshFlag();
	///	if(!mustRefresh2.equals(mustRefresh1)) {
			//return HagUtil.shortHtml("You must refresh cfgMenuWeb (by clicking the PF5 key)","red","bg");
			//try {
			//	response.sendRedirect(request.getContextPath());
		//	} catch (IOException e) {
			//	// TODO Auto-generated catch block
			//	e.printStackTrace();
		//	} 
	///	}
		
		String user 	= request.getParameter("user");
		String pass 	= request.getParameter("pass");
		String opt 		= request.getParameter("opt");
		
		
		if(opt.startsWith("closeTask~") || opt.startsWith("openNewTask~"))
			return HagTasks.before(opt,user,pass);
	
		//if(1==1)
		
		
		//	return opt;
		String w1 		= HagUtil.getWord0(opt,"~", 0,true);
		String w2 		= HagUtil.getWord0(opt,"~", 1,true);
		String path 	= request.getSession().getServletContext().getRealPath("");
	//	if(1==1)
	//	return w1;


		String auth = HagUtil.isAuthorized(w1,user,pass);
		if(!auth.equals("OK")){
			return HagUtil.shortHtml(auth , "red", "bg");
		}



		//	if(w1.equals("publicRiEnvsNewStruct")){
		//		return HagForms.envsNewForm();
		//		}else if(w1.equals("publicRiEnvs")){
		if(w1.equals("publicRiEnvs")){
			return HagForms.riEnvsForm(user,pass);
		}else if(w1.equals("publicRiEnvsAzure")){
			return HagForms.riEnvsFormAzure(user,pass);
		}else if(w1.equals("compileIOM")){
			return HagIOM.compileIOM_before(user,pass);
		}else if(w1.equals("runTranFile")){
			return HagRunTran.runTran_before(user,pass);
		}else if(w1.equals("createXmlFromTaskAndDates")){
			return HagCreateXmlFromTask.createXmlFromTask_before(user,pass);
		}else if(w1.equals("createXmlFromTaskAndDates")){
			return HagCreateXml.createXml_before(user,pass);
		}else if(w1.equals("publicBackupDbWithNotesList")){
			return backupDbWithNotesList();
		}else if(w1.equals("RequestsLog")){
			return requestsLog(null);
		}else if(w1.equals("publicRiServers")){
			return HagForms.riServersForm(user,pass);
		}else if(w1.equals("publicGardEnvs")){
			return HagForms.gardEnvsForm(user,pass);
		}else if(w1.equals("publicMigsList")){
				return HagForms.migsListForm(user,pass);
		}else if(w1.equals("publicDeleteFromMigDetails")){
			return HagForms.deleteFromMigDetails(user,pass);
		}else if(w1.equals("publicAppServersInfo")){
			return HagServersInfo.getAppServersInfo_before("AppServerInfo",user,pass);
		}else if(w1.equals("publicClientModuleMigsList")){
			return HagForms.clientModuleMigsListForm(user,pass);
		}else if(w1.equals("publicClientModuleMigsListFutureRelease")){
			return ClientModule.clientModuleMigsListForm(user,pass);
		}else if(w1.equals("publicScheduleFMigsList")){
			return ScheduleF.scheduleFMigsListForm(user,pass);
		}else if(w1.equals("publicRiEnvsI5os")){
			return HagForms.riEnvsI5osForm(user,pass);
		}else if(w1.equals("publicRiPrc1000")){
			return HagForms.prcForm(null,1000);
		}else if(w1.equals("publicRiPrc10000")){
			System.gc();
			String aaaaa= HagForms.prcForm(null,10000);
			// System.out.println("end3"+HagUtil.getDateTime("mm:ss:SSS"));
			 System.gc();
			return aaaaa;
		}else if(w1.equals("publicRiPrcFull")){
			System.gc();
			String aaaaa=  HagForms.prcForm(null,-1);
			// System.out.println("end3"+HagUtil.getDateTime("mm:ss:SSS"));
				System.gc();
				return aaaaa;
		}else if(w1.equals("publicRiPrcNew")){
			return HagIKnowWhatYouDid.before(user,pass);
			//return HagForms.prcFormNew(null,1000);
		}else if(w1.equals("publicRiPrcPerCust")){
			return RequestsFlowPerCust.requestsStatusForm(user,pass,null,w2);
		}else if(w1.equals("killList")){
			return HagKillList.after();
		}else if(w1.equals("publicTomcatsStatus")){
			return HagForms.tomcatsStatus(null);
		}else if(w1.equals("publicIwaysLinks")){
			return HagForms.iWayLinks(null);
		}else if(w1.equals("generalRequest")){
			return HagForms.generalRequest(user,pass,w1);
		}else if(w1.equals("RequestsNew")){
			return HagRequests.requestsNew_before(user,pass,w1);
		}else if(w1.equals("addNewCustomerRequest")){
			return HagForms.addNewCustomer_before(user,pass,w1);
		}else if(w1.equals("changePassword")){
			return HagForms.registerForm(w1,user,pass);
		}else if(w1.equals("authorizationsRequest")){
			return HagForms.registerForm(w1,user,pass);
		}else if(w1.equals("reqCreateHotfixCI")){
			//return HagForms.reqCreateHotfixBefore(user,pass);
			return HagForms.reqCreateHotfixBefore1(user,pass);
		}else if(w1.equals("reqCreateHotfixA")){
			return HagForms.reqCreateHotfixAriaBefore1(user,pass);
		}else if(w1.equals("publicReadHtmlLoc1")){
			return HagUtil.readFile(HagUtil.cfgMenuWebLoc+"",w2);
		}else if(w1.equals("publicTasksTable")){
			return HagForms.tasksForm(w2,user,pass);
		}else if(w1.equals("RiFtpPackRequest")){
			//return path+"<br>"+w2;
			HagStringList list = new HagStringList(path+"\\"+w2,false,"xxssss",false);
			String str = list.convertToString("\n");
			String str1 = HagUtil.replaceStr(str, "{USER}", user);
			return str1;
		}else if(w1.equals("reqUtilityDev")){
			//return path+"<br>"+w2;
			HagStringList list = new HagStringList(path+"\\"+w2,false,"xxssss",false);
			String str = list.convertToString("\n");
			String str1 = HagUtil.replaceStr(str, "{USER}", user);
			return str1;
		}else if(	w1.equals("AccumulativeAddRequest")			){
			HagStringList list = new HagStringList(path+"\\"+w2,false,"xxssss",false);
			String str = list.convertToString("\n");
			String str1 = HagUtil.replaceStr(str, "{USER}", user);
			return str1;
		//}else if(w1.equals("ChangeUpdateNumberRi") ){
			//return HagUtil.runRiChangeUpdateNumber();
		}else if(w1.equals("ChangeMxx") ){
			return ChangeMxx.before(user);
		}else if(w1.equals("minorVersionsRel") ){
			return MinorVersions.before(user,"REL");
		}else if(w1.equals("minorVersionsPack") ){
			return MinorVersions.before(user,"PACK");
		}else if(w1.equals("minorVersionsNew") ){
			return MinorVersions.before(user,"NEW");
		}else if(w1.equals("minorVersionsLoadTask") ){
			return MinorVersions.before(user,"LOADTASK");
		}else if(w1.equals("ChangeMxxCm") ){
			return ChangeMxx.beforeCm(user);
		}else if(w1.equals("reqCrtNewEnv") ){
			return riCrtNewEnv( request,  response,w2, user);
		}else if(w1.equals("insertClientModuleMig") ){
			return insertClientModuleMig( request,  response,w2, user);
		}else if(w1.equals("ClientModuleBuild") ){
			return ClientModuleBuild.clientModuleBuild( request,  response,user);
		}else if(w1.equals("insertClientModuleMigFutureRelease") ){
			return ClientModule.insertClientModuleMig( request,  response,w2, user);
		}else if(w1.equals("insertScheduleFMig") ){
			return ScheduleF.insertScheduleFMig( request,  response,w2, user);
		}else if(w1.equals("reqRefreshEnv") ){
			return riRefreshEnv( request,  response,w2, user);
		}else if(w1.equals("reqRelease") ){
			return reqRelease( request,  response, user);
		}else if(w1.equals("reqReleaseNew") ){
			return HagReqRelease.before(  user);
		}else if(w1.equals("reqRelease4PreVersion") ){
			return PreVersion.reqRelease4PreVersion( request,  response, user);
		}else if(w1.equals("publicJiraCheck") ){
			return jiraCheckInit(user,pass);
		}else if(w1.equals("publicDebug1") ){
			return debug1(request, response);
		}else if(w1.equals("publicDebug2") ){
			return debug2(request, response);
		}else if(w1.equals("publicHagPrivate") ){
			return HagPrivate.before1(user);
		}else if(w1.equals("publicGetServersStatus") ){
			return getServersStatus(request, response);
		}else if(w1.equals("publicMailPassword") ){
			return mailPassword(user,pass);
		}else if(w1.equals("devReplaceJar") ){
			return runDevAct(request,response,"devReplaceJar",user,"Replace","Jar",".");
			//return devReplaceJar( request,  response,w2,user);
		}else if(w1.equals("devReplaceWar") ){
			return runDevAct(request,response,"devReplaceWar",user,"Replace","War",".");
		}else if(w1.equals("devReplaceJarI5os") ){
			return runDevActI5os(request,response,"devReplaceJarI5os",user,"Replace","Jar",".");
		}else if(w1.equals("devReplaceWarI5os") ){
			return runDevActI5os(request,response,"devReplaceWarI5os",user,"Replace","War",".");
		}else if(w1.equals("openCoreSplits") ){
			//return runDevAct(request,response,"openCoreSplits",user,"Set","CoreSplits","open");
			return HagUtil.future( request,  response,w2,user);
		}else if(w1.equals("closeCoreSplits") ){
			//return runDevAct(request,response,"closeCoreSplits",user,"Set","CoreSplits","close");
			return HagUtil.future( request,  response,w2,user);
		}else if(w1.equals("compileCustIom") ){
			return HagUtil.future( request,  response,w2,user);
			//return runDevAct(request,response,"compileCustIom",user,"Compile","CustIom",".");
		}else if(w1.equals("delRiLogs") ){
			//return devDeleteLogs( request,  response,w2,user);
			return runDevAct(request,response,"delRiLogs",user,"Delete","TomcatLogs",".");
		}else if(w1.equals("setLog4jDebug") ){
			//return future( request,  response,w2,user);
			return runDevAct(request,response,"setLog4jDebug",user,"Set","Log4jLevel","DEBUG");
		}else if(w1.equals("setLog4jError") ){
			//return future( request,  response,w2,user);
			return runDevAct(request,response,"setLog4jError",user,"Set","Log4jLevel","ERROR");
		}else if(w1.equals("setTomcatDebug") ){
			//return setTomcatOptDebug( request,  response,w2,user,"DEBUG");
			return runDevAct(request,response,"setTomcatDebug",user,"Set","TomcatLevel","DEBUG");
		}else if(w1.equals("setTomcatOpt") ){
			//return setTomcatOptDebug( request,  response,w2,user,"OPT");
			return runDevAct(request,response,"setTomcatOpt",user,"Set","TomcatLevel","OPT");
		}else if(w1.equals("dontMessWinEnvs") ){
			return HagForms.riEnvsDontMessForm(user,pass);
		
		}else if(w1.equals("RequestsStatus") ){//spr1008
			return RequestsStatus.requestsStatusForm(user,pass,null,w2);
		}else if(w1.equals("Requests4packagesLink") ){//spr1008
			return RequestsStatus.requestsStatusForm500(user,pass,null,w2);
		}else if(w1.equals("merge_and_build") ){
			return HagForms.mergeAndBuildPre(user,pass);
		}else if(w1.equals("powershell_debug") ){
			return HagDebug.powershellDebug_before(user,pass);
		//}else if(w1.equals("add_user") ){
		//	return HagAddUser.before_addUser(user);
		}else if(w1.equals("addCfgMenuWebUser") ){
			return HagAddUser.before_addCfgMenuWebUser(user);
		}else if(w1.equals("delCfgMenuWebUser") ){
			return HagAddUser.before_delCfgMenuWebUser(user);
		}else if(w1.equals("setAuthorizationsPerUser") ){
			return HagAddUser.before_setAuthorizationsPerUser(user);
		}else if(w1.equals("setUsersPerAuthorization") ){
			return HagAddUser.before_setUsersPerAuthorization(user);
		}else if(w1.equals("AZURE") ){
			return Azure.azure_before(user,pass);
		}else if(w1.equals("getDdcAsBcp") ){
			return cmGetDdcAsBcpMulti_before();
		}else if(w1.equals("runRemoteCommand") ){
			return runRemoteCommand_before();
		}else if(w1.equals("gonenTest") ){
			return HagDebug.gonenTestBefore();
		}else if(w1.equals("AddMigRequest") ){
			return addMigRequest( request,  response, user);
		}
		//	 String test1 = request.getSession().getServletContext().getRealPath("");
		//   String test2 = request.getSession().getServletContext().getRealPath("/");
		//   String test3 = request.getRealPath("");
		//   String test4 = request.getRealPath("/");
		//   String test5 = request.getSession().getServletContext().getRealPath(request.getServletPath());

		return "???";
	}
	public String 	jiraCheckInit(String cfgMenuWebUser,String cfgMenuWebPass){
		String txt = HagJira.getForm( cfgMenuWebUser,cfgMenuWebPass);
		return txt;
	}
	public  String 			filterRequestsLogs(HttpServletRequest request, HttpServletResponse response){
		String keyString 	= 	request.getParameter("keyString");
		
		return requestsLog(keyString);
	}
	public  final 			String requestsLog(String keyString){
		String whereLine="";
		if(keyString!=null) {
			 whereLine=" where LOWER(ind) like LOWER('%"+keyString+"%') or "+ 
					"LOWER(dateTime) like LOWER('%"+keyString+"%') or "+
					"LOWER(doneBy) like LOWER('%"+keyString+"%') or "+
					"LOWER(status) like LOWER('%"+keyString+"%') or "+
					"LOWER(subject) like LOWER('%"+keyString+"%') or "+
					"LOWER(dateTimeLastUpd) like LOWER('%"+keyString+"%') or "+
					"LOWER(customer) like LOWER('%"+keyString+"%') or "+
					"LOWER(tgtEnv) like LOWER('%"+keyString+"%') or "+
					"LOWER(note) like LOWER('%"+keyString+"%') or "+
					"LOWER(owner) like LOWER('%"+keyString+"%') or "+
					"LOWER(perPhone) like LOWER('%"+keyString+"%') or "+
					"LOWER(Req_Type) like LOWER('%"+keyString+"%') or "+
					"LOWER(Req_File) like LOWER('%"+keyString+"%') or "+
					"LOWER(Envs_To_Install) like LOWER('%"+keyString+"%') ";
		}
    	HagStringList ans3 = new	HagStringList(); 
    	
		ans3.add("<h2><u>Requests log:</u></h2>");
		
		
		ans3.add("<table bgColor=\"888888\" id=\"table2\" cellpadding=\"1\" cellspacing=\"1\" border=\"1\" >");
		ans3.add("<FORM METHOD=POST name=\"Form\" ACTION=\"filterRequestsLogs.jsp\">");
		ans3.add("<tr bgColor=\"888888\"><td>write key string:(case insensitive)</td>");
		if(keyString==null)
			ans3.add("<td><input type=\"text\" name=\"keyString\" id=\"keyString\"  maxlength=\"80\" size=\"80\"></td>");
		else
			ans3.add("<td><input type=\"text\" name=\"keyString\" id=\"keyString\" value = \""+keyString+"\" maxlength=\"80\" size=\"80\"></td>");
			
		ans3.add("<td><INPUT TYPE=SUBMIT value=\"Filter\" style=\"background-color:LightGreen\" ></FORM></td></tr>");
		ans3.add("</table><br>");
			
		
		
		
		ans3.add("<h3>Requests log results:</h3>");
	 	ans3.add("<table  bgcolor=\"#888888\" border=\"1\"  CELLPADDING=\"3\"  style=\"width:100%\">");
	 	ans3.add("<tr bgcolor=\"#888888\"><td >ind</td> <td >dateTime</td> <td>doneBy</td> <td>status</td><td>owner</td><td>subject</td><td>dateTimeLastUpd</td><td>customer</td><td>tgtEnv</td> <td>note</td><td>perPhone</td>");
	 	ans3.add("<td>Req_Type</td><td>Req_File</td><td>Envs_To_Install</td></tr>");
		String stm3 = "select ind," +
				"dateTime," +
				"doneBy," +
				"status," +
				"owner," +
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
		
		
		//System.out.println(stm3);
    	HagSQL hagSQL = new HagSQL();
    	HagStringList results = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm3,14,"|",null,".");
		for(int j=0;j<results.size();j++){
			String temp1 = results.get(j);
			String temp2 = temp1.replace("|","</td><td>");
		//	String w7 = HagUtil.getWord0(temp1,"|",7,true);
			//if(w7.equals("YES"))
			//	ans3.add("<tr bgcolor=\"#D8D8D8\"><td>"+temp2+"</td></tr>");
			//else
				ans3.add("<tr bgcolor=\"#bbbbbb\" ><td>"+temp2+"</td></tr>");
		}				
		ans3.add("</table>");
		String str3 = ans3.convertToString(" ");
		return str3;
	}
	
	public String backupDbWithNotesList() {
		//HagStringList files2= new HagStringList();
		String pthBackupLocation=HagUtil.getPropertyVal(HagUtil.cfgMenuWebLoc+"\\lists\\cfg.list", "dbBackupLocation");
		HagStringList files1 = HagUtil.getFilesInDir(pthBackupLocation);
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
				
				
				
		ans.append("<table  id=\"myTable\" bgcolor=\"#c0c0c0\" border=\"1\" >")
		.append("<tr ><td><img src=\"arrow_down.gif\"  onclick=\"sortTable(0,'d')\"   width=\"25\" height=\"20\"> <img src=\"arrow_up.gif\"  onclick=\"sortTable(0,'u')\"  width=\"25\" height=\"20\"> </td>"
				+ "<td><img src=\"arrow_down.gif\" onclick=\"sortTable(1,'d')\"  width=\"25\" height=\"20\"> <img src=\"arrow_up.gif\"  onclick=\"sortTable(1,'u')\"  width=\"25\" height=\"20\"> </td>"
				+ "<td><img src=\"arrow_down.gif\" onclick=\"sortTable(2,'d')\"  width=\"25\" height=\"20\"> <img src=\"arrow_up.gif\"  onclick=\"sortTable(2,'u')\"  width=\"25\" height=\"20\"></td>"
				+ "<td><img src=\"arrow_down.gif\" onclick=\"sortTable(3,'d')\"  width=\"25\" height=\"20\"> <img src=\"arrow_up.gif\"  onclick=\"sortTable(3,'u')\"  width=\"25\" height=\"20\"></td>"
				+ "<td><img src=\"arrow_down.gif\" onclick=\"sortTable(4,'d')\"  width=\"25\" height=\"20\"> <img src=\"arrow_up.gif\"  onclick=\"sortTable(4,'u')\"  width=\"25\" height=\"20\"></td>"
				+ "<td>.</td>"
				+ "<td>.</td>"
				+ "<td>.</td></tr>")
		.append("<tr><td width=\"150\">title</td>"
				+ "<td>done by</td>"
				+ "<td>sourceSQL</td>"
				+ "<td>sourceDB</td>"
				+ "<td>date</td>"
				+ "<td>file</td>"
				+ "<td>size</td>"
				+ "<td>note</td></tr>");
		for(int i = 0 ; i < files1.size();i++) {
			String temp = files1.get(i);
			
			if(temp.endsWith(".bkKeep") ) {
				String title = "aaaaa";
				String color = "bgcolor=\"#dcd3ff\" ";
				String tempNt = "cccc";
				if(temp.equals("\\lastInitial.bkKeep")) {
					title = "lastInitial";
					color = "bgcolor=\"#ff6688\" ";
					tempNt = HagUtil.replaceStr(temp,".bkKeep",".txt");
				}else {
					title = temp.substring(temp.lastIndexOf("_")+1,temp.length()-7);
					if(title.equals("Vanilla"))
						color = "bgcolor=\"#ffd5b8\" ";
					if(title.equals("Initial"))
						color = "bgcolor=\"#fff9aa\" ";
					if(title.equals("Setup"))
						color = "bgcolor=\"#acecd5\" ";
					tempNt = HagUtil.replaceStr(temp,".bkKeep",".txt");
				}
				
				HagStringList tempNote = new HagStringList(pthBackupLocation+tempNt,false,"dfafgfs",false);
				String sourceSql = ".";
				String sourceDB = ".";
				String date = ".";
				if(temp.indexOf("lastInitial.bkKeep")<0) {
					String sourceSql1 = HagUtil.getWord0(temp,"_",0,true);
					sourceSql = sourceSql1.substring(1,sourceSql1.length());
					 sourceDB = HagUtil.getWord0(temp,"_",1,true);
					String yyyymmdd = HagUtil.getWord0(temp,"_",2,true);
					String yy1=yyyymmdd.substring(0,4);
					String mm1=yyyymmdd.substring(4,6);
					String dd1=yyyymmdd.substring(6,8);
					String hh = HagUtil.getWord0(temp,"_",3,true);
					String mm = HagUtil.getWord0(temp,"_",4,true);
					String ss = HagUtil.getWord0(temp,"_",5,true);
					
					date = yy1+"/"+mm1+"/"+dd1+"-"+hh+":"+mm+":"+ss;
				}
				String noteText1 = tempNote.convertToString(", ");
				String doneBy=noteText1.substring(0, noteText1.indexOf(":"));
				String noteText=noteText1.substring(noteText1.indexOf(":")+1,noteText1.length());
				String size = HagUtil.getFileSize(pthBackupLocation+temp,'m');
				ans.append("<tr "+color+" ><td >"+title+"</td><td >"+doneBy+"</td><td>"+sourceSql+"</td><td>"+sourceDB+"</td><td>"+date+"</td><td>"+temp+"</td><td>"+size+"</td><td>"+noteText+"</td></tr>");
			}
		}
		ans.append("</table>");
		//	aa
		return ans.toString();
		
		
	}
	public static final String addNewCustomer_after(HttpServletRequest request, HttpServletResponse response){
		String cfgMenuWebUser 	= request.getParameter("cfgMenuWebUser");
		String shortName 	= request.getParameter("shortName");
		String longName 	= request.getParameter("longName");
		String partyId	= request.getParameter("partyId");
		
		String to = "david.hagay@sapiens.com;gonen.s@sapiens.com;"+cfgMenuWebUser+"@sapiens.com";
//		String ccList 	= HagUtil.getRiMails("all");
		String ccList 	= to;
		int ind = HagUtil.getRequestInd();
		String indS = HagUtil.padNumLeft(""+ind, 5, '0');
		String subject 	= "#Request to create new customer @BREAK-REQ@ #"+indS+"# sent by "+cfgMenuWebUser;
		StringBuilder ans = new StringBuilder();
		ans.append("<table bgcolor=\"#aaaacc\" border=\"1\">");
		ans.append("<tr bgColor=#737373><td>CUSTOMER_TITLE</td><td>CUSTOMER_CODE</td><td>PARTY_ID</td></tr>");
		ans.append("<tr><td>"+longName+"</td>");
		ans.append("<td>"+shortName+"</td>");
		ans.append("<td>"+partyId+"</td></tr>");
		ans.append("</table>");
		ans.append("<br>DEV team: need to add "+shortName+" folder to ri-web.war");
	//	String ans1		= HagUtil.sendMail_hag(HagUtil.cfgTeamMail,to,ccList,subject,ans.toString());
		
		String reqSub = "create new customer:CUSTOMER_TITLE="+longName+",CUSTOMER_CODE="+shortName+",PARTY_ID="+partyId;
		if(!HagUtil.addRequest(ind,cfgMenuWebUser, ".",reqSub,".",".",".",null,null,null,null,999))
			return HagUtil.shortHtml("Error adding request-mail sent to hagay & gonen, please call hagay or gonen", "red","bg");
		
		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps,HagUtil.mailList_hag,subject,ans.toString());
		if(ans1.startsWith("0~")) {
			return "Request sent"+"<br><br>"+ans.toString();
			
		}else {
			return HagUtil.shortHtml("Failed to send the request","red","bg");
		}
	}
	public String 	register(HttpServletRequest request, HttpServletResponse response){
		String user 	= request.getParameter("user");
		String pass 	= request.getParameter("password");
		String pass2 	= request.getParameter("password2");
		String [] auth	= request.getParameterValues("auth");


		//empty user
		if(user==null || user.trim().length()==0)
			return HagUtil.shortHtml("User field cannot be empty","red","bg");
		//empty pass
		if(pass==null || pass.trim().length()==0)
			return HagUtil.shortHtml("Password field cannot be empty","red","bg");

		//user exist
		HagStringList users=HagUtil.getUsers();
		if(	HagUtil.isInList(user, users, true, false))
			return HagUtil.shortHtml(user + " user already exists.","red","bg");

		//password not the same
		if(!pass.equals(pass2))
			return HagUtil.shortHtml("password confirmation and password are not the same value","red","bg");


		String authList="";
		if (auth != null && auth.length != 0) {
			for (int i = 0; i < auth.length; i++) {
				authList = authList + "<br>" + auth[i];
			}
		}
		String ans0 =HagUtil.addUser(user, pass);
		StringBuilder  content = new StringBuilder(user).append("<br>")
				.append(ans0).append("<br>")
				.append("authorizations:<br>").append(authList);
		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+user,HagUtil.mailList_hag,"cfgMenuWeb new user",content.toString());
		StringBuilder ans = new StringBuilder();
		if(ans0.startsWith("0~"))
			ans.append(user).append(" user created.<br>");
		else
			ans.append(user).append(" user failed to create.<br>");	

		if(ans1.startsWith("0~")){
			ans.append("Mail with the requested authorizations sent to hagay<br>authorizations:").append(authList).append("<br>")
			.append("you will receive mail from hagay when authorizations granted");
			return HagUtil.shortHtml(ans.toString(),"green","bg");
		}else{
			ans.append("Failed to send mail to hagay<br>")
			.append("please call hagay (2527)");
			return HagUtil.shortHtml(ans.toString(),"red","bg");
		}


	}	

	public String 	changePassword(HttpServletRequest request, HttpServletResponse response){
		String user 	= request.getParameter("user");
		String oldPass 	= request.getParameter("oldPass");
		String newPass 	= request.getParameter("passwordNew");
		String newPass2 	= request.getParameter("passwordNew2");
		//checkPass
		
		//empty user
		if(user==null || user.trim().length()==0)
			return HagUtil.shortHtml("User field cannot be empty","red","bg");
		//empty pass
		if(newPass==null || newPass.trim().length()==0)
			return HagUtil.shortHtml("Password field (new) cannot be empty","red","bg");
		if(newPass.trim().startsWith("."))
			return HagUtil.shortHtml("new Password cannot start with .","red","bg");

		//password not the same
		if(!newPass.equals(newPass2))
			return HagUtil.shortHtml("password confirmation and password are not the same value","red","bg");



		String ans0 =HagUtil.changePass(user, newPass);
		StringBuilder  content = new StringBuilder(user).append("<br>")
				.append(ans0).append("<br>");
		String ans1		=HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+user,HagUtil.mailList_hag,"cfgMenuWeb user changed password",content.toString());
		StringBuilder ans = new StringBuilder();
		if(ans0.startsWith("0~"))
			ans.append("password changed for ").append(user).append(" user.<br>");
		else
			ans.append(user).append(" user failed to change password.<br>");	

		if(ans1.startsWith("0~")){
			return HagUtil.shortHtml(ans.toString(),"green","bg");
		}else{
			ans.append("Failed to send mail to hagay<br>")
			.append("please call hagay (2527)");
			return HagUtil.shortHtml(ans.toString(),"red","bg");
		}


	}	


	public String 	authorizationsRequest(HttpServletRequest request, HttpServletResponse response){
		String user 	= request.getParameter("user");
		String [] auth	= request.getParameterValues("auth");
		String authList="";
		if (auth != null && auth.length != 0) {
			for (int i = 0; i < auth.length; i++) {
				authList = authList + "<br>" + auth[i];
			}
		}

		StringBuilder  content = new StringBuilder(user).append("<br>")
				.append("authorizations:<br>").append(authList);
		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+user,HagUtil.mailList_hag,"#cfgMenuWeb authorizationsRequest",content.toString());
		StringBuilder ans = new StringBuilder();
		if(ans1.startsWith("0~")){
			ans.append("Mail with the requested authorizations sent to hagay<br>authorizations:").append(authList).append("<br>")
			.append("you will receive mail from hagay when authorizations granted");
			return HagUtil.shortHtml(ans.toString(),"green","bg");
		}else{
			ans.append("Failed to send mail to hagay<br>")
			.append("please call hagay (2527)");
			return HagUtil.shortHtml(ans.toString(),"red","bg");
		}


	}	

	public String 	done(HttpServletRequest request, HttpServletResponse response){
		String value = request.getParameter( "value" );
		return value;
	}			
	public String 	dspFile1(HttpServletRequest request, HttpServletResponse response){
		String value = request.getParameter( "value" );
		String ff = HagUtil.cfgMenuWebLoc+"\\"+value;
		HagStringList list = new HagStringList(ff,false,"xxssss",false);
		return list.convertToString("\n");

	}	
	public String 	dspFile2(HttpServletRequest request, HttpServletResponse response){
		String value = request.getParameter( "value" );
		//String ff = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01tmp\\RI\\HagI5os\\txt\\"+value;
		String ff = HagUtil.cfgMenuWebLoc+"\\"+value;
		HagStringList list = new HagStringList(ff,false,"xxssss",false);
		return list.convertToString("\n");

	}	
	//////////////////////////////////////////////////////////
	public String 	accumulativeAddRequest(HttpServletRequest request, HttpServletResponse response){
		String eMergeVersion 	= request.getParameter("eMergeVersion");
		String eMergeAdd	 	= request.getParameter("eMergeAdd");
		String add_description 	= request.getParameter("add_description");
		String appendToMail 	= request.getParameter("appendToMail");
		String done_by 			= request.getParameter("done_by");
		String action			= request.getParameter("action");
		String [] cc			= request.getParameterValues("cc");

		if(action.equals("Request to pack add") && HagUtil.isEmptyString(add_description))
			return HagUtil.shortHtml("You must enter add description","red","bg");

		String ccList="david.ha@sapiens.com";
		if (cc != null && cc.length != 0) {
			for (int i = 0; i < cc.length; i++) {
				ccList = ccList + ";" + cc[i];
			}
		}	


		String [] ans = {".",".","."};
		HagUtil.getMailList( eMergeVersion,eMergeAdd,action,add_description,ans);

	//	String from		= HagUtil.cfgTeamMail;
		String to		= ans[0];
		String toCc		= ccList;
		//String to="david.ha@sapiens.com";
		//String toCc="david.ha@sapiens.com";

		String link 	= "http://cfg-ri:8080/hagay/index"+eMergeVersion+".html";
		String subject	= "# "+eMergeVersion+", Add=" + eMergeAdd + ", platfom=ALL , step=" + action + ", sent by=" + done_by;
		String content 	= HagUtil.getContentBisAdd(eMergeVersion,eMergeAdd,add_description,action,
				"Accumulative",done_by,appendToMail,link,to,ccList); 
		String ans1 = HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+done_by,HagUtil.mailList_hag,subject,content);
		String ff 	= HagUtil.cfgMenuWebLoc+"\\AccumulativeAddLogs"+eMergeVersion+".html";
		String ans2 = HagUtil.updateLogFile(eMergeVersion,eMergeAdd,action,done_by,add_description,ff,ans);


		if(!ans1.startsWith("0~"))
			content=content+"<br>"+ans1;
		if(!ans2.startsWith("0~"))
			content=content+"<br>"+ans2;

		try {

			String content1=HagUtil.replaceStr(content,"&" ,"");
			response.sendRedirect("done.jsp?value=The following request sent by mail:<br><br>"+content1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content+"<br><br>Mail sent to:"+to+"<br>cc:"+toCc;
	}
	//////////////////////////////////////////////////////////
	public String 	riFtpPackRequest(HttpServletRequest request, HttpServletResponse response,String type){
//		if(type.endsWith("update"))
			return riFtpPackRequest_update( request,  response);
//		else
	//		return riFtpPackRequest_hotfix( request,  response);
	}	
	public boolean isEnvExist(String bisServer,String batchName){
		HagSQL hagSQL = new HagSQL();
		String stm = "select bis_server  from dbo.ri_environments_new where status='A' and project <> 'HAGWIDTH' and bis_server ='"+bisServer+"' and batchName='"+batchName+"'";
		ArrayList<String> ans = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm);
		if (	ans == null || ans.size()==0) 
			return  false;
		else
			return true;
	}
	public boolean getLastInstalled(String bisServer,String batchName){
		HagSQL hagSQL = new HagSQL();
		String stm = "select bis_server  from dbo.ri_environments_new where status='A' and project <> 'HAGWIDTH' and bis_server ='"+bisServer+"' and batchName='"+batchName+"'";
		ArrayList<String> ans = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm);
		if (	ans == null || ans.size()==0) 
			return  false;
		else
			return true;
	}

	public String 	riCrtNewEnv(HttpServletRequest request, HttpServletResponse response,String type){
		String sentBy 	= request.getParameter("sentBy").trim();
		String from 	= request.getParameter("from").trim();
		String to1 		= request.getParameter("to1").trim();
		String to2 		= request.getParameter("to2");
		String target 	= request.getParameter("target").trim();
		if(target.endsWith("-"))
			target=target.substring(0,target.length()-1);
		String note 	= request.getParameter("note").trim();
		String mailList = "david.ha@sapiens.com;gonen.s@sapiens.com;david.b@sapiens.com";
		String ccList 	= HagUtil.getRiMails("all");
		String server1 = HagUtil.getWord0(from,",",0,true);
		server1 = server1.substring(4,server1.length()-1);
		String dbid1 = HagUtil.getWord0(from,",",1,true);
		dbid1 = dbid1.substring(5,dbid1.length()-1);
		String cust1 = HagUtil.getWord0(from,",",3,true);
		cust1 = cust1.substring(9,cust1.length()-1);
		int ind = HagUtil.getRequestInd();
		
		String indS = HagUtil.padNumLeft(""+ind, 5, '0');
		String title1 	= "#Request for NEW "+type+" ENV "+server1+"/"+dbid1+"("+cust1+") -> "+to1+"/"+to2+" <"+target+"> @BREAK-REQ@ #"+indS+"# sent by "+sentBy;
	
		if(to2==null || to2.trim().length()==0)
			return HagUtil.shortHtml("Error: new environment name cannot be empty", "red", "bg");
		if(to2.length()!=2)
			return HagUtil.shortHtml("Error: new environment name length must be 2 characters", "red", "bg");
		String nums="0123456789";
		if(nums.indexOf(to2.substring(0,1))>-1 || nums.indexOf(to2.substring(1,2))>-1)
			return HagUtil.shortHtml("Error: new environment name cannot contain digits", "red", "bg");

		if(!HagUtil.addRequest(ind,sentBy, ".",title1,to1+"/"+to2,cust1,".",null,null,null,null,999))
			return HagUtil.shortHtml("Error adding request-mail sent to hagay & gonen, please call hagay or gonen", "red","bg");
			to2=to2.trim().toUpperCase();
		if(isEnvExist(to1,to2))
			return HagUtil.shortHtml("Error: "+to1+"->"+to2+" already exist", "red", "bg");
		String from9a = HagUtil.convertServersNameDsp(from);
		String to1a = HagUtil.convertServersNameDsp(to1);
		
		StringBuilder ans = new StringBuilder()
		.append(title1).append("<br><br>")
		.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:70%\">")
		.append("<tr><td bgcolor=\"#ccccaa\">From</td><td>").append(from9a).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">To</td><td>").append(to1a).append(" -> ").append(to2).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Target version</td><td>").append(target).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Note</td><td>").append(note).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Sent by</td><td>").append(sentBy).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Sent to</td><td>").append(mailList.replace(";","<br>")).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">CC</td><td>").append(ccList.replace(";","<br>")).append("</td></tr>")
		.append("</table>");
		String ans1 = ans.toString();

	//	mailList="david.ha@sapiens.com";
	//	ccList="david.ha@sapiens.com";
		
		String ans2		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+sentBy,HagUtil.mailList_hag,title1,ans1);
		HagUtil.writeToRelDiary2("CrtNewEnv","WIN","Request",".",".",".",".",".","#"+indS,sentBy,to1,to2);
		return ans1+"<br><br>"+ans2;
	}

	public String 	riRefreshEnv(HttpServletRequest request, HttpServletResponse response,String type){
		String sentBy 	= request.getParameter("sentBy").trim();
		String from 	= request.getParameter("from").trim();
		String to 		= request.getParameter("to").trim();
		String target 	= request.getParameter("target").trim();
		String note 	= request.getParameter("note").trim();
		String mailList = "david.ha@sapiens.com;gonen.s@sapiens.com;david.b@sapiens.com";
		String ccList 	= HagUtil.getRiMails("all");
		String server1 = HagUtil.getWord0(from,",",0,true);
		server1 = server1.substring(4,server1.length()-1);
		String dbid1 = HagUtil.getWord0(from,",",1,true);
		dbid1 = dbid1.substring(5,dbid1.length()-1);
		String cust1 = HagUtil.getWord0(from,",",3,true);
		cust1 = cust1.substring(9,cust1.length()-1);

		String server2 = HagUtil.getWord0(to,",",0,true);
		server2 = server2.substring(4,server2.length()-1);
		String dbid2 = HagUtil.getWord0(to,",",1,true);
		dbid2 = dbid2.substring(5,dbid2.length()-1);
		String cust2 = HagUtil.getWord0(to,",",3,true);
		cust2 = cust2.substring(9,cust2.length()-1);

		int ind = HagUtil.getRequestInd();
		
		String indS = HagUtil.padNumLeft(""+ind, 5, '0');
		String note2 = ".";
		if (note!=null && note.trim().length()>0)
			note2=note;
		String title1 	= "#Request for REFRESH "+type+" ENV "+server1+"/"+dbid1+"("+cust1+") -> "+server2+"/"+dbid2+"("+cust2+") <"+target+"> @BREAK-REQ@ #"+indS+"# sent by "+sentBy;
		if(!HagUtil.addRequest(ind,sentBy, note2,title1,server2+"/"+dbid2,cust1,".",null,null,null,null,999))
			return HagUtil.shortHtml("Error adding request-mail sent to hagay & gonen, please call hagay or gonen", "red","bg");
		//String title 	= "Request to <font color=#blue>Refresh RI-"+type+" environment</font> sent.";
		if(from.equals(to))
			return HagUtil.shortHtml("Error: source environment and target environment are the same", "red", "bg");

		StringBuilder ans = new StringBuilder()
		.append(title1).append("<br><br>")
		.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:70%\">")
		.append("<tr><td bgcolor=\"#ccccaa\">From</td><td>").append(from).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">To</td><td>").append(to).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Target version</td><td>").append(target).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Note</td><td>").append(note).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Sent by</td><td>").append(sentBy).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Sent to</td><td>").append(mailList.replace(";","<br>")).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">CC</td><td>").append(ccList.replace(";","<br>")).append("</td></tr>")
		.append("</table>");
		String ans1 = ans.toString();
		String ans2		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+sentBy,HagUtil.mailList_hag,title1,ans1);
		HagUtil.writeToRelDiary2("RefreshEnv","WIN","Request",".",".",".",".",".","#"+indS,sentBy,server2,dbid2);
		return ans1+"<br><br>"+ans2;
	}
	public String 	riReleaseRequest(HttpServletRequest request, HttpServletResponse response){

		String sentBy 	= request.getParameter("sentBy").trim();
		String jiraVer 	= request.getParameter("jiraVer").trim();


		return HagForms.reqReleaseSend(jiraVer,sentBy);
		/*
		String sentBy 	= request.getParameter("sentBy").trim();
		String jiraVer 	= request.getParameter("jiraVer").trim();
		String note 	= request.getParameter("note").trim();
		String mailList = "david.ha@sapiens.com;gonen.s@sapiens.com;david.b@sapiens.com";
		String ccList 	= HagUtil.getRiMails();
		String server1 = HagUtil.getWord0(from,",",0,true);
		server1 = server1.substring(4,server1.length()-1);
		String dbid1 = HagUtil.getWord0(from,",",1,true);
		dbid1 = dbid1.substring(5,dbid1.length()-1);
		String cust1 = HagUtil.getWord0(from,",",3,true);
		cust1 = cust1.substring(9,cust1.length()-1);

		String server2 = HagUtil.getWord0(to,",",0,true);
		server2 = server2.substring(4,server2.length()-1);
		String dbid2 = HagUtil.getWord0(to,",",1,true);
		dbid2 = dbid2.substring(5,dbid2.length()-1);
		String cust2 = HagUtil.getWord0(to,",",3,true);
		cust2 = cust2.substring(9,cust2.length()-1);



		String title1 	= "#Request for REFRESH "+type+" ENV "+server1+"/"+dbid1+"("+cust1+") -> "+server2+"/"+dbid2+"("+cust2+") @BREAK-REQ@ sent by "+sentBy;

		//String title 	= "Request to <font color=#blue>Refresh RI-"+type+" environment</font> sent.";
		if(from.equals(to))
			return HagUtil.shortHtml("Error: source environment and target environment are the same", "red", "bg");

		StringBuilder ans = new StringBuilder()
		.append(title1).append("<br><br>")
		.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:70%\">")
		.append("<tr><td bgcolor=\"#ccccaa\">From</td><td>").append(from).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">To</td><td>").append(to).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Target version</td><td>").append(target).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Note</td><td>").append(note).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Sent by</td><td>").append(sentBy).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">Sent to</td><td>").append(mailList.replace(";","<br>")).append("</td></tr>")
		.append("<tr><td bgcolor=\"#ccccaa\">CC</td><td>").append(ccList.replace(";","<br>")).append("</td></tr>")
		.append("</table>");
		String ans1 = ans.toString();
		String ans2		= HagUtil.sendMail_hag("cfgWeb@sapiens.com",mailList,ccList,title1,ans1);

		return ans1+"<br><br>"+ans2;
		 */
	}
	
	
	public String 	cmGetDdcAsBcpMulti_before(){
		String ddcAsBcp=HagUtil.getPropertyVal(HagUtil.cfgMenuWebLoc+"\\lists\\cfg.list", "ddcAsBcp"); 
		String server = HagUtil.getWord0(ddcAsBcp, "~", 0, true);
		String dbid = HagUtil.getWord0(ddcAsBcp, "~", 1, true);
		String from = HagUtil.getEnvLine("from",server,dbid);
		StringBuilder sb = new StringBuilder("<HTML><body bgcolor=\"#ccccbb\">")	
		.append("<FORM METHOD=POST name=\"Form\" ACTION=\"cmGetDdcAsBcpMulti.jsp\">")
		.append("<table bgcolor=\"#aaaacc\" border=\"1\" style=\"width:100%\">")
		.append("<tr><td bgcolor=\"#ccccaa\">DDC from</td><td>").append(from).append("</td></tr>")
		.append("</table><br><INPUT TYPE=SUBMIT value=\"Submit\"   onclick=\"document.getElementById('loader').style.display = 'block';\" ></FORM>")
		.append("<table bgColor=\"#ffff00\" cellpadding=\"3\" cellspacing=\"3\" border=\"1\">")
		.append("<tr><td><img id=\"loader\" src=\"Please_wait.gif\" style=\"display: none;\"/></td></tr>")
		.append("</table></BODY>");
		return sb.toString();
	
	
	}
	public String cmGetDdcAsBcpMulti(HttpServletRequest request, HttpServletResponse response){
		String ddcFrom 	= request.getParameter("from").trim();
		String ddcFromApp= HagUtil.getWord0(ddcFrom,",",0,true);
		String ddcFromDb1= HagUtil.getWord0(ddcFrom,",",1,true);
		String ddcFromSql1= HagUtil.getWord0(ddcFrom,",",2,true);
		String ddcFromDb= ddcFromDb1.substring(ddcFromDb1.indexOf("(")+1,ddcFromDb1.indexOf(")"));
		String ddcFromSql= ddcFromSql1.substring(ddcFromSql1.indexOf("(")+1,ddcFromSql1.indexOf(")"));
		HagStringList ans = new HagStringList();
		ans.add("<table bgColor=\"#dddddd\" cellpadding=\"3\" border =\"1\">");
		HagRc hagRcGetDdc = new HagRc();
		//String ddcFolder = "\\\\ri-archive\\Saptech-Archive\\RI\\work\\DDC";
		String ddcFolder = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\archive_temp\\RI\\work\\DDC";
		HagUtil.reCreateFolder(hagRcGetDdc, ddcFolder);
	
		if(hagRcGetDdc.rc!=0)
			ans.add( "<tr><td>recreate DDC folder</td><td bgColor=\"#FF0000\">Failed to recreate "+ddcFolder+"<br>"+hagRcGetDdc.log.convertToString("<BR>")+"</td></tr>");
		else
			ans.add( "<tr><td>recreate DDC folder</td><td bgColor=\"#00ff00\">"+ddcFolder+" recreated<br>"+hagRcGetDdc.log.convertToString("<BR>")+"</td></tr>");
		
				
	
	
		ans.add("<tr><td>create CO0 split</td>"+cmGetDdcAsBcpSingle( 		ddcFolder,  "RI",  		"CO0",  	ddcFromSql,  ddcFromDb, hagRcGetDdc)+"</tr>");
		ans.add("<tr><td>create CO1 split</td>"+cmGetDdcAsBcpSingle( 		ddcFolder,  "RI",  		"CO1",  	ddcFromSql,  ddcFromDb, hagRcGetDdc)+"</tr>");
		ans.add("<tr><td>create CAT split</td>"+cmGetDdcAsBcpSingle( 		ddcFolder,  "RI",  		"CAT",  	ddcFromSql,  ddcFromDb, hagRcGetDdc)+"</tr>");
		ans.add("<tr><td>create CT split</td>"+cmGetDdcAsBcpSingle( 		ddcFolder,  "RI",  		"CT",  		ddcFromSql,  ddcFromDb, hagRcGetDdc)+"</tr>");
		ans.add("<tr><td>create CTCAT split</td>"+cmGetDdcAsBcpSingle( 		ddcFolder,  "RI",  		"CTCAT",  	ddcFromSql,  ddcFromDb, hagRcGetDdc)+"</tr>");
		ans.add("<tr><td>create CTCM1 split</td>"+cmGetDdcAsBcpSingle( 		ddcFolder,  "RI",  		"CTCM1",  	ddcFromSql,  ddcFromDb, hagRcGetDdc)+"</tr>");
		ans.add("<tr><td>create CTCMN split</td>"+cmGetDdcAsBcpSingle( 		ddcFolder,  "RI",  		"CTCMN",  	ddcFromSql,  ddcFromDb, hagRcGetDdc)+"</tr>");
		ans.add("<tr><td>create CTCTU split</td>"+cmGetDdcAsBcpSingle( 		ddcFolder,  "RI",  		"CTCTU",  	ddcFromSql,  ddcFromDb, hagRcGetDdc)+"</tr>");
		ans.add("<tr><td>create CTCU0 split</td>"+cmGetDdcAsBcpSingle( 		ddcFolder,  "RI",  		"CTCU0",  	ddcFromSql,  ddcFromDb, hagRcGetDdc)+"</tr>");
		ans.add("<tr><td>create CTCU1 split</td>"+cmGetDdcAsBcpSingle( 		ddcFolder,  "RI", 		"CTCU1", 	ddcFromSql,  ddcFromDb, hagRcGetDdc)+"</tr>");
		ans.add("<tr><td>create CTDAT split</td>"+cmGetDdcAsBcpSingle( 		ddcFolder,  "RI",  		"CTDAT", 	ddcFromSql,  ddcFromDb, hagRcGetDdc)+"</tr>");
		ans.add("<tr><td>create CTDLK split</td>"+cmGetDdcAsBcpSingle( 		ddcFolder,  "RI",  		"CTDLK", 	ddcFromSql,  ddcFromDb, hagRcGetDdc)+"</tr>");
		ans.add("<tr><td>create CTENU split</td>"+cmGetDdcAsBcpSingle( 		ddcFolder,  "RI", 		"CTENU",  	ddcFromSql,  ddcFromDb, hagRcGetDdc)+"</tr>");
		ans.add("<tr><td>create CTJSQ split</td>"+cmGetDdcAsBcpSingle( 		ddcFolder,  "RI",  		"CTJSQ",  	ddcFromSql,  ddcFromDb, hagRcGetDdc)+"</tr>");
		ans.add("<tr><td>create CTLCL split</td>"+cmGetDdcAsBcpSingle( 		ddcFolder,  "RI",  		"CTLCL",  	ddcFromSql,  ddcFromDb, hagRcGetDdc)+"</tr>");
		ans.add("<tr><td>create CTLEX split</td>"+cmGetDdcAsBcpSingle( 		ddcFolder,  "RI",  		"CTLEX",  	ddcFromSql,  ddcFromDb, hagRcGetDdc)+"</tr>");
		ans.add("<tr><td>create CTSEC split</td>"+cmGetDdcAsBcpSingle( 		ddcFolder,  "RI",  		"CTSEC",  	ddcFromSql,  ddcFromDb, hagRcGetDdc)+"</tr>");
		ans.add("<tr><td>create CTSWS split</td>"+cmGetDdcAsBcpSingle( 		ddcFolder,  "RI",  		"CTSWS",  	ddcFromSql,  ddcFromDb, hagRcGetDdc)+"</tr>");
		ans.add("<tr><td>create PR4530EN split</td>"+cmGetDdcAsBcpSingle( 	ddcFolder,  "SAPIENS",  "PR4530EN", ddcFromSql,  ddcFromDb, hagRcGetDdc)+"</tr>");
		ans.add("<tr><td>create PR4530ENJ split</td>"+cmGetDdcAsBcpSingle( 	ddcFolder,  "SAPIENS",  "PR4530ENJ",ddcFromSql,  ddcFromDb, hagRcGetDdc)+"</tr>");
		ans.add("<tr><td>create PR4530ENL split</td>"+cmGetDdcAsBcpSingle( 	ddcFolder,  "SAPIENS",  "PR4530ENL",ddcFromSql,  ddcFromDb, hagRcGetDdc)+"</tr>");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//if(hagRcGetDdc.rc!=0)	
		//	return "<td bgColor=\"#FF0000\">Failed to get DDC as bcp "+ddcFolder+"<br>"+hagRcGetDdc.log.convertToString("<BR>")+"</td>";
	
		ans.add("</table>");
		
		return ans.convertToString("");
		
	}
	
	public String cmGetDdcAsBcpSingle(String ddcFolder, String creator, String split, String ddcFromSql1, String ddcFromDb1,HagRc hagRcGetDdc){
	
		StringBuilder sb = new StringBuilder();
		String del1 = HagUtil.deleteFile(ddcFolder+"\\"+split+".DDC",  false);
		String del2 = HagUtil.deleteFile(ddcFolder+"\\"+split+".FMT",  false);
		String del3 = HagUtil.deleteFile(ddcFolder+"\\"+split+"_DDC_log.txt",  false);
		String del4 = HagUtil.deleteFile(ddcFolder+"\\"+split+"_FMT_log.txt",  false);
	
		if(!del1.startsWith("0~") || !del2.startsWith("0~") || !del3.startsWith("0~") || !del4.startsWith("0~")){
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to delete "+split+" old files from "+ddcFolder+"</td>";
			hagRcGetDdc.log.add(ansTemp2);
			hagRcGetDdc.rc=1;
			return ansTemp2;
		}
		HagUtil.crtBcps( ddcFolder+"\\", ddcFromDb1, creator, split, ddcFromSql1, hagRcGetDdc);
		if(hagRcGetDdc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CO0 bcp file<br>"+hagRcGetDdc.log.get(hagRcGetDdc.log.size()-1) +"</td>";
			hagRcGetDdc.log.add(ansTemp2);
			hagRcGetDdc.rc=1;
			return ansTemp2;
		}else {
			sb.append("<td bgColor=\"#00ff00\">"+split+" bcp files done</td>");
		}
		return sb.toString();
	}
	
	

	
	public String 	riDevAddMigRequest(HttpServletRequest request, HttpServletResponse response){

		
		String jClass 		= request.getParameter("jClass").trim();
		String jiraTask 	= request.getParameter("jiraTask").trim();
		String sentBy 		= request.getParameter("sentBy").trim();
		//String jiraVer 		= request.getParameter("jiraVer").trim();
		String note 		= request.getParameter("note").trim();
		String runTypeHotfix 		= request.getParameter("runTypeHotfix").trim();
		String runTypeMajor 		= request.getParameter("runTypeMajor").trim();
		String location 		= request.getParameter("location").trim();
		//String dist 		= request.getParameter("dist").trim();
		
		String [] cbgroup1		= request.getParameterValues("cb1");
		if(jClass.indexOf("-")>-1)
			return HagUtil.shortHtml("You must not use '-' character in the jClass name","red","bg");
		
		if(cbgroup1==null || cbgroup1.length==0)
			return HagUtil.shortHtml("You must select at least one version","red","bg");

		return HagForms.requestDevAddMigRequestSent(sentBy,jClass,jiraTask,note,runTypeHotfix,runTypeMajor,location,cbgroup1,null);
}
public String 	deleteFromMigDetails_insert(HttpServletRequest request, HttpServletResponse response){
	String sentBy 		= request.getParameter("sentBy").trim();
	String cfgMenuWebPass 	= request.getParameter("cfgMenuWebPass");
	String auth = HagUtil.isAuthorized("deleteFromMigDetails_insert",sentBy,cfgMenuWebPass);
	if(!auth.equals("OK")){
		return HagUtil.shortHtml(auth , "red", "bg");
	}
	
	
		String ver 	= request.getParameter("ver").trim();
		String ver1 = HagUtil.getWord0(ver,"|" ,0,true);
	
		String note 		= request.getParameter("note").trim();
		String mig 		= request.getParameter("mig").trim();
		String location 		= request.getParameter("location").trim();
		String customer 		= request.getParameter("customer").trim();
		if(mig==null || mig.trim().length()==0)
			return HagUtil.shortHtml("you must insert the mig name","red","bg");
		if(note==null || note.trim().length()==0)
			return HagUtil.shortHtml("you must insert short note","red","bg");

		
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
		if(list.size()>0)
			return HagUtil.shortHtml("Mig "+mig + " already in.","red","bg");
		
		
		
		String date = HagUtil.getDateTime("dd/MM/yyyy-HH:mm:ss");
		String[] vals=new String[9];
		vals[0]="'"+ver1+"'";
		vals[1]="'"+mig+"'";
		vals[2]="'"+location+"'";
		vals[3]="'"+customer+"'";
		vals[4]="'"+sentBy+"'";
		vals[5]="'"+date+"'";
		vals[6]="'ON'";
		vals[7]="'YES'";
		vals[8]="'"+note+"'";
		String ans= hagSql.insertInto("confg1","cfg","cfg09c",HagParam.getConfg1DB(),"dbo.DeleteFromMigDetails",vals);
		
		
		
		if(!ans.startsWith("0~")) 
			return HagUtil.shortHtml(ans,"red","bg");		
			
		
		
		String to = "david.hagay@sapiens.com;gonen.s@sapiens.com;"+sentBy+"@sapiens.com";
		String ccList 	= HagUtil.getRiMails("all");
//		String ccList 	= to;
		int ind = HagUtil.getRequestInd();
		
		String subject 	= "Insert "+mig+" mig into deleteFromMigDetails "+ver1+" list done by "+sentBy;
		String content 	= "location: "+location+", customer="+customer;
		HagUtil.writeToRelDiary2("Insert","WIN","deleteFromMigDetails",ver1,"00","OK",".",".",mig,sentBy,".",".");
		String ans1		=HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+sentBy,HagUtil.mailList_hag,subject,content); 
		if(!ans1.startsWith("0~")) 
			return HagUtil.shortHtml("Failed to send mail.<br>"+ans1, "red","bg");
			
		return HagUtil.shortHtml(ans,"green","bg");
	
}

public String 	deleteFromMigDetails_delete(HttpServletRequest request, HttpServletResponse response){
	String cfgMenuWebUser 		= request.getParameter("cfgMenuWebUser").trim();
	String cfgMenuWebPass 	= request.getParameter("cfgMenuWebPass");
	String auth = HagUtil.isAuthorized("deleteFromMigDetails_delete",cfgMenuWebUser,cfgMenuWebPass);
	if(!auth.equals("OK")){
		return HagUtil.shortHtml(auth , "red", "bg");
	}
	
	
	String [] cbgroup1		= request.getParameterValues("cb");
	HagSQL hagSql=new HagSQL();
	HagStringList ansList = new HagStringList(); 
	for(int i = 0 ; i< cbgroup1.length;i++) {
		String temp = cbgroup1[i];
		String relevant = HagUtil.getWord0(temp, "~",0,true);
		String version 		= HagUtil.getWord0(temp, "~",1,true);
		String jclass 		= HagUtil.getWord0(temp, "~",2,true);
		String location = HagUtil.getWord0(temp, "~",3,true);
		String customer = HagUtil.getWord0(temp, "~",4,true);
		String owner 	= HagUtil.getWord0(temp, "~",5,true);
		String date 	= HagUtil.getWord0(temp, "~",6,true);
		String status 	= HagUtil.getWord0(temp, "~",7,true);
		String note 	= HagUtil.getWord0(temp, "~",8,true);
		String date1 	= HagUtil.getDateTime("dd/MM/yyyy-HH:mm:ss");
	
		StringBuilder where = new StringBuilder(" where ")
				.append("version='").append(version).append("' AND ")
				.append("jclass='").append(jclass).append("' AND ")
				.append("location='").append(location).append("' AND ")
				.append("customer='").append(customer).append("' AND ")
				.append("owner='").append(owner).append("' AND ")
				.append("date='").append(date).append("' AND ")
				.append("status='").append(status).append("' AND ")
				.append("relevant='").append(relevant).append("' AND ")
				.append("note='").append(note).append("' ");
			
		StringBuilder stm4= new StringBuilder("Update "+HagParam.getConfg1DB()+".[dbo].[DeleteFromMigDetails] ")
				.append("set ")
				.append("relevant='NO'")
				.append(",status='OFF'")
				.append(",note='deleted by ").append(cfgMenuWebUser).append(" at ").append(date1).append("' ")
				.append(where.toString());
		
				String stm4S = stm4.toString();
				int ans4i =hagSql.updateStm("confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm4S);
				if(ans4i>0)
					ansList.add("<tr><td bgColor=\"#00FF00\">"+jclass+" mig removed from DeleteFromMigDetails table.</td></tr>");
				else
					ansList.add("<tr><td bgColor=\"#FF0000\">failed to remove "+jclass+" mig from DeleteFromMigDetails table.</td></tr>");
				
				
				
				
				
				String to = "david.hagay@sapiens.com;gonen.s@sapiens.com;"+cfgMenuWebUser+"@sapiens.com";
				String ccList 	= HagUtil.getRiMails("all");
//				String ccList 	= to;
				int ind = HagUtil.getRequestInd();
				
				String subject 	= "Delete "+jclass+" mig from deleteFromMigDetails "+version+" list done by "+cfgMenuWebUser;
				String content 	= "location: "+location+", customer="+customer+", mig original owner="+owner;
				HagUtil.writeToRelDiary2("Delete","WIN","deleteFromMigDetails",version,"00","OK",".",".",jclass,cfgMenuWebUser,".",".");
				String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+cfgMenuWebUser,HagUtil.mailList_hag,subject,content); 
				if(!ans1.startsWith("0~")) 
					ansList.add("<tr><td bgColor=\"#FF0000\">Failed to send mail.<br>"+ans1+"</td></tr>");
				
	}
	String ans = "<table>"+ansList.convertToString(" ")+"</table>";
	return ans;
	
	//if(!ans.startsWith("0~")) 
	//	return HagUtil.shortHtml(ans,"red","bg");		
		
	
	

		
	//return HagUtil.shortHtml(ans,"green","bg");
	
	
	
	
	
	
	/*
	String ver 	= request.getParameter("ver").trim();
	String ver1 = HagUtil.getWord0(ver,"|" ,0,true);
	String cfgMenuWebUser 		= request.getParameter("cfgMenuWebUser").trim();
	String note 		= request.getParameter("note").trim();
	String mig 		= request.getParameter("mig").trim();
	String location 		= request.getParameter("location").trim();
	String customer 		= request.getParameter("customer").trim();
	if(mig==null || mig.trim().length()==0)
		return HagUtil.shortHtml("you must insert the mig name","red","bg");
	if(note==null || note.trim().length()==0)
		return HagUtil.shortHtml("you must insert short note","red","bg");
f
	
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
	if(list.size()>0)
		return HagUtil.shortHtml("Mig "+mig + " already in.","red","bg");
	
	
	
	String date = HagUtil.getDateTime("dd/MM/yyyy-HH:mm:ss");
	String[] vals=new String[9];
	vals[0]="'"+ver1+"'";
	vals[1]="'"+mig+"'";
	vals[2]="'"+location+"'";
	vals[3]="'"+customer+"'";
	vals[4]="'"+sentBy+"'";
	vals[5]="'"+date+"'";
	vals[6]="'ON'";
	vals[7]="'YES'";
	vals[8]="'"+note+"'";
	String ans= hagSql.insertInto("confg1","cfg","cfg09c",HagParam.getConfg1DB(),"dbo.DeleteFromMigDetails",vals);
	if(ans.startsWith("0~"))
		return HagUtil.shortHtml(ans,"green","bg");
	else
		return HagUtil.shortHtml(ans,"red","bg");
		*/
}


public String 	clientModuleAddMigRequest(HttpServletRequest request, HttpServletResponse response){

		
		String jClass 		= request.getParameter("jClass").trim();
		String jiraTask 	= request.getParameter("jiraTask").trim();
		String sentBy 		= request.getParameter("sentBy").trim();
		//String jiraVer 		= request.getParameter("jiraVer").trim();
		String note 		= request.getParameter("note").trim();
		String runTypeHotfix 		= request.getParameter("runTypeHotfix").trim();
		//String dist 		= request.getParameter("dist").trim();
		
		String [] cbgroup1		= request.getParameterValues("cb1");
		if(cbgroup1==null || cbgroup1.length!=1)
			return HagUtil.shortHtml("You must select  one clientModule version","red","bg");

		return HagForms.requestClientModuleAddMigRequestSent(sentBy,jClass,jiraTask,note,runTypeHotfix,cbgroup1,null);
		
//		return HagForms.requestDevAddMigRequestSent(sentBy,jClass,jiraTask,note,runTypeHotfix,runTypeMajor,location,cbgroup1,null);
}
	/*
	public String 	riDevAddMigRequest1(HttpServletRequest request, HttpServletResponse response){

		String jClass 		= request.getParameter("jClass").trim();
		String jiraTask 	= request.getParameter("jiraTask").trim();
		String sentBy 		= request.getParameter("sentBy").trim();
		String jiraVer 		= request.getParameter("jiraVer").trim();
		String note 		= request.getParameter("note").trim();
		String runType 		= request.getParameter("runType").trim();
		String location 		= request.getParameter("location").trim();
		String dist 		= request.getParameter("dist").trim();
		
		String [] cbgroup1		= request.getParameterValues("cb1");

		return HagForms.requestDevAddMigRequestSent1(jiraVer,sentBy,jClass,jiraTask,note,runType,location,dist,cbgroup1);
}
*/
public String 	convert1(String source){
	HagStringList temp = new HagStringList(source,"-",true);
	if (temp.size()==2)
		return temp.get(0)+"/"+temp.get(1);
	else if (temp.size()==3)
		return temp.get(0)+temp.get(1)+"/"+temp.get(2);
	else
		return source;
}
	public String 	riReleaseRequestSend(HttpServletRequest request, HttpServletResponse response){
		StringBuilder ans = new StringBuilder("<h3>Release-Request</h3><table bgColor=\"#aaaaaa\" cellpadding=\"3\" border =\"1\">");
		String sentBy 	= request.getParameter("sentBy").trim();
		String jiraVer 	= request.getParameter("jiraVer").trim();
		String whenTo 	= request.getParameter("whenTo").trim();
		String note 	= request.getParameter("note").trim();
	//	String first_extra_dev 	= request.getParameter("first_extra_dev").trim();


		if(note==null || note.trim().length()==0)
			note="None";
		
		String extraDev 	= request.getParameter("extraDev").trim();
	//	String porting = request.getParameter("porting").trim();
		String [] cbgroup1		= request.getParameterValues("cb1");
		String [] cbgroup2		= request.getParameterValues("cb2");
		String [] cbgroup3		= request.getParameterValues("cb3");
		StringBuilder jiraTasks=new StringBuilder();
		//		String winInstEnv = "No environments to install.";
		//		String as400InstEnv = "No environments to install.";
		//		String components = "No components to release.";
		ans.append("<tr><td>jiraVer</td><td>").append(jiraVer).append("</td></tr>");
		ans.append("<tr><td>Sent by</td><td>").append(sentBy).append("</td></tr>");
	//	ans.append("<tr><td>Porting</td><td>").append(porting).append("</td></tr>");
		ans.append("<tr><td>Note</td><td>").append(note).append("</td></tr>");
		ans.append("<tr><td>extraDev</td><td>").append(extraDev).append("</td></tr>");
		ans.append("<tr><td>Components to release</td><td>");
		if (cbgroup3 != null && cbgroup3.length > 0){
			for(int i = 0 ; i < cbgroup3.length ; i++){
				ans.append(cbgroup3[i]).append("<br>");
				jiraTasks.append(cbgroup3[i]).append("<br>");
			}
		}
		ans.append("</td></tr>");
		ans.append("<tr><td>Win envs to install</td><td>");
		HagStringList listWin = new HagStringList();
		if (cbgroup1 != null && cbgroup1.length > 0){
			for(int i = 0 ; i < cbgroup1.length ; i++){
				ans.append(cbgroup1[i]).append("<br>");
				//String converted1 = convert1(cbgroup1[i]);
				listWin.add(cbgroup1[i]);
			}
		}else{
			ans.append("None");
		}
		ans.append("</td></tr>");
		ans.append("<tr><td>as400 envs to install</td><td>");
		HagStringList listAs400= new HagStringList();
		if (cbgroup2 != null && cbgroup2.length > 0){
			for(int i = 0 ; i < cbgroup2.length ; i++){
				ans.append(cbgroup2[i]).append("<br>");
			//	String converted1 = convert1(cbgroup2[i]);
				listAs400.add(cbgroup2[i]);
			}
		}else{
			ans.append("None");
		}

		String extraDevFlag=" ";
		if(listWin.size()==0 && listAs400.size()==0)
			extraDevFlag=" (no envs to install) ";

		ans.append("</td></tr>");
		ans.append("<tr><td>When to install</td><td>").append(whenTo).append("</td></tr>");
		ans.append("</table>");
		StringBuilder cfg = new StringBuilder("<h3>cm team:</h3>");
	//	StringBuilder distTo = new StringBuilder("PORTING=").append(porting).append("<br>"); 
	
		StringBuilder distTo = new StringBuilder();
		composeReqFile( listWin, jiraVer,	 cfg ,true,distTo);
		cfg.append("<br>");
		composeReqFile( listAs400, jiraVer,	 cfg ,false,distTo);
//
		String int1= HagUtil.getVersionTxtVal(jiraVer, 4);
		String server1= HagUtil.getVersionTxtVal(jiraVer, 5);
	//	copied = "<td bgColor=\""+colorG+"\">0~"+jarSrc+" copied to "+jarTgtToCopy+"</td></tr>"; 
		
		HagUtil.writeToRelDiary2("Release","WIN","Request",jiraVer,"00","OK",distTo.toString(),jiraTasks.toString(),"cfgMenuWeb",sentBy,server1,int1);

		String ccList 	= HagUtil.getRiMails(jiraVer);
		//String ccList 	= "david.ha@sapiens.com";
		int ind = HagUtil.getRequestInd();
		
		String indS = HagUtil.padNumLeft(""+ind, 5, '0');
//
	//	String subject = "#Request to install "+jiraVer+" on INT-WIN , @BREAK-REQ@ #"+indS+"#  ("+whenTo+") "+extraDevFlag+" sent by "+sentBy;
			String subject = "#Request to install "+jiraVer+" on INT-WIN ("+whenTo+") - @BREAK-REQ@ #"+indS+"#"+extraDevFlag+"sent by "+sentBy;
		//spr1007
			String winInstallOn=null;
			String as400InstallOn=null;
			String  whenToInstall="ASAP";
			if(listAs400!=null && listAs400.size()>0)
				as400InstallOn=listAs400.convertToString(" ");
			if(listWin!=null && listWin.size()>0)
				winInstallOn=listWin.convertToString(" ");
			if(whenTo!=null && whenTo.indexOf("night")>-1)
				whenToInstall="Night";
		if(!HagUtil.addRequest(ind,sentBy, ".",subject,"./.",".",".",winInstallOn,as400InstallOn,whenToInstall,null,997))
			return HagUtil.shortHtml("Error adding request-mail sent to hagay & gonen, please call hagay or gonen", "red","bg");
		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+sentBy,HagUtil.mailList_hag,subject,cfg.toString()+ans.toString());
		String ans2		= HagUtil.sendSms("0528399706;0528399727", "david.ha@sapiens.com",subject, "cfgMenuRequest");

		return ans1+"<br>"+ans2+"<br>"+ans.toString();
	
	}
	public String 	replacePrivateDb_replaceProperties(String fromApp,String fromBN,String toApp,String toDB,String toSQL,String toBatchName){
		HagStringList ans = new HagStringList();
		
		
		//properties
		String propFileFrom = "\\\\"+fromApp+"\\RI_JAVA\\RIjava_"+fromBN+"\\properties\\"+fromBN+".properties"; 
		String propFileTo 	= "\\\\"+toApp+"\\RI_JAVA\\RIjava_"+toBatchName+"\\properties\\"+toBatchName+".properties"; 
		String ans1 = HagUtil.deleteFile(propFileTo,  false);
		if(!ans1.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to delete "+propFileTo+"<br>"+ans1+"</td>";
		else
			ans.add(propFileTo + " deleted");
		ans1 = HagUtil.copyFileViaDos(propFileFrom, propFileTo);		
		if(!ans1.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to copy "+propFileFrom+" to "+propFileTo+"<br>"+ans1+"</td>";
		else
			ans.add(propFileTo + " copied");

		HagStringList propFileList = new HagStringList(propFileTo,false,"asasassd",true);
		for (int i = 0;i<propFileList.size();i++){
			String temp = propFileList.get(i);
			if(temp.startsWith("db.host="))						propFileList.set( i,"db.host="+toSQL);
			if(temp.startsWith("db.schema="))					propFileList.set( i,"db.schema="+toDB);
			if(temp.startsWith("jg.tmplDir="))					propFileList.set( i,"jg.tmplDir=d:\\\\RI_JAVA\\\\RIjava_"+toBatchName+"\\\\ritemplates");
			if(temp.startsWith("jg.filesDir="))					propFileList.set( i,"jg.filesDir=d:\\\\RI_JAVA\\\\RIjava_"+toBatchName+"\\\\runtime\\\\"+toBatchName);
			if(temp.startsWith("jg.iseriesTmpLib="))			propFileList.set( i,"jg.iseriesTmpLib="+toBatchName+"TEMP");
			if(temp.startsWith("web.startup.dir="))				propFileList.set( i,"web.startup.dir=d:\\\\RI_JAVA\\\\RIjava_"+toBatchName+"\\\\runtime\\\\startup");
			if(temp.startsWith("cmInstaller.iom="))				propFileList.set( i,"cmInstaller.iom=d:\\\\RI_SHARE\\\\"+toBatchName+"\\\\IOM_Core");
			if(temp.startsWith("cmInstaller.batchName="))		propFileList.set( i,"cmInstaller.batchName="+toBatchName);
			if(temp.startsWith("cmInstaller.CtDatabaseName="))	propFileList.set( i,"cmInstaller.CtDatabaseName="+toDB);
		}
		propFileList.writeToFile(propFileTo, null, false);
		ans.add(propFileTo + " parameters updated:");
		
		return "<td bgColor=\"#00FF00\">"+ans.convertToString("<br>")+"</td>";
	}
	public String 	replacePrivateDb_setTomcatProperties(String version,String serverPort,String connectionPort,String debugPort,String fromDb,String toApp,
			String toBatchName,String log4jLevel,String debugFolder){
		HagStringList ans = new HagStringList();
		String 			fromSkel = HagUtil.cfgMenuWebLoc+"\\skel\\tomcat8";
		String log4jName = "log4j2.xml";
		if(version.equals("0700") || version.startsWith("06")){
			fromSkel = HagUtil.cfgMenuWebLoc+"\\skel\\tomcat7";
			log4jName = "log4j.xml";
		}
		
		//log4j
		String log4jFileFrom = fromSkel+"\\"+log4jName; 
		String log4jFileTo 	= "\\\\"+toApp+"\\RI_JAVA\\RIjava_"+toBatchName+"\\tomcat\\shared\\"+log4jName; 
		String ansTemp = HagUtil.deleteFile(log4jFileTo,  false);
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to delete "+log4jFileTo+"<br>"+ansTemp+"</td>";
		else
			ans.add(log4jFileTo + " deleted");
		ansTemp = HagUtil.copyFileViaDos(log4jFileFrom, log4jFileTo);		
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to copy "+log4jFileFrom+" to "+log4jFileTo+"<br>"+ansTemp+"</td>";
		else
			ans.add(log4jFileTo + " copied");
		ansTemp = HagUtil.replaceStringInFile(log4jFileTo, "{RI-TOMCAT-LOG}", "d://RI_SHARE//"+toBatchName+"//TomcatLogs");
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to configure1 "+log4jFileTo+"<br>"+ansTemp+"</td>";
		else
			ans.add(log4jFileTo + " configured1");	
		
		ansTemp = HagUtil.replaceStringInFile(log4jFileTo, "{errorOrDebug}", log4jLevel);
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to configure2 "+log4jFileTo+"<br>"+ansTemp+"</td>";
		else
			ans.add(log4jFileTo + " configured2");	
		//server.xml
		String serverXmlFileFrom = fromSkel+"\\server.xml"; 
		String serverXmlFileTo 	= "\\\\"+toApp+"\\RI_JAVA\\RIjava_"+toBatchName+"\\tomcat\\conf\\server.xml"; 
		ansTemp = HagUtil.deleteFile(serverXmlFileTo,  false);
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to delete "+serverXmlFileTo+"<br>"+ansTemp+"</td>";
		else
			ans.add(serverXmlFileTo + " deleted");
		ansTemp = HagUtil.copyFileViaDos(serverXmlFileFrom, serverXmlFileTo);		
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to copy "+serverXmlFileFrom+" to "+serverXmlFileTo+"<br>"+ansTemp+"</td>";
		else
			ans.add(serverXmlFileTo + " copied");
		String ansTemp1 = HagUtil.replaceStringInFile(serverXmlFileTo, "{RI-SERVER-PORT}", serverPort);
		String ansTemp2 = HagUtil.replaceStringInFile(serverXmlFileTo, "{RI-CONNECTOR-PORT}", connectionPort);
		if(!ansTemp1.startsWith("0~") ||!ansTemp2.startsWith("0~")  )
			return "<td bgColor=\"#FF0000\">Failed to configure "+serverXmlFileTo+"<br>"+ansTemp1+"<br>"+ansTemp2+"</td>";
		else
			ans.add(serverXmlFileTo + " configured");	
		/*
		HagStringList list = new HagStringList(serverXmlFileFrom,false,"adafrsa",true);
		list.replaceStr("{RI-SERVER-PORT}", serverPort);
		list.replaceStr("{RI-CONNECTOR-PORT}", connectionPort);
		ansTemp = list.writeToFile(serverXmlFileTo,  false);
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to configure "+serverXmlFileTo+"<br>"+ansTemp+"</td>";
		else
			ans.add(serverXmlFileTo + " configured");	
		*/
		//debug.bat
		String debugFileFrom = fromSkel+"\\debug.bat"; 
		String debugFileTo 	= "\\\\"+toApp+"\\RI_JAVA\\RIjava_"+toBatchName+"\\tomcat\\bin\\debug.bat"; 
		ansTemp = HagUtil.deleteFile(debugFileTo,  false);
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to delete "+debugFileTo+"<br>"+ansTemp+"</td>";
		else
			ans.add(debugFileTo + " deleted");
		ansTemp = HagUtil.copyFileViaDos(debugFileFrom, debugFileTo);		
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to copy "+debugFileFrom+" to "+debugFileTo+"<br>"+ansTemp+"</td>";
		else
			ans.add(debugFileTo + " copied");
		ansTemp = HagUtil.replaceStringInFile(debugFileTo, "{RI-DEBUG-PORT}", debugPort);
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to configure "+debugFileTo+"<br>"+ansTemp+"</td>";
		else
			ans.add(debugFileTo + " configured");	
		//catalina.bat
		
		String catalinaS 	= "\\\\"+toApp+"\\RI_JAVA\\RIjava_"+toBatchName+"\\tomcat\\bin\\catalina.bat"; 
		
		String aaaa= HagUtil.updateCatalinaLines( catalinaS, version,debugFolder,ans);
		return "<td bgColor=\"#00FF00\">"+ans.convertToString("<br>")+"</td>";
	}
	
	public String 	replacePrivateDb_replaceEobjects(String fromApp,String fromBN,String toApp,String toBatchName,String user,String clientLocation4530){

		HagStringList ans = new HagStringList();
		String fromFolder = "\\\\"+fromApp+"\\clientDB\\DB\\"+fromBN; 
	//	String fromFolderRi = "\\\\"+fromApp+"\\clientDB\\DB\\"+fromBN+"RI"; 
	
		
		String toFolder = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName; 
		String toToolbarDbMs = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"\\EOBJECTS\\MS\\toolbar.htm"; 
		String toToolbarDbSf = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"\\EOBJECTS\\SF\\toolbar.htm"; 
		String toDes = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"\\sapdb"+toBatchName+".des";
		String toDesOld = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"\\sapdb"+fromBN+".des";
		
	//	String toFolderRi = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"RI"; 
		String toToolbarDbRIMs = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"RI\\EOBJECTS\\MS\\toolbar.htm"; 
		String toToolbarDbRISf = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"RI\\EOBJECTS\\SF\\toolbar.htm"; 
	//	String toDesRi = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"RI\\sapdb"+toBatchName+"RI.des"; 
	//	String toDesRiOld = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"RI\\sapdb"+fromBN+"RI.des"; 
		String toFolder_RI = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"RI"; 
		String toFolder_CT = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"CT"; 
		
		
		HagStringList toDesList = new HagStringList();
		toDesList.add("No.      Dataset           OpenMode");
		toDesList.add("1   kb4530EN");
		toDesList.add("2   err4530EN");
		toDesList.add("3   ub"+toBatchName);
		toDesList.add("");
		if(clientLocation4530!=null && clientLocation4530.length()>0)
			toDesList.add(clientLocation4530+"4530.map");

			//			toDesList.add("C:\\ProgramData\\Sapiens\\eMerge Client Tools\\DB\\4530.map");
		//toDesList.add(toClient);
		
		
		//delete
		String ansTemp = HagUtil.deleteFolder(toFolder, null, false, false);
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to delete "+toFolder+"<br>"+ansTemp+"</td>";
		else
			ans.add(toFolder + " deleted");
		
		//dbidRI + dbidCT
		ansTemp = HagUtil.deleteFolder(toFolder_RI, null, false, false);
		ans.add(ansTemp);
		ansTemp = HagUtil.deleteFolder(toFolder_CT, null, false, false);
		ans.add(ansTemp);
		HagRc hagRc= new HagRc();
		HagUtil.reCreateFolder(hagRc, toFolder_RI);
		ans.add(hagRc.log.get(0));
		if(hagRc.rc!=0)
			return "<td bgColor=\"#FF0000\">Failed to create "+toFolder_RI+"<br>"+hagRc.log.convertToString("<BR>")+"</td>";
		HagUtil.reCreateFolder(hagRc, toFolder_RI+"\\EOBJECTS");
		ans.add(hagRc.log.get(0));
		if(hagRc.rc!=0)
			return "<td bgColor=\"#FF0000\">Failed to create "+toFolder_RI+"\\EOBJECTS<br>"+hagRc.log.convertToString("<BR>")+"</td>";
		HagUtil.reCreateFolder(hagRc, toFolder_RI+"\\HTML");
		ans.add(hagRc.log.get(0));
		if(hagRc.rc!=0)
			return "<td bgColor=\"#FF0000\">Failed to create "+toFolder_RI+"\\HTML<br>"+hagRc.log.convertToString("<BR>")+"</td>";
		HagUtil.reCreateFolder(hagRc, toFolder_CT);
		ans.add(hagRc.log.get(0));
		if(hagRc.rc!=0)
			return "<td bgColor=\"#FF0000\">Failed to create "+toFolder_CT+"<br>"+hagRc.log.convertToString("<BR>")+"</td>";
		HagUtil.reCreateFolder(hagRc, toFolder_CT+"\\EOBJECTS");
		ans.add(hagRc.log.get(0));
		if(hagRc.rc!=0)
			return "<td bgColor=\"#FF0000\">Failed to create "+toFolder_CT+"\\EOBJECTS<br>"+hagRc.log.convertToString("<BR>")+"</td>";
		HagUtil.reCreateFolder(hagRc, toFolder_CT+"\\HTML");
		ans.add(hagRc.log.get(0));
		if(hagRc.rc!=0)
			return "<td bgColor=\"#FF0000\">Failed to create "+toFolder_CT+"\\HTML<br>"+hagRc.log.convertToString("<BR>")+"</td>";
	

		//create ri des files
		String toDesRi = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"RI\\sapdb"+toBatchName+"RI.des";
		HagStringList toDesRiList = new HagStringList();
		toDesRiList.add("No.      Dataset           OpenMode");
		toDesRiList.add("1   kb4530EN");
		toDesRiList.add("2   err4530EN");
		toDesRiList.add("3   ub"+toBatchName+"RI");
		toDesRiList.add("");
		if(clientLocation4530!=null && clientLocation4530.length()>0)
			toDesRiList.add(clientLocation4530+"4530.map");

		//toDesRiList.add(toClient);
		String ansTempRi = toDesRiList.writeToFile(toDesRi, false);
		if(!ansTempRi.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to create "+toDesRi+"<br>"+ansTempRi+"</td>";
		else
			ans.add(toDesRi + " created");

		//create ct des files
		String toDesCt = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"CT\\sapdb"+toBatchName+"CT.des";
		HagStringList toDesCtList = new HagStringList();
		toDesCtList.add("No.      Dataset           OpenMode");
		toDesCtList.add("1   kb4530EN");
		toDesCtList.add("2   err4530EN");
		toDesCtList.add("3   ub"+toBatchName+"CT");
		toDesCtList.add("");
		if(clientLocation4530!=null && clientLocation4530.length()>0)
			toDesCtList.add(clientLocation4530+"4530.map");

		//toDesCtList.add(toClient);
		String ansTempCt = toDesCtList.writeToFile(toDesCt, false);
		if(!ansTempCt.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to create "+toDesCt+"<br>"+ansTempCt+"</td>";
		else
			ans.add(toDesCt + " created");
		

		
	
		//copy
		ansTemp = HagUtil.copyFolder(fromFolder, toFolder, false, false,"1","");
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to copy "+fromFolder+" to "+toFolder+"<br>"+ansTemp+"</td>";
		else
			ans.add(fromFolder + " copied to "+toFolder);
		/*
		ansTemp = HagUtil.copyFolder(fromFolderRi, toFolderRi, false, false,"1","");
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to copy "+fromFolderRi+" to "+toFolderRi+"<br>"+ansTemp+"</td>";
		else
			ans.add(fromFolderRi + " copied to "+toFolderRi);
		 */
		
		//toolbar
		ansTemp = HagUtil.replaceStringInFile(toToolbarDbMs, "/"+fromBN+"/", "/"+toBatchName+"/");
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to configure toolbar.htm file in "+toToolbarDbMs+"<br>"+ansTemp+"</td>";
		else
			ans.add(toToolbarDbMs + " toolbar.htm file configured");	
		ansTemp = HagUtil.replaceStringInFile(toToolbarDbSf, "/"+fromBN+"/", "/"+toBatchName+"/");
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to configure toolbar.htm file in "+toToolbarDbSf+"<br>"+ansTemp+"</td>";
		else
			ans.add(toToolbarDbSf + " toolbar.htm file configured");		
		/*
		ansTemp = HagUtil.replaceStringInFile(toToolbarDbRIMs, "/"+fromBN+"/", "/"+toDb+"/");
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to configure toolbar.htm file in "+toToolbarDbRIMs+"<br>"+ansTemp+"</td>";
		else
			ans.add(toToolbarDbRIMs + " toolbar.htm file configured");		
		ansTemp = HagUtil.replaceStringInFile(toToolbarDbRISf, "/"+fromBN+"/", "/"+toDb+"/");
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to configure toolbar.htm file in "+toToolbarDbRISf+"<br>"+ansTemp+"</td>";
		else
			ans.add(toToolbarDbRISf + " toolbar.htm file configured");		
		*/
		
		//dbid-des file
		ansTemp = HagUtil.deleteFile(toDesOld,  false);
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to delete "+toDesOld+"<br>"+ansTemp+"</td>";
		else
			ans.add(toDesOld + " deleted");
		ansTemp = toDesList.writeToFile(toDes, false);
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to create "+toDes+"<br>"+ansTemp+"</td>";
		else
			ans.add(toDes + " created");

		//dbidRI-des file
//		ansTemp = HagUtil.deleteFile(toDesRiOld, false, false);
//		if(!ansTemp.startsWith("0~"))
//			return "<td bgColor=\"#FF0000\">Failed to delete "+toDesRiOld+"<br>"+ansTemp+"</td>";
//		else
//			ans.add(toDesRiOld + " deleted");
		
		
		
		String clearDb =HagUtil.cfgMenuWebLoc+"\\skel\\admindb.bat "+HagUtil.cfgMenuWebLoc+"\\skel\\admindb.js\" -k "+toBatchName;
		//ansTemp = HagUtil.simpleDosCmd(clearDb,false);
		ansTemp =HagUtil.runNotSudo(toApp,clearDb,user);
		if(ansTemp.startsWith("1~"))
			return "<td bgColor=\"#FF0000\">Failed to run clearDB <br>"+ansTemp+"</td>";
	    else
	    	ans.add("clearDB = done<br>"+ansTemp);
		
		return "<td bgColor=\"#00FF00\">"+ans.convertToString("<br>")+"</td>";
	}
	public String 	replacePrivateDb_deleteTomcatLogs(String toApp,String toDb,String tomcatNfs){
		String tomcatNfsLogs=tomcatNfs+"\\*.log";
		String tomcatNfsRolled=tomcatNfs+"\\*.rolled";
		String tomcatNfsBinLogs=tomcatNfs+"\\bin\\*.log";
		String tomcatNfsBinRolled=tomcatNfs+"\\bin\\*.rolled";

		HagStringList ans = new HagStringList();
		String rc = HagUtil.simpleDosCmd("javaFunc~simpleDosCmd~del~\""+tomcatNfsLogs+"\"",false);
		if(rc.startsWith("0~"))
			ans.add("<font color=\"#000000\">delete source logs .log: "+rc+"</font>");
		else
			ans.add("<font color=\"#FF0001\">delete source logs .log: "+rc+"</font>");
		String rc1 = HagUtil.simpleDosCmd("javaFunc~simpleDosCmd~del~\""+tomcatNfsRolled+"\"",false);
		if(rc1.startsWith("0~"))
			ans.add("<font color=\"#000000\">delete source logs .rolled: "+rc1+"</font>");
		else
			ans.add("<font color=\"#FF0001\">delete source logs .rolled: "+rc1+"</font>");

		String rc2 = HagUtil.simpleDosCmd("javaFunc~simpleDosCmd~del~\""+tomcatNfsBinLogs+"\"",false);
		if(rc2.startsWith("0~"))
			ans.add("<font color=\"#000000\">delete source logs bin\\*.log: "+rc2+"</font>");
		else
			ans.add("<font color=\"#FF0001\">delete source logs bin\\*.log: "+rc2+"</font>");
		String rc3 = HagUtil.simpleDosCmd("javaFunc~simpleDosCmd~del~\""+tomcatNfsBinRolled+"\"",false);
		if(rc3.startsWith("0~"))
			ans.add("<font color=\"#000000\">delete source logs bin\\*.rolled: "+rc3+"</font>");
		else
			ans.add("<font color=\"#FF0001\">delete source logs bin\\*.rolled: "+rc3+"</font>");

		ans.add("source tomcat logs deleted:(if found)");
		
		return "<td bgColor=\"#00FF00\">"+ans.convertToString("<br>")+"</td>";

	}

	public String 	replacePrivateDb_deleteTomcat22222222(String toApp,String toDb ){
		String toTomcat ="d:\\RI_JAVA\\RIjava_"+toDb+"\\tomcat";
		String toTomcatNfs ="\\\\"+toApp+"\\RI_JAVA\\RIjava_"+toDb+"\\tomcat";

		File dir = new File(toTomcatNfs);
		if(dir.exists()) {
			String cmd = "RD "+toTomcat+" /S /Q";
			String ans2 =  HagUtil.runCmdRemote(toApp, cmd+"\n","N");
			if(!ans2.startsWith("0~")){
				return "<td bgColor=\"#FF0000\">Failed to recreate tomcat folder "+toTomcat+"<br>"+ans2 +"</td>";
			}
		}
		HagRc hagRcTomcat = new HagRc();
		HagUtil.reCreateFolder(hagRcTomcat, toTomcatNfs);	
		if(hagRcTomcat.rc!=0)
			return "<td bgColor=\"#FF0000\">Failed to recreate tomcat folder "+toTomcat+"<br>"+hagRcTomcat.log.convertToString("<br>") +"</td>";
		return "<td bgColor=\"#00FF00\">tomcat folder "+toTomcat+" deleted<br>"+hagRcTomcat.log.convertToString("<br>") +"</td>";
	}
		
		
	public String 	replacePrivateDb_deleteTomcat(String toApp,String toDb,String tomcatNfs){
		HagStringList ans = new HagStringList();
		String toTomcat ="\\\\"+toApp+"\\RI_JAVA\\RIjava_"+toDb+"\\tomcat";
		HagRc hagRcTomcat = new HagRc();

		HagUtil.reCreateFolder(hagRcTomcat, toTomcat);
		if(hagRcTomcat.rc!=0){
			HagUtil.sleep(5000);
			hagRcTomcat.log.add("delete try no#2");
			hagRcTomcat.rc = 0;
			HagUtil.reCreateFolder(hagRcTomcat, toTomcat);
			if(hagRcTomcat.rc!=0){
				HagUtil.sleep(10000);
				hagRcTomcat.log.add("delete try no#3");
				hagRcTomcat.rc = 0;
				HagUtil.reCreateFolder(hagRcTomcat, toTomcat);
			}
		}
			
		
	//	String ansTemp = "<td bgColor=\"#00FF00\">tomcat folder recreated and copied from "+tomcatNfs+" to "+toTomcat+"</td>";
		if(hagRcTomcat.rc!=0)
			return "<td bgColor=\"#FF0000\">Failed to recreate tomcat folder "+toTomcat+"<br>"+hagRcTomcat.log.convertToString("<br>") +"</td>";
		return "<td bgColor=\"#00FF00\">tomcat folder "+toTomcat+" deleted<br>"+hagRcTomcat.log.convertToString("<br>") +"</td>";
	}
	
	public String 	replacePrivateDb_copyTomcat(String toApp,String toDb,String tomcatNfs){
	
		HagStringList ans = new HagStringList();
		String toTomcat ="\\\\"+toApp+"\\RI_JAVA\\RIjava_"+toDb+"\\tomcat";
	
 		String ans13i = HagUtil.copyFolder( tomcatNfs,toTomcat,false,false,"1",HagUtil.cfgMenuWebLoc+"\\lists\\excludedFilesList.txt");
		String ans13ia=HagUtil.createFolder(toTomcat+"\\logs");
 		String ans13ib=HagUtil.createFolder(toTomcat+"\\temp");
 		if(!ans13i.startsWith("0~") )
			return "<td bgColor=\"#FF0000\">Failed to copy tomcat from "+tomcatNfs+" to "+toTomcat+"<br>"+ans13i+"</td>";
 		else if(!ans13ia.startsWith("0~") ) 
 				return "<td bgColor=\"#FF0000\">Failed to create tomcat logs folder <br>"+ans13ia+"</td>";
 		else if(!ans13ib.startsWith("0~") ) 
				return "<td bgColor=\"#FF0000\">Failed to create tomcat temp folder <br>"+ans13ib+"</td>";
		ans.add("tomcat replaced:"+ans13i);
		ans.add("tomcat replaced:");
		
		return "<td bgColor=\"#00FF00\">"+ans.convertToString("<br>")+"</td>";

	}
	public String 	replacePrivateDb_replaceCmTable(String toApp,String toDb,String version,String patch,String party,String lastInst,String lastGoodInst){
		HagSQL hagSQL = new HagSQL();
		StringBuilder stm4= new StringBuilder("Update "+HagParam.getConfg1DB()+".[dbo].[ri_environments_new] ")
		.append("set ")
		.append("version='"+version+"'")
		.append(",patch='"+patch+"'")
		.append(",party='"+party+"'")
		.append(",lastInst='"+lastInst+"'")
		.append(",lastGoodInst='"+lastGoodInst+"' ")
		.append("where bis_server='"+toApp+"' and batchName='"+toDb+"'");
		//.append(",JSP_TEMP_URL='"+sentBy+"'");
		//.append(",JSP_PROD_URL='"+sentBy+"'");
		String stm4S = stm4.toString();
		int ans4i =hagSQL.updateStm("confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm4S);
		if(ans4i>0)
			return "<td bgColor=\"#00FF00\">ri_environments table updated.</td>";
		return "<td bgColor=\"#FF0000\">Failed to update ri_environments table.</td><td>stm="+stm4+"</td><td>rc="+ans4i+"</td>";
		
	}
	public String 	replaceJasper_Runtime_TemplatesFolder(String fromApp,String fromBatchName,String toApp,String toBatchName,String party){
		
		String fromJasper_Runtime_Templates ="\\\\"+fromApp+"\\RI_SHARE\\"+fromBatchName+"\\Jasper_Runtime_Templates";
		String toJasper_Runtime_Templates ="\\\\"+toApp+"\\RI_SHARE\\"+toBatchName+"\\Jasper_Runtime_Templates";
		HagRc hagRc = new HagRc();
		HagUtil.reCreateFolder(hagRc, toJasper_Runtime_Templates);
		String ansTemp = "<td bgColor=\"#00FF00\">Jasper_Runtime_Templates folder recreated and copied from "+fromJasper_Runtime_Templates+" to "+toJasper_Runtime_Templates+"</td>";
		if(hagRc.rc!=0){
			ansTemp = "<td bgColor=\"#FF0000\">Failed to recreate Jasper_Runtime_Templates folder "+toJasper_Runtime_Templates+"<br>"+hagRc.log.convertToString("<br>") +"</td>";
		}else{
			if(party.equals("190000")) {
				ansTemp = "<td bgColor=\"#FFFF00\">RML customer is not a Jasper customer</td>";
			}else {
				String ans13i = HagUtil.copyFolder( fromJasper_Runtime_Templates,toJasper_Runtime_Templates,false,false,"1","");
				if(!ans13i.startsWith("0~"))
					ansTemp = "<td bgColor=\"#FF0000\">Failed to copy Jasper_Runtime_Templates from "+fromJasper_Runtime_Templates+" to "+toJasper_Runtime_Templates+"<br>"+ans13i+"</td>";
			}
		}
		
		return ansTemp;
	}
	public String 	replaceInputfilesFolder(String fromApp,String fromDb,String toApp,String toDb){
		
		String fromInputfilesFolder ="\\\\"+fromApp+"\\RI_SHARE\\"+fromDb+"\\INPUTFILES";
		String toInputfilesFolder ="\\\\"+toApp+"\\RI_SHARE\\"+toDb+"\\INPUTFILES";
		HagRc hagRc = new HagRc();
		HagUtil.reCreateFolder(hagRc, toInputfilesFolder);
		String ansTemp = "<td bgColor=\"#00FF00\">INPUTFILES folder recreated and copied from "+fromInputfilesFolder+" to "+toInputfilesFolder+"</td>";
		if(hagRc.rc!=0){
			ansTemp = "<td bgColor=\"#FF0000\">Failed to recreate INPUTFILES folder "+toInputfilesFolder+"<br>"+hagRc.log.convertToString("<br>") +"</td>";
		}else{
			String ans13i = HagUtil.copyFolder( fromInputfilesFolder,toInputfilesFolder,false,false,"1","");
			if(!ans13i.startsWith("0~"))
				ansTemp = "<td bgColor=\"#FF0000\">Failed to copy INPUTFILES from "+fromInputfilesFolder+" to "+toInputfilesFolder+"<br>"+ans13i+"</td>";
		}
	
		
		return ansTemp;
	}
	public String 	replacePrivateDb_setRiadminUser(String toSql,String toDB){
		//riadmin
		HagSQL hagSQL = new HagSQL();
		StringBuilder stm7= new StringBuilder()
		.append("IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'RI') ")
		.append("BEGIN EXEC('CREATE SCHEMA [RI] AUTHORIZATION [dbo]') END ")
		.append("IF NOT EXISTS(SELECT principal_id FROM sys.database_principals WHERE name = 'RIADMIN') ") 
		.append("BEGIN CREATE USER [RIADMIN] FOR LOGIN [RIADMIN] END ")
		.append("ALTER USER [RIADMIN] WITH DEFAULT_SCHEMA=[RI] ")
		.append("EXEC sp_addrolemember N'db_owner', N'RIADMIN' ")
		.append("exec sp_change_users_login 'update_one', 'RIADMIN', 'RIADMIN'"); 
		String stm7S = stm7.toString();
		int ans7i =hagSQL.updateStm(toSql, "cfg","cfg09c", toDB,  stm7S);
		
		if(ans7i==-2)
			return "<td bgColor=\"#FF0000\">Failed</td>";
		
		return "<td bgColor=\"#00FF00\">"+"RIADMIN done"+"</td>";
	}
	public String 	replacePrivateDb_setRiUser(String toSql,String toDB,String toBatchName){
		//ri
		HagSQL hagSQL = new HagSQL();
		StringBuilder stm6= new StringBuilder()
		.append("IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'RI') ")
		.append("BEGIN EXEC('CREATE SCHEMA [RI] AUTHORIZATION [dbo]') END ")
		.append("IF NOT EXISTS(SELECT principal_id FROM sys.server_principals WHERE name = '"+toBatchName+"') ") 
		.append("BEGIN CREATE LOGIN ["+toBatchName+"] WITH PASSWORD=N'"+toBatchName+"PASS', DEFAULT_DATABASE=["+toDB+"], CHECK_EXPIRATION=OFF, CHECK_POLICY=OFF END ")
		.append("IF NOT EXISTS(SELECT principal_id FROM sys.database_principals WHERE name = '"+toBatchName+"') ") 
		.append("BEGIN CREATE USER ["+toBatchName+"] FOR LOGIN ["+toBatchName+"] END ")
		.append("ALTER USER ["+toBatchName+"] WITH DEFAULT_SCHEMA=[RI] ")
		.append("EXEC sp_addrolemember N'db_owner', N'"+toBatchName+"' ")
		.append("exec sp_change_users_login 'update_one', '"+toBatchName+"', '"+toBatchName+"'"); 
		String stm6S = stm6.toString();
		int ans6i =hagSQL.updateStm(toSql, "cfg","cfg09c", toDB,  stm6S);
	
		if(ans6i==-2)
			return"<td bgColor=\"#FF0000\">set RI user Failed</td>"+"<td bgColor=\"#FF0000\">"+stm6S+"</td>";
		
		return "<td bgColor=\"#00FF00\">"+"set RI user done"+"</td>";
	}
	public String 	replacePrivateDb_setRiUserTable(String fromBN,String toSql,String toDB,String toBatchName,HagRc hatRc_setRiUserTable){
		//ri
		HagSQL hagSQL = new HagSQL();
		StringBuilder stm5= new StringBuilder("Update ["+toDB+"].[RI].[RIUSER] ")
		.append("set ")
		.append("USER_IDENTITY='"+toBatchName+"'")
		.append(",RI_USER_NAME='"+toBatchName+"'")
		.append(" where USER_IDENTITY='"+fromBN+"'");
		String stm5S = stm5.toString();
		hatRc_setRiUserTable.log.add(stm5S);
		hatRc_setRiUserTable.log.add("toSql="+toSql);
		hatRc_setRiUserTable.log.add("toDB="+toDB);
		hatRc_setRiUserTable.log.add("user=cfg");
		int ans5i =hagSQL.updateStm(toSql, "cfg","cfg09c", toDB,  stm5S);
		hatRc_setRiUserTable.log.add("num updated rows = "+ans5i);
		if(ans5i>0)
			return "<td bgColor=\"#00FF00\">RIUSER table updated.</td>";
		return "<td bgColor=\"#FF0000\">Failed to update RIUSER table.</td>";
	}
	public String 	replacePrivateDb_setRiProfileTable(String toApp,String toSql,String toDB,String tomcat_port,String cust,String toBatchName){
		//ri
	//	RID-2224 String glFileLoc = "\\\\"+toApp+"\\RI_SHARE\\"+toBatchName+"\\INPUTFILES"; 
		String glFileLoc = "D:\\RI_SHARE\\"+toBatchName+"\\GL_TRANSFER_FILES"; 
		
		HagSQL hagSQL = new HagSQL();
		StringBuilder stm4= new StringBuilder("Update ["+toDB+"].[RI].[RIPROFILE] ")
		.append("set ")
		.append("DOC_CONV_IP='"+toApp+"'")
		.append(",HTTP_LISTEN_URL='"+"http://"+toApp+":"+tomcat_port+"/ri-web"+"'")
		.append(",CLAIM_INPUT_PATH='"+"\\\\"+toApp+"\\RI_SHARE\\"+toBatchName+"\\INPUTFILES"+"'")
		.append(",DOC_CONV_DB_DIR='"+toBatchName+"'")
		.append(",DATA_BASE_ID='"+"RI"+"'")
		.append(",CONFIG_FILE_ID='"+toBatchName+"'")
		.append(",JSP_PROD_URL='"+"d:\\RI_JAVA\\RIjava_"+toBatchName+"\\tomcat\\webapps\\ri-web\\WEB-INF\\classes\\documents-word\\"+cust+"'")
		.append(",JSP_TEMP_URL='"+"d:\\RI_SHARE\\"+toBatchName+"\\Jasper_Runtime_Templates"+"'")
		.append(",DOC_HTTP_URL='"+"WebProxy"+"'");
	//RID-2224	if(cust.equals("GRD")) 
			stm4.append(",GL_FILE_LOC='"+glFileLoc+"'");
		//.append(",JSP_TEMP_URL='"+sentBy+"'");
		//.append(",JSP_PROD_URL='"+sentBy+"'");
		String stm4S = stm4.toString();
		int ans4i =hagSQL.updateStm(toSql, "cfg","cfg09c", toDB,  stm4S);
		if(ans4i>0)
			return "<td bgColor=\"#00FF00\">RIPROFILE table updated.</td>";
		return "<td bgColor=\"#FF0000\">Failed to update RIPROFILE table.</td>";
		
	}
	public String 	replacePrivateDb_setAdapter(String toSql,String toDB){
		//adapter1
		HagStringList ans = new HagStringList();
		HagSQL hagSQL = new HagSQL();
		String stm8S= "ALTER DATABASE "+toDB+" SET TRUSTWORTHY ON";
		int ans8i =hagSQL.updateStm(toSql, "cfg","cfg09c", toDB,  stm8S);
		if(ans8i==-2)
			return "<td bgColor=\"#FF0000\">SET TRUSTWORTHY ON Failed</td>";
		ans.add("SET TRUSTWORTHY ON done");
		//adapter2
		String stm9S= "GRANT EXTERNAL ACCESS ASSEMBLY TO RIADMIN";
		int ans9i =hagSQL.updateStm(toSql, "cfg","cfg09c", "master",  stm9S);
		if(ans9i==-2)
			return "<td bgColor=\"#FF0000\">GRANT EXTERNAL ACCESS ASSEMBLY TO RIADMIN Failed</td>";
		ans.add("GRANT EXTERNAL ACCESS ASSEMBLY TO RIADMIN done");
		//adapter3
		String stm10S= "execute sp_changedbowner @loginame = N'sa', @map = false";
		int ans10i =hagSQL.updateStm(toSql, "cfg","cfg09c", toDB,  stm10S);
		if(ans10i==-2)
			return "<td bgColor=\"#FF0000\">execute sp_changedbowner Failed</td>";
		ans.add("execute sp_changedbowner done");
		//adapter4
		String stm11S= "EXEC sp_configure 'clr enabled' , '1'";
		int ans11i =hagSQL.updateStm(toSql, "cfg","cfg09c", toDB,  stm11S);
		if(ans11i==-2)
			return "<td bgColor=\"#FF0000\">sp_configure Failed</td>";
		ans.add("sp_configure done");
		//adapter5
		String stm12S= "reconfigure";
		int ans12i =hagSQL.updateStm(toSql, "cfg","cfg09c", toDB,  stm12S);
		if(ans12i==-2)
			return "<td bgColor=\"#FF0000\">reconfigure Failed</td>";
		ans.add("reconfigure done");

		
		return "<td bgColor=\"#00FF00\">"+ans.convertToString("<br>")+"</td>";
	}
	public String 	replacePrivateDb_replaceIom(String toApp,String toDb,String iomNfs,HagRc hagRcCopyIom){
		//iom

		String to ="\\\\"+toApp+"\\RI_SHARE\\"+toDb+"\\IOM_Core";
	
	
		
			String ans12i = HagUtil.copyFolder( iomNfs,to,false,false,"3","");
			hagRcCopyIom.log.add(ans12i);

			if(!ans12i.startsWith("0~"))
				return "<td bgColor=\"#FF0000\">Failed to copy IOM from "+iomNfs+" to "+to+"<br>"+ans12i+"</td>";
			else
				return "<td bgColor=\"#00FF00\">copy IOM from "+iomNfs+" to "+to+"<br> done.</td>";
	
		
		
	}
	//spr1004 - add iom-cust
	public String 	replacePrivateDb_replaceIomCust(String toApp,String toDb,String iomNfs,HagRc hagRcCopyIom){
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

		private String 		simpleJavaCmd(String cmdStr,String ver5)	{
		String str = ""; 
		String err = ""; 
		StringBuilder result = new StringBuilder();    
		StringBuilder error = new StringBuilder(); 
		StringBuilder warn = new StringBuilder(); 
		int rc =1;
//		String cdVersion = HagFinalProperties.getVal("packages\\"+riPackage+"\\skel\\config.txt","CDversion");
		
		try{
			Process p = Runtime.getRuntime().exec(cmdStr);                                         
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while ((str = stdInput.readLine()) != null){ 
				result.append("\n").append(str);
			}
			while ((err = stdError.readLine()) != null) {
				if(err.startsWith("log4j:WARN") || 
						    (		!ver5.startsWith("07.00") && 
									!ver5.startsWith("06.06") && 
									!ver5.startsWith("06.07") &&
									!ver5.startsWith("06.08") &&
									!ver5.startsWith("06.09")
							)		
					){
					warn.append("\n").append(err);
				}else{
					error.append("\n").append(err);
				}
			}
			rc = p.exitValue();
		}catch(Exception e){    
			return rc+"~"+cmdStr+" failed.\n"+e.getMessage();                                            
		} 
		String error1 = error.toString();
		String result1 = result.toString();
		String warn1 = warn.toString();

		if(error1.length()>0){
			StringBuilder temp = new StringBuilder("1~").append(cmdStr).append(" failed.\n").append(result1).append("\n").append(warn1).append("\n").append(error1);;
			return temp.toString();    
		}
		if(warn1.length()>0){
			StringBuilder temp = new StringBuilder("4~").append(cmdStr).append(" ended with warnings.\n").append(result1).append("\n").append(warn1);
			return temp.toString();    
		}
		if(result1.length()>0){
			StringBuilder temp = new StringBuilder("0~").append(cmdStr).append("\n").append(result1);
			return temp.toString();    
		}
		return rc+"~"+cmdStr;    
	}   
	private boolean 		checkMigRC(HagRc hagRc,String ans) {
		if (ans.startsWith("0~") || ans.startsWith("4~")) {
			// rc = ok
			int pos = ans.indexOf("Migration finished!");
			if (pos < 0){ 
				// rc = ok but str Migration finished! not found
				pos = ans.indexOf("Returning exitCode 90");
				if (pos < 0){ 
					// rc=ok but str Migration finished! not found and Returning exitCode 90 not found
					hagRc.log.add("1~rimig rc="+ans.charAt(0)+" but \"Migration finished!\" or \"Returning exitCode 90\" not found.");
					return false;
				}
			}
			int pos1 = ans.lastIndexOf("Exception");
			if (pos1 > pos){
				// rc = ok but Exception found after "Migration finished!
				hagRc.log.add("1~rimig rc="+ans.charAt(0)+" but \"Exception\" found after \"Migration finished!\" .");
				return false;
			}else{
				// rc = ok and no Exception found after "Migration finished!
				hagRc.log.add("0~rimig rc="+ans.charAt(0)+" and \"Exception\" not found after \"Migration finished!\" .");
				return true;
			}
	
		} else {
			//rc = failed
			hagRc.log.add("1~rimig rc="+ans.charAt(0));
			return false;
		}
	}
	public	void			runmigMng(HagRc hagRc,String batchName,String riMigCmd,String ver5) {
		String javaFolder = "d:\\RI_JAVA\\RIjava_"+batchName;

			String ans = simpleJavaCmd(riMigCmd, ver5);
			hagRc.log.add(ans);
	
			if (checkMigRC(hagRc,ans)) 
				hagRc.rc=0;
			else
				hagRc.rc=1;
			
			//hagRc.log.append(hagRc1.log);
			return;
		}
	

	public String 	replacePrivateDb_deleteIom(String toApp,String toDb,String iomNfs,HagRc hagRcIom){
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
	//spr1004 - add iom-cust
	public String 	replacePrivateDb_deleteIomCust(String toApp,String toDb,HagRc hagRcIom){
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
	public String 	replacePrivateDb_replaceStartup(String toApp,String toDb,String startupNfs){
		//startup
		String to ="\\\\"+toApp+"\\RI_JAVA\\RIjava_"+toDb+"\\runtime\\startup";
		HagRc hagRcStartup = new HagRc();
		HagUtil.reCreateFolder(hagRcStartup, to);
		String ansTemp = "<td bgColor=\"#00FF00\">runtime\\startup folder recreated and copied from "+startupNfs+" to "+to+"</td>";
		if(hagRcStartup.rc!=0){
			ansTemp = "<td bgColor=\"#FF0000\">Failed to recreate runtime\\startup folder "+to+"<br>"+hagRcStartup.log.convertToString("<br>") +"</td>";
		}else{
			String ans12i = HagUtil.copyFolder( startupNfs,to,false,false,"1","");
			if(!ans12i.startsWith("0~"))
				ansTemp = "<td bgColor=\"#FF0000\">Failed to copy runtime\\startup from "+startupNfs+" to "+to+"<br>"+ans12i+"</td>";
		}
		
		return ansTemp;
	}
	public String 	backupDbWithNotes(HttpServletRequest request, HttpServletResponse response){
		StringBuilder ans = new StringBuilder("<h3>Replace private environment</h3><table bgColor=\"#aaaaaa\" cellpadding=\"3\" border =\"1\">");
		String user 	= request.getParameter("user").trim();
		String fromDB 	= request.getParameter("fromDb").trim();
		String fromBN 	= request.getParameter("fromBn").trim();
		String fromSql 	= request.getParameter("fromSql").trim();
		String fromApp 	= request.getParameter("fromApp").trim();
		String fromCust 	= request.getParameter("fromCust").trim();
		String note1 	= request.getParameter("note1").trim();
		String note2 	= request.getParameter("note2").trim();
			
		HagSQL HagSql = new HagSQL();
		String an1s = HagSql.dbBackupWithNotes(fromSql,fromDB,note1,user+": "+note2,user);

		return an1s;
		/*
		//String myBrowser 	= request.getParameter("myBrowser").trim();
		//String myWebAppsServer = request.getHeader("Host");
		
	
		//String toSql 	= "RIPERFSQL02";
		//String toApp 	= "RIPERFAPP02";
		
		//if(1==1)
		//	return myBrowser+"$$$"+myWebAppsServer;
		StringBuilder ans0 = new StringBuilder("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" border =\"1\">");
		ans0.append("<tr><td> myWebAppsServer</td><td>"+myWebAppsServer+"</td></tr>");
		ans0.append("<tr><td> myBrowser</td><td>"+myBrowser+"</td></tr>");
		ans0.append("<tr><td> user</td><td>"+user+"</td></tr>");
		ans0.append("<tr><td> fromDB</td><td>"+fromDB+"</td></tr>");
		ans0.append("<tr><td> fromBN</td><td>"+fromBN+"</td></tr>");
		ans0.append("<tr><td> fromSql</td><td>"+fromSql+"</td></tr>");
		ans0.append("<tr><td> fromApp</td><td>"+fromApp+"</td></tr>");
		ans0.append("<tr><td> fromCust</td><td>"+fromCust+"</td></tr>");
		ans0.append("<tr><td> toDb</td><td>"+toDb+"</td></tr>");
		ans0.append("<tr><td> log4jLevel</td><td>"+log4jLevel+"</td></tr>");
		ans0.append("</table>");
		String ans01		= HagUtil.sendMail_hag(HagUtil.cfgTeamMail,"david.ha@sapiens.com","david.ha@sapiens.com","pre-refresh private from "+fromApp+"("+fromBN+") to "+toApp+"("+toDb+") by "+user,ans0.toString()); 
		
		String stm1 = "select emerge_port,server_port,connection_port,debug_port from dbo.ri_environments_new where bis_server='"+toApp+"' AND db='"+toDb+"'";
		//stm = HagUtil.replaceStr(stm,"{SERVER}","'"+server+"'");
		HagSQL hagSQL=new HagSQL();
		HagStringList privateDetails = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,4,"~",null,null);
		String emerge_port = HagUtil.getWord0(privateDetails.get(0),"~",0,true); 
		String serverPort = HagUtil.getWord0(privateDetails.get(0),"~",1,true);
		String connectionPort = HagUtil.getWord0(privateDetails.get(0),"~",2,true);
		String debugPort = HagUtil.getWord0(privateDetails.get(0),"~",3,true);
		
		String stm2 = "select tomcat_location,iom,version,patch,party,lastInst,lastGoodInst from dbo.ri_environments_new where bis_server='"+fromApp+"' AND db='"+fromDB+"'";
		HagStringList sourceDetails = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm2,7,"~",null,null);
		String iom = HagUtil.getWord0(sourceDetails.get(0),"~",1,true);
		String iomNfs = "\\\\"+fromApp+iom.substring(2,iom.length());
		String tomcat = HagUtil.getWord0(sourceDetails.get(0),"~",0,true);
		String tomcatNfs = "\\\\"+fromApp+tomcat.substring(2,tomcat.length());
		String version = HagUtil.getWord0(sourceDetails.get(0),"~",2,true);
		String patch = HagUtil.getWord0(sourceDetails.get(0),"~",3,true);
		String party = HagUtil.getWord0(sourceDetails.get(0),"~",4,true);
		String lastInst = HagUtil.getWord0(sourceDetails.get(0),"~",5,true);
		String lastGoodInst = HagUtil.getWord0(sourceDetails.get(0),"~",6,true);
		String startupNfs = "\\\\"+fromApp+"\\RI_JAVA\\RIjava_"+fromBN+"\\runtime\\startup";
		String debug2 = "\\\\ri-archive\\Saptech-Archive\\RI\\debug\\"+toApp+"_private_envs\\"+toDb;
		String debug1 = "\\\\ri-archive\\Saptech-Archive\\RI\\debug\\"+toApp+"_private_envs";
	
		String targetTomcatNfs = "\\\\"+toApp+"\\RI_JAVA\\RIjava_"+toDb+"\\tomcat";
		
		HagRc debugRc=new HagRc();
		HagUtil.reCreateFolder(debugRc, debug2);
		if(debugRc.rc!=0)
			return 	HagUtil.shortHtml("debug folder creation failed - call hagay 2527","red","bg");
		
		String sourceTomcat = "\\\\"+fromApp+"\\RI_JAVA\\RIjava_"+fromBN;
		//stopTomcat
		
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","stopTomcat_START","");
		String stopTomcat= HagUtil.stopTomcat(toApp, toDb);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","stopTomcat_END",stopTomcat);
		HagUtil.writeStringToFile(debug2+"\\stopTomcat.txt",stopTomcat);
		//stopEmergeListener
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","stopEmergeListener_START","");
		String stopEmergeListener= HagUtil.stopEmergeListener(toApp, toDb);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","stopEmergeListener_END",stopEmergeListener);
		HagUtil.writeStringToFile(debug2+"\\stopEmergeListener.txt",stopEmergeListener);
		
		//
		
		
		
		
		//copyDb
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","copyDb_START","");
		String copyDb=HagUtil.copyDb( fromDB,  toDb,  fromSql,  toSql  );
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","copyDb_END",copyDb);
		HagUtil.writeStringToFile(debug2+"\\copyDb.txt",copyDb);
		//set riprofileTable
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","setRIProfileTable_START","");
		String setRIProfileTable = replacePrivateDb_setRiProfileTable( toApp, toSql, toDb, connectionPort,fromCust,toBatchName);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","setRIProfileTable_END",setRIProfileTable);
		HagUtil.writeStringToFile(debug2+"\\setRIProfileTable.txt",setRIProfileTable);
		//set riuserTable
		HagRc hatRc_setRiUserTable = new HagRc(); 
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","setRIuserTable_START","");
		String setRIuserTable = replacePrivateDb_setRiUserTable(fromBN,toSql,toDb,toBatchName,hatRc_setRiUserTable);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","setRIuserTable_END",setRIuserTable);
//		HagUtil.writeStringToFile(debug2+"\\setRIuserTable.txt",setRIuserTable);
		hatRc_setRiUserTable.log.writeToFile(debug2+"\\setRIuserTable.txt", false);
		//set ri
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","setRIuser_START","");
		String setRIuser = replacePrivateDb_setRiUser(toSql,toDb,toBatchName);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","setRIuser_END",setRIuser);
		HagUtil.writeStringToFile(debug2+"\\setRIuser.txt",setRIuser);
		//set riadmin
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","setRIADMINuser_START","");
		String setRIADMINuser = replacePrivateDb_setRiadminUser(toSql,toDb);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","setRIADMINuser_END",setRIADMINuser);
		HagUtil.writeStringToFile(debug2+"\\setRIADMINuser.txt",setRIADMINuser);
		//set adapter
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","setAdapter_START","");
		String setAdapter = replacePrivateDb_setAdapter(toSql,toDb);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","setAdapter_END",setAdapter);
		HagUtil.writeStringToFile(debug2+"\\setAdapter.txt",setAdapter);
		
		//kill locks
		String vbLine="cscript //nologo \\\\orsayserver\\d\\gonteam\\#VBscript_and_SQL\\PROD\\Gopenfiles.vbs   /Opt:\"Kill\" /Server:\""+toApp+"\"    /Object:\"_"+toDb+"\"";
		String ansLock1 =  HagUtil.runCmd2(vbLine+"\n",false);
		HagUtil.sleep(3000);
		String ansLock2 =  HagUtil.runCmd2(vbLine+"\n",false);
		HagUtil.sleep(3000);
		String ansLock3 =  HagUtil.runCmd2(vbLine+"\n",false);
		
		HagUtil.writeStringToFile(debug2+"\\deletelockes.txt",ansLock1+"\n***\n"+ansLock2+"\n***\n"+ansLock3);

		//delete iom
		HagRc hagRcDelIom = new HagRc();
				HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","deleteIom_START","");
				String deleteIom = replacePrivateDb_deleteIom(toApp,toBatchName,iomNfs,hagRcDelIom);
				HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","deleteIom_END",deleteIom);
				HagUtil.writeStringToFile(debug2+"\\deleteIom.txt",hagRcDelIom.log.get(0));
			
		//replace iom
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","replaceIom_START","");
		HagRc hagRcCopyIom = new HagRc();
		String replaceIom = replacePrivateDb_replaceIom(toApp,toDb,iomNfs,hagRcCopyIom);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","replaceIom_END",replaceIom);
		HagUtil.writeStringToFile(debug2+"\\replaceIom.txt",hagRcCopyIom.log.get(0));
		//replace startup
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","replaceStartup_START","");
		String replaceStartup = replacePrivateDb_replaceStartup(toApp,toDb,startupNfs);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","replaceStartup_END",replaceStartup);
		HagUtil.writeStringToFile(debug2+"\\replaceStartup.txt",replaceStartup);

		//delete source tomcat logs
		//HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","deleteTomcatLogs_START","");
		//String deleteTomcatLogs = replacePrivateDb_deleteTomcatLogs(toApp,toDb,tomcatNfs);
		//HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","deleteTomcatLogs_END",deleteTomcatLogs);
		//HagUtil.writeStringToFile(debug2+"\\deleteTomcatLogs.txt",deleteTomcatLogs);
		
		//delete tomcat
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","recreateTomcatFolder_START",sourceTomcat);
		String recreateTomcatFolder = replacePrivateDb_deleteTomcat(toApp,toDb,tomcatNfs);
		//String recreateTomcatFolder = replacePrivateDb_deleteTomcat(toApp,toDb);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","recreateTomcatFolder_END",recreateTomcatFolder);
		HagUtil.writeStringToFile(debug2+"\\recreateTomcatFolder.txt",recreateTomcatFolder);
		//copy tomcat
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","copyTomcat_START",sourceTomcat);
		String copyTomcat = replacePrivateDb_copyTomcat(toApp,toDb,tomcatNfs);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","copyTomcat_END",copyTomcat);
		HagUtil.writeStringToFile(debug2+"\\replaceTomcat.txt",copyTomcat);
		//replace Properties
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","replaceProperties_START","");
		String replaceProperties = replacePrivateDb_replaceProperties(fromApp,fromBN,toApp,toDb,toSql,toBatchName);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","replaceProperties_END",replaceProperties);
		HagUtil.writeStringToFile(debug2+"\\replaceProperties.txt",replaceProperties);
		//setTomcatProperties
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","setTomcatProperties_START","");
		String setTomcatProperties = replacePrivateDb_setTomcatProperties(version,serverPort,connectionPort,debugPort,fromBN,toApp,toDb,log4jLevel);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","setTomcatProperties_END",setTomcatProperties);
		HagUtil.writeStringToFile(debug2+"\\setTomcatProperties.txt",setTomcatProperties);
		//replace {batchName} eObjects
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","replaceEobjects_START","");
		String clientLocation = HagUtil.regGetValSingleRemote(toApp,"HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Sapiens\\Web","DBRoot","REG_SZ");
	//	String clientLocation4530 = clientLocation+"4530.map";
		String replaceEobjects= replacePrivateDb_replaceEobjects(fromApp,fromBN,toApp,toDb,user,clientLocation);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","replaceEobjects_END",replaceEobjects);
		HagUtil.writeStringToFile(debug2+"\\replaceEobjects.txt",replaceEobjects);
		//replace replaceCmTable
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","replaceCmTable_START","");
		String replaceCmTable= replacePrivateDb_replaceCmTable(toApp,toDb,version,patch,party,lastInst,lastGoodInst);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","replaceCmTable_END",replaceCmTable);
		HagUtil.writeStringToFile(debug2+"\\replaceCmTable.txt",replaceCmTable);
		//replace replaceJasper_Runtime_TemplatesFolder
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","replaceJasper_START","");
		String replaceJasper_Runtime_TemplatesFolder= replaceJasper_Runtime_TemplatesFolder(fromApp, fromBN,toApp,toBatchName,party);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","replaceJasper_END",replaceJasper_Runtime_TemplatesFolder);
		HagUtil.writeStringToFile(debug2+"\\replaceJasper_Runtime_TemplatesFolder.txt",replaceJasper_Runtime_TemplatesFolder);
		//replace replaceInputfilesFolder
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","stopTomcat_START","");
		String replaceInputfilesFolder= replaceInputfilesFolder( fromApp, fromBN,toApp,toDb);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","stopTomcat_END",replaceInputfilesFolder);
		HagUtil.writeStringToFile(debug2+"\\replaceInputfilesFolder.txt",replaceInputfilesFolder);				
		//startEmergeListener1
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","startEmergeListener_START","");
		String startEmergeListener= HagUtil.startEmergeListener1(toApp, toDb);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","startEmergeListener_END",startEmergeListener);
		HagUtil.writeStringToFile(debug2+"\\startEmergeListener.txt",startEmergeListener);
		//startTomcat
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","startTomcat_START","");
		String startTomcat= HagUtil.startTomcat(toApp, toDb,null);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","startTomcat_END",startTomcat);

		HagUtil.writeStringToFile(debug2+"\\startTomcat.txt",startTomcat);	
	
		
		ans.append("<tr><td>stop tomcat</td>"+stopTomcat+"</tr>");
		ans.append("<tr><td>stop eMerge listener</td>"+stopEmergeListener+"</tr>");

		ans.append("<tr><td>replace private DB</td>"+copyDb+"</tr>");
		ans.append("<tr><td>set RIPROFILE table</td>"+setRIProfileTable+"</tr>");
		ans.append("<tr><td>set RIUSER table</td>"+setRIuserTable+"</tr>");
		ans.append("<tr><td>set RI SQL user</td>"+setRIuser+"</tr>");
		ans.append("<tr><td>set RIADMIN SQL user</td>"+setRIADMINuser+"</tr>");
		ans.append("<tr><td>set adapter: </td>"+setAdapter+"</tr>");
		ans.append("<tr><td>replace IOM</td>"+replaceIom+"</tr>");
		ans.append("<tr><td>replace runtime startup</td>"+replaceStartup+"</tr>");
		//ans.append("<tr><td>delete tomcat logs(source)</td>"+deleteTomcatLogs+"</tr>");
		
		ans.append("<tr><td>recreate tomcat target folder</td>"+recreateTomcatFolder+"</tr>");
		ans.append("<tr><td>copy tomcat</td>"+copyTomcat+"</tr>");
		ans.append("<tr><td>Replace properties</td>"+replaceProperties+"</tr>");
		ans.append("<tr><td>Set tomcat properties</td>"+setTomcatProperties+"</tr>");
		ans.append("<tr><td>replace eObjects</td>"+replaceEobjects+"</tr>");
		ans.append("<tr><td>replace cmTable</td>"+replaceCmTable+"</tr>");
		ans.append("<tr><td>replace Jasper_Runtime_Template folder</td>"+replaceJasper_Runtime_TemplatesFolder+"</tr>");
		ans.append("<tr><td>replace INPUTFILES folder</td>"+replaceInputfilesFolder+"</tr>");
		ans.append("<tr><td>start eMerge listener</td>"+startEmergeListener+"</tr>");
		ans.append("<tr><td>start tomcat</td>"+startTomcat+"</tr>");
		ans.append("<tr><td>start iWay</td><td><a href=\"http://"+toApp+"/sapweb/Admin/Logon.htm?"+toApp+"&/sapweb&"+toApp+"-"+toDb+"\">http://"+toApp+"/sapweb/Admin/Logon.htm?"+toApp+"&/sapweb&"+toApp+"-"+toDb+"</a></td></tr>");
		ans.append("</table>");
		//String linkToDebug 	= "<a href=\"file:///ri-archive/Saptech-Archive/RI/debug/private_envs\">link to debug</a>";
		String ans1		= HagUtil.sendMail_hag(HagUtil.cfgTeamMail,"david.ha@sapiens.com;gonen.s@sapiens.com","david.ha@sapiens.com","refresh private from "+fromApp+"("+fromBN+") to "+toApp+"("+toDb+") by "+user,ans.toString()); 
		return ans.toString();
	*/
	}
	
	public String 	replacePrivateDbNew(String user,String myBrowser,String myWebAppsServer,
			String fromDB,String fromBN,String fromSql,String fromApp,String fromCust,
			String toDb,String toBatchName,String toSql,String toApp){
		StringBuilder ans = new StringBuilder("<h3>Replace private environment</h3><table bgColor=\"#aaaaaa\" cellpadding=\"3\" border =\"1\">");
	//	String user 	= request.getParameter("user").trim();

	//	String fromDB 	= request.getParameter("fromDb").trim();
	//	String fromBN 	= request.getParameter("fromBn").trim();
	//	String fromSql 	= request.getParameter("fromSql").trim();
	//	String fromApp 	= request.getParameter("fromApp").trim();
	//	String fromCust 	= request.getParameter("fromCust").trim();
		
	//	String [] tgtEnv		= request.getParameterValues("cbEnvs");
	//	String line = tgtEnv[0];
	//	String toApp 	= HagUtil.getWord0(line, "!",0, true);
	//	String toBatchName 	= HagUtil.getWord0(line, "!",1, true);
	//	String toSql 	= HagUtil.getWord0(line, "!",2, true);
	//	String toDb 	= HagUtil.getWord0(line, "!",3, true);
		//String toApp 	= request.getParameter("targetAPP").trim();
		//String toSql 	= request.getParameter("targetSQL").trim();
		
		//String toDb 	= request.getParameter("toDb").trim();
		//String toBatchName=toDb;
		String log4jLevel  = "<select class=\"c30\" id=\"log4jCombo\" name =\"log4jCombo\" bgColor=\"#66ff66\"><option class=\"c30\" value=\"error\">error</option><option class=\"c30\" value=\"debug\">debug</option></select>";
		
		//String myBrowser 	= request.getParameter("myBrowser").trim();
	//	String myWebAppsServer = request.getHeader("Host");
		
	
		//String toSql 	= "RIPERFSQL02";
		//String toApp 	= "RIPERFAPP02";
		
		//if(1==1)
		//	return myBrowser+"$$$"+myWebAppsServer;
		StringBuilder ans0 = new StringBuilder("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" border =\"1\">");
		ans0.append("<tr><td> myWebAppsServer</td><td>"+myWebAppsServer+"</td></tr>");
		ans0.append("<tr><td> myBrowser</td><td>"+myBrowser+"</td></tr>");
		ans0.append("<tr><td> user</td><td>"+user+"</td></tr>");
		ans0.append("<tr><td> fromDB</td><td>"+fromDB+"</td></tr>");
		ans0.append("<tr><td> fromBN</td><td>"+fromBN+"</td></tr>");
		ans0.append("<tr><td> fromSql</td><td>"+fromSql+"</td></tr>");
		ans0.append("<tr><td> fromApp</td><td>"+fromApp+"</td></tr>");
		ans0.append("<tr><td> fromCust</td><td>"+fromCust+"</td></tr>");
		ans0.append("<tr><td> toDb</td><td>"+toDb+"</td></tr>");
		ans0.append("<tr><td> log4jLevel</td><td>"+log4jLevel+"</td></tr>");
		ans0.append("</table>");
		
	
		String ans01		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+user,HagUtil.mailList_hag,"pre-refresh private from "+fromApp+"("+fromBN+") to "+toApp+"("+toDb+") by "+user,ans0.toString()); 
		
		String stm1 = "select emerge_port,server_port,connection_port,debug_port from dbo.ri_environments_new where  status='A' and bis_server='"+toApp+"' AND db='"+toDb+"'";
		//stm = HagUtil.replaceStr(stm,"{SERVER}","'"+server+"'");
		HagSQL hagSQL=new HagSQL();
		HagStringList privateDetails = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,4,"~",null,null);
		String emerge_port = HagUtil.getWord0(privateDetails.get(0),"~",0,true); 
		String serverPort = HagUtil.getWord0(privateDetails.get(0),"~",1,true);
		String connectionPort = HagUtil.getWord0(privateDetails.get(0),"~",2,true);
		String debugPort = HagUtil.getWord0(privateDetails.get(0),"~",3,true);
		
		String stm2 = "select tomcat_location,iom,version,patch,party,lastInst,lastGoodInst from dbo.ri_environments_new where status='A' and bis_server='"+fromApp+"' AND db='"+fromDB+"'";
		HagStringList sourceDetails = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm2,7,"~",null,null);
		String iom = HagUtil.getWord0(sourceDetails.get(0),"~",1,true);
		String iomNfs = "\\\\"+fromApp+iom.substring(2,iom.length());
		String tomcat = HagUtil.getWord0(sourceDetails.get(0),"~",0,true);
		String tomcatNfs = "\\\\"+fromApp+tomcat.substring(2,tomcat.length());
		String version = HagUtil.getWord0(sourceDetails.get(0),"~",2,true);
		String patch = HagUtil.getWord0(sourceDetails.get(0),"~",3,true);
		String party = HagUtil.getWord0(sourceDetails.get(0),"~",4,true);
		String lastInst = HagUtil.getWord0(sourceDetails.get(0),"~",5,true);
		String lastGoodInst = HagUtil.getWord0(sourceDetails.get(0),"~",6,true);
		String startupNfs = "\\\\"+fromApp+"\\RI_JAVA\\RIjava_"+fromBN+"\\runtime\\startup";
		
		String debugBase = "\\\\ri-archive\\Saptech-Archive\\RI\\debug\\private_envs_new";
		String debugHtmlFile = debugBase+"\\iframes\\"+toApp+"_"+toBatchName+".html";
		String debugFolder = debugBase+"\\"+toApp+"_"+toBatchName;
		
		
		String targetTomcatNfs = "\\\\"+toApp+"\\RI_JAVA\\RIjava_"+toDb+"\\tomcat";
		
		HagRc debugRc=new HagRc();
		HagUtil.reCreateFolder(debugRc, debugFolder);
		if(debugRc.rc!=0)
			return 	HagUtil.shortHtml("debug folder creation failed - call hagay 2527","red","bg");
		
		String sourceTomcat = "\\\\"+fromApp+"\\RI_JAVA\\RIjava_"+fromBN;
		//stopTomcat
		
		HagUtil.updateTimeStamp(debugHtmlFile,"stopTomcat_START","");
		String stopTomcat= HagUtil.stopTomcat(toApp, toDb);
		HagUtil.updateTimeStamp(debugHtmlFile,"stopTomcat_END",stopTomcat);
		HagUtil.writeStringToFile(debugFolder+"\\stopTomcat.txt",stopTomcat);
		//stopEmergeListener
		HagUtil.updateTimeStamp(debugHtmlFile,"stopEmergeListener_START","");
		String stopEmergeListener= HagUtil.stopEmergeListener(toApp, toDb);
		HagUtil.updateTimeStamp(debugHtmlFile,"stopEmergeListener_END",stopEmergeListener);
		HagUtil.writeStringToFile(debugFolder+"\\stopEmergeListener.txt",stopEmergeListener);
		
		//
		
		
		
		
		//copyDb
		HagUtil.updateTimeStamp(debugHtmlFile,"copyDb_START","");
		String copyDb=HagUtil.copyDb( fromDB,  toDb,  fromSql,  toSql  );
		HagUtil.updateTimeStamp(debugHtmlFile,"copyDb_END",copyDb);
		HagUtil.writeStringToFile(debugFolder+"\\copyDb.txt",copyDb);
		//set riprofileTable
		HagUtil.updateTimeStamp(debugHtmlFile,"setRIProfileTable_START","");
		String setRIProfileTable = replacePrivateDb_setRiProfileTable( toApp, toSql, toDb, connectionPort,fromCust,toBatchName);
		HagUtil.updateTimeStamp(debugHtmlFile,"setRIProfileTable_END",setRIProfileTable);
		HagUtil.writeStringToFile(debugFolder+"\\setRIProfileTable.txt",setRIProfileTable);
		//set riuserTable
		HagRc hatRc_setRiUserTable = new HagRc(); 
		HagUtil.updateTimeStamp(debugHtmlFile,"setRIuserTable_START","");
		String setRIuserTable = replacePrivateDb_setRiUserTable(fromBN,toSql,toDb,toBatchName,hatRc_setRiUserTable);
		HagUtil.updateTimeStamp(debugHtmlFile,"setRIuserTable_END",setRIuserTable);
//		HagUtil.writeStringToFile(debug2+"\\setRIuserTable.txt",setRIuserTable);
		hatRc_setRiUserTable.log.writeToFile(debugFolder+"\\setRIuserTable.txt", false);
		//set ri
		HagUtil.updateTimeStamp(debugHtmlFile,"setRIuser_START","");
		String setRIuser = replacePrivateDb_setRiUser(toSql,toDb,toBatchName);
		HagUtil.updateTimeStamp(debugHtmlFile,"setRIuser_END",setRIuser);
		HagUtil.writeStringToFile(debugFolder+"\\setRIuser.txt",setRIuser);
		//set riadmin
		HagUtil.updateTimeStamp(debugHtmlFile,"setRIADMINuser_START","");
		String setRIADMINuser = replacePrivateDb_setRiadminUser(toSql,toDb);
		HagUtil.updateTimeStamp(debugHtmlFile,"setRIADMINuser_END",setRIADMINuser);
		HagUtil.writeStringToFile(debugFolder+"\\setRIADMINuser.txt",setRIADMINuser);
		//set adapter
		HagUtil.updateTimeStamp(debugHtmlFile,"setAdapter_START","");
		String setAdapter = replacePrivateDb_setAdapter(toSql,toDb);
		HagUtil.updateTimeStamp(debugHtmlFile,"setAdapter_END",setAdapter);
		HagUtil.writeStringToFile(debugFolder+"\\setAdapter.txt",setAdapter);
		
		//kill locks
		String vbLine="cscript //nologo \\\\orsayserver\\d\\gonteam\\#VBscript_and_SQL\\PROD\\Gopenfiles.vbs   /Opt:\"Kill\" /Server:\""+toApp+"\"    /Object:\"_"+toDb+"\"";
		String ansLock1 =  HagUtil.runCmd2(vbLine+"\n",false);
		HagUtil.sleep(3000);
		String ansLock2 =  HagUtil.runCmd2(vbLine+"\n",false);
		HagUtil.sleep(3000);
		String ansLock3 =  HagUtil.runCmd2(vbLine+"\n",false);
		
		HagUtil.writeStringToFile(debugFolder+"\\deletelockes.txt",ansLock1+"\n***\n"+ansLock2+"\n***\n"+ansLock3);

		//delete iom
		HagRc hagRcDelIom = new HagRc();
		HagUtil.updateTimeStamp(debugHtmlFile,"deleteIom_START","");
		String deleteIom = replacePrivateDb_deleteIom(toApp,toBatchName,iomNfs,hagRcDelIom);
		HagUtil.updateTimeStamp(debugHtmlFile,"deleteIom_END",deleteIom);
		HagUtil.writeStringToFile(debugFolder+"\\deleteIom.txt",hagRcDelIom.log.get(0));
			
		//replace iom
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceIom_START","");
		HagRc hagRcCopyIom = new HagRc();
		String replaceIom = replacePrivateDb_replaceIom(toApp,toDb,iomNfs,hagRcCopyIom);
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceIom_END",replaceIom);
		HagUtil.writeStringToFile(debugFolder+"\\replaceIom.txt",hagRcCopyIom.log.get(0));
		
		//delete iom cust
		HagRc hagRcDelIomCust = new HagRc();
		HagUtil.updateTimeStamp(debugHtmlFile,"deleteIomCust_START","");
		String deleteIomCust = replacePrivateDb_deleteIomCust(toApp,toBatchName,hagRcDelIomCust);
		HagUtil.updateTimeStamp(debugHtmlFile,"deleteIomCust_END",deleteIomCust);
		HagUtil.writeStringToFile(debugFolder+"\\deleteIomCust.txt",hagRcDelIomCust.log.get(0));
			
		//replace iom cust
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceIomCust_START","");
		HagRc hagRcCopyIomCust = new HagRc();
		String replaceIomCust = replacePrivateDb_replaceIomCust(toApp,toDb,iomNfs,hagRcCopyIomCust);
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceIomCust_END",replaceIomCust);
		HagUtil.writeStringToFile(debugFolder+"\\replaceIomCust.txt",hagRcCopyIomCust.log.get(0));
		
		//replace startup
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceStartup_START","");
		String replaceStartup = replacePrivateDb_replaceStartup(toApp,toDb,startupNfs);
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceStartup_END",replaceStartup);
		HagUtil.writeStringToFile(debugFolder+"\\replaceStartup.txt",replaceStartup);

		//delete source tomcat logs
		//HagUtil.updateTimeStamp(debugHtmlFile,"deleteTomcatLogs_START","");
		//String deleteTomcatLogs = replacePrivateDb_deleteTomcatLogs(toApp,toDb,tomcatNfs);
		//HagUtil.updateTimeStamp(debugHtmlFile,"deleteTomcatLogs_END",deleteTomcatLogs);
		//HagUtil.writeStringToFile(debug2+"\\deleteTomcatLogs.txt",deleteTomcatLogs);
		
		//delete tomcat
		HagUtil.updateTimeStamp(debugHtmlFile,"recreateTomcatFolder_START",sourceTomcat);
		String recreateTomcatFolder = replacePrivateDb_deleteTomcat(toApp,toDb,tomcatNfs);
		//String recreateTomcatFolder = replacePrivateDb_deleteTomcat(toApp,toDb);
		HagUtil.updateTimeStamp(debugHtmlFile,"recreateTomcatFolder_END",recreateTomcatFolder);
		HagUtil.writeStringToFile(debugFolder+"\\recreateTomcatFolder.txt",recreateTomcatFolder);
		//copy tomcat
		HagUtil.updateTimeStamp(debugHtmlFile,"copyTomcat_START",sourceTomcat);
		String copyTomcat = replacePrivateDb_copyTomcat(toApp,toDb,tomcatNfs);
		HagUtil.updateTimeStamp(debugHtmlFile,"copyTomcat_END",copyTomcat);
		HagUtil.writeStringToFile(debugFolder+"\\replaceTomcat.txt",copyTomcat);
		//replace Properties
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceProperties_START","");
		String replaceProperties = replacePrivateDb_replaceProperties(fromApp,fromBN,toApp,toDb,toSql,toBatchName);
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceProperties_END",replaceProperties);
		HagUtil.writeStringToFile(debugFolder+"\\replaceProperties.txt",replaceProperties);
		//setTomcatProperties
		HagUtil.updateTimeStamp(debugHtmlFile,"setTomcatProperties_START","");
		String setTomcatProperties = replacePrivateDb_setTomcatProperties(version,serverPort,connectionPort,debugPort,fromBN,toApp,toDb,log4jLevel,debugFolder);
		HagUtil.updateTimeStamp(debugHtmlFile,"setTomcatProperties_END",setTomcatProperties);
		HagUtil.writeStringToFile(debugFolder+"\\setTomcatProperties.txt",setTomcatProperties);
		//replace {batchName} eObjects
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceEobjects_START","");
		String clientLocation = HagUtil.regGetValSingleRemote(toApp,"HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Sapiens\\Web","DBRoot","REG_SZ");
	//	String clientLocation4530 = clientLocation+"4530.map";
		String replaceEobjects= replacePrivateDb_replaceEobjects(fromApp,fromBN,toApp,toDb,user,clientLocation);
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceEobjects_END",replaceEobjects);
		HagUtil.writeStringToFile(debugFolder+"\\replaceEobjects.txt",replaceEobjects);
		//replace replaceCmTable
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceCmTable_START","");
		String replaceCmTable= replacePrivateDb_replaceCmTable(toApp,toDb,version,patch,party,lastInst,lastGoodInst);
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceCmTable_END",replaceCmTable);
		HagUtil.writeStringToFile(debugFolder+"\\replaceCmTable.txt",replaceCmTable);
		//replace replaceJasper_Runtime_TemplatesFolder
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceJasper_START","");
		String replaceJasper_Runtime_TemplatesFolder= replaceJasper_Runtime_TemplatesFolder(fromApp, fromBN,toApp,toBatchName,party);
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceJasper_END",replaceJasper_Runtime_TemplatesFolder);
		HagUtil.writeStringToFile(debugFolder+"\\replaceJasper_Runtime_TemplatesFolder.txt",replaceJasper_Runtime_TemplatesFolder);
		//replace replaceInputfilesFolder
		HagUtil.updateTimeStamp(debugHtmlFile,"stopTomcat_START","");
		String replaceInputfilesFolder= replaceInputfilesFolder( fromApp, fromBN,toApp,toDb);
		HagUtil.updateTimeStamp(debugHtmlFile,"stopTomcat_END",replaceInputfilesFolder);
		HagUtil.writeStringToFile(debugFolder+"\\replaceInputfilesFolder.txt",replaceInputfilesFolder);				
		//startEmergeListener1
		HagUtil.updateTimeStamp(debugHtmlFile,"startEmergeListener_START","");
		String startEmergeListener= HagUtil.startEmergeListener1(toApp, toDb);
		HagUtil.updateTimeStamp(debugHtmlFile,"startEmergeListener_END",startEmergeListener);
		HagUtil.writeStringToFile(debugFolder+"\\startEmergeListener.txt",startEmergeListener);
		//startTomcat
		HagUtil.updateTimeStamp(debugHtmlFile,"startTomcat_START","");
		String startTomcat= HagUtil.startTomcat(toApp, toDb,null);
		HagUtil.updateTimeStamp(debugHtmlFile,"startTomcat_END",startTomcat);

		HagUtil.writeStringToFile(debugFolder+"\\startTomcat.txt",startTomcat);	
	
		
		ans.append("<tr><td>stop tomcat</td>"+stopTomcat+"</tr>");
		ans.append("<tr><td>stop eMerge listener</td>"+stopEmergeListener+"</tr>");

		ans.append("<tr><td>replace private DB</td>"+copyDb+"</tr>");
		ans.append("<tr><td>set RIPROFILE table</td>"+setRIProfileTable+"</tr>");
		ans.append("<tr><td>set RIUSER table</td>"+setRIuserTable+"</tr>");
		ans.append("<tr><td>set RI SQL user</td>"+setRIuser+"</tr>");
		ans.append("<tr><td>set RIADMIN SQL user</td>"+setRIADMINuser+"</tr>");
		ans.append("<tr><td>set adapter: </td>"+setAdapter+"</tr>");
		ans.append("<tr><td>replace IOM</td>"+replaceIom+"</tr>");
		ans.append("<tr><td>replace IOM cust</td>"+replaceIomCust+"</tr>");
		ans.append("<tr><td>replace runtime startup</td>"+replaceStartup+"</tr>");
		//ans.append("<tr><td>delete tomcat logs(source)</td>"+deleteTomcatLogs+"</tr>");
		
		ans.append("<tr><td>recreate tomcat target folder</td>"+recreateTomcatFolder+"</tr>");
		ans.append("<tr><td>copy tomcat</td>"+copyTomcat+"</tr>");
		ans.append("<tr><td>Replace properties</td>"+replaceProperties+"</tr>");
		ans.append("<tr><td>Set tomcat properties</td>"+setTomcatProperties+"</tr>");
		ans.append("<tr><td>replace eObjects</td>"+replaceEobjects+"</tr>");
		ans.append("<tr><td>replace cmTable</td>"+replaceCmTable+"</tr>");
		ans.append("<tr><td>replace Jasper_Runtime_Template folder</td>"+replaceJasper_Runtime_TemplatesFolder+"</tr>");
		ans.append("<tr><td>replace INPUTFILES folder</td>"+replaceInputfilesFolder+"</tr>");
		ans.append("<tr><td>start eMerge listener</td>"+startEmergeListener+"</tr>");
		ans.append("<tr><td>start tomcat</td>"+startTomcat+"</tr>");
		ans.append("<tr><td>start iWay</td><td><a href=\"http://"+toApp+"/sapweb/Admin/Logon.htm?"+toApp+"&/sapweb&"+toApp+"-"+toDb+"\">http://"+toApp+"/sapweb/Admin/Logon.htm?"+toApp+"&/sapweb&"+toApp+"-"+toDb+"</a></td></tr>");
		ans.append("</table>");
		//String linkToDebug 	= "<a href=\"file:///ri-archive/Saptech-Archive/RI/debug/private_envs\">link to debug</a>";
		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+user,HagUtil.mailList_hag,"refresh private from "+fromApp+"("+fromBN+") to "+toApp+"("+toDb+") by "+user,ans.toString()); 
		return ans.toString();
	}
	
	
	
	private void composeReqFile(HagStringList list,String verJira,	StringBuilder cfg,boolean isWin,StringBuilder distTo ) {
		HagStringList servers = new HagStringList();
		for (int i = 0; i < list.size(); i++) {
			String temp = list.get(i);
			
			String temp1 = temp.substring(0,temp.lastIndexOf("-"));
			if (!servers.isInList(temp1)) {
				servers.add(temp1);
			}
		}
		String serversLine = servers.convertToString("&nbsp;&nbsp;&nbsp;");
		for (int i = 0; i < list.size(); i++) {
			String temp = list.get(i);
//			String temp1 = HagUtil.getWord0(temp, "-", 0, true);
	//		String temp2 = HagUtil.getWord0(temp, "-", 1, true);

			String temp1 = temp.substring(0,temp.lastIndexOf("-"));
			String temp2 = temp.substring(temp.lastIndexOf("-")+1,temp.length());

			
			if(isWin  && temp2.equalsIgnoreCase("RI"))
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
		distTo.append(servers.convertToString("&nbsp;")).append("<br>"); 
		cfg.append("=>=>cb&nbsp;").append(verJira).append("&nbsp;&nbsp;&nbsp;").append(servers.convertToString("&nbsp;")).append("&nbsp;<=<=<br>=>=>ms&nbsp;").append(serversLine).append("&nbsp;<=<=<br>");
		if(isWin){
			String hyun = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\";
			String ff1 = hyun+"temp\\" + verJira + "_new.req";
			String ff2 = hyun+"temp\\" + verJira + "_hat.req";
			HagUtil.deleteFile(ff1,  false);
			servers.writeToFile(ff1, null, false);
			HagUtil.deleteFile(ff2,  false);
			servers.writeToFile(ff2, null, false);
		}
	}
	
	public String 	riCrtNewEnv(HttpServletRequest request, HttpServletResponse response,String plat,String user){
		return HagForms.riCrtNewEnv(plat,user);
	}
	public String 	insertClientModuleMig(HttpServletRequest request, HttpServletResponse response,String plat,String user){
		return HagForms.insertClientModuleMig_before(plat,user);
	}
	public String 	riRefreshEnv(HttpServletRequest request, HttpServletResponse response,String plat,String user){
		return HagForms.riRefreshEnv(plat,user);
	}
	
	
	public String 	reqRelease(HttpServletRequest request, HttpServletResponse response,String user){
		return HagForms.reqRelease(user);
	}
	
	public String 	addMigRequest(HttpServletRequest request, HttpServletResponse response,String user){
		return HagForms.addMigRequest(user);
	}
	public String 	riCrtNewEnvI5os(HttpServletRequest request, HttpServletResponse response){
		return "riCrtNewEnvI5os";
	}

	public String 	riFtpPackRequest_update(HttpServletRequest request, HttpServletResponse response){
		String[] riRel 			= {"0000","00","00"}; 
		String riVersions 		= request.getParameter("riVersions");
		String riMaint		 	= request.getParameter("riMaint");
		String done_by 			= request.getParameter("done_by");
		String action 			= request.getParameter("action");
		String appendToMail		= request.getParameter("appendToMail");
		String instFromFtp   	= request.getParameter("instFromFtp");
		String [] cbgroup1		= request.getParameterValues("cbgroup1");
		String [] cbgroup2		= request.getParameterValues("cbgroup2");
		//String from				= HagUtil.cfgTeamMail;
		String to				= "david.ha@sapiens.com";
		String toCc				= "david.ha@sapiens.com";
		String subject			= "# RI-update FTP-Pack request ,sent by=" + done_by;
		String riUpdate 		= request.getParameter("riUpdate");

		if (cbgroup1 == null || cbgroup1.length == 0)
			return HagUtil.shortHtml("You must select at least one cmInstaller tab","red","bg");
		if (cbgroup2 != null && cbgroup2.length > 0 && !cbgroup1[1].equals("RI Scripts (eObjects)"))
			return HagUtil.shortHtml("You cannot select  \"pack RIFLARE folder\" without selecting \"RI Scripts (eObjects)\" Tab","red","bg");
	//	if (cbgroup2 != null && cbgroup2.length > 0 && riVersions.equals("0603"))
	//		return HagUtil.shortHtml("You cannot select  jasper update when version=0603","red","bg");

		riRel[0] = riVersions;
		riRel[1] = riMaint;
		riRel[2] = riUpdate;

		StringBuilder winS= new StringBuilder();
		StringBuilder desc= new StringBuilder();
		String appendToMail1 = HagUtil.replaceStr(appendToMail, "\n", "<br>");
		
		if(appendToMail1.trim().length()==0)
			appendToMail1=".";

		String [] riProp = {".",".","."};
		HagUtil.setPropRi( action,appendToMail1,riProp,riVersions);
		String content 	= HagUtil.getContentRiFtpPack(riRel,winS,done_by,action,
				cbgroup1,instFromFtp,cbgroup2,appendToMail1,riProp[0],toCc); 

		if(content.startsWith("<html><body>ERROR:")){
			try {
				String content1=HagUtil.replaceStr(content,"&" ,"");
				response.sendRedirect("done.jsp?value="+content1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return content;
		}


		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+done_by,HagUtil.mailList_hag,subject,content);
		String ff = HagUtil.cfgMenuWebLoc+"\\RiFtpPackLogs"+riVersions+".html";
		String ans2 = HagUtil.updateLogFileRiFtp(riVersions+"M"+riMaint+"U"+riUpdate,winS.toString(),".",action,done_by,ff,riProp);
		
		HagUtil.writeToRelDiary2("Pack","WIN","Request",riVersions+"M"+riMaint+"U"+riUpdate,"00","OK",winS.toString(),".","cfgMenuWeb",done_by,".",".");
		

		try {
			String content1=HagUtil.replaceStr(content,"&" ,"");
			response.sendRedirect("done.jsp?value=The following request sent by mail:<br><br>"+content1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content+"<br><br>Mail sent to:"+to+"<br>cc:"+toCc;
	}


	public String 	riChangeUpdate(HttpServletRequest request, HttpServletResponse response){
		String curOrg 		= request.getParameter("cur");
		String cur	 		=HagUtil.getWord0(curOrg,"~",0,true);
		String prev	 		=HagUtil.getWord0(curOrg,"~",1,true); //06.06M02U01
		String prev_	 	=HagUtil.replaceStr(prev, ".", "_"); //06_06M02U01
		String prev_lower 	=prev_.toLowerCase(); //06_06m02u01


		String newAdd		 	= request.getParameter("newAdd");
		String propFile = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\list\\riRel.properties";
		String cur1 = cur.substring(0,2);
		String cur2 = cur.substring(2,7);
		String cur3 = cur.substring(5,7);

		String str1 = 	cur1+"."+cur2.toUpperCase()+newAdd.toUpperCase(); 	//06.06M02U01
		String str2 = 	cur1+"_"+cur2.toLowerCase()+newAdd.toLowerCase(); 	//06_06m02u01
		String str3 =	cur3+newAdd.toUpperCase(); 							//02U01
		String str4 = 	cur1+"_"+cur2.toUpperCase()+newAdd.toLowerCase(); 	//06_06M02u01

		HagStringList ans = new HagStringList();

		String extraDev = HagUtil.getPropertyVal(propFile,cur+"_extraDev");
		//rs
		ans.add("build logs folders:");
		ans.add("------------------");
		String ff = "\\\\hyundai.sapiens.com\\hyun05ri\\rs\\v"+str2;
		String ff1 = "\\\\\\\\HYUNDAI.SAPIENS.COM\\\\HYUN05ri\\\\rs\\\\v"+str2;
		ans.add(HagUtil.createFolder(ff));
		ans.add(HagUtil.createFolder(ff+"\\cl"));
		ans.add(HagUtil.createFolder(ff+"\\win"));
		ans.add(HagUtil.createFolder(ff+"\\win\\dev"));
		ans.add(HagUtil.createFolder(ff+"\\win\\int"));
		if(!extraDev.equalsIgnoreCase("NO")){
			ans.add(HagUtil.createFolder(ff+"A"));
			ans.add(HagUtil.createFolder(ff+"A\\cl"));
			ans.add(HagUtil.createFolder(ff+"A\\win"));
			ans.add(HagUtil.createFolder(ff+"A\\win\\dev"));
			ans.add(HagUtil.createFolder(ff+"A\\win\\int"));
			ans.add(HagUtil.createFolder(ff+"B"));
			ans.add(HagUtil.createFolder(ff+"B\\cl"));
			ans.add(HagUtil.createFolder(ff+"B\\win"));
			ans.add(HagUtil.createFolder(ff+"B\\win\\dev"));
			ans.add(HagUtil.createFolder(ff+"B\\win\\int"));
		}
		//change riRel.properties:
		ans.add(" ");
		ans.add("change riRel.properties:");
		ans.add("-----------------------");
		String propFile1 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\list\\riRel.properties";
		String [][]varVals = 	{
				{cur+"_jiraVer",str1},
				{cur+"_currentRel",cur3+newAdd.toUpperCase()},
				{cur+"_rsFolder",ff1+"\\\\win\\\\"}
		};
		String [][]varVals1 = 	{
				{cur+"_jiraVer",str1},
				{cur+"_currentRel",str3},
				{cur+"_rsFolder",ff1+"\\\\win\\\\"},
				{cur+"a_jiraVer",str1},
				{cur+"a_currentRel",str3},
				{cur+"a_rsFolder",ff1+"A\\\\win\\\\"},
				{cur+"b_jiraVer",str1},
				{cur+"b_currentRel",str3},
				{cur+"b_rsFolder",ff1+"B\\\\win\\\\"}
		};

		if(extraDev.equalsIgnoreCase("NO"))
			ans.add(HagUtil.changeRirelProperties(propFile1,varVals));
		else
			ans.add(HagUtil.changeRirelProperties(propFile1,varVals1));

		// change fromIist
		ans.add(" ");
		ans.add("change fromInstList1:");
		ans.add("--------------------");
		String propFile2 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\list\\cfg.list";
		if(cur.startsWith("0603") || cur.startsWith("0605") )
			ans.add(HagUtil.changeStringInPropFile(propFile2,"fromInstList2",prev+"()",str1+"()"));
		else
			ans.add(HagUtil.changeStringInPropFile(propFile2,"fromInstList1",prev+"_new()",str1+"_new()"));

		// change logViewer
		ans.add(" ");
		ans.add("change logViewer tree:");
		ans.add("--------------------");
		String propFile3 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\txt\\logViewer.ini";
		String strS = "3)as400-int";
		String strI	="\\\\hyundai.sapiens.com\\hyun05ri\\rs\\v"+prev_lower+"\\cl\\";	
		HagStringList strN = new HagStringList();
		strN.add("*********************************************************************************************************************");
		strN.add("2)v"+str4+"       ~NA                                                                             ~N        ~0,0,0");
		strN.add("3)win-dev           ~"+ff+"\\win\\dev\\                         ~N        ~0,0,0");
		strN.add("3)win-int           ~"+ff+"\\win\\int\\                         ~N        ~0,0,0");
		strN.add("3)as400-int         ~"+ff+"\\win\\cl\\                          ~N        ~0,0,0");

		ans.add(HagUtil.appendStringtoPropFile(propFile3,strS,strI,strN));
		//change l cfgMenuWeb\lists\versions.txt 
		ans.add(" ");
		ans.add("change cfgMenuWeb versions.txt:");
		ans.add("--------------------");
		
		String propFile4 = HagUtil.cfgMenuWebLoc+"\\lists\\versions.txt";
		HagStringList list4 = new HagStringList(propFile4,false,"asafadfs",false);

		list4.replaceStr(prev, str1);
		HagUtil.deleteFile(propFile4,  false);
		list4.writeToFile(propFile4, null, false);
		ans.add("0~change cfgMenuWeb versions.txt");

		String ansS = ans.convertToString("<br>");
		String ans9=HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps,HagUtil.mailList_hag, "need to create folder","\\\\ri-archive\\Saptech-Archive\\RI\\HAT_pack\\skel\\bin\\CFG\\");

		return ansS;

	}


	
}