/**
 * 
 */
package com.duyi.admin.service;

import java.util.List;

import com.duyi.admin.domain.AdminRoleInfo;
import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;

/**
 * @author wangyan
 *
 */
public interface IAdminRoleService {
	AdminRoleInfo getByName(String name);
	AdminRoleInfo addRole(AdminRoleInfo role);
	AdminRoleInfo modifyRole(AdminRoleInfo role);
	Page<AdminRoleInfo> findRoles(Pageable pageable);
	List<AdminRoleInfo> findAllRoles();
	Page<AdminRoleInfo> findRolesByName(String name,Pageable pageable);

}
