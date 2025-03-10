package com.hag.hagay;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;



public class ey_Gsetlibl {
	
	public ey_Gsetlibl(){
	}
//	######################################################################################################		
	public String SimpleZIP(String comp_name, String bkfolder,String zipnamepath){
		String rtn="";
		String cmd="";
		zipnamepath="\\\\" + comp_name + "\\" + zipnamepath.replace(":", "$");
		bkfolder="\\\\" + comp_name + "\\" + bkfolder.replace(":", "$");
		cmd = "\\\\" + comp_name +"\\c$\\backups\\extzip_pkzip25.exe -add -recurse -path=relative " + zipnamepath +" "+ bkfolder + "\\*.*";
		String ret = exec(cmd);
		rtn=ret;
//		System.out.println(cmd);
		if (ret.indexOf("(E") > -1) {
			rtn="1~ZIP " + bkfolder + " - FINISHED WITH ERROR ~" + ret;
		}
		if (ret.indexOf("done") > -1) {
			rtn="0~ZIP " + bkfolder + " - FINISHED SUCCESSFULLY~" + ret;
		}
		return rtn;
	}
//	######################################################################################################		
	public String SimpleZIPRestore(String comp_name,String zipnamepath, String resotrefolder){
		String rtn="";
		String cmd="";
		
		zipnamepath="\\\\" + comp_name + "\\" + zipnamepath.replace(":", "$");
		resotrefolder="\\\\" + comp_name + "\\" + resotrefolder.replace(":", "$");
		cmd="cmd /c del /F /Q " + resotrefolder + "\\*.*";
		String ret = exec(cmd);
		cmd = "\\\\" + comp_name +"\\c$\\backups\\pkunzip.exe "+ zipnamepath +" "+ resotrefolder ;
		ret = exec(cmd);
		rtn=ret;
//		System.out.println("ww"+ret);
		if (ret.indexOf("returned") > -1) {
			rtn="1~UNZIP " + resotrefolder + " - RETURNED ERROR ~" + ret;
		}
		else {
			rtn="0~UNZIP " + resotrefolder + " - FINISHED SUCCESSFULLY~" + ret;
		}
		return rtn;
	}
//######################################################################################################		
public String SimpleBK(String comp_name, String dbname,String bkpath,String WT_OP ){
	String rtn="";
	String cmd="";
	dbname="["+dbname+"]";
	cmd = "OSQL -S " + comp_name + "  -U cfg -P cfg09c -Q " + "\"BACKUP DATABASE " + dbname + " TO DISK ='" + bkpath + "' " + WT_OP + "\"";
	String ret = exec(cmd);
	rtn=ret;
	//System.out.println(cmd);
	if (ret.indexOf("terminating") > -1) {
		rtn="1~BACKUP DATABASE :"+ dbname +" is terminating abnormally. ~" + ret;
	}
	if (ret.indexOf("successfully") > -1) {
		rtn="0~BACKUP DATABASE :"+ dbname +" successfully ~" + ret;
	}
	
	return rtn;
}
public String SimpleBK1(String comp_name, String dbname,String bkpath,String WT_OP ){
	String rtn="";
	String cmd="";
	//cmd = "OSQL -U cfg -P cfg09c -S " + comp_name + " -Q " + "\"BACKUP DATABASE " + dbname + " TO DISK ='" + bkpath + "' " + WT_OP + "\"";
	cmd = "OSQL -U cfg-burner.sapiens.int -P gon09c -S " + comp_name + " -Q " + "\"BACKUP DATABASE " + dbname + " TO DISK ='" + bkpath + "' " + WT_OP + "\"";
	
	//HagUtil.p(cmd);
	String ret = exec(cmd);
	//HagUtil.p(ret);
	rtn=ret;
	//System.out.println(cmd);
	if (ret.indexOf("terminating") > -1) {
		rtn="1~BACKUP DATABASE :"+ dbname +" is terminating abnormally. ~" + ret;
	}
	if (ret.indexOf("successfully") > -1) {
		rtn="0~BACKUP DATABASE :"+ dbname +" successfully ~" + ret;
	}
	
	return rtn;
}
//######################################################################################################		
public String FullBK(String comp_name, String dbname,String bkpath,String WT_OP ){
	String rtn="";
	String cmd="";
	cmd = "OSQL -S " + comp_name + " -U cfg -P cfg09c -Q \"ALTER DATABASE " + dbname +" SET RECOVERY FULL BACKUP LOG " + dbname +" TO DISK ='" + bkpath + "'\";";
	String ret = exec(cmd);

	if (ret.indexOf("terminating") > -1) {
		rtn="1~FULL BACKUP DATABASE :"+ dbname +" is terminating abnormally. ~" + ret;
	}
	if (ret.indexOf("successfully") > -1) {
		rtn="0~FULL BACKUP DATABASE :"+ dbname +" successfully ~" + ret;
	}
	return rtn;
}
//######################################################################################################		
public String SimpleRestore(String comp_name, String dbname,String bkpath,String WT_OP ){
	
	String rtn=" ";
	String cmd=" ";
	
	rtn=Unlock_DB(comp_name,dbname,"Single_User WITH ROLLBACK IMMEDIATE");
	
	cmd = "OSQL -S " + comp_name + " -U cfg -P cfg09c -Q \"" + " RESTORE DATABASE " + dbname + " FROM DISK ='" + bkpath + "' " + WT_OP + "\"";
	
	String ret = exec(cmd);

	if (ret.indexOf("terminating") > -1) {
		rtn="1~RESTORE DATABASE :"+ dbname +" is terminating abnormally. ~" + ret;
	}
	if (ret.indexOf("successfully") > -1) {
		rtn="0~RESTORE DATABASE :"+ dbname +" successfully ~" + ret;
	}
	Unlock_DB(comp_name,dbname,"Multi_User ");
	return rtn;
}
//######################################################################################################		
public String Unlock_DB(String comp_name, String dbname,String sing_mult ){
	String rtn=" ";
	String cmd=" ";
	cmd = "OSQL -S " + comp_name + " -U cfg -P cfg09c -Q \"" + "USE MASTER  ALTER DATABASE " + dbname + " SET "+ sing_mult +"\"" ;
	
	String ret = exec(cmd);
	//System.out.println(ret);
	if (ret.indexOf("terminating") > -1) {
		rtn="1~Unlock DATABASE :"+ dbname +" is terminating abnormally. ~" ;
	}
	if (ret.indexOf("Estimated") > -1) {
		rtn="0~Unlock DATABASE :"+ dbname +" Done successfully ~" ;
	}
	if (ret.indexOf("") > -1) {
		rtn="0~Unlock DATABASE :"+ dbname +" Was Not Needed ~" ;
	}
	return rtn;
}
//######################################################################################################		
public String CloneRestore(String comp_name, String dbname,String bkpath ){
	
	String rtn="";
	
	rtn=Unlock_DB(comp_name,dbname,"Single_User WITH ROLLBACK IMMEDIATE");
	
	String cmd = "OSQL -S " + comp_name + " -U cfg -P cfg09c -Q \"" + " RESTORE FILELISTONLY FROM DISK = '"+bkpath+"';\"" ;
	String ret = exec(cmd);
    rtn=ret;
    
    if (ret.indexOf("terminating") > -1) {
		rtn="1~RESTORE DATABASE :"+ dbname +"step 1 is terminating abnormally. ~" + ret;
		return rtn;
	}
    
 //   System.out.println("cxcxc"+ ret + "\r\n");
    String cloning="MOVE '";
 //   dbname="moko";
    
    int pos1=rtn.indexOf(".ldf");
    int pos2=rtn.lastIndexOf(":", pos1);
    String str1=rtn.substring(pos2-1, pos1+4);
      
    pos1=str1.indexOf(".ldf");
    int pos3=str1.lastIndexOf("\\", pos1);
    String str4=str1.substring(0, pos3+1) + dbname + "_log.ldf' , NOUNLOAD,  REPLACE,  STATS = 10 \"";
    
    str1=rtn.substring(0,pos2-2).trim();
    String str3=str1.substring(str1.lastIndexOf(" ")+1,str1.length());
    
    cloning=cloning + str3 + "' TO '" + str4;
///    System.out.println("cxcxc"+ cloning + "\r\n");
    
    pos1=rtn.indexOf(".mdf");
    pos2=rtn.lastIndexOf(":", pos1);
    str1=rtn.substring(pos2-1, pos1+4);   
       
    pos1=str1.indexOf(".mdf");
    pos3=str1.lastIndexOf("\\", pos1);
    str4=str1.substring(0, pos3+1) + dbname + ".mdf' , ";
 ///   System.out.println("mm"+ str4 + "\r\n");
    
    str1=rtn.substring(0,pos2-2).trim();
    String str2=str1.substring(str1.lastIndexOf(" ")+1,str1.length());
///    System.out.println("ss"+ str2 + "\r\n");
    cloning=" MOVE '" + str2 + "' TO '" + str4 + cloning;

//    cloning = "OSQL -S " + comp_name + " -E -Q \"" +" RESTORE DATABASE " + dbname + " FROM DISK ='" + bkpath + "' " + WT_OP +","+ cloning;
    cloning = "OSQL -S " + comp_name + " -U cfg -P cfg09c -Q \"" +" RESTORE DATABASE " + dbname + " FROM DISK ='" + bkpath + "' WITH FILE = 1,"+ cloning;
    
///    System.out.println(cloning + "\r\n");
    ret = exec(cloning);
    
	if (ret.indexOf("terminating") > -1) {
		rtn="1~CLONE DATABASE :"+ dbname +"step 2 is terminating abnormally. ~" + ret;
	}
	if (ret.indexOf("successfully") > -1) {
		rtn="0~CLONE DATABASE :"+ dbname +" successfully ~" + ret;
	}
	Unlock_DB(comp_name,dbname,"Multi_User ");
	return rtn;
}
//######################################################################################################		
public String ShowLock(String comp_name,String dbname){
	String rtn="";
	String cmd="";
	cmd = "OSQL -S " + comp_name + " -U cfg -P cfg09c -Q \"USE [master] DECLARE @return_value int EXEC @return_value = [dbo].[sp_lock2]  " ;
	cmd = cmd + "SELECT *  FROM master.dbo.ShowLock where dbname like '" + dbname + "'" + "\";";
///	Vector ret = execvector(cmd);
	String ret = exec(cmd);
//	cmd=new Date().toString() + cmd;
//	System.out.println(ret);
//	System.out.println(cmd);
	if (ret.indexOf("affected") > -1) {
//		System.out.println("dddddd" + cmd + "\r\n");
		rtn="0~ShowLock Ended successfully ~" + ret;
	}
	else{
		rtn="1~ShowLock Ended With Error. ~" + ret;
	}	
	return rtn;
}
//######################################################################################################		
public String Restoring_to_point_in_time(String comp_name,String dbname,String bkpath,String STOPAT,int fno,String logbak ){
  String rtn="";
  
  rtn=Unlock_DB(comp_name,dbname,"Single_User WITH ROLLBACK IMMEDIATE");
  
  String cmd = "OSQL -S " + comp_name + " -U cfg -P cfg09c -Q \"RESTORE HEADERONLY FROM DISK = '" + bkpath  + "';";          
	 cmd = cmd + "RESTORE DATABASE " + dbname +" FROM DISK ='" + bkpath + "'  WITH FILE = 1,NORECOVERY,REPLACE,STOPAT = ";
     cmd =cmd + "'" + STOPAT + "';";
     for (int x=1;x<fno;x++) {
    	 cmd = cmd + "RESTORE LOG " + dbname +" FROM DISK ='" + logbak + "'  WITH FILE = " + x + " ,NORECOVERY,STOPAT = " + "'" + STOPAT + "';";
     }
     cmd = cmd + "RESTORE LOG " + dbname +" FROM DISK ='" + logbak + "'  WITH FILE = " + fno + " ,STOPAT = " + "'" + STOPAT + "';";
     cmd = cmd + "RESTORE DATABASE " + dbname +" WITH RECOVERY;";
     System.out.println(cmd);
     String ret = exec(cmd);

 	if (ret.indexOf("terminating") > -1) {
 		rtn="1~RESTORE TO TIME DATABASE :"+ dbname +" is terminating abnormally. ~" + ret;
 	}
 	if (ret.indexOf("successfully") > -1) {
 		rtn="0~RESTORE TO TIME DATABASE :"+ dbname +" successfully ~" + ret;
 	}
 	Unlock_DB(comp_name,dbname,"Multi_User "); 
 	return rtn;
}
//######################################################################################################		
public String Db_Snapshots(String comp_name, String newdb ,String dbname,String newpath ){
	String rtn="";
	String cmd = "OSQL -S " + comp_name + " -U cfg -P cfg09c -Q \"" +" CREATE DATABASE " + newdb + " ON (NAME =" + dbname + ", FILENAME = '" + newpath + "') AS SNAPSHOT OF " + dbname + ";";
	//cmd =cmd + "DROP DATABASE  " + dbname + ";";
	rtn=Unlock_DB(comp_name,dbname,"Single_User WITH ROLLBACK IMMEDIATE");
	cmd =cmd + "RESTORE DATABASE  " + dbname + " FROM DATABASE_SNAPSHOT = " + newdb + ";\"";
	System.out.println(cmd);

	String ret = exec(cmd);

	if (ret.indexOf("terminating") > -1) {
		rtn="1~RESTORE DATABASE :"+ dbname +" is terminating abnormally. ~" + ret;
	}
	if (ret.indexOf("successfully") > -1) {
		rtn="0~RESTORE DATABASE :"+ dbname +" successfully ~" + ret;
	}
	Unlock_DB(comp_name,dbname,"Multi_User ");
	return rtn;
}
//######################################################################################################

public String exec(String cmd){
//++	int ret =1;
	String strerr="";
//	AppendingFile(cmd);
	strerr=new Date().toString() + cmd;
	try{
		////System.out.println(cmd);
		Process p = Runtime.getRuntime().exec(cmd);
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
//      read the output from the command
		String s;
//		System.out.println("Here is the standard output of the command:\n");
		while ((s = stdInput.readLine()) != null) {
//			if ( s.indexOf("terminating") > -1 ) {  System.out.println("BACKUP DATABASE is terminating abnormally."); }
//++		if ( s.indexOf("successfully") > -1 ) {  ret =0; }//System.out.println("BACKUP DATABASE successfully"); }
///			System.out.println(s);
//++		AppendingFile(s);
			strerr = strerr + s;
		}
//		wait for end of process and get exit status
		int status ;
		status = p.waitFor();
		if (status != 0) {
//         System.out.println("The command returned a status of " + status);
         strerr = strerr +" The command returned a status of " + status;
		}

//	    read any errors from the attempted command
//		System.out.println("Here is the standard error of the command(if any):\n");
		while ((s = stdError.readLine()) != null) {	
//++		AppendingFile(s);
			strerr = strerr + s;
//			System.out.println(s);	
		}
		} catch (InterruptedException intexc) {
//		System.out.println("Interrupted Exception on waitFor: " + intexc.getMessage());
		} catch (IOException e) { 
		//System.out.println("ExecuteCmd.main() : " + e.getMessage());
//++	AppendingFile(e.toString());
		strerr = strerr + e.toString();
		} catch (Exception e) {
		//System.out.println("ExecuteCmd.main() : " + e);
//++	AppendingFile(e.toString());
		strerr = strerr + e.toString();
		}
		//return ret;
//		System.out.println("zzzzzzzz"+strerr+"0000");
		return strerr;
}

/*
//######################################################################################################
public void p1(Vector aaa){
    for(int i = 0 ; i < aaa.size();i++)
                System.out.println("*"+aaa.get(i).toString()+"*");
}
//######################################################################################################
public Vector execvector(String cmd){
//	++	int ret =1;
		String strerr="";
//		AppendingFile(cmd);
		Vector answer= new Vector();
		strerr=new Date().toString() + cmd;
		try{
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
//	      read the output from the command
			String s;
			
//			System.out.println("Here is the standard output of the command:\n");
			while ((s = stdInput.readLine()) != null) {
//				if ( s.indexOf("terminating") > -1 ) {  System.out.println("BACKUP DATABASE is terminating abnormally."); }
//	++		if ( s.indexOf("successfully") > -1 ) {  ret =0; }//System.out.println("BACKUP DATABASE successfully"); }
//	/			System.out.println(s);
//	++		AppendingFile(s);
				
				answer.add(s);
				strerr = strerr + s;
			}
//			wait for end of process and get exit status
			int status ;
			status = p.waitFor();
			if (status != 0) {
//	         System.out.println("The command returned a status of " + status);
	         strerr = strerr +" The command returned a status of " + status;
			}

//		    read any errors from the attempted command
//			System.out.println("Here is the standard error of the command(if any):\n");
			while ((s = stdError.readLine()) != null) {	
//	++		AppendingFile(s);
				strerr = strerr + s;
//				System.out.println(s);	
			}
			} catch (InterruptedException intexc) {
//			System.out.println("Interrupted Exception on waitFor: " + intexc.getMessage());
			} catch (IOException e) { 
			System.out.println("ExecuteCmd.main() : " + e.getMessage());
//	++	AppendingFile(e.toString());
			strerr = strerr + e.toString();
			} catch (Exception e) {
			System.out.println("ExecuteCmd.main() : " + e);
//	++	AppendingFile(e.toString());
			strerr = strerr + e.toString();
			}
			//return ret;
//			System.out.println("zzzzzzzz"+strerr+"0000");
			return answer;
	}
 
*/
//######################################################################################################	
  public void AppendingFile(String str){
      try{
		  // Create file 
		  FileWriter fstream = new FileWriter("\\\\INGSQL\\C$\\Backups\\bkup.log", true);
		  BufferedWriter out = new BufferedWriter(fstream);
		  out.write(new Date ()+ ":" + str + "\r\n");
		  //Close the output stream
		  out.close();
	  }
	  catch (Exception e){//Catch exception if any
		  System.err.println("Error: " + e.getMessage());
	  }
   }
//######################################################################################################
  public String Create_Restore(String comp_name, String dbname,String bkpath ){

	  String rtn="";
	  String cmd = "OSQL -U cfg -P cfg09c -S " + comp_name + " -Q \"" + "select filename from sysdatabases where dbid =6;\"" ;
	  String ret = exec(cmd);
	  rtn=ret;
	  
	  System.out.println("xx-->"+ rtn + "\r\n");
	  
	  int pos1=rtn.indexOf(".mdf");
	  int pos2=rtn.lastIndexOf(":", pos1);
	  String str1=rtn.substring(pos2-1, pos1+4);
	      
	  
	  
	  pos1=str1.indexOf(".mdf");
	  int pos3=str1.lastIndexOf("\\", pos1);
	  String NewDBPAth=str1.substring(0, pos3+1) ;
	   
///	  System.out.println("1-->"+ NewDBPAth + "\r\n");
	  
		cmd = "OSQL -S " + comp_name + " -U cfg -P cfg09c  -Q \"" + " RESTORE FILELISTONLY FROM DISK = '"+bkpath+"';\"" ;
		ret = exec(cmd);
	    rtn=ret;
	    //System.out.println("a-->"+ rtn + "\r\n");
	    if (ret.indexOf("terminating") > -1) {
			rtn="1~RESTORE FILELISTONLY :"+ dbname +"step 1 is terminating abnormally. ~" + ret;
			return rtn;
		}
	    
	    String cloning="MOVE '";
	    
	    pos1=rtn.indexOf(".ldf");
	    pos2=rtn.lastIndexOf(":", pos1);
	    str1=rtn.substring(pos2-1, pos1+4);
	    
	    pos1=str1.indexOf(".ldf");
	    pos3=str1.lastIndexOf("\\", pos1);
	    String str4=NewDBPAth + dbname + "_log.ldf' , NOUNLOAD,  REPLACE,  STATS = 10 \"";
	    
	    str1=rtn.substring(0,pos2-2).trim();
	    String str3=str1.substring(str1.lastIndexOf(" ")+1,str1.length());
	    
	    cloning=cloning + str3 + "' TO '" + str4;
//        System.out.println("cxcxc"+ str3 + "\r\n");
	    
	    pos1=rtn.indexOf(".mdf");
	    pos2=rtn.lastIndexOf(":", pos1);
	    str1=rtn.substring(pos2-1, pos1+4);   
	       
	    pos1=str1.indexOf(".mdf");
	    pos3=str1.lastIndexOf("\\", pos1);
	    str4=NewDBPAth + dbname + ".mdf' , ";
//	    System.out.println("mm"+ str4 + "\r\n");
	    
	    str1=rtn.substring(0,pos2-2).trim();
	    String str2=str1.substring(str1.lastIndexOf(" ")+1,str1.length());
 //   System.out.println("ss"+ str2 + "\r\n");
	    cloning=" MOVE '" + str2 + "' TO '" + str4 + cloning;
	    rtn=Unlock_DB(comp_name,dbname,"Single_User WITH ROLLBACK IMMEDIATE");
//	    cloning = "OSQL -S " + comp_name + " -E -Q \"" +" RESTORE DATABASE " + dbname + " FROM DISK ='" + bkpath + "' " + WT_OP +","+ cloning;
	    cloning = "OSQL -S " + comp_name + " -U cfg -P cfg09c -Q \"" +" RESTORE DATABASE " + dbname + " FROM DISK ='" + bkpath + "' WITH "+ cloning;
	    
    
	    ret = exec(cloning);
	    
	    System.out.println(cloning + "\r\n");
		if (ret.indexOf("terminating") > -1) {
			rtn="1~CreateRestore :"+ dbname +"step 2 is terminating abnormally. ~" + ret;
		}
		if (ret.indexOf("successfully") > -1) {
			rtn="0~CreateRestore :"+ dbname +" successfully ~" + ret;
		}
		Unlock_DB(comp_name,dbname,"Multi_User ");
		return rtn;
	}
  
//######################################################################################################  
  public String drop_tables(String comp_name, String dbname ,Vector vec ){
		String rtn="";
		String ret="";
		for(int i = 0 ; i < vec.size();i++){
//            System.out.println("*"+aaa.get(i).toString()+"*");
			String cmd = "OSQL -S " + comp_name + " -U cfg -P cfg09c  -Q \"" +" DROP TABLE [" + dbname + "]." + vec.get(i).toString() + ";";
			rtn = exec(cmd);
			ret=ret+rtn;
		}
//		System.out.println("*"+ret+"*");
		if (ret.indexOf("Cannot") > -1) {
			rtn="1~DROP TABLE :"+ dbname +" ERROR. ~" + ret;
		}
		if (ret.indexOf("Cannot") == -1) {
			rtn="0~DROP TABLE :"+ dbname +" successfully ~" + ret;
		}
		return rtn;
	}
  
//######################################################################################################	
  public String Check_lst_files(String str){
      
	  File file = new File(str);
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    DataInputStream dis = null;
	    int errs =0;
	    int warnings=0;
	    String inputoper="";
	    String inputdely="";
	    String goodoper="";
	    String gooddely="";
	    String rtn="";
	    
	    try {
	      fis = new FileInputStream(file);

	      // Here BufferedInputStream is added for fast reading.
	      bis = new BufferedInputStream(fis);
	      dis = new DataInputStream(bis);

	      // dis.available() returns 0 if the file does not have more lines.
	      while (dis.available() != 0) {

	      // this statement reads the line from the file and print it to
	        // the console.
//	        System.out.println(dis.readLine());
	        rtn=dis.readLine();
	        if (rtn.indexOf("ERR") > -1) { errs += 1; }
	        if (rtn.indexOf("WRN") > -1) { warnings += 1; }
	        if (rtn.indexOf("Input") > -1) { 
	        	int pos1=rtn.indexOf(":");	
	        	if( pos1 + 1 != rtn.length()){ 
	            inputoper=rtn.substring(pos1+2, rtn.length());
	        	}
	        	else { inputoper="x"; }
	            rtn=dis.readLine();
	            if( pos1 + 1 != rtn.length()){
	              goodoper=rtn.substring(pos1+2, rtn.length());
	            }
	            else { goodoper="x"; }
	            rtn=dis.readLine();
	            rtn=dis.readLine();
	            if( pos1 + 1 != rtn.length()){
	               inputdely=rtn.substring(pos1+2, rtn.length());
	            }
	            else { inputdely="x"; }
	            rtn=dis.readLine();
	            if( pos1 + 1 != rtn.length()){
	               gooddely=rtn.substring(pos1+2, rtn.length());
	            }
	            else { gooddely="x";}
/*	            System.out.println("www" + inputoper);
	            System.out.println("www" +goodoper);
	            System.out.println("www" +inputdely);
	            System.out.println("www" +gooddely);
*/
	        	 }
	      }

	      // dispose all the resources after using them.
	      fis.close();
	      bis.close();
	      dis.close();

	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
//	    System.out.println(inputoper);
//	    System.out.println(goodoper);
	    inputoper=inputoper.trim();
	    goodoper=goodoper.trim();
	    if (inputoper.equals(goodoper) ){ 
//	    	System.out.println(inputoper);
	    	rtn="0~listing file is o.k.";
	    }
	    else { rtn="1~input :"+ inputoper + " and good " + goodoper + " are not equals"; return rtn;}
	    if (inputdely.equals(gooddely) ){
//	    	System.out.println(inputoper);
	    	rtn="0~listing file is o.k.";
	    }
	    else { rtn="1~input DELAYED OPERATIONS :"+ inputdely + "and good : " + gooddely + "are not equals"; }
	    
	    if (errs > 0) {
			rtn="1~There are " + errs + " errors on listing file";
		}
		if (warnings > 0) {
			rtn=rtn + "~There are " + warnings + " warnings on listing file";
		}
		return rtn;
	    
	    
	  }
		  
//######################################################################################################	
  public String Run_Tran_files(String umode,String db,String mem,String nuc,String dte,String sp,String pr,String us,String pass,String ag,String batch,String ifn,String ofn,String tfn,String exfn){
      
		String rtn="";
		String ret="";
		
        if (umode.equals("")) {
        	rtn="1~there is no mode to run";
        	return rtn;
        }
        String cmd = "dbblp -u " + umode;
        if (db.equals("")) {
        	rtn="1~there is no db name";
        	return rtn;
        }
        cmd = cmd + " -d " + db;
        if (! mem.equals("")) {
        	cmd = cmd + " -m " + mem;
        }
        if (! nuc.equals("")) {
        	cmd = cmd + " -n " + nuc;
        }
        if (!dte.equals("")) {
        	cmd = cmd + " -dt " + dte;
        }
        if (!sp.equals("")) {
        	cmd = cmd + " -sp " + sp;
        }
        if (!pr.equals("")) {
        	cmd = cmd + " -pr " + pr;
        }
        if (!us.equals("")) {
        	cmd = cmd + " -us " + us;
        }
        if (!pass.equals("")) {
        	cmd = cmd + " -p " + pass;
        }
        if (!ag.equals("")) {
        	cmd = cmd + " -ag " + ag;
        }
        if (!batch.equals("")) {
        	cmd = cmd + " -bn " + batch;
        }
        if (!ifn.equals("")) {
        	cmd = cmd + " -i " + ifn;
        }
        if (!ofn.equals("")) {
        	cmd = cmd + " -o " + ofn;
        }
        if (!exfn.equals("")) {
        	cmd = cmd + " -ex " + exfn;
        }
       	cmd = cmd + " -BT";
//       	System.out.println(cmd);
		rtn = exec(cmd);
		ret=ret+rtn;
		
		if (ret.indexOf("ERROR") > -1) {
			rtn="1~" + ret;
		}
		else {rtn="0~Finished Successfully" + ret;}
		return rtn;	  
   }  

//######################################################################################################	
  public String Simple_Tran_files(String umode,String db,String batch,String ifn){
      
		String rtn="";
		String ret="";
		
        if (umode.equals("")) {
        	rtn="1~there is no mode to run";
        	return rtn;
        }
        String cmd = "dbblp -u " + umode;
        if (db.equals("")) {
        	rtn="1~there is no db name";
        	return rtn;
        }
        cmd = cmd + " -d " + db;
        if (!batch.equals("")) {
        	cmd = cmd + " -bn " + batch;
        }
        if (!ifn.equals("")) {
        	cmd = cmd + " -i " + ifn;
        }
       	cmd = cmd + " -BT";
       	System.out.println(cmd);
		rtn = exec(cmd);
		ret=ret+rtn;
		
		if (ret.indexOf("ERROR") > -1) {
			rtn="1~" + ret;
		}
		else {rtn="0~Finished Successfully" + ret;}
		return rtn;	  
   }  
    
//######################################################################################################	
  public String GetDBSize(String comp_name, String dbname){
      
		String rtn="";
		String ret="";
		
		String cmd = "OSQL -S " + comp_name + " -U cfg -P cfg09c -Q \"" ;
        cmd = cmd + "use master; select db.[name] as 'Database Name' ,af.[name] as 'Logical Name' ,(((CAST(af.[size] as DECIMAL(18,4)) * 8192) /1024) /1024) as 'File Size (MB)' ,af.[filename] as 'Physical Name' from sysdatabases db inner join sysaltfiles af on db.dbid = af.dbid where [fileid] in (1,2) and db.[name] like '" + dbname + "';\"";
        
       	
 //      	System.out.println(cmd);
		rtn = exec(cmd);
		ret=ret+rtn;
		
		if (ret.indexOf("0 rows affected") > -1) {
			rtn="1~wrong db name or there is  no such db !" + ret;
		}
		else {rtn="0~Finished Successfully" + ret;}
		return rtn;	  
   }  
//######################################################################################################	
  public String FixLogicalName(String comp_name,String dbname){
      
		String rtn="";
		String cmd="";
		String ret="";
		cmd = "OSQL -S " + comp_name + " -U cfg -P cfg09c -Q \"" ;
		cmd = cmd + "use master; select af.[name] from sysdatabases db inner join sysaltfiles af on db.dbid = af.dbid where  db.[name] like ";
		cmd = cmd + "'" + dbname + "' and af.[filename] like '%" + dbname + "%.mdf';\"";
		rtn = exec(cmd);
//		System.out.println(rtn);		
		int pos1=rtn.indexOf("(");
	    int pos2=rtn.lastIndexOf("-", pos1);
	    String str1=rtn.substring(pos2+1, pos1-1);
	    str1=rtrim(str1);
	    str1=ltrim(str1);
//	    System.out.println("-"+str1+"-");
		if (dbname.equalsIgnoreCase(str1)) {
//			System.out.println("zzznothing to change"+str1+"-");
			ret="2";
		}
		else {
	    cmd="";
		cmd = "OSQL -S " + comp_name + " -U cfg -P cfg09c -Q \"" ;
        cmd = cmd + "use master; ALTER DATABASE " + dbname; 
        cmd = cmd + " MODIFY FILE (NAME = " + str1;
        cmd = cmd + ", NEWNAME='" + dbname + "')\";";
////       	System.out.println(cmd);
		rtn = exec(cmd);
		if (rtn.indexOf("has been set")> -1 ) {
			ret = "1";
		}
		else { ret = "0"; }
		}
		
		
		cmd = "OSQL -S " + comp_name + " -U cfg -P cfg09c -Q \"" ;
		cmd = cmd + "use master; select af.[name] from sysdatabases db inner join sysaltfiles af on db.dbid = af.dbid where  db.[name] like ";
		cmd = cmd + "'" + dbname + "' and af.[filename] like '%" + dbname + "_log%';\"";
		rtn = exec(cmd);
//		System.out.println(cmd);		
		pos1=rtn.indexOf("(");
	    pos2=rtn.lastIndexOf("-", pos1);
	    str1=rtn.substring(pos2+1, pos1-1);
	    str1=rtrim(str1);
	    str1=ltrim(str1);
//	    System.out.println("-"+str1+"-");
	    String lnme = dbname + "_log";
		if (lnme.equalsIgnoreCase(str1)) {
//			System.out.println("nothing to change"+str1+"-");
			ret=ret + "2";
		}
		else {
		cmd="";
		cmd = "OSQL -S " + comp_name + " -U cfg -P cfg09c -Q \"" ;
        cmd = cmd + "use master; ALTER DATABASE " + dbname; 
        cmd = cmd + " MODIFY FILE (NAME = " + str1;
        cmd = cmd + ", NEWNAME='" + dbname + "_log')\";";
////       	System.out.println(cmd);
		rtn = exec(cmd);System.out.println(rtn);
		if (rtn.indexOf("has been set")> -1 ) {
			ret = ret + "1";
		}
		else { ret = ret + "0"; }
		}
//		System.out.println(ret);
		if (ret.equals("11")) { ret = "0~the logical name of log was changed"; }
		else if (ret.equals("22")){ ret = "0~Nothing was changed"; }
		else {ret = "1~there was an error in logical name change";}
       	return ret;
		  
   }    
//######################################################################################################	
 public String Check_logger(String str){
      
	    String rtn="";
	    int x = 0 ;
	    try	{
  			BufferedReader buff=	new BufferedReader(
  									new InputStreamReader(
  									new FileInputStream(str)));
  			String line = buff.readLine();
  			while(line != null ){
  				for (; x<10 ; x++) { line = buff.readLine(); }
   	        	if (line.indexOf("Valid")>-1){
  	        		String fname=line.substring(2, 31).trim();
  	        		String fvalid=line.substring(33, 54).trim();
  	        		String svalid=line.substring(56, 76).trim();
  	        		String chng=line.substring(78, 86).trim();
  	        		String fail=line.substring(99, 108).trim();
  	        		if (!fvalid.equals("Valid.")){ rtn = rtn + "," + fname; }
  	        		else if (!svalid.equals("Valid.")){ rtn = rtn + "," + fname; }
  	        		else if (!chng.equals("Y")){ rtn = rtn + "," + fname; }
  	        		else if (!fail.equals("0")){ rtn = rtn + "," + fname;}
  	        	//	else { System.out.println(cc); }
  	        	}
  	        	line = buff.readLine();
   	        }
  			if (rtn.indexOf("txt") > -1) { rtn = "1~error in members : " + rtn ; }
  			else { rtn = "0~Logger File o.k."; }
			buff.close();
  		}
  		catch(IOException ioe){ rtn="can't find the file --> " + str;  }
		return rtn;
	  }
//######################################################################################################	
 public void collect_errlog(String str,String full_log){
      
	    String rtn="";
	    
	    try	{
  			BufferedReader buff=	new BufferedReader(
  									new InputStreamReader(
  									new FileInputStream(str)));
  			String line = buff.readLine();
   	        	if (line.indexOf("1~")>-1){
 //  	        	  while(line != null ){rtn =rtn + line ;line = buff.readLine(); }
   	        		rtn = "There is an error in :: " + str;
   	        		write_stat(rtn,full_log);
   	            }
			buff.close();
  		}
  		catch(IOException ioe){ rtn="can't find the file --> " + str;  
  		write_stat(rtn,full_log);}
 }
//######################################################################################################	
 public void collect_errlog_loop(String str,String full_log){
      
	    String rtn="";
	    
	    try	{
  			BufferedReader buff=	new BufferedReader(
  									new InputStreamReader(
  									new FileInputStream(str)));
  			String line = buff.readLine();
   	        	
         	  while(line != null ){
         		 if (line.indexOf("1~")>-1){write_stat("There is an error in :: " + line,full_log);}
         		  line = buff.readLine();        	  
         	  }
			buff.close();
  		}
  		catch(IOException ioe){ rtn="can't find the file --> " + str;  
  		write_stat(rtn,full_log);}
 }
//###################################################################################################### 
public void write_stat(String rtn,String full_log) {
  		try{
  		  // Create file 
  		  FileWriter fstream = new FileWriter(full_log, true);
  		  BufferedWriter out = new BufferedWriter(fstream);
  //		out.write("---------------------------------------------------------------\r\n");
  		  out.write( rtn + "\r\n");
  		  //Close the output stream
  		  out.close();
  	  }
  	  catch (Exception e){//Catch exception if any
  		  System.err.println("Error: " + e.getMessage());
  	  }
  		
	  }
//###################################################################################################### 
public void ck_logsize(String ferr,String full_log) {
	File file = new File(ferr);
    long length = file.length();
    if (length > 0) {
    write_stat("There is an error in :: " + file,full_log);
    }
	
}
//######################################################################################################
 public void loglist() {
 	 collect_errlog("C:\\temp\\tbllogger.log","C:\\temp\\full_log.log");
	 collect_errlog("C:\\temp\\indexlogger.log","C:\\temp\\full_log.log");
	 collect_errlog("C:\\temp\\viewlogger.log","C:\\temp\\full_log.log");
	 ck_logsize("C:\\temp\\data.err","C:\\temp\\full_log.log");
 	 collect_errlog("C:\\temp\\dblex.log","C:\\temp\\full_log.log");
	 collect_errlog("C:\\temp\\sec_off.log","C:\\temp\\full_log.log");
 	 collect_errlog("C:\\temp\\replacestrupd.log","C:\\temp\\full_log.log");
	 collect_errlog("C:\\temp\\ocmoff.log","C:\\temp\\full_log.log");
	 collect_errlog("C:\\temp\\sadapcomp.log","C:\\temp\\full_log.log");
	 collect_errlog("C:\\temp\\sadapquix.log","C:\\temp\\full_log.log");
	 collect_errlog("C:\\temp\\sadaprule.log","C:\\temp\\full_log.log");
	 collect_errlog("C:\\temp\\compruntaskquix.log","C:\\temp\\full_log.log");
	 collect_errlog_loop( "C:\\temp\\parm_ddc_migrate_log.txt","C:\\temp\\full_log.log");
 }
 
//######################################################################################################
 public String Create_Restore_with_dbfpath(String comp_name, String dbname,String bkpath, String dbf_path, String ldf_path ){

	  String rtn="";
	  String unlckrtn="";
	  String cmd="";
	  String ret="";
	  String DBF=dbf_path ;
	  String LDF=ldf_path ;
	  dbname="["+dbname+"]";
///	  System.out.println("1-->"+ NewDBPAth + "\r\n");
	  
		cmd = "OSQL -S " + comp_name + " -U cfg -P cfg09c -Q \"" + " RESTORE FILELISTONLY FROM DISK = '"+bkpath+"';\"" ;
		ret = exec(cmd);
	    rtn=ret;
	    //System.out.println("a-->"+ rtn + "\r\n");
	    if (ret.indexOf("terminating") > -1) {
			rtn="1~RESTORE FILELISTONLY :"+ dbname +"step 1 is terminating abnormally. ~" + ret;
			return rtn;
		}
	    
	    String cloning="MOVE '";
	    HagUtil.writeStringToFile("\\\\ri33qa\\ri_share\\1.txt", rtn);
	    int pos1=rtn.indexOf(".ldf");
	    int pos2=rtn.lastIndexOf(":", pos1);
	    String str1=rtn.substring(pos2-1, pos1+4);
	    
	    pos1=str1.indexOf(".ldf");
//	    int pos3=str1.lastIndexOf("\\", pos1);
	    String str4=LDF + dbname + "_log.ldf' , NOUNLOAD,  REPLACE,  STATS = 10 \"";
	    
	    str1=rtn.substring(0,pos2-2).trim();
	    String str3=str1.substring(str1.lastIndexOf(" ")+1,str1.length());
	    
	    cloning=cloning + str3 + "' TO '" + str4;
//       System.out.println("cxc   "+ str3 + "\r\n");
	    
	    pos1=rtn.indexOf(".mdf");
	    pos2=rtn.lastIndexOf(":", pos1);
	    str1=rtn.substring(pos2-1, pos1+4);   
	       
	    pos1=str1.indexOf(".mdf");
//	    pos3=str1.lastIndexOf("\\", pos1);
	    str4=DBF + dbname + ".mdf' , ";
//	    System.out.println("mm"+ str4 + "\r\n");
	    
	    str1=rtn.substring(0,pos2-2).trim();
	    String str2=str1.substring(str1.lastIndexOf(" ")+1,str1.length());
//      System.out.println("ss  "+ str2 + "\r\n");
	    cloning=" MOVE '" + str2 + "' TO '" + str4 + cloning;
	    unlckrtn=Unlock_DB(comp_name,dbname,"Single_User WITH ROLLBACK IMMEDIATE");
//	    cloning = "OSQL -S " + comp_name + " -E -Q \"" +" RESTORE DATABASE " + dbname + " FROM DISK ='" + bkpath + "' " + WT_OP +","+ cloning;
	    cloning = "OSQL -S " + comp_name + "  -U cfg -P cfg09c -Q \"" +" RESTORE DATABASE " + dbname + " FROM DISK ='" + bkpath + "' WITH "+ cloning;
	    
   
	    ret = exec(cloning);
	    
	    //System.out.println(cloning + "\r\n");
		if (ret.indexOf("terminating") > -1) {
			rtn="1~CreateRestore :"+ dbname +"step 2 is terminating abnormally. ~" + ret;
		}else if (ret.indexOf("successfully") > -1) {
			rtn="0~CreateRestore :"+ dbname +" successfully ~" + ret;
		}
		Unlock_DB(comp_name,dbname,"Multi_User ");
		return rtn;
	}
//###################################################################################################### 
 public String crt_sql_us(String comp_name, String dbname,String usr,String pass, ArrayList<String> aaa) {
	 
	 String rtn="";
	 String ret="";
	 String cmd="";
	 
	 String sql_syntax ="DROP LOGIN [" + usr + "] CREATE LOGIN [" + usr + "] WITH PASSWORD=N'" + pass + "',DEFAULT_DATABASE=[master], DEFAULT_LANGUAGE=[us_english], CHECK_EXPIRATION=OFF, CHECK_POLICY=ON" ;
	 String rolename=""; 
	 for(int i = 0 ;i<aaa.size();i++){

		 rolename = rolename + " EXEC sys.sp_addsrvrolemember @loginame = N'" + usr + "', @rolename = N'" + aaa.get(i) + "'" ;
      }
  //   System.out.println(sql_syntax + rolename);
	 rolename = rolename + " ALTER LOGIN [" + usr +"] DISABLE";
	 
	 cmd = "OSQL -S " + comp_name + "  -U cfg -P cfg09c -Q \"" + sql_syntax + rolename + "\"";
	 
		ret = exec(cmd);
		
		System.out.println(ret);
		
		if (ret.indexOf("Msg") > -1) {
			rtn="1~crt_sql_us :"+ dbname +"something is wrong in syntax. ~" + ret;
		}
		if (ret.indexOf("error") > -1) {
			rtn="1~crt_sql_us :"+ dbname +"something is wrong in syntax. ~" + ret;
		}
		if (rtn=="") {
			rtn="0~crt_sql_us :"+ dbname +" successfully ~" + ret;
		}
		
 	return rtn;
 }
//###################################################################################################### 
 public String xCopy(String from,String to){
		
	    from=from.trim();
	    to=to.trim();
	    if(from.trim().length()<2)
	          return "0~no file to copy";
	    String cmd = "Xcopy /S/E /K /Y  " + from +"  "+ to + "\\"; 
	    String ret = exec(cmd);		
	    
	    
	    return ret;  
	    
	}

//######################################################################################################
 
 public String emergeCopy(String flocation,String tloc,String emver){
		
	    flocation=flocation.trim();
	    tloc=tloc.trim();
	    if(flocation.trim().length()<2)
	          return "0~no file to copy";
	    String ret =xCopy(flocation+"\\bin",tloc+"\\"+emver+"\\bin");
	      ret = ret +"\n"+ xCopy(flocation+"\\examples",tloc+"\\"+emver+"\\examples");
	      ret = ret +"\n"+ xCopy(flocation+"\\iomodule",tloc+"\\"+emver+"\\iomodule");
	      ret = ret +"\n"+ xCopy(flocation+"\\iomodules",tloc+"\\"+emver+"\\iomodules");
	      ret = ret +"\n"+ xCopy(flocation+"\\tran",tloc+"\\"+emver+"\\tran");
	      ret = ret +"\n"+ xCopy(flocation+"\\utility",tloc+"\\"+emver+"\\utility");
	      ret = ret +"\n"+ xCopy(flocation+"\\x2tran",tloc+"\\"+emver+"\\x2tran");
	    
	    if(ret.indexOf("returned") > -1){ 	return "1~"+" failed."+ret; }
	    else { return "0~"+ret+"successfully  ";  }
	    
	}

//######################################################################################################
 public String make_dir(String crtdir){
		
	 crtdir=crtdir.trim();
	    
	    if(crtdir.trim().length()<2)
	          return "0~no dir to create";
	    String cmd = "cmd /c mkdir  \"" + crtdir + "\""; 
	    //System.out.println(cmd);
	    String ret = exec(cmd);		
	    return ret;  
	}

//######################################################################################################
 
 public String remove_dir(String deldir){
		
	 deldir=deldir.trim();
	    
	    if(deldir.trim().length()<2)
	          return "0~no dir to delete";
	    String cmd = "cmd /c RD /Q /S  " + deldir; 
	    String ret = exec(cmd);		
	    return ret;  
	}

//######################################################################################################
 public String ri_java_Copy(String flocation,String tloc,String dbid){
		
	    flocation=flocation.trim();
	    tloc=tloc.trim();
	    if(flocation.trim().length()<2)
	          return "0~no file to copy";
	    String ret =make_dir(tloc+"\\RIjava_"+dbid+"\\archive");
	      ret = ret +"\n"+ xCopy(flocation+"\\properties",tloc+"\\RIjava_"+dbid+"\\properties");
	      ret = ret +"\n"+ xCopy(flocation+"\\RILSService",tloc+"\\RIjava_"+dbid+"\\RILSService");
	      ret = ret +"\n"+ make_dir(tloc+"\\RIjava_"+dbid+"\\runtime\\"+dbid+"V0530DB2");
	      ret = ret +"\n"+ xCopy(flocation+"\\runtime\\startup",tloc+"\\RIjava_"+dbid+"\\runtime\\startup");
	      ret = ret +"\n"+ xCopy(flocation+"\\tomcat\\bin",tloc+"\\RIjava_"+dbid+"\\tomcat\\bin");
	      ret = ret +"\n"+ xCopy(flocation+"\\tomcat\\conf",tloc+"\\RIjava_"+dbid+"\\tomcat\\conf");
	      ret = ret +"\n"+ xCopy(flocation+"\\tomcat\\lib",tloc+"\\RIjava_"+dbid+"\\tomcat\\lib");
	      ret = ret +"\n"+ make_dir(tloc+"\\RIjava_"+dbid+"\\tomcat\\log");
	      ret = ret +"\n"+ make_dir(tloc+"\\RIjava_"+dbid+"\\tomcat\\logs");
	      ret = ret +"\n"+ xCopy(flocation+"\\tomcat\\riExit",tloc+"\\RIjava_"+dbid+"\\tomcat\\riExit");
	      ret = ret +"\n"+ xCopy(flocation+"\\tomcat\\shared",tloc+"\\RIjava_"+dbid+"\\tomcat\\shared");
	      ret = ret +"\n"+ xCopy(flocation+"\\tomcat\\webapps",tloc+"\\RIjava_"+dbid+"\\tomcat\\webapps");
	      ret = ret +"\n"+ xCopy(flocation+"\\tomcat\\work",tloc+"\\RIjava_"+dbid+"\\tomcat\\work");
	      
	    if(ret.indexOf("returned") > -1){ 	return "1~"+" failed."+ret; }
	    else { return "0~"+ret+"successfully  ";  }
	    
	}

//######################################################################################################
 public String ri_share_Copy(String flocation,String tloc,String dbid){
		
	    flocation=flocation.trim();
	    tloc=tloc.trim();
	    if(flocation.trim().length()<2)
	          return "0~no file to copy";
	    String ret = make_dir(tloc+"\\RI_SHARE\\"+dbid+"\\"+dbid+"V0530INS");
	      ret = ret +"\n"+ xCopy(flocation+"\\"+dbid+"\\"+dbid+"V0530INS",tloc+"\\RI_SHARE\\"+dbid+"\\"+dbid+"V0530INS");
	      ret = ret +"\n"+ xCopy(flocation+"\\"+dbid+"\\"+dbid+"V0530M00",tloc+"\\RI_SHARE\\"+dbid+"\\"+dbid+"V0530M00");
	      ret = ret +"\n"+ xCopy(flocation+"\\"+dbid+"\\"+"IOM_BUILD",tloc+"\\RI_SHARE\\"+dbid+"\\IOM_BUILD");
	      ret = ret +"\n"+ xCopy(flocation+"\\"+dbid+"\\"+"IOM_CUST",tloc+"\\RI_SHARE\\"+dbid+"\\IOM_CUST");
	      ret = ret +"\n"+ make_dir(tloc+"\\RI_SHARE\\"+dbid+"\\tomcatLogs");
	      ret = ret +"\n"+ make_dir(tloc+"\\RI_SHARE\\"+dbid+"\\trace");
	      ret = ret +"\n"+ xCopy(flocation+"\\reg",tloc+"\\RI_SHARE\\reg");
	      ret = ret +"\n"+ xCopy(flocation+"\\RunProcSet",tloc+"\\RI_SHARE\\RunProcSet");
	      
	    if(ret.indexOf("returned") > -1){ 	return "1~"+" failed."+ret; }
	    else { return "0~"+ret+"successfully  ";  }
	    
	}

//######################################################################################################
 public String bk_ri_env(String emloc,String tloc,String emver,String rijloc,String dbid,String risloc,String sqlcomp,String sqldbid,String sqlct,String river){
		
	    String zip_loc=tloc;
	    tloc =tloc+"\\"+river;
	    String ret="";
	    String rtn="";
	    ret=remove_dir(tloc);
	    //   if(ret.indexOf("returned") > -1){ 	rtn="1~"+" failed."+ret+"\n"; }
	      // else {rtn = rtn + tloc + " --> removed\n ";}
	    ret=make_dir(tloc);
	       if(ret.indexOf("returned") > -1){ 	rtn= rtn + "1~"+" failed."+ret+"\n"; }
	       else {rtn = rtn + tloc + " --> create\n ";}
		ret=emergeCopy(emloc,tloc,emver);
		   if(ret.indexOf("returned") > -1){ 	rtn= rtn + "1~"+" failed."+ret+"\n"; }
		   else {rtn = rtn + emloc + " --> copied\n ";}
		ret=ri_java_Copy(rijloc,tloc,dbid);
		   if(ret.indexOf("returned") > -1){ 	rtn= rtn + "1~"+" failed."+ret+"\n"; }
		   else {rtn = rtn + rijloc + " --> copied\n ";}
		ret=ri_share_Copy(risloc,tloc,dbid);
		   if(ret.indexOf("returned") > -1){ 	rtn= rtn + "1~"+" failed."+ret+"\n"; }
		   else {rtn = rtn + risloc + " --> copied\n ";}
		   
		//ret=SimpleBK(sqlcomp,sqlct,tloc+"\\"+sqlct+".bak","WITH INIT");
		ret=SimpleBK(sqlcomp,sqlct,tloc+"\\"+sqlct+".bak","WITH COPY_ONLY");
		   if(ret.indexOf("returned") > -1){ 	rtn= rtn + "1~"+" failed."+ret+"\n"; }
		   else {rtn = rtn + sqlct + " --> backup success\n";}
		ret=SimpleBK(sqlcomp,sqldbid,tloc+"\\"+sqldbid+".bak","WITH INIT");
		   if(ret.indexOf("returned") > -1){ 	rtn= rtn + "1~"+" failed."+ret+"\n"; }
		   else {rtn = rtn + sqldbid + " --> backup success\n ";}
		   
	    if(rtn.indexOf("returned") > -1){ 	return "1~"+" failed."+rtn; }
	    else { return "0~ Backup Finished Successfully in :" + tloc;  }
	    	
	    /*
	    	String sourceDir = tloc;   ////  "C:/examples";
            String zipFile = zip_loc +"\\"+ river + ".zip";   /////"C:/FileIO/zipdemo.zip";
            
            try
            {
                    //create object of FileOutputStream
                    FileOutputStream fout = new FileOutputStream(zipFile);
                             
                    //create object of ZipOutputStream from FileOutputStream
                    ZipOutputStream zout = new ZipOutputStream(fout);
                    
                    //create File object from source directory
                    File fileSource = new File(sourceDir);
                    
                    ret=addDirectory(zout, fileSource);
                    
                    //close the ZipOutputStream
                    zout.close();
                    
                    System.out.println("Zip file has been created!");
                    
            }
            catch(IOException ioe)
            {
                    System.out.println("IOException :" + ioe);      
            }
	    
	    
	    
	    
	    }
	    if(ret.indexOf("returned") > -1){ 	rtn= rtn + "1~"+" failed."+ret+"\n"; }
		   else {rtn = rtn + "zip" + " --> zip success\n";}
	    return rtn;*/
	}

//######################################################################################################
 public static String  unzip(String filename,String destinationname,boolean gui){
     ZipFile zipFile;
     Enumeration entries;
     boolean flag=true;
     StringBuilder ans = new StringBuilder();
      try {
          zipFile = new ZipFile(filename);
          entries = zipFile.entries();
          int counter=0;
          while(entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry)entries.nextElement();
            ans.append("\n").append(entry);
            counter++;
            if(entry.isDirectory()) {
              // Assume directories are stored parents first then children.
             // System.err.println("Extracting directory: " + entry.getName());
              // This is not robust, just for demonstration purposes.
              (new File(destinationname+entry.getName())).mkdir();
              continue;
            }

           // System.err.println("Extracting file: " + entry.getName());
            copyInputStream(zipFile.getInputStream(entry),
               new BufferedOutputStream(new FileOutputStream(destinationname+entry.getName())));
          }
          ans.append("\n0~").append(counter).append(" files extracted.");
          zipFile.close();
        } catch (IOException ioe) {
          flag = false;
        
          System.err.println("Unhandled exception:");
          ioe.printStackTrace();
          ans.append("\n1~").append("Failed.");
        }
      if(flag){
            return "0~Unzip:\n"+ans.toString();
      }else{
             
            return "1~Unzip:\n"+ans.toString();
      }
}
//######################################################################################################
 public static String addDirectory(ZipOutputStream zout, File fileSource)
 {
	     String rtn="";
	     //get sub-folder/files list
         File[] files = fileSource.listFiles();
         System.out.println("Adding directory " + fileSource.getName());
         for(int i=0; i < files.length; i++)
         {
                 //if the file is directory, call the function recursively
                 if(files[i].isDirectory())
                 {
                         addDirectory(zout, files[i]);
                         continue;
                 }
                 /*
                  * we are here means, its file and not directory, so
                  * add it to the zip file
                  */
                 try
                 {
                        //// System.out.println("Adding file " + files[i].getName());
                         //create byte buffer
                         byte[] buffer = new byte[1024];
                         //create object of FileInputStream
                         FileInputStream fin = new FileInputStream(files[i]);
                         zout.putNextEntry(new ZipEntry(files[i].getName()));
                         /*
                          * After creating entry in the zip file, actually 
                          * write the file.
                          */
                         int length;
                         while((length = fin.read(buffer)) > 0)
                         {
                            zout.write(buffer, 0, length);
                         }
                  
                         /*
                          * After writing the file to ZipOutputStream, use
                          * 
                          * void closeEntry() method of ZipOutputStream class to 
                          * close the current entry and position the stream to 
                          * write the next entry.
                          */
                   
                          zout.closeEntry();
                   
                          //close the InputStream
                          fin.close();
                 rtn="finished";
                 }
                 catch(IOException ioe)
                 {
                         System.out.println("IOException :" + ioe);  
                         rtn="IOException :" + ioe;
                 }
         }
         return rtn;
 }
//######################################################################################################
 public static final void copyInputStream(InputStream in, OutputStream out)
 throws IOException
 {
   byte[] buffer = new byte[1024];
   int len;

   while((len = in.read(buffer)) >= 0)
     out.write(buffer, 0, len);

   in.close();
   out.close();
 }
//######################################################################################################
 
