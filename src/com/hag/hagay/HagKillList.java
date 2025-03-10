package com.hag.hagay;

import java.util.ArrayList;

public final class  HagKillList {
	public static final String 	after() {
		HagStringList killList= new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\kill.list",true,"*",false);
		String mailTo=killList.get(0);
		HagSQL hagSQL = new HagSQL();
		HagStringList ans = new HagStringList();
		for(int i = 1 ;i < killList.size();i++) {
			String server = killList.get(i).trim();
			String stm = "select batchName from dbo.ri_environments_new where status='A' AND bis_server='"+server+"' AND project <> 'HAGWIDTH'";
			ArrayList<String> list = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm);
			ans.add(stopTomcatListener1(list,server));
			
		}
	
		String content= ans.convertToString("");
		//String mailList 	= HagUtil.getRiMails("all");
		String ccList 	= HagUtil.getRiMails("david.hagay@sapiens.com");
		String ans9=HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps,HagUtil.mailList_hag, " stop tomcat & listenr (servers killList) "	,content);
return "done";
	}
	public static final String 		stopTomcatListener1(ArrayList<String>list,String appServer){
		StringBuilder ans =new StringBuilder();
		

		ans.append("<h3>Stop tomcat and eMerge listener</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\" bgColor=\"#ffff00\"><td>APP server</td><td>Batch name</td><td>tomcat status</td><td>eMerge listener status</td></tr>");

		for(int i = 0;i<list.size();i++){
			String batchName = list.get(i);
			
			String ans1= "<td bgColor=\"#FF0000\">Locked by cmInstaller</td>";
			String ans2= "<td bgColor=\"#FF0000\">Please try again after cmInstaller-finish mail.</td>";
			
			
					ans1= HagUtil.stopTomcat(appServer, batchName);
					ans2= HagUtil.stopEmergeListener(appServer, batchName);
				
			
	
			ans.append("<tr bgColor=\"#ffffff\"><td>"+appServer+"</td><td>"+batchName+"</td>"+ans1+ans2+"</tr>");
			//ans.append("<tr><td>"+appServer+"</td><td>"+batchName+"</td><td>"+ans1+"</td></tr>" );
		}
		ans.append("</table>");
		return ans.toString();
	}
}
