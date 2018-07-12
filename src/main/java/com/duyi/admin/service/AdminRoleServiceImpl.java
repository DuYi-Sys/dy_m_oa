/**
 * 
 */
package com.duyi.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.duyi.admin.dao.AdminRoleDao;
import com.duyi.admin.domain.AdminRoleInfo;
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
public class AdminRoleServiceImpl implements IAdminRoleService {

	@Autowired
	private AdminRoleDao roleDao;
	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminRoleService#getByName(java.lang.String)
	 */
	@Override
	public AdminRoleInfo getByName(String name) {
		return roleDao.getByName(name);
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminRoleService#addRole(com.duyi.admin.domain.AdminRoleInfo)
	 */
	@Override
	public AdminRoleInfo addRole(AdminRoleInfo role) {
		Assert.notNull(role);
		return roleDao.add(role);

	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminRoleService#modifyRole(com.duyi.admin.domain.AdminRoleInfo)
	 */
	@Override
	public AdminRoleInfo modifyRole(AdminRoleInfo role) {
		Assert.notNull(role);
		return roleDao.update(role);
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminRoleService#findRoles(com.duyi.commons.Pageable)
	 */
	@Override
	public Page<AdminRoleInfo> findRoles(Pageable pageable) {
		int startPosition=pageable.getOffset();
		int maxResult=pageable.getPageSize();
		
		List<AdminRoleInfo> roles=roleDao.findAll(startPosition, maxResult);
		return PageableExecutionUtils.getPage(roles, pageable, new TotalSupplier() {

			@Override
			public long get() {
				return roleDao.getCount();
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminRoleService#findAllRoles()
	 */
	@Override
	public List<AdminRoleInfo> findAllRoles() {
		return roleDao.findAll();
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminRoleService#findUsersByName(java.lang.String, com.duyi.commons.Pageable)
	 */
	@Override
	public Page<AdminRoleInfo> findRolesByName(final String name, Pageable pageable) {
		if(Strings.isNullOrEmpty(name)) {
			return findRoles(pageable);
		}
		int startPosition=pageable.getOffset();
		int maxResult=pageable.getPageSize();
		
		List<AdminRoleInfo> roles=roleDao.findByName(startPosition, maxResult, name);
		return PageableExecutionUtils.getPage(roles, pageable, new TotalSupplier() {

			@Override
			public long get() {
				return roleDao.getCountByName(name);
			}
			
		});
	}

}
