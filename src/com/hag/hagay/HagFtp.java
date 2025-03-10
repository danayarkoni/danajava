package com.hag.hagay;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public  final class HagFtp {

        /**
         * @param args
         */
        public static String put(	String toServer,
        							String username,
        							String password,
        							String targetDir,
        							String inputFileWithPath,
        							String type		) {
        		StringBuilder ans = new StringBuilder();
        		if(!type.equalsIgnoreCase("BIN") && !type.equalsIgnoreCase("TXT")){
        			ans.append(type).append("- type unknown.").append("\n");
        			return "1~ftp\n"+ans.toString();
        		}
        		
        		FTPClient ftp = new FTPClient();
                InetAddress addr = null;
                
                try {
                    addr = InetAddress.getByName(toServer);
                  
                } catch (UnknownHostException ex) {
                        ans.append(toServer).append(" - Host unknown.\n");
                    	return "1~ftp\n"+ans.toString();
                }

                try {
                        int reply;
                        ftp.connect(addr);
            			ans.append("Connected to ").append(addr).append(".\n");
            			ans.append(ftp.getReplyString()).append("\n");
                        reply = ftp.getReplyCode();

                        if (!FTPReply.isPositiveCompletion(reply)) {
                                ftp.disconnect();
                            	ans.append("FTP server refused connection.").append("\n");
                            	return "1~ftp\n"+ans.toString();
                        }

                        if (!ftp.login(username, password)) {
                                ans.append(ftp.getReplyString()).append("\n");
                                ftp.logout();
                        } else {
                        		ans.append(ftp.getReplyString()).append("\n");
                                if(type.equalsIgnoreCase("BIN"))
                                	ftp.setFileType(FTP.BINARY_FILE_TYPE);
                                else
                                 	ftp.setFileType(FTP.ASCII_FILE_TYPE);
                                 	
                                ans.append(ftp.getReplyString()).append("\n");
                                
                                ftp.enterLocalPassiveMode();
                                ans.append("Enter local passive mode.\n");

                             //   FTPFile[] files = ftp.listFiles("/");
                             //   System.out.print(ftp.getReplyString());
                              //  for (FTPFile ftpFile : files) {
                               //         System.out.println(ftpFile.getName() + " \t\t# " + ftpFile.getType() + " \t# "+ftpFile.getUser());
                                //}
                                
                                if(targetDir != null){
                                	boolean cd = ftp.changeWorkingDirectory(targetDir);
                                	ans.append(ftp.getReplyString()).append("\n");
                                	if (!cd) {
                                        boolean mkdir = ftp.makeDirectory(targetDir);
                                        ans.append(ftp.getReplyString()).append("\n");
                                        if (!mkdir) {
                                        	ans.append("Cannot create upload directory: ").append(targetDir).append(" at server: ").append(addr).append("\n");
                                        	return "1~ftp\n"+ans.toString();
                                        }
                                        cd = ftp.changeWorkingDirectory(targetDir);
                                	}
                                }
                                File uploadFile = new File(inputFileWithPath);
                                if (uploadFile.exists()) {
                                    	ans.append("Uploading file... ").append(uploadFile.getName()).append("\n");
                                        FileInputStream fis = new FileInputStream(uploadFile);
                                        ftp.storeFile(uploadFile.getName(), fis);
                                        fis.close();
                                    	ans.append(ftp.getReplyString()).append("\n");
                                        
                                } else {
                                        System.out.println("Not uploading file, because it doesn't exists.");
                                     	ans.append("Not uploading file, because it doesn't exists.\n");
                                }
                                
                                ftp.logout();
                            	ans.append(ftp.getReplyString()).append("\n");
                        }
                } catch (SocketException ex) {
                        //ex.printStackTrace();
                        ans.append("1~").append(ex.getMessage()).append("\n");
                } catch (IOException ex) {
                        if (ftp.isConnected()) {
                                try {
                                        ftp.disconnect();
                                        //System.out.print("Disconnected from server: " + addr);
                                        ans.append("1~").append("Disconnected from server: ").append(addr).append("\n");
                                } catch (IOException e) {
                                    ans.append("1~").append(e.getMessage()).append("\n");
                                }
                        }
                        //System.err.println("Couldn't connect to server: " + addr);
                        ans.append("1~Couldn't connect to server: ").append(addr).append("\n");
                        //ex.printStackTrace();
                        //System.exit(1);
                    	return "1~ftp\n"+ans.toString();
                } finally {
                        if (ftp.isConnected()) {
                                try {
                                        ftp.disconnect();
                                       // System.out.print("Disconnected from server: " + addr);
                                        ans.append("Disconnected from server: ").append(addr).append("\n");
                                } catch (IOException e) {
                                        // do nothing
                                    ans.append("1~").append(e.getMessage()).append("\n");
                                }
                        }
                       
                }
                return "0~ftp\n"+ans.toString();

          
        }

}