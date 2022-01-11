package com.hcl.demo.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class UncaughtExceptionHandler  extends BaseExceptionHandler
		implements java.lang.Thread.UncaughtExceptionHandler {

	public UncaughtExceptionHandler() {
		super("");
	}

	@Override
	public void uncaughtException(Thread t, Throwable ex) {
		super.handleException(BaseMessageConstants.UNCAUGHT, null, ex);
	}

}
	
