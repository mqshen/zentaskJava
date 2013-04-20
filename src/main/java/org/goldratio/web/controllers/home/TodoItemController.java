package org.goldratio.web.controllers.home;


import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.goldratio.core.ZenTaskConstants;
import org.goldratio.models.TodoItem;
import org.goldratio.models.User;
import org.goldratio.repositories.TodoItemRepository;
import org.goldratio.repositories.UserRepository;
import org.goldratio.services.TodoItemService;
import org.goldratio.web.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class TodoItemController extends BaseController{
	@Autowired
	private TodoItemRepository todoItemRepository;
	
	@Autowired
	private TodoItemService todoItemService;
	
	@Autowired
	private UserRepository userRepository;


	@RequestMapping(value = "/project/{projectId}/todoList/{todoListId}/todoItem", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> create(@PathVariable long projectId, @PathVariable long todoListId
			, TodoItem todoItem, HttpServletResponse response, HttpSession session)  {
		Long teamId = (Long) session.getAttribute(ZenTaskConstants.TEAM_FIELD);
		User currentUser = (User) session.getAttribute(ZenTaskConstants.USER_FIELD);
		todoItem.setAuthorId(currentUser.getId());
		Date currentDate = new Date();
		todoItem.setCreateTime(currentDate);
		todoItem.setTodoListId(todoListId);
		todoItem.setTeamId(teamId);
		todoItem.setProjectId(projectId);
		todoItemService.create(todoItem);
		if(todoItem.getWorkerId() != null) {
			User worker = userRepository.findById(todoItem.getWorkerId());
			todoItem.setWorker(worker);
		}
		return buildSuccessResult(session, "todoItem", todoItem);
	}
	
	@RequestMapping(value = "/project/{projectId}/todoList/{todoListId}/todoItem/{todoItemId}/{operationType}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateOperation(@PathVariable long projectId, @PathVariable long todoListId
			, @PathVariable long todoItemId, @PathVariable String operationType,TodoItem todoItemRequest, HttpServletRequest request, HttpServletResponse response, HttpSession session)  {
		TodoItem todoItem = todoItemRepository.findById(todoItemId);
		Long teamId = (Long) session.getAttribute(ZenTaskConstants.TEAM_FIELD);
		if(todoItem == null)
			return null;
		todoItem.setTeamId(teamId);
		if(operationType.equals("running")) {
			todoItem.setRunning();
		}
		else if(operationType.equals("pause")) {
			todoItem.setRunning();
		}
		else if(operationType.equals("worker")) {
			todoItem.setWorkerId(todoItemRequest.getWorkerId());
			todoItem.setDeadLine(todoItemRequest.getDeadLine());
		}
		todoItemRepository.save(todoItem);
		return buildSuccessResult(request, session, "todoItem", todoItem);
	}
}
