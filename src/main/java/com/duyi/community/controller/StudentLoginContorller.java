/**
 * 
 */
package com.duyi.community.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duyi.commons.validate.WebException;

/**
 * @author wangyan
 *
 */
@RestController
@RequestMapping("/c/login")
public class StudentLoginContorller {

	@RequestMapping
	public boolean login() {
		return true;
	}
	@RequestMapping("usererr")
	public void usererr() {
		throw new WebException(1, "用户名或密码错误");
	}

	@RequestMapping("unauthorized")
	public ResponseEntity<?> unauthorized() {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized");
	}
	@RequestMapping("forbidden")
	public ResponseEntity<?> forbidden() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("forbidden");
	}
}
