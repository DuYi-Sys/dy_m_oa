package com.duyi.oa.service;

import com.duyi.oa.dao.TestDao;
import com.duyi.oa.domain.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

@Service
public class TestService {

    @Autowired
    TestDao testDao;

    public List<Test> getAllTest() {
        List<Test> testList = testDao.getAll();
        if (testList != null) {
            return testList;
        } else {
            return Collections.emptyList();
        }
    }
}
