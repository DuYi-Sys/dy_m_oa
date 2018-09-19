package com.duyi.oa.dao;

import com.duyi.oa.domain.CssBody;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface CssDao {
    @Select("select id,tag, classification, syntax, value, intro,example ,update_time from crawl_css")
    public ArrayList<CssBody> getCssInfo();
}
