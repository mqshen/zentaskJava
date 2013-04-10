package org.goldratio.services.impl;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.goldratio.core.ZenTaskConstants;
import org.goldratio.core.velocity.VelocityManager;
import org.goldratio.services.MailService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/** 
 * ClassName: MailServiceImpl <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 7, 2013 8:37:44 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

@Service("mailService")
public class MailServiceImpl extends JavaMailSenderImpl implements MailService,InitializingBean {
	
	private static final Log LOG = LogFactory.getLog(MailServiceImpl.class);
	
	@Autowired
	private Properties configProperties;
	
	@Autowired
	private VelocityManager velocityManager;

	
	public void afterPropertiesSet() throws Exception {

		setHost(configProperties.getProperty("mail.server.host"));
		setPort(Integer.parseInt(configProperties.getProperty("mail.server.prot")));
		setProtocol(configProperties.getProperty("mail.server.protocol"));
		setUsername(configProperties.getProperty("mail.from.account"));
		setPassword(configProperties.getProperty("mail.from.password"));
		
		Properties properties = new Properties();		
		properties.put("mail.smtp.auth", true);		
		properties.put("mail.smtp.starttls.enable", false);
		properties.put("mail.smtp.timeout", 30000);
		setJavaMailProperties(properties);
	}
	
	@Override
	@Async
	public void send(String targetAddress, String templateCode,	Map<String, Object> args){
		MimeMessage mimeMessage = createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF8");
		String from = configProperties.getProperty("mail.from.account");
		try {
			helper.setSubject((String) args.get(ZenTaskConstants.PARAM_MAIL_SUBJECT));
			helper.setFrom(from);
			helper.setTo(targetAddress);
			//helper.setText(code, true);
			helper.setText(velocityManager.parseTemplate(templateCode, args), true);
			send(mimeMessage);
		} 
		catch (Exception e) {
			LOG.error("邮件发送失败:" ,e);
		} 
	}
}
