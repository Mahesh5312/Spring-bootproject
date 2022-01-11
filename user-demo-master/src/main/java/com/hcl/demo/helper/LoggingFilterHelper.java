package com.hcl.demo.helper;

import java.util.UUID;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.InputStream;
import java.util.Properties;

/**
 * The Class LoggingFilterHelper.
 */
 public class LoggingFilterHelper extends OncePerRequestFilter {
 
 private String customHeader;
 private static final String TXID_KEY = "TxId";
 private String customHeaderUserid;
 private static final String USER_ID = "UserId";
 
 	public LoggingFilterHelper() {
 		try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            customHeader = prop.getProperty("custom.header");
            customHeaderUserid = prop.getProperty("custom.header.userid");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
 	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			if(user!=null && user.length()>0) {
				ThreadContext.put(USER_ID, USER_ID + "=" + user);
				request.setAttribute(customHeaderUserid, ThreadContext.get(USER_ID));
				}
			final String transactionId = request.getHeader(customHeader);
			if(transactionId != null && transactionId.length()>0)
				ThreadContext.put(TXID_KEY, TXID_KEY + "=" + transactionId);
			else {
				ThreadContext.put(TXID_KEY, TXID_KEY + "=" + UUID.randomUUID().toString());
				request.setAttribute(customHeader, ThreadContext.get(TXID_KEY));
			}
			filterChain.doFilter(request, response);
		} finally {
			ThreadContext.clearAll();
		}
	}
 
 }