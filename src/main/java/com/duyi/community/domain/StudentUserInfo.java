/**
 * 
 */
package com.duyi.community.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 用户注册信息
 * @author wangyan
 *
 */
public class StudentUserInfo {

	private Long id;
	private String username;
	private String nickName;
	@JsonIgnore
	private String credential;
	private boolean actived=true;
	private String unencodePassword;
	private String weixin;
	private String qq;
	private boolean vip;
	private Long studentBodyId;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getCredential() {
		return credential;
	}
	public void setCredential(String credential) {
		this.credential = credential;
	}
	public boolean isActived() {
		return actived;
	}
	public void setActived(boolean actived) {
		this.actived = actived;
	}
	public String getUnencodePassword() {
		return unencodePassword;
	}
	public void setUnencodePassword(String unencodePassword) {
		this.unencodePassword = unencodePassword;
	}
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public boolean isVip() {
		return vip;
	}
	public void setVip(boolean vip) {
		this.vip = vip;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getStudentBodyId() {
		return studentBodyId;
	}
	public void setStudentBodyId(Long studentBodyId) {
		this.studentBodyId = studentBodyId;
	}
	
}
