package com.BAS.model;

public class AccountStatement {
private String userName;
private String transactionDate;
private String referenceNumber;
private String transactionDescription;
private long debit;
private long credit;
private String status;
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
 * @return the transactionDate
 */
public String getTransactionDate() {
	return transactionDate;
}
/**
 * @param transactionDate the transactionDate to set
 */
public void setTransactionDate(String transactionDate) {
	this.transactionDate = transactionDate;
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
 * @return the transactionDescription
 */
public String getTransactionDescription() {
	return transactionDescription;
}
/**
 * @param transactionDescription the transactionDescription to set
 */
public void setTransactionDescription(String transactionDescription) {
	this.transactionDescription = transactionDescription;
}
/**
 * @return the debit
 */
public long getDebit() {
	return debit;
}
/**
 * @param debit the debit to set
 */
public void setDebit(long debit) {
	this.debit = debit;
}
/**
 * @return the credit
 */
public long getCredit() {
	return credit;
}
/**
 * @param credit the credit to set
 */
public void setCredit(long credit) {
	this.credit = credit;
}
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


}
