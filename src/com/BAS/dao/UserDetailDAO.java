package com.BAS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.BAS.model.UserDetails;
import com.BAS.util.DBUtil;

public class UserDetailDAO {
	private Connection connection;
	
	
	

	public UserDetailDAO() {
		connection = DBUtil.getConnection();
		System.out
		.println("UserDetailDAO | UserDetailDAO | constructor is invoked"
				+ connection);
	}


	public List<UserDetails> getAllUsers() {
        List<UserDetails> usersDetailList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from USER_DETAILS");
            while (rs.next()) {
                UserDetails UserDetails = new UserDetails();
                
                
                UserDetails.setUserName(rs.getString("USER_NAME")); 
                UserDetails.setAccountNumber((rs.getLong("ACC_NUMBER")));
                UserDetails.setEmailId(rs.getString("EMAIL_ID"));
                UserDetails.setMobileNumber(rs.getLong("MOBILE_NUMBER"));
                UserDetails.setRegisterDate(rs.getString("REGISTER_DATE"));
              //  System.out.println("UserDetailDAO | GETDETAILS |" + rs.getDate("REGISTER_DATE"));
              //  System.out.println(UserDetails.getRegisterDate());
                usersDetailList.add(UserDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersDetailList;
    }


	public boolean addUser(UserDetails userDetails) {
		boolean flag = false;
		 try {
			 System.out.println("UserDetailsDAO | addUser() | entered into add user method");
	            PreparedStatement preparedStatement = connection
	                    .prepareStatement("insert into USER_DETAILS values (?, ?, ?, ? , ?)");
	            // Parameters start with 1
	            preparedStatement.setString(1, userDetails.getUserName());
	            preparedStatement.setLong(2, userDetails.getAccountNumber());
	            preparedStatement.setString(3, userDetails.getEmailId());
	            preparedStatement.setLong(4, userDetails.getMobileNumber());
	            preparedStatement.setString(5, 	userDetails.getRegisterDate());	            
	           
	            int i = preparedStatement.executeUpdate();
				if (i > 0) {

					flag = true;
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
			 System.out.println("REgisterAccountDAO | insertIntoUserDetails() | flag is " + flag);
			return flag;
		
	}
	public boolean updateUser(UserDetails userDetails) {
		boolean flag = false;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE USER_DETAILS SET ACC_NUMBER = ?,EMAIL_ID = ?, MOBILE_NUMBER = ?, REGISTER_DATE = ? WHERE USER_NAME = ?");
            // Parameters start with 1
           
            preparedStatement.setLong(1, userDetails.getAccountNumber());
            preparedStatement.setString(2, userDetails.getEmailId());
            preparedStatement.setLong(3, userDetails.getMobileNumber());
            preparedStatement.setString(4, 	userDetails.getRegisterDate());	   
            preparedStatement.setString(5, userDetails.getUserName());
            int i = preparedStatement.executeUpdate();
			if (i > 0) {

				flag =updateRegister(userDetails);
			}

		} catch (Exception e2) {
			e2.printStackTrace();
		}
		 System.out.println("REgisterAccountDAO | insertIntoUserDetails() | flag is " + flag);
		// TODO Auto-generated method stub
		return flag;
	}
	


	public boolean updateRegister(UserDetails userDetails) {
		boolean flag = false;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE REGISTER SET ACCOUNT_NUMBER = ?,EMAIL = ?,MOBILENUMBER =? WHERE FIRSTNAME = ?");
            
           
            preparedStatement.setLong(1,userDetails.getAccountNumber());
            preparedStatement.setString(2,userDetails.getEmailId());
            preparedStatement.setLong(3,userDetails.getMobileNumber());
             
            preparedStatement.setString(4,userDetails.getUserName());
            int i = preparedStatement.executeUpdate();
			if (i > 0) {

				flag =updateaccount(userDetails);
			}

		} catch (Exception e2) {
			e2.printStackTrace();
		}
		 System.out.println("REgisterAccountDAO | insertIntoUserDetails() | flag is " + flag);
		// TODO Auto-generated method stub
		return false;
	}
	public boolean updateaccount(UserDetails userDetails) {
		boolean flag = false;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE ACCOUNT_DETAILS SET ACCOUNT_NUMBER = ? WHERE USER_NAME = ?");
            // Parameters start with 1
           
            preparedStatement.setLong(1, userDetails.getAccountNumber());
            preparedStatement.setString(2, userDetails.getUserName());
            int i = preparedStatement.executeUpdate();
			if (i > 0) {

				flag = true;
			}

		} catch (Exception e2) {
			e2.printStackTrace();
		}
		 System.out.println("REgisterAccountDAO | insertIntoUserDetails() | flag is " + flag);
		// TODO Auto-generated method stub
		return flag;
	}


	public boolean delete(String userName) {
		boolean flag = false;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from USER_DETAILS where USER_NAME=?");
            // Parameters start with 1
            preparedStatement.setString(1,userName);
            int i = preparedStatement.executeUpdate();
			if (i > 0) {

				flag = deleteRegister(userName);
			}

		} catch (Exception e2) {
			e2.printStackTrace();
		}
		 System.out.println("REgisterAccountDAO | insertIntoUserDetails() | flag is " + flag);
		return flag;
	
    }
	public boolean deleteRegister(String userName) {
		boolean flag = false;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from REGISTER where  FIRSTNAME=?,");
            // Parameters start with 1
            preparedStatement.setString(1,userName);
            int i = preparedStatement.executeUpdate();
			if (i > 0) {

				flag =deleteAccount(userName);
			}

		} catch (Exception e2) {
			e2.printStackTrace();
		}
		 System.out.println("REgisterAccountDAO | insertIntoUserDetails() | flag is " + flag);
		return flag;
	
    }
	public boolean deleteAccount(String userName) {
		boolean flag = false;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from ACCOUNT_DETAILS where  USER_NAME=?,");
            // Parameters start with 1
            preparedStatement.setString(1,userName);
            int i = preparedStatement.executeUpdate();
			if (i > 0) {

				flag =true;
			}

		} catch (Exception e2) {
			e2.printStackTrace();
		}
		 System.out.println("REgisterAccountDAO | insertIntoUserDetails() | flag is " + flag);
		return flag;
	
    }



	public UserDetails getUserByName(String userName) {

		UserDetails userDetails = new UserDetails();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM USER_DETAILS WHERE USER_NAME = ?");
            preparedStatement.setString(1, userName);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	 userDetails.setUserName(rs.getString("USER_NAME")); 
            	 userDetails.setAccountNumber((rs.getLong("ACC_NUMBER")));
            	 userDetails.setEmailId(rs.getString("EMAIL_ID"));
            	 userDetails.setMobileNumber(rs.getLong("MOBILE_NUMBER"));
            	 userDetails.setRegisterDate(rs.getString("REGISTER_DATE"));
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userDetails;
    
	}


	
}