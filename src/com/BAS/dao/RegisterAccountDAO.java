package com.BAS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.BAS.model.AccountStatement;
import com.BAS.model.FundTransfer;
import com.BAS.model.RegisterAccount;
import com.BAS.util.DBUtil;

public class RegisterAccountDAO {

	private Connection connection;

	public RegisterAccountDAO() {
		connection = DBUtil.getConnection();
		System.out.println("connected" + connection);
	}

	public boolean addUser(RegisterAccount registerAccount) {
		boolean flag = false;

		
			try {

				PreparedStatement ps = connection
						.prepareStatement("insert into REGISTER values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

				ps.setString(1, registerAccount.getName());
				ps.setString(2, registerAccount.getLastName());
				ps.setString(3, registerAccount.getEmail());
				ps.setLong(4, registerAccount.getMobile());
				ps.setString(5, registerAccount.getDate());
				ps.setString(6, registerAccount.getGender());
				ps.setString(7, registerAccount.getAddress());
				ps.setString(8, registerAccount.getCity());
				ps.setString(9, registerAccount.getState());
				ps.setLong(10, registerAccount.getZipCode());
				ps.setString(11, registerAccount.getAccountType());
				ps.setLong(12, registerAccount.getAccountNumber());
				ps.setLong(13, registerAccount.getBalance());
				ps.setLong(14, registerAccount.getPin());
				ps.setLong(15, registerAccount.getvPin());

				int i = ps.executeUpdate();
				if (i > 0) {
					flag = insertInUserLogin(registerAccount);

				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		
		
		return flag;

	}

	private boolean insertInUserLogin(RegisterAccount registerAccount) {
		boolean flag = false;
		try {

			PreparedStatement ps = connection.prepareStatement("insert into USER_LOGIN values(?,?)");

			ps.setString(1, registerAccount.getName());
			ps.setString(2, registerAccount.getConfirmPassword());

			int i = ps.executeUpdate();
			if (i > 0) {

				flag = insertIntoNewPin(registerAccount);
			}

		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return flag;

	}

	private boolean insertIntoNewPin(RegisterAccount registerAccount) {
		boolean flag = false;
		try {

			PreparedStatement ps = connection.prepareStatement("insert into NEWPIN values(?,?)");

			ps.setString(1, registerAccount.getName());
			ps.setLong(2, registerAccount.getvPin());

			int i = ps.executeUpdate();
			if (i > 0) {

				flag = insertIntoUserDetails(registerAccount);
			}

		} catch (Exception e2) {
			e2.printStackTrace();
		}
		 System.out.println("REgisterAccountDAO | insertIntoANewPin) | flag is " + flag);
		return flag;

	}

	private boolean insertIntoUserDetails(RegisterAccount registerAccount) {
		boolean flag = false;
		try {

			PreparedStatement ps = connection.prepareStatement("INSERT INTO USER_DETAILS VALUES(?,?,?,?,?)");

			ps.setString(1, registerAccount.getName());
			ps.setLong(2, registerAccount.getAccountNumber());
			ps.setString(3, registerAccount.getEmail());
			ps.setLong(4, registerAccount.getMobile());
			ps.setString(5, registerAccount.getDate());

			int i = ps.executeUpdate();
			if (i > 0) {

				flag = insertIntoACcountDetails(registerAccount);
			}

		} catch (Exception e2) {
			e2.printStackTrace();
		}
		 System.out.println("REgisterAccountDAO | insertIntoUserDetails() | flag is " + flag);
		return flag;

	}

	private boolean insertIntoACcountDetails(RegisterAccount registerAccount) {
		boolean flag = false;
		try {

			PreparedStatement ps = connection.prepareStatement("INSERT INTO ACCOUNT_DETAILS VALUES(?,?,?,?)");

			ps.setString(1, registerAccount.getName());
			ps.setLong(2, registerAccount.getAccountNumber());
			ps.setLong(3, registerAccount.getBalance());
			System.out.println(registerAccount.getAccountType());
			ps.setString(4, registerAccount.getAccountType());
			

			int i = ps.executeUpdate();
			if (i > 0) {
				flag = true;
			}

		} catch (Exception e2) {
			e2.printStackTrace();
		}
		 System.out.println("REgisterAccountDAO | insertIntoAccountDetails() | flag is " + flag);
		return flag;

	}

	public RegisterAccount getAccountDetails(String name) throws SQLException {
System.out.println("The name is "+ name);
RegisterAccount registerAccount = new RegisterAccount();
		PreparedStatement statement = connection
				.prepareStatement("SELECT * FROM REGISTER WHERE FIRSTNAME = '" + name +"'");
		System.out.println("RegisterAccountDAO | getAccountDetails() | connection"
				+ connection);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			System.out.println(statement + rs.getString("FIRSTNAME"));
			registerAccount.setAccountType(rs.getString("ACCOUNT_TYPE"));
			registerAccount.setAddress(rs.getString("ADDRESS"));
			registerAccount.setCity(rs.getString("CITYNAME"));
			registerAccount.setDate(rs.getString("DATEOFBIRTH"));
			registerAccount.setEmail(rs.getString("EMAIL"));
			registerAccount.setGender(rs.getString("GENDER"));
			registerAccount.setLastName(rs.getString("LASTNAME"));
			registerAccount.setMobile(rs.getLong("MOBILENUMBER"));
			registerAccount.setName(rs.getString("FIRSTNAME"));
			registerAccount.setPin(rs.getLong("ACCOUNT_PIN"));
			registerAccount.setState(rs.getString("STATE"));
			registerAccount.setvPin(rs.getLong("VERIFY_PINNUMBER"));
			registerAccount.setZipCode(rs.getLong("ZIPCODE"));
			registerAccount.setAccountNumber(rs.getLong("ACCOUNT_NUMBER"));
			registerAccount.setBalance(rs.getLong("BALANCE"));
		}
       System.out.println("the statemeny is executed in the dao class reutning account statement list");
		return registerAccount;

	}


	public void updateInRegister(FundTransfer fundTransfer, long balance) {
		
		try {
        System.out.println("RegisterAccountDAO | updateInRegister()| entering method");
        PreparedStatement ps = connection.prepareStatement("UPDATE REGISTER SET BALANCE=? where ACCOUNT_NUMBER = ?");
        ps.setLong(1,balance);
        ps.setLong(2,fundTransfer.getSenderAccountNumber());
        System.out.println("RegisterAccountDAO | updateInRegister()| connection"+ connection);
		System.out.println(ps);
		
		
    	int i= ps.executeUpdate();
    	
    			    	  
	} catch (Exception e2) {
		e2.printStackTrace();
	}
	


	}

	public void updateInRegisterForReceiver(FundTransfer fundTransfer, long balance) {
		
		try {
        System.out.println("RegisterAccountDAO | updateInRegister()| entering method");
        PreparedStatement ps = connection.prepareStatement("UPDATE REGISTER SET BALANCE=? where ACCOUNT_NUMBER = ?");
        ps.setLong(1,balance);
        ps.setLong(2,fundTransfer.getReceiverAccountNumber());
        System.out.println("RegisterAccountDAO | updateInRegister()| connection"+ connection);
		System.out.println(ps);
		
		
    	int i= ps.executeUpdate();
    	
    			    	  
	} catch (Exception e2) {
		e2.printStackTrace();
	}
	


	}
	
	
	
	
}
