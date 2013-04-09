package org.goldratio.validators;

import org.apache.commons.lang.ArrayUtils;
import org.goldratio.models.UploadFile;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

/** 
 * ClassName: AvatarValidator <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 4, 2013 3:27:14 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public class AvatarValidator implements Validator{
	private final static long MAX_SIZE = 1024 * 1024;  
	  
    /** 
     * 文件大小上限 
     */  
    private long maxSize = MAX_SIZE;  
  
    /** 
     * 可接受的文件content-type 
     */  
    private String[] allowedContentTypes;

	@Override
	public boolean supports(Class<?> clazz) {
		//return MultipartFile.creturn UploadBackingForm.class.equals(clazz);lass.isAssignableFrom(clazz);
		return UploadFile.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UploadFile uploadFile = (UploadFile) target;
		MultipartFile multipartFile = uploadFile.getFile(); 
		if(multipartFile.getSize() == 0 || multipartFile.getSize() > maxSize) {
			errors.rejectValue("file", "file size is illegal");
			return;
		}
		if (!ArrayUtils.contains(allowedContentTypes, multipartFile.getContentType())) {
			errors.rejectValue("file", "file type is illegal");
			return;
		}
	}

	public void setAllowedContentTypes(String[] allowedContentTypes) {
		this.allowedContentTypes = allowedContentTypes;
	}

}
