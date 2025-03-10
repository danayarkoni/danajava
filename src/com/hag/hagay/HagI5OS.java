package com.hag.hagay;

/* 
JAVA CLASS(HagAs400Exec) PARM('LINCOLN,/QSYS.LIB/CONFIG.LIB/GETPARM.PGM')                                               
CLASSPATH('/config:/com/lib/jt400Proxy.jar:/com/lib/jt400Native.jar') 
*/
//import java.beans.PropertyVetoException;

import java.awt.Color;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JOptionPane;

import com.ibm.as400.access.*;
import com.ibm.as400.access.list.OpenListException;
import com.ibm.as400.access.list.SpooledFileListItem;
import com.ibm.as400.access.list.SpooledFileOpenList;

public class HagI5OS { 
	Job jjj = null;
	//HagGridPanel hagGridPanel= null;
	
	String host2,user2,pass2;

	 
	public HagI5OS(){
	  //  ref2hagUtil = hagUtil;
	}
	//public HagI5OS(HagGridPanel ref2hagGridPanel){
	//	this.hagGridPanel = ref2hagGridPanel;
	//	}
	/*
	public void  aaa(String server,String connectU,String connectP,
									String lib,String file,String mbr){
		lib=lib.toUpperCase();
		lib=lib.trim();
		file=file.toUpperCase();
		file=file.trim();
		mbr=mbr.toUpperCase();
		mbr=mbr.trim();

		AS400 sys = new AS400(server+".sapiens.com",connectU,connectP);
		
		SequentialFile myFile = new SequentialFile(sys, "/QSYS.LIB/MYLIB.LIB/MYFILE.FILE/%FILE%.MBR");



	}
	*/
	
	public String i5osDtaara(String server,String connectU,String connectP,String lib,String dtaara ,String key ){
		AS400 sys = new AS400(server+".sapiens.com",connectU,connectP);
		QSYSObjectPathName path = new QSYSObjectPathName(lib, dtaara, "DTAARA");
		CharacterDataArea dataArea = new CharacterDataArea(sys, path.getPath());
		String line="";
		try {
			String all = dataArea.read();
			int ifsPos1 = all.indexOf(key);
			int ifsPos2 = all.indexOf(":",ifsPos1);
			System.out.println(key);
			String temp =all.substring(ifsPos1,ifsPos2);
			if(temp.trim().equalsIgnoreCase(key)){
				line = all.substring(ifsPos2+1,ifsPos1+50);
				line=line.trim();
			}else				
				line=null;
			
		} catch (AS400SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ErrorCompletingRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalObjectTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ObjectDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			sys.disconnectAllServices();
		}
		return line;
	
	}
	public String getDtaara(String server,String connectU,String connectP,String lib,String dtaara ){
		AS400 sys = new AS400(server+".sapiens.com",connectU,connectP);
		QSYSObjectPathName path = new QSYSObjectPathName(lib, dtaara, "DTAARA");
		CharacterDataArea dataArea = new CharacterDataArea(sys, path.getPath());
		String line="";
		try {
			line= dataArea.read();
			line = HagUtil.splitLines(line,50,"\n");
		} catch (AS400SecurityException e) {
			e.printStackTrace();
			line="ERROR: "+e.getMessage();
		} catch (ErrorCompletingRequestException e) {
			e.printStackTrace();
			line="ERROR: "+e.getMessage();
		} catch (IllegalObjectTypeException e) {
			e.printStackTrace();
			line="ERROR: "+e.getMessage();
		} catch (InterruptedException e) {
			e.printStackTrace();
			line="ERROR: "+e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			line="ERROR: "+e.getMessage();
		} catch (ObjectDoesNotExistException e) {
			e.printStackTrace();
			line="ERROR: "+e.getMessage();
		}finally{
			sys.disconnectAllServices();
		}
		return line;
	
	}
	/*
	public String startTomcat(String server,String user,String pass,String dbid){
		AS400 sys = new AS400(server+".sapiens.com",user,pass);
		QSYSObjectPathName path = new QSYSObjectPathName(lib, dtaara, "DTAARA");
		CharacterDataArea dataArea = new CharacterDataArea(sys, path.getPath());
		String line="";
		try {
			String all = dataArea.read();
			int ifsPos1 = all.indexOf(key);
			int ifsPos2 = all.indexOf(":",ifsPos1);
			String temp =all.substring(ifsPos1,ifsPos2);
			if(temp.trim().equalsIgnoreCase(key)){
				line = all.substring(ifsPos2+1,ifsPos1+50);
				line=line.trim();
			}else				
				line=null;
			
		} catch (AS400SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ErrorCompletingRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalObjectTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ObjectDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			sys.disconnectAllServices();
		}
		return line;
	
	}
	*/
	public String readFile(String server,String connectU,String connectP, String lib, String obj, String mbr) {
		StringBuilder aa = new StringBuilder();
		AS400 sys = new AS400(server+".sapiens.com",connectU,connectP);
		QSYSObjectPathName filename = 	new    QSYSObjectPathName(lib,obj,mbr,"MBR"); 
		SequentialFile sFile = new SequentialFile(sys,filename.getPath()); 
		AS400FileRecordDescription recordDescription = 	new AS400FileRecordDescription(sys, filename.getPath()); 
		try{ 			
			RecordFormat[] format =  recordDescription.retrieveRecordFormat(); 
			sFile.setRecordFormat(format[0]); 
			sFile.open(SequentialFile.READ_ONLY, 100,SequentialFile.COMMIT_LOCK_LEVEL_NONE); 
			Record data = sFile.readFirst();
			while (data != 	null){ 
				Object[] oo = data.getFields();
				//String os = oo[0].toString();
				String os = "";
				for(int i=2;i<oo.length;i++)
					os=os+oo[i].toString();
				aa.append(os).append("\n");
				data = sFile.readNext(); 
			} 
	
	}catch(IOException ioe){ 
		System.out.println(ioe.getMessage()); 
		ioe.printStackTrace(); 
		return ioe.getMessage();
		 
	}catch(Exception ioe){ 
		System.out.println(ioe.getMessage()); 
		ioe.printStackTrace(); 
		return ioe.getMessage();
	}finally{
		sys.disconnectAllServices();
	}
	return aa.toString();
	
}

