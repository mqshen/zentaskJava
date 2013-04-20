package org.goldratio.web.forms;

import org.goldratio.models.SelectObject;
import org.springframework.util.AutoPopulatingList;

/**
 * ClassName: MemberForm <br/>
 * Function: <br/>
 * Reason: <br/>
 * date: Apr 15, 2013 1:45:03 PM <br/>
 * 
 * @author GoldRatio
 * @version 1.0
 */

public class MemberForm {
	private AutoPopulatingList<SelectObject> members;

	public MemberForm() {
		members = new AutoPopulatingList<SelectObject>(SelectObject.class);
	}

	public AutoPopulatingList<SelectObject> getMembers() {
		return members;
	}

	public void setMembers(AutoPopulatingList<SelectObject> members) {
		this.members = members;
	}

}
