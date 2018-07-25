/**
 * 
 */
package com.duyi.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duyi.admin.domain.AdminUserInfo;
import com.duyi.admin.service.IAdminUserService;
import com.duyi.commons.log.Trace;
import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.security.PasswordEncoder;
import com.duyi.security.SecurityContextHolder;
import com.duyi.security.model.UserDetails;

/**
 * @author wangyan
 *
 */
@RestController
@RequestMapping("/api/s/adminusers")
public class AdminUserApiController {

	private static Trace log=Trace.register(AdminUserApiController.class);
	@Autowired
	private IAdminUserService adminUserService;
	@Autowired
	private PasswordEncoder encoder;
	
	
	@RequestMapping(method=RequestMethod.POST,consumes="application/json")
	public AdminUserInfo addUser(@RequestBody AdminUserInfo user) {

		return adminUserService.addAdminUser(user);
	}
	
//	@RequestMapping(method=RequestMethod.PUT, produces="application/json",consumes="application/json")
//	public IAdminUser updateUser(@RequestBody @Valid AdminUserDto dto,BindingResult result,HttpServletRequest request) {
//		if(result.hasErrors()) {
//			throw new ValidateException(result,"修改用户失败");
//		}
//		return userService.modifyAdminUser(toUserInfo(dto));
//	}
	
//	@RequestMapping(method=RequestMethod.GET, produces="application/json")
//	public Page<AdminUserInfo> findAdminUsers(@RequestParam(name="username",required=false) String username) {
//		PageRequest pageable=new PageRequest(1, 10);
//		return adminUserService.findUsersByUsername(pageable, username);
//	}
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public Page<AdminUserInfo> findAdminUsers(Pageable pageable) {
		log.info("pageable"+pageable);
		log.info("current user:"+SecurityContextHolder.getContext().getPrincipal().getUsername());

		return adminUserService.findUsers(pageable);
	}
	
	
	@RequestMapping(value="/{username}",method=RequestMethod.GET)
	public AdminUserInfo findUser(@PathVariable String username) {
		
		return adminUserService.getByUsername(username);
//		return adminUserService.getByUsername(username);
	}
	@RequestMapping(value="/current",method=RequestMethod.GET)
	public AdminUserInfo getCurrentUser() {
		String username =SecurityContextHolder.getContext().getPrincipal().getUsername();
		log.info("=======user name"+username);
		return adminUserService.getByUsername(username);
	}
	@RequestMapping(value="/modifyPwd",method=RequestMethod.POST)
	public String modifyPassword(String oldPassword,String unencodePassword) {
		log.info("oldPassword:"+oldPassword+"unencodePassword:"+unencodePassword);
		String username =SecurityContextHolder.getContext().getPrincipal().getUsername();
		log.info("=======user name"+username);
		AdminUserInfo adminUserInfo=adminUserService.getByUsername(username);
		if(encoder.matches(oldPassword, adminUserInfo.getCredential())) {
			adminUserInfo.setUnencodePassword(unencodePassword);
			adminUserService.modifyAdminUser(adminUserInfo);
			return "1";
		}else {
			return "-1";
		}
		
		
	}
}
