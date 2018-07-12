/**
 * 
 */
package com.duyi.admin.dao;

import java.util.List;

import com.duyi.admin.domain.AdminOperationInfo;
import com.duyi.commons.page.Pageable;

/**
 * @author wangyan
 *
 */
public interface AdminOperationDao {
	AdminOperationInfo getByPath(String path);
	AdminOperationInfo add(AdminOperationInfo operation);
	AdminOperationInfo update(AdminOperationInfo operation);
	void deleteById(Long id);
	int getCount();
	int getCountByName();
	List<AdminOperationInfo> findAllByName(int startPosition,int maxResult,String name);

	List<AdminOperationInfo> findAll(int startPosition,int maxResult);
	AdminOperationInfo findById();
}
