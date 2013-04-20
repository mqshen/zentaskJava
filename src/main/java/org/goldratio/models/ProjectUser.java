package org.goldratio.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/** 
 * ClassName: UserTeamRole <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 7, 2013 7:47:16 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */
@Entity
@Table(name = "projectUserRel")
@IdClass(ProjectUserPk.class)
public class ProjectUser implements Serializable{
	public ProjectUser() {
		
	}
	
	public ProjectUser(Long projectId, Long userId) {
		super();
		this.projectId = projectId;
		this.userId = userId;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 8723525764267891369L;
	@Id
	private Long projectId;
	@Id
    private Long userId;
    
    
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
