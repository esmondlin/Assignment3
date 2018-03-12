package com.display;

import java.util.Scanner;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

import com.entities.Account;

public class DOB {
//	private static Pattern pattern;
 //   private static Matcher matcher;
   
 
    private static final String DOB_PATTERN = 
    		"(^(((0[1-9]|1[0-9]|2[0-8])[\\/](0[1-9]|1[012]))|((29|30|31)[\\/](0[13578]|1[02]))|((29|30)[\\/](0[4,6,9]|11)))[\\/](19|[2-9][0-9])\\d\\d$)|(^29[\\\\/]02[\\\\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)";	

    static private boolean validate(final String hex) {
    	if(hex.matches(DOB_PATTERN)) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    static public Account display(Account acc, Scanner sc){
    	String dob;
    	boolean valid = false;
    	while(!valid) {
    		System.out.println("DOB (DD/MM/YYYY):");
    		dob = sc.next();
    		if(DOB.validate(dob)) {
    			acc.setDOB(dob);
    			valid = true;
    		}else {
    		System.out.println("Invalid DOB. Please Enter again");
    		}
    	}
    	return acc;
    }
}
