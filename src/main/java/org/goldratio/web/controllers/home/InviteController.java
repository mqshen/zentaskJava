package org.goldratio.web.controllers.home;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.goldratio.core.ZenTaskConstants;
import org.goldratio.models.InviteProject;
import org.goldratio.models.InviteUser;
import org.goldratio.models.Team;
import org.goldratio.models.User;
import org.goldratio.repositories.InviteProjectRepository;
import org.goldratio.repositories.InviteUserRepository;
import org.goldratio.repositories.TeamRepository;
import org.goldratio.repositories.UserRepository;
import org.goldratio.services.GetSequenceService;
import org.goldratio.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/** 
 * ClassName: SettingsController <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 4, 2013 4:25:28 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */
class InviteForm {

	private List<String> emails;
	
	private List<Integer> roles;
	
	private List<Long> projects;


	public List<Long> getProjects() {
		return projects;
	}

	public void setProjects(List<Long> projects) {
		this.projects = projects;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public List<Integer> getRoles() {
		return roles;
	}

	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}
}
@Controller
public class InviteController {
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired 
	private InviteUserRepository inviteUserRepository;
	
	@Autowired 
	private InviteProjectRepository inviteProjectRepository;
	
	@Autowired
	private GetSequenceService getSequenceService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MailService mailService;
	
	
	@RequestMapping(value = "/invite", method = RequestMethod.GET)
	public ModelAndView getInviteView(HttpServletResponse response, HttpSession session)  {
		long teamId = (Long) session.getAttribute(ZenTaskConstants.TEAM_FIELD);
		Team team = teamRepository.findById(teamId);
		User currentUser = (User) session.getAttribute(ZenTaskConstants.USER_FIELD);
		return new ModelAndView("invite", "team", team).addObject("user", currentUser);
	}
	
	@RequestMapping(value = "/invite", method = RequestMethod.POST)
	public ModelAndView inviteUsers(InviteForm inviteForm, HttpServletResponse response, HttpSession session)  {
		long teamId = (Long) session.getAttribute(ZenTaskConstants.TEAM_FIELD);
		Team team = teamRepository.findById(teamId);
		Long inviteId = getSequenceService.getNextVal("inviteId");
		User currentUser = (User) session.getAttribute(ZenTaskConstants.USER_FIELD);
		Map<String, Object> args = new HashMap<String,Object>();
		args.put(ZenTaskConstants.PARAM_MAIL_SUBJECT, "用户邀请");
		args.put("team", team);
		boolean invited = false;
		for(int i=0; i < inviteForm.getEmails().size(); ++ i) {
			String email = inviteForm.getEmails().get(i);
			InviteUser inviteUserExist = inviteUserRepository.findByEmailAndTeamId(email, teamId);
			if(inviteUserExist != null)
				continue;
			User userExist = userRepository.findByEmailAneTeamId(email, teamId);
			if(userExist != null) {
				continue;
			}
			invited = true;
			InviteUser inviteUser = new InviteUser();
			int role = inviteForm.getRoles().get(i);
			inviteUser.setEmail(email);
			inviteUser.setRole(role);
			inviteUser.setInviteId(inviteId);
			inviteUser.setTeamId(teamId);
			
			String hashCode = UUID.randomUUID().toString();
			inviteUser.setHashCode(hashCode);
			inviteUserRepository.save(inviteUser);

			args.put("inviteUser", inviteUser);
			args.put("adminUser", currentUser);
			mailService.send(email, "invite", args);
		}
		if(invited) {
			for(Long projectId: inviteForm.getProjects()) {
				InviteProject inviteProject = new InviteProject();
				inviteProject.setInviteId(inviteId);
				inviteProject.setProjectId(projectId);
				inviteProjectRepository.save(inviteProject);
			}
		}
		return new ModelAndView("redirect:/member");
	}
	
}
