package org.goldratio.services;

import java.util.Map;

/** 
 * ClassName: MailService <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 7, 2013 8:31:47 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public interface MailService {

	/**
	 * 发送邮件
	 * @param targetAddress 收件人
	 * @param templateCode 邮件模板代码
	 * @param args 邮件模板传入参数
	 */
	public void send(String targetAddress, String templateCode,	Map<String, Object> args);
}
