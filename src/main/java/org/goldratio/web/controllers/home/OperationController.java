package org.goldratio.web.controllers.home;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/** 
 * ClassName: OperationController <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 10, 2013 2:53:24 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

@Controller
public class OperationController {

	@RequestMapping("/operation")
	public ModelAndView listProject(HttpServletResponse response, HttpSession session)  {
		return new ModelAndView("operations" );
	}

}
