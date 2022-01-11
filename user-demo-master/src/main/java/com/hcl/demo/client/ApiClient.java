package com.hcl.demo.client;

import java.util.*;
import org.springframework.http.*;
import org.springframework.web.context.request.*;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import com.hcl.demo.entities.*;



/**
 * The Class ApiClient.
 */
@Component
public class ApiClient {

	private static final Logger log = LogManager.getLogger(ApiClient.class);

	@Value("${core.server.url}")
	private String url;
	
	@Value("${custom.header}")
	private String customHeader;

	private HttpHeaders getToken() {
		HttpHeaders headers = new HttpHeaders();
		final String token = getCurrentRequest().getHeader("Authorization");
		final String transactionId = getCurrentRequest().getHeader(customHeader);
		if(transactionId != null && transactionId.length()>0) {
			headers.add(customHeader, transactionId);
		}else {
			String newtransactionId = ThreadContext.get("TxId").toString();
			newtransactionId = newtransactionId.substring(newtransactionId.indexOf('=') + 1);
			headers.add(customHeader, newtransactionId);
		}
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("User-Agent", "Spring's RestTemplate");
		headers.add("Authorization", token);
		return headers;
	}

	private static HttpServletRequest getCurrentRequest() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		HttpServletRequest servletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
		return servletRequest;
	}

	/**
	 * getUser.
	 *
     * @param userId the userId
	 * @return the Users
	 */

	public Users getUser(int userId) {
		long startTime = System.currentTimeMillis();
		log.info("Entering into the ApiClient.getUser method with UserId: {}", userId);
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = getToken();
		ResponseEntity<Users> response;
		 response = rest.exchange(url + "/users/" + userId, HttpMethod.GET,
				new HttpEntity<>(null, headers), Users.class);
		log.info("Exiting from the ApiClient.getUser method with UserId: {} in {}ms", userId, System.currentTimeMillis()-startTime);
		return response.getBody();
	}
	
	/**
	 * getallUsers.
	 *
     * @param page the page
     * @param size the size
     * @param orderBy the orderBy
	 * @return the List of Users
	 */

	public List<Users> getallUsers(Integer page, Integer size, String orderBy) {
		long startTime = System.currentTimeMillis();
		log.info("Entering into the ApiClient.getallUsers method ");
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = getToken();
		ResponseEntity<List<Users>> response;
		if(page==null){
		response = rest.exchange(url + "/users", HttpMethod.GET,
				new HttpEntity<>(null, headers), new ParameterizedTypeReference<List<Users>>(){});
		 }else{
		response = rest.exchange(url + "/users"+"?page="+page+"&size="+size, HttpMethod.GET,
				new HttpEntity<>(null, headers), new ParameterizedTypeReference<List<Users>>(){});
		}
		log.info("Exiting from the ApiClient.getallUsers method in {}ms",  System.currentTimeMillis()-startTime);
		return response.getBody();
	}
	
	/**
	 * createUser.
	 *
     * @param CreateUser the CreateUser
	 * @return the Users
	 */

	public Users createUser(Users CreateUser) {
		long startTime = System.currentTimeMillis();
		log.info("Entering into the ApiClient.createUser method ");
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = getToken();
		ResponseEntity<Users> response;
		 response = rest.exchange(url + "/users", HttpMethod.POST,
				new HttpEntity<>(CreateUser, headers), Users.class);
		log.info("Exiting from the ApiClient.createUser method in {}ms",  System.currentTimeMillis()-startTime);
		return response.getBody();
	}
	
	/**
	 * deleteUser.
	 *
     * @param userId the userId
	 * @return the Users
	 */

	public Users deleteUser(int userId) {
		long startTime = System.currentTimeMillis();
		log.info("Entering into the ApiClient.deleteUser method ");
		RestTemplate rest = new RestTemplate();
		HttpHeaders headers = getToken();
		ResponseEntity<Users> response;
		response = rest.exchange(url + "/users?userId=" + userId, HttpMethod.DELETE,
				new HttpEntity<>(userId, headers), Users.class);
		log.info("Exiting from the ApiClient.deleteUser method in {}ms",  System.currentTimeMillis()-startTime);
		return response.getBody();
	}
	
}