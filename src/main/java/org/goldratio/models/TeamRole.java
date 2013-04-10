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
@Table(name = "teamUserRel")
@IdClass(TeamRolePk.class)
public class TeamRole {

	@Id
	private Long teamId;  
	  
	@Id
    private Long userId;

	private int role;

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
}
