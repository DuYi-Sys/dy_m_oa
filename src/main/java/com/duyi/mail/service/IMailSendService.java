/**
 * 
 */
package com.duyi.mail.service;

/**
 * @author wangyan
 *
 */
public interface IMailSendService {
	void sendMail(String subject,String to,String cc,String content);
}
