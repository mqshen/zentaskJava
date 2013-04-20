package org.goldratio.web.controllers.home;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.goldratio.core.ZenTaskConstants;
import org.goldratio.models.Project;
import org.goldratio.models.ProjectUser;
import org.goldratio.models.SelectObject;
import org.goldratio.models.Team;
import org.goldratio.models.User;
import org.goldratio.repositories.ProjectRepository;
import org.goldratio.repositories.ProjectUserRepository;
import org.goldratio.repositories.TeamRepository;
import org.goldratio.repositories.UserRepository;
import org.goldratio.services.ProjectService;
import org.goldratio.web.controllers.BaseController;
import org.goldratio.web.forms.MemberForm;
import org.goldratio.web.forms.ProjectForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.AutoPopulatingList;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
public class ProjectMemberController extends BaseController {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProjectUserRepository projectUserRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@RequestMapping(value = "/project/{projectId}/members", method = RequestMethod.GET)
	public ModelAndView members(@PathVariable Long projectId, HttpServletResponse response, HttpSession session) {
		Project project = projectRepository.findById(projectId);
		long teamId = (Long) session.getAttribute(ZenTaskConstants.TEAM_FIELD);
		Team team = teamRepository.findById(teamId);
		return new ModelAndView("projectMembers" , "project", project).addObject("team", team);
	}
	
	
	@RequestMapping(value = "/project/{projectId}/members", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> setMembers(@PathVariable Long projectId,@ModelAttribute MemberForm memberForm, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		for(SelectObject selectObject : memberForm.getMembers()) {
			if(selectObject.isSelected()) {
				ProjectUser projectUser = new ProjectUser(projectId, selectObject.getContent());
				projectUserRepository.save(projectUser);
			}
			else {
				ProjectUser projectUser = new ProjectUser(projectId, selectObject.getContent());
				projectUserRepository.delete(projectUser);
			}
		}
		return buildSuccessResult(request, session, "successUrl", request.getContextPath() + "/project/" + projectId);
	}
}
