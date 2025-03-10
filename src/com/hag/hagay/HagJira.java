package com.hag.hagay;

import java.io.File;
import java.util.ArrayList;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

public final class HagJira {
	
	public static String getForm(String cfgMenuWebUser,String cfgMenuWebPass){
		
		String riJiraVersions = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\riJiraVersions.txt";
		HagStringList riJiraVersionsList = new HagStringList(riJiraVersions,true,"*",false);

		HagStringList list = new HagStringList();
		list.add("<HTML><body bgcolor=\"#ccccbb\">");
		list.add("<font size=4 color=\"blue\"><u>check request(jira):</u></font><br><br>");
		list.add("<FORM METHOD=POST name=\"Form\" ACTION=\"jiraCheck.jsp\">");
		list.add("<table bgcolor=\"#aaaacc\" border=\"1\"><tr>");
		list.add("<td>version</td>");
		list.add("<td><fontcolor=\"yellow\"><select name=\"jiraVer\"></font>");
		for(int i =0;i< riJiraVersionsList.size();i++){
			String temp = riJiraVersionsList.get(i).trim();
			list.add("<option value=\""+temp+"\">"+temp+"</option>");
			
		}
		list.add("</select></font></td>");
		list.add("</tr></table>");
		list.add("<input type=\"hidden\" name=\"cfgMenuWebUser\" id=\"cfgMenuWebUser\" value = \""+cfgMenuWebUser+"\" maxlength=\"140\" size=\"140\">");
		list.add("<input type=\"hidden\" name=\"cfgMenuWebPass\" id=\"cfgMenuWebPass\" value = \""+cfgMenuWebPass+"\" maxlength=\"140\" size=\"140\">");
		list.add("<br><INPUT TYPE=SUBMIT value=\"Submit\"></FORM></BODY></HTML>");
		String str=list.convertToString("\n");
		return str;
	}
	
	
	public static HagStringList checkTopackFolder(HagRc hagRc,String ver) {
		hagRc.rc=0;
		HagStringList ans = new HagStringList();
		String folder1 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V" + ver + "m00\\TOPACK\\ri-components\\";
		String folder = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V" + ver + "m00\\TOPACK\\ri-components\\RiInstaller\\";
		if (ver.length() > 4)
			folder = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V" + ver + "u00\\TOPACK\\ri-components\\RiInstaller\\";

		// ri-web.war
		String war = folder + "ri-web.war";
		File warF = new File(war);
		if (warF.exists()) {
			String lastModified = HagUtil.getFileLastModified(warF,"dd/MM/yyyy-HH:ss");
			ans.add("0~ ri-web.war file found in " + folder+"<FONT color=\"#FF0000\"> (last modified at: "+lastModified+")</Font>");
			ans.add("<font color=green>0~ ri-web.war file found in " + folder+"</font>");
		}else{
			ans.add("<font color=red>1~ ri-web.war file not found in " + folder+"</font>");
			hagRc.rc=1;
		}
		// mig-mng.jar
		String jar = folder + "mig-mng.jar";
		File jarF = new File(jar);
		if (jarF.exists()) {
			String lastModified = HagUtil.getFileLastModified(jarF,"dd/MM/yyyy-HH:ss");
			ans.add("0~ mig-mng.jar file found in " + folder+"<FONT color=\"#FF0000\"> (last modified at: "+lastModified+")</Font>");
			ans.add("<font color=green>0~ mig-mng.jar file found in " + folder+"</font>");
		}else{
			ans.add("<font color=red>1~ mig-mng.jar file not found in " + folder+"</font>");
			hagRc.rc=1;
		}
		// eObject
		String script = folder1 + "EOBJECTS\\metadata.htm";
		File scriptF = new File(script);
		if (scriptF.exists())
			ans.add("<font color=green>0~ EOBJECTS\\metadata.htm file found in " + folder1+"</font>");
		else{
			ans.add("<font color=red>1~ EOBJECTS\\metadata.htm file not found in " + folder1+"</font>");
			hagRc.rc=1;
		}
		// webProxy as folder
		if (ver.equals("0801")) {
			String folder12 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V" + ver + "m00";
			String webProxyS = "\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V" + ver + "m00\\TOPACK\\WebProxyAsFolder\\WebProxy\\web.config";
			
				File webProxyF = new File(webProxyS);
				if (webProxyF.exists() && webProxyF.isFile() )
					ans.add("<font color=green>0~ (0801 ver) TOPACK\\WebProxyAsFolder\\WebProxy\\web.config file found in " + folder12+"</font>");
				else{
					ans.add("<font color=red>1~ (0801 ver) TOPACK\\WebProxyAsFolder\\WebProxy\\web.config file not found in " + folder12+"</font>");
					hagRc.rc=1;
				}
		}
		return ans;
	}
	public static String riWinJiraCheckVer(String ver, String jiraVer) {
		HagJiraWin hagJiraWin = new HagJiraWin();
		String ans1 = hagJiraWin.rismt1(ver, jiraVer).trim();
		// String ans1 = "0~hagtempfix-NOT RELEASED";
		if (!ans1.endsWith("NOT RELEASED"))
			return "1~version status problem";
		return "0~Version status is NOT RELEASED.";
	}
	
