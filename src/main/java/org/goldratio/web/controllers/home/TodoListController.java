package org.goldratio.web.controllers.home;


import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.goldratio.core.ZenTaskConstants;
import org.goldratio.models.TodoList;
import org.goldratio.models.User;
import org.goldratio.repositories.TodoListRepository;
import org.goldratio.web.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class TodoListController extends BaseController{
	@Autowired
	private TodoListRepository todoListRepository;
	
	
	
	@RequestMapping("/project/{projectId}/todoList/{todoListId}")
	public ModelAndView listProject(@PathVariable long todoListId, @PathVariable long messageId, HttpServletResponse response, HttpSession session)  {
		TodoList todoList = todoListRepository.findById(messageId);
		return new ModelAndView("todoList", "todoList", todoList);
	}


	@RequestMapping(value = "/project/{projectId}/todoList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> create(@PathVariable long projectId, TodoList todoList, HttpServletResponse response, HttpSession session)  {
		User currentUser = (User) session.getAttribute(ZenTaskConstants.USER_FIELD);
		todoList.setAuthorId(currentUser.getId());
		Date currentDate = new Date();
		todoList.setCreateTime(currentDate);
		todoList.setProjectId(projectId);
		todoListRepository.save(todoList);
		return buildSuccessResult(session, "todoList", todoList);
	}
}
