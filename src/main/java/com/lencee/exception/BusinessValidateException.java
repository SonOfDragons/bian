package com.lencee.exception;

/**
 * 业务校验异常
 * @author 		yangjie
 * @created	2014.05.19
 *
 */
public class BusinessValidateException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2333726144918456070L;
	
	private String errorCode;
	
	public BusinessValidateException(String errorCode, String msg) {
		super(msg);
		setErrorCode(errorCode);
    }
	
	/**
	 * 业务校验异常
	 * @param 	msg	错误信息
	 */
	public BusinessValidateException(String msg) {
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
