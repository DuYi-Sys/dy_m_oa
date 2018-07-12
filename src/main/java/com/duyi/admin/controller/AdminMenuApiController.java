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

import com.duyi.admin.domain.AdminMenuInfo;
import com.duyi.admin.service.IAdminMenuService;
import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;

/**
 * @author wangyan
 *
 */
@RestController
@RequestMapping("/api/adminmenus")
public class AdminMenuApiController {

	@Autowired
	private IAdminMenuService adminMenuService;
	
	
	@RequestMapping(method=RequestMethod.POST, produces="application/json",consumes="application/json")
	public AdminMenuInfo addMenu(@RequestBody  AdminMenuInfo menu) {
		return adminMenuService.addMenu(menu);
	}
	@RequestMapping(method= {RequestMethod.PUT,RequestMethod.PATCH}, produces="application/json",consumes="application/json")
	public AdminMenuInfo updateMenu(@RequestBody  AdminMenuInfo menu) {
		return adminMenuService.modifyMenu(menu);
	}
	@RequestMapping(method=RequestMethod.GET, produces="application/json",consumes="application/json")
	public Page<AdminMenuInfo> findAdminMenus( Pageable pageable,@RequestParam(name="name") String name) {
		Page<AdminMenuInfo> page=adminMenuService.findMenusByName(pageable, name);
		return page;
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public void deleteMenu(@RequestParam(name="id")Long id) {

		 adminMenuService.deleteAdminMenu(id);
	}
	
}
