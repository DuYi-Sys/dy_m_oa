package com.duyi.oa.service;

import com.duyi.commons.page.Page;
import com.duyi.commons.page.Pageable;
import com.duyi.commons.page.PageableExecutionUtils;
import com.duyi.oa.dao.QuestionDao;
import com.duyi.oa.domain.QuestionBody;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class QuestionProcess {
    @Autowired
    QuestionDao questionDao;

    public Page<QuestionBody> selectOperation(Pageable pageable, Long topicId, Long uploadId, String keyWord, Long status, String strData){
        List<QuestionBody>  res = new ArrayList(); // res set
        int cnt = 0;
        if( uploadId > 0 && topicId <= 0 ){
            cnt = questionDao.getQuestionUploadIdCnt( uploadId, keyWord, status, strData);
            pageable = PageableExecutionUtils.calculatePageable(cnt, pageable);
            RowBounds bounds=new RowBounds(pageable.getOffset(),pageable.getPageSize());
            res = questionDao.selectQuestionUploadId(bounds, uploadId, keyWord, status, strData); // uploadId
        }else if(uploadId <= 0 && topicId >  0){
            cnt = questionDao.getQuestionTopicIdCnt(topicId, keyWord, status, strData);
            pageable = PageableExecutionUtils.calculatePageable(cnt, pageable);
            RowBounds bounds=new RowBounds(pageable.getOffset(),pageable.getPageSize());
            res = questionDao.selectQuestionTopicId(bounds, topicId, keyWord, status, strData); // topicId
        }else if (uploadId > 0 && topicId > 0){
            cnt = questionDao.getQuestionUploadTopicCnt(uploadId, topicId, keyWord, status, strData);
            pageable = PageableExecutionUtils.calculatePageable(cnt, pageable);
            RowBounds bounds=new RowBounds(pageable.getOffset(),pageable.getPageSize());
            res = questionDao.selectQuestionUploadTopic(bounds, uploadId, topicId, keyWord, status, strData);
        }

        final int count = cnt;
        return PageableExecutionUtils.getPage(res, pageable, new PageableExecutionUtils.TotalSupplier() {
            @Override
            public long get() {
                return count;
            }
        });
    }

    public HashMap<String, String> getGetUrlParams(HttpServletRequest request){
        HashMap<String, String> resMap = new HashMap<>(); // result set
        Enumeration req = request.getParameterNames();
        while( req.hasMoreElements() ) {
            String name = (String) req.nextElement();
            String value = request.getParameter(name);
            resMap.put(name, value);
        }
        return  resMap;
    }

    public HashMap<String, String > getPostUrlParams(HttpServletRequest request){
        HashMap<String, String> resMap = new HashMap();
        return  resMap;
    }
}
