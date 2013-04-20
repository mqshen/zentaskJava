package org.goldratio.web.controllers.home;


import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.goldratio.core.ZenTaskConstants;
import org.goldratio.models.Team;
import org.goldratio.models.TodoItem;
import org.goldratio.models.User;
import org.goldratio.repositories.TeamRepository;
import org.goldratio.repositories.TodoItemRepository;
import org.goldratio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/** 
 * ClassName: ProjectController <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 2, 2013 1:58:35 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

@Controller
public class MemberController {
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TodoItemRepository todoItemRepository;
	
	@RequestMapping("/member")
	public ModelAndView listMember(HttpServletResponse response, HttpSession session)  {
		Long teamId = (Long) session.getAttribute(ZenTaskConstants.TEAM_FIELD);
		Team testTeam = teamRepository.findById(teamId);
		return new ModelAndView("member", "team", testTeam);
	}
	
	@RequestMapping("/member/{userId}")
	public ModelAndView memberDeatil(@PathVariable long userId, HttpServletResponse response, HttpSession session)  {
		Long teamId = (Long) session.getAttribute(ZenTaskConstants.TEAM_FIELD);
		Long currentUserId = (Long) session.getAttribute(ZenTaskConstants.USER_ID_FIELD);
		User user = userRepository.findById(userId);
		List<TodoItem> todoItems = todoItemRepository.findByTeamIdAndUserId(teamId, userId);
		return new ModelAndView("memberDeatil", "user", user).addObject("todoItems", todoItems);
	}
	
}
