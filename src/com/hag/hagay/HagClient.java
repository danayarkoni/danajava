package com.hag.hagay;
import java.net.*;   
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.io.*;   
public class HagClient {   
	
	public HagClient(){
	}
	
	public HagStringList  run(String server1, String command){
		String server 	= server1+".sapiens.int";
		String who 		= System.getProperty("user.name");
		String date 	= getDateTime("dd/MM/yyyy");
		String time 	= getDateTime("HH:mm");
		
		HagStringList ans = new HagStringList();
		String ans1 = "";
		String ans2 = "";
		if(command.startsWith("stopListener ")){
			ans =stopListener(server,command);	
			ans1 = ans.get(0);
			ans2 = ans1.substring(2,ans1.length());
		}else if(command.startsWith("startListener ")){
			ans =startListener(server,command);	
			ans1 = ans.get(0);
			ans2 = ans1.substring(2,ans1.length());
		}else if(command.startsWith("statusListener")){
			ans =statusListener(server,command);	
			ans1 = ans.get(0);
			ans2 = ans1.substring(2,ans1.length());
		}else if(command.startsWith("startTomcat ")){
			//ans =startTomcat(server,command);
			ans=setTomcat( server, command,"START");
			ans1 = ans.get(0);
			ans2 = "FAILED";
			if(ans1.indexOf("service was started successfully")>0)
				ans2 = "OK";
		}else if(command.startsWith("stopTomcat ")){
			//ans =stopTomcat(server,command);
			ans=setTomcat( server, command,"STOP");
			ans1 = ans.get(0);
			ans2 = "FAILED";
			if(ans1.indexOf("service was stopped successfully")>0)
				ans2 = "OK";
		}else{
			System.out.println("error 10001");
			//ans.add(runCmd(server,command,"Y"));	
		}
	
		append("\\\\hyundai.sapiens.com\\hyun01log\\closed_patches\\config\\cfgMenu\\bin\\log\\hagServer_"+server1+".txt",
		"XGI~"+ans2+"~"+who+"~"+date+"~"+time+"~"+command);
		return ans;
	}

	
	private HagStringList loadSudoCmd(){
		 HagStringList list = new  HagStringList();
		 list.add("startListener");
		 list.add("stopListener");
		 list.add("statusListener");
		 list.add("startTomcat");
		 list.add("stopTomcat");
		// list.add("C:\\WINDOWS\\Microsoft.NET\\Framework\\v1.1.4322\\InstallUtil");
		 return list;
    }
	
	private boolean checkSudoCmd(HagStringList list,String cmd){
		cmd = cmd.trim().toLowerCase();
		for(int i = 0 ; i < list.size();i++){
			if(cmd.startsWith(list.get(i).toLowerCase()))
				return true;
		}
		 return false;
	}
	
	private String getDateTime(String format){
		Date date = new Date();
		TimeZone tz = TimeZone.getTimeZone("Asia/jerusalem");
		SimpleDateFormat logFolder  = new SimpleDateFormat(format);
		logFolder.setTimeZone(tz);	
		String dateStr = logFolder.format(date);
		return dateStr;
	}
	private void append(String fileName,String str){
		try{      
			FileOutputStream fos = new FileOutputStream(fileName, true);     
			fos.write(str.getBytes());     
			fos.close();       
		}catch(FileNotFoundException ex){
			System.out.println("FileNotFoundException : " + ex);     
		}catch(IOException ioe){     
			System.out.println("IOException : " + ioe);     
		}
	}
	private String getWord0(String str,String delim,int pos,boolean trim){
		String ret = "";
		StringTokenizer words = new StringTokenizer(str,delim);
		if (words.countTokens()<pos)
			return "";
		for(int i = 0 ; i <= pos; i++){
			try {
				ret = words.nextToken();
			}catch(NoSuchElementException nsee){
				System.out.println("getWord0");
			}
		}
		if(trim)
			ret = ret.trim();
		return ret;
	}
	
