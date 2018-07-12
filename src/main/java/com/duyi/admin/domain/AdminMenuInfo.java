/**
 * 
 */
package com.duyi.admin.domain;

/**
 * @author wangyan
 *
 */

public class AdminMenuInfo {

	private Long id;
	private String name;
	
	private String path;
	
	public Long getId() {
		return this.id;
	}


	public String getName() {
		return this.name;
	}


	public String getPath() {
		return this.path;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPath(String path) {
		this.path = path;
	}




	
}
