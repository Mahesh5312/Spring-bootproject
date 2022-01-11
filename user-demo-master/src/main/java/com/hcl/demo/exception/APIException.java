package com.hcl.demo.exception;

import lombok.Getter;


@Getter
public class APIException extends Exception {

	private static final long serialVersionUID = 1L;

	private String code;

	public APIException(String code, Throwable ex) {
		super(code, ex);
		this.code = code;
	}

}

