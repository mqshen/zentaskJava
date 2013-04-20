package org.goldratio.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * ClassName: Message <br/>
 * Function: <br/>
 * Reason: <br/>
 * date: Apr 2, 2013 5:24:37 PM <br/>
 * 
 * @author GoldRatio
 * @version 1.0
 */

@Entity
@Table(name = "todoItem")
@JsonIgnoreProperties(value={"todoList", "author"})
public class TodoItem  extends BaseModel implements Serializable {
	
	public static final int CREATE = 0;
	public static final int PAUSE = 1;
	public static final int RUNNING = 2;
	public static final int DONE = 3;
	/**
	 * 
	 */
	private static final long serialVersionUID = -2027515822240181184L;
	private String title;
	private Long authorId;
	private Long todoListId;
	private Long workerId;
	
	private Date deadLine;
	
	private Date createTime;

	private Long projectId;
	private Long teamId;
	private Integer status = CREATE;

	@ManyToOne(optional=false, fetch = FetchType.LAZY) 
	@JoinColumn(name="authorId", insertable=false, updatable=false)
	private User author;
	
	@ManyToOne(optional=false, fetch = FetchType.LAZY) 
	@JoinColumn(name="workerId", insertable=false, updatable=false)
	private User worker;

	@ManyToOne(optional=false, fetch = FetchType.LAZY) 
	@JoinColumn(name="todoListId", insertable=false, updatable=false)
	private TodoList todoList;
	

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

	public Long getTodoListId() {
		return todoListId;
	}

	public void setTodoListId(Long todoListId) {
		this.todoListId = todoListId;
	}

	public Long getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getWorker() {
		return worker;
	}

	public void setWorker(User worker) {
		this.worker = worker;
	}

	public TodoList getTodoList() {
		return todoList;
	}

	public void setTodoList(TodoList todoList) {
		this.todoList = todoList;
	}

	public boolean isDone() {
		return status == DONE;
	}

	public void setDone() {
		this.status = DONE;
	}
	public boolean isRunning() {
		return status == RUNNING;
	}
	
	public void setRunning() {
		this.status = RUNNING;
	}
	
	public boolean isPause() {
		return status == PAUSE;
	}
	public void setPause() {
		this.status = PAUSE;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

}
