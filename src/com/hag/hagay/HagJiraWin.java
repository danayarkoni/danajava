package com.hag.hagay;



//get version status
//java HagJira  "RISTM1" "SMTGH"    "06.02M01"

//get components in request for integration
//java HagJira  RISTM2   "06.02M01" "QRAPRQA"
//java HagJira  RISTM2B  "06.02M01" "QRAPRQA"

//move ticket to install in integration
//java -cp /cfgri/LINKTODEV/hagjira:/cfgri/LINKTODEV/hagjira/jar/jt400Native.jar HagJira  RISTM3   "SMTGH-29"  "Install In QA"
//java -cp /cfgri/LINKTODEV/hagjira:/cfgri/LINKTODEV/hagjira/jar/jt400Native.jar HagJira  RISTM3B  "29"        "Install In QA"
//java -cp /cfgri/LINKTODEV/hagjira:/cfgri/LINKTODEV/hagjira/jar/jt400Native.jar HagJira  RISTM3   "SMTGH-29"  "TOINTEG"
//java -cp /cfgri/LINKTODEV/hagjira:/cfgri/LINKTODEV/hagjira/jar/jt400Native.jar HagJira  RISTM3B  "29"        "TOINTEG"

//move ticket to qa
//java -cp /cfgri/LINKTODEV/hagjira:/cfgri/LINKTODEV/hagjira/jar/jt400Native.jar HagJira  RISTM3   "SMTGH-29"  "QRIFIXM"
//java -cp /cfgri/LINKTODEV/hagjira:/cfgri/LINKTODEV/hagjira/jar/jt400Native.jar HagJira  RISTM3B  "29"        "QRIFIXM"


//get all tickets in integration per version
//java HagJira  RISTM4   "06.02M01"  "ININTEG"
//java HagJira  RISTM4B  "06.02M01"  "ININTEG"




