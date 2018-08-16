/**
 * 
 */
package com.duyi.admin.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.duyi.admin.dao.AdminUserDao;
import com.duyi.admin.domain.AdminUserInfo;
import com.duyi.commons.log.Trace;
import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.commons.page.PageableExecutionUtils;
import com.duyi.commons.page.PageableExecutionUtils.TotalSupplier;
import com.duyi.security.PasswordEncoder;
import com.google.common.base.Strings;

/**
 * @author wangyan
 *
 */
@Service
public class AdminUserServiceImpl implements IAdminUserService {

	private static Trace log=Trace.register(AdminUserServiceImpl.class);
	@Autowired
	private PasswordEncoder passwordEncoder;
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
			user.setCredential(passwordEncoder.encode(user.getUnencodePassword()));
		}
		userDao.add(user);
		return user;
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminUserService#modifyAdminUser(com.duyi.admin.domain.AdminUserInfo)
	 */
	@Override
	public AdminUserInfo modifyAdminUser(AdminUserInfo user) {
		Assert.notNull(user);
		if(!Strings.isNullOrEmpty(user.getUnencodePassword()) ) {
			user.setCredential(passwordEncoder.encode(user.getUnencodePassword()));
		}
		userDao.update(user);
		return user;
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminUserService#findUsers(com.duyi.commons.Pageable)
	 */
	@Override
	public Page<AdminUserInfo> findUsers(Pageable pageable) {
		final int count= userDao.getCount();

		pageable=PageableExecutionUtils.calculatePageable(count, pageable);

		RowBounds bounds=new RowBounds(pageable.getOffset(),pageable.getPageSize());
		List<AdminUserInfo> users=userDao.findPageAll(bounds);
//		for(AdminUserInfo userInfo : users) {
//			log.info("userId"+userInfo.getId());
//			List<AdminRoleInfo> roles=roleDao.findByUserId(userInfo.getId());
//			log.info("roles"+roles);
//
//			userInfo.setRoles(roles);
//		}
		return PageableExecutionUtils.getPage(users, pageable, new TotalSupplier() {
			@Override
			public long get() {
				return count;
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminUserService#findUsersByUsername(com.duyi.commons.Pageable, java.lang.String)
	 */
	@Override
	public Page<AdminUserInfo> findUsersByUsername(Pageable pageable,final String username) {
		if(Strings.isNullOrEmpty(username)) {
			return findUsers(pageable);
		}
		final int count= userDao.getCountByUsername(username);

		pageable=PageableExecutionUtils.calculatePageable(count, pageable);

		RowBounds bounds=new RowBounds(pageable.getOffset(),pageable.getPageSize());

		List<AdminUserInfo> users=userDao.findByUsername(username, bounds);
		return PageableExecutionUtils.getPage(users, pageable, new TotalSupplier() {

			@Override
			public long get() {
				return userDao.getCountByUsername(username);
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminUserService#findUsersByName(com.duyi.commons.page.Pageable, java.lang.String)
	 */
	@Override
	public Page<AdminUserInfo> findUsersByName(Pageable pageable, String name) {
		if(Strings.isNullOrEmpty(name)) {
			return findUsers(pageable);
		}
		final int count= userDao.getCountByName(name);

		if(count<=pageable.getPageSize()) {
			pageable=pageable.previousOrFirst();
		}
		RowBounds bounds=new RowBounds(pageable.getOffset(),pageable.getPageSize());

		List<AdminUserInfo> users=userDao.findPageByName(name, bounds);
		return PageableExecutionUtils.getPage(users, pageable, new TotalSupplier() {

			@Override
			public long get() {
				return count;
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminUserService#findUsersByName(java.lang.String)
	 */
	@Override
	public List<AdminUserInfo> findUsersByName(String name) {
		return userDao.findByName(name);
	}

	/* (non-Javadoc)
	 * @see com.duyi.admin.service.IAdminUserService#findUsersByRoleId(java.lang.Long)
	 */
	@Override
	public List<AdminUserInfo> findUsersByRoleId(Long roleId) {
		return userDao.findByRoleId(roleId);
	}



}
