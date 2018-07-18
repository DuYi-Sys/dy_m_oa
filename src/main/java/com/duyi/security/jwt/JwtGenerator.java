/**
 * 
 */
package com.duyi.security.jwt;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.duyi.commons.utils.TimeUtils;
import com.duyi.security.jwt.model.JwtUser;
import com.duyi.security.model.UserDetails;

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author wangyan
 *
 */
@Service
public class JwtGenerator implements IJwtGenerator {

//	@Value("${jwt.secret:mySecret}")
	private String secret = "mySecret";
	
//	@Value("${jwt.expire:30}")
	private Long expireMinutes=30L;

	/* (non-Javadoc)
	 * @see com.goldidea.common.security.jwt.IJwtGenerator#parseToken(java.lang.String)
	 */
	@Override
	public UserDetails parseToken(String token) {
		
		Claims body;
		try {
			body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (ClaimJwtException e) {
			return null;
		}
		JwtUser user = new JwtUser();
		user.setId(Long.parseLong(body.getSubject()));
		user.setUsername((String)body.get("username"));
		user.setName((String)body.get("name"));
		user.setRoles((List<String>)body.get("roles"));
		return user;
	}

	/* (non-Javadoc)
	 * @see com.goldidea.common.security.jwt.IJwtGenerator#generateToken(com.goldidea.common.security.model.IJwtUser)
	 */
	@Override
	public String generateToken(UserDetails user) {
		
		Claims claims = Jwts.claims().setSubject(user.getId().toString());
		
		
		claims.put("username", user.getUsername());
		claims.put("name", user.getName());
		
		

		claims.put("roles", user.getRoles());
		
		claims.setExpiration(TimeUtils.plusMinutes(new Date(), expireMinutes));
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
	}



}
