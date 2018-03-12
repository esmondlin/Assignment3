package com.display;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.entities.Account;

public class Name {
	private static Pattern pattern;
    private static Matcher matcher;
   
 
    private static final String Name_PATTERN = 
    		"([a-zA-Z]+\\s)*[a-zA-Z]*";	

    static private boolean validate(final String hex) {
    pattern = Pattern.compile(Name_PATTERN);
    matcher = pattern.matcher(hex);
    return matcher.matches();
    }
    
    static public Account display(Account acc, Scanner sc){
    	String name;
    	boolean valid = false;
    	while(!valid) {
    		System.out.print("Name:");
    		name = sc.nextLine();
    		if(Name.validate(name)) {
    			acc.setName(name);
    			valid = true;
    		}else {
    		System.out.println("Invalid Name. Please Enter again");
    		}
    	}
    	sc.nextLine();
    	return acc;
    }
}
