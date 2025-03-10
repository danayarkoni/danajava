package com.hag.hagay;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class  HagTasks {

	
	public static final String 	before(String act,String user,String pass) {
		String blpUser =getBlpUser(user);
		if(blpUser==null)
			return HagUtil.shortHtml("Error: "+user+" user is not in the blp dev users list - please call hagay -2527" , "red", "bg");
		if(act.startsWith("openNewTask~"))
			return openTask_before(user,blpUser);
		if(act.startsWith("closeTask~"))
			return closeTask_before(user,blpUser);
		return HagUtil.shortHtml("Error: "+act+" option not valid - please call hagay -2527" , "red", "bg");

	}
	public static final String 	getBlpUser(String user) {
		HagStringList tasksUsersList= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\tasks_users.txt",true,"*",false);
		for (int i = 0 ; i < tasksUsersList.size();i++) {
			String temp = tasksUsersList.get(i);
			String w0 = HagUtil.getWord0(temp, "|", 0, true);
			String w1 = HagUtil.getWord0(temp, "|", 1, true);
			if(w0.equals(user))
				return w1;
		}
		return null;
	}
	public static final String 	openTask_before(String cfgMenuUser,String blpUser) {
	HagStringList ans3 = new	HagStringList(); 
    	
		ans3.add("<h2><u>Open new task:</u></h2>");
		
		
		ans3.add("<table bgColor=\"888888\"  cellpadding=\"1\" cellspacing=\"1\" border=\"1\" >");
		ans3.add("<FORM METHOD=POST name=\"Form\" ACTION=\"openTask.jsp\">");

		ans3.add("<tr ><td>cfgMenuWeb user</td><td>");
		ans3.add("<input type=\"text\" readonly=\"readonly\" name=\"cfgMenuUser\" id=\"cfgMenuUser\"  value = "+cfgMenuUser+" maxlength=\"30\" size=\"30\">");
		ans3.add("</td></tr>");
		
		ans3.add("<tr ><td>blp user</td><td>");
		ans3.add("<input type=\"text\" readonly=\"readonly\" name=\"blpUser\" id=\"blpUser\"  value = "+blpUser+" maxlength=\"30\" size=\"30\">");
		ans3.add("</td></tr>");
		
		ans3.add("<tr ><td>RI version</td><td>");
		ans3.add("<select  name=\"riVer\" id=\"riVer\"  value = \"0801\" >");
		ans3.add(getRiVers("0801"));
		ans3.add("</select></td></tr>");
		 
			
	//	ans3.add("<td><INPUT TYPE=SUBMIT value=\"Submit\" style=\"background-color:LightGreen\" ></FORM></td></tr>");
		ans3.add("<td><INPUT TYPE=SUBMIT value=\"Submit\" style=\"background-color:LightGreen\" onclick=\"this.disabled=true;this.value='Please wait(40sec)...';this.form.submit();\"></FORM></td></tr>");
		
		ans3.add("</table><br>");
			
		return ans3.convertToString("");
		

		
		

	}
	public static final String 	closeTask_before(String cfgMenuUser,String blpUser) {
		HagStringList ans3 = new	HagStringList(); 
    	
		ans3.add("<h2><u>Close task:</u></h2>");
		
		
		ans3.add("<table bgColor=\"888888\"  cellpadding=\"1\" cellspacing=\"1\" border=\"1\" >");
		ans3.add("<FORM METHOD=POST name=\"Form\" ACTION=\"closeTask.jsp\">");

		ans3.add("<tr ><td>cfgMenuWeb user</td><td>");
		ans3.add("<input type=\"text\" readonly=\"readonly\" name=\"cfgMenuUser\" id=\"cfgMenuUser\"  value = "+cfgMenuUser+" maxlength=\"30\" size=\"30\">");
		ans3.add("</td></tr>");
		
		ans3.add("<tr ><td>blp user</td><td>");
		ans3.add("<input type=\"text\" readonly=\"readonly\" name=\"blpUser\" id=\"blpUser\"  value = "+blpUser+" maxlength=\"30\" size=\"30\">");
		ans3.add("</td></tr>");
		
		ans3.add("<tr ><td>RI version</td><td>");
		ans3.add("<select  name=\"riVer\" id=\"riVer\"  value = \"0801\" >");
		ans3.add(getRiVers("0801"));
		ans3.add("</select></td></tr>");
		 
		ans3.add("<tr ><td>Task nubmer</td><td>");
		ans3.add("<input type=\"text\"  name=\"taskNo\" id=\"taskNo\"  value = \"\" maxlength=\"30\" size=\"30\">");
		ans3.add("</td></tr>");
		
			
		ans3.add("<td><INPUT TYPE=SUBMIT value=\"Submit\" style=\"background-color:LightGreen\" onclick=\"this.disabled=true;this.value='Please wait(60sec)...';this.form.submit();\"></FORM></td></tr>");
		ans3.add("</table><br>");
		return ans3.convertToString("");
	}
	public static final String 	getRiVers(String def) {
		HagStringList list= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riVersionsTasks.txt",true,"*",false);
		StringBuilder strB = new StringBuilder();
		for (int i = 0 ; i < list.size();i++) {
			String temp = list.get(i);
			if(temp.equals(def))
				strB.append("<option  value=\"").append(temp).append("\"  selected>").append(temp).append("</option>");
			else
				strB.append("<option  value=\"").append(temp).append("\">").append(temp).append("</option>");
		}
		return strB.toString();
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
	public String winTaskCheckUserOpen(String tasksTable, String user) {
	HagSQL hagSQL = new HagSQL();
	String stm = "SELECT TASKNO FROM " + tasksTable + " WHERE USERNAME='" + user + "' and STATUS='OPN'";
	ArrayList<String> ans = hagSQL.select1col("SQL", "confg1", "cfg", "cfg09c", HagParam.getConfg1DB(), stm);
	if (ans == null)
		return "2~NULL";
	else if (ans.size() == 0)
		return "0~no open tasks for " + user;
	else if (ans.get(0).equals("Could not connect to the database"))
		return "3~" + ans.get(0);
	return "1~" + ans.get(0);
}
	public String winTaskGetLast(String tasksTable, String stm) {
	HagSQL hagSQL = new HagSQL();
	ArrayList<String> ans = hagSQL.select1col("SQL", "confg1", "cfg", "cfg09c", HagParam.getConfg1DB(), stm);
	if (ans == null)
		return "1~NULL";
	else if (ans.size() == 0)
		return "1~size=0";
	else if (ans.get(0).equals("Could not connect to the database"))
		return "1~" + ans.get(0);
	else
		return "0~taskNo=" + ans.get(0);
}
	public String winTaskIncLast( int lastTask, String stm) {
	HagSQL hagSQL = new HagSQL();
	String ans = hagSQL.update("SQL", "confg1", "cfg", "cfg09c", HagParam.getConfg1DB(), stm);
	return ans;
}
	public String winTaskInsertLine(String tasksTable,String ver4, String user, String task) {
	HagSQL hagSQL = new HagSQL();
	String openDate = HagUtil.getDateTime("yyyy-MM-dd");
	String openTime = HagUtil.getDateTime("HH:mm:ss");
	String prm = "'RI'," + "'" + ver4 + "'," + "'" + task + "'," + "'" + user + "'," + "'" + openDate + "'," + "'" + openTime + "'," + "'-',"
			+ "'-'," + "'OPN'";
    String tasksTable1=tasksTable.substring(tasksTable.indexOf(".")+1,tasksTable.length()); 
	String ans = hagSQL.cfgInsert(tasksTable1, prm);
	return ans;
	}

  	public String openTask_after(HttpServletRequest request, HttpServletResponse response) {
		String cfgMenuUser 	= request.getParameter("cfgMenuUser");
		String blpUser 	= request.getParameter("blpUser");
		String riVer 		= request.getParameter("riVer");
		
	///	hagWorkerChange("add", "starting winTaskOpen\n", false, false, Color.black, null, 0);
	///	hagLinkPanel.addLabel("open new task:", "title");

		// checkUserOpen
	///	hagLinkPanel.addLabel("first step(cm tables)", "title");
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
			return HagUtil.shortHtml("Error: failed to read riRel_properties - please call hagay -2527" , "red", "bg");
		if(!rc.startsWith("0~"))
			return HagUtil.shortHtml(rc , "red", "bg");
			 
			 
			 
		String taskTable = riRel_properties[0][1];
		String rsFolder = riRel_properties[1][1]+"dev\\";
		String server = riRel_properties[2][1];
		String bisVer= riRel_properties[3][1];
		String devDbid= riRel_properties[4][1];
		String devBatchName= riRel_properties[5][1];
		
		String ans1 = winTaskCheckUserOpen(taskTable, blpUser);

		if (ans1.startsWith("1~")) 
			return HagUtil.shortHtml("Error: One task per user – please close " + ans1.substring(2, ans1.length()).trim() +" task first." , "red", "bg");

		if (ans1.startsWith("2~") || ans1.startsWith("3~"))
			return HagUtil.shortHtml("Error: " + ans1+"- please call hagay -2527." , "red", "bg");
		
		String stm = "SELECT TASKNO FROM dbo.tasks_ind WHERE project='RI'";
		
		String ans2 = winTaskGetLast(taskTable, stm);
		
		if (ans2.startsWith("1~"))
			return HagUtil.shortHtml("Error: Failed to get next task number - please call hagay -2527." , "red", "bg");
		String lastTaskS = ans2.substring(9, ans2.length()).trim();
		String stm3 = "SELECT TASKLIMIT FROM  dbo.tasks_ind  WHERE project='RI'";

		String ans31 = winTaskGetLast(taskTable, stm3);
		if (ans31.startsWith("1~")) 
			return HagUtil.shortHtml("Error: Failed to get limit task number - please call hagay -2527." , "red", "bg");

		String limitTaskS = ans31.substring(9, ans31.length()).trim();

		int limitTaskI = HagUtil.s2i(limitTaskS);
		int lastTaskI = HagUtil.s2i(lastTaskS);
		if((limitTaskI-lastTaskI)<4)
			return HagUtil.shortHtml("Error: tasks range limit - please call hagay -2527." , "red", "bg");
		// inc last
		int lastTask = HagUtil.s2i(lastTaskS);
		int lastTask1 = lastTask + 1;
		String newTask = HagUtil.i2s(lastTask1);
		String stm1 = "UPDATE  dbo.tasks_ind SET TASKNO='" + newTask + "' WHERE project='RI'";
		String ans3 = winTaskIncLast( lastTask, stm1);
		if (ans3.startsWith("1~")) 
			return HagUtil.shortHtml("Error: Failed to increase next task number - please call hagay -2527." , "red", "bg");
		String ans4 = winTaskInsertLine(taskTable,riVer, blpUser, lastTaskS);
		if (ans4.startsWith("1~")) 
			return HagUtil.shortHtml("Error: Failed to insert task line to ritasks table - please call hagay -2527." , "red", "bg");

		ArrayList<String> trn = new ArrayList<String>();
		trn.add("USER $SYS");
		trn.add("MANDATORY ON");
		trn.add("1101,," + lastTaskS + ",M00,7,,,,,2000");
		// trn.add("1036,,"+lastTaskS+",K90INA");
		trn.add("1036,," + lastTaskS + "," + blpUser);
		
		String ffTran = rsFolder + "openTask_" + lastTaskS + "_" + blpUser + ".tran";
		String ffLst = rsFolder + "openTask_" + lastTaskS + "_" + blpUser + "_lst.txt";
		String ffLog = rsFolder + "openTask_" + lastTaskS + "_" + blpUser + "_log.txt";
		String ffRc = rsFolder + "openTask_" + lastTaskS + "_" + blpUser + "_RC";
		
		StringBuilder logsSb = new StringBuilder();
		logsSb.append("<table bgcolor=\"#c0c0c0\" border=\"1\" >")
		.append("<tr ><td>link to Tran file</td><td><a href=\""+ffTran+"\">"+ffTran+"</a></td></tr >")
		.append("<tr ><td>link to Lst file</td><td><a href=\""+ffLst+"\">"+ffLst+"</a></td></tr >")
		.append("<tr ><td>link to Log file</td><td><a href=\""+ffLog+"\">"+ffLog+"</a></td></tr >")
	//	.append("<tr ><td>link to Rc file</td><td><a href=\""+ffRc+"\">"+ffRc+"</a></td></tr >")
										.append("</table>");
		String logs = logsSb.toString();
		String ansDelffTran = HagUtil.deleteFile(ffTran,  false);
		if (ansDelffTran.startsWith("1~")) 
			return HagUtil.shortHtml("Error: Failed to delete "+ffTran+" - please call hagay -2527." , "red", "bg");

		HagUtil.writeArrayListToFile(ffTran, trn, null, false);

		ArrayList<String> ans5 = runBisTranOnWinServer(
				server,
				"\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\emerge"+bisVer+"cmd.bat bis " + devBatchName + " "
						+ riVer.substring(0, 4) + " arg4 dbblp -u f -d " + devDbid + " -bn " + devBatchName + " -i " + ffTran + " -o " + ffLst + " && exit",
				ffLst, ffLog, ffLst, ffRc, "openTask");
		
		if (ans5.get(0).startsWith("0~")) {
			return HagUtil.shortHtml("Task number " + lastTaskS + " created."+"<br>"+logs, "green", "bg");
		
		} else {
			return HagUtil.shortHtml("Failed to create task number " + lastTaskS + " eMerge problem - please call hagay -2527."+"<br>"+ans5+"<br>"+logs , "red", "bg");
		}
	}
  
	public ArrayList<String> runBisTranOnWinServer(String server, String tranLine, String lstUncFile, String logF, String lstF, String rcF,
			String type) {

		HagUtil.deleteFile(logF,  false);
		HagUtil.deleteFile(lstF,  false);
		HagUtil.deleteFile(rcF + "_0",  false);
		HagUtil.deleteFile(rcF + "_99",  false);
		String tranLog=HagUtil.runCmdRemote(server,tranLine+"\n","N");
	///	ArrayList<String> tranLog = runJavaFunc("javaFunc~runOnServer~" + server + "~" + tranLine);
	///	HagUtil.writeArrayListToFile(logF, tranLog, this, true);
		HagUtil.writeStringToFile(logF, tranLog);
		HagUtil.sleep(10000);
		ArrayList<String> tranLst = HagUtil.loadFileIntoArrayList(lstUncFile, false, "zzzzz", false);
		HagStringList rcList = new HagStringList();
		if (type.equals("extract")) {
			if (HagUtil.isInArray("Return Code 00:", tranLst, false, true)) {

				rcList.add("0~extract ");
				rcList.writeToFile(rcF + "_0", true);
			///	HagUtil.writeArrayListToFile(rcF + "_0", rcList, this, true);
			} else {
				rcList.add("1~extract ");
				rcList.writeToFile(rcF + "_99", true);
		//.logF..	HagUtil.writeArrayListToFile(rcF + "_99", rcList, this, true);
			}
			HagUtil.appendArrayList2ArrayList(rcList, tranLst);
		} else if (type.equals("createXml")) {
			rcList.add("0~createXml ");
			HagUtil.appendArrayList2ArrayList(rcList, tranLst);
			rcList.writeToFile(rcF + "_0", true);
			//HagUtil.writeArrayListToFile(rcF + "_0", rcList, this, true);
			//HagUtil.writeArrayListToFile(rcF + "_0", rcList, this, true);
		} else if (type.equals("loadXml")) {
			rcList.add("0~loadXml ");
			HagUtil.appendArrayList2ArrayList(rcList, tranLst);
			rcList.writeToFile(rcF + "_0", true);
			//HagUtil.writeArrayListToFile(rcF + "_0", rcList, this, true);
		//	HagUtil.writeArrayListToFile(rcF + "_0", rcList, this, true);
		} else {
			HagUtil.sleep(6000);
			String ansCheck = checkSapiens(tranLst);
			if (ansCheck.startsWith("0~")) {
				rcList.add("0~tran ");
				rcList.writeToFile(rcF + "_0", true);
				//HagUtil.writeArrayListToFile(rcF + "_0", rcList, this, true);
			} else {
				rcList.add("1~tran ");
				rcList.add("checkSapiensRC="+ansCheck);
				rcList.add("lstUncFile="+lstUncFile);
				rcList.add("type="+type);
				rcList.add("type="+type);
				rcList.writeToFile(rcF + "_99", true);
				//HagUtil.writeArrayListToFile(rcF + "_99", rcList, this, true);
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
	public static final String checkSapiens(ArrayList<String> lst){
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

	public String closeTask_after(HttpServletRequest request, HttpServletResponse response){
		
		String cfgMenuUser 	= request.getParameter("cfgMenuUser");
		String blpUser 	= request.getParameter("blpUser");
		String riVer 		= request.getParameter("riVer");
		String taskNo 		= request.getParameter("taskNo");
		
	///	hagWorkerChange("add", "starting winTaskOpen\n", false, false, Color.black, null, 0);
	///	hagLinkPanel.addLabel("open new task:", "title");

		// checkUserOpen
	///	hagLinkPanel.addLabel("first step(cm tables)", "title");
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
			return HagUtil.shortHtml("Error: failed to read riRel_properties - please call hagay -2527" , "red", "bg");
		if(!rc.startsWith("0~"))
			return HagUtil.shortHtml(rc , "red", "bg");
			 
			 
			 
		String taskTable = riRel_properties[0][1];
		String rsFolder = riRel_properties[1][1]+"\\dev\\";;
		String server = riRel_properties[2][1];
		String bisVer= riRel_properties[3][1];
		String devDbid= riRel_properties[4][1];
		String devBatchName= riRel_properties[5][1];

	
		ArrayList<String> trn = new ArrayList<String>();
		trn.add("USER $SYS");
		trn.add("MANDATORY ON");
		trn.add("1101,C," + taskNo + ",,,,,,,,0001");

		String ffTran = rsFolder + "closeTask_" + taskNo + "_" + blpUser + ".tran";
		String ffLst = rsFolder + "closeTask_" + taskNo + "_" + blpUser + "_lst.txt";
		String ffLog = rsFolder + "closeTask_" + taskNo + "_" + blpUser + "_log.txt";
		String ffRc = rsFolder + "closeTask_" + taskNo + "_" + blpUser + "_RC";
		String ansDelffTran = HagUtil.deleteFile(ffTran,  false);
		if (ansDelffTran.startsWith("1~")) 
			return HagUtil.shortHtml("Error: Failed to delete "+ffTran+" - please call hagay -2527." , "red", "bg");
		StringBuilder logsSb = new StringBuilder();
		logsSb.append("<table bgcolor=\"#c0c0c0\" border=\"1\" >")
		.append("<tr ><td>link to Tran file</td><td><a href=\""+ffTran+"\">"+ffTran+"</a></td></tr >")
		.append("<tr ><td>link to Lst file</td><td><a href=\""+ffLst+"\">"+ffLst+"</a></td></tr >")
		.append("<tr ><td>link to Log file</td><td><a href=\""+ffLog+"\">"+ffLog+"</a></td></tr >")
	//	.append("<tr ><td>link to Rc file</td><td><a href=\""+ffRc+"\">"+ffRc+"</a></td></tr >")
										.append("</table>");
		String logs = logsSb.toString();
	
		HagUtil.writeArrayListToFile(ffTran, trn, null, false);

		ArrayList<String> ans5 = runBisTranOnWinServer(
				server,
				"\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\emerge"+bisVer+"cmd.bat bis " + devBatchName + " "
						+ riVer.substring(0, 4) + " arg4 dbblp -u f -d " + devDbid + " -bn " + devBatchName + " -i " + ffTran + " -o " + ffLst + " && exit",
				ffLst, ffLog, ffLst, ffRc, "closeTask");
		String ans = 	"";
		if (ans5.get(0).startsWith("0~")) {
			ans= HagUtil.shortHtml(taskNo + " task closed."+"<br>", "green", "bg");
			
		
		} else {
			ans= HagUtil.shortHtml("Failed to close " + taskNo + " task. - please call hagay -2527."+"<br>"+ans5+"<br>" , "red", "bg");

		
		}
	
	
		
		// cfgMenu table checkUserOpen
		String ans1 = winTaskCheckUserOpenTask(taskTable, blpUser, taskNo).trim();
		if (ans1.startsWith("1~") || ans1.startsWith("3~")) {
			return HagUtil.shortHtml(ans+"<br>"+ans1+"<br>"+logs , "red", "bg");

		}
		///hagWorkerChange("add", "winTaskCheckUserOpenTask rc = "+ans1+"\n", false, false, Color.black, null, 0);
		if (ans1.startsWith("2~")) {
			
			return HagUtil.shortHtml(ans+"<br>"+"Task " + taskNo + " not found for user " + blpUser+"<br>"+logs , "red", "bg");

		}
		if (ans1.startsWith("0~CLS")) {
			
			
			return HagUtil.shortHtml(ans+"<br>"+"Task " + taskNo + " already closed. " +"<br>"+logs , "red", "bg");

		}
	
		// update task status
		String ans2 = winTaskUpdateTaskStatus(taskTable, blpUser, taskNo);
		
		if (ans2.startsWith("1~")) {
			return HagUtil.shortHtml(ans+"<br>"+logs+"<BR>"+"Failed to update " + taskNo + " task status. " +"<br>" , "red", "bg");
		
		}
		
		return HagUtil.shortHtml(ans+"<br>"+logs+"<BR>"+ taskNo + " task status updated. " +"<br>" , "green", "bg");
		
		// return ans0.toString();
		

	}
	public String winTaskUpdateTaskStatus(String tasksTable, String user, String task) {
		String closeDate = HagUtil.getDateTime("yyyy-MM-dd");
		String closeTime = HagUtil.getDateTime("HH:mm:ss");
		HagSQL hagSQL = new HagSQL();
		String stm = "UPDATE " + tasksTable + " SET STATUS='CLS',CLOSEDATE='" + closeDate + "',CLOSETIME='" + closeTime + "' WHERE USERNAME='"
				+ user + "' and TASKNO='" + task + "'";
		String ans = hagSQL.update("SQL", "confg1", "cfg", "cfg09c", HagParam.getConfg1DB(), stm);
		return ans;
	}
	public String winTaskCheckUserOpenTask(String tasksTable, String user, String task) {
		HagSQL hagSQL = new HagSQL();
		String stm = "SELECT STATUS FROM " + tasksTable + " WHERE USERNAME='" + user + "' and TASKNO='" + task + "'";
		ArrayList<String> ans = hagSQL.select1col("SQL", "confg1", "cfg", "cfg09c", HagParam.getConfg1DB(),  stm);
		if (ans == null)
			return "1~NULL";
		else if (ans.size() == 0)
			return "2~task not found.";
		else if (ans.get(0).equals("Could not connect to the database"))
			return "3~" + ans.get(0);
		return "0~" + ans.get(0);
	}


	
}
