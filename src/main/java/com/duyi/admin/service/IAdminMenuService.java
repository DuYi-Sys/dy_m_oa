/**
 * 
 */
package com.duyi.admin.service;

import java.util.List;
import java.util.Set;

import com.duyi.admin.domain.AdminMenuInfo;
import com.duyi.admin.domain.AdminRoleInfo;
import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;

/**
 * @author wangyan
 *
 */
public interface IAdminMenuService {
	AdminMenuInfo getByPath(String path);

	AdminMenuInfo addMenu(AdminMenuInfo menu);
	AdminMenuInfo modifyMenu(AdminMenuInfo menu);

	Page<AdminMenuInfo> findMenus(Pageable pageable);
	Page<AdminMenuInfo> findMenusByName(Pageable pageable,String name);
	List<AdminMenuInfo> findAllMenus();
	
	List<AdminMenuInfo> findByRoles(Set<AdminRoleInfo> roles);
	void deleteAdminMenu(Long id);
}
