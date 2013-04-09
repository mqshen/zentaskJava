package org.goldratio.repositories;

import java.util.List;

import org.goldratio.models.Comment;
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

public interface CommentRepository extends CrudRepository<Comment, Long> {
	
	Comment findById(long id);
	
	List<Comment> findByMessageId(long messageId);
	
}
