package com.hag.hagay;

public final class HagParam {
	public static String propFile = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\testProdlock.txt";
	
	public static final String 			getVbProdOrTest(){
		if(HagUtil.serverName != null) {
			if(HagUtil.serverName.equalsIgnoreCase("cfg-ri2")) 
				return HagUtil.getPropertyVal(propFile,"cfg-ri2_vb");
			else if(HagUtil.serverName.equalsIgnoreCase("cfg-ri")) 
				return HagUtil.getPropertyVal(propFile,"cfg-ri_vb");
			else if(HagUtil.serverName.equalsIgnoreCase("rii-5os")) 
				return HagUtil.getPropertyVal(propFile,"rii-5os_vb");
			else if(HagUtil.serverName.equalsIgnoreCase("uka-ripocapp02")) 
				return HagUtil.getPropertyVal(propFile,"uka-ripocapp02_vb");
		}
		return HagUtil.getPropertyVal(propFile,"other_vb");
	}
	public static final String 			getConfg1DB(){
		if(HagUtil.serverName != null) {
			if(HagUtil.serverName.equalsIgnoreCase("cfg-ri2")) 
				return HagUtil.getPropertyVal(propFile,"cfg-ri2_confg1DB");
			else if(HagUtil.serverName.equalsIgnoreCase("cfg-ri")) 
				return HagUtil.getPropertyVal(propFile,"cfg-ri_confg1DB");
			else if(HagUtil.serverName.equalsIgnoreCase("rii-5os")) 
				return HagUtil.getPropertyVal(propFile,"rii-5os_confg1DB");
			else if(HagUtil.serverName.equalsIgnoreCase("uka-ripocapp02")) 
				return HagUtil.getPropertyVal(propFile,"uka-ripocapp02_confg1DB");
			else
				return HagUtil.getPropertyVal(propFile,"other_confg1DB");
		}
		return HagUtil.getPropertyVal(propFile,"other_confg1DB");
	}
	public static final String 			getStatus(){
		//HagUtil.hagDebugToFile("d:\\temp\\1.txt", "1"  );
		//HagUtil.hagDebugToFile("d:\\temp\\1.txt", HagUtil.serverName  );
		//HagUtil.hagDebugToFile("d:\\temp\\1.txt", propFile );
		if(HagUtil.serverName != null) {
			if(HagUtil.serverName.equalsIgnoreCase("cfg-ri2")) 
				return HagUtil.getPropertyVal(propFile,"cfg-ri2_title");
			else if(HagUtil.serverName.equalsIgnoreCase("cfg-ri")) 
				return HagUtil.getPropertyVal(propFile,"cfg-ri_title");
			else if(HagUtil.serverName.equalsIgnoreCase("rii-5os")) 
				return HagUtil.getPropertyVal(propFile,"rii-5os_title");
			else if(HagUtil.serverName.equalsIgnoreCase("uka-ripocapp02")) {
			//	HagUtil.hagDebugToFile("d:\\temp\\1.txt", "111" );
				return HagUtil.getPropertyVal(propFile,"uka-ripocapp02_title");
			}
		}
		return HagUtil.getPropertyVal(propFile,"other_title");
	}
	public static final String 			getDiary(){
		if(HagUtil.serverName != null) {
			if(HagUtil.serverName.equalsIgnoreCase("cfg-ri2")) 
				return HagUtil.getPropertyVal(propFile,"cfg-ri2_diary");
			else if(HagUtil.serverName.equalsIgnoreCase("cfg-ri")) 
				return HagUtil.getPropertyVal(propFile,"cfg-ri_diary");
			else if(HagUtil.serverName.equalsIgnoreCase("rii-5os")) 
				return HagUtil.getPropertyVal(propFile,"rii-5os_diary");
			else if(HagUtil.serverName.equalsIgnoreCase("uka-ripocapp02")) 
				return HagUtil.getPropertyVal(propFile,"uka-ripocapp02_diary");
		}
		return HagUtil.getPropertyVal(propFile,"other_diary");
	}
	
