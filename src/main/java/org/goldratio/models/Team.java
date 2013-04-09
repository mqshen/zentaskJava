package org.goldratio.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/** 
 * ClassName: Project <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 2, 2013 2:37:21 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

@Entity
@Table(name = "project")
@JsonIgnoreProperties(value={"invitedMembers"})
public class Team  extends BaseModel implements Serializable{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = -2027515822240181184L;
    private String title;
    private Long authorId;
	private Date createTime;
	
	@ManyToOne(optional=false) 
	@JoinColumn(name="authorId", insertable=false, updatable=false)
	private User author;
	
	@OneToMany(fetch= FetchType.LAZY)
	@JoinColumn(name="teamId")
	private List<Project> projects;
	
	@ManyToMany 
    @JoinTable(name="teamUserRel", 
          joinColumns=@JoinColumn(name="teamId"),
          inverseJoinColumns=@JoinColumn(name="userId"))
	private List<User> members;

	@OneToMany(fetch= FetchType.LAZY)
	@JoinColumn(name="teamId")
	private List<InviteUser> invitedMembers;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public List<InviteUser> getInvitedMembers() {
		return invitedMembers;
	}

	public void setInvitedMembers(List<InviteUser> invitedMembers) {
		this.invitedMembers = invitedMembers;
	}
	
}
