package com.lencee.exception;

/**
 * 格式异常
 * @author 		yangjie
 * @created	2014.05.19
 *
 */
public class PatternException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7440750270276349757L;
	
	private String errorCode;
	
	public PatternException(String errorCode, String msg) {
		super(msg);
		setErrorCode(errorCode);
    }
	
	public PatternException(String msg) {
		super(msg);
    }
	
    /**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}


}