	private static String checkExtraDevCurDev(ArrayList<String> list,String extraDev,String jiraVer,StringBuilder extraDev9){
		for(int i = 0 ; i< list.size();i++){
			String temp= list.get(i);
			if(temp.indexOf("error:")>-1)
				if(temp.indexOf("will be ignored")<0)
					return "error";
		}
		if(extraDev.equals("NO"))
			return "NN";
		HagRiRel hagRiRel1 = new HagRiRel(extraDev);
		String extraJiraVer = hagRiRel1.riVerJira;
		int extraCount=0;
		int curCount=0;
		for(int i = 0 ; i< list.size();i++){
			String temp= list.get(i);
			if(temp.endsWith("~"+jiraVer))
				curCount++;
			if(temp.endsWith("~"+extraJiraVer))
				extraCount++;

		}
			
		if(extraCount==0 && curCount == 0)
			return "NN";
		if(extraCount>0 && curCount > 0)
			return "error";
		if(extraCount>curCount){
			extraDev9.append("(ACTIVE)");
			return extraJiraVer;
		}else{
			extraDev9.append("(NOT ACTIVE)");
			return jiraVer;
		}
				
	
	}
	/*
	public static String checkRequest(String jiraVer1,String jiraVer){
		HagRiRel hagRiRel = new HagRiRel(jiraVer);
		String extraDev1 = hagRiRel.getExtraDev();
		String extraDev = ""+extraDev1;
		String affectsVersion = "";
		if(extraDev1.indexOf("!")>-1){
			extraDev = HagUtil.getWord0(extraDev1, "!", 0, true);
			affectsVersion = HagUtil.getWord0(extraDev1, "!", 1, true);
		}else{
			if(!extraDev.equalsIgnoreCase("NO")){
				return HagUtil.shortHtml("Error: ri.properties extraDev - please call hagay", "red", "bg");
			}
		}
		String ver 			= hagRiRel.getRiVer();
		HagStringList ans=new HagStringList();
		ans.add("TOPACK folder check:");
		HagStringList ans1 = checkTopackFolder( ver);
		ans.append(ans1);
		ans.add("Components check:");
		HagStringList ans2 = riWinJira_components(hagRiRel,  extraDev,affectsVersion);
		ans.append(ans2);
		return ans.convertToString("<br>");
	}
	*/
	
