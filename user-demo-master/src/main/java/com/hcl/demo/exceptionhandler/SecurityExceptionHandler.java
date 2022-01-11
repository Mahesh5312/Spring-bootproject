package com.hcl.demo.exceptionhandler;

import org.springframework.stereotype.Component;

@Component
public class SecurityExceptionHandler  extends BaseExceptionHandler {
	public SecurityExceptionHandler() {
		super("Security");
	}

	public void handleException(Throwable ex) {
		super.handleException(BaseMessageConstants.SECURITY, null, ex);
	}

}


	
