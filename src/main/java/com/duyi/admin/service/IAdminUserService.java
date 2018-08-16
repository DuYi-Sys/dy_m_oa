/**
 * 
 */
package com.duyi.admin.service;

import java.util.List;

import com.duyi.admin.domain.AdminUserInfo;
import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;

/**
 * @author wangyan
 *
 */
public interface IAdminUserService {
	
	AdminUserInfo getByUsername(String username);
	AdminUserInfo addAdminUser(AdminUserInfo user);
	AdminUserInfo modifyAdminUser(AdminUserInfo user);
	Page<AdminUserInfo> findUsers(Pageable pageable);
	Page<AdminUserInfo> findUsersByUsername(Pageable pageable,String username);
	Page<AdminUserInfo> findUsersByName(Pageable pageable,String name);
	List<AdminUserInfo> findUsersByName(String name);
	List<AdminUserInfo> findUsersByRoleId(Long roleId);
	
}
