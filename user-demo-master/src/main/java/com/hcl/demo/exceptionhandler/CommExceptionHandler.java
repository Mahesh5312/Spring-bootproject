package com.hcl.demo.exceptionhandler;

import org.springframework.stereotype.Component;

@Component
public class CommExceptionHandler extends BaseExceptionHandler {

	public CommExceptionHandler() {
		super("Communication");
	}

	public void handleException(Throwable ex) {
		super.handleException(BaseMessageConstants.COMMUNICATION, null, ex);
	}


}

	
