package com.BAS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.BAS.model.UserCredentials;
import com.BAS.model.UserLogin;
import com.BAS.util.DBUtil;

public class UserLoginDAO {
	private Connection connection;
	UserLogin userCredentials = new UserLogin();
    
	public UserLoginDAO() {
		connection = DBUtil.getConnection();
		System.out.println("userlogin" + connection);
	}

	public boolean checkCredentials(UserLogin userCredentials) 
	{
		boolean flag = false;
		
		try {
			System.out.println("inside dao");
			System.out.println("UserLoginDAO | checkCredentials| entering check credentials");
		
	      
	    
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM USER_LOGIN");
			System.out.println("UserLoginDAO | checkCredentials| connection"+ connection);
			System.out.println(statement);
			//System.out.println(statement.executeQuery("SELECT * FROM ADMIN_LOGIN WHERE LOGIN_NAME='ADMIN';"));
			ResultSet rs = statement.executeQuery();
		
			while(rs.next()) {
				System.out.println(rs.getString("LOGIN_NAME"));
				if((userCredentials.getUserName().equals(rs.getString("LOGIN_NAME"))) && (userCredentials.getPassword().equals(rs.getString("LOGIN_PASSWORD"))))
						{
					flag =  true;
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


