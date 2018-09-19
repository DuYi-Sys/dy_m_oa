package com.duyi.oa.service;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.commons.page.PageableExecutionUtils;
import com.duyi.oa.dao.JsDao;
import com.duyi.oa.domain.JsBody;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrawlJslProcess {
    @Autowired
    JsDao jsDao;
}
