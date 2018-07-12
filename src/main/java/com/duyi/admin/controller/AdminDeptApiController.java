/**
 * 
 */
package com.duyi.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duyi.admin.domain.AdminDeptInfo;
import com.duyi.admin.service.IAdminDeptService;
import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;

/**
 * @author wangyan
 *
 */
@RestController
@RequestMapping("/api/admindept")
public class AdminDeptApiController {

	@Autowired
	private IAdminDeptService adminDeptService;
	
	
	@RequestMapping(method=RequestMethod.POST, produces="application/json",consumes="application/json")
	public AdminDeptInfo addDept(@RequestBody  AdminDeptInfo dept) {
		return adminDeptService.addDept(dept);
	}
	@RequestMapping(method= {RequestMethod.PUT,RequestMethod.PATCH}, produces="application/json",consumes="application/json")
	public AdminDeptInfo updateDept(@RequestBody  AdminDeptInfo dept) {
		return adminDeptService.modifyDept(dept);
	}
	@RequestMapping(method=RequestMethod.GET, produces="application/json",consumes="application/json")
	public Page<AdminDeptInfo> findDepts( Pageable pageable,@RequestParam(name="name") String name) {
		Page<AdminDeptInfo> page=adminDeptService.findDeptsByName(pageable, name);
		return page;
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public void deleteDept(@RequestParam(name="id")Long id) {
		adminDeptService.deleteById(id);
	}
	
}
