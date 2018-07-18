/**
 * 
 */
package com.duyi.security;

import com.duyi.security.model.UserDetails;

/**
 * @author wangyan
 *
 */
public class SecurityContextImpl implements SecurityContext {

	private UserDetails principal;
	/* (non-Javadoc)
	 * @see com.duyi.security.SecurityContext#getPrincipal()
	 */
	@Override
	public UserDetails getPrincipal() {
		return principal;
	}
	public void setPrincipal(UserDetails principal) {
		this.principal = principal;
	}

}
