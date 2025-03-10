package com.hag.hagay;
/*
 * Method: getIssuesByJql
Parameters : Jql (String) 
Response : list of issues(JSON)
Example : http://ridevblp05:8089/jira/getIssuesByJql?jql=project=AIG


Method: getIssuesByProjectAndVersionAndStatus
Parameters : project (String), status (String) , version (String) 
Response : list of issues with content(JSON)
Example : http://ridevblp05:8089/jira/getIssuesByProjectAndVersionAndStatus?project=AIG&status=INSTALLED%20IN%20INT&version=08.01M01


Method: getIssuesKeysByProjectAndVersionAndStatus
Parameters : project (String), status (String) , version (String) 
Response : list of issues keys (JSON)
Example : http://ridevblp05:8089/jira/getIssuesKeysByProjectAndVersionAndStatus?project=AIG&status=IN%20TEST&version=08.01M01


Method: updateIssueStatusById
Parameters : id (String) , status (String)
Response : update status (String)
Example : http://ridevblp05:8089/jira/updateIssueStatusById?id=AIG-67&status=IN%20TEST


Method: getVersionStatus
Parameters : projectKey (String) , version (String)
Response : status (String)
Example : http://ridevblp05:8089/jira/getVersionStatus?projectKey=AIG&version=08.01M01



status parameter valid values:
•	NONE
•	REQUEST FOR INT
•	INSTALLED IN INT
•	IN TEST

*/

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.X509Certificate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class HagJiraNew {
	 public static InputStream readFrom(String urlStr, String postStr)
	    	    throws MalformedURLException, IOException
	    	{
	    	final HagStringList aaa= new HagStringList();
	    	//
	    	aaa.add("start");
	    	TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
	    	      public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	    	    	 	aaa.add("1");
	    	        return null;
	    	      }

	    	      public void checkClientTrusted(X509Certificate[] certs, String authType) {
	    	    	  aaa.add("2");
	    	      }

	    	      public void checkServerTrusted(X509Certificate[] certs, String authType) {
	    	    	  aaa.add("3");
	    	      }

				public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {
					aaa.add("4");
					// TODO Auto-generated method stub
					
				}

				public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {
					aaa.add("5");
					// TODO Auto-generated method stub
					
				}
	    	    } };
	    	aaa.add("6");
	    	    SSLContext sc;
	    	    aaa.add("7");
				try {
					aaa.add("8");
					sc = SSLContext.getInstance("SSL");
					aaa.add("9");
					sc.init(null, trustAllCerts, new java.security.SecureRandom());
					aaa.add("10");
				  HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
					aaa.add("11");
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					aaa.add("12");
					e.printStackTrace();
				} catch (KeyManagementException e) {
					aaa.add("13");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				aaa.add("14");
	    	 
	    	//
	    	
				//System.out.println("urlStr="+urlStr);
	    	    URLConnection conn = new URL(urlStr).openConnection();
	    		aaa.add("15");
	    	    conn.setDoInput(true);
	    		aaa.add("16");
	    	    if (postStr != null && postStr.length() > 0) {
	    	    	aaa.add("17");
	    	        conn.setDoOutput(true);
	    	    	aaa.add("18");
	    	        DataOutputStream output =  new DataOutputStream(conn.getOutputStream());
	    	    	aaa.add("19");
	    	        output.writeBytes(postStr);
	    	    	aaa.add("20");
	    	        output.flush();
	    	    	aaa.add("21");
	    	        output.close();
	    	    	aaa.add("22");
	    	    }
	    		aaa.add("23");
	    		aaa.add(urlStr);
	    		aaa.add("24");
	    		aaa.add(postStr);
	    		
	    		aaa.writeToFile("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\debug\\1.txt", true);
	    	    return conn.getInputStream();
	    	}
	 public static InputStream get(String urlStr) throws Exception {
	        return readFrom(urlStr, null);
	    }
	 public static InputStream httpReq(String ver,String verJira,String status,String val,String ticket){
		//String aa="Aa";
		  //String key99="AIG";
			String key99="RI%20Product%20(SCRUM)";
		// String key99="RI Product (SCRUM)";
	    	//String  jiraServer=
			//String  url = jiraServer+"/sr/jira.issueviews:searchrequest-xml/temp/SearchRequest.xml?jqlQuery=project+%3D+%22RI+Product+(SCRUM)%22+and+status+%3D+\""+status+"\"+and+fixVersion+in+%28%22"+verJira+"%22%29&tempMax=1000&os_username=cfg-burner&os_password=gon09c";
	    String val1 ="aa";
	    String  url ="aaaa";
	    if(val.equals("get version status"))
	    	url ="http://ridevblp05:8089/jira/getVersionStatus?projectKey="+key99+"&version="+ver;
	   
	    //if(val.equals("get version status"))
	    //	url ="http://ridevblp05:8089/jira/getVersionStatus?version="+ver;
	 
	    
	    if(val.equals("move ticket to 'REQUEST FOR INT'"))
	    	url ="http://ridevblp05:8089/jira/updateIssueStatusById?id="+ticket+"&status=REQUEST%20FOR%20INT";
	    if(val.equals("move ticket to 'INSTALLED IN INT'"))
	    	url ="http://ridevblp05:8089/jira/updateIssueStatusById?id="+ticket+"&status=INSTALLED%20IN%20INT";
	    if(val.equals("move ticket to 'IN TEST'"))
	    	url ="http://ridevblp05:8089/jira/updateIssueStatusById?id="+ticket+"&status=IN%20TEST";
	   
	    if(val.equals("REQUEST FOR INT"))
	   		//url ="http://ridevblp05:8089/jira/getIssuesByProjectAndVersionAndStatus?project="+key99+"&status=REQUEST%20FOR%20INT&version="+ver;
	    	url ="http://ridevblp05:8089/jira/getIssuesByJql?jql=fixVersion="+ver+"%20and%20'RI%20Integration'='REQUEST%20FOR%20INT'";
	    if(val.equals("INSTALLED IN INT"))
	    	//url ="http://ridevblp05:8089/jira/getIssuesByProjectAndVersionAndStatus?project="+key99+"&status=INSTALLED%20IN%20INT&version="+ver;
	    	url ="http://ridevblp05:8089/jira/getIssuesByJql?jql=fixVersion="+ver+"%20and%20'RI Integration'='INSTALLED%20IN%20INT'";
	   // if(val.equals("REQUEST FOR INT"))
	   //		url ="http://ridevblp05:8089/jira/getIssuesByProjectAndVersionAndStatus?status=REQUEST%20FOR%20INT&version="+ver;
	   // if(val.equals("INSTALLED IN INT"))
	    //	url ="http://ridevblp05:8089/jira/getIssuesByProjectAndVersionAndStatus?status=INSTALLED%20IN%20INT&version="+ver;
	
	    try {
			   		InputStream is = get(url);
									      
					return  is ;
			    } catch (Exception e) {
			    	e.printStackTrace();
			    	return null;
			    }
		}
	 
	 
		private  static String getValue(String tag, Element element) {
			//	HagUtil.p("aaa"+tag);
				NodeList nodes=null;
				try{
					nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
				}catch(NullPointerException npe){
					return ("NPE");
				}
				Node node = (Node) nodes.item(0);
				return node.getNodeValue();
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
		private static void addToArr(ArrayList<String> arr,String val,String var,String ticket,String key,String flag) {
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
	 public static String sendReq(String ver,String key,String verJira,String status,String extraDev,String val,String ticket){
	    	String hagVersion;
	    	String hagVersion1;
	    //	int hagFromR6=0;
	    //	int hagFromD1=0;
	    	
	    
	    	
			InputStream input  = httpReq(ver,verJira,status, val,ticket);
			if(input==null)
				return null;
			
			StringBuilder sb = new StringBuilder();
			try {
				for (int ch; (ch = input.read()) != -1; ) {
				    sb.append((char) ch);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String aa= sb.toString();
			
			return sb.toString();
			 
			/* 
			
			try {
			
				
			//File stocks = new File("d:\\temp\\SearchRequest.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			//Document doc = dBuilder.parse(stocks);
			Document doc = dBuilder.parse(input);
			//Document doc = dBuilder.parse(new InputSource(new InputStreamReader(input, "UTF-8")));
			doc.getDocumentElement().normalize();
			
			NodeList nodes = doc.getElementsByTagName("item");
			HagStringList arr = new HagStringList();
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					String ticket = getValue("key", element);
					boolean emptyTicket = true;
					NodeList nodes1 = (NodeList) node.getChildNodes();
					for (int k = 0; k < nodes1.getLength(); k++) {
						Node node1 = nodes1.item(k);
						String name1 = node1.getNodeName();
						if(name1.equals("customfields")){
							NodeList nodes2 = (NodeList) node1.getChildNodes();
							for (int h = 0; h < nodes2.getLength(); h++) {
								Node node2 = nodes2.item(h);
								String name2 = node2.getNodeName();
								if(name2.equals("customfield")){
									NodeList nodes3 = (NodeList) node2.getChildNodes(); //customfield 
								
									for (int p = 0; p < nodes3.getLength(); p++) {
										//boolean flag = false;
										Node node3 = nodes3.item(p);
										String name3 = node3.getNodeName();
										if (node3.getNodeType() == Node.ELEMENT_NODE) {
											if(name3.equals("customfieldname")){
												String fieldName3 = node3.getTextContent();
												if(fieldName3.equals("IOM Release Components") ||
														fieldName3.equals("Java Release Components") ||
														fieldName3.equals("JavaScript Release Components") ||
														fieldName3.equals("MD Release Components") ||
														fieldName3.equals("UTP Release Components") ||
														fieldName3.equals("REX Release Components") ||
														fieldName3.equals("CMD Release Components") 
														
														){
												
													//System.out.println("name:"+fieldName3);
													Node node4 = node3.getNextSibling();
													Node node5 = node4.getNextSibling();
													//String fieldName5 = node5.getTextContent();
													//System.out.println("values:"+node5.getTextContent().trim());
													String val = node5.getTextContent().trim();
													//hagaytofix
													String flag = "NN";
													
													hagVersion= getValue("fixVersion", element);
													
													if(hagVersion.equals("NPE")){
														flag="error:fixVersion";
													}
													hagVersion1= getValue("version", element);
													if(hagVersion1.equals("NPE")){
														if(extraDev.equalsIgnoreCase("NO"))
															flag="error:version field in jira is empty, will be ignored - not an extraDev version";
														else
															flag="error:version";
													}
													if(!flag.startsWith("error:")){
														if(hagVersion.equalsIgnoreCase(hagVersion1))
															flag=hagVersion;
														else
															flag=hagVersion1;
													}
													addToArr(arr,val,fieldName3, ticket,key,flag);
													emptyTicket = false;
												}
											}
										}
									}
								}
							}
						}
					}
					if(emptyTicket)
						addToArr(arr,"empty ticket","emptyTicket", ticket,key,"emptyTicket");
				}
				
			}
			//for(int i = 0 ; i < arr.size();i++){
			//	String temp = arr.get(i);
			//	System.out.println(temp);
			//}
			//HagUtil.p(arr);
			return arr;
		} catch (Exception ex) {
			String aa= ex.getMessage();
			
			ex.printStackTrace();
			return new HagStringList();
		}*/
			
		}   
	 public 		static final String 	before(String user,String act) {
		 String options = "<option value=\".\">.</option>"+
				 "<option value=\"get version status\">get version status</option>"+
				 "<option value=\"get 'REQUEST FOR INT' tickets\">get 'REQUEST FOR INT' tickets</option>"+
				 "<option value=\"get 'INSTALLED IN INT' tickets\">get 'INSTALLED IN INT' tickets</option>"+
				 "<option value=\"get 'REQUEST FOR INT' components\">get 'REQUEST FOR INT' components</option>"+
				 "<option value=\"get 'INSTALLED IN INT' components\">get 'INSTALLED IN INT' components</option>"+
				 "<option value=\"move ticket to 'INSTALLED IN INT'\">move ticket to 'INSTALLED IN INT'</option>"+
				 "<option value=\"move ticket to 'IN TEST'\">move ticket to 'IN TEST'</option>"	 +
		 		 "<option value=\"move ticket to 'REQUEST FOR INT'\">move ticket to 'REQUEST FOR INT'</option>"	 ;
		String ticket1 =getTextField( "ticket1","50","TFS-171","");
		String ticket2 =getTextField( "ticket2","50","TFS-171","");
		String ticket3 =getTextField( "ticket3","50","TFS-171","");
		String version1 =getTextField( "version1","50","Version%201.0","");
		String version2 =getTextField( "version2","50","Version%201.0","");
		String version3 =getTextField( "version3","50","Version%201.0","");
		String version4 =getTextField( "version4","50","Version%201.0","");
		String version5 =getTextField( "version5","50","Version%201.0","");
		String act1 			= getRiEnvSelectField("act1",options);
			StringBuilder ans = new StringBuilder();
			ans.append("<html><body bgcolor=\"#dddddd\">");
			ans.append("<h2>new jira test </h2>");
			ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"newJiraTest.jsp\">");
			
			ans.append("<input type=\"hidden\" name=\"sentBy\" id=\"sentBy\" value = \""+user+"\" maxlength=\"140\" size=\"140\">");
			ans.append("<input type=\"hidden\" name=\"act\" id=\"act\" value = \""+act+"\" maxlength=\"140\" size=\"140\">");
			ans.append("<h4>fill the needed values and select the option</h4>");
			ans.append(act1).append("<br><br>");
		//ans.append("<font color=#ff0000><b><u>Select one line:</b></u></font><br><br>");
			ans.append("<table bgcolor=\"#aaaaaa\" cellpadding=\"5\" border =\"1\"   >");
			ans.append("<tr bgcolor=\"#9999bb\"><td>title</td><td>parameters</td></tr>");
			ans.append("<tr><td>get version status</td><td>version: "+version1+"</td></tr>");
			ans.append("<tr><td>get 'REQUEST FOR INT' tickets</td><td>version: "+version2+"</td></tr>");
			ans.append("<tr><td>get 'INSTALLED IN INT'  tickets</td><td>version: "+version3+"</td></tr>");
			ans.append("<tr><td>get 'REQUEST FOR INT' components</td><td>version: "+version4+"</td></tr>");
			ans.append("<tr><td>get 'INSTALLED IN INT' components</td><td>version: "+version5+"</td></tr>");
			ans.append("<tr><td>move ticket to 'INSTALLED IN INT'</td><td>ticket: "+ticket1+"</td></tr>");
			ans.append("<tr><td>move ticket to 'IN TEST'</td><td>ticket: "+ticket2+"</td></tr>");
			ans.append("<tr><td>move ticket to 'REQUEST FOR INT'</td><td>ticket: "+ticket3+"</td></tr>");
		
			ans.append("</table>");
			
			ans.append("<br><INPUT TYPE=SUBMIT 	name = \"SUBMIT\" 	value=\"Submit\">			</FORM>");
			
		
			ans.append("</body></html>");
			return ans.toString();
		

		}
	 public 		static final String 	after(HttpServletRequest request, HttpServletResponse response) {
		 String act1 	= request.getParameter("act1");
		 String version1 	= request.getParameter("version1");
		 String version2 	= request.getParameter("version2");
		 String version3 	= request.getParameter("version3");
		 String version4 	= request.getParameter("version4");
		 String version5 	= request.getParameter("version5");
		 String ticket1 	= request.getParameter("ticket1");
		 String ticket2 	= request.getParameter("ticket2");
		 String ticket3 	= request.getParameter("ticket3");
		 if(act1.equals("get version status"))
			 return getIssuesByJql("string","get version status",false,version1,null);
		 if(act1.equals("get 'REQUEST FOR INT' tickets"))
			 return getIssuesByJql("json","REQUEST FOR INT",false,version2,null);
		 if(act1.equals("get 'INSTALLED IN INT' tickets"))
			 return getIssuesByJql("json","INSTALLED IN INT",false,version3,null);
		 if(act1.equals("get 'REQUEST FOR INT' components"))
			 return getIssuesByJql("json","REQUEST FOR INT",true,version4,null);
		 if(act1.equals("get 'INSTALLED IN INT' components"))
			 return getIssuesByJql("json","INSTALLED IN INT",true,version5,null);
		 if(act1.equals("move ticket to 'INSTALLED IN INT'"))
			 return getIssuesByJql("string","move ticket to 'INSTALLED IN INT'",true,null,ticket1);
		
		 if(act1.equals("move ticket to 'IN TEST'"))
			 return getIssuesByJql("string","move ticket to 'IN TEST'",true,null,ticket2);
		 if(act1.equals("move ticket to 'REQUEST FOR INT'"))
			 return getIssuesByJql("string","move ticket to 'REQUEST FOR INT'",true,null,ticket3);
		 
		return "not found";
	 }
	 private static 	String			getRiEnvSelectField(String name,String vals) {
			StringBuilder ansSB =new StringBuilder("<select name=\""+name+"\">");
			ansSB.append(vals);
			ansSB.append("</select>");	
			String ans  = ansSB.toString();
			return ans;
		}
	
	private static 	String			getTextField(String name,String len,String val,String disabled) {
			String ans ="<input type=\"text\" id=\""+name+"\"    name=\""+name+"\"    size=\""+len+"\"  value = \""+val+"\" "+disabled+">";
			
			return ans;
		}
	 public 		static final String 	getIssuesByJql(String type,String val,boolean withComponents,String ver,String ticket) {
	//	String req = "http://ridevblp05:8089/jira/getIssuesByJql?jql=project=AIG";
		String ans2 = sendReq(ver,"RISTM2B","jiraVer","w2","extraDev"	, val,ticket);
		if(ans2==null)
			return "not found1";
		if(type.equals("string"))
			return ans2;
		//String ans2S= ans2.convertToString("<br>");
		ArrayList<HagStringList> arrList=null;
		if(withComponents)
			arrList= loadJsonWithComponents( ans2);
		else
			arrList= loadJsonWithoutComponents( ans2);
		HagStringList ans = new HagStringList();
		 for (int i = 0; i < arrList.size(); i++) {
				HagStringList temp = arrList.get(i);
				ans.append(temp);
		 }
		String ans2S= ans.convertToString("<br>");
		return ans2S;
	}
	 public static ArrayList<HagStringList> loadJsonWithComponents                      (String jsonStr) {
		 
		 ArrayList<HagStringList> arrList = new ArrayList<HagStringList>();
		 jsonStr=" {\"jira\": "+jsonStr+"  }";
		
	     
	        JSONArray jsonarray1 = null;
	        JSONObject  jsonobject1 = null;
	        try {
	            jsonobject1 = new JSONObject(jsonStr);
	            jsonarray1 = jsonobject1.getJSONArray("jira");
	            //loop tickets
	            for (int i = 0; i < jsonarray1.length(); i++) {
	            	 HagStringList list = new HagStringList();
	                JSONObject jsonobject2 = jsonarray1.getJSONObject(i);
	                String key          = jsonobject2.getString("key");
	            
	                JSONArray jsonarray2 = jsonobject2.getJSONArray("issueFields");
	                //loop fixVersions
	                JSONArray jsonarray21 = jsonobject2.getJSONArray("fixVersions");
	                JSONObject jsonobject21 = jsonarray21.getJSONObject(0);
	                String name21          = jsonobject21.getString("name");
	                //loop affectedVersions
	                JSONArray jsonarray22 = jsonobject2.getJSONArray("affectedVersions");
	                JSONObject jsonobject22 = jsonarray22.getJSONObject(0);
	                String name22          = jsonobject22.getString("name");
	                //loop components
	                for (int k = 0; k < jsonarray2.length(); k++) {
		                JSONObject jsonobject3 = jsonarray2.getJSONObject(k);
		                String name          = jsonobject3.getString("name");
		                if(		name.equals("RI IOM Release Components") 		||
		                		name.equals("RI Java Release Components") 		||
		                		name.equals("RI JavaScript Release Components") ||
		                		name.equals("RI MD Release Components") 		||
		                		name.equals("RI UTP Release Components")		||
		                		name.equals("RI REX Release Components") 		||
		                		name.equals("RI CMD Release Components") 		||
		                   		name.equals("RI emptyTicket")             		){
		                	String value          = jsonobject3.getString("value");
		                	if(value!=null && !value.equals("null")) {
		                		HagStringList list1 =getValsLines ( value, key, name,name21,name22);
		                		list.append(list1);
		                	}
		                }
	                }
	                arrList.add(list);
	            }
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	        return arrList;
	    }
	 public static ArrayList<HagStringList> loadJsonWithoutComponents                      (String jsonStr) {
		 
		 ArrayList<HagStringList> arrList = new ArrayList<HagStringList>();
		 jsonStr=" {\"jira\": "+jsonStr+"  }";
		
	     
	        JSONArray jsonarray1 = null;
	        JSONObject  jsonobject1 = null;
	        try {
	            jsonobject1 = new JSONObject(jsonStr);
	            jsonarray1 = jsonobject1.getJSONArray("jira");
	            //loop tickets
	            
	            for (int i = 0; i < jsonarray1.length(); i++) {
	            	
	            	 HagStringList list = new HagStringList();
	                JSONObject jsonobject2 = jsonarray1.getJSONObject(i);
	                String key          = jsonobject2.getString("key");
	                HagStringList list1 =new HagStringList ( );
	                list1.add(key);
	            	list.append(list1);
	            	arrList.add(list);
	            
	                
	            }
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	        return arrList;
	    }
	 public static HagStringList getValsLines (String value,String key,String name,String fixVersions,String affectedVersions) {
		 HagStringList valuesList = new HagStringList(value,",",true);
		 HagStringList ans = new HagStringList();
    	 for (int m = 0; m < valuesList.size(); m++) {
    		 String val = valuesList.get(m);
    		 String line=key+"~!~"+ getShortName(name)+"~!~"+val+"~!~"+fixVersions+"~!~"+affectedVersions;
    		 ans.add(line);
    	 }
    	 return ans;
	 }
	 public static String getShortName (String name) {
			if(name.equals("RI IOM Release Components")) 		return "IOM";
			if(name.equals("RI Java Release Components")) 		return "JVA";
			if(name.equals("RI JavaScript Release Components")) return "JVAS";
			if(name.equals("RI MD Release Components")) 		return "MTD";
			if(name.equals("RI UTP Release Components")) 		return "TSK";
			if(name.equals("RI REX Release Components")) 		return "RX";
			if(name.equals("RI CMD Release Components")) 		return "CMD";
			if(name.equals("RI emptyTicket")) 					return "emptyTicket";
			return "no Ri components";
	 }	
	 	
}
