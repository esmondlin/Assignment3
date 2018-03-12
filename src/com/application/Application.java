package com.application;

import java.sql.SQLException;
import java.util.Scanner;

import com.controller.UserController;
import com.display.Display;

import com.entities.Account;

/*
 The codes is built with many small functions together. 
 Display Package contains all displays along with their function call from different class
 Controller package contains all the switch case that direct the display
 DAO package only contain the function to access to database
 Entities package contains class which can stored all the details gathered from SQL
 util package contains the connection to SQL
 Editing can be done on just its relevant module.
 Change display go to display package
 Change link to other display on different option go to Controller
 Add database access method go to DAO
 Add more parameters on details of retrieved object from sql go to Entities package
 */
public class Application {
	static Account acc = new Account() ;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException {
		//Go to first JSP, then UserController direct the display function
		while(true) {
		UserController.checkOptionHomePage(Display.print1stJsp()); 
		}//end of while loop
	}// end of main
}// end of class
