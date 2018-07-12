/**
 * 
 */
package com.duyi.admin.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.duyi.admin.dao.AdminMenuDao;
import com.duyi.admin.domain.AdminMenuInfo;
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
public class AdminMenuServiceImpl implements IAdminMenuService {

	@Autowired
	private AdminMenuDao menuDao;
	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminMenuService#getByPath(java.lang.String)
	 */
	@Override
	public AdminMenuInfo getByPath(String path) {
		return menuDao.getByPath(path);
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminMenuService#addMenu(com.duyi.admin.domain.AdminMenuInfo)
	 */
	@Override
	public AdminMenuInfo addMenu(AdminMenuInfo menu) {
		Assert.notNull(menu);
		return menuDao.add(menu);
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminMenuService#modifyMenu(com.duyi.admin.domain.AdminMenuInfo)
	 */
	@Override
	public AdminMenuInfo modifyMenu(AdminMenuInfo menu) {
		Assert.notNull(menu);
		return menuDao.update(menu);
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminMenuService#findMenus(com.duyi.commons.Pageable)
	 */
	@Override
	public Page<AdminMenuInfo> findMenus(Pageable pageable) {
		int startPosition=pageable.getOffset();
		int maxResult=pageable.getPageSize();
		List<AdminMenuInfo> menus=menuDao.findAll(startPosition, maxResult);
		return PageableExecutionUtils.getPage(menus, pageable, new TotalSupplier() {
			@Override
			public long get() {
				return menuDao.getCount();
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminMenuService#findMenusByName(com.duyi.commons.Pageable, java.lang.String)
	 */
	@Override
	public Page<AdminMenuInfo> findMenusByName(Pageable pageable, final String name) {
		if(Strings.isNullOrEmpty(name)) {
			return this.findMenus(pageable);
		}
		int startPosition=pageable.getOffset();
		int maxResult=pageable.getPageSize();
		List<AdminMenuInfo> menus=menuDao.findByName(startPosition, maxResult, name);
		return PageableExecutionUtils.getPage(menus, pageable, new TotalSupplier() {
			@Override
			public long get() {
				return menuDao.getCountByName(name);
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminMenuService#findAllMenus()
	 */
	@Override
	public List<AdminMenuInfo> findAllMenus() {
		return menuDao.findAll();
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminMenuService#findByRoles(java.util.Set)
	 */
	@Override
	public List<AdminMenuInfo> findByRoles(Set<AdminRoleInfo> roles) {
		return menuDao.findByRoles(roles);
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminMenuService#deleteAdminMenu(java.lang.Long)
	 */
	@Override
	public void deleteAdminMenu(Long id) {
		menuDao.delete(id);
	}

}
