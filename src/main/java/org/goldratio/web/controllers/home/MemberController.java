package org.goldratio.web.controllers.home;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.goldratio.models.Team;
import org.goldratio.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	@RequestMapping("/member")
	public ModelAndView listMember(HttpServletResponse response, HttpSession session)  {
		long teamId = (Long) session.getAttribute("teamId");
		Team team = teamRepository.findById(teamId);
		return new ModelAndView("member", "team", team);
	}
	
}
