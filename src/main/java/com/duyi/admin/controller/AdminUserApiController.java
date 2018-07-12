/**
 * 
 */
package com.duyi.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duyi.admin.domain.AdminUserInfo;
import com.duyi.admin.service.IAdminRoleService;
import com.duyi.admin.service.IAdminUserService;
import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;

/**
 * @author wangyan
 *
 */
@RestController
@RequestMapping("/api/adminusers")
public class AdminUserApiController {

	@Autowired
	private IAdminUserService adminUserService;

	@Autowired
	private IAdminRoleService roleService;
	
	
	@RequestMapping(method=RequestMethod.POST, produces="application/json",consumes="application/json")
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
	
	@RequestMapping(value="/",method=RequestMethod.GET, produces="application/json",consumes="application/json")
	public Page<AdminUserInfo> findAdminUsers(Pageable pageable,@RequestParam(name="username") String username) {
		return adminUserService.findUsersByUsername(pageable, username);
	}
	
	
	@RequestMapping(value="/{username}",method=RequestMethod.GET)
	public AdminUserInfo findUser(@PathVariable String username) {
		return adminUserService.getByUsername(username);
	}

	
	
}
