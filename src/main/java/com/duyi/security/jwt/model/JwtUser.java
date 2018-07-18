/**
 * 
 */
package com.duyi.security.jwt.model;

import java.util.Collections;
import java.util.List;

import com.duyi.security.model.UserDetails;


/**
 * @author wangyan
 *
 */

public class JwtUser implements UserDetails {

	private Long id;
	private String name;
	private String username;
	private String password;

	private List<String> roles;
	
	

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}





	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		return username;
	}


	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.duyi.security.model.UserDetails#getRoles()
	 */
	@Override
	public List<String> getRoles() {
		return Collections.unmodifiableList(roles);
	}


	

}
