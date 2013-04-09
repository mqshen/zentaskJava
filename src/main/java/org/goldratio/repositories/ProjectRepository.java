package org.goldratio.repositories;

import java.util.List;

import org.goldratio.models.Project;
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

public interface ProjectRepository extends CrudRepository<Project, Long> {
	
	Project findById(long id);
	
	List<Project> findByAuthorId(long authorId);
	
	List<Project> findByTeamId(long teamId);
}
