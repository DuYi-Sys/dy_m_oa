/**
 * 
 */
package com.duyi.community.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.duyi.community.domain.StudentQuestionTypeInfo;

/**
 * @author wangyan
 *
 */
public interface StudentQuestionTypeDao {
	@Insert("INSERT INTO student_question_type (NAME)  VALUES(#{name})")
	int add(StudentQuestionTypeInfo fileType);
	@Update("UPDATE student_question_type SET NAME=#{name} where id=#{id}")
    int update(StudentQuestionTypeInfo fileType);
	@Select("SELECT ID, NAME FROM student_question_type")
	List<StudentQuestionTypeInfo> findPageAll(RowBounds brounds);
	@Select("SELECT ID, NAME FROM student_question_type")
	List<StudentQuestionTypeInfo> findAll();

	@Select("SELECT count(ID) FROM student_question_type")
	int getCountByAll();
	@Select("SELECT ID, NAME FROM student_question_type where id=#{id}")
	StudentQuestionTypeInfo get(@Param("id")Long id);
	@Delete("delete  from student_question_type where id =#{id}")
	void deleteById(@Param("id")Long id);
}
