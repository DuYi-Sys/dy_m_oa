package com.duyi.oa.controller;

import com.duyi.oa.dao.JqueryDao;
import com.duyi.oa.dao.JsDao;
import com.duyi.oa.domain.JqueryBody;
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
@RequestMapping("/api/crawlJquery")
public class CrawlJqueryController {
    @Autowired
    private JqueryDao jqDao; // 问题处理对象

    @RequestMapping( method= RequestMethod.GET, produces="application/json",consumes="application/json" )
    public ArrayList<JqueryBody>  getJsInfo() {
        ArrayList<JqueryBody> res = jqDao.getJqInfo();
        return  res;
    }
}
