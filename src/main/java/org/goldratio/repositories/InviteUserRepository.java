package org.goldratio.repositories;

import org.goldratio.models.InviteUser;
import org.springframework.data.repository.CrudRepository;
/** 
 * ClassName: UserRepository <br/> 
 * Function: TODO <br/> 
 * Reason: TODO <br/> 
 * date: Mar 27, 2013 2:53:11 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public interface InviteUserRepository extends CrudRepository<InviteUser, String> {
	
	InviteUser findByHashCode(String hashCode);
	
}
