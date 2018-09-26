/**
 * 
 */
package com.duyi.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.community.domain.StudentQuestionInfo;
import com.duyi.community.service.IQuestionService;

/**
 * @author wangyan
 *
 */
@RestController
@RequestMapping("/api/question")
public class StudentQuestionManagmentController {
	
	@Autowired
	private IQuestionService questionService;
	
	@RequestMapping(method=RequestMethod.POST, produces="application/json",consumes="application/json")
	public StudentQuestionInfo addQuestion(@RequestBody StudentQuestionInfo questionInfo) {
		return questionService.addQuestion(questionInfo);
	}
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public Page<StudentQuestionInfo> findQuestions(@RequestParam(value="typeId",required=false) Long typeId,Pageable pageable) {
		if(typeId!=null && typeId!=0L) {
			return questionService.findAllQuestionsByQuestionTypeId(typeId,pageable);
		}
		return questionService.findAllQuestions(pageable);
	}
	
	@RequestMapping(path="/{id}",method=RequestMethod.GET, produces="application/json")
	public StudentQuestionInfo getQuestion(@PathVariable Long id) {
		return questionService.getQuestion(id);
	}
}
