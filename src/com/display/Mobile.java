package com.display;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.entities.Account;

public class Mobile {
	
	static public Account display(Account acc, Scanner sc){
		boolean valid = false;
		int number;
		while(!valid) {
			System.out.println("Mobile No:");
			try {
				number = sc.nextInt();
				System.out.println("debug");
				if(number>=80000000 && number<=99999999) {
				acc.setMobile(number);
				valid = true;
				}else {
					System.out.println("Invalid mobile number");
				}
			}catch(InputMismatchException e) {
				System.out.println("Please click in only number");
				
			}
			sc.nextLine();
		}
		return acc;
	}
}
