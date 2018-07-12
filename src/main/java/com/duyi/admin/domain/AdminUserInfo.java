/**
 * 
 */
package com.duyi.admin.domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author wangyan
 *
 */
public class AdminUserInfo  {

	protected Long id;
	
	protected String username;
	protected String name;
	
	protected String credential;
	
	protected boolean actived=true;
	
	protected String unencodePassword;

	protected List<AdminRoleInfo> roles;
	public String getUsername() {
		return this.username;
	}


	public String getCredential() {
		return this.credential;
	}


	public String getUnencodePassword() {
		return this.unencodePassword;
	}



	public List<AdminRoleInfo> getRoles() {
		return roles;
	}


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id=id;
	}



	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setCredential(String credential) {
		this.credential = credential;
	}


	public void setUnencodePassword(String unencodePassword) {
		this.unencodePassword = unencodePassword;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setRoles(List<AdminRoleInfo> roles) {
		this.roles = roles;
	}



}
