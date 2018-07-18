/**
 * 
 */
package com.duyi.admin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangyan
 *
 */
@RestController
@RequestMapping("/api/adminlogin")
public class AdminLoginContorller {

	@RequestMapping
	public boolean login() {
		return true;
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
