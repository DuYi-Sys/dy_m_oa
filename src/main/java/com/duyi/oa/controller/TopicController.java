package com.duyi.oa.controller;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.oa.domain.TopicBody;
import com.duyi.oa.service.QuestionProcess;
import com.duyi.oa.domain.QuestionBody;
import com.duyi.oa.dao.QuestionDao;
import com.duyi.oa.service.TopicProcess;
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
@RequestMapping("/api/topic")
public class TopicController {
    @Autowired
    private TopicProcess topicProcess;// 问题处理对象

    @RequestMapping( method= {RequestMethod.GET, RequestMethod.PATCH },produces="application/json" )
    public Page<TopicBody> selectOperation(Pageable pageable, Long level){
        Page<TopicBody> res = topicProcess.selectTopic(pageable, level);
        return  res;
    }
}
