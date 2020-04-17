package com.pivovarovanatol.Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

// submitted and accepted for all three tests. 
public class RecreateMultipleExportFolders {
    public static void solve(Scanner input, int caseNum, int n, String[] words, int maxLen) {

    }

    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        System.out.println("Copying file " + source + " to " + dest);
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
    
    static void modifyFile(String filePath, String oldString, String newString) {
        File fileToBeModified = new File(filePath);
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            //Reading all the lines of input text file into oldContent
            String line = reader.readLine();
            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            //Replacing oldString with newString in the oldContent
            String newContent = oldContent.replaceAll(oldString, newString);
            //Rewriting the input text file with newContent
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                //Closing the resources
                reader.close();
                writer.close();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String args[]) {
    		
    		String path = "C:\\Temp\\test\\";
    	
 			BufferedReader reader;
 			try {
 				reader = new BufferedReader(new FileReader("C:\\Users\\i855719\\git\\Tools\\Tools\\src\\com\\pivovarovanatol\\Tools\\input.txt"));
 				String line = reader.readLine();
 				while (line != null) {
 					//System.out.println(path + line);
 					
				    //Creating a File object
				    File file = new File(path + line);
				    //Creating the directory
				    boolean bool = file.mkdir();
				      
				    File dir = new File("C:\\Temp\\test\\bw2hana%2fSAPHANADB__SYS_BIC_ZPLNI0004_XXXX\\");
				    File[] directoryListing = dir.listFiles();
				    if (directoryListing != null) {
				      for (File fromFile : directoryListing) {
				        // Do something with child
				    	  String newPath = path + line + "\\" + fromFile.getName();
				    	  File toFile =  new File(newPath);
				    	  copyFileUsingStream(fromFile, toFile);
				    	  
				      }
				    } 
				    
				    // Modify creates create.sql (replace analytic privilege name)
				    String privName = line.replace("%2f", "/");
				    modifyFile(file.getAbsolutePath() + "//" + "create.sql", "XXXXX", privName);
	
				    // read next line
				    line = reader.readLine();
 				}
 				reader.close();
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
    }
}
