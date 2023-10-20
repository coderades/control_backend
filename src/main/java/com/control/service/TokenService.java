package com.control.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.control.model.User;

@Service
public class TokenService {
	public String create(User user) {
		return JWT.create().withIssuer("control").withSubject(user.getUsername()).withClaim("id", user.getUserId())
				.withExpiresAt(LocalDateTime.now().plusSeconds(getWithExpiresAt()).toInstant(ZoneOffset.of("-03:00")))
				.sign(Algorithm.HMAC512("r%FL<67Hzq7;p'}p}s%Y!>b|&ittk7'?K=T}AsJvcQc6Jg`/z`,LTeK4\"ao_cQ9"));
	}

	public String getSubject(String token) {
		return JWT.require(Algorithm.HMAC512("r%FL<67Hzq7;p'}p}s%Y!>b|&ittk7'?K=T}AsJvcQc6Jg`/z`,LTeK4\"ao_cQ9"))
				.withIssuer("control").build().verify(token).getSubject();
	}

	public int getWithExpiresAt() {
		return 3600;
	}

}