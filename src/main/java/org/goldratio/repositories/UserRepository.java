package org.goldratio.repositories;
import org.goldratio.models.User;
import org.springframework.data.jpa.repository.Query;
/** 
 * ClassName: UserRepository <br/> 
 * Function: TODO <br/> 
 * Reason: TODO <br/> 
 * date: Mar 27, 2013 2:53:11 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);
	
	User findById(long userId);
	
	
	@Query(value = "select a from User a JOIN a.teams b where a.email = :email and b.id = :teamId")
	User findByEmailAneTeamId(@Param("email") String email, @Param("teamId") Long teamId);
}
