package org.goldratio.services.impl;

import org.goldratio.models.TodoItem;
import org.goldratio.repositories.TodoItemRepository;
import org.goldratio.services.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** 
 * ClassName: TodoListServiceImpl <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 10, 2013 7:51:28 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */
@Service("todoItemService")
public class TodoItemServiceImpl implements TodoItemService {
	@Autowired
	private TodoItemRepository todoItemRepository;

	@Override
	public void create(TodoItem todoItem) {
		todoItemRepository.save(todoItem);
	}

}
