package org.goldratio.repositories;
import org.goldratio.models.User;
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

public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);
	
	User findById(long userId);
}
