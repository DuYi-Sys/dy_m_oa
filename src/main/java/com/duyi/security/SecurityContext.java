/**
 * 
 */
package com.duyi.security;

import com.duyi.security.model.UserDetails;

/**
 * @author wangyan
 *
 */
public interface SecurityContext {

	UserDetails getPrincipal();

}
