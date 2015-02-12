package com.lencee.exception;

/**
 * 配置异常
 * @author 		yangjie
 * @created	2014.05.19
 *
 */
public class ConfigException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6163420665638751581L;
	
	private String errorCode;
	
	public ConfigException(String errorCode, String msg) {
		super(msg);
		setErrorCode(errorCode);
    }
	
	public ConfigException(String msg) {
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
