package org.goldratio.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "teamUserRole")
public class TeamUserRole implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5294593333626185434L;
	static final int GUEST = 0;
	static final int MEMBER = 0;
	static final int ADMIN = 0;
	
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
