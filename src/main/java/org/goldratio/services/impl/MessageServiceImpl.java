package org.goldratio.services.impl;


import org.goldratio.models.Message;
import org.goldratio.repositories.MessageRepository;
import org.goldratio.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** 
 * ClassName: ProjectServiceImpl <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 10, 2013 5:51:11 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

@Service("messageService")
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageRepository messageRepository;

	@Override
	public void create(Message message) {
		messageRepository.save(message);
	}
}
