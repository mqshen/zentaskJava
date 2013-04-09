package org.goldratio.models;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@JsonIgnoreProperties(value={"password", "salt"})
public class User  extends BaseModel implements Serializable{

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

	public void encryptPass( String password ) throws NoSuchAlgorithmException, InvalidKeySpecException {
		Random random = new Random();
		StringBuffer strBuffer = new StringBuffer();
		for(int i = 0; i < SALT_LENGTH; ++i) {
			strBuffer.append(SALT_STRING.charAt(random.nextInt(SALT_STRING.length())));
		}
        setSalt( salt );
        KeySpec spec = new PBEKeySpec(password.toCharArray(), getSalt().getBytes(), 65536, 128);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        setPassword( new String(f.generateSecret(spec).getEncoded() ));
    }

    public boolean authPass( String password ) {
    	StringBuffer strBuffer = new StringBuffer();
    	strBuffer.append(password).append(this.getSalt());
        String passwordEncrypt = ZenTaskUtil.getMD5Str(strBuffer.toString());
        return getPassword().equals(passwordEncrypt);
    }
    
    
}
