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

import org.hibernate.validator.constraints.NotBlank;

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
@Table(name = "message")
@JsonIgnoreProperties(value={"project", "author", "comments"})
public class Message extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2027515822240181184L;
	@NotBlank
	private String title;
	@NotBlank
	private String content;
	private Long authorId;
	private Long projectId;
	private Date createTime;

	@ManyToOne(optional=false) 
	@JoinColumn(name="projectId", insertable=false, updatable=false)
	private Project project;

	@ManyToOne(optional=false) 
	@JoinColumn(name="authorId", insertable=false, updatable=false)
	private User author;
	
	private int commentNum;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="messageId")
	private List<Comment> comments;

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}


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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	

}
