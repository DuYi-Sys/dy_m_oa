/**
 * 
 */
package com.duyi.security.jwt.model;

import java.util.ArrayList;
import java.util.List;

import com.duyi.security.model.UserDetails;

/**
 * @author wangyan
 *
 */
public class JwtStudent implements UserDetails {

	private Long id;
	private String nickName;
	private String username;
	private String password;
	private boolean vip;

	/* (non-Javadoc)
	 * @see com.duyi.security.model.UserDetails#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see com.duyi.security.model.UserDetails#getName()
	 */
	@Override
	public String getName() {
		return nickName;
	}

	/* (non-Javadoc)
	 * @see com.duyi.security.model.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/* (non-Javadoc)
	 * @see com.duyi.security.model.UserDetails#getPassword()
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/* (non-Javadoc)
	 * @see com.duyi.security.model.UserDetails#getRoles()
	 */
	@Override
	public List<String> getRoles() {
		List<String> roles=new ArrayList<>();
		if(vip) {
			roles.add("vip");
		}
		return roles;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
