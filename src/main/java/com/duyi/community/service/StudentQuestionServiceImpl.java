/**
 * 
 */
package com.duyi.community.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.commons.page.PageableExecutionUtils;
import com.duyi.commons.page.PageableExecutionUtils.TotalSupplier;
import com.duyi.community.dao.StudentAnswerDao;
import com.duyi.community.dao.StudentQuestionDao;
import com.duyi.community.domain.StudentAnswerInfo;
import com.duyi.community.domain.StudentQuestionInfo;

/**
 * @author wangyan
 *
 */
@Service
public class StudentQuestionServiceImpl implements IQuestionService{

	@Autowired
	private StudentQuestionDao studentQuestionDao;
	@Autowired
	private StudentAnswerDao studentAnswerDao;
	/* (non-Javadoc)
	 * @see com.duyi.community.service.IQuestionService#addQuestion(com.duyi.community.domain.QuestionInfo)
	 */
	@Override
	public StudentQuestionInfo addQuestion(StudentQuestionInfo questionInfo) {
		Assert.notNull(questionInfo);
		questionInfo.setCreated(new Date());
		studentQuestionDao.addQuestion(questionInfo);
		return questionInfo;
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IQuestionService#addAnswer(com.duyi.community.domain.AnswerInfo)
	 */
	@Override
	public StudentAnswerInfo addAnswer(StudentAnswerInfo answerInfo) {
		Assert.notNull(answerInfo);
		answerInfo.setCreated(new Date());
		studentAnswerDao.addAnswer(answerInfo);
		return answerInfo;
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IQuestionService#updateQuestion(com.duyi.community.domain.QuestionInfo)
	 */
	@Override
	public StudentQuestionInfo updateQuestion(StudentQuestionInfo questionInfo) {
		Assert.notNull(questionInfo);
		studentQuestionDao.updateQuestion(questionInfo);
		return questionInfo;
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IQuestionService#deleteQuestion(java.lang.Long)
	 */
	@Override
	public void deleteQuestion(Long id) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IQuestionService#deleteAnswer(java.lang.Long)
	 */
	@Override
	public void deleteAnswer(Long id) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IQuestionService#findAnswersById(java.lang.Long, com.duyi.commons.page.Pageable)
	 */
	@Override
	public Page<StudentAnswerInfo> findAnswersByQuestionId(Long questionId, Pageable pageable) {
		final int count= studentAnswerDao.getCountByQuestionId(questionId);

		pageable=PageableExecutionUtils.calculatePageable(count, pageable);
		RowBounds bounds=new RowBounds(pageable.getOffset(),pageable.getPageSize());

		List<StudentAnswerInfo> list=studentAnswerDao.findPageByQuestionId(questionId,bounds);
		return PageableExecutionUtils.getPage(list, pageable, new TotalSupplier() {
			@Override
			public long get() {
				return count;
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IQuestionService#findAllQuestions()
	 */
	@Override
	public Page<StudentQuestionInfo> findAllQuestions(Pageable pageable) {
		final int count= studentQuestionDao.getCount();

		pageable=PageableExecutionUtils.calculatePageable(count, pageable);
		RowBounds bounds=new RowBounds(pageable.getOffset(),pageable.getPageSize());

		List<StudentQuestionInfo> list=studentQuestionDao.findPageAll(bounds);
		return PageableExecutionUtils.getPage(list, pageable, new TotalSupplier() {
			@Override
			public long get() {
				return count;
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IQuestionService#getQuestion(java.lang.Long)
	 */
	@Override
	public StudentQuestionInfo getQuestion(Long id) {
		return studentQuestionDao.getQuestion(id);
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IQuestionService#findAllQuestionsByQuestionTypeId(java.lang.Long, com.duyi.commons.page.Pageable)
	 */
	@Override
	public Page<StudentQuestionInfo> findAllQuestionsByQuestionTypeId(Long typeId, Pageable pageable) {
		final int count= studentQuestionDao.getCountByTypeId(typeId);

		pageable=PageableExecutionUtils.calculatePageable(count, pageable);
		RowBounds bounds=new RowBounds(pageable.getOffset(),pageable.getPageSize());

		List<StudentQuestionInfo> list=studentQuestionDao.findPageByTypeId(typeId, bounds);
		return PageableExecutionUtils.getPage(list, pageable, new TotalSupplier() {
			@Override
			public long get() {
				return count;
			}
		});
	}

}
