package org.goldratio.models;

import java.io.Serializable;
import java.nio.file.spi.FileTypeDetector;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ClassName: Message <br/>
 * Function: <br/>
 
 * date: Apr 2, 2013 5:24:37 PM <br/>
 * 
 * @author GoldRatio
 * @version 1.0
 */

@Entity
@Table(name = "file")
public class File extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2027515822240181184L;
	private String url;
	private String name;
	private Long authorId;
	private Long projectId;
	private Long ownId;
	private OwnType ownType;
	private FileType fileType;
	private Date createTime;
	private String contentType;

	@ManyToOne(optional=false,fetch=FetchType.LAZY) 
	@JoinColumn(name="authorId", insertable=false, updatable=false)
	private User author;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Long getOwnId() {
		return ownId;
	}

	public void setOwnId(Long ownId) {
		this.ownId = ownId;
	}

	public OwnType getOwnType() {
		return ownType;
	}

	public void setOwnType(OwnType ownType) {
		this.ownType = ownType;
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

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	public boolean isImageFile() {
		return this.fileType == FileType.IMAGE;
	}

	@Override
	public Long getTeamId() {
		// TODO Auto-generated method stub
		/** 
		 * getTeamId:(这里用一句话描述这个方法的作用). <br/> 
		 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
		 * 
		 * @author GoldRatio 
		 * date: Apr 20, 2013 10:55:58 AM <br/> 
		 */
		return null;
	}
}
