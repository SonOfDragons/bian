package com.lencee.result;

/**
 *
 * @author		jacksyen
 * @created	2014年8月14日-下午3:37:57
 */
public class BaseResult {
	
	private String errorCode;
	
	private String errorMessage;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public BaseResult(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public BaseResult() {
	}

}
