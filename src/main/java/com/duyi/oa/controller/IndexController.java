package com.duyi.oa.controller;

import com.duyi.oa.domain.Test;
import com.duyi.oa.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    TestService testService;

    @RequestMapping(value = "/")
    @ResponseBody
    public ModelAndView index() {
        List<Test> testList = testService.getAllTest();
        for (Test test : testList) {
            System.out.println(test.getA());
        }
        return new ModelAndView("index.html");
    }

}