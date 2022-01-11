package com.hcl.demo.exceptionhandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


public class BaseExceptionHandler  {

	@Value("${exception.suppress}")
	private boolean suppress;

	@Autowired
	private BaseMessageHandler messageHandler;

	private static final Logger log = LogManager.getLogger(BaseExceptionHandler.class);

	private String name;

	private static ThreadLocal<String> CURRENT_CODE = new ThreadLocal<>();
	
	private static ThreadLocal<Throwable> CURRENT_EXCEPTION = new ThreadLocal<>();

	public BaseExceptionHandler(String name) {
		this.name = name;
	}

	public String handleException(String code, Object[] args, Throwable ex) {
		if (CURRENT_EXCEPTION.get() == null || CURRENT_EXCEPTION.get() != ex) {
			CURRENT_CODE.set(code);
			CURRENT_EXCEPTION.set(ex);
		} else {
			if (suppress) {
				return CURRENT_CODE.get();
			}
		}

		log.error("Caught " + name + " : " + messageHandler.getMessage(code, args), ex);
		return code;
	}

	public static void reset() {
		CURRENT_CODE.remove();
		CURRENT_EXCEPTION.remove();
	}


}