package com.hag.hagay;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HagIOM {
	public static final String 	compileIOM_before(String cfgMenuUser,String blpUser) {
		HagStringList ans3 = new	HagStringList(); 
		HagStringList compileIomList= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\compileIom.txt",true,"*",false);
			ans3.add("<h2><u>Compile IOM:</u></h2>");
			
			
			ans3.add("<table bgColor=\"888888\"  cellpadding=\"1\" cellspacing=\"1\" border=\"1\" >");
			ans3.add("<FORM METHOD=POST name=\"Form\" ACTION=\"compileIOM.jsp\">");

			ans3.add("<tr ><td>cfgMenuWeb user</td><td>");
			ans3.add("<input type=\"text\" readonly=\"readonly\" name=\"cfgMenuUser\" id=\"cfgMenuUser\"  value = "+cfgMenuUser+" maxlength=\"30\" size=\"30\">");
			ans3.add("</td></tr>");
			
			ans3.add("<tr ><td>IOM name (for example RI00053)</td><td>");
			ans3.add("<input type=\"text\"  name=\"iomName\" id=\"blpUser\"  value = \"\" maxlength=\"10\" size=\"10\">");
			ans3.add("</td></tr>");
			
			ans3.add("<tr ><td>Env</td><td>");
			ans3.add("<select  name=\"Env\" id=\"Env\"  >");
			for(int i = 0 ;i < compileIomList.size();i++) {
				String temp =compileIomList.get(i);
				String w0=HagUtil.getWord0(temp, "|", 0, true);
				String w1=HagUtil.getWord0(temp, "|", 1, true);
			
				ans3.add("<option  value=\""+temp+"\"  > "+w0+"/"+w1+"</option>");
			}
			ans3.add("</select></td></tr>");
			
			
			 
				
		//	ans3.add("<td><INPUT TYPE=SUBMIT value=\"Submit\" style=\"background-color:LightGreen\" ></FORM></td></tr>");
			ans3.add("<td><INPUT TYPE=SUBMIT value=\"Submit\" style=\"background-color:LightGreen\" onclick=\"this.disabled=true;this.value='Please wait(40sec)...';this.form.submit();\"></FORM></td></tr>");
			
			ans3.add("</table><br>");
				
			return ans3.convertToString("");
			

			
			

		}
	
 	private static final  String  get_riRel_properties(String ver,String[][] varsVals){

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
	public String compileIOM_after(HttpServletRequest request, HttpServletResponse response) {
		String cfgMenuUser 	= request.getParameter("cfgMenuUser");
	
		String iomName 	= request.getParameter("iomName");
		String Env 		= request.getParameter("Env");
		String bisServer 	= HagUtil.getWord0(Env, "|", 0, true);
		String batchName 	= HagUtil.getWord0(Env, "|", 1, true);
		String riVer 	= HagUtil.getWord0(Env, "|", 2, true);
		String rs 	= HagUtil.getWord0(Env, "|", 3, true);
		String[][] riRel_properties = {
				{"_tasksTable",null},
				{"_rsFolder",null},
				{"_bisServerDev",null},
				{"_bisVer",null},
				{"_devDbid",null},
				{"_devBatchName",null}
				
		};
		String rc = get_riRel_properties( riVer,riRel_properties);
		if(rc==null)
			return HagUtil.shortHtml("Error: failed to read riRel_properties - Env: " + Env + ", riVer: " + riVer + " - please call hagay -2527", "red", "bg");
		if(!rc.startsWith("0~"))
			return HagUtil.shortHtml(rc , "red", "bg");
			 
			 
			
	
		
		
		String rsFolder = riRel_properties[1][1]+rs+"\\";
		
	
		String ffLog = rsFolder + iomName + "_log.txt";
		String ffLst = rsFolder + iomName + "_lst.txt";
		String ffRc = rsFolder + iomName + "_RC";
		String ffRc0 = rsFolder + iomName + "_RC_0.txt";
		String ffRc99 = rsFolder + iomName + "_RC_99.txt";
		
		//String ansDelffTran = HagUtil.deleteFile(ffTran,  false);
		//if (ansDelffTran.startsWith("1~")) 
		//	return HagUtil.shortHtml("Error: Failed to delete "+ffTran+" - please call hagay -2527." , "red", "bg");

		//HagUtil.writeArrayListToFile(ffTran, trn, null, false);

		//ArrayList<String> ans5 = runBisTranOnWinServer(
		//		server,
		//		"\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\emerge"+bisVer+"cmd.bat bis " + devBatchName + " "
		//				+ riVer.substring(0, 4) + " arg4 dbblp -u f -d " + devDbid + " -bn " + devBatchName + " -i " + ffTran + " -o " + ffLst + " && exit",
		//		ffLst, ffLog, ffLst, ffRc, "openTask");
		String ans4 = riWinCompileSingleIOM( bisServer, riVer, "RI",  batchName,  iomName,   ffLog, ffLst,  ffRc, "4530");
	
		
		if (!ans4.startsWith("0~")) {
			return HagUtil.shortHtml("Failed to run IOM compilation command-please call hagay 2527", "red", "bg");
		}
		
		StringBuilder logsSb = new StringBuilder();
		logsSb.append("<table bgcolor=\"#c0c0c0\" border=\"1\" >");
		File ffRc0F = new File(ffRc0);
		String rc1="Failed";
		HagUtil.sleep(5000);
		if(ffRc0F.exists() && ffRc0F.isFile()) {
			logsSb.append("<tr ><td>link to rc file</td><td><a href=\""+ffRc0+"\">"+ffRc0+"</a></td></tr >");
			rc1="OK";
		}else {
			
			File ffRc99F = new File(ffRc99);
			if(ffRc99F.exists() && ffRc99F.isFile())
				logsSb.append("<tr bgcolor=\"#ff6666\"><td>link to rc file</td><td><a href=\""+ffRc99+"\">"+ffRc99+"</a></td></tr >");
		}
		File ffLstF = new File(ffLst);
		if(ffLstF.exists() && ffLstF.isFile())
			logsSb.append("<tr ><td>link to Lst file</td><td><a href=\""+ffLst+"\">"+ffLst+"</a></td></tr >");
				
		File ffLogF = new File(ffLog);
		if(ffLogF.exists() && ffLogF.isFile())
			logsSb.append("<tr ><td>link to Log file</td><td><a href=\""+ffLog+"\">"+ffLog+"</a></td></tr >");


		logsSb.append("</table>");
		
		String mailRc="<br>Mail sent.";
		String logs = logsSb.toString();
		String subject1 ="compile "+iomName+" IOM ran on "+bisServer+" "+batchName+" environment by "+cfgMenuUser+" rc= "+rc1;
		//String note =".";
		HagUtil.writeToRelDiary2("Compile","WIN","IOM",riVer,iomName,rc1,".",".",".",cfgMenuUser,bisServer,batchName);
		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+cfgMenuUser,HagUtil.mailList_hag,subject1,logs); 
		if(!ans1.startsWith("0~")) 
			mailRc="Failed to send mail.<br>"+ans1;

		if(ffRc0F.exists() && ffRc0F.isFile())
			return HagUtil.shortHtml(iomName + " IOM created on "+bisServer+"/"+batchName+" environment."+"<br><BR>"+logs+"<br><br>"+mailRc, "green", "bg");
		
		 else 
			return HagUtil.shortHtml("Failed to create "+iomName+" IOM on " + bisServer+"/"+batchName+" environment." + "<br><BR> eMerge problem - please call hagay -2527."+"<br><br>"+logs+"<br><br>"+mailRc , "red", "bg");
		
	}
	public String riWinCompileSingleIOM(String bisServer, String ver, String eMergeDbid, String batchName, String iomName, 
			String ffLog,String ffLst, String ffRc,String bisVer) {

		boolean rc = true;
		HagUtil.deleteFile(ffLog,  false);
		HagUtil.deleteFile(ffLst,  false);
		HagUtil.deleteFile(ffRc + "_0.txt",  false);
		HagUtil.deleteFile(ffRc + "_99.txt",  false);
	
		
		String cmd = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\emerge"+bisVer+"cmd.bat "+
		" bisLst " +batchName + 
		" "	+ ver.substring(0, 4) + " " + ffLst + 
		" dbiomodl -d RI" + 
		" -bn " + batchName +
		" -i " + iomName + 
		" -ms e -m 999 -n 999 && exit";
		
			
		String iomLog = HagUtil.runCmdRemote(bisServer,cmd+"\n","Y");
		//return iomLog;
		
			
		HagUtil.writeStringToFile(ffLog, iomLog);
		
		ArrayList<String> iomLst = HagUtil.loadFileIntoArrayList(ffLst, false, "*****", false);
		ArrayList<String> rcList = new ArrayList<String>();
		if (	(	HagUtil.isInArray("DBIOMODL ended with return code 0", iomLst, false, true) ||
					HagUtil.isInArray("DBIOMODL ended with return code 4", iomLst, false, true) 	)	&&
				chkIomLog(iomLog) ) {
			rcList.add( "IOM compiled");
			HagUtil.writeArrayListToFile(ffRc + "_0.txt", rcList, null, false);
		} else {
			//String msg = iomName + "_lst.txt";
			//if (HagUtil.isInArray("DBIOMODL ended with return code 0", iomLst, false, true) || HagUtil.isInArray("DBIOMODL ended with return code 4", iomLst, false, true) )
			//	msg = iomName + "_log.txt";
			//hagLinkPanel.addLabel(iomName + " iom not created. (" + msg + ")", "error");
			HagUtil.writeArrayListToFile(ffRc + "_99.txt", rcList, null, false);
			//rc = false;
		}

		// hagLinkPanel.addLabel("Links to IOM "+iomName+" log files:","title");
		// hagLinkPanel.addLinkLabel(iomName+"_log.txt", ffLog,"link");
		// hagLinkPanel.addLinkLabel(iomName+"_lst.txt", ffLst,"link");
		// hagLinkPanel.addLabelSpace();
		//String[][] titleLink = { { iomName + "_log.txt", ffLog }, { iomName + "_lst.txt", ffLst }, };

		//hagLinkPanel.addLinkGroup(titleLink, "Links to IOM " + iomName + " log files:", "link");

		//return rc;
		return iomLog;
	}
	
	public boolean chkIomLog(String iomLog) {
		if (iomLog.indexOf("fatal error")>-1)
			return false;
		if (iomLog.indexOf("The system cannot find the path specified")>-1)
			return false;
		if (iomLog.indexOf("not recognized")>-1)
			return false;
	
		return true;
	}

	
}
