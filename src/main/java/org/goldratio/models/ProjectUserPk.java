package org.goldratio.models;

import java.io.Serializable;


/** 
 * ClassName: TeamRolePk <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 9, 2013 2:24:05 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public class ProjectUserPk  implements Serializable {  
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long projectId;  
  
    private Long userId;  
  
    public ProjectUserPk() {  
  
    }  
  
    public ProjectUserPk(Long projectId, Long userId) {  
        this.projectId = projectId;  
        this.userId = userId;  
    }  
  

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override  
    public int hashCode() {  
        final int PRIME = 31;  
        int result = 1;  
        result = PRIME * result + ((projectId == null) ? 0 : projectId.hashCode());  
        result = PRIME * result + ((userId == null) ? 0 : userId.hashCode());  
        return result;  
    }  
  
    @Override  
    public boolean equals(Object obj) {  
        if (this == obj)  
            return true;  
        if (obj == null)  
            return false;  
        if (getClass() != obj.getClass())  
            return false;  
        final ProjectUserPk other = (ProjectUserPk) obj;  
        if (projectId == null) {  
            if (other.projectId != null)  
                return false;  
        } else if (!projectId.equals(other.projectId))  
            return false;  
        if (userId == null) {  
            if (other.userId != null)  
                return false;  
        } else if (!userId.equals(other.userId))  
            return false;  
        return true;  
    }  
  
}  