 public String zip_7(String fold2zip, String zipname,String zipoutfold,String zipcomp){
   ;
   String cmd = "cmd /c del /F /Q " + zipoutfold + "\\zip7list.txt"; 
   String rtn = exec(cmd);
    cmd = "cmd /c for /F %I in ('dir /b " + fold2zip + "') do (echo " + fold2zip + "\\%I) >> " +  zipoutfold + "\\zip7list.txt"; 
    rtn = exec(cmd);
     
//    cmd = " cmd /c echo  C:\\Progra~2\\7-Zip\\7z.exe a -tzip " + zipoutfold + "\\" + zipname + "  @"+ zipoutfold + "\\zip7list.txt >> "+ zipoutfold+ "\\zip7cmd.bat";
    cmd = " cmd /c " + zipcomp + " a -tzip " + zipoutfold + "\\" + zipname + "  @"+ zipoutfold + "\\zip7list.txt ";
    //System.out.println(cmd);
    rtn = exec(cmd);   
    if(rtn.indexOf("Everything is Ok") > -1){ 	rtn= "0~ Zip Finished Successfully.\n" + rtn; }
	   else {rtn = "1~ Zip Failed\n" + rtn ;}

   return rtn;
 }
//######################################################################################################		
 public String Fix_User_Map(String comp_name, String dbname,String user,String pass,String def_schma,String member ){
 	String rtn="";
 	String cmd="";
 	String ret ="";
 	
 	cmd = "OSQL -S " + comp_name + " -d " + dbname + "  -U cfg -P cfg09c -Q \"ALTER USER ["+ user + "]  with DEFAULT_SCHEMA=[" + def_schma + "]" ;
 	 ret = exec(cmd);
 	
 	cmd = "OSQL -S " + comp_name + " -d " + dbname + "  -U cfg -P cfg09c -Q \"sp_addrolemember '"   + member +"', N'"+ user+"'";
 	 ret = exec(cmd) + ret;
 	
 	
 	cmd = "OSQL -S " + comp_name + " -U cfg -P cfg09c -Q \"USE MASTER EXEC sp_change_users_login 'Auto_Fix', '" + user + "', NULL, '" + pass + "' ";
 	 rtn = exec(cmd)+ ret;
 	
 	cmd = "OSQL -S " + comp_name + " -U cfg -P cfg09c -Q \"USE "+ dbname +" EXEC sp_change_users_login 'Auto_Fix', '" + user + "', NULL, '" + pass + "' ";
 	 rtn = exec(cmd)+ ret;
 	 
 	
 	if (ret.indexOf("msg") > -1) {
 		rtn="1~Problem with user :"+ dbname +" not fixed. ~" + ret;
 	}
 	else {
 		rtn="0~User Fixed :"+ dbname +" successfully ~" + ret;
 	}
 	return rtn;
 }
//######################################################################################################		
 public String add_srvr_rols(String comp_name,String user,String srvr_role ){
 	String rtn="";
 	String cmd="";
 	String ret ="";
 	
 	cmd = "OSQL -S " + comp_name + " -d master  -U cfg -P cfg09c -Q \"sp_addsrvrolemember @loginame = N'"+ user + "', @rolename = N'" + srvr_role + "'" ;
 	 ret = exec(cmd); 
 	
 	if (ret.indexOf("msg") > -1) {
 		rtn="1~Problem with user :"+ srvr_role +" not added. ~" + ret;
 	}
 	else {
 		rtn="0~User got :"+ srvr_role +" successfully ~" + ret;
 	}
 	return rtn;
 }
//######################################################################################################
//-------------------------------------------------------------------------------------------------
	public  static void main(String s[]) {
		//ey_Gsetlibl frame = new ey_Gsetlibl();
		//frame.go();
		ey_Gsetlibl zz = new ey_Gsetlibl();
		String sx="";
//		zz.AppendingFile();
//		sx=zz.SimpleBK("INGSQL","AAV0980DDC","C:\\backups\\bkrstr_Log.bak","");
//		sx=zz.SimpleBK("INGSQL","bkrstr","C:\\backups\\bkrstr_Log.bak","WITH COPY_ONLY");
//		sx=zz.SimpleBK("INGSQL","bkrstr","C:\\backups\\bkrstr_Log.bak","WITH DIFFERENTIAL");
//		sx=zz.SimpleBK("INGSQL","omega5","\\\\INGSQL\\d$\\backups\\init\\omega5.bak","WITH INIT");
/////		sx=zz.SimpleBK("INGSQL","ri02s","d:\\backups\\new_shrink_tmpct_ri02s.bak","WITH INIT");
//		sx=zz.FullBK("INGSQL","bkrstr","C:\\backups\\strbkr_Log.bak","");
		
//		sx=zz.SimpleRestore("INGSQL","bkrstr","C:\\backups\\bkrstr_Log.bak","");
//		sx=zz.SimpleRestore("INGSQL","bkrstr","C:\\backups\\bkrstr_Log.bak","WITH FILE = 1, REPLACE");
		
//		sx=zz.SimpleZIP("INGAPP","C:\\backups\\bin","C:\\backups\\ssbin.zip");
//		sx=zz.SimpleZIPRestore("INGAPP","C:\\backups\\ssbin.zip","C:\\backups\\bin");

//		sx=zz.CloneRestore("INGSQL","muku","C:\\backups\\ri01s.bak");
//		sx=zz.Create_Restore("INGSQL","omega5","\\\\INGSQL\\d$\\backups\\init\\ddc_init.bak");
//		sx=zz.Create_Restore("INGSQL","tmp_CTV0500","d:\\backups\\new_shrink_tmpct_ri02s.bak");
		//sx=zz.Restore_from_to("RI-SQL","kuku","\\\\confg1\\d$\\ri\\manage\\temp\\R2V0520DB2.bk","RI-SQL","kuku");
//		sx=zz.Create_Restore_with_dbfpath("RI-SQL","R2V0520DB2","\\\\confg1\\d$\\ri\\manage\\temp\\H2V0520DB2.bk","d:\\sqldata\\");
//		sx=zz.Create_Restore_with_dbfpath("RI-SQL","C3V0530DB2","\\\\confg1\\d$\\ri\\manage\\temp\\C3V0530DB2.bk","e:\\mmdata\\","g:\\log\\");
//1		Vector vec= new Vector();
//1		vec.add("[R1V0410DB2].[CAFINNTRAN]");
//1		vec.add("[R1V0410DB2].[CAITEM]");
//1		vec.add("[R1V0410DB2].[CAFINNERR]");

//1		sx=zz.drop_tables("INGSQL","omega44",vec);
		
// not working ERROR	zz.Restoring_to_point_in_time("INGSQL","bkrstr","C:\\backups\\bkrstr.bak","Mar 23, 2009 11:52:29.000",4,"C:\\Backups\\bkrstr_log.bak");	
// not working ERROR	zz.Db_Snapshots("INGSQL","clone","bkrstr","C:\\backups\\newname.ss");
//		sx=zz.ShowLock("INGSQL","RI02S");
		//zz.p1(ret);
		
//		sx=zz.Check_lst_files("C:\\temp\\r5_comp.lst");
//		sx=zz.Check_lst_files("d:\\temp\\s\\lsiomlist2.lst");
//		sx=zz.Run_Tran_files("h","r5","999","999","dte","sp","pr","us","pass","ag","r5batch","c:\\xx.txt","c:\\xx.txt","","");
		//sx=zz.Run_Tran_files("h","r5","","","","","","","","","","c:\\xx.txt","","","");
//		sx=zz.GetDBSize("INGSQL","R5V0500DB2");
//		sx=zz.FixLogicalName("RI-SQL","F2V0520DB2");
//		sx=zz.Check_logger("d:\\LoggerFileName.log");
//		zz.loglist(); 
//		zz.collect_errlog("C:\\temp\\tbllogger.log","C:\\temp\\full_log.log");
//		zz.ck_logsize("C:\\temp\\data.err","C:\\temp\\full_log.log");
//		zz.collect_errlog_loop( "C:\\temp\\parm_ddc_migrate_log.txt","C:\\temp\\full_log.log");
		
// st<String> aaa = new ArrayList<String>();
//		aaa.add("bulkadminll");
//		aaa.add("processadmin");
//		aaa.add("dbcreator");

		
		
		///sx=zz.xCopy("D:\\xx\\c","D:\\xx\\c22");
		//sx=zz.remove_dir("D:\\xx\\cc22");
		//System.out.println(sx);
		//sx=zz.make_dir("D:\\xx\\cc22");
		//sx=zz.emergeCopy("D:\\xx\\emerge\\ri\\eMerge\\eMergeBIS4415","D:\\xx\\cc22","eMergeBIS4415");
		//sx=zz.ri_java_Copy("D:\\xx\\ri_java\\RIjava_X3","D:\\xx\\cc22","X3");
		//sx=zz.ri_share_Copy("\\\\ri2-app\\d$\\RI_SHARE","D:\\xx\\cc22","X3");
		//sx=zz.crt_sql_us("RI-SQL","kukutst","kuku","kuku", aaa);
		//sx=zz.bk_ri_env("\\\\ri2-app\\d$\\ri\\eMerge\\eMergeBIS4415","\\\\ri2qa\\e\\Versions_Backups\\eybk","eMergeBIS4415","\\\\ri2-app\\d$\\ri_java\\RIjava_X3","X3","\\\\ri2-app\\d$\\RI_SHARE","riqa","X3V0530DB2","CTV0510","m07a01");
		//sx=zz.zip_7("\\\\ri2qa\\e\\Versions_Backups\\eybk\\m07a01", "m07a01.zip","\\\\ri2qa\\e\\Versions_Backups\\eybk","\\\\ri2qa\\C$\\Progra~2\\7-Zip\\7z.exe");
		
		
		//sx=zz.Unlock_DB("RIQA","F0V0600DB2");
		//sx=zz.Fix_User_Map("P1-SQL","X0V0600DB2","K90RIPRC","ADMINRI","RI","db_owner");
		//sx=zz.add_srvr_rols("P1-SQL","RIADMIN","bulkadmin");
		//sx=zz.Fix_User_Map("RIDEMO","F0V0600DB2","F0","F0PASS","RI","db_owner");
		//sx=zz.Create_Restore_with_dbfpath("ridemo","X0V0600DB2","\\\\ridemo\\c$\\RIQA_BK\\open_sqmc\\RIQA_X0V0600DB2_20120328_0200_DATA","\\\\ridemo\\c$\\Program Files\\Microsoft SQL Server\\MSSQL10_50.MSSQLSERVER\\MSSQL\\DATA","\\\\ridemo\\c$\\Program Files\\Microsoft SQL Server\\MSSQL10_50.MSSQLSERVER\\MSSQL\\DATA");
		System.out.println(sx);
	
	}
	
	  public static String ltrim(String source) {
	        return source.replaceAll("^\\s+", "");
	    }

	    /* remove trailing whitespace */
	    public static String rtrim(String source) {
	        return source.replaceAll("\\s+$", "");
	    }

	    /* replace multiple whitespaces between words with single blank */
	    public static String itrim(String source) {
	        return source.replaceAll("\\b\\s{2,}\\b", " ");
	    }

	    /* remove all superfluous whitespaces in source string */
	    public static String trim(String source) {
	        return itrim(ltrim(rtrim(source)));
	    }

	    public static String lrtrim(String source){
	        return ltrim(rtrim(source));
	    }
 }	