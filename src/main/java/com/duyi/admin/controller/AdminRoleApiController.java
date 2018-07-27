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
import org.springframework.web.bind.annotation.RestController;

import com.duyi.admin.domain.AdminRoleInfo;
import com.duyi.admin.service.IAdminRoleService;
import com.duyi.commons.log.Trace;
import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;

/**
 * @author wangyan
 *
 */
@RestController
@RequestMapping("/api/adminroles")
public class AdminRoleApiController {

	private static Trace log=Trace.register(AdminRoleApiController.class);
	@Autowired
	private IAdminRoleService roleService;
	
	@RequestMapping(method=RequestMethod.POST, produces="application/json",consumes="application/json")
	public AdminRoleInfo addRole(@RequestBody AdminRoleInfo role) {

		return roleService.addRole(role);
	}
	
	@RequestMapping(method= RequestMethod.PUT, produces="application/json",consumes="application/json")
	public AdminRoleInfo updateRole(@RequestBody  AdminRoleInfo role) {

		return roleService.modifyRole(role);
		 
	}
	@RequestMapping(value="/{id}/users",method=RequestMethod.POST,consumes="application/json")
	public void addRoleUsers(@PathVariable Long id, @RequestBody Long[] userIds) {
		roleService.addRoleUsers(id, userIds);
		
	}
	@RequestMapping(value="/{id}/operations",method=RequestMethod.POST,consumes="application/json")
	public void addRoleOperations(@PathVariable Long id, @RequestBody Long[] operationIds) {
		roleService.addRoleOperations(id, operationIds);
		
	}
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public Page<AdminRoleInfo> findAdminRoles(Pageable pageable, String name) {
		return roleService.findRolesByName(name, pageable);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public AdminRoleInfo getRole(@PathVariable Long id) {
		return roleService.getById(id);
	}
	
	@RequestMapping(value="all",method=RequestMethod.GET)
	public List<AdminRoleInfo> findAllRoles() {
		return roleService.findAllRoles();
	}
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public void deleteRole(@PathVariable Long id) {
		roleService.delete(id);
	}
}
