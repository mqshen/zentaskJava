package org.goldratio.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
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
	private Long authorId;
	private Long ownId;
	private OwnType ownType;
	private Date createTime;

	@ManyToOne(optional=false) 
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

	
}
