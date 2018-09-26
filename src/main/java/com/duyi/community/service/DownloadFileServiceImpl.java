/**
 * 
 */
package com.duyi.community.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.commons.page.PageableExecutionUtils;
import com.duyi.commons.page.PageableExecutionUtils.TotalSupplier;
import com.duyi.community.dao.DownloadFileDao;
import com.duyi.community.dao.DownloadFileTypeDao;
import com.duyi.community.domain.DownloadFileInfo;
import com.duyi.community.domain.DownloadFileTypeInfo;

/**
 * @author wangyan
 *
 */
@Service
public class DownloadFileServiceImpl implements IDownloadFileService {

	@Autowired
	private DownloadFileDao fileDao;
	@Autowired
	private DownloadFileTypeDao fileTypeDao;
	/* (non-Javadoc)
	 * @see com.duyi.community.service.IDownloadFileService#addDownloadFile(com.duyi.community.domain.DownloadFileInfo)
	 */
	@Override
	public DownloadFileInfo addDownloadFile(DownloadFileInfo fileInfo) {
		Assert.notNull(fileInfo);
		fileDao.add(fileInfo);
		return fileInfo;
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IDownloadFileService#updateDownloadFile(com.duyi.community.domain.DownloadFileInfo)
	 */
	@Override
	public DownloadFileInfo updateDownloadFile(DownloadFileInfo fileInfo) {
		Assert.notNull(fileInfo);
		fileDao.update(fileInfo);
		return fileInfo;
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IDownloadFileService#findDownloadFiles(com.duyi.commons.page.Pageable)
	 */
	@Override
	public Page<DownloadFileInfo> findDownloadFiles(Pageable pageable) {
		final int count= fileDao.getCountByAll();

		pageable=PageableExecutionUtils.calculatePageable(count, pageable);


		RowBounds bounds=new RowBounds(pageable.getOffset(),pageable.getPageSize());
		List<DownloadFileInfo> files=fileDao.findPageAll(bounds);
		return PageableExecutionUtils.getPage(files, pageable, new TotalSupplier() {
			@Override
			public long get() {
				return count;
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IDownloadFileService#deleteDownloadFile(java.lang.Long)
	 */
	@Override
	public void deleteDownloadFile(Long id) {
		fileDao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IDownloadFileService#addDownloadFileType(com.duyi.community.domain.DownloadFileTypeInfo)
	 */
	@Override
	public DownloadFileTypeInfo addDownloadFileType(DownloadFileTypeInfo fileTypeInfo) {
		Assert.notNull(fileTypeInfo);
		fileTypeDao.add(fileTypeInfo);
		return fileTypeInfo;
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IDownloadFileService#updateDownloadFileType(com.duyi.community.domain.DownloadFileTypeInfo)
	 */
	@Override
	public DownloadFileTypeInfo updateDownloadFileType(DownloadFileTypeInfo fileTypeInfo) {
		Assert.notNull(fileTypeInfo);
		fileTypeDao.update(fileTypeInfo);
		return fileTypeInfo;
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IDownloadFileService#findDownloadFileTypes(com.duyi.commons.page.Pageable)
	 */
	@Override
	public Page<DownloadFileTypeInfo> findDownloadFileTypes(Pageable pageable) {
		final int count= fileTypeDao.getCountByAll();

		pageable=PageableExecutionUtils.calculatePageable(count, pageable);


		RowBounds bounds=new RowBounds(pageable.getOffset(),pageable.getPageSize());
		List<DownloadFileTypeInfo> fileTypes=fileTypeDao.findPageAll(bounds);
		return PageableExecutionUtils.getPage(fileTypes, pageable, new TotalSupplier() {
			@Override
			public long get() {
				return count;
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IDownloadFileService#findAllDownloadFileTypes()
	 */
	@Override
	public List<DownloadFileTypeInfo> findAllDownloadFileTypes() {
		return fileTypeDao.findAll();
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IDownloadFileService#deleteDownloadFileType(java.lang.Long)
	 */
	@Override
	public void deleteDownloadFileType(Long id) {
		fileTypeDao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IDownloadFileService#findDownloadFilesByTypeId(com.duyi.commons.page.Pageable, java.lang.Long)
	 */
	@Override
	public Page<DownloadFileInfo> findDownloadFilesByTypeId(Pageable pageable, Long typeId) {
		final int count= fileDao.getCountByTypeId(typeId);

		pageable=PageableExecutionUtils.calculatePageable(count, pageable);


		RowBounds bounds=new RowBounds(pageable.getOffset(),pageable.getPageSize());
		List<DownloadFileInfo> files=fileDao.findPageByTypeId(typeId, bounds);
		return PageableExecutionUtils.getPage(files, pageable, new TotalSupplier() {
			@Override
			public long get() {
				return count;
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IDownloadFileService#getDownloadFile(java.lang.Long)
	 */
	@Override
	public DownloadFileInfo getDownloadFile(Long id) {
		return fileDao.get(id);
	}

	/* (non-Javadoc)
	 * @see com.duyi.community.service.IDownloadFileService#getDownloadFileType(java.lang.Long)
	 */
	@Override
	public DownloadFileTypeInfo getDownloadFileType(Long id) {
		return fileTypeDao.get(id);
	}

}
