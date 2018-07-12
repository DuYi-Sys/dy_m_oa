/**
 * 
 */
package com.duyi.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.duyi.admin.dao.AdminOperationDao;
import com.duyi.admin.domain.AdminOperationInfo;
import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.commons.page.PageableExecutionUtils;
import com.duyi.commons.page.PageableExecutionUtils.TotalSupplier;

/**
 * @author wangyan
 *
 */
@Service
public class AdminOpetationServiceImpl implements IAdminOperationService {

	@Autowired
	private AdminOperationDao operationDao;
	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminOpetationService#getByPath(java.lang.String)
	 */
	@Override
	public AdminOperationInfo getByPath(String path) {
		return operationDao.getByPath(path);
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminOpetationService#addOperation(com.duyi.admin.domain.AdminOperationInfo)
	 */
	@Override
	public AdminOperationInfo addOperation(AdminOperationInfo operation) {
		Assert.notNull(operation);
		return operationDao.add(operation);
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminOpetationService#modifyOperation(com.duyi.admin.domain.AdminOperationInfo)
	 */
	@Override
	public AdminOperationInfo modifyOperation(AdminOperationInfo operation) {
		Assert.notNull(operation);
		return operationDao.update(operation);
	}



	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminOpetationService#deleteAdminOperation(java.lang.Long)
	 */
	@Override
	public void deleteAdminOperation(Long id) {
		operationDao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminOperationService#findByName(com.duyi.commons.page.Pageable, java.lang.String)
	 */
	@Override
	public Page<AdminOperationInfo> findAllByName(Pageable pageable, String name) {
		int startPosition=pageable.getOffset();
		int maxResult=pageable.getPageSize();
		List<AdminOperationInfo> operations=operationDao.findAllByName(startPosition, maxResult, name);
		return PageableExecutionUtils.getPage(operations, pageable, new TotalSupplier() {
			@Override
			public long get() {
				return operationDao.getCountByName();
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminOperationService#findAll(com.duyi.commons.page.Pageable)
	 */
	@Override
	public Page<AdminOperationInfo> findAll(Pageable pageable) {
		int startPosition=pageable.getOffset();
		int maxResult=pageable.getPageSize();
		List<AdminOperationInfo> operations=operationDao.findAll(startPosition, maxResult);
		return PageableExecutionUtils.getPage(operations, pageable, new TotalSupplier() {
			@Override
			public long get() {
				return operationDao.getCount();
			}
			
		});
	}

}
