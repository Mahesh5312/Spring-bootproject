package com.hcl.demo.jwt;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class JwtTokenResponse {
	
	@NonNull
	private String jwt;
	
	@NonNull
	private Date expiresOn;
}
