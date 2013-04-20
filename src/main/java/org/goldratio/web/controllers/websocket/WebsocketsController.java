package org.goldratio.web.controllers.websocket;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.atmosphere.cpr.AtmosphereResource;
import org.goldratio.atmosphere.AtmosphereUtils;
import org.goldratio.core.ZenTaskConstants;
import org.goldratio.models.Operation;
import org.goldratio.models.UploadFile;
import org.goldratio.models.User;
import org.goldratio.repositories.UserRepository;
import org.goldratio.services.UserService;
import org.goldratio.validators.AvatarValidator;
import org.goldratio.web.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * ClassName: CometController <br/>
 * Function: <br/>
 * Reason: <br/>
 * date: Apr 11, 2013 11:34:06 PM <br/>
 * 
 * @author GoldRatio
 * @version 1.0
 */

@Controller
public class WebsocketsController {
	@Autowired
	private UserService userService;

	/**
	 * @param user
	 *            logged in User.
	 * @param event
	 *            Atmosphere's resource.
	 */
	@RequestMapping(value = "/websockets", method = RequestMethod.GET)
	@ResponseBody
	public void websockets(
			final AtmosphereResource event, HttpServletRequest request, HttpSession session) {
		User user = (User) session.getAttribute(ZenTaskConstants.USER_FIELD);
		this.userService.registerWebsocket(user, event);
		event.suspend();
	}

	@RequestMapping(value = "/websockets", method = RequestMethod.POST)
	@ResponseBody
	public void post(final AtmosphereResource event, @RequestBody String message)
			throws JsonGenerationException, JsonMappingException, IOException {

		event.getBroadcaster().getAtmosphereResources().size();
		event.getBroadcaster().broadcast("sdfsdfsdf");
	}

}
