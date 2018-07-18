/**
 * 
 */
package com.duyi.security.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.duyi.security.jwt.model.JwtUser;
import com.duyi.security.model.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author wangyan
 *
 */
public abstract class AbstractJwtGenerator implements IJwtGenerator {
//	@Value("${jwt.secret}")
	private String secret = "mySecret";

	public UserDetails parseToken(String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
			JwtUser user = new JwtUser();
			user.setUsername(body.getSubject());
//			List<String> roles = (List<String>) body.get("roles");

			return user;
		} catch (JwtException | ClassCastException e) {
			return null;
		}
	}

	public String generateToken(UserDetails user) {
		Claims claims = Jwts.claims().setSubject(user.getUsername());
		

		claims.put("roles", user.getRoles());
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
}
