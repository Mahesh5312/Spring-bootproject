package com.hcl.demo.exception;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import com.hcl.demo.exceptionhandler.APIError;
import lombok.Getter;


@Getter
public class ApiClientException extends IOException {

	private static final long serialVersionUID = 1L;

	private APIError error;
	private HttpStatus status;

	public ApiClientException(HttpStatus status, APIError error, String message, Throwable exception) {
		super(message, exception);
		this.error = error;
		this.status = status;
	}

	public ApiClientException(HttpStatus status, APIError error) {
		this.error = error;
		this.status = status;
	}

	public ApiClientException(APIError error, String message, Throwable exception) {
		super(message, exception);
		this.error = error;
	}

	public ApiClientException(String message, Throwable exception) {
		super(message, exception);
	}

	public ApiClientException(Throwable exception) {
		super(exception);
	}

	public ApiClientException(String message) {
		super(message);
	}
}
