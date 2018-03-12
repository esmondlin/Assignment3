package com.display;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.entities.Account;

public class Email{
	private static Pattern pattern;
    private static Matcher matcher;
   
 
    private static final String EMAIL_PATTERN = 
    		"^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";	

    static private boolean validate(final String hex) {
    pattern = Pattern.compile(EMAIL_PATTERN);
    matcher = pattern.matcher(hex);
    return matcher.matches();
    }
    
    static public Account display(Account acc, Scanner sc){
    	String email;
    	boolean valid = false;
    	while(!valid) {
    		System.out.println("Email:");
    		email = sc.next();
    		if(Email.validate(email)) {
    			acc.setEmail(email);
    			valid = true;
    		}else {
    		System.out.println("Invalid email address. Please Enter again");
    		}
    	}
    	return acc;
    }
}
