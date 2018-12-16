package pers.jim.util;

import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class MailUtil {
	@SuppressWarnings("restriction")
	public static void imapReceive(String host,int port,String username,String password){
		try {
            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory"; 
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			Properties props = System.getProperties();
	        props.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);  
	        props.setProperty("mail.imap.socketFactory.port",String.valueOf(port)); 
			props.setProperty("mail.imap.host", host);
			props.setProperty("mail.imap.port", String.valueOf(port));
			props.setProperty("mail.store.protocol", "imap");
			props.setProperty("mail.imap.auth.login.disable", "true"); 
			Session session = Session.getInstance(props);
			session.setDebug(false);
			Store store =  session.getStore("imap");
			store.connect(username,password);
			Folder folder  = store.getFolder("INBOX");
			if (folder.exists())
				folder.open(Folder.READ_WRITE);
			Message[] messages = folder.getMessages();
			if(messages!=null&&messages.length>0){
				for (Message message : messages) {
					System.out.println(message.getFrom()[0].toString() +":" + message.getSubject());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pop3Receive(){
		try {
			Properties props = System.getProperties();
			props.setProperty("mail.pop3.host", "192.168.1.194");
			props.setProperty("mail.pop3.port", "110");
			props.setProperty("mail.store.protocol", "pop3");
			props.setProperty("mail.debug", "true");
			String username = "test@ext.com";
			String password = "123456";
			Session session = Session.getInstance(props);
			
			Store store = (Store) session.getStore("pop3");
			store.connect(username,password);
			Folder folder = null;
			folder =  (Folder) store.getFolder("INBOX");
			if (folder.exists())
				folder.open(2);
			Message[] messages = folder.getMessages();
			if(messages!=null&&messages.length>0){
				for (Message message : messages) {
					System.out.println(message.getSubject());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void smtpSend(){
		try {
			Properties properties = new Properties();
			properties.setProperty("mail.transport.protocol", "smtp");// 发送邮件协议
			properties.setProperty("mail.smtp.auth", "true");// 需要验证
			properties.setProperty("mail.smtp.port", "25");
			properties.setProperty("mail.transport.protocol", "smtp");
			properties.setProperty("mail.smtp.host", "192.168.1.194");
			properties.setProperty("mail.debug", "true");//设置debug模式
			final String username = "test@ext.com";
			final String password = "123456";
			// 后台输出邮件发送的过程
			Session session = Session.getInstance(properties,
					new Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});
			// 邮件信息
			Message messgae = new MimeMessage(session);
			messgae.setFrom(new InternetAddress("test@ext.com"));// 设置发送人
			messgae.setText("X5O!P%@AP[4PZX54(P^)7CC)7}$EICAR-STANDARD-ANTIVIRUS-TEST-FILE!$H+H*");// 设置邮件内容
			messgae.setSubject("测试postfix邮件发送");// 设置邮件主题
			// 发送邮件
			Transport tran = session.getTransport();
			tran.connect("192.168.1.194", username, password);// 连接到新浪邮箱服务器
			tran.sendMessage(messgae, new Address[] { new InternetAddress("postmaster@ext.com") });// 设置邮件接收人
			tran.close();
		} catch (Exception e) {
		}
	}
 
	public void smtpSSLSend(){
		try {
			MailSSLSocketFactory sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			Properties properties = new Properties();
			// 邮件发送协议
			properties.setProperty("mail.transport.protocol", "smtp");
			// SMTP邮件服务器 
			properties.setProperty("mail.smtp.host", "192.168.1.194");
			// SMTP邮件服务器默认端口 
			properties.put("mail.smtp.socketFactory.port", 465);//发信端口
			 // 是否要求身份认证 
			properties.setProperty("mail.smtp.auth", "true");
			 // 是否启用调试模式
			properties.setProperty("mail.debug", "true");//设置debug模式
			properties.put("mail.smtp.ssl.enable", "true");//是否开启ssl
			properties.put("mail.smtp.ssl.socketFactory", sf);
			// 发送邮件协议
			properties.setProperty("mail.smtp.auth", "true");// 需要验证
			final String username = "test@ext.com";
			final String password = "123456";
			
			// 创建Session实例对象 
	        Session session = Session.getDefaultInstance(properties,new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username,
							password);
				}
			});
	        // 创建MimeMessage实例对象 
	        MimeMessage message = new MimeMessage(session); 
	        // 设置发件人 
	        message.setFrom(new InternetAddress("test@ext.com")); 
	        // 设置邮件主题 
	        message.setSubject("SSL认证测试javamail"); 
	        // 设置收件人 
	        message.setRecipient(RecipientType.TO, new InternetAddress("postmaster@ext.com")); 
	        // 设置发送时间 
	        message.setSentDate(new Date()); 
	        // 设置纯文本内容为邮件正文 
	        message.setText("abcdefghijklmnopqrstuvwxyz");
	        //回执
			message.setHeader("Disposition-Notification-To", "test@ext.com");
			//紧急
			message.setHeader("X-Priority", "1");
	        // 保存并生成最终的邮件内容 
	        message.saveChanges(); 
	        Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
