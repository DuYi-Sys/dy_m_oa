/**
 * 
 */
package com.duyi.community.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.commons.page.PageableExecutionUtils;
import com.duyi.commons.page.PageableExecutionUtils.TotalSupplier;
import com.duyi.community.dao.StudentUserDao;
import com.duyi.community.domain.StudentUserInfo;
import com.duyi.security.PasswordEncoder;
import com.google.common.base.Strings;

/**
 * @author wangyan
 *
 */
@Service
public class StudentUserServiceImpl implements IStudentUserService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private StudentUserDao dao;
	/* (non-Javadoc)
	 * @see com.duyi.community.service.IStudentService#addStudent(com.duyi.community.domain.StudentInfo)
	 */
	@Override
	public StudentUserInfo addStudent(StudentUserInfo studentInfo) {
		Assert.notNull(studentInfo);
		if(!Strings.isNullOrEmpty(studentInfo.getUnencodePassword()) ) {
			studentInfo.setCredential(passwordEncoder.encode(studentInfo.getUnencodePassword()));
		}
		dao.add(studentInfo);
		return studentInfo;
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IStudentService#updateStudent(com.duyi.community.domain.StudentInfo)
	 */
	@Override
	public StudentUserInfo modifyStudent(StudentUserInfo studentInfo) {
		Assert.notNull(studentInfo);
		if(!Strings.isNullOrEmpty(studentInfo.getUnencodePassword()) ) {
			studentInfo.setCredential(passwordEncoder.encode(studentInfo.getUnencodePassword()));
		}
		dao.update(studentInfo);
		return studentInfo;
	}



	/* (non-Javadoc)
	 * @see com.duyi.community.service.IStudentService#findStudents(com.duyi.commons.page.Pageable)
	 */
	@Override
	public Page<StudentUserInfo> findStudents(Pageable pageable) {
		final int count=dao.getCount();
		pageable=PageableExecutionUtils.calculatePageable(count, pageable);


		RowBounds bounds=new RowBounds(pageable.getOffset(),pageable.getPageSize());
		List<StudentUserInfo> students=dao.findPageAll(bounds);
		return PageableExecutionUtils.getPage(students, pageable, new TotalSupplier() {
			@Override
			public long get() {
				return count;
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IStudentUserService#getByUsername(java.lang.String)
	 */
	@Override
	public StudentUserInfo getByUsername(String username) {
		return dao.getByUsername(username);
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IStudentUserService#getByNickName(java.lang.String)
	 */
	@Override
	public StudentUserInfo getByNickName(String nickName) {
		return dao.getByNickName(nickName);
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IStudentUserService#findStudentsByNickName(java.lang.String)
	 */
	@Override
	public List<StudentUserInfo> findStudentsByNickName(String nickName) {
		return dao.findByNickName(nickName);
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IStudentUserService#getById(java.lang.String)
	 */
	@Override
	public StudentUserInfo getById(Long id) {
		return dao.getById(id);
	}

}
