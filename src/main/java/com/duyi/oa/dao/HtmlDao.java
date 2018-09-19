package com.duyi.oa.dao;

import com.duyi.oa.domain.HtmlBody;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface HtmlDao {
    @Select("select id, html_title, type, html_content, update_time from crawl_html")
    public ArrayList<HtmlBody> getHtmlInfo();
}
