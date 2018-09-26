/**
 * 
 */
package com.duyi.community.service;

import java.util.List;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.community.domain.DownloadFileInfo;
import com.duyi.community.domain.DownloadFileTypeInfo;

/**
 * @author wangyan
 *
 */
public interface IDownloadFileService {

	DownloadFileInfo addDownloadFile(DownloadFileInfo fileInfo);
	DownloadFileInfo updateDownloadFile(DownloadFileInfo fileInfo);
	Page<DownloadFileInfo> findDownloadFiles(Pageable pageable);
	Page<DownloadFileInfo> findDownloadFilesByTypeId(Pageable pageable,Long typeId);
	DownloadFileInfo getDownloadFile(Long id);
	void deleteDownloadFile(Long id);
	
	DownloadFileTypeInfo addDownloadFileType(DownloadFileTypeInfo fileTypeInfo);
	DownloadFileTypeInfo updateDownloadFileType(DownloadFileTypeInfo fileTypeInfo);
	Page<DownloadFileTypeInfo> findDownloadFileTypes(Pageable pageable);
	List<DownloadFileTypeInfo> findAllDownloadFileTypes();
	DownloadFileTypeInfo getDownloadFileType(Long id);
	void deleteDownloadFileType(Long id);

}
