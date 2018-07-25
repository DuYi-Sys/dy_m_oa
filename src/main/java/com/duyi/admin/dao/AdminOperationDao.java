/**
 * 
 */
package com.duyi.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.duyi.admin.domain.AdminOperationInfo;

/**
 * @author wangyan
 *
 */
public interface AdminOperationDao {
	
	
	@Insert("INSERT INTO admin_operation(NAME, `PATH`, OPERATION) VALUES(#{name}, #{path}, #{operation})")
	int add(AdminOperationInfo operation);

	@Update("UPDATE admin_operation set name=#{name}, path=#{path},operation=#{operation} WHERE id=#{id}")
	int update(AdminOperationInfo operation);
	@Delete("delete  from admin_operation where id =#{id}")

	void deleteById(Long id);
	@Select("select count(id) from admin_operation")
	int getCount();

	@Select("select * from admin_operation")
	List<AdminOperationInfo> findAll(RowBounds brounds);
	@Select("select * from admin_operation where id=#{id}")

	AdminOperationInfo getById(Long id);

	@Select("select o.* from admin_operation o left join admin_role_operation ro on o.id=ro.operation_id where ro.role_id=#{roleId} ")
	List<AdminOperationInfo> findByRoleId(Long roleId);
	@Select("select * from admin_operation where path like  concat('%', #{path}, '%')" )
	List<AdminOperationInfo> findByPath(String path);
}
