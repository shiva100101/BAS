package com.BAS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.BAS.model.UserCredentials;
import com.BAS.util.DBUtil;

public class ChangePasswordDAO {
	private Connection connection;
	UserCredentials userCredentials = new UserCredentials();
    
	public ChangePasswordDAO() {
		connection = DBUtil.getConnection();
		System.out.println("ChangePasswordDAO | ChangePasswordDAO | constructor is invoked" + connection);
	}

	public boolean updatePassword(UserCredentials userCredentials) 
	{
		boolean flag = false;
		
		try {
			System.out.println("ChangePasswordDAO | checkCredentials| entering check credentials");
			PreparedStatement statementOne = connection.prepareStatement("SELECT LOGIN_NAME FROM USER_LOGIN");
			ResultSet rs = statementOne.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("LOGIN_NAME"));
				if((userCredentials.getUserName().equals(rs.getString("LOGIN_NAME"))))
						{
					 if(userCredentials.getNewPassword().equals(userCredentials.getConfirmPassword())) {
				    	  
				    	  PreparedStatement statement = connection.prepareStatement("UPDATE USER_LOGIN SET LOGIN_PASSWORD = ? WHERE LOGIN_NAME = ?");
							System.out.println("ChangePasswordDAO | checkCredentials| connection"+ connection);
							System.out.println(statement);
							statement.setString(1,userCredentials.getConfirmPassword());
							statement.setString(2,userCredentials.getUserName());

							    // call executeUpdate to execute our sql update statement
							statement.executeUpdate();
							statement.close();
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
	

}
