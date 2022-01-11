package com.hcl.demo.exceptionhandler;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BusinessExceptionHandler  extends BaseExceptionHandler {
	public BusinessExceptionHandler() {
		super("Business");
	}

	@AfterThrowing(pointcut = "execution(* com.hcl.demo.service.*.*(..))", throwing = "ex")
	public void handleException(Throwable ex) {
		super.handleException(BaseMessageConstants.BUSINESS, null, ex);
	}

}

	
