package org.goldratio.models;

import java.io.Serializable;


/** 
 * ClassName: InviteProjectPk <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 8, 2013 5:12:42 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public class InviteProjectPk implements Serializable {  
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long inviteId;  
  
    private Long projectId;  
  
    public InviteProjectPk() {  
  
    }  
  
    public InviteProjectPk(Long inviteId, Long porojectId) {  
        this.inviteId = inviteId;  
        this.projectId = porojectId;  
    }  
  
  
    public Long getInviteId() {
		return inviteId;
	}

	public void setInviteId(Long inviteId) {
		this.inviteId = inviteId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Override  
    public int hashCode() {  
        final int PRIME = 31;  
        int result = 1;  
        result = PRIME * result + ((projectId == null) ? 0 : projectId.hashCode());  
        result = PRIME * result + ((inviteId == null) ? 0 : inviteId.hashCode());  
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
        final InviteProjectPk other = (InviteProjectPk) obj;  
        if (inviteId == null) {  
            if (other.inviteId != null)  
                return false;  
        } else if (!inviteId.equals(other.inviteId))  
            return false;  
        if (projectId == null) {  
            if (other.projectId != null)  
                return false;  
        } else if (!projectId.equals(other.projectId))  
            return false;  
        return true;  
    }  
  
}  
