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

public class TeamRolePk  implements Serializable {  
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long teamId;  
  
    private Long userId;  
  
    public TeamRolePk() {  
  
    }  
  
    public TeamRolePk(Long teamId, Long userId) {  
        this.teamId = teamId;  
        this.userId = userId;  
    }  
  

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
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
        result = PRIME * result + ((userId == null) ? 0 : userId.hashCode());  
        result = PRIME * result + ((teamId == null) ? 0 : teamId.hashCode());  
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
        final TeamRolePk other = (TeamRolePk) obj;  
        if (teamId == null) {  
            if (other.teamId != null)  
                return false;  
        } else if (!teamId.equals(other.teamId))  
            return false;  
        if (userId == null) {  
            if (other.userId != null)  
                return false;  
        } else if (!userId.equals(other.userId))  
            return false;  
        return true;  
    }  
  
}  