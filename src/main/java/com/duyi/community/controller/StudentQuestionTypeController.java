/**
 * 
 */
package com.duyi.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duyi.community.domain.StudentQuestionTypeInfo;
import com.duyi.community.service.IQuestionTypeService;

/**
 * @author wangyan
 *
 */
@RestController
@RequestMapping("/c/questiontype")
public class StudentQuestionTypeController {

	@Autowired
	private IQuestionTypeService questionTypeSevice;
	@RequestMapping(method=RequestMethod.GET,produces="application/json")
	public List<StudentQuestionTypeInfo> findQuestionTypes(){
		return questionTypeSevice.findAllTypes();
	}
}
