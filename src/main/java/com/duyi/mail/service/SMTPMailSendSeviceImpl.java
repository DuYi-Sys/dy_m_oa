/**
 * 
 */
package com.duyi.mail.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.springframework.stereotype.Service;

import com.duyi.commons.log.Trace;

/**
 * @author wangyan
 *
 */
@Service
public class SMTPMailSendSeviceImpl {

	private static Trace log = Trace.register(SMTPMailSendSeviceImpl.class);

	private String from="yan.wang@duyi-inc.com";

	private String host="smtp.qiye.aliyun.com";
	private String username="yan.wang@duyi-inc.com";
	private String password="Wangyan0122";
	private String protocol="smtp";
	private int port = -1;


	/**
	 * Activate the specified options, such as the smtp host, the recipient, from,
	 * etc.
	 */
	public Message activateOptions(Session session) {
		
		
		Message msg = new MimeMessage(session);
		return msg;
	}

	protected void addressMessage(String to, String cc, final Message msg) throws MessagingException {
		if (from != null) {
			msg.setFrom(getAddress(from));
		} else {
			msg.setFrom();
		}

		if (to != null && to.length() > 0) {
			msg.setRecipients(Message.RecipientType.TO, parseAddress(to));
		}

		// Add CC receipients if defined.
		if (cc != null && cc.length() > 0) {
			msg.setRecipients(Message.RecipientType.CC, parseAddress(cc));
		}
	}

	protected InternetAddress[] parseAddress(String addressStr) {
		try {
			return InternetAddress.parse(addressStr, true);
		} catch (AddressException e) {
			log.error("Could not parse address [" + addressStr + "].", e);
			return null;
		}
	}

	protected InternetAddress getAddress(String addressStr) {
		try {
			return new InternetAddress(addressStr);
		} catch (AddressException e) {
			log.error("Could not parse address [" + addressStr + "].", e);
			return null;
		}
	}

	protected Session createSession() {
		Properties props = null;
		try {
			props = new Properties(System.getProperties());
		} catch (SecurityException ex) {
			props = new Properties();
		}

		String prefix = "mail.smtp";
		if (protocol != null) {
			props.put("mail.transport.protocol", protocol);
			prefix = "mail." + protocol;
		}
		if (host != null) {
			props.put(prefix + ".host", host);
		}
		if (port > 0) {
			props.put(prefix + ".port", String.valueOf(port));
		}

		Authenticator auth = null;
		if (password != null && username != null) {
			props.put(prefix + ".auth", "true");
			auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			};
		}
		Session session = Session.getInstance(props, auth);
		if (protocol != null) {
			session.setProtocolForAddress("rfc822", protocol);
		}

		return session;
	}

	protected String formatBody(String content) {

		StringBuffer sbuf = new StringBuffer();
		sbuf.append(content);
		return sbuf.toString();
	}


	/* (non-Javadoc)
	 * @see com.duyi.mail.service.IMailSendService#sendMail(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
//	@Override
	public void sendMail(Session session,String subject, String to, String cc, String content) {
		Message msg=activateOptions(session);
		try {
			addressMessage(to,cc,msg);
			if (subject != null) {
				try {
					msg.setSubject(MimeUtility.encodeText(subject, "UTF-8", null));
				} catch (UnsupportedEncodingException ex) {
					log.error("Unable to encode SMTP subject", ex);
				}
			}
		} catch (MessagingException e) {
			log.error("Could not activate SMTP mail service options.", e);
		}
		try {
			String s = formatBody(content);
//			boolean allAscii = true;
//			for (int i = 0; i < s.length() && allAscii; i++) {
//				allAscii = s.charAt(i) <= 0x7F;
//			}
			MimeBodyPart part;
//			if (allAscii) {
				part = new MimeBodyPart();
				part.setContent(s, "text/html;charset=UTF-8");
//			} else {
//				try {
//					ByteArrayOutputStream os = new ByteArrayOutputStream();
//					Writer writer = new OutputStreamWriter(MimeUtility.encode(os, "quoted-printable"), "UTF-8");
//					writer.write(s);
//					writer.close();
//					InternetHeaders headers = new InternetHeaders();
//					headers.setHeader("Content-Type", "text/html; charset=UTF-8");
//					headers.setHeader("Content-Transfer-Encoding", "quoted-printable");
//					part = new MimeBodyPart(headers, os.toByteArray());
//				} catch (Exception ex) {
//					StringBuffer sbuf = new StringBuffer(s);
//					for (int i = 0; i < sbuf.length(); i++) {
//						if (sbuf.charAt(i) >= 0x80) {
//							sbuf.setCharAt(i, '?');
//						}
//					}
//					part = new MimeBodyPart();
//					part.setContent(sbuf.toString(), "text/html; charset=UTF-8");
//				}
//			}

			Multipart mp = new MimeMultipart();
			mp.addBodyPart(part);
			msg.setContent(mp);

			msg.setSentDate(new Date());
			Transport.send(msg);
		} catch (MessagingException e) {
			log.error("Error occured while sending e-mail notification.", e);
		} catch (RuntimeException e) {
			log.error("Error occured while sending e-mail notification.", e);
		}
	}
	public static void main(String[] args) {
		
		SMTPMailSendSeviceImpl sender=new SMTPMailSendSeviceImpl();
		Session session = sender.createSession();
		for(int i=0;i<2;i++) {
			sender.sendMail(session,"session 测试", "yan.wang@duyi-inc.com", null, "<html><body><a href='www.baidu.com'>baidu"+new Date()+"</a></body></html>");
			try {
				Thread.sleep(60*60*1000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
