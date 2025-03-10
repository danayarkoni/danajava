package com.hag.hagay;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.*;
import java.util.ArrayList;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class HagSQL{
	public HagSQL(){
	}
	public ArrayList<String> callEyJar(String parent, String type, String name, String nameZip, String server) {
		String rcStr = "";
		ey_Gsetlibl ey_gsetlibl = new ey_Gsetlibl();
		if (parent.equals("SqlBackup"))
			//rcStr = ey_gsetlibl.SimpleBK(server, name, nameZip, "WITH INIT");
			rcStr = ey_gsetlibl.SimpleBK(server, name, nameZip, "WITH COPY_ONLY");
		else if (parent.equals("createRestore"))
			rcStr = ey_gsetlibl.Create_Restore(server, nameZip, nameZip);
	//	else if ((parent.equals("BackUp") || parent.equals("BKOnly")) && type.equals("SQL"))
	//		rcStr = ey_gsetlibl.SimpleBK("INGSQL", name, "C:\\backups\\" + name + ".bak", "WITH INIT");
	//	else if ((parent.equals("BackUp") || parent.equals("BKOnly")) && type.equals("ZIP"))
	//		rcStr = ey_gsetlibl.SimpleZIP("INGAPP", name, "C:\\backups\\" + nameZip + ".zip");
	//	else if ((parent.equals("Restore") || parent.equals("RSOnly")) && type.equals("SQL"))
	//		rcStr = ey_gsetlibl.SimpleRestore("INGSQL", name, "C:\\backups\\" + name + ".bak", "");
	//	else if ((parent.equals("Restore") || parent.equals("RSOnly")) && type.equals("ZIP"))
	//		rcStr = ey_gsetlibl.SimpleZIPRestore("INGAPP", "C:\\backups\\" + nameZip + ".zip", name);

		/*
		 * rcStr = ey_gsetlibl.SimpleBK(server,name,nameZip,"WITH INIT"); String
		 * rcStr0 =
		 * ey_gsetlibl.SimpleBK(fromSql_server,fromDb,bkupFile,"WITH INIT");
		 * ArrayList<String> arr0 = HagUtil.setPrint(rcStr0,100); int arr0Size =
		 * arr0.size(); String rcStr1
		 * =ey_gsetlibl.Create_Restore_with_dbfpath(toSql_server, toDb,
		 * bkupFile,"d:\\sqldata\\"); ArrayList<String> arr1 =
		 * HagUtil.setPrint(rcStr1,100); int arr1Size = arr1.size();
		 */

		/*
		 * if(chkVec.name.equals("BackUp")){ if(type.equals("SQL")) rcStr =
		 * ey_gsetlibl.SimpleBK("INGSQL",name,"C:\\backups\\"+name+".bak","");
		 * //rcStr =
		 * ey_gsetlibl.SimpleBK(type,"INGSQL",name,"C:\\backups\\"+name
		 * +".bak",""); else rcStr =
		 * ey_gsetlibl.SimpleZIP("INGAPP",name,"C:\\backups\\"+nameZip+".zip");
		 * //rcStr =
		 * ey_gsetlibl.SimpleBK(type,"INGAPP",name,"C:\\backups\\"+nameZip
		 * +".zip",""); }else{ if(type.equals("SQL")) rcStr =
		 * ey_gsetlibl.SimpleRestore
		 * ("INGSQL",name,"C:\\backups\\"+name+".bak",""); // rcStr =
		 * ey_gsetlibl
		 * .SimpleRestore(type,"INGSQL",name,"C:\\backups\\"+name+".bak"
		 * ,"WITH FILE = 1, REPLACE"); else rcStr =
		 * ey_gsetlibl.SimpleZIPRestore(
		 * "INGAPP",name,"C:\\backups\\"+nameZip+".zip"); //rcStr =
		 * ey_gsetlibl.SimpleRestore
		 * (type,"INGSQL",name,"C:\\backups\\"+nameZip+".zip",""); }
		 */
		ArrayList<String> ans = new ArrayList<String>();
		ans.add(rcStr);
		return ans;
	}
	public String dbBackupWithNotes(String sqlServer,String db2,String title,String note,String user) {
		String dbDataSize = ".";
		String dbLogSize = ".";
		int dbDataSizeI = 0;
		int dbLogSizeI = 0;
		int limitI = 0;
if(title.equals("Setup") && !user.equals("david.hagay"))
	return 	HagUtil.shortHtml("only hagay can run backup with type=Setup","red","bg");
//		int size = 0;
//		String limit = HagUtil.getPropertyVal("list\\cfg.list", "pthBackupSizeLimit");
//		if(limit==null)
//			limit="5000";
//		limitI=HagUtil.s2i(limit);
//		if(dbSize.size()>2){
//			String temp=dbSize.get(2).trim();;
//			String dbDataSize1 = HagUtil.getWord0(temp, "~", 2, true);
//			String dbLogSize1 = HagUtil.getWord0(temp, "~", 3, true);
//			dbDataSize = dbDataSize1.substring(0,dbDataSize1.indexOf(".")); 
//			dbLogSize = dbLogSize1.substring(0,dbLogSize1.indexOf("."));
//			dbDataSizeI=HagUtil.s2i(dbDataSize);
///			dbLogSizeI=HagUtil.s2i(dbLogSize);
//			size = dbDataSizeI+dbLogSizeI;
//		}
//		if(size > limitI){
//			Object[] options = { "continue","skip" };
//			StringBuilder textArea = new StringBuilder("");
//			textArea.append(sqlServer).append("-").append(db2).append("  Database size = ").append(size).append(" mb\n")
//			.append("Do you want to continue with this database backup ?\n")
//			.append("limit = ").append(limit).append(" (list\\cfg.list)" );
//			int answer = HagUtil.dspControlTextArea(500,330,"dsp before backup",textArea.toString(),
//					 JOptionPane.INFORMATION_MESSAGE, null, options);
//			if (answer==1)
//				return;
//		}
			
		
		Color cg = new Color(55,139,19);
		Color cr = new Color(162,19,28);
		String myDate = HagUtil.getDateTime("yyyyMMdd_HH_mm_ss");
		String pthBackupLocation=HagUtil.getPropertyVal("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\lists\\cfg.list", "dbBackupLocation");
		String bkF = pthBackupLocation+sqlServer+"_"+db2+"_"+myDate+"_"+title+".bk";
		String ntF = pthBackupLocation+sqlServer+"_"+db2+"_"+myDate+"_"+title+".txt";
		String bkF1 = pthBackupLocation+sqlServer+"_"+db2+"_"+myDate+"_"+title+".bkKeep";
		if(sqlServer.indexOf("\\")>0) {
			String sqlServer1 = HagUtil.replaceStr(sqlServer, "\\","#");
			bkF = pthBackupLocation+sqlServer1+"_"+db2+"_"+myDate+"_"+title+".bk";
			ntF = pthBackupLocation+sqlServer1+"_"+db2+"_"+myDate+"_"+title+".txt";
			bkF1 = pthBackupLocation+sqlServer1+"_"+db2+"_"+myDate+"_"+title+".bkKeep";
			
		}
		
		
		//String del1 = HagUtil.deleteFile(bkF, false, false);
		//hagGridPanel.hagWorkerChange("add", del1+"\n", false, false, cg, null, 3);
		ArrayList<String> ans = callEyJar("SqlBackup", "SQL",db2,bkF,sqlServer);
		//HagUtil.p(ans);
		
		if(ans.size()==0){
			return "Failed1";
		}
		if(ans.get(0).startsWith("0~")){
			HagUtil.writeStringToFile(ntF, note);
			HagUtil.renameFile(bkF, bkF1);
			
			if(title.equals("Initial")) {
				String ans1 = HagUtil.deleteFile(pthBackupLocation+"lastInitial.bkKeep",  false);
				String ans2 = HagUtil.deleteFile(pthBackupLocation+"lastInitial.txt",  false);
				String ans3 = HagUtil.copyFileViaDos(bkF1, pthBackupLocation+"lastInitial.bkKeep");		
				String ans4 = HagUtil.copyFileViaDos(ntF, pthBackupLocation+"lastInitial.txt");		
				if(!ans1.startsWith("0~") || !ans2.startsWith("0~") || !ans4.startsWith("0~") || !ans3.startsWith("0~"))
					return "Failed to replace lastInitial files";
			}
			return "Database backup file = "+bkF1+"<br>note file = "+ntF ;
			
		}else{
			return "Failed2-"+sqlServer+"_"+db2+"-"+ans.get(0);
		}
			
	}
	
	
	/******************************************************************************/
	private Connection getI5osConn(String server,String user,String pass,String schema){
		String url=null;
		Connection con = null;
		try {
			String driverName = "com.ibm.as400.access.AS400JDBCDriver";
			url = "jdbc:as400:"+server+".sapiens.com/"
			+ schema
			+ ";prompt=false;blocksize=8;block criteria=2;prefetch true;date format=iso;";
			Class.forName(driverName);
			con = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			System.out.println("Could not find the database driver - "+e.getMessage());
		} catch (SQLException e) {
			System.out.println("Could not connect to the database 1- "+e.getMessage());
		}
		if(con == null)
		    JOptionPane.showMessageDialog(null,"Failed to connect to\nServer: "+server+"\nUser: "+user+"\nSchema: "+schema,"Connection1 error",JOptionPane.ERROR_MESSAGE);
		return con;
	}
	private Connection getSqlConn(String server,String user,String pass,String db,String creator){
		String url=null;
		Connection con = null;
		try {
			String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			url = "jdbc:sqlserver://"+server+";Database="+db;
			Class.forName(driverName);
			con = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			System.out.println("Could not find the database driver - "+e.getMessage());
		} catch (SQLException e) {
			System.out.println("Could not connect to the database 2- "+e.getMessage());
		}
		if(con == null)
		    JOptionPane.showMessageDialog(null,"Failed to connect to\nServer: "+server+"\nUser: "+user+"\nDatabase: "+db,"Connection error",JOptionPane.ERROR_MESSAGE);

		return con;
	}
	 public String cfgInsert(String tbl,String val){

		 Connection con = getSqlConn("CONFG1","cfg","cfg09c",HagParam.getConfg1DB(),"dbo."); 
		 
		if(con==null)	return ""; 	//hagay-con=null
			
		 String stm="INSERT INTO "+HagParam.getConfg1DB()+".[dbo].["+tbl+"] "+
		 	"VALUES("+val+")";
		 try {
		 	con.createStatement().execute(stm);
		 	con.commit();
		 	return "0~from cfgInsert to "+tbl;
		 } catch (Exception e) {
			 System.out.println("insert2cfgout error-"+tbl+"-"+val);
			return "1~failed to insert to cfgInsert to "+tbl;
		 }
	 }
	public String update(	String type,
			String server,
			String username,
			String password,
			String db,
			String stm			){
		
		Connection con=null;
		if(type.equalsIgnoreCase("SQL"))
			con = getSqlConn(server,username,password,db,"dbo.");
		else
			con = getI5osConn(server,username,password,db);
		
		if(con==null)	
			return "1~no connection";
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate(stm);
			return "0~"+stm;
		} catch (SQLException e) {
			System.out.println("Could not connect to the database - "+e.getMessage());
			return "1~"+stm;
		}
	}
	/******************************************************************************/
	
	public HagStringList select1col(String type,
									String server,
									String username,
									String password,
									String db,
									
									String stm	){
		Connection con=null;
		if(type.equalsIgnoreCase("SQL"))
			con = getSqlConn(server,username,password,db,"dbo.");
		else
			con = getI5osConn(server,username,password,db);
		
		if(con==null)	return new HagStringList(); 	//hagay-con=null
		
		HagStringList 	mtxVec 		= new HagStringList();
		try {
			Statement stmt = con.createStatement();     
			ResultSet rs;
			rs = stmt.executeQuery(stm);                                                                                           
			while (rs.next())  
				mtxVec.add(rs.getString(1)); 
			stmt.close();                                                                                                                      
			con.close();      
		} catch (SQLException e) {
			mtxVec.add("Could not connect to the database");
			System.out.println("Could not connect to the database 3- "+e.getMessage());
		}
		return mtxVec;
	}
	public HagStringList select(String type,
			String server,
			String username,
			String password,
			String db,
			String stm,
			int colsNum,
			String delim,
			String pre,
			String def){
		Connection con=null;
		if(type.equalsIgnoreCase("SQL"))
			con = getSqlConn(server,username,password,db,"dbo.");
		else
			con = getI5osConn(server,username,password,db);
		
		if(con==null)	return new HagStringList(); 	//hagay-con=null
		
		HagStringList 	mtxVec 		= new HagStringList();
		try {
			Statement stmt = con.createStatement();     
			ResultSet rs;
			//HagUtil.p(stm);
			rs = stmt.executeQuery(stm);   
		
			while (rs.next()) {
				String str = rs.getString(1).trim();
				if(pre!=null)
					str=pre+"~"+str;
				
				//HagUtil.p(str);
				for(int i =2 ;i<= colsNum ;i++){
					String temp = rs.getString(i).trim();
					if(def!=null && temp.equals(""))
						temp=def;
					str=str+delim+temp;
				}
//				if(!s2.equals("."))	
					mtxVec.add(str); 
			}
			stmt.close();                                                                                                                      
			con.close();      
		} catch (SQLException e) {
			mtxVec.add("Could not connect to the database");
			System.out.println("Could not connect to the database 4 - "+e.getMessage());
		}
		return mtxVec;
	}
	
	public HagStringList selectAll(String type,
			String server,
			String username,
			String password,
			String db,
			String stm,
			String delim,
			String pre,
			String def){
		
		Connection con=null;
		if(type.equalsIgnoreCase("SQL"))
			con = getSqlConn(server,username,password,db,"dbo.");
		else
			con = getI5osConn(server,username,password,db);
		
		if(con==null)	return new HagStringList(); 	//hagay-con=null
		
		HagStringList 	mtxVec 		= new HagStringList();
		try {
			Statement stmt = con.createStatement();     
			ResultSet rs;
		
			//HagUtil.p(stm);
			rs = stmt.executeQuery(stm);   
			ResultSetMetaData rsmd = rs.getMetaData();
			int column_count = rsmd.getColumnCount();
			while (rs.next()) {
				String str = rs.getString(1).trim();
				if(pre!=null)
					str=pre+"~"+str;
				
				//HagUtil.p(str);
				for(int i =2 ;i<= column_count ;i++){
					String temp = rs.getString(i).trim();
					if(def!=null && temp.equals(""))
						temp=def;
					str=str+delim+temp;
				}
//				if(!s2.equals("."))	
					mtxVec.add(str); 
			}
			stmt.close();                                                                                                                      
			con.close();      
		} catch (SQLException e) {
			mtxVec.add("Could not connect to the database");
			System.out.println("Could not connect to the database 4 - "+e.getMessage());
		}
		return mtxVec;
	}
	
	public int updateStm(	
			String server,
			String username,
			String password,
			String db,
			String stm			){
		
		Connection con=null;
		
			con = getSqlConn(server,username,password,db,"dbo.");
	
		
		if(con==null)	return -2; 	//hagay-con=null
		
		try {
			Statement statement = con.createStatement();
			int changed = statement.executeUpdate(stm);
			return changed;
		} catch (SQLException e) {
			System.out.println("Could not connect to the database 5- "+e.getMessage());
			return -2;
		}
	}

	public String updateI5os(	String server,
			String username,
			String password,
			String schema,
			String stm			){
Connection con =getI5osConn( server, username, password, schema);
try {
Statement statement = con.createStatement();
statement.executeUpdate(stm);
} catch (SQLException e) {
System.out.println("Could not connect to the database - "+e.getMessage());
return "Could not connect to the database - "+e.getMessage();
}
return "updated";
}
	
	public     void 	    runSqlExec(HagRc hagRc , String sqlServer, String user, String pass, String db,String smt) {
		
	
		Connection con = getSqlConn(sqlServer,user,pass,db,"RI.");
	
		if(con==null) {
			hagRc.log.add("failed to create SQL connection to sqlServer="+sqlServer+" db="+db+" with user="+user);
			hagRc.rc=1;
			return;
		}
		
		Statement statement = null;
		hagRc.log.add("runSqlExec stm="+smt);
		int rc1=-1;
		
		try {
			statement = con.createStatement();
			rc1 = statement.executeUpdate(smt);
			hagRc.log.add("runSqlExec on: sqlServer="+sqlServer+" DB="+db);
			hagRc.log.add("0~"+ rc1+" rows updated.\n");
			hagRc.rc=0;
		} catch (SQLException e) {
			hagRc.log.add("1~Failed to run SQL Statement: - " + e.getMessage());
			StringBuilder ffStr = new StringBuilder("\nsmt=").append(smt).append("\nrc=").append(rc1).append("\nFailed to run SQL Statement: - ")
					.append(e.getMessage());
			hagRc.log.add(ffStr.toString()+"\n");
			hagRc.rc=1;
		}finally{
			try {
				if(statement!=null) 
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}    
			try {
				if(con!=null) 
					con.close();  
			} catch (SQLException e) {
				e.printStackTrace();
			}                 
		}
		return;
	}
	public 		String 	selectCount(HagRc hagRc ,String server,	String username,String password,String db,String table){
		String stm = "select count(*) from "+table;
		Connection con = getSqlConn(server,username,password,db,"RI.");
		Statement stmt = null;
		if(con==null) {
			hagRc.log.add("failed to create SQL connection to sqlServer="+server+" db="+db+" with user="+username);
			hagRc.rc=1;
			return null;
		}
		
		HagStringList mtxVec	= new HagStringList();
		try {
			stmt = con.createStatement();     
			ResultSet rs;
			rs = stmt.executeQuery(stm);                                                                                           
			while (rs.next())  {
			//	HagFinalDebug.p(rs.getString(1));
				mtxVec.add(rs.getString(1)); 
			}
			
		} catch (SQLException e) {
			hagRc.log.add(e.getMessage());
			hagRc.rc=1;
		//	HagFinalDebug.p(e.getMessage());
			mtxVec.add(e.getMessage());
		}finally{
			try {
				if(stmt!=null) 
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}    
			try {
				if(con!=null) 
					con.close();  
			} catch (SQLException e) {
				e.printStackTrace();
			}                 
			 
		}
		if(mtxVec.size()!=1)
			return "-"+mtxVec.size();
		return mtxVec.get(0);
	}
	public 	void 		runSqlGroupDDC(HagRc hagRc,String ddcFolder, 
			String sqlServer,String user,String pass,String db)
			 {
	
		// co0
			String dropTable ="drop table RI.CO0";
			runSqlExec( hagRc ,  sqlServer,  user,  pass, db,dropTable);
			if(hagRc.rc!=0)
				hagRc.rc=0;
			
			HagStringList list = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\sql\\create_co0.sql",true,"*",false);
			String createTable =list.convertToString(" ");
			runSqlExec( hagRc ,  sqlServer,  user,  pass, db,createTable);
			if(hagRc.rc!=0)
				hagRc.rc=0;
			
			String rowCount1 = selectCount( hagRc , sqlServer,	user,pass, db,"RI.CO0");
			hagRc.log.add("CO0 row count before bulkInsert="+rowCount1);
			
			String bulkInsert = "BULK INSERT [RI].[CO0] FROM '"+ddcFolder+"CO0.DDC' WITH (FORMATFILE = '"+ddcFolder+"CO0.FMT')";
			runSqlExec( hagRc ,  sqlServer,  user,  pass, db,bulkInsert);
			if(hagRc.rc!=0)
				return;
			
			String rowCount2 = selectCount( hagRc , sqlServer,	user,pass, db,"RI.CO0");
			hagRc.log.add("CO0 row count after bulkInsert="+rowCount2);
			if(hagRc.rc!=0)
				return;
	
		// co1
			String dropTable2 ="drop table RI.CO1";
			runSqlExec( hagRc ,  sqlServer,  user,  pass, db,dropTable2);
			if(hagRc.rc!=0)
				return;

			HagStringList list2 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\sql\\create_co1.sql",true,"*",false);
			String createTable2 =list2.convertToString(" ");
			runSqlExec( hagRc ,  sqlServer,  user,  pass, db,createTable2);
			if(hagRc.rc!=0)
				hagRc.rc=0;

			String rowCount21 = selectCount( hagRc , sqlServer,	user,pass, db,"RI.CO1");
			hagRc.log.add("CO1 row count before bulkInsert="+rowCount21);
			
			String bulkInsert2 = "BULK INSERT [RI].[CO1] FROM '"+ddcFolder+"CO1.DDC' WITH (FORMATFILE = '"+ddcFolder+"CO1.FMT')";
			runSqlExec( hagRc ,  sqlServer,  user,  pass, db,bulkInsert2);
			if(hagRc.rc!=0)
				return;
			
			String rowCount22 = selectCount( hagRc , sqlServer,	user,pass, db,"RI.CO1");
			hagRc.log.add("CO0 row count after bulkInsert="+rowCount22);
			if(hagRc.rc!=0)
				return;
		

		// cat
		
			String dropTable3 ="drop table RI.CAT";
			runSqlExec( hagRc ,  sqlServer,  user,  pass, db,dropTable3);
			if(hagRc.rc!=0)
				return;

			HagStringList list3 = new HagStringList("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\cfg\\cfgMenuWeb\\sql\\create_cat.sql",true,"*",false);
			String createTable3 =list3.convertToString(" ");
			runSqlExec( hagRc ,  sqlServer,  user,  pass, db,createTable3);
			if(hagRc.rc!=0)
				hagRc.rc=0;

			String rowCount31 = selectCount( hagRc , sqlServer,	user,pass, db,"RI.CAT");
			hagRc.log.add("CAT row count before bulkInsert="+rowCount31);
			
			String bulkInsert3 = "BULK INSERT [RI].[CAT] FROM '"+ddcFolder+"CAT.DDC' WITH (FORMATFILE = '"+ddcFolder+"CAT.FMT')";
			runSqlExec( hagRc ,  sqlServer,  user,  pass, db,bulkInsert3);
			if(hagRc.rc!=0)
				return;
			
			String rowCount32 = selectCount( hagRc , sqlServer,	user,pass, db,"RI.CAT");
			hagRc.log.add("CAT row count after bulkInsert="+rowCount32);
			if(hagRc.rc!=0)
				return;

		return ;
	}

	
	
	public static final String  			insertInto(
			String sqlServer,
			String user,
			String password,
			String database,
			String tableName,
			String[] vals
			){
		String ans="9~init";
		StringBuilder stm= new StringBuilder("INSERT INTO "+tableName+" values(");
		for (int i = 0 ; i < vals.length;i++) {
			if(i>0)
				stm.append(",");
			stm.append(vals[i]);
		}
		stm.append(")")		;
		String stmS= stm.toString();
		String url="jdbc:sqlserver://"+sqlServer+";Database="+database;
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		Connection con = null;
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, user,password);
			con.createStatement().execute(stm.toString());
		 	con.commit();
			ans="0~done";
			
		} catch (ClassNotFoundException e) {
			ans="1~"+e.getMessage();
		} catch (SQLException e) {
			ans="2~"+e.getMessage();
		} catch (Exception e) {
			ans="3~"+e.getMessage();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ans;
	}
}