	private String runCmd(String server,String command,String wait){
		try {   
			String whoAmI = System.getProperty("user.name");
			int  port= HagUtil.getService( server);
			//int port = 2000;
			//if(server.toUpperCase().startsWith("CONFG1") || server.toUpperCase().startsWith("WIN0864")|| server.toUpperCase().startsWith("RIDEVBLP05") )
			//	port = 2001;
			Socket client = new Socket(server, port);   
		
			command = whoAmI+"~"+command;
			
			
			BufferedOutputStream out = new BufferedOutputStream(   
                                           client.getOutputStream());   
			BufferedInputStream in = new BufferedInputStream(   
                                         client.getInputStream());   
		
			byte[] cmd = command.getBytes();   
			out.write(cmd, 0, cmd.length);   
			out.flush();   
			StringBuffer sb = new StringBuffer();   
			int c = -1;  
			c = in.read();
			while (wait.toUpperCase().equals("Y") && c != -1 ) {  
				sb.append((char) c);   
				c = in.read();

			}   
			//System.out.println(new String(sb));   
			out.close();   
			in.close();   
			client.close();
			String ans = new String(sb);
			return ans;
		} catch (ConnectException c) {   
		//	c.printStackTrace();   
			return "1~check cfgMenu_service on "+server+" If cfgMenu service keeps falling check hyundai mapping";
		} catch (Exception e) {   
			e.printStackTrace();   
			return "runCmdFailed";
		}
	}
	public String getprcId(String str,String env){
		HagStringList arr = splitStr2ArrayList(str,"\r\n");
		String prcFound = "-1";
		for(int i = 1 ; i < arr.size();i++){
			String temp = arr.get(i);
			String dbid = getWord0(temp," ",1, true);
			String prc = getWord0(temp," ",3, true);
			if(dbid.equalsIgnoreCase("\""+env+"\""))
				prcFound=prc;
		}
		//System.out.println("prcFound="+prcFound);
		if(prcFound.equals("-1"))
			return "1~"+env +" not found.";
		else if(prcFound.equals("0."))
			return "2~"+env +" is down.";
		else{
			return "0~"+prcFound.substring(0,prcFound.length()-1);
		}
	}
	
	private HagStringList setTomcat(String server,String command,String act){
		HagStringList ans = new HagStringList();
		//check if params
		if((command.trim()).indexOf(" ")< 0){
			ans.add("2~Failed");
			printHelp();
			return ans;	
		}
		String env = getWord0(command," ",1, true);
		
		String ans1 = runCmd(server,"NET "+act+" RILS-"+env+"\n","Y");
		System.out.println(ans1);
		if(act.equals("START")){
			if(ans1.indexOf("requested service has already been started")>0 || ans1.equals(""))
				ans.add("1~"+ans1);
			else
				ans.add("0~"+ans1);
		}else if(act.equals("STOP")){
			if(ans1.indexOf("service is not started")>0  || ans1.equals(""))
				ans.add("1~"+ans1);
			else
				ans.add("0~"+ans1);
		}
		return ans;	
	}	
	/*
	private HagStringList startTomcat(String server,String command){
		HagStringList ans = new HagStringList();
		//check if params
		if((command.trim()).indexOf(" ")< 0){
			ans.add("2~Failed");
			printHelp();
			return ans;	
		}
		String env = getWord0(command," ",1, true);
		String ans1 = runCmd(server,"NET START RILS-"+env+"\n","Y");
		System.out.println(ans1);
		if(ans1.indexOf("requested service has already been started")>0 || ans1.equals(""))
			ans.add("1~"+ans1);
		else
			ans.add("0~"+ans1);
		return ans;	
	}	
	*/
	/*
	private HagStringList stopTomcat(String server,String command){
		HagStringList ans = new HagStringList();
		if((command.trim()).indexOf(" ")< 0){
			ans.add("2~Failed");
			printHelp();
			return ans;	
		}
		String env = getWord0(command," ",1, true);
		String cmd1 = "taskkill /F /FI \"SERVICES eq RILS-"+env+"\" /T";
		String ans1 = runCmd(server,cmd1+"\n","Y");
		System.out.println(ans1);
		if(ans1.indexOf("SUCCESS:")>-1 )
			ans.add("0~"+ans1);			
		else
			ans.add("1~"+ans1);

		return ans;	
	}	
*/
	/*
	private HagStringList serviceAct(String server,String command,String act){
		HagStringList ans = new HagStringList();
		String ans4 = runCmd(server,command+"\n","Y");
		System.out.println(ans4);
		
		String goodAns = " service was stopped successfully";
		if(act.equalsIgnoreCase("start"))
			goodAns = " service was started successfully";
		
		
		if(ans4.indexOf(goodAns) > 0)
			ans.add("0~OK");
		else
			ans.add("1~Failed");
		ans.add(ans4);
		return ans;	
	}	
	*/
	private HagStringList statusListener(String server,String command){
		String strbislLoc = "C:\\Program Files\\Sapiens\\eMergeBIS4415\\bin\\strbisl";
		if(	server.equalsIgnoreCase("ri-app.sapiens.int") 	||
			server.equalsIgnoreCase("ri2-app.sapiens.int") 	||
			server.equalsIgnoreCase("ri08demo.sapiens.int") 	||
			server.equalsIgnoreCase("riqa.sapiens.int" 	) )
			strbislLoc = "D:\\ri\\eMerge\\eMergeBIS4415\\bin\\strbisl";
		strbislLoc = "strbisl";
		HagStringList ans = new HagStringList();
		String ans4 = runCmd(server,strbislLoc+" -t\n","Y");
		System.out.println(ans4);
		if(!ans4.equals("runCmdFailed"))
			ans.add("0~OK");
		else
			ans.add("1~Failed");
		ans.add(ans4);
		return ans;	
	}
	
