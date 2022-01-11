package com.hcl.demo;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.hcl.demo.helper.LoggingFilterHelper;
import org.springframework.web.client.RestTemplate;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.scheduling.annotation.EnableAsync;
import com.hcl.demo.exceptionhandler.AsyncExceptionHandler;
import com.hcl.demo.exceptionhandler.RestTemplateErrorHandler;
import com.hcl.demo.helper.ExceptionFilter;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;

/**
 * The Class AppConfig.
 */
 @Configuration
 @EnableAsync
 public class AppConfig  extends AsyncConfigurerSupport {
 	@Bean
	public FilterRegistrationBean<LoggingFilterHelper> loggingFilter() {
		return new FilterRegistrationBean<>(new LoggingFilterHelper());
	}
    @Bean
	public FilterRegistrationBean<ExceptionFilter> exceptionFilter() {
		return new FilterRegistrationBean<>(new ExceptionFilter());
	}
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new AsyncExceptionHandler();
	}
	
	@Bean
	RestTemplate restTemplate(RestTemplateErrorHandler rteh) {
		RestTemplate rest = new RestTemplate();
		rest.setErrorHandler(rteh);
		return rest;
	}
 
 }