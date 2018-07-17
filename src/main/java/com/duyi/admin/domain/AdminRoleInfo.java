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

	private List<AdminDeptInfo> depts;
	
	private List<AdminMenuInfo> menus;
	
	private List<AdminOperationInfo> operations;
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


	public List<AdminDeptInfo> getDepts() {
		return depts;
	}


	public void setDepts(List<AdminDeptInfo> depts) {
		this.depts = depts;
	}


	public List<AdminMenuInfo> getMenus() {
		return menus;
	}


	public void setMenus(List<AdminMenuInfo> menus) {
		this.menus = menus;
	}


	public List<AdminOperationInfo> getOperations() {
		return operations;
	}


	public void setOperations(List<AdminOperationInfo> operations) {
		this.operations = operations;
	}
	
}
