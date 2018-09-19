package com.duyi.oa.controller;

import com.duyi.oa.dao.HtmlDao;
import com.duyi.oa.domain.HtmlBody;
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
@RequestMapping("/api/crawlHtml")

public class CrawlHtmlContronller {
    @Autowired
    private HtmlDao htmlDao; // html Dao

    @RequestMapping( method= RequestMethod.GET, produces="application/json",consumes="application/json" )
    public ArrayList<HtmlBody> getCssInfo(){
        ArrayList<HtmlBody> res = htmlDao.getHtmlInfo();
        return  res;
    }
}