	  public String readFileTest(String server, String path){
		  AS400 sys = new AS400(server+".sapiens.com","RIGONEN1","GON09C"); 
		  StringBuffer sb = new StringBuffer();
	   try{
		  IFSFile file = new IFSFile(sys, path);
	       /** creates a file reader */
	       IFSFileReader fr = new IFSFileReader (file);
	       BufferedReader br = new BufferedReader(fr);
	     
	       /** Reads a line of text */
	       String line = br.readLine();
	       while( line==null)
	    	  line = br.readLine();
		         
	       while (!line.equals("************End of Data********************")){

	            while( line==null)
	  	    	  line = br.readLine();
	  		    
	            sb.append(line);
	       }
	       fr.close();
	  }catch(IOException ioe){ 
				System.out.println(ioe.getMessage()); 
				ioe.printStackTrace(); 
				System.exit(0); 
			} 

			catch(Exception ioe){ 
				System.out.println(ioe.getMessage()); 
				ioe.printStackTrace(); 
				System.exit(0); 
			
	  }finally{
			sys.disconnectAllServices();
		}
	       return sb.toString();
	  }
	public String readFile1111111111(String server,String connectU,String connectP, String path) {
		AS400 sys = new AS400(server+".sapiens.com",connectU,connectP);
		StringBuilder aa = new StringBuilder(); 
		try{ 
			QSYSObjectPathName filename = 	new    QSYSObjectPathName("CFG","CPYDB_RI","CPYDB_SKL","MBR"); 
			SequentialFile file = new SequentialFile(sys,filename.getPath()); 
//		     Retrieve the record format for the file 
			AS400FileRecordDescription recordDescription = 	new AS400FileRecordDescription(sys, filename.getPath()); 
			RecordFormat[] format =  recordDescription.retrieveRecordFormat(); 
			file.setRecordFormat(format[0]); 
			file.open(SequentialFile.READ_ONLY, 100,SequentialFile.COMMIT_LOCK_LEVEL_NONE); 
			Record data = file.read();   
		// Loop while there are records in the file (while we have not 
		// reached end-of-file). 

			while (data != 	null){ 
				//String desc = (String) data.getField(1); 
				aa.append(data).append("\n");
				data = file.read(); 
			} 
		} 
		catch(IOException ioe){ 
			System.out.println(ioe.getMessage()); 
			ioe.printStackTrace(); 
			System.exit(0); 
		} 

		catch(Exception ioe){ 
			System.out.println(ioe.getMessage()); 
			ioe.printStackTrace(); 
			System.exit(0); 
		}finally{
			sys.disconnectAllServices();
		}
		return aa.toString();
	}    
	
	public String getFromDtaara(String server,String connectU,String connectP,String lib,String dtaara){
		AS400 sys = new AS400(server+".sapiens.com",connectU,connectP);
		QSYSObjectPathName path = new QSYSObjectPathName(lib, dtaara, "DTAARA");
		CharacterDataArea dataArea = new CharacterDataArea(sys, path.getPath());
		String line="";
		try {
			String all = dataArea.read();
			String tomCatCmd = all.substring(all.indexOf("QSH"),all.indexOf("))")+1);
			return tomCatCmd;
		} catch (AS400SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ErrorCompletingRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalObjectTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ObjectDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			sys.disconnectAllServices();
		}
		return line;
	
	}


	  
	public String i5osFtp(String server,String connectU,String connectP,String lib,String file,String mbr,String target){
		String as400Name =  server+"-"+lib+"/"+file+"("+mbr+")";
		lib=lib.toUpperCase();
		lib=lib.trim();
		file=file.toUpperCase();
		file=file.trim();
		mbr=mbr.toUpperCase();
		mbr=mbr.trim();
		FTP client = new FTP(server+".sapiens.com", connectU, connectP);
		try {
			client.cd(lib);
		    client.get(file+"."+mbr, target);
		    client.disconnect();
		}catch (Exception e){
	       System.out.println("  ftp failed:"+e.getMessage());
		  return "1~"+e.getMessage();
		}
		 return "0~"+as400Name+" copied to " + target;

	}
	public boolean i5osFtp1(String server,String connectU,String connectP,String lib,String file,String mbr,String target){
		lib=lib.toUpperCase();
		lib=lib.trim();
		file=file.toUpperCase();
		file=file.trim();
		mbr=mbr.toUpperCase();
		mbr=mbr.trim();
		FTP client = new FTP(server, connectU, connectP);
		try {
			client.cd(lib);
		    client.put("/QSYS.LIB/"+lib+".LIB/"+file+".FILE", target);
		    client.disconnect();
		}catch (Exception e)	    {
	       System.out.println("  ftp failed");
	       return false;
		}
		 return true;

	}
	public boolean i5osFtpFile(String server,String connectU,String connectP,String lib,String file,String target){
		AS400 sys = new AS400(server+".sapiens.com",connectU,connectP);
		IFSJavaFile file1 = new IFSJavaFile(sys, "/QSYS.LIB/"+lib+".LIB/"+file+".FILE");
		File[] mbrList = file1.listFiles();
		for(int i = 0 ; i < mbrList.length;i++){
			String temp = mbrList[i].toString();
			String MEMBER_NAME = temp.substring(temp.lastIndexOf("\\")+1,temp.lastIndexOf("."));
			String target1 = target+"\\"+MEMBER_NAME;
			if(!i5osFtp(server,connectU,connectP,lib,file,MEMBER_NAME,target1).startsWith("0~"))
				return false;
		}
		return true;
	}
	
	public ArrayList<String> getFileContent11111(String server,String connectU,String connectP,String lib,String file){
		ArrayList<String> ans = new ArrayList<String>();
		ans.add("MEMBER_NAME~CREATION_DATE_TIME~CHANGE_DATE_AND_TIME~DATE_LAST_USED~MEMBER_TEXT_DESCRIPTION");
		ans.add("y100~y100~y100~y100~y100");
		lib=lib.toUpperCase();
		lib=lib.trim();
		file=file.toUpperCase();
		file=file.trim();
		AS400 sys = new AS400(server+".sapiens.com",connectU,connectP);
		MemberList memberList = new MemberList(sys,lib,file);
		MemberDescription[]  memberDescriptions = memberList.getMemberDescriptions();
		for(int i = 0 ; i < memberDescriptions.length;i++){
			MemberDescription memberDescription = memberDescriptions[i];
			try {
				String MEMBER_NAME = (String) memberDescription.getValue(MemberDescription.MEMBER_NAME);
				String CREATION_DATE_TIME = (String) memberDescription.getValue(MemberDescription.CREATION_DATE_TIME );
				String CHANGE_DATE_AND_TIME = (String) memberDescription.getValue(MemberDescription.CHANGE_DATE_AND_TIME);
				String DATE_LAST_USED = (String) memberDescription.getValue(MemberDescription.DATE_LAST_USED );
				String MEMBER_TEXT_DESCRIPTION = (String) memberDescription.getValue(MemberDescription.MEMBER_TEXT_DESCRIPTION);
				ans.add(MEMBER_NAME+"~"+CREATION_DATE_TIME+"~"+CHANGE_DATE_AND_TIME+"~"+DATE_LAST_USED+"~"+MEMBER_TEXT_DESCRIPTION);
			} catch (AS400Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AS400SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ErrorCompletingRequestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ObjectDoesNotExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				sys.disconnectAllServices();
			}
		}
		return ans;
	}

