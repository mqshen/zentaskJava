package org.goldratio.web.forms;

import java.util.List;

/**
 * ClassName: ProjectForm <br/>
 * Function: <br/>
 * Reason: <br/>
 * date: Apr 10, 2013 5:54:35 PM <br/>
 * 
 * @author GoldRatio
 * @version 1.0
 */

public class ProjectForm {
	private String title;
	private String content;
	private List<Long> users;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Long> getUsers() {
		return users;
	}

	public void setUsers(List<Long> users) {
		this.users = users;
	}
}
