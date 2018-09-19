package com.duyi.oa.dao;


import com.duyi.oa.domain.JsBody;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
public interface JsDao {
    @Select("select id,js_obj,js_desc,js_attr,js_funs,update_time from crawl_js")
    public ArrayList<JsBody> getJsInfo();
}