	public static String checkRequest_components(HagRc hagRc,String jiraVer1,String jiraVer,StringBuilder extraDev9){
		hagRc.rc=0;
		HagRiRel hagRiRel = new HagRiRel(jiraVer);
		String extraDev1 = hagRiRel.getExtraDev();
		String extraDev = ""+extraDev1;
		String affectsVersion = "";
		if(extraDev1.indexOf("!")>-1){
			extraDev = HagUtil.getWord0(extraDev1, "!", 0, true);
			affectsVersion = HagUtil.getWord0(extraDev1, "!", 1, true);
			extraDev9.append(extraDev1);
		}else{
			if(!extraDev.equalsIgnoreCase("NO")){
				extraDev9.append("Error: ri.properties");
				return HagUtil.shortHtml("Error: ri.properties extraDev - please call hagay","red","bg");
				//hagRc.rc=1;
				//hagRc.log.add("Error: ri.properties extraDev - please call hagay");
			//	return null;
			}
			extraDev9.append("NO");
		}
		//ans.add("Components check:");
		HagStringList ans2 = riWinJira_components(hagRc,hagRiRel,  extraDev,affectsVersion,extraDev9);
		//affVerCheck
		hagRc.rc=0;
		String affCurVal="hagInit";
		
		for(int i = 0 ; i<ans2.size();i++){
			String temp = ans2.get(i);
			String aff = HagUtil.getWord0(temp,"~",4,true);
			if(affCurVal.equals("hagInit") || affCurVal.equals("emptyTicket"))
				affCurVal=aff;
			else{
			//	System.out.println("*"+aff+"!"+affCurVal+"*");
				if(!aff.equalsIgnoreCase(affCurVal) && !aff.equals("emptyTicket") ){
					hagRc.rc=1;
				}
			}
		}
		if(hagRc.rc!=0 && !extraDev.equalsIgnoreCase("NO")){
			hagRc.log.add("<font color=red>Jira Source Version field must be the same in all the tickets</font>");
			HagStringList ans3 = HagUtil.prepMsgSrcVer(ans2);
			hagRc.log.append(ans3);
		}
		//return ans2;
		StringBuilder ans1 = new StringBuilder();
		for(int i = 0 ; i <ans2.size();i++ ){
			String temp = ans2.get(i);
			ans1.append("<input type=\"checkbox\" onclick=\"return false\" name=\"cb3\" id=\"cb3\" value=\"").append(temp).append("\" checked >"+temp).append("<br>");
		}
		
		
		
		return ans1.toString();
	}
	public static String checkRequest_topack(HagRc hagRc,String jiraVer1,String jiraVer){
		HagRiRel hagRiRel = new HagRiRel(jiraVer);
		String ver 			= hagRiRel.getRiVer();
		HagStringList ans=new HagStringList();
	//	ans.add("TOPACK folder check:");
		HagStringList ans1 = checkTopackFolder(hagRc, ver);
		ans.append(ans1);
		return ans.convertToString("<br>");
	}
	public static final ArrayList<String> checkComponerntsTask(String task,ArrayList<String> list,String tasksTable ){
		HagSQL hagSQL = new HagSQL();
		 String stm = "SELECT STATUS FROM "+tasksTable+" WHERE TASKNO='"+task+"'";

		 ArrayList<String> ans = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm);
		 ArrayList<String> ans1= new ArrayList<String>();
		 if (	ans == null ){ 
			ans1.add("1");
			ans1.add("NULL");
			return  ans1;
		 }

