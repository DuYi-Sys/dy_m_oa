/**
 * 
 */
package com.duyi.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.duyi.admin.domain.AdminRoleInfo;

/**
 * @author wangyan
 *
 */
public interface AdminRoleDao {
	
	@Insert( "INSERT INTO admin_role  ( NAME, CN_NAME)  VALUES(#{name},#{cnName} ) " )
	int add(AdminRoleInfo role);
	
	int update(AdminRoleInfo role);
	@Insert("INSERT INTO admin_role_user (ROLE_ID,USER_ID) VALUES(#{roleId},#{userId})")
	int addRoleUsers(@Param("roleId") Long roleId,@Param("userId") Long userId);
	@Insert("INSERT INTO admin_role_operation (ROLE_ID,OPERATION_ID) VALUES(#{roleId},#{operationId})")
	int addRoleOperations(@Param("roleId") Long roleId,@Param("operationId") Long operationId);
	@Select("select * from admin_role where id =#{id}")
	AdminRoleInfo getById(Long id);
	@Select("select * from admin_role where name =#{name}")
	AdminRoleInfo getByName(String name);

	@Select("select * from admin_role ar left join admin_role_user aru on ar.id=aru.role_id where aru.user_id=#{userId}")
//	@Results({
//		@Result(property="operations",column="id",many=@Many(select="com.duyi.admin.dao.AdminOperationDao.findByRoleId"))
//	})
	List<AdminRoleInfo> findByUserId(Long userId);
	@Select("select * from admin_role ar left join admin_role_user aru on ar.id=aru.role_id where ar.id=#{roleId} and aru.user_id=#{userId}")
	AdminRoleInfo getByRoleIdAndUserId(@Param("roleId") Long roleId,@Param("userId")Long userId);
	@Select("select * from admin_role ar left join admin_role_operation aro on ar.id=aro.role_id where ar.id=#{roleId} and aro.operation_id=#{operationId}")
	AdminRoleInfo getByRoleIdAndOperationId(@Param("roleId") Long roleId,@Param("operationId")Long operationId);
	@Select("select * from admin_role ")
	@Results({
		@Result(property="operations",column="id",many=@Many(select="com.duyi.admin.dao.AdminOperationDao.findByRoleId"))
	})
	List<AdminRoleInfo> findPageAll(RowBounds brounds);
	@Select("select * from admin_role ")
	@Results({
		@Result(property="operations",column="id",many=@Many(select="com.duyi.admin.dao.AdminOperationDao.findByRoleId"))
	})
	List<AdminRoleInfo> findAll();

	@Select("select * from admin_role where name like  concat('%', #{name}, '%')")
	List<AdminRoleInfo> findByName(RowBounds brounds,String name);

	@Select("select count(id) from admin_role ")
	int getCount();
	@Select("select count(id) from admin_role where name like  concat('%', #{name}, '%')")
	int getCountByName(String name);

}
