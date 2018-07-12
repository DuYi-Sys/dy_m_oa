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

import com.duyi.admin.domain.AdminOperationInfo;
import com.duyi.admin.service.IAdminOperationService;
import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;


/**
 * @author wangyan
 *
 */
@RestController
@RequestMapping("/api/adminoperations")
public class AdminOperationApiController {

	@Autowired
	private IAdminOperationService operationService;
	
	
	@RequestMapping(method=RequestMethod.POST, produces="application/json",consumes="application/json")
	public AdminOperationInfo addAdminOperation(@RequestBody  AdminOperationInfo operation) {
		
		return operationService.addOperation(operation);
	}
	
	@RequestMapping(method= {RequestMethod.PUT, RequestMethod.PATCH},produces="application/json",consumes="application/json")
	public AdminOperationInfo updateOperation(@RequestBody  AdminOperationInfo operation) {

		return operationService.modifyOperation(operation);
		 
	}
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json",consumes="application/json")
	public Page<AdminOperationInfo> findAdminOperations( Pageable pageable,@RequestParam(name="name") String name) {
		Page<AdminOperationInfo> page=operationService.findAllByName(pageable, name);
		return page;
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public void deleteOperation(@RequestParam(name="id")Long id) {
		operationService.deleteAdminOperation(id);
	}
	
}