	private HagStringList stopListener(String server,String command){
		HagStringList ans = new HagStringList();
		if((command.trim()).indexOf(" ")< 0){
			ans.add("4~Failed");
			printHelp();
			return ans;	
		}
		String strbislLoc = "C:\\Program Files\\Sapiens\\eMergeBIS4415\\bin\\strbisl";
		if(		server.equalsIgnoreCase("ri-app.sapiens.int") 		||
				server.equalsIgnoreCase("ri2-app.sapiens.int") 		||
				server.equalsIgnoreCase("ri2qa.sapiens.int") 		||
				server.equalsIgnoreCase("ridev.sapiens.int") 		||
				server.equalsIgnoreCase("ri08demo.sapiens.int") 	||
				server.equalsIgnoreCase("riqa.sapiens.int" 	) )
			strbislLoc = "D:\\ri\\eMerge\\eMergeBIS4415\\bin\\strbisl";
		else if(server.equalsIgnoreCase("ri3qa.sapiens.int") 		||
				server.equalsIgnoreCase("ri6qa.sapiens.int") 		||
				server.equalsIgnoreCase("ridev45.sapiens.int") 		||
				server.equalsIgnoreCase("RIDEVBLP.sapiens.int") 	||
				server.equalsIgnoreCase("RIDEVBLP05.sapiens.int") 	||
				server.equalsIgnoreCase("p1-bis.sapiens.int" 	) )
			strbislLoc = "D:\\ri\\eMerge\\eMergeBIS4520\\bin\\strbisl";
		
	
		strbislLoc = "strbisl";
		String env = getWord0(command," ",1, true);
		
		String ans1 = runCmd(server,strbislLoc+" -t\n","Y");
		String prc = getprcId( ans1,env);
		if(!prc.startsWith("0~")){
			ans.add(prc);
			return ans;
		}
		
		prc.substring(2,prc.length());
		/*
		String cmd2 = "taskkill /F /FI \"PID eq "+prcNum+"\" /T";
		System.out.println(cmd2);
		String ans2 = runCmd(server,cmd2+"\n","Y");
		String ans3 = runCmd(server,strbislLoc+" -e "+env.toUpperCase()+"\n","N");
		System.out.println(ans2);
		if(ans2.indexOf("SUCCESS:")>-1 )
			ans.add("0~"+ans2);			
		else
			ans.add("1~"+ans2);
		*/
		if(prc.startsWith("0~")){
			String prcFound = getWord0(prc,"~",1,true);
			//String ans2 = runCmd(server,"D:\\ri\\bat\\SysinternalsSuite\\pskill -t "+prcFound+"\n","N");
			//runCmd(server,"D:\\ri\\bat\\SysinternalsSuite\\pskill -t "+prcFound+"\n","N");
			runCmd(server,"taskkill /F /T /PID "+prcFound+"\n","N");
			//taskkill /F /T /PID "+pid
			String ans3 = runCmd(server,strbislLoc+" -e "+env.toUpperCase()+"\n","N");
			if(!ans3.equals("runCmdFailed"))
				ans.add("0~OK");
			else
				ans.add("1~Failed");
		}else{
			ans.add("3~Already down");
		}
		
		return ans;	
	}
	private HagStringList startListener(String server,String command){
		HagStringList ans = new HagStringList();
		if((command.trim()).indexOf(" ")< 0){
			ans.add("2~Failed");
			printHelp();
			return ans;	
		}
		//String strbislLoc = "D:\\ri\\eMerge\\eMergeBIS4520\\bin\\strbisl";
		//String strbislLoc = "C:\\Program Files\\Sapiens\\eMergeBIS4415\\bin\\strbisl";
		//if(	server.equalsIgnoreCase("ri-app.sapiens.int") 	||
		//		server.equalsIgnoreCase("ri2-app.sapiens.int") 	||
		//		server.equalsIgnoreCase("ri08demo.sapiens.int") 	||
		//		server.equalsIgnoreCase("riqa.sapiens.int" 	) )
		//	strbislLoc = "D:\\ri\\eMerge\\eMergeBIS4415\\bin\\strbisl";
		String strbislLoc = "strbisl";
		String env = getWord0(command," ",1, true);
		String ans1 = runCmd(server,strbislLoc+" -t\n","Y");
		String prc = getprcId( ans1,env);//	1~= not found   2~= is down 0~=ok
		if(prc.startsWith("2~")){
			String ans3 = runCmd(server,strbislLoc+" -l "+env.toUpperCase()+"\n","N");
			if(!ans3.equals("runCmdFailed"))
				ans.add("0~OK");
			else
				ans.add("1~Failed");
		}else if(prc.startsWith("0~")){
			System.out.println("Already active");
			ans.add("2~Already active");
		}else if(prc.startsWith("1~")){
			ans.add("1~not found");
		}
		return ans;	
	}
	private HagStringList splitStr2ArrayList(String str,String delim){
		HagStringList vec = new HagStringList();
		StringTokenizer words = new StringTokenizer(str,delim);
		while(true){
			try {
				String word = words.nextToken();
				vec.add(word);
			}
			catch(NoSuchElementException nsee){
				return vec;
			}
		}
	}   
	private void printHelp() {  
		HagStringList list = loadSudoCmd();
		System.out.println("rmsudo syntax:");
		System.out.println("  rmsudo <server> <command>");
		System.out.println("Examples:");
		for(int i = 0 ; i < list.size();i++){
			String temp = list.get(i);
			String param = "<Parameters>";
			if(temp.equals("statusListener"))
				param = "";
			System.out.println("  rmsudo RI-APP "+temp+" "+ param);
		}
	}
	private String getCommand(String[] args1) {  
		String command 	= args1[1];
		for(int i = 2 ;i<args1.length;i++)
			command = command+" "+args1[i];
		command=command+"\n";
		return command;
	}

	
	
