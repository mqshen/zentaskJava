package org.goldratio.web.controllers.home;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.goldratio.models.Message;
import org.goldratio.models.Project;
import org.goldratio.repositories.MessageRepository;
import org.goldratio.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/** 
 * ClassName: TopicController <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 3, 2013 3:50:32 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

@Controller
public class TopicController {
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@RequestMapping("/project/{projectId}/topic")
	public ModelAndView listProject(@PathVariable long projectId, HttpServletResponse response, HttpSession session)  {
		List<Message> messages = messageRepository.findByProjectId(projectId);
		Project project = projectRepository.findById(projectId);
		return new ModelAndView("topics", "messages", messages).addObject("project", project);
	}
}
