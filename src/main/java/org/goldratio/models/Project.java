package org.goldratio.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@JsonIgnoreProperties({"members","messages","todoLists"})
public class Project  extends BaseModel implements Serializable{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = -2027515822240181184L;
    private String title;
    private String content;
	private Long authorId;
	private Long teamId;
	private Date createTime;
	
	/**
	 * 所有讨论
	 */
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="projectId")
	private List<Message> messages;
	
	@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="projectUserRel", 
          joinColumns=@JoinColumn(name="projectId"),
          inverseJoinColumns=@JoinColumn(name="userId"))
	private List<User> members;
	
	/**
	 * 所有讨论
	 */
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="projectId")
	private List<TodoList> todoLists;
	
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
	public Long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	public List<TodoList> getTodoLists() {
		return todoLists;
	}
	public void setTodoLists(List<TodoList> todoLists) {
		this.todoLists = todoLists;
	}
	public List<User> getMembers() {
		return members;
	}
	public void setMembers(List<User> members) {
		this.members = members;
	}
}
