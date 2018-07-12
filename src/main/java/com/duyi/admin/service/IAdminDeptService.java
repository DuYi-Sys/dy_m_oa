/**
 * 
 */
package com.duyi.admin.service;

import java.util.List;

import com.duyi.admin.domain.AdminDeptInfo;
import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;

/**
 * @author wangyan
 *
 */
public interface IAdminDeptService {
	AdminDeptInfo addDept(AdminDeptInfo dept);
	AdminDeptInfo modifyDept(AdminDeptInfo dept);
	Page<AdminDeptInfo> findDepts(Pageable pageable);
	Page<AdminDeptInfo> findDeptsByName(Pageable pageable,String name);
	void deleteById(Long id);
	List<AdminDeptInfo> findAllDepts();
}
