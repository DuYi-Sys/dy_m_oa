/**
 * 
 */
package com.duyi.community.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.community.domain.StudentUserInfo;
import com.duyi.community.service.IStudentUserService;
import com.duyi.security.PasswordEncoder;
import com.google.common.base.Strings;

/**
 * @author wangyan
 *
 */
@RestController
@RequestMapping("/api/student")
public class StudentUserManagmentController {

	@Autowired
	private IStudentUserService studentUserService;
	

	
	@RequestMapping(method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public StudentUserInfo addStudentUser(@RequestBody StudentUserInfo student) {
		return studentUserService.addStudent(student);
	}
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public Page<StudentUserInfo> findStudentUsers(Pageable pageable,String name){
		return studentUserService.findStudents(pageable);
	}
	@RequestMapping(path="/nickname",method=RequestMethod.GET, produces="application/json")
	public List<StudentUserInfo> findStudentUserByNickName(String nickName){
		if(Strings.isNullOrEmpty(nickName)) {
			return Collections.emptyList();
		}
		return studentUserService.findStudentsByNickName(nickName);
	}
	@RequestMapping(path="/relation",method=RequestMethod.PUT, produces="application/json")
	public void relationStudentUser(Long studentId,Long studentBodyId) {
		StudentUserInfo userInfo=studentUserService.getById(studentId);
		userInfo.setStudentBodyId(studentBodyId);
		studentUserService.modifyStudent(userInfo);
	}
}
