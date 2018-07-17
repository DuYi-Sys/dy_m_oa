/**
 * 
 */
package com.duyi.admin.dao;

import java.util.List;

import com.duyi.admin.domain.AdminUserInfo;

/**
 * @author wangyan
 *
 */
public interface AdminUserDao {
	AdminUserInfo add(AdminUserInfo user);
	AdminUserInfo update(AdminUserInfo user);
	List<AdminUserInfo> findAll();
	List<AdminUserInfo> findAll(int startPosition,int maxResult);
	List<AdminUserInfo> findByUsername(String username,int startPosition,int maxResult);

	AdminUserInfo getById(Long id);
	AdminUserInfo getByUsername(String username);
	int getCount();
	int getCountByUsername(String username);

}
