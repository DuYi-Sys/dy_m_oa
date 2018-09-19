package com.duyi.oa.controller;

import com.duyi.oa.dao.JsDao;
import com.duyi.oa.domain.JsBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @author liming
 *
 */
@RestController
@RequestMapping("/api/crawlJs")
public class CrawlJsController {
    @Autowired
    private JsDao jsDao; // 问题处理对象

    @RequestMapping( method= RequestMethod.GET, produces="application/json",consumes="application/json" )
    public ArrayList<JsBody>  getJsInfo() {
        ArrayList<JsBody> res = jsDao.getJsInfo();
        return  res;
    }
}