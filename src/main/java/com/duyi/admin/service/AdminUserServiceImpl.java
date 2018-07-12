/**
 * 
 */
package com.duyi.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.duyi.admin.dao.AdminUserDao;
import com.duyi.admin.domain.AdminUserInfo;
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
public class AdminUserServiceImpl implements IAdminUserService {

	@Autowired
	private AdminUserDao userDao;
	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminUserService#getByUsername(java.lang.String)
	 */
	@Override
	public AdminUserInfo getByUsername(String username) {
		return userDao.getByUsername(username);
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminUserService#addAdminUser(com.duyi.admin.domain.AdminUserInfo)
	 */
	@Override
	public AdminUserInfo addAdminUser(AdminUserInfo user) {
		Assert.notNull(user);
		if(!Strings.isNullOrEmpty(user.getUnencodePassword()) ) {
			user.setCredential(encodePasssword(user.getUnencodePassword()));
		}
		return userDao.add(user);
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminUserService#modifyAdminUser(com.duyi.admin.domain.AdminUserInfo)
	 */
	@Override
	public AdminUserInfo modifyAdminUser(AdminUserInfo user) {
		Assert.notNull(user);
		if(!Strings.isNullOrEmpty(user.getUnencodePassword()) ) {
			user.setCredential(encodePasssword(user.getUnencodePassword()));
		}
		return userDao.update(user);
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminUserService#findUsers(com.duyi.commons.Pageable)
	 */
	@Override
	public Page<AdminUserInfo> findUsers(Pageable pageable) {
		int startPosition=pageable.getOffset();
		int maxResult=pageable.getPageSize();
		List<AdminUserInfo> users=userDao.findAll(startPosition, maxResult);
		return PageableExecutionUtils.getPage(users, pageable, new TotalSupplier() {

			@Override
			public long get() {
				return userDao.getCount();
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminUserService#findUsersByUsername(com.duyi.commons.Pageable, java.lang.String)
	 */
	@Override
	public Page<AdminUserInfo> findUsersByUsername(Pageable pageable,final String username) {

		int startPosition=pageable.getOffset();
		int maxResult=pageable.getPageSize();
		List<AdminUserInfo> users=userDao.findByUsername(username, startPosition, maxResult);
		return PageableExecutionUtils.getPage(users, pageable, new TotalSupplier() {

			@Override
			public long get() {
				return userDao.getCountByUsername(username);
			}
			
		});
	}
	private String encodePasssword(String unencodePassword) {
		return unencodePassword;
	}
}
