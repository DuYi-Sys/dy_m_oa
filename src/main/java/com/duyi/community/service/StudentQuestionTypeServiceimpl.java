/**
 * 
 */
package com.duyi.community.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.commons.page.PageableExecutionUtils;
import com.duyi.commons.page.PageableExecutionUtils.TotalSupplier;
import com.duyi.community.dao.StudentQuestionTypeDao;
import com.duyi.community.domain.StudentQuestionTypeInfo;

/**
 * @author wangyan
 *
 */
@Service
public class StudentQuestionTypeServiceimpl implements IQuestionTypeService {

	@Autowired
	private StudentQuestionTypeDao studentQuestionTypeDao;
	/* (non-Javadoc)
	 * @see com.duyi.community.service.IQuestionTypeService#findAllTypes()
	 */
	@Override
	public List<StudentQuestionTypeInfo> findAllTypes() {
		return studentQuestionTypeDao.findAll();
	}
	/* (non-Javadoc)
	 * @see com.duyi.community.service.IQuestionTypeService#addType(com.duyi.community.domain.StudentQuestionTypeInfo)
	 */
	@Override
	public StudentQuestionTypeInfo addType(StudentQuestionTypeInfo type) {
		Assert.notNull(type);
		studentQuestionTypeDao.add(type);
		return type;
	}
	/* (non-Javadoc)
	 * @see com.duyi.community.service.IQuestionTypeService#updateType(com.duyi.community.domain.StudentQuestionTypeInfo)
	 */
	@Override
	public StudentQuestionTypeInfo updateType(StudentQuestionTypeInfo type) {
		Assert.notNull(type);
		studentQuestionTypeDao.update(type);
		return type;
	}
	/* (non-Javadoc)
	 * @see com.duyi.community.service.IQuestionTypeService#deleteType(java.lang.Long)
	 */
	@Override
	public void deleteType(Long id) {
		studentQuestionTypeDao.deleteById(id);
	}
	/* (non-Javadoc)
	 * @see com.duyi.community.service.IQuestionTypeService#findAllTypes(com.duyi.commons.page.Pageable)
	 */
	@Override
	public Page<StudentQuestionTypeInfo> findAllTypes(Pageable pageable) {
		final int count= studentQuestionTypeDao.getCountByAll();

		pageable=PageableExecutionUtils.calculatePageable(count, pageable);
		RowBounds bounds=new RowBounds(pageable.getOffset(),pageable.getPageSize());

		List<StudentQuestionTypeInfo> list=studentQuestionTypeDao.findPageAll(bounds);
		return PageableExecutionUtils.getPage(list, pageable, new TotalSupplier() {
			@Override
			public long get() {
				return count;
			}
		});
	}
	/* (non-Javadoc)
	 * @see com.duyi.community.service.IQuestionTypeService#getType(java.lang.Long)
	 */
	@Override
	public StudentQuestionTypeInfo getType(Long id) {
		return studentQuestionTypeDao.get(id);
	}

}
