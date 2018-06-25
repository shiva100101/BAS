package com.BAS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.BAS.model.AccountDetails;
import com.BAS.model.FundTransfer;
import com.BAS.util.DBUtil;

public class AccountDetailsDAO {
	private Connection connection;

	public AccountDetailsDAO() {
		connection = DBUtil.getConnection();
		System.out
				.println("AccountDetailsDAO | AccountDetailsDAO | constructor is invoked"
						+ connection);
	}

	public List<AccountDetails> getDetailsList() throws SQLException {

		List<AccountDetails> accountDetailsList = new ArrayList<>();
		PreparedStatement statement = connection
				.prepareStatement("SELECT * FROM ACCOUNT_DETAILS");
		System.out.println("AccountDetailsDAO | getDetailsList() | connection"
				+ connection);
		System.out.println(statement);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			AccountDetails accountDetails = new AccountDetails();
			accountDetails.setUserName(rs.getString("USER_NAME"));
			accountDetails.setAccuntNumber(rs.getLong("ACCOUNT_NUMBER"));
			System.out.println("AccountDetailsDAO | getDetailsList() | in result set" + rs.getLong("BALANCE"));
			System.out.println(accountDetails.getUserName());
			accountDetails.setBalance(rs.getLong("BALANCE"));
			System.out.println("AccountDetailsDAO | getDetailsList() | in result set" + accountDetails.getBalance());
			accountDetails.setAccountType(rs.getString("ACCOUNT_TYPE"));
			accountDetailsList.add(accountDetails);

		}

		return accountDetailsList;

	}

	public void updateBalance(FundTransfer fundTransfer) {
		
		try {
        System.out.println("AccountDetailsDAO | updateBalance()| entering method");
        PreparedStatement ps = connection.prepareStatement("UPDATE ACCOUNT_DETAILS SET BALANCE=? where ACCOUNT_NUMBER = ?");
        long existingBalance = getAccountBalance(fundTransfer);
        long balance = existingBalance - fundTransfer.getAmount();
        ps.setLong(1,balance);
        ps.setLong(2,fundTransfer.getSenderAccountNumber());
        System.out.println("AccountDetailsDAO | updateBalance()| connection"+ connection);
		System.out.println(ps);
		
		
    	int i= ps.executeUpdate();
    	
    	if(i>0) {
    		updateBalanceForReceiver(fundTransfer);
    		RegisterAccountDAO registerAccountDAO = new RegisterAccountDAO();
    		registerAccountDAO.updateInRegister(fundTransfer,balance);
    		
    	}			    	  
	} catch (Exception e2) {
		e2.printStackTrace();
	}
	


	}

	private void updateBalanceForReceiver(FundTransfer fundTransfer) {
		
		try {
        System.out.println("AccountDetailsDAO | updateBalance()| entering method");
        PreparedStatement ps = connection.prepareStatement("UPDATE ACCOUNT_DETAILS SET BALANCE=? where ACCOUNT_NUMBER = ?");
        long existingBalance = getAccountBalanceForReceiver(fundTransfer);
        long balance = existingBalance + fundTransfer.getAmount();
        ps.setLong(1,balance);
        ps.setLong(2,fundTransfer.getReceiverAccountNumber());
        System.out.println("AccountDetailsDAO | updateBalance()| connection"+ connection);
		System.out.println(ps);
		
		
    	int i= ps.executeUpdate();
    	
    	if(i>0) {
    		RegisterAccountDAO registerAccountDAO = new RegisterAccountDAO();
    		registerAccountDAO.updateInRegisterForReceiver(fundTransfer,balance);
    		
    	}			    	  
	} catch (Exception e2) {
		e2.printStackTrace();
	}
	


	}

	private long getAccountBalanceForReceiver(FundTransfer fundTransfer) throws SQLException {
		long balance = 0 ;
		PreparedStatement statement = connection
				.prepareStatement("SELECT BALANCE FROM ACCOUNT_DETAILS where ACCOUNT_NUMBER = ?");
		System.out.println("AccountDetailsDAO | getAccountBAlance() | connection"
				+ connection);
		statement.setLong(1,fundTransfer.getReceiverAccountNumber());
		System.out.println(statement);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			balance = rs.getLong("BALANCE");
			System.out.println("AccountDetailsDAO | getDetailsList() | in result set" + rs.getLong("BALANCE"));

		}

		return balance;

	}

	public long getAccountBalance(FundTransfer fundTransfer) throws SQLException {
		long balance = 0 ;
		PreparedStatement statement = connection
				.prepareStatement("SELECT BALANCE FROM ACCOUNT_DETAILS where ACCOUNT_NUMBER = ?");
		System.out.println("AccountDetailsDAO | getAccountBAlance() | connection"
				+ connection);
		statement.setLong(1,fundTransfer.getSenderAccountNumber());
		System.out.println(statement);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			balance = rs.getLong("BALANCE");
			System.out.println("AccountDetailsDAO | getDetailsList() | in result set" + rs.getLong("BALANCE"));

		}

		return balance;

	}
	public  long fetchbalance(String name) throws SQLException {
		long Balance = 0 ;
		PreparedStatement statement = connection
				.prepareStatement("SELECT BALANCE FROM ACCOUNT_DETAILS where USER_NAME = ?");
		System.out.println("AccountDetailsDAO | getAccountBAlance() | connection"
				+ connection);
		statement.setString(1,name);
		System.out.println(statement);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			Balance = rs.getLong("BALANCE");
			System.out.println("AccountDetailsDAO | getDetailsList() | in result set" + rs.getLong("BALANCE"));

		}

		return Balance;

	}


}