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

import com.duyi.community.domain.DownloadFileTypeInfo;

/**
 * @author wangyan
 *
 */
public interface DownloadFileTypeDao {
	@Insert("INSERT INTO download_file_type (NAME)  VALUES(#{name})")
	int add(DownloadFileTypeInfo fileType);
	@Update("UPDATE download_file_type SET NAME=#{name} WHERE id=#{id}")
    int update(DownloadFileTypeInfo fileType);
	@Select("SELECT ID, NAME FROM download_file_type")
	List<DownloadFileTypeInfo> findPageAll(RowBounds brounds);
	@Select("SELECT ID, NAME FROM download_file_type")
	List<DownloadFileTypeInfo> findAll();

	@Select("SELECT count(ID) FROM download_file_type")
	int getCountByAll();
	@Select("SELECT ID, NAME FROM download_file_type where id=#{id}")
	DownloadFileTypeInfo get(@Param("id")Long id);
	@Delete("delete  from download_file_type where id =#{id}")
	void deleteById(@Param("id")Long id);
}
