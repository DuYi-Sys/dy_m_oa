package com.duyi.oa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duyi.oa.dao.StudentDao;

@Service
public class StudentProcess {
    @Autowired
    StudentDao studentDao;

}