	public ArrayList<String> getLibContent(String server,String connectU,String connectP,String lib,String obj,String type){
		lib=lib.toUpperCase();
		lib=lib.trim();
		obj=obj.toUpperCase();
		obj=obj.trim();
		type=type.toUpperCase();
		type=type.trim();
		
		ArrayList<String> ans = new ArrayList<String>();
		ans.add("Name~Type~OWNER~CREATOR~CHANGE_DATE~JOURNAL_STATUS~JOURNAL_NAME");
		ans.add("y100~y100~y100~y100~y200~y150~y200");
		AS400 sys = new AS400(server+".sapiens.com",connectU,connectP);
		ObjectList ol = new ObjectList(sys, lib, obj, type) ;
		try {
			
			ObjectDescription[] od = ol.getObjects(0,ol.getLength());
			for(int i = 0 ; i < od.length;i++){
				ObjectDescription od1 = od[i];
				
				String CREATOR = od1.getValueAsString(ObjectDescription.CREATOR_USER_PROFILE);
				String CHANGE_DATE = od1.getValueAsString(ObjectDescription.CHANGE_DATE);
				String OWNER = od1.getValueAsString(ObjectDescription.OWNER);
				String JOURNAL_STATUS = od1.getValueAsString(ObjectDescription.JOURNAL_STATUS);
				String JOURNAL_NAME = "not journaled";
				if(!JOURNAL_STATUS.equals("false")){
					JOURNAL_NAME = od1.getValueAsString(ObjectDescription.JOURNAL);
					JOURNAL_STATUS = "journaled";
				}else{
					JOURNAL_STATUS = "not journaled";
					JOURNAL_NAME = "not journaled";
				}
				String name1 = od1.getName();
				String type1 = od1.getType();
				if(type1.equals("FILE"))
					
					ans.add(name1 +"~"+type1+"~"+OWNER+"~"+CREATOR+"~"+CHANGE_DATE+"~"+JOURNAL_STATUS+"~"+JOURNAL_NAME);
				else
					ans.add(name1 +"~"+type1+"~"+OWNER+"~"+CREATOR+"~"+CHANGE_DATE+"~.~.");
								
			}
		} catch (ExtendedIllegalArgumentException e1) {
			e1.printStackTrace();
			return ans;
		} catch (AS400Exception e1) {
			e1.printStackTrace();
		} catch (AS400SecurityException e1) {
			e1.printStackTrace();
		} catch (ErrorCompletingRequestException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ObjectDoesNotExistException e1) {
			e1.printStackTrace();
		}finally{
			sys.disconnectAllServices();
		}
		return ans;
	}

	public ArrayList<String> emptyFilesInLib(String server,String connectU,String connectP,String lib){
		lib=lib.toUpperCase();
		lib=lib.trim();
		AS400 sys = new AS400(server+".sapiens.com",connectU,connectP);
		ObjectList ol = new ObjectList(sys, lib, "*ALL", "*FILE") ;
		ArrayList<String> ans = new ArrayList<String>();
		try {
			ObjectDescription[] od = ol.getObjects(0,ol.getLength());
			for(int i = 0 ; i < od.length;i++){
			
				ObjectDescription od1 = od[i];
				String name1 = od1.getName();
				String cmdStr = "RMVM FILE("+lib+"/"+name1+") MBR(*ALL)";	
				CommandCall cmd = new CommandCall(sys);
				cmd.run(cmdStr);
				AS400Message[] messageList = cmd.getMessageList();
				for(int k = 0 ; k <	messageList.length	;	k++)
					ans.add(messageList[k].getText());
			}
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		} catch (AS400Exception e1) {
			e1.printStackTrace();
		} catch (AS400SecurityException e1) {
			e1.printStackTrace();
		} catch (ErrorCompletingRequestException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ObjectDoesNotExistException e1) {
			e1.printStackTrace();
		}finally{
			sys.disconnectAllServices();
		}
		
		return ans;
	}
	public String lockLib111111111(String server,String connectU,String connectP,String lib,String act,String riList){
		String ans = "";
		AS400 sys = new AS400(server+".sapiens.com",connectU,connectP);
		ObjectDescription objectDescription = new ObjectDescription(sys, "*LIBL", lib,"LIB"); 
		try {
			ObjectLockListEntry[] objectLockListEntry = objectDescription.getObjectLockList();
		
			if(objectLockListEntry==null || objectLockListEntry.length==0){
				if(act.equals("UNLOCK"))
					return "Listener "+riList+" = UNLOCK";
				else
					return "Listener "+riList+" = DOWN";
			}
			int i=0;
			Boolean stopFlag=false;	
			while(!stopFlag){
				System.out.println(i);
				if(objectLockListEntry[i]==null){
					i++;
					continue;
				}
				ObjectLockListEntry objectLock = objectLockListEntry[i];
				i++;
				String name = objectLock.getJobName();
				String num = objectLock.getJobNumber();
				String user = objectLock.getJobUserName();
				Job job = new Job(sys, name, user,num);
				if(act.equals("UNLOCK")){
					try{
						job.end(i);
					}catch(com.ibm.as400.access.AS400Exception ciaaae){
						System.out.println(ciaaae.getMessage());
					}
				}else{
					if(riList.equals(name)){
						stopFlag=true;
						ans = "Listener "+riList+" = UP";
					}
				}
				if(i>objectLockListEntry.length){
					if(act.equals("UNLOCK")){
						objectDescription = new ObjectDescription(sys, "*LIBL", lib,"LIB"); 
						objectLockListEntry = objectDescription.getObjectLockList();
						i=0;
						if(objectLockListEntry.length==0){
							stopFlag=true;
							ans = "Listener "+riList+" = UNLOCK";	
						}
					}else{
						stopFlag=true;
						ans = "Listener "+riList+" = DOWN";	
					}
				}
			}
		} catch (AS400Exception e) {
			e.printStackTrace();
		} catch (AS400SecurityException e) {
			e.printStackTrace();
		} catch (ErrorCompletingRequestException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ObjectDoesNotExistException e) {
			e.printStackTrace();
		}finally{
			sys.disconnectAllServices();
		}
		return ans;
	}


	public boolean isProcessActive(String server,String connectU,String connectP,String lib,String prcName){
		//String ans = "NA";
		AS400 sys = new AS400(server+".sapiens.com",connectU,connectP);
		ObjectDescription objectDescription = new ObjectDescription(sys, "*LIBL", lib,"LIB"); 
		try {
			ObjectLockListEntry[] objectLockListEntry = objectDescription.getObjectLockList();
			if(objectLockListEntry==null || objectLockListEntry.length==0){
					return false;
			}
			int i =  objectLockListEntry.length;
			for(int k = 0 ; k < i ; k++){
				ObjectLockListEntry objectLock = objectLockListEntry[k];
				if(objectLock == null) continue;
				String name = objectLock.getJobName();
				//String num = objectLock.getJobNumber();
				//String user = objectLock.getJobUserName();
				if( name.equalsIgnoreCase(prcName))
					return true;
			}
			return false;
		} catch (AS400Exception e) {
			e.printStackTrace();
		} catch (AS400SecurityException e) {
			e.printStackTrace();
		} catch (ErrorCompletingRequestException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ObjectDoesNotExistException e) {
			e.printStackTrace();
		}finally{
			sys.disconnectAllServices();
		}
		return false;
	}

