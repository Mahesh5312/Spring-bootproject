package com.hcl.demo.exceptionhandler;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DBExceptionHandler extends BaseExceptionHandler {
	public DBExceptionHandler() {
		super("DB");
	}

	@AfterThrowing(pointcut = "execution(* com.hcl.demo.repository.*.*(..))", throwing = "ex")
	public void handleException(Throwable ex) {
		super.handleException(BaseMessageConstants.DB, null, ex);
	}

}
	
