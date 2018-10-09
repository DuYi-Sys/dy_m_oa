/**
 * 
 */
package com.duyi.community.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangyan
 *
 */
@Controller
@RequestMapping("/c/jsonp")
public class JsonpTestController {
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public String jsonp(HttpServletRequest request) {
		String callback = request.getParameter("callback");
		System.out.println("callback "+callback);
		return callback + "([{name:'jsonp',age:'30'},{name:'jack',age:'90'}])";
	}

}
