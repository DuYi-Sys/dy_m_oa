/**
 * 
 */
package com.duyi.security.service;

import com.duyi.security.model.UserDetails;

/**
 * @author wangyan
 *
 */
public interface IUserDetailsService {

	UserDetails loadUserByUsername(String username) ;

}
