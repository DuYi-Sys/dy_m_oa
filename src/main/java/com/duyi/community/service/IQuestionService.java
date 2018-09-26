/**
 * 
 */
package com.duyi.community.service;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.community.domain.StudentAnswerInfo;
import com.duyi.community.domain.StudentQuestionInfo;

/**
 * @author wangyan
 *
 */
public interface IQuestionService {
	StudentQuestionInfo addQuestion(StudentQuestionInfo questionInfo);
	StudentAnswerInfo addAnswer(StudentAnswerInfo answerInfo);
	StudentQuestionInfo updateQuestion(StudentQuestionInfo questionInfo);
	StudentQuestionInfo getQuestion(Long id);
	Page<StudentQuestionInfo> findAllQuestionsByQuestionTypeId(Long typeId,Pageable pageable);
	Page<StudentQuestionInfo> findAllQuestions(Pageable pageable);
	Page<StudentAnswerInfo> findAnswersByQuestionId(Long questionId,Pageable pageable);
	void deleteQuestion(Long id);
	void deleteAnswer(Long id);
}