		 if (	ans.size() == 0){ 
			ans1.add("1");
			ans1.add("task not found.");
			return  ans1;
		 }
		 String temp = ans.get(0).trim();
	
		 
		 if (temp.equals("Could not connect to the database")){
			ans1.add("1");
			ans1.add("Could not connect to the database");
			return  ans1;
		 }
		 if(temp.equals("OPN")){
			ans1.add("1");
			ans1.add("task status = OPN.("+tasksTable+")");
			return  ans1;
		 }
		 HagStringList listT =getExistInTaskList(task, list);
		 if(listT.size() > 1){
			 String ansT = listT.convertToString(" and ");
			 ans1.add("1");
		     ans1.add("task status = DUP.("+tasksTable+") "+ansT);
		     return  ans1;
		 }
		ans1.add("0");
		ans1.add("task status = CLS.("+tasksTable+")");
		return  ans1;
		 
	}
	
	
	public static final HagStringList getExistInTaskList(String task,ArrayList<String> list ){
		HagStringList rc = new HagStringList();
		for(int i = 0 ; i < list.size();i++){
			String temp = list.get(i);
			String w1 = HagUtil.getWord0(temp,"~", 1, true);
			String w2 = HagUtil.getWord0(temp,"~", 2, true);
			String w3 = HagUtil.getWord0(temp,"~", 3, true);
			if(w2.endsWith("TSK") && w3.equals(task)){
				rc.add(w1+"~"+w2+"~"+w3);
			}
		}
		return rc;
	}
		
	public static final void checkComponents(HagRc hagRc,HagStringList ans2 ,String iomFolder,String tasksTable){
		//"0~check components to release";
		//HagUtil.p(tasksTable);
		hagRc.rc=0;
		for(int i = 0 ; i < ans2.size();i++){
			String temp = ans2.get(i);
			String ticket= HagUtil.getWord0(temp,"~",1,true);
			String type= HagUtil.getWord0(temp,"~",2,true);
			String name= HagUtil.getWord0(temp,"~",3,true);
			String affVer= HagUtil.getWord0(temp,"~",4,true);
			ArrayList<String> ww= new ArrayList<String>();
			ww.add("0");
			ww.add("no checks");
			if(type.equals("TSK")) {
				ww = checkComponerntsTask(name,ans2,tasksTable);
				}
			if(type.equals("IOM")) {
				ww = checkComponerntsIom(iomFolder,name);
			}
			if(type.equals("CMD") || type.equals("RX")) {
				ww = checkComponerntsAs400(name);
			}
			String pre = HagUtil.left(ww.get(0)+"~"+ticket+"~"+type+"~"+name+"~"+affVer," ", 50,".");
			
			String color = "<font color=red>";
			if(ww.get(0).endsWith("0"))
				color = "<font color=green>";
			ans2.set(i,color+pre+"~"+ww.get(1)+"</font>");
			if(!ww.get(0).equals("0")){
				hagRc.log.add(color+pre+"~"+ww.get(1)+"</font>");
				hagRc.rc=1;
			}
		}
	}
	public static final ArrayList<String> checkComponerntsAs400(String name ){
		ArrayList<String> ans1= new ArrayList<String>();
		String chk = isAs400ValidName(name);
		if (!chk.startsWith("0~")){
			ans1.add("1");
			ans1.add(chk.substring(2,chk.length()));
			return  ans1;
		}
		ans1.add("0");
		ans1.add("valid as400 name");
		return  ans1;
		 
	}	
	public static final String isAs400ValidName(String name){
		String valid = "ABCDEFGFIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz#";
		if(name==null)								return "1~name is null";
		if(name.length()==0)						return "1~name length = 0";
		if(name.length()>10)						return "1~name exceeds 10 characters";
		if(valid.indexOf(name.substring(0,1))<0)	return "1~name first character not valid";
		return "0~valid As400 name";
	}
	public static final ArrayList<String> checkComponerntsIom(String iomFolder,String iom ){
		ArrayList<String> ans1= new ArrayList<String>();
		File ff = new File(iomFolder+"\\"+iom+".dll");
       
		 if (!ff.exists()){
			ans1.add("1");
			ans1.add("IOM not found in DEV");
			return  ans1;
		 }
		ans1.add("0");
		ans1.add("IOM found in DEV");
		return  ans1;
		 
	}	
	/*
	public static HagStringList riWinJira(HagRiRel hagRiRel,String extraDev,String affectsVersion) {
		String ver 			= hagRiRel.getRiVer();
		String jiraVer 		= hagRiRel.getRiVerJira();
		String iomFolder 	= hagRiRel.getIomUncDev();
		String tasksTable 	= hagRiRel.getTasksTable();
		String devBatchName = hagRiRel.getDevBatchName();
	
		HagStringList listJira = new HagStringList();
		listJira.add("TOPACK folder check:<br>");
		ArrayList<String> ans3 = checkTopackFolder(ver);
		boolean topackRC = true;
		listJira.add("<table>");
		for (int i = 0; i < ans3.size(); i++) {
			String temp = ans3.get(i);
			if (temp.startsWith("0~"))
				listJira.add("<tr bgcolor=\"#00FF00\"><td>"+temp+"</td></tr>");
			else {
				listJira.add("<tr bgcolor=\"#FF0000\"><td>"+temp+"</td></tr>");
				topackRC = false;
			}
		}
		listJira.add("</table>");
	
		if (!topackRC) {
			listJira.add("1~TOPACK folder problem.");
			return listJira;
		}
	

		listJira.add("Components to release:<br>");
		
		HagJiraWin hagJiraWin = new HagJiraWin();
		HagStringList ans2 = hagJiraWin.rismt2(ver, jiraVer );

		String checkHag = checkExtraDevCurDev(ans2,extraDev,jiraVer,extraDev9);
		
		//HagUtil.p(checkHag);
		if(checkHag.equals("error")){
			listJira.add("<table>");
			for(int i=0;i<ans2.size();i++){
				listJira.add("<tr bgcolor=\"#FF0000\"><td>"+ans2.get(i)+"</td></tr>");
			}
			listJira.add("</table></body></html>");
			return listJira;
		}
		if(!extraDev.equalsIgnoreCase("NO") && !checkHag.equalsIgnoreCase(hagRiRel.getRiVerJira()) ){
			HagRiRel hagRiRelExtraDev = new HagRiRel(extraDev);
			iomFolder = hagRiRelExtraDev.getIomUncDev();
			tasksTable = hagRiRelExtraDev.getTasksTable();
		}
		checkComponents(ans2, iomFolder,tasksTable);
		//if (!from.equals("CFG")) {
			String checkRCS = "0~Check components";
			// HagUtil.checkComponents(ver,ans2,iomFolder);
			if (ans2.size() == 0) {
				listJira.add("<table>");
				listJira.add("<tr bgcolor=\"#FF0000\"><td>No components to release.</td></tr>");
				listJira.add("</table>");
				return listJira;
			}
			listJira.add("<table>");
			for (int i = 0; i < ans2.size(); i++) {
				String temp = ans2.get(i);
				if (temp.startsWith("0~"))
					listJira.add("<tr bgcolor=\"#00FF00\"><td>"+temp+"</td></tr>");
				else {
					listJira.add("<tr bgcolor=\"#FF0000\"><td>"+temp+"</td></tr>");
					checkRCS = "1~Check components";
				}
			}
			listJira.add("</table>");
			return listJira;
		
			}
	
	*/
	public static HagStringList riWinJira_components(HagRc hagRc,HagRiRel hagRiRel,String extraDev,String affectsVersion,StringBuilder extraDev9) {
		String ver 			= hagRiRel.getRiVer();
		String jiraVer 		= hagRiRel.getRiVerJira();
		String iomFolder 	= hagRiRel.getIomUncDev();
		String tasksTable 	= hagRiRel.getTasksTable();
	//	String devBatchName = hagRiRel.getDevBatchName();
	
		HagStringList listJira = new HagStringList();
		//	listJira.add("Components to release:<br>");
		String jiraVer0000 =jiraVer;
		if(jiraVer.equals("00.00M01"))
			jiraVer0000="Trunk";
		HagJiraWin hagJiraWin = new HagJiraWin();
		HagStringList ans2 = hagJiraWin.rismt2(ver, jiraVer0000 ,extraDev);
		
		String checkHag = checkExtraDevCurDev(ans2,extraDev,jiraVer,extraDev9);
		
		if(checkHag.equals("error")){
			for(int i=0;i<ans2.size();i++)
				listJira.add(ans2.get(i));
			return listJira;
		}
		//replace the task table
		if(!extraDev.equalsIgnoreCase("NO")){  //define with extra dev from hagay
			if(!checkHag.equalsIgnoreCase("NN")){ //extra dev from oded = yes
				if( !checkHag.equalsIgnoreCase(hagRiRel.getRiVerJira()) ){ //extra dev in ticket != jiradev
					HagRiRel hagRiRelExtraDev = new HagRiRel(extraDev);
					iomFolder = hagRiRelExtraDev.getIomUncDev();
					tasksTable = hagRiRelExtraDev.getTasksTable();
				}
			}
		}
		
		checkComponents( hagRc,ans2, iomFolder,tasksTable);
		if(hagRc.rc!=0)
			hagRc.log.add("components check failed.");
		//String checkRCS = "0~Check components";
		if (ans2.size() == 0) {
			listJira.add("<font color=red>No components to release.</font>");
			return listJira;
		}
		for (int i = 0; i < ans2.size(); i++) {
			String temp = ans2.get(i);
			listJira.add(temp);
		//		if (!temp.startsWith("0~"))
		//			checkRCS = "1~Check components";
		}
		return listJira;
	
	}
	
	
	
