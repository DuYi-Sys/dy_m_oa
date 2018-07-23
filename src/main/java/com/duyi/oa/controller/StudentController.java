package com.duyi.oa.controller;

import com.duyi.oa.dao.StudentDao;
import com.duyi.oa.domain.StudentBody;
import com.duyi.oa.service.StudentProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangyan
 *
 */

@RestController
@RequestMapping("/api/studentOperation")
public class StudentController {
    @Autowired
    private StudentProcess studentProcess;// 问题处理对象

    @Autowired
    private StudentDao studentDao;

    @RequestMapping( method=RequestMethod.POST, produces="application/json",consumes="application/json" )
    public int addQuestion(@RequestBody StudentBody operation){
        int res = studentDao.insertStudentInfo(operation);
        return  res;
    }

    @RequestMapping( method= {RequestMethod.DELETE, RequestMethod.PATCH },produces="application/json" )
    public int deleteOperation(@RequestParam(name="id")Long id){
        int res = studentDao.deleteOperation(id);
        return  res;
    }


}
