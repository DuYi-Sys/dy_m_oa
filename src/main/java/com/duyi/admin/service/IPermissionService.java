/**
 * 
 */
package com.duyi.admin.service;

/**
 * @author wangyan
 *
 */
public interface IPermissionService {
	boolean hasPermission(String path, String method,String[] roleNames);
}
