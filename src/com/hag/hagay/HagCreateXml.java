package com.hag.hagay;

public class HagCreateXml {
	public static final String 	createXml_before(String cfgMenuUser,String blpUser) {
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
	
}
