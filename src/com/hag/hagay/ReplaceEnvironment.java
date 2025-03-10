package com.hag.hagay;

import java.io.File;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class  ReplaceEnvironment {

	public static String 		ReplaceMyPrivateEnvWithThisEnv(HagStringList cbEnvs ,String cfgMenuWebUser,HttpServletRequest request, String plat){
		//replace private
		System.out.println("replacePrivateEnvNew1Pre");
		//6665
		if(!HagUtil.isIE(request))
			return HagUtil.shortHtml("this option can run on InternetExplorer only", "red","bg");

		
		if(cbEnvs.size()>1)
			return HagUtil.shortHtml("only one env (checkBox) can be selected for this option", "red","bg");
		if(cfgMenuWebUser.endsWith("guest"))
			return HagUtil.shortHtml("please login with your user - guest user cannot run this option", "red","bg");

		
		String line = cbEnvs.get(0);
		String sourceAPP=HagUtil.getWord0(line,"~",1,true);
		String sourceDB=HagUtil.getWord0(line,"~",11,true);
		String sourceBN=HagUtil.getWord0(line,"~",2,true);
		String sourceSQL=HagUtil.getWord0(line,"~",10,true);
		String w3=HagUtil.getWord0(line,"~",3,true);
		String sourceEnv=HagUtil.getWord0(line,"~",9,true);
		String customerLong = HagUtil.getCustomerByPartyLong(w3);
		String customerShort = HagUtil.getCustomerByPartyShort(w3);
		String ownerSource=HagUtil.getWord0(line,"~",12,true);
		String sqlVersionSource=HagUtil.getWord0(line,"~",21,true);
	
		
		HagStringList ans1 = new HagStringList();
	 	String stm1 = "select bis_server,batchName,sql_server,db,owner,sql_server_version from dbo.ri_environments_new where  status='A' and env ='Private' and owner = '"+cfgMenuWebUser+"'";
		HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1, ans1, "~");
	
		//StringBuilder from =new StringBuilder("<select name=\""+"name"+"\">");
		//StringBuilder from1 =new StringBuilder();
		String[] bis_servers = new String[ans1.size()];
		String[] dbids = new String[ans1.size()];
		String[] sql_servers = new String[ans1.size()];
		String[] db_names = new String[ans1.size()];
		String[] owners = new String[ans1.size()];
		String[] sqlVersions = new String[ans1.size()];
		StringBuilder sb = new StringBuilder();
		sb.append("<FORM METHOD=POST name=\"Form\" ACTION=\"replacePrivateDbPre.jsp\">");
		sb.append("<table  bgcolor = \"#E6E6FA\" border = 1 CELLPADDING=3 >");
		sb.append("<tr bgcolor = \"#00ff00\"><td>.</td><td>appServer</td><td>batchName</td><td>sqlServer</td><td>database name</td></tr>");
		
		for (int i=0;i<ans1.size();i++){
			String temp = ans1.get(i);
			bis_servers[i]= HagUtil.getWord0(temp, "~", 0,true);
			dbids[i]= HagUtil.getWord0(temp, "~", 1,true);
			sql_servers[i]= HagUtil.getWord0(temp, "~", 2,true);
			db_names[i]= HagUtil.getWord0(temp, "~", 3,true);
			owners[i]= HagUtil.getWord0(temp, "~", 4,true);
			sqlVersions[i]= HagUtil.getWord0(temp, "~", 5,true);
			sb.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cbEnvs\" id=\"cbEnvs\" value=\""+bis_servers[i]+"!"+dbids[i]+"!"+sql_servers[i]+"!"+db_names[i]+"!"+owners[i]+"!"+sqlVersions[i]+"\" ></td><td>" +bis_servers[i]+"</td><td>"+dbids[i]+"</td><td>"+sql_servers[i]+"</td><td>"+db_names[i]+"</td></tr>");
		}
		sb.append("</table><br>");
		sb.append("<table><tr><td bgcolor = \"#ffff00\">");
		sb.append("Please select only one private environment to replace.<br>");
		sb.append("and click the replace button.<br>"); 
		sb.append("</td></tr></table>");
		
		sb.append("<input type=\"hidden\" name=\"user\" id=\"user\" value = \""+cfgMenuWebUser+"\" maxlength=\"140\" size=\"140\">");
		sb.append("<input type=\"hidden\" name=\"fromDb\" id=\"fromDb\" value = \""+sourceDB+"\" maxlength=\"140\" size=\"140\">");
		sb.append("<input type=\"hidden\" name=\"fromBn\" id=\"fromBn\" value = \""+sourceBN+"\" maxlength=\"140\" size=\"140\">");
		sb.append("<input type=\"hidden\" name=\"fromSql\" id=\"fromSql\" value = \""+sourceSQL+"\" maxlength=\"140\" size=\"140\">");
		sb.append("<input type=\"hidden\" name=\"fromApp\" id=\"fromApp\" value = \""+sourceAPP+"\" maxlength=\"140\" size=\"140\">");
		sb.append("<input type=\"hidden\" name=\"fromCust\" id=\"fromCust\" value = \""+customerShort+"\" maxlength=\"140\" size=\"140\">");
	
		sb.append("<input type=\"hidden\" name=\"sourceEnv\" id=\"sourceEnv\" value = \""+sourceEnv+"\" maxlength=\"140\" size=\"140\">");
		sb.append("<input type=\"hidden\" name=\"customerLong\" id=\"customerLong\" value = \""+customerLong+"\" maxlength=\"140\" size=\"140\">");
		sb.append("<input type=\"hidden\" name=\"customerShort\" id=\"customerShort\" value = \""+customerShort+"\" maxlength=\"140\" size=\"140\">");
		
		sb.append("<input type=\"hidden\" name=\"ownerSource\" id=\"ownerSource\" value = \""+ownerSource+"\" maxlength=\"140\" size=\"140\">");
		sb.append("<input type=\"hidden\" name=\"sqlVersionSource\" id=\"sqlVersionSource\" value = \""+sqlVersionSource+"\" maxlength=\"140\" size=\"140\">");
		
			
		
		sb.append("<input type=\"hidden\" name=\"myBrowser\" id=\"myBrowser\" maxlength=\"140\" size=\"140\">");
		
		
		
		
		
		sb.append("<br><INPUT TYPE=SUBMIT value=\"replace my private environment\" ></FORM>");
	
		return sb.toString();	
	}
	public static String 		appendDevElementsToMyPrivateEnv(String cfgMenuWebUser, HttpServletRequest request,String plat){
		//appendDevElementsToMyPrivate
		System.out.println("appendDevElementsToMyPrivateEnvNew1Pre");
		if(!HagUtil.isIE(request))
			return HagUtil.shortHtml("this option can run on InternetExplorer only", "red","bg");


		
		HagStringList ans1 = new HagStringList();
	 	String stm1 = "select bis_server,batchName,sql_server,db from dbo.ri_environments_new where status='A' and env ='Private' and owner = '"+cfgMenuWebUser+"'";
		HagJdbc.selectIntoList("SQL","CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),stm1, ans1, "~");
	
		String[] bis_servers = new String[ans1.size()];
		String[] dbids = new String[ans1.size()];
		String[] sql_servers = new String[ans1.size()];
		String[] db_names = new String[ans1.size()];
		StringBuilder sb = new StringBuilder();
		sb.append("<FORM METHOD=POST name=\"Form\" ACTION=\"appendDevToPrivateEnvPre.jsp\">");
		sb.append("<table  bgcolor = \"#E6E6FA\" border = 1 CELLPADDING=3 >");
		sb.append("<tr bgcolor = \"#00ff00\"><td>.</td><td>appServer</td><td>batchName</td><td>sqlServer</td><td>database name</td></tr>");
		
		for (int i=0;i<ans1.size();i++){
			String temp = ans1.get(i);
			bis_servers[i]= HagUtil.getWord0(temp, "~", 0,true);
			dbids[i]= HagUtil.getWord0(temp, "~", 1,true);
			sql_servers[i]= HagUtil.getWord0(temp, "~", 2,true);
			db_names[i]= HagUtil.getWord0(temp, "~", 3,true);
			sb.append("<tr bgColor=\"#bbbbbb\"><td><input type=\"checkbox\" name=\"cbEnvs\" id=\"cbEnvs\" value=\""+bis_servers[i]+"!"+dbids[i]+"!"+sql_servers[i]+"!"+db_names[i]+"\" ></td><td>" +bis_servers[i]+"</td><td>"+dbids[i]+"</td><td>"+sql_servers[i]+"</td><td>"+db_names[i]+"</td></tr>");
		}
		sb.append("</table><br>");
		sb.append("<table><tr><td bgcolor = \"#ffff00\">");
		sb.append("Please select only one private environment to replace.<br>");
		sb.append("and click the replace button.<br>"); 
		sb.append("</td></tr></table>");
		
		sb.append("<input type=\"hidden\" name=\"user\" id=\"user\" value = \""+cfgMenuWebUser+"\" maxlength=\"140\" size=\"140\">");
		
			
		
		sb.append("<input type=\"hidden\" name=\"myBrowser\" id=\"myBrowser\" maxlength=\"140\" size=\"140\">");
		
		
		
		
		
		sb.append("<br><INPUT TYPE=SUBMIT value=\"append Dev Elements to my private env\" ></FORM>");
	
		return sb.toString();	
	}
	public static String 		replaceCmEnvPre(	
								HagStringList 		cbEnvs ,	
								String 				cfgMenuWebUser,  
								HttpServletRequest 	request,
								String 				plat,
								String 				group){
	
		System.out.println("1replaceCmEnvPre");
		//6666
		if(!HagUtil.isIE(request))
			return HagUtil.shortHtml("this option can run on InternetExplorer only", "red","bg");

		if(cbEnvs.size()>1)
			return HagUtil.shortHtml("only one env (checkBox) can be selected for this option", "red","bg");
		if(cfgMenuWebUser.endsWith("guest"))
			return HagUtil.shortHtml("please login with your user - guest user cannot run this option", "red","bg");
	
		HagSQL hagSQL = new HagSQL();
		String stm = "select batchName,bis_server,sql_server,db,lastInst,lastGoodInst,owner,sql_server_version from dbo.ri_environments_new where status='A' and project='RI' order by bis_server,batchName";
		HagStringList list = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,8,"~",null,null);
	
		
		
		StringBuilder ans = new StringBuilder();
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"replaceCmDbPre.jsp\">");
		ans.append("<h3>Select target environment (this environemnt will be replaced)</h3>");
		ans.append("<br><table bgColor=\"#dddddd\"cellpadding=\"3\" cellspacing=\"3\" border =\"1\" >");
		ans.append("<tr><td>bis_server~batchName~sql_server~db</td></tr>");
		ans.append("<tr><td><select class=\"c30\" id=\"targetEnv\" name =\"targetEnv\" >");
		
		for(int k = 0 ; k < list.size();k++){
			String temp1 = list.get(k);
			String batchName = HagUtil.getWord0(temp1, "~", 0,true);
			String bis_server = HagUtil.getWord0(temp1, "~", 1,true);
			String sql_server = HagUtil.getWord0(temp1, "~", 2,true);
			String ownerTarget = HagUtil.getWord0(temp1, "~", 6,true);
			String sqlVersionTarget = HagUtil.getWord0(temp1, "~", 7,true);
			String db = HagUtil.getWord0(temp1, "~", 3,true);
			String ver = HagUtil.getWord0(temp1, "~", 4,true)+"("+HagUtil.getWord0(temp1, "~", 5,true)+")";
		//	String line = HagUtil.left(bis_server,"&nbsp;",30,"INIT")+"~"+HagUtil.left(batchName,"&nbsp;",30,"INIT")+"~"+HagUtil.left(sql_server,"&nbsp;",30,"INIT")+"~"+HagUtil.left(db,"&nbsp;",30,"INIT");
			String line = bis_server+"~"+batchName+"~"+sql_server+"~"+db+"~"+ver+"~"+ownerTarget+"~"+sqlVersionTarget;
			
			ans.append("<option class=\"c30\" value=\""+line+"\" >"+line+" </option>");
		}
		ans.append("</td></tr>");
		ans.append("</table><br>");

		
		ans.append("<br><table bgColor=\"#dddddd\"cellpadding=\"3\" cellspacing=\"3\" border =\"1\" >");
		
		ans.append("<tr><td>Include replace database process</td><td>Source User in RI.RIUSER table</td></tr>");
		ans.append("<tr><td><select class=\"c30\" id=\"withDB\" name =\"withDB\" >");
		ans.append("<option class=\"c30\" value=\"YES\">YES</option>");
		ans.append("<option class=\"c30\" value=\"NO\">NO</option>");
		ans.append("</td>");
		ans.append("<td><input type=\"text\" name=\"sourceRIUSER\" id=\"sourceRIUSER\" value = \"\" maxlength=\"40\" size=\"40>\"");
		ans.append("</td>only when withDB=NO </tr>");
		ans.append("</table><br>");
		
		ans.append("<br><table bgColor=\"#dddddd\"cellpadding=\"3\" cellspacing=\"3\" border =\"1\" >");
		ans.append("<tr><td>eObjectsOnly</td></tr>");
		ans.append("<tr><td><select class=\"c30\" id=\"eObjectsOnly\" name =\"eObjectsOnly\" >");
		ans.append("<option class=\"c30\" value=\"NO\">NO</option>");
		ans.append("<option class=\"c30\" value=\"YES\">YES</option>");
		ans.append("</td></tr>");
		ans.append("</table><br>");

		String sourceLine=cbEnvs.get(0);
		ans.append("<input type=\"hidden\" name=\"sourceLine\" id=\"sourceLine\" value = \"").append(sourceLine).append("\" maxlength=\"140\" size=\"140\">");
		ans.append("<input type=\"hidden\" name=\"toUser\" id=\"toUser\" value = \"").append(cfgMenuWebUser).append("\" maxlength=\"140\" size=\"140\">");
		ans.append("<input type=\"hidden\" name=\"toPlat\" id=\"toPlat\" value = \"").append(plat).append("\" maxlength=\"140\" size=\"140\">");
		ans.append("<input type=\"hidden\" name=\"group\" id=\"group\" value = \"").append(group).append("\" maxlength=\"140\" size=\"140\">");
		
		ans.append("<br><INPUT TYPE=SUBMIT value=\"Replace CM env. \" onclick=\"this.style.display = 'none'\" ></FORM>");
		
		
		// HagUtil.replaceCmEnvDB="DH1";
		// HagUtil.replaceCmEnvBatchName="DH";

		/*
		ans.add("<tr><td></td><td>Source</td><td>Target</td></tr>");
		ans.add("<tr><td >APP server name</td><td bgColor=\"#66ff66\">"+sourceAPP+"</td><td bgColor=\"#00ffff\">"+toAPP+"</td></tr>");
		ans.add("<tr><td >SQL server name</td><td bgColor=\"#66ff66\">"+sourceSQL+"</td><td bgColor=\"#00ffff\">"+toSQL+"</td></tr>");
		ans.add("<tr><td >Batch name</td><td bgColor=\"#66ff66\">"+sourceBN+"</td><td bgColor=\"#00ffff\">"+toBatchName+"</td></tr>");
		ans.add("<tr><td >Database name</td><td bgColor=\"#66ff66\">"+sourceDB+"</td><td bgColor=\"#00ffff\">"+toDB+"</td></tr>");
		ans.add("<tr><td >Database Dat size</td><td bgColor=\"#66ff66\">"+sourceDatSize+" mb</td><td bgColor=\"#00ffff\">"+targetDatSize+" mb</td></tr>");
		ans.add("<tr><td >Database Log size</td><td bgColor=\"#66ff66\">"+sourceLogSize+" mb</td><td bgColor=\"#00ffff\">"+targetLogSize+" mb</td></tr>");
		ans.add("<tr><td >log4j level</td><td bgColor=\"#66ff66\">"+log4jCombo+"</td><td bgColor=\"#00ffff\">.</td></tr>");
		ans.add("<tr><td >Customer name</td><td bgColor=\"#66ff66\">"+customerLong+"</td><td bgColor=\"#00ffff\">"+"."+"</td></tr>");
		
		//ans.add("<tr><td >Source tomcat folder size</td><td bgColor=\"#66ff66\">"+tomcatFolderSize+"</td><td bgColor=\"#00ffff\">"+"."+"</td></tr>");
		
		ans.add("</table>");
		
		ans.add("<input type=\"hidden\" name=\"user\" id=\"user\" value = \""+cfgMenuWebUser+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromDb\" id=\"fromDb\" value = \""+sourceDB+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromBn\" id=\"fromBn\" value = \""+sourceBN+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromSql\" id=\"fromSql\" value = \""+sourceSQL+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromApp\" id=\"fromApp\" value = \""+sourceAPP+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromCust\" id=\"fromCust\" value = \""+customerShort+"\" maxlength=\"140\" size=\"140\">");

		ans.add("<input type=\"hidden\" name=\"toDB\" id=\"toDB\" value = \""+toDB+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"toBatchName\" id=\"toBatchName\" value = \""+toBatchName+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"toSQL\" id=\"toSQL\" value = \""+toSQL+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"toAPP\" id=\"toAPP\" value = \""+toAPP+"\" maxlength=\"140\" size=\"140\">");

		ans.add("<input type=\"hidden\" name=\"myBrowser\" id=\"myBrowser\" maxlength=\"140\" size=\"140\">");
		
		ans.add("<input type=\"hidden\" name=\"toDb\" id=\"toDb\" value = \""+toDB+"\" maxlength=\"140\" size=\"140\">");

	//	ans.add("<br><INPUT TYPE=SUBMIT value=\"Replace my private environment\"onclick=\"document.getElementById('loader').style.display = 'block';\" ></FORM>");
	//	ans.add("<br><INPUT TYPE=SUBMIT value=\"Replace my private environment\" onclick=\"document.getElementById('loader').style.display = 'block'; this.style.display = 'none'\" ></FORM>");

	
		ans.add("<br><INPUT TYPE=SUBMIT value=\"Replace my private environment\" onclick=\"document.getElementById('loader1').style.display = ''; this.style.display = 'none'\" ></FORM>");
		
		
		String toAPP = HagUtil.getWord0(replaceCmEnvList.get(0), "~", 0,true);
		String toSQL = HagUtil.getWord0(replaceCmEnvList.get(0), "~", 1,true);
		String toDB = HagUtil.getWord0(replaceCmEnvList.get(0), "~", 2,true);
		String toBatchName = HagUtil.getWord0(replaceCmEnvList.get(0), "~", 2,true);
		*/
		//return replaceCmEnv( cbEnvs, cfgMenuWebUser,response,plat);
		return ans.toString();
	}
	public static String 		ReplaceEnvironmentUsingVb(HagStringList cbEnvs ,String cfgMenuWebUser,  HttpServletRequest request,String plat){
		System.out.println("1ReplaceEnvironmentUsingVb");
		//replace cm env gonen
		//6667
		if(!HagUtil.isIE(request))
			return HagUtil.shortHtml("this option can run on InternetExplorer only", "red","bg");

		if(cbEnvs.size()>1)
			return HagUtil.shortHtml("only one env (checkBox) can be selected for this option", "red","bg");
		if(cfgMenuWebUser.endsWith("guest"))
			return HagUtil.shortHtml("please login with your user - guest user cannot run this option", "red","bg");
	
		HagSQL hagSQL = new HagSQL();
		String stm = "select batchName,bis_server,sql_server,db,lastInst,lastGoodInst,owner,sql_server_version from dbo.ri_environments_new where status='A' and project='RI' order by bis_server,batchName";
		HagStringList list = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,8,"~",null,null);
		
		StringBuilder ans = new StringBuilder();
		ans.append("<FORM METHOD=POST name=\"Form\" ACTION=\"replaceEnvironmentPre.jsp\">");
		ans.append("<h3>Select target environment (this environemnt will be replaced)</h3>");
		ans.append("<br><table bgColor=\"#dddddd\"cellpadding=\"3\" cellspacing=\"3\" border =\"1\" >");
		ans.append("<tr><td>bis_server~batchName~sql_server~db</td></tr>");
		ans.append("<tr><td><select class=\"c30\" id=\"targetEnv\" name =\"targetEnv\" >");
		
		for(int k = 0 ; k < list.size();k++){
			String temp1 = list.get(k);
			String batchName = HagUtil.getWord0(temp1, "~", 0,true);
			String bis_server = HagUtil.getWord0(temp1, "~", 1,true);
			String sql_server = HagUtil.getWord0(temp1, "~", 2,true);
			String ownerTarget = HagUtil.getWord0(temp1, "~", 6,true);
			String sqlVersionTarget = HagUtil.getWord0(temp1, "~", 7,true);
			String db = HagUtil.getWord0(temp1, "~", 3,true);
			String ver = HagUtil.getWord0(temp1, "~", 4,true)+"("+HagUtil.getWord0(temp1, "~", 5,true)+")";
		//	String line = HagUtil.left(bis_server,"&nbsp;",30,"INIT")+"~"+HagUtil.left(batchName,"&nbsp;",30,"INIT")+"~"+HagUtil.left(sql_server,"&nbsp;",30,"INIT")+"~"+HagUtil.left(db,"&nbsp;",30,"INIT");
			String line = bis_server+"~"+batchName+"~"+sql_server+"~"+db+"~"+ver+"~"+ownerTarget+"~"+sqlVersionTarget;
			
			ans.append("<option class=\"c30\" value=\""+line+"\" >"+line+" </option>");
		}
		ans.append("</td></tr>");
		ans.append("</table><br>");

		
		ans.append("<br><table bgColor=\"#dddddd\"cellpadding=\"3\" cellspacing=\"3\" border =\"1\" >");
		
		ans.append("<tr><td>Include replace database process</td><td>Source User in RI.RIUSER table</td></tr>");
		ans.append("<tr><td><select class=\"c30\" id=\"withDB\" name =\"withDB\" >");
		ans.append("<option class=\"c30\" value=\"YES\">YES</option>");
		ans.append("<option class=\"c30\" value=\"NO\">NO</option>");
		ans.append("</td>");
		ans.append("<td><input type=\"text\" name=\"sourceRIUSER\" id=\"sourceRIUSER\" value = \"\" maxlength=\"40\" size=\"40>\"");
		ans.append("</td>only when withDB=NO </tr>");
		ans.append("</table><br>");
		
		ans.append("<br><table bgColor=\"#dddddd\"cellpadding=\"3\" cellspacing=\"3\" border =\"1\" >");
		ans.append("<tr><td>eObjectsOnly</td></tr>");
		ans.append("<tr><td><select class=\"c30\" id=\"eObjectsOnly\" name =\"eObjectsOnly\" >");
		ans.append("<option class=\"c30\" value=\"NO\">NO</option>");
		ans.append("<option class=\"c30\" value=\"YES\">YES</option>");
		ans.append("</td></tr>");
		ans.append("</table><br>");

		String sourceLine=cbEnvs.get(0);
		ans.append("<input type=\"hidden\" name=\"sourceLine\" id=\"sourceLine\" value = \"").append(sourceLine).append("\" maxlength=\"140\" size=\"140\">");
		ans.append("<input type=\"hidden\" name=\"toUser\" id=\"toUser\" value = \"").append(cfgMenuWebUser).append("\" maxlength=\"140\" size=\"140\">");
		ans.append("<input type=\"hidden\" name=\"toPlat\" id=\"toPlat\" value = \"").append(plat).append("\" maxlength=\"140\" size=\"140\">");
		ans.append("<br><INPUT TYPE=SUBMIT value=\"Replace CM env\" onclick=\"this.style.display = 'none'\" ></FORM>");
		
		
		// HagUtil.replaceCmEnvDB="DH1";
		// HagUtil.replaceCmEnvBatchName="DH";

		/*
		ans.add("<tr><td></td><td>Source</td><td>Target</td></tr>");
		ans.add("<tr><td >APP server name</td><td bgColor=\"#66ff66\">"+sourceAPP+"</td><td bgColor=\"#00ffff\">"+toAPP+"</td></tr>");
		ans.add("<tr><td >SQL server name</td><td bgColor=\"#66ff66\">"+sourceSQL+"</td><td bgColor=\"#00ffff\">"+toSQL+"</td></tr>");
		ans.add("<tr><td >Batch name</td><td bgColor=\"#66ff66\">"+sourceBN+"</td><td bgColor=\"#00ffff\">"+toBatchName+"</td></tr>");
		ans.add("<tr><td >Database name</td><td bgColor=\"#66ff66\">"+sourceDB+"</td><td bgColor=\"#00ffff\">"+toDB+"</td></tr>");
		ans.add("<tr><td >Database Dat size</td><td bgColor=\"#66ff66\">"+sourceDatSize+" mb</td><td bgColor=\"#00ffff\">"+targetDatSize+" mb</td></tr>");
		ans.add("<tr><td >Database Log size</td><td bgColor=\"#66ff66\">"+sourceLogSize+" mb</td><td bgColor=\"#00ffff\">"+targetLogSize+" mb</td></tr>");
		ans.add("<tr><td >log4j level</td><td bgColor=\"#66ff66\">"+log4jCombo+"</td><td bgColor=\"#00ffff\">.</td></tr>");
		ans.add("<tr><td >Customer name</td><td bgColor=\"#66ff66\">"+customerLong+"</td><td bgColor=\"#00ffff\">"+"."+"</td></tr>");
		
		//ans.add("<tr><td >Source tomcat folder size</td><td bgColor=\"#66ff66\">"+tomcatFolderSize+"</td><td bgColor=\"#00ffff\">"+"."+"</td></tr>");
		
		ans.add("</table>");
		
		ans.add("<input type=\"hidden\" name=\"user\" id=\"user\" value = \""+cfgMenuWebUser+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromDb\" id=\"fromDb\" value = \""+sourceDB+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromBn\" id=\"fromBn\" value = \""+sourceBN+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromSql\" id=\"fromSql\" value = \""+sourceSQL+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromApp\" id=\"fromApp\" value = \""+sourceAPP+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromCust\" id=\"fromCust\" value = \""+customerShort+"\" maxlength=\"140\" size=\"140\">");

		ans.add("<input type=\"hidden\" name=\"toDB\" id=\"toDB\" value = \""+toDB+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"toBatchName\" id=\"toBatchName\" value = \""+toBatchName+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"toSQL\" id=\"toSQL\" value = \""+toSQL+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"toAPP\" id=\"toAPP\" value = \""+toAPP+"\" maxlength=\"140\" size=\"140\">");

		ans.add("<input type=\"hidden\" name=\"myBrowser\" id=\"myBrowser\" maxlength=\"140\" size=\"140\">");
		
		ans.add("<input type=\"hidden\" name=\"toDb\" id=\"toDb\" value = \""+toDB+"\" maxlength=\"140\" size=\"140\">");

	//	ans.add("<br><INPUT TYPE=SUBMIT value=\"Replace my private environment\"onclick=\"document.getElementById('loader').style.display = 'block';\" ></FORM>");
	//	ans.add("<br><INPUT TYPE=SUBMIT value=\"Replace my private environment\" onclick=\"document.getElementById('loader').style.display = 'block'; this.style.display = 'none'\" ></FORM>");

	
		ans.add("<br><INPUT TYPE=SUBMIT value=\"Replace my private environment\" onclick=\"document.getElementById('loader1').style.display = ''; this.style.display = 'none'\" ></FORM>");
		
		
		String toAPP = HagUtil.getWord0(replaceCmEnvList.get(0), "~", 0,true);
		String toSQL = HagUtil.getWord0(replaceCmEnvList.get(0), "~", 1,true);
		String toDB = HagUtil.getWord0(replaceCmEnvList.get(0), "~", 2,true);
		String toBatchName = HagUtil.getWord0(replaceCmEnvList.get(0), "~", 2,true);
		*/
		//return replaceCmEnv( cbEnvs, cfgMenuWebUser,response,plat);
		return ans.toString();
	}
	
	///////////////////////////////////
	//after replaceCmEnvPre from  replaceCmDbPre.jsp
	public String 	replaceCmEnv(HttpServletRequest request, HttpServletResponse response){
		System.out.println("1replaceCmEnv");
	//	if(cbEnvs.size()>1)
	//		return HagUtil.shortHtml("only one env (checkBox) can be selected for this option", "red","bg");
	//	if(cfgMenuWebUser.endsWith("guest"))
	//		return HagUtil.shortHtml("please login with your user - guest user cannot run this option", "red","bg");
		String group		= request.getParameter( "group" );
		String toPlat		= request.getParameter( "toPlat" );
		String cfgMenuWebUser		= request.getParameter( "toUser" );
		String line		= request.getParameter( "sourceLine" );
		String targetEnv 	= request.getParameter("targetEnv").trim();
		String withDB 	= request.getParameter("withDB");
		String eObjectsOnly 	= request.getParameter("eObjectsOnly");
		String sourceRIUSER 	= request.getParameter("sourceRIUSER");
		
		String ownerSource=HagUtil.getWord0(line,"~",12,true);
		String sqlVersionSource=HagUtil.getWord0(line,"~",21,true);
		
		//HagStringList replaceCmEnvList= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\replaceCmEnv.hag",true,"*",false);

		String toAPP = HagUtil.getWord0(targetEnv, "~",0,true);
		String toSQL = HagUtil.getWord0(targetEnv, "~",2,true);
		String toDB =  HagUtil.getWord0(targetEnv, "~",3,true);
		String toVer =  HagUtil.getWord0(targetEnv, "~",4,true);
		String ownerTarget =  HagUtil.getWord0(targetEnv, "~",5,true);
		String sqlVersionTarget =  HagUtil.getWord0(targetEnv, "~",6,true);
		String toBatchName =  HagUtil.getWord0(targetEnv, "~",1,true);
						
		
		
		
		if(!group.equals("devOps") && !group.equals("vb") && !cfgMenuWebUser.equalsIgnoreCase(ownerTarget)){
			return HagUtil.shortHtml("to run Refresh env by owner option you must be target env owner","red","bg");
				
		}
		if(!group.equals("devOps") && !group.equals("devOps") && toAPP.toUpperCase().startsWith("RIDEVBLP")){
			return HagUtil.shortHtml("you cannot run Refresh env by owner option on RIAPPLDB servers","red","bg");
				
		}
		
		
		if(withDB.equals("NO") && (sourceRIUSER ==null || sourceRIUSER.length()!=2)) {
			return HagUtil.shortHtml("invalid input - when withDB=NO sourceRIUSER need to be string with 2 chars","red","bg");
				
		}
		if(sourceRIUSER!=null)
			sourceRIUSER = sourceRIUSER.toUpperCase();
		
		//String privateDb ="CW";
		//String targetAPP ="RI39-APP";
		//String targetSQL ="RI36-SQL";
		
		
	
		String sourceAPP=HagUtil.getWord0(line,"~",1,true);
		String sourceDB=HagUtil.getWord0(line,"~",11,true);
		String sourceBN=HagUtil.getWord0(line,"~",2,true);
		String sourceVer=HagUtil.getWord0(line,"~",4,true)+"("+HagUtil.getWord0(line,"~",5,true)+")";
	//	String sourceBN = sourceDB;
		//if(sourceDB.equals("RIAPPLDB"))
		//	sourceBN = "RI";
		String w3=HagUtil.getWord0(line,"~",3,true);
		String sourceSQL=HagUtil.getWord0(line,"~",10,true);
		//String ownerSource=HagUtil.getWord0(line,"~",12,true);
		
		//String sourceOwner=HagUtil.getWord0(line,"~",12,true);
		String sourceEnv=HagUtil.getWord0(line,"~",9,true);
		String customerLong = HagUtil.getCustomerByPartyLong(w3);
		String customerShort = HagUtil.getCustomerByPartyShort(w3);
	//	if(!sourceEnv.equals("qa") && !sourceEnv.equals("private") && !sourceEnv.equals("training") && !sourceEnv.equals("setup")&& !sourceEnv.equals("demo"))
	//		return HagUtil.shortHtml("for now only qa2,demo,setup,training or other private environments can be copied to target environment", "red","bg");
		
		if(toBatchName.equals(sourceBN) && toAPP.equals(sourceAPP) )
			return HagUtil.shortHtml("Cannot copy environment to itself", "red","bg");
		
		///String propFile = HagUtil.cfgMenuWebLoc+"\\lists\\cfgMenuWeb.properties";
		///String sizeLimit = HagUtil.getPropertyVal(propFile,"privateDbSize");
		///int sizeLimitI = HagUtil.s2i(sizeLimit);
		///String sourceDBsize = HagUtil.getWord0(sourceDatSize, ".",0,true);
		///int sourceDBsizeI = HagUtil.s2i(sourceDBsize);
		
		///if(sourceDBsizeI >sizeLimitI)
		///	return HagUtil.shortHtml("source-DB size is too big - please call hagay 2527 <BR>source-DB size = "+sourceDatSize+"<BR>DB size limit = "+sizeLimit, "red","bg");
	
		
		
		HagStringList ans1 = new HagStringList();
		String rc1= HagJdbc.getDbSize("SQL",sourceSQL,"cfg","cfg09c",sourceDB, ans1);
		String rc1a = HagUtil.replaceStr(rc1,"<tbody class=\"scrollingContent\"><tr><td>"+sourceDB+"</td><td>","");
		rc1a = HagUtil.replaceStr(rc1a,"</td></tr></tbody>","");
		rc1a = HagUtil.replaceStr(rc1a,"</td><td>","#");
		String sourceDatSize=HagUtil.getWord0(rc1a,"#",0,true);
		String sourceLogSize=HagUtil.getWord0(rc1a,"#",1,true);

		//if(sourceDatSize.indexOf(".")>4 || sourceLogSize.indexOf(".")>4)
		//	return HagUtil.shortHtml("source-DB size is too big - please call hagay 2527", "red","bg");
		
		
				
		HagStringList ans2 = new HagStringList();
		String rc2= HagJdbc.getDbSize("SQL",toSQL,"cfg","cfg09c",toDB, ans2);
		String rc2a = HagUtil.replaceStr(rc2,"<tbody class=\"scrollingContent\"><tr><td>"+toDB+"</td><td>","");
		rc2a = HagUtil.replaceStr(rc2a,"</td></tr></tbody>","");
		rc2a = HagUtil.replaceStr(rc2a,"</td><td>","#");
		String targetDatSize=HagUtil.getWord0(rc2a,"#",0,true);
		String targetLogSize=HagUtil.getWord0(rc2a,"#",1,true);
		//updateSapiensReg_START replace env (cm)
		String iFrameFolder="\\\\ri-archive\\Saptech-Archive\\RI\\debug\\cm_envs\\iframe";
		String iframeInit = HagUtil.copyFileViaDos(iFrameFolder+"\\iframeSkel.html", iFrameFolder+"\\"+toBatchName+".html");
		
		if(!iframeInit.startsWith("0~"))
			return HagUtil.shortHtml("Failed to init iFrameFile5. callhagay - 2527<br>"+iframeInit, "red","bg");
		String log4jCombo = "<select class=\"c30\" id=\"log4jCombo\" name =\"log4jCombo\" bgColor=\"#66ff66\"><option class=\"c30\" value=\"error\">error</option><option class=\"c30\" value=\"debug\">debug</option></select>";
		File sourceTomcatFolder = new File("\\\\"+sourceAPP+"\\ri_java\\RIjava_"+sourceBN+"\\tomcat");
		//long tomcatFolderSize = HagUtil.getFolderSize(sourceTomcatFolder);
		HagStringList ans = new HagStringList();
		
		
	
		
		
		ans.add("<head><script type=\"text/javascript\">");
		ans.add("function refreshIFrame() {");
		ans.add("var interval =setInterval(\"reloadIFrame();\", 3000);");
		ans.add("}");
		ans.add("function reloadIFrame() {");
		ans.add("document.getElementById('iframe_id').src += '';");
		ans.add("}");
		ans.add("function getBrowser()"); 
		ans.add("{");
	//	ans.add("var ua = window.navigator.userAgent;");
	//	ans.add("document.getElementById(\"myBrowser\").value = ua;");
		ans.add("}");
		ans.add("</script></head>	");
		ans.add("<body onload=\"refreshIFrame();getBrowser()\">");
		ans.add("<h3>Replace env by "+cfgMenuWebUser+"</h3>");
		ans.add("<FORM METHOD=POST name=\"Form\" ACTION=\"replaceCmDb.jsp\">");
		ans.add("<table bgColor=\"#cccccc\" border=\"2\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.add("<tr><td>					</td><td>						Source	    	 </td><td>						Target</td></tr>");
		ans.add("<tr><td>APP server name	</td><td bgColor=\"#66ff66\">"+sourceAPP+		"</td><td bgColor=\"#00ffff\">"+toAPP+			"</td></tr>");
		ans.add("<tr><td>SQL server name	</td><td bgColor=\"#66ff66\">"+sourceSQL+		"</td><td bgColor=\"#00ffff\">"+toSQL+			"</td></tr>");
		ans.add("<tr><td>Batch name			</td><td bgColor=\"#66ff66\">"+sourceBN+		"</td><td bgColor=\"#00ffff\">"+toBatchName+	"</td></tr>");
		ans.add("<tr><td>Database name		</td><td bgColor=\"#66ff66\">"+sourceDB+		"</td><td bgColor=\"#00ffff\">"+toDB+			"</td></tr>");
		ans.add("<tr><td>Database Dat size2	</td><td bgColor=\"#66ff66\">"+sourceDatSize+" mb</td><td bgColor=\"#00ffff\">"+targetDatSize+	" mb</td></tr>");
		ans.add("<tr><td>Database Log size	</td><td bgColor=\"#66ff66\">"+sourceLogSize+" mb</td><td bgColor=\"#00ffff\">"+targetLogSize+	" mb</td></tr>");
		if(withDB.equals("YES"))
			ans.add("<tr><td>RIUSER user    	</td><td bgColor=\"#66ff66\">"+sourceBN+"</td><td bgColor=\"#00ffff\">"+toBatchName+	"</td></tr>");
		else
			ans.add("<tr><td>RIUSER user    	</td><td bgColor=\"#66ff66\">"+sourceRIUSER+"</td><td bgColor=\"#00ffff\">"+toBatchName+	"</td></tr>");
		ans.add("<tr><td>log4j level		</td><td bgColor=\"#66ff66\">"+log4jCombo+		"</td><td bgColor=\"#00ffff\">.</td></tr>");
		ans.add("<tr><td>Customer name		</td><td bgColor=\"#66ff66\">"+customerLong+	"</td><td bgColor=\"#00ffff\">"+"."+"</td></tr>");
		ans.add("<tr><td>last Inst		</td><td bgColor=\"#66ff66\">"+sourceVer+	"</td><td bgColor=\"#00ffff\">"+toVer+"</td></tr>");
		ans.add("<tr><td>with DB		</td><td bgColor=\"#66ff66\">"+withDB+	"</td><td bgColor=\"#00ffff\">"+"."+"</td></tr>");
		if(eObjectsOnly.equals("YES"))
			ans.add("<tr bgColor=\"#ff3333\"><td>eObjects Only1		</td><td >"+eObjectsOnly+	"</td><td >"+"."+"</td></tr>");
		else
			ans.add("<tr><td>eObjects Only1		</td><td bgColor=\"#66ff66\">"+eObjectsOnly+	"</td><td bgColor=\"#00ffff\">"+"."+"</td></tr>");
		ans.add("<tr><td>Owner		</td><td bgColor=\"#66ff66\">"+ownerSource+	"</td><td bgColor=\"#00ffff\">"+ownerTarget+"</td></tr>");
		ans.add("<tr><td>SQL version		</td><td bgColor=\"#66ff66\">"+sqlVersionSource+	"</td><td bgColor=\"#00ffff\">"+sqlVersionTarget+"</td></tr>");
		
		//..			if(withDB.equals("YES"))
		//ans.add("<tr><td >Source tomcat folder size</td><td bgColor=\"#66ff66\">"+tomcatFolderSize+"</td><td bgColor=\"#00ffff\">"+"."+"</td></tr>");
	//	ans.add("<tr><td>Owner		</td><td bgColor=\"#66ff66\">"+sourceOwner+	"</td><td bgColor=\"#00ffff\">"+owner+"</td></tr>");
		
		ans.add("</table>");
		
		ans.add("<input type=\"hidden\" name=\"user\" 		id=\"user\" 	value = \""+cfgMenuWebUser+"\" 	maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"withDB\" 	id=\"withDB\" 	value = \""+withDB+"\" 	maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"sourceRIUSER\" 	id=\"withDB\" 	value = \""+sourceRIUSER+"\" 	maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"eObjectsOnly\" 	id=\"withDB\" 	value = \""+eObjectsOnly+"\" 	maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromDb\" 	id=\"fromDb\" 	value = \""+sourceDB+"\" 		maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromBn\" 	id=\"fromBn\" 	value = \""+sourceBN+"\" 		maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromSql\" 	id=\"fromSql\" 	value = \""+sourceSQL+"\" 		maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromApp\" 	id=\"fromApp\" 	value = \""+sourceAPP+"\" 		maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromCust\" 	id=\"fromCust\" value = \""+customerShort+"\" 	maxlength=\"140\" size=\"140\">");

		ans.add("<input type=\"hidden\" name=\"toDB\" 			id=\"toDB\" 		value = \""+toDB+"\" 		maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"toBatchName\" 	id=\"toBatchName\" 	value = \""+toBatchName+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"toSQL\" 			id=\"toSQL\" 		value = \""+toSQL+"\" 		maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"toAPP\" 			id=\"toAPP\" 		value = \""+toAPP+"\" 		maxlength=\"140\" size=\"140\">");
		
		ans.add("<input type=\"hidden\" name=\"sourceDatSize\" 			id=\"sourceDatSize\" 		value = \""+sourceDatSize+"\" 		maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"sqlVersionTarget\" 			id=\"sqlVersionTarget\" 		value = \""+sqlVersionTarget+"\" 		maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"sqlVersionSource\" 			id=\"sqlVersionSource\" 		value = \""+sqlVersionSource+"\" 		maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"group\" 			id=\"group\" 		value = \""+group+"\" 		maxlength=\"140\" size=\"140\">");
		
		ans.add("<input type=\"hidden\" name=\"myBrowser\" 		id=\"myBrowser\" 	maxlength=\"140\" size=\"140\">");
		
	

	//	ans.add("<br><INPUT TYPE=SUBMIT value=\"Replace my private environment\"onclick=\"document.getElementById('loader').style.display = 'block';\" ></FORM>");
	//	ans.add("<br><INPUT TYPE=SUBMIT value=\"Replace my private environment\" onclick=\"document.getElementById('loader').style.display = 'block'; this.style.display = 'none'\" ></FORM>");

	
		ans.add("<br><INPUT TYPE=SUBMIT value=\"Replace environment..\" onclick=\"document.getElementById('loader1').style.display = ''; this.style.display = 'none'\" ></FORM>");
		
		
		ans.add("<img id=\"loader\" src=\"Please_wait.gif\" style=\"display: none;\"/>");
		//ans.add("<input type=\"text\" id=\"loader1\" value =\"Please_wait33\" style=\"display: none;\"/>");


ans.add("<table bgColor=\"#00ff00\"><tr><td bgColor=\"#00ffff\"><textarea readonly style=\"color: red; background-color: lightyellow;display: none; \" cols=\"100\" rows=\"5\" id=\"loader1\">Please wait&#13;&#10;wait time depends on the DB and APP elements size&#13;&#10;usually takes a few minutes&#13;&#10;Take a coffee and relax&#13;&#10;HAKUNA MATATA</textarea></td></tr></table>");
ans.add("<br><iframe  id=\"iframe_id\" name=\"iframe_id\" src=\"file://///ri-archive/Saptech-Archive/RI/debug/cm_envs/iframe/"+toBatchName+".html\" height=\"850\" width=\"1500\"></iframe>");		
//ans.add("</body>"); 
return ans.convertToString(" ");
		/*
		int iwayServerPos = 12;
		if(plat.equalsIgnoreCase("I5OS")){
			iwayServerPos = 11;
		}
		String iwayS1 =  "I-WAY server";
		if(cbEnvs.size()==1){
			String temp = cbEnvs.get(0);
			String iwayServer = HagUtil.getWord0(temp,"~",iwayServerPos,true);
			if(iwayServer.trim().equalsIgnoreCase("NN")){
				iwayS1 =  "not defined please call gonen or hagay";
				HagUtil.shortHtml(iwayS1, "red","bg");
				return "1~";
			}
			try {
				response.sendRedirect("http://"+iwayServer+"/sapweb/Admin/AdmFrames.htm");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "0~";
		}
		StringBuilder ans =new StringBuilder();
		ans.append("<h3>Open multiple links of I-WAY servers</h3>");
		ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.append("<tr border=\"2\" bgColor=\"#ffff00\"><td>APP server</td><td>Batch name</td><td>"+iwayS1+"</td><td>Link to I-WAY server</td></tr>");

		for(int i = 0;i<cbEnvs.size();i++){
			String temp = cbEnvs.get(i);
			String appServer = HagUtil.getWord0(temp,"~",1,true);
			String batchName = HagUtil.getWord0(temp,"~",2,true);
			String iwayServer = HagUtil.getWord0(temp,"~",iwayServerPos,true);
			if(iwayServer.trim().equalsIgnoreCase("NN")){
				String str = "<a href=\"http://"+iwayServer+"/sapweb/Admin/AdmFrames.htm\" target=\"_blank\">not defined please call gonen or hagay</a>";
				ans.append("<tr bgColor=\"#ffffff\"><td>"+appServer+"</td><td>"+batchName+"</td><td>"+iwayServer+"</td><td>"+str+"</td></tr>");
			}else{
				String str = "<a href=\"http://"+iwayServer+"/sapweb/Admin/AdmFrames.htm\" target=\"_blank\">I-WAY Server</a>";
				ans.append("<tr bgColor=\"#ffffff\"><td>"+appServer+"</td><td>"+batchName+"</td><td>"+iwayServer+"</td><td>"+str+"</td></tr>");
			}
		}
		ans.append("</table>");
		return ans.toString();
		*/
	}
	//after replaceCmEnvPre from  replaceEnvironmentPre.jsp zzzz
	public String 	replaceEnv(HttpServletRequest request, HttpServletResponse response){
		System.out.println("1replaceEnv");
		//	if(cbEnvs.size()>1)
		//		return HagUtil.shortHtml("only one env (checkBox) can be selected for this option", "red","bg");
		//	if(cfgMenuWebUser.endsWith("guest"))
		//		return HagUtil.shortHtml("please login with your user - guest user cannot run this option", "red","bg");
			String toPlat		= request.getParameter( "toPlat" );
			String cfgMenuWebUser		= request.getParameter( "toUser" );
			String line		= request.getParameter( "sourceLine" );
			String targetEnv 	= request.getParameter("targetEnv").trim();
			String withDB 	= request.getParameter("withDB");
			String eObjectsOnly 	= request.getParameter("eObjectsOnly");
			String sourceRIUSER 	= request.getParameter("sourceRIUSER");
			String group		= request.getParameter( "group" );
			String ownerSource=HagUtil.getWord0(line,"~",12,true);
			String sqlVersionSource=HagUtil.getWord0(line,"~",21,true);
			
			//HagStringList replaceCmEnvList= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\replaceCmEnv.hag",true,"*",false);
			String toAPP = HagUtil.getWord0(targetEnv, "~",0,true);
			String toSQL = HagUtil.getWord0(targetEnv, "~",2,true);
			String toDB =  HagUtil.getWord0(targetEnv, "~",3,true);
			String toVer =  HagUtil.getWord0(targetEnv, "~",4,true);
			String ownerTarget =  HagUtil.getWord0(targetEnv, "~",5,true);
			String sqlVersionTarget =  HagUtil.getWord0(targetEnv, "~",6,true);
			//String owner =  HagUtil.getWord0(targetEnv, "~",5,true);
			String toBatchName =  HagUtil.getWord0(targetEnv, "~",1,true);

			
			
			if(withDB.equals("NO") && (sourceRIUSER ==null || sourceRIUSER.length()!=2)) {
				return HagUtil.shortHtml("invalid input - when withDB=NO sourceRIUSER need to be string with 2 chars","red","bg");
					
			}
			if(sourceRIUSER!=null)
				sourceRIUSER = sourceRIUSER.toUpperCase();
			
			//String privateDb ="CW";
			//String targetAPP ="RI39-APP";
			//String targetSQL ="RI36-SQL";
			
		
			String sourceAPP=HagUtil.getWord0(line,"~",1,true);
			String sourceDB=HagUtil.getWord0(line,"~",11,true);
			String sourceBN=HagUtil.getWord0(line,"~",2,true);
			String sourceVer=HagUtil.getWord0(line,"~",4,true)+"("+HagUtil.getWord0(line,"~",5,true)+")";
		
			///String sourceBN = sourceDB;
			//if(sourceDB.equals("RIAPPLDB"))
			//	sourceBN = "RI";
			String w3=HagUtil.getWord0(line,"~",3,true);
			String sourceSQL=HagUtil.getWord0(line,"~",10,true);
		//	String ownerSource=HagUtil.getWord0(line,"~",12,true);
			String sourceEnv=HagUtil.getWord0(line,"~",9,true);
			String customerLong = HagUtil.getCustomerByPartyLong(w3);
			String customerShort = HagUtil.getCustomerByPartyShort(w3);
		///	if(!sourceEnv.equalsIgnoreCase("qa") && !sourceEnv.equalsIgnoreCase("private") && !sourceEnv.equalsIgnoreCase("training") && 
		///			!sourceEnv.equalsIgnoreCase("setup")&& !sourceEnv.equalsIgnoreCase("cust"))
		///		return HagUtil.shortHtml("for now only qa3,setup,cust training or other private environments can be copied to your private environment", "red","bg");
			
			if(toBatchName.equals(sourceBN) && toAPP.equals(sourceAPP) )
				return HagUtil.shortHtml("Cannot copy environment to itself", "red","bg");
			
			HagStringList ans1 = new HagStringList();
			String rc1= HagJdbc.getDbSize("SQL",sourceSQL,"cfg","cfg09c",sourceDB, ans1);
			String rc1a = HagUtil.replaceStr(rc1,"<tbody class=\"scrollingContent\"><tr><td>"+sourceDB+"</td><td>","");
			rc1a = HagUtil.replaceStr(rc1a,"</td></tr></tbody>","");
			rc1a = HagUtil.replaceStr(rc1a,"</td><td>","#");
			String sourceDatSize=HagUtil.getWord0(rc1a,"#",0,true);
			String sourceLogSize=HagUtil.getWord0(rc1a,"#",1,true);

			//if(sourceDatSize.indexOf(".")>4 || sourceLogSize.indexOf(".")>4)
			//	return HagUtil.shortHtml("source-DB size is too big - please call hagay 2527", "red","bg");
			
			
					
			HagStringList ans2 = new HagStringList();
			String rc2= HagJdbc.getDbSize("SQL",toSQL,"cfg","cfg09c",toDB, ans2);
			String rc2a = HagUtil.replaceStr(rc2,"<tbody class=\"scrollingContent\"><tr><td>"+toDB+"</td><td>","");
			rc2a = HagUtil.replaceStr(rc2a,"</td></tr></tbody>","");
			rc2a = HagUtil.replaceStr(rc2a,"</td><td>","#");
			String targetDatSize=HagUtil.getWord0(rc2a,"#",0,true);
			String targetLogSize=HagUtil.getWord0(rc2a,"#",1,true);
			//updateSapiensReg cm gonen
			String iFrameFolder="\\\\ri-archive\\Saptech-Archive\\RI\\debug\\cm_envs\\iframe";
			String iframeInit = HagUtil.copyFileViaDos(iFrameFolder+"\\iframeSkel.html", iFrameFolder+"\\"+toBatchName+".html");
			
			if(!iframeInit.startsWith("0~"))
				return HagUtil.shortHtml("Failed to init iFrameFile5. callhagay - 2527<br>"+iframeInit, "red","bg");
			String log4jCombo = "<select class=\"c30\" id=\"log4jCombo\" name =\"log4jCombo\" bgColor=\"#66ff66\"><option class=\"c30\" value=\"error\">error</option><option class=\"c30\" value=\"debug\">debug</option></select>";
			File sourceTomcatFolder = new File("\\\\"+sourceAPP+"\\ri_java\\RIjava_"+sourceBN+"\\tomcat");
			//long tomcatFolderSize = HagUtil.getFolderSize(sourceTomcatFolder);
			HagStringList ans = new HagStringList();

			ans.add("<head><script type=\"text/javascript\">");
			ans.add("function refreshIFrame() {");
			ans.add("var interval =setInterval(\"reloadIFrame();\", 3000);");
			ans.add("}");
			ans.add("function reloadIFrame() {");
			ans.add("document.getElementById('iframe_id').src += '';");
			ans.add("}");
			ans.add("function getBrowser()"); 
			ans.add("{");
		//	ans.add("var ua = window.navigator.userAgent;");
		//	ans.add("document.getElementById(\"myBrowser\").value = ua;");
			ans.add("}");
			ans.add("</script></head>	");
			ans.add("<body onload=\"refreshIFrame();getBrowser()\">");
			ans.add("<h3>Replace2 private database for "+cfgMenuWebUser+"</h3>");
			ans.add("<FORM METHOD=POST name=\"Form\" ACTION=\"replaceEnvironment.jsp\">");
			ans.add("<table bgColor=\"#cccccc\" border=\"2\" cellpadding=\"3\" cellspacing=\"3\">");
			ans.add("<tr><td>					</td><td>						Source	    	 </td><td>						Target</td></tr>");
			ans.add("<tr><td>APP server name	</td><td bgColor=\"#66ff66\">"+sourceAPP+		"</td><td bgColor=\"#00ffff\">"+toAPP+			"</td></tr>");
			ans.add("<tr><td>SQL server name	</td><td bgColor=\"#66ff66\">"+sourceSQL+		"</td><td bgColor=\"#00ffff\">"+toSQL+			"</td></tr>");
			ans.add("<tr><td>Batch name			</td><td bgColor=\"#66ff66\">"+sourceBN+		"</td><td bgColor=\"#00ffff\">"+toBatchName+	"</td></tr>");
			ans.add("<tr><td>Database name		</td><td bgColor=\"#66ff66\">"+sourceDB+		"</td><td bgColor=\"#00ffff\">"+toDB+			"</td></tr>");
			ans.add("<tr><td>Database Dat size4	</td><td bgColor=\"#66ff66\">"+sourceDatSize+" mb</td><td bgColor=\"#00ffff\">"+targetDatSize+	" mb</td></tr>");
			ans.add("<tr><td>Database Log size	</td><td bgColor=\"#66ff66\">"+sourceLogSize+" mb</td><td bgColor=\"#00ffff\">"+targetLogSize+	" mb</td></tr>");
			if(withDB.equals("YES"))
				ans.add("<tr><td>RIUSER user    	</td><td bgColor=\"#66ff66\">"+sourceBN+"</td><td bgColor=\"#00ffff\">"+toBatchName+	"</td></tr>");
			else
				ans.add("<tr><td>RIUSER user    	</td><td bgColor=\"#66ff66\">"+sourceRIUSER+"</td><td bgColor=\"#00ffff\">"+toBatchName+	"</td></tr>");
			ans.add("<tr><td>log4j level		</td><td bgColor=\"#66ff66\">"+log4jCombo+		"</td><td bgColor=\"#00ffff\">.</td></tr>");
			ans.add("<tr><td>Customer name		</td><td bgColor=\"#66ff66\">"+customerLong+	"</td><td bgColor=\"#00ffff\">"+"."+"</td></tr>");
			ans.add("<tr><td>last Inst		</td><td bgColor=\"#66ff66\">"+sourceVer+	"</td><td bgColor=\"#00ffff\">"+toVer+"</td></tr>");
			ans.add("<tr><td>with DB		</td><td bgColor=\"#66ff66\">"+withDB+	"</td><td bgColor=\"#00ffff\">"+"."+"</td></tr>");
			if(eObjectsOnly.equals("YES"))
				ans.add("<tr bgColor=\"#ff3333\"><td>eObjects Only1		</td><td >"+eObjectsOnly+	"</td><td >"+"."+"</td></tr>");
			else
				ans.add("<tr><td>eObjects Only1		</td><td bgColor=\"#66ff66\">"+eObjectsOnly+	"</td><td bgColor=\"#00ffff\">"+"."+"</td></tr>");
			ans.add("<tr><td>Owner		</td><td bgColor=\"#66ff66\">"+ownerSource+	"</td><td bgColor=\"#00ffff\">"+ownerTarget+"</td></tr>");
			ans.add("<tr><td>Sql Version		</td><td bgColor=\"#66ff66\">"+sqlVersionSource+	"</td><td bgColor=\"#00ffff\">"+sqlVersionTarget+"</td></tr>");
			
			//..			if(withDB.equals("YES"))
			//ans.add("<tr><td >Source tomcat folder size</td><td bgColor=\"#66ff66\">"+tomcatFolderSize+"</td><td bgColor=\"#00ffff\">"+"."+"</td></tr>");
		//	ans.add("<tr><td>Owner		</td><td bgColor=\"#66ff66\">"+sourceOwner+	"</td><td bgColor=\"#00ffff\">"+owner+"</td></tr>");
			
			ans.add("</table>");
			
			ans.add("<textarea id=cmdTextArea name=cmdTextArea rows=\"10\" cols=\"150\">");
			ans.add(getInitCmd( sourceDB,  toDB,  sourceAPP,  toAPP,cfgMenuWebUser));
			ans.add("</textarea>");
			ans.add("<input type=\"hidden\" name=\"user\" 		id=\"user\" 	value = \""+cfgMenuWebUser+"\" 	maxlength=\"140\" size=\"140\">");
			ans.add("<input type=\"hidden\" name=\"withDB\" 	id=\"withDB\" 	value = \""+withDB+"\" 	maxlength=\"140\" size=\"140\">");
			ans.add("<input type=\"hidden\" name=\"sourceRIUSER\" 	id=\"withDB\" 	value = \""+sourceRIUSER+"\" 	maxlength=\"140\" size=\"140\">");
			ans.add("<input type=\"hidden\" name=\"eObjectsOnly\" 	id=\"withDB\" 	value = \""+eObjectsOnly+"\" 	maxlength=\"140\" size=\"140\">");
			ans.add("<input type=\"hidden\" name=\"fromDb\" 	id=\"fromDb\" 	value = \""+sourceDB+"\" 		maxlength=\"140\" size=\"140\">");
			ans.add("<input type=\"hidden\" name=\"fromBn\" 	id=\"fromBn\" 	value = \""+sourceBN+"\" 		maxlength=\"140\" size=\"140\">");
			ans.add("<input type=\"hidden\" name=\"fromSql\" 	id=\"fromSql\" 	value = \""+sourceSQL+"\" 		maxlength=\"140\" size=\"140\">");
			ans.add("<input type=\"hidden\" name=\"fromApp\" 	id=\"fromApp\" 	value = \""+sourceAPP+"\" 		maxlength=\"140\" size=\"140\">");
			ans.add("<input type=\"hidden\" name=\"fromCust\" 	id=\"fromCust\" value = \""+customerShort+"\" 	maxlength=\"140\" size=\"140\">");

			ans.add("<input type=\"hidden\" name=\"toDB\" 			id=\"toDB\" 		value = \""+toDB+"\" 		maxlength=\"140\" size=\"140\">");
			ans.add("<input type=\"hidden\" name=\"toBatchName\" 	id=\"toBatchName\" 	value = \""+toBatchName+"\" maxlength=\"140\" size=\"140\">");
			ans.add("<input type=\"hidden\" name=\"toSQL\" 			id=\"toSQL\" 		value = \""+toSQL+"\" 		maxlength=\"140\" size=\"140\">");
			ans.add("<input type=\"hidden\" name=\"toAPP\" 			id=\"toAPP\" 		value = \""+toAPP+"\" 		maxlength=\"140\" size=\"140\">");
			ans.add("<input type=\"hidden\" name=\"sourceDatSize\" 			id=\"sourceDatSize\" 		value = \""+sourceDatSize+"\" 		maxlength=\"140\" size=\"140\">");
			ans.add("<input type=\"hidden\" name=\"sqlVersionTarget\" 			id=\"sqlVersionTarget\" 		value = \""+sqlVersionTarget+"\" 		maxlength=\"140\" size=\"140\">");
			ans.add("<input type=\"hidden\" name=\"sqlVersionSource\" 			id=\"sqlVersionSource\" 		value = \""+sqlVersionSource+"\" 		maxlength=\"140\" size=\"140\">");
			ans.add("<input type=\"hidden\" name=\"group\" 			id=\"group\" 		value = \""+group+"\" 		maxlength=\"140\" size=\"140\">");
			
			
			ans.add("<input type=\"hidden\" name=\"myBrowser\" 		id=\"myBrowser\" 	maxlength=\"140\" size=\"140\">");
			
		

		//	ans.add("<br><INPUT TYPE=SUBMIT value=\"Replace my private environment\"onclick=\"document.getElementById('loader').style.display = 'block';\" ></FORM>");
		//	ans.add("<br><INPUT TYPE=SUBMIT value=\"Replace my private environment\" onclick=\"document.getElementById('loader').style.display = 'block'; this.style.display = 'none'\" ></FORM>");

		
			ans.add("<br><INPUT TYPE=SUBMIT value=\"Replace CM environment\" onclick=\"document.getElementById('loader1').style.display = ''; this.style.display = 'none'\" ></FORM>");
			
			
			ans.add("<img id=\"loader\" src=\"Please_wait.gif\" style=\"display: none;\"/>");
			//ans.add("<input type=\"text\" id=\"loader1\" value =\"Please_wait33\" style=\"display: none;\"/>");


	ans.add("<table bgColor=\"#00ff00\"><tr><td bgColor=\"#00ffff\"><textarea readonly style=\"color: red; background-color: lightyellow;display: none; \" cols=\"100\" rows=\"5\" id=\"loader1\">Please wait&#13;&#10;wait time depends on the DB and APP elements size&#13;&#10;usually takes a few minutes&#13;&#10;Take a coffee and relax&#13;&#10;HAKUNA MATATA</textarea></td></tr></table>");
	ans.add("<br><iframe  id=\"iframe_id\" name=\"iframe_id\" src=\"file://///ri-archive/Saptech-Archive/RI/debug/cm_envs/iframe/"+toBatchName+".html\" height=\"850\" width=\"1500\"></iframe>");		
	//ans.add("</body>"); 
	return ans.convertToString(" ");
			/*
			int iwayServerPos = 12;
			if(plat.equalsIgnoreCase("I5OS")){
				iwayServerPos = 11;
			}
			String iwayS1 =  "I-WAY server";
			if(cbEnvs.size()==1){
				String temp = cbEnvs.get(0);
				String iwayServer = HagUtil.getWord0(temp,"~",iwayServerPos,true);
				if(iwayServer.trim().equalsIgnoreCase("NN")){
					iwayS1 =  "not defined please call gonen or hagay";
					HagUtil.shortHtml(iwayS1, "red","bg");
					return "1~";
				}
				try {
					response.sendRedirect("http://"+iwayServer+"/sapweb/Admin/AdmFrames.htm");
				} catch (IOException e) {
					e.printStackTrace();
				}
				return "0~";
			}
			StringBuilder ans =new StringBuilder();
			ans.append("<h3>Open multiple links of I-WAY servers</h3>");
			ans.append("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" cellspacing=\"3\">");
			ans.append("<tr border=\"2\" bgColor=\"#ffff00\"><td>APP server</td><td>Batch name</td><td>"+iwayS1+"</td><td>Link to I-WAY server</td></tr>");

			for(int i = 0;i<cbEnvs.size();i++){
				String temp = cbEnvs.get(i);
				String appServer = HagUtil.getWord0(temp,"~",1,true);
				String batchName = HagUtil.getWord0(temp,"~",2,true);
				String iwayServer = HagUtil.getWord0(temp,"~",iwayServerPos,true);
				if(iwayServer.trim().equalsIgnoreCase("NN")){
					String str = "<a href=\"http://"+iwayServer+"/sapweb/Admin/AdmFrames.htm\" target=\"_blank\">not defined please call gonen or hagay</a>";
					ans.append("<tr bgColor=\"#ffffff\"><td>"+appServer+"</td><td>"+batchName+"</td><td>"+iwayServer+"</td><td>"+str+"</td></tr>");
				}else{
					String str = "<a href=\"http://"+iwayServer+"/sapweb/Admin/AdmFrames.htm\" target=\"_blank\">I-WAY Server</a>";
					ans.append("<tr bgColor=\"#ffffff\"><td>"+appServer+"</td><td>"+batchName+"</td><td>"+iwayServer+"</td><td>"+str+"</td></tr>");
				}
			}
			ans.append("</table>");
			return ans.toString();
			*/
		}
	//after replaceEnv from  replaceEnvironment.jsp
	public String 	replaceEnv2(HttpServletRequest request, HttpServletResponse response){
		String withDB 	= request.getParameter("withDB");
		System.out.println("1replaceEnv2");
		String group		= request.getParameter( "group" );
		String sqlVersionSource=request.getParameter("sqlVersionSource").trim();
		String sqlVersionTarget =  request.getParameter("sqlVersionTarget").trim();
	
		if(		sqlVersionSource!=null && 
				sqlVersionTarget!=null && 
				sqlVersionSource.trim().length()>0 &&  
				sqlVersionTarget.trim().length()>0) 		{
			
	
		int sqlVersionTargetI =HagUtil.s2i(sqlVersionTarget);
		int sqlVersionSourceI =HagUtil.s2i(sqlVersionSource);
		
		if(sqlVersionTargetI<sqlVersionSourceI && withDB.equals("YES"))
			return HagUtil.shortHtml("A cannot copy database from sql version "+sqlVersionSource+" to sql version "+sqlVersionTarget, "red", "bg");
	
	}
		StringBuilder ans = new StringBuilder("<h3>Replace private environment</h3><table bgColor=\"#aaaaaa\" cellpadding=\"3\" border =\"1\">");
		String user 	= request.getParameter("user").trim();
		String fromDB 	= request.getParameter("fromDb").trim();
		String fromBN 	= request.getParameter("fromBn").trim();
		String fromSql 	= request.getParameter("fromSql").trim();
		String fromApp 	= request.getParameter("fromApp").trim();
		String fromCust 	= request.getParameter("fromCust").trim();
		String toDB 	= request.getParameter("toDB").trim();
		String toAPP 	= request.getParameter("toAPP").trim();
		String toSQL 	= request.getParameter("toSQL").trim();
		String toBatchName 	= request.getParameter("toBatchName").trim();
		//String withDB 	= request.getParameter("withDB");
		String sourceRIUSER 	= request.getParameter("sourceRIUSER");	
		String cmdTextArea 	= request.getParameter("cmdTextArea");	
	
		String eObjectsOnly 	= request.getParameter("eObjectsOnly");
		
		String log4jLevel 	= request.getParameter("log4jCombo").trim();
		String myBrowser 	= request.getParameter("myBrowser").trim();
		String myWebAppsServer = request.getHeader("Host");
		
		
	String sourceDatSize =request.getParameter("sourceDatSize");
		
		String propFile = HagUtil.cfgMenuWebLoc+"\\lists\\cfgMenuWeb.properties";
		String sizeLimit = HagUtil.getPropertyVal(propFile,"privateDbSize");
		int sizeLimitI = HagUtil.s2i(sizeLimit);
		String sourceDBsize = HagUtil.getWord0(sourceDatSize, ".",0,true);
		int sourceDBsizeI = HagUtil.s2i(sourceDBsize);
		
		if(sourceDBsizeI >sizeLimitI)
			return HagUtil.shortHtml("A source-DB size is too big - please call hagay 2527 <BR>source-DB size = "+sourceDatSize+"<BR>DB size limit = "+sizeLimit, "red","bg");
	
		
		//HagStringList replaceCmEnvList= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\replaceCmEnv.hag",true,"*",false);
		//String toApp = HagUtil.getWord0(replaceCmEnvList.get(0), "~", 0,true);
		//String toSql = HagUtil.getWord0(replaceCmEnvList.get(0), "~", 1,true);
		//String toDb = HagUtil.getWord0(replaceCmEnvList.get(0), "~", 2,true);
		
		//String toApp 	= "RI39-APP";
		//String toSql 	= "RI36-SQL";
		//String toDb		 ="CW";
		//String toSql 	= "RIPERFSQL02";
		//String toApp 	= "RIPERFAPP02";
		
		//if(1==1)
		//	return myBrowser+"$$$"+myWebAppsServer;
		StringBuilder ans0 = new StringBuilder("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" border =\"1\">");
		ans0.append("<tr><td> myWebAppsServer</td><td>"+myWebAppsServer+"</td></tr>");
		ans0.append("<tr><td> myBrowser</td><td>"+myBrowser+"</td></tr>");
		ans0.append("<tr><td> user</td><td>"+user+"</td></tr>");
		ans0.append("<tr><td> fromDB</td><td>"+fromDB+"</td></tr>");
		ans0.append("<tr><td> fromBN</td><td>"+fromBN+"</td></tr>");
		ans0.append("<tr><td> fromSql</td><td>"+fromSql+"</td></tr>");
		ans0.append("<tr><td> fromApp</td><td>"+fromApp+"</td></tr>");
		ans0.append("<tr><td> fromCust</td><td>"+fromCust+"</td></tr>");
		ans0.append("<tr><td> toDb</td><td>"+toDB+"</td></tr>");
		ans0.append("<tr><td> log4jLevel</td><td>"+log4jLevel+"</td></tr>");
		ans0.append("</table>");
	
		
		String ans01		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+user,HagUtil.mailList_hag,"pre-refresh private from "+fromApp+"("+fromBN+") to "+toAPP+"("+toBatchName+") by "+user,ans0.toString()); 
		
		String stm1 = "select emerge_port,server_port,connection_port,debug_port from dbo.ri_environments_new where status='A' and  bis_server='"+toAPP+"' AND db='"+toDB+"'";
		//stm = HagUtil.replaceStr(stm,"{SERVER}","'"+server+"'");
		HagSQL hagSQL=new HagSQL();
		HagStringList privateDetails = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,4,"~",null,null);
		String emerge_port = HagUtil.getWord0(privateDetails.get(0),"~",0,true); 
		String serverPort = HagUtil.getWord0(privateDetails.get(0),"~",1,true);
		String connectionPort = HagUtil.getWord0(privateDetails.get(0),"~",2,true);
		String debugPort = HagUtil.getWord0(privateDetails.get(0),"~",3,true);
		
		String stm2 = "select tomcat_location,iom,version,patch,party,lastInst,lastGoodInst from dbo.ri_environments_new where status='A' and  bis_server='"+fromApp+"' AND db='"+fromDB+"'";
		HagStringList sourceDetails = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm2,7,"~",null,null);
		String iom = HagUtil.getWord0(sourceDetails.get(0),"~",1,true);
		String iomNfs = "\\\\"+fromApp+iom.substring(2,iom.length());
		String tomcat = HagUtil.getWord0(sourceDetails.get(0),"~",0,true);
		String tomcatNfs = "\\\\"+fromApp+tomcat.substring(2,tomcat.length());
		String version = HagUtil.getWord0(sourceDetails.get(0),"~",2,true);
		String patch = HagUtil.getWord0(sourceDetails.get(0),"~",3,true);
		String party = HagUtil.getWord0(sourceDetails.get(0),"~",4,true);
		String lastInst = HagUtil.getWord0(sourceDetails.get(0),"~",5,true);
		String lastGoodInst = HagUtil.getWord0(sourceDetails.get(0),"~",6,true);
		String startupNfs = "\\\\"+fromApp+"\\RI_JAVA\\RIjava_"+fromBN+"\\runtime\\startup";
		String debug2 = "\\\\ri-archive\\Saptech-Archive\\RI\\debug\\cm_envs\\"+toBatchName;
		String debug1 = "\\\\ri-archive\\Saptech-Archive\\RI\\debug\\cm_envs";
		HagRc debugRc=new HagRc();
		HagUtil.reCreateFolder(debugRc, debug2);
		if(debugRc.rc!=0)
			return 	HagUtil.shortHtml("debug folder creation failed - call hagay 2527","red","bg");
	
		
		if(eObjectsOnly.equals("YES")) {
			//replace eObjects
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceEobjects_START","");
			String clientLocation = HagUtil.regGetValSingleRemote(toAPP,"HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Sapiens\\Web","DBRoot","REG_SZ");
			//String clientLocation4530 = clientLocation+"4530.map";
			String replaceEobjects= replacePrivateDb_replaceEobjects(fromApp,fromBN,toAPP,toBatchName,user,clientLocation);
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceEobjects_END",replaceEobjects);
			HagUtil.writeStringToFile(debug2+"\\replaceEobjects.txt",replaceEobjects);
			ans.append("<tr><td>replace eObjects</td>"+replaceEobjects+"</tr>");
			ans.append("</table>");
			//String linkToDebug 	= "<a href=\"file:///ri-archive/Saptech-Archive/RI/debug/private_envs\">link to debug</a>";
			String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+user,HagUtil.mailList_hag,"refresh private from "+fromApp+"("+fromBN+") to "+toAPP+"("+toDB+") by "+user,ans.toString()); 
			return ans.toString();
		}
		
		String sourceTomcat = "\\\\"+fromApp+"\\RI_JAVA\\RIjava_"+fromBN;
		//stopTomcat
		
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","stopTomcat_START","");
		String stopTomcat= HagUtil.stopTomcat(toAPP, toBatchName);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","stopTomcat_END",toAPP+"("+toBatchName+")  "+stopTomcat);
		HagUtil.writeStringToFile(debug2+"\\stopTomcat.txt",stopTomcat);
		//stopEmergeListener
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","stopEmergeListener_START","");
		String stopEmergeListener= HagUtil.stopEmergeListener(toAPP, toBatchName);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","stopEmergeListener_END",toAPP+"("+toBatchName+")  "+stopEmergeListener);
		HagUtil.writeStringToFile(debug2+"\\stopEmergeListener.txt",stopEmergeListener);
		
		//copyDb
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","copyDb_START","");
		HagUtil.sleep(2000);
		String copyDb="ALREADY DONE BY CM-team";
		if(withDB.equals("YES")) {
			String copyDb1=""+cmdTextArea;
			copyDb =runVbCmd(copyDb1);
			
			//copyDb=copyDbGonen( fromDB,  toDB,  fromApp,  toAPP  );
		}
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","copyDb_END",copyDb);
		HagUtil.writeStringToFile(debug2+"\\copyDb.txt",copyDb);
	
		
		//set riprofileTable
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setRIProfileTable_START","");
		String setRIProfileTable = replacePrivateDb_setRiProfileTable( toAPP, toSQL, toDB, connectionPort,fromCust,toBatchName);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setRIProfileTable_END",setRIProfileTable);
		HagUtil.writeStringToFile(debug2+"\\setRIProfileTable.txt",setRIProfileTable);
	
		//set riuserTable
		String sourceRIuser1 = fromBN;
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setRIuserTable_START","");
		if(!withDB.equals("YES")){ 
			sourceRIuser1 = sourceRIUSER;
		}
		HagRc hatRc_setRiUserTable = new HagRc(); 
		String setRIuserTable = replacePrivateDb_setRiUserTable(sourceRIuser1,toSQL,toDB,toBatchName,hatRc_setRiUserTable);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setRIuserTable_END",setRIuserTable);
		//HagUtil.writeStringToFile(debug2+"\\setRIuserTable.txt",setRIuserTable);
		hatRc_setRiUserTable.log.writeToFile(debug2+"\\setRIuserTable.txt", false);
		
	
		//set ri
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setRIuser_START","");
		String setRIuser = replacePrivateDb_setRiUser(toSQL,toDB,toBatchName);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setRIuser_END",setRIuser);
		HagUtil.writeStringToFile(debug2+"\\setRIuser.txt",setRIuser);
		//set riadmin
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setRIADMINuser_START","");
		String setRIADMINuser = replacePrivateDb_setRiadminUser(toSQL,toDB);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setRIADMINuser_END",setRIADMINuser);
		HagUtil.writeStringToFile(debug2+"\\setRIADMINuser.txt",setRIADMINuser);
		//set adapter
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setAdapter_START","");
		String setAdapter = replacePrivateDb_setAdapter(toSQL,toDB);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setAdapter_END",setAdapter);
		HagUtil.writeStringToFile(debug2+"\\setAdapter.txt",setAdapter);
	//	//clear DB
	//	HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","clearDB_START","");
	//	String clearDB = replacePrivateDb_clearDB(toSQL,toDB);
	//	HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","clearDB_END",clearDB);
	//	HagUtil.writeStringToFile(debug2+"\\clearDB.txt",clearDB);
		//delete iom
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","deleteIom_START","");
		HagRc hagRcDelIom = new HagRc();
		String deleteIom = replacePrivateDb_deleteIom(toAPP,toBatchName,iomNfs,hagRcDelIom);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","deleteIom_END",deleteIom);
		
		HagUtil.writeStringToFile(debug2+"\\deleteIom.txt",hagRcDelIom.log.get(0));
	
		//replace iom
		HagRc hagRcCopyIom = new HagRc();
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceIom_START","");
		String replaceIom = replacePrivateDb_replaceIom(toAPP,toBatchName,iomNfs,hagRcCopyIom,debug2+"\\replaceIom_log.txt");
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceIom_END",replaceIom);
		HagUtil.writeStringToFile(debug2+"\\replaceIom.txt",hagRcCopyIom.log.get(0));
		
		//delete iom cust
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","deleteIomCust_START","");
		HagRc hagRcDelIomCust = new HagRc();
		String deleteIomCust = replacePrivateDb_deleteIomCust(toAPP,toBatchName,hagRcDelIomCust);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","deleteIomCust_END",deleteIomCust);
		HagUtil.writeStringToFile(debug2+"\\deleteIomCust.txt",hagRcDelIomCust.log.get(0));
			
		//replace iom
		HagRc hagRcCopyIomCust = new HagRc();
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceIomCust_START","");
		String replaceIomCust = replacePrivateDb_replaceIomCust(toAPP,toBatchName,iomNfs,hagRcCopyIomCust);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceIomCust_END",replaceIomCust);
		HagUtil.writeStringToFile(debug2+"\\replaceIomCust.txt",hagRcCopyIomCust.log.get(0));
		
		//replace startup
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceStartup_START","");
		String replaceStartup = replacePrivateDb_replaceStartup(toAPP,toBatchName,startupNfs);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceStartup_END",replaceStartup);
		HagUtil.writeStringToFile(debug2+"\\replaceStartup.txt",replaceStartup);

		//delete source tomcat logs
		//HagUtil.updateTimeStamp(debugHtmlFile,"deleteTomcatLogs_START","");
		//String deleteTomcatLogs = replacePrivateDb_deleteTomcatLogs(toApp,toDb,tomcatNfs);
		//HagUtil.updateTimeStamp(debugHtmlFile,"deleteTomcatLogs_END",deleteTomcatLogs);
		//HagUtil.writeStringToFile(debug2+"\\deleteTomcatLogs.txt",deleteTomcatLogs);
		
		//delete tomcat
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","recreateTomcatFolder_START",sourceTomcat);
		String recreateTomcatFolder = replacePrivateDb_deleteTomcat(toAPP,toBatchName,tomcatNfs);
		//String recreateTomcatFolder = replacePrivateDb_deleteTomcat(toApp,toDb);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","recreateTomcatFolder_END",recreateTomcatFolder);
		HagUtil.writeStringToFile(debug2+"\\recreateTomcatFolder.txt",recreateTomcatFolder);
		//copy tomcat
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","copyTomcat_START",sourceTomcat);
		String copyTomcat = replacePrivateDb_copyTomcat(toAPP,toBatchName,tomcatNfs,debug2+"\\replaceTomcat_log.txt");
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","copyTomcat_END",copyTomcat);
		HagUtil.writeStringToFile(debug2+"\\replaceTomcat.txt",copyTomcat);
		//replace Properties
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceProperties_START","");
		String replaceProperties = replacePrivateDb_replaceProperties(fromApp,fromBN,toAPP,toDB,toSQL,toBatchName);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceProperties_END",replaceProperties);
		HagUtil.writeStringToFile(debug2+"\\replaceProperties.txt",replaceProperties);
		//setTomcatProperties
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setTomcatProperties_START","");
		String setTomcatProperties = replacePrivateDb_setTomcatProperties(version,serverPort,connectionPort,debugPort,fromBN,toAPP,toBatchName,log4jLevel,debug2);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setTomcatProperties_END",setTomcatProperties);
		HagUtil.writeStringToFile(debug2+"\\setTomcatProperties.txt",setTomcatProperties);

		//replace eObjects
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceEobjects_START","");
		String clientLocation = HagUtil.regGetValSingleRemote(toAPP,"HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Sapiens\\Web","DBRoot","REG_SZ");
		//String clientLocation4530 = clientLocation+"4530.map";
		String replaceEobjects= replacePrivateDb_replaceEobjects(fromApp,fromBN,toAPP,toBatchName,user,clientLocation);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceEobjects_END",replaceEobjects);
		HagUtil.writeStringToFile(debug2+"\\replaceEobjects.txt",replaceEobjects);
		
		//replace replaceCmTable
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceCmTable_START","");
		String replaceCmTable= replacePrivateDb_replaceCmTable(toAPP,toBatchName,version,patch,party,lastInst,lastGoodInst);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceCmTable_END",replaceCmTable);
		HagUtil.writeStringToFile(debug2+"\\replaceCmTable.txt",replaceCmTable);
		
		//replace replaceJasper_Runtime_TemplatesFolder
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceJasper_START","");
		String replaceJasper_Runtime_TemplatesFolder= replaceJasper_Runtime_TemplatesFolder(fromApp, fromBN,toAPP,toBatchName,party,debug2+"\\replaceJasper_Runtime_TemplatesFolder_log.txt");
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceJasper_END",replaceJasper_Runtime_TemplatesFolder);
		HagUtil.writeStringToFile(debug2+"\\replaceJasper_Runtime_TemplatesFolder.txt",replaceJasper_Runtime_TemplatesFolder);
		
		//replace replaceInputfilesFolder
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","stopTomcat_START","");
		String replaceInputfilesFolder= replaceInputfilesFolder( fromApp, fromBN,toAPP,toBatchName);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","stopTomcat_END",replaceInputfilesFolder);
		HagUtil.writeStringToFile(debug2+"\\replaceInputfilesFolder.txt",replaceInputfilesFolder);	
		////
		//updateSiteExitTable
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","updateSiteExitTable_START","");
		String updateSiteExitTable= updateSiteExitTable( toBatchName, toSQL,toDB);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","updateSiteExitTable_END",updateSiteExitTable);
		HagUtil.writeStringToFile(debug2+"\\updateSiteExitTable.txt",updateSiteExitTable);	
	
		//updateTalendTable
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","updateTalendTable_START","");
		String updateTalendTable= updateTalendTable( );
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","updateTalendTable_END",updateTalendTable);
		HagUtil.writeStringToFile(debug2+"\\updateTalendTable.txt",updateTalendTable);	
		
		//updateSapiensReg
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","updateSapiensReg_START","");
		String updateSapiensReg= updateSapiensReg(  toAPP, toSQL,"RIADMIN","ADMINRI",toDB,toBatchName.toUpperCase()) ;
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","updateSapiensReg_END",updateSapiensReg);
		HagUtil.writeStringToFile(debug2+"\\updateSapiensReg.txt",updateSapiensReg);	
	////
		
		//startEmergeListener1
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","startEmergeListener_START","");
		String startEmergeListener= HagUtil.startEmergeListener1(toAPP, toBatchName);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","startEmergeListener_END",startEmergeListener);
		HagUtil.writeStringToFile(debug2+"\\startEmergeListener.txt",startEmergeListener);
		
	
		
		//startTomcat
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","startTomcat_START","");
		String startTomcat= HagUtil.startTomcat(toAPP, toBatchName,null);
		HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","startTomcat_END",startTomcat);

		HagUtil.writeStringToFile(debug2+"\\startTomcat.txt",startTomcat);	
	
		
		ans.append("<tr><td>stop tomcat</td>"+stopTomcat+"</tr>");
		ans.append("<tr><td>stop eMerge listener</td>"+stopEmergeListener+"</tr>");

		ans.append("<tr><td>replace private DB</td>"+copyDb+"</tr>");
		ans.append("<tr><td>set RIPROFILE table</td>"+setRIProfileTable+"</tr>");
		ans.append("<tr><td>set RIUSER table</td>"+setRIuserTable+"</tr>");
		ans.append("<tr><td>set RI SQL user</td>"+setRIuser+"</tr>");
		ans.append("<tr><td>set RIADMIN SQL user</td>"+setRIADMINuser+"</tr>");
		ans.append("<tr><td>set adapter: </td>"+setAdapter+"</tr>");
	//	ans.append("<tr><td>clearDB: </td>"+clearDB+"</tr>");
		ans.append("<tr><td>replace IOM</td>"+replaceIom+"</tr>");
		ans.append("<tr><td>replace IOM cust</td>"+replaceIomCust+"</tr>");
		ans.append("<tr><td>replace runtime startup</td>"+replaceStartup+"</tr>");
		//ans.append("<tr><td>delete tomcat logs(source)</td>"+deleteTomcatLogs+"</tr>");
		
		ans.append("<tr><td>recreate tomcat target folder</td>"+recreateTomcatFolder+"</tr>");
		ans.append("<tr><td>copy tomcat</td>"+copyTomcat+"</tr>");
		ans.append("<tr><td>Replace properties</td>"+replaceProperties+"</tr>");
		ans.append("<tr><td>Set tomcat properties</td>"+setTomcatProperties+"</tr>");
		ans.append("<tr><td>replace eObjects</td>"+replaceEobjects+"</tr>");
		ans.append("<tr><td>replace cmTable</td>"+replaceCmTable+"</tr>");
		ans.append("<tr><td>replace Jasper_Runtime_Template folder</td>"+replaceJasper_Runtime_TemplatesFolder+"</tr>");
		ans.append("<tr><td>replace INPUTFILES folder</td>"+replaceInputfilesFolder+"</tr>");
		ans.append("<tr><td>update SiteExit table</td>"+updateSiteExitTable+"</tr>");
		ans.append("<tr><td>update Talend table</td>"+updateTalendTable+"</tr>");
		ans.append("<tr><td>update sapiens reg</td>"+updateSapiensReg+"</tr>");
		ans.append("<tr><td>start eMerge listener</td>"+startEmergeListener+"</tr>");
		ans.append("<tr><td>start tomcat</td>"+startTomcat+"</tr>");
		ans.append("<tr><td>start iWay</td><td><a href=\"http://"+toAPP+"/sapweb/Admin/Logon.htm?"+toAPP+"&/sapweb&"+toAPP+"-"+toBatchName+"\">http://"+toAPP+"/sapweb/Admin/Logon.htm?"+toAPP+"&/sapweb&"+toAPP+"-"+toBatchName+"</a></td></tr>");
		ans.append("</table>");
		//String linkToDebug 	= "<a href=\"file:///ri-archive/Saptech-Archive/RI/debug/private_envs\">link to debug</a>";
		HagUtil.writeToRelDiary2("Refresh","WIN","CmEnv",".",".",".",".",".","from "+fromApp+"/"+fromBN,user,toAPP,toBatchName);
		
		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+user,HagUtil.mailList_hag,"refresh private from "+fromApp+"("+fromBN+") to "+toAPP+"("+toDB+") by "+user,ans.toString()); 
		return ans.toString();
	}
	//after replaceEnv2 from  replaceCmDb.jsp	
	public String 	replaceCmDb(HttpServletRequest request, HttpServletResponse response){
		System.out.println("1replaceCmDb");
			String sqlVersionSource=request.getParameter("sqlVersionSource").trim();
			String sqlVersionTarget =  request.getParameter("sqlVersionTarget").trim();
			String withDB 	= request.getParameter("withDB");
			
			if(		sqlVersionSource!=null && 
					sqlVersionTarget!=null && 
					sqlVersionSource.trim().length()>0 &&  
					sqlVersionTarget.trim().length()>0) 		{
			int sqlVersionTargetI =HagUtil.s2i(sqlVersionTarget);
			int sqlVersionSourceI =HagUtil.s2i(sqlVersionSource);
			if(sqlVersionTargetI<sqlVersionSourceI && withDB.equals("YES"))
				return HagUtil.shortHtml("cannot copy database from sql version "+sqlVersionSource+" to sql version "+sqlVersionTarget, "red", "bg");
		
			}
			StringBuilder ans = new StringBuilder("<h3>Replace private environment</h3><table bgColor=\"#aaaaaa\" cellpadding=\"3\" border =\"1\">");
			String user 	= request.getParameter("user").trim();
			String fromDB 	= request.getParameter("fromDb").trim();
			String fromBN 	= request.getParameter("fromBn").trim();
			String fromSql 	= request.getParameter("fromSql").trim();
			String fromApp 	= request.getParameter("fromApp").trim();
			String fromCust 	= request.getParameter("fromCust").trim();
			String toDB 	= request.getParameter("toDB").trim();
			String toAPP 	= request.getParameter("toAPP").trim();
			String toSQL 	= request.getParameter("toSQL").trim();
			String toBatchName 	= request.getParameter("toBatchName").trim();
			//String withDB 	= request.getParameter("withDB");
			String sourceRIUSER 	= request.getParameter("sourceRIUSER");	
			
			String eObjectsOnly 	= request.getParameter("eObjectsOnly");
			
			String log4jLevel 	= request.getParameter("log4jCombo").trim();
			String myBrowser 	= request.getParameter("myBrowser").trim();
			String myWebAppsServer = request.getHeader("Host");
			
		String sourceDatSize =request.getParameter("sourceDatSize");
			
			String propFile = HagUtil.cfgMenuWebLoc+"\\lists\\cfgMenuWeb.properties";
			String sizeLimit = HagUtil.getPropertyVal(propFile,"privateDbSize");
			int sizeLimitI = HagUtil.s2i(sizeLimit);
			String sourceDBsize = HagUtil.getWord0(sourceDatSize, ".",0,true);
			int sourceDBsizeI = HagUtil.s2i(sourceDBsize);
			
			if(sourceDBsizeI >sizeLimitI)
				return HagUtil.shortHtml("B source-DB size is too big - please call hagay 2527 <BR>source-DB size = "+sourceDatSize+"<BR>DB size limit = "+sizeLimit, "red","bg");

			
			//HagStringList replaceCmEnvList= new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\replaceCmEnv.hag",true,"*",false);
			//String toApp = HagUtil.getWord0(replaceCmEnvList.get(0), "~", 0,true);
			//String toSql = HagUtil.getWord0(replaceCmEnvList.get(0), "~", 1,true);
			//String toDb = HagUtil.getWord0(replaceCmEnvList.get(0), "~", 2,true);
			
			//String toApp 	= "RI39-APP";
			//String toSql 	= "RI36-SQL";
			//String toDb		 ="CW";
			//String toSql 	= "RIPERFSQL02";
			//String toApp 	= "RIPERFAPP02";
			
			//if(1==1)
			//	return myBrowser+"$$$"+myWebAppsServer;
			StringBuilder ans0 = new StringBuilder("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" border =\"1\">");
			ans0.append("<tr><td> myWebAppsServer</td><td>"+myWebAppsServer+"</td></tr>");
			ans0.append("<tr><td> myBrowser</td><td>"+myBrowser+"</td></tr>");
			ans0.append("<tr><td> user</td><td>"+user+"</td></tr>");
			ans0.append("<tr><td> fromDB</td><td>"+fromDB+"</td></tr>");
			ans0.append("<tr><td> fromBN</td><td>"+fromBN+"</td></tr>");
			ans0.append("<tr><td> fromSql</td><td>"+fromSql+"</td></tr>");
			ans0.append("<tr><td> fromApp</td><td>"+fromApp+"</td></tr>");
			ans0.append("<tr><td> fromCust</td><td>"+fromCust+"</td></tr>");
			ans0.append("<tr><td> toDb</td><td>"+toDB+"</td></tr>");
			ans0.append("<tr><td> log4jLevel</td><td>"+log4jLevel+"</td></tr>");
			ans0.append("</table>");
			String ans01		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+user,HagUtil.mailList_hag,"pre-refresh private from "+fromApp+"("+fromBN+") to "+toAPP+"("+toBatchName+") by "+user,ans0.toString()); 
			
			String stm1 = "select emerge_port,server_port,connection_port,debug_port from dbo.ri_environments_new where status='A' and  bis_server='"+toAPP+"' AND db='"+toDB+"'";
			//stm = HagUtil.replaceStr(stm,"{SERVER}","'"+server+"'");
			HagSQL hagSQL=new HagSQL();
			HagStringList privateDetails = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,4,"~",null,null);
			String emerge_port = HagUtil.getWord0(privateDetails.get(0),"~",0,true); 
			String serverPort = HagUtil.getWord0(privateDetails.get(0),"~",1,true);
			String connectionPort = HagUtil.getWord0(privateDetails.get(0),"~",2,true);
			String debugPort = HagUtil.getWord0(privateDetails.get(0),"~",3,true);
			
			String stm2 = "select tomcat_location,iom,version,patch,party,lastInst,lastGoodInst from dbo.ri_environments_new where status='A' and  bis_server='"+fromApp+"' AND db='"+fromDB+"'";
			HagStringList sourceDetails = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm2,7,"~",null,null);
			String iom = HagUtil.getWord0(sourceDetails.get(0),"~",1,true);
			String iomNfs = "\\\\"+fromApp+iom.substring(2,iom.length());
			String tomcat = HagUtil.getWord0(sourceDetails.get(0),"~",0,true);
			String tomcatNfs = "\\\\"+fromApp+tomcat.substring(2,tomcat.length());
			String version = HagUtil.getWord0(sourceDetails.get(0),"~",2,true);
			String patch = HagUtil.getWord0(sourceDetails.get(0),"~",3,true);
			String party = HagUtil.getWord0(sourceDetails.get(0),"~",4,true);
			String lastInst = HagUtil.getWord0(sourceDetails.get(0),"~",5,true);
			String lastGoodInst = HagUtil.getWord0(sourceDetails.get(0),"~",6,true);
			String startupNfs = "\\\\"+fromApp+"\\RI_JAVA\\RIjava_"+fromBN+"\\runtime\\startup";
			String debug2 = "\\\\ri-archive\\Saptech-Archive\\RI\\debug\\cm_envs\\"+toBatchName;
			String debug1 = "\\\\ri-archive\\Saptech-Archive\\RI\\debug\\cm_envs";
			HagRc debugRc=new HagRc();
			HagUtil.reCreateFolder(debugRc, debug2);
			if(debugRc.rc!=0)
				return 	HagUtil.shortHtml("debug folder creation failed - call hagay 2527","red","bg");

			
			if(eObjectsOnly.equals("YES")) {
				//replace eObjects
				HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceEobjects_START","");
				String clientLocation = HagUtil.regGetValSingleRemote(toAPP,"HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Sapiens\\Web","DBRoot","REG_SZ");
				//String clientLocation4530 = clientLocation+"4530.map";
				String replaceEobjects= replacePrivateDb_replaceEobjects(fromApp,fromBN,toAPP,toBatchName,user,clientLocation);
				HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceEobjects_END",replaceEobjects);
				HagUtil.writeStringToFile(debug2+"\\replaceEobjects.txt",replaceEobjects);
				ans.append("<tr><td>replace eObjects</td>"+replaceEobjects+"</tr>");
				ans.append("</table>");
				//String linkToDebug 	= "<a href=\"file:///ri-archive/Saptech-Archive/RI/debug/private_envs\">link to debug</a>";
				String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+user,HagUtil.mailList_hag,"refresh private from "+fromApp+"("+fromBN+") to "+toAPP+"("+toDB+") by "+user,ans.toString()); 
				return ans.toString();
			}
			
			String sourceTomcat = "\\\\"+fromApp+"\\RI_JAVA\\RIjava_"+fromBN;
			//stopTomcat
			
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","stopTomcat_START","");
			String stopTomcat= HagUtil.stopTomcat(toAPP, toBatchName);
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","stopTomcat_END",toAPP+"("+toBatchName+")  "+stopTomcat);
			HagUtil.writeStringToFile(debug2+"\\stopTomcat.txt",stopTomcat);
			//stopEmergeListener
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","stopEmergeListener_START","");
			String stopEmergeListener= HagUtil.stopEmergeListener(toAPP, toBatchName);
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","stopEmergeListener_END",toAPP+"("+toBatchName+")  "+stopEmergeListener);
			HagUtil.writeStringToFile(debug2+"\\stopEmergeListener.txt",stopEmergeListener);
			
			//copyDb
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","copyDb_START","");
			HagUtil.sleep(2000);
			String copyDb="ALREADY DONE BY CM-team";
			if(withDB.equals("YES"))
				copyDb=copyDb( fromDB,  toDB,  fromApp,  toAPP ,user );
			
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","copyDb_END",copyDb);
			HagUtil.writeStringToFile(debug2+"\\copyDb.txt",copyDb);
			//set riprofileTable
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setRIProfileTable_START","");
			String setRIProfileTable = replacePrivateDb_setRiProfileTable( toAPP, toSQL, toDB, connectionPort,fromCust,toBatchName);
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setRIProfileTable_END",setRIProfileTable);
			HagUtil.writeStringToFile(debug2+"\\setRIProfileTable.txt",setRIProfileTable);
			
			//set riuserTable
			String sourceRIuser1 = fromBN;
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setRIuserTable_START","");
			if(!withDB.equals("YES")){ 
				sourceRIuser1 = sourceRIUSER;
			}
			HagRc hatRc_setRiUserTable = new HagRc(); 
			String setRIuserTable = replacePrivateDb_setRiUserTable(sourceRIuser1,toSQL,toDB,toBatchName,hatRc_setRiUserTable);
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setRIuserTable_END",setRIuserTable);
			//HagUtil.writeStringToFile(debug2+"\\setRIuserTable.txt",setRIuserTable);
			hatRc_setRiUserTable.log.writeToFile(debug2+"\\setRIuserTable.txt", false);
			

			//set ri
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setRIuser_START","");
			String setRIuser = replacePrivateDb_setRiUser(toSQL,toDB,toBatchName);
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setRIuser_END",setRIuser);
			HagUtil.writeStringToFile(debug2+"\\setRIuser.txt",setRIuser);
			//set riadmin
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setRIADMINuser_START","");
			String setRIADMINuser = replacePrivateDb_setRiadminUser(toSQL,toDB);
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setRIADMINuser_END",setRIADMINuser);
			HagUtil.writeStringToFile(debug2+"\\setRIADMINuser.txt",setRIADMINuser);
			//set adapter
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setAdapter_START","");
			String setAdapter = replacePrivateDb_setAdapter(toSQL,toDB);
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setAdapter_END",setAdapter);
			HagUtil.writeStringToFile(debug2+"\\setAdapter.txt",setAdapter);
		//	//clearDB
		//	HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","clearDB_START","");
		//	String clearDB = replacePrivateDb_clearDB(toSQL,toDB);
		//	HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","clearDB_END",clearDB);
		//	HagUtil.writeStringToFile(debug2+"\\clearDB.txt",clearDB);
			//delete iom
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","deleteIom_START","");
			HagRc hagRcDelIom = new HagRc();
			String deleteIom = replacePrivateDb_deleteIom(toAPP,toBatchName,iomNfs,hagRcDelIom);
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","deleteIom_END",deleteIom);
			HagUtil.writeStringToFile(debug2+"\\deleteIom.txt",hagRcDelIom.log.get(0));

			//replace iom
			HagRc hagRcCopyIom = new HagRc();
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceIom_START","");
			String replaceIom = replacePrivateDb_replaceIom(toAPP,toBatchName,iomNfs,hagRcCopyIom,debug2+"\\replaceIom_log.txt");
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceIom_END",replaceIom);
			HagUtil.writeStringToFile(debug2+"\\replaceIom.txt",hagRcCopyIom.log.get(0));
			
			//delete iom cust
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","deleteIomCust_START","");
			HagRc hagRcDelIomCust = new HagRc();
			String deleteIomCust = replacePrivateDb_deleteIomCust(toAPP,toBatchName,hagRcDelIomCust);
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","deleteIomCust_END",deleteIomCust);
			HagUtil.writeStringToFile(debug2+"\\deleteIomCust.txt",hagRcDelIomCust.log.get(0));
				
			//replace iom
			HagRc hagRcCopyIomCust = new HagRc();
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceIomCust_START","");
			String replaceIomCust = replacePrivateDb_replaceIomCust(toAPP,toBatchName,iomNfs,hagRcCopyIomCust);
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceIomCust_END",replaceIomCust);
			HagUtil.writeStringToFile(debug2+"\\replaceIomCust.txt",hagRcCopyIomCust.log.get(0));
			
			//replace startup
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceStartup_START","");
			String replaceStartup = replacePrivateDb_replaceStartup(toAPP,toBatchName,startupNfs);
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceStartup_END",replaceStartup);
			HagUtil.writeStringToFile(debug2+"\\replaceStartup.txt",replaceStartup);

			//delete source tomcat logs
			//HagUtil.updateTimeStamp(debugHtmlFile,"deleteTomcatLogs_START","");
			//String deleteTomcatLogs = replacePrivateDb_deleteTomcatLogs(toApp,toDb,tomcatNfs);
			//HagUtil.updateTimeStamp(debugHtmlFile,"deleteTomcatLogs_END",deleteTomcatLogs);
			//HagUtil.writeStringToFile(debug2+"\\deleteTomcatLogs.txt",deleteTomcatLogs);
			
			//delete tomcat
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","recreateTomcatFolder_START",sourceTomcat);
			String recreateTomcatFolder = replacePrivateDb_deleteTomcat(toAPP,toBatchName,tomcatNfs);
			//String recreateTomcatFolder = replacePrivateDb_deleteTomcat(toApp,toDb);
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","recreateTomcatFolder_END",recreateTomcatFolder);
			HagUtil.writeStringToFile(debug2+"\\recreateTomcatFolder.txt",recreateTomcatFolder);
			//copy tomcat
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","copyTomcat_START",sourceTomcat);
			String copyTomcat = replacePrivateDb_copyTomcat(toAPP,toBatchName,tomcatNfs,debug2+"\\replaceTomcat_log.txt");
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","copyTomcat_END",copyTomcat);
			HagUtil.writeStringToFile(debug2+"\\replaceTomcat.txt",copyTomcat);
			//replace Properties
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceProperties_START","");
			String replaceProperties = replacePrivateDb_replaceProperties(fromApp,fromBN,toAPP,toDB,toSQL,toBatchName);
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceProperties_END",replaceProperties);
			HagUtil.writeStringToFile(debug2+"\\replaceProperties.txt",replaceProperties);
			//setTomcatProperties
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setTomcatProperties_START","");
			String setTomcatProperties = replacePrivateDb_setTomcatProperties(version,serverPort,connectionPort,debugPort,fromBN,toAPP,toBatchName,log4jLevel,debug2);
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","setTomcatProperties_END",setTomcatProperties);
			HagUtil.writeStringToFile(debug2+"\\setTomcatProperties.txt",setTomcatProperties);

			//replace eObjects
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceEobjects_START","");
			String clientLocation = HagUtil.regGetValSingleRemote(toAPP,"HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Sapiens\\Web","DBRoot","REG_SZ");
			//String clientLocation4530 = clientLocation+"4530.map";
			String replaceEobjects= replacePrivateDb_replaceEobjects(fromApp,fromBN,toAPP,toBatchName,user,clientLocation);
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceEobjects_END",replaceEobjects);
			HagUtil.writeStringToFile(debug2+"\\replaceEobjects.txt",replaceEobjects);
			
			//replace replaceCmTable
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceCmTable_START","");
			String replaceCmTable= replacePrivateDb_replaceCmTable(toAPP,toBatchName,version,patch,party,lastInst,lastGoodInst);
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceCmTable_END",replaceCmTable);
			HagUtil.writeStringToFile(debug2+"\\replaceCmTable.txt",replaceCmTable);
			
			//replace replaceJasper_Runtime_TemplatesFolder
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceJasper_START","");
			String replaceJasper_Runtime_TemplatesFolder= replaceJasper_Runtime_TemplatesFolder(fromApp, fromBN,toAPP,toBatchName,party,debug2+"\\replaceJasper_Runtime_TemplatesFolder_log.txt");
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","replaceJasper_END",replaceJasper_Runtime_TemplatesFolder);
			HagUtil.writeStringToFile(debug2+"\\replaceJasper_Runtime_TemplatesFolder.txt",replaceJasper_Runtime_TemplatesFolder);
			
			//replace replaceInputfilesFolder
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","stopTomcat_START","");
			String replaceInputfilesFolder= replaceInputfilesFolder( fromApp, fromBN,toAPP,toBatchName);
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","stopTomcat_END",replaceInputfilesFolder);
			HagUtil.writeStringToFile(debug2+"\\replaceInputfilesFolder.txt",replaceInputfilesFolder);	
			
			
			////////
			//updateSiteExitTable
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","updateSiteExitTable_START","");
			String updateSiteExitTable= updateSiteExitTable( toBatchName, toSQL,toDB);
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","updateSiteExitTable_END",updateSiteExitTable);
			HagUtil.writeStringToFile(debug2+"\\updateSiteExitTable.txt",updateSiteExitTable);	
			
			//updateTalendTable
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","updateTalendTable_START","");
			String updateTalendTable= updateTalendTable( );
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","updateTalendTable_END",updateTalendTable);
			HagUtil.writeStringToFile(debug2+"\\updateTalendTable.txt",updateTalendTable);	
			//updateSapiensReg
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","updateSapiensReg_START","");
			String updateSapiensReg= updateSapiensReg(  toAPP,toSQL,"RIADMIN","ADMINRI",toDB,toBatchName.toUpperCase()) ;
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","updateSapiensReg_END",updateSapiensReg);
			HagUtil.writeStringToFile(debug2+"\\updateSapiensReg.txt",updateSapiensReg);	
			///////////
			
			
			//startEmergeListener1
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","startEmergeListener_START","");
			String startEmergeListener= HagUtil.startEmergeListener1(toAPP, toBatchName);
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","startEmergeListener_END",startEmergeListener);
			HagUtil.writeStringToFile(debug2+"\\startEmergeListener.txt",startEmergeListener);
			//startTomcat
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","startTomcat_START","");
			String startTomcat= HagUtil.startTomcat(toAPP, toBatchName,null);
			HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toBatchName+".html","startTomcat_END",startTomcat);

			HagUtil.writeStringToFile(debug2+"\\startTomcat.txt",startTomcat);	

			
			ans.append("<tr><td>stop tomcat</td>"+stopTomcat+"</tr>");
			ans.append("<tr><td>stop eMerge listener</td>"+stopEmergeListener+"</tr>");

			ans.append("<tr><td>replace private DB</td>"+copyDb+"</tr>");
			ans.append("<tr><td>set RIPROFILE table</td>"+setRIProfileTable+"</tr>");
			ans.append("<tr><td>set RIUSER table</td>"+setRIuserTable+"</tr>");
			ans.append("<tr><td>set RI SQL user</td>"+setRIuser+"</tr>");
			ans.append("<tr><td>set RIADMIN SQL user</td>"+setRIADMINuser+"</tr>");
			ans.append("<tr><td>set adapter: </td>"+setAdapter+"</tr>");
		//	ans.append("<tr><td>clearDB: </td>"+clearDB+"</tr>");
			ans.append("<tr><td>replace IOM</td>"+replaceIom+"</tr>");
			ans.append("<tr><td>replace IOM cust</td>"+replaceIomCust+"</tr>");
			ans.append("<tr><td>replace runtime startup</td>"+replaceStartup+"</tr>");
			//ans.append("<tr><td>delete tomcat logs(source)</td>"+deleteTomcatLogs+"</tr>");
			
			ans.append("<tr><td>recreate tomcat target folder</td>"+recreateTomcatFolder+"</tr>");
			ans.append("<tr><td>copy tomcat</td>"+copyTomcat+"</tr>");
			ans.append("<tr><td>Replace properties</td>"+replaceProperties+"</tr>");
			ans.append("<tr><td>Set tomcat properties</td>"+setTomcatProperties+"</tr>");
			ans.append("<tr><td>replace eObjects</td>"+replaceEobjects+"</tr>");
			ans.append("<tr><td>replace cmTable</td>"+replaceCmTable+"</tr>");
			ans.append("<tr><td>replace Jasper_Runtime_Template folder</td>"+replaceJasper_Runtime_TemplatesFolder+"</tr>");
			ans.append("<tr><td>replace INPUTFILES folder</td>"+replaceInputfilesFolder+"</tr>");
			ans.append("<tr><td>update SiteExit table</td>"+updateSiteExitTable+"</tr>");
			ans.append("<tr><td>update Talend table</td>"+updateTalendTable+"</tr>");
			ans.append("<tr><td>update sapiens reg</td>"+updateSapiensReg+"</tr>");
			ans.append("<tr><td>start eMerge listener</td>"+startEmergeListener+"</tr>");
			ans.append("<tr><td>start tomcat</td>"+startTomcat+"</tr>");
			ans.append("<tr><td>start iWay</td><td><a href=\"http://"+toAPP+"/sapweb/Admin/Logon.htm?"+toAPP+"&/sapweb&"+toAPP+"-"+toBatchName+"\">http://"+toAPP+"/sapweb/Admin/Logon.htm?"+toAPP+"&/sapweb&"+toAPP+"-"+toBatchName+"</a></td></tr>");
			ans.append("</table>");
			//String linkToDebug 	= "<a href=\"file:///ri-archive/Saptech-Archive/RI/debug/private_envs\">link to debug</a>";
			HagUtil.writeToRelDiary2("Refresh","WIN","CmEnv",".",".",".",".",".","from "+fromApp+"/"+fromBN,user,toAPP,toBatchName);
			
			String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+user,HagUtil.mailList_hag,"refresh private from "+fromApp+"("+fromBN+") to "+toAPP+"("+toDB+") by "+user,ans.toString()); 
			return ans.toString();
		}
	
	public String 			copyDb(String fromDb, String toDb, String fromApp_server, String toApp_server ,String user ) {
		
		//String cmd ="\\\\ORSAYSERVER\\System32\\cscript.exe "+ // 08.04.2024
		String cmd ="cscript.exe "+
				"\\\\orsayserver\\d\\gonteam\\#VBscript_and_SQL\\"+HagParam.getVbProdOrTest()+"\\Backup_Restore.vbs "+
				"/OPT:\"BK&RS\" "+
				"/ProdTest:\"PROD\" "+
				"/User:\""+user+"\" "+
				"/BackupFileDir:\"\\\\ri-archive\\Saptech-Archive\\Temp\\TempBackups\\\" " +
				"/BackupFileName:\".\"  "+
				"/SrcAppServer:\""+fromApp_server+"\"  "+
				"/SrcDBID:\""+fromDb+"\"  "+
				"/TgtAppServer:\""+toApp_server+"\"  "+
				"/TgtDBID:\""+toDb+"\" "+
				"/LeaveTrace:\"Y\" "+
				"/Monitor:\"N\"";
			
		

		String copyDb =runVbCmd(cmd);
		return copyDb;
	}
	public  String 	runVbCmd(String str){
		

	//String opt = "GET_ROW";
	//String vals = list.convertToString(" ");

//	String outFile		="\\\\ri-archive\\Saptech-Archive\\cm\\work\\aa\\CCC\\Servers_Det.html";
//	String outFolder	="\\\\ri-archive\\Saptech-Archive\\cm\\work";
	String vbCmd		="\\\\orsayserver\\d\\gonteam\\#VBscript_and_SQL\\"+HagParam.getVbProdOrTest()+"\\VB_from_CFGWEB.vbs";
		

	//String vbLine="cscript "+vbCmd+" "+opt+" /DBIDS:\""+vals+"\"";

	
	String str1="before1**************:";
	String str2=str;			
	String str3="before**************:";
	//	String vbLine="cscript //nologo \\\\orsayserver\\d\\gonteam\\#VBscript_and_SQL\\PROD\\Gopenfiles.vbs   /Opt:\"Kill\" /Server:\""+toApp+"\"    /Object:\"_"+toDb+"\"";

	//String vbLine="cscript "+vbCmd+" "+vbVals;
	
	//String ans4= HagUtil.runCmd2(str+"\n",false);1
	HagClient hagClient = new HagClient();
	String server1=HagUtil.serverName;
	System.out.println("Server1 test test"+server1);
	HagStringList ans4List = hagClient.runNotSudo(server1, str);
	String ans4 = ans4List.convertToString("<br>");
	
	
	String str5="after1**************:";
	String str6=ans4;			
	String str7="after2**************:";
	
	String kuku = "D:\\temp\\kuku";
	File directory = new File(kuku);
	
	
	
//

//return str1+"<br>"+str2+"<br>"+str3+"<br>"+str5+"<br>"+str6+"<br>"+str7;
	String ans = "";
	String color = "bgColor = \"#00ff00\"";
	if (ans4.indexOf("1~") > -1 || ans4.indexOf("2~") > -1){
		ans = ans+", targetRst RC =FAILED<br><br><br>"+ans4;
		color = "bgColor = \"#ff0000\"";

	}else{
		ans = ans+", targetRst RC =OK<br><br><br>"+ans4 ;

	}
	return "<td "+color+">"+ans+"</td>";
}	
	public String 	replacePrivateDb(HttpServletRequest request, HttpServletResponse response){
		StringBuilder ans = new StringBuilder("<h3>Replace private environment</h3><table bgColor=\"#aaaaaa\" cellpadding=\"3\" border =\"1\">");
		
		String sqlVersionSource=request.getParameter("sqlVersionSource").trim();
		String sqlVersionTarget =  request.getParameter("sqlVersionTarget").trim();
		String withDB 	= request.getParameter("withDB");
		
		if(		sqlVersionSource!=null && 
				sqlVersionTarget!=null && 
				sqlVersionSource.trim().length()>0 &&  
				sqlVersionTarget.trim().length()>0) 		{
		int sqlVersionTargetI =HagUtil.s2i(sqlVersionTarget);
		int sqlVersionSourceI =HagUtil.s2i(sqlVersionSource);
		if(sqlVersionTargetI<sqlVersionSourceI && withDB.equals("YES") )
			return HagUtil.shortHtml("cannot copy database from sql version "+sqlVersionSource+" to sql version "+sqlVersionTarget, "red", "bg");
		}
		
		String user 	= request.getParameter("user").trim();
	
		String fromDB 	= request.getParameter("fromDb").trim();
		String fromBN 	= request.getParameter("fromBn").trim();
		String fromSql 	= request.getParameter("fromSql").trim();
		String fromApp 	= request.getParameter("fromApp").trim();
		String fromCust 	= request.getParameter("fromCust").trim();
		String toApp 	= request.getParameter("targetAPP").trim();
		String toSql 	= request.getParameter("targetSQL").trim();
		
	//	String ownerSource 	= request.getParameter("ownerSource").trim();
	//	String sqlVersionSource 	= request.getParameter("sqlVersionSource").trim();
		
		String toDb1 	= request.getParameter("toDb").trim();
		String toBatchName = request.getParameter("toBN").trim();;
		String log4jLevel 	= request.getParameter("log4jCombo").trim();
		String myBrowser 	= request.getParameter("myBrowser").trim();
		String myWebAppsServer = request.getHeader("Host");
		
		String sourceDatSize =request.getParameter("sourceDatSize");
		
		String propFile = HagUtil.cfgMenuWebLoc+"\\lists\\cfgMenuWeb.properties";
		String sizeLimit = HagUtil.getPropertyVal(propFile,"privateDbSize");
		int sizeLimitI = HagUtil.s2i(sizeLimit);
		String sourceDBsize = HagUtil.getWord0(sourceDatSize, ".",0,true);
		int sourceDBsizeI = HagUtil.s2i(sourceDBsize);
		
		if(sourceDBsizeI >sizeLimitI)
			return HagUtil.shortHtml("C source-DB size is too big - please call hagay 2527 <BR>source-DB size = "+sourceDatSize+"<BR>DB size limit = "+sizeLimit, "red","bg");
		
		
		//String toSql 	= "RIPERFSQL02";
		//String toApp 	= "RIPERFAPP02";
		
		//if(1==1)
		//	return myBrowser+"$$$"+myWebAppsServer;
		StringBuilder ans0 = new StringBuilder("<table bgColor=\"#aaaaaa\" cellpadding=\"3\" border =\"1\">");
		ans0.append("<tr><td> myWebAppsServer</td><td>"+myWebAppsServer+"</td></tr>");
		ans0.append("<tr><td> myBrowser</td><td>"+myBrowser+"</td></tr>");
		ans0.append("<tr><td> user</td><td>"+user+"</td></tr>");
		ans0.append("<tr><td> fromDB</td><td>"+fromDB+"</td></tr>");
		ans0.append("<tr><td> fromBN</td><td>"+fromBN+"</td></tr>");
		ans0.append("<tr><td> fromSql</td><td>"+fromSql+"</td></tr>");
		ans0.append("<tr><td> fromApp</td><td>"+fromApp+"</td></tr>");
		ans0.append("<tr><td> fromCust</td><td>"+fromCust+"</td></tr>");
		ans0.append("<tr><td> toDb</td><td>"+toDb1+"</td></tr>");
		ans0.append("<tr><td> log4jLevel</td><td>"+log4jLevel+"</td></tr>");
		ans0.append("</table>");
		 String[] ans111=HagUtil.getOsAndBrowser( request);
		 ans0.append("<br><br>for cm use:");
		 ans0.append("<br>os:");
		 ans0.append("<br>").append(ans111[0]);
		 ans0.append("<br>browser:");
		 ans0.append("<br>").append(ans111[1]);
		 ans0.append("<br>userAgent:");
		 ans0.append("<br>").append(ans111[2]);
		 ans0.append("<br>user:");
		 ans0.append("<br>").append(ans111[3]);
		String ans01		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+user,HagUtil.mailList_hag,"pre-refresh private from "+fromApp+"("+fromBN+") to "+toApp+"("+toDb1+") by "+user,ans0.toString()); 
		
		String stm1 = "select emerge_port,server_port,connection_port,debug_port from dbo.ri_environments_new where status='A' and bis_server='"+toApp+"' AND db='"+toDb1+"'";
		//stm = HagUtil.replaceStr(stm,"{SERVER}","'"+server+"'");
		HagSQL hagSQL=new HagSQL();
		HagStringList privateDetails = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm1,4,"~",null,null);
		String emerge_port = HagUtil.getWord0(privateDetails.get(0),"~",0,true); 
		String serverPort = HagUtil.getWord0(privateDetails.get(0),"~",1,true);
		String connectionPort = HagUtil.getWord0(privateDetails.get(0),"~",2,true);
		String debugPort = HagUtil.getWord0(privateDetails.get(0),"~",3,true);
		
		String stm2 = "select tomcat_location,iom,version,patch,party,lastInst,lastGoodInst from dbo.ri_environments_new where status='A' and bis_server='"+fromApp+"' AND db='"+fromDB+"'";
		HagStringList sourceDetails = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm2,7,"~",null,null);
		String iom = HagUtil.getWord0(sourceDetails.get(0),"~",1,true);
		String iomNfs = "\\\\"+fromApp+iom.substring(2,iom.length());
		String tomcat = HagUtil.getWord0(sourceDetails.get(0),"~",0,true);
		String tomcatNfs = "\\\\"+fromApp+tomcat.substring(2,tomcat.length());
		String version = HagUtil.getWord0(sourceDetails.get(0),"~",2,true);
		String patch = HagUtil.getWord0(sourceDetails.get(0),"~",3,true);
		String party = HagUtil.getWord0(sourceDetails.get(0),"~",4,true);
		String lastInst = HagUtil.getWord0(sourceDetails.get(0),"~",5,true);
		String lastGoodInst = HagUtil.getWord0(sourceDetails.get(0),"~",6,true);
		String startupNfs = "\\\\"+fromApp+"\\RI_JAVA\\RIjava_"+fromBN+"\\runtime\\startup";
		String debugBase = "\\\\ri-archive\\Saptech-Archive\\RI\\debug\\private_envs_new";
		String debugFolder = debugBase+"\\"+toApp+"_"+toBatchName;
		String debugHtmlFile =  debugBase+"\\iframes\\"+toApp+"_"+toBatchName+".html";
		
		String targetTomcatNfs = "\\\\"+toApp+"\\RI_JAVA\\RIjava_"+toBatchName+"\\tomcat";
		
		HagRc debugRc=new HagRc();
		HagUtil.reCreateFolder(debugRc, debugFolder);
		if(debugRc.rc!=0)
			return 	HagUtil.shortHtml("debug folder creation failed - call hagay 2527","red","bg");
		
		String sourceTomcat = "\\\\"+fromApp+"\\RI_JAVA\\RIjava_"+fromBN;
		//stopTomcat
		
		HagUtil.updateTimeStamp(debugHtmlFile,"stopTomcat_START","");
		String stopTomcat= HagUtil.stopTomcat(toApp, toBatchName);
		HagUtil.updateTimeStamp(debugHtmlFile,"stopTomcat_END",stopTomcat);
		HagUtil.writeStringToFile(debugFolder+"\\stopTomcat.txt",stopTomcat);
		//stopEmergeListener
		HagUtil.updateTimeStamp(debugHtmlFile,"stopEmergeListener_START","");
		String stopEmergeListener= HagUtil.stopEmergeListener(toApp, toBatchName);
		HagUtil.updateTimeStamp(debugHtmlFile,"stopEmergeListener_END",stopEmergeListener);
		HagUtil.writeStringToFile(debugFolder+"\\stopEmergeListener.txt",stopEmergeListener);
		
		//
		
		
		
		
		//copyDb
		HagUtil.updateTimeStamp(debugHtmlFile,"copyDb_START","");
		String copyDb=copyDb( fromDB,  toDb1,  fromApp,  toApp ,user );
		HagUtil.updateTimeStamp(debugHtmlFile,"copyDb_END",copyDb);
		HagUtil.writeStringToFile(debugFolder+"\\copyDb.txt",copyDb);
		//set riprofileTable
		HagUtil.updateTimeStamp(debugHtmlFile,"setRIProfileTable_START","");
		String setRIProfileTable = replacePrivateDb_setRiProfileTable( toApp, toSql, toDb1, connectionPort,fromCust,toBatchName);
		HagUtil.updateTimeStamp(debugHtmlFile,"setRIProfileTable_END",setRIProfileTable);
		HagUtil.writeStringToFile(debugFolder+"\\setRIProfileTable.txt",setRIProfileTable);
		//set riuserTable
		HagRc hatRc_setRiUserTable = new HagRc(); 
		HagUtil.updateTimeStamp(debugHtmlFile,"setRIuserTable_START","");
		String setRIuserTable = replacePrivateDb_setRiUserTable(fromBN,toSql,toDb1,toBatchName,hatRc_setRiUserTable);
		HagUtil.updateTimeStamp(debugHtmlFile,"setRIuserTable_END",setRIuserTable);
