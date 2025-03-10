package com.hag.hagay;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

public class HagRunTran {
	public static final String 	runTran_before(String cfgMenuUser,String blpUser) {
		HagStringList ans3 = new	HagStringList(); 
			ans3.add("<h2><u>Run tran file:</u></h2>");
			
			
			ans3.add("<table bgColor=\"dddddd\"  cellpadding=\"1\" cellspacing=\"1\" border=\"1\" >");
			ans3.add("<FORM METHOD=POST name=\"Form\" ACTION=\"runTranFile.jsp\">");

			ans3.add("<tr ><td>cfgMenuWeb user</td><td>");
			ans3.add("<input type=\"text\" readonly=\"readonly\" name=\"cfgMenuUser\" id=\"cfgMenuUser\"  value = "+cfgMenuUser+" maxlength=\"30\" size=\"30\">");
			ans3.add("</td></tr>");
			
			

			ans3.add("<tr ><td>before running the tran file you must copy the file to this location</td><td>");
			ans3.add("<a href=\"\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\tranFiles\\\">\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\tranFiles\\</a>");
			ans3.add("</td></tr>");
			
			ans3.add("<tr ><td>tran file name for example aaa.tran</td><td>");
			ans3.add("<input type=\"text\"  name=\"tranName\" id=\"blpUser\"  value = \"\" maxlength=\"100\" size=\"100\">");
			ans3.add("</td></tr>");
			
			ans3.add("<tr ><td>run the tran on this environment</td><td>");
			ans3.add("<select  STYLE=\"font-family : monospace; font-size : 12pt\"  id=\"tgtEnv\" name=\"tgtEnv\" >"+getTargetEnv()+"</select></td></tr>");
			
			ans3.add("<td><INPUT TYPE=SUBMIT value=\"Submit\" style=\"background-color:LightGreen\" onclick=\"this.disabled=true;this.value='Please wait(40sec)...';this.form.submit();\"></FORM></td></tr>");
			
			ans3.add("</table><br>");
				
			return ans3.convertToString("");
			

			
			

		}
	
	
	public  String runTran_after(HttpServletRequest request, HttpServletResponse response) {
		//runTran~RI22QA~RI~0800~WR~D:\hagay\TransactionFile_8.1.txt
		String dbid="RI";
		//runTran( hagRiRel, server, dbid,eMergeDbid,ver, tranFile, null);
		
		
		String cfgMenuUser 	= request.getParameter("cfgMenuUser");
	
		String tgtEnv 	= request.getParameter("tgtEnv");
		String tranName 		= request.getParameter("tranName");
		String bisServer 	= HagUtil.getWord0(tgtEnv, "|", 0, true);
		String batchName 	= HagUtil.getWord0(tgtEnv, "|", 1, true);
		String riVer 	= HagUtil.getWord0(tgtEnv, "|", 2, true);
		String patch 	= HagUtil.getWord0(tgtEnv, "|", 3, true);
		String patch1 	= patch.substring(0,3);
		String patchVer=riVer+patch1;
		String rs 	= "dev";
		String[][] riRel_properties = {
						{"_rsFolder",null},
						{"_bisVer",null}};
		String riVer1=riVer;
		if(!patch1.endsWith("00"))
			 riVer1=riVer+patch1;
		if (tranName.trim().indexOf(" ") > -1) {
			return HagUtil.shortHtml("Blanks not allowed in file name" , "red", "bg");
		}
		String srcTran = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\tranFiles\\"+tranName.trim();
		File srcTranF = new File(srcTran);
		if (!srcTranF.exists() || !srcTranF.isFile()) {
			return HagUtil.shortHtml(srcTran+" file not found" , "red", "bg");
		}
		
		String rc = get_riRel_properties( riVer1,riRel_properties);
	
		if(rc==null)
			return HagUtil.shortHtml("Error: failed to read riRel_properties - please call hagay -2527" , "red", "bg");
		if(!rc.startsWith("0~"))
			return HagUtil.shortHtml(rc , "red", "bg");
			 
			 
			
	
		
		
		String rsFolder = riRel_properties[0][1]+rs+"\\";
		String bisVer = riRel_properties[1][1];
		String tranShortName = tranName;
		int pos = tranName.indexOf(".");
		if(pos>0)
		 tranShortName = tranName.substring(0,pos);
		String ffLog = rsFolder + tranShortName + "_log.txt";
		String ffLst = rsFolder + tranShortName + "_lst.txt";
		String ffRc = rsFolder + tranShortName + "_RC";
		String ffRc0 = rsFolder + tranShortName + "_RC_0";
		String ffRc99 = rsFolder + tranShortName + "_RC_99";
		String ffTran = rsFolder + tranName;
		String copyRC = HagUtil.copyFileViaDos(srcTran, ffTran);
		if(!copyRC.startsWith("0~"))
			return HagUtil.shortHtml("failed to copy "+srcTran+" to "+ffTran , "red", "bg");
		//String ansDelffTran = HagUtil.deleteFile(ffTran,  false);
		//if (ansDelffTran.startsWith("1~")) 
		//	return HagUtil.shortHtml("Error: Failed to delete "+ffTran+" - please call hagay -2527." , "red", "bg");

		//HagUtil.writeArrayListToFile(ffTran, trn, null, false);

		//ArrayList<String> ans5 = runBisTranOnWinServer(
		//		server,
		//		"\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\emerge"+bisVer+"cmd.bat bis " + devBatchName + " "
		//				+ riVer.substring(0, 4) + " arg4 dbblp -u f -d " + devDbid + " -bn " + devBatchName + " -i " + ffTran + " -o " + ffLst + " && exit",
		//		ffLst, ffLog, ffLst, ffRc, "openTask");
		//String ans4 = riWinCompileSingleIOM( bisServer, riVer, "RI",  batchName,  iomName,   ffLog, ffLst,  ffRc, "4530");
		//	String ffTran = rsFolder + "runTran_" + ffa + "_" + date1 + ".tran";
		//	String ffLst = rsFolder + "runTran_" + ffa + "_" + date1 + "_lst.txt";
		//	String ffLog = rsFolder + "runTran_" + ffa + "_" + date1 + "_log.txt";
		//	String ffRc = rsF
		String ans4=runTran(  bisVer,rsFolder,  bisServer,  "RI",  batchName,  riVer,  ffTran,ffLst,ffLog,ffRc) ;
		//if (!ans4.startsWith("0~")) {
		//	return HagUtil.shortHtml("Failed to run tran file command-please call hagay 2527", "red", "bg");
		//}
		
		StringBuilder logsSb = new StringBuilder();
		logsSb.append("<table bgcolor=\"#c0c0c0\" border=\"1\" >");
		File ffRc0F = new File(ffRc0);
		String rc1="Failed";
		if(ffRc0F.exists() && ffRc0F.isFile()) {
			logsSb.append("<tr ><td>link to rc file</td><td><a href=\""+ffRc0+"\">"+ffRc0+"</a></td></tr >");
			rc1="OK";
		}
	
		File ffRc99F = new File(ffRc99);
		if(ffRc99F.exists() && ffRc99F.isFile())
			logsSb.append("<tr bgcolor=\"#ff6666\"><td>link to rc file</td><td><a href=\""+ffRc99+"\">"+ffRc99+"</a></td></tr >");
		
		File ffLstF = new File(ffLst);
		if(ffLstF.exists() && ffLstF.isFile())
			logsSb.append("<tr ><td>link to Lst file</td><td><a href=\""+ffLst+"\">"+ffLst+"</a></td></tr >");
				
		File ffLogF = new File(ffLog);
		if(ffLogF.exists() && ffLogF.isFile())
			logsSb.append("<tr ><td>link to Log file</td><td><a href=\""+ffLog+"\">"+ffLog+"</a></td></tr >");


		logsSb.append("</table>");
		
		String mailRc="<br>Mail sent.";
		String logs = logsSb.toString();
		String subject1 ="runTran "+tranName+"  ran on "+bisServer+" "+batchName+" environment by "+cfgMenuUser+" rc= "+rc1;
		//String note =".";
		HagUtil.writeToRelDiary2("RunTran","WIN","TRAN",riVer,tranName,rc1,".",".",".",cfgMenuUser,bisServer,batchName);
		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+cfgMenuUser,HagUtil.mailList_hag,subject1,logs); 
		if(!ans1.startsWith("0~")) 
			mailRc="Failed to send mail.<br>"+ans1;

