/**
 * 
 */
package com.duyi.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duyi.commons.log.Trace;
import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.commons.validate.WebException;
import com.duyi.community.domain.StudentUserInfo;
import com.duyi.community.service.IStudentUserService;
import com.duyi.security.PasswordEncoder;
import com.duyi.security.SecurityContextHolder;


/**
 * @author wangyan
 *
 */
@RestController
public class StudentUserController {

	private static Trace log=Trace.register(StudentUserController.class);
	@Autowired
	private IStudentUserService studentUserService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@RequestMapping(path="/c/student",method=RequestMethod.POST,consumes="application/json",produces="application/json")
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
	
	@RequestMapping(path="/c/s/student",method=RequestMethod.PUT,consumes="application/json",produces="application/json")
	public StudentUserInfo updateStudentUser(@RequestBody StudentUserInfo student,@RequestParam("oldPassword")String oldPassword) {
		String username =SecurityContextHolder.getContext().getPrincipal().getUsername();
		student.setUsername(username);
	
		
		
		
		return studentUserService.modifyStudent(student,oldPassword);
	}
	@RequestMapping(path="/c/student",method=RequestMethod.GET, produces="application/json")
	public Page<StudentUserInfo> findStudentUsers(Pageable pageable,String name){
		return studentUserService.findStudents(pageable);
	}

	@RequestMapping(path="/c/s/student/current",method=RequestMethod.GET)
	public StudentUserInfo getCurrentUser() {
		String username =SecurityContextHolder.getContext().getPrincipal().getUsername();
		log.info("=======user name"+username);
		return studentUserService.getByUsername(username);
	}
//	@RequestMapping(value="/c/s/student/modifyPwd",method=RequestMethod.POST)
//	public void modifyPassword(String oldPassword,String unencodePassword) {
//		log.info("oldPassword:"+oldPassword+"unencodePassword:"+unencodePassword);
//		String username =SecurityContextHolder.getContext().getPrincipal().getUsername();
////		log.info("=======user name"+username);
//		StudentUserInfo userInfo=studentUserService.getByUsername(username);
//		if(encoder.matches(oldPassword, userInfo.getCredential())) {
//			userInfo.setUnencodePassword(unencodePassword);
//			studentUserService.modifyStudent(userInfo);
//		}else {
//			throw new WebException(1, "旧密码错误");
//		}
//	}
}
