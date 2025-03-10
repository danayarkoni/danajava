package com.hag.hagay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeMxx {
	public static final String 	before(String user) {
		String[][] vals = getValues();
		StringBuilder ans = new StringBuilder();
		ans.append("<html><body bgcolor=\"#dddddd\">");
		
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"increaseMxx.jsp\">");
		ans.append("<input type=\"hidden\" name=\"sentBy\" id=\"sentBy\" value = \""+user+"\" maxlength=\"140\" size=\"140\">");
		
		ans.append("<font color=#ff0000><b><u>Select one line:</b></u></font><br><br>");
		ans.append("<table bgcolor=\"#9999bb\" cellpadding=\"5\" border =\"1\">");
		ans.append("<tr><td>version</td><td>current Mxx</td><td>Mxx will be increased to</td><td>note</td><tr>");
		for(int i = 0;i<vals.length;i++) {
			int curI = HagUtil.s2i(vals[i][1]);
			int nextI = curI+1;
			String nextS = ""+nextI;
			if(nextI<10)
				nextS = "0"+nextI;
			//if()
			//String rad = "<input type=\"radio\" id=\"verRad\" name=\"verRad\" value=\""+vals[i][0]+"_"+vals[i][1]+"\" >";
			String rad="";
			if(vals[i][0].trim().equals("0901")) {
				 rad = "<input type=\"radio\" id=\"verRad\" name=\"verRad\" value=\""+vals[i][0]+"_"+vals[i][1]+"\" checked >";
				 ans.append("<tr bgcolor=\"#ccccee\"><td>").append(rad).append(vals[i][0]).append("</td><td>").append(vals[i][1]).append("</td><td>").append(nextS).append("</td><td>").append(vals[i][2]).append("</td></tr>");
					
			}else {
				 ans.append("<tr bgcolor=\"#aaaacc\"><td>").append(rad).append(vals[i][0]).append("</td><td>").append(vals[i][1]).append("</td><td>").append(nextS).append("</td><td>").append(vals[i][2]).append("</td></tr>");
			}
		}
	
		ans.append("</table>");
		ans.append("<br><br><h2>");
		ans.append("<font color=#ff0000><b><u>please read and confirm:</b></u></font><br>");
		ans.append("1)	Maintenance number will be increased<br>");
		ans.append("2)	A minor version will be created from the current INT<br>");
		ans.append("3)	Activities on DEV/INT will be locked by devOps team<br>");
		ans.append("4)	When done, the lock will be released and the new maintenance will be active<br>");
		ans.append("5)	Double check that the current maintenance already packed to FTP<br>");
		ans.append("6)	It will not be possible to rollback from this action<br>");
		ans.append("</h2><br><br>");
		ans.append("<INPUT TYPE=SUBMIT value=\"Confirmed, please increase\"></FORM>");
		ans.append("</body></html>");
		return ans.toString();
	

	}
	public static final String 	beforeCm(String user) {
		HagRc hagRc=new HagRc();
		
		recreatePropertiesFile(hagRc);
		if(hagRc.rc!=0) {
			return "error: <br>" +hagRc.log.convertLogsToString()+"<br>"+"check results in:\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\list\\riRel_properties\\";
		}else {
			return "done: <br>" +"check results in:\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\list\\riRel_properties\\";
		}
	

	}
	public static final String [][] getValues(){
		String stm = "select " +
				"ver," +
				"mxx," +
				"note" +
				" from dbo.version_mxx order by ver DESC" ;
		HagSQL hagSQL = new HagSQL();
		HagStringList ans1 = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,3,"~",null,null);
		
		String[][] ans=new String[ans1.size()][3];
		for(int i = 0;i<ans1.size();i++) {
			String line = ans1.get(i);
			ans[i][0]= HagUtil.getWord0(line, "~", 0, true);
			ans[i][1]= HagUtil.getWord0(line, "~", 1, true);
			ans[i][2]= HagUtil.getWord0(line, "~", 2, true);
		}
		return ans;
	}

	
	public static final void recreatePropertiesFile(HagRc hagRc){

		HagStringList 	list 			= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\riRel_properties_skelVer.list",true,"*",false);
		String 			targetFolder 	= HagUtil.cfgMenuLoc+"\\bin\\list\\riRel_properties3";
		String 			targetFolderBK 	= HagUtil.cfgMenuLoc+"\\bin\\list\\riRel_propertiesBK";
		String 			date 			= HagUtil.getDateTime("yyyyMMdd_HHmmss");
		
		//backup
		String ans2 = HagUtil.zip(targetFolderBK+"\\"+date+".zip",targetFolder+"\\", targetFolderBK + "\\"+date+"zip_log.txt", "N", false, true);
		if(!ans2.startsWith("0~")){
			hagRc.rc=1;
			hagRc.log.add("failed to zip "+targetFolder+" into "+targetFolderBK+"\\"+date+".zip "+ans2);
			return;
		}
		//recreate folder
		HagUtil.reCreateFolder(hagRc,targetFolder);
		if(hagRc.rc!=0){
			return ;
		}
		
		for(int i = 0;i<list.size();i++) {
			String temp=list.get(i);
			String key			= HagUtil.getWord0(temp,"|",0,true);
			String vvvv			= HagUtil.getWord0(temp,"|",1,true);
			String vvDotvv		= HagUtil.getWord0(temp,"|",2,true);
			String vv_vv		= HagUtil.getWord0(temp,"|",3,true);
			String devDb		= HagUtil.getWord0(temp,"|",4,true);
			String intDb		= HagUtil.getWord0(temp,"|",5,true);
			String jiraVer		= HagUtil.getWord0(temp,"|",6,true);
			String extraDev		= HagUtil.getWord0(temp,"|",7,true);
			String curRel		= HagUtil.getWord0(temp,"|",8,true);
			String topack		= HagUtil.getWord0(temp,"|",9,true);
			String trunkDevDb	= HagUtil.getWord0(temp,"|",10,true);
			String trunkVer		= HagUtil.getWord0(temp,"|",11,true);
			
			HagStringList listSkel = new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\riRel_properties_skel.list",true,"*",false);
			HagStringList listSkelA = new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\riRel_properties_skelA.list",true,"*",false);
			HagStringList listSkelB = new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\riRel_properties_skelB.list",true,"*",false);
			
			listSkel.replaceStr("{key}", 		key);
			listSkel.replaceStr("{vvvv}", 		vvvv);
			listSkel.replaceStr("{vv.vv}", 		vvDotvv);
			listSkel.replaceStr("{vv_vv}", 		vv_vv);
			listSkel.replaceStr("{devDb}", 		devDb);
			listSkel.replaceStr("{intDb}", 		intDb);
			listSkel.replaceStr("{jiraVer}", 	jiraVer);
			listSkel.replaceStr("{extraDev}", 	extraDev);
			listSkel.replaceStr("{curRel}", 	curRel);
			listSkel.replaceStr("{topackSuff}", 	topack);
			listSkel.replaceStr("{trunkDevDb}", trunkDevDb);
			listSkel.replaceStr("{trunkVer}", 	trunkVer);
			
			listSkelA.replaceStr("{key}", 		key);
			listSkelA.replaceStr("{vvvv}", 		vvvv);
			listSkelA.replaceStr("{vv.vv}", 	vvDotvv);
			listSkelA.replaceStr("{vv_vv}", 	vv_vv);
			listSkelA.replaceStr("{devDb}", 	devDb);
			listSkelA.replaceStr("{intDb}", 	intDb);
			listSkelA.replaceStr("{jiraVer}", 	jiraVer);
			listSkelA.replaceStr("{extraDev}", 	extraDev);
			listSkelA.replaceStr("{curRel}", 	curRel);
			listSkelA.replaceStr("{topackSuff}", 	topack);
			listSkelA.replaceStr("{trunkDevDb}", trunkDevDb);
			listSkelA.replaceStr("{trunkVer}", 	trunkVer);
			
			listSkelB.replaceStr("{key}", 		key);
			listSkelB.replaceStr("{vvvv}", 		vvvv);
			listSkelB.replaceStr("{vv.vv}", 	vvDotvv);
			listSkelB.replaceStr("{vv_vv}", 	vv_vv);
			listSkelB.replaceStr("{devDb}", 	devDb);
			listSkelB.replaceStr("{intDb}", 	intDb);
			listSkelB.replaceStr("{jiraVer}", 	jiraVer);
			listSkelB.replaceStr("{extraDev}", 	extraDev);
			listSkelB.replaceStr("{curRel}", 	curRel);
			listSkelB.replaceStr("{topackSuff}", 	topack);
			listSkelB.replaceStr("{trunkDevDb}", trunkDevDb);
			listSkelB.replaceStr("{trunkVer}", 	trunkVer);
			
			listSkel.writeToFile(targetFolder+"\\"+key+".txt", false);
			listSkelA.writeToFile(targetFolder+"\\"+key+"A.txt", false);
			listSkelB.writeToFile(targetFolder+"\\"+key+"B.txt", false);
		}
	}
	public  String 	increaseMxx(HttpServletRequest request, HttpServletResponse response){
		String verRad 	= request.getParameter("verRad").trim();
		String sentBy 	= request.getParameter("sentBy").trim();
		String ver = HagUtil.getWord0(verRad, "_", 0, true);
		String curMaint = HagUtil.getWord0(verRad, "_", 1, true);
		int curI = HagUtil.s2i(curMaint);
		int nextI = curI+1;
		String nextMaint = ""+nextI;
		if(nextI<10)
			nextMaint = "0"+nextI;
		
		StringBuilder ans = new StringBuilder();
		ans.append("<br>").append("increasing ").append(ver).append(" Mxx from ").append(curMaint).append(" to ").append(nextMaint);
		ans.append("<br>").append("processes to run:");
		ans.append("<br>").append("send mail");
		ans.append("<br>").append("lock activities");
		ans.append("<br>").append("build minor");
		ans.append("<br>").append("increase Mxx");
		ans.append("<br>").append("change configuration files");
		ans.append("<br>").append("run tests");
		return ans.toString();
	
	}
		
}
