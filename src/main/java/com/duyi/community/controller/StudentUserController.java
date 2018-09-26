/**
 * 
 */
package com.duyi.community.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.commons.validate.WebException;
import com.duyi.community.domain.StudentUserInfo;
import com.duyi.community.service.IStudentUserService;
import com.duyi.security.PasswordEncoder;
import com.google.common.base.Strings;


/**
 * @author wangyan
 *
 */
@RestController
@RequestMapping("/c/student")
public class StudentUserController {

	@Autowired
	private IStudentUserService studentUserService;
	

	
	@RequestMapping(method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public StudentUserInfo addStudentUser(@RequestBody StudentUserInfo student) {
		StudentUserInfo studentUserInfo=studentUserService.getByUsername(student.getUsername());
		if(studentUserInfo!=null) {
			throw new WebException(1, "用户已存在");
		}
		studentUserInfo=studentUserService.getByNickName(student.getNickName());
		if(studentUserInfo!=null) {
			throw new WebException(2, "用户昵称已用");
		}
		return studentUserService.addStudent(student);
	}
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public Page<StudentUserInfo> findStudentUsers(Pageable pageable,String name){
		return studentUserService.findStudents(pageable);
	}

}
