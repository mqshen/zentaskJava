package org.goldratio.repositories;

import org.goldratio.models.InviteProject;
import org.goldratio.models.InviteProjectPk;
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

public interface InviteProjectRepository extends CrudRepository<InviteProject, InviteProjectPk> {
	
	InviteProject findByInviteId(Long inviteId);
	
}
