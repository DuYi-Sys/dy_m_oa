/**
 * 
 */
package com.duyi.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.community.domain.DownloadFileInfo;
import com.duyi.community.service.IDownloadFileService;

/**
 * @author wangyan
 *
 */
@RestController
@RequestMapping("/c/downloadFile")
public class DownloadFileController {
	
	@Autowired
	private IDownloadFileService downloadfileService;
	@RequestMapping(method=RequestMethod.GET)
	public Page<DownloadFileInfo> findDownloadFiles(@RequestParam(value="typeId",required=false) Long typeId,Pageable pageable) {
		if(typeId!=null && typeId!=0L) {
			return downloadfileService.findDownloadFilesByTypeId(pageable, typeId);
		}
		return downloadfileService.findDownloadFiles(pageable);
	}
}
