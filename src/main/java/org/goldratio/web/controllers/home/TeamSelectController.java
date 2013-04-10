package org.goldratio.web.controllers.home;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.goldratio.core.ZenTaskConstants;
import org.goldratio.models.Team;
import org.goldratio.repositories.TeamRepository;
import org.goldratio.web.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/** 
 * ClassName: AvatarController <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 4, 2013 3:04:43 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */
@Controller
public class TeamSelectController extends BaseController{
	@Autowired
	private TeamRepository teamRepository;
	
	@RequestMapping(value = "/teamSelect/{teamId}", method = RequestMethod.GET)
	public ModelAndView  teamSelect(@PathVariable Long teamId, HttpServletRequest request, HttpSession session) throws ServerException, IOException {
		Team team = teamRepository.findById(teamId);
		session.setAttribute(ZenTaskConstants.TEAM_FIELD, team);
		return new ModelAndView("redirect:/project");
	}
}
