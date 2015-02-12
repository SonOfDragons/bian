package com.lencee.exception;

/**
 * 
 * 项目名称：common-simple 类名称：BusinessDealException 类描述： 创建人：刘刚 创建时间：2014年10月28日 上午10:50:33 修改人：刘刚 修改时间：2014年10月28日 上午10:50:33 修改备注：
 * 
 * @version
 * 
 */
public class BusinessDealException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7970147372045781333L;

	private String errorCode;

	public BusinessDealException(String errorCode, String msg) {
		super(msg);
		setErrorCode(errorCode);
	}

	public BusinessDealException(String msg) {
		super(msg);
		setErrorCode(errorCode);
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
