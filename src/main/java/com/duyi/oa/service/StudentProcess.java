package com.duyi.oa.service;

import com.duyi.oa.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentProcess {
    @Autowired
    StudentDao studentDao;

}
