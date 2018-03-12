package com.display;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.entities.Account;

public class Nric {
	private static Pattern pattern;
    private static Matcher matcher;
   
 
    private static final String Nric_PATTERN = 
    		"^[STFG]\\d{7}[A-Z]$";	

    static private boolean validate(final String hex) {
    pattern = Pattern.compile(Nric_PATTERN);
    matcher = pattern.matcher(hex);
    return matcher.matches();
    }
    
    static public Account display(Account acc, Scanner sc){
    	String nric;
    	boolean valid = false;
    	while(!valid) {
    		System.out.println("Nric:");
    		nric = sc.next();
    		if(Nric.validate(nric)) {
    			acc.setNric(nric);
    			valid = true;
    		}else {
    		System.out.println("Invalid Nric. Please Enter again");
    		}
    	}
    	return acc;
    }
}
