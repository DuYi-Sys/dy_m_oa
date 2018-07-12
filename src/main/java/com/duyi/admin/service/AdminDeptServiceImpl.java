/**
 * 
 */
package com.duyi.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.duyi.admin.dao.AdminDeptDao;
import com.duyi.admin.domain.AdminDeptInfo;
import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.commons.page.PageableExecutionUtils;
import com.duyi.commons.page.PageableExecutionUtils.TotalSupplier;
import com.google.common.base.Strings;

/**
 * @author wangyan
 *
 */
@Service
public class AdminDeptServiceImpl implements IAdminDeptService {

	@Autowired
	private AdminDeptDao deptDao;


	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminDeptService#addDept(com.duyi.admin.domain.AdminDeptInfo)
	 */
	@Override
	public AdminDeptInfo addDept(AdminDeptInfo dept) {
		Assert.notNull(dept);
		return deptDao.add(dept);

	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminDeptService#modifyDept(com.duyi.admin.domain.AdminDeptInfo)
	 */
	@Override
	public AdminDeptInfo modifyDept(AdminDeptInfo dept) {
		Assert.notNull(dept);
		return deptDao.update(dept);
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminDeptService#findDepts(com.duyi.commons.Pageable)
	 */
	@Override
	public Page<AdminDeptInfo> findDepts(Pageable pageable) {
		int startPosition=pageable.getOffset();
		int maxResult=pageable.getPageSize();
		List<AdminDeptInfo> depts=deptDao.findAll(startPosition, maxResult);
		return PageableExecutionUtils.getPage(depts, pageable, new TotalSupplier() {

			@Override
			public long get() {
				return deptDao.getCount();
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminDeptService#findAllDepts()
	 */
	@Override
	public List<AdminDeptInfo> findAllDepts() {
		return deptDao.findAll();
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminDeptService#findDeptsByName(com.duyi.commons.page.Pageable, java.lang.String)
	 */
	@Override
	public Page<AdminDeptInfo> findDeptsByName(Pageable pageable,final String name) {
		if(Strings.isNullOrEmpty(name)) {
			return this.findDepts(pageable);
		}
		int startPosition=pageable.getOffset();
		int maxResult=pageable.getPageSize();
		List<AdminDeptInfo> depts= deptDao.findAllByName(startPosition, maxResult, name);
		return PageableExecutionUtils.getPage(depts, pageable, new TotalSupplier() {

			@Override
			public long get() {
				return deptDao.getCountByName(name);
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminDeptService#deleteById(java.lang.Long)
	 */
	@Override
	public void deleteById(Long id) {
		deptDao.deleteById(id);
		
	}

}
