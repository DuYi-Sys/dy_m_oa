/**
 * 
 */
package com.duyi.security.jwt;

import com.duyi.security.model.UserDetails;

/**
 * @author wangyan
 *
 */
public interface IJwtGenerator {
	UserDetails parseToken(String token);
	String generateToken(UserDetails user);
}
