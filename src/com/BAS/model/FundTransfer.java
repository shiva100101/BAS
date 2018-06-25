package com.BAS.model;

public class FundTransfer {

	private String receiverBankName;
	private String receiverName;
	private long receiverAccountNumber;
	private long senderAccountNumber; 
	private long amount;
	private String transfer;
	private String date;
	private String description;
	private String password;
	private String referenceNumber;
	private String senderBankName;
	private String senderName;
	private String status;
	
	
	
	
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the senderBankName
	 */
	public String getSenderBankName() {
		return senderBankName;
	}
	/**
	 * @param senderBankName the senderBankName to set
	 */
	public void setSenderBankName(String senderBankName) {
		this.senderBankName = senderBankName;
	}
	/**
	 * @return the senderName
	 */
	public String getSenderName() {
		return senderName;
	}
	/**
	 * @param senderName the senderName to set
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	/**
	 * @return the referenceNumber
	 */
	public String getReferenceNumber() {
		return referenceNumber;
	}
	/**
	 * @param referenceNumber the referenceNumber to set
	 */
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the receiverBankName
	 */
	public String getReceiverBankName() {
		return receiverBankName;
	}
	/**
	 * @param receiverBankName the receiverBankName to set
	 */
	public void setReceiverBankName(String receiverBankName) {
		this.receiverBankName = receiverBankName;
	}
	/**
	 * @return the receiverName
	 */
	public String getReceiverName() {
		return receiverName;
	}
	/**
	 * @param receiverName the receiverName to set
	 */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	/**
	 * @return the receiverAccountNumber
	 */
	public long getReceiverAccountNumber() {
		return receiverAccountNumber;
	}
	/**
	 * @param receiverAccountNumber the receiverAccountNumber to set
	 */
	public void setReceiverAccountNumber(long receiverAccountNumber) {
		this.receiverAccountNumber = receiverAccountNumber;
	}
	/**
	 * @return the senderAccountNumber
	 */
	public long getSenderAccountNumber() {
		return senderAccountNumber;
	}
	/**
	 * @param senderAccountNumber the senderAccountNumber to set
	 */
	public void setSenderAccountNumber(long senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
	}
	/**
	 * @return the amount
	 */
	public long getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(long amount) {
		this.amount = amount;
	}
	/**
	 * @return the transfer
	 */
	public String getTransfer() {
		return transfer;
	}
	/**
	 * @param transfer the transfer to set
	 */
	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
