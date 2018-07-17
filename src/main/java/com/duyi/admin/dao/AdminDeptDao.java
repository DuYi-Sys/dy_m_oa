/**
 * 
 */
package com.duyi.admin.dao;


import java.util.List;

import com.duyi.admin.domain.AdminDeptInfo;

/**
 * @author wangyan
 *
 */
public interface AdminDeptDao {
	AdminDeptInfo add(AdminDeptInfo adminDept);
	AdminDeptInfo update(AdminDeptInfo adminDept);
	List<AdminDeptInfo> findAll();
	List<AdminDeptInfo> findAll(int startPosition,int maxResult);
	List<AdminDeptInfo> findAllByName(int startPosition,int maxResult,String name);

	int getCount();
	int getCountByName(String name);
	AdminDeptInfo getById(Long id);
	void deleteById(Long id);
}