	public String lockLib(String server,String connectU,String connectP,String lib,String act,String riList){
		String ans = "NA";
		AS400 sys = new AS400(server+".sapiens.com",connectU,connectP);
		ObjectDescription objectDescription = new ObjectDescription(sys, "*LIBL", lib,"LIB"); 
		try {
			ObjectLockListEntry[] objectLockListEntry = objectDescription.getObjectLockList();
		
			
			if(objectLockListEntry==null || objectLockListEntry.length==0){
				if(act.equals("UNLOCK"))
					return "Listener "+riList+" = UNLOCK";
				else
					return "Listener "+riList+" = DOWN";
			}
			if(act.equals("?"))
				return "Listener "+riList+" = UP";
			while(objectLockListEntry.length>0){
				ObjectLockListEntry objectLock = objectLockListEntry[0];
				String name = objectLock.getJobName();
				String num = objectLock.getJobNumber();
				String user = objectLock.getJobUserName();
				Job job = new Job(sys, name, user,num);
				try{
					job.end(1);
				}catch(com.ibm.as400.access.AS400Exception ciaaae){
				//	System.out.println(ciaaae.getMessage());
				}
				objectLockListEntry = objectDescription.getObjectLockList();
			}
			return "Listener "+riList+" = unlocked";
		} catch (AS400Exception e) {
			e.printStackTrace();
		} catch (AS400SecurityException e) {
			e.printStackTrace();
		} catch (ErrorCompletingRequestException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ObjectDoesNotExistException e) {
			e.printStackTrace();
		}finally{
			sys.disconnectAllServices();
		}
		return ans;
	}
	
	
	public ArrayList<String[]> killProcessLopp(String server,String connectU,String connectP,String lib,String riList1,boolean killListener,boolean killTomcat){
		ArrayList<String[]> toKill =new ArrayList<String[]>();
		String riList = riList1.toUpperCase();
		AS400 sys = new AS400(server+".sapiens.com",connectU,connectP);
		ObjectDescription objectDescription = new ObjectDescription(sys, "*LIBL", lib,"LIB"); 
		try {
			ObjectLockListEntry[] objectLockListEntry = objectDescription.getObjectLockList();
			if(objectLockListEntry==null || objectLockListEntry.length==0)
				return null;
			for(int i = 0 ; i < objectLockListEntry.length;i++){
				ObjectLockListEntry objectLock = objectLockListEntry[i];
				String name = objectLock.getJobName();
				String num = objectLock.getJobNumber();
				String user = objectLock.getJobUserName();
				String[] arr = {name,num,user};
				if(  (killListener 	&& (name.equalsIgnoreCase(riList) || name.startsWith(riList) )) 	||
					 ( killTomcat   && (  name.equalsIgnoreCase("QP0ZSPWT") 	|| name.equalsIgnoreCase("QZDASOINIT")	) ) )     
					toKill.add(arr);
				
			}
		} catch (AS400Exception e) {
			e.printStackTrace();
		} catch (AS400SecurityException e) {
			e.printStackTrace();
		} catch (ErrorCompletingRequestException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ObjectDoesNotExistException e) {
			e.printStackTrace();
		}finally{
			sys.disconnectAllServices();
		}
		return toKill;
	}