	public HagStringList  runNotSudo(String server1, String command){
		String server 	= server1+".sapiens.int";
		String who 		= System.getProperty("user.name");
		String date 	= getDateTime("dd/MM/yyyy");
		String time 	= getDateTime("HH:mm");
		
		HagStringList ans = new HagStringList();	
	
		String ans1 = runCmd(server,command+"\n","Y");
		//System.out.println(command);
		//System.out.println(ans1);
		ans.add(ans1);
		append("\\\\hyundai.sapiens.com\\hyun01log\\closed_patches\\config\\cfgMenu\\bin\\log\\hagServer_"+server1+".txt","XGI~NOTSUDO"+"~"+who+"~"+date+"~"+time+"~"+command+"\n");
		//HagUtil.p11(ans);
		return ans;
	}
	


	///////////////////////////////
	public static void main(String[] args) {  
		HagClient hagClient = new HagClient();
		//debug
/*
		HagStringList list = hagClient.loadSudoCmd();
		String server1 	= "RI2-APP";
		String command 	= "W1MIC /node:\"confg1\" /user:\"sapiens\\cfg-burner\" /password:\"gon09c\" PROCESS WHERE (Name='perl.exe') get Commandline\n";  
		//String command 	= "statusListener\n";  
		//if(hagClient.checkSudoCmd(list,command))
			hagClient.runNotSudo(server1,command);
		//else
			//System.out.println(command+" command  not found in SUDO list.");
	
		//rmsudo
	*/	
		if(args.length==0)
			hagClient.printHelp();
		else if (args[0].equalsIgnoreCase("-h"))
			hagClient.printHelp();
		else{
			HagStringList list = hagClient.loadSudoCmd();
			String server1 	= args[0];
			String command 	= hagClient.getCommand(args);  
			if(hagClient.checkSudoCmd(list,command))
				hagClient.run(server1,command);
			else
				System.out.println(command+" command  not found in SUDO list.");
		}
		
    }   
}  
