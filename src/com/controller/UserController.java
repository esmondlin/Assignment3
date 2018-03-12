package com.controller;

import java.sql.SQLException;

import com.display.Display;
import com.entities.Account;

public class UserController {
	//Controller to direct display for all user.
	public static void checkOptionHomePage(String option) throws SQLException {
		
		switch (option) {
			//Select Login
		case "1": displayValidation(); 
				break;	
			//Select ForgetPassword	
		case "2": Account acc = new Account();
			do {
				acc = displayForgetPassword();
			}while(acc.isValid()); //Repeat only when LoginID is valid for forget password
				break;
		default: System.out.println("Please key in only 1 and 2"); 
			break;
		}//end of switch case
	}//end of check option
	
	static private void displayValidation() throws SQLException{
		Account acc = new Account();
		// check authentication and save all info to acc.
		acc = Display.displayLoginValidation(acc);
		//Check user is admin
		if(acc.getRole().equals("Admin")) { 
			System.out.println("Welcome Esmond");
			/*Stuck in AdminController unless it return false when user click log out.
		 	Direct the display according to the option on admin page*/
			while(!AdminController.checkOptionAdminPage(Display.printAdminJsp()));
			// Check user account is not block
		}else if(acc.getRole().equals("User") && acc.isBlocked()==0){
			//Check for firstLogin
			if(acc.getFirstLogin() ==1) {
			System.out.println("Welcome "+ acc.getName());
			}else {
				//Display firstlogin function
				Display.displayFirstLogin(acc);
			}
		}else {
			// Display account block
			System.out.println("Your account is blocked");
		}
	}
	
	static private Account displayForgetPassword() throws SQLException {
		Account acc = new Account();
		//Check LoginID is valid in SQL
		acc = Display.displayForgetPassword(acc);
		
		if(acc.isValid()) {
			//Reset Password and update SQL
			acc = Display.displayReenterIDPW(acc);
			acc.setValid(false);
			
		}else {
			acc.setValid(true);
		}
		return acc;
	}
	
	public static Account pickSecurityQuestion(Account acc, String option) {
		switch (option) {
		case"A": acc.setSecurityQuestion("Favourite Colour");
				acc.setValid(true);
		break;
		case"B": acc.setSecurityQuestion("Favourite Drink");
				acc.setValid(true);
		break;
		case"C": acc.setSecurityQuestion("Favourite Place");
				acc.setValid(true);
		break;
		default: System.out.println("Please pick only A,B,C");
				acc.setValid(false);
		break;
		}
		return acc;
	}
}
