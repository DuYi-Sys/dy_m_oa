/**
 * 
 */
package com.duyi.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duyi.admin.domain.AdminRoleInfo;
import com.duyi.admin.service.IAdminRoleService;
import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;

/**
 * @author wangyan
 *
 */
@RestController
@RequestMapping("/api/adminroles")
public class AdminRoleApiController {

	@Autowired
	private IAdminRoleService roleService;
	
	@RequestMapping(method=RequestMethod.POST, produces="application/json",consumes="application/json")
	public AdminRoleInfo addRole(@RequestBody AdminRoleInfo role) {

		return roleService.addRole(role);
	}
	
	@RequestMapping(method=RequestMethod.PUT, produces="application/json",consumes="application/json")
	public AdminRoleInfo updateRole(@RequestBody  AdminRoleInfo role) {

		return roleService.modifyRole(role);
		 
	}
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json",consumes="application/json")
	public Page<AdminRoleInfo> findAdminRoles(Pageable pageable,@RequestParam(name="name") String name) {
		return roleService.findRolesByName(name, pageable);
	}
	
	@RequestMapping(value="/{name}",method=RequestMethod.GET)
	public AdminRoleInfo findRole(@PathVariable String name) {
		return roleService.getByName(name);
	}
	
	@RequestMapping(value="all",method=RequestMethod.GET)
	public List<AdminRoleInfo> findAllRoles() {
		return roleService.findAllRoles();
	}
}
