package com.duyi.oa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.oa.dao.TopicDao;
import com.duyi.oa.domain.TopicBody;
import com.duyi.oa.service.TopicProcess;


/**
 * @author wangyan
 *
 */
@RestController
@RequestMapping("/api/topic")
public class TopicController {
    @Autowired
    private TopicProcess topicProcess;// 问题处理对象

    @Autowired
    private TopicDao topicDao;

    @RequestMapping( method= {RequestMethod.GET},produces="application/json" )
    public Page<TopicBody> selectOperation(Pageable pageable, Long level){
        Page<TopicBody> res = topicProcess.selectTopic(pageable, level);
        return  res;
    }

    @RequestMapping( method=RequestMethod.GET, produces="application/json",path = "getChildTopic")
    public List<TopicBody> selectChildTopic( Long parentId ){
        ArrayList<TopicBody> res = topicDao.selectParentTopic(parentId);
        System.out.println("res"+parentId);
        return  res;
    }
}
