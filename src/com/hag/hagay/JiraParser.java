package com.hag.hagay;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class JiraParser {

	
	public 		static final HagStringList 	parse(String inputAsString,String key ,String extraDev ) {
		String ticket="";
		String fixVersion="";
		String version="";
		boolean emptyTicket = true;
		HagStringList ans = new HagStringList();
		
		//String str = convertIsTOString( hagRc, input); 
		//if(hagRc.rc==1)
			//return null;
		HagStringList itemsList = new HagStringList(inputAsString,"<item>",true);
		for(int i = 1;i<itemsList.size();i++) {
			String tempItem = itemsList.get(i);
			emptyTicket = true;
			ticket = getKey(tempItem);
			fixVersion = getFixVersion(tempItem);
			version = getVersion(tempItem);
		
			String tempFields = tempItem.substring(tempItem.indexOf("<customfields>")+14,tempItem.indexOf("</customfields>"));
			HagStringList customfieldList = new HagStringList(tempItem,"</customfield>",true);
			for(int k = 0;k<customfieldList.size()-1;k++) {
				String customfield = customfieldList.get(k);
				String customfieldname= getCustomfieldname(customfield);
			
				
				if(customfieldname.equals("IOM Release Components") 			||
						customfieldname.equals("Java Release Components") 		||
						customfieldname.equals("JavaScript Release Components") ||
						customfieldname.equals("MD Release Components") 		||
						customfieldname.equals("UTP Release Components") 		||
						customfieldname.equals("REX Release Components") 		||
						customfieldname.equals("CMD Release Components") 					){
			
					//hagVersion= getValue("fixVersion", element);
					
					//System.out.println("name:"+fieldName3);
				///	Node node4 = node3.getNextSibling();
				///	Node node5 = node4.getNextSibling();
					//String fieldName5 = node5.getTextContent();
					//System.out.println("values:"+node5.getTextContent().trim());
					//String val = node5.getTextContent().trim();
					//hagaytofix
					String flag = "NN";
					
				//	hagVersion= getValue("fixVersion", element);
					
					if(fixVersion==null){
						flag="error:fixVersion";
					}
					
					if(version==null){
						if(extraDev.equalsIgnoreCase("NO"))
							flag="error:version field in jira is empty, will be ignored - not an extraDev version";
						else
							flag="error:version";
					}
					//fixVersion = hagVersion
					//version = hagVersion1
					if(!flag.startsWith("error:")){
						if(fixVersion.equalsIgnoreCase(version))
							flag=fixVersion;
						else
							flag=version;
					}
					if(		customfieldname.equals("UTP Release Components") 		||
							customfieldname.equals("IOM Release Components") 								){
						String customfieldvalue= getCustomfieldValue(customfield);
						HagStringList customfieldValueList = new HagStringList(customfieldvalue,";",true);
						for(int m = 0;m<customfieldValueList.size();m++) {
							String tempCustomfieldValue = customfieldValueList.get(m);
							if(tempCustomfieldValue!=null && tempCustomfieldValue.trim().length()>0)
								addToOutput(ans,tempCustomfieldValue,customfieldname, ticket,key,flag);
						}
					}else {
						addToOutput(ans,customfieldname,customfieldname, ticket,key,flag);
				
					}
					emptyTicket = false;
				}
			}
			if(emptyTicket)
				addToOutput(ans,"empty ticket","emptyTicket", ticket,key,"emptyTicket");
		}
	
		return ans;
	}
	 public static String getCustomfieldValue(String str) {
		 int pos2 = str.indexOf("</customfieldvalue>");
		 if(pos2<0)
			 return null;
		 String tmp = str.substring(0,pos2);
		 String key = tmp.substring(tmp.lastIndexOf(">")+1,tmp.length());
		 return key;
	 }
	 public static String getCustomfieldname(String str) {
		 int pos2 = str.indexOf("</customfieldname>");
		 if(pos2<0)
			 return null;
		 String tmp = str.substring(0,pos2);
		 String key = tmp.substring(tmp.lastIndexOf(">")+1,tmp.length());
		 return key;
	 }
	 public static String getKey(String str) {
		 int pos2 = str.indexOf("</key>");
		 if(pos2<0)
			 return null;
		 String tmp = str.substring(0,pos2);
		 String key = tmp.substring(tmp.lastIndexOf(">")+1,tmp.length());
		 return key;
	 }
	 public static String getFixVersion(String str) {
		 int pos2 = str.indexOf("</fixVersion>");
		 if(pos2<0)
			 return null;
		 String tmp = str.substring(0,pos2);
		 String key = tmp.substring(tmp.lastIndexOf(">")+1,tmp.length());
		 return key;
	 }
	 public static String getVersion(String str) {
		 int pos2 = str.indexOf("</version>");
		 if(pos2<0)
			 return null;
		 String tmp = str.substring(0,pos2);
		 String key = tmp.substring(tmp.lastIndexOf(">")+1,tmp.length());
		 return key;
	 }
	private static void addToOutput(ArrayList<String> arr,String val,String var,String ticket,String key,String flag) {
			ticket = ticket.trim();
			var = var.trim();
			val = val.trim();
			if(key.endsWith("B")){
				if(ticket.startsWith("RI-"))
					ticket = ticket.substring(3,ticket.length()).trim();
				if(ticket.startsWith("SMTGH-"))
					ticket = ticket.substring(6,ticket.length()).trim();
			}
			val = val.replaceAll("<br/>", "");
			val = val.replaceAll("<br>", "");
			val = val.replaceAll("<BR/>", "");
			val = val.replaceAll("<BR>", "");
			val = val.replaceAll("<bR/>", "");
			val = val.replaceAll("<bR>", "");
			val = val.replaceAll("<Br/>", "");
			val = val.replaceAll("<Br>", "");
			while(val.indexOf(";;")<-1){
				val = val.replaceAll(";;", ";").trim();
			}
			String var1 = getVar(var);
			
			ArrayList<String> temp = HagUtil.splitStr2ArrayList(val,";");
			for(int i = 0 ; i < temp.size();i++)
				arr.add("INIT~"+ticket.trim()+"~"+var1+"~"+temp.get(i).trim()+"~"+flag);
		
		}  
		private static String getVar(String var) {
			if(var.equals("IOM Release Components")) 		return "IOM";
			if(var.equals("Java Release Components")) 		return "JVA";
			if(var.equals("JavaScript Release Components")) return "JVAS";
			if(var.equals("MD Release Components")) 		return "MTD";
			if(var.equals("UTP Release Components")) 		return "TSK";
			if(var.equals("REX Release Components")) 		return "RX";
			if(var.equals("CMD Release Components")) 		return "CMD";
			if(var.equals("emptyTicket")) 					return "emptyTicket";
			
			return "ERROR";
		}

		
		
}
