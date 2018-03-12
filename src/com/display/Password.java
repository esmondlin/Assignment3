package com.display;

import java.util.Scanner;

import com.entities.Account;

public class Password {
	
	static public Account display(Account acc, Scanner sc){
		String TempPw;
		String RetypePw;
		boolean valid = false;
	while(!valid) {
		System.out.println("Temp Password:");
		TempPw = sc.next();
		System.out.println("Retype Password: ");
		RetypePw = sc.next();
			if(TempPw.matches(RetypePw)) {
				acc.setPassword(TempPw);
				valid = true;
			}else {
			System.out.println("Temp and Retype password different");
			}
		}
		return acc;
	}
	
	static public Account autoGenerate(Account acc) {
		String nric = acc.getNric();
		String mobile = Integer.toString(acc.getMobile());
		int password = Password.generatePW(nric,mobile);
		acc.setPassword(Integer.toString(password));
		return acc;
	}
	
	static private int generatePW(String nric, String mobile) {
	
		String password = new StringBuilder().append(nric.charAt(1))
                .append(nric.charAt(2))
                .append(nric.charAt(3))
                .append(nric.charAt(4))
                .append(mobile.charAt(4))
                .append(mobile.charAt(5))
                .append(mobile.charAt(6))
                .append(mobile.charAt(7)).toString();
		int pw =Integer.parseInt(password);
		return pw;
	}
}
