package org.goldratio.web.controllers.home;

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
import org.goldratio.models.Team;
import org.goldratio.models.User;
import org.goldratio.repositories.ProjectRepository;
import org.goldratio.repositories.ProjectUserRepository;
import org.goldratio.repositories.TeamRepository;
import org.goldratio.repositories.UserRepository;
import org.goldratio.services.ProjectService;
import org.goldratio.web.controllers.BaseController;
import org.goldratio.web.forms.ProjectForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class ProjectController extends BaseController {
	
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
	
	@ModelAttribute("projectForm")
	public ProjectForm createFormBean() {
		return new ProjectForm();
	}
	
	@RequestMapping("/project")
	public ModelAndView listProject(HttpServletResponse response, HttpSession session)  {
		Long userId = (Long) session.getAttribute(ZenTaskConstants.USER_ID_FIELD);
		long teamId = (Long) session.getAttribute(ZenTaskConstants.TEAM_FIELD);
		List<Project> projects = projectRepository.findByTeamIdAndUserId(teamId, userId);
		return new ModelAndView("projects", "projects", projects);
	}
	
	@RequestMapping(value = "/project/{projectId}", method = RequestMethod.GET)
	public ModelAndView showDetail(@PathVariable Long projectId, HttpServletResponse response, HttpSession session) {
		Project project = projectRepository.findById(projectId);
		return new ModelAndView("project" , "project", project);
	}
	
	
	@RequestMapping(value = "/project/{projectId}/settings", method = RequestMethod.GET)
	public ModelAndView showSettings(@PathVariable Long projectId, HttpServletResponse response, HttpSession session) {
		Project project = projectRepository.findById(projectId);
		return new ModelAndView("projectSettings" , "project", project);
	}
	
	
	@RequestMapping(value = "/project/{projectId}/settings", method = RequestMethod.POST)
	public Map<String, Object> modifySettings(@PathVariable Long projectId, @RequestParam String title, 
			@RequestParam String content, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		Project project = projectRepository.findById(projectId);
		project.setTitle(title);
		project.setContent(content);
		projectRepository.save(project);
		
		return buildSuccessResult(request, session, "successUrl", request.getContextPath() + "/project/" + projectId);
	}
	
	@RequestMapping(value = "/project/create", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createProject(@Valid ProjectForm projectForm, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		long teamId = (Long) session.getAttribute(ZenTaskConstants.TEAM_FIELD);
		User currentUser = (User) session.getAttribute(ZenTaskConstants.USER_FIELD);
		
		Project project = new Project();
		project.setAuthorId(currentUser.getId());
		project.setContent(projectForm.getContent());
		
		Date currentDate = new Date();
		project.setCreateTime(currentDate);
		
		project.setTeamId(teamId);
		project.setTitle(projectForm.getTitle());
		
		projectService.create(project);
		
		ProjectUser projectUser = new ProjectUser(project.getId(), currentUser.getId());
		projectUserRepository.save(projectUser);
		
		List<Long> users = projectForm.getUsers();
		if(users != null) {
			for(Long userId : projectForm.getUsers()) {
				projectUser = new ProjectUser(project.getId(), userId);
			}
		}
		
		return buildSuccessResult(request, session, "project", project);
	}
	
	@RequestMapping(value = "/project/create", method = RequestMethod.GET)
	public ModelAndView getCreatePage(HttpServletResponse response, HttpSession session) {
		long teamId = (Long) session.getAttribute(ZenTaskConstants.TEAM_FIELD);
		Team team = teamRepository.findById(teamId);
		return new ModelAndView("createProject", "team", team);
	}
	
	
}
