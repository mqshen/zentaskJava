package org.goldratio.web.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * ClassName: LoginForm <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 2, 2013 9:35:39 AM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public class LoginForm {
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
