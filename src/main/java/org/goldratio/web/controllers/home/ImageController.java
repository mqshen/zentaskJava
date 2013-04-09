package org.goldratio.web.controllers.home;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.goldratio.core.ZenTaskConstants;
import org.goldratio.models.File;
import org.goldratio.models.OwnType;
import org.goldratio.models.UploadFile;
import org.goldratio.models.User;
import org.goldratio.repositories.FileRepository;
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
import org.springframework.web.bind.annotation.RequestParam;
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
public class ImageController extends BaseController{
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private FileRepository fileRepository;
	
	@InitBinder
	public void initBinder(final DataBinder binder) {
		AvatarValidator avatarValidator = new AvatarValidator();
		avatarValidator.setAllowedContentTypes(new String[] { "image/jpeg", "image/pjpeg" });
	    binder.setValidator(avatarValidator);
	}
	
	@RequestMapping(value = "/image", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upload(@Valid @ModelAttribute("uploadFile") UploadFile file, @RequestParam(required=false) Long messageId, 
			@RequestParam(required=false) Long commentId, HttpServletRequest request, HttpSession session) throws ServerException, IOException {
		String url = attachmentService.uploadImage(file.getFile());
		User sessionUser = (User) session.getAttribute(ZenTaskConstants.USER_FIELD);
		File imageFile = new File();
		imageFile.setUrl(url);
		imageFile.setAuthorId(sessionUser.getId());
		if (messageId != null) {
			imageFile.setOwnType(OwnType.MESSAGE);
			imageFile.setOwnId(messageId);
		}
		else if (commentId != null) {
			imageFile.setOwnType(OwnType.COMMENT);
			imageFile.setOwnId(commentId);
		}
		Date currentDate = new Date();
		imageFile.setCreateTime(currentDate);
		fileRepository.save(imageFile);
		return buildSuccessResult(request, session, "imageUrl", url);
	}
	
	@RequestMapping(value = "/image/{avatarUrl}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> download(@PathVariable String avatarUrl, HttpServletResponse response, HttpSession session) throws ServerException, IOException {
		response.setContentType("image/jpeg");
		response.setHeader("Content-Disposition", "attachment; filename="+ avatarUrl);
		attachmentService.download(avatarUrl, response.getOutputStream());
		return null;
	}
}
