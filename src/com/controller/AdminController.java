package com.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dao.AdminDAO;
import com.display.Display;
import com.entities.Account;

public class AdminController {
	//AdminController direct display for admin
	private static AdminDAO admin = new AdminDAO();
	
public static boolean checkOptionAdminPage(String option) throws SQLException {
		boolean logout = false;
		switch (option) {
			
		case "1": Account acc = new Account();
				do {
					//show Register function
					acc = Display.showRegister();
				}while(!acc.isValid()); // repeat only when register function is successfully execute
				admin.createUserAccount(acc); // create acc on sql
				break;	
				
		case "2":List<Account>users = new ArrayList<Account>();
		//Show all user database
				users = admin.getAllUser();
				Display.showAllUsers(users);
				// Display blocking option
				Display.displayBlockOption();
				break;
				
		case "3": logout=true;
				break;
				
		default: logout = false;
				break;
		}// end of switch case
		return logout;
	}//end of checkOption

public static boolean checkBlockOption(String ans, Scanner sc) {
	int block ;
	int serialno;
	boolean valid ;
	switch (ans) {
	case "y" : System.out.println("Please enter their serial no: ");
				serialno = sc.nextInt();
				System.out.println("Pick 1 to block and 0 to unblock: ");
				block = sc.nextInt();
				// update block option
				admin.updateStatus(serialno, block);
				valid = true;
				break;
	case "n" : valid = true;
				break;
	
	default	: System.out.println("Please enter only y or n");
				valid = false;
				break;
	}
	return valid;
}
}//end of class
