/**
 * 
 */
package com.duyi.community.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.duyi.community.domain.StudentQuestionInfo;

/**
 * @author wangyan
 *
 */
public interface StudentQuestionDao {

  @Insert("INSERT INTO student_question (TITLE, CONTENT, TYPE_ID, OWNER_ID,CREATED) " + 
  		"VALUES(#{title}, #{content}, #{typeId}, #{ownerId},#{created}) ")
  int addQuestion(StudentQuestionInfo question);
  @Update("UPDATE student_question SET TITLE=#{title}, CONTENT=#{content}, TYPE_ID=#{typeId}, OWNER_ID=#{ownerId} " + 
  		"WHERE ID=#{id}")
  int updateQuestion(StudentQuestionInfo question);
  @Select("SELECT q.ID, TITLE, CONTENT, TYPE_ID, OWNER_ID,CREATED, u.NICKNAME as OWNER_NAME " + 
  		"FROM student_question q LEFT  JOIN student_user_info u ON OWNER_ID=u.ID")
  List<StudentQuestionInfo> findPageAll(RowBounds bounds);
  @Select("SELECT q.ID, TITLE, CONTENT, TYPE_ID, OWNER_ID,CREATED,u.NICKNAME as OWNER_NAME  " + 
	  		"FROM student_question q LEFT  JOIN student_user_info u ON OWNER_ID=u.ID where TYPE_ID=#{typeId}")
	  List<StudentQuestionInfo> findPageByTypeId(@Param("typeId") Long typeId,RowBounds bounds);
  @Select("SELECT q.ID, TITLE, CONTENT, TYPE_ID, OWNER_ID,CREATED,u.NICKNAME as OWNER_NAME  " + 
	  		"FROM student_question q LEFT  JOIN student_user_info u ON OWNER_ID=u.ID")
	  List<StudentQuestionInfo> findAll();
  @Select("SELECT q.ID, TITLE, CONTENT, TYPE_ID, OWNER_ID,CREATED,u.NICKNAME as OWNER_NAME  " + 
	  		"FROM student_question q LEFT  JOIN student_user_info u ON OWNER_ID=u.ID where id=#{id}")
  StudentQuestionInfo getQuestion(@Param("id")Long id);
  @Select("SELECT count(ID) " + 
	  		"FROM student_question")
  int getCount();
  @Select("SELECT count(ID) " + 
	  		"FROM student_question where TYPE_ID=#{typeId}")
int getCountByTypeId(@Param("typeId") Long typeId);
}