	public void killList(String server,String connectU,String connectP, ArrayList<String[]> toKill ){
		AS400 sys = new AS400(server+".sapiens.com",connectU,connectP);
		
		try {
			for(int i = 0 ; i < toKill.size();i++){
				String name = toKill.get(i)[0];
				String num = toKill.get(i)[1];
				String user = toKill.get(i)[2];
				Job job = new Job(sys, name, user,num);
				try{
					job.end(1);
				}catch(com.ibm.as400.access.AS400Exception ciaaae){
				//	System.out.println(ciaaae.getMessage());
				}
			}
		} catch (AS400Exception e) {
			e.printStackTrace();
		} catch (AS400SecurityException e) {
			e.printStackTrace();
		} catch (ErrorCompletingRequestException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ObjectDoesNotExistException e) {
			e.printStackTrace();
		}finally{
			sys.disconnectAllServices();
		}
	}
/*
	private ArrayList<String> loadI5osProcessesToList(ObjectLockListEntry[] ole){
		ArrayList<String> list = new ArrayList<String>();
		list.add("count = 0");
		for(int i = 0 ; i < ole.length;i++){
			ObjectLockListEntry ol = ole[i];
			String name = ol.getJobName();
			String num = ol.getJobNumber();
			String user = ol.getJobUserName();
			list.add(name+","+num+","+user);
		}
		return list;
	}
	*/
	public boolean i5osKillTomcat(String server,String lib){
		AS400 sys = new AS400(server+".sapiens.com","RIGONEN1","GON09C");
		ObjectDescription objectDescription = new ObjectDescription(sys, "*LIBL", lib,"LIB"); 
		try {
			ObjectLockListEntry[] objectLockListEntry = objectDescription.getObjectLockList();
			if(objectLockListEntry==null || objectLockListEntry.length==0)
				return true;
			int count = 0;
			boolean found = true;
//			HagFrameThread hagFrameThread = new HagFrameThread("replaceWar",loadI5osProcessesToList(objectLockListEntry));
	//		hagFrameThread.start();
			
			while (count < 20 && found) {
				ArrayList<String> list = new ArrayList<String>();
	//			if(count > 0){
		//			hagFrameThread = new HagFrameThread("replaceWar",list);
			//		hagFrameThread.start();
				//	list.add("count = "+count);
			//	}
				list.add("count ="+count);
				count++;
				found = false;
				objectLockListEntry = objectDescription.getObjectLockList();
				if(objectLockListEntry==null || objectLockListEntry.length==0)
					return true;
				for (int r = 0 ; r < objectLockListEntry.length ; r ++){
					ObjectLockListEntry objectLock = objectLockListEntry[r];
					if(objectLock != null){
						String name = objectLock.getJobName();
						String num = objectLock.getJobNumber();
						String user = objectLock.getJobUserName();
						list.add(name+","+num+","+user);
						if(name.equalsIgnoreCase("QP0ZSPWT") || name.equalsIgnoreCase("QZDASOINIT") || name.equalsIgnoreCase("QSQSRVR")){
							if(name.equalsIgnoreCase("QP0ZSPWT")) 
								found = true;
							Job job = new Job(sys, name, user,num);
							try{
								job.end(1);
							}catch(com.ibm.as400.access.AS400Exception ciaaae){
							//	System.out.println(ciaaae.getMessage());
							}
						}
					}
					try{
						Thread.currentThread();
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				//j1.dispose();
		//		hagFrameThread.kill();
			}
			return !found;
		} catch (AS400Exception e) {
			e.printStackTrace();
		} catch (AS400SecurityException e) {
			e.printStackTrace();
		} catch (ErrorCompletingRequestException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ObjectDoesNotExistException e) {
			e.printStackTrace();
		}finally{
			sys.disconnectAllServices();
		}
		return false;
	}
	
	public boolean isTomcatActive(String server,String lib){
		AS400 sys = new AS400(server+".sapiens.com","RIGONEN1","GON09C");
		ObjectDescription objectDescription = new ObjectDescription(sys, "*LIBL", lib,"LIB"); 
		try {
			ObjectLockListEntry[] objectLockListEntry = objectDescription.getObjectLockList();
			if(objectLockListEntry==null || objectLockListEntry.length==0)
				return false;
			for (int r = 0 ; r < objectLockListEntry.length ; r ++){
				ObjectLockListEntry objectLock = objectLockListEntry[r];
				if(objectLock != null){
					String name = objectLock.getJobName();
					if(name.equalsIgnoreCase("QP0ZSPWT"))
						return true;
				}
			}
		} catch (AS400Exception e) {
			e.printStackTrace();
		} catch (AS400SecurityException e) {
			e.printStackTrace();
		} catch (ErrorCompletingRequestException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ObjectDoesNotExistException e) {
			e.printStackTrace();
		}finally{
			sys.disconnectAllServices();
		}
		return false;
	}

	public ArrayList<String> getLocks(String server,String lib){
		ArrayList<String> ans = new ArrayList<String> ();
		AS400 sys = new AS400(server+".sapiens.com","RIGONEN1","GON09C");
		ObjectDescription objectDescription = new ObjectDescription(sys, "*LIBL", lib,"LIB"); 
		try {
			ObjectLockListEntry[] objectLockListEntry = objectDescription.getObjectLockList();
			if(objectLockListEntry==null || objectLockListEntry.length==0)
				return ans;
			for (int r = 0 ; r < objectLockListEntry.length ; r ++){
				ObjectLockListEntry objectLock = objectLockListEntry[r];
				if(objectLock != null){
					//String name = HagUtil.left(objectLock.getJobName()," ",20," ");
					//String user = HagUtil.left(objectLock.getJobUserName()," ",20," ");
					//String num  = HagUtil.left(objectLock.getJobNumber()," ",20," ");
					String name = objectLock.getJobName();
					String user = objectLock.getJobUserName();
					String num  = objectLock.getJobNumber();
					ans.add(name+"-"+user+"-"+num);
				}
			}
		} catch (AS400Exception e) {
			e.printStackTrace();
		} catch (AS400SecurityException e) {
			e.printStackTrace();
		} catch (ErrorCompletingRequestException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ObjectDoesNotExistException e) {
			e.printStackTrace();
		}finally{
			sys.disconnectAllServices();
		}
		return ans;
	}
	/*
	public ArrayList<String> commandCall(String server,String connectU,String connectP,String cmdStr){
		ArrayList<String> ans = new ArrayList<String>();
		AS400 sys = new AS400(server+".sapiens.com",connectU,connectP);
		CommandCall cmd = new CommandCall(sys);
		try {
			cmd.run(cmdStr);
			AS400Message[] messageList = cmd.getMessageList();
			for(int i = 0 ; i <	messageList.length	;	i++)
				ans.add(messageList[i].getText());
		} catch (AS400SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ErrorCompletingRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			sys.disconnectAllServices();
		}
		
		//sys.disconnectService(AS400.COMMAND);
		   return ans;
	}
	*/
	public String jetJobNumber (String server,String connectU,String connectP,String jobU,String jobN){
		   AS400 sys = new AS400(server+".sapiens.com",connectU,connectP);
		   JobList jobList = new JobList(sys);
        // Get the list of active jobs.
		   Enumeration<?> list = null;
		   try {
			   list = jobList.getJobs();
		   } catch (AS400SecurityException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   } catch (ErrorCompletingRequestException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   } catch (InterruptedException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   } catch (IOException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   } catch (ObjectDoesNotExistException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   }finally{
				sys.disconnectAllServices();
			}
        // For each active job on the system
        // print job information.
		   while (list.hasMoreElements())
		   {
			   Job j = (Job) list.nextElement();
			   String user1 =  j.getUser();
			   if(user1.equalsIgnoreCase(jobU)){
				   String name1 =  j.getName();
				   if(name1.equalsIgnoreCase(jobN)){
					   String num1 =  j.getNumber();
					//   System.out.println(name1 + "." +user1 + "." +  num1);
					   return "ACTIVE("+jobN+"."+jobU+"."+num1+")";
				   }
			   }
		   }
		   return "NOT ACTIVE("+jobN+"."+jobU+")";
		}
	public ArrayList<String> getJobLog(String server,String userC,String passwordC,String name,String user,String num){
		ArrayList<String> rst = new ArrayList<String>();
		AS400 sys = new AS400(server,"RIGONEN1","GON09C");
		Job job = new Job(sys, name, user,num);
		JobLog jlog = new JobLog(sys, job.getName(), job.getUser(), job.getNumber());
		Enumeration<?> messageList = null;
		try {
			messageList = jlog.getMessages();
			while (messageList.hasMoreElements()){
				AS400Message message = (AS400Message) messageList.nextElement();
				rst.add(message.getText());
			}
		} catch (AS400SecurityException e) {
			rst.add(e.toString());
			e.printStackTrace();
			return rst;
		} catch (ErrorCompletingRequestException e) {
			rst.add(e.toString());
			e.printStackTrace();
			return rst;
		} catch (InterruptedException e) {
			rst.add(e.toString());
			e.printStackTrace();
			return rst;
		} catch (IOException e) {
			rst.add(e.toString());
			e.printStackTrace();
			return rst;
		} catch (ObjectDoesNotExistException e) {
			rst.add(e.toString());
			e.printStackTrace();
			return rst;
		}finally{
			sys.disconnectAllServices();
		}
		
		return rst;
	}
	
	public ArrayList<String> getActiveJobs(String server,String omit){
		
		omit=omit.trim();	
		ArrayList<String> rst = new ArrayList<String>();
		AS400 sys = new AS400(server+".sapiens.com","ZSAVI2","YOSSI");
		JobList jobList = new JobList(sys);
		Enumeration<?> list = null;
		rst.add("RGX~Server~Name~User~Number~status~statusMessage~SubSystem~CurrentFolder~CPU-Used~InteractiveTransactions");
		rst.add("y25~y100~y100~y100~y70~y80~y100~y120~y100~y100~y100");
		try {
			list = jobList.getJobs();
			while (list.hasMoreElements())
			{
				Job j = (Job) list.nextElement();
				String 	name1 	= j.getName();
				String 	user1 	= j.getUser();
				String 	num1 	= j.getNumber();
				String 	stat1 	= j.getStatus();
				String 	stat2 	= j.getStatusMessageHandling();
				String 	sub1 	= j.getSubsystem();
				String 	lib1 	= j.getCurrentLibrary();
				int 	cpu1 	= j.getCPUUsed();
				int 	inter1 	= j.getInteractiveTransactions();
				if(stat1 ==null || stat1.length()<1 ) stat1 	=	" ";
				if(stat2 ==null || stat2.length()<1 ) stat2 	=	" ";
				if(sub1  ==null ||  sub1.length()<1) 	sub1 	=	" ";
				if(lib1  ==null || lib1.length()<1)	lib1 	=	" ";

				if(omit.indexOf("*") < 0 && !user1.equalsIgnoreCase(omit))	
					rst.add("RGX~"+server+"~"+name1+"~"+user1+"~"+num1+"~"+stat1+"~"+stat2+"~"+sub1+"~"+lib1+"~"+cpu1+"~"+inter1);
				else if(omit.indexOf("*") == 0 && !user1.endsWith(omit.substring(1,omit.length())))	
					rst.add("RGX~"+server+"~"+name1+"~"+user1+"~"+num1+"~"+stat1+"~"+stat2+"~"+sub1+"~"+lib1+"~"+cpu1+"~"+inter1);
				else if(omit.indexOf("*") > 0 && !user1.startsWith(omit.substring(0,omit.indexOf("*"))))	
					rst.add("RGX~"+server+"~"+name1+"~"+user1+"~"+num1+"~"+stat1+"~"+stat2+"~"+sub1+"~"+lib1+"~"+cpu1+"~"+inter1);
			}
		} catch (AS400SecurityException e) {
			e.printStackTrace();
		} catch (ErrorCompletingRequestException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ObjectDoesNotExistException e) {
			e.printStackTrace();
		}finally{
			sys.disconnectAllServices();
		}
		return rst;
	}
	
	public ArrayList<String> 	cmdCallInteractive(String host,String user,String password,String cmd)  {
		//0=Ok 		//1=Exception		//2=Errors occurred in command.
		host=host+".SAPIENS.COM";
		ArrayList<String> ans = new ArrayList<String>();
		ans.add("0~INIT");
		ans.add(cmd);
		AS400 system = new AS400(host,user,password);    
		try{
			CommandCall commandCall = new CommandCall(system);
			Job job = commandCall.getServerJob();
			//commandCall.setThreadSafe(true);
			ans.add(job.getName()+"/"+job.getUser()+"/"+job.getNumber());
			boolean  rc = commandCall.run(cmd); 
			AS400Message[] as400Message = commandCall.getMessageList();
			for (int i=0; i<as400Message.length; i++){    
				String temp = as400Message[i].getText();
				if(temp.equals("Errors occurred in command"))
	       			ans.set(0,"2~"+temp+"~"+cmd);
	       		ans.add(temp);
	        }
			ArrayList<String> jl = getJobLog(host,"RIGONENE1","GON09C",job.getName(), job.getUser(), job.getNumber());
			ans.add("joblog of "+job.getName()+"/"+job.getUser()+"/"+job.getNumber()+":");
			HagUtil.appendArrayList2ArrayList(ans, jl);
		}catch (AS400SecurityException e) {
			ans.set(0,"1~Exception~"+cmd+"~"+e.getMessage());
		}catch (ErrorCompletingRequestException e) {
			ans.set(0,"1~Exception~"+cmd+"~"+e.getMessage());
		}catch (IOException e) {
			ans.set(0,"1~Exception~"+cmd+"~"+e.getMessage());
		}catch (InterruptedException e) {
			ans.set(0,"1~Exception~"+cmd+"~"+e.getMessage());
		}catch (PropertyVetoException e) {
			ans.set(0,"1~Exception~"+cmd+"~"+e.getMessage());
		}catch (Exception e) {
			ans.set(0,"1~Exception~"+cmd+"~"+e.getMessage());
		}finally{
			system.disconnectAllServices(); 
		}
	 	return ans;    
	}
	public  HagStringList runCmd2(HagStringList cmd,String log) {
		HagStringList ans = new HagStringList();
		HagStringList cmd1 = new HagStringList();
	    for(int i = 0 ; i < cmd.size(); i++){
	        String temp = cmd.get(i).toString();
	        if(temp.startsWith("|")){
	            if(cmd1.size() > 0)
//	               ans.add(runGroupCmd(cmd1));
	            	HagUtil.appendArrayList2ArrayList(ans,runGroupCmd(cmd1,log));
            	cmd1.clear();
	            String host1 = HagUtil.getWord0(temp,"|",0,true);
	            String user1 = HagUtil.getWord0(temp,"|",1,true);
	            String pass1 = HagUtil.getWord0(temp,"|",2,true);
	               // ans.add(connect2server(host1,user1,pass1));
	            cmd1.add(host1); 
	            cmd1.add(user1); 
	            cmd1.add(pass1); 
	         }else{
	            cmd1.add(temp); 
	         }
	    }
	    if(cmd1.size() > 0)
//	        ans.add(runGroupCmd(cmd1));
	    	HagUtil.appendArrayList2ArrayList(ans,runGroupCmd(cmd1,log));
	    return ans;
   }
	
	public ArrayList<String> runGroupCmd(ArrayList<String> cmd1,String log){
	        this.host2 = cmd1.get(0).toString();
	        this.user2 = cmd1.get(1).toString();
	        this.pass2 = cmd1.get(2).toString();
	        ArrayList<String> ans = new ArrayList<String>();
	        
	        if(host2.indexOf(".")<0 )
	        	host2=host2+".SAPIENS.COM";
	       // pass = pass2;
	        ans.add("\ncommand=connect to "+host2+" user="+user2+"\n");
	        AS400 myServer1 = new AS400(host2, user2, pass2);
	        //SocketProperties socketProperties = new SocketProperties();
	        //socketProperties.setKeepAlive(true);
	        //socketProperties.setLoginTimeout(150000);
	        //socketProperties.setReceiveBufferSize(2048);
	        //socketProperties.setSendBufferSize(2048);
	        //socketProperties.setSoLinger(30);
	        //socketProperties.setSoTimeout(100000);
	        //socketProperties.setTcpNoDelay(true);
	        //myServer1.setSocketProperties(socketProperties);
	        try{
	            for(int i = 3 ; i < cmd1.size(); i++){
	            	String temp = cmd1.get(i).toString();
	            	String log1 = log;
	            	int k=i-2;
	            	if(log!=null)
	            		log1=log+"_"+k;
	                ArrayList<String> ansSingle = runSingleCmd(temp,myServer1, log1);
	               // HagUtil.hagSleep(5000);
	                for(int j = 0 ; j < ansSingle.size();j++){
	                    ans.add(ansSingle.get(j).toString());
	                }
	            }
	        }catch(Exception e){
	            e.printStackTrace();
	            System.out.println("Exception"+e);
	        //}finally{
			//	myServer.disconnectAllServices();
			}
	        myServer1.disconnectService(AS400.COMMAND);
	        return ans;
	    }	
	/*
	private static String getJobId(String number, String user, String name) {
		return number + "/" + user + "/" + name;
	}
	*/
	/*
	private void checkQsysopr(Job jj,boolean act,AS400 myServer ){
	//	HagUtil.p("31");
		String jobId = getJobId(jj.getNumber(), jj.getUser(), jj.getName()); 
	//	HagUtil.p("32");
		MessageQueue queue = new MessageQueue(myServer, "/QSYS.LIB/QSYSOPR.MSGQ");
	//	HagUtil.p("33");
		
		Enumeration<QueuedMessage> messages;
	//	HagUtil.p("34");
		try {
	//		HagUtil.p("35");
			int aa  = queue.getLength();
	//		HagUtil.p(aa);
			
			messages = queue.getMessages();
	//		HagUtil.p("36");
			while (messages.hasMoreElements()) {
	//			HagUtil.p("37");
				QueuedMessage message = (QueuedMessage) messages.nextElement();
	//			HagUtil.p("38");
				message.load();
	//			HagUtil.p("39");
		//		HagUtil.p(message.getFromJobName()+","+message.getUser()+","+message.getFromJobNumber());
				if (message.getType() == AS400Message.INQUIRY) {
					message = queue.receive(message.getKey(), 0, MessageQueue.SAME, MessageQueue.ANY);
					String number = message.getFromJobNumber();
					String user = message.getUser();
					String name = message.getFromJobName();
					if (jobId.equals(getJobId(number,user,name))) {
						//HagUtil.dspMsgBox(message.getMessage(), JOptionPane.INFORMATION_MESSAGE, null, null);
						System.out.println(message.getMessage());
						//	queue.reply(message.getKey(), response);
					}
				}
			}
		} catch (AS400SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ErrorCompletingRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ObjectDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	/*
	public ArrayList<String> runGroupCmd0(ArrayList<String> cmd1){
        String host2 = cmd1.get(0).toString();
        String user2 = cmd1.get(1).toString();
        String pass2 = cmd1.get(2).toString();
        ArrayList<String> ans = new ArrayList<String>();
        
        if(host2.indexOf(".")<0 )
        	host2=host2+".SAPIENS.COM";
       // pass = pass2;
        ans.add("\ncommand=connect to "+host2+" user="+user2+"\n");
        try{
        	myServer = new AS400(host2, user2, pass2);
        	
            for(int i = 3 ; i < cmd1.size(); i++){
                final String temp = cmd1.get(i).toString();
                	Thread th = new Thread(new Runnable() {
            			public void run() {
            				CommandCall command = new CommandCall(myServer);
            				jjj = command.getServerJob();
            				ArrayList<String> ansSingle = runSingleCmd2(temp);
            			}
            	  	});
            	  	th.start();
                
                ArrayList<String> ansSingle = runSingleCmd2(temp);
               
                // HagUtil.hagSleep(5000);
                for(int j = 0 ; j < ansSingle.size();j++){
                    ans.add(ansSingle.get(j).toString());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Exception"+e);
        }finally{
			myServer.disconnectAllServices();
		}
        myServer.disconnectService(AS400.COMMAND);
        return ans;
    }	
	public ArrayList<String> runSingleCmd1(String cmd){
	
	}
	public ArrayList<String> runSingleCmd2(String cmd){
	    CommandCall command = new CommandCall(myServer);
		ArrayList<String> ans = new ArrayList<String>();
	    ans.add("command="+cmd+"\n");
	    try
	    {
	    	jjj = command.getServerJob();
	    	HagUtil.p("jjj="+jjj.getName()+","+jjj.getUser()+","+jjj.getNumber());
	    if (command.run(cmd) != true){
	    //    	hagGridPanel.hagWorkerChange("add","   -"+"2\n",false,false,Color.RED,null,50);
	        	ans.add("command.run=false");
	        }
	        AS400Message[] messagelist = command.getMessageList();
	        for (int i = 0; i < messagelist.length; i++) {
	           ans.add(messagelist[i].getText()+"\n");
	           if(hagGridPanel != null)
	   				hagGridPanel.hagWorkerChange("add","   -"+messagelist[i].getText()+"\n",false,false,Color.blue,null,-1);
	        }
	    }
	    catch (Exception e)
	    {
        //	hagGridPanel.hagWorkerChange("add","   -"+"6\n",false,false,Color.RED,null,50);
	    	System.out.println("Command " + command.getCommand() + " issued an exception!");
	        ans.add("*"+command.getCommand()+"* issued an exception! "+e.getMessage());
	        ans.add(e.toString());
	    	
	    }
	    return ans;

	}
*/
	public ArrayList<String> runSingleCmd(String cmd,AS400 myServer1 ,String log){
		//if(hagGridPanel != null)
			//hagGridPanel.hagWorkerChange("add","   -"+cmd+"\n",false,false,Color.black,null,50);
		HagCommandCall command = new HagCommandCall(myServer1,host2,user2,pass2);
		return command.run(cmd,log);
		
	}

	/*
	public HagI5OS(String host,String user,String pass){
		
		myServer = new AS400(host, user, pass);
	}
	
	public HagVector runCmd(String cmd1,int num){
	    CommandCall command = new CommandCall(myServer);
	    HagVector ans = new HagVector();
	    ans.add("command="+cmd1);
	    try
	    {
	        // Run the command "CRTLIB FRED."
	        if (command.run(cmd1) != true)
	        {
	            // Note that there was an error.
	            //System.out.println("Command failed!");
	           // ans.add("command="+cmd1);
	     	   
	           // return ("Command failed!");
	        }
	        // Show the messages (returned whether or not there was an error.)
	        AS400Message[] messagelist = command.getMessageList();
	        String aaa = "";
	        for (int i = 0; i < messagelist.length; ++i)
	        {
	            // Show each message.
	            //System.out.println(messagelist[i].getText());
	           ans.add(messagelist[i].getText());
	     	   
	        }
//	        return (aaa);
	    }
	    catch (Exception e)
	    {
	        System.out.println("Command " + command.getCommand() + " issued an exception!");
	        ans.add("issued an exception!");
	        //  e.printStackTrace();
	       // return("Command " + command.getCommand() + " issued an exception!");
	    }
	    // Done with the server.
	    myServer.disconnectService(AS400.COMMAND);
	    return ans;
	}
	*/
/*
	public String runCmd(String cmd1,int num){
		String answer1 = "";
		System.out.println("A");
//		CommandCall cmd = new CommandCall(myServer, cmd1);
		CommandCall command = new CommandCall(myServer);

		System.out.println("B");
		try{
			System.out.println("C");
			if (!cmd.run()){
			    System.out.println("D");
			    AS400Message[] msgList = cmd.getMessageList();
			    System.out.println("E");
			    for (int i = 0; i < msgList.length; ++i){
			        System.out.println("F");
			        int b = i+1;
			        System.out.println("G");
			        answer1 = answer1 +"\ncmd "+num+" Message"+b+"="+ msgList[i].getText();
			        System.out.println("H");
				}
			    System.out.println("I");
			}
		}catch (InterruptedException e2) {
			return "InterruptedException "+cmd;
		}catch (AS400SecurityException e2) {
			return "AS400SecurityException "+cmd;
		}catch (ErrorCompletingRequestException e2) {
			return "ErrorCompletingRequestException "+cmd;
	//	}catch (PropertyVetoException e) {
	//		return "PropertyVetoException "+cmd;
		}catch (IOException e2) {
			return "IOException "+cmd;
		}catch (Exception e) {
			return "Exception "+cmd;
		}
		return answer1;
		//
	
		CommandCall cmd = new CommandCall(myServer);
		try{
			cmd.run(cmd1);
			cmd.w
		} catch (InterruptedException e2) {
			return "InterruptedException "+cmd;
		}catch (AS400SecurityException e2) {
			return "AS400SecurityException "+cmd;
		}catch (ErrorCompletingRequestException e2) {
			return "ErrorCompletingRequestException "+cmd;
		}catch (PropertyVetoException e) {
			return "PropertyVetoException "+cmd;
		}catch (IOException e2) {
			return "IOException "+cmd;
		}catch (Exception e) {
			return "Exception "+cmd;
		}
		//		Print out the messages that came back
		AS400Message[] messageList = cmd.getMessageList();
		int numberOfMessages = messageList.length;
		String answer1 = "";
		for (int i = 0; i < numberOfMessages; i++){
			int b = i+1;
			answer1 = answer1 +"\ncmd "+num+" Message"+b+"="+ messageList[i].getText();
		}
		return answer1;
		
	}
	*/
	/*
	public ArrayList<String> test1(String cmd){
	    CommandCall command = new CommandCall(myServer);
	    ArrayList<String> ans = new ArrayList<String>();
	    ans.add("command="+cmd+"\n");
	    try
	    {
	        if (command.run(cmd) != true)
	        { 
	        	System.out.println("failed");
	        }
	        AS400Message[] messagelist = command.getMessageList();
	        for (int i = 0; i < messagelist.length; ++i)
	        {
	           ans.add(messagelist[i].getText()+"\n");
	        }
	    }
	    catch (Exception e)
	    {
	        System.out.println("Command " + command.getCommand() + " issued an exception!");
	        ans.add("issued an exception!");
	    }
	    return ans;
	}
	public void test() {
	    String host2 = "bora.sapiens.com";
        String user2 = "sapiens";
        String pass2 = "jamesbond";
        ArrayList<String> cmd1 = new ArrayList<String>();
        ArrayList<String> ans = new ArrayList<String>();
        String cmd = "RTVLIBD LIB(hagtemp)";
        cmd1.add(cmd);

        try{
        	myServer = new AS400(host2, user2, pass2);
        	for(int i = 0 ; i < cmd1.size(); i++){
                String temp = cmd1.get(i).toString();
                ArrayList<String> ansSingle = test1(temp);
                for(int j = 0 ; j < ansSingle.size();j++){
                    ans.add(ansSingle.get(j).toString());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Exception"+e);
        }finally{
			myServer.disconnectAllServices();
		}
        for(int i = 0; i<ans.size();i++)
        	System.out.println(ans.get(i));
          	
        myServer.disconnectService(AS400.COMMAND);
	}
*/
	public String stopListenrTomcat(String server,String connectU,String connectP,String lib,String riList,boolean killListener ,boolean killTomcat) {
		ArrayList<String[]> toKill = killProcessLopp(server,connectU,connectP,lib,riList,killListener,killTomcat);
	    while (toKill !=null && toKill.size()>0){
	    	killList(server,connectU,connectP,toKill);
    	    toKill = killProcessLopp(server,connectU,connectP,lib,riList,killListener,killTomcat);
	    }
	    String  ans = "";
	    if(killListener) ans = ans + "Listener "+riList+" killed";
	    if(killTomcat){
	    	ans = ans + "  Tomcat killed";
	    
	    }
	    return ans;
	}

	public ArrayList<String[]> debug(String server,String connectU,String connectP,String lib,String riList1){
		ArrayList<String[]> toKill =new ArrayList<String[]>();
		String riList = riList1.toUpperCase();
		AS400 sys = new AS400(server+".sapiens.com",connectU,connectP);
		ObjectDescription objectDescription = new ObjectDescription(sys, "*LIBL", lib,"LIB"); 
		try {
			ObjectLockListEntry[] objectLockListEntry = objectDescription.getObjectLockList();
			if(objectLockListEntry==null || objectLockListEntry.length==0)
				return null;
			for(int i = 0 ; i < objectLockListEntry.length;i++){
				ObjectLockListEntry objectLock = objectLockListEntry[i];
				String name = objectLock.getJobName();
				String num = objectLock.getJobNumber();
				String user = objectLock.getJobUserName();
				String[] arr = {name,num,user};
				if(	name.equalsIgnoreCase(riList) 		|| name.startsWith(riList) || 
					name.equalsIgnoreCase("QP0ZSPWT") 	|| name.equalsIgnoreCase("QZDASOINIT")	)
					toKill.add(arr);
			}
		} catch (AS400Exception e) {
			e.printStackTrace();
		} catch (AS400SecurityException e) {
			e.printStackTrace();
		} catch (ErrorCompletingRequestException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ObjectDoesNotExistException e) {
			e.printStackTrace();
		}finally{
			sys.disconnectAllServices();
		}
		return toKill;
	}
	
	public void test1(){
		AS400 sys = new AS400("CLIO.sapiens.com","ZSAVI2","YOSSI");
		
		IFSJavaFile cccc = new IFSJavaFile(sys, "/QSYS.LIB/CFG.LIB/REXX.FILE");
		  
		// Create a DirFilter object and get the directories.
		//  DirFilter filter = new DirFilter();
		File[] mbrList = cccc.listFiles();
		for(int i = 0 ; i < mbrList.length;i++){
			String temp = mbrList[i].toString();
			String temp1 = temp.substring(temp.lastIndexOf("\\")+1,temp.lastIndexOf("."));
			System.out.println(temp1);
		}
	
		sys.disconnectAllServices();
	

	
	}
	
	public ArrayList<String> davidb(String server,String connectU,String connectP,String dbid){
		AS400 sys = new AS400(server+".sapiens.com",connectU,connectP);
		ArrayList<String> ans = new ArrayList<String>();
		try {
			//////
			String cmdStr = "CALL INL"+dbid;	
			ans.add("----------------");
			ans.add(cmdStr);
			CommandCall cmd = new CommandCall(sys);
			cmd.run(cmdStr);
			AS400Message[] messageList = cmd.getMessageList();
			for(int k = 0 ; k <	messageList.length	;	k++)
				ans.add(messageList[k].getText());
			//////
			String cmdStr1 = "RUNPROCSET DBID(F3) PROCSETS(DIM) CALCDATE('20270201') CALCYEAR(2027)";	
			ans.add("----------------");
			ans.add(cmdStr1);
			CommandCall cmd1 = new CommandCall(sys);
			cmd1.run(cmdStr1);

			Job jj = cmd1.getServerJob();
			
			
			    SpooledFileOpenList list = new SpooledFileOpenList(sys);
			    // Get all of myUserID's spooled files.
			    list.setFilterUsers(new String[] { "*CURRENT" } );
			    // Sort the list by job number in ascending order.
			    list.addSortField(SpooledFileOpenList.JOB_NUMBER, true);
			    try {
					list.open();
				} catch (ObjectDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OpenListException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    Enumeration items;
				try {
					items = list.getItems();
				    while (items.hasMoreElements())
				    {
				        SpooledFileListItem item = (SpooledFileListItem)items.nextElement();
				        System.out.println(item.getJobName() + "/" + item.getJobUser() + "/" + item.getJobNumber() + " - " + item.getName() + ", " + item.getNumber());
				    }
				    list.close();

				} catch (ObjectDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OpenListException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
				SpooledFile splf = new SpooledFile( sys, // AS400
						"TRNPROCSET", // splf name
						1721, // splf number
						"QPRTJOB", // job name
						"RIDEMO", // job user
						"898234" ); // job number
/*
				try {
					InputStream in = splf.getInputStream(null);
					String ffff = HagUtil.convertStreamToString(in);
					HagUtil.p1(ffff);
				} catch (RequestNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	*/
				PrintParameterList objPPL = new PrintParameterList();
		        PrintObjectTransformedInputStream objInputStream;
		        try {
					objInputStream = splf.getTransformedInputStream(objPPL);
					  int intInputStreamSize = objInputStream.available();           
				        byte[] objSpoolByte = new byte[intInputStreamSize];
				        objInputStream.read(objSpoolByte); 
				        AS400Text textConverter = new AS400Text(intInputStreamSize,37);  
				        String strConvertedFile = ((String) textConverter.toObject(objSpoolByte));
				       // HagUtil.p1(strConvertedFile);
				} catch (RequestNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      
		
			
			
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		} catch (AS400Exception e1) {
			e1.printStackTrace();
		} catch (AS400SecurityException e1) {
			e1.printStackTrace();
		} catch (ErrorCompletingRequestException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
	//	} catch (ObjectDoesNotExistException e) {
			// TODO Auto-generated catch block
	//		e.printStackTrace();
		}finally{
			sys.disconnectAllServices();
		}
		
		return ans;
	}
	/*
	public static void main(String args[]) {
	    HagI5OS hx = new HagI5OS();
	    hx.test1();
	  //  ArrayList<String[]>  aa = hx.debug("clio","RIHAGAY1","GON09C","I9V0980DB2","IL4042");
	  //  HagUtil.p1a("aaa");
	    
	    //String str = args[0];
		//String aa = hx.go(str);
		//System.out.println(aa);
	}
	*/
	
}
