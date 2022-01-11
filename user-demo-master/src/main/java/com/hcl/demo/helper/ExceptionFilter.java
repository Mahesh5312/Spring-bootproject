package com.hcl.demo.helper;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hcl.demo.exceptionhandler.BaseExceptionHandler;


public class ExceptionFilter extends OncePerRequestFilter {
private static final Logger log = LogManager.getLogger(ExceptionFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			filterChain.doFilter(request, response);
		} catch (Throwable ex) {
			BaseExceptionHandler.reset();
			log.info("Throwing : " + ex.getMessage());
			throw ex;
		}
	}

}

	
