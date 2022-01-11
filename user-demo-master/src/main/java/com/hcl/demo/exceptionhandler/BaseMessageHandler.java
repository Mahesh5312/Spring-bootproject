package com.hcl.demo.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class BaseMessageHandler  {

	@Autowired
	private MessageSource messageSource;

	protected String getMessage(String code, Object... args) {
		return messageSource.getMessage(code, args, null);
	}

}

	
