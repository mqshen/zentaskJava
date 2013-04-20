package org.goldratio.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.goldratio.util.ZenTaskUtil;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Created with IntelliJ IDEA.
 * User: GoldRatio
 * Date: 3/27/13
 * Time: 10:22 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "user")
@JsonIgnoreProperties(value={"password", "salt", "teams", "hibernateLazyInitializer", "handler", "fieldHandler"})
public class User  extends BaseModel implements Serializable{

	public static final int GUEST = 0;
	public static final int USER = 1;
	public static final int ADMIN = 2;
	
	private static String[] ROLE_NAME_ARRAY = {
			"访客",
			"成员",
			"管理员"
	};
	/**
	 * 
	 */
	private static final long serialVersionUID = -8915908399902917196L;
	static final String SALT_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static final int SALT_LENGTH = 10;
    private String name;
    private String email;
    private String salt;
    private String password;
    private String nickName;
    private String description;
    private String avatarUrl;
    

	@ManyToMany(fetch= FetchType.LAZY)
    @JoinTable(name="teamUserRel", 
          joinColumns=@JoinColumn(name="userId", referencedColumnName="id"),
          inverseJoinColumns=@JoinColumn(name="teamId", referencedColumnName="id"))
	private List<Team> teams;
	
	
    
    @Transient
    private int role;
    
    @Transient
    private Long currentTeamId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    
    public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void encryptPass( String password ) {
		StringBuffer strBuffer = new StringBuffer();
    	strBuffer.append(password).append(this.getSalt());
        String passwordEncrypt = ZenTaskUtil.getMD5Str(strBuffer.toString());
        setPassword(passwordEncrypt);
    }

    public boolean authPass( String password ) {
    	StringBuffer strBuffer = new StringBuffer();
    	strBuffer.append(password).append(this.getSalt());
        String passwordEncrypt = ZenTaskUtil.getMD5Str(strBuffer.toString());
        return getPassword().equals(passwordEncrypt);
    }
    
    @Transient
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

    public List<Team> getTeams() {
		return teams;
	}

    @Transient
	public Long getCurrentTeamId() {
		return currentTeamId;
	}

	public void setCurrentTeamId(Long currentTeamId) {
		this.currentTeamId = currentTeamId;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public boolean isAdmin() {
		return role == ADMIN;
	}

	public String getRoleName() {
		return ROLE_NAME_ARRAY[this.role];
	}
	@Transient
	private Long teamId;
	@Transient
	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
}
