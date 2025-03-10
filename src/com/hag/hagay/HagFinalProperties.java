package com.hag.hagay;

import java.awt.Color;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public final class HagFinalProperties {
	static int 	widthD 		= 0;
	static int 	heightD 	= 0;
	static int 	widthX 		= 0;
	static int 	heightX 	= 0;
	static int 	widthL 		= 0;
	static int 	heightL 	= 0;
	static int 	widthS 		= 0;
	static int 	heightS 	= 0;

	static int 	locationX 	= 0;
	static int 	locationY 	= 0;
	static int 	tabInd 		= 0;
	static int 	parmsVarWidth 			= 200;
	static int 	parmsCompWidth 			= 200;
	static int 	parmsButWidth 			= 200;
	static int 	parmsTooltipWidth 		= 200;
	static int 	parmsCompHeight 		= 30; 

	

	static String 	tabTry1Title 	= "KUKU";
	static String 	tabRiTitle 		= "KUKU";
	static String 	tabCmTitle 		= "KUKU";
	
	static String 	tabTry1Icon 	= "KUKU";
	static String 	tabRiIcon 		= "KUKU";
	static String 	tabCmIcon 		= "KUKU";
	
	
	static Font 			    CourierNew20p			= new Font( "Courier New", Font.PLAIN, 20 );
	static Font 			    CourierNew18p			= new Font( "Courier New", Font.PLAIN, 18 );
	static Font 			    CourierNew16p			= new Font( "Courier New", Font.PLAIN, 16 );
	
	static Font 			    CourierNew16b			= new Font( "Courier New", Font.BOLD, 16 );
	static Font 			    CourierNew12b			= new Font( "Courier New", Font.BOLD, 12 );
	
	static Font 			    font20			= new Font( "Ariel", Font.PLAIN, 20 );
	static Font 			    font18			= new Font( "Ariel", Font.PLAIN, 18 );
	static Font 			    font16			= new Font( "Ariel", Font.PLAIN, 16 );
	static Font 			    font14			= new Font( "Ariel", Font.PLAIN, 14 );
	
	static Color                colorG          = new Color(0,124,0);
	static Color tab1Color = new Color(200,200,220);
	static Color tab2Color = new Color(200,220,200);
	static Color generatorButColor 		= new Color(100,155,100);
	static Color generatorDescColor		= new Color(150,205,150);
	static Color generatorPanelColor 	= new Color(200,255,200);
	static Color actionButColor 		= new Color(100,120,200);
	static Color actionLogsColor 		= new Color(123,169,255);
	static Color actionPanelColor 		= new Color(206,223,235);
	static Color previewPanelColor 		= new Color(206,223,244);
	
	
	
	static Color tab3Color = new Color(220,200,200);
	static Color workerColor = new Color(250,250,178);
	static HagStringList serversList = null;
	static HagStringList errorsList = null;
	//private void HagFinalProperties(){
	//	loadDisplay();
	//}
	public static final void loadDisplay(){
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("config\\lists\\display.txt"));
			//HagProperties.width=1200;
			widthD					=	Integer.parseInt(prop.getProperty("widthD").trim());
			heightD					=	Integer.parseInt(prop.getProperty("heightD").trim());
			widthX					=	Integer.parseInt(prop.getProperty("widthX").trim());
			heightX					=	Integer.parseInt(prop.getProperty("heightX").trim());
			widthL					=	Integer.parseInt(prop.getProperty("widthL").trim());
			heightL					=	Integer.parseInt(prop.getProperty("heightL").trim());
			widthS					=	Integer.parseInt(prop.getProperty("widthS").trim());
			heightS					=	Integer.parseInt(prop.getProperty("heightS").trim());
			locationX				=	Integer.parseInt(prop.getProperty("locationX").trim());
			locationY				=	Integer.parseInt(prop.getProperty("locationY").trim());
			tabInd					=	Integer.parseInt(prop.getProperty("tabInd").trim());
			parmsVarWidth			=	Integer.parseInt(prop.getProperty("parmsVarWidth").trim());
			parmsCompWidth			=	Integer.parseInt(prop.getProperty("parmsCompWidth").trim());
			parmsButWidth			=	Integer.parseInt(prop.getProperty("parmsButWidth").trim());
			parmsTooltipWidth		=	Integer.parseInt(prop.getProperty("parmsTooltipWidth").trim());
			parmsCompHeight			=	Integer.parseInt(prop.getProperty("parmsCompHeight").trim());
			
			
			
			tabRiTitle				=	prop.getProperty("tabRiTitle").trim();
			tabCmTitle				=	prop.getProperty("tabCmTitle").trim();
			
			tabRiIcon				=	prop.getProperty("tabRiIcon").trim();
			tabCmIcon				=	prop.getProperty("tabCmIcon").trim();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	public static final void loadLists(){
		serversList = new HagStringList("config\\lists\\allServers.list",true,"*",false);
		errorsList = new HagStringList("config\\lists\\errors.list",true,"*",false);
	}
	
	public static final String getVal(String file,String var){
		Properties prop1 = new Properties();
		try {
		//	HagFinalDebug.p2(file);
		//	HagFinalDebug.p2(var);
			
			prop1.load(new FileInputStream(file));
			String dd = prop1.getProperty(var);
			if(dd==null)
				return null;
			String dd1 = dd.trim();
			return dd1;
		} catch (NullPointerException npe) {
			npe.printStackTrace();
			return null;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	public static final String setVal(String file,String var,String val){
		Properties prop1 = new Properties();
		try {
		//	HagFinalDebug.p2(file);
		//	HagFinalDebug.p2(var);
			
			prop1.load(new FileInputStream(file));
			prop1.setProperty(var, val);
			prop1.store(new FileOutputStream(file), null);
			return "0~"+file+" "+var+" value changed to "+val;
		} catch (NullPointerException npe) {
			npe.printStackTrace();
			return "1~Failed to set "+var+" value to "+val+" in "+file+" - error="+npe.getMessage();
		//	return null;
		} catch (IOException ex) {
			ex.printStackTrace();
			return "2~Failed to set "+var+" value to "+val+" in "+file+" - error="+ex.getMessage();
			//return null;
		}
	}
}
