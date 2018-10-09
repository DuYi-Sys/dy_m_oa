/**
 * 
 */
package com.duyi.community.service;

import java.util.List;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.community.domain.StudentUserInfo;

/**
 * @author wangyan
 *
 */
public interface IStudentUserService {
	StudentUserInfo getById(Long id);

	StudentUserInfo getByUsername(String username);
	StudentUserInfo getByNickName(String nickName);

	StudentUserInfo addStudent(StudentUserInfo studentInfo);
	StudentUserInfo modifyStudent(StudentUserInfo studentInfo,String oldPassword);
	StudentUserInfo modifyStudent(StudentUserInfo studentInfo);

	Page<StudentUserInfo> findStudents(Pageable pageable);
	List<StudentUserInfo> findStudentsByNickName(String nickName);
	
}
