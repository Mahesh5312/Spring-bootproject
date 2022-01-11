package com.hcl.demo.exceptionhandler;

 
public class APIError {

	public String code;
	
	private String message;
	
    public APIError(String code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	public String getCode() {
		return code;
	}
	
}