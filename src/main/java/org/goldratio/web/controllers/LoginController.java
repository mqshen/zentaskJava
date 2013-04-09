package org.goldratio.web.controllers;

import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.goldratio.core.ZenTaskConstants;
import org.goldratio.models.User;
import org.goldratio.repositories.UserRepository;
import org.goldratio.util.ZenTaskUtil;
import org.goldratio.web.forms.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/** 
 * ClassName: LoginController <br/> 
 * Function: TODO <br/> 
 * Reason: TODO <br/> 
 * date: Mar 27, 2013 3:21:31 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;
	
	@ModelAttribute("loginForm")
	public LoginForm createFormBean() {
		return new LoginForm();
	}
	
	@RequestMapping(method=RequestMethod.GET)                                                                                                                    
	public ModelAndView showLoginForm() {
		return new ModelAndView("login");
	}
	
	@RequestMapping(method=RequestMethod.POST)                                                                                                                    
	public ModelAndView doLogin(@Valid LoginForm loginForm, BindingResult result, Model model, HttpServletResponse response, HttpSession session)  {
		if (result.hasErrors()) {
			return null;
		}
		User user = userRepository.findByEmail(loginForm.getEmail());
		if(user == null)
			return null;
		boolean isEquale = user.authPass(loginForm.getPassword());
		loginForm.setPassword("");
		if(user !=null && isEquale) {
			StringBuffer strBuffer = new StringBuffer();
			strBuffer.append(user.getEmail());
			strBuffer.append(user.getSalt());
			Calendar calendar = Calendar.getInstance();
			long current = calendar.getTimeInMillis();
			strBuffer.append(current);
			String uuid = ZenTaskUtil.getMD5Str(strBuffer.toString());
			Cookie cookie = new Cookie(ZenTaskConstants.UUID, uuid);
			cookie.setMaxAge(-1);
			session.setAttribute(ZenTaskConstants.UUID, uuid);
			session.setAttribute(ZenTaskConstants.USER_FIELD, user);
			session.setAttribute("teamId", 1L);
			response.addCookie(cookie);
			return new ModelAndView("redirect:/project");
		}
		return new ModelAndView("login");
	}

}