//		HagUtil.writeStringToFile(debug2+"\\setRIuserTable.txt",setRIuserTable);
		hatRc_setRiUserTable.log.writeToFile(debugFolder+"\\setRIuserTable.txt", false);
		//set ri
		HagUtil.updateTimeStamp(debugHtmlFile,"setRIuser_START","");
		String setRIuser = replacePrivateDb_setRiUser(toSql,toDb1,toBatchName);
		HagUtil.updateTimeStamp(debugHtmlFile,"setRIuser_END",setRIuser);
		HagUtil.writeStringToFile(debugFolder+"\\setRIuser.txt",setRIuser);
		//set riadmin
		HagUtil.updateTimeStamp(debugHtmlFile,"setRIADMINuser_START","");
		String setRIADMINuser = replacePrivateDb_setRiadminUser(toSql,toDb1);
		HagUtil.updateTimeStamp(debugHtmlFile,"setRIADMINuser_END",setRIADMINuser);
		HagUtil.writeStringToFile(debugFolder+"\\setRIADMINuser.txt",setRIADMINuser);
		//set adapter
		HagUtil.updateTimeStamp(debugHtmlFile,"setAdapter_START","");
		String setAdapter = replacePrivateDb_setAdapter(toSql,toDb1);
		HagUtil.updateTimeStamp(debugHtmlFile,"setAdapter_END",setAdapter);
		HagUtil.writeStringToFile(debugFolder+"\\setAdapter.txt",setAdapter);
		
		////clear DB
	//	HagUtil.updateTimeStamp(debugHtmlFile,"clearDB_START","");
	//	String clearDB = replacePrivateDb_clearDB(toSql,toDb1);
		//HagUtil.updateTimeStamp(debugHtmlFile,"clearDB_END",clearDB);
		//HagUtil.writeStringToFile(debugFolder+"\\clearDB.txt",clearDB);
		
		//kill locks
		String vbLine="cscript //nologo \\\\orsayserver\\d\\gonteam\\#VBscript_and_SQL\\PROD\\Gopenfiles.vbs   /Opt:\"Kill\" /Server:\""+toApp+"\"    /Object:\"_"+toDb1+"\"";
		String ansLock1 =  HagUtil.runCmd2(vbLine+"\n",false);
		HagUtil.sleep(3000);
		String ansLock2 =  HagUtil.runCmd2(vbLine+"\n",false);
		HagUtil.sleep(3000);
		String ansLock3 =  HagUtil.runCmd2(vbLine+"\n",false);
		
		HagUtil.writeStringToFile(debugFolder+"\\deletelockes.txt",ansLock1+"\n***\n"+ansLock2+"\n***\n"+ansLock3);

		//delete iom
		HagRc hagRcDelIom = new HagRc();
		HagUtil.updateTimeStamp(debugHtmlFile,"deleteIom_START","");
		String deleteIom = replacePrivateDb_deleteIom(toApp,toBatchName,iomNfs,hagRcDelIom);
		HagUtil.updateTimeStamp(debugHtmlFile,"deleteIom_END",deleteIom);
		HagUtil.writeStringToFile(debugFolder+"\\deleteIom.txt",hagRcDelIom.log.get(0));
			
		//replace iom
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceIom_START","");
		HagRc hagRcCopyIom = new HagRc();
		String replaceIom = replacePrivateDb_replaceIom(toApp,toBatchName,iomNfs,hagRcCopyIom,debugFolder+"\\replaceIom_log.txt");
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceIom_END",replaceIom);
		HagUtil.writeStringToFile(debugFolder+"\\replaceIom.txt",hagRcCopyIom.log.get(0));
		
		//delete iom cust
		HagRc hagRcDelIomCust = new HagRc();
		HagUtil.updateTimeStamp(debugHtmlFile,"deleteIomCust_START","");
		String deleteIomCust = replacePrivateDb_deleteIomCust(toApp,toBatchName,hagRcDelIomCust);
		HagUtil.updateTimeStamp(debugHtmlFile,"deleteIomCust_END",deleteIomCust);
		HagUtil.writeStringToFile(debugFolder+"\\deleteIomCust.txt",hagRcDelIomCust.log.get(0));
			
		//replace iom cust
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceIomCust_START","");
		HagRc hagRcCopyIomCust = new HagRc();
		String replaceIomCust = replacePrivateDb_replaceIomCust(toApp,toBatchName,iomNfs,hagRcCopyIomCust);
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceIomCust_END",replaceIomCust);
		HagUtil.writeStringToFile(debugFolder+"\\replaceIomCust.txt",hagRcCopyIomCust.log.get(0));
		
		//replace startup
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceStartup_START","");
		String replaceStartup = replacePrivateDb_replaceStartup(toApp,toBatchName,startupNfs);
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceStartup_END",replaceStartup);
		HagUtil.writeStringToFile(debugFolder+"\\replaceStartup.txt",replaceStartup);

		//delete source tomcat logs
		//HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","deleteTomcatLogs_START","");
		//String deleteTomcatLogs = replacePrivateDb_deleteTomcatLogs(toApp,toDb,tomcatNfs);
		//HagUtil.updateTimeStamp(debug1+"\\iframe\\"+toDb+".html","deleteTomcatLogs_END",deleteTomcatLogs);
		//HagUtil.writeStringToFile(debug2+"\\deleteTomcatLogs.txt",deleteTomcatLogs);
		
		//delete tomcat
		HagUtil.updateTimeStamp(debugHtmlFile,"recreateTomcatFolder_START",sourceTomcat);
		String recreateTomcatFolder = replacePrivateDb_deleteTomcat(toApp,toBatchName,tomcatNfs);
		//String recreateTomcatFolder = replacePrivateDb_deleteTomcat(toApp,toDb);
		HagUtil.updateTimeStamp(debugHtmlFile,"recreateTomcatFolder_END",recreateTomcatFolder);
		HagUtil.writeStringToFile(debugFolder+"\\recreateTomcatFolder.txt",recreateTomcatFolder);
		//copy tomcat
		HagUtil.updateTimeStamp(debugHtmlFile,"copyTomcat_START",sourceTomcat);
		String copyTomcat = replacePrivateDb_copyTomcat(toApp,toBatchName,tomcatNfs,debugFolder+"\\replaceTomcat_log.txt");
		HagUtil.updateTimeStamp(debugHtmlFile,"copyTomcat_END",copyTomcat);
		HagUtil.writeStringToFile(debugFolder+"\\replaceTomcat.txt",copyTomcat);
		//replace Properties
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceProperties_START","");
		String replaceProperties = replacePrivateDb_replaceProperties(fromApp,fromBN,toApp,toDb1,toSql,toBatchName);
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceProperties_END",replaceProperties);
		HagUtil.writeStringToFile(debugFolder+"\\replaceProperties.txt",replaceProperties);
		//setTomcatProperties
		HagUtil.updateTimeStamp(debugHtmlFile,"setTomcatProperties_START","");
		String setTomcatProperties = replacePrivateDb_setTomcatProperties(version,serverPort,connectionPort,debugPort,fromBN,toApp,toBatchName,log4jLevel,debugFolder);
		HagUtil.updateTimeStamp(debugHtmlFile,"setTomcatProperties_END",setTomcatProperties);
		HagUtil.writeStringToFile(debugFolder+"\\setTomcatProperties.txt",setTomcatProperties);
		//replace {batchName} eObjects
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceEobjects_START","");
		String clientLocation = HagUtil.regGetValSingleRemote(toApp,"HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Sapiens\\Web","DBRoot","REG_SZ");
	//	String clientLocation4530 = clientLocation+"4530.map";
		String replaceEobjects= replacePrivateDb_replaceEobjects(fromApp,fromBN,toApp,toBatchName,user,clientLocation);
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceEobjects_END",replaceEobjects);
		HagUtil.writeStringToFile(debugFolder+"\\replaceEobjects.txt",replaceEobjects);
		//replace replaceCmTable
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceCmTable_START","");
		String replaceCmTable= replacePrivateDb_replaceCmTable(toApp,toBatchName,version,patch,party,lastInst,lastGoodInst);
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceCmTable_END",replaceCmTable);
		HagUtil.writeStringToFile(debugFolder+"\\replaceCmTable.txt",replaceCmTable);
		//replace replaceJasper_Runtime_TemplatesFolder
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceJasper_START","");
		String replaceJasper_Runtime_TemplatesFolder= replaceJasper_Runtime_TemplatesFolder(fromApp, fromBN,toApp,toBatchName,party,debugFolder+"\\replaceJasper_Runtime_TemplatesFolder_log.txt");
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceJasper_END",replaceJasper_Runtime_TemplatesFolder);
		HagUtil.writeStringToFile(debugFolder+"\\replaceJasper_Runtime_TemplatesFolder.txt",replaceJasper_Runtime_TemplatesFolder);
		//replace replaceInputfilesFolder
		HagUtil.updateTimeStamp(debugHtmlFile,"stopTomcat_START","");
		String replaceInputfilesFolder= replaceInputfilesFolder( fromApp, fromBN,toApp,toBatchName);
		HagUtil.updateTimeStamp(debugHtmlFile,"stopTomcat_END",replaceInputfilesFolder);
		HagUtil.writeStringToFile(debugFolder+"\\replaceInputfilesFolder.txt",replaceInputfilesFolder);		
		/////
		//updateSiteExitTable
		HagUtil.updateTimeStamp(debugHtmlFile,"updateSiteExitTable_START","");
		String updateSiteExitTable= updateSiteExitTable(toBatchName, toSql,toDb1);
		HagUtil.updateTimeStamp(debugHtmlFile,"updateSiteExitTable_END",updateSiteExitTable);
		HagUtil.writeStringToFile(debugFolder+"\\updateSiteExitTable.txt",updateSiteExitTable);	
		//updateTalendTable
		HagUtil.updateTimeStamp(debugHtmlFile,"updateTalendTable_START","");
		String updateTalendTable= updateTalendTable( );
		HagUtil.updateTimeStamp(debugHtmlFile,"updateTalendTable_END",updateTalendTable);
		HagUtil.writeStringToFile(debugFolder+"\\updateTalendTable.txt",updateTalendTable);		
		//updateSapiensReg
		HagUtil.updateTimeStamp(debugHtmlFile,"updateSapiensReg_START","");
		String updateSapiensReg= updateSapiensReg( toApp, toSql,"RIADMIN","ADMINRI",toDb1,toBatchName.toUpperCase()) ;
		HagUtil.updateTimeStamp(debugHtmlFile,"updateSapiensReg_END",updateSapiensReg);
		HagUtil.writeStringToFile(debugFolder+"\\updateSapiensReg.txt",updateSapiensReg);	
		/////
		//startEmergeListener1
		HagUtil.updateTimeStamp(debugHtmlFile,"startEmergeListener_START","");
		String startEmergeListener= HagUtil.startEmergeListener1(toApp, toBatchName);
		HagUtil.updateTimeStamp(debugHtmlFile,"startEmergeListener_END",startEmergeListener);
		HagUtil.writeStringToFile(debugFolder+"\\startEmergeListener.txt",startEmergeListener);
		//startTomcat
		HagUtil.updateTimeStamp(debugHtmlFile,"startTomcat_START","");
		String startTomcat= HagUtil.startTomcat(toApp, toBatchName,null);
		HagUtil.updateTimeStamp(debugHtmlFile,"startTomcat_END",startTomcat);

		HagUtil.writeStringToFile(debugFolder+"\\startTomcat.txt",startTomcat);	
	
		
		ans.append("<tr><td>stop tomcat</td>"+stopTomcat+"</tr>");
		ans.append("<tr><td>stop eMerge listener</td>"+stopEmergeListener+"</tr>");

		ans.append("<tr><td>replace private DB</td>"+copyDb+"</tr>");
		ans.append("<tr><td>set RIPROFILE table</td>"+setRIProfileTable+"</tr>");
		ans.append("<tr><td>set RIUSER table</td>"+setRIuserTable+"</tr>");
		ans.append("<tr><td>set RI SQL user</td>"+setRIuser+"</tr>");
		ans.append("<tr><td>set RIADMIN SQL user</td>"+setRIADMINuser+"</tr>");
		ans.append("<tr><td>set adapter: </td>"+setAdapter+"</tr>");
	//	ans.append("<tr><td>clearDB: </td>"+clearDB+"</tr>");
		ans.append("<tr><td>replace IOM</td>"+replaceIom+"</tr>");
		ans.append("<tr><td>replace IOM cust</td>"+replaceIomCust+"</tr>");
		ans.append("<tr><td>replace runtime startup</td>"+replaceStartup+"</tr>");
		//ans.append("<tr><td>delete tomcat logs(source)</td>"+deleteTomcatLogs+"</tr>");
		
		ans.append("<tr><td>recreate tomcat target folder</td>"+recreateTomcatFolder+"</tr>");
		ans.append("<tr><td>copy tomcat</td>"+copyTomcat+"</tr>");
		ans.append("<tr><td>Replace properties</td>"+replaceProperties+"</tr>");
		ans.append("<tr><td>Set tomcat properties</td>"+setTomcatProperties+"</tr>");
		ans.append("<tr><td>replace eObjects</td>"+replaceEobjects+"</tr>");
		ans.append("<tr><td>replace cmTable</td>"+replaceCmTable+"</tr>");
		ans.append("<tr><td>replace Jasper_Runtime_Template folder</td>"+replaceJasper_Runtime_TemplatesFolder+"</tr>");
		ans.append("<tr><td>replace INPUTFILES folder</td>"+replaceInputfilesFolder+"</tr>");
		ans.append("<tr><td>update SiteExit table</td>"+updateSiteExitTable+"</tr>");
		ans.append("<tr><td>update Talend table</td>"+updateTalendTable+"</tr>");
		ans.append("<tr><td>update sapiens reg</td>"+updateSapiensReg+"</tr>");
		ans.append("<tr><td>start eMerge listener</td>"+startEmergeListener+"</tr>");
		ans.append("<tr><td>start tomcat</td>"+startTomcat+"</tr>");
		ans.append("<tr><td>start iWay</td><td><a href=\"http://"+toApp+"/sapweb/Admin/Logon.htm?"+toApp+"&/sapweb&"+toApp+"-"+toBatchName+"\">http://"+toApp+"/sapweb/Admin/Logon.htm?"+toApp+"&/sapweb&"+toApp+"-"+toDb1+"</a></td></tr>");
		ans.append("</table>");
		//String linkToDebug 	= "<a href=\"file:///ri-archive/Saptech-Archive/RI/debug/private_envs\">link to debug</a>";
		HagUtil.writeToRelDiary2("Refresh","WIN","PrivateEnv",".",".",".",".",".","from "+fromApp+"/"+fromBN,user,toApp,toDb1);
		String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+user,HagUtil.mailList_hag,"refresh private from "+fromApp+"("+fromBN+") to "+toApp+"("+toDb1+") by "+user,ans.toString()); 
		return ans.toString();
	}
	public String 	replacePrivateDbPre(HttpServletRequest request, HttpServletResponse response){
		System.out.println("replacePrivateDbPre");
		String [] cbEnvsTarget		= request.getParameterValues("cbEnvs");
		String user 	= request.getParameter("user").trim();
		String sourceAPP 	= request.getParameter("fromApp").trim();
		String sourceDB 	= request.getParameter("fromDb").trim();
		String sourceBN 	= request.getParameter("fromBn").trim();
		String sourceSQL 	= request.getParameter("fromSql").trim();
		String sourceEnv 	= request.getParameter("sourceEnv").trim();
		String customerShort 	= request.getParameter("customerShort").trim();
		String customerLong 	= request.getParameter("customerLong").trim();
		String ownerSource 	= request.getParameter("ownerSource").trim();
		String sqlVersionSource 	= request.getParameter("sqlVersionSource").trim();
		
		if(cbEnvsTarget.length>1)
			return HagUtil.shortHtml("only one env (checkBox) can be selected for this option", "red","bg");
		
		String line = cbEnvsTarget[0];
		String toApp 	= HagUtil.getWord0(line,"!",0,true);
		String toBN 	= HagUtil.getWord0(line,"!",1,true);
		String toSql 	= HagUtil.getWord0(line,"!",2,true);
		String toDb 	= HagUtil.getWord0(line,"!",3,true);
		String ownerTarget 	= HagUtil.getWord0(line,"!",4,true);
		String sqlVersionTarget 	= HagUtil.getWord0(line,"!",5,true);
		return replacePrivateEnvNew( user, request,response,sourceAPP, sourceSQL, sourceBN, sourceDB, sourceEnv, customerShort,customerLong,ownerSource,sqlVersionSource,toBN,toDb,toApp,toSql,ownerTarget,sqlVersionTarget);


}
	public String 	appendDevToPrivateEnvPre(HttpServletRequest request, HttpServletResponse response){
		
		String [] cbEnvsTarget		= request.getParameterValues("cbEnvs");
		String user 	= request.getParameter("user").trim();
		
		
		
		if(cbEnvsTarget.length>1)
			return HagUtil.shortHtml("only one env (checkBox) can be selected for this option", "red","bg");
		
		String line = cbEnvsTarget[0];
		String toApp 	= HagUtil.getWord0(line,"!",0,true);
		String toBatchName 	= HagUtil.getWord0(line,"!",1,true);
		String toSql 	= HagUtil.getWord0(line,"!",2,true);
		String toDb 	= HagUtil.getWord0(line,"!",3,true);
		//String sourceAPP,String sourceSQL,String sourceDB,String sourceEnv,String customerShort,
		return appendDevElementsToMyPrivateEnvNew( user,response,toBatchName,toDb,toApp,toSql);
		/*
		String line = cbEnvs[0];
		
		String toApp 	= HagUtil.getWord0(line,"!",0,true);
		String toBatchName 	= HagUtil.getWord0(line,"!",1,true);
		String toSql 	= HagUtil.getWord0(line,"!",2,true);
		String toDb 	= HagUtil.getWord0(line,"!",3,true);
		
		String user 	= request.getParameter("user").trim();
		
		String fromDB 	= request.getParameter("fromDb").trim();
		String fromBN 	= request.getParameter("fromBn").trim();
		String fromSql 	= request.getParameter("fromSql").trim();
		String fromApp 	= request.getParameter("fromApp").trim();
		String fromCust 	= request.getParameter("fromCust").trim();
		
	
		String myBrowser 	= request.getParameter("myBrowser").trim();
		String myWebAppsServer = request.getHeader("Host");
		
	//	return replacePrivateDbNew(
		//		user, myBrowser,myWebAppsServer,
		//		fromDB, fromBN, fromSql, fromApp, fromCust,
		//		toDb, toBatchName, toSql, toApp);
		
		HagStringList ans = new HagStringList();

	//	ans.add("<head><script type=\"text/javascript\">");
	//	ans.add("function refreshIFrame() {");
	//	ans.add("var interval =setInterval(\"reloadIFrame();\", 3000);");
	//	ans.add("}");
	//	ans.add("function reloadIFrame() {");
	//	ans.add("document.getElementById('iframe_id').src += '';");
	//	ans.add("}");
	//	ans.add("function getBrowser()"); 
	//	ans.add("{");
	////	ans.add("var ua = window.navigator.userAgent;");
	////	ans.add("document.getElementById(\"myBrowser\").value = ua;");
	//	ans.add("}");
	//	ans.add("</script></head>	");
		ans.add("<body onload=\"refreshIFrame();getBrowser()\">");
//		ans.add("<h3>Replace private database for "+cfgMenuWebUser+"</h3>");
		ans.add("<FORM METHOD=POST name=\"Form\" ACTION=\"replacePrivateDb.jsp\">");
//		ans.add("<table bgColor=\"#cccccc\" border=\"2\" cellpadding=\"3\" cellspacing=\"3\">");
//		ans.add("<tr><td></td><td>Source</td><td>Target</td></tr>");
//		ans.add("<tr><td >APP server name</td><td bgColor=\"#66ff66\">"+sourceAPP+"</td><td bgColor=\"#00ffff\">"+targetAPP+"</td></tr>");
//		ans.add("<tr><td >SQL server name</td><td bgColor=\"#66ff66\">"+sourceSQL+"</td><td bgColor=\"#00ffff\">"+targetSQL+"</td></tr>");
//		ans.add("<tr><td >Batch name</td><td bgColor=\"#66ff66\">"+sourceBN+"</td><td bgColor=\"#00ffff\">"+privateDb+"</td></tr>");
//		ans.add("<tr><td >Database name</td><td bgColor=\"#66ff66\">"+sourceDB+"</td><td bgColor=\"#00ffff\">"+privateDb+"</td></tr>");
//		ans.add("<tr><td >Database Dat size</td><td bgColor=\"#66ff66\">"+sourceDatSize+" mb</td><td bgColor=\"#00ffff\">"+targetDatSize+" mb</td></tr>");
//		ans.add("<tr><td >Database Log size</td><td bgColor=\"#66ff66\">"+sourceLogSize+" mb</td><td bgColor=\"#00ffff\">"+targetLogSize+" mb</td></tr>");
//		ans.add("<tr><td >log4j level</td><td bgColor=\"#66ff66\">"+log4jCombo+"</td><td bgColor=\"#00ffff\">.</td></tr>");
//		ans.add("<tr><td >Customer name</td><td bgColor=\"#66ff66\">"+customerLong+"</td><td bgColor=\"#00ffff\">"+"."+"</td></tr>");
	
		
	//	//ans.add("<tr><td >Source tomcat folder size</td><td bgColor=\"#66ff66\">"+tomcatFolderSize+"</td><td bgColor=\"#00ffff\">"+"."+"</td></tr>");
		
	//	ans.add("</table>");
		
		ans.add("<input type=\"hidden\" name=\"user\" id=\"user\" value = \""+user+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromDb\" id=\"fromDb\" value = \""+fromDB+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromBn\" id=\"fromBn\" value = \""+fromBN+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromSql\" id=\"fromSql\" value = \""+fromSql+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromApp\" id=\"fromApp\" value = \""+fromApp+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromCust\" id=\"fromCust\" value = \""+fromCust+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"targetAPP\" id=\"targetAPP\" value = \""+toApp+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"targetSQL\" id=\"targetSQL\" value = \""+toSql+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"myBrowser\" id=\"myBrowser\" maxlength=\"140\" size=\"140\">");
		
		ans.add("<input type=\"hidden\" name=\"toDb\" id=\"toDb\" value = \""+toDb+"\" maxlength=\"140\" size=\"140\">");

	//	ans.add("<br><INPUT TYPE=SUBMIT value=\"Replace my private environment\"onclick=\"document.getElementById('loader').style.display = 'block';\" ></FORM>");
	//	ans.add("<br><INPUT TYPE=SUBMIT value=\"Replace my private environment\" onclick=\"document.getElementById('loader').style.display = 'block'; this.style.display = 'none'\" ></FORM>");

	
		ans.add("<br><INPUT TYPE=SUBMIT value=\"Replace my private Environment\" onclick=\"document.getElementById('loader1').style.display = ''; this.style.display = 'none'\" ></FORM>");
		
		return ans.convertToString("");
		//ans.add("<img id=\"loader\" src=\"Please_wait.gif\" style=\"display: none;\"/>");
	//	//ans.add("<input type=\"text\" id=\"loader1\" value =\"Please_wait33\" style=\"display: none;\"/>");


//ans.add("<table bgColor=\"#00ff00\"><tr><td bgColor=\"#00ffff\"><textarea readonly style=\"color: red; background-color: lightyellow;display: none; \" cols=\"100\" rows=\"5\" id=\"loader1\">Please wait&#13;&#10;wait time depends on the DB and APP elements size&#13;&#10;usually takes a few minutes&#13;&#10;Take a coffee and relax&#13;&#10;HAKUNA MATATA</textarea></td></tr></table>");
//ans.add("<br><iframe  id=\"iframe_id\" name=\"iframe_id\" src=\"file://///ri-archive/Saptech-Archive/RI/debug/"+targetAPP+"_private_envs/iframe/"+privateDb+".html\" height=\"850\" width=\"1500\"></iframe>");		
*/

}
	public String 	appendDevToPrivateEnv(HttpServletRequest request, HttpServletResponse response){
	
	String [] prc8	= request.getParameterValues("prc8");
	String user		= request.getParameter( "user" );
	String devDb		= request.getParameter( "devDb" );
	String devAppServer	= request.getParameter( "devAppServer" );
	String devSqlServer	= request.getParameter( "devSqlServer" );
	String toDbName1		= request.getParameter( "privateDb" );
	String toBatchName1		= request.getParameter( "privateBN" );
	String toAPP	= request.getParameter( "privateAppServer" );
	String toSQL	= request.getParameter( "privateSqlServer" );
	String ver	= request.getParameter( "ver" );
	String toPack	= request.getParameter( "toPack" );
	String toIomNfs = "\\\\"+toAPP+"\\RI_SHARE\\"+toBatchName1+"\\IOM_Core";
	String devIomNfs = "\\\\"+devAppServer+"\\RI_SHARE\\"+devDb+"\\IOM";
	//String devIomCustNfs = "\\\\"+devAppServer+"\\RI_SHARE\\"+devDb+"\\IOM_Cust";
	String debugBase = "\\\\ri-archive\\Saptech-Archive\\RI\\debug\\dev2privateNew";
	String debugFolder = debugBase+"\\"+toAPP+"_"+toBatchName1;
	String debugHtmlFile = debugBase+"\\iframes\\"+toAPP+"_"+toBatchName1+".html";
	HagUtil.replaceStringInFile(debugHtmlFile, "Process", "Process:");
	if(prc8 == null || prc8.length==0) {
		return HagUtil.shortHtml("No action selected.", "red","bg");
	}
	HagUtil.replaceStringInFile(debugHtmlFile, "Estimated time", "Estimated time:");
	boolean continueFlag = true;
	String stopTomcat			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
	String stopEmergeListener	="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
	String deleteIom			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
	String replaceIom			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
	String deleteIomCust			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
	String replaceIomCust			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
	String getDdc				="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
	String loadDdc				="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
	String replaceWar			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
	String runMigMng			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
	String startEmergeListener	="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
	String startTomcat			="<td bgColor=\"#FFFF00\">skipped - not selected</td>";
	HagUtil.replaceStringInFile(debugHtmlFile, "Started at", "Started at:");

	//String[] timeReportStart = {".",".",".",".",".",".",".",".",".","."};
	//String[] timeReportEnd = {".",".",".",".",".",".",".",".",".","."};
	
	//stopTomcat
	HagUtil.updateTimeStamp(debugHtmlFile,"stopTomcat_START","");
	stopTomcat= HagUtil.stopTomcat(toAPP, toBatchName1);
	HagUtil.updateTimeStamp(debugHtmlFile,"stopTomcat_END",toAPP+"("+toBatchName1+")  "+stopTomcat);
	HagUtil.writeStringToFile(debugFolder+"\\stopTomcat.txt",stopTomcat);
	if(stopTomcat.startsWith("<td bgColor=\"#FF0000\">"))
		continueFlag=false;

	//stopEmergeListener
	HagUtil.updateTimeStamp(debugHtmlFile,"stopEmergeListener_START","");
	stopEmergeListener= HagUtil.stopEmergeListener(toAPP, toBatchName1);
	HagUtil.updateTimeStamp(debugHtmlFile,"stopEmergeListener_END",toAPP+"("+toBatchName1+")  "+stopEmergeListener);
	HagUtil.writeStringToFile(debugFolder+"\\stopEmergeListener.txt",stopEmergeListener);
	if(stopEmergeListener.startsWith("<td bgColor=\"#FF0000\">"))
		continueFlag=false;
	

	if(continueFlag && Arrays.asList(prc8).contains("Replace IOM folder")) {
		//delete iom
		HagUtil.updateTimeStamp(debugHtmlFile,"deleteIom_START","");
		HagRc hagRcDelIom = new HagRc();
		deleteIom = replacePrivateDb_deleteIom(toAPP,toBatchName1,toIomNfs,hagRcDelIom);
		HagUtil.updateTimeStamp(debugHtmlFile,"deleteIom_END",deleteIom);
		HagUtil.writeStringToFile(debugFolder+"\\deleteIom.txt",hagRcDelIom.log.convertToString("\n"));
		if(deleteIom.startsWith("<td bgColor=\"#FF0000\">"))
			continueFlag=false;
		
		
		//replace iom
		HagRc hagRcCopyIom = new HagRc();
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceIom_START","");
		replaceIom = replacePrivateDb_replaceIom(toAPP,toBatchName1,devIomNfs,hagRcCopyIom,debugFolder+"\\replaceIom_log.txt");
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceIom_END",replaceIom);
		HagUtil.writeStringToFile(debugFolder+"\\replaceIom.txt",hagRcCopyIom.log.convertToString("\n"));
		if(replaceIom.startsWith("<td bgColor=\"#FF0000\">"))
			continueFlag=false;
		
	}
	if(continueFlag && Arrays.asList(prc8).contains("Replace IOM_Cust folder")) {
		//delete iom cust
		HagUtil.updateTimeStamp(debugHtmlFile,"deleteIomCust_START","");
		HagRc hagRcDelIom = new HagRc();
		deleteIomCust = replacePrivateDb_deleteIomCust(toAPP,toBatchName1,hagRcDelIom);
		HagUtil.updateTimeStamp(debugHtmlFile,"deleteIomCust_END",deleteIomCust);
		HagUtil.writeStringToFile(debugFolder+"\\deleteIomCust.txt",hagRcDelIom.log.convertToString("\n"));
		if(deleteIomCust.startsWith("<td bgColor=\"#FF0000\">"))
			continueFlag=false;
		
		
		//replace iom cust
		HagRc hagRcCopyIomCust = new HagRc();
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceIomCust_START","");
		replaceIomCust = replacePrivateDb_replaceIomCust(toAPP,toBatchName1,devIomNfs,hagRcCopyIomCust);
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceIomCust_END",replaceIomCust);
		HagUtil.writeStringToFile(debugFolder+"\\replaceIomCust.txt",hagRcCopyIomCust.log.convertToString("\n"));
		if(replaceIom.startsWith("<td bgColor=\"#FF0000\">"))
			continueFlag=false;
		
	}
	if(continueFlag && Arrays.asList(prc8).contains("Replace DDC splits")) {
		//get ddc bcp
		HagRc hagRcGetDdc = new HagRc();
		HagUtil.updateTimeStamp(debugHtmlFile,"getDdc_START","");
		getDdc = getreplacePrivateDb_getDDC(devDb,devSqlServer,debugFolder,hagRcGetDdc);
		HagUtil.updateTimeStamp(debugHtmlFile,"getDdc_END",getDdc);
		HagUtil.writeStringToFile(debugFolder+"\\getDdc.txt",hagRcGetDdc.log.convertToString("\n"));
		if(getDdc.startsWith("<td bgColor=\"#FF0000\">"))
			continueFlag=false;
		
		//load ddc bcp
		HagRc hagRcLoadDdc = new HagRc();
		HagUtil.updateTimeStamp(debugHtmlFile,"loadDdc_START","");
		loadDdc = getreplacePrivateDb_loadDDC(toDbName1,toSQL,debugFolder,hagRcLoadDdc);
		HagUtil.updateTimeStamp(debugHtmlFile,"loadDdc_END",loadDdc);
		HagUtil.writeStringToFile(debugFolder+"\\loadDdc.txt",hagRcLoadDdc.log.convertToString("\n"));
		if(loadDdc.startsWith("<td bgColor=\"#FF0000\">"))
			continueFlag=false;
	}
	if(continueFlag && Arrays.asList(prc8).contains("Replace war")) {
		//replace war
		HagRc hagRcReplaceWar = new HagRc();
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceWar_START","");
		replaceWar= replacePrivateDb_replaceWar(toBatchName1,toAPP,hagRcReplaceWar,toPack);
		HagUtil.updateTimeStamp(debugHtmlFile,"replaceWar_END",toAPP+"("+toBatchName1+")  "+replaceWar);
		HagUtil.writeStringToFile(debugFolder+"\\replaceWar.txt",hagRcReplaceWar.log.convertToString("\n"));
		if(replaceWar.startsWith("<td bgColor=\"#FF0000\">"))
			continueFlag=false;
	}
	if(continueFlag && Arrays.asList(prc8).contains("Run mig-mng")) {
		//run mig-mng
		HagRc hagRcRunMigMng = new HagRc();
		HagUtil.updateTimeStamp(debugHtmlFile,"runMigMng_START","");
		runMigMng= replacePrivateDb_runMigMng(toBatchName1,toAPP,hagRcRunMigMng,ver,debugBase,debugHtmlFile,toPack);
		HagUtil.updateTimeStamp(debugHtmlFile,"runMigMng_END",toAPP+"("+toBatchName1+")  "+runMigMng);
		HagUtil.writeStringToFile(debugFolder+"\\runMigMng.txt",hagRcRunMigMng.log.convertToString("\n"));
		if(runMigMng.startsWith("<td bgColor=\"#FF0000\">"))
			continueFlag=false;
	}
		
	//startEmergeListener1
	HagUtil.updateTimeStamp(debugHtmlFile,"startEmergeListener_START","");
	startEmergeListener= HagUtil.startEmergeListener1(toAPP, toBatchName1);
	HagUtil.updateTimeStamp(debugHtmlFile,"startEmergeListener_END",startEmergeListener);
	HagUtil.writeStringToFile(debugFolder+"\\startEmergeListener.txt",startEmergeListener);
	
	//startTomcat
	HagUtil.updateTimeStamp(debugHtmlFile,"startTomcat_START","");
	startTomcat= HagUtil.startTomcat(toAPP, toBatchName1,replaceWar);
	HagUtil.updateTimeStamp(debugHtmlFile,"startTomcat_END",startTomcat);
	HagUtil.writeStringToFile(debugFolder+"\\startTomcat.txt",startTomcat);	
		
			
			
	StringBuilder ans = new StringBuilder("<h3>Replace private environment</h3><table bgColor=\"#aaaaaa\" cellpadding=\"3\" border =\"1\" >");
	ans.append("<tr><td>Stop tomcat					</td>"+stopTomcat+"</tr>");
	ans.append("<tr><td>Stop eMerge listener		</td>"+stopEmergeListener+"</tr>");
	ans.append("<tr><td>Replace IOM					</td>"+replaceIom+"</tr>");
	ans.append("<tr><td>Replace IOM	cust				</td>"+replaceIomCust+"</tr>");
	ans.append("<tr><td>Get DDC bcp files			</td>"+getDdc+"</tr>");
	ans.append("<tr><td>Load DDC bcp files			</td>"+loadDdc+"</tr>");
	ans.append("<tr><td>Replace War					</td>"+replaceWar+"</tr>");
	ans.append("<tr><td>Run mig-mng					</td>"+runMigMng+"</tr>");
	ans.append("<tr><td>Start eMerge listener		</td>"+startEmergeListener+"</tr>");
	ans.append("<tr><td>Start tomcat				</td>"+startTomcat+"</tr>");
	ans.append("<tr><td>Start iWay					</td><td><a href=\"http://"+toAPP+"/sapweb/Admin/Logon.htm?"+toAPP+"&/sapweb&"+toAPP+"-"+toBatchName1+"\">http://"+toAPP+"/sapweb/Admin/Logon.htm?"+toAPP+"&/sapweb&"+toAPP+"-"+toBatchName1+"</a></td></tr>");
	ans.append("</table>");
	
			
	//String linkToDebug 	= "<a href=\"file:///ri-archive/Saptech-Archive/RI/debug/private_envs\">link to debug</a>";
	String ans1		= HagUtil.sendMail_hagPre(HagUtil.cfgTeamMail,HagUtil.mailList_devOps+";"+user,HagUtil.mailList_hag,"Append DEV elements to my private env "+devAppServer+"("+devDb+") to "+toAPP+"("+toBatchName1+") by "+user,ans.toString()); 
	return ans.toString();

}
			
	///////////////////////
	
	public String 	replacePrivateEnvNew(String cfgMenuWebUser, HttpServletRequest request,HttpServletResponse response,String sourceAPP,String sourceSQL,String sourceBN,String sourceDB,String sourceEnv,
			String customerShort,String customerLong,String ownerSource,String sqlVersionSource,String privateBN,String privateDb,String targetAPP,String targetSQL,String ownerTarget,String sqlVersionTarget){
	//	System.out.println("replacePrivateEnvNew");
		
	
		
		
		if(privateDb.equals(sourceBN) && targetAPP.equals(sourceAPP) )
			return HagUtil.shortHtml("Cannot copy environment to itself", "red","bg");
		
		HagStringList ans1 = new HagStringList();
	//	String rc1= HagJdbc.getDbSize("SQL",sourceSQL,"cfg","cfg09c",sourceDB, ans1);
		String rc1= HagJdbc.getDbSize("SQL",sourceSQL,"ADMIN","ADMIN",sourceDB, ans1);
		String rc1a = HagUtil.replaceStr(rc1,"<tbody class=\"scrollingContent\"><tr><td>"+sourceDB+"</td><td>","");
		rc1a = HagUtil.replaceStr(rc1a,"</td></tr></tbody>","");
		rc1a = HagUtil.replaceStr(rc1a,"</td><td>","#");
		String sourceDatSize=HagUtil.getWord0(rc1a,"#",0,true);
		String sourceLogSize=HagUtil.getWord0(rc1a,"#",1,true);

		
		String propFile = HagUtil.cfgMenuWebLoc+"\\lists\\cfgMenuWeb.properties";
		String sizeLimit = HagUtil.getPropertyVal(propFile,"privateDbSize");
		int sizeLimitI = HagUtil.s2i(sizeLimit);
		String sourceDBsize = HagUtil.getWord0(sourceDatSize, ".",0,true);
		int sourceDBsizeI = HagUtil.s2i(sourceDBsize);
		
		String withDB 	= request.getParameter("withDB");
		if(sourceDBsizeI >sizeLimitI)
			return HagUtil.shortHtml("D source-DB size is too big - please call hagay 2527 <BR>source-DB size = "+sourceDatSize+"<BR>DB size limit = "+sizeLimit, "red","bg");
		
		HagStringList ans2 = new HagStringList();
		String rc2= HagJdbc.getDbSize("SQL",targetSQL,"cfg","cfg09c",privateDb, ans2);
		String rc2a = HagUtil.replaceStr(rc2,"<tbody class=\"scrollingContent\"><tr><td>"+privateDb+"</td><td>","");
		rc2a = HagUtil.replaceStr(rc2a,"</td></tr></tbody>","");
		rc2a = HagUtil.replaceStr(rc2a,"</td><td>","#");
		String targetDatSize=HagUtil.getWord0(rc2a,"#",0,true);
		String targetLogSize=HagUtil.getWord0(rc2a,"#",1,true);
		//updateSapiensReg_START private
		String iFrameTarget="\\\\ri-archive\\Saptech-Archive\\RI\\debug\\private_envs_new\\iframes\\"+targetAPP+"_"+privateBN+".html";
		String iFrameSkel="\\\\ri-archive\\Saptech-Archive\\RI\\debug\\private_envs_new\\iframeSkel.html";
		String iframeInit = HagUtil.copyFileViaDos(iFrameSkel, iFrameTarget);
		
		if(!iframeInit.startsWith("0~"))
			return HagUtil.shortHtml("Failed to init iFrameFile4. callhagay - 2527<br>"+iframeInit, "red","bg");
		String log4jCombo = "<select class=\"c30\" id=\"log4jCombo\" name =\"log4jCombo\" bgColor=\"#66ff66\"><option class=\"c30\" value=\"error\">error</option><option class=\"c30\" value=\"debug\">debug</option></select>";
		String note1 =      "<select class=\"c30\" id=\"note1\"      name =\"note1\"      bgColor=\"#66ff66\"><option class=\"c30\" value=\"Other\">Other</option><option class=\"c30\" value=\"Initial\">Initial</option><option class=\"c30\" value=\"Vanilla\">Vanilla</option><option class=\\\"c30\\\" value=\\\"Setup\\\">Setup</option></select>";
		String note2 = "<input type=\"text\" name=\"note2\" id=\"note2\" maxlength=\"140\" size=\"140\">";
		File sourceTomcatFolder = new File("\\\\"+sourceAPP+"\\ri_java\\RIjava_"+sourceBN+"\\tomcat");
		HagStringList ans = new HagStringList();

		ans.add("<head><script type=\"text/javascript\">");
		ans.add("function refreshIFrame() {");
		ans.add("var interval =setInterval(\"reloadIFrame();\", 3000);");
		ans.add("}");
		ans.add("function reloadIFrame() {");
		ans.add("document.getElementById('iframe_id').src += '';");
		ans.add("}");
		ans.add("function getBrowser()"); 
		ans.add("{");
		ans.add("}");
		ans.add("</script></head>	");
		ans.add("<body onload=\"refreshIFrame();getBrowser()\">");
		ans.add("<h3>Replace1 private database for "+cfgMenuWebUser+"</h3>");
		ans.add("<FORM METHOD=POST name=\"Form\" ACTION=\"replacePrivateDb.jsp\">");
		ans.add("<table bgColor=\"#cccccc\" border=\"2\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.add("<tr><td></td><td>Source</td><td>Target</td></tr>");
		ans.add("<tr><td >APP server name</td><td bgColor=\"#66ff66\">"+sourceAPP+"</td><td bgColor=\"#00ffff\">"+targetAPP+"</td></tr>");
		ans.add("<tr><td >SQL server name</td><td bgColor=\"#66ff66\">"+sourceSQL+"</td><td bgColor=\"#00ffff\">"+targetSQL+"</td></tr>");
		ans.add("<tr><td >Batch name</td><td bgColor=\"#66ff66\">"+sourceBN+"</td><td bgColor=\"#00ffff\">"+privateBN+"</td></tr>");
		ans.add("<tr><td >Database name</td><td bgColor=\"#66ff66\">"+sourceDB+"</td><td bgColor=\"#00ffff\">"+privateDb+"</td></tr>");
		ans.add("<tr><td >Database Dat size1</td><td bgColor=\"#66ff66\">"+sourceDatSize+" mb</td><td bgColor=\"#00ffff\">"+targetDatSize+" mb</td></tr>");
		ans.add("<tr><td >Database Log size</td><td bgColor=\"#66ff66\">"+sourceLogSize+" mb</td><td bgColor=\"#00ffff\">"+targetLogSize+" mb</td></tr>");
		ans.add("<tr><td >log4j level</td><td bgColor=\"#66ff66\">"+log4jCombo+"</td><td bgColor=\"#00ffff\">.</td></tr>");
		ans.add("<tr><td >Customer name</td><td bgColor=\"#66ff66\">"+customerLong+"</td><td bgColor=\"#00ffff\">"+"."+"</td></tr>");
		ans.add("<tr><td >Owner</td><td bgColor=\"#66ff66\">"+ownerSource+"</td><td bgColor=\"#00ffff\">"+ownerTarget+"</td></tr>");
		ans.add("<tr><td >Sql version</td><td bgColor=\"#66ff66\">"+sqlVersionSource+"</td><td bgColor=\"#00ffff\">"+sqlVersionTarget+"</td></tr>");
		
		ans.add("</table>");
		
		ans.add("<input type=\"hidden\" name=\"user\" id=\"user\" value = \""+cfgMenuWebUser+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromDb\" id=\"fromDb\" value = \""+sourceDB+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromBn\" id=\"fromBn\" value = \""+sourceBN+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromSql\" id=\"fromSql\" value = \""+sourceSQL+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromApp\" id=\"fromApp\" value = \""+sourceAPP+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"fromCust\" id=\"fromCust\" value = \""+customerShort+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"targetAPP\" id=\"targetAPP\" value = \""+targetAPP+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"targetSQL\" id=\"targetSQL\" value = \""+targetSQL+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"myBrowser\" id=\"myBrowser\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"toBN\" id=\"toBN\" value = \""+privateBN+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"toDb\" id=\"toDb\" value = \""+privateDb+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"sourceDatSize\" id=\"sourceDatSize\" value = \""+sourceDatSize+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"sqlVersionSource\" id=\"sqlVersionSource\" value = \""+sqlVersionSource+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"sqlVersionTarget\" id=\"sqlVersionTarget\" value = \""+sqlVersionTarget+"\" maxlength=\"140\" size=\"140\">");

		ans.add("<br><INPUT TYPE=SUBMIT value=\"Replace my Private environment\" onclick=\"document.getElementById('loader1').style.display = ''; this.style.display = 'none'\" ></FORM>");
		
		
		ans.add("<img id=\"loader\" src=\"Please_wait.gif\" style=\"display: none;\"/>");
		String iFrameTarget1="src=\"file://///ri-archive/Saptech-Archive/RI/debug/private_envs_new/iframes/"+targetAPP+"_"+privateBN+".html\"";
		ans.add("<table bgColor=\"#00ff00\"><tr><td bgColor=\"#00ffff\"><textarea readonly style=\"color: red; background-color: lightyellow;display: none; \" cols=\"100\" rows=\"5\" id=\"loader1\">Please wait&#13;&#10;wait time depends on the DB and APP elements size&#13;&#10;usually takes a few minutes&#13;&#10;Take a coffee and relax&#13;&#10;HAKUNA MATATA</textarea></td></tr></table>");
		ans.add("<br><iframe  id=\"iframe_id\" name=\"iframe_id\"" +iFrameTarget1+" height=\"850\" width=\"1500\"></iframe>");		
		return ans.convertToString(" ");
	
	}
	public String  	getreplacePrivateDb_loadDDC(String toDb,String toSQL,String debug2,HagRc hagRc){
	String ddcfolder = debug2 + "\\DDC\\";
	
	HagSQL hagSql = new HagSQL();
	hagSql.runSqlGroupDDC(hagRc,ddcfolder, toSQL,"cfg","cfg09c",toDb);
	if(hagRc.rc!=0) {
		String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to load DDC to "+toDb+"<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
		return ansTemp2;
	}
		

	
	String ansTemp2 = "<td bgColor=\"#00FF00\">bcp files loaded</td>";
	return ansTemp2;
}
	public String 	replacePrivateDb_replaceWar(String toBatchName,String toApp,HagRc hagRcReplaceWar,String toPack){
	

	String to ="\\\\"+toApp+"\\RI_SHARE\\"+toBatchName+"\\TO_APPEND";
	String warSrc =to+"\\ri-web.war";
	
//	File dir = new File(to);
//	if(!dir.exists() || !dir.isDirectory())
//		return "<td bgColor=\"#FFFF00\">Skipped - "+to +" folder not found.</td>";

	File war1 = new File(warSrc);
	if(!war1.exists() || !war1.isFile()) {
		warSrc =toPack+"\\ri-components\\RiInstaller\\ri-web.war";
		File war2 = new File(warSrc);
		if(!war2.exists() || !war2.isFile()) 
		 return "<td bgColor=\"#FFFF00\">Skipped - ri-web.war file not found (in "+to+" and in "+toPack+"\\ri-components\\RiInstaller).</td>";
	
	}
	String target = "d:\\RI_JAVA\\RIjava_"+toBatchName+"\\tomcat\\";
	String warLoc = target + "\\webapps\\";
	String warTgt = target + "\\webapps\\ri-web.war";
	String warChk = target + "\\webapps\\ri-web\\index.jsp";
	String warTgt1 = "\\\\" + toApp + "\\" + warTgt.substring(3, warTgt.length());
	String warChk1 = "\\\\" + toApp + "\\" + warChk.substring(3, warChk.length());
	String riwebLoc1 = "\\\\" + toApp + "\\RI_JAVA\\RIjava_"+toBatchName+"\\tomcat\\webapps\\ri-web";
	HagClient hagClient= new HagClient();;
	// deleted1
	String command1 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\runcmd.bat del /Q " + warLoc	+ "ri-web.war";
	HagStringList tmp1 = hagClient.runNotSudo(toApp, command1);
	HagUtil.appendArrayList2ArrayList(hagRcReplaceWar.log,tmp1);
	String tmp1s = tmp1.convertToString("<br>");
	if (tmp1s.startsWith("1~")) {
		//ans[1]  = tmp1s; 
		//ans[0]	="STOP";
		//return ans;
		return "<td bgColor=\"FF0000\">failed to delete "+warLoc+"\\ri-web.war.</td>";
	}else{
		hagRcReplaceWar.log.add(warLoc+"\\ri-web.war deleted."); 
	}			
	
	//delete ri-web dir
	String command2 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\runcmd.bat rmdir " + warLoc+ "ri-web\\ /s /q";
	HagStringList tmp2 = hagClient.runNotSudo(toApp, command2);
	HagUtil.appendArrayList2ArrayList(hagRcReplaceWar.log,tmp2);
//	String tmp2s = tmp2.convertToString("<br>");
	if (HagUtil.isFileExist(riwebLoc1)) {
		//ans[1]  = "Failed to delete+"+riwebLoc1; 
		//ans[0]	="STOP";
		//return ans;
		return "<td bgColor=\"FF0000\">failed to delete "+warLoc+"\\ri-web folder.</td>";
	}else{
		hagRcReplaceWar.log.add(warLoc+"\\ri-web folder deleted."); 
		//ans1.append(tmp2s).append("<br>"); 
	}

	// copy war file
	String ans9="aaaaaaaaaa";
	HagStringList ans6 =null;
	String command3 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\runcmd.bat copy " + warSrc + " "+ warTgt;
	HagStringList tmp3 = hagClient.runNotSudo(toApp, command3);
	HagUtil.appendArrayList2ArrayList(hagRcReplaceWar.log,tmp3);
	//String tmp3s = tmp3.convertToString("<br>");
//	ans1.append(tmp3s).append("<br>"); 
	//start tomcat
	ans6 =hagClient.run(toApp, "startTomcat " + toBatchName + "\n");
	HagUtil.appendArrayList2ArrayList(hagRcReplaceWar.log,ans6);
//	ans1.append(ans6).append("<br>"); 
	HagUtil.sleep(5000);
	if (!HagUtil.isFileExist(warChk1)) {
//		ans[1]  = "cfgMenuWeb-Failed to copy war";
//		ans[0]	="STOP";
//		return ans;
		HagUtil.sleep(15000);
		if (!HagUtil.isFileExist(warChk1)) 
			return "<td bgColor=\"FF0000\">failed to copy "+warSrc+" to "+warTgt+"</td>";
	}
		hagRcReplaceWar.log.add(warSrc+" copied to "+warTgt); 
	//	ans1.append("War replaced.<br>"); 
		//String command4 = "\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\bat\\runcmd.bat del /Q " + warLoc	+ "ri-web.war";
		//HagStringList tmp4 = hagClient.runNotSudo(server1, command4);
		//String tmp4s = tmp4.convertToString("<br>");
		//if (tmp4s.startsWith("1~")) {
		//		ans1.append("Failed to delete war file in " + server1 + " " + batchName1+ " environment (after)").append("<br>");
		//	}else{
		//		ans1.append(warLoc).append("ri-web.war deleted.<br>"); 
		//	}
	//	ans[1]  = ans1.toString();
	//	ans[0]	="GOOD";
		return "<td bgColor=\"00FF00\">War replaced.</td>";
	
	
}
	public String 	replacePrivateDb_runMigMng(String toBatchName,String toApp,HagRc hagRcRunMigMng,String ver5,String debugBase,String debugHtmlFile,String toPack){
	String to ="\\\\"+toApp+"\\RI_SHARE\\"+toBatchName+"\\TO_APPEND";
	String migMngSrc =to+"\\mig-mng.jar";
	String RIAdapterSrc =to+"\\RIAdapter.dll";
	String folder2 = "d:\\cfg\\TO_APPEND_DEV2PRIVATE\\"+toApp+"_"+toBatchName;
	//check
	File dir = new File(to);
	if(!dir.exists() || !dir.isDirectory())
		return "<td bgColor=\"#FFFF00\">Skipped - "+to +" folder not found.</td>";
	
	
		
	File migMng1 = new File(migMngSrc);
	if(!migMng1.exists() || !migMng1.isFile()) {
		migMngSrc =toPack+"\\ri-components\\RiInstaller\\mig-mng.jar";
		File migMng2 = new File(migMngSrc);
		if(!migMng2.exists() || !migMng2.isFile()) 
		 return "<td bgColor=\"#FFFF00\">Skipped - mig-mng.jar file not found (in "+to+" and in "+toPack+"\\ri-components\\RiInstaller).</td>";
	}
	
	File adapter1 = new File(RIAdapterSrc);
	if(!adapter1.exists() || !adapter1.isFile()) {
		RIAdapterSrc =toPack+"\\ri-components\\RiInstaller\\RIAdapter.dll";
		File adapter2 = new File(RIAdapterSrc);
		if(!adapter2.exists() || !adapter2.isFile()) 
		 return "<td bgColor=\"#FFFF00\">Skipped - RIAdapter.dll file not found (in "+to+" and in "+toPack+"\\ri-components\\RiInstaller).</td>";
	}
	//recreat folder2
	HagRc hagRcRunMigMng1 = new HagRc();
	HagUtil.reCreateFolder(hagRcRunMigMng1, folder2);
	if(hagRcRunMigMng1.rc!=0)
		return "<td bgColor=\"#FF0000\">Failed to reCreate "+folder2+" folder - call hagay 2527.</td>";

	//copy migMngSrc
	String copyRc1=HagUtil.copyFileViaDos(migMngSrc, folder2+"\\mig-mng.jar");
	if(!copyRc1.startsWith("0~")){
		String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to copy mig-mng.jar file - call hagay 2527</td>";
		return ansTemp2;
	}
	
	//copy adapter
	String copyRc=HagUtil.copyFileViaDos(RIAdapterSrc, folder2+"\\RIAdapter.dll");
	if(!copyRc.startsWith("0~")){
		String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to copy RIAdapter.dll file - call hagay 2527</td>";
		return ansTemp2;
	}
	//check webapps\\ri-web\\WEB-INF\\lib exist
	String javaFolder = "\\\\"+toApp+"\\RI_JAVA\\RIjava_"+toBatchName;
	String libStr = javaFolder + "\\tomcat\\webapps\\ri-web\\WEB-INF\\lib";
	File check1 = new File(libStr);
	int num = 0;
	while(!check1.exists() && num < 6) {
		HagUtil.replaceStringInFile(debugHtmlFile, "-}", "--}");
		HagUtil.sleep(5000);
		num++;
	}
	if(num==6) {
		return "<td bgColor=\"#FF0000\">Failed to create mig-mng command because "+libStr+" not found - call hagay 2527.</td>";	
	}
	HagUtil.sleep(5000);
	//build command
	buildRiMigCmd(hagRcRunMigMng,toBatchName, toApp,folder2,null,null,null);
	if(hagRcRunMigMng.rc!=0){
		String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create mig-mng command - call hagya 2527</td>";
		return ansTemp2;
	}

	String writeRc=hagRcRunMigMng.log.writeToFile(folder2+"\\run.bat", false);
	if(writeRc.startsWith("1~"))
		return "<td bgColor=\"#FF0000\">Failed to write java migmng command into batch file - call hagay 2527.</td>";
	//String rcc = HagUtil.runBatch(folder2 + "\\run.bat > "+folder2+"\\log.txt",true);
	String rcc = HagUtil.runBatch(folder2 + "\\run.bat",true);
	
	File check = new File(folder2+"\\done.txt");
	HagUtil.replaceStringInFile(debugHtmlFile, "Wait.", "<font color=red>Waiting for mig-mng to finish<br>-}</font>");

	while(!check.exists()) {
		HagUtil.replaceStringInFile(debugHtmlFile, "-}", "--}");
		HagUtil.sleep(5000);
	}
	//runmigMng(hagRcRunMigMng,toBatchName, riMigCmd,ver5) ;
	String tgtLog = debugBase+"\\logs\\"+toApp+"_"+toBatchName+"_mig-mng.log";
	String link = "<br>link to log will not work , failed to delete old log file.";
	
	String ans2 = HagUtil.deleteFile(tgtLog, false);
	if(!ans2.startsWith("0~")) {
		String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to to delete mig-mng old log file</td>";
		return ansTemp2;
	}
	String ans3 = HagUtil.copyFileViaDos( "d:\\cfg\\TO_APPEND_DEV2PRIVATE\\"+toApp+"_"+toBatchName+"\\mig-mng.log",tgtLog);
	if(ans3.startsWith("0~")) {
		link = "<br><a href=\""+tgtLog+"\">mig-mng log</a>";
	}else {
		link = "<br>link to log will not work , failed to copy "+"d:\\cfg\\TO_APPEND_DEV2PRIVATE\\"+toApp+"_"+toBatchName+"\\mig-mng.log";
	}
	
	return "<td bgColor=\"#00FF00\">"+rcc+link+"</td>";
}
	private void  	buildRiMigCmd(HagRc hagRc,String batchName,String serverAPP,String migMngLoc, String migName,String bulkUser,String bulkPass) {
	//String riInst = javaFolder + "\\properties\\ri.install";
	String javaFolder = "\\\\"+serverAPP+"\\RI_JAVA\\RIjava_"+batchName;
	String dbidProperties = javaFolder + "\\properties\\" + batchName + ".properties";
	String libStr = javaFolder + "\\tomcat\\webapps\\ri-web\\WEB-INF\\lib";
	File lib = new File(libStr);
	if (!lib.exists()) {
		hagRc.log.add(libStr + " folder not found.");
		hagRc.rc=1;	
		return;
	}if (!lib.isDirectory()) {
		hagRc.log.add(libStr + " not a directory.");
		hagRc.rc=1;	
		return;

	}
	String[] children = lib.list();
	String classPath = migMngLoc+"\\mig-mng.jar;";
//	classPath = classPath+"packages\\"+riPackage+"\\fromTopack;";
	for (int i = 0; i < children.length; i++)
		classPath = classPath + libStr + "\\" + children[i] + ";";
//	hagRc.log.add("@echo off");
	hagRc.log.add("d:");
	hagRc.log.add("cd \\");
	hagRc.log.add("cd cfg");
	hagRc.log.add("cd TO_APPEND_DEV2PRIVATE");
	hagRc.log.add("cd "+serverAPP+"_"+batchName);
	if(migName==null) {
		hagRc.log.add("d:\\cfg\\TO_APPEND_DEV2PRIVATE\\jre\\bin\\java -cp \"" + classPath + "\" com.sapiens.mig.core.MigMngLauncher \"" + dbidProperties + "\" ");

	}else{
		hagRc.log.add("d:\\cfg\\TO_APPEND_DEV2PRIVATE\\jre\\bin\\java -cp \"" + classPath + "\" com.sapiens.mig.core.MigMngLauncher \"" + dbidProperties + "\" "+migName+" "+bulkUser+" "+bulkPass);
	}
	hagRc.log.add("echo done >done.txt");
	hagRc.log.add("exit");
}
	public String 	getDevDB(String w1){
		
		String ver = w1.substring(0, 5);
		HagStringList list = new HagStringList(HagUtil.cfgMenuWebLoc+"\\lists\\devIntPerVersions.list",true,"*",false);
		for(int i = 0;i<list.size();i++) {
			String temp=list.get(i);
			String dev1 = HagUtil.getWord0(temp,"|",0,true);
			String int1 = HagUtil.getWord0(temp,"|",1,true);
			String ver1 = HagUtil.getWord0(temp,"|",2,true);
			if(ver1.equals(ver)) {
				return dev1;
			}
			
		}
		return null;
		
	}
	public String  	getreplacePrivateDb_getDDC(String fromDb,String fromSqlServer,String logsFolder,HagRc hagRc){
		
		
		// replace ddc + pure
		
		String ddcfolder = logsFolder + "\\DDC\\";
		HagUtil.reCreateFolder(hagRc,ddcfolder);
		if(hagRc.rc!=0){
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to recreate DDC folder "+ddcfolder+"<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}
		
		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CO0", fromSqlServer, hagRc);
		
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CO0 bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CO1", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CO1 bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CAT", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CAT bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}
	/*
		HagUtil.crtBcps( ddcfolder, fromDb, "SAPIENS", "PR4530EN", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create PR4530EN bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "SAPIENS", "PR4530ENL", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create PR4530ENL bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "SAPIENS", "PR4530ENJ", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create PR4530ENJ bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CT", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CT bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}
		
		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTCAT", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTCAT bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTCM1", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTCM1 bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTCMN", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTCMN bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTCTU", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTCTU bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTCU0", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTCU0 bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTCU1", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTCU1 bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTDAT", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTDAT bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTDLK", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTDLK bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTENU", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTENU bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTJSQ", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTJSQ bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTLCL", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTLCL bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTLEX", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTLEX bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTSWS", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTSWS bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}

		HagUtil.crtBcps( ddcfolder, fromDb, "RI", "CTSEC", fromSqlServer, hagRc);
		if(hagRc.rc!=0) {
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to create CTSEC bcp file<br>"+hagRc.log.get(hagRc.log.size()-1) +"</td>";
			return ansTemp2;
		}
	*/

		String ansTemp2 = "<td bgColor=\"#00FF00\">bcp files created</td>";
		return ansTemp2;
	}
	public String 	appendDevElementsToMyPrivateEnvNew(String cfgMenuWebUser, HttpServletResponse response,String privateBN,String privateDb,String privateAppServer,String privateSqlServer){
		

		//if(!isMyPrivateEnv(cfgMenuWebUser,privateDb,privateAppServer))
		//	return HagUtil.shortHtml(privateAppServer+"/"+privateDb+" is not your private environment", "red","bg");

		int[] inds = {7,8};
		String[] vals = getEnvVals(privateAppServer,privateBN,inds);
		String verLine= vals[0];
		String ver = HagUtil.getWord0(verLine,"-",0, true);
		String verRC= vals[1];
		if(!verRC.equals("OK"))
			return HagUtil.shortHtml(privateAppServer+"/"+privateBN+" last installation = failed", "red","bg");
		
		String[] toPackArr = getTopackFromVer(ver);
		String toPack0 = toPackArr[0];
		String toPack1 = toPackArr[1];
		String dev = getDevDB(ver);
		if(dev==null)
			return HagUtil.shortHtml("DEV DB not found for "+ver.substring(0, 5)+" (devIntPerVersions.list)", "red","bg");
		
		
		//String iFrameFolder="\\\\ri-archive\\Saptech-Archive\\RI\\debug\\dev2private\\iframe";
		//String iframeInit = HagUtil.copyFileViaDos(iFrameFolder+"\\iframeSkel.html", iFrameFolder+"\\"+privateDb+".html");
		
		String iFrameTarget="\\\\ri-archive\\Saptech-Archive\\RI\\debug\\dev2privateNew\\iframes\\"+privateAppServer+"_"+privateBN+".html";
		String iFrameSkel="\\\\ri-archive\\Saptech-Archive\\RI\\debug\\dev2privateNew\\iframeSkel.html";
		String iframeInit = HagUtil.copyFileViaDos(iFrameSkel, iFrameTarget);
		
		
		
		if(!iframeInit.startsWith("0~"))
			return HagUtil.shortHtml("Failed to init iFrameFile2. callhagay - 2527<br>"+iframeInit, "red","bg");
		String toAppendFolder ="\\\\"+privateAppServer+"\\RI_SHARE\\"+privateBN+"\\TO_APPEND";
		HagStringList toAppendFiles = HagUtil.getFilesInDir(toAppendFolder);
		String toAppend=toAppendFiles.convertToString(" "); 
		toAppend = HagUtil.replaceStr(toAppend, "\\", " , ");
		toAppend= toAppend.trim();
		if(toAppend.startsWith(","))
			toAppend= toAppend.substring(1, toAppend.length());
		HagStringList ans = new HagStringList();
		ans.add("<head><script type=\"text/javascript\">");
		ans.add("function refreshIFrame() {");
		ans.add("var interval =setInterval(\"reloadIFrame();\", 3000);");
		ans.add("}");
		ans.add("function reloadIFrame() {");
		ans.add("document.getElementById('iframe_id').src += '';");
		ans.add("}");
		ans.add("function getBrowser()"); 
		ans.add("{");
		//ans.add("var ua = window.navigator.userAgent;");
		//ans.add("document.getElementById(\"myBrowser\").value = ua;");
		ans.add("}");
		ans.add("</script></head>	");
		ans.add("<body onload=\"refreshIFrame();getBrowser()\">");

		ans.add("<h3>Append Dev elements to my private environmnet</h3>");
		ans.add("<FORM METHOD=POST name=\".\" ACTION=\"appendDevToPrivateEnv.jsp\">");
		ans.add("<table bgColor=\"#666666\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.add("<tr><td bgColor=\"#bbbbbb\">APP server						</td><td bgColor=\"#dddddd\">"+privateAppServer+"         						</td></tr>");
		ans.add("<tr><td bgColor=\"#bbbbbb\">SQL server						</td><td bgColor=\"#dddddd\">"+privateSqlServer+"         						</td></tr>");
		ans.add("<tr><td bgColor=\"#bbbbbb\">batchName						</td><td bgColor=\"#dddddd\">"+privateBN+"										</td></tr>");
		ans.add("<tr><td bgColor=\"#bbbbbb\">database name						</td><td bgColor=\"#dddddd\">"+privateDb+"										</td></tr>");
		ans.add("<tr><td bgColor=\"#bbbbbb\">ver							</td><td bgColor=\"#dddddd\">"+verLine+"				     					</td></tr>");
		ans.add("<tr><td bgColor=\"#bbbbbb\">TOPACK folder              	</td><td bgColor=\"#ccff99\">"+toPack1+"					     					</td></tr>");
		ans.add("<tr><td bgColor=\"#bbbbbb\">TO_APPEND folder				</td><td bgColor=\"#ccff99\">"+toAppendFolder+"				     				</td></tr>");

		ans.add("<tr><td bgColor=\"#bbbbbb\">dev DB (source)				</td><td bgColor=\"#dddddd\">"+dev+"	         								</td></tr>");
		if(toAppend.length()==0)
			ans.add("<tr><td bgColor=\"#bbbbbb\">elements found in your TO_APPEND folder</td><td bgColor=\"#dddddd\">"+toAppend+"	         								</td></tr>");
		else
			ans.add("<tr><td bgColor=\"#bbbbbb\">elements found in your TO_APPEND folder</td><td bgColor=\"#ccff99\">"+toAppend+"	         								</td></tr>");
				
		//ans.add("<tr bgColor=\"#dddddd\"><td>replace DDC splits	</td><td>from DEV database									</td><td>.				</td></tr>");
		//ans.add("<tr bgColor=\"#dddddd\"><td>replace IOM folder	</td><td>from DEV environment								</td><td>.				</td></tr>");
		//ans.add("<tr bgColor=\"#dddddd\"><td>replace war		</td><td>from \\\\"+privateAppServer+"\\RI_SHARE\\TO_APPEND folder	</td><td>if exist 		</td></tr>");
		//ans.add("<tr bgColor=\"#dddddd\"><td>run mig-mng		</td><td>from \\\\"+privateAppServer+"\\RI_SHARE\\TO_APPEND folder	</td><td>if exist		</td></tr>");

		ans.add("</table>");
		ans.add("<br>");
		ans.add("<table bgColor=\"#999999\" cellpadding=\"3\" cellspacing=\"3\">");
		ans.add("<tr bgColor=\"#aaaaaa\"><td>process			</td><td>from 												</td><td>note			</td></tr>");
		ans.add("<tr bgColor=\"#dddddd\"><td><input type=\"checkbox\" name=\"prc8\" id=\"prc8\" value=\"Replace IOM folder\" 	checked>Replace IOM folder	</td><td>from DEV environmnet										</td><td>.			</td></tr>");
		ans.add("<tr bgColor=\"#dddddd\"><td><input type=\"checkbox\" name=\"prc8\" id=\"prc8\" value=\"Replace DDC splits\" 	checked>Replace DDC splits	</td><td>from DEV database											</td><td bgColor=\"#ff8866\">Only CO0,CO1 and CAT splits, without running dblex	</td></tr>");
		ans.add("<tr bgColor=\"#dddddd\"><td><input type=\"checkbox\" name=\"prc8\" id=\"prc8\" value=\"Replace war\" 			checked>Replace war			</td><td>from TO_APPEND or in TOPACK folders	</td><td bgColor=\"#ff8866\">ri-web.war must exist	in TO_APPEND or in TOPACK </td></tr>");
		ans.add("<tr bgColor=\"#dddddd\"><td><input type=\"checkbox\" name=\"prc8\" id=\"prc8\" value=\"Run mig-mng\" 			checked>Run mig-mng			</td><td>from TO_APPEND or in TOPACK folders	</td><td bgColor=\"#ff8866\">RIAdapter.dll and mig-mng.jar must exist in TO_APPEND or in TOPACK<br>a cmd windows will be prompted - do not close it</td></tr>");
		ans.add("</table>");

		
		ans.add("<input type=\"hidden\" name=\"user\" 				id=\"user\" 				value = \""+cfgMenuWebUser+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"devDb\" 				id=\"devDb\" 				value = \""+dev+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"devAppServer\" 		id=\"devAppServer\" 		value = \"RIDEVBLP06\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"devSqlServer\" 		id=\"devSqlServer\" 		value = \"RI7QA\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"privateBN\" 			id=\"privateBN\" 			value = \""+privateBN+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"privateDb\" 			id=\"privateDb\" 			value = \""+privateDb+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"privateAppServer\" 	id=\"privateAppServer\" 	value = \""+privateAppServer+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"privateSqlServer\" 	id=\"privateSqlServer\" 	value = \""+privateSqlServer+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"ver\" 				id=\"ver\" 					value = \""+ver+"\" maxlength=\"140\" size=\"140\">");
		ans.add("<input type=\"hidden\" name=\"toPack\" 			id=\"toPack\" 				value = \""+toPack0+"\" maxlength=\"140\" size=\"140\">");
		//ans.add("<br><INPUT TYPE=SUBMIT value=\"Run\" onclick=\"this.style.display = 'none'\" ></FORM>");
		//	ans.add("<br><br><INPUT TYPE=SUBMIT value=\"Run\"   onclick=\"this.disabled=true; this.value='Please Wait...';\"></FORM>");
		//ans.add("<br><INPUT TYPE=SUBMIT value=\"Replace my private environment\" onclick=\"document.getElementById('loader1').style.display = ''; this.style.display = 'none'\" ></FORM>");
		ans.add("<br><INPUT TYPE=SUBMIT value=\"Run\" onclick=\"document.getElementById('loader1').style.display = ''; this.style.display = 'none'\" ></FORM>");
		
		ans.add("<table bgColor=\"#00ff00\"><tr><td bgColor=\"#00ffff\"><textarea readonly style=\"color: red; background-color: lightyellow;display: none; \" cols=\"100\" rows=\"5\" id=\"loader1\">Please wait&#13;&#10;wait time depends on the DB and APP elements size&#13;&#10;usually takes a few minutes&#13;&#10;Take a coffee and relax&#13;&#10;HAKUNA MATATA</textarea></td></tr></table>");
		ans.add("<br><iframe  id=\"iframe_id\" name=\"iframe_id\" src=\"file://///ri-archive/Saptech-Archive/RI/debug/dev2privateNew/iframes/"+privateAppServer+"_"+privateBN+".html\" height=\"850\" width=\"1500\"></iframe>");		
		ans.add("</BODY>");
		return ans.convertToString(" ");

	}
	public String[] getEnvVals(String appServer ,String batchName, int[] inds){
		String[] ans = new String[inds.length];
		HagSQL hagSQL = new HagSQL();
		String stm = "select * from dbo.ri_environments_new where status='A' and bis_server='"+appServer+"' and batchName='"+batchName+"'";
		HagStringList list = hagSQL.select("SQL","confg1","cfg","cfg09c",HagParam.getConfg1DB(),stm,37,"~",null,null);
		for(int i = 0 ; i < inds.length;i++) {
			ans[i]=HagUtil.getWord0(list.get(0),"~",inds[i],true);
		}
		return ans;
	}
	public String[] getTopackFromVer(String ver){
		String[] ans = {".","."};
		StringBuilder ans0 = new StringBuilder("\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\V");
		ans0.append(ver.charAt(0)).append(ver.charAt(1)).append(ver.charAt(3)).append(ver.charAt(4));

		StringBuilder ans1 = new StringBuilder("\\\\HYUNDAI.SAPIENS.COM\\HYUN05ri\\DEV\\<font color='red'>V");
		ans1.append(ver.charAt(0)).append(ver.charAt(1)).append(ver.charAt(3)).append(ver.charAt(4));

		if(ver.endsWith("00.00")) {
			ans0.append("m00\\TOPACK");
			ans1.append("m00</font>\\TOPACK");
			ans[0]=ans0.toString();
			ans[1]=ans1.toString();
			return ans;
		}
		
		ans0.append("m").append(ver.charAt(6)).append(ver.charAt(7)).append("U00\\TOPACK");
		ans1.append("m").append(ver.charAt(6)).append(ver.charAt(7)).append("U00</font>\\TOPACK");
		ans[0]=ans0.toString();
		ans[1]=ans1.toString();
		return ans;

	}
			
	
	///////////////////////
	
	public String 	replacePrivateDb_replaceEobjects(String fromApp,String fromBN,String toApp,String toBatchName,String user,String clientLocation4530){

		HagStringList ans = new HagStringList();
		String fromFolder = "\\\\"+fromApp+"\\clientDB\\DB\\"+fromBN; 
	//	String fromFolderRi = "\\\\"+fromApp+"\\clientDB\\DB\\"+fromBN+"RI"; 
	
		
		String toFolder = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName; 
		String toToolbarDbMs = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"\\EOBJECTS\\MS\\toolbar.htm"; 
		String toToolbarDbSf = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"\\EOBJECTS\\SF\\toolbar.htm"; 
		String toDes = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"\\sapdb"+toBatchName+".des";
		String toDesOld = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"\\sapdb"+fromBN+".des";
		
	//	String toFolderRi = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"RI"; 
		String toToolbarDbRIMs = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"RI\\EOBJECTS\\MS\\toolbar.htm"; 
		String toToolbarDbRISf = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"RI\\EOBJECTS\\SF\\toolbar.htm"; 
	//	String toDesRi = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"RI\\sapdb"+toBatchName+"RI.des"; 
	//	String toDesRiOld = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"RI\\sapdb"+fromBN+"RI.des"; 
		String toFolder_RI = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"RI"; 
		String toFolder_CT = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"CT"; 
		
		
		HagStringList toDesList = new HagStringList();
		toDesList.add("No.      Dataset           OpenMode");
		toDesList.add("1   kb4530EN");
		toDesList.add("2   err4530EN");
		toDesList.add("3   ub"+toBatchName);
		toDesList.add("");
		if(clientLocation4530!=null && clientLocation4530.length()>0)
			toDesList.add(clientLocation4530+"4530.map");

			//			toDesList.add("C:\\ProgramData\\Sapiens\\eMerge Client Tools\\DB\\4530.map");
		//toDesList.add(toClient);
		
		
		//delete
		String ansTemp = HagUtil.deleteFolder(toFolder, null, false, false);
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to delete "+toFolder+"<br>"+ansTemp+"</td>";
		else
			ans.add(toFolder + " deleted");
		
		//dbidRI + dbidCT
		ansTemp = HagUtil.deleteFolder(toFolder_RI, null, false, false);
		ans.add(ansTemp);
		ansTemp = HagUtil.deleteFolder(toFolder_CT, null, false, false);
		ans.add(ansTemp);
		HagRc hagRc= new HagRc();
		HagUtil.reCreateFolder(hagRc, toFolder_RI);
		ans.add(hagRc.log.get(0));
		if(hagRc.rc!=0)
			return "<td bgColor=\"#FF0000\">Failed to create "+toFolder_RI+"<br>"+hagRc.log.convertToString("<BR>")+"</td>";
		HagUtil.reCreateFolder(hagRc, toFolder_RI+"\\EOBJECTS");
		ans.add(hagRc.log.get(0));
		if(hagRc.rc!=0)
			return "<td bgColor=\"#FF0000\">Failed to create "+toFolder_RI+"\\EOBJECTS<br>"+hagRc.log.convertToString("<BR>")+"</td>";
		HagUtil.reCreateFolder(hagRc, toFolder_RI+"\\HTML");
		ans.add(hagRc.log.get(0));
		if(hagRc.rc!=0)
			return "<td bgColor=\"#FF0000\">Failed to create "+toFolder_RI+"\\HTML<br>"+hagRc.log.convertToString("<BR>")+"</td>";
		HagUtil.reCreateFolder(hagRc, toFolder_CT);
		ans.add(hagRc.log.get(0));
		if(hagRc.rc!=0)
			return "<td bgColor=\"#FF0000\">Failed to create "+toFolder_CT+"<br>"+hagRc.log.convertToString("<BR>")+"</td>";
		HagUtil.reCreateFolder(hagRc, toFolder_CT+"\\EOBJECTS");
		ans.add(hagRc.log.get(0));
		if(hagRc.rc!=0)
			return "<td bgColor=\"#FF0000\">Failed to create "+toFolder_CT+"\\EOBJECTS<br>"+hagRc.log.convertToString("<BR>")+"</td>";
		HagUtil.reCreateFolder(hagRc, toFolder_CT+"\\HTML");
		ans.add(hagRc.log.get(0));
		if(hagRc.rc!=0)
			return "<td bgColor=\"#FF0000\">Failed to create "+toFolder_CT+"\\HTML<br>"+hagRc.log.convertToString("<BR>")+"</td>";
	

		//create ri des files
		String toDesRi = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"RI\\sapdb"+toBatchName+"RI.des";
		HagStringList toDesRiList = new HagStringList();
		toDesRiList.add("No.      Dataset           OpenMode");
		toDesRiList.add("1   kb4530EN");
		toDesRiList.add("2   err4530EN");
		toDesRiList.add("3   ub"+toBatchName+"RI");
		toDesRiList.add("");
		if(clientLocation4530!=null && clientLocation4530.length()>0)
			toDesRiList.add(clientLocation4530+"4530.map");

		//toDesRiList.add(toClient);
		String ansTempRi = toDesRiList.writeToFile(toDesRi, false);
		if(!ansTempRi.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to create "+toDesRi+"<br>"+ansTempRi+"</td>";
		else
			ans.add(toDesRi + " created");

		//create ct des files
		String toDesCt = "\\\\"+toApp+"\\clientDB\\DB\\"+toBatchName+"CT\\sapdb"+toBatchName+"CT.des";
		HagStringList toDesCtList = new HagStringList();
		toDesCtList.add("No.      Dataset           OpenMode");
		toDesCtList.add("1   kb4530EN");
		toDesCtList.add("2   err4530EN");
		toDesCtList.add("3   ub"+toBatchName+"CT");
		toDesCtList.add("");
		if(clientLocation4530!=null && clientLocation4530.length()>0)
			toDesCtList.add(clientLocation4530+"4530.map");

		//toDesCtList.add(toClient);
		String ansTempCt = toDesCtList.writeToFile(toDesCt, false);
		if(!ansTempCt.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to create "+toDesCt+"<br>"+ansTempCt+"</td>";
		else
			ans.add(toDesCt + " created");
		

		
	
		//copy
		ansTemp = HagUtil.copyFolder(fromFolder, toFolder, false, false,"1","");
		// ansTemp =  HagUtil.copyFolderGonen(fromFolder, toFolder, toApp, toBatchName);
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to copy "+fromFolder+" to "+toFolder+"<br>"+ansTemp+"</td>";
		else
			ans.add(fromFolder + " copied to "+toFolder);
		/*
		ansTemp = HagUtil.copyFolder(fromFolderRi, toFolderRi, false, false,"1","");
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to copy "+fromFolderRi+" to "+toFolderRi+"<br>"+ansTemp+"</td>";
		else
			ans.add(fromFolderRi + " copied to "+toFolderRi);
		 */
		
		//toolbar
		ansTemp = HagUtil.replaceStringInFile(toToolbarDbMs, "/"+fromBN+"/", "/"+toBatchName+"/");
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to configure toolbar.htm file in "+toToolbarDbMs+"<br>"+ansTemp+"</td>";
		else
			ans.add(toToolbarDbMs + " toolbar.htm file configured");	
		ansTemp = HagUtil.replaceStringInFile(toToolbarDbSf, "/"+fromBN+"/", "/"+toBatchName+"/");
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to configure toolbar.htm file in "+toToolbarDbSf+"<br>"+ansTemp+"</td>";
		else
			ans.add(toToolbarDbSf + " toolbar.htm file configured");		
		/*
		ansTemp = HagUtil.replaceStringInFile(toToolbarDbRIMs, "/"+fromBN+"/", "/"+toDb+"/");
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to configure toolbar.htm file in "+toToolbarDbRIMs+"<br>"+ansTemp+"</td>";
		else
			ans.add(toToolbarDbRIMs + " toolbar.htm file configured");		
		ansTemp = HagUtil.replaceStringInFile(toToolbarDbRISf, "/"+fromBN+"/", "/"+toDb+"/");
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to configure toolbar.htm file in "+toToolbarDbRISf+"<br>"+ansTemp+"</td>";
		else
			ans.add(toToolbarDbRISf + " toolbar.htm file configured");		
		*/
		
		//dbid-des file
		ansTemp = HagUtil.deleteFile(toDesOld,  false);
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to delete "+toDesOld+"<br>"+ansTemp+"</td>";
		else
			ans.add(toDesOld + " deleted");
		ansTemp = toDesList.writeToFile(toDes, false);
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to create "+toDes+"<br>"+ansTemp+"</td>";
		else
			ans.add(toDes + " created");

		//dbidRI-des file
//		ansTemp = HagUtil.deleteFile(toDesRiOld, false, false);
//		if(!ansTemp.startsWith("0~"))
//			return "<td bgColor=\"#FF0000\">Failed to delete "+toDesRiOld+"<br>"+ansTemp+"</td>";
//		else
//			ans.add(toDesRiOld + " deleted");
		
		
	//	String clearDb ="config\\skel\\admindb.bat~\""+"config\\skel\\admindb.js\"~-k~"+dbid;
		String clearDb =HagUtil.cfgMenuWebLoc+"\\skel\\admindb.bat \""+HagUtil.cfgMenuWebLoc+"\\skel\\admindb.js\" -k "+toBatchName;
		//ansTemp = HagUtil.simpleDosCmd(clearDb,false);
		ansTemp =HagUtil.runNotSudo(toApp,clearDb,user);
		if(ansTemp.startsWith("1~"))
			return "<td bgColor=\"#FF0000\">Failed to run clearDB <br>"+ansTemp+"</td>";
	    else
	    	ans.add("clearDB = done<br>"+ansTemp);
		
		return "<td bgColor=\"#00FF00\">"+ans.convertToString("<br>")+"</td>";
	}
	public String 	replacePrivateDb_setRiProfileTable(String toApp,String toSql,String toDB,String tomcat_port,String cust,String toBatchName){
		//ri
		//	RID-2224 String glFileLoc = "\\\\"+toApp+"\\RI_SHARE\\"+toBatchName+"\\INPUTFILES"; 
		String glFileLoc = "D:\\RI_SHARE\\"+toBatchName+"\\GL_TRANSFER_FILES"; 
		
		HagSQL hagSQL = new HagSQL();
		StringBuilder stm4= new StringBuilder("Update ["+toDB+"].[RI].[RIPROFILE] ")
		.append("set ")
		.append("DOC_CONV_IP='"+toApp+"'")
		.append(",HTTP_LISTEN_URL='"+"http://"+toApp+":"+tomcat_port+"/ri-web"+"'")
		.append(",CLAIM_INPUT_PATH='"+"\\\\"+toApp+"\\RI_SHARE\\"+toBatchName+"\\INPUTFILES"+"'")
		.append(",DOC_CONV_DB_DIR='"+toBatchName+"'")
		.append(",DATA_BASE_ID='"+"RI"+"'")
		.append(",CONFIG_FILE_ID='"+toBatchName+"'")
		.append(",JSP_PROD_URL='"+"d:\\RI_JAVA\\RIjava_"+toBatchName+"\\tomcat\\webapps\\ri-web\\WEB-INF\\classes\\documents-word\\"+cust+"'")
		.append(",JSP_TEMP_URL='"+"d:\\RI_SHARE\\"+toBatchName+"\\Jasper_Runtime_Templates"+"'")
		.append(",DOC_HTTP_URL='"+"WebProxy"+"'");
		//RID-2224	if(cust.equals("GRD")) 
		stm4.append(",GL_FILE_LOC='"+glFileLoc+"'");
		//.append(",JSP_TEMP_URL='"+sentBy+"'");
		//.append(",JSP_PROD_URL='"+sentBy+"'");
		String stm4S = stm4.toString();
		int ans4i =hagSQL.updateStm(toSql, "cfg","cfg09c", toDB,  stm4S);
		if(ans4i>0)
			return "<td bgColor=\"#00FF00\">RIPROFILE table updated.</td>";
		return "<td bgColor=\"#FF0000\">Failed to update RIPROFILE table.</td>";
		
	}	
	public String 	replacePrivateDb_setRiUserTable(String fromBN,String toSql,String toDB,String toBatchName,HagRc hatRc_setRiUserTable){
		//ri
		HagSQL hagSQL = new HagSQL();
		StringBuilder stm5= new StringBuilder("Update ["+toDB+"].[RI].[RIUSER] ")
		.append("set ")
		.append("USER_IDENTITY='"+toBatchName+"'")
		.append(",RI_USER_NAME='"+toBatchName+"'")
		.append(" where USER_IDENTITY='"+fromBN+"'");
		String stm5S = stm5.toString();
		hatRc_setRiUserTable.log.add(stm5S);
		hatRc_setRiUserTable.log.add("toSql="+toSql);
		hatRc_setRiUserTable.log.add("toDB="+toDB);
		hatRc_setRiUserTable.log.add("user=cfg");
		int ans5i =hagSQL.updateStm(toSql, "cfg","cfg09c", toDB,  stm5S);
		hatRc_setRiUserTable.log.add("num updated rows = "+ans5i);
		if(ans5i>0)
			return "<td bgColor=\"#00FF00\">RIUSER table updated.</td>";
		return "<td bgColor=\"#FF0000\">Failed to update RIUSER table.</td>";
	}
	public String 	replacePrivateDb_setRiUser(String toSql,String toDB,String toBatchName){
		//ri
		HagSQL hagSQL = new HagSQL();
		StringBuilder stm6= new StringBuilder()
		.append("IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'RI') ")
		.append("BEGIN EXEC('CREATE SCHEMA [RI] AUTHORIZATION [dbo]') END ")
		.append("IF NOT EXISTS(SELECT principal_id FROM sys.server_principals WHERE name = '"+toBatchName+"') ") 
		.append("BEGIN CREATE LOGIN ["+toBatchName+"] WITH PASSWORD=N'"+toBatchName+"PASS', DEFAULT_DATABASE=["+toDB+"], CHECK_EXPIRATION=OFF, CHECK_POLICY=OFF END ")
		.append("IF NOT EXISTS(SELECT principal_id FROM sys.database_principals WHERE name = '"+toBatchName+"') ") 
		.append("BEGIN CREATE USER ["+toBatchName+"] FOR LOGIN ["+toBatchName+"] END ")
		.append("ALTER USER ["+toBatchName+"] WITH DEFAULT_SCHEMA=[RI] ")
		.append("EXEC sp_addrolemember N'db_owner', N'"+toBatchName+"' ")
		.append("exec sp_change_users_login 'update_one', '"+toBatchName+"', '"+toBatchName+"'"); 
		String stm6S = stm6.toString();
		int ans6i =hagSQL.updateStm(toSql, "cfg","cfg09c", toDB,  stm6S);
	
		if(ans6i==-2)
			return"<td bgColor=\"#FF0000\">set RI user Failed</td>"+"<td bgColor=\"#FF0000\">"+stm6S+"</td>";
		
		return "<td bgColor=\"#00FF00\">"+"set RI user done"+"</td>";
	}
	public String 	replacePrivateDb_setRiadminUser(String toSql,String toDB){
		//riadmin
		HagSQL hagSQL = new HagSQL();
		StringBuilder stm7= new StringBuilder()
		.append("IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'RI') ")
		.append("BEGIN EXEC('CREATE SCHEMA [RI] AUTHORIZATION [dbo]') END ")
		.append("IF NOT EXISTS(SELECT principal_id FROM sys.database_principals WHERE name = 'RIADMIN') ") 
		.append("BEGIN CREATE USER [RIADMIN] FOR LOGIN [RIADMIN] END ")
		.append("ALTER USER [RIADMIN] WITH DEFAULT_SCHEMA=[RI] ")
		.append("EXEC sp_addrolemember N'db_owner', N'RIADMIN' ")
		.append("exec sp_change_users_login 'update_one', 'RIADMIN', 'RIADMIN'"); 
		String stm7S = stm7.toString();
		int ans7i =hagSQL.updateStm(toSql, "cfg","cfg09c", toDB,  stm7S);
		
		if(ans7i==-2)
			return "<td bgColor=\"#FF0000\">Failed</td>";
		
		return "<td bgColor=\"#00FF00\">"+"RIADMIN done"+"</td>";
	}
	public String 	replacePrivateDb_setAdapter(String toSql,String toDB){
		//adapter1
		HagStringList ans = new HagStringList();
		HagSQL hagSQL = new HagSQL();
		String stm8S= "ALTER DATABASE "+toDB+" SET TRUSTWORTHY ON";
		int ans8i =hagSQL.updateStm(toSql, "cfg","cfg09c", toDB,  stm8S);
		if(ans8i==-2)
			return "<td bgColor=\"#FF0000\">SET TRUSTWORTHY ON Failed</td>";
		ans.add("SET TRUSTWORTHY ON done");
		//adapter2
		String stm9S= "GRANT EXTERNAL ACCESS ASSEMBLY TO RIADMIN";
		int ans9i =hagSQL.updateStm(toSql, "cfg","cfg09c", "master",  stm9S);
		if(ans9i==-2)
			return "<td bgColor=\"#FF0000\">GRANT EXTERNAL ACCESS ASSEMBLY TO RIADMIN Failed</td>";
		ans.add("GRANT EXTERNAL ACCESS ASSEMBLY TO RIADMIN done");
		//adapter3
		String stm10S= "execute sp_changedbowner @loginame = N'sa', @map = false";
		int ans10i =hagSQL.updateStm(toSql, "cfg","cfg09c", toDB,  stm10S);
		if(ans10i==-2)
			return "<td bgColor=\"#FF0000\">execute sp_changedbowner Failed</td>";
		ans.add("execute sp_changedbowner done");
		//adapter4
		String stm11S= "EXEC sp_configure 'clr enabled' , '1'";
		int ans11i =hagSQL.updateStm(toSql, "cfg","cfg09c", toDB,  stm11S);
		if(ans11i==-2)
			return "<td bgColor=\"#FF0000\">sp_configure Failed</td>";
		ans.add("sp_configure done");
		//adapter5
		String stm12S= "reconfigure";
		int ans12i =hagSQL.updateStm(toSql, "cfg","cfg09c", toDB,  stm12S);
		if(ans12i==-2)
			return "<td bgColor=\"#FF0000\">reconfigure Failed</td>";
		ans.add("reconfigure done");

		
		return "<td bgColor=\"#00FF00\">"+ans.convertToString("<br>")+"</td>";
	}
	public String 	replacePrivateDb_deleteIom(String toApp,String toDb,String iomNfs,HagRc hagRcIom){
		//iom
		
if(1==1) {
	hagRcIom.log.add("no need to delete");
	return "<td bgColor=\"#00FF00\">no need to delete</td>";
		
}


String to ="\\\\"+toApp+"\\RI_SHARE\\"+toDb+"\\IOM_Core";
		hagRcIom.log.add("folder to delete = "+to);
		HagUtil.reCreateFolder(hagRcIom, to);
		if(hagRcIom.rc!=0){
			String ansTemp2 = "<td bgColor=\"#FF0000\">Failed to recreate IOM folder "+to+"<br>"+hagRcIom.log.convertToString("<br>") +"</td>";
			return ansTemp2;
		}else{
			String ansTemp2 = "<td bgColor=\"#00FF00\">IOM folder "+to+"reCreated<br>"+hagRcIom.log.convertToString("<br>") +"</td>";
			return ansTemp2;
		}
	}
	
	public String 	replacePrivateDb_replaceIom(String toApp,String toDb,String iomNfs,HagRc hagRcCopyIom,String log){
		
		//HagStringList ans = new HagStringList();
		String to ="\\\\"+toApp+"\\RI_SHARE\\"+toDb+"\\IOM_Core\\";
	
		String ans13i =  HagUtil.copyFolderGonen(iomNfs, to, toApp, toDb,log);
		hagRcCopyIom.log.add(ans13i);
 	
 		if(!ans13i.startsWith("0~") )
 			return "<td bgColor=\"#FF0000\">Failed to copy IOM from "+iomNfs+" to "+to+"<br>"+ans13i+"</td>";
		else
			return "<td bgColor=\"#00FF00\">copy IOM from "+iomNfs+" to "+to+"<br> "+ans13i+"</td>";


	}

	public String 	replacePrivateDb_replaceIom1111(String toApp,String toDb,String iomNfs,HagRc hagRcCopyIom){
		//iom

		String to ="\\\\"+toApp+"\\RI_SHARE\\"+toDb+"\\IOM_Core";
	
	
		
			String ans12i = HagUtil.copyFolder( iomNfs,to,false,false,"3","");
			hagRcCopyIom.log.add(ans12i);

			if(!ans12i.startsWith("0~"))
				return "<td bgColor=\"#FF0000\">Failed to copy IOM from "+iomNfs+" to "+to+"<br>"+ans12i+"</td>";
			else
				return "<td bgColor=\"#00FF00\">copy IOM from "+iomNfs+" to "+to+"<br> done.</td>";
	
		
		
	}
	//spr1004 - add iom-cust
	public String 	replacePrivateDb_replaceIomCust(String toApp,String toDb,String iomNfs,HagRc hagRcCopyIom){
		//iom
if(iomNfs.endsWith("_Core"))
	
	iomNfs=iomNfs.substring(0,iomNfs.length()-5);

iomNfs=iomNfs+"_Cust";
	
String to ="\\\\"+toApp+"\\RI_SHARE\\"+toDb+"\\IOM_Cust";
			String ans12i = HagUtil.copyFolder( iomNfs,to,false,false,"3","");
			hagRcCopyIom.log.add(ans12i);

			if(!ans12i.startsWith("0~"))
				return "<td bgColor=\"#E2F30C\">Failed to copy IOM from "+iomNfs+" to "+to+"<br>"+ans12i+"</td>";
			else
				return "<td bgColor=\"#00FF00\">copy IOM from "+iomNfs+" to "+to+"<br> done.</td>";
	
		
		
	}
	public String 	replacePrivateDb_deleteIomCust(String toApp,String toDb,HagRc hagRcIom){
		//iom

		String to ="\\\\"+toApp+"\\RI_SHARE\\"+toDb+"\\IOM_Cust";
		hagRcIom.log.add("folder to delete = "+to);
		HagUtil.reCreateFolder(hagRcIom, to);
		if(hagRcIom.rc!=0){
			String ansTemp2 = "<td bgColor=\"#E2F30C\">Failed to recreate IOM folder "+to+"<br>"+hagRcIom.log.convertToString("<br>") +"</td>";
			return ansTemp2;
		}else{
			String ansTemp2 = "<td bgColor=\"#00FF00\">IOM folder "+to+"reCreated<br>"+hagRcIom.log.convertToString("<br>") +"</td>";
			return ansTemp2;
		}
	}
	public String 	replacePrivateDb_replaceStartup(String toApp,String toDb,String startupNfs){
		//startup
		String to ="\\\\"+toApp+"\\RI_JAVA\\RIjava_"+toDb+"\\runtime\\startup";
		HagRc hagRcStartup = new HagRc();
		HagUtil.reCreateFolder(hagRcStartup, to);
		String ansTemp = "<td bgColor=\"#00FF00\">runtime\\startup folder recreated and copied from "+startupNfs+" to "+to+"</td>";
		if(hagRcStartup.rc!=0){
			ansTemp = "<td bgColor=\"#FF0000\">Failed to recreate runtime\\startup folder "+to+"<br>"+hagRcStartup.log.convertToString("<br>") +"</td>";
		}else{
			String ans12i = HagUtil.copyFolder( startupNfs,to,false,false,"1","");
			if(!ans12i.startsWith("0~"))
				ansTemp = "<td bgColor=\"#FF0000\">Failed to copy runtime\\startup from "+startupNfs+" to "+to+"<br>"+ans12i+"</td>";
		}
		
		return ansTemp;
	}
	public String 	replacePrivateDb_deleteTomcat(String toApp,String toDb,String tomcatNfs){
		if(1==1)
			return "<td bgColor=\"#00FF00\">no need to delete</td>";
		HagStringList ans = new HagStringList();
		String toTomcat ="\\\\"+toApp+"\\RI_JAVA\\RIjava_"+toDb+"\\tomcat";
		HagRc hagRcTomcat = new HagRc();

		HagUtil.reCreateFolder(hagRcTomcat, toTomcat);
		if(hagRcTomcat.rc!=0){
			HagUtil.sleep(5000);
			hagRcTomcat.log.add("delete try no#2");
			hagRcTomcat.rc = 0;
			HagUtil.reCreateFolder(hagRcTomcat, toTomcat);
			if(hagRcTomcat.rc!=0){
				HagUtil.sleep(10000);
				hagRcTomcat.log.add("delete try no#3");
				hagRcTomcat.rc = 0;
				HagUtil.reCreateFolder(hagRcTomcat, toTomcat);
			}
		}
			
		
	//	String ansTemp = "<td bgColor=\"#00FF00\">tomcat folder recreated and copied from "+tomcatNfs+" to "+toTomcat+"</td>";
		if(hagRcTomcat.rc!=0)
			return "<td bgColor=\"#FF0000\">Failed to recreate tomcat folder "+toTomcat+"<br>"+hagRcTomcat.log.convertToString("<br>") +"</td>";
		return "<td bgColor=\"#00FF00\">tomcat folder "+toTomcat+" deleted<br>"+hagRcTomcat.log.convertToString("<br>") +"</td>";
	}
	public String 	replacePrivateDb_deleteTomcat1111(String toApp,String toDb,String tomcatNfs){
		HagStringList ans = new HagStringList();
		String toTomcat ="d:\\RI_JAVA\\RIjava_"+toDb+"\\tomcat";

		String delCmd="rmdir "+toTomcat+" /s /q";
		String delAns=HagUtil.simpleDosCmd("javaFunc~simpleDosCmd~"+delCmd, true);
		if(!delAns.startsWith("0~")) {
			return "<td bgColor=\"#FF0000\">Failed to delete tomcat folder "+toTomcat +"</td>";
	
		}
		String crtCmd="mkdir "+toTomcat;
		String crtAns=HagUtil.simpleDosCmd("javaFunc~simpleDosCmd~"+crtCmd, true);
		if(!crtAns.startsWith("0~")) {
			return "<td bgColor=\"#FF0000\">Failed to create tomcat folder "+toTomcat+"</td>";
	
		}
				return "<td bgColor=\"#00FF00\">tomcat folder "+toTomcat+" recreated<br>" +"</td>";
	}
	
	public String 	replacePrivateDb_copyTomcat(String toApp,String toDb,String tomcatNfs,String log){
		
		HagStringList ans = new HagStringList();
		String toTomcat ="\\\\"+toApp+"\\RI_JAVA\\RIjava_"+toDb+"\\tomcat";
	
		String ans13i =  HagUtil.copyFolderGonen(tomcatNfs, toTomcat,toApp, toDb,log);
		
 	
 		if(!ans13i.startsWith("0~") )
			return "<td bgColor=\"#FF0000\">Failed to copy tomcat from "+tomcatNfs+" to "+toTomcat+"<br>"+ans13i+"</td>";
 		else
			return "<td bgColor=\"#00FF00\">copy tomcat from "+tomcatNfs+" to "+toTomcat+"<br>"+ans13i+"</td>";

	}
public String 	replacePrivateDb_copyTomcat111(String toApp,String toDb,String tomcatNfs){
		
		HagStringList ans = new HagStringList();
		String toTomcat ="\\\\"+toApp+"\\RI_JAVA\\RIjava_"+toDb+"\\tomcat";
	
 		String ans13i = HagUtil.copyFolder( tomcatNfs,toTomcat,false,false,"1",HagUtil.cfgMenuWebLoc+"\\lists\\excludedFilesList.txt");
 		String ans13ia=HagUtil.createFolder(toTomcat+"\\logs");
 		String ans13ib=HagUtil.createFolder(toTomcat+"\\temp");
 		if(!ans13i.startsWith("0~") )
			return "<td bgColor=\"#FF0000\">Failed to copy tomcat from "+tomcatNfs+" to "+toTomcat+"<br>"+ans13i+"</td>";
 		else if(!ans13ia.startsWith("0~") ) 
 				return "<td bgColor=\"#FF0000\">Failed to create tomcat logs folder <br>"+ans13ia+"</td>";
 		else if(!ans13ib.startsWith("0~") ) 
				return "<td bgColor=\"#FF0000\">Failed to create tomcat temp folder <br>"+ans13ib+"</td>";
		ans.add("tomcat replaced:"+ans13i);
		
		return "<td bgColor=\"#00FF00\">"+ans.convertToString("<br>")+"</td>";

	}
	public String 	replacePrivateDb_replaceProperties(String fromApp,String fromBN,String toApp,String toDB,String toSQL,String toBatchName){
		HagStringList ans = new HagStringList();
		
		
		//properties
		String propFileFrom = "\\\\"+fromApp+"\\RI_JAVA\\RIjava_"+fromBN+"\\properties\\"+fromBN+".properties"; 
		String propFileTo 	= "\\\\"+toApp+"\\RI_JAVA\\RIjava_"+toBatchName+"\\properties\\"+toBatchName+".properties"; 
		String ans1 = HagUtil.deleteFile(propFileTo,  false);
		if(!ans1.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to delete "+propFileTo+"<br>"+ans1+"</td>";
		else
			ans.add(propFileTo + " deleted");
		ans1 = HagUtil.copyFileViaDos(propFileFrom, propFileTo);		
		if(!ans1.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to copy "+propFileFrom+" to "+propFileTo+"<br>"+ans1+"</td>";
		else
			ans.add(propFileTo + " copied");

		HagStringList propFileList = new HagStringList(propFileTo,false,"asasassd",true);
		for (int i = 0;i<propFileList.size();i++){
			String temp = propFileList.get(i);
			if(temp.startsWith("db.host="))						propFileList.set( i,"db.host="+toSQL);
			if(temp.startsWith("db.schema="))					propFileList.set( i,"db.schema="+toDB);
			if(temp.startsWith("jg.tmplDir="))					propFileList.set( i,"jg.tmplDir=d:\\\\RI_JAVA\\\\RIjava_"+toBatchName+"\\\\ritemplates");
			if(temp.startsWith("jg.filesDir="))					propFileList.set( i,"jg.filesDir=d:\\\\RI_JAVA\\\\RIjava_"+toBatchName+"\\\\runtime\\\\"+toBatchName);
			if(temp.startsWith("jg.iseriesTmpLib="))			propFileList.set( i,"jg.iseriesTmpLib="+toBatchName+"TEMP");
			if(temp.startsWith("web.startup.dir="))				propFileList.set( i,"web.startup.dir=d:\\\\RI_JAVA\\\\RIjava_"+toBatchName+"\\\\runtime\\\\startup");
			if(temp.startsWith("cmInstaller.iom="))				propFileList.set( i,"cmInstaller.iom=d:\\\\RI_SHARE\\\\"+toBatchName+"\\\\IOM_Core");
			if(temp.startsWith("cmInstaller.batchName="))		propFileList.set( i,"cmInstaller.batchName="+toBatchName);
			if(temp.startsWith("cmInstaller.CtDatabaseName="))	propFileList.set( i,"cmInstaller.CtDatabaseName="+toDB);
		}
		propFileList.writeToFile(propFileTo, null, false);
		ans.add(propFileTo + " parameters updated:");
		
		return "<td bgColor=\"#00FF00\">"+ans.convertToString("<br>")+"</td>";
	}
	public String 	replacePrivateDb_setTomcatProperties(String version,String serverPort,String connectionPort,String debugPort,String fromDb,String toApp,
			String toBatchName,String log4jLevel,String debugFolder){
		HagStringList ans = new HagStringList();
		String 			fromSkel = HagUtil.cfgMenuWebLoc+"\\skel\\tomcat8";
		String log4jName = "log4j2.xml";
		if(version.equals("0700") || version.startsWith("06")){
			fromSkel = HagUtil.cfgMenuWebLoc+"\\skel\\tomcat7";
			log4jName = "log4j.xml";
		}
		
		//log4j
		String log4jFileFrom = fromSkel+"\\"+log4jName; 
		String log4jFileTo 	= "\\\\"+toApp+"\\RI_JAVA\\RIjava_"+toBatchName+"\\tomcat\\shared\\"+log4jName; 
		String ansTemp = HagUtil.deleteFile(log4jFileTo,  false);
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to delete "+log4jFileTo+"<br>"+ansTemp+"</td>";
		else
			ans.add(log4jFileTo + " deleted");
		ansTemp = HagUtil.copyFileViaDos(log4jFileFrom, log4jFileTo);		
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to copy "+log4jFileFrom+" to "+log4jFileTo+"<br>"+ansTemp+"</td>";
		else
			ans.add(log4jFileTo + " copied");
		ansTemp = HagUtil.replaceStringInFile(log4jFileTo, "{RI-TOMCAT-LOG}", "d://RI_SHARE//"+toBatchName+"//TomcatLogs");
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to configure1 "+log4jFileTo+"<br>"+ansTemp+"</td>";
		else
			ans.add(log4jFileTo + " configured1");	
		
		ansTemp = HagUtil.replaceStringInFile(log4jFileTo, "{errorOrDebug}", log4jLevel);
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to configure2 "+log4jFileTo+"<br>"+ansTemp+"</td>";
		else
			ans.add(log4jFileTo + " configured2");	
		//server.xml
		String serverXmlFileFrom = fromSkel+"\\server.xml"; 
		String serverXmlFileTo 	= "\\\\"+toApp+"\\RI_JAVA\\RIjava_"+toBatchName+"\\tomcat\\conf\\server.xml"; 
		ansTemp = HagUtil.deleteFile(serverXmlFileTo,  false);
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to delete "+serverXmlFileTo+"<br>"+ansTemp+"</td>";
		else
			ans.add(serverXmlFileTo + " deleted");
		ansTemp = HagUtil.copyFileViaDos(serverXmlFileFrom, serverXmlFileTo);		
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to copy "+serverXmlFileFrom+" to "+serverXmlFileTo+"<br>"+ansTemp+"</td>";
		else
			ans.add(serverXmlFileTo + " copied");
		String ansTemp1 = HagUtil.replaceStringInFile(serverXmlFileTo, "{RI-SERVER-PORT}", serverPort);
		String ansTemp2 = HagUtil.replaceStringInFile(serverXmlFileTo, "{RI-CONNECTOR-PORT}", connectionPort);
		if(!ansTemp1.startsWith("0~") ||!ansTemp2.startsWith("0~")  )
			return "<td bgColor=\"#FF0000\">Failed to configure "+serverXmlFileTo+"<br>"+ansTemp1+"<br>"+ansTemp2+"</td>";
		else
			ans.add(serverXmlFileTo + " configured");	
		/*
		HagStringList list = new HagStringList(serverXmlFileFrom,false,"adafrsa",true);
		list.replaceStr("{RI-SERVER-PORT}", serverPort);
		list.replaceStr("{RI-CONNECTOR-PORT}", connectionPort);
		ansTemp = list.writeToFile(serverXmlFileTo,  false);
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to configure "+serverXmlFileTo+"<br>"+ansTemp+"</td>";
		else
			ans.add(serverXmlFileTo + " configured");	
		*/
		//debug.bat
		String debugFileFrom = fromSkel+"\\debug.bat"; 
		String debugFileTo 	= "\\\\"+toApp+"\\RI_JAVA\\RIjava_"+toBatchName+"\\tomcat\\bin\\debug.bat"; 
		ansTemp = HagUtil.deleteFile(debugFileTo,  false);
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to delete "+debugFileTo+"<br>"+ansTemp+"</td>";
		else
			ans.add(debugFileTo + " deleted");
		ansTemp = HagUtil.copyFileViaDos(debugFileFrom, debugFileTo);		
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to copy "+debugFileFrom+" to "+debugFileTo+"<br>"+ansTemp+"</td>";
		else
			ans.add(debugFileTo + " copied");
		ansTemp = HagUtil.replaceStringInFile(debugFileTo, "{RI-DEBUG-PORT}", debugPort);
		if(!ansTemp.startsWith("0~"))
			return "<td bgColor=\"#FF0000\">Failed to configure "+debugFileTo+"<br>"+ansTemp+"</td>";
		else
			ans.add(debugFileTo + " configured");	
		
		
		String catalinaS 	= "\\\\"+toApp+"\\RI_JAVA\\RIjava_"+toBatchName+"\\tomcat\\bin\\catalina.bat"; 
		
		String aaaa= HagUtil.updateCatalinaLines( catalinaS, version,debugFolder,ans);
		return "<td bgColor=\"#00FF00\">"+ans.convertToString("<br>")+"</td>";
	}
	public String 	replacePrivateDb_replaceCmTable(String toApp,String toDb,String version,String patch,String party,String lastInst,String lastGoodInst){
		HagSQL hagSQL = new HagSQL();
		StringBuilder stm4= new StringBuilder("Update "+HagParam.getConfg1DB()+".[dbo].[ri_environments_new] ")
		.append("set ")
		.append("version='"+version+"'")
		.append(",patch='"+patch+"'")
		.append(",party='"+party+"'")
		.append(",lastInst='"+lastInst+"'")
		.append(",lastGoodInst='"+lastGoodInst+"' ")
		.append("where bis_server='"+toApp+"' and batchName='"+toDb+"'");
		//.append(",JSP_TEMP_URL='"+sentBy+"'");
		//.append(",JSP_PROD_URL='"+sentBy+"'");
		String stm4S = stm4.toString();
		int ans4i =hagSQL.updateStm("confg1", "cfg","cfg09c", HagParam.getConfg1DB(),  stm4S);
		if(ans4i>0)
			return "<td bgColor=\"#00FF00\">ri_environments table updated.</td>";
		return "<td bgColor=\"#FF0000\">Failed to update ri_environments table.</td><td>stm="+stm4+"</td><td>rc="+ans4i+"</td>";
		
	}
	public String 	replaceJasper_Runtime_TemplatesFolder(String fromApp,String fromBatchName,String toApp,String toBatchName,String party,String log){
		
		String fromJasper_Runtime_Templates ="\\\\"+fromApp+"\\RI_SHARE\\"+fromBatchName+"\\Jasper_Runtime_Templates";
		String toJasper_Runtime_Templates ="\\\\"+toApp+"\\RI_SHARE\\"+toBatchName+"\\Jasper_Runtime_Templates\\";
		HagRc hagRc = new HagRc();
		//HagUtil.reCreateFolder(hagRc, toJasper_Runtime_Templates);
	//	String ansTemp = "<td bgColor=\"#00FF00\">Jasper_Runtime_Templates folder recreated and copied from "+fromJasper_Runtime_Templates+" to "+toJasper_Runtime_Templates+"</td>";
		//if(hagRc.rc!=0){
		//	ansTemp = "<td bgColor=\"#FF0000\">Failed to recreate Jasper_Runtime_Templates folder "+toJasper_Runtime_Templates+"<br>"+hagRc.log.convertToString("<br>") +"</td>";
	//	}else{
		String ansTemp = "";
			if(party.equals("190000")) {
				ansTemp = "<td bgColor=\"#FFFF00\">RML customer is not a Jasper customer</td>";
			}else {
				//String ans13i = HagUtil.copyFolder( fromJasper_Runtime_Templates,toJasper_Runtime_Templates,false,false,"1","");
				String ans13i =  HagUtil.copyFolderGonen(fromJasper_Runtime_Templates, toJasper_Runtime_Templates, toApp, toBatchName,log);
					if(!ans13i.startsWith("0~") )
					return "<td bgColor=\"#FF0000\">Failed to copy tomcat from "+fromJasper_Runtime_Templates+" to "+toJasper_Runtime_Templates+"<br>"+ans13i+"</td>";
		 		else
					return "<td bgColor=\"#00FF00\">copy tomcat from "+fromJasper_Runtime_Templates+" to "+toJasper_Runtime_Templates+"<br>"+ans13i+"</td>";

			}
		//}
		
		return ansTemp;
	}
	public String 	replaceInputfilesFolder(String fromApp,String fromDb,String toApp,String toDb){
	
	String fromInputfilesFolder ="\\\\"+fromApp+"\\RI_SHARE\\"+fromDb+"\\INPUTFILES";
	String toInputfilesFolder ="\\\\"+toApp+"\\RI_SHARE\\"+toDb+"\\INPUTFILES";
	HagRc hagRc = new HagRc();
	HagUtil.reCreateFolder(hagRc, toInputfilesFolder);
	String ansTemp = "<td bgColor=\"#00FF00\">INPUTFILES folder recreated and copied from "+fromInputfilesFolder+" to "+toInputfilesFolder+"</td>";
	if(hagRc.rc!=0){
		ansTemp = "<td bgColor=\"#FF0000\">Failed to recreate INPUTFILES folder "+toInputfilesFolder+"<br>"+hagRc.log.convertToString("<br>") +"</td>";
	}else{
		String ans13i = HagUtil.copyFolder( fromInputfilesFolder,toInputfilesFolder,false,false,"1","");
		if(!ans13i.startsWith("0~"))
			ansTemp = "<td bgColor=\"#FF0000\">Failed to copy INPUTFILES from "+fromInputfilesFolder+" to "+toInputfilesFolder+"<br>"+ans13i+"</td>";
	}

	
	return ansTemp;
}
	
	public String 	updateSiteExitTable(String toBatchName,String toSQL,String toDb){
		
		String libName = "FILE:D:\\\\RI_JAVA\\\\RIjava_"+toBatchName.toUpperCase().trim()+"\\\\tomcat\\\\riExit\\\\";
		
		String stm = "update RI.RIEXITPROG set  LIBRARY_NAME='"+libName+"' where EXIT_PROG_LANG='JVA'";
		HagRc hagRc = new HagRc();
		HagSQL hagSQL = new HagSQL();
		hagSQL.runSqlExec(hagRc, toSQL, "RIADMIN", "ADMINRI", toDb, stm);
		String ansTemp ="";
		if(hagRc.rc!=0)
			ansTemp = "<td bgColor=\"#FF0000\">Failed to update  SiteExit Table folder <br>"+hagRc.log.convertToString("<br>") +"</td>";
		else
			ansTemp = "<td bgColor=\"#00FF00\">SiteExit Table updated</td>";

	

	
		return ansTemp;
	}
	public String 	updateTalendTable(){
		
		String ansTemp = "<td bgColor=\"#00FF00\">FUTURE1</td>";

	

	
		return ansTemp;
	}
public String 	updateSapiensReg(String  appServer,String  sqlServer,String  user,String  pass,String  db,String  batchName){
	HagRc hagRc = new HagRc();
	
	HagUtil.setSapiensRegCharSet( hagRc, appServer, sqlServer, user, pass, db, batchName);
		String ansTemp = "<td bgColor=\"#00FF00\">"+hagRc.log.convertToString("<br>")+"</td>";

	

	
		return ansTemp;
	}


	public  String 			getInitCmd(String fromDb, String toDb, String fromApp_server, String toApp_server, String user  ) {
	//String cmd ="\\\\ORSAYSERVER\\System32\\cscript.exe "+ //08042024
	String cmd ="cscript.exe "+
	"\\\\orsayserver\\d\\gonteam\\#VBscript_and_SQL\\"+HagParam.getVbProdOrTest()+"\\Backup_Restore.vbs "+
	"/OPT:\"BK&RS\" "+
	"/ProdTest:\"PROD\" "+
	"/User:\""+user+"\" "+
	"/BackupFileDir:\"\\\\ri-archive\\Saptech-Archive\\Temp\\TempBackups\\\" " +
	"/BackupFileName:\".\"  "+
	"/SrcAppServer:\""+fromApp_server+"\"  "+
	"/SrcDBID:\""+fromDb+"\"  "+
	"/TgtAppServer:\""+toApp_server+"\"  "+
	"/TgtDBID:\""+toDb+"\" "+
	"/LeaveTrace:\"Y\" "+
	"/Monitor:\"N\"";
	return cmd;
}
    public  String 			copyDbGonen(String fromDb, String toDb, String fromApp_server, String toApp_server  ) {
    //String cmd ="\\\\ORSAYSERVER\\System32\\cscript.exe "+ //08042024
	String cmd ="cscript.exe "+
	"\\\\orsayserver\\d\\gonteam\\#VBscript_and_SQL\\"+HagParam.getVbProdOrTest()+"\\Backup_Restore.vbs "+
	"/ProdTest:\"TEST\" "+
	"/BackupFileDir:\"\\\\ri-archive\\Saptech-Archive\\RI\\PTH\\\" " +
	"/BackupFileName:\".\"  "+
	"/SrcAppServer:\""+fromApp_server+"\"  "+
	"/SrcDBID:\""+fromDb+"\"  "+
	"/TgtAppServer:\""+toApp_server+"\"  "+
	"/TgtDBID:\""+toDb+"\" "+
	"/InFolder:\"\\\\orsayserver\\d\\gonteam\\#VBscript_and_SQL\\\" "+
	"/OutFolder:\"d:\\temp\\\" "+
	"/Monitor:\"N\"";
	String ans =runVbCmd(cmd);
	return ans;
	//// String bkupFile = "\\\\confg1\\d$\\ri\\manage\\temp\\" + fromDb +
	//// ".bk";
	//String ans = "";
	//String color = "bgColor = \"#00ff00\"";
	//String bkupFile = "\\\\ri-archive\\Saptech-Archive\\RI\\copyDbTemp\\" + fromDb + ".bk";
/*
	HagUtil.deleteFile(bkupFile,  false);
	ey_Gsetlibl ey_gsetlibl = new ey_Gsetlibl();
	//String rcStr0 = ey_gsetlibl.SimpleBK(fromSql_server, fromDb, bkupFile, "WITH INIT");
	String rcStr0 = ey_gsetlibl.SimpleBK(fromSql_server, fromDb, bkupFile, "WITH COPY_ONLY");
	if (rcStr0.indexOf("1~") > -1){
		ans = ans+"sourceBkup RC =FAILED<br>"+rcStr0;
		color = "bgColor = \"#ff0000\"";
		
	}else{
		ans = ans+"sourceBkup RC =OK<br>"+rcStr0;
	}
	
//	String datLoc = "D:\\Database\\";
//	String logLoc = "D:\\Database\\";
	
	String datLoc =HagUtil.getSqlLocation(toSql_server,4).trim();
	String logLoc =HagUtil.getSqlLocation(toSql_server,5).trim();
	ans = ans+"<br><br>";
	String rcStr1 = ey_gsetlibl.Create_Restore_with_dbfpath(toSql_server, toDb, bkupFile, datLoc, logLoc);
	if (rcStr1.indexOf("1~") > -1){
		ans = ans+", targetRst RC =FAILED<br>"+rcStr1;
		color = "bgColor = \"#ff0000\"";

	}else{
		ans = ans+", targetRst RC =OK<br>"+rcStr1 ;

	}
	return "<td "+color+">"+ans+"</td>";
	*/
}

}
