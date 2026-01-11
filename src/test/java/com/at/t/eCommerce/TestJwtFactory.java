package com.at.t.eCommerce;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TestJwtFactory {
	   public static String createUserJwt(String userId) {
	        return Jwts.builder()
	            .setSubject(userId)
	            .signWith(Keys.hmacShaKeyFor("test-secret-test-secret-test-secret".getBytes()))
	            .compact();
	    }
}
