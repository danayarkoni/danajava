package com.hag.hagay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HagPrivate {
	public 		static final String 	before1(String user) {
		String pass 	= HagHtmlUtil.getPasswordField("pass","40","","");
		StringBuilder ans = new StringBuilder();
		ans.append("<html><body bgcolor=\"#dddddd\">");
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"hagPrivateBefore.jsp\">");
		ans.append("<table bgcolor=\"#9999bb\" cellpadding=\"5\" border =\"1\"   >");
		ans.append("<tr><td>pass</td><td>"+pass+"</td><tr>");
		ans.append("</table><br><br>");	
		ans.append("<br><br>");
		ans.append("<INPUT TYPE=SUBMIT 	name = \"SUBMIT\" 	value=\"Submit\">			</FORM>");
		ans.append("</body></html>");
		return ans.toString();
	

	}
	public 		static final String 	before2(HttpServletRequest request, HttpServletResponse response){
		String pass 	= request.getParameter("pass");
		if(!pass.equals("mashi11"))
			return HagUtil.shortHtml("Error: you are not hagay", "red", "bg");
		StringBuilder ans = new StringBuilder();
		ans.append("<html><body bgcolor=\"#dddddd\">");
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"hagPrivateAfter.jsp\">");
		ans.append("<table bgcolor=\"#9999bb\" cellpadding=\"5\" border =\"1\"   >");
		ans.append(getP1("1"));
		ans.append("</table><br><br>");		
		ans.append("<br><br>");
		ans.append("<INPUT TYPE=SUBMIT 	name = \"SUBMIT\" 	value=\"Submit\">			</FORM>");
		ans.append("</body></html>");
		return ans.toString();
	

	}
	public 		static final String 	getP1(String radNo){
		StringBuilder ans = new StringBuilder();
		String temp1="<option    value=\".\">.</option>";
		String temp2="<option    value=\"David.Hagay\">David.Hagay</option>";
		String temp3="<option    value=\"Gonen.Shoham\">Gonen.Shoham</option>";
		String temp4="<option    value=\"Danielle.Sapir\">Danielle.Sapir</option>";
		StringBuilder ans1 = new StringBuilder();
		ans1.append(temp1);
		ans1.append(temp2);
		ans1.append(temp3);
		ans1.append(temp4);
		
		String p1 	= HagHtmlUtil.getTextField("p1","40","","");
		String p2 	= HagHtmlUtil.getRiEnvSelectField("p2",ans1.toString());
		
		String	rad 	= "<input type=\"radio\" id=\"rad\" name=\"rad\" value=\""+radNo+"\" checked >";
		ans.append("<tr bgcolor=\"#ccccee\"><td>").append(rad).append(radNo).append("</td><td>");
		ans.append("<table bgcolor=\"#9999bb\" cellpadding=\"5\" border =\"1\"   >");
		ans.append("<h3>update powerpoint request owner</h3>");
		ans.append("<tr><td>req</td><td>"+p1+"</td><tr>");
		ans.append("<tr><td>new owner</td><td>"+p2+"</td><tr>");
		ans.append("</table>");
		ans.append("</td></tr>");
		ans.append("</table><br><br>");	
		return ans.toString();
	}
		
	public 	 	static final String 	after(HttpServletRequest request, HttpServletResponse response){
		String rad 	= request.getParameter("rad");
		String p1 	= request.getParameter("p1");
		String p2 	= request.getParameter("p2");
		if(rad.equals("1")) {
			return opt1(p1,p2);
		}		
		return "wrong radio";
	}
	public 	 	static final String 	opt1(String p1, String p2){
		//update powerpoint request owner
		HagSQL hagSQL = new HagSQL();
		String stm = "UPDATE dbo.req_ind_log_new SET owner='"+p2+"' WHERE ind="+ p1;
		String ans11 = hagSQL.update("SQL", "confg1", "cfg", "cfg09c", HagParam.getConfg1DB(), stm);
		String rc = "OK";
		if (!ans11.startsWith("0~"))
				 rc = "Failed";
	
		return rc+" from "+stm;
	}
}
