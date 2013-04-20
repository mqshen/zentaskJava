package org.goldratio.repositories;

import java.util.List;

import org.goldratio.models.TodoItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
/** 
 * ClassName: UserRepository <br/> 
 * Function: TODO <br/> 
 * Reason: TODO <br/> 
 * date: Mar 27, 2013 2:53:11 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public interface TodoItemRepository extends CrudRepository<TodoItem, Long> {
	
	TodoItem findById(long id);
	
	List<TodoItem> findByTodoListId(long projectId);
	
	@Query(value = "select t from TodoItem t JOIN t.worker u" +
			" where t.teamId = :teamId" + 
			" and u.id = :userId" )
	List<TodoItem> findByTeamIdAndUserId(@Param("teamId") long teamId, @Param("userId") Long userId);
}
