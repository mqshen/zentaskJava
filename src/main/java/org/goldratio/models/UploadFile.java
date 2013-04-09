package org.goldratio.models;

import org.springframework.web.multipart.MultipartFile;

/** 
 * ClassName: UploadFile <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 4, 2013 5:41:48 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public class UploadFile {

	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
