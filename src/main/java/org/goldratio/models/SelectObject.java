package org.goldratio.models;

/** 
 * ClassName: SelectObject <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 15, 2013 10:35:33 AM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public class SelectObject {
	private Long content;
	private boolean selected;
	
	public SelectObject() {
	}
	
	public SelectObject(Long content, boolean selected) {
		this.content = content;
		this.selected = selected;
	}
	
	public Long getContent() {
		return content;
	}
	public void setContent(Long content) {
		this.content = content;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
