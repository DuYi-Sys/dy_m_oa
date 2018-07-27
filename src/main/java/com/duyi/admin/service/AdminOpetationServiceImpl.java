/**
 * 
 */
package com.duyi.admin.service;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.duyi.admin.dao.AdminOperationDao;
import com.duyi.admin.domain.AdminOperationInfo;
import com.duyi.commons.log.Trace;
import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.commons.page.PageableExecutionUtils;
import com.duyi.commons.page.PageableExecutionUtils.TotalSupplier;

/**
 * @author wangyan
 *
 */
@Service
public class AdminOpetationServiceImpl  implements IAdminOperationService {

	private static Trace log=Trace.register(AdminOpetationServiceImpl.class);
	
	@Autowired
	private AdminOperationDao operationDao;
	
	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminOpetationService#getByPath(java.lang.String)
	 */
	@Override
	public List<AdminOperationInfo> findByPath(String path) {
		return operationDao.findByPath(path);
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminOpetationService#addOperation(com.duyi.admin.domain.AdminOperationInfo)
	 */
	@Override
	public AdminOperationInfo addOperation(AdminOperationInfo operation) {
		Assert.notNull(operation);
		int row=operationDao.add(operation);
		return operation;
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminOpetationService#modifyOperation(com.duyi.admin.domain.AdminOperationInfo)
	 */
	@Override
	public AdminOperationInfo modifyOperation(AdminOperationInfo operation) {
		Assert.notNull(operation);
		int row=operationDao.update(operation);
		return operation;
	}



	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminOpetationService#deleteAdminOperation(java.lang.Long)
	 */
	@Override
	public void deleteAdminOperation(Long id) {
		operationDao.deleteById(id);
	}



	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminOperationService#findAll(com.duyi.commons.page.Pageable)
	 */
	@Override
	public Page<AdminOperationInfo> findAll(Pageable pageable) {
		final int count=operationDao.getCount();
		log.info(pageable);
		pageable=PageableExecutionUtils.calculatePageable(count, pageable);
		log.info(pageable);
		RowBounds bounds=new RowBounds(pageable.getOffset(),pageable.getPageSize());

		List<AdminOperationInfo> operations=operationDao.findAll(bounds);
		return PageableExecutionUtils.getPage(operations, pageable, new TotalSupplier() {
			@Override
			public long get() {
				return count;
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminOperationService#getById(java.lang.Long)
	 */
	@Override
	public AdminOperationInfo getById(Long id) {
		return operationDao.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminOperationService#findByName(java.lang.String)
	 */
	@Override
	public List<AdminOperationInfo> findByName(String name) {
		return operationDao.findByName(name);
	}






}
