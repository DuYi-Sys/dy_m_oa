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

import com.duyi.community.domain.StudentUserInfo;

/**
 * @author wangyan
 *
 */
public interface StudentUserDao {
	@Insert("INSERT INTO student_user_info " + 
			"(ACTIVED, CREDENTIAL, NICKNAME, USERNAME, WEIXIN, QQ, VIP) " + 
			"VALUES(#{actived}, #{credential}, #{nickName}, #{username}, #{weixin}, #{qq}, #{vip})")
	Long add(StudentUserInfo student);
	@Update("UPDATE student_user_info " + 
			"SET ACTIVED=#{actived},   CREDENTIAL=#{credential}, WEIXIN=#{weixin}, QQ=#{qq}, VIP=#{vip},STUDENT_BODY_ID=#{studentBodyId} " +
			"WHERE ID=#{id}")
	int update(StudentUserInfo student);
	@Select("SELECT ID, ACTIVED, DATE_CREATED, DATE_MODIFIED, UPDT_ID, CREDENTIAL, NICKNAME, USERNAME, WEIXIN, QQ, VIP " + 
			"FROM student_user_info where id=#{id}")
	StudentUserInfo getById(@Param("id")Long id);
	@Select("SELECT ID, ACTIVED, DATE_CREATED, DATE_MODIFIED, UPDT_ID, CREDENTIAL, NICKNAME, USERNAME, WEIXIN, QQ, VIP " + 
			"FROM student_user_info where username=#{username}")
	StudentUserInfo getByUsername(@Param("username") String username);
	@Select("SELECT ID, ACTIVED, DATE_CREATED, DATE_MODIFIED, UPDT_ID, CREDENTIAL, NICKNAME, USERNAME, WEIXIN, QQ, VIP " + 
			"FROM student_user_info where NICKNAME=#{nickName}")
	StudentUserInfo getByNickName(@Param("nickName") String nickName);
	@Select("SELECT ID, ACTIVED, DATE_CREATED, DATE_MODIFIED, UPDT_ID, CREDENTIAL, NICKNAME, USERNAME, WEIXIN, QQ, VIP " + 
			"FROM student_user_info where NICKNAME like  concat(#{nickName}, '%') ")
	List<StudentUserInfo> findByNickName(@Param("nickName") String nickName);
	@Select("SELECT ID, ACTIVED, DATE_CREATED, DATE_MODIFIED, UPDT_ID, CREDENTIAL, NICKNAME, USERNAME, WEIXIN, QQ, VIP " + 
			"FROM student_user_info ")
	List<StudentUserInfo> findPageAll(RowBounds brounds);
	@Select("SELECT count( ID) " + 
			"FROM student_user_info ")
	int getCount();
}
