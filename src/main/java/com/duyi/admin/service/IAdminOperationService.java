/**
 * 
 */
package com.duyi.admin.service;

import java.util.List;

import com.duyi.admin.domain.AdminOperationInfo;
import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;

/**
 * @author wangyan
 *
 */
public interface IAdminOperationService {
	List<AdminOperationInfo> findByPath(String path);

	AdminOperationInfo addOperation(AdminOperationInfo operation);
	AdminOperationInfo modifyOperation(AdminOperationInfo operation);
	Page<AdminOperationInfo> findAll(Pageable pageable );
	void deleteAdminOperation(Long id);
}
