package com.duyi.oa.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.commons.page.PageableExecutionUtils;
import com.duyi.oa.dao.TopicDao;
import com.duyi.oa.domain.TopicBody;

@Service
public class TopicProcess {
    @Autowired
    TopicDao topicDao;

    public Page<TopicBody> selectTopic(Pageable pageable, Long level) {
        final  int count = topicDao.getCount(level);
        pageable = PageableExecutionUtils.calculatePageable(count, pageable);
        RowBounds bounds=new RowBounds(pageable.getOffset(),pageable.getPageSize());

        ArrayList<TopicBody>  res = topicDao.selectTopic(bounds, level);
        return PageableExecutionUtils.getPage(res, pageable, new PageableExecutionUtils.TotalSupplier() {
            @Override
            public long get() {
                return count;
            }
        });
    }
}
