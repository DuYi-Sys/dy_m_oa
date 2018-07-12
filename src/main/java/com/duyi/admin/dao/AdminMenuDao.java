/**
 * 
 */
package com.duyi.admin.dao;

import java.util.List;
import java.util.Set;

import com.duyi.admin.domain.AdminMenuInfo;
import com.duyi.admin.domain.AdminRoleInfo;

/**
 * @author wangyan
 *
 */
public interface AdminMenuDao {
	AdminMenuInfo add(AdminMenuInfo menu);
	AdminMenuInfo update(AdminMenuInfo menu);
	AdminMenuInfo getByPath(String path);
	List<AdminMenuInfo> findByRoles(Set<AdminRoleInfo> roles);
	List<AdminMenuInfo> findAll();
	AdminMenuInfo findById();
	void delete(Long id);
	int getCount();
	List<AdminMenuInfo>  findAll(int startPosition,int maxResult);
	List<AdminMenuInfo>  findByName(int startPosition,int maxResult,String name);
	int getCountByName(String name);


}