		if(ffRc0F.exists() && ffRc0F.isFile())
			return HagUtil.shortHtml(tranName + " ran on "+bisServer+"/"+batchName+" environment."+"<br><BR>"+logs+"<br><br>"+mailRc, "green", "bg");
		
		 else 
			return HagUtil.shortHtml("Failed to run "+tranName+"  on " + bisServer+"/"+batchName+" environment." + "<br><br>"+logs+"<br><br>"+mailRc , "red", "bg");
		
			
	}
	private static final  	String  get_riRel_properties(String ver,String[][] varsVals){

		Properties prop = new Properties();
		try {
		
			prop.load(new FileInputStream("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\list\\riRel_properties\\"+ver+".txt"));
		    for(int i = 0 ; i < varsVals.length;i++) {
		    	String val = prop.getProperty(ver+"_rsFolder").trim();
		    	if (val==null)
		    		return "1~failed to get "+varsVals[i][0]+" from riRel_properties - please call hagay -2527.";
		    	varsVals[i][1]=prop.getProperty(ver+varsVals[i][0]).trim();      
		    }
			return "0~";       
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}

	}
	public 					String  runTran( String bisVer,String rsFolder, String server, String dbid1, String batchName1, String ver4,
			String ffTran,String ffLst,String ffLog,String ffRc) {
		
		HagStringList ans=new HagStringList();
		//String bisVer = hagRiRel.getBisVer();
		String rc = "OK";
		String bg = "00FF00";
		//String ff1 = tranFile.substring(tranFile.lastIndexOf("\\") + 1, tranFile.length());
	
		//String ffa = "";
	//	if (tranFile1.indexOf(".") > -1)
		//	ffa = tranFile1.substring(0, tranFile1.lastIndexOf("."));
	//	else
		//	ffa = tranFile1 + "";
		//String date1 = HagUtil.getDateTime("yyMMdd_HHmmss");
		//String rsFolder = hagRiRel.getRsFolder() + "dev\\";
		//if (rsFolderOver != null) {
		//	rsFolder = rsFolderOver;
		//	date1 = "";
		//}
	//	String ffTran = rsFolder + "runTran_" + ffa + "_" + date1 + ".tran";
	//	String ffLst = rsFolder + "runTran_" + ffa + "_" + date1 + "_lst.txt";
	//	String ffLog = rsFolder + "runTran_" + ffa + "_" + date1 + "_log.txt";
	//	String ffRc = rsFolder + "runTran_" + ffa + "_" + date1 + "_RC";
/*
		// copy tran to rsfolder
		String ans4 = HagUtil.copyFileViaDos(tranFile, ffTran, true, false);
		if (!ans4.startsWith("0~")) {
			hagLinkPanel.addLabel("Failed to copy " + tranFile + " to " + ffTran, "error");
			hagWorkerChange("add", "failed\n", true, false, Color.black, Color.red, 100);
			return;
		}
		hagLinkPanel.addLabel(tranFile + " copied.", "good");
		//String verB = "4520";
		//if(ver4.substring(0, 4).equals("0607"))
		//	verB = "4530";
		*/
		HagStringList ans5 = runBisTranOnWinServer(
				server,
				"\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\emerge"+bisVer+"cmd.bat bis " + batchName1 + " "
						+ ver4.substring(0, 4) + " arg4 dbblp -u f -d " + dbid1 + " -bn " + batchName1 + " -i " + ffTran + " -o " + ffLst + " && exit",
				ffLst, ffLog, ffLst, ffRc, "runTran");
		
	//	HagUtil.writeStringToFile(ffLog, ffLog);
		

		return ans5.convertToString(" ");
	}
	public 					HagStringList runBisTranOnWinServer(String server, String tranLine, String lstUncFile, String logF, String lstF, String rcF,
			String type) {

		HagUtil.deleteFile(logF,  false);
		HagUtil.deleteFile(lstF,  false);
		HagUtil.deleteFile(rcF + "_0",  false);
		HagUtil.deleteFile(rcF + "_99",  false);
		String tranLogS = HagUtil.runCmdRemote(server,tranLine+"\n","Y");
		//ArrayList<String> tranLog = runJavaFunc("javaFunc~runOnServer~" + server + "~" + tranLine);
		HagUtil.writeStringToFile(logF, tranLogS);
		HagUtil.sleep(10000);
		ArrayList<String> tranLst = HagUtil.loadFileIntoArrayList(lstUncFile, false, "zzzzz", false);
		HagStringList rcList = new HagStringList();
		if (type.equals("extract")) {
			if (HagUtil.isInArray("Return Code 00:", tranLst, false, true)) {
				rcList.add("0~extract ");
				HagUtil.writeArrayListToFile(rcF + "_0", rcList, null, true);
				HagUtil.writeArrayListToFile(rcF + "_0", rcList, null, true);
			} else {
				rcList.add("1~extract ");
				HagUtil.writeArrayListToFile(rcF + "_99", rcList, null, true);
			}
			HagUtil.appendArrayList2ArrayList(rcList, tranLst);
		} else if (type.equals("createXml")) {
			rcList.add("0~createXml ");
			HagUtil.appendArrayList2ArrayList(rcList, tranLst);
			HagUtil.writeArrayListToFile(rcF + "_0", rcList, null, true);
			HagUtil.writeArrayListToFile(rcF + "_0", rcList, null, true);
		} else if (type.equals("loadXml")) {
			rcList.add("0~loadXml ");
			HagUtil.appendArrayList2ArrayList(rcList, tranLst);
			HagUtil.writeArrayListToFile(rcF + "_0", rcList, null, true);
			HagUtil.writeArrayListToFile(rcF + "_0", rcList, null, true);
		} else {
			HagUtil.sleep(6000);
			String ansCheck = checkSapiens(tranLst);
			if (ansCheck.startsWith("0~")) {
				rcList.add("0~tran ");
				HagUtil.writeArrayListToFile(rcF + "_0", rcList, null, true);
			} else {
				rcList.add("1~tran ");
				rcList.add("checkSapiensRC="+ansCheck);
				rcList.add("lstUncFile="+lstUncFile);
				rcList.add("type="+type);
				rcList.add("type="+type);
				HagUtil.writeArrayListToFile(rcF + "_99", rcList, null, true);
			}
			rcList.add(ansCheck);
		}
		// ans0.append("*tran check:").append("\n");
		// ans0.append(ansCheck).append("\n");

		// ans0.append("\n").append("*tranLog:").append("\n");
		// HagUtil.appendArrayList2StringBuilder(ans0, tranLog,"\n");

		// ans0.append("\n").append("*tranLst:").append("\n");
		// HagUtil.appendArrayList2StringBuilder(ans0, lst,"\n");

		return rcList;
	}
	public 	static final 	String checkSapiens(ArrayList<String> lst){
		String ans ="1~failed to check sapiens lst file.";
		String input ="";
		String good ="";
		for(int i = 0 ; i < lst.size();i++){
			String temp = lst.get(i);
			if(temp.indexOf("Input     :")>-1){
				input = ""+temp;
			}
			if(temp.indexOf("Good      :")>-1){
				good = ""+temp;
			}
			if(input.length()>0 && good.length()>0)
				break;
		}
		if(input.length()>0 && good.length()>0){
			input=input.trim();
			good=good.trim();
			String inputVal = input.substring(input.indexOf(":"),input.length());
			String goodVal 	= good.substring(good.indexOf(":"),good.length());
			inputVal 	= inputVal.trim();
			goodVal 	= goodVal.trim();
			if(inputVal.equals(goodVal))
				ans="0~sapiens lst check\n"+input+"\n"+good;	
			else
				ans="1~sapiens lst check\n"+input+"\n"+good;	
		}else {
			ans =ans+"lstLen="+lst.size()+",inputLen="+input.length()+",goodLength"+good.length();
		}
		
		return ans;
	}
	private static String 	getTargetEnv() {
		   HagSQL hagSQL = new HagSQL();
	   	   String stm = "SELECT bis_server,batchName,[version],patch from "+HagParam.getConfg1DB()+".[dbo].[ri_environments_new] where status='A' order by bis_server, db";
			
		//   String stm = "SELECT Rowx.[bis_server], Rowx.[db], Customer.[Customer_code], Rowx.[Owner], Rowx.[lastInst], Rowx.[Note] "
		 //       +" From  "+HagParam.getConfg1DB()+".[dbo].[ri_environments_new]  Rowx "
		 //       +" INNER JOIN "+HagParam.getConfg1DB()+".[dbo].[RIcustomer]  Customer "
		 //       +" ON Rowx.Party = Customer.PARTY_ID "
		  //      +" Where len(Rowx.bis_server) > 3 "
		    //    +" order by bis_server, db";
		    	HagStringList results = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,4,"|",null,null);
		    	
	//	    	   String stm1 = "SELECT bis_server,batchName,[version] from "+HagParam.getConfg1DB()+".[dbo].[ri_environments_ext] where status='A' order by bis_server, db";
		//   		  	HagStringList results1 = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,3,"|",null,null);
		   HagStringList ans1 = new HagStringList();
		 
			for(int j=0;j<results.size();j++){
				String temp = results.get(j);
				String AppServer=HagUtil.getWord0(temp, "|", 0, true);
				String dbid=HagUtil.getWord0(temp, "|", 1, true);
				String ver=HagUtil.getWord0(temp, "|", 2, true);
				String patch=HagUtil.getWord0(temp, "|", 3, true);
			
			//	String lastInst=HagUtil.getWord0(temp, "|", 4, true);
			//	String note=HagUtil.getWord0(temp, "|", 5, true);
			//	String cust=HagUtil.getWord0(temp, "|", 2, true);
			//	String owner=HagUtil.getWord0(temp, "|",3, true);
			//	String AppServer1 = pad(AppServer,13);
			//	String dbid1 = pad(dbid,8);
			//	String cust1 = pad(cust,5);
			//	String owner1 = pad(owner,20);
			//	String lastInst1 = pad(lastInst,40);
			//	AppServer = pad(AppServer,5);
			//	if( note==null) {
			//		note=".";
			//	}
			//	String temp3 = HagUtil.replaceStr(temp, "|", "   |   ");
				String temp5 = AppServer+"|"+ dbid  ;
				String temp4 =  AppServer+"|"+ dbid+"|"+ ver +"|"+ patch   ;
				String temp1="<option    value=\""+temp4+"\">"+temp5+"</option>";
				
				ans1.add(temp1);
			}
			
		//	for(int k=0;k<results1.size();k++){
		//		String temp = results1.get(k);
			//	String temp = results.get(j);
		//		String AppServer=HagUtil.getWord0(temp, "|", 0, true);
		//		String dbid=HagUtil.getWord0(temp, "|", 1, true);
		//		String ver=HagUtil.getWord0(temp, "|", 2, true);
				//String temp3 = HagUtil.replaceStr(temp, "|", "   |   ");
			//	String temp5 = AppServer+"|"+ dbid  ;
			//	String temp4 =  AppServer+"|"+ dbid+"|"+ ver   ;
			//	String temp1="<option    value=\""+temp4+"\">"+temp5+"</option>";
			
			
			//	ans1.add(temp1);
		//	}
			/////
			/////
			//String bisServer 	= HagUtil.getWord0(tgtEnv, "|", 0, true);
			//String batchName 	= HagUtil.getWord0(tgtEnv, "|", 1, true);
			//String riVer 	= HagUtil.getWord0(tgtEnv, "|", 2, true);
		//	String rs 	= HagUtil.getWord0(tgtEnv, "|", 3, true);
			String ans2 = ans1.convertToString("");
			return ans2;
	}
	private static 			String 	pad(String org,int len) {
		if(org.length()>=len)
			return org;
		String tgt=""+org;
		for(int i=org.length();i<len;i++) {
			tgt=tgt+"&nbsp;";
		}
		return tgt;
		
	}
}
