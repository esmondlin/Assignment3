package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entities.Account;

import com.util.DBConnection;

public class AdminDAO {
	
	private Connection conn;
	//Create AdminDao object will set up the connection for sql
	
	public AdminDAO() {
		conn = DBConnection.getConnection();
	}// end of constructor
	
	public void createUserAccount(Account user) {
		// TODO Auto-generated method stub
	        try {
	            String query = "insert into user (Name, Nric, DOB, Email, Mobile, Password, BlockStatus, FirstLogin,Role,NumberOfAttempts) values (?,?,?,?,?,?,?,?,?,?)";
	            PreparedStatement preparedStatement = conn.prepareStatement( query );
	            preparedStatement.setString( 1, user.getName() );
	            preparedStatement.setString( 2, user.getNric());
	            preparedStatement.setString( 3, user.getDOB());
	            preparedStatement.setString( 4, user.getEmail());
	            preparedStatement.setInt( 5, user.getMobile());
	            preparedStatement.setString( 6, user.getPassword());
	            preparedStatement.setInt(7, 0);
	            preparedStatement.setInt(8, 0);
	            preparedStatement.setString(9, "User");
	            preparedStatement.setInt(10, 0);
	            preparedStatement.executeUpdate();
	            preparedStatement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}//end of createUserAccount
	
	public List<Account> getAllUser() {
        List<Account> users = new ArrayList<Account>();
        try {
            Statement statement = conn.createStatement(); //java.sql.Statement
            ResultSet resultSet = statement.executeQuery( "select * from user where SerialNo>1" ); //java.sql.ResultSet
            while( resultSet.next() ) {
            	Account user = new Account();
                user.setAccId( resultSet.getInt( "SerialNo" ) );
                user.setName( resultSet.getString( "Name" ) );
                user.setLoginId( resultSet.getString( "Email" ) );
                user.setPassword( resultSet.getString( "Password" ) );
                user.setBlocked(resultSet.getInt( "BlockStatus" ));
                users.add(user);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }//end of getAllUser()
	
	public void updateStatus( int SerialNumber, int i) {
        try {
            String query = "update user set BlockStatus=? where SerialNo=?";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setInt( 1, i);
            preparedStatement.setInt( 2, SerialNumber);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
    	}
    }//end of updateStatus() 
	
	public void updateAttempts(Account acc) {
		String Email = acc.getEmail();
        try {
            String query = "update user set NumberOfAttempts=NumberOfAttempts+1 where Email = ?";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setString( 1, Email );
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
    	}
    }//end of updateAttempts()
	
	public void resetAttempts(Account acc) {
		String Email = acc.getEmail();
        try {
            String query = "update user set NumberOfAttempts=0 where Email = ?";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setString( 1, Email );
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
    	}
    }//end of resetAttempts
}//end of class
