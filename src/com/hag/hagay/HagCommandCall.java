package com.hag.hagay;

import java.awt.Color;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JOptionPane;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Exception;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.CommandCall;
import com.ibm.as400.access.ConnectionDroppedException;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.Job;
import com.ibm.as400.access.JobLog;
import com.ibm.as400.access.ObjectDoesNotExistException;
import com.ibm.as400.access.RequestNotSupportedException;
import com.ibm.as400.access.SpooledFile;
import com.ibm.as400.access.SpooledFileList;


public class HagCommandCall {
	//HagGridPanel 	hagGridPanel= null;
	AS400 			myServer 	=null;
	CommandCall 	command 	= null;
	Thread 			th 			= null;
	String 			jobName,jobUser,jobNumber,host2,user2,pass2;
	Job job1=null;
	String connectAns = null;
	HagCommandCall(AS400 myServer,String host2,String user2,String pass2){
		this.host2		=host2;
		this.user2		=user2;
		this.pass2		=pass2;
		this.myServer		=myServer;
		//this.hagGridPanel	=hagGridPanel;
		
		init();
		
	}
	public void init(){
		try {
			this.command 		= new CommandCall(myServer);
			job1 = this.command.getServerJob();
			this.jobName = job1.getName();
			this.jobUser = job1.getUser();
			this.jobNumber = job1.getNumber();
		//	HagUtil.p("WRKJOB JOB("+this.jobNumber+"/"+this.jobUser+"/"+this.jobName+")");
		} catch (com.ibm.as400.access.ConnectionDroppedException e) {
			e.printStackTrace();
			connectAns=e.getMessage();
		} catch (AS400SecurityException e) {
			e.printStackTrace();
		} catch (ErrorCompletingRequestException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> run(final String cmd,String log){
		Thread th = new Thread(new Runnable() {
			public void run() {
				AS400 sys =null;
				try {
					Thread.sleep(2000);
					sys = new AS400(host2,user2,pass2);
					Job job = new Job(sys, jobName, jobUser,jobNumber);
					//String jobName1 = job.getName();
					//String jobUser1 = job.getUser();
					//String jobNumber1 = job.getNumber();
					
					
					
					boolean loop=true;
					while(job.getStatus().equals("*ACTIVE") && loop){
						Thread.sleep(1000);
						if(job.getValue(Job.ACTIVE_JOB_STATUS).toString().equals("MSGW")){
							//hagGridPanel.hagWorkerChange("add","   -*MSGW from job("+jobName+"/"+jobUser+"/"+jobNumber+")\n",false,false,Color.RED,null,-1);
							loop=false;
						}
						if(job.getValue(Job.ACTIVE_JOB_STATUS).toString().equals("LCKW")){
							//hagGridPanel.hagWorkerChange("add","   -*LCKW from job("+jobName+"/"+jobUser+"/"+jobNumber+")\n",false,false,Color.RED,null,-1);
							loop=false;
						}
						//HagUtil.p(aaa);
					}
					
				} catch (AS400SecurityException e) {
					e.printStackTrace();
				} catch (AS400Exception e) {
					String msg = e.getMessage();
					if( (!(msg.startsWith("CPF3C53 Job") && msg.endsWith("not found."))) &&
						(!(msg.startsWith("CPF3C52 Internal job identifier no longer valid")))	)
						e.printStackTrace();
				} catch (ErrorCompletingRequestException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ObjectDoesNotExistException e) {
					e.printStackTrace();
				
				} catch (InterruptedException e) {
					e.printStackTrace();
				
				}finally{
					sys.disconnectService(AS400.COMMAND);
					//sys.disconnectAllServices();
				}
  			}
  		});
  		th.start();
  	
 		return run1(cmd,log);
	}
		
	public void writeLogs(String log){
		Job job;
		try {
			//joblog
			job = command.getServerJob();
			JobLog joblog = job.getJobLog();
			ArrayList<String> arr=convertJobLogToArrayString(joblog);
			HagUtil.writeArrayListToFile(log+"_joblog"+".txt", arr, null, false);
			//String str = HagUtil.converetArrayStringToString(arr, "\n");
			//HagUtil.writeStringToFile(log+"_joblog"+".txt", str, true);
			//spools
			//ArrayList<String> spools = getJobSpools(myServer,user2 ,job.getName(),job.getUser(),job.getNumber());
			//HagUtil.p(spools);
		} catch (AS400SecurityException e) {
			e.printStackTrace();
		} catch (ErrorCompletingRequestException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	public ArrayList<String> run1(String cmd,String log){
		ArrayList<String> ans = new ArrayList<String>();
		if(connectAns != null){
			  ans.add("*! "+connectAns+"\n");
			  ans.add("*! If the next line in the group failed to execute\n");
			  ans.add("*! It is because the connection was recreated but lost the libl you previously set\n");
			  ans.add("*! You need to set the libl again after the problematic rexx\n");
		}
		int timeToWait = 0;
		if(cmd.toUpperCase().indexOf(",SBMJOB")>-1){
			String time1 = cmd.substring(0,cmd.indexOf(","));
    		timeToWait = HagUtil.s2i(time1); 
    		cmd = cmd.substring(cmd.indexOf(",")+1,cmd.length());
    	}
	    ans.add("command="+cmd+"\n");
	    try
	    {
	    	//  	jjj = command.getServerJob();
	    	//	HagUtil.p("jjj="+jjj.getName()+","+jjj.getUser()+","+jjj.getNumber());
	    	if (command.run(cmd) != true){
	    		//    	hagGridPanel.hagWorkerChange("add","   -"+"2\n",false,false,Color.RED,null,50);
	        	ans.add("command.run=false");
	        }
	    	
	    	
	    	
	    	if(log != null)
	    		 writeLogs(log);
	    	
	    	String number1=null;
	    	String user1=null;
	    	String name1=null;
	    	AS400Message[] messagelist = command.getMessageList();
	    	if(messagelist!= null && messagelist.length>0){
	    		for (int i = 0; i < messagelist.length; i++) {
	    			String tmp = messagelist[i].getText();
	    			int pos2 = tmp.indexOf("submitted to job queue");
	    			if(tmp.startsWith("Job") && pos2 >-1){
	    				String tmp1 = tmp.substring(4,pos2-1);
	    				ArrayList<String> tmp2 = HagUtil.splitStr2ArrayList(tmp1,"/");
	    				 number1 	= tmp2.get(0).trim();
	    				 user1	= tmp2.get(1).trim();
	    				 name1		= tmp2.get(2).trim();
	    				//hagGridPanel.setLastJobName(name1);
	    				//hagGridPanel.setLastJobNumber(number1);
	    				//hagGridPanel.setLastJobUser(user1);
	    			}
	    			ans.add(tmp+"\n");
	    			//if(hagGridPanel != null)
	    				//hagGridPanel.hagWorkerChange("add","   -"+messagelist[i].getText()+"\n",false,false,Color.blue,null,-1);
	    		}
	    	}
	    	if(timeToWait>0){
	    		int i = 0;
	    		String stat = "**";
	    		while (i <10 && !stat.equals("*OUTQ")){
	    			//HagUtil.p(stat);
	    			Job job = new Job(myServer, name1, user1, number1);
		    		HagUtil.sleep(timeToWait);
		    		stat=job.getStatus();
	    			i++;
	    			
	    		}
	    		HagUtil.sleep(timeToWait);
       		}
	    } catch (ConnectionDroppedException cde){
	    	 //writeLogs("\\\\hyundai.sapiens.com\\hyun05lp\\rs\\v0_00m00\\as400\\00a.txt");
        //	hagGridPanel.hagWorkerChange("add","   -"+"6\n",false,false,Color.RED,null,50);
	     	 
	    	//String aaa = hagGridPanel.getLastJob("JOBNAME")+hagGridPanel.getLastJob("JOBUSER")+hagGridPanel.getLastJob("JOBNUMBER");
	    	System.out.println("Command " + command.getCommand() + " issued an exception!");
	        ans.add("**"+cde.getReturnCode()+"**"+command.getCommand()+"* issued an exception! "+cde.getMessage());
	        ans.add(cde.toString());
	    	
	    }catch (Exception e){
        //	hagGridPanel.hagWorkerChange("add","   -"+"6\n",false,false,Color.RED,null,50);
	    	System.out.println("Command " + command.getCommand() + " issued an exception!");
	        ans.add("*"+command.getCommand()+"* issued an exception! "+e.getMessage());
	        ans.add(e.toString());
	    	
	    }
	    return ans;

	}
	
	//----------------------------------------------------------
		public ArrayList<String> getJobSpools(AS400 sys1,String connectU ,String jobName,String jobUser,String jobNumber){
			ArrayList<String>  results = new ArrayList<String> ();
			SpooledFileList splfList = new SpooledFileList( sys1 );
			try{
				splfList.setUserFilter(jobUser);
				splfList.openSynchronously();
				Enumeration enumx = splfList.getObjects();
				splfList.setCache(false);
				while(enumx.hasMoreElements()){
					SpooledFile splf = (SpooledFile)enumx.nextElement();
					if ( splf != null ){
						String 	Name 		= splf.getName();
						int 	Number 		= splf.getNumber();
						String NumberS = HagUtil.i2s(Number);
						String jobname1 	= splf.getJobName();
						String jobuser1 	= splf.getJobUser();
						String jobnumber1 	= splf.getJobNumber();
						//HagUtil.p(jobname1+","+jobName);
					//	HagUtil.p(jobnumber1+","+jobNumber);
					//	HagUtil.p(jobuser1+","+jobUser);
						if(	jobName.equalsIgnoreCase(jobname1) && 
							jobUser.equalsIgnoreCase(jobuser1) &&
							jobNumber.equalsIgnoreCase(jobnumber1))
						results.add(Name+"~"+Number);
					}
				}
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
			} catch (RequestNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				splfList.close();
			}
			
			return results;
		}
	
	private ArrayList<String> convertJobLogToArrayString(JobLog jlog){
		ArrayList<String> rst = new ArrayList<String>();
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
		}
		return rst;
	}
}
