package com.BAS.model;

public class AccountDetails {

	private String userName;
	private long accuntNumber;
	private  long balance;
	private String accountType;
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the accuntNumber
	 */
	public long getAccuntNumber() {
		return accuntNumber;
	}
	/**
	 * @param accuntNumber the accuntNumber to set
	 */
	public void setAccuntNumber(long accuntNumber) {
		this.accuntNumber = accuntNumber;
	}
	
	/**
	 * @return the balance
	 */
	public long getBalance() {
		return balance;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(long balance) {
		this.balance = balance;
	}
	/**
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}
	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	
	
}