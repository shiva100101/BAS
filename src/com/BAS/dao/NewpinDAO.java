package com.BAS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.BAS.model.PinCredentials;
import com.BAS.util.DBUtil;

public class NewpinDAO {
	private Connection connection;
	PinCredentials pinCredentials = new PinCredentials();
    
	public NewpinDAO() {
		connection = DBUtil.getConnection();
		System.out.println("ChangePasswordDAO | ChangePasswordDAO | constructor is invoked" + connection);
	}
	
	public boolean updatePin(PinCredentials pinCredentials) 
	{
		boolean flag = false;
		
		try {
			System.out.println("NewpinDAO | updatepin()| entering method" );
		
	      if(pinCredentials.getPin().equalsIgnoreCase(pinCredentials.getConfirmPin())) {
	    	  PreparedStatement statement = connection.prepareStatement("SELECT PIN FROM NEWPIN WHERE USER_NAME =?");
	    	  statement.setString(1, pinCredentials.getUserName());
	    	  ResultSet rs =statement.executeQuery();
	    	  while(rs.next()) {
	    		  if(pinCredentials.getOldpin().equalsIgnoreCase(rs.getString("PIN"))) {
	    			  updatePinUser(pinCredentials);
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

	private boolean updatePinUser(PinCredentials pinCredentials) {
		boolean flag = false;
	
		try {
         System.out.println("NewPinDAO | updatePindUser()| entering method");
		PreparedStatement statement = connection.prepareStatement("UPDATE NEWPIN SET PIN =? WHERE USER_NAME =?");
		System.out.println("NewPinDAO | checkCredentials| connection"+ connection);
		System.out.println(statement);
		statement.setString(1,pinCredentials.getConfirmPin());
		statement.setString(2,pinCredentials.getUserName());

		    // call executeUpdate to execute our sql update statement
		int i=statement.executeUpdate();
		
		
		if(i>0) {
			flag = updatePinRegister(pinCredentials);
		
		}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return flag;


	}
	private boolean updatePinRegister(PinCredentials pinCredentials)  {
		boolean flag = false;
		try {
        System.out.println("NewPinDAO | updatePindUser()| entering method");
		PreparedStatement statement = connection.prepareStatement("UPDATE REGISTER SET  ACCOUNT_PIN = ?,VERIFY_PINNUMBER=? WHERE FIRSTNAME = ?");
		System.out.println("NewPinDAO | checkCredentials| connection"+ connection);
		System.out.println(statement);
		statement.setString(1,pinCredentials.getPin());
		statement.setString(2,pinCredentials.getConfirmPin());
		statement.setString(3,pinCredentials.getUserName());

		    // call executeUpdate to execute our sql update statement
		int i=statement.executeUpdate();
		
		if (i > 0) {

			flag = true;
		}

	} catch (Exception e2) {
		e2.printStackTrace();
	}
	return flag;


	}

}
