package com.duyi.oa.controller;


import java.util.ArrayList;
import java.util.List;

import com.duyi.oa.dao.TopicDao;
import com.duyi.oa.domain.QuestionBody;
import com.duyi.oa.domain.StudentBody;
import com.duyi.oa.service.TopicProcess;
import com.duyi.security.SecurityContextHolder;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;

import com.duyi.oa.dao.IntentionDao;
import com.duyi.oa.domain.IntentionBody;
import com.duyi.oa.service.IntentionProcess;


/**
 * @author liming
 *
 */
@RestController
@RequestMapping("/api/addIntention")
public class IntentionController { // intention
    @Autowired
    private IntentionProcess intentionProcess;// 问题处理对象

    @RequestMapping( method=RequestMethod.POST, produces="application/json",consumes="application/json" )
    public int addIntention(@RequestBody IntentionBody operation){
        int res = intentionProcess.insertIntention(operation);
        return  res;
    }

    @RequestMapping( method=RequestMethod.PUT, produces="application/json",consumes="application/json" )
    public int updateIntention(@RequestBody IntentionBody operation){
        int res = intentionProcess.updateIntention(operation);
        return  res;
    }

    @RequestMapping( method=RequestMethod.POST,consumes="application/json", path = "claimIntention" )
    public int claimIntention(@RequestParam(name="id") Long id){
        Long claimId = SecurityContextHolder.getContext().getPrincipal().getId();
        int res = intentionProcess.claimIntention(claimId, id);
        return  res;
    }

    @RequestMapping( method=RequestMethod.DELETE,consumes="application/json" )
    public int deleteIntention(@RequestParam(name="id") Long id){
        int res = intentionProcess.deleteIntention(id);
        return  res;
    }

    @RequestMapping( method=RequestMethod.GET,consumes="application/json" )
    public Page<IntentionBody> getIntention(Pageable pageable,@RequestParam(name="claimId") Long claimId, @RequestParam(name="status") int status) {
        Page<IntentionBody> res = intentionProcess.selectOperation(pageable,claimId,status);
        return  res;
    }
}
