package org.goldratio.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "todoList")
public class TodoList  extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2027515822240181184L;
	private String title;
	private Long authorId;
	private Long projectId;
	private Date createTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="projectId", insertable=false, updatable=false)
	private Project project;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="authorId", insertable=false, updatable=false)
	private User author;
	
	
	@OneToMany
	@JoinColumn(name="todoListId")
	private List<TodoItem> todoItems;


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

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public List<TodoItem> getTodoItems() {
		return todoItems;
	}

	public void setTodoItems(List<TodoItem> todoItems) {
		this.todoItems = todoItems;
	}
}
