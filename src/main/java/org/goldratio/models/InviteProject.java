package org.goldratio.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/** 
 * ClassName: InviteProject <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 8, 2013 4:41:55 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */
@Entity
@Table(name = "inviteProject")
@IdClass(InviteProjectPk.class)
public class InviteProject {

	@Id
	private Long inviteId;  
	  
	@Id
    private Long projectId;

	public Long getInviteId() {
		return inviteId;
	}

	public void setInviteId(Long inviteId) {
		this.inviteId = inviteId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}


}
