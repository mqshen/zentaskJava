package org.goldratio.web.controllers;

import org.goldratio.models.InviteUser;
import org.goldratio.repositories.InviteUserRepository;
import org.goldratio.repositories.UserRepository;
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
	@Autowired
	private InviteUserRepository inviteUserRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/{hashCode}" ,method=RequestMethod.GET)                                                                                                                    
	public ModelAndView showJoin(@PathVariable String hashCode) {
		InviteUser inviteUser = inviteUserRepository.findByHashCode(hashCode);
		if(inviteUser != null)
			return new ModelAndView("join", "inviteUser", inviteUser);
		return new ModelAndView("join_no_found");
	}
	
	
	@RequestMapping(value="/{hashCode}" ,method=RequestMethod.POST)                                                                                                                    
	public ModelAndView join(@PathVariable String hashCode, @RequestParam String nickName, @RequestParam String password) {
		InviteUser inviteUser = inviteUserRepository.findByHashCode(hashCode);
		
		if(inviteUser != null)
			return new ModelAndView("join", "inviteUser", inviteUser);
		return new ModelAndView("join_no_found");
	}
}
