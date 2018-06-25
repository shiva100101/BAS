package com.BAS.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.BAS.model.AccountDetails;
import com.BAS.model.AccountStatement;
import com.BAS.util.DBUtil;

public class AccountStatementDAO {
	private Connection connection;

	public AccountStatementDAO() {
		connection = DBUtil.getConnection();
		System.out
				.println("AccountStatementDAO | AccountStatementDAO | constructor is invoked"
						+ connection);
	}

	public List<AccountStatement> getAccountStatement(String name) throws SQLException {
System.out.println("The name is "+ name);
		List<AccountStatement> accountStatementList = new ArrayList<>();
		PreparedStatement statement = connection
				.prepareStatement("SELECT * FROM ACCOUNT_STATEMENT WHERE USER_NAME = '" + name +"'");
		System.out.println("AccountStatementDAO | getAccountStatement() | connection"
				+ connection);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			System.out.println(statement + rs.getString("USER_NAME") + rs.getLong("DEBIT"));

			AccountStatement accountStatement = new AccountStatement();
			accountStatement.setUserName(rs.getString("USER_NAME"));
			accountStatement.setTransactionDate(rs.getString("TXN_DATE"));
			accountStatement.setReferenceNumber(rs.getString("REF_NUM"));
			accountStatement.setTransactionDescription(rs.getString("TXN_DESC"));
			accountStatement.setDebit(rs.getLong("DEBIT"));
			accountStatement.setCredit(rs.getLong("CREDIT"));
			accountStatement.setStatus(rs.getString("STATUS"));
			
			accountStatementList.add(accountStatement);
		}
       System.out.println("the statemeny is executed in the dao class reutning account statement list");
		return accountStatementList;

	}
	public AccountDetails getBalance (AccountDetails accountDetails )throws SQLException {
		PreparedStatement statement = connection
				.prepareStatement("SELECT BALANCE FROM ACCOUNT_DETAILS where USER_NAME = ?");
		System.out.println("AccountDetailsDAO | getDetailsList() | connection"
				+ connection);
		System.out.println(statement);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			
			accountDetails.setBalance(rs.getLong("BALANCE"));
			System.out.println("Account Credit balance: " );
			

		}

		return accountDetails;

	}

	
	}

	
	

	
	

