package org.goldratio.web.forms;

import org.goldratio.models.SelectObject;
import org.springframework.util.AutoPopulatingList;

/** 
 * ClassName: FilesForm <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 17, 2013 11:06:31 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public class MessageForm {
	private String title;
	private String content;
	
	private AutoPopulatingList<SelectObject> files;

	public MessageForm() {
		files = new AutoPopulatingList<SelectObject>(SelectObject.class);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public AutoPopulatingList<SelectObject> getFiles() {
		return files;
	}

	public void setFiles(AutoPopulatingList<SelectObject> files) {
		this.files = files;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
