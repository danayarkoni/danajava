package com.hag.hagay;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

public final class  HagServersInfo {
	public static final String 	getAppServersInfo_before(String act,String user,String pass) {
		HagStringList ans3 = new	HagStringList(); 
    	ans3.add("<h2><u>get APP servers info:</u></h2>");
    	
		ans3.add("<table bgColor=\"888888\"  cellpadding=\"1\" cellspacing=\"1\" border=\"1\" >");
		ans3.add("<FORM METHOD=POST name=\"Form\" ACTION=\"getAppServerInfo.jsp\">");

		
		ans3.add("<tr ><td>APP server</td><td>");
		ans3.add("<select  name=\"bis_server\" id=\"bis_server\"  value = \"0801\" >");
		HagStringList appServersList = getRiServers("APP");
		for(int i = 0;i<appServersList.size();i++) {
			ans3.add("<option  value=\""+appServersList.get(i)+"\"  selected>"+appServersList.get(i)+"</option>");
		}
		ans3.add("</select></td></tr>");
		 
			
	//	ans3.add("<td><INPUT TYPE=SUBMIT value=\"Submit\" style=\"background-color:LightGreen\" ></FORM></td></tr>");
		ans3.add("<td><INPUT TYPE=SUBMIT value=\"Submit\" style=\"background-color:LightGreen\" onclick=\"this.disabled=true;this.value='Please wait(40sec)...';this.form.submit();\"></FORM></td></tr>");
		
		ans3.add("</table><br>");
			
		return ans3.convertToString("");
	//	return HagUtil.shortHtml("Error: "+act+" option not valid - please call hagay -2527" , "red", "bg");


	
	
	}
	
