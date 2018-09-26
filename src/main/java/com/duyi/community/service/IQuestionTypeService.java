/**
 * 
 */
package com.duyi.community.service;

import java.util.List;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.community.domain.StudentQuestionTypeInfo;

/**
 * @author wangyan
 *
 */
public interface IQuestionTypeService {

	StudentQuestionTypeInfo addType(StudentQuestionTypeInfo type);
	StudentQuestionTypeInfo updateType(StudentQuestionTypeInfo type);
	StudentQuestionTypeInfo getType(Long id);
	void deleteType(Long id);
	List<StudentQuestionTypeInfo>  findAllTypes();
	Page<StudentQuestionTypeInfo>  findAllTypes(Pageable pageable);
}
