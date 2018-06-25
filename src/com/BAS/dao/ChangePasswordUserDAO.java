package com.BAS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.BAS.model.CredentialsForUser;
import com.BAS.util.DBUtil;

public class ChangePasswordUserDAO {
	private Connection connection;
	CredentialsForUser userCredentials = new CredentialsForUser();
    
	public ChangePasswordUserDAO() {
		connection = DBUtil.getConnection();
		System.out.println("ChangePasswordDAO | ChangePasswordDAO | constructor is invoked" + connection);
	}
	
	public boolean updatePassword(CredentialsForUser userCredentials) 
	{
		boolean flag = false;
		
		try {
			System.out.println("ChangePasswordUserDAO | updatepassword()| entering method");
		
	      if(userCredentials.getNewPassword().equalsIgnoreCase(userCredentials.getConfirmPassword())) {
	    	  PreparedStatement statement = connection.prepareStatement("SELECT LOGIN_PASSWORD FROM USER_LOGIN WHERE LOGIN_NAME = ?");
	    	  statement.setString(1, userCredentials.getUserName());
	    	  ResultSet rs =statement.executeQuery();
	    	  while(rs.next()) {
	    		  if(userCredentials.getOldPassword().equalsIgnoreCase(rs.getString("LOGIN_PASSWORD"))) {
	    			  updatePasswordUser(userCredentials);
	    			  flag = true;
	    		  }
	    	  }
	    	 
	 
	    	  	      }
	    
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	System.out.println(flag);
		return flag;
	}

	private void updatePasswordUser(CredentialsForUser userCredentials) throws SQLException {
         System.out.println("ChangePasswordUserDAO | updatePasswordUser()| entering method");
		PreparedStatement statement = connection.prepareStatement("UPDATE USER_LOGIN SET LOGIN_PASSWORD = ? WHERE LOGIN_NAME = ?");
		System.out.println("ChangePasswordDAO | checkCredentials| connection"+ connection);
		System.out.println(statement);
		statement.setString(1,userCredentials.getConfirmPassword());
		statement.setString(2,userCredentials.getUserName());

		    // call executeUpdate to execute our sql update statement
		statement.executeUpdate();
		statement.close();
		

	}

}