	public String getAppServersInfo_after(HttpServletRequest request, HttpServletResponse response){
		String bis_server 	= request.getParameter("bis_server");
		bis_server=bis_server.trim();
		HagRc hagRc=new HagRc();
		HagStringList list = runTaskManager(hagRc,bis_server);
		if(hagRc.rc!=0)
			return hagRc.log.get(0);
		String memTotal = runMemTotal(hagRc,bis_server);
		if(hagRc.rc!=0)
			return hagRc.log.get(0);
		String memFree   = runMemFree(hagRc,bis_server);
		if(hagRc.rc!=0)
			return hagRc.log.get(0);
		String tomcats = getInfo(list,memTotal,memFree,bis_server);
		
		return tomcats;
	}
	public String getInfo(HagStringList list,String memTotal,String memFree,String bis_server) {
		HagStringList ans3 = new HagStringList();
		ans3.add("<h3>App server info: ("+bis_server+")</h3>");
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
		ans3.add(ans.toString());
	
		ans3.add("<table  id=\"myTableAll\"  bgColor=\"eeeeee\"  cellpadding=\"1\" cellspacing=\"4\" border=\"1\">");
		
		ans3.add("<tr>");
		ans3.add("<td>");
		ans3.add("<h4>Server cpu total/use:</h4>");
		ans3.add("<table  id=\"myTableCpu\"  bgColor=\"eeeeee\"  cellpadding=\"4\" cellspacing=\"1\" border=\"1\">");
		ans3.add("<tr><td>Total</td><td>"+"future"+"</td></tr>");
		ans3.add("<tr><td>Use</td><td>"+"future"+"</td></tr>");
	
		ans3.add("</table>");
		ans3.add("</td>");
	
		ans3.add("<td  rowspan=2>");
		ans3.add("<h4>Server memory per tomcat:</h4>");
		ans3.add("<table  id=\"myTable\"  bgColor=\"eeeeee\"  cellpadding=\"4\" cellspacing=\"1\" border=\"1\">");
		ans3.add("<tr ><td>.</td>"
				+ "<td><img src=\"arrow_down.gif\" onclick=\"sortTable(1,'d')\"  width=\"25\" height=\"20\"> <img src=\"arrow_up.gif\"  onclick=\"sortTable(1,'u')\"  width=\"25\" height=\"20\"> </td>"
				+ "<td><img src=\"arrow_down.gif\" onclick=\"sortTable(2,'d')\"  width=\"25\" height=\"20\"> <img src=\"arrow_up.gif\"  onclick=\"sortTable(2,'u')\"  width=\"25\" height=\"20\"></td>"
				+ "</tr>");
	
		ans3.add("<tr bgColor=\"cccccc\" ><td>ProcessId</td><td>Mem Usage</td><td>env</td></tr>");
	
		for(int i = 0 ; i< list.size();i++) {
			String temp = list.get(i);
			String w1= HagUtil.getWord0(temp,"~",1,true);
			if(w1.trim().equals("java.exe")){
				String w0= HagUtil.getWord0(temp,"~",0,true);
				String w2= HagUtil.getWord0(temp,"~",2,true);
				String w3= HagUtil.getWord0(temp,"~",3,true);
				w3=w3.substring(0,w3.length()-6);
				String w4= HagUtil.getWord0(temp,"~",4,true);
				String  env=".";
				int pos = w4.indexOf("\\RIjava_");
				if(pos>-1) {
					env=w4.substring(pos+8,w4.indexOf("\\",pos+10));
				}
				ans3.add("<tr ><td>"+w0+"</td><td>"+w3+"</td><td>"+env+"</td></tr>");
				
			}
		}
		ans3.add("</table>");
		ans3.add("</td>");
		ans3.add("</tr>");
		
		ans3.add("<tr>");
		ans3.add("<td>");
		ans3.add("<h4>Server memory total/free:</h4>");
		ans3.add("<table  id=\"myTableMem\"  bgColor=\"eeeeee\"  cellpadding=\"4\" cellspacing=\"1\" border=\"1\">");
		ans3.add("<tr><td>Total</td><td>"+memTotal+"</td></tr>");
		ans3.add("<tr><td>Free</td><td>"+memFree+"</td></tr>");
		ans3.add("</table>");
		ans3.add("</td>");
		ans3.add("</tr>");
		ans3.add("</table>");
		return ans3.convertToString("");
	}
	public static HagStringList runTaskManager1111111111111(HagRc hagRc,String server) {
		String timestamp=HagUtil.getDateTime("yyyyMMdd_HHmmssSSS");
		String server1 = server + ".sapiens.int";
		String server2 = HagUtil.replaceStr(server1, "-", "_");
		String wmicFile1 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\temp\\taskManager_" + server2 +"_"+timestamp+"_asc.txt";
		String wmicFile2 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\temp\\taskManager_" + server2 + "_"+timestamp+"_utf8.txt";
		String cmd = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\wmic_list.bat " + wmicFile1 + " " + server1
				+ " gon09c";
		ArrayList<String> taskList = getTaskListToFile(server, wmicFile1, wmicFile2, cmd,0);
		if(taskList==null) {
			hagRc.rc=1;
			hagRc.log.add(HagUtil.shortHtml("Error: failed to run runTaskManager on "+server+" server - please call hagay -2527" , "red", "bg"));
			return null;
		}
		HagStringList taskListNew = convertTaskManager(taskList, server);
		return taskListNew;
	}
	
	public static HagStringList runTaskManager(HagRc hagRc,String server) {
	//	String cmd = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\wmic_list.bat " + wmicFile1 + " " + server1
	//			+ " gon09c";
	//	WMIC /OUTPUT:%1 /node:"%2" /user:"sapiens\cfg-burner" /password:"%3"  PROCESS get Commandline,processid,name,WorkingSetSize
		String cmd = "WMIC /OUTPUT:STDOUT   process  get Commandline,processid,name,WorkingSetSize";
		System.out.println(cmd);
		String aaa =HagUtil.runNotSudo9(server, cmd, "sapiens\\cfg-burner");
		System.out.println(aaa);
		
		
			
			HagStringList list =new HagStringList(aaa,"\n",false);
			return list;
		
	}
	
