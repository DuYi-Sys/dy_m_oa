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

import com.duyi.community.domain.DownloadFileInfo;

/**
 * @author wangyan
 *
 */
public interface DownloadFileDao {
	@Insert("INSERT INTO download_file " + 
			"(TITLE, DESCRIPTION, DOWNLOAD_URL, TYPE_ID) " + 
			"VALUES(#{title},#{description},#{downloadUrl},#{typeId})")
	int add(DownloadFileInfo file);
	@Update("UPDATE download_file " + 
			"SET TITLE=#{title}, DESCRIPTION=#{description}, DOWNLOAD_URL=#{downloadUrl}, TYPE_ID=#{typeId} " + 
			"WHERE ID=#{id} ")
    int update(DownloadFileInfo file);
    @Select("SELECT ID, TITLE, DESCRIPTION, DOWNLOAD_URL, TYPE_ID " + 
    		"FROM download_file")
	List<DownloadFileInfo> findPageAll(RowBounds bounds);
    @Select("SELECT ID, TITLE, DESCRIPTION, DOWNLOAD_URL, TYPE_ID " + 
    		"FROM download_file where TYPE_ID=#{typeId}")
	List<DownloadFileInfo> findPageByTypeId(@Param("typeId") Long typeId,RowBounds bounds);
    @Select("SELECT count(ID)  FROM download_file")
	int getCountByAll();
    @Select("SELECT count(ID)  FROM download_file where TYPE_ID=#{typeId}")
	int getCountByTypeId(@Param("typeId")Long typeId);
    @Select("SELECT ID, TITLE, DESCRIPTION, DOWNLOAD_URL, TYPE_ID " + 
    		"FROM download_file where id =#{id}")
	DownloadFileInfo get(@Param("id")Long id);
	@Delete("delete  from download_file where id =#{id}")
	void deleteById(@Param("id")Long id);
	
}
