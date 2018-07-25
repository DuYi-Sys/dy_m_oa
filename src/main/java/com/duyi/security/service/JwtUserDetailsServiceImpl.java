/**
 * 
 */
package com.duyi.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duyi.admin.domain.AdminRoleInfo;
import com.duyi.admin.domain.AdminUserInfo;
import com.duyi.admin.service.IAdminUserService;
import com.duyi.commons.log.Trace;
import com.duyi.security.jwt.model.JwtUser;
import com.duyi.security.model.UserDetails;

/**
 * @author wangyan
 *
 */
@Service
public class JwtUserDetailsServiceImpl implements IUserDetailsService {

	private static Trace log=Trace.register(JwtUserDetailsServiceImpl.class);
	@Autowired
	private IAdminUserService adminUserService;

	/* (non-Javadoc)
	 * @see com.duyi.security.service.IUserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		AdminUserInfo user=adminUserService.getByUsername(username);
		if(user!=null) {
			JwtUser jwtUser = new JwtUser();
			jwtUser.setId(user.getId());
			jwtUser.setUsername(username);
			jwtUser.setPassword(user.getCredential());
			jwtUser.setName(user.getName());
			List<String> roles=new ArrayList<>();
			List<AdminRoleInfo> adminRoles=user.getRoles();
			log.info("ADMIN ROLES=="+adminRoles);
			if(adminRoles!=null) {
				for(AdminRoleInfo adminRole :adminRoles) {
					roles.add(adminRole.getName());
				}
			}
			jwtUser.setRoles(roles);
			return jwtUser;
		}
		return null;
	}

}
