package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.entities.Account;
import com.util.DBConnection;

public class UserDAO {
private Connection conn = null;
private AdminDAO admin = new AdminDAO();
	// When create UserDAO object, connection is set up
	public UserDAO() {
		System.out.println("Trying to get Connection..");
		conn = DBConnection.getConnection();
	}
	//Check loginID is valid in SQL
	public boolean loginIDValidation(String LoginID) throws SQLException {
		
	      
	      Statement stmt = null; 
		    
	      String searchQuery =
	            "select * from user where Email='"
	                     + LoginID
	                     + "'";
		   
	      System.out.println("Query: "+searchQuery);
	   
	      stmt=conn.createStatement();
	      ResultSet rs = stmt.executeQuery(searchQuery);	        
	      boolean resultexist = rs.next();
		       
	      // if user does not exist set the isValid variable to false
	      if (!resultexist) 
	      {
	         System.out.println("Sorry, you are not a registered user! Please sign up first");
	         return false;
	      } 
		        
	      //if user exists set the isValid variable to true
	      else 
	      {
	         return true;
	      }
	   }
	//Check password is valid in SQL and return acc with all information
	public Account loginPasswordValidation(String Password, String Email) throws SQLException {
			Account user = new Account();
	  
	      Statement stmt = null; 
		    
	      String searchQuery =
	            "select * from User where "+ "	Email ='"+ Email + "'AND"+ "	Password='"+ Password + "'";
		   
	      System.out.println("Query: "+searchQuery);
	   
	      stmt=conn.createStatement();
	      ResultSet rs = stmt.executeQuery(searchQuery);	        
	      boolean resultexist = rs.next();
		       
	      // if user does not exist set the isValid variable to false
	      if (!resultexist) 
	      {
	         System.out.println("Sorry, you are not a registered user! Please sign up first");
	         user.setValid(false);
	         return user;
	      } 
		        
	      //if user exists set the isValid variable to true
	      else 
	      {
	    	 user.setAccId(rs.getInt("SerialNo"));
	    	 user.setName(rs.getString("Name"));
	    	 user.setBlocked(rs.getInt("BlockStatus"));
	    	 user.setFirstLogin(rs.getInt("FirstLogin"));
	    	 user.setPassword(rs.getString("Password"));
	    	 user.setEmail(rs.getString("Email"));
	    	 user.setAttempts(rs.getInt("NumberOfAttempts"));
	    	 if(user.getAttempts()<3) {
	    	 user.setValid(true);
	    	 }else {
	    		 admin.updateStatus(user.getAccId(), 1);
	    		 user.setValid(false);
	    	 }
	         return user;
	      }
	   }
	// Retrieve security Question and answer from SQL
	public Account retrieveSecurityQuestion(Account user) throws SQLException {
		Statement stmt = null; 
	    
	      String searchQuery =
	            "select * from User where "+ "	Email ='"+ user.getEmail() + "'";
		   
	      System.out.println("Query: "+searchQuery);
	   
	      stmt=conn.createStatement();
	      ResultSet rs = stmt.executeQuery(searchQuery);	        
	      boolean resultexist = rs.next();
		       
	      // if user does not exist set the isValid variable to false
	      if (!resultexist) 
	      {
	         System.out.println("Sorry, Wrong Login ID");
	         user.setValid(false);
	         return user;
	      } 
		        
	      //if user exists set the isValid variable to true
	      else 
	      {
	    	 user.setSecurityQuestion(rs.getString("SecurityQuestion"));
	    	 user.setSecurityAnswer(rs.getString("SecurityAnswer"));
	    	 user.setValid(true);
	         return user;
	      }
	}
	// Update password in sql
	public void updatePassword( Account acc) {
		String Email = acc.getEmail();
		String Password = acc.getPassword();
        try {
            String query = "update user set Password=? where Email=?";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setString( 1, Password);
            preparedStatement.setString( 2, Email);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
    	}
    }
	//update security question and answer in SQL
	public void updateSecurity(Account acc) {
		String SecurityQuestion = acc.getSecurityQuestion();
		String SecurityAnswer = acc.getSecurityAnswer();
		String Email = acc.getEmail();
		
		try {
            String query = "update user set SecurityQuestion=?, SecurityAnswer=?  where Email=?";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setString( 1, SecurityQuestion);
            preparedStatement.setString( 2, SecurityAnswer);
            preparedStatement.setString( 3, Email);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
    	}
		
	}
	//update firstlogin valid in sql
	public void updateFirstLogin(Account acc) {
		int FirstLogin = acc.getFirstLogin();
		String Email = acc.getEmail();
		try {
			String query = "update user set FirstLogin=?  where Email=?";
			PreparedStatement preparedStatement = conn.prepareStatement( query );
			preparedStatement.setInt( 1, FirstLogin);
			preparedStatement.setString( 2, Email);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
}