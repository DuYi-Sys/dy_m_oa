package com.duyi.oa.controller;

import com.duyi.oa.dao.CssDao;
import com.duyi.oa.domain.CssBody;
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
@RequestMapping("/api/crawlCss")

public class CrawlCssController {
    @Autowired
    private CssDao cssDao; // Css Dao

    @RequestMapping( method= RequestMethod.GET, produces="application/json",consumes="application/json" )
    public ArrayList<CssBody> getCssInfo(){
        ArrayList<CssBody> res = cssDao.getCssInfo();
        return  res;
    }
}
