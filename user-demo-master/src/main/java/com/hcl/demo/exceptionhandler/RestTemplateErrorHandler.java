package com.hcl.demo.exceptionhandler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

import com.hcl.demo.exceptionhandler.APIError;
import com.hcl.demo.exception.ApiClientException;

@Component
public class RestTemplateErrorHandler extends DefaultResponseErrorHandler {

	@Autowired
	private MappingJackson2HttpMessageConverter messageConverter;

	@Override
	public void handleError(ClientHttpResponse httpResponse) throws IOException {
		APIError error = (APIError) messageConverter.read(APIError.class, httpResponse);
		throw new ApiClientException(httpResponse.getStatusCode(), error);
	}

}