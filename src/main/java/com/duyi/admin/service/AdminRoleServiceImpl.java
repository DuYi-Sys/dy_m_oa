/**
 * 
 */
package com.duyi.admin.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.duyi.admin.dao.AdminRoleDao;
import com.duyi.admin.domain.AdminRoleInfo;
import com.duyi.commons.log.Trace;
import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.commons.page.PageableExecutionUtils;
import com.duyi.commons.page.PageableExecutionUtils.TotalSupplier;
import com.google.common.base.Strings;

/**
 * @author wangyan
 *
 */
@Service("adminRoleService")
public class AdminRoleServiceImpl implements IAdminRoleService {

	private static Trace log=Trace.register(AdminRoleServiceImpl.class);
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
		roleDao.add(role);
		return role;
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminRoleService#modifyRole(com.duyi.admin.domain.AdminRoleInfo)
	 */
	@Override
	public AdminRoleInfo modifyRole(AdminRoleInfo role) {
		Assert.notNull(role);
		roleDao.update(role);
		return role;
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminRoleService#findRoles(com.duyi.commons.Pageable)
	 */
	@Override
	public Page<AdminRoleInfo> findRoles(Pageable pageable) {

		final int count= roleDao.getCount();

		pageable=PageableExecutionUtils.calculatePageable(count, pageable);


		RowBounds bounds=new RowBounds(pageable.getOffset(),pageable.getPageSize());
		List<AdminRoleInfo> roles=roleDao.findPageAll(bounds);
		return PageableExecutionUtils.getPage(roles, pageable, new TotalSupplier() {
			@Override
			public long get() {
				return count;
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
		
		final int count=roleDao.getCountByName(name);
		pageable=PageableExecutionUtils.calculatePageable(count, pageable);

		RowBounds bounds=new RowBounds(pageable.getOffset(),pageable.getPageSize());

		List<AdminRoleInfo> roles=roleDao.findByName(bounds, name);
		return PageableExecutionUtils.getPage(roles, pageable, new TotalSupplier() {

			@Override
			public long get() {
				return count;
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminRoleService#AddRoleUsers(java.lang.Long, java.lang.Long[])
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addRoleUsers(Long roleId, Long[] userIds) {
		for(Long userId:userIds) {
			log.info("roleId:"+roleId+", userIds:"+userId);
			AdminRoleInfo roleInfo=roleDao.getByRoleIdAndUserId(roleId, userId);
			if(roleInfo==null) {
				roleDao.addRoleUsers(roleId, userId);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminRoleService#getById(java.lang.Long)
	 */
	@Override
	public AdminRoleInfo getById(Long id) {
		return roleDao.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminRoleService#addRoleOperations(java.lang.Long, java.lang.Long[])
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addRoleOperations(Long roleId, Long[] operationIds) {
		for(Long operationId:operationIds) {
			log.info("roleId:"+roleId+", operationIds:"+operationId);
			AdminRoleInfo roleInfo=roleDao.getByRoleIdAndOperationId(roleId, operationId);
			if(roleInfo==null) {
				roleDao.addRoleOperations(roleId, operationId);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminRoleService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		roleDao.delete(id);
	}



}
