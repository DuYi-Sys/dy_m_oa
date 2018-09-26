/**
 * 
 */
package com.duyi.commons.validate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author wangyan
 *
 */
@ControllerAdvice
public class SpringValidateExcepionHandler {


	@ExceptionHandler(WebException.class)
	public ResponseEntity<WebExMesage> webException(HttpServletRequest req, WebException e) 
	{
		
		return new ResponseEntity<WebExMesage>(new WebExMesage(e.getCode(),e.getMessage()), HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		
	}

	static class WebExMesage{
		private int code;
		private String message;
		
		/**
		 * @param code
		 * @param message
		 */
		public WebExMesage(int code, String message) {
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
			return "WebExMesage [code=" + code + ", message=" + message + "]";
		}
		
		
	}
}
