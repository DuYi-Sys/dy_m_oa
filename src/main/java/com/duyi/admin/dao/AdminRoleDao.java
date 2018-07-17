/**
 * 
 */
package com.duyi.admin.dao;

import java.util.List;

import com.duyi.admin.domain.AdminRoleInfo;

/**
 * @author wangyan
 *
 */
public interface AdminRoleDao {
	AdminRoleInfo add(AdminRoleInfo role);
	AdminRoleInfo update(AdminRoleInfo role);
	AdminRoleInfo getById(Long id);
	AdminRoleInfo getByName(String name);

	List<AdminRoleInfo> findAll();
	List<AdminRoleInfo> findAll(int startPosition,int maxResult);
	List<AdminRoleInfo> findByName(int startPosition,int maxResult,String name);

	AdminRoleInfo findById();
	int getCount();
	int getCountByName(String name);

}
