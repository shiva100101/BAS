package com.BAS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.BAS.model.AdminLogin;
import com.BAS.util.DBUtil;

public class AdminLoginDAO {
     
	private Connection connection;
	AdminLogin adminLogin = new AdminLogin();
    
	public AdminLoginDAO() {
		connection = DBUtil.getConnection();
		System.out.println("AdminLoginDAO | AdminLoginDAO | constructor is invoked" + connection);
	}

	public boolean checkCredentials(AdminLogin adminLogin) 
	{
		boolean flag = false;
		
		try {
			System.out.println("AdminLoginDAO | checkCredentials| entering check credentials");
		
	      
	    
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM ADMIN_LOGIN");
			System.out.println("AdminLoginDAO | checkCredentials| connection"+ connection);
			System.out.println(statement);
			//System.out.println(statement.executeQuery("SELECT * FROM ADMIN_LOGIN WHERE LOGIN_NAME='ADMIN';"));
			ResultSet rs = statement.executeQuery();
		
			while(rs.next()) {
				System.out.println(rs.getString("LOGIN_NAME"));
				if((adminLogin.getUserName().equals(rs.getString("LOGIN_NAME"))) && (adminLogin.getPassword().equals(rs.getString("LOGIN_PASSWORD"))))
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
