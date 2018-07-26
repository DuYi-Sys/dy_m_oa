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
	
	@RequestMapping(method= RequestMethod.PUT,produces="application/json",consumes="application/json")
	public AdminOperationInfo updateOperation(@RequestBody  AdminOperationInfo operation) {
		return operationService.modifyOperation(operation);
	}
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public Page<AdminOperationInfo> findAdminOperations( Pageable pageable) {
		Page<AdminOperationInfo> page=operationService.findAll(pageable);
		return page;
	}
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public AdminOperationInfo getOperation(@PathVariable Long id) {
		return operationService.getById(id);
	}

	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public void deleteOperation(@PathVariable("id") Long id) {
		operationService.deleteAdminOperation(id);
	}
	
}