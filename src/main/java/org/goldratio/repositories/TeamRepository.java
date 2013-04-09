package org.goldratio.repositories;

import org.goldratio.models.Team;
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

public interface TeamRepository extends CrudRepository<Team, Long> {
	
	Team findById(long id);
	
}
