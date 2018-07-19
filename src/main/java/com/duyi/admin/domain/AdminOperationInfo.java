/**
 * 
 */
package com.duyi.admin.domain;

/**
 * @author wangyan
 *
 */

public class AdminOperationInfo{
	/**
	 * 
	 */

	private Long id;

	private String name;
	
	private String path;
	
	private String operation;
	
	
	
	public Long getId() {
		return id;
	}


	public String getName() {
		return this.name;
	}


	public String getPath() {
		return this.path;
	}


	public String getOperation() {
		return operation;
	}


	public void setOperation(String operation) {
		this.operation = operation;
	}


	public void setId(Long id) {
		this.id=id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPath(String path) {
		this.path = path;
	}


	@Override
	public String toString() {
		return "AdminOperationInfo [id=" + id + ", name=" + name + ", path=" + path + ", operation=" + operation + "]";
	}



}
