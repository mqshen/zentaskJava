package org.goldratio.web.controllers.home;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.ArrayUtils;
import org.goldratio.core.ZenTaskConstants;
import org.goldratio.models.File;
import org.goldratio.models.FileType;
import org.goldratio.models.OwnType;
import org.goldratio.models.UploadFile;
import org.goldratio.models.User;
import org.goldratio.repositories.FileRepository;
import org.goldratio.services.AttachmentService;
import org.goldratio.validators.AvatarValidator;
import org.goldratio.web.controllers.BaseController;
import org.springframework.beans.factory.InitializingBean;
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
public class AttachmentController extends BaseController implements InitializingBean {
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private FileRepository fileRepository;
	
	
	@Autowired
	private Properties configProperties;
	
	private String[] imageTypeArrary;
	
	public void afterPropertiesSet() throws Exception {
		String imageFilesString = configProperties.getProperty("file.imageType");
		imageTypeArrary = imageFilesString.split(",");
	}
	
	@RequestMapping(value = "/attachment", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upload(@Valid @ModelAttribute("uploadFile") UploadFile file, 
			@RequestParam(required=false) Long projectId, 
			@RequestParam(required=false) Long messageId, 
			@RequestParam(required=false) Long commentId, HttpServletRequest request, HttpSession session) throws ServerException, IOException {
		
		String url = attachmentService.uploadAttachment(file.getFile());
		User sessionUser = (User) session.getAttribute(ZenTaskConstants.USER_FIELD);
		File imageFile = new File();
		if (ArrayUtils.contains(imageTypeArrary, file.getFile().getContentType())) {
			imageFile.setFileType(FileType.IMAGE);
		}
		else {
			imageFile.setFileType(FileType.DOCUMENT);
		}
		imageFile.setUrl(url);
		imageFile.setAuthorId(sessionUser.getId());
		imageFile.setProjectId(projectId);
		if (messageId != null) {
			imageFile.setOwnType(OwnType.MESSAGE);
			imageFile.setOwnId(messageId);
		}
		else if (commentId != null) {
			imageFile.setOwnType(OwnType.COMMENT);
			imageFile.setOwnId(commentId);
		}
		imageFile.setName(file.getFile().getName());
		Date currentDate = new Date();
		imageFile.setContentType(file.getFile().getContentType());
		
		imageFile.setCreateTime(currentDate);
		fileRepository.save(imageFile);
		return buildSuccessResult(request, session, "file", imageFile);
	}
	
	@RequestMapping(value = "/attachment/{attachmentUrl}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> download(@PathVariable String attachmentUrl, HttpServletResponse response, HttpSession session) throws ServerException, IOException {
		File file = fileRepository.findByUrl(attachmentUrl);
		if(file == null)
			return null;
		response.setContentType(file.getContentType());
		response.setHeader("Content-Disposition", "attachment; filename="+ file.getName());
		attachmentService.downloadAttachment(attachmentUrl, response.getOutputStream());
		return null;
	}
}
