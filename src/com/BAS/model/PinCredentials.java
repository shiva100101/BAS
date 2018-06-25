package com.BAS.model;

public class PinCredentials {
	private String userName;
	private String oldpin;
	private String pin;
	private String confirmPin;
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
	 * @return the oldpine
	 */
	public String getOldpin() {
		return oldpin;
	}
	/**
	 * @param oldpine the oldpine to set
	 */
	public void setOldpin(String oldpin) {
		this.oldpin = oldpin;
	}
	/**
	 * @return the pin
	 */
	public String getPin() {
		return pin;
	}
	/**
	 * @param pin the pin to set
	 */
	public void setPin(String pin) {
		this.pin = pin;
	}
	/**
	 * @return the confirmPin
	 */
	public String getConfirmPin() {
		return confirmPin;
	}
	/**
	 * @param confirmPin the confirmPin to set
	 */
	public void setConfirmPin(String confirmPin) {
		this.confirmPin = confirmPin;
	}

}
