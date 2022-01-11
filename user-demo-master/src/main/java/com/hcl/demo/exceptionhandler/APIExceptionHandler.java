package com.hcl.demo.exceptionhandler;

import javax.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class APIExceptionHandler extends BaseExceptionHandler {

	public APIExceptionHandler() {
		super("API");
	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<APIError> handleException(HttpServletRequest req, Throwable ex) {
		String code = super.handleException(BaseMessageConstants.API, null, ex);
		APIError apiError = new APIError(code);
		apiError.setMessage(ex.getMessage());
		return new ResponseEntity<APIError>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

	
