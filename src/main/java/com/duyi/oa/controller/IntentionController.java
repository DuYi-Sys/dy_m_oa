package com.duyi.oa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.oa.domain.IntentionBody;
import com.duyi.oa.service.IntentionProcess;
import com.duyi.security.SecurityContextHolder;


/**
 * @author liming
 *
 */
@RestController
@RequestMapping("/api/s/addIntention")
public class IntentionController { // intention
    @Autowired
    private IntentionProcess intentionProcess;// 问题处理对象

    @RequestMapping( method=RequestMethod.POST, produces="application/json",consumes="application/json" )
    public int addIntention(@RequestBody IntentionBody operation){
    		operation.setStatus(1);
        Long userId = SecurityContextHolder.getContext().getPrincipal().getId();
        operation.setUploadId(userId);
        int res = intentionProcess.insertIntention(operation);
        return  res;
    }

    @RequestMapping( method=RequestMethod.PUT, produces="application/json",consumes="application/json" )
    public int updateIntention(@RequestBody IntentionBody operation){
        int res = intentionProcess.updateIntention(operation);
        return  res;
    }

    @RequestMapping( method=RequestMethod.POST, path = "claimIntention" )
    public int claimIntention(@RequestParam(name="id") Long id){
        Long claimId = SecurityContextHolder.getContext().getPrincipal().getId();
        int res = intentionProcess.claimIntention(claimId, id);
        return  res;
    }

    @RequestMapping( method=RequestMethod.DELETE )
    public int deleteIntention(@RequestParam(name="id") Long id){
        int res = intentionProcess.deleteIntention(id);
        return  res;
    }

    @RequestMapping(path="/search",method=RequestMethod.GET,produces="application/json" )
    public Page<IntentionBody> getIntention(Pageable pageable,@RequestParam(name="status",required=false) Integer status) {
        Long claimId = SecurityContextHolder.getContext().getPrincipal().getId();

        Page<IntentionBody> res = intentionProcess.selectOperation(pageable,claimId,status);
        return  res;
    }
    @RequestMapping( method=RequestMethod.GET,produces="application/json" )
    public Page<IntentionBody> getIntention(Pageable pageable) {
        Page<IntentionBody> res = intentionProcess.selectOperation(pageable);
        return  res;
    }
}
