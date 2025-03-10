package com.hag.hagay;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HagDebug {

	public static final  String 	gonenTestBefore(){
			HagStringList ans = new HagStringList();
			ans.add("<head><script type=\"text/javascript\">");
			ans.add("function refreshIFrame() {");
			ans.add("var interval =setInterval(\"reloadIFrame();\", 3000);");
			ans.add("}");
			ans.add("function reloadIFrame() {");
			ans.add("document.getElementById('iframe_id').src += '';");
			ans.add("}");
			ans.add("</script></head>	");
			ans.add("<body bgcolor=#eeeeee>	");
			ans.add("<FORM METHOD=POST name=\"Form\" ACTION=\"gonenTest.jsp\">");
			ans.add("<h2>run remote command:</h2>");
			ans.add("<br><br>");
			ans.add("server:");
			ans.add("<br>");
			//ans.add("<input type=\"text\" name=\"server\" id=\"server\" value = \"\" maxlength=\"40\" size=\"40>\"");
			ans.add("<select name=\"server\" id=\"server\""+getServer()+"</select>");  
			ans.add("<br><br><br>");
			ans.add("command to run:");
			ans.add("<br>");
			ans.add("<textarea id=cmdTextArea name=cmdTextArea rows=\"10\" cols=\"150\">");
			//ans.add("");
			ans.add("</textarea>");
			ans.add("<br><br>");
			ans.add("<br><INPUT TYPE=SUBMIT value=\"run the command  \" onclick=\"document.getElementById('loader1').style.display = ''; this.style.display = 'none'\" ></FORM>");
			
			return ans.convertToString(" ");
			}
	public String 	gonenTestAfter(HttpServletRequest request, HttpServletResponse response){
		String cmdTextArea 	= request.getParameter("cmdTextArea");	
		String command3 = HagUtil.cfgMenuLoc+"\\bin\\bat\\runcmd.bat " + cmdTextArea ;
		
		String server 	= request.getParameter("server");	
		server=server.trim();
		HagClient hagClient= new HagClient();
		HagStringList ans4List = hagClient.runNotSudo(server, command3);
		String ans4 = ans4List.convertToString("<br>");
		return ans4;
		}
	public  static final String 	getServer() {
        HagSQL hagSQL = new HagSQL();
        //win local
      
        String stm = "SELECT DISTINCT bis_server  FROM "+HagParam.getConfg1DB()+".[dbo].[ri_environments_new] where project <> 'HAGWIDTH' 	ORDER BY [bis_server]";
        HagStringList ans = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm);
        HagStringList ans1 = new HagStringList();
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
	public 		static final String 	datePrompt_before() {
		String[] usersArr1 = new String[356];
		//String startDate =HagUtil.getDateTime("yyyy/MM/dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd  -  EEEEE");
	//	usersArr1[0] = startDate;
		Calendar c = Calendar.getInstance();
	//	c.setTime(sdf.parse(dt));
		for(int i = 0 ;i < usersArr1.length;i++) {
			
			String dt = sdf.format(c.getTime());  // dt is now the new date
			usersArr1[i] = dt;
			c.add(Calendar.DATE, 1);  // number of days to add
		}
		String usersArr 	= HagHtmlUtil.getSelectField("usersArr",usersArr1);
		StringBuilder ans = new StringBuilder();
		ans.append("<html>");
		ans.append("<h2> debug </h2>");
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"debug2_after.jsp\">");
		ans.append("<table bgcolor=\"#aaaac\" cellpadding=\"5\" border =\"1\"   >");
		ans.append("<tr bgcolor=\"#9999bb\"> <td>Title</td><td>Value</td><td>Note</td><tr>");
	
		ans.append("<tr><td>like user</td><td>"+usersArr+"</td><td>select like which user</td><tr>");
		ans.append("</table>");
	
		ans.append("</body></html>");
		return ans.toString();
	

	}
	public 		static final String 	datePrompt_after(HttpServletRequest request, HttpServletResponse response) {
		String cmdTextArea 	= request.getParameter("cmdTextArea");
		
		String sentBy 	= request.getParameter("sentBy").trim();
	
		//ArrayList<String>  ans1=HagUtil.runCmdWithErr(cmdTextArea, true);
		String server = "orsayserver";
		String aaa =HagUtil.runNotSudo9(server, cmdTextArea, "sapiens\\cfg-burner");
		//System.out.println(aaa);
		StringBuilder ans = new StringBuilder();
		ans.append("command:<br>").append(cmdTextArea);
		ans.append("<br><br><br><br>results:<br>");
		
		ans.append("<p><span class=\"nowrap\">"+aaa+"</span></p>");
	    System.out.println("11111111111111111");
	    System.out.println(aaa);
	    System.out.println("22222222222222222");
		//ans.append(aaa);
	//for(int i = 0;i<ans1.size();i++)
		//ans.append(ans1.get(i)).append("<br>");
		return ans.toString();
	

	}
	public 		static final String 	powershellDebug_before(String user,String act) {

		StringBuilder ans = new StringBuilder();
		ans.append("<html><body bgcolor=\"#dddddd\">");
		ans.append("<h2>powershell debug </h2>");
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"hagDebug_powerShell_before.jsp\">");
	
		//ans.append("<input type=\"hidden\" name=\"sentBy\" id=\"sentBy\" value = \""+user+"\" maxlength=\"140\" size=\"140\">");
		//ans.append("<input type=\"hidden\" name=\"act\" id=\"act\" value = \""+act+"\" maxlength=\"140\" size=\"140\">");
		
		ans.append("<textarea id=cmdTextArea name=cmdTextArea rows=\"10\" cols=\"150\">");
		
		ans.append("</textarea>");
		
		ans.append("<br><br>");
			ans.append("<INPUT TYPE=SUBMIT 	name = \"SUBMIT\" 	value=\"Submit\">			</FORM>");
		ans.append("</table>");
	
		ans.append("</body></html>");
		return ans.toString();
	

	}
	public 		static final String 	powershellDebug_after(HttpServletRequest request, HttpServletResponse response) {
		String cmdTextArea 	= request.getParameter("cmdTextArea");
		
		String sentBy 	= request.getParameter("sentBy").trim();
	
		//ArrayList<String>  ans1=HagUtil.runCmdWithErr(cmdTextArea, true);
		String server = "orsayserver";
		String aaa =HagUtil.runNotSudo9(server, cmdTextArea, "sapiens\\cfg-burner");
		//System.out.println(aaa);
		StringBuilder ans = new StringBuilder();
		ans.append("command:<br>").append(cmdTextArea);
		ans.append("<br><br><br><br>results:<br>");
		
		ans.append("<p><span class=\"nowrap\">"+aaa+"</span></p>");
	    System.out.println("11111111111111111");
	    System.out.println(aaa);
	    System.out.println("22222222222222222");
		//ans.append(aaa);
	//for(int i = 0;i<ans1.size();i++)
		//ans.append(ans1.get(i)).append("<br>");
		return ans.toString();
	

	}
}
