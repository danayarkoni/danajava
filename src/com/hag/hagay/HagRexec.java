package com.hag.hagay;
import com.jcraft.jsch.*;

import java.io.*;
import java.util.ArrayList;

public final class HagRexec{
    String user ;
    static String pass ;
    String server ;
    JSch jsch = null;

   // public HagRexec() {
    //}
    
 
    public static final  ArrayList<String> runGroupCmd(ArrayList<String> cmd1,boolean waitForAns){
    
        String host2 = cmd1.get(0).toString();
        String user2 = cmd1.get(1).toString();
        String pass2 = cmd1.get(2).toString();
        JSch jsch = new JSch();
        ArrayList<String> ans = new ArrayList<String>();
        pass = pass2;
        ans.add("#command=connect to "+host2+" user="+user2+"\n");
        try{
            for(int i = 3 ; i < cmd1.size(); i++){
            //
            	Session session=jsch.getSession(user2,host2,22);
                UserInfo usrInfo=new MyUserInfo();
                session.setUserInfo(usrInfo);
                session.connect();
           	//
            	String temp = cmd1.get(i).toString();
               
            	ArrayList<String> ansSingle = runSingleCmd(session,temp,waitForAns);
                for(int j = 0 ; j < ansSingle.size();j++){
                    ans.add(ansSingle.get(j).toString());
                }
            //
                session.disconnect();                
            //
            }

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Exception"+e);
            ans.add("\n"+ e.getMessage()+"\n");
        }
      
        return ans;

    }
    
    public static final  ArrayList<String> runSingleCmd(Session session,String cmd,boolean waitForAns) {
    	ArrayList<String> ans = new ArrayList<String>();
    	ChannelExec channel = null;
		try {
			channel = (ChannelExec)session.openChannel("exec");
		} catch (JSchException e) {
			e.printStackTrace();
		}
        channel.setCommand(cmd);
        channel.setInputStream(null);
        ByteArrayOutputStream myStream = new ByteArrayOutputStream();
        ((ChannelExec)channel).setErrStream(myStream);
		String myError = "" ;
        InputStream in = null;
		try {
			in = channel.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			ans.add(e.getMessage()+","+e.toString());
		}
        try {
			channel.connect();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			myError = myStream.toString();
			myError = myError.replace("cat: /etc/MANPATH: No such file or directory","");
			myError = myError.replace("LD_LIBRARY_PATH: Undefined variable.","");
			myError = myError.replace("\n","");
	    } catch (JSchException e) {
			e.printStackTrace();
			ans.add(e.getMessage()+","+e.toString());
		}
        byte[] tmp=new byte[1024];
        while(waitForAns){
          try {
			while(in.available()>0){
			    int i=in.read(tmp, 0, 1024);
			    if(i<0)break;
			    String temp1 = new String(tmp, 0, i);
				ans.add(temp1);
			  }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ans.add(e.getMessage()+","+e.toString());
		}
          if(channel.isClosed()){
        	  String sign1 = "";
        	  if (channel.getExitStatus() != 0)
        		  sign1 = "  ->  ";
        	  ans.add(""+channel.getExitStatus()+"~"+cmd+sign1+myError);
        	  break;
          }
          try{Thread.sleep(1000);
          }catch(Exception ee){
        		ans.add(ee.getMessage()+","+ee.toString());
          }
        }
        channel.disconnect();
        return ans;
    }
    
      public static class MyUserInfo implements UserInfo {
        public String getPassword() {
            return pass;
        }
        public String getPassphrase() {
            return "";
        }
        public boolean promptPassword(String arg0) {
            return true;
        }

        public boolean promptPassphrase(String arg0) {
            return true;
        }

        public boolean promptYesNo(String arg0) {
            return true;
        }

        public void showMessage(String arg0) {
        }
    }
    
}
