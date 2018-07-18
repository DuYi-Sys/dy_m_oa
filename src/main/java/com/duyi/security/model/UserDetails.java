/**
 * 
 */
package com.duyi.security.model;

import java.util.List;

/**
 * @author wangyan
 *
 */
public interface UserDetails {
	Long getId();
	String getName();
	String getUsername();
	String getPassword();
	List<String> getRoles();
}
