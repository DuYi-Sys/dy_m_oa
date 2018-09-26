/**
 * 
 */
package com.duyi.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duyi.commons.log.Trace;
import com.duyi.community.domain.StudentUserInfo;
import com.duyi.community.service.IStudentUserService;
import com.duyi.security.jwt.model.JwtStudent;
import com.duyi.security.model.UserDetails;

/**
 * @author wangyan
 *
 */
@Service("studentUserDetailsService")
public class StudentUserDetailsServiceImpl implements IUserDetailsService {
	private static Trace log=Trace.register(StudentUserDetailsServiceImpl.class);
	@Autowired
	private IStudentUserService studentUserService;
	/* (non-Javadoc)
	 * @see com.duyi.security.service.IUserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		StudentUserInfo userInfo=studentUserService.getByUsername(username);
		if(userInfo==null) {
			return null;
		}
		JwtStudent jwtStudent=new JwtStudent();
		jwtStudent.setId(userInfo.getId());
		jwtStudent.setNickName(userInfo.getNickName());
		jwtStudent.setPassword(userInfo.getCredential());
		jwtStudent.setUsername(userInfo.getUsername());
		jwtStudent.setVip(userInfo.isVip());
		return jwtStudent;
	}

}
