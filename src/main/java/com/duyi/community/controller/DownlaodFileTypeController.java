/**
 * 
 */
package com.duyi.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duyi.community.domain.DownloadFileTypeInfo;
import com.duyi.community.service.IDownloadFileService;

/**
 * @author wangyan
 *
 */
@RestController
@RequestMapping("/c/downlaodFileType")
public class DownlaodFileTypeController {
	@Autowired
	private IDownloadFileService downloadFileService;
	
	@RequestMapping(method=RequestMethod.GET,produces="application/json")
	public List<DownloadFileTypeInfo> findAll(){
		return downloadFileService.findAllDownloadFileTypes();
	}
}
