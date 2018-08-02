package com.duyi.oa.controller;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.oa.service.QuestionProcess;
import com.duyi.oa.domain.QuestionBody;
import com.duyi.oa.dao.QuestionDao;
import com.duyi.security.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author wangyan
 *
 */
@RestController
@RequestMapping("/api/s/addQuestion")
public class QuestionController {
    @Autowired
    private QuestionProcess operationService;// 问题处理对象

    @Autowired
    private QuestionDao operationDao;

    @RequestMapping( method=RequestMethod.POST, produces="application/json",consumes="application/json" )
    public int addQuestion(@RequestBody  QuestionBody operation) {
        Long uploadId =SecurityContextHolder.getContext().getPrincipal().getId();
        operation.setUploadId(uploadId); // insert body
        operation.setStatus(1);
        int res = operationDao.insertQuestion(operation);
        return  res;
    }

    @RequestMapping( method=RequestMethod.POST,consumes="application/json", path = "auditQuestion")
    public int auditQuestion(@RequestParam(name="id") Long id,@RequestParam(name = "reason",required=false) String reason,@RequestParam(name = "status") Long status) {
        Long reviewer_id =SecurityContextHolder.getContext().getPrincipal().getId();
        int res = operationDao.auditOperation(id, reviewer_id, reason, status);
        return  res;
    }

    @RequestMapping( method= {RequestMethod.PUT, RequestMethod.PUT },produces="application/json",consumes="application/json" )
    public int updateOperation(@RequestBody  QuestionBody operation){
        int res = operationDao.updateOperation(operation);
        return  res;
    }

    @RequestMapping( method= {RequestMethod.GET},produces="application/json" )
    public Page<QuestionBody> selectOperation(Pageable pageable, @RequestParam(name="topicId") Long topicId, @RequestParam(name="uploadId")Long uploadId,
                                              @RequestParam(name="keyWord") String keyWord, @RequestParam(name="status") Long status, @RequestParam(name="date") String strData){
        if( strData.isEmpty() ) { strData = "1990-11-06"; }
        Page<QuestionBody> res = operationService.selectOperation(pageable, topicId, uploadId, keyWord, status, strData);
        return  res;
    }

    @RequestMapping( method= {RequestMethod.DELETE, RequestMethod.PATCH },produces="application/json" )
    public int deleteOperation(@RequestParam(name="id")Long id){
        int res = operationDao.deleteOperation(id);
        return  res;
    }
}