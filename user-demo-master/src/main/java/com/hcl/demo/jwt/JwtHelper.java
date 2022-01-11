package com.hcl.demo.jwt;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;


@Component
public class JwtHelper {
	
	private final RSAPrivateKey privateKey;
	private final RSAPublicKey publicKey;
	private static final Logger log = LogManager.getLogger(JwtHelper.class);
	
	public JwtHelper(RSAPrivateKey privateKey, RSAPublicKey publicKey) {
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	}
	
	public JwtTokenResponse createJwtForClaims(String subject, Map<String, String> claims) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(Instant.now().toEpochMilli());
		//calendar.add(Calendar.DATE, 1);
		//calendar.roll(Calendar.HOUR_OF_DAY, 1);
		calendar.roll(Calendar.MINUTE, 5);
		
		JWTCreator.Builder jwtBuilder = JWT.create().withSubject(subject);
		
		// Add claims
		claims.forEach(jwtBuilder::withClaim);
		
		// Add expiredAt and etc
		String jwt = jwtBuilder
				.withNotBefore(new Date())
				.withExpiresAt(calendar.getTime())
				.sign(Algorithm.RSA256(publicKey, privateKey));
		
		JwtTokenResponse result = new JwtTokenResponse(jwt, calendar.getTime());
		return result;
	}
}