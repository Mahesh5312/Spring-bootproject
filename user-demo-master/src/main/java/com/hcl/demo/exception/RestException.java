package com.hcl.demo.exception;

import org.springframework.http.HttpStatus;

public class RestException extends Exception {
	private static final long serialVersionUID = 1L;

	private final HttpStatus httpStatus;
	private final String message;

	public RestException(HttpStatus httpStatus, String message, Throwable ex) {
		super(ex);

		this.httpStatus = httpStatus;
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getMessage() {
		return message;
	}

}