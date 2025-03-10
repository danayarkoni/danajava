package com.hag.hagay;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HagStringList extends ArrayList<String>{
	private static final long serialVersionUID = 1L;

	public HagStringList(){
		super();
	}
	public HagStringList(String file,boolean skipFlag,String skipStr,boolean keepEmptyLines){
		super();
		loadFileIntoArrayList(file,skipFlag,skipStr,keepEmptyLines);
	}
	
	public HagStringList(String str,String delim,boolean trim){
		super();
		splitStr2ArrayList(str,delim,trim);
	}
	public HagStringList(HagStringList arr,String type){
		super();
		if(type.equalsIgnoreCase("fix"))
			fixArrayList( arr);
		else
			dup(arr);
	}
	
//	public HagStringList (ArrayList<JComponent> compsList){
//		super();
//		for(int i = 0 ; i < compsList.size();i++){
//			JComponent comp = compsList.get(i);
//			 this.add(HagFinalUtil.getCompVal(  comp));
		/*
			if(comp instanceof HagComboBox){
				this.add(((HagComboBox)(comp)).getSelectedItem().toString());
			}else if(comp instanceof JTextField){
				this.add(((JTextField)(comp)).getText());
			}else if(comp instanceof JLabel){
				this.add(((JLabel)(comp)).getText());
			}else{
				this.add("HagError1");
			}
			*/
	//	}
//	}
	
	public void dup(HagStringList from){
		this.clear();
		for(int i = 0 ; i < from.size();i++)
			this.add(from.get(i));
	}
	public void dropDup(){
		HagStringList temp = new HagStringList();
		for(int i = 0 ; i<this.size();i++){
			String temp1 = this.get(i);
			if(!temp.isInList(temp1)){
				temp.add(temp1);
			}
		}
		this.clear();
		for(int i = 0 ; i<temp.size();i++){
			String temp1 = temp.get(i);
			this.add(temp1);
		}
	}
	public void replaceStr(String oldStr,String newStr) {
		for(int i = 0 ; i<this.size();i++){
			String temp1 = this.get(i);
			String temp = HagUtil.replaceStr(temp1,oldStr,newStr);
			this.set(i,temp);
		}
	}
	
	public boolean isInList(String str){
		for(int i = 0 ; i<this.size();i++){
			String temp1 = this.get(i).trim();
			if(str.equals(temp1))
				return true;
		}
		return false;
	}
	private void 	fixArrayList(HagStringList arr){
		
		for(int k =0; k<arr.size();k++){
		   String temp = arr.get(k);
		   HagStringList arr2 = new HagStringList(temp,"\n",false);
		   for(int kk =0; kk<arr2.size();kk++){
			   this.add(arr2.get(kk));
		   }
	   }
	}
	
	private void  	splitStr2ArrayList(String str,String delim,boolean trim){
		 String[] array = str.split("\\"+delim,-1);
		 for(int i = 0 ; i < array.length;i++){
			 if(trim)
				 this.add(array[i].trim());
			 else
				 this.add(array[i]);
		 }
	}   
	
	public void 	append(HagStringList list2){
		for(int i = 0 ; i < list2.size();i++)
			this.add(list2.get(i));
	}
	 
	public String 	convertToString(String delim){
		StringBuilder ans = new StringBuilder();
	   for(int i =0; i<this.size();i++){
	     if(i == 0 ){
	    	 ans.append(this.get(i));
	     }else{
	    	 ans.append(delim).append(this.get(i));
	     }
	   }
	   return ans.toString();
	}
	public String[] 	convertToArray(){
		String[] ans =  new String[this.size()];
	   for(int i =0; i<this.size();i++)
	    
	    	 ans[i]=this.get(i);
	   
	   return ans;
	}
	public String lookFor(String key,String delim,boolean trim,boolean caseS){
		String ans = null;
		if(trim)
			key=key.trim();
		for(int i = 0 ; i < this.size();i++){
			String temp=this.get(i);
			String w1= HagUtil.getWord0(temp, delim,0, trim);
			String w2= HagUtil.getWord0(temp, delim,1, trim);
			if(caseS){
				if(w1.equals(key))
					return w2;
			}else{
				if(w1.equalsIgnoreCase(key))
					return w2;
			}
		}
		return ans;
	}
	
	public String convertLogsToString(){
	   StringBuilder ans = new StringBuilder();
	   ans.append("<html><body bgcolor=\"#ccccbb\">");
	   for(int i =0; i<this.size();i++){
		  String temp = this.get(i);
		  if(temp.indexOf("Exception")>-1 || temp.startsWith("1~"))
	    	 ans.append("<font color=\"#ff0000\">").append(temp).append("</font>").append("<br>");
	     else
	    	 ans.append(temp).append("<br>");
	   }
	   ans.append("</body></html>");
	   String str1 =ans.toString();
	   String str2= str1.substring(0,str1.length()-4);
	   return str2;
	}
	public String convertMainLogsToString(){
		   StringBuilder ans1 = new StringBuilder();
		   ans1.append("<html><body bgcolor=\"#ccccbb\">");
		   for(int i =0; i<this.size();i++){
			  String temp1 = this.get(i);
			  if(temp1.startsWith("Failed "))
		    	 ans1.append("<font color=\"#ff0000\">").append(temp1).append("</font>").append("<br>");
			  else if(temp1.startsWith("Skipped "))
			   	 ans1.append("<font color=\"#663399\">").append(temp1).append("</font>").append("<br>");
			  else if(temp1.startsWith("OK "))
				   	 ans1.append("<font color=\"#008000\">").append(temp1).append("</font>").append("<br>");
			  else
		    	 ans1.append(temp1).append("<br>");
		   }
		   ans1.append("</body></html>");
		   String str1a =ans1.toString();
		   String str2a= str1a.substring(0,str1a.length()-4);
		   return str2a;
		}
	public void sortByColor(){
		HagStringList Offfe21=new HagStringList();
		HagStringList M93f03b=new HagStringList();
		HagStringList P7f8c8d=new HagStringList();
		HagStringList extra=new HagStringList();
		
		for(int i =0;i<this.size();i++) {
			String temp=this.get(i);
			if(temp.indexOf("\"#fffe21\"")  >-1)
				Offfe21.add(temp);
			else if(temp.indexOf("\"#93f03b\"")>-1)
				M93f03b.add(temp);
			else if(temp.indexOf("\"#7f8c8d\"")>-1)
				P7f8c8d.add(temp);
			else
				extra.add(temp);
		}
		this.clear();
		
		this.append(M93f03b);
		this.append(Offfe21);
		this.append(P7f8c8d);
		this.append(extra);
	}
 	private void loadFileIntoArrayList(String file,boolean skipFlag,String skipStr,boolean keepEmptyLines){
 	     BufferedReader buff=	null;
		try	{
	        buff=	new BufferedReader(
	                				new InputStreamReader(
	                				new FileInputStream(file)));
	        String line = buff.readLine();
	       	while(line!=null){
  	       		if(skipFlag){
  	       			if(!line.startsWith(skipStr))
  	       				if(keepEmptyLines || line.trim().length()>0)
  	       					this.add(line);
 	       		}else{//not skeep
       				if(keepEmptyLines || line.trim().length()>0)
       					this.add(line);
	 	      	}
	 	        line = buff.readLine();
	 	    }
	
		}catch(IOException ioe){
			//System.out.println(ioe.toString());
		}finally{
	       	try {
				buff.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/*
	public void writeArrayListToFile(String file,ArrayList<String> list,JPanel panel,boolean unixFormat){
		try	{
			PrintWriter out =new PrintWriter(
								new BufferedWriter(
									 new FileWriter(file)));
			for(int i=0 ; i<list.size();i++){
				String str=list.get(i);
				if(unixFormat)
					if(i==list.size()-1 )
						out.print(str);
					else
						out.print(str+"\n");
				else
					out.println(str);
					
			}
			out.flush();
			out.close();
		}catch(IOException ioe)	{
			 JOptionPane.showMessageDialog(panel,
	  	             "Can't create file "+file, "I/O Error",
	  	             JOptionPane.ERROR_MESSAGE);
		}
	}*/
 	
 	public void writeToFile(String file,JPanel panel,boolean unixFormat){
 		PrintWriter out=null;
		try	{
			 out =new PrintWriter(
								new BufferedWriter(
									 new FileWriter(file)));
			for(int i=0 ; i<this.size();i++){
				String str=this.get(i);
				if(unixFormat){
					if(i==this.size()-1 )
						out.print(str);
					else
						out.print(str+"\n");
				}else
					out.println(str);
					
			}
			out.flush();
		
		}catch(IOException ioe)	{
			 JOptionPane.showMessageDialog(panel,
	  	             "Can't create file "+file, "I/O Error",
	  	             JOptionPane.ERROR_MESSAGE);
		}finally{
			if(out!=null)
				out.close();
		}
	
 	}
	public 	String 		writeToFile1(String file,JPanel panel,boolean unixFormat){
 		
		try	{
			PrintWriter out =new PrintWriter(
								new BufferedWriter(
									 new FileWriter(file)));
			for(int i=0 ; i<this.size();i++){
				String str=this.get(i);
				if(unixFormat){
					if(i==this.size()-1 )
						out.print(str);
					else
						out.print(str+"\n");
				}else{
					out.println(str);
				}
			}
			out.flush();
			out.close();
			return "0~";
		}catch(IOException ioe)	{
			 JOptionPane.showMessageDialog(panel,
	  	             "Can't create file "+file, "I/O Error",
	  	             JOptionPane.ERROR_MESSAGE);
			 return "1~"+ioe.getMessage();
		}
	
 	}
 	public void writeToFilewithRc(HagRc hagRc,String file,JPanel panel,boolean unixFormat){
 		PrintWriter out=null;
		try	{
			 out =new PrintWriter(
								new BufferedWriter(
									 new FileWriter(file)));
			for(int i=0 ; i<this.size();i++){
				String str=this.get(i);
				if(unixFormat){
					if(i==this.size()-1 )
						out.print(str);
					else
						out.print(str+"\n");
				}else
					out.println(str);
					
			}
			out.flush();
		
		}catch(IOException ioe)	{
			hagRc.rc=1;
			hagRc.log.add("failed to write HagStringList to file:"+file+" error="+ioe.getMessage());
			JOptionPane.showMessageDialog(panel,
	  	             "Can't create file "+file, "I/O Error",
	  	             JOptionPane.ERROR_MESSAGE);
		}finally{
			if(out!=null)
				out.close();
		}
	
 	}
 	public String writeToFile(String file,boolean unixFormat){
 		String ans = "0~"+file+" written.";
 		PrintWriter out=null;
		try	{
			 out =new PrintWriter(
								new BufferedWriter(
									 new FileWriter(file)));
			for(int i=0 ; i<this.size();i++){
				String str=this.get(i);
				if(unixFormat){
					if(i==this.size()-1 )
						out.print(str);
					else
						out.print(str+"\n");
				}else
					out.println(str);
					
			}
			out.flush();
		
		}catch(IOException ioe)	{
			ans = "1~Can't create file "+file+ " I/O Error "+ioe.getMessage();
		}finally{
			if(out!=null)
				out.close();
		}

		return ans;
 	}
}
