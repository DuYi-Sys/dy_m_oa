/**
 * 
 */
package com.duyi.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.community.domain.StudentAnswerInfo;
import com.duyi.community.service.IQuestionService;

/**
 * @author wangyan
 *
 */
@RestController
@RequestMapping("/api/answer")
public class StudentAnswerManagmentController {

	@Autowired
	private IQuestionService questionService;
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public Page<StudentAnswerInfo> findAnswersByQuestionId(@RequestParam("questionId") Long questionId,Pageable pageable) {
		return questionService.findAnswersByQuestionId(questionId, pageable);
	}
	@RequestMapping(method=RequestMethod.POST,produces="application/json",consumes="application/json")
	public StudentAnswerInfo addAnswer(@RequestBody StudentAnswerInfo answerInfo) {
		return questionService.addAnswer(answerInfo);
	}
	
}
