/**
 * 
 */
package com.duyi.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.community.domain.StudentQuestionTypeInfo;
import com.duyi.community.service.IQuestionTypeService;

/**
 * @author wangyan
 *
 */
@RestController
@RequestMapping("/api/questiontype")
public class StudentQuestionTypeManagmentController {

	@Autowired
	private IQuestionTypeService questionTypeSevice;
	@RequestMapping(method=RequestMethod.GET,produces="application/json")
	public Page<StudentQuestionTypeInfo> findQuestionTypes(Pageable pageable){
		return questionTypeSevice.findAllTypes(pageable);
	}
	@RequestMapping(value="/{id}",method=RequestMethod.GET,produces="application/json")
	public StudentQuestionTypeInfo getType(@PathVariable("id") Long id) {
		return questionTypeSevice.getType(id);
	}
	@RequestMapping(method=RequestMethod.POST, produces="application/json",consumes="application/json")
	public StudentQuestionTypeInfo addQuestionType(@RequestBody StudentQuestionTypeInfo type) {
		return questionTypeSevice.addType(type);
	}
	@RequestMapping(method=RequestMethod.PUT, produces="application/json",consumes="application/json")
	public StudentQuestionTypeInfo updateQuestionType(@RequestBody StudentQuestionTypeInfo type) {
		return questionTypeSevice.updateType(type);
	}
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public void deleteQuestionType(@PathVariable("id") Long id) {
		 questionTypeSevice.deleteType(id);
	}
}
