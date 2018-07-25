package com.duyi.oa.controller;

import com.duyi.oa.service.QuestionProcess;
import com.duyi.oa.domain.QuestionBody;
import com.duyi.oa.dao.QuestionDao;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * @author wangyan
 *
 */
@RestController
@RequestMapping("/api/addQuestion")
public class QuestionController {
    @Autowired
    private QuestionProcess operationService;// 问题处理对象

    @Autowired
    private QuestionDao operationDao;

    @RequestMapping( method=RequestMethod.POST, produces="application/json",consumes="application/json" )
    public int addQuestion(@RequestBody  QuestionBody operation) {
        int res = operationDao.insertQuestion(operation);
        return  res;
    }

    @RequestMapping( method= {RequestMethod.PUT, RequestMethod.PATCH },produces="application/json",consumes="application/json" )
    public int updateOperation(@RequestBody  QuestionBody operation){
        int res = operationDao.updateOperation(operation);
        return  res;
    }

    @RequestMapping( method= {RequestMethod.GET, RequestMethod.PATCH },produces="application/json" )
    public List<QuestionBody> selectOperation(Long topicId, Long reviewerId, Long status){
        ArrayList<QuestionBody> res = operationService.selectOperation(topicId,  reviewerId, status);
        return  res;
    }

    @RequestMapping( method= {RequestMethod.DELETE, RequestMethod.PATCH },produces="application/json" )
    public int deleteOperation(@RequestParam(name="id")Long id){
        int res = operationDao.deleteOperation(id);
        return  res;
    }
}