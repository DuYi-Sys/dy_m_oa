/**
 * 
 */
package com.duyi.community.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.duyi.community.domain.StudentAnswerInfo;

/**
 * @author wangyan
 *
 */
public interface StudentAnswerDao {
	@Insert("INSERT INTO student_answer( CONTENT, QUESTION_ID, OWNER_ID,CREATED) " + 
			"VALUES(#{content}, #{questionId}, #{ownerId},#{created}) ")
	int addAnswer(StudentAnswerInfo answer);
	@Update("UPDATE student_answer SET CONTENT=#{content}, QUESTION_ID=#{questionId}, OWNER_ID=#{ownerId} " + 
			"WHERE ID=#{id}")
	int updateAnswer(StudentAnswerInfo answer);
	@Select("SELECT a.ID, CONTENT, QUESTION_ID, OWNER_ID,CREATED,u.NICKNAME as OWNER_NAME FROM student_answer a LEFT  JOIN student_user_info u ON OWNER_ID=u.ID where a.ID=#{questionId}")
	List<StudentAnswerInfo> findPageByQuestionId(@Param("questionId")Long questionId,RowBounds brounds);
	@Select("SELECT count(ID)  FROM student_answer where ID=#{questionId}")
	int getCountByQuestionId(@Param("questionId") Long questionId);
}
