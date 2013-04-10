package org.goldratio.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/** 
 * ClassName: InviteUser <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 8, 2013 4:38:54 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

@Entity
@Table(name = "inviteUser")
public class InviteUser {
	@Id
	private String hashCode;
	private String email;
	private Long inviteId;
	private Long teamId;
	private int role;
	
	@OneToMany
	@JoinColumn(name="inviteId", insertable=false, updatable=false)
	private List<InviteProject> inviteProjects;
	
	public String getHashCode() {
		return hashCode;
	}
	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getInviteId() {
		return inviteId;
	}
	public void setInviteId(Long inviteId) {
		this.inviteId = inviteId;
	}
	public List<InviteProject> getInviteProjects() {
		return inviteProjects;
	}
	public void setInviteProjects(List<InviteProject> inviteProjects) {
		this.inviteProjects = inviteProjects;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
}
