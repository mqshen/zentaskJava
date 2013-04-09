package org.goldratio.web.controllers.home;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.goldratio.core.ZenTaskConstants;
import org.goldratio.models.User;
import org.goldratio.repositories.UserRepository;
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

@Controller
public class SettingsController {
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public ModelAndView getUserDetail(HttpServletResponse response, HttpSession session)  {
		return new ModelAndView("settings", "user", session.getAttribute(ZenTaskConstants.USER_FIELD));
	}
	
	@RequestMapping(value = "/settings", method = RequestMethod.POST)
	public ModelAndView updateUser(User user, HttpServletResponse response, HttpSession session)  {
		User sessionUser = (User) session.getAttribute(ZenTaskConstants.USER_FIELD);
		sessionUser.setEmail(user.getEmail());
		sessionUser.setNickName(user.getNickName());
		userRepository.save(sessionUser);
		return new ModelAndView("settings", "user", sessionUser);
	}

}
