package com.hag.hagay;

public final class HagHtmlUtil {
	public  static String	getRiEnvSelectField(String name,String vals) {
		StringBuilder ansSB =new StringBuilder("<select name=\""+name+"\">");
		ansSB.append(vals);
		ansSB.append("</select>");	
		String ans  = ansSB.toString();
		return ans;
	}
	public  static String	getSelectField(String name,String[] vals) {
		StringBuilder ansSB =new StringBuilder("<select name=\""+name+"\">");
		for(int i = 0;i<vals.length;i++)
			ansSB.append("<option class=\"c30\" value=\"").append(vals[i]).append("\">").append(vals[i]).append("</option>");
		ansSB.append("</select>");	
		String ans  = ansSB.toString();
		return ans;
	}

	public  static String	getRiEnvSelectFieldLong(String name,String vals) {
		String envs ="<select  STYLE=\"font-family : monospace; font-size : 10pt\" id=\""+name+"\"  name=\""+name+"\"  >"+vals+"</select>";
		return envs;
	}
	public  static String	getTextField(String name,String len,String val,String disabled) {
		String ans ="<input type=\"text\" id=\""+name+"\"    name=\""+name+"\"    size=\""+len+"\"  value = \""+val+"\" "+disabled+">";
		return ans;
	}
	public  static String	getHiddenField(String name,String len,String val) {
		String ans ="<input type=\"hidden\" id=\""+name+"\"    name=\""+name+"\"    size=\""+len+"\"  value = \""+val+"\" >";
		return ans;
	}
	public  static String	getPasswordField(String name,String len,String val,String disabled) {
		String ans ="<input type=\"password\" id=\""+name+"\"    name=\""+name+"\"    size=\""+len+"\"  value = \""+val+"\" "+disabled+">";
		return ans;
	}
	
}
