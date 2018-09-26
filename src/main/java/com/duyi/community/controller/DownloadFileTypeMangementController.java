/**
 * 
 */
package com.duyi.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.community.domain.DownloadFileTypeInfo;
import com.duyi.community.service.IDownloadFileService;

/**
 * @author wangyan
 *
 */
@RestController
@RequestMapping("/api/downloadFileType")
public class DownloadFileTypeMangementController {

	@Autowired
	private IDownloadFileService downloadFileService;
	@RequestMapping(method=RequestMethod.POST ,produces="application/json",consumes="application/json")
	public DownloadFileTypeInfo add(@RequestBody DownloadFileTypeInfo fileType) {
		return downloadFileService.addDownloadFileType(fileType);
	}
	@RequestMapping(value="/{id}",method= RequestMethod.GET ,produces="application/json")
	public DownloadFileTypeInfo get(@PathVariable Long id) {
		return downloadFileService.getDownloadFileType(id);

	}
	@RequestMapping(method= {RequestMethod.PUT,RequestMethod.PATCH} ,produces="application/json",consumes="application/json")
	public DownloadFileTypeInfo modify(@RequestBody DownloadFileTypeInfo fileType) {
		return downloadFileService.updateDownloadFileType(fileType);

	}
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public Page<DownloadFileTypeInfo> findAll(Pageable pageable){
		return downloadFileService.findDownloadFileTypes(pageable);
	}
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		downloadFileService.deleteDownloadFileType(id);

	}
	@RequestMapping(path="/list",method=RequestMethod.GET,produces="application/json")
	public List<DownloadFileTypeInfo> findAll(){
		return downloadFileService.findAllDownloadFileTypes();
	}
}
