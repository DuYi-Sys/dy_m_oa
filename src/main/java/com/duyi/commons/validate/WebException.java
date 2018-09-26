/**
 * 
 */
package com.duyi.commons.validate;

/**
 * @author wangyan
 *
 */
public class WebException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9014689899003900267L;

	private int code;
	
	private String message;

	/**
	 * @param code
	 * @param message
	 */
	public WebException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "WebException [code=" + code + ", message=" + message + "]";
	}
	
	
}