import java.util.ArrayList;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.io.*;   

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.X509Certificate;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class HagJiraWin {
	/*
	String connectU1		=	"RIGONEN1";
	String connectP		=	"GON09C";
	String schema 		= 	"CFG";
	String server 		=	"CLIO.sapiens.com";
	*/
	
	//String pName ="SMTGH";
	String pName ="RI";
	String xslFile  	= "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\list\\stm2.xsl";
	String typesFile    = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\list\\types.txt";
	String reqFile  	= "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\list\\req.txt";
		
	String tempServer = "RI3QA";
	//String  jiraServer = "https://jiraapp.sapiens.com";
	
	
		

				
	
	
	
	////String  jiraServer = "http://emrh64-v6:8080/jira";
	
	
	
	//String jiraServer = "https://209.88.187.37";
	//String jiraServer = "https://jiraapp-test2.sapiens.com";
	//String jiraServer = "https://jiraapp.sapiens.com";
	
	//final static String proj = "SMT~Test~GH";
	//final static String proj = "SLAUAT";
	
	public HagJiraWin(){
	}
	/*
	public static void main( String[] args ){
		int num=3;
		if(args.length < 1 ){
 		System.out.println("no args found");
 		return;
 	}
		if(args.length != num ){
 		System.out.println("wrong number of args");
 		return;
 	}
 	
 	HagJiraWin hagJira = new HagJiraWin();
 	String w0 = args[0];
 	String w1 = args[1];
		String w2 = args[2];
		w1=w1.replace(' ', '!');
		w2=w2.replace(' ', '!');
		System.out.println("bbb="+w2);

		w2 = hagJira.replaceFromReqFile(w2);
		System.out.println("aaa="+w2);
		
		System.out.println("Request="+w0+","+w1+","+w2);

		if(w0.equals("RISTM1")){
			ArrayList<String> ans1 = hagJira.runNotSudo("RI2QA","\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\jiraApi\\1.bat "+w1+" " +w2);
 		hagJira.afterRistm1(w1,w2,ans1);
 	}else if(w0.equals("RISTM2") || w0.equals("RISTM2B")){
  		ArrayList<String> ans1 = hagJira.runNotSudo("RI2QA","\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\jiraApi\\2.bat "+w1+" "+w2);
  		hagJira.afterRistm2(w0,w1,w2, ans1);
 	}else if(w0.equals("RISTM3") || w0.equals("RISTM3B")){
 		if(w0.equals("RISTM3B")) 	w1="SMTGH-"+w1;
 		ArrayList<String> ans1 = hagJira.runNotSudo("RI2QA","\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\jiraApi\\3.bat "+w1+" "+w2);
 		hagJira.afterRistm3(w1,w2, ans1);
 	}else if(w0.equals("RISTM4")|| w0.equals("RISTM4B")){
 		ArrayList<String> ans1 = hagJira.runNotSudo("RI2QA","\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\jiraApi\\4.bat "+w1+" "+w2);
			hagJira.afterRistm4(w0,w1,w2, ans1);
 	}else{
 		System.out.println("wrong key arg");
 		return;
 	}
		
		
	  }
	*/
	
	
	
	
	public String rismt1(String ver,String jiraVer){
		String ans =HagUtil.runBatch("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\jiraApi\\1.bat "+pName+" " +jiraVer,true);
		return ans;
	}
	
	public HagStringList rismt2(String ver,String jiraVer,String extraDev	){
		String w2 = "QRAPRQA";

		w2=w2.replace(' ', '!');
		w2 = replaceFromReqFile(w2);

		//get components
		HagStringList ans2 = sendReq(ver,"RISTM2B",jiraVer,w2,extraDev	);
		return ans2;
		
	}
	
	public ArrayList<String> rismt3(String ver,ArrayList<String> tickets,String queue1){
	//	if(ver.equals("0980"))
	//		 pName ="SMTGH";
		ArrayList<String> ans = new ArrayList<String>();
		String queue = replaceFromReqFile(queue1);
		for(int i = 0 ; i<tickets.size();i++){
			String temp = tickets.get(i);
			
			String ans11 =HagUtil.runBatch( "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\jiraApi\\3.bat "+pName+"-"+temp+" "+queue, true);
			if(ans11.indexOf("false") > -1)
				ans.add("1~"+temp);
			else if(ans11.indexOf("true") > -1)
				ans.add("0~"+temp);
			else
				ans.add("2~"+temp);
		}
		return ans;
	}
	public ArrayList<String> rismt4(String ver,String verJira , String queue1){
		//if(ver.equals("0980"))
		//	 pName ="SMTGH";
		String queue = replaceFromReqFile(queue1);
		//HagClient hagClient = new HagClient();
		//ArrayList<String> ans1 = hagClient.runNotSudo(tempServer,"\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\jiraApi\\4.bat "+verJira+" "+queue);
		//HagUtil.p(ans1);
		//afterRistm4("a",verJira,queue, ans1);
		ArrayList<String> list = sendReq4(ver,"RISTM4B",verJira, queue);
		return list;

	}

	/*
	public void checkComponernts(String ver,ArrayList<String> ans2 ,String iomFolder){
		//"0~check components to release";
		for(int i = 0 ; i < ans2.size();i++){
			String temp = ans2.get(i);
			String ticket= HagUtil.getWord0(temp,"~",0,true);
			String type= HagUtil.getWord0(temp,"~",1,true);
			String name= HagUtil.getWord0(temp,"~",2,true);
			ArrayList<String> ww= new ArrayList<String>();
			ww.add("0");
			ww.add("no checks");
			if(type.equals("TSK")) {
				ww = checkComponerntsTask(ver,name);
				//if(!ww.get(0).equals("0"))
				//	ans = "1~check components to release";

			}
			if(type.equals("IOM")) {
				ww = checkComponerntsIom(iomFolder,name);
				//if(!ww.get(0).equals("0"))
				//	ans = "1~check components to release";

			}
			String pre = HagUtil.left(ww.get(0)+"~"+ticket+"~"+type+"~"+name," ", 50,".");
			ans2.set(i,pre+"-"+ww.get(1));
		}
	}
	
	public ArrayList<String> checkComponerntsTask(String ver,String task ){
		 HagSQL hagSQL = new HagSQL();
		 String stm = "SELECT STATUS FROM dbo.RITASKS"+ver+" WHERE TASKNO='"+task+"'";
	
		 ArrayList<String> ans = hagSQL.select1col("SQL", "confg1", "cfg","cfg09c", HagParam.getConfg1DB(), "RITASKS", stm);
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
			ans1.add("task status = OPN.");
			return  ans1;
		 }
		 
		ans1.add("0");
		ans1.add("task status = CLS.");
		return  ans1;
		 
	}	
	public ArrayList<String> checkComponerntsIom(String iomFolder,String iom ){
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
	*/
	public String replaceFromReqFile(String org){
		HagStringList reqList = new HagStringList(reqFile,true,"*",false);
		String req = getType(reqList,org);
		if(req==null) 
			req=""+org;
		return req;
	}
	public void afterRistm1(String arg1,String arg2,ArrayList<String> ans1 ){
		String ans2 = "'"+arg1+"','"+arg2+"',";
		String temp = ans1.get(ans1.size()-1);
		if(temp.indexOf("NOT RELEASED") > -1)
			ans2=ans2+"'NOT_RELEASED'";
		else if(temp.indexOf("RELEASED") > -1)
			ans2=ans2+"'RELEASED'";
		else
			ans2=ans2+"'ERROR'";
		ArrayList<String> ans3 = new ArrayList<String>();
		ans3.add(ans2);

		
	}
	public void afterRistm3(String arg1,String arg2,ArrayList<String> ans1 ){
		String ans2 = "'"+arg1+"','"+arg2+"',";
		String temp = ans1.get(ans1.size()-2);
		if(temp.indexOf("false") > -1)
			ans2=ans2+"'99'";
		else if(temp.indexOf("true") > -1)
			ans2=ans2+"'0'";
		else
			ans2=ans2+"'ERROR'";
		ArrayList<String> ans3 = new ArrayList<String>();
		ans3.add(ans2);
	
		
	}
/*
	public void afterRistm4(String arg0,String arg1,String arg2,ArrayList<String> ans1 ){
		ArrayList<String> list = sendReq(ver,arg0,arg1,arg2);
	
	}
	*/
	 public static InputStream get(String urlStr) throws Exception {
	        return readFrom(urlStr, null);
	    }
	
	 public static InputStream post(String urlStr, String post)
	    	    throws Exception
	    	{
	    	    return readFrom(urlStr, post);
	    	}
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
	    public InputStream httpReq(String ver,String verJira,String status){
	    //	if(ver.equals("0980"))
		//	pName ="SMTGH";
	    	String jiraServer=HagUtil.getPropertyVal("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\list\\cfg.list", "jiraServer");
	    	String jiraServer8=HagUtil.getPropertyVal("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\list\\cfg.list", "jiraServer_new_jira8");
	    	if(jiraServer8!=null)
	    		jiraServer=jiraServer8;
	    	try {
		   		String  url = jiraServer+"/sr/jira.issueviews:searchrequest-xml/temp/SearchRequest.xml?jqlQuery=project+%3D+%22RI+Product+(SCRUM)%22+and+status+%3D+\""+status+"\"+and+fixVersion+in+%28%22"+verJira+"%22%29&tempMax=1000&os_username=cfg-burner&os_password=gon09c";
				if(pName.equals("SMTGH"))
					url = jiraServer+"/sr/jira.issueviews:searchrequest-xml/temp/SearchRequest.xml?jqlQuery=project+%3D+%22RI+Product+STMT%22+and+status+%3D+\""+status+"\"+and+fixVersion+in+%28%22"+verJira+"%22%29&tempMax=1000&os_username=cfg-burner&os_password=gon09c";
				InputStream is = get(url);
				//aaa(url);
		        //return new StreamSource(is) ;
				//HagUtil.p(url);
				//String aa=is.toString();
				//String bb=aa;
			 //    checkConnection(is);
			      
				return  is ;
		    } catch (Exception e) {
		    	e.printStackTrace();
		    	return null;
		    }
		}
	    
	   // public String aaa(url url) {
	    	
	  //  }
	    public String checkConnection(InputStream aa) {
	    	HagUtil.sleep(2000);
	    	  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	          int length;
	          byte[] data = new byte[1024];

	          try {
				while ((length = aa.read(data)) != -1) {
				      outputStream.write(data, 0, length);
				  }
			     String text = outputStream.toString(StandardCharsets.UTF_8.name());
			     String aaw="aa";
			     String dateTime = HagUtil.getDateTime("yyyyMMdd_HHmmss");
			     String ffs ="\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\debug\\"+ dateTime+".xml";
			     HagUtil.writeStringToFile(ffs, text);
			     return text;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "1~"+e.getMessage();
			}

	     
	         // return "aa";
	    }
	    /*
	    public StreamSource httpReq11111111111111(String ver1,String status){
	    	//  ArrayList<String> reqList = loadFileIntoArrayList( reqFile,true ,"*");
	    	//  String req = getType(reqList,status);
			//  if(req==null) 
			//	  req=""+status;
	    	try {
	    		//status = status.replace('~','+');
	    		
	    	//	////////String  url = "https://jiraapp.sapiens.com/sr/jira.issueviews:searchrequest-xml/temp/SearchRequest.xml?jqlQuery=project+%3D+%22RI+Product+STMT%22+and+status+%3D+\""+status+"\"+and+fixVersion+in+%28%22"+ver1+"%22%29&tempMax=1000&os_username=cfg-burner&os_password=gon09c";
	    	//	String  url = "http://emrh64-v6:8080/jira/sr/jira.issueviews:searchrequest-xml/temp/SearchRequest.xml?jqlQuery=project+%3D+%22RI+Product+STMT%22+and+status+%3D+\""+status+"\"+and+fixVersion+in+%28%22"+ver1+"%22%29&tempMax=1000&os_username=cfg-burner&os_password=gon09c";
		    
	    		
	    		//String  url = jiraServer+"/sr/jira.issueviews:searchrequest-xml/temp/SearchRequest.xml?jqlQuery=project+%3D+%22RI+Product+STMT%22+and+status+%3D+\""+status+"\"+and+fixVersion+in+%28%22"+ver1+"%22%29&tempMax=1000&os_username=cfg-burner&os_password=gon09c";
	    		
	    		String  url = jiraServer+"/sr/jira.issueviews:searchrequest-xml/temp/SearchRequest.xml?jqlQuery=project+%3D+%22RI+Product+(SCRUM)%22+and+status+%3D+\""+status+"\"+and+fixVersion+in+%28%22"+ver1+"%22%29&tempMax=1000&os_username=cfg-burner&os_password=gon09c";
				if(pName.equals("SMTGH"))
					url = jiraServer+"/sr/jira.issueviews:searchrequest-xml/temp/SearchRequest.xml?jqlQuery=project+%3D+%22RI+Product+STMT%22+and+status+%3D+\""+status+"\"+and+fixVersion+in+%28%22"+ver1+"%22%29&tempMax=1000&os_username=cfg-burner&os_password=gon09c";
		    	
				 HagUtil.p(ver1);
				 HagUtil.p(status);
	    		 HagUtil.p(url);
	    		
	    		//String  url = "https://jiraapp.sapiens.com/sr/jira.issueviews:searchrequest-xml/temp/SearchRequest.xml?jqlQuery=project+%3D+%22SMT+Test+GH%22+and+status+%3D+\""+status+"\"+and+fixVersion+in+%28%22"+ver1+"%22%29&tempMax=1000&os_username=cfg-burner&os_password=gon09c";
	    		//String  url = "https://jiraapp-test2.sapiens.com/sr/jira.issueviews:searchrequest-xml/temp/SearchRequest.xml?jqlQuery=project+%3D+%22SMT+Test+GH%22+and+status+%3D+\""+status+"\"+and+fixVersion+in+%28%22"+ver1+"%22%29&tempMax=1000&os_username=cfg-burner&os_password=gon09c";
	    		
	    		//String  url = url1.encode(data_src_id, "UTF-8");

	    		//String  url=URLEncoder.encode(url1, "UTF-8");

	    		//System.out.println();
	    		//System.out.println(url);
	    		//System.out.println();
	    		
	    		InputStream is = HagJiraWin.get(url);
	    		//InputStreamReader aaa = new InputStreamReader( is, "UTF-8" );
	    		    return new StreamSource(is) ;
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    		return null;
	    	}
	    }
	    */
	    //------------------------------------------------
	  
//------------------------------------------------
	  
	    public  String convertIsTOString(HagRc hagRc,InputStream aa) {
		    	//HagUtil.sleep(2000);
		    	  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		          int length;
		          byte[] data = new byte[1024];

		          try {
					while ((length = aa.read(data)) != -1) {
					      outputStream.write(data, 0, length);
					  }
				     String text = outputStream.toString(StandardCharsets.UTF_8.name());
				     return text;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					hagRc.rc=1;
					hagRc.log.add(e.getMessage());
					return null;
				}

		     
		        
		    }
	    public HagStringList sendReqNew(String ver,String key,String verJira,String status,String extraDev){
	    	
	    
	    	
			InputStream input  = httpReq(ver,verJira,status);
			HagUtil.sleep(4000);
		
			String inputAsString = checkConnection(  input);
			if(inputAsString.startsWith("1~")) {
				return new HagStringList();
			}
			HagStringList itemsList =JiraParser.parse(inputAsString, key,extraDev);
		
			return itemsList;
		}   
	    
	 
	    public HagStringList sendReq(String ver,String key,String verJira,String status,String extraDev){
	    	String hagVersion;
	    	String hagVersion1;
	    //	int hagFromR6=0;
	    //	int hagFromD1=0;
	    	
	    
	    	
			InputStream input  = httpReq(ver,verJira,status);
		
	
			
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
		}
			
		}   
	    
	    public ArrayList<String> sendReq4(String ver,String key,String verJira,String status){
			InputStream input  = httpReq(ver,verJira,status);
			try {
			//File stocks = new File("d:\\temp\\SearchRequest.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			//Document doc = dBuilder.parse(stocks);
			Document doc = dBuilder.parse(input);
			doc.getDocumentElement().normalize();

			//System.out.println("root of xml file" + doc.getDocumentElement().getNodeName());
			NodeList nodes = doc.getElementsByTagName("item");
			ArrayList<String> arr = new ArrayList<String>();
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					//String name = node.getNodeName();
					//System.out.println("Ticket: " + getValue("key", element));
					String ticket = getValue("key", element);
					if(ticket.startsWith("RI-"))
						ticket = ticket.substring(3,ticket.length()).trim();
					if(ticket.startsWith("SMTGH-"))
						ticket = ticket.substring(6,ticket.length()).trim();
					arr.add(ticket);
				}
			}
			//HagUtil.p(arr);
			//for(int i = 0 ; i < arr.size();i++){
			//	String temp = arr.get(i);
			//	System.out.println(temp);
			//}
		
			return arr;
		
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ArrayList<String>();
		}
			
		}   
		private void addToArr(ArrayList<String> arr,String val,String var,String ticket,String key,String flag) {
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
		private String getVar(String var) {
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
		private  String getValue(String tag, Element element) {
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


 public String getType(ArrayList<String> types,String title){
 	  for(int i = 0 ; i < types.size();i++){
 		  String temp = types.get(i);
 		  String w1 = getWord0(temp,"|",0,true);
 		  String w2 = getWord0(temp,"|",1,true);
 		  if(title.equals(w2))
 				  return w1;
 	  }
 	  return null;
   }
 public static final String getWord0(String str,String delim,int pos,boolean trim){
		String ret = "";
		StringTokenizer words = new StringTokenizer(str,delim);
		if (words.countTokens()<pos)
			return "";
		for(int i = 0 ; i <= pos; i++){
			try {
				ret = words.nextToken();
			}
			catch(NoSuchElementException nsee){
			}
		}
		if(trim)
			ret = ret.trim();
		return ret;
	}
 
 public ArrayList<String> setLines2(String arg0,String str,String ver){
 	//HagUtil.p(str);
	 HagStringList temp = new HagStringList( str,"\n",true);
   HagStringList outArr = new  HagStringList();
	  HagStringList types = new HagStringList( typesFile,true ,"*",false);
	  String curTicket = "?";
	  for(int i = 0 ; i < temp.size();i++){
		  String temp1 = temp.get(i).trim();
		
		  if(temp1==null) 					continue;
		  if(temp1.length()<2) 				continue;
		  if(temp1.startsWith("<?xml"))		continue;
		  if(temp1.startsWith("ticket=")){
			  curTicket= temp1.substring(7,temp1.length()).trim();
			  if(arg0.equalsIgnoreCase("ristm2b")){
				  if(curTicket.indexOf("-") > 0 && curTicket.indexOf("-") < curTicket.length())
					  curTicket = curTicket.substring(curTicket.lastIndexOf("-")+1,curTicket.length());
			  }
			  continue;
		  }
		  if(temp1.indexOf("(") <0)
			continue;

		 // HagUtil.p(temp1);
		  String title = temp1.substring(0,temp1.indexOf("("));
		// HagUtil.p("title"+i+"="+title);
		  String type = getType(types,title);
		  if(type==null) 					continue;
		 
		  if(temp1.indexOf(")") <0)
			  temp1=temp1+")";

		  String elementsListStr = temp1.substring(temp1.indexOf("(")+1,temp1.indexOf(")"));
		  ArrayList<String> elementsListArr =  HagUtil.splitStr2ArrayList(elementsListStr,";");

		  for(int j=0;j<elementsListArr.size();j++){
			  String element = elementsListArr.get(j).trim();
			  StringBuilder line = new StringBuilder()
			  .append("INIT").append("~")
			  .append(curTicket).append("~")
			  .append(type).append("~")	
			  .append(element).append("~")	
			  .append("INIT");
			 
			  outArr.add(line.toString());
		  }
		  
	  }
	  return outArr;
 }
 public ArrayList<String> setLines4(String arg0,String str,String ver){
 	  ArrayList<String> temp = HagUtil.splitStr2ArrayList( str,"\n");
 	  ArrayList<String> outArr = new  ArrayList<String>();
 	  String curTicket = "?";
 	  for(int i = 0 ; i < temp.size();i++){
 		  String temp1 = temp.get(i).trim();
 		  if(temp1==null) 					continue;
 		  if(temp1.length()<2) 				continue;
 		  if(temp1.startsWith("<?xml"))		continue;
 		  if(temp1.startsWith("ticket=")){
 			  curTicket= temp1.substring(7,temp1.length()).trim();
 			  if(arg0.equalsIgnoreCase("ristm4b")){
 				  if(curTicket.indexOf("-") > 0 && curTicket.indexOf("-") < curTicket.length())
 					  curTicket = curTicket.substring(curTicket.lastIndexOf("-")+1,curTicket.length());
 			  }
 			  
 			  StringBuilder line = new StringBuilder().append(curTicket)	;
     		  outArr.add(line.toString());
 			  continue;
 		  }
 	  }
 	  return outArr;
   }

}  

