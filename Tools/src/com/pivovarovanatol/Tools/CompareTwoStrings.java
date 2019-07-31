package com.pivovarovanatol.Tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class CompareTwoStrings {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
    	Instant programStart = Instant.now();
    	
        File file1 = new File("C:\\Users\\i855719\\git\\Tools\\Tools\\src\\com\\pivovarovanatol\\Tools\\data.txt");
        Scanner scanner = new Scanner(file1);
        
        File file2 = new File("C:\\Users\\i855719\\git\\Tools\\Tools\\src\\com\\pivovarovanatol\\Tools\\data1.txt");
        Scanner scanner2 = new Scanner(file2);

        String str1 = scanner.nextLine();
        String str2 = scanner2.nextLine();

        compareStrings(str1, str2);
        
        scanner.close();
        scanner2.close();

        Instant programEnd = Instant.now();
        Duration timeElapsed = Duration.between(programStart, programEnd);
        System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");
		
	}
	
	
	public static void compareStrings(String str1, String str2) {
		
		int n1=str1.length();
		int n2=str2.length();
		
		if (n1!=n2) {
			System.out.println("Strings are NOT the same! Different strings lenght! " + n1 + " " + n2);
			return;
		}
		
		char[] arr1 = str1.toCharArray();
		char[] arr2 = str2.toCharArray();
		int count = 0;
		
		for (int i=0;i<n1;i++) {
			if (arr1[i]!=arr2[i]) {
				System.out.println("Difference is found at position: " + i + ". Values are " + arr1[i] + " <--> " + arr2[i]); 
				count++;
			}
		}
		
		if (count==0) {
			System.out.println("Strings are identical!");
		}
		else {
			System.out.println("Strings are NOT identical. Number of discrepancies: " + count);
		}
	}

}
