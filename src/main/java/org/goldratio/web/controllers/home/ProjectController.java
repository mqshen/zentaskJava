package org.goldratio.web.controllers.home;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.goldratio.core.ZenTaskConstants;
import org.goldratio.models.Project;
import org.goldratio.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class ProjectController {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@RequestMapping("/project")
	public ModelAndView listProject(HttpServletResponse response, HttpSession session)  {
		Long reamId = (Long) session.getAttribute(ZenTaskConstants.TEAM_FIELD); 
		List<Project> projects = projectRepository.findByTeamId(reamId);
		return new ModelAndView("projects", "projects", projects);
	}
	
	
	@RequestMapping(value = "/project/{projectId}", method = RequestMethod.GET)
	public ModelAndView showDetail(@PathVariable Long projectId, HttpServletResponse response, HttpSession session) {
		Project project = projectRepository.findById(projectId);
		return new ModelAndView("project" , "project", project);
	}
	
	@RequestMapping(value = "/project/{projectId}/members", method = RequestMethod.GET)
	public ModelAndView members(@PathVariable Long projectId, HttpServletResponse response, HttpSession session) {
		Project project = projectRepository.findById(projectId);
		return new ModelAndView("project" , "project", project);
	}
}
