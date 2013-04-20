package org.goldratio.web.controllers.home;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.goldratio.core.ZenTaskConstants;
import org.goldratio.models.Operation;
import org.goldratio.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@Autowired
	private OperationRepository operationRepository;

	@RequestMapping("/operation")
	public ModelAndView listProject(@RequestParam(required=false) Long userId, Pageable pageable, HttpServletResponse response, HttpSession session)  {
		Long teamId = (Long) session.getAttribute(ZenTaskConstants.TEAM_FIELD);
		Page<Operation> operations = operationRepository.findByTeamIdAndUserId(teamId, userId, pageable);
		return new ModelAndView("operations" ,"operations", operations);
	}
}
