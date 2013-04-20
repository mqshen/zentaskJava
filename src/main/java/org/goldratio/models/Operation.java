package org.goldratio.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** 
 * ClassName: Operation <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 10, 2013 3:37:14 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */
@Entity
@Table(name = "operation")
public class Operation extends BaseModel implements Serializable {
	private static final String[] OperationTypeArray = {
		"创建",
		"删除",
		"编辑",
		"查询",
		"回复",
		"开始",
		"暂停",
		"完成"
	};
	private static final String[] TargetTypeArray = {
		"项目",
		"讨论",
		"评论",
		"任务列表",
		"任务",
		"用户"
	};
	
	public enum OperationType {
		create, 
		delete, 
		edit, 
		query, 
		reply, 
		start, 
		pause, 
		complete
	}
	
	public enum TargetType {
		project,
		message,
		comment,
		todoList,
		todoItem,
		user
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2722609918625686861L;
	
	private Long authorId;
	private Date createTime;
	private OperationType opType;
	private TargetType targetType;
	private Long targetId;
	private String title;
	private String digest;
	private Long teamId;
	private Long projectId;
	
	@ManyToOne(optional=false) 
	@JoinColumn(name="authorId", insertable=false, updatable=false)
	private User author;
	
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
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
	public OperationType getOpType() {
		return opType;
	}
	public void setOpType(OperationType opType) {
		this.opType = opType;
	}
	public TargetType getTargetType() {
		return targetType;
	}
	public void setTargetType(TargetType targetType) {
		this.targetType = targetType;
	}
	public Long getTargetId() {
		return targetId;
	}
	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOperationStr() {
		return OperationTypeArray[this.opType.ordinal()] + TargetTypeArray[this.targetType.ordinal()];
	}
	
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getTargetUrl() {
		switch (this.targetType) {
			case project:
				return "/project";
			case message:
			case comment:
				return "/project/" + this.getProjectId() + "/message";
			case todoList:
			case todoItem:
				return "/project/" + this.getProjectId() + "/todoList";
			default:
				return "";
		}
	}
	public boolean hasDigest() {
		return this.targetType == TargetType.comment;
	}
}
