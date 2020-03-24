package com.pivovarovanatol.Tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SplitFilePerLine {
/* Class to split file by lines. Useful for long-lines files, like SQL trace with lots of long TREX calls, which make the Npp die on opening those.  
 * 
 */
	
	public static void main(String[] args) {
		String inputFileName = "C:\\Temp\\123\\trex_calls.txt";
		String outputFileNameMask = "C:\\\\Temp\\\\123\\\\trex_call";
		
		BufferedReader reader;
		BufferedWriter writer;
		int i=0;
		
		try {
			reader = new BufferedReader(new FileReader(inputFileName));
			String line;
			line = reader.readLine();
			while (line != null) {
				writer = new BufferedWriter(new FileWriter(outputFileNameMask + i + ".txt"));
				//System.out.println(line);
				writer.write(line);
				// read next line
				line = reader.readLine();
				i++;
				writer.close();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	
	}
}
