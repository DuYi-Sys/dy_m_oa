package com.duyi.oa.service;

import com.duyi.oa.dao.QuestionDao;
import com.duyi.oa.domain.QuestionBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class QuestionProcess {
    @Autowired
    QuestionDao questionDao;

    public ArrayList<QuestionBody> selectOperation(Long topicId, Long uploadId, String keyWord, Long status, String strData){
        ArrayList<QuestionBody> res = new ArrayList(); // res set
        if( uploadId > 0 && topicId <= 0 ){
            res = questionDao.selectQuestionUploadId(uploadId, keyWord, status, strData); // uploadId
        }else if(uploadId <= 0 && topicId >  0){
            res = questionDao.selectQuestionTopicId(topicId, keyWord, status, strData); // topicId
        }else if (uploadId > 0 && topicId > 0){
            res = questionDao.selectQuestionUploadTopic(uploadId, topicId, keyWord, status, strData);
        }

        return  res; // set
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
