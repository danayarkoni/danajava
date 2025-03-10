package com.hag.hagay;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HagRiRel {
	boolean rc 				= true;
	String bisVer		    ="x";
	String bisMaint		    ="x";
	String extraDev		    ="x";
	String ddcType		    ="x";
	String topackFolder		="x";
	String as400Server     	="x";
	String currentRel		="x";
	String riVer			="x";
//	String riVer1			="x";
//	String riVer2			="x";
	String riVerJira		="x";
	String rsFolder			="x";
	
	//dev
	String bisServerDev		="x";
	String sqlServerDev		="x";
	String devDbid			="x";
	String devBatchName  	="x";
	String dbDev			="x";
	String iomDev			="x";
	String iomUncDev		="x";
	String rijavaUncDev     ="x";
	String dbfLocDev		="x";
	String dblogLocDev		="x";
	String m00LibDev		="x";
	String configUncDev     ="x";	

	String tasksTable		="x"; 
	
	//int
	String bisServerInt		="x";
	String sqlServerInt		="x";
	String intDbid			="x";
	String intBatchName  	="x";
	String dbInt			="x";
	String iomInt	   	 	="x";
	String rijavaUncInt		="x";
	String configUncInt  	="x";
	String iomUncInt		="x";
	String dbfLocInt		="x";
	String dblogLocInt		="x";
	String bkupInt			="x";
	String bkupIomUncInt	="x";
	String bkupJavaUncInt	="x";
	String bkupLogUncInt	="x";
	String bkupDbUncInt		="x";
	String cmInstallerUncInt="x";
	String portingUnc      	="x";
	String m00LibInt		="x"; 
	
	String instEnvs			="x";
	String instEnvsNew		="x";
	String instEnvsI5os			="x";
	
	
	String bkupDev			="x";
	public HagRiRel(String ver){
		Properties prop = new Properties();
		try {
			//System.out.println("*"+ver+"*");
			//prop.load(new FileInputStream("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\list\\riRel.properties"));
			prop.load(new FileInputStream("\\\\HYUNDAI.SAPIENS.COM\\HYUN01log\\closed_patches\\config\\cfgMenu\\bin\\list\\riRel_properties\\"+ver+".txt"));
			this.bisVer						=		prop.getProperty(ver+"_bisVer").trim();
			this.bisMaint					=		prop.getProperty(ver+"_bisMaint").trim();
			this.extraDev					=		prop.getProperty(ver+"_extraDev").trim();
			this.ddcType					=		prop.getProperty(ver+"_ddcType").trim();
			this.currentRel					=		prop.getProperty(ver+"_currentRel").trim();
			this.as400Server   			 	=		prop.getProperty(ver+"_as400Server").trim();
			this.riVerJira					=		prop.getProperty(ver+"_jiraVer").trim();
			//dev - to change
			this.devDbid					=		prop.getProperty(ver+"_devDbid").trim();
			this.devBatchName				=		prop.getProperty(ver+"_devBatchName").trim();
			this.bisServerDev				=		prop.getProperty(ver+"_bisServerDev").trim();
			this.sqlServerDev				=		prop.getProperty(ver+"_sqlServerDev").trim();
			this.dbfLocDev					=		prop.getProperty(ver+"_dbfLocDev").trim();
			this.dblogLocDev				=		prop.getProperty(ver+"_dblogLocDev").trim();
			//int - to change
			this.intDbid					=		prop.getProperty(ver+"_intDbid").trim();
			this.intBatchName				=		prop.getProperty(ver+"_intBatchName").trim();
			this.bisServerInt				=		prop.getProperty(ver+"_bisServerInt").trim();
			this.sqlServerInt				=		prop.getProperty(ver+"_sqlServerInt").trim();
			this.dbfLocInt					=		prop.getProperty(ver+"_dbfLocInt").trim();
			this.dblogLocInt				=		prop.getProperty(ver+"_dblogLocInt").trim();
			//
			//this.riVer1						=		prop.getProperty(ver+"_riVer1").trim();                                                                                      
			//this.riVer2						=		prop.getProperty(ver+"_riVer2").trim();                                                                                 
			this.riVer						=		prop.getProperty(ver+"_riVer").trim();                                                                                             
			this.topackFolder				=		prop.getProperty(ver+"_topackFolder").trim();                                                                            
			this.rsFolder					=		prop.getProperty(ver+"_rsFolder").trim();
			this.m00LibDev		  			=		prop.getProperty(ver+"_m00LibDev").trim();                                                            
			this.dbDev		    			=		prop.getProperty(ver+"_dbDev").trim();                                                                
			this.iomDev						=		prop.getProperty(ver+"_iomDev").trim();  
			this.iomUncDev					=		prop.getProperty(ver+"_iomUncDev").trim();     
			this.rijavaUncDev				=		prop.getProperty(ver+"_rijavaUncDev").trim();                                                   
			this.tasksTable					=		prop.getProperty(ver+"_tasksTable").trim();         
			this.dbInt  					=		prop.getProperty(ver+"_dbInt").trim();                                                               
			this.m00LibInt			  		=		prop.getProperty(ver+"_m00LibInt").trim();                                                             
			this.iomInt						=		prop.getProperty(ver+"_iomInt").trim();                                                 
			this.bkupInt	     			=		prop.getProperty(ver+"_bkupInt").trim();    
			this.bkupDev                    =       this.bkupInt+"_dev";
			this.configUncDev 			    =	 	prop.getProperty(ver+"_configUncDev").trim();
		//	this.bkupIomUncInt				=		prop.getProperty(ver+"_bkupIomUncInt").trim();                                    
		//	this.bkupJavaUncInt				=		prop.getProperty(ver+"_bkupJavaUncInt").trim();                        
		//	this.bkupLogUncInt				=		prop.getProperty(ver+"_bkupLogUncInt").trim();                           
		//	this.bkupDbUncInt				=		prop.getProperty(ver+"_bkupDbUncInt").trim();                        
			this.iomUncInt					=		prop.getProperty(ver+"_iomUncInt").trim();                      
			this.rijavaUncInt				=		prop.getProperty(ver+"_rijavaUncInt").trim();                                     
			this.configUncInt				=		prop.getProperty(ver+"_configUncInt").trim();                               
			this.cmInstallerUncInt			=		prop.getProperty(ver+"_cmInstallerUncInt").trim();                                 
			this.portingUnc					=		prop.getProperty(ver+"_portingUnc").trim();                                     
			this.instEnvs					=		prop.getProperty(ver+"_instEnvs").trim();
			this.instEnvsNew				=		prop.getProperty(ver+"_instEnvsNew").trim();
			this.instEnvsI5os				=		prop.getProperty(ver+"_instEnvsI5os").trim();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public boolean getRC() {				return rc;					}
	public String getBisVer() {				return bisVer;				}
	public String getBisMaint() {			return bisMaint;			}
	public String getExtraDev() {			return extraDev;			}
	public String getDdcType() {			return ddcType;				}
	public String getTopackFolder() {		return topackFolder;		}
	public String getCmInstallerUncInt() {	return cmInstallerUncInt;	}
	public String getAs400Server() {		return as400Server;			}
	public String getCurrentRel() {			return currentRel;			}
	public String getM00LibInt() {			return m00LibInt;			}
	public String getM00LibDev() {			return m00LibDev;			}
	public String getPortingUnc() {			return portingUnc;			}
	public String getBkupInt() {			return bkupInt;				}
	public String getBkupDev() {			return bkupDev;				}
	public String getConfigUncDev() {		return configUncDev;		}

	//public String getBkupIomUncInt() {		return bkupIomUncInt;		}
	//public String getBkupJavaUncInt() {		return bkupJavaUncInt;		}
	//public String getBkupLogUncInt() {		return bkupLogUncInt;		}
	//public String getBkupDbUncInt() {		return bkupDbUncInt;		}
	public String getBkupIomUncInt() {		return bkupInt+"\\"+m00LibInt+"_M"+currentRel+".zip";		}
	public String getBkupJavaUncInt() {		return bkupInt+"\\RIjava_"+intBatchName+"_M"+currentRel+".zip";	}
	public String getBkupLogUncInt() {		return bkupInt+"\\RIlog_"+intBatchName+"_M"+currentRel+".zip";	}
	public String getBkupDbUncInt() {		return bkupInt+"\\"+dbInt+"_M"+currentRel+".bk";			}

	public String getBkupIomUncDev() {		return bkupDev+"\\"+m00LibDev+"_M"+currentRel+".zip";		}
	public String getBkupJavaUncDev() {		return bkupDev+"\\RIjava_"+devBatchName+"_M"+currentRel+".zip";	}
	public String getBkupLogUncDev() {		return bkupDev+"\\RIlog_"+devBatchName+"_M"+currentRel+".zip";	}
	public String getBkupDbUncDev() {		return bkupDev+"\\"+dbDev+"_M"+currentRel+".bk";			}

	
	public String getRijavaUncInt() {		return rijavaUncInt;		}
	public String getConfigUncInt() {		return configUncInt;		}
	public String getRiVer() {				return riVer;				}
	public String getRsFolder() {			return rsFolder;			}
	public String getRiVerJira() {		
		return riVerJira;			
		}
	public String getBisServerDev() {		return bisServerDev;		}
	public String getSqlServerDev() {		return sqlServerDev;		}
	public String getDevDbid() {	    	return devDbid;	            }
	public String getDevBatchName() {		return devBatchName;	    }
	public String getDbDev() {				return dbDev;				}
	public String getIomDev() {				return iomDev;				}
	public String getIomUncDev() {			return iomUncDev;			}
	public String getRijavaUncDev() {		return rijavaUncDev;		}
	public String getDbfLocDev() {			return dbfLocDev;			}
	public String getDblogLocDev() {		return dblogLocDev;			}
	public String getTasksTable() {			return tasksTable;  		}
	public String getBisServerInt() {		return bisServerInt;		}
	public String getSqlServerInt() {		return sqlServerInt;		}
	public String getIntDbid() {			return intDbid;				}
	public String getIntBatchName() {		return intBatchName;		}
	public String getDbInt() {				return dbInt;				}
	public String getIomInt() {				return iomInt;				}
	public String getIomUncInt() {			return iomUncInt;			}
	public String getDbfLocInt() {			return dbfLocInt;			}
	public String getDblogLocInt() {		return dblogLocInt;			}
	public String getInstEnvs() {			return instEnvs;			}
	public String getInstEnvsNew() {		return instEnvsNew;			}
	public String getInstEnvsI5os() {		return instEnvsI5os;		}
}
