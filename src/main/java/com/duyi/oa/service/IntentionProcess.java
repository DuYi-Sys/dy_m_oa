package com.duyi.oa.service;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.commons.page.PageableExecutionUtils;
import com.duyi.oa.dao.IntentionDao;
import com.duyi.oa.domain.IntentionBody;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IntentionProcess {

    @Autowired
    IntentionDao intentionDao;
    public int insertIntention(IntentionBody operation){
        int res = intentionDao.insertIntention(operation);
        return  res;
    }
    public int updateIntention(IntentionBody operation){
        int res = intentionDao.updateIntention(operation);
        return  res;
    }
    public int claimIntention(Long claimId, Long id){
        int res = intentionDao.claimIntenion(id,claimId);
        return  res;
    }
    public int deleteIntention(Long id){
        int res = intentionDao.deleteIntention(id);
        return  res;
    }

    public Page<IntentionBody>  selectOperation(Pageable pageable, Long claimId, int status){
        List<IntentionBody> res = new ArrayList(); // res set
        final int cnt = intentionDao.countIntention( claimId, status);
        pageable = PageableExecutionUtils.calculatePageable(cnt, pageable);
        RowBounds bounds=new RowBounds(pageable.getOffset(),pageable.getPageSize());
        res = intentionDao.selectIntention(bounds, claimId, status);
        return PageableExecutionUtils.getPage(res, pageable, new PageableExecutionUtils.TotalSupplier() {
            @Override
            public long get() {
                return cnt;
            }
        });
    }
}
