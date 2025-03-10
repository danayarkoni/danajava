package com.hag.hagay;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HagCreateXmlFromTask {

	public static final String 	createXmlFromTask_before(String cfgMenuUser,String blpUser) {
		HagStringList ans3 = new	HagStringList(); 
			ans3.add("<h2><u>Create XML from task and dates</u></h2>");
			
			
			ans3.add("<table bgColor=\"dddddd\"  cellpadding=\"1\" cellspacing=\"1\" border=\"1\" >");
			ans3.add("<FORM METHOD=POST name=\"Form\" ACTION=\"createXmlFromTaskAndDates.jsp\">");

			ans3.add("<tr ><td>cfgMenuWeb user</td><td>");
			ans3.add("<input type=\"text\" readonly=\"readonly\" name=\"cfgMenuUser\" id=\"cfgMenuUser\"  value = "+cfgMenuUser+" maxlength=\"30\" size=\"30\">");
			ans3.add("</td></tr>");
			
			

				
			
			ans3.add("<tr ><td>create the XML from this environment</td><td>");
			ans3.add("<select  STYLE=\"font-family : monospace; font-size : 12pt\"  id=\"tgtEnv\" name=\"tgtEnv\" >"+getTargetEnv()+"</select></td></tr>");
			
			
			ans3.add("<tr ><td>task number</td><td>");
			ans3.add("<input type=\"text\"  name=\"taskNumber\" id=\"taskNumber\"  value = \"\" maxlength=\"10\" size=\"10\">");
			ans3.add("</td></tr>");
			
			
			ans3.add("<tr ><td>from date (format ddMMyyyy)</td><td>");
			ans3.add("<input type=\"text\"  name=\"fromDate\" id=\"fromDate\"  value = \"\" maxlength=\"10\" size=\"10\">");
			ans3.add("</td></tr>");
			
			ans3.add("<tr ><td>to date (format ddMMyyyy)</td><td>");
			ans3.add("<input type=\"text\"  name=\"toDate\" id=\"toDate\"  value = \"\" maxlength=\"10\" size=\"10\">");
			ans3.add("</td></tr>");
			
			ans3.add("<td><INPUT TYPE=SUBMIT value=\"Submit\" style=\"background-color:LightGreen\" onclick=\"this.disabled=true;this.value='Please wait(200sec)...';this.form.submit();\"></FORM></td></tr>");
			
			ans3.add("</table><br>");
				
			return ans3.convertToString("");
			

			
			

		}
	
	
	public  String createXmlFromTask_after(HttpServletRequest request, HttpServletResponse response) {
		//runTran~RI22QA~RI~0800~WR~D:\hagay\TransactionFile_8.1.txt
		String dbid="RI";
		//runTran( hagRiRel, server, dbid,eMergeDbid,ver, tranFile, null);
		
		
		String cfgMenuUser 	= request.getParameter("cfgMenuUser");
	
		String tgtEnv 	= request.getParameter("tgtEnv");
		String taskNumber 		= request.getParameter("taskNumber");
		String fromDate 		= request.getParameter("fromDate");
		String toDate 		= request.getParameter("toDate");
		String bisServer 	= HagUtil.getWord0(tgtEnv, "|", 0, true);
		String batchName 	= HagUtil.getWord0(tgtEnv, "|", 1, true);
		String riVer 	= HagUtil.getWord0(tgtEnv, "|", 2, true);
		
		String rs 	= "dev";
		String[][] riRel_properties = {
						{"_rsFolder",null},
						{"_bisVer",null}};

		
		String rc = get_riRel_properties( riVer,riRel_properties);
	
		if(rc==null)
			return HagUtil.shortHtml("Error: failed to read riRel_properties - please call hagay -2527" , "red", "bg");
		if(!rc.startsWith("0~"))
			return HagUtil.shortHtml(rc , "red", "bg");
			 
			 
	
		
		
		String rsFolder = riRel_properties[0][1]+rs+"\\";
		String bisVer = riRel_properties[1][1];
		
		
		String outBase=rsFolder+"bn"+taskNumber;
		//dbjrndb -d RI -bn R8 -o pb -tsn xxxxx       -fd 311220015 -td 311220015           -out \\HYUNDAI.SAPIENS.COM\HYUN05ri\rs\v07_02M01U01\win\int\bnxxxxx      _extract.tran -lst \\HYUNDAI.SAPIENS.COM\HYUN05ri\rs\v07_02M01U01\win\int\bnxxxxx      _extract.lst1
		String cmd11 = "dbjrndb -d RI -bn "+batchName+" -o pb -tsn "+taskNumber+" -fd "+fromDate+" -td "+toDate+" -out "+outBase+"_extract.tran -lst "+outBase+"_extract.lst1";
		String rc11 = HagUtil.runCmdRemote(bisServer,cmd11+"\n","Y");
		
		//dbblp -u q\s -d RI -bn R8 -i \\HYUNDAI.SAPIENS.COM\HYUN05ri\rs\v07_02M01U01\win\int\bnxxxxx      _extract.tran -t \\HYUNDAI.SAPIENS.COM\HYUN05ri\rs\v07_02M01U01\win\int\bnxxxxx      _extract.xml -o \\HYUNDAI.SAPIENS.COM\HYUN05ri\rs\v07_02M01U01\win\int\bnxxxxx      _extract.lst2
		String cmd12 = "dbblp -u q\\s -d RI -bn "+batchName+" -i "+outBase+"_extract.tran -t "+outBase+"_extract.xml -o "+outBase+"_extract.lst2";
		String rc12 = HagUtil.runCmdRemote(bisServer,cmd12+"\n","Y");
		
		
		String copyRc = HagUtil.copyFileViaDos(outBase+"_extract.xml", outBase+"_extract_xml.txt");
		if(!copyRc.startsWith("0~"))
			return HagUtil.shortHtml(copyRc , "red", "bg");
			 
		
		StringBuilder logsSb = new StringBuilder();
		logsSb.append("<h3>Create XML from Task and dates results:</h3><br>");
		
		logsSb.append("<table bgcolor=\"#c0c0c0\" border=\"1\" >");

	//	File tran1 = new File(outBase+"_extract.tran");
		//if(tran1.exists() && tran1.isFile())
		//	logsSb.append("<tr bgcolor=\"#ff6666\"><td>link to dbjrndb tran file</td><td><a href=\""+outBase+"_extract.tran"+"\">"+outBase+"_extract.tran"+"</a></td></tr >");
		
		File lst1 = new File(outBase+"_extract.lst1");
		if(lst1.exists() && lst1.isFile())
			logsSb.append("<tr bgcolor=\"#dddddd\"><td>link to rc dbjrndb lst file</td><td><a href=\""+outBase+"_extract.lst1"+"\">"+outBase+"_extract.lst1"+"</a></td></tr >");
		
		File lst2 = new File(outBase+"_extract.lst2");
		if(lst1.exists() && lst1.isFile())
			logsSb.append("<tr bgcolor=\"#dddddd\"><td>link to create XML lst file</td><td><a href=\""+outBase+"_extract.lst2"+"\">"+outBase+"_extract.lst2"+"</a></td></tr >");
		
		File xml1 = new File(outBase+"_extract_xml.txt");
		if(xml1.exists() && xml1.isFile())
			logsSb.append("<tr bgcolor=\"#dddddd\"><td>link to XML  file</td><td><a href=\""+outBase+"_extract_xml.txt"+"\">"+outBase+"_extract_xml.txt"+"</a></td></tr >");
		
		File folder = new File(rsFolder);
		if(folder.exists() && folder.isDirectory())
			logsSb.append("<tr bgcolor=\"#dddddd\"><td>link to folder</td><td><a href=\""+rsFolder+"\">"+rsFolder+"</a></td></tr >");
	
		logsSb.append("</table>");
		
		String mailRc="<br>Mail sent.";
		String logs = logsSb.toString();
		String subject1 ="create XML from task "+taskNumber+" dates "+fromDate+" to "+toDate+"  ran on "+bisServer+" "+batchName+" environment by "+cfgMenuUser;
		//String note =".";
		HagUtil.writeToRelDiary2("CreateXML","WIN","XML",riVer,taskNumber,".",".",".",".",cfgMenuUser,bisServer,batchName);
		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+cfgMenuUser,HagUtil.mailList_hag,subject1,logs); 
		if(!ans1.startsWith("0~")) 
			mailRc="Failed to send mail.<br>"+ans1;

	//	if(ffRc0F.exists() && ffRc0F.isFile())
		//	return HagUtil.shortHtml(tranName + " ran on "+bisServer+"/"+batchName+" environment."+"<br><BR>"+logs+"<br><br>"+mailRc, "green", "bg");
		
		// else 
		//	return HagUtil.shortHtml("Failed to run "+tranName+"  on " + bisServer+"/"+batchName+" environment." + "<br><BR> eMerge problem - please call hagay -2527."+"<br><br>"+logs+"<br><br>"+mailRc , "red", "bg");
		
			return logsSb.toString();
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
	
		String rc = "OK";
		String bg = "DDDDDD";
	
	

		HagStringList ans5 = runBisTranOnWinServer(
				server,
				"\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\emerge"+bisVer+"cmd.bat bis " + batchName1 + " "
						+ ver4.substring(0, 4) + " arg4 dbblp -u f -d " + dbid1 + " -bn " + batchName1 + " -i " + ffTran + " -o " + ffLst + " && exit",
				ffLst, ffLog, ffLst, ffRc, "runTran");
		

		

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
		HagStringList list = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\createXmlFromTask.txt",false,"xxssss",false);
		
		   HagStringList ans1 = new HagStringList();
			for(int j=0;j<list.size();j++){
				String temp = list.get(j);
			//	String AppServer=HagUtil.getWord0(temp, "|", 0, true);
			//	String dbid=HagUtil.getWord0(temp, "|", 1, true);
			//	String ver=HagUtil.getWord0(temp, "|", 2, true);
				String temp1="<option    value=\""+temp+"\">"+temp+"</option>";
				
				ans1.add(temp1);
			}
			

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
