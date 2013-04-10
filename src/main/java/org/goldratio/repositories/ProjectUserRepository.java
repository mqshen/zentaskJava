package org.goldratio.repositories;

import org.goldratio.models.ProjectUser;
import org.goldratio.models.TeamRolePk;
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

public interface ProjectUserRepository extends CrudRepository<ProjectUser, TeamRolePk> {
	
}