	public static final String 			getMailTo(){
		if(HagUtil.serverName != null) {
			if(HagUtil.serverName.equalsIgnoreCase("cfg-ri2")) 
				return HagUtil.getPropertyVal(propFile,"cfg-ri2_mailTo");
			else if(HagUtil.serverName.equalsIgnoreCase("cfg-ri")) 
				return HagUtil.getPropertyVal(propFile,"cfg-ri_mailTo");
			else if(HagUtil.serverName.equalsIgnoreCase("rii-5os")) 
				return HagUtil.getPropertyVal(propFile,"rii-5os_mailTo");
			else if(HagUtil.serverName.equalsIgnoreCase("uka-ripocapp02")) 
				return HagUtil.getPropertyVal(propFile,"uka-ripocapp02_mailTo");
		}
		return HagUtil.getPropertyVal(propFile,"other_mailTo");
	}
	public static final String 			PrintTitle(){
		
		if(HagUtil.serverName != null) {
			if(HagUtil.serverName.equalsIgnoreCase("cfg-ri2")) 
				return "Status="+HagUtil.getPropertyVal(propFile,"cfg-ri2_title");
			else if(HagUtil.serverName.equalsIgnoreCase("cfg-ri")) 
				return "Status="+HagUtil.getPropertyVal(propFile,"cfg-ri_title");
			else if(HagUtil.serverName.equalsIgnoreCase("rii-5os")) 
				return "Status="+HagUtil.getPropertyVal(propFile,"rii-5os_title");
			else if(HagUtil.serverName.equalsIgnoreCase("uka-ripocapp02")) 
				return HagUtil.getPropertyVal(propFile,"uka-ripocapp02_title");
		}
		return "Status="+HagUtil.getPropertyVal(propFile,"other_title");
	}
	
	public static final String 			PrintDB(){
		
		if(HagUtil.serverName != null) {
			if(HagUtil.serverName.equalsIgnoreCase("cfg-ri2")) 
				return "confg1DB="+HagUtil.getPropertyVal(propFile,"cfg-ri2_confg1DB");
			else if(HagUtil.serverName.equalsIgnoreCase("cfg-ri")) 
				return "confg1DB="+HagUtil.getPropertyVal(propFile,"cfg-ri_confg1DB");
			else if(HagUtil.serverName.equalsIgnoreCase("rii-5os")) 
				return "confg1DB="+HagUtil.getPropertyVal(propFile,"rii-5os_confg1DB");
			else if(HagUtil.serverName.equalsIgnoreCase("uka-ripocapp02")) 
				return HagUtil.getPropertyVal(propFile,"uka-ripocapp02_confg1DB");
		}
		return "confg1DB="+HagUtil.getPropertyVal(propFile,"other_confg1DB");
	}
	
	public static final String 			PrintVB(){
		if(HagUtil.serverName != null) {
			if(HagUtil.serverName.equalsIgnoreCase("cfg-ri2")) 
				return  "vbFolder="+HagUtil.getPropertyVal(propFile,"cfg-ri2_vb");
			else if(HagUtil.serverName.equalsIgnoreCase("cfg-ri")) 
				return  "vbFolder="+HagUtil.getPropertyVal(propFile,"cfg-ri_vb");
			else if(HagUtil.serverName.equalsIgnoreCase("rii-5os")) 
				return  "vbFolder="+HagUtil.getPropertyVal(propFile,"rii-5os_vb");
			else if(HagUtil.serverName.equalsIgnoreCase("uka-ripocapp02")) 
				return HagUtil.getPropertyVal(propFile,"uka-ripocapp02_vb");
		}
		return "vbFolder="+ HagUtil.getPropertyVal(propFile,"other_vb");
	}
	
	public static final String 			PrintMail(){
		if(HagUtil.serverName != null) {
			if(HagUtil.serverName.equalsIgnoreCase("cfg-ri2")) 
				return  "mailTo="+HagUtil.getPropertyVal(propFile,"cfg-ri2_mailTo");
			else if(HagUtil.serverName.equalsIgnoreCase("cfg-ri")) 
				return  "mailTo="+HagUtil.getPropertyVal(propFile,"cfg-ri_mailTo");
			else if(HagUtil.serverName.equalsIgnoreCase("rii-5os")) 
				return  "mailTo="+HagUtil.getPropertyVal(propFile,"rii-5os_mailTo");
			else if(HagUtil.serverName.equalsIgnoreCase("uka-ripocapp02")) 
				return HagUtil.getPropertyVal(propFile,"uka-ripocapp02_mailTo");
		}
		return "mailTo="+ HagUtil.getPropertyVal(propFile,"other_mailTo");
	}
	
	public static final String 			PrintDiary(){
		if(HagUtil.serverName != null) {
			if(HagUtil.serverName.equalsIgnoreCase("cfg-ri2")) 
				return  "diary="+HagUtil.getPropertyVal(propFile,"cfg-ri2_diary");
			else if(HagUtil.serverName.equalsIgnoreCase("cfg-ri")) 
				return  "diary="+HagUtil.getPropertyVal(propFile,"cfg-ri_diary");
			else if(HagUtil.serverName.equalsIgnoreCase("rii-5os")) 
				return  "diary="+HagUtil.getPropertyVal(propFile,"rii-5os_diary");
			else if(HagUtil.serverName.equalsIgnoreCase("uka-ripocapp02")) 
				return HagUtil.getPropertyVal(propFile,"uka-ripocapp02_diary");
		}
		return "diary="+ HagUtil.getPropertyVal(propFile,"other_diary");
	}
}
