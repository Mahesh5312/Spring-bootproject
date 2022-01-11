package com.hcl.demo.exceptionhandler;

import java.lang.reflect.Method;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

@Component
public class AsyncExceptionHandler extends BaseExceptionHandler implements AsyncUncaughtExceptionHandler {

	public AsyncExceptionHandler() {
		super("Async");
	}

	@Override
	public void handleUncaughtException(Throwable ex, Method method, Object... params) {
		super.handleException(BaseMessageConstants.ASYNC, null, ex);
	}

}


	
