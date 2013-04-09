package org.goldratio.repositories;

import java.util.List;

import org.goldratio.models.TodoItem;
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

public interface TodoItemRepository extends CrudRepository<TodoItem, Long> {
	
	TodoItem findById(long id);
	
	List<TodoItem> findByTodoListId(long projectId);
	
}
