/**
 * 
 */
package com.duyi.admin.domain;

import java.util.List;

/**
 * @author wangyan
 *
 */
public class AdminRoleInfo  {

	private Long id;

	private String name;
	
	private String cnName;

	private List<AdminOperationInfo> operations;
	private List<AdminUserInfo> users;

	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}
	public void setId(Long id) {
		this.id=id;
	}

	public String getCnName() {
		return cnName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}


	public List<AdminOperationInfo> getOperations() {
		return operations;
	}


	public void setOperations(List<AdminOperationInfo> operations) {
		this.operations = operations;
	}


	public List<AdminUserInfo> getUsers() {
		return users;
	}


	public void setUsers(List<AdminUserInfo> users) {
		this.users = users;
	}
	
}
