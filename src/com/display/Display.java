package com.display;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.controller.AdminController;
import com.controller.UserController;
import com.dao.AdminDAO;
import com.dao.UserDAO;
import com.entities.Account;

public class Display{
	
	private static Scanner sc = new Scanner(System.in);
	private static Account acc = new Account();
	private static UserDAO user = new UserDAO();
	private static AdminDAO admin = new AdminDAO();
	
	//Print First Page and return option
	static public String print1stJsp() {
		String option;
		System.out.println("1. Login");
		System.out.println("2. Forget Password");
		option = sc.next();
		return option;
	}// end of print1stJsp
	//Print Admin page and return option
	static public String printAdminJsp() {
		String option;
		System.out.println("1. Register New User");
		System.out.println("2. Show UserList");
		System.out.println("3. Logout");
		option = sc.next();
		return option;
	}//end of printAdminJsp
	// Account with all valid details
	static public Account showRegister() {
		acc = Name.display(acc,sc);
		acc = Nric.display(acc,sc);
		acc = DOB.display(acc,sc);
		acc = Email.display(acc,sc);
		acc = Mobile.display(acc, sc);
		acc = Password.autoGenerate(acc);
		acc.setRole("User");
		
		System.out.println("Name:"+ acc.getName());
		System.out.println("Nric:"+ acc.getNric());
		System.out.println("DOB:"+ acc.getDOB());
		System.out.println("Email:"+ acc.getEmail());
		System.out.println("Mobile:"+ acc.getMobile());
		System.out.println("Password:"+ acc.getPassword());
		boolean valid = false;
		while(!valid) {
			System.out.println("Continue Press 1 :");
			System.out.println("Restart Register Press 2 :");
			String option = sc.next();
			switch (option) {
			case "1" : acc.setValid(true);
						valid = true;
				break;
			case "2" : acc.setValid(false);
						valid = true;
				break;
			case "3" : System.out.println("Please enter only 1 or 2");
				valid = false;
				break;
			}//end of switch case
		}//end of while loop
		return acc;
	}//end of showRegister()
	
	// check login authentication
	public static Account displayLoginValidation(Account user1) throws SQLException {
		boolean valid1 = false;
		boolean valid2 = false;
		while(!valid1) {
			System.out.println("Login ID: ");
			String email = sc.next();
			if(user.loginIDValidation(email)) {
				return Display.displayPasswordValidation(user1, email);
			}else {
				admin.updateAttempts(user1);
				while(!valid2) {
					System.out.println("Try again, y/n:");
					String yn = sc.next();
					switch (yn) {
					case "y": valid2 =true;
							break;
					case "n": valid1 = true;
							break;
					default: System.out.println("Please click only y or n:");
						break;
					}//end of switch case
				}//end of while loop
			}//end of else loop
		}// end of while loop
		return null;
	}// end of displayloginvalidation
	
	//Check password authentication
	static private Account displayPasswordValidation(Account user1, String email) throws SQLException {
		boolean valid1 = false;
		boolean valid2 = false;
		while (!valid1) {
			System.out.println("Password: ");
			String password = sc.next();
			user1 = user.loginPasswordValidation(password, email);
			if(user1.isValid()) {
				if(email.equals("esmondlin@outlook.com") && password.equals("123")) {
					user1.setRole("Admin");
					return user1;
				}else {
					user1.setRole("User");
					admin.resetAttempts(user1);
					return user1;
				}
			}else {
				while(!valid2) {
					System.out.println("Try again, y/n:");
					String yn = sc.next();
					switch (yn) {
					case "y": valid2 =true;
							break;
					case "n": valid1 = true;
							break;
					default: System.out.println("Please click only y or n:");
						break;
					}//end of switch case
				}//end of while loop
			}//end of else loop
		}//end of while loop
		return null;
	}//end of displaypasswordvalidation
	
	// Display database
	public static void showAllUsers(List<Account> users) {
		System.out.println("User DataBase");
		for(Account user:users) {
			System.out.print(user.getAccId()+"\t"+user.getName()+"\t"+user.getLoginId());
			if(user.isBlocked()==1) {
				System.out.println("\t Blocked");
			}else {
				System.out.println("\t Unblocked");
			}
		}// end of for loop
	}// end of showAllUser()
	
	// return account of all Security Question and answer
	
	public static Account displayForgetPassword(Account acc) throws SQLException {
		System.out.println("LoginID: ");
		acc.setEmail(sc.next());
		acc = user.retrieveSecurityQuestion(acc); 
		if(acc.isValid()) {
			System.out.println("Security Question: " + acc.getSecurityQuestion());
			System.out.println("Security Answer: ");
			if(acc.getSecurityAnswer().equals(sc.next())) {
				System.out.println("Correct");
			}else {
				System.out.println("Incorrect");
			}
		}//end of if loop
		
		return acc;
	}// end of displayForgetPassword()
	
	//Display reenter password function
	public static Account displayReenterIDPW(Account acc) {
		String OldPw = acc.getPassword();
		System.out.println("New Password: ");
		String NewPw = sc.next();
		if(OldPw.equals(NewPw)) {
			acc.setValid(false);
			return acc;
		}
		System.out.println("Retyped Password: ");
		String RetypePw = sc.next();
		if(NewPw.equals(RetypePw)) {
			acc.setPassword(NewPw);
			user.updatePassword(acc);
			acc.setValid(true);
		}
		return acc;
	}
	//display first login function
	public static Account displayFirstLogin(Account acc) {
		System.out.println("Temp Password: ");
		if(acc.getPassword().equals(sc.next())) {
			do {
			acc = Display.displayReenterIDPW(acc);
			}while(!Display.displayReenterIDPW(acc).isValid());
			acc.setFirstLogin(1);
			user.updateFirstLogin(acc);
			do {
			acc = Display.displayPickSecurityQuestion(acc);
			}while(acc.isValid());
			user.updateSecurity(acc);
		}
		return acc;
	}
	//display security Question and input for security answer
	public static Account displayPickSecurityQuestion(Account acc) {
		System.out.println("Security Question:" );
		System.out.println("A: Favourite Colour" );
		System.out.println("B: Favourite Drink" );
		System.out.println("C: Favourite Place" );
		String option = sc.next();
		
		acc = UserController.pickSecurityQuestion(acc, option);
		System.out.println("Security Answer: ");
		acc.setSecurityAnswer(sc.next());
		user.updateSecurity(acc);
		return acc;
	}
	
	// display block option
	public static void displayBlockOption() {
		boolean valid;
		do {
			System.out.println("Would you like to block/unblock anyone? y/n");
			String ans = sc.next();
			valid = AdminController.checkBlockOption(ans, sc);
		}
		while(!valid);
	}
	
}