/*
			Object[] options = { "Continue", "Exit" };
			hagTablePanel = new HagTablePanel(results, width, " Components from JIRA:", false, this, "HagSelectAll", "aa", true,null);
			// setTableView(hagTablePanel);

			int answer = -1;
			while (answer == -1)
				answer = HagUtil.dspHagTablePanel(width, 400, ver + " components from JIRA", hagTablePanel, JOptionPane.INFORMATION_MESSAGE, this,
						options);

			switch (answer) {
				case 0:
					boolean checkRC = true;
					// ArrayList<String> list = new ArrayList<String>();
					for (int g = 0; g < hagTablePanel.table.getRowCount(); g++) {
						HagCheckBox xxx = (HagCheckBox) (hagTablePanel.table.getValueAt(g, 0));
						String temp1 = ans2.get(g);
						String temp2 = HagUtil.getWord0(temp1, "~", 0, true);
						StringBuilder temp = new StringBuilder().append(temp2).append("~").append(hagTablePanel.table.getValueAt(g, 1).toString())
								.append("~").append(hagTablePanel.table.getValueAt(g, 2).toString()).append("~")
								.append(hagTablePanel.table.getValueAt(g, 3).toString()).append("~")
								.append(hagTablePanel.table.getValueAt(g, 4).toString());
						list1.add(temp.toString());
						if (xxx.isSelected()) {
							list.add(temp.toString());
						}
					}
					HagUtil.checkComponents( list, iomFolder,tasksTable);
					for (int i = 0; i < list.size(); i++) {
						String temp = list.get(i);
						if (temp.startsWith("0~"))
							hagLinkPanel.addLabel(temp, "good");
						else {
							hagLinkPanel.addLabel(temp, "error");
							checkRC = false;
						}
					}
					if (checkRC) {
						rcc.add("0~components to release.");
						rcc.add(hagTablePanel);
						rcc.add(checkHag);
						return rcc;
						// return "0~components to release.";
					} else {
						rcc.add("1~components to release.");
						rcc.add(hagTablePanel);
						rcc.add(checkHag);
						return rcc;
						// return "1~components to release.";
					}
				case 1:
					hagLinkPanel.addLabel("1~Release process stopped by user.", "error");
					rcc.add("2~Release process stopped by user.");
					rcc.add(hagTablePanel);
					rcc.add(checkHag);
					return rcc;
					// return "1~Release process stopped by user.";
			}
*/
			

	//	rcc.add("0~");
	//	rcc.add(hagTablePanel);
	//	rcc.add(checkHag);
	//	return rcc;
		
	
}
