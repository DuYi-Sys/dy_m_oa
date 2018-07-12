/**
 * 
 */
package com.duyi.admin.service;

import com.duyi.admin.domain.AdminOperationInfo;
import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;

/**
 * @author wangyan
 *
 */
public interface IAdminOperationService {
	AdminOperationInfo getByPath(String path);

	AdminOperationInfo addOperation(AdminOperationInfo operation);
	AdminOperationInfo modifyOperation(AdminOperationInfo operation);
	Page<AdminOperationInfo> findAllByName(Pageable pageable ,String name);
	Page<AdminOperationInfo> findAll(Pageable pageable );

	void deleteAdminOperation(Long id);
}
