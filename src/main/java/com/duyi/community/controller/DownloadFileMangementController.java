/**
 * 
 */
package com.duyi.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.community.domain.DownloadFileInfo;
import com.duyi.community.domain.DownloadFileTypeInfo;
import com.duyi.community.service.IDownloadFileService;

/**
 * @author wangyan
 *
 */
@RestController
@RequestMapping("/api/downloadFile")
public class DownloadFileMangementController {

	@Autowired
	private IDownloadFileService downloadFileService;
	@RequestMapping(method=RequestMethod.POST ,produces="application/json",consumes="application/json")
	public DownloadFileInfo add(@RequestBody DownloadFileInfo file) {
		return downloadFileService.addDownloadFile(file);
	}
	@RequestMapping(method= {RequestMethod.PUT,RequestMethod.PATCH} ,produces="application/json",consumes="application/json")
	public DownloadFileInfo modify(@RequestBody DownloadFileInfo file) {
		return downloadFileService.updateDownloadFile(file);

	}
	
	@RequestMapping(value="/{id}",method= RequestMethod.GET ,produces="application/json")
	public DownloadFileInfo get(@PathVariable Long id) {
		return downloadFileService.getDownloadFile(id);

	}
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public Page<DownloadFileInfo> findAll(Pageable pageable){
		return downloadFileService.findDownloadFiles(pageable);
	}
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		downloadFileService.deleteDownloadFile(id);

	}
}
