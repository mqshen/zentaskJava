package org.goldratio.web.controllers.home;


import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.goldratio.core.ZenTaskConstants;
import org.goldratio.models.Comment;
import org.goldratio.models.Message;
import org.goldratio.models.User;
import org.goldratio.repositories.CommentRepository;
import org.goldratio.repositories.MessageRepository;
import org.goldratio.web.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/** 
 * ClassName: MessageController <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 3, 2013 3:50:32 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

@Controller
public class MessageController extends BaseController{
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@RequestMapping(value = "/project/{projectId}/message/{messageId}", method = RequestMethod.GET)
	public ModelAndView listProject(@PathVariable long projectId, @PathVariable long messageId, HttpServletResponse response, HttpSession session)  {
		Message message = messageRepository.findById(messageId);
		return new ModelAndView("message", "message", message);
	}
	
	
	@RequestMapping(value = "/project/{projectId}/message/{messageId}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(@PathVariable long projectId, @PathVariable long messageId, 
			@RequestParam String title, @RequestParam String content, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session)  {
		Message message = messageRepository.findById(messageId);
		message.setTitle(title);
		message.setContent(content);
		messageRepository.save(message);
		return buildSuccessResult(request, session, "message", message);
	}


	@RequestMapping(value = "/project/{projectId}/message", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> create(@PathVariable long projectId, Message message, HttpServletRequest request,
			HttpServletResponse response, HttpSession session)  {
		User currentUser = (User) session.getAttribute(ZenTaskConstants.USER_FIELD);
		message.setAuthorId(currentUser.getId());
		Date currentDate = new Date();
		message.setCreateTime(currentDate);
		message.setProjectId(projectId);
		messageRepository.save(message);
		return buildSuccessResult(request, session, "message", message);
	}
	
	
	@RequestMapping(value = "/project/{projectId}/message/{messageId}/comment", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> comment(@PathVariable long projectId, @PathVariable long messageId, Comment comment,
			HttpServletRequest request, HttpServletResponse response, HttpSession session)  {
		User currentUser = (User) session.getAttribute(ZenTaskConstants.USER_FIELD);
		comment.setAuthorId(currentUser.getId());
		Date currentDate = new Date();
		comment.setCreateTime(currentDate);
		comment.setMessageId(messageId);
		commentRepository.save(comment);
		return buildSuccessResult(request, session, "comment", comment);
	}
}
