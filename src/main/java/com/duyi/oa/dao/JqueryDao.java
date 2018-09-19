package com.duyi.oa.dao;

import com.duyi.oa.domain.JqueryBody;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface JqueryDao {
    @Select("select id, attribute, first_class, second_class, ret_value, summarize, parameter, example, update_time from crawl_jquery")
    public ArrayList<JqueryBody> getJqInfo();
}
