package org.goldratio.web.controllers.home;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.goldratio.core.ZenTaskConstants;
import org.goldratio.models.UploadFile;
import org.goldratio.models.User;
import org.goldratio.repositories.UserRepository;
import org.goldratio.services.AttachmentService;
import org.goldratio.validators.AvatarValidator;
import org.goldratio.web.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/** 
 * ClassName: AvatarController <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 4, 2013 3:04:43 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */
@Controller
public class AvatarController extends BaseController{
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private UserRepository userRepository;
	
	@InitBinder
	public void initBinder(final DataBinder binder) {
		AvatarValidator avatarValidator = new AvatarValidator();
		avatarValidator.setAllowedContentTypes(new String[] { "image/jpeg", "image/pjpeg" });
	    binder.setValidator(avatarValidator);
	}
	
	@RequestMapping(value = "/settings/avatar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upload(@Valid @ModelAttribute("uploadFile") UploadFile file, HttpServletRequest request, HttpSession session) throws ServerException, IOException {
		String url = attachmentService.uploadAvatar(file.getFile());
		User sessionUser = (User) session.getAttribute(ZenTaskConstants.USER_FIELD);
		sessionUser.setAvatarUrl(url);
		userRepository.save(sessionUser);
		return buildSuccessResult(request, session, "avatarUrl", url);
	}
	
	@RequestMapping(value = "/avatar/{avatarUrl}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> download(@PathVariable String avatarUrl, HttpServletResponse response, HttpSession session) throws ServerException, IOException {
		response.setContentType("image/jpeg");
		response.setHeader("Content-Disposition", "attachment; filename="+ avatarUrl);
		attachmentService.download(avatarUrl, response.getOutputStream());
		return null;
	}
}
