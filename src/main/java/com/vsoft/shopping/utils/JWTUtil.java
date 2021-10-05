package com.vsoft.shopping.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Component
public class JWTUtil {
	private String jwtSecret = "randondummypassword" ;
	
	private String issuer = "vsfot" ;
	
	public String generateToken(String email) {
		 Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
		 Date expiresAt = new Date(System.currentTimeMillis() + (1000 * 60 * 10));
		String jwtToken = JWT.create().withIssuer(issuer).withSubject(email).withExpiresAt(expiresAt).sign(algorithm);
		System.out.println(jwtToken);
		return jwtToken;
	}

	public String verify(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
			JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build(); // Reusable verifier instance
			DecodedJWT jwt = verifier.verify(token);
			return jwt.getSubject();
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
		
	}
	
}