	public static String runMemTotal(HagRc hagRc,String server) {
		String server1 = server + ".sapiens.int";
	
		String server2 = HagUtil.replaceStr(server1, "-", "_");
		String timestamp=HagUtil.getDateTime("yyyyMMdd_HHmmssSSS");
		String wmicFile1 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\temp\\taskMemTotal_" + server2 +"_"+timestamp+"_asc.txt";
		String wmicFile2 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\temp\\taskMemTotal_" + server2 + "_"+timestamp+"_utf8.txt";
		//String cmd = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\wmic_memTotal.bat " + wmicFile1 + " " + server1
		//		+ " gon09c";
	//	ArrayList<String> taskList = getTaskListToFile(server, wmicFile1, wmicFile2, cmd,1);
		//WMIC /OUTPUT:%1 /node:"%2" /user:"sapiens\cfg-burner" /password:"%3"  ComputerSystem get TotalPhysicalMemory
		String cmd = "WMIC /OUTPUT:STDOUT  ComputerSystem get TotalPhysicalMemory";
		String aaa =HagUtil.runNotSudo9(server, cmd, "sapiens\\cfg-burner");
		HagStringList taskList =new HagStringList(aaa,"\n",false);
			
		if(taskList==null || taskList.size()<2) {
		//	hagRc.rc=1;
		//	hagRc.log.add(HagUtil.shortHtml("Error: failed to run runTaskManager on "+server+" server - please call hagay -2527" , "red", "bg"));
		//	return null;
			return HagUtil.shortHtml("Error: failed to run runMemTotal on "+server+" server - please call hagay -2527" , "red", "bg");
		}
		//HagStringList taskListNew = convertTaskManager(taskList, server);
		String aa= taskList.get(1).trim();
	
		String cc=aa.substring(0,aa.length()-9);
		int ccI=HagUtil.s2i(cc);
		int ccI2= ccI-ccI/16;
		return ""+ccI2;
	}
	public static String runMemFree(HagRc hagRc,String server) {
		String server1 = server + ".sapiens.int";
		String server2 = HagUtil.replaceStr(server1, "-", "_");
		String timestamp=HagUtil.getDateTime("yyyyMMdd_HHmmssSSS");
		String wmicFile1 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\temp\\taskMemFree_" + server2 +"_"+timestamp+"_asc.txt";
		String wmicFile2 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\temp\\taskMemFree_" + server2 + "_"+timestamp+"_utf8.txt";
	//	String cmd = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\wmic_memFree.bat " + wmicFile1 + " " + server1
	//			+ " gon09c";
		//WMIC /OUTPUT:%1 /node:"%2" /user:"sapiens\cfg-burner" /password:"%3"  os get freephysicalmemory /format:value
		String cmd = "WMIC /OUTPUT:STDOUT   os get freephysicalmemory /format:value";
		System.out.println(cmd);
		String aaa =HagUtil.runNotSudo9(server, cmd, "sapiens\\cfg-burner");
		System.out.println(aaa);
		
		
			
			HagStringList taskList =new HagStringList(aaa,"\n",false);
			//return list;
			
		//ArrayList<String> taskList = getTaskListToFile(server, wmicFile1, wmicFile2, cmd,2);
		if(taskList==null) {
			return HagUtil.shortHtml("Error: failed to run runMemFree on "+server+" server - please call hagay -2527" , "red", "bg");
			//hagRc.rc=1;
			//hagRc.log.add(HagUtil.shortHtml("Error: failed to run runTaskManager on "+server+" server - please call hagay -2527" , "red", "bg"));
			//return null;
		}
		//HagStringList taskListNew = convertTaskManager(taskList, server);
		String aa=  taskList.get(2);
		String bb=  aa.substring(aa.indexOf("=")+1,aa.length());
		if(bb.length()==6)
			return "0."+bb.charAt(0);
		if(bb.length()==5)
			return "0.0"+bb.charAt(0);
		if(bb.length()<5)
			return "0";
		String cc=bb.substring(0,bb.length()-9);

		return cc;
	}
	private static HagStringList convertTaskManager(ArrayList<String> oldList, String server) {
		HagStringList newList = new HagStringList();
		newList.add("ProcessId~Name~Server~Mem Usage~CommandLine");
		newList.add("y80~y180~y120~y120~y1800");
		for (int i = 1; i < oldList.size(); i++) {
			String temp = oldList.get(i).trim();

			// HagUtil.p(temp);
			int pos = temp.lastIndexOf(" ");
			// MEM
			String temp4 = temp.substring(pos, temp.length());
			String temp4a = HagUtil.padNumLeft(temp4, 13, '0');
			temp = temp.substring(0, pos).trim();
			// pid
			pos = temp.lastIndexOf(" ");
			String temp3 = temp.substring(pos, temp.length());
			temp = temp.substring(0, pos).trim();
			if (temp.endsWith("System Idle Process")) {
				newList.add(temp3 + "~System Idle Process" + "~" + server + "~" + temp4a + "~.");
			} else {
				pos = temp.lastIndexOf(" ");
				if (pos == -1) {
					newList.add(temp3 + "~" + temp + "~" + server + "~" + temp4a + "~.");
				} else {
					String temp2 = temp.substring(pos, temp.length());
					temp = temp.substring(0, pos).trim();
					newList.add(temp3 + "~" + temp2 + "~" + server + "~" + temp4a + "~" + temp);
				}
			}
		}
		return newList;

	}
	public static final ArrayList<String>  getTaskListToFile(	String server,
			String wmicFile_asc,
			String wmicFile_utf8,
			String cmd		,int flag	) {
		// delete file
		HagUtil.deleteFile(wmicFile_asc, false);
		HagUtil.deleteFile(wmicFile_utf8,false);
		// run batch
		HagUtil.runBatch(cmd, true);
		HagUtil.sleep(2500);
		convertEncoding(wmicFile_asc,"utf-16le",wmicFile_utf8,"UTF-8");
		HagUtil.sleep(2500);
		try {
			Thread.currentThread();
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// read file
		ArrayList<String> list = HagUtil.loadFileIntoArrayList(wmicFile_utf8, false, "*",true);
		
		int num = 0;
		if(flag==0) {
			while(list.size()<2 && num < 3){
				HagUtil.sleep(2500);
				HagUtil.deleteFile(wmicFile_utf8,false);
				convertEncoding(wmicFile_asc,"utf-16le",wmicFile_utf8,"UTF-8");	
				list = HagUtil.loadFileIntoArrayList(wmicFile_utf8, false, "*",true);
				num++;
			}
		}else {
			return list;
		}
		if(list.get(0) == null || list.get(0).indexOf("CommandLine")<0){
			JOptionPane.showMessageDialog(null,"Convert to UTF8 format failed - please call hagay 2831", "Convert to UTF8 ",JOptionPane.ERROR_MESSAGE);
			return null ;
		}
		HagUtil.deleteFile(wmicFile_asc, false);
		HagUtil.deleteFile(wmicFile_utf8,false);
		return list;
	}
	public static final ArrayList<String>  getTaskListToFile1(	String server,
			String wmicFile_asc,
			String wmicFile_utf8,
			String cmd			) {
		// delete file
		HagUtil.deleteFile(wmicFile_asc, false);
		HagUtil.deleteFile(wmicFile_utf8,false);
		// run batch
		HagUtil.runBatch(cmd, true);
		HagUtil.sleep(2500);
		convertEncoding(wmicFile_asc,"utf-16le",wmicFile_utf8,"UTF-8");
		HagUtil.sleep(2500);
		try {
			Thread.currentThread();
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// read file
		ArrayList<String> list = HagUtil.loadFileIntoArrayList(wmicFile_utf8, false, "*",true);
		
		int num = 0;
		while(list.size()<2 && num < 3){
			HagUtil.sleep(2500);
			HagUtil.deleteFile(wmicFile_utf8,false);
			convertEncoding(wmicFile_asc,"utf-16le",wmicFile_utf8,"UTF-8");	
			list = HagUtil.loadFileIntoArrayList(wmicFile_utf8, false, "*",true);
			num++;
		}
		if(list.get(0) == null || list.get(0).indexOf("CommandLine")<0){
			JOptionPane.showMessageDialog(null,"Convert to UTF8 format failed - please call hagay 2831", "Convert to UTF8 ",JOptionPane.ERROR_MESSAGE);
			return null ;
		}
		HagUtil.deleteFile(wmicFile_asc, false);
		HagUtil.deleteFile(wmicFile_utf8,false);
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
	public static final HagStringList 	getRiServers(String type) {
		String stm3 = "select distinct bis_server from dbo.ri_environments_new where project <> 'HAGWIDTH' and status='A' order by bis_server";
		
		HagSQL hagSQL =new HagSQL();
		HagStringList ans3= hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm3);
		return ans3;
	}
}
