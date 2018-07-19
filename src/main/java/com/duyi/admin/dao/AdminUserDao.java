/**
 * 
 */
package com.duyi.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.duyi.admin.domain.AdminUserInfo;

/**
 * @author wangyan
 *
 */
public interface AdminUserDao {
	@Insert("insert into admin_user " + 
			"(actived, credential, name, username) " + 
			"values(#{actived}, #{credential}, #{name}, #{username})")
//    @Options(useGeneratedKeys=true,keyProperty="id")
	Long add(AdminUserInfo user);
	@Update("UPDATE admin_user set actived=#{actived}, credential=#{credential}, username=#{username} WHERE id=#{id}")
	int update(AdminUserInfo user);
	@Select("select * from admin_user ")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="roles",column="id",many=@Many(select="com.duyi.admin.dao.AdminRoleDao.findByUserId"))
	})
	List<AdminUserInfo> findPageAll(RowBounds brounds);
	@Select("select * from admin_user where username like  concat('%', #{username}, '%')")
	List<AdminUserInfo> findByUsername(String username,RowBounds brounds);
	@Select("select * from admin_user where id=#{id} ")
	AdminUserInfo getById(Long id);
	@Select("select * from admin_user where username=#{username}")
	@Results({
		@Result(property="id",column="id"),
		@Result(property="roles",column="id",many=@Many(select="com.duyi.admin.dao.AdminRoleDao.findByUserId"))
	})
	AdminUserInfo getByUsername(String username);
	@Select("select count(id) from admin_user ")
	int getCount();
	@Select("select count(id) from admin_user where username=#{username} ")
	int getCountByUsername(String username);

}
