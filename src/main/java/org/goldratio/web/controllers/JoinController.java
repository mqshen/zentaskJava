package org.goldratio.web.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.goldratio.core.ZenTaskConstants;
import org.goldratio.models.InviteProject;
import org.goldratio.models.InviteUser;
import org.goldratio.models.ProjectUser;
import org.goldratio.models.Team;
import org.goldratio.models.TeamRole;
import org.goldratio.models.User;
import org.goldratio.repositories.InviteProjectRepository;
import org.goldratio.repositories.InviteUserRepository;
import org.goldratio.repositories.ProjectUserRepository;
import org.goldratio.repositories.TeamRepository;
import org.goldratio.repositories.TeamUserRoleRepository;
import org.goldratio.repositories.UserRepository;
import org.goldratio.util.ZenTaskUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * ClassName: JoinController <br/>
 * Function: <br/>
 * Reason: <br/>
 * date: Apr 9, 2013 10:58:42 AM <br/>
 * 
 * @author GoldRatio
 * @version 1.0
 */

@Controller
public class JoinController {
	private static final Log LOG = LogFactory.getLog(JoinController.class);
	
	@Autowired
	private InviteUserRepository inviteUserRepository;
	
	@Autowired
	private InviteProjectRepository inviteProjectRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TeamUserRoleRepository teamUserRoleRepository;
	
	@Autowired
	private ProjectUserRepository projectUserRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public ModelAndView getRegist() {
		return new ModelAndView("regist");
	}
	
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public ModelAndView doRegist(String teamName, String name, String email, String password) {

		if(userRepository.findByEmail(email)!=null)
			return new ModelAndView("registFailed");
		Date currentDate = new Date();
		
		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setSalt(name);
		user.setNickName(name);
		user.encryptPass(password);
		
		userRepository.save(user);
		
		Team team = new Team();
		
		team.setTitle(teamName);
		team.setCreateTime(currentDate);
		team.setAuthorId(user.getId());
		
		teamRepository.save(team);
		
		TeamRole teamRole = new TeamRole();
		teamRole.setRole(User.ADMIN);
		teamRole.setTeamId(team.getId());
		teamRole.setUserId(user.getId());
		
		teamUserRoleRepository.save(teamRole);
		
		return new ModelAndView("registSuccess");
	}
	
	@RequestMapping(value = "/join/{hashCode}", method = RequestMethod.GET)
	public ModelAndView showJoin(@PathVariable String hashCode, HttpServletResponse response) {
		InviteUser inviteUser = inviteUserRepository.findByHashCode(hashCode);
//		Cookie cookie = new Cookie(ZenTaskConstants.UUID, null);
//		cookie.setPath("/");
//		cookie.setMaxAge(0);
//		response.addCookie(cookie);
		if (inviteUser != null)
			return new ModelAndView("join", "inviteUser", inviteUser);
		return new ModelAndView("join_no_found");
	}

	
	@RequestMapping(value = "/join/{hashCode}", method = RequestMethod.POST)
	public ModelAndView join(@PathVariable String hashCode, @RequestParam String nickName, 
			@RequestParam String password,HttpServletResponse response, HttpSession session) {
		InviteUser inviteUser = inviteUserRepository.findByHashCode(hashCode);
		if (inviteUser != null) {
			User user = userRepository.findByEmail(inviteUser.getEmail());
			if(user == null) {
				user = new User();
				user.setEmail(inviteUser.getEmail());
				user.setNickName(nickName);
				user.setAvatarUrl(ZenTaskConstants.DEFAULT_USER_AVATAR);
				user.setSalt(nickName);
				user.encryptPass(password);
				user.setName(nickName);
				userRepository.save(user);
			}
			TeamRole teamUserRole = new TeamRole();
			teamUserRole.setRole(inviteUser.getRole());
			teamUserRole.setTeamId(inviteUser.getTeamId());
			teamUserRole.setUserId(user.getId());
			teamUserRoleRepository.save(teamUserRole);
			
			List<InviteProject> inviteProjects = inviteProjectRepository.findByInviteId(inviteUser.getInviteId());
			for(InviteProject inviteProject : inviteProjects) {
				ProjectUser projectUser = new ProjectUser(inviteProject.getProjectId(), user.getId());
				projectUserRepository.save(projectUser);
			}
			inviteUserRepository.delete(inviteUser.getHashCode());
			
			StringBuffer strBuffer = new StringBuffer();
			strBuffer.append(user.getEmail());
			strBuffer.append(user.getSalt());
			Calendar calendar = Calendar.getInstance();
			long current = calendar.getTimeInMillis();
			strBuffer.append(current);
			String uuid = ZenTaskUtil.getMD5Str(strBuffer.toString());
			Cookie cookie = new Cookie(ZenTaskConstants.UUID, uuid);
			cookie.setMaxAge(-1);
			cookie.setPath("/");
			response.addCookie(cookie);
			session.setAttribute(ZenTaskConstants.UUID, uuid);
			session.setAttribute(ZenTaskConstants.USER_FIELD, user);
			session.setAttribute(ZenTaskConstants.TEAM_FIELD, inviteUser.getTeamId());
			
			return new ModelAndView("redirect:/project");
		}
		try {
			LOG.error("join hash code not found!");
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Bad or missing CSRF value");
		} 
		catch (IOException e) {
			LOG.error("join hash code not found!", e);
		}
		return null;
	}